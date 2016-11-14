package com.mockCommon.controller.mock.pingan;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.pingan.GetSpecialPromiseMockService;

@Controller
public class GetSpecialPromiseMockController {

	@Autowired
	private GetSpecialPromiseMockService getSpecialPromiseMockService;

	@RequestMapping("/autox/do/get-special-promise")
	@ResponseBody
	public Map<String, Object> getSpecialPromise(
			@RequestParam("flowId") String flowId) {
		Map<String, Object> retMap = getSpecialPromiseMockService
				.getSpecialPromise(flowId);
		return retMap;
	}
}