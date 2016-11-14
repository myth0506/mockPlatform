package com.mockCommon.model.web.yangguang;

import java.sql.Timestamp;

/**
 * @author li_zhe
 *  cov_200	车辆损失险
	cov_600	商业第三者责任险
	cov_500	全车盗抢险
	cov_701	司机座位责任险
	cov_702	乘客座位责任险
	cov_321	指定专修厂
	cov_310	自燃损失险
	cov_231	玻璃单独破碎险
	cov_210	车身划痕损失险
	cov_390	高速高价救援险
	cov_291	发动机特别损失险
	cov_640	交通事故精神损害赔偿责任险
	cov_921	不计免赔险（机动车盗抢险）
	cov_922	不计免赔险（车身划痕损失险）
	cov_911	不计免赔险(车损险)
	cov_912	不计免赔险(三者险)
	cov_928	不计免赔险(司机险)
	cov_929	不计免赔险(乘客险)
 */
public class SunshineBaoJiaModel {
	private String id;
	private String packageName;
	private String packageNameDesc;
	private String packageType;
	private String packageTypeDesc;
	private String packageDefault;
	private String packageReturn;
	private Timestamp busiInsrStartTime;
	private Timestamp jqInsrStartTime;
	private String cov_200;
	private String cov_600;
	private String cov_500;
	private String cov_701;
	private String cov_702;
	private String cov_321;
	private String cov_310;
	private String cov_231;
	private String cov_210;
	private String cov_390;
	private String cov_291;
	private String cov_640;
	private String cov_921;
	private String cov_922;
	private String cov_911;
	private String cov_912;
	private String cov_928;
	private String cov_929;
	// 费改后新增的险种
	private String cov_734;
	private String cov_731;
	private String cov_732;
	private String cov_733;
	
	private Timestamp updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageNameDesc() {
		return packageNameDesc;
	}
	public void setPackageNameDesc(String packageNameDesc) {
		this.packageNameDesc = packageNameDesc;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getPackageTypeDesc() {
		return packageTypeDesc;
	}
	public void setPackageTypeDesc(String packageTypeDesc) {
		this.packageTypeDesc = packageTypeDesc;
	}
	public String getPackageDefault() {
		return packageDefault;
	}
	public void setPackageDefault(String packageDefault) {
		this.packageDefault = packageDefault;
	}
	public String getPackageReturn() {
		return packageReturn;
	}
	public void setPackageReturn(String packageReturn) {
		this.packageReturn = packageReturn;
	}
	public Timestamp getBusiInsrStartTime() {
		return busiInsrStartTime;
	}
	public void setBusiInsrStartTime(Timestamp busiInsrStartTime) {
		this.busiInsrStartTime = busiInsrStartTime;
	}
	public Timestamp getJqInsrStartTime() {
		return jqInsrStartTime;
	}
	public void setJqInsrStartTime(Timestamp jqInsrStartTime) {
		this.jqInsrStartTime = jqInsrStartTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getCov_200() {
		return cov_200;
	}
	public void setCov_200(String cov_200) {
		this.cov_200 = cov_200;
	}
	public String getCov_600() {
		return cov_600;
	}
	public void setCov_600(String cov_600) {
		this.cov_600 = cov_600;
	}
	public String getCov_500() {
		return cov_500;
	}
	public void setCov_500(String cov_500) {
		this.cov_500 = cov_500;
	}
	public String getCov_701() {
		return cov_701;
	}
	public void setCov_701(String cov_701) {
		this.cov_701 = cov_701;
	}
	public String getCov_702() {
		return cov_702;
	}
	public void setCov_702(String cov_702) {
		this.cov_702 = cov_702;
	}
	public String getCov_321() {
		return cov_321;
	}
	public void setCov_321(String cov_321) {
		this.cov_321 = cov_321;
	}
	public String getCov_310() {
		return cov_310;
	}
	public void setCov_310(String cov_310) {
		this.cov_310 = cov_310;
	}
	public String getCov_231() {
		return cov_231;
	}
	public void setCov_231(String cov_231) {
		this.cov_231 = cov_231;
	}
	public String getCov_210() {
		return cov_210;
	}
	public void setCov_210(String cov_210) {
		this.cov_210 = cov_210;
	}
	public String getCov_390() {
		return cov_390;
	}
	public void setCov_390(String cov_390) {
		this.cov_390 = cov_390;
	}
	public String getCov_291() {
		return cov_291;
	}
	public void setCov_291(String cov_291) {
		this.cov_291 = cov_291;
	}
	public String getCov_640() {
		return cov_640;
	}
	public void setCov_640(String cov_640) {
		this.cov_640 = cov_640;
	}
	public String getCov_921() {
		return cov_921;
	}
	public void setCov_921(String cov_921) {
		this.cov_921 = cov_921;
	}
	public String getCov_922() {
		return cov_922;
	}
	public void setCov_922(String cov_922) {
		this.cov_922 = cov_922;
	}
	public String getCov_911() {
		return cov_911;
	}
	public void setCov_911(String cov_911) {
		this.cov_911 = cov_911;
	}
	public String getCov_912() {
		return cov_912;
	}
	public void setCov_912(String cov_912) {
		this.cov_912 = cov_912;
	}
	public String getCov_928() {
		return cov_928;
	}
	public void setCov_928(String cov_928) {
		this.cov_928 = cov_928;
	}
	public String getCov_929() {
		return cov_929;
	}
	public void setCov_929(String cov_929) {
		this.cov_929 = cov_929;
	}
	
	public String getCov_734() {
		return cov_734;
	}
	public void setCov_734(String cov_734) {
		this.cov_734 = cov_734;
	}
	public String getCov_731() {
		return cov_731;
	}
	public void setCov_731(String cov_731) {
		this.cov_731 = cov_731;
	}
	public String getCov_732() {
		return cov_732;
	}
	public void setCov_732(String cov_732) {
		this.cov_732 = cov_732;
	}
	public String getCov_733() {
		return cov_733;
	}
	public void setCov_733(String cov_733) {
		this.cov_733 = cov_733;
	}
}