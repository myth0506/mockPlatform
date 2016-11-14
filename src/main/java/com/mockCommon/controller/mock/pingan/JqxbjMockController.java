package com.mockCommon.controller.mock.pingan;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.pingan.JqxbjMockService;

@Controller
public class JqxbjMockController {
	
	@Autowired
	private JqxbjMockService JqxbjMockService;
	
	//@RequestMapping("/autox/do/api/force-quote")
	@ResponseBody
	public Map<String, Object> forceQuote( 
	@RequestParam("flowId") String flowId,
	@RequestParam("beginDate") String beginDate){
		Map<String, Object> retMap = JqxbjMockService.forceQuote(flowId,beginDate);
		return retMap;
	}
	
}