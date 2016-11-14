package com.mockCommon.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

/*
 * 这段程序只用于演示简化的OpenID认证过程，没有完善地检查，并不适合于生产环境中使用。 这个程序用于演示 openid 协议中 consumer
 * 的行为， 主要有一下几个操作： 1. consumer 与 openid server 进行关联（association） 2. consumer
 * 发起认证请求。 3. consumer 对 openid 发送过来的消息进行签名验证，如果签名一致，则认证通过！ 4.
 * check_authentication在以下情况下可以使用：
 * （1）consumer没有assoc_handle/mac_key对，并且直接进行第二步（即发起验证请求
 * ），此时consumer不能校验签名值，只能向服务器发送check_authentication请求。
 * （2）consumer的assoc_handle和服务器返回的assoc_handle不一致，此时consumer需要向openid
 * server发送check_authentication请求。 check_authentication过程中，consumer只需要将 openid
 * 发送过来的消息组织一下，再重新发送给 openid server，即可判断验证是否通过。
 */

@Service
public class OpenId {
	
	private static String openid_server = "https://login.netease.com/openid/";
	/*
	 * 通常来说，在WEB应用中，这个时候你需要把assoc_handle/mac_key保存在一个固定的地方（可以是session或者后端文件，又或者是数据库
	 * ），但一定不能放在cookie里！
	 */
	private String assoc_handle = "";
	private Map<String,String> redirect_data;
	private Map<String,String> auth_response;
	
	@SuppressWarnings("rawtypes")
	public static String MaptoString_url_utf8(Map<String,String> map) {
	
		/*
		 * 将URL的参数以分段的方式进行URL utf8编码，并返回一个字符串
		 */
		String arguments = "?";
		Iterator iter = map.entrySet().iterator();
		boolean first_arg = true;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			String key_str = (String) key;
			String val_str = (String) val;
			
			try {
				key_str = URLEncoder.encode(key_str, "UTF-8");
				val_str = URLEncoder.encode(val_str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (first_arg == false) {
				arguments = arguments + "&";
			}
			first_arg = false;
			
			arguments = arguments + key_str;
			arguments = arguments + "=";
			arguments = arguments + val_str;
		}
		return arguments;
	}
	
	
	public String generateAuthUrl() {
	
		redirect_data = new HashMap<String,String>();
		redirect_data.put("openid.ns", "http://specs.openid.net/auth/2.0");
		redirect_data.put("openid.mode", "checkid_setup");
		redirect_data.put("openid.assoc_handle", assoc_handle);
		//根据实际项目修改openid.return_to
		redirect_data.put("openid.return_to", "http://localhost/index.html");
		redirect_data.put("openid.claimed_id", "http://specs.openid.net/auth/2.0/identifier_select");
		redirect_data.put("openid.identity", "http://specs.openid.net/auth/2.0/identifier_select");
		//根据实际项目修改openid.realm
		redirect_data.put("openid.realm", "http://localhost/");
		redirect_data.put("openid.ns.sreg", "http://openid.net/extensions/sreg/1.1");
		redirect_data.put("openid.sreg.required", "nickname,email,fullname");
		redirect_data.put("openid.ns.ax", "http://openid.net/srv/ax/1.0");
		redirect_data.put("openid.ax.mode", "fetch_request");
		redirect_data.put("openid.ax.type.empno", "https://login.netease.com/openid/empno/");
		redirect_data.put("openid.ax.type.dep", "https://login.netease.com/openid/dep/");
		redirect_data.put("openid.ax.required", "empno,dep");
		
		return openid_server + MaptoString_url_utf8(redirect_data);
	}
	
	/**
	 * @param serverResponse
	 *            ——OpenID server返回的URL
	 * @throws IOException
	 */
	public boolean check_authentication(String serverResponse) throws IOException {
	
		auth_response = new HashMap<String,String>();
		
		/* 将OpenID server返回的URL地址解析，获取参数，参数和值都需要UTF-8解码 */
		/*
		 * 注：这里最好不要先把整个URL进行UTF-8解码，然后再取出每个openid参数和值。
		 * 因为openid.return_to或者openid.realm指定的URL可能需要带参数，这样 会引入 ?, =,
		 * &等特殊符号，从而让openid的参数截取变得困难。
		 */
		
		String[] arrays = serverResponse.split("\\?|&");
		int size = arrays.length;
		for (int i = 0; i < size; i++) {
			int index = arrays[i].indexOf("=");
			if (index == -1) continue;
			String arg = URLDecoder.decode(arrays[i].substring(0, index), "UTF-8");
			String val = URLDecoder.decode(arrays[i].substring(index + 1, arrays[i].length()), "UTF-8");
			auth_response.put(arg, val);
		}
		
		/* 将openid.mode参数的值设置为check_authentication，其他参数和值不变，发回给OpenID server */
		auth_response.put("openid.mode", "check_authentication");
		
		String arguments = MaptoString_url_utf8(auth_response);
		URL url = null;
		try {
			
			url = new URL(openid_server + arguments);
			
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String str = "";
			String auth_result = "";
			
			do {
				try {
					str = r.readLine();
					
					if (str == null) break;
					String[] temp_arrays = str.split(":");
					if (temp_arrays[0].equals("is_valid")) {
						auth_result = temp_arrays[1];
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} while (str != null);
			if (auth_result.equals("true")) {
				return true;
			} else {
				return false;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
	
//		OpenId consumer = new OpenId();
//		 System.out.println(consumer.generateAuthUrl());
		
//		 System.out.println(consumer.check_authentication("http://fa.qa.ms.netease.com/index.html?openid.assoc_handle=%7BHMAC-SHA1%7D%7B5565a1e6%7D%7B4C6Whw%3D%3D%7D&openid.ax.count.dep=0&openid.ax.count.empno=0&openid.ax.mode=fetch_response&openid.ax.type.dep=https%3A%2F%2Flogin.netease.com%2Fopenid%2Fdep%2F&openid.ax.type.empno=https%3A%2F%2Flogin.netease.com%2Fopenid%2Fempno%2F&openid.claimed_id=https%3A%2F%2Flogin.netease.com%2Fopenid%2Ffangxiaobin%2F&openid.identity=https%3A%2F%2Flogin.netease.com%2Fopenid%2Ffangxiaobin%2F&openid.mode=id_res&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.ax=http%3A%2F%2Fopenid.net%2Fsrv%2Fax%2F1.0&openid.ns.sreg=http%3A%2F%2Fopenid.net%2Fextensions%2Fsreg%2F1.1&openid.op_endpoint=https%3A%2F%2Flogin.netease.com%2Fopenid%2F&openid.response_nonce=2015-05-27T10%3A52%3A22ZkHaX1g&openid.return_to=http%3A%2F%2Ffa.qa.ms.netease.com%2Findex.html&openid.sig=kOtN5cuXt5Vt80hNMBK0Rzl2If4%3D&openid.signed=assoc_handle%2Cax.count.dep%2Cax.count.empno%2Cax.mode%2Cax.type.dep%2Cax.type.empno%2Cclaimed_id%2Cidentity%2Cmode%2Cns%2Cns.ax%2Cns.sreg%2Cop_endpoint%2Cresponse_nonce%2Creturn_to%2Csigned%2Csreg.email%2Csreg.fullname%2Csreg.nickname&openid.sreg.email=fangxiaobin%40corp.netease.com&openid.sreg.fullname=fangxiaobin&openid.sreg.nickname=fangxiaobin"));
		
	}
}
