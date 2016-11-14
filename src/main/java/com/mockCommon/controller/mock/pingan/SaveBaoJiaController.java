package com.mockCommon.controller.mock.pingan;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.service.mock.pingan.impl.SaveBaoJiaServiceImpl;

@Controller
public class SaveBaoJiaController {
	
	@Autowired
	private SaveBaoJiaServiceImpl saveBaoJiaServiceImpl; 
	@RequestMapping("/autox/do/api/to-supplement-info")
	@ResponseBody
	public String saveBaoJia(@RequestParam("bizConfig.pkgName")String pkgName, @RequestParam("forceInfo.isApplyForce")String isApplyForce,
				@RequestParam(value="bizInfo.isNeedRuleCheck", required=false)String isNeedRuleCheck, @RequestParam("flowId")String flowId,
				@RequestParam(value="bizInfo.isApplyBiz", required=false)String isApplyBiz){
		LogConstant.runLog.info("[to-supplement-info]pkgName is:" + pkgName + ", isApplyForce is: "+ isApplyForce + ", flowId is: " + flowId);
		if(StringUtils.isEmpty(pkgName) || StringUtils.isEmpty(isApplyForce) || StringUtils.isEmpty(flowId)){
			return "invalida parameters";
		}
		String result = saveBaoJiaServiceImpl.saveBaoJia(pkgName, isApplyForce, isNeedRuleCheck, flowId, isApplyBiz);
		return result;
	}
}