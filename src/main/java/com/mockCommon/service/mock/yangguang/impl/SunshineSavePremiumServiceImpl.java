package com.mockCommon.service.mock.yangguang.impl;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.yangguang.ResponsePackageModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.yangguang.SunshineBaoJiaModel;
import com.mockCommon.service.mock.yangguang.SunshineSavePremiumMockService;
import com.mockCommon.service.web.yangguang.SunshineBaoJiaService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.service.web.yangguang.SunshineIniService;
import com.mockCommon.util.CacheUtil;
import com.mockCommon.util.ParseXmlUtil;
import com.mockCommon.util.WipeTabEnterSpaceUtil;
import com.mockCommon.util.freeMarker.impl.yangguang.PackageFreeMarker;
import com.mockCommon.util.yangguang.KeyPairer;
import com.mockCommon.util.yangguang.PartnerSignerImpl;

@Service("sunshineSavePremiumServiceImpl")
public class SunshineSavePremiumServiceImpl implements
		SunshineSavePremiumMockService {

	@Autowired
	private SunshineIniService sunshineIniServiceImpl;
	
	@Autowired
	private SunshineBaoJiaService sunshineBaoJiaService;
	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigService;
	@Autowired
	private PackageFreeMarker savePremiumDataMaker;

	@Override
	public String savePremium(String xml) {
		String result = null;
		String pkgName = null;
		ResponsePackageModel packageModel = new ResponsePackageModel();
		SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder builder = new StringBuilder();
		builder.append("<Response>");
		if(StringUtils.isEmpty(xml)){
			LogConstant.debugLog.error("[SunshineSavePremiumServiceImpl-->savePremium] xml can not be empty!");
			return null;
		}
		//获取套餐类型 start
		Element packageElement = ParseXmlUtil.queryElementByXPath(xml, 
							"/PackageList/Package/Request/InputsList/Inputs/Input[@name=\"packageType\"]");
		if(packageElement != null){
			pkgName = packageElement.getTextTrim();
		}
		if(StringUtils.isEmpty(pkgName)){
			pkgName = (String) CacheUtil.get(SessionKey.SUNSHINE_PACKAGETYPE_BEFORE_SAVE_BAOJIA);
			if(StringUtils.isEmpty(pkgName)){
				LogConstant.debugLog.error("[SunshineSavePremiumServiceImpl-->savePremium] package name can not be empty!");
				return null;
			}
		}
		builder.append("<TagsList>");
		PartnerSignerImpl signer=new PartnerSignerImpl();
		Timestamp sendTs = new Timestamp(new Date().getTime());
		packageModel.setSendTime(sendTs);
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageName", pkgName);
		SunshineBaoJiaModel baoJiaModel = sunshineBaoJiaService.queryConfig(map);
		Timestamp bizStartTs = baoJiaModel.getBusiInsrStartTime();
		Timestamp forceStartTs = baoJiaModel.getJqInsrStartTime();
		String bizStartTsStr = timeSdf.format(bizStartTs);
		String forceStartTsStr = timeSdf.format(forceStartTs);
		builder.append("<Tags type=\"deadline\">").append("<Tag>")
			.append("<Definition name=\"type\">date</Definition>").append("<Definition name=\"label\">商业险起保日期</Definition>")
			.append("<Definition name=\"key\">bizBeginDate</Definition>").append("<Definition name=\"value\">").append(bizStartTsStr).append("</Definition>")
			.append("<Definition name=\"dataUrl\"></Definition>").append("<Definition name=\"checkUrl\"></Definition>").append("</Tag>");
		builder.append("<Tag>").append("<Definition name=\"type\">date</Definition>").append("<Definition name=\"label\">交强险起保日期</Definition>")
			.append("<Definition name=\"key\">forceBeginDate</Definition>").append("<Definition name=\"value\">").append(forceStartTsStr).append("</Definition>")
			.append("<Definition name=\"dataUrl\"></Definition>").append("<Definition name=\"checkUrl\"></Definition>").append("</Tag>").append("</Tags>");
		int forceFee = 0;
		String[] forceFeeArr = {"forcePremium", "vehicleTaxPremium"};
		builder.append("<Tags type=\"force\">");
		for(String str : forceFeeArr){
			builder.append("<Tag><Definition name=\"type\">label</Definition>").append("<Definition name=\"key\">").append(str).append("</Definition>");
			if(str.equals("forcePremium")){
				builder.append("<Definition name=\"label\">交强险费</Definition>");
			}else{
				builder.append("<Definition name=\"label\">车船税费</Definition>");
			}
			String iniValue = getPremiumByName(str, true);
			forceFee += Integer.parseInt(iniValue);
			builder.append("<Definition name=\"value\"></Definition>").append("<Definition name=\"premium\">").append(iniValue).append("</Definition>");
			builder.append("</Tag>");
		}
		builder.append("<Tag>").append("<Definition name=\"type\">label</Definition>").append("<Definition name=\"key\">forceTotalPremium</Definition>")
			.append("<Definition name=\"label\">交强险总保费</Definition>").append("<Definition name=\"value\"></Definition>")
			.append("<Definition name=\"premium\">").append(forceFee).append("</Definition>").append("</Tag>").append("</Tags>");
		String bizIniValue = getPremiumByName("bizTotalPremium", true);
		builder.append("<Tags type=\"commercial\">").append("<Tag>").append("<Definition name=\"type\">label</Definition>").append("<Definition name=\"key\">bizTotalPremium</Definition>")
			.append("<Definition name=\"label\">商业总保费</Definition>").append("<Definition name=\"value\"></Definition>")
			.append("<Definition name=\"premium\">").append(bizIniValue).append("</Definition>").append("</Tag>").append("</Tags>");
		builder.append("<Tags type=\"subPremium\">");
		String[] string = {"standardPremium", "sunshineTotalPremium"};
		for(String str : string){
			builder.append("<Tag><Definition name=\"type\">label</Definition>").append("<Definition name=\"key\">").append(str).append("</Definition>");
			String iniValue = getPremiumByName(str, true);
			if(str.equals("standardPremium")){
				builder.append("<Definition name=\"label\">市场总价</Definition>");
				builder.append("<Definition name=\"value\"></Definition>")
				.append("<Definition name=\"premium\">").append("548102").append("</Definition>").append("</Tag>");
			}else{
				builder.append("<Definition name=\"label\">网购总价</Definition>");
				builder.append("<Definition name=\"value\"></Definition>")
				.append("<Definition name=\"premium\">").append(iniValue).append("</Definition>").append("</Tag>");
			}
		}
		builder.append("</Tags>");
		//获取商业特约 start
		builder.append("<Tags type=\"engageList\">");
		String[] engageList = {};
		for(String engage : engageList){
			String iniValue = getPremiumByName(engage, false);
			String iniDesc = getIniDesc(engage);
			builder.append("<Tag>").append("<Definition name=\"type\">label</Definition>").append("<Definition name=\"key\">").append(engage).append("</Definition>")
				.append("<Definition name=\"label\">").append(iniDesc).append("</Definition>")
				.append("<Definition name=\"value\">").append(iniValue).append("</Definition>")
				.append("<Definition name=\"premium\"></Definition>").append("<Tag>");
		}
		builder.append("</Tags>");
		//获取商业特约 end
		builder.append("<Tags type=\"lifeTablePresents\"></Tags>").append("<Tags type=\"addPresentPackages\"></Tags>")
			.append("</TagsList>").append("</Response>");
		//签名 start
		String response = builder.toString();
		//签名 end
		packageModel.setErrorMessage("");
		packageModel.setSendTime(sendTs);		
		packageModel.setStatus("100");
		packageModel.setRequestType("115");
		packageModel.setInsureType("100");
		
		
		//灾备测试
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.SBZB_YG);
		iniZb = sunshineIniServiceImpl.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.SBZB_YG_DT);
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
			packageModel.setStatus("400");
			packageModel.setErrorMessage("检索车辆信息错误");
			response="";
		}
		
		PrivateKey ygPrivate = KeyPairer.getPrivateKey(KeyPairer.PARTNER_PRIVATE_KEY);
		String sign = "";
		try {
			response = WipeTabEnterSpaceUtil.wipe(response);
			sign = signer.sign(response.getBytes("GBK"), ygPrivate);
		} catch (UnsupportedEncodingException e) {
			LogConstant.debugLog.error("[SunshineModifyBaoJiaMockServiceImpl-->modifyBaoJia] signature failed!", e);
		}
		
		packageModel.setSign(sign);
		packageModel.setPackageBody(response);
		result = savePremiumDataMaker.generateData2output(ContextConstant.PREFIX_RESPONSE_PACKAGE, packageModel);
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