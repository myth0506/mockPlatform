package com.mockCommon.controller.web.youbi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.web.youbi.ZaiBeiYouBiService;

@Controller
public class ZaiBeiYouBiController {
	
	@Autowired 
	private ZaiBeiYouBiService zaiBeiYouBiService;

	@RequestMapping("/insurance/youbi/zaiBeiYouBi")
	public String index(){
	
		return "/insurance/youbi/zaiBeiYouBi";
	}
	
	@RequestMapping("/zaiBeiYouBi/setIniValue")
	@ResponseBody
	public Map<String,Object> setIniValue(HttpServletRequest request,Model model){
		
		Map<String,Object> retMap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("iniName", request.getParameter("iniName"));
		map.put("iniValue", request.getParameter("iniValue"));
		map.put("iniDesc", request.getParameter("iniDesc"));
		int ret1 = zaiBeiYouBiService.setIniValue(map);
		int ret2 = 1;
		if (request.getParameter("iniValue").equals("delay")){
			if(request.getParameter("iniValueDelay")!=null && !request.getParameter("iniValueDelay").equals("")){
				map.put("iniName", request.getParameter("iniNameDelay"));
				map.put("iniValue", request.getParameter("iniValueDelay"));
				map.put("iniDesc", request.getParameter("iniDescDelay"));
				ret2 = zaiBeiYouBiService.setIniValue(map);
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
	
}
