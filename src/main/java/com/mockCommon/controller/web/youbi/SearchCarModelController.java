package com.mockCommon.controller.web.youbi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.youbi.YoubiIniService;


@Controller
public class SearchCarModelController {
	@Autowired
	private YoubiIniService youbiIniService;
	
	@RequestMapping("/youbi/getSearchCarModelSwitch")
	@ResponseBody
	public Map<String, Object> getSearchCarModelSwitch(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<String> results = new ArrayList<>();
		BusinessIni ini;
		ini = youbiIniService.selectIni(SessionKey.SEARCH_CAR_MODEL_MOCK);
		if(ini != null)
			results.add(ini.getIniValue());
		ini = youbiIniService.selectIni(SessionKey.SEARCH_CAR_MODEL);
		if(ini != null)
			results.add(ini.getIniValue());
		if(results.size() <= 0){
			retMap.put("retCode", -1);
			retMap.put("retDesc", "查询开关设置失败。");
		}else{
			retMap.put("retCode", 200);
			retMap.put("result", results);
			retMap.put("retDesc", "查询开关设置成功。");
		}
		return retMap;
	}
	
	@RequestMapping("/youbi/saveSearchCarModelIsMock")
	@ResponseBody
	public Map<String, Object> saveSearchCarModelIsMock(String isMock){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SEARCH_CAR_MODEL_MOCK);
		ini.setIniValue(isMock);
		ini.setIniDesc("查询车型Mock开关");
		int count = youbiIniService.saveBusinessIni(ini);
		if(count <= 0){
			retMap.put("retCode", -1);
			retMap.put("retDesc", "查询车型Mock开关设置失败。");
		}else{
			retMap.put("retCode", 200);
			retMap.put("retDesc", "查询车型Mock开关设置成功。");
		}
		return retMap;
	}
	
	@RequestMapping("/youbi/saveSearchCarModelIsSearch")
	@ResponseBody
	public Map<String, Object> saveSearchCarModelIsSearch(String isSearch){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SEARCH_CAR_MODEL);
		ini.setIniValue(isSearch);
		ini.setIniDesc("查询车型查询车辆信息开关");
		int count = youbiIniService.saveBusinessIni(ini);
		if(count <= 0){
			retMap.put("retCode", -1);
			retMap.put("retDesc", "查询车型查询车辆信息开关设置失败。");
		}else{
			retMap.put("retCode", 200);
			retMap.put("retDesc", "查询车型查询车辆信息开关设置成功。");
		}
		return retMap;
	}
}
