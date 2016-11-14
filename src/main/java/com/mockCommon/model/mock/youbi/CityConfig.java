package com.mockCommon.model.mock.youbi;
/**
 * @author bjhuwei1
 * forceFlag: 交强险标志位, 0: 不可投保, 1: 交强险/车船税, 2: 交强险, 3: 车船税
 * separateTax: 车船税是否单独投保, 若此标志位为 1 则上车船税险种选择需要传送 tax 代码
 * forceDays: 交强险提前投保天数
 * bizDays: 商业险提前投保天数
 * supportDrivingArea: 是否支持指定驾驶区域
 * supportAssignDriver: 是否支持指定驾驶人
 */
public class CityConfig {
	private String forceFlag;
	private String separateTax;
	private String forceDays;
	private String bizDays;
	private String supportDrivingArea;
	private String supportAssignDriver;
	public String getForceFlag() {
		return forceFlag;
	}
	public void setForceFlag(String forceFlag) {
		this.forceFlag = forceFlag;
	}
	public String getSeparateTax() {
		return separateTax;
	}
	public void setSeparateTax(String separateTax) {
		this.separateTax = separateTax;
	}
	public String getForceDays() {
		return forceDays;
	}
	public void setForceDays(String forceDays) {
		this.forceDays = forceDays;
	}
	public String getBizDays() {
		return bizDays;
	}
	public void setBizDays(String bizDays) {
		this.bizDays = bizDays;
	}
	public String getSupportDrivingArea() {
		return supportDrivingArea;
	}
	public void setSupportDrivingArea(String supportDrivingArea) {
		this.supportDrivingArea = supportDrivingArea;
	}
	public String getSupportAssignDriver() {
		return supportAssignDriver;
	}
	public void setSupportAssignDriver(String supportAssignDriver) {
		this.supportAssignDriver = supportAssignDriver;
	}
}
