package com.mockCommon.controller.web.yangguang;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.netease.common.util.StringUtil;

@Controller
public class SunshineAuditController {

	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigService;

	@RequestMapping("/yangguang/InsrToAudit/submitAuditInfo")
	@ResponseBody
	public Map<String, Object> submitAuditInfo(
			@RequestParam("auditStatus") String auditStatus,
			@RequestParam("smsCodeStatus") String smsCodeStatus,
			@RequestParam("auditBizOrderNo") String auditBizOrderNo,
			@RequestParam("auditJqxOrderNo") String auditJqxOrderNo,
			@RequestParam("auditSmsCode") String auditSmsCode) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		int status = 0;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_AUDIT_STATUS);
		ini.setIniValue(auditStatus);
		ini.setIniDesc("核保信息状态");
		status = sunshineBusiConfigService.mergeIni(ini);
		//保存商业险和交强险保单号
		ini.setIniName(SessionKey.SUNSHINE_BIZ_PROPOSALNO);
		ini.setIniValue(auditBizOrderNo);
		ini.setIniDesc("核保商业险保单号");
		status = sunshineBusiConfigService.mergeIni(ini);
		ini.setIniName(SessionKey.SUNSHINE_FORCE_PROPOSALNO);
		ini.setIniValue(auditJqxOrderNo);
		ini.setIniDesc("核保交强险保单号");
		status = sunshineBusiConfigService.mergeIni(ini);
		
		if(status > 0){
			ini.setIniName(SessionKey.SUNSHINE_AUDIT_SMS);
			ini.setIniValue(smsCodeStatus);
			ini.setIniDesc("手机核保状态");
			status = sunshineBusiConfigService.mergeIni(ini);
		}
		if(status > 0 && !StringUtil.isEmpty(auditSmsCode)){
			ini.setIniName(SessionKey.SUNSHINE_AUDIT_SMS_CODE);
			ini.setIniValue(auditSmsCode);
			ini.setIniDesc("手机核保校验码");
			status = sunshineBusiConfigService.mergeIni(ini);
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
	
	@RequestMapping("/yangguang/InsrToAudit/iniAuditInfo")
	@ResponseBody
	public Map<String, Object> iniAuditInfo(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_AUDIT_STATUS);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("auditStatus", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_AUDIT_SMS);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("auditSms", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_AUDIT_SMS_CODE);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("auditSmsCode", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_BIZ_PROPOSALNO);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("bizOrderNo", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_FORCE_PROPOSALNO);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("forceOrderNo", ini.getIniValue());
		}
		retMap.put("retCode", "200");
		return retMap;
	}
}