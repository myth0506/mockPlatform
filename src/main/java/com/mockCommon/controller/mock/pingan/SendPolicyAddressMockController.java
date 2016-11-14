package com.mockCommon.controller.mock.pingan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.pingan.SendPolicyAddressMockService;

@Controller
public class SendPolicyAddressMockController {

	@Autowired
	private SendPolicyAddressMockService sendPolicyAddressMockService;

	@RequestMapping("/autox/do/get-sendpolicy-address")
	@ResponseBody
	public String sendPolicyAddress(@RequestParam("flowId") String flowId) {
		String result = sendPolicyAddressMockService.sendPolicyAddress(flowId);
		return result;
	}
}