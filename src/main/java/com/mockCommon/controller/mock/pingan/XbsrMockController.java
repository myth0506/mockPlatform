package com.mockCommon.controller.mock.pingan;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.pingan.XbsrMockService;

@Controller
public class XbsrMockController {
	
	@Autowired
	private XbsrMockService xbsrMockService;
	
	@RequestMapping("/autox/do/api/renewal-check")
	@ResponseBody
	public Map<String, Object> renewalCheck() {
		Map<String, Object> retMap = xbsrMockService.renewalCheck();
		return retMap;
	}
	
	@RequestMapping("/autox/do/api/renewal-confirm")
	@ResponseBody
	public Map<String, Object> renewalConfirm(
			@RequestParam("flowId") String flowId,
			@RequestParam("renewal.idNo") String idNo) {
		Map<String, Object> retMap = xbsrMockService.renewalConfirm(flowId, idNo);
		return retMap;
	}
}