package com.mockCommon.model.mock.pay;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "wyb_transfer.xml", output = "transfer_out.xml")
public class WybTransferMockModel {
	private String PlatformId;
	private String OutAccount;
	private String InAccount;
	private String ProductId;
	private String ProductTime;
	private String TransferTime;
	private String TransferAmount;
	private String AccountType;
	private String CurrencyType;
	private String TransferStatus;
	private String TransferType;
	private String TransResult;
	private String Sign;
	
	public String getPlatformId() {
		return PlatformId;
	}
	public void setPlatformId(String platformId) {
		PlatformId = platformId;
	}
	public String getOutAccount() {
		return OutAccount;
	}
	public void setOutAccount(String outAccount) {
		OutAccount = outAccount;
	}
	public String getInAccount() {
		return InAccount;
	}
	public void setInAccount(String inAccount) {
		InAccount = inAccount;
	}
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String productId) {
		ProductId = productId;
	}
	public String getProductTime() {
		return ProductTime;
	}
	public void setProductTime(String productTime) {
		ProductTime = productTime;
	}
	public String getTransferTime() {
		return TransferTime;
	}
	public void setTransferTime(String transferTime) {
		TransferTime = transferTime;
	}
	public String getTransferAmount() {
		return TransferAmount;
	}
	public void setTransferAmount(String transferAmount) {
		TransferAmount = transferAmount;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getCurrencyType() {
		return CurrencyType;
	}
	public void setCurrencyType(String currencyType) {
		CurrencyType = currencyType;
	}
	public String getTransferStatus() {
		return TransferStatus;
	}
	public void setTransferStatus(String transferStatus) {
		TransferStatus = transferStatus;
	}
	public String getTransferType() {
		return TransferType;
	}
	public void setTransferType(String transferType) {
		TransferType = transferType;
	}
	public String getTransResult() {
		return TransResult;
	}
	public void setTransResult(String transResult) {
		TransResult = transResult;
	}
	public String getSign() {
		return Sign;
	}
	public void setSign(String sign) {
		Sign = sign;
	}
}