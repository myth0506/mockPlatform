package com.mockCommon.model.web;

public class Wzcx {

	private String id;
	private String api_id;
	private Integer resp_status;
	private String resp_retCode;
	private String resp_retDesc;
	private String resp_content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApi_id() {
		return api_id;
	}
	public void setApi_id(String api_id) {
		this.api_id = api_id;
	}
	public Integer getResp_status() {
		return resp_status;
	}
	public void setResp_status(Integer resp_status) {
		this.resp_status = resp_status;
	}
	public String getResp_retCode() {
		return resp_retCode;
	}
	public void setResp_retCode(String resp_retCode) {
		this.resp_retCode = resp_retCode;
	}
	public String getResp_retDesc() {
		return resp_retDesc;
	}
	public void setResp_retDesc(String resp_retDesc) {
		this.resp_retDesc = resp_retDesc;
	}
	public String getResp_content() {
		return resp_content;
	}
	public void setResp_content(String resp_content) {
		this.resp_content = resp_content;
	}
	@Override
	public String toString() {
		return "Wzcx [id=" + id + ", api_id=" + api_id + ", resp_status="
				+ resp_status + ", resp_retCode=" + resp_retCode
				+ ", resp_retDesc=" + resp_retDesc + ", resp_content="
				+ resp_content + "]";
	}
}
