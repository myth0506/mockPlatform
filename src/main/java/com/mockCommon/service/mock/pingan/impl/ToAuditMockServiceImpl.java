package com.mockCommon.service.mock.pingan.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.AuditResultCode;
import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.pingan.AuditInfoMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.AuditInfoModel;
import com.mockCommon.service.mock.pingan.ToAuditMockService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.CacheUtil;
import com.mockCommon.util.freeMarker.impl.pingan.ToAuditFreeMarker;

@Service
public class ToAuditMockServiceImpl implements ToAuditMockService {

	@Autowired
	private ToAuditFreeMarker toAuditFreeMarker;
	@Autowired
	private BusiConfigService busiConfigService;

	/*
	 * 核保mock接口
	 * 
	 * @see com.mockCommon.service.mock.ToAuditMockService#toAudit()
	 */
	@Override
	public String toAudit(String flowId, String smsCode) {
		String result = null;
		AuditInfoMockModel auditInfoMockModel = new AuditInfoMockModel();
		auditInfoMockModel.setFlowId(flowId);
		AuditInfoModel auditInfoModel = new AuditInfoModel();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.AUDIT_STATUS);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			String auditStatus = ini.getIniValue();
			auditInfoModel.setAuditStatus(auditStatus);
			if("C0000".equals(auditStatus) || "C6003".equals(auditStatus)){
				String bizOrderNo = getOrderNo(8);
				auditInfoModel.setBizOrderNo(bizOrderNo);
			}
			if("C0000".equals(auditStatus) || "C6002".equals(auditStatus)){
				String jqxOrderNo = getOrderNo(8);
				auditInfoModel.setJqxOrderNo(jqxOrderNo);
			}
			if("C0009".equals(auditStatus)){
				result = "{\"flowId\":\"" + flowId + "\",\"resultCode\":\"C0009\",\"resultMessage\":\"两号信息不一致，需返回\",\"infoMsgs\":{\"q2TotalPremium\":\"26537.06\",\"q2TotalPremiumChangedFlag\":\"Y\"}}";
				return result;
			}
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.AUDIT_SMS);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			auditInfoModel.setSmsCodeStatus(ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.AUDIT_SMS_CODE);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			auditInfoModel.setAuditSmsCode(ini.getIniValue());
		}
		// 从缓存中取商业险和交强险的金额
		String str_bizPremium = (String)CacheUtil.get(SessionKey.SHYX_TOTAL_FEE);
		float bizPremium = 0;
		if(str_bizPremium != null){
			try{
				bizPremium = Float.parseFloat(str_bizPremium);
			}catch(NumberFormatException e){
				bizPremium = 0;
				LogConstant.debugLog.info("NumberFormatException in toAudit Biz");
			}
		}
		
		String str_jqxPremium = (String)CacheUtil.get(SessionKey.JQX_TOTAL_FEE);
		float jqxPremium = 0;
		if(str_jqxPremium != null){
			try{
				jqxPremium = Float.parseFloat(str_jqxPremium);
			}catch(NumberFormatException e){
				jqxPremium = 0;
				LogConstant.debugLog.info("NumberFormatException in toAudit Jqx");
			}
		}
		
		if (auditInfoModel != null) {
			String smsCodeStatus = auditInfoModel.getSmsCodeStatus();
			if (smsCodeStatus != null) {
				if (smsCodeStatus.equals("0")) { // 不需要手机验证
					auditInfoMockModel.setBizOrderNo(auditInfoModel
							.getBizOrderNo());
					auditInfoMockModel.setBizPremium(bizPremium);
					auditInfoMockModel.setJqxOrderNo(auditInfoModel
							.getJqxOrderNo());
					auditInfoMockModel.setJqxPremium(jqxPremium);
					String auditStatus = auditInfoModel.getAuditStatus();
					if (auditStatus != null) {
						auditInfoMockModel.setResultCode(auditStatus);
						auditInfoMockModel.setMessage(AuditResultCode
								.getValue(auditStatus));
					}
				} else { // 需要手机验证
					if (smsCode == null) { // 没有输入验证码
						auditInfoMockModel.setResultCode("C9102");
						auditInfoMockModel.setMessage(AuditResultCode.C9102);
					} else if (smsCode.equals(auditInfoModel.getAuditSmsCode())) { // 验证通过
						auditInfoMockModel.setBizOrderNo(auditInfoModel
								.getBizOrderNo());
						auditInfoMockModel.setBizPremium(bizPremium);
						auditInfoMockModel.setJqxOrderNo(auditInfoModel
								.getJqxOrderNo());
						auditInfoMockModel.setJqxPremium(jqxPremium);
						String auditStatus = auditInfoModel.getAuditStatus();
						if (auditStatus != null) {
							auditInfoMockModel.setResultCode(auditStatus);
							auditInfoMockModel.setMessage(AuditResultCode
									.getValue(auditStatus));
						}
					} else { // 验证未通过
						auditInfoMockModel.setResultCode("C9103");
						auditInfoMockModel.setMessage(AuditResultCode.C9103);
					}
				}
			}
		}
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.HBZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.HBZB_PA_DT);
			iniDelayTime = busiConfigService.selectIni(iniDelayTime);
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
			auditInfoMockModel.setResultCode("S0001");
		}else if(zbValue != null && zbValue.equals("session")){
			auditInfoMockModel.setResultCode("S0003");
			auditInfoMockModel.setMessage("flow<" + flowId + ">不存在或已超时！");
		}
		
		result = toAuditFreeMarker.generateData2output(
				ContextConstant.PREFIX_TO_AUDIT, auditInfoMockModel);
		LogConstant.debugLog.info("toAudit  Mock数据：" + result);
		return result;
	}
	private String getOrderNo(int len){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String dataStr = sdf.format(date);
		return dataStr + "0000" + randomStr(len);
	}
	private String randomStr(int length){
		  Random random = new Random();
		  if (length <= 0) {
			  throw new IllegalArgumentException("length must be positive");
		  }
		  StringBuilder sb = new StringBuilder();
		  for (int i = 0; i < length; i++){
			  sb.append("0123456789".charAt(random.nextInt("0123456789".length())));
		  }
		  return sb.toString();
	}
}