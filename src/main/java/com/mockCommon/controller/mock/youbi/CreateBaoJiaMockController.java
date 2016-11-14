package com.mockCommon.controller.mock.youbi;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;

@Controller("createBaoJiaMockController")
public class CreateBaoJiaMockController {
	
	@RequestMapping(value="/quote",method=RequestMethod.POST)  
	@ResponseBody
	public String createBaojia(@RequestBody Map<String, Object> params) {
		LogConstant.runLog.info("[YoubiJiekouCreateBaojia]parameter vehicle_id:" + params.get("vehicle_id") + ", city_code:" + params.get("city_code")+"" + ",selection:" + params.get("selection"));
		 if(params.get("vehicle_id")==null || params.get("city_code")==null || params.get("selection")==null){
			 return "传递参数不正确";
		 }
		 String result;
		 result = "{\"success\": true,\"code\": 200,\"data\": {\"request_id\": \"l1g9i2vmwowk36pg\"}}";
		return result;
	}
}
