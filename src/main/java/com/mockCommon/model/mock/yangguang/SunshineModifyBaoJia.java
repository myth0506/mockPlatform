package com.mockCommon.model.mock.yangguang;

import java.sql.Timestamp;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "sunshine_responsePackage.xml", output = "sunshine_modify_baojia.xml")
public class SunshineModifyBaoJia {
	private String requestType;
	private String insureType;
	private String status;
	private String errorMessage;
	private Timestamp sendTime;
	private String sign;
	private String packageBody;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPackageBody() {
		return packageBody;
	}
	public void setPackageBody(String packageBody) {
		this.packageBody = packageBody;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getInsureType() {
		return insureType;
	}
	public void setInsureType(String insureType) {
		this.insureType = insureType;
	}
}
