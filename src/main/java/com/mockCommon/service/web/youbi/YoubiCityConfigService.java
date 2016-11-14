package com.mockCommon.service.web.youbi;

import com.mockCommon.model.mock.youbi.CityConfig;

public interface YoubiCityConfigService {
	int saveCityCode( String cityCode, String isMock);

	int saveCityConfig(String province_code, String license_prefix, String local_car, String out_car, String need_pic,
			String force_flag, String biz_days, String support_driving_area, String support_assign_driver);
	
	CityConfig getCityConfig();
}
