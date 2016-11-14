package com.mockCommon.controller.web.youbi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarTypeController {
	
	@RequestMapping()
	@ResponseBody
	public String inputCarType(){
		return "";
		
	}

}
