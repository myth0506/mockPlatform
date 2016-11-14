package com.mockCommon.model.mock.pingan;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "toAudit_template.json", output = "toAudit_out.json")
public class AuditInfoMockModel {
	private String flowId;
	private String resultCode;
	private String message;
	private String bizApplyNo;
	private String jqxApplyNo;
	private float bizPremium;
	private String bizOrderNo;
	private float jqxPremium;
	private String jqxOrderNo;
	
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBizApplyNo() {
		return bizApplyNo;
	}
	public void setBizApplyNo(String bizApplyNo) {
		this.bizApplyNo = bizApplyNo;
	}
	public String getJqxApplyNo() {
		return jqxApplyNo;
	}
	public void setJqxApplyNo(String jqxApplyNo) {
		this.jqxApplyNo = jqxApplyNo;
	}
	public float getBizPremium() {
		return bizPremium;
	}
	public void setBizPremium(float bizPremium) {
		this.bizPremium = bizPremium;
	}
	public String getBizOrderNo() {
		return bizOrderNo;
	}
	public void setBizOrderNo(String bizOrderNo) {
		this.bizOrderNo = bizOrderNo;
	}
	public float getJqxPremium() {
		return jqxPremium;
	}
	public void setJqxPremium(float jqxPremium) {
		this.jqxPremium = jqxPremium;
	}
	public String getJqxOrderNo() {
		return jqxOrderNo;
	}
	public void setJqxOrderNo(String jqxOrderNo) {
		this.jqxOrderNo = jqxOrderNo;
	}
	
	@Override
	public String toString() {
		return "AuditInfoMockModel [flowId=" + flowId + ", resultCode="
				+ resultCode + ", message=" + message + ", bizApplyNo="
				+ bizApplyNo + ", jqxApplyNo=" + jqxApplyNo + ", bizPremium="
				+ bizPremium + ", bizOrderNo=" + bizOrderNo + ", jqxPremium="
				+ jqxPremium + ", jqxOrderNo=" + jqxOrderNo + "]";
	}
	
}