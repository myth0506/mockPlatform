package com.mockCommon.model.mock.pingan;

import java.util.Map;

import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.pingan.BaoJiaConfig;
import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "businessinsure_template.json", output = "businessinsure_out.json")
public class BusinessConfigMockModel {

	private String flowId;
	private String resultCode;
	private String pkgName;
	private String beginDate; 
	private String inputAmount;
	private BaoJiaConfig baoJiaConfig;
	private Map<String, String> iniMap;
	private Map<String, BusinessConfig> dynamicIniMap;
	private String isApplyBiz;
	private String isApplyForce;
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getInputAmount() {
		return inputAmount;
	}
	public void setInputAmount(String inputAmount) {
		this.inputAmount = inputAmount;
	}
	public Map<String, String> getIniMap() {
		return iniMap;
	}
	public void setIniMap(Map<String, String> iniMap) {
		this.iniMap = iniMap;
	}
	public BaoJiaConfig getBaoJiaConfig() {
		return baoJiaConfig;
	}
	public void setBaoJiaConfig(BaoJiaConfig baoJiaConfig) {
		this.baoJiaConfig = baoJiaConfig;
	}
	public Map<String, BusinessConfig> getDynamicIniMap() {
		return dynamicIniMap;
	}
	public void setDynamicIniMap(Map<String, BusinessConfig> dynamicIniMap) {
		this.dynamicIniMap = dynamicIniMap;
	}
	public String getIsApplyBiz() {
		return isApplyBiz;
	}
	public void setIsApplyBiz(String isApplyBiz) {
		this.isApplyBiz = isApplyBiz;
	}
	public String getIsApplyForce() {
		return isApplyForce;
	}
	public void setIsApplyForce(String isApplyForce) {
		this.isApplyForce = isApplyForce;
	}
}
