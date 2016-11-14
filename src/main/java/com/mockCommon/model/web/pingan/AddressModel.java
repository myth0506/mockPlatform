package com.mockCommon.model.web.pingan;

public class AddressModel {
	private String city_name;
	private String city_code;
	private String short_time;
	private int city_level;
	private String parent_code;
	private int invalid;
	
	public int getInvalid() {
		return invalid;
	}
	public void setInvalid(int invalid) {
		this.invalid = invalid;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getShort_time() {
		return short_time;
	}
	public void setShort_time(String short_time) {
		this.short_time = short_time;
	}
	public int getCity_level() {
		return city_level;
	}
	public void setCity_level(int city_level) {
		this.city_level = city_level;
	}
	public String getParent_code() {
		return parent_code;
	}
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}
	
	@Override
	public String toString() {
		return "AddressModel [city_name=" + city_name + ", city_code="
				+ city_code + ", short_time=" + short_time + ", city_level="
				+ city_level + ", parent_code=" + parent_code + ", invalid="
				+ invalid + "]";
	}
}