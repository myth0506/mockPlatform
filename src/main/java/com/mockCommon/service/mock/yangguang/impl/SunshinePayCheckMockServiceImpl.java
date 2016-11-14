package com.mockCommon.service.mock.yangguang.impl;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.yangguang.ResponsePackageModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.yangguang.SunshinePayCheckMockService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.service.web.yangguang.SunshineIniService;
import com.mockCommon.util.ParseXmlUtil;
import com.mockCommon.util.WipeTabEnterSpaceUtil;
import com.mockCommon.util.freeMarker.IDataMaker;
import com.mockCommon.util.yangguang.KeyPairer;
import com.mockCommon.util.yangguang.PartnerSignerImpl;

@Service("sunshinePayCheckMockServiceImpl")
public class SunshinePayCheckMockServiceImpl implements
		SunshinePayCheckMockService {

	@Autowired
	private SunshineIniService sunshineIniServiceImpl;
	
	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigService;
	@Autowired
	private IDataMaker<ResponsePackageModel> packageFreeMarker;
	@Override
	public String payCheck(String xml) {
		String result = null;
		int totalPremium = 0;
		int forcePremium = 0;
		int bizPremium = 0;
		ResponsePackageModel packageModel = new ResponsePackageModel();
		StringBuilder builder = new StringBuilder();
		if(StringUtils.isEmpty(xml)){
			LogConstant.debugLog.error("[SunshinePayCheckMockServiceImpl-->payCheck] xml can not be empty!");
			return null;
		}
		
		builder.append("<Response>").append("<TagsList>").append("<Tags>");
		String[] feeArray = {"forcePremium", "vehicleTaxPremium", "bizTotalPremium"};
		for(String fee : feeArray){
			String premium = getPremiumByName(fee, true);
			String desc = getIniDesc(fee);
			builder.append("<Tag>").append("<Definition name=\"type\">label</Definition>").append("<Definition name=\"key\">").append(fee).append("</Definition>")
				.append("<Definition name=\"label\">").append(desc).append("</Definition>")
				.append("<Definition name=\"value\"></Definition>").append("<Definition name=\"premium\">").append(premium).append("</Definition>").append("</Tag>");
			totalPremium += Integer.parseInt(premium);
			if(!fee.equals("bizTotalPremium")){
				forcePremium += Integer.parseInt(premium);
			}else{
				bizPremium += Integer.parseInt(premium);
			}
		}
		builder.append("<Tag>").append("<Definition name=\"type\">label</Definition>").append("<Definition name=\"key\">totalPremium</Definition>")
			.append("<Definition name=\"label\">网购总价</Definition>")
			.append("<Definition name=\"value\"></Definition>").append("<Definition name=\"premium\">").append(totalPremium).append("</Definition>").append("</Tag>");
		builder.append("</Tags>").append("</TagsList>");
		Element tbOrderIdEle = ParseXmlUtil.queryElementByXPath(xml, 
				"/PackageList/Package/Request/Order/TBOrderId");
		String tBOrderId = tbOrderIdEle.getTextTrim();
		/*Element forceIdEle = ParseXmlUtil.queryElementByXPath(xml, 
				"/PackageList/Package/Request/Order/SubOrderList/SubOrder[@type=\"force\"]/TBPrderId");
		String forceId = forceIdEle.getTextTrim();
		Element bizIdEle = ParseXmlUtil.queryElementByXPath(xml, 
				"/PackageList/Package/Request/Order/SubOrderList/SubOrder[@type=\"biz\"]/TBPrderId");
		String bizId = bizIdEle.getTextTrim();*/
		builder.append("<Order>").append("<TBOrderId>").append(tBOrderId).append("</TBOrderId>")
			.append("<Premium>").append(totalPremium).append("</Premium>").append("<PayNo></PayNo>")
			.append("<SubOrderList>");
		String[] orderArr = {"sunshineBizProposalNo", "sunshineForceProposalNo"};
		for(String order : orderArr){
			String value = getPremiumByName(order, false);
			if(order.equals("sunshineBizProposalNo")){
				builder.append("<SubOrder type=\"biz\">").append("<TBOrderId>").append(tBOrderId).append("</TBOrderId>")
					.append("<ProposalNo>").append(value).append("</ProposalNo>").append("<Premium>").append(bizPremium).append("</Premium>");
			}else{
				builder.append("<SubOrder type=\"force\">").append("<TBOrderId>").append(tBOrderId).append("</TBOrderId>")
				.append("<ProposalNo>").append(value).append("</ProposalNo>").append("<Premium>").append(forcePremium).append("</Premium>");
			}
			builder.append("</SubOrder>");
		}
		builder.append("</SubOrderList>").append("</Order>").append("</Response>");
		//签名 start
		String response = builder.toString();
		PartnerSignerImpl signer=new PartnerSignerImpl();
		PrivateKey ygPrivate = KeyPairer.getPrivateKey(KeyPairer.PARTNER_PRIVATE_KEY);
		String sign = "";
		try {
			response = WipeTabEnterSpaceUtil.wipe(response);
			sign = signer.sign(response.getBytes("GBK"), ygPrivate);
		} catch (UnsupportedEncodingException e) {
			LogConstant.debugLog.error("[SunshinePayCheckMockServiceImpl-->payCheck] signature failed!", e);
		}
		//签名 end
		String status = getPremiumByName(SessionKey.CD_PAY_CHECK, false);
		Timestamp sendTs = new Timestamp(new Date().getTime());
		packageModel.setSendTime(sendTs);
		packageModel.setErrorMessage("");
		packageModel.setStatus(status);
		packageModel.setRequestType("125");
		packageModel.setInsureType("100");
		if("0".equals(status)){  // 支付检查成功
			packageModel.setSign(sign);
			packageModel.setPackageBody(response);
			packageModel.setStatus("100");
		}else{
			response = "<Response></Response>";
			try {
				sign = signer.sign(response.getBytes("GBK"), ygPrivate);
			} catch (UnsupportedEncodingException e) {
				LogConstant.debugLog.error("[SunshinePayCheckMockServiceImpl-->payCheck] signature failed!", e);
			}
			packageModel.setSign(sign);
			packageModel.setPackageBody(response);
			packageModel.setStatus("400");
		}
		
		//灾备测试
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.PCZB_YG);
		iniZb = sunshineIniServiceImpl.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
				
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.PCZB_YG_DT);
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
			response = "<Response></Response>";
			try {
				sign = signer.sign(response.getBytes("GBK"), ygPrivate);
			} catch (UnsupportedEncodingException e) {
				LogConstant.debugLog.error("[SunshinePayCheckMockServiceImpl-->payCheck] signature failed!", e);
			}
			packageModel.setSign(sign);
			packageModel.setErrorMessage("支付检查失败");
			packageModel.setPackageBody(response);
			packageModel.setStatus("400");
		}
		result = packageFreeMarker.generateData2output(ContextConstant.PREFIX_RESPONSE_PACKAGE, packageModel);
		return result;
	}
	private String getPremiumByName(String iniName, boolean isFee){
		String iniValue = "";
		BusinessIni ini = new BusinessIni();
		ini.setIniName(iniName);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			iniValue = ini.getIniValue();
		}
		if(isFee){
			if("".equals(iniValue)){
				return "0";
			}else{
				iniValue = iniValue.replace(".", "");
				return iniValue;
			}
		}else{
			return iniValue;
		}
	}
	private String getIniDesc(String iniName){
		String desc = "";
		BusinessIni ini = new BusinessIni();
		ini.setIniName(iniName);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			desc = ini.getIniDesc();
		}
		return desc;
	}

}
