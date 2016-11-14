package com.mockCommon.controller.mock.pingan;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.service.mock.pingan.BaoJiaConfigService;

@Controller
public class BaoJiaConfigController {

	@Autowired
	private BaoJiaConfigService baoJiaConfigService;
	
	@RequestMapping("/autox/do/api/save-quote-info")
	@ResponseBody
	public String quoteInfo(@RequestParam("vehicle.vehicleId")String vehicleId,
				@RequestParam(value="vehicle.frameNo", required=false)String frameNo, @RequestParam(value="vehicle.engineNo", required=false)String engineNo,
				@RequestParam("vehicle.registerDate")String registerDate, @RequestParam("vehicle.modelName")String modelName,
				@RequestParam("register.name")String name, @RequestParam("flowId")String flowId){
		
		LogConstant.runLog.info("[Jiekoubaojia]parameter vehicleId:" + vehicleId + ", frameNo:" + frameNo + ", engineNo:" + engineNo
							+", registerDate:" + registerDate + ", modelName:" + modelName + ", name:" + name + ", flowId" + flowId);
		String result = null;
		if(StringUtils.isEmpty(vehicleId) || StringUtils.isEmpty(registerDate)
				|| StringUtils.isEmpty(modelName) || StringUtils.isEmpty(name)
				|| StringUtils.isEmpty(flowId)){
			return "Invalid parameters";
		}
		result = baoJiaConfigService.quoteInfo(vehicleId, frameNo, engineNo, registerDate, modelName, name, flowId);
		return result;
	}
}