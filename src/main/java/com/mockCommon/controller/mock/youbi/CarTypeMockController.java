package com.mockCommon.controller.mock.youbi;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.service.mock.youbi.CarTypeMockService;

@Controller
public class CarTypeMockController {

	@Autowired
	private CarTypeMockService carTypeMockService;

	@RequestMapping(value="/vehicle/input",method=RequestMethod.POST)
    @ResponseBody
    public String vehicleInput(@RequestBody Map<String,Object> params){
		LogConstant.runLog.info("[JiekouInputCarModel]parameter city_code:"
				+ params.get("city_code") + ", license_no:"
				+ params.get("license_no")+ ", license_owner:"
				+ params.get("license_owner")+ ", frame_no:"
				+ params.get("frame_no")+ ", engine_no:"
				+ params.get("engine_no")+ ", vehicle_name:"
				+ params.get("vehicle_name")+ ", enroll_date:"
				+ params.get("enroll_date")+ ", seat_count:"
				+ params.get("seat_count"));	
		if (params.get("city_code") == null || params.get("license_no") == null
				|| params.get("license_owner") == null
				|| params.get("frame_no") == null||params.get("engine_no") == null
				|| params.get("vehicle_name") == null ||params.get("enroll_date") == null
				|| params.get("seat_count") == null ) {
			return "传递参数不正确";
		}
		String result = carTypeMockService.vehicleInput(params.get("city_code").toString(), 
				params.get("license_no").toString(),
				params.get("license_owner").toString(), params.get("frame_no")
						.toString(), params.get("engine_no").toString(), params
						.get("vehicle_name").toString(), params.get("enroll_date")
						.toString(), params.get("seat_count").toString());
	return result;
}
}
