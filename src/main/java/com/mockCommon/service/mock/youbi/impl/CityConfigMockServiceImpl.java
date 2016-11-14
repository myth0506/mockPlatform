package com.mockCommon.service.mock.youbi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.youbi.CityConfigMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.youbi.CityConfigMockService;
import com.mockCommon.service.web.youbi.YoubiIniService;
import com.mockCommon.util.freeMarker.IDataMaker;

@Service
public class CityConfigMockServiceImpl implements CityConfigMockService{
	
	@Autowired
	private IDataMaker<CityConfigMockModel> cityConfigFreeMarker;
	
	@Autowired
	private YoubiIniService youbiIniService;

	@Override
	public String getCityConfig(String cityCode) {
		
		if(cityCode != null && !"".equals(cityCode)){
			BusinessIni ini;
			ini = youbiIniService.selectIni(SessionKey.CITY_CODE);
			if(ini!=null && !ini.getIniValue().equals(cityCode)){
				return "城市代码不匹配";
			}
		}
		
		String result = null;
		CityConfigMockModel cityConfig = new CityConfigMockModel();
		cityConfig.setLocal_car("111111");
		cityConfig.setOut_car("111111");
		BusinessIni ini;
		ini = youbiIniService.selectIni(SessionKey.PROVINCE_CODE);
		if(ini != null){
			cityConfig.setProvince_code(ini.getIniValue());
		}
		ini = youbiIniService.selectIni(SessionKey.LICENSE_PREFIX);
		if(ini != null){
			cityConfig.setLicense_prefix(ini.getIniValue());
		}
		ini = youbiIniService.selectIni(SessionKey.LOCAL_CAR);
		if(ini != null){
			cityConfig.setLocal_car(ini.getIniValue());
		}
		ini = youbiIniService.selectIni(SessionKey.OUT_CAR);
		if(ini != null){
			cityConfig.setOut_car(ini.getIniValue());
		}
		ini = youbiIniService.selectIni(SessionKey.NEED_PIC);
		if(ini != null){
			cityConfig.setNeed_pic(ini.getIniValue());
		}
		ini = youbiIniService.selectIni(SessionKey.FORCE_FLAG);
		if(ini != null){
			cityConfig.setForce_flag(Integer.parseInt(ini.getIniValue()));
		}
		ini = youbiIniService.selectIni(SessionKey.BIZ_DAYS);
		if(ini != null){
			cityConfig.setBiz_days(Integer.parseInt(ini.getIniValue()));
		}
		ini = youbiIniService.selectIni(SessionKey.SUPPORT_DRIVING_AREA);
		if(ini != null){
			cityConfig.setSupport_driving_area(Integer.parseInt(ini.getIniValue()));
		}
		ini = youbiIniService.selectIni(SessionKey.SUPPORT_ASSIGN_DRIVER);
		if(ini != null){
			cityConfig.setSupport_assign_driver(Integer.parseInt(ini.getIniValue()));
		}
		
		result = cityConfigFreeMarker.generateData2output(ContextConstant.PREFIX_CITYCONFIG, cityConfig);
		LogConstant.runLog.info("[baoxian-cityConfig]mock result is:" + result);
		
		return result;

	}

}
