package com.mockCommon.model.web;

import java.sql.Timestamp;

public class WzcxRecordModel {
	private String record_id;
	private String wzcx_id;
	private Timestamp record_wzsj;
	private String record_wzdd;
	private String record_wzyy;
	private int record_kf;
	private double record_fk;
	private int record_wzzt;
	private int record_status;
	
	public int getRecord_status() {
		return record_status;
	}
	public void setRecord_status(int record_status) {
		this.record_status = record_status;
	}
	public void setRecord_fk(double record_fk) {
		this.record_fk = record_fk;
	}
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getWzcx_id() {
		return wzcx_id;
	}
	public void setWzcx_id(String wzcx_id) {
		this.wzcx_id = wzcx_id;
	}
	public Timestamp getRecord_wzsj() {
		return record_wzsj;
	}
	public void setRecord_wzsj(Timestamp record_wzsj) {
		this.record_wzsj = record_wzsj;
	}
	public String getRecord_wzdd() {
		return record_wzdd;
	}
	public void setRecord_wzdd(String record_wzdd) {
		this.record_wzdd = record_wzdd;
	}
	public String getRecord_wzyy() {
		return record_wzyy;
	}
	public void setRecord_wzyy(String record_wzyy) {
		this.record_wzyy = record_wzyy;
	}
	public int getRecord_kf() {
		return record_kf;
	}
	public void setRecord_kf(int record_kf) {
		this.record_kf = record_kf;
	}
	public double getRecord_fk() {
		return record_fk;
	}
	public void setRecord_fk(int record_fk) {
		this.record_fk = record_fk;
	}
	public int getRecord_wzzt() {
		return record_wzzt;
	}
	public void setRecord_wzzt(int record_wzzt) {
		this.record_wzzt = record_wzzt;
	}
	
	@Override
	public String toString() {
		return "WzcxRecordModel [record_id=" + record_id + ", wzcx_id="
				+ wzcx_id + ", record_wzsj=" + record_wzsj + ", record_wzdd="
				+ record_wzdd + ", record_wzyy=" + record_wzyy + ", record_kf="
				+ record_kf + ", record_fk=" + record_fk + ", record_wzzt="
				+ record_wzzt + ", record_status=" + record_status + "]";
	}
}