package com.mockCommon.controller.web.pingan;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.web.pingan.ZaiBeiPingAnService;

@Controller
public class ZaiBeiPingAnController {

	@Autowired
	private ZaiBeiPingAnService zaiBeiService;
	
	@RequestMapping("/insurance/pingan/zaiBei")
	public String index(Model model){
		
		
		Map<String, String> xuBaoZaiBei = zaiBeiService.queryZaiBeiPingAn();
		
		if(xuBaoZaiBei != null && !xuBaoZaiBei.isEmpty()){
			for(Map.Entry<String, String> entry : xuBaoZaiBei.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				model.addAttribute(key, value);					
			}
		}
		
		return "/insurance/pingan/zaiBei";
	}
	
	@RequestMapping("/zaiBeiPingAn/setIniValue")
	@ResponseBody
	public Map<String, Object> setIniValue(HttpServletRequest request, Model model){		
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("iniName", request.getParameter("iniName"));
		map.put("iniValue", request.getParameter("iniValue"));
		map.put("iniDesc", request.getParameter("iniDesc"));
		int ret1 = zaiBeiService.setIniValue(map);
		int ret2 = 1;
		if(request.getParameter("iniValue").equals("delay")){
			if(request.getParameter("iniValueDelay")!=null && !request.getParameter("iniValueDelay").equals("")){
				map.put("iniName", request.getParameter("iniNameDelay"));
				map.put("iniValue", request.getParameter("iniValueDelay"));
				map.put("iniDesc", request.getParameter("iniDescDelay"));
				ret2 = zaiBeiService.setIniValue(map);
			}else{
				ret2 = 0;
			}
			
		}
				
		if(ret1 == 1 && ret2 == 1){
			retMap.put("retCode", 200);
			retMap.put("retDesc", "设置成功");
		}else{
			retMap.put("retCode", -1);
			retMap.put("retDesc", "设置失败");
		}
		return retMap;
	}
	
	
	@RequestMapping("/zaiBeiPingAn/setBackUp")
	@ResponseBody
	public Map<String, Object> setBackUp(Model model){		
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		int ret = zaiBeiService.setBackUp();
				
		if(ret == 1){
			retMap.put("retCode", 200);
			retMap.put("retDesc", "还原成功");
		}else{
			retMap.put("retCode", -1);
			retMap.put("retDesc", "还原失败");
		}
		return retMap;
	}
	
	@RequestMapping("/zaiBeiPingAn/setAllIni")
	@ResponseBody
	public Map<String, Object> setAllIni(HttpServletRequest request, Model model){		
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		int ret = zaiBeiService.setAllIni(request);
				
		if(ret == 1){
			retMap.put("retCode", 200);
			retMap.put("retDesc", "批量设置成功");
		}else{
			retMap.put("retCode", -1);
			retMap.put("retDesc", "批量设置失败");
		}
		return retMap;
	}

}