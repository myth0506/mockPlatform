package com.mockCommon.model.web.pingan;


public class PolicyAndOrderInfoModel {
	private String policyStatus;
	private String setOrderStatus;
	private String bizOrder;
	private String jqxOrder;
	
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public String getSetOrderStatus() {
		return setOrderStatus;
	}
	public void setSetOrderStatus(String setOrderStatus) {
		this.setOrderStatus = setOrderStatus;
	}
	public String getBizOrder() {
		return bizOrder;
	}
	public void setBizOrder(String bizOrder) {
		this.bizOrder = bizOrder;
	}
	public String getJqxOrder() {
		return jqxOrder;
	}
	public void setJqxOrder(String jqxOrder) {
		this.jqxOrder = jqxOrder;
	}
	
	@Override
	public String toString() {
		return "PolicyAndOrderInfoModel [policyStatus=" + policyStatus
				+ ", setOrderStatus=" + setOrderStatus + ", bizOrder="
				+ bizOrder + ", jqxOrder=" + jqxOrder + "]";
	}
	
}