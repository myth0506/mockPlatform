package com.mockCommon.service.mock.yangguang.impl;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.yangguang.ResponsePackageModel;
import com.mockCommon.model.mock.yangguang.SunshineVerifyCodeMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.yangguang.SunshineVerifyCodeMockService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.service.web.yangguang.SunshineIniService;
import com.mockCommon.util.WipeTabEnterSpaceUtil;
import com.mockCommon.util.freeMarker.IDataMaker;
import com.mockCommon.util.yangguang.KeyPairer;
import com.mockCommon.util.yangguang.PartnerSignerImpl;
import com.netease.common.util.StringUtil;

@Service("sunshineVerifyCodeServiceImpl")
public class SunshineVerifyCodeMockServiceImpl implements SunshineVerifyCodeMockService {

	@Autowired
	private SunshineIniService sunshineIniServiceImpl;
	
	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigService;
	
	@Autowired
	private IDataMaker<ResponsePackageModel> packageFreeMarker;
	
	@Autowired
	private IDataMaker<SunshineVerifyCodeMockModel> sunshineVerifyCodeFreeMarker;
	
	/*
	 * 手机验证说明 
	 * 在120中新增加节点IsIdVerifi,如果120返回报文中,IsIdVerifi为1则直接调用127接口进行验证即可
	 * ,无需调用此接口,只有在短信未发送到用户手机或用户多次输入验证码错误,需要重新获取验证码的时候才调用此接口.
	 */
	@Override
	public String verifyCode(String code) {
		String result = null;
		SunshineVerifyCodeMockModel sunshineVerifyCodeMockModel = new SunshineVerifyCodeMockModel();
		ResponsePackageModel responsePackage = new ResponsePackageModel();
		
		String verifyCodeStatus = null;
		String verifyCode = null;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_AUDIT_SMS);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			verifyCodeStatus = ini.getIniValue();
		}
		if (!StringUtil.isEmpty(code)) {
			if("1".equals(verifyCodeStatus)){
				ini.setIniName(SessionKey.SUNSHINE_AUDIT_SMS_CODE);
				ini = sunshineBusiConfigService.selectIni(ini);
				if(ini != null){
					verifyCode = ini.getIniValue();
				}
				if(code.equals(verifyCode)){  //手机验证成功
					sunshineVerifyCodeMockModel.setResponseCode("success");
					sunshineVerifyCodeMockModel.setResponseMessage("验证成功");
				}else{
					sunshineVerifyCodeMockModel.setResponseCode("failed");
					sunshineVerifyCodeMockModel.setResponseMessage("输入的验证码不匹配，请重新输入！");
				}
			}else{  // 不需要手机验证
				sunshineVerifyCodeMockModel.setResponseCode("failed");
				sunshineVerifyCodeMockModel.setResponseMessage("系统设置错误！");
			}
			responsePackage.setRequestType("127");
		} else {
			if(!StringUtil.isEmpty(verifyCodeStatus)){
				if("1".equals(verifyCodeStatus)){
					sunshineVerifyCodeMockModel.setResponseCode("success");
					sunshineVerifyCodeMockModel.setResponseMessage("验证成功");
				}else{
					sunshineVerifyCodeMockModel.setResponseCode("failed");
					sunshineVerifyCodeMockModel.setResponseMessage("输入的验证码不匹配，请重新输入！");
				}
			}else{
				sunshineVerifyCodeMockModel.setResponseCode("failed");
				sunshineVerifyCodeMockModel.setResponseMessage("mock参数为空！");
			}
			responsePackage.setRequestType("126");
		}
		
		// 生成最终返回数据
		String packageBody = sunshineVerifyCodeFreeMarker
				.generateData2output(ContextConstant.PREFIX_SUNSHINE_VERIFYCODE,
						sunshineVerifyCodeMockModel);
		LogConstant.debugLog.info("阳光保险mock手机验证接口数据报文体：\n" + packageBody);

		responsePackage.setInsureType("100");
		responsePackage.setStatus("100");
		
		Date now = new Date();
		Timestamp stamp = new Timestamp(now.getTime());
		responsePackage.setSendTime(stamp);
		
		//灾备测试
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.GVCZB_YG);
		iniZb = sunshineIniServiceImpl.selectIni(iniZb);
		String zbValueGVC = iniZb.getIniValue();
		
		BusinessIni iniZbSave = new BusinessIni();
		iniZbSave.setIniName(SessionKey.SVCZB_YG);
		iniZbSave = sunshineIniServiceImpl.selectIni(iniZbSave);
		String zbValueSVC = iniZbSave.getIniValue();
		
		if(zbValueGVC != null && zbValueGVC.equals("delay") && code == null){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.GVCZB_YG_DT);
			iniDelayTime = sunshineIniServiceImpl.selectIni(iniDelayTime);
			int delayTimeValue = 1000;
			if(iniDelayTime != null){
				delayTimeValue = Integer.parseInt(iniDelayTime.getIniValue());
			}
			try {
				Thread.sleep(delayTimeValue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(zbValueSVC != null && zbValueSVC.equals("delay") && code != null){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.SVCZB_YG_DT);
			iniDelayTime = sunshineIniServiceImpl.selectIni(iniDelayTime);
			int delayTimeValue = 1000;
			if(iniDelayTime != null){
				delayTimeValue = Integer.parseInt(iniDelayTime.getIniValue());
			}
			try {
				Thread.sleep(delayTimeValue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(zbValueGVC != null && zbValueGVC.equals("failure")){
			responsePackage.setStatus("400");
			responsePackage.setErrorMessage("获取验证码失败");
			packageBody="";
		}else if(zbValueSVC != null && zbValueSVC.equals("failure")){
			responsePackage.setStatus("400");
			responsePackage.setErrorMessage("保存验证码失败");
			packageBody="";
		}
		
		// 生产签名串 和 验证类
		PartnerSignerImpl signer = new PartnerSignerImpl();
		// 得到私钥
		PrivateKey ygPrivate = KeyPairer
				.getPrivateKey(KeyPairer.PARTNER_PRIVATE_KEY);
		String sign = null;
		try {
			packageBody = WipeTabEnterSpaceUtil.wipe(packageBody);
			responsePackage.setPackageBody(packageBody);
			sign = signer.sign(packageBody.getBytes("GBK"), ygPrivate);
		} catch (UnsupportedEncodingException e) {
			LogConstant.debugLog.info("阳光保险mock手机验证接口签名编码异常：" + e.getMessage());
			e.printStackTrace();
		}
		responsePackage.setSign(sign);
		result = packageFreeMarker.generateData2output(
				ContextConstant.PREFIX_RESPONSE_PACKAGE, responsePackage);
		LogConstant.debugLog.info("阳光保险mock手机验证接口返回数据：\n" + result);
		
		return result;
	}
}