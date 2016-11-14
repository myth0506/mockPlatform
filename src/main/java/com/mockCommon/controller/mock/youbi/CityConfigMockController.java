package com.mockCommon.controller.mock.youbi;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.youbi.CityConfigMockService;

@Controller("cityConfigMockController")
public class CityConfigMockController {
	
	@Autowired
	private CityConfigMockService cityConfigMockService;

	@RequestMapping("/city/{cityCode}")
	@ResponseBody
	public String getCityConfig(HttpServletResponse response, @PathVariable String cityCode) {
		 response.setContentType("text/html; charset=UTF-8");  
		String result = cityConfigMockService.getCityConfig(cityCode);
		return result;
	}
}
