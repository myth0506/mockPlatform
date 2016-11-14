package com.mockCommon.model.web.pingan;

import java.sql.Timestamp;

public class PersonInfoModel {
	private String personId;
	private String licenseNo;
	private String personType;
	private String personName;
	private String personGender;
	private String personMobile;
	private int personIdType;
	private String personIdNo;
	private Timestamp personBirthday;
	private String personEmail;
	private String personAddress;
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonGender() {
		return personGender;
	}
	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}
	public String getPersonMobile() {
		return personMobile;
	}
	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}
	public int getPersonIdType() {
		return personIdType;
	}
	public void setPersonIdType(int personIdType) {
		this.personIdType = personIdType;
	}
	public String getPersonIdNo() {
		return personIdNo;
	}
	public void setPersonIdNo(String personIdNo) {
		this.personIdNo = personIdNo;
	}
	public Timestamp getPersonBirthday() {
		return personBirthday;
	}
	public void setPersonBirthday(Timestamp personBirthday) {
		this.personBirthday = personBirthday;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}
	
	@Override
	public String toString() {
		return "PersonInfoModel [personId=" + personId + ", licenseNo="
				+ licenseNo + ", personType=" + personType + ", personName="
				+ personName + ", personGender=" + personGender
				+ ", personMobile=" + personMobile + ", personIdType="
				+ personIdType + ", personIdNo=" + personIdNo
				+ ", personBirthday=" + personBirthday + ", personEmail="
				+ personEmail + ", personAddress=" + personAddress + "]";
	}
}