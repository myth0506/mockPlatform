package com.mockCommon.model.mock.yangguang;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "sunshine_xubaoBaoJia.xml", output = "xubaoBaoJia_out.xml")
public class SunshineXubaoBaoJiaMockModel {
	private String baseBaojiaPackage;
	private SunshineSearchCarInfoMockModel SunshineSearchCarInfoMockModel;
	
	public String getBaseBaojiaPackage() {
		return baseBaojiaPackage;
	}
	public void setBaseBaojiaPackage(String baseBaojiaPackage) {
		this.baseBaojiaPackage = baseBaojiaPackage;
	}
	public SunshineSearchCarInfoMockModel getSunshineSearchCarInfoMockModel() {
		return SunshineSearchCarInfoMockModel;
	}
	public void setSunshineSearchCarInfoMockModel(
			SunshineSearchCarInfoMockModel sunshineSearchCarInfoMockModel) {
		SunshineSearchCarInfoMockModel = sunshineSearchCarInfoMockModel;
	}
}