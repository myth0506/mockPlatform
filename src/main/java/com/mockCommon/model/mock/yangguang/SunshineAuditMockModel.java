package com.mockCommon.model.mock.yangguang;

import com.mockCommon.util.freeMarker.DataTemplate;

/**
 * @author li_zhe
   tBOrderId	第三方总订单号
   bizProposalNo	保险公司投保单号
   forceProposalNo	保险公司投保单号
   itemId	第三方商品ID
   forcePremium	交强保费
   vehicleTaxPremium	车船税
   bizTotalPremium	商业总保费
   standardPremium	市场价
   totalPremium	网购价
   isIdVerifi	是否需要身份证验证
 */
@DataTemplate(template = "sunshine_audit.xml", output = "sunshine_audit_out.json")
public class SunshineAuditMockModel {
	private String tBOrderId;
	private String bizProposalNo;
	private String forceProposalNo;
	private String itemId;
	private String forcePremium;
	private String vehicleTaxPremium;
	private String bizTotalPremium;
	private String standardPremium;
	private String totalPremium;
	private String isIdVerifi;
	
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
	public String getForceProposalNo() {
		return forceProposalNo;
	}
	public void setForceProposalNo(String forceProposalNo) {
		this.forceProposalNo = forceProposalNo;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getForcePremium() {
		return forcePremium;
	}
	public void setForcePremium(String forcePremium) {
		this.forcePremium = forcePremium;
	}
	public String getVehicleTaxPremium() {
		return vehicleTaxPremium;
	}
	public void setVehicleTaxPremium(String vehicleTaxPremium) {
		this.vehicleTaxPremium = vehicleTaxPremium;
	}
	public String getBizTotalPremium() {
		return bizTotalPremium;
	}
	public void setBizTotalPremium(String bizTotalPremium) {
		this.bizTotalPremium = bizTotalPremium;
	}
	public String getStandardPremium() {
		return standardPremium;
	}
	public void setStandardPremium(String standardPremium) {
		this.standardPremium = standardPremium;
	}
	public String getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getIsIdVerifi() {
		return isIdVerifi;
	}
	public void setIsIdVerifi(String isIdVerifi) {
		this.isIdVerifi = isIdVerifi;
	}
}