package com.mockCommon.model.web;

public class WzcxInfoModel {
	private String wzcx_id;
	private String wzcx_car_num;
	private int wzcx_status;
	private int wzcx_sfwz;

	public String getWzcx_id() {
		return wzcx_id;
	}

	public void setWzcx_id(String wzcx_id) {
		this.wzcx_id = wzcx_id;
	}

	public String getWzcx_car_num() {
		return wzcx_car_num;
	}

	public void setWzcx_car_num(String wzcx_car_num) {
		this.wzcx_car_num = wzcx_car_num;
	}

	public int getWzcx_status() {
		return wzcx_status;
	}

	public void setWzcx_status(int wzcx_status) {
		this.wzcx_status = wzcx_status;
	}

	public int getWzcx_sfwz() {
		return wzcx_sfwz;
	}

	public void setWzcx_sfwz(int wzcx_sfwz) {
		this.wzcx_sfwz = wzcx_sfwz;
	}

	@Override
	public String toString() {
		return "WzcxInfoModel [wzcx_id=" + wzcx_id + ", wzcx_car_num="
				+ wzcx_car_num + ", wzcx_status=" + wzcx_status
				+ ", wzcx_sfwz=" + wzcx_sfwz + "]";
	}
}