package com.mockCommon.model.mock.youbi;

import java.util.List;

import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "youbi_getLastYearTouBao_template.json",output = "youbi_getLastYearTouBao_out.json")
public class LastYearTouBaoMockModel {
	
	private String supplier_code;
	
	private String supplier_name;
	
	private List<BusinessConfig> selection;
	
	private String status;
	
	public String getSupplier_code() {
		return supplier_code;
	}
	public void setSupplier_code(String supplier_code) {
		this.supplier_code = supplier_code;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public List<BusinessConfig> getSelection() {
		return selection;
	}
	public void setSelection(List<BusinessConfig> selection) {
		this.selection = selection;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
