package com.mockCommon.model.mock.pingan;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "force_template.json", output = "force_insure_out.json")
public class ForceConfigMockModel {

	private String beginDate;
	private String isApplyForce;
	private String forceFee;
	private String taxFee;
	private String resultCode;
	private String flowId;
	private String errorMessage;
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getIsApplyForce() {
		return isApplyForce;
	}
	public void setIsApplyForce(String isApplyForce) {
		this.isApplyForce = isApplyForce;
	}
	public String getForceFee() {
		return forceFee;
	}
	public void setForceFee(String forceFee) {
		this.forceFee = forceFee;
	}
	public String getTaxFee() {
		return taxFee;
	}
	public void setTaxFee(String taxFee) {
		this.taxFee = taxFee;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
