package com.mockCommon.controller.web.youbi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.model.web.youbi.BusinessConfigInfo;
import com.mockCommon.model.web.youbi.BusinessDictModel;
import com.mockCommon.service.web.youbi.YoubiBusiConfigInfoService;
import com.mockCommon.service.web.youbi.YoubiBusiConfigService;

@Controller
public class YoubiBusinessConfigController {

	@Autowired 
	private YoubiBusiConfigService youbiBusiConfigService;
	
	@Autowired
	private YoubiBusiConfigInfoService youbiBusiConfigInfoService;

	@RequestMapping("/youbi/insertMulti")
	@ResponseBody
	public Map<String,Object> insertMultiBusiness(@RequestParam("name") String name,@RequestParam("code") String code){
		Map<String,Object> retMap = new HashMap<String,Object>();
		
		BusinessDictModel bc = new BusinessDictModel();
		bc.setName(name);
		bc.setCode(code);
		bc.setType("1");
		bc = youbiBusiConfigService.insertConfig(bc);
		if (bc != null){
			retMap.put("retCode", "200");
			retMap.put("retMessage", "配置成功");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retMessage", "配置失败");
		}
		return retMap;
	}
	
	//商业配置险
	@RequestMapping("/youbi/queryConfigModel")
	@ResponseBody
	public Map<String,Object> queryConfigModel(@RequestParam("code") String code){
		Map<String,Object> retMap = new HashMap<String,Object>();
		Map<String,String> bc = new HashMap<String,String>();
		bc.put("code", code);
		List<BusinessDictModel> list = youbiBusiConfigService.queryConfigList(bc);
		
		if (list != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", list);
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "选择失败");
		}
		return retMap;
	}
	
	//车辆损失险
	@RequestMapping("/youbi/insertConfigInfo")
	@ResponseBody
	public Map<String, Object> insertConfigInfo(
			@RequestParam("wenan") String wenan,
			@RequestParam("baoe") String baoe,
			@RequestParam("baofei") String baofei,
			@RequestParam("isvalid") String isvalid,
			@RequestParam("isvote") String isvote,
			@RequestParam("businesscode") String businesscode) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		
		Map<String,String> bc = new HashMap<String,String>();
		bc.put("businesscode", businesscode);
		List<BusinessDictModel> list = youbiBusiConfigService.queryConfigList(bc);
		BusinessConfigInfo bci = new BusinessConfigInfo();
		for (int i = 0;i<list.size();i++){
			String  code = list.get(i).getCode();
			if (code.equals(businesscode)){
				String  id = list.get(i).getId();
				bci.setBusinessid(id);
			}
		
		}
		
		
		bci.setWenan(wenan);
		bci.setBaoe(baoe);
		bci.setBaofei(baofei);
		bci.setIsvalid(isvalid);
		bci.setIsvote(isvote);
		bci.setBusinesscode(businesscode);

		bci = youbiBusiConfigInfoService.insertConfigInfo(bci);
		if (bci != null){
			retMap.put("retCode", "200");
			retMap.put("retMessage", "配置成功");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retMessage", "配置失败");
		}
		return retMap;
	}
	
	
	@RequestMapping("/youbi/queryConfigInfo")
	@ResponseBody
	public Map<String,Object> queryConfigInfo(@RequestParam("businesscode") String businesscode){
		Map<String,Object> retMap = new HashMap<String,Object>();
		Map<String,String> bc = new HashMap<String,String>();
		BusinessConfigInfo bci = new BusinessConfigInfo();
		bci.setBusinesscode(businesscode);
		List<BusinessConfigInfo> list = youbiBusiConfigInfoService.queryConfigInfoList(bci);
		
		if (list != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", list);
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "选择失败");
		}
		return retMap;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/youbi/DeleteInsr")
	@ResponseBody
	public Map<String, String> deleteInsr(@RequestParam("id") String id){
		Map<String,String> retMap = new HashMap<String,String>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		BusinessDictModel bcm = new BusinessDictModel();
		bcm.setId(id);
		bcm.setStatus("0");
		int retCode = youbiBusiConfigService.updateConfig(bcm);
		
		if (retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "删除成功");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "删除失败");
		}
		return retMap;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/youbi/ModifyInsr")
	@ResponseBody
	public Map<String, String> ModifyInsr(@RequestParam("id") String id,
			@RequestParam("name") String name, @RequestParam("code") String code) {
		Map<String, String> retMap = new HashMap<String, String>();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		BusinessDictModel bcm = new BusinessDictModel();
		bcm.setId(id);
		bcm.setCode(code);
		bcm.setName(name);
		int retCode = youbiBusiConfigService.modifyConfig(bcm);
		
		if (retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "删除成功");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "删除失败");
		}
		return retMap;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/youbi/ModifyConfigInfo")
	@ResponseBody
	public Map<String, String> ModifyConfigInfo(@RequestParam("id") String id,
			@RequestParam("wenan") String wenan, @RequestParam("baoe") String baoe,@RequestParam("baofei") String baofei,
			@RequestParam("isvalid") String isvalid,@RequestParam("isvote") String isvote) {
		Map<String, String> retMap = new HashMap<String, String>();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		BusinessConfigInfo bci = new BusinessConfigInfo();
		bci.setId(id);
		bci.setBaoe(baoe);
		bci.setBaofei(baofei);
		bci.setIsvalid(isvalid);
		bci.setIsvote(isvote);
	
		int retCode = youbiBusiConfigInfoService.modifyConfigInfo(bci);
		
		if (retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "删除成功");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "删除失败");
		}
		return retMap;
	}
	
	@RequestMapping("/youbi/DeleteConfigInfo")
	@ResponseBody
	public Map<String, String> deleteConfigInfo(@RequestParam("id") String id){
		Map<String,String> retMap = new HashMap<String,String>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		BusinessConfigInfo bci = new BusinessConfigInfo();
		bci.setId(id);
		bci.setStatus("0");
		int retCode = youbiBusiConfigInfoService.updateConfigInfo(bci);
		
		if (retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "删除成功");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "删除失败");
		}
		return retMap;
	}
	
	
	@RequestMapping("/youbi/insertBaoXian")
	@ResponseBody
	public Map<String, String> insertBaoXian(
			@RequestParam("wenan") String wenan,
			@RequestParam("baoe") String baoe,
			@RequestParam("baofei") String baofei,
			@RequestParam("isvalid") String isvalid,
			@RequestParam("isvote") String isvote) {
		Map<String,String> retCode = new HashMap<String,String>();

				return null;

	}

}
