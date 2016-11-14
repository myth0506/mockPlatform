package com.mockCommon.model.mock.youbi;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "youbi_cityConfig.json", output = "")
public class CityConfigMockModel {
	
	private String province_code;
	private String license_prefix;
	private String local_car;
	private String out_car;
	private String need_pic;
	private int force_flag;
	private int biz_days;
	private int support_driving_area;
	private int support_assign_driver;
	
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getLicense_prefix() {
		return license_prefix;
	}
	public void setLicense_prefix(String license_prefix) {
		this.license_prefix = license_prefix;
	}
	public String getLocal_car() {
		return local_car;
	}
	public void setLocal_car(String local_car) {
		this.local_car = local_car;
	}
	public String getOut_car() {
		return out_car;
	}
	public void setOut_car(String out_car) {
		this.out_car = out_car;
	}
	public String getNeed_pic() {
		return need_pic;
	}
	public void setNeed_pic(String need_pic) {
		this.need_pic = need_pic;
	}
	public int getForce_flag() {
		return force_flag;
	}
	public void setForce_flag(int force_flag) {
		this.force_flag = force_flag;
	}
	public int getBiz_days() {
		return biz_days;
	}
	public void setBiz_days(int biz_days) {
		this.biz_days = biz_days;
	}
	public int getSupport_driving_area() {
		return support_driving_area;
	}
	public void setSupport_driving_area(int support_driving_area) {
		this.support_driving_area = support_driving_area;
	}
	public int getSupport_assign_driver() {
		return support_assign_driver;
	}
	public void setSupport_assign_driver(int support_assign_driver) {
		this.support_assign_driver = support_assign_driver;
	}
	

}
