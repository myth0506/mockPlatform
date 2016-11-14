package com.mockCommon.controller.web.pingan;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.service.web.pingan.GetSpecialPromiseService;

@Controller
public class GetSpecialPromiseController {

	@Autowired
	private GetSpecialPromiseService getSpecialPromiseService;
	@Autowired
	private BusiConfigService busiConfigService;

	@RequestMapping("/InsrGetSpecialPromise/getSpecialPromise")
	@ResponseBody
	public Map<String, Object> getSpecialPromise(
			@RequestParam("bizSpecialPromise") String bizSpecialPromise,
			@RequestParam("jqxSpecialPromise") String jqxSpecialPromise) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		int status = 0;
		if (StringUtils.isNotEmpty(bizSpecialPromise) && StringUtils.isNotEmpty(jqxSpecialPromise)) {
			status = getSpecialPromiseService.getSpecialPromise(
					bizSpecialPromise, jqxSpecialPromise);
		}

		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "提交信息成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败，请填写完整的约定值");
		}
		return retMap;
	}
	@RequestMapping("/InsrGetSpecialPromise/iniSpecialPromise")
	@ResponseBody
	public Map<String, Object> iniSpecialPromise(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.GET_SPECIAL_PROMISE_BIZ);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			LogConstant.runLog.info("[biz promise is]" + ini.getIniValue());
			retMap.put("bizPromise", ini.getIniValue());
		}
		ini.setIniName(SessionKey.GET_SPECIAL_PROMISE_JQX);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			LogConstant.runLog.info("[force promise is]" + ini.getIniValue());
			retMap.put("forcePromise", ini.getIniValue());
		}
		return retMap;
	}
}