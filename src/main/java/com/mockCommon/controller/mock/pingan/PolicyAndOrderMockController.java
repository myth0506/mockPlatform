package com.mockCommon.controller.mock.pingan;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.pingan.PolicyAndOrderMockService;

@Controller
public class PolicyAndOrderMockController {

	@Autowired
	private PolicyAndOrderMockService policyAndOrderMockService;

	@RequestMapping("/autox/do/query-policy-status")
	@ResponseBody
	public Map<String, Object> queryPolicystatus() {
		return policyAndOrderMockService.queryPolicyStatus();
	}

	@RequestMapping("/autox/do/accept-shop-order")
	@ResponseBody
	public Map<String, Object> acceptShopOrder(
			@RequestParam(value = "bizOrderNo", required = false) String bizOrderNo,
			@RequestParam(value = "forceOrderNo", required = false) String forceOrderNo) {
		return policyAndOrderMockService.acceptShopOrder(bizOrderNo,
				forceOrderNo);
	}
}