package com.mockCommon.model.mock.yangguang;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "sunshine_payment.xml", output = "payment_out.xml")
public class SunshinePaymentMockModel {
	private String payAmount;
	private String payTime;
	private String tBOrderId;
	private String bizProposalNo;
	private String bizPolicyNo;
	private String forceProposalNo;
	private String forcePolicyNo;
	
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String gettBOrderId() {
		return tBOrderId;
	}
	public void settBOrderId(String tBOrderId) {
		this.tBOrderId = tBOrderId;
	}
	public String getBizProposalNo() {
		return bizProposalNo;
	}
	public void setBizProposalNo(String bizProposalNo) {
		this.bizProposalNo = bizProposalNo;
	}
	public String getBizPolicyNo() {
		return bizPolicyNo;
	}
	public void setBizPolicyNo(String bizPolicyNo) {
		this.bizPolicyNo = bizPolicyNo;
	}
	public String getForceProposalNo() {
		return forceProposalNo;
	}
	public void setForceProposalNo(String forceProposalNo) {
		this.forceProposalNo = forceProposalNo;
	}
	public String getForcePolicyNo() {
		return forcePolicyNo;
	}
	public void setForcePolicyNo(String forcePolicyNo) {
		this.forcePolicyNo = forcePolicyNo;
	}
}