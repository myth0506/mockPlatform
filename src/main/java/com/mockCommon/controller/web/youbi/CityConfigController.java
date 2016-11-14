package com.mockCommon.controller.web.youbi;

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
import com.mockCommon.service.web.youbi.YoubiCityConfigService;
import com.mockCommon.service.web.youbi.YoubiIniService;

@Controller
public class CityConfigController {
	
	@Autowired
	private YoubiCityConfigService youbiCityConfigService;
	
	@Autowired
	private YoubiIniService youbiIniService;
	
	
	@RequestMapping("/youbi/saveCityCode")
	@ResponseBody
	public Map<String, Object> saveCityCode(
			HttpServletRequest request,
			@RequestParam("cityCode") String cityCode,
			@RequestParam("isMock") String isMock) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		int status = youbiCityConfigService.saveCityCode(cityCode,isMock);
		
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "设置城市代码成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "设置城市代码失败");
		}
		return retMap;
	}
	
	@RequestMapping("/youbi/saveCityConfig")
	@ResponseBody
	public Map<String, Object> saveCityConfig(
			HttpServletRequest request,
			@RequestParam("province_code") String province_code,
			@RequestParam("license_prefix") String license_prefix,
			@RequestParam("local_car") String local_car,
			@RequestParam("out_car") String out_car,
			@RequestParam("need_pic") String need_pic,
			@RequestParam("force_flag") String force_flag,
			@RequestParam("biz_days") String biz_days,
			@RequestParam("support_driving_area") String support_driving_area,
			@RequestParam("support_assign_driver") String support_assign_driver) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		int status = youbiCityConfigService.saveCityConfig(province_code,license_prefix,local_car,out_car,need_pic,force_flag,biz_days,support_driving_area,support_assign_driver);
		
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "设置城市配置成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "设置城市配置失败");
		}
		return retMap;
	}
	
	@RequestMapping("/youbi/initCityConfig")
	@ResponseBody
	public Map<String, Object> initCityConfig(){
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.CITY_CONFIG_MOCK);
		if(ini != null){
			LogConstant.runLog.info("[youbi cityConfigMock]" + ini.getIniValue());
			retMap.put("cityConfigMock", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.CITY_CODE);
		if(ini != null){
			LogConstant.runLog.info("[youbi cityCode]" + ini.getIniValue());
			retMap.put("cityCode", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.PROVINCE_CODE);
		if(ini != null){
			LogConstant.runLog.info("[youbi provinceCode]" + ini.getIniValue());
			retMap.put("provinceCode", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.LICENSE_PREFIX);
		if(ini != null){
			LogConstant.runLog.info("[youbi license_prefix]" + ini.getIniValue());
			retMap.put("license_prefix", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.LOCAL_CAR);
		if(ini != null){
			LogConstant.runLog.info("[youbi local_car]" + ini.getIniValue());
			retMap.put("local_car", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.OUT_CAR);
		if(ini != null){
			LogConstant.runLog.info("[youbi out_car]" + ini.getIniValue());
			retMap.put("out_car", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.NEED_PIC);
		if(ini != null){
			LogConstant.runLog.info("[youbi need_pic]" + ini.getIniValue());
			retMap.put("need_pic", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.FORCE_FLAG);
		if(ini != null){
			LogConstant.runLog.info("[youbi force_flag]" + ini.getIniValue());
			retMap.put("force_flag", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.BIZ_DAYS);
		if(ini != null){
			LogConstant.runLog.info("[youbi biz_days]" + ini.getIniValue());
			retMap.put("biz_days", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.SUPPORT_DRIVING_AREA);
		if(ini != null){
			LogConstant.runLog.info("[youbi support_driving_area]" + ini.getIniValue());
			retMap.put("support_driving_area", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini = youbiIniService.selectIni(SessionKey.SUPPORT_ASSIGN_DRIVER);
		if(ini != null){
			LogConstant.runLog.info("[youbi support_assign_driver]" + ini.getIniValue());
			retMap.put("support_assign_driver", ini.getIniValue());
		}
		return retMap;
	}

}
