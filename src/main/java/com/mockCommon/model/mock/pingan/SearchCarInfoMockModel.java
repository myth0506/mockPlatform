package com.mockCommon.model.mock.pingan;

import com.mockCommon.model.web.pingan.PersonInfoModel;
import com.mockCommon.model.web.pingan.VehicleInfoModel;
import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "searchCarInfo_template.json", output = "searchCarInfo_out.json")
public class SearchCarInfoMockModel {
	/*
	 * vehicle.registerDate 车辆初登日期 vehicle.frameNo 车架号 vehicle.engineNo 发动机号
	 * vehicle.model 车型号 vehicle.modelName 品牌型号 vehicle.vehicleId 配置型号
	 * insured.name 被保人姓名 insured.idType 证件类型 insured.idNo 证件号码 insured.birthday
	 * 生日 insured.gender 性别 insured.mobile 手机号 insured.email E-mail
	 * insured.provinceCode 被保人省份 insured.cityCode 被保人城市 insured.areaCode 被保人区县
	 * insured.address 被保人地址 applicant.name 投保人姓名 applicant.idType 证件类型
	 * applicant.idNo 证件号码 applicant.birthday 生日 applicant.gender 性别
	 * applicant.mobile 手机 applicant.email E-mail applicant.provinceCode 投保人省份
	 * applicant.cityCode 投保人城市 applicant.areaCode 投保人区县 applicant.address 投保人地址
	 * register.name 车主姓名 register.idType 证件类型 register.idNo 证件号码
	 * register.birthday 生日 register.gender 性别 register.ageCode 年龄区间
	 * bizInfo.specialCarFlag 是否为过户车 bizInfo.specialCarDate 过户日期
	 * bizInfo.kilometrePerYear 年平均行驶里程 forceInfo.fuelType 燃料种类
	 * vehicle.vehicleCertiNo 新车购置发票号 vehicle.vehicleCertiDate 发票开具日期
	 * vehicle.invoiceDate 车船税购车发票日期 isUseTaxPrice 是否使用含税价开关 isUpGradePMhasData
	 * 交管平台是否有数据返回 renewalConfirm 续保确认状态
	 */
	private String flowId;
	private String resultCode;
	private String renewalConfirm;
	
	//车主信息
	private PersonInfoModel registerPersonInfo;
	
	//投保人信息
	private PersonInfoModel applicantPersonInfo;
	
	//被投保人信息
	private PersonInfoModel insuredPersonInfo;
	
	//车辆信息
	private VehicleInfoModel vehicleInfo;
	
	//是否是费改区域
	private String isNpsFlow;
	
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
	
	public String getRenewalConfirm() {
		return renewalConfirm;
	}

	public void setRenewalConfirm(String renewalConfirm) {
		this.renewalConfirm = renewalConfirm;
	}

	public PersonInfoModel getRegisterPersonInfo() {
		return registerPersonInfo;
	}

	public void setRegisterPersonInfo(PersonInfoModel registerPersonInfo) {
		this.registerPersonInfo = registerPersonInfo;
	}

	public PersonInfoModel getApplicantPersonInfo() {
		return applicantPersonInfo;
	}

	public void setApplicantPersonInfo(PersonInfoModel applicantPersonInfo) {
		this.applicantPersonInfo = applicantPersonInfo;
	}

	public PersonInfoModel getInsuredPersonInfo() {
		return insuredPersonInfo;
	}

	public void setInsuredPersonInfo(PersonInfoModel insuredPersonInfo) {
		this.insuredPersonInfo = insuredPersonInfo;
	}

	public VehicleInfoModel getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfoModel vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}
	
	@Override
	public String toString() {
		return "SearchCarInfoMockModel [flowId=" + flowId + ", resultCode="
				+ resultCode + ", renewalConfirm=" + renewalConfirm
				+ ", registerPersonInfo=" + registerPersonInfo
				+ ", applicantPersonInfo=" + applicantPersonInfo
				+ ", insuredPersonInfo=" + insuredPersonInfo + ", vehicleInfo="
				+ vehicleInfo + "]";
	}

	public String getIsNpsFlow() {
		return isNpsFlow;
	}

	public void setIsNpsFlow(String isNpsFlow) {
		this.isNpsFlow = isNpsFlow;
	}
}