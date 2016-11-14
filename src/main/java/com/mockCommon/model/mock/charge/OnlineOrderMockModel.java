package com.mockCommon.model.mock.charge;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "charge_onlineorder.xml", output = "onlineorder_out.xml")
public class OnlineOrderMockModel {
	private String errMsg;
	private String retCode;
	private String orderId;
	private String cardId;
	private String cardNum;
	private String orderCash;
	private String cardName;
	private String sporderId;
	private String gameUserId;
	private String gameState;
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getOrderCash() {
		return orderCash;
	}
	public void setOrderCash(String orderCash) {
		this.orderCash = orderCash;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getSporderId() {
		return sporderId;
	}
	public void setSporderId(String sporderId) {
		this.sporderId = sporderId;
	}
	public String getGameUserId() {
		return gameUserId;
	}
	public void setGameUserId(String gameUserId) {
		this.gameUserId = gameUserId;
	}
	public String getGameState() {
		return gameState;
	}
	public void setGameState(String gameState) {
		this.gameState = gameState;
	}
}