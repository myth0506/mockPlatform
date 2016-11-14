package com.mockCommon.controller.mock.pingan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.pingan.BusinessConfigService;

@Controller
public class BusinessConfigController {

	@Autowired
	private BusinessConfigService businessConfigService;
	@RequestMapping("/autox/do/api/biz-quote")
	@ResponseBody
	public String bizQuote(@RequestParam("flowId")String flowId, @RequestParam("bizConfig.pkgName")String pkgName,
			@RequestParam(value="bizInfo.beginDate", required=false)String beginDate, @RequestParam(value="bizConfig.inputAmount", required=false)String inputAmount,
			@RequestParam(value="bizConfig.amount01", required=false)String amount01, @RequestParam(value="bizConfig.amount02", required=false)String amount02,
			@RequestParam(value="bizConfig.amount03", required=false)String amount03, @RequestParam(value="bizConfig.amount04", required=false)String amount04,
			@RequestParam(value="bizConfig.amount05", required=false)String amount05, @RequestParam(value="bizConfig.amount08", required=false)String amount08,
			@RequestParam(value="bizConfig.amount17", required=false)String amount17, @RequestParam(value="bizConfig.amount18", required=false)String amount18,
			@RequestParam(value="bizConfig.amount27", required=false)String amount27, @RequestParam(value="bizConfig.amount28", required=false)String amount28,
			@RequestParam(value="bizConfig.amount48", required=false)String amount48, @RequestParam(value="bizConfig.amount49", required=false)String amount49,
			@RequestParam(value="bizConfig.amount50", required=false)String amount50, @RequestParam(value="bizConfig.amount41", required=false)String amount41,
			@RequestParam(value="bizConfig.amount57", required=false)String amount57, @RequestParam(value="bizConfig.amount59", required=false)String amount59,
			@RequestParam(value="bizConfig.amount63", required=false)String amount63){
		
		return businessConfigService.bizQuote(flowId, pkgName, beginDate, inputAmount, amount01, amount02, amount03, amount04, amount05,
				amount08, amount17, amount18, amount27, amount28, amount48, amount49, amount50, amount41, amount57, amount59, amount63);
	}
}
