package com.mockCommon.controller.web.pingan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.controller.web.BaseController;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessDict;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.BusinessDictService;
import com.mockCommon.service.web.pingan.BusiConfigService;

@Controller
public class BusinessController extends BaseController {

	@Autowired
	private BusinessDictService businessDictService;
	@Autowired
	private BusiConfigService busiConfigService;
	
	@RequestMapping("/insurance/pingan/shybxpz")
	public String loadAllInis(Model model){
		List<BusinessIni> list = busiConfigService.queryAllInis();
		if(list != null){
			for(int i=0; i<list.size(); i++){
				BusinessIni ini = list.get(i);
				model.addAttribute(ini.getIniName(), ini.getIniValue());
			}
		}
		return "/insurance/pingan/shybxpz";
	}
	
	@RequestMapping("/business/insertMulti")
	@ResponseBody
	public Map<String, Object> insertMultiBusiness(@RequestParam("businessCode")String businessCode, @RequestParam("wenan")String wenan
						,@RequestParam("baoe")String baoe, @RequestParam("baofei")String baofei){
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(businessCode) || StringUtils.isEmpty(wenan)
				|| StringUtils.isEmpty(baofei) || StringUtils.isEmpty(baoe)){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		BusinessDict dict = new BusinessDict();
		dict.setBusinessCode(businessCode);
		dict = businessDictService.queryBusiDict(dict);
		if(dict == null){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		BusinessConfig config = new BusinessConfig();
		config.setBusinessId(dict.getId());
		config.setWenAn(wenan);
		config.setBaoFei(baofei);
		config.setBaoE(baoe);
		config.setStatus("1");
		config.setBusinessCode(businessCode);
		config = busiConfigService.insertConfig(config);
		if(config != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", config.getId());
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "failed!");
		}
		return retMap;
	}
	@RequestMapping("/business/ModifyInsr")
	@ResponseBody
	public Map<String, Object> ModifyInsr(@RequestParam("id")String id, @RequestParam("wenan")String wenan
						, @RequestParam("baoe")String baoe, @RequestParam("baofei")String baofei){
		Map<String, Object> retMap = new HashMap<String, Object>();
		LogConstant.runLog.info("[baoxian]modify insurance paramter: id is:" + id + ", wenan is:" + wenan
								+", baoe is:" + baoe + ", baofei is:" + baofei);
		if(StringUtils.isEmpty(id) || StringUtils.isEmpty(wenan)
				|| StringUtils.isEmpty(baoe) || StringUtils.isEmpty(baofei)){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		BusinessConfig config = new BusinessConfig();
		config.setId(id);
		config.setWenAn(wenan);
		config.setBaoFei(baofei);
		config.setBaoE(baoe);
		int retCode = busiConfigService.updateConfig(config);
		if(retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "modify succeed");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "modify failed");
		}
		return retMap;
	}
	@RequestMapping("/business/DeleteInsr")
	@ResponseBody
	public Map<String, Object> ModifyInsr(@RequestParam("id")String id){
		Map<String, Object> retMap = new HashMap<String, Object>();
		LogConstant.runLog.info("[baoxian]delete insurance paramter: id is:" + id);
		if(StringUtils.isEmpty(id)){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		BusinessConfig config = new BusinessConfig();
		config.setId(id);
		config.setStatus("0");
		int retCode = busiConfigService.updateConfig(config);
		if(retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "modify succeed");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "modify failed");
		}
		return retMap;
	}
	@RequestMapping("/business/queryConfig")
	@ResponseBody
	public Map<String, Object> queryConfig(@RequestParam("businessCode")String businessCode){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> paramMap = new HashMap<String, String>();
		LogConstant.runLog.info("[baoxian]business code is:" + businessCode);
		if(StringUtils.isEmpty(businessCode)){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		paramMap.put("businessCode", businessCode);
		List<BusinessConfig> list = busiConfigService.queryConfigList(paramMap);
		if(list != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", list);
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "select failed");
		}
		return retMap;
	}
	@RequestMapping("/business/updateCache")
	@ResponseBody
	public Map<String, Object> updateCache(@RequestParam("content")String content){
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<BusinessIni> insertList = new ArrayList<BusinessIni>();
		List<BusinessIni> updateList = new ArrayList<BusinessIni>();
		LogConstant.runLog.info("business insure config params is: " + content);
		String[] paramArr = content.split("&");
		for(int i=0; i<paramArr.length; i++){
			String param = paramArr[i];
			String[] keyAndValue = param.split("=");
			String key = keyAndValue[0];
			String value = keyAndValue[1];
			BusinessIni ini = new BusinessIni();
			ini.setIniName(key);
			ini.setIniValue(value);
			BusinessIni queryIni = busiConfigService.selectIni(ini);
			if(queryIni==null){
				insertList.add(ini);
			}else{
				updateList.add(ini);
			}
		}

		//执行插入操作
		if(insertList.size() > 0){
			busiConfigService.batchInsert(insertList);
		}
		//执行更新操作
		if(updateList.size() > 0){
			busiConfigService.batchUpdate(updateList);
		}
		retMap.put("retCode", "200");
		return retMap;
	}
	@RequestMapping("/business/validateWenAn")
	@ResponseBody
	public Map<String, Object> validateWenAn(@RequestParam("wenan")String wenan, @RequestParam("businessCode")String businessCode){
		LogConstant.runLog.info("[baoxian]validate wenan parameter is:" + wenan +", businesscode is:" + businessCode);
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> paramMap = new HashMap<String, String>();
		if(StringUtils.isEmpty(wenan) || StringUtils.isEmpty(businessCode)){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		paramMap.put("businessCode", businessCode);
		paramMap.put("baoE", wenan);
		List<BusinessConfig> list = busiConfigService.queryConfigList(paramMap);
		if(list != null && list.size() > 0){
			LogConstant.runLog.info("[baoxian]wenan is already exist");
			retMap.put("retCode", "200");
			retMap.put("retDesc", "wenan is already exist");
		}else{
			LogConstant.runLog.info("[baoxian]wenan is not exist");
			retMap.put("retCode", "0");
			retMap.put("retDesc", "wenan is not exist");
		}
		return retMap;
	}
	@RequestMapping("/business/getAllDict")
	@ResponseBody
	public Map<String, Object> getAllIni(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<BusinessDict> list = businessDictService.queryAllBusiDict();
		if(list == null){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "query business ini config failed");
			return retMap;
		}
		retMap.put("retCode", "200");
		retMap.put("retDesc", list);
		return retMap;
	}
}
