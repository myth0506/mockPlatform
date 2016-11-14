package com.mockCommon.controller.web.youbi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessDict;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.BusinessDictService;
import com.mockCommon.service.web.youbi.YouBiBaoJiaConfigService;
import com.mockCommon.service.web.youbi.YoubiIniService;
import com.netease.common.util.StringUtil;

@Controller
public class GetBaoJiaConfigController {
	
	@Autowired
	private YouBiBaoJiaConfigService youBiBaoJiaConfigService;
	
	@Autowired
	private YoubiIniService youbiIniService;
	
	@Autowired
	private BusinessDictService businessDictService;
	
	@SuppressWarnings("static-access")
	@RequestMapping("/youbi/initBaoJiaConfig")
    @ResponseBody
    public Map<String,Object> initBaoJiaConfig(){
    	//设置默认时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();// 当前时间
		Date tom = new Date();
		Calendar cd = Calendar.getInstance();// 得到日历
		cd.setTime(date);// 把当前时间赋给日历
		cd.add(cd.DAY_OF_MONTH, +1);
		tom = cd.getTime();
		String defaultTime = sdf.format(tom);
  
    	Map<String,Object> retMap = new HashMap<String,Object>();
    	BusinessIni bi = new BusinessIni();
    	bi = youbiIniService.selectIni(SessionKey.SFFG);
    	if(bi != null){
			LogConstant.runLog.info("[youbi SFFG]" + bi.getIniValue());
			retMap.put("sffg", bi.getIniValue());
		}
    	bi = new BusinessIni();
		bi = youbiIniService.selectIni(SessionKey.SYXQBRQ);
		if(bi != null){
			LogConstant.runLog.info("[youbi SYXQBRQ]" + bi.getIniValue());
			retMap.put("syxqbrq", bi.getIniValue());
		}if(bi.getIniValue() == null){
			retMap.put("syxqbrq", defaultTime);
		}
		
		bi = youbiIniService.selectIni(SessionKey.JQXQBRQ);
		if(bi != null){
			LogConstant.runLog.info("[youbi JQXQBRQ]" + bi.getIniValue());
			retMap.put("jqxqbrq", bi.getIniValue());
		}if(bi.getIniValue() == null){
			retMap.put("jqxqbrq", defaultTime);
		}
		
		bi = youbiIniService.selectIni(SessionKey.JQXBF);
		
		if(bi != null){
			LogConstant.runLog.info("[youbi JQXBF]" + bi.getIniValue());
			retMap.put("jqxbf", bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.CCS);
		if(bi != null){
			LogConstant.runLog.info("[youbi CCS]" + bi.getIniValue());
			retMap.put("ccs", bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.JQXQNZBRQ);
		if(bi != null){
			LogConstant.runLog.info("[youbi JQXQNZBRQ]" + bi.getIniValue());
			retMap.put("jqxqnzbrq", bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.JQXDQTS);
		if(bi != null){
			LogConstant.runLog.info("[youbi JQXDQTS]" + bi.getIniValue());
			retMap.put("jqxdqts", bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.JQXSBXX);
		if(bi != null){
			LogConstant.runLog.info("[youbi JQXSBXX]" + bi.getIniValue());
			retMap.put("jqxsbxx", bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.JQXSBXXMA);
		if(bi != null){
			LogConstant.runLog.info("[youbi JQXSBXXMA]" + bi.getIniValue());
			retMap.put("jqxsbxxma", bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.SBJQXSBXX);
		if(bi != null){
			LogConstant.runLog.info("[youbi SBJQXSBXX]" + bi.getIniValue());
			retMap.put("sbjqxsbxx", bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.SBJQXSBXXMA);
		if(bi != null){
			LogConstant.runLog.info("[youbi SBJQXSBXXMA]" + bi.getIniValue());
			retMap.put("sbjqxsbxxma", bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.DEFAULTRADIO);
		if(bi != null){
			LogConstant.runLog.info("[youbi DEFAULTRADIO]" + bi.getIniValue());
			retMap.put("defaultRadio", bi.getIniValue());
		}
    	return retMap;
    }
    
	@RequestMapping("/youbi/getBaoJiaConfig")
    @ResponseBody
    public Map<String,Object> getBaoJiaConfig(){
    	Map<String,Object> retMap = new HashMap<String,Object>();
    	List<BusinessConfig> busiConfig = youBiBaoJiaConfigService.queryAllYouBiBaoJiaConfig();
    	retMap.put("busiConfigList", busiConfig);
		return retMap;  	
    }
    
	@RequestMapping("/youbi/getAllDict")
    @ResponseBody
    public Map<String,Object> getAllDict(){
    	Map<String,Object> retMap = new HashMap<String,Object>();
    	List<BusinessDict> dictList = businessDictService.queryAllYouBiBusiDict();
    	if(dictList == null){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "query business dict failed");
			return retMap;
		}
		retMap.put("retCode", "200");
    	retMap.put("dictList", dictList);
		return retMap;  	
    }
	
	@RequestMapping("/youbi/queryConfigs")
    @ResponseBody
    public Map<String, Object> queryConfigs(@RequestParam("businessCode")String businessCode){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> paramMap = new HashMap<String, String>();
		LogConstant.runLog.info("[youbi]business code is:" + businessCode);
		if(StringUtils.isEmpty(businessCode)){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		paramMap.put("businessCode", businessCode);
		List<BusinessConfig> list = youBiBaoJiaConfigService.queryConfigList(paramMap);
		if(list != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", list);
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "select failed");
		}
		return retMap;
	}
	
	@RequestMapping("/youbi/getLatestConfigs")
	@ResponseBody
	public Map<String, Object> getLatestConfigs(@RequestParam("tcType")String tcType){
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(StringUtil.isEmpty(tcType)){
			retMap.put("retCode", -1);
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		LogConstant.runLog.info("[youbi]business type is:" + tcType);
		BusinessIni bi = new BusinessIni();
		if("1".equals(tcType)){
			bi = youbiIniService.selectIni(SessionKey.XUBAOINFO);
		}else{
			bi = youbiIniService.selectIni(SessionKey.NEWBAOINFO);
		}
		retMap.put("retCode", 200);
		retMap.put("retDesc", bi);
		return retMap;
	}
	
	@RequestMapping("/youbi/queryConfig")
    @ResponseBody
    public Map<String, Object> queryConfig(@RequestParam("configId")String configId){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> paramMap = new HashMap<String, String>();
		LogConstant.runLog.info("[youbi]businessConfig id is:" + configId);
		if(StringUtils.isEmpty(configId)){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		paramMap.put("configId", configId);
		BusinessConfig config = youBiBaoJiaConfigService.queryConfigByConfigId(paramMap);
		if(config != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", config);
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "select failed");
		}
		return retMap;
	}
	
	@RequestMapping("/youbi/getConfigId")
    @ResponseBody
    public Map<String, Object> getConfigId(@RequestParam("businessCode")String businessCode, @RequestParam("baoE")String baoE){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> paramMap = new HashMap<String, String>();
		if(StringUtils.isEmpty(businessCode) || StringUtils.isEmpty(baoE)){
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "invalid parameter");
			return retMap;
		}
		paramMap.put("businessCode", businessCode);
		paramMap.put("baoE", baoE);
		BusinessConfig config = youBiBaoJiaConfigService.queryConfigByCand(paramMap);
		if(config != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", config.getId());
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "select failed");
		}
		return retMap;
	}
	
	@RequestMapping("/youbi/updateConfigs")
	@ResponseBody
	public Map<String, Object> updateConfigs(@RequestParam("tcType")String tcType, @RequestParam("tcName")String tcName,
						@RequestParam("configIds")String configIds){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni bi = new BusinessIni();
		String iniName = "1".equals(tcType)? SessionKey.XUBAOINFO : SessionKey.NEWBAOINFO;
		bi.setIniName(iniName);
		bi.setIniValue(configIds);
		bi.setIniDesc(tcName);
		int num = youbiIniService.saveBusinessIni(bi);
		if(num<=0){
			retMap.put("retCode", -1);
			retMap.put("retDesc", "保存失败");
		}else{
			retMap.put("retCode", 200);
			retMap.put("retDesc", "保存成功");
		}
		return retMap;
	}
	
	@RequestMapping("/youbi/savebjxx")
	@ResponseBody
	public Map<String, Object> savebjxx(@RequestParam("sffg")String sffg, @RequestParam("bjkg")String bjkg,
						@RequestParam("sbjqxsbxx")String sbjqxsbxx, @RequestParam("sbjqxsbxxma")String sbjqxsbxxma,
						@RequestParam("syxqbrq")String syxqbrq, @RequestParam("defaultRadio")String defaultRadio, @RequestParam("jqxqbrq")String jqxqbrq,
						@RequestParam("jqxbf")String jqxbf, @RequestParam("ccs")String ccs,
						@RequestParam("jqxqnzbrq")String jqxqnzbrq, @RequestParam("jqxdqts")String jqxdqts,
						@RequestParam("jqxsbxx")String jqxsbxx, @RequestParam("jqxsbxxma")String jqxsbxxma){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni bi = new BusinessIni();
		bi.setIniName(SessionKey.SFFG);
		bi.setIniValue(sffg);
		int r1 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.BJKG);
		bi.setIniValue(bjkg);
		int r2 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.SBJQXSBXX);
		bi.setIniValue(sbjqxsbxx);
		int r3 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.SBJQXSBXXMA);
		bi.setIniValue(sbjqxsbxxma);
		int r4 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.SYXQBRQ);
		bi.setIniValue(syxqbrq);
		int r5 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.JQXQBRQ);
		bi.setIniValue(jqxqbrq);
		int r6 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.JQXBF);
		bi.setIniValue(jqxbf);
		int r7 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.CCS);
		bi.setIniValue(ccs);
		int r8 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.JQXQNZBRQ);
		bi.setIniValue(jqxqnzbrq);
		int r9 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.JQXDQTS);
		bi.setIniValue(jqxdqts);
		int r10 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.JQXSBXX);
		bi.setIniValue(jqxsbxx);
		int r11 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.JQXSBXXMA);
		bi.setIniValue(jqxsbxxma);
		int r12 = youbiIniService.saveBusinessIni(bi);
		bi.setIniName(SessionKey.DEFAULTRADIO);
		bi.setIniValue(defaultRadio);
		int r13 = youbiIniService.saveBusinessIni(bi);
		
		int r = r1*r2*r3*r4*r5*r6*r7*r8*r9*r10*r11*r12*r13;
		if(r<=0){
			retMap.put("retCode", -1);
			retMap.put("retDesc", "保存失败");
		}else{
			retMap.put("retCode", 200);
			retMap.put("retDesc", "保存成功");
		}
		return retMap;
	}

}
