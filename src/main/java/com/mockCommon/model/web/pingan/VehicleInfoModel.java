package com.mockCommon.model.web.pingan;

import java.sql.Timestamp;

public class VehicleInfoModel {
	private String vehicleId;
	private String licenseNo;
	private String vehicleFrameNo;
	private String vehicleEngineNo;
	private Timestamp vehicleRegisterDate;
	private String vehicleModel;
	private String vehicleModelName;
	private String vehicleVehicleId;
	private String vehicleSpecialCarFlag;
	private Timestamp vehicleSpecialCarDate;
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getVehicleFrameNo() {
		return vehicleFrameNo;
	}
	public void setVehicleFrameNo(String vehicleFrameNo) {
		this.vehicleFrameNo = vehicleFrameNo;
	}
	public String getVehicleEngineNo() {
		return vehicleEngineNo;
	}
	public void setVehicleEngineNo(String vehicleEngineNo) {
		this.vehicleEngineNo = vehicleEngineNo;
	}
	public Timestamp getVehicleRegisterDate() {
		return vehicleRegisterDate;
	}
	public void setVehicleRegisterDate(Timestamp vehicleRegisterDate) {
		this.vehicleRegisterDate = vehicleRegisterDate;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public String getVehicleModelName() {
		return vehicleModelName;
	}
	public void setVehicleModelName(String vehicleModelName) {
		this.vehicleModelName = vehicleModelName;
	}
	public String getVehicleVehicleId() {
		return vehicleVehicleId;
	}
	public void setVehicleVehicleId(String vehicleVehicleId) {
		this.vehicleVehicleId = vehicleVehicleId;
	}
	public String getVehicleSpecialCarFlag() {
		return vehicleSpecialCarFlag;
	}
	public void setVehicleSpecialCarFlag(String vehicleSpecialCarFlag) {
		this.vehicleSpecialCarFlag = vehicleSpecialCarFlag;
	}
	public Timestamp getVehicleSpecialCarDate() {
		return vehicleSpecialCarDate;
	}
	public void setVehicleSpecialCarDate(Timestamp vehicleSpecialCarDate) {
		this.vehicleSpecialCarDate = vehicleSpecialCarDate;
	}
	@Override
	public String toString() {
		return "VehicleInfoModel [vehicleId=" + vehicleId + ", licenseNo="
				+ licenseNo + ", vehicleFrameNo=" + vehicleFrameNo
				+ ", vehicleEngineNo=" + vehicleEngineNo
				+ ", vehicleRegisterDate=" + vehicleRegisterDate
				+ ", vehicleModel=" + vehicleModel + ", vehicleModelName="
				+ vehicleModelName + ", vehicleVehicleId=" + vehicleVehicleId
				+ ", vehicleSpecialCarFlag=" + vehicleSpecialCarFlag
				+ ", vehicleSpecialCarDate=" + vehicleSpecialCarDate + "]";
	}
	
}