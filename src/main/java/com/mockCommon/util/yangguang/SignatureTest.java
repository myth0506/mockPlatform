package com.mockCommon.util.yangguang;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;



public class SignatureTest {
	
	
	public static void main(String[] args) {
		
		/*
		 *此内容为合租商 生成签名 和 阳光验证前面的方式  
		 *反之 阳光会用用过私钥生成签名串    合作商用阳光公钥进行验证
		 */
		String verifyStr="验证字符：yanzhengzifu";
		
		//生产签名串 和 验证类
		PartnerSignerImpl signer=new PartnerSignerImpl();
		
		//得到私钥
		PrivateKey ygPrivate = KeyPairer.getPrivateKey(KeyPairer.PARTNER_PRIVATE_KEY);
		
		//返回报文签名串  此字符传放入报文的<Sign>标签中
		String sign ="";
		try {
			sign = signer.sign(verifyStr.getBytes("GBK"), ygPrivate);
		} catch (UnsupportedEncodingException e) {}
		//以上为生成签名串的方法
		
		//以下为验证发送报文签名一致的方法
		//获取公钥
		PublicKey aliPublicKey = KeyPairer.getPublicKey(KeyPairer.PARTNER_PUBLIC_KEY);
		//校验签名
		boolean signFlag = false;
		try {
			signFlag = signer.verify(verifyStr.getBytes("GBK"),sign, aliPublicKey);
		} catch (UnsupportedEncodingException e1) {}
		System.out.println("验证结果:"+signFlag);
	}
}