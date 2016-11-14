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
import com.mockCommon.model.mock.yangguang.SunshineAuditMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.yangguang.SunshineAuditMockService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.util.ParseXmlUtil;
import com.mockCommon.util.WipeTabEnterSpaceUtil;
import com.mockCommon.util.freeMarker.IDataMaker;
import com.mockCommon.util.yangguang.KeyPairer;
import com.mockCommon.util.yangguang.PartnerSignerImpl;

@Service("sunshineAuditMockServiceImpl")
public class SunshineAuditMockServiceImpl implements SunshineAuditMockService {

	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigService;

	@Autowired
	private IDataMaker<ResponsePackageModel> packageFreeMarker;

	@Autowired
	private IDataMaker<SunshineAuditMockModel> sunshineAuditFreeMarker;

	@Override
	public String audit(String xmlParams) {
		String result = null;
		String xpath = "/PackageList/Package/Request/Order/TBOrderId";
		String tBOrderId = ParseXmlUtil.queryElementByXPath(xmlParams, xpath)
				.getTextTrim();

		SunshineAuditMockModel SunshineAuditMockModel = new SunshineAuditMockModel();
		SunshineAuditMockModel.settBOrderId(tBOrderId);

		ResponsePackageModel responsePackage = new ResponsePackageModel();
		String auditStatus = null;
		String auditSms = null;
		String bizOrderNo = null;
		String forceOrderNo = null;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_AUDIT_STATUS);
		ini = sunshineBusiConfigService.selectIni(ini);
		if (ini != null) {
			auditStatus = ini.getIniValue();
		}
		if (auditStatus != null && auditStatus.equals("1")) {
			ini = new BusinessIni();
			ini.setIniName(SessionKey.SUNSHINE_AUDIT_SMS);
			ini = sunshineBusiConfigService.selectIni(ini);
			if (ini != null) {
				auditSms = ini.getIniValue();
			}
			ini = new BusinessIni();
			ini.setIniName(SessionKey.SUNSHINE_BIZ_PROPOSALNO);
			ini = sunshineBusiConfigService.selectIni(ini);
			if (ini != null) {
				bizOrderNo = ini.getIniValue();
			}
			ini = new BusinessIni();
			ini.setIniName(SessionKey.SUNSHINE_FORCE_PROPOSALNO);
			ini = sunshineBusiConfigService.selectIni(ini);
			if (ini != null) {
				forceOrderNo = ini.getIniValue();
			}
			SunshineAuditMockModel.setIsIdVerifi(auditSms);
			SunshineAuditMockModel.setBizProposalNo(bizOrderNo);
			SunshineAuditMockModel.setForceProposalNo(forceOrderNo);

			// 从数据库读取交强险，车船税，商业总保费
			String forcePremium = null;
			String vehicleTaxPremium = null;
			String bizTotalPremium = null;
			double totalPremium = 0;
			ini = new BusinessIni();
			ini.setIniName(SessionKey.SUNSHINE_JQXBJ_JQXJG);
			ini = sunshineBusiConfigService.selectIni(ini);
			if (ini != null) {
				forcePremium = ini.getIniValue();
				totalPremium += Double.parseDouble(forcePremium);
			}
			ini = new BusinessIni();
			ini.setIniName(SessionKey.SUNSHINE_JQXBJ_CCSJG);
			ini = sunshineBusiConfigService.selectIni(ini);
			if (ini != null) {
				vehicleTaxPremium = ini.getIniValue();
				totalPremium += Double.parseDouble(vehicleTaxPremium);
			}
			ini = new BusinessIni();
			ini.setIniName(SessionKey.SUNSHINE_BIZ_TOTAL_PREMIUM);
			ini = sunshineBusiConfigService.selectIni(ini);
			if (ini != null) {
				bizTotalPremium = ini.getIniValue();
				totalPremium += Double.parseDouble(bizTotalPremium);
			}
			SunshineAuditMockModel.setForcePremium(forcePremium);
			SunshineAuditMockModel.setVehicleTaxPremium(vehicleTaxPremium);
			SunshineAuditMockModel.setBizTotalPremium(bizTotalPremium);
			SunshineAuditMockModel.setTotalPremium(String.valueOf((int) Math
					.round(totalPremium)));

			responsePackage.setStatus("100");
		} else if (auditStatus != null && auditStatus.equals("0")) {
			responsePackage.setStatus("400");
			responsePackage.setErrorMessage("核保失败");
		}

		// 生成最终返回数据
		String packageBody = sunshineAuditFreeMarker.generateData2output(
				ContextConstant.PREFIX_SUNSHINE_AUDIT, SunshineAuditMockModel);
		LogConstant.debugLog.info("阳光保险mock核保请求接口数据报文体：\n" + packageBody);
		
		//灾备测试
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.HBZB_YG);
		iniZb = sunshineBusiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.HBZB_YG_DT);
			iniDelayTime = sunshineBusiConfigService.selectIni(iniDelayTime);
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
			responsePackage.setErrorMessage("核保失败");
			packageBody="";
		}

		responsePackage.setRequestType("120");
		responsePackage.setInsureType("100");

		Date now = new Date();
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
			LogConstant.debugLog.info("阳光保险mock核保请求接口签名编码异常：" + e.getMessage());
			e.printStackTrace();
		}
		responsePackage.setSign(sign);
		result = packageFreeMarker.generateData2output(
				ContextConstant.PREFIX_RESPONSE_PACKAGE, responsePackage);
		LogConstant.debugLog.info("阳光保险mock核保请求接口返回数据：\n" + result);

		return result;
	}
}