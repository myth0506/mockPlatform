package com.mockCommon.model.mock.youbi;

import java.util.List;

import com.mockCommon.model.web.youbi.BusinessInfoModel;
import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "youbi_getbaojia_template.json",output = "youbi_getbaojia_out.json")
public class GetBaoJiaMockModel {

	private String fgRadio;
	private String syxstart;
	private String jqxstart;
	private String jqxbf;
	private String ccs;
	private String jqxend;
	private String jqxdqts;
	private List<BusinessInfoModel> businessInfoModel;
	private String defaultTime;
	private double total;
	private double biz_total;
	private double original;
	private double discount;
	private double biz_original;
	private String endTime;
	private String standrad;
	private String jqxsbxx;
	private String jqxsbxxma;
	private String bjkg;
	private String sbjqxsbxx;
	private String sbjqxsbxxma;
	
	public String getFgRadio() {
		return fgRadio;
	}
	public void setFgRadio(String fgRadio) {
		this.fgRadio = fgRadio;
	}
	public String getSyxstart() {
		return syxstart;
	}
	public void setSyxstart(String syxstart) {
		this.syxstart = syxstart;
	}
	public String getJqxstart() {
		return jqxstart;
	}
	public void setJqxstart(String jqxstart) {
		this.jqxstart = jqxstart;
	}
	public String getJqxbf() {
		return jqxbf;
	}
	public void setJqxbf(String jqxbf) {
		this.jqxbf = jqxbf;
	}
	public String getCcs() {
		return ccs;
	}
	public void setCcs(String ccs) {
		this.ccs = ccs;
	}
	public String getJqxend() {
		return jqxend;
	}
	public void setJqxend(String jqxend) {
		this.jqxend = jqxend;
	}
	public String getJqxdqts() {
		return jqxdqts;
	}
	public void setJqxdqts(String jqxdqts) {
		this.jqxdqts = jqxdqts;
	}
	public List<BusinessInfoModel> getBusinessInfoModel() {
		return businessInfoModel;
	}
	public void setBusinessInfoModel(List<BusinessInfoModel> businessInfoModel) {
		this.businessInfoModel = businessInfoModel;
	}
	public String getDefaultTime() {
		return defaultTime;
	}
	public void setDefaultTime(String defaultTime) {
		this.defaultTime = defaultTime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getBiz_total() {
		return biz_total;
	}
	public void setBiz_total(double biz_total) {
		this.biz_total = biz_total;
	}
	public double getOriginal() {
		return original;
	}
	public void setOriginal(double original) {
		this.original = original;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStandrad() {
		return standrad;
	}
	public void setStandrad(String standrad) {
		this.standrad = standrad;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getBiz_original() {
		return biz_original;
	}
	public void setBiz_original(double biz_original) {
		this.biz_original = biz_original;
	}
	public String getJqxsbxx() {
		return jqxsbxx;
	}
	public void setJqxsbxx(String jqxsbxx) {
		this.jqxsbxx = jqxsbxx;
	}
	public String getJqxsbxxma() {
		return jqxsbxxma;
	}
	public void setJqxsbxxma(String jqxsbxxma) {
		this.jqxsbxxma = jqxsbxxma;
	}
	public String getBjkg() {
		return bjkg;
	}
	public void setBjkg(String bjkg) {
		this.bjkg = bjkg;
	}
	public String getSbjqxsbxx() {
		return sbjqxsbxx;
	}
	public void setSbjqxsbxx(String sbjqxsbxx) {
		this.sbjqxsbxx = sbjqxsbxx;
	}
	public String getSbjqxsbxxma() {
		return sbjqxsbxxma;
	}
	public void setSbjqxsbxxma(String sbjqxsbxxma) {
		this.sbjqxsbxxma = sbjqxsbxxma;
	}
	
	
}
