package com.mockCommon.controller.web.yangguang;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessDict;
import com.mockCommon.model.web.yangguang.SunshineBaoJiaModel;
import com.mockCommon.service.web.BusinessDictService;
import com.mockCommon.service.web.yangguang.SunshineBaoJiaService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;

@Controller("sunshineBaojiaController")
public class SunshineBaojiaController {

	@Autowired
	private SunshineBaoJiaService sunshineBaoJiaServiceImpl;
	
	@Autowired
	private BusinessDictService businessDictService;
	
	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigService;
	
	@RequestMapping("/yangguang/baojia/iniPage")
	@ResponseBody
	public Map<String, Object> iniPage(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<String> returnList = new ArrayList<String>();
		List<SunshineBaoJiaModel> baojiaModel = sunshineBaoJiaServiceImpl.queryAllConfigs();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(baojiaModel != null && baojiaModel.size() > 0){
			SunshineBaoJiaModel config0 = baojiaModel.get(0);
			Timestamp tsShyx = config0.getBusiInsrStartTime();
			Timestamp tsJqx = config0.getJqInsrStartTime();
			Date dateShyx = new Date(tsShyx.getTime());
			Date dateJqx = new Date(tsJqx.getTime());
			retMap.put("dateShyx", sdf.format(dateShyx));
			retMap.put("dateJqx", sdf.format(dateJqx));
			for(int i=0; i<baojiaModel.size(); i++){
				SunshineBaoJiaModel config = baojiaModel.get(i);
				String configType = config.getPackageType();
				String configDefault = config.getPackageDefault();
				String configReturn = config.getPackageReturn();
				if("1".equals(configDefault)){
					retMap.put("defaultConfig", configType);
				}
				if("1".equals(configReturn)){
					returnList.add(configType);
				}
			}
		}
		retMap.put("returnList", returnList);
		return retMap;
	}
	
	@RequestMapping("/yangguang/getAllDict")
	@ResponseBody
	public Map<String, Object> getAllDict(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<BusinessDict> list = businessDictService.queryAllSunshineBusiDict();
		if(list == null){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "query business ini config failed");
			return retMap;
		}
		retMap.put("retCode", "200");
		retMap.put("retDesc", list);
		return retMap;
	}
	
	@RequestMapping("/yangguang/queryConfig")
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
		List<BusinessConfig> list = sunshineBusiConfigService.queryConfigList(paramMap);
		if(list != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", list);
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "select failed");
		}
		return retMap;
	}
	
	@RequestMapping("/yangguang/baojia/updateConfigs")
	@ResponseBody
	public Map<String, Object> updateConfigs(@RequestParam("tcType")String tcType, @RequestParam("tcName")String tcName,
						@RequestParam("codeStr")String codeStr, @RequestParam("valStr")String valStr){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageType", tcType);
		SunshineBaoJiaModel config = sunshineBaoJiaServiceImpl.queryConfig(map);
		String[] codeArr = codeStr.split(",");
		String[] valArr = valStr.split(",");
		SunshineBaoJiaModel sunshineBaoJia = new SunshineBaoJiaModel();
		sunshineBaoJia.setPackageType(tcType);
		for(int i = 0; i < codeArr.length; i++){
			String code = codeArr[i];
			String value = valArr[i];
			if("cov_200".equals(code)){
				sunshineBaoJia.setCov_200(value);
			}else if("cov_600".equals(code)){
				sunshineBaoJia.setCov_600(value);
			}else if("cov_500".equals(code)){
				sunshineBaoJia.setCov_500(value);
			}else if("cov_701".equals(code)){
				sunshineBaoJia.setCov_701(value);
			}else if("cov_702".equals(code)){
				sunshineBaoJia.setCov_702(value);
			}else if("cov_321".equals(code)){
				sunshineBaoJia.setCov_321(value);
			}else if("cov_310".equals(code)){
				sunshineBaoJia.setCov_310(value);
			}else if("cov_231".equals(code)){
				sunshineBaoJia.setCov_231(value);
			}else if("cov_210".equals(code)){
				sunshineBaoJia.setCov_210(value);
			}else if("cov_390".equals(code)){
				sunshineBaoJia.setCov_390(value);
			}else if("cov_291".equals(code)){
				sunshineBaoJia.setCov_291(value);
			}else if("cov_640".equals(code)){
				sunshineBaoJia.setCov_640(value);
			}else if("cov_921".equals(code)){
				sunshineBaoJia.setCov_921(value);
			}else if("cov_922".equals(code)){
				sunshineBaoJia.setCov_922(value);
			}else if("cov_911".equals(code)){
				sunshineBaoJia.setCov_911(value);
			}else if("cov_912".equals(code)){
				sunshineBaoJia.setCov_912(value);
			}else if("cov_928".equals(code)){
				sunshineBaoJia.setCov_928(value);
			}else if("cov_929".equals(code)){
				sunshineBaoJia.setCov_929(value);
			}else if("cov_734".equals(code)){
				sunshineBaoJia.setCov_734(value);
			}else if("cov_733".equals(code)){
				sunshineBaoJia.setCov_733(value);
			}
		}
		int retCode = 0;
		if(config == null){
			retCode = sunshineBaoJiaServiceImpl.insertConfig(sunshineBaoJia);
		}else{
			retCode = sunshineBaoJiaServiceImpl.updateConfig(sunshineBaoJia);
		}
		if(retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "update config succeed");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "update config failed");
		}
		return retMap;
	}
	
	@RequestMapping("/yangguang/baojia/getLatestConfigs")
	@ResponseBody
	public Map<String, Object> getLatestConfigs(@RequestParam("tcType")String tcType){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageType", tcType);
		SunshineBaoJiaModel config = sunshineBaoJiaServiceImpl.queryConfig(map);
		List<BusinessDict> dictList = businessDictService.queryAllSunshineBusiDict();
		SunshineBaoJiaModel chkConfig = sunshineBaoJiaServiceImpl.queryLatestConfig();
		if(config != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", config);
			retMap.put("retDict", dictList);
			retMap.put("retChk", chkConfig);
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "There is no package data in db");
		}
		return retMap;
	}
	
	@RequestMapping("/yangguang/baojia/saveReturnInsures")
	@ResponseBody
	public Map<String, Object> saveReturnInsures(
			@RequestParam("defaultTclb")String defaultTclb, 
			@RequestParam("returnlb")String returnlb,
			@RequestParam("syxqbrq")String syxqbrq, 
			@RequestParam("jqxqbrq")String jqxqbrq){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		List<String> lbList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int retCode = 0;
		map.put("package_type", defaultTclb);
		retCode = sunshineBaoJiaServiceImpl.updateDefaultPk(map);
		String[] lbArr = returnlb.split(",");
		for(int i=0; i < lbArr.length; i++){
			lbList.add(lbArr[i]);
		}
		retCode = sunshineBaoJiaServiceImpl.updateReturnPk(lbList);
		Timestamp tsShyx = null;
		Timestamp tsJqx = null;
		try {
			tsShyx = new Timestamp(sdf.parse(syxqbrq).getTime());
			tsJqx = new Timestamp(sdf.parse(jqxqbrq).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SunshineBaoJiaModel baojiaModel = new SunshineBaoJiaModel();
		if(tsShyx != null && tsJqx != null){
			baojiaModel.setBusiInsrStartTime(tsShyx);
			baojiaModel.setJqInsrStartTime(tsJqx);
		}
		retCode = sunshineBaoJiaServiceImpl.updateStartTime(baojiaModel);
		if(retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "save config succeed");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "save config failed");
		}
		return retMap;
	}
}