package com.mockCommon.controller.mock.pingan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.pingan.SearchCarInfoMockService;

@Controller("pinganSearchCarInfoMockController")
public class SearchCarInfoMockController {

	@Autowired
	private SearchCarInfoMockService searchCarInfoMockService;

	@RequestMapping("/autox/do/api/to-query-info")
	@ResponseBody
	public String toQyeryInfo(
			@RequestParam("flowId") String flowId,
			@RequestParam(value = "renewalJump", required = false) String renewalJump) {
		String result = searchCarInfoMockService.toQueryInfo(flowId,
				renewalJump);
		return result;
	}
}