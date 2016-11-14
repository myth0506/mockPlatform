package com.mockCommon.controller.web.pingan;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.AuditInfoModel;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.service.web.pingan.ToAuditService;

@Controller
public class ToAuditController {

	@Autowired
	private ToAuditService toAuditService;
	@Autowired
	private BusiConfigService busiConfigService;

	@RequestMapping("/InsrToAudit/submitAuditInfo")
	@ResponseBody
	public Map<String, Object> submitAuditInfo(
			@RequestParam("auditStatus") String auditStatus,
			@RequestParam("smsCodeStatus") String smsCodeStatus,
			@RequestParam("auditBizOrderNo") String auditBizOrderNo,
			@RequestParam("auditJqxOrderNo") String auditJqxOrderNo,
			@RequestParam("auditSmsCode") String auditSmsCode) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		int status = 0;
		AuditInfoModel auditInfoModel = new AuditInfoModel();
		auditInfoModel.setSmsCodeStatus(smsCodeStatus);
		auditInfoModel.setBizOrderNo(auditBizOrderNo);
		auditInfoModel.setJqxOrderNo(auditJqxOrderNo);
		auditInfoModel.setAuditSmsCode(auditSmsCode);
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.AUDIT_STATUS);
		ini.setIniValue(auditStatus);
		ini.setIniDesc("核保信息状态");
		status = busiConfigService.mergeIni(ini);
		if(status > 0){
			ini.setIniName(SessionKey.AUDIT_SMS);
			ini.setIniValue(smsCodeStatus);
			ini.setIniDesc("手机核保状态");
			status = busiConfigService.mergeIni(ini);
		}
		if(status > 0 && "1".equals(smsCodeStatus)){
			ini.setIniName(SessionKey.AUDIT_SMS_CODE);
			ini.setIniValue(auditSmsCode);
			ini.setIniDesc("手机核保校验码");
			status = busiConfigService.mergeIni(ini);
		}
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}
	@RequestMapping("/InsrToAudit/iniAuditInfo")
	@ResponseBody
	public Map<String, Object> iniAuditInfo(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.AUDIT_STATUS);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("auditStatus", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.AUDIT_SMS);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("auditSms", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.AUDIT_SMS_CODE);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("auditSmsCode", ini.getIniValue());
		}
		retMap.put("retCode", "200");
		return retMap;
	}
}