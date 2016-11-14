package com.mockCommon.controller.mock.pingan;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.service.mock.pingan.ForceInsureConfigService;

@Controller
public class ForceConfigController {

	@Autowired
	private ForceInsureConfigService forceInsureConfigService;
	
	@RequestMapping("/autox/do/api/force-quote")
	@ResponseBody
	public String forceQuote(@RequestParam(value="forceInfo.beginDate", required=false)String beginDate, 
						@RequestParam("flowId")String flowId){
		String result = null;
		LogConstant.runLog.info("[forceInfo]flowId is:" + flowId);
		if(StringUtils.isEmpty(flowId)){
			return "invalid parameter";
		}
		result = forceInsureConfigService.forceQuote(beginDate, flowId);
		return result;
	}
}
