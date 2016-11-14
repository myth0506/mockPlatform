package com.mockCommon.model.web.pingan;

public class AuditInfoModel {
	private String auditStatus;
	private String smsCodeStatus;
	private float bizPremium;
	private String bizOrderNo;
	private float jqxPremium;
	private String jqxOrderNo;
	private String auditSmsCode;
	
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getSmsCodeStatus() {
		return smsCodeStatus;
	}
	public void setSmsCodeStatus(String smsCodeStatus) {
		this.smsCodeStatus = smsCodeStatus;
	}
	public String getAuditSmsCode() {
		return auditSmsCode;
	}
	public void setAuditSmsCode(String auditSmsCode) {
		this.auditSmsCode = auditSmsCode;
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
		return "AuditInfoModel [auditStatus=" + auditStatus
				+ ", smsCodeStatus=" + smsCodeStatus + ", bizPremium="
				+ bizPremium + ", bizOrderNo=" + bizOrderNo + ", jqxPremium="
				+ jqxPremium + ", jqxOrderNo=" + jqxOrderNo + ", auditSmsCode="
				+ auditSmsCode + "]";
	}
	
}