package com.mockCommon.model.mock.pingan;

import java.util.Map;

import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.pingan.BaoJiaConfig;
import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "save_baojia_template.json", output = "save_baojia_out.json")
public class SaveBaoJiaMockModel {

	private String flowId;
	private String resultCode;
	private SearchCarInfoMockModel searchCarInfoMockModel;
	private BaoJiaConfig baoJiaConfig;
	private Map<String, String> iniMap;
	private Map<String, BusinessConfig> dynamicIniMap;
	private String isApplyForce;
	private String forceFee;
	private String taxFee;
	private String jqxBeginDate;
	private String shyxBeginDate;
	private String isApplyBiz;
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
	public SearchCarInfoMockModel getSearchCarInfoMockModel() {
		return searchCarInfoMockModel;
	}
	public void setSearchCarInfoMockModel(
			SearchCarInfoMockModel searchCarInfoMockModel) {
		this.searchCarInfoMockModel = searchCarInfoMockModel;
	}
	public BaoJiaConfig getBaoJiaConfig() {
		return baoJiaConfig;
	}
	public void setBaoJiaConfig(BaoJiaConfig baoJiaConfig) {
		this.baoJiaConfig = baoJiaConfig;
	}
	public Map<String, String> getIniMap() {
		return iniMap;
	}
	public void setIniMap(Map<String, String> iniMap) {
		this.iniMap = iniMap;
	}
	public Map<String, BusinessConfig> getDynamicIniMap() {
		return dynamicIniMap;
	}
	public void setDynamicIniMap(Map<String, BusinessConfig> dynamicIniMap) {
		this.dynamicIniMap = dynamicIniMap;
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
	public String getJqxBeginDate() {
		return jqxBeginDate;
	}
	public void setJqxBeginDate(String jqxBeginDate) {
		this.jqxBeginDate = jqxBeginDate;
	}
	public String getShyxBeginDate() {
		return shyxBeginDate;
	}
	public void setShyxBeginDate(String shyxBeginDate) {
		this.shyxBeginDate = shyxBeginDate;
	}
	public String getIsApplyBiz() {
		return isApplyBiz;
	}
	public void setIsApplyBiz(String isApplyBiz) {
		this.isApplyBiz = isApplyBiz;
	}
}
