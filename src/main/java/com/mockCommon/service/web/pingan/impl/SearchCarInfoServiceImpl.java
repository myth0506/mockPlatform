package com.mockCommon.service.web.pingan.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.pingan.SearchCarInfoDao;
import com.mockCommon.model.web.pingan.PersonInfoModel;
import com.mockCommon.model.web.pingan.VehicleInfoModel;
import com.mockCommon.service.web.pingan.SearchCarInfoService;

@Service("pinganSearchCarInfoServiceImpl")
public class SearchCarInfoServiceImpl implements SearchCarInfoService {

	@Autowired
	private SearchCarInfoDao searchCarInfoDao;
	
	@Override
	public PersonInfoModel searchPersonInfo(Map<String, Object> paramMap) {
		return searchCarInfoDao.searchPersonInfo(paramMap);
	}

	@Override
	public VehicleInfoModel searchVehicleInfo(Map<String, Object> paramMap) {
		return searchCarInfoDao.searchVehicleInfo(paramMap);
	}

	@Override
	public int updatePersonInfo(Map<String, Object> paramMap) {
		return searchCarInfoDao.updatePersonInfo(paramMap);
	}

	@Override
	public int insertPersonInfo(PersonInfoModel personInfoModel) {
		return searchCarInfoDao.insertPersonInfo(personInfoModel);
	}

	@Override
	public int updateVehicleInfo(Map<String, Object> paramMap) {
		return searchCarInfoDao.updateVehicleInfo(paramMap);
	}

	@Override
	public int insertVehicleInfo(VehicleInfoModel vehicleInfoModel) {
		return searchCarInfoDao.insertVehicleInfo(vehicleInfoModel);
	}

}