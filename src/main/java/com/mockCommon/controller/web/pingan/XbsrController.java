package com.mockCommon.controller.web.pingan;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.mockCommon.service.web.pingan.XbsrService;
import com.mockCommon.util.CookiesUtil;

@Controller
public class XbsrController {

	@Autowired
	private XbsrService xbsrService;
	@Autowired
	private BusiConfigService busiConfigService;
	
	@RequestMapping("/InsrXbsr/renewalCheck")
	@ResponseBody
	public Map<String, Object> renewalCheck(
			@RequestParam("isXbyh") String isXbyh) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.XBSR_IS_XBYH);
		ini.setIniValue(isXbyh);
		ini.setIniDesc("是否续保");
		int status = busiConfigService.mergeIni(ini);
		//int status = xbsrService.renewalCheck(isXbyh);
		LogConstant.runLog.info("是否续保：" + isXbyh);
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "设置是否续保成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "设置是否续保失败");
		}
		
		return retMap;
	}
	
	@RequestMapping("/InsrXbsr/renewalConfirm")
	@ResponseBody
	public Map<String, Object> renewalConfirm(
			@RequestParam("birthday") String birthday) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.XBSR_BIRTHDAY);
		ini.setIniValue(birthday);
		ini.setIniDesc("校验生日");
		int status = busiConfigService.mergeIni(ini);
		//int status = xbsrService.renewalConfirm(birthday);
		LogConstant.runLog.info("校验生日：" + birthday);
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "设置校验生日成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "设置校验生日失败");
		}
		return retMap;
	}
	@RequestMapping("/InsrXbsr/saveVehicleNo")
	@ResponseBody
	public Map<String, Object> saveVehicleNo(@RequestParam("vehicleNo")String vehicleNo, HttpServletResponse response) throws UnsupportedEncodingException{
		Map<String, Object> retMap = new HashMap<String, Object>();
		LogConstant.runLog.info("vehicle number is: " + vehicleNo);
		if(StringUtils.isEmpty(vehicleNo)){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "please input correct vehicle number");
			return retMap;
		}
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SEARCH_CAR_INFO_VEHICLE_NO);
		ini.setIniValue(vehicleNo);
		ini.setIniDesc("设置车牌号");
		int status = busiConfigService.mergeIni(ini);
		//CacheUtil.put(SessionKey.SEARCH_CAR_INFO_VEHICLE_NO, vehicleNo);
		CookiesUtil.setCookie(response, "vehicleNo", URLEncoder.encode(vehicleNo, "utf-8"));
		if(status > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "save vehicle number succeed");
			return retMap;
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "save vehicle number failed");
			return retMap;
		}
	}
	@RequestMapping("/InsrXbsr/ini")
	@ResponseBody
	public Map<String, Object> ini(){
		Map<String, Object> map = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.XBSR_IS_XBYH);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			map.put("sfxb", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.XBSR_BIRTHDAY);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			map.put("birthday", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.SEARCH_CAR_INFO_VEHICLE_NO);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			map.put("vehicleNo", ini.getIniValue());
		}
		map.put("retCode", "200");
		return map;
	}
}