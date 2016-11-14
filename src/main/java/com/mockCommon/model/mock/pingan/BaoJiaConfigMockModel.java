package com.mockCommon.model.mock.pingan;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.BaoJiaConfig;
import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "baojia_template.json", output = "baojia_out.json")
public class BaoJiaConfigMockModel {
	private String vehicleId;
	private String frameNo;
	private String engineNo;
	private String registerDate;
	private String modelName;
	private String name;
	private String flowId;
	private String resultCode;
	
	//默认套餐名称
	private String selectedPkgName;
	//商业险保险起期
	private String syxBeginDate;
	//交强险保险起期
	private String jqxBeginDate;
	//返回的险种
	private BaoJiaConfig baojiaConfig;
	//返回设置的投保金额
	private BusinessIni businessIni;
	//返回显示的套餐类别
	private List<BaoJiaConfig> configList;
	//返回配置可选择项的险种
	private Map<String, List<BusinessConfig>> optionInsureMap;
	//车损险的最低限
	private String lowAmount01;
	//车损险的最高限
	private String heighAmount01;
	//车损险默认保额
	private String defaultAmount01;
	//车损险自定义保额
	private String zidingyiAmount01;
	
	public String getSelectedPkgName() {
		return selectedPkgName;
	}
	public void setSelectedPkgName(String selectedPkgName) {
		this.selectedPkgName = selectedPkgName;
	}
	public String getSyxBeginDate() {
		return syxBeginDate;
	}
	public void setSyxBeginDate(String syxBeginDate) {
		this.syxBeginDate = syxBeginDate;
	}
	public String getJqxBeginDate() {
		return jqxBeginDate;
	}
	public void setJqxBeginDate(String jqxBeginDate) {
		this.jqxBeginDate = jqxBeginDate;
	}
	public BaoJiaConfig getBaojiaConfig() {
		return baojiaConfig;
	}
	public void setBaojiaConfig(BaoJiaConfig baojiaConfig) {
		this.baojiaConfig = baojiaConfig;
	}
	public List<BaoJiaConfig> getConfigList() {
		return configList;
	}
	public void setConfigList(List<BaoJiaConfig> configList) {
		this.configList = configList;
	}
	public BusinessIni getBusinessIni() {
		return businessIni;
	}
	public void setBusinessIni(BusinessIni businessIni) {
		this.businessIni = businessIni;
	}
	public Map<String, List<BusinessConfig>> getOptionInsureMap() {
		return optionInsureMap;
	}
	public void setOptionInsureMap(Map<String, List<BusinessConfig>> optionInsureMap) {
		this.optionInsureMap = optionInsureMap;
	}
	public String getLowAmount01() {
		return lowAmount01;
	}
	public void setLowAmount01(String lowAmount01) {
		this.lowAmount01 = lowAmount01;
	}
	public String getHeighAmount01() {
		return heighAmount01;
	}
	public void setHeighAmount01(String heighAmount01) {
		this.heighAmount01 = heighAmount01;
	}
	public String getDefaultAmount01() {
		return defaultAmount01;
	}
	public void setDefaultAmount01(String defaultAmount01) {
		this.defaultAmount01 = defaultAmount01;
	}
	
	public String getZidingyiAmount01() {
		return zidingyiAmount01;
	}
	
	public void setZidingyiAmount01(String zidingyiAmount01) {
		this.zidingyiAmount01 = zidingyiAmount01;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getFrameNo() {
		return frameNo;
	}
	public void setFrameNo(String frameNo) {
		this.frameNo = frameNo;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	
}
