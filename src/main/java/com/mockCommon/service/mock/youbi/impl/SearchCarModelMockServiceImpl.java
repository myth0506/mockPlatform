package com.mockCommon.service.mock.youbi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.youbi.CityConfig;
import com.mockCommon.model.mock.youbi.SearchCarModelMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.youbi.YoubiCityConfigService;
import com.mockCommon.service.web.youbi.YoubiIniService;
import com.mockCommon.util.freeMarker.IDataMaker;

@Service("searchCarModelMockServiceImpl")
public class SearchCarModelMockServiceImpl {
	
	@Autowired
	private IDataMaker<SearchCarModelMockModel> searchCarModelFreeMarker;
	
	@Autowired
	private YoubiCityConfigService youbiCityConfigService;
	
	@Autowired
	private YoubiIniService youbiIniService;
	
	public String getResult(String license_no, String license_owner, String city_code){
		String result = null;
		SearchCarModelMockModel mockModel = new SearchCarModelMockModel();
		//设置mockModel的属性值
		CityConfig cityConfig = youbiCityConfigService.getCityConfig();
		if(cityConfig==null){
			return "未设置城市配置！";
		}
		BusinessIni ini = youbiIniService.selectIni(SessionKey.SEARCH_CAR_MODEL);
		if(ini == null || "0".equals(ini.getIniValue())){
			mockModel.setCode("11001");
			mockModel.setSearchCarModel("0");
		}else{
			mockModel.setCode("200");
			mockModel.setSearchCarModel("1");
		}
		cityConfig.setForceDays("90");
		cityConfig.setSeparateTax("0");
		
	
		
		
		
		
		
		
		mockModel.setCityConfig(cityConfig);
		result = searchCarModelFreeMarker.generateData2output(ContextConstant.PREFIX_YOUBI_SEARCH_CAR_MODEL, mockModel);
		LogConstant.runLog.info("[youbi-searchCarModel]mock result is:" + result);
		return result;
	}
}
