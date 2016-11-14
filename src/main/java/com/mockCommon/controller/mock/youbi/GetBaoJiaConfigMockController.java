package com.mockCommon.controller.mock.youbi;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.youbi.GetBaoJiaConfigMockService;

@Controller
public class GetBaoJiaConfigMockController {
	
	@Autowired
	private GetBaoJiaConfigMockService getBaoJiaConfigMockService;

	@RequestMapping("/quote/{request_id}")
	@ResponseBody
	public String getBaoJiaConfigMock(HttpServletResponse response,@PathVariable String request_id){
		 response.setContentType("text/html; charset=UTF-8");  
		String result = getBaoJiaConfigMockService.getBaoJiaConfig(request_id);
		return result;
	}
	
	@RequestMapping("/renewal/{request_id}")
	@ResponseBody
	public String getLastYearBaoJiaConfigMock(HttpServletResponse response,@PathVariable String request_id){
		 response.setContentType("text/html; charset=UTF-8");  
		String result = getBaoJiaConfigMockService.getLastYearBaoJiaConfig(request_id);
		return result;
	}
}
