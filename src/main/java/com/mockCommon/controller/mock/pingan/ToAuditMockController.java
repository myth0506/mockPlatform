package com.mockCommon.controller.mock.pingan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.pingan.ToAuditMockService;

@Controller
public class ToAuditMockController {
	

	@Autowired
	private ToAuditMockService toAuditMockService;

	@RequestMapping("/autox/do/api/to-audit")
	@ResponseBody
	public String submitAuditInfo(
			@RequestParam("flowId") String flowId,
			@RequestParam(value = "idAcquisition.smsCode", required = false) String smsCode) {
		String result = toAuditMockService.toAudit(flowId, smsCode);
		return result;
	}
	
}