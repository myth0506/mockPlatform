package com.mockCommon.model.mock.yangguang;

import java.sql.Timestamp;

import com.mockCommon.util.freeMarker.DataTemplate;

/**
 * @author li_zhe
  ownerIdNo	身份证
  ownerName	车主姓名
  vehicleId	车辆Id
  vehicleModelName	品牌型号
  vehicleFrameNo	车架号
  engineNo	发动机号
  registerDate	注册日期
  specialCarFlag	是否过户车
  specialCarDate	过户日期
  vehicleInvoiceNo	新车购置发票号
  vehicleInvoiceDate	发票开具日期
  isOcr	  是否使用OCR
  isLoanCar	  是否贷款车
  firstBenefitPeople	第一受益人
 */
@DataTemplate(template = "sunshine_searchCarInfo.xml", output = "searchCarInfo_out.xml")
public class SunshineSearchCarInfoMockModel {
	
	private String ownerIdNo;
	private String ownerName;
	private String ownerMobile;
	private String ownerEmail;
	private String applicantName;
	private String applicantIdNo;
	private String applicantMobile;
	private String applicantEmail;
	private String insuredName;
	private String insuredIdNo;
	private String insuredMobile;
	private String insuredEmail;
	private String vehicleId;
	private String vehicleModelName;
	private String vehicleFrameNo;
	private String engineNo;
	private Timestamp registerDate;
	private String specialCarFlag;
	private Timestamp specialCarDate;
	private String vehicleInvoiceNo;
	private Timestamp vehicleInvoiceDate;
	private String isOcr;
	private String isLoanCar;
	private String firstBenefitPeople;
	
	public String getOwnerMobile() {
		return ownerMobile;
	}
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getApplicantIdNo() {
		return applicantIdNo;
	}
	public void setApplicantIdNo(String applicantIdNo) {
		this.applicantIdNo = applicantIdNo;
	}
	public String getApplicantMobile() {
		return applicantMobile;
	}
	public void setApplicantMobile(String applicantMobile) {
		this.applicantMobile = applicantMobile;
	}
	public String getApplicantEmail() {
		return applicantEmail;
	}
	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getInsuredIdNo() {
		return insuredIdNo;
	}
	public void setInsuredIdNo(String insuredIdNo) {
		this.insuredIdNo = insuredIdNo;
	}
	public String getInsuredMobile() {
		return insuredMobile;
	}
	public void setInsuredMobile(String insuredMobile) {
		this.insuredMobile = insuredMobile;
	}
	public String getInsuredEmail() {
		return insuredEmail;
	}
	public void setInsuredEmail(String insuredEmail) {
		this.insuredEmail = insuredEmail;
	}
	public String getOwnerIdNo() {
		return ownerIdNo;
	}
	public void setOwnerIdNo(String ownerIdNo) {
		this.ownerIdNo = ownerIdNo;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleModelName() {
		return vehicleModelName;
	}
	public void setVehicleModelName(String vehicleModelName) {
		this.vehicleModelName = vehicleModelName;
	}
	public String getVehicleFrameNo() {
		return vehicleFrameNo;
	}
	public void setVehicleFrameNo(String vehicleFrameNo) {
		this.vehicleFrameNo = vehicleFrameNo;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public Timestamp getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}
	public String getSpecialCarFlag() {
		return specialCarFlag;
	}
	public void setSpecialCarFlag(String specialCarFlag) {
		this.specialCarFlag = specialCarFlag;
	}
	public Timestamp getSpecialCarDate() {
		return specialCarDate;
	}
	public void setSpecialCarDate(Timestamp specialCarDate) {
		this.specialCarDate = specialCarDate;
	}
	public String getVehicleInvoiceNo() {
		return vehicleInvoiceNo;
	}
	public void setVehicleInvoiceNo(String vehicleInvoiceNo) {
		this.vehicleInvoiceNo = vehicleInvoiceNo;
	}
	public Timestamp getVehicleInvoiceDate() {
		return vehicleInvoiceDate;
	}
	public void setVehicleInvoiceDate(Timestamp vehicleInvoiceDate) {
		this.vehicleInvoiceDate = vehicleInvoiceDate;
	}
	public String getIsOcr() {
		return isOcr;
	}
	public void setIsOcr(String isOcr) {
		this.isOcr = isOcr;
	}
	public String getIsLoanCar() {
		return isLoanCar;
	}
	public void setIsLoanCar(String isLoanCar) {
		this.isLoanCar = isLoanCar;
	}
	public String getFirstBenefitPeople() {
		return firstBenefitPeople;
	}
	public void setFirstBenefitPeople(String firstBenefitPeople) {
		this.firstBenefitPeople = firstBenefitPeople;
	}
}