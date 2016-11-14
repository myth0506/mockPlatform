package com.mockCommon.model.mock.pingan;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "getSendPolicyAddress_template.json", output = "sendPolicyAddress_out.json")
public class SendPolicyAddressMockModel {
	private String name;
	private String mobile;
	private String provinceName;
	private String cityName;
	private String townCityCode;
	private String detail;
	private String confirmSendFlag;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTownCityCode() {
		return townCityCode;
	}
	public void setTownCityCode(String townCityCode) {
		this.townCityCode = townCityCode;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getConfirmSendFlag() {
		return confirmSendFlag;
	}
	public void setConfirmSendFlag(String confirmSendFlag) {
		this.confirmSendFlag = confirmSendFlag;
	}
	
	@Override
	public String toString() {
		return "SendPolicyAddressMockModel [name=" + name + ", mobile="
				+ mobile + ", provinceName=" + provinceName + ", cityName="
				+ cityName + ", townCityCode=" + townCityCode + ", detail="
				+ detail + ", confirmSendFlag=" + confirmSendFlag + "]";
	}
	
}