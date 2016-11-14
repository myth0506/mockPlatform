package com.mockCommon.model.mock.youbi;

import com.mockCommon.util.freeMarker.DataTemplate;

@DataTemplate(template = "searchcarmodel_template.json", output = "searchcarmodel_out.json")
public class SearchCarModelMockModel {
	private String code;
	private CityConfig cityConfig;
	private String searchCarModel;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CityConfig getCityConfig() {
		return cityConfig;
	}

	public void setCityConfig(CityConfig cityConfig) {
		this.cityConfig = cityConfig;
	}

	public String getSearchCarModel() {
		return searchCarModel;
	}

	public void setSearchCarModel(String searchCarModel) {
		this.searchCarModel = searchCarModel;
	}
}
