package com.mockCommon.model.mock.yangguang;

import java.sql.Timestamp;

import com.mockCommon.util.freeMarker.DataTemplate;

/**
 *@author li_zhe
  requestType 返回类型
  insureType 
  status   返回状态
  errorMessage   错误信息
  sendTime 发送时间
  sign  签名
  packageBody 报文体
 */
@DataTemplate(template = "sunshine_responsePackage.xml", output = "responsePackage_out.xml")
public class ResponsePackageModel {
	private String requestType;
	private String insureType;
	private String status;
	private String errorMessage;
	private Timestamp sendTime;
	private String sign;
	private String packageBody;
	
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
}