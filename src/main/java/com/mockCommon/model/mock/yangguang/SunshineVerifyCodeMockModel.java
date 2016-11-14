package com.mockCommon.model.mock.yangguang;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "sunshine_verifyCode.xml", output = "verifyCode_out.xml")
public class SunshineVerifyCodeMockModel {
	private String responseCode;
	private String responseMessage;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}