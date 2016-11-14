package com.mockCommon.service.mock.yangguang.impl;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.yangguang.ResponsePackageModel;
import com.mockCommon.model.mock.yangguang.SunshinePaymentMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.yangguang.SunshinePaymentMockService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.service.web.yangguang.SunshineIniService;
import com.mockCommon.util.ParseXmlUtil;
import com.mockCommon.util.WipeTabEnterSpaceUtil;
import com.mockCommon.util.freeMarker.IDataMaker;
import com.mockCommon.util.yangguang.KeyPairer;
import com.mockCommon.util.yangguang.PartnerSignerImpl;
import com.netease.common.util.StringUtil;

@Service("sunshinePaymentMockServiceImpl")
public class SunshinePaymentMockServiceImpl implements
		SunshinePaymentMockService {

	@Autowired
	private SunshineIniService sunshineIniServiceImpl;
	
	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigService;
	
	@Autowired
	private IDataMaker<ResponsePackageModel> packageFreeMarker;
	
	@Autowired
	private IDataMaker<SunshinePaymentMockModel> sunshinePaymentFreeMarker;
	
	@Override
	public String payment(String xmlParams) {
		LogConstant.debugLog.info("阳光保险payment：");
		String result = null;
		SunshinePaymentMockModel SunshinePaymentMockModel = new SunshinePaymentMockModel();
		ResponsePackageModel responsePackage = new ResponsePackageModel();
		
		String tBOrderId = ParseXmlUtil.queryElementByXPath(xmlParams,
				"/PackageList/Package/Request/Order/TBOrderId").getTextTrim();
		String payAmount = ParseXmlUtil.queryElementByXPath(xmlParams,
				"/PackageList/Package/Request/Order/Premium").getTextTrim();
		String bizProposalNo = ParseXmlUtil.queryElementByXPath(xmlParams,
				"/PackageList/Package/Request/Order/SubOrderList/SubOrder[starts-with(@type, \"biz\")]/ProposalNo").getTextTrim();
		String forceProposalNo = ParseXmlUtil.queryElementByXPath(xmlParams,
				"/PackageList/Package/Request/Order/SubOrderList/SubOrder[starts-with(@type, \"force\")]/ProposalNo").getTextTrim();
		String bizPolicyNo = "1085105092015000096";
		String forcePolicyNo = "1085105072015000093";
		
		LogConstant.debugLog.info("阳光保险tBOrderId：" + tBOrderId);
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		String payTime = sdf.format(now);
		SunshinePaymentMockModel.settBOrderId(tBOrderId);
		SunshinePaymentMockModel.setPayAmount(payAmount);
		SunshinePaymentMockModel.setBizProposalNo(bizProposalNo);
		SunshinePaymentMockModel.setForceProposalNo(forceProposalNo);
		SunshinePaymentMockModel.setBizPolicyNo(bizPolicyNo);
		SunshinePaymentMockModel.setForcePolicyNo(forcePolicyNo);
		SunshinePaymentMockModel.setPayTime(payTime);
		
		LogConstant.debugLog.info("阳光保险payTime：" + payTime);
		
		String paymentStatus = null;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.CD_RET_STATUS);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			paymentStatus = ini.getIniValue();
		}
		LogConstant.debugLog.info("阳光保险paymentStatus111：" + paymentStatus);
		if(!StringUtil.isEmpty(paymentStatus)){
			if(paymentStatus.equals("0")){ // 出单成功
				responsePackage.setStatus("100");
			}else{
				responsePackage.setStatus("400");
			}
		}else{
			responsePackage.setStatus("400");
		}
		LogConstant.debugLog.info("阳光保险paymentStatus：" + paymentStatus);
		// 生成最终返回数据
		String packageBody = sunshinePaymentFreeMarker
				.generateData2output(ContextConstant.PREFIX_SUNSHINE_PAYMENT,
						SunshinePaymentMockModel);
		LogConstant.debugLog.info("阳光保险mock出单接口数据报文体：\n" + packageBody);

		
		//灾备测试
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.CDZB_YG);
		iniZb = sunshineIniServiceImpl.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
				
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.CDZB_YG_DT);
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
		}else if(zbValue != null && zbValue.equals("failure")){
			responsePackage.setStatus("400");
			responsePackage.setErrorMessage("出单失败");
			packageBody="";
		}
		
		
		responsePackage.setRequestType("130");
		responsePackage.setInsureType("100");
		
		Timestamp stamp = new Timestamp(now.getTime());
		responsePackage.setSendTime(stamp);
		
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
			LogConstant.debugLog.info("阳光保险mock出单接口签名编码异常：" + e.getMessage());
			e.printStackTrace();
		}
		responsePackage.setSign(sign);
		result = packageFreeMarker.generateData2output(
				ContextConstant.PREFIX_RESPONSE_PACKAGE, responsePackage);
		LogConstant.debugLog.info("阳光保险mock出单接口返回数据：\n" + result);
		
		return result;
	}
}