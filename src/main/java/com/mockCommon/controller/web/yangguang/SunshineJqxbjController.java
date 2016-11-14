package com.mockCommon.controller.web.yangguang;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.service.web.yangguang.SunshineJqxbjService;

@Controller("sunshineJqxbjController")
public class SunshineJqxbjController {

	@Autowired
	private SunshineJqxbjService sunshineJqxbjService;
	
	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigService;
	
	@RequestMapping("/yangguang/InsrJqxbj/forceQuote")
	@ResponseBody
	public Map<String, Object> forceQuote(
			HttpServletRequest request,
			@RequestParam("isJqx") String isJqx,
			@RequestParam("jqxjg") String jqxjg,
			@RequestParam("ccsjg") String ccsjg) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		int status = sunshineJqxbjService.forceQuote(isJqx,jqxjg,ccsjg);
		LogConstant.runLog.info("交强险失败原因：" + isJqx);
		LogConstant.runLog.info("交强险价格：" + jqxjg);
		LogConstant.runLog.info("车船税价格：" + ccsjg);
		
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "设置交强险报价成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "设置交强险报价失败");
		}
		return retMap;
	}
	
	@RequestMapping("/yangguang/InsrJqxbj/iniQuote")
	@ResponseBody
	public Map<String, Object> iniQuote(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_JQXBJ_IS_JQX);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			LogConstant.runLog.info("[use force insure]" + ini.getIniValue());
			retMap.put("isJqx", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_JQXBJ_JQXJG);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			LogConstant.runLog.info("[force fee]" + ini.getIniValue());
			retMap.put("jqxFee", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_JQXBJ_CCSJG);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			LogConstant.runLog.info("[tax fee]" + ini.getIniValue());
			retMap.put("taxFee", ini.getIniValue());
		}
		return retMap;
	}
}