package com.mockCommon.service.web.pingan;

import java.util.Map;

import com.mockCommon.model.web.pingan.PersonInfoModel;
import com.mockCommon.model.web.pingan.VehicleInfoModel;

public interface SearchCarInfoService {
	
	PersonInfoModel searchPersonInfo(Map<String, Object> paramMap);
	
	VehicleInfoModel searchVehicleInfo(Map<String, Object> paramMap);
	
	int updatePersonInfo(Map<String, Object> paramMap);
	
	int insertPersonInfo(PersonInfoModel personInfoModel);
	
	int updateVehicleInfo(Map<String, Object> paramMap);
	
	int insertVehicleInfo(VehicleInfoModel vehicleInfoModel);
	
}