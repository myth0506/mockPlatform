package com.mockCommon.model.mock.charge;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "charge_queryCardInfo.xml", output = "queryCardInfo_out.xml")
public class QueryCardInfoMockModel {
	private String errMsg;
	private String retCode;
	private String gameUserId;
	private String userName;
	
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
	public String getGameUserId() {
		return gameUserId;
	}
	public void setGameUserId(String gameUserId) {
		this.gameUserId = gameUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}