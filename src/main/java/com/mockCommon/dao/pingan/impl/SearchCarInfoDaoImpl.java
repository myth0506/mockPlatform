package com.mockCommon.dao.pingan.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.pingan.SearchCarInfoDao;
import com.mockCommon.model.web.pingan.PersonInfoModel;
import com.mockCommon.model.web.pingan.VehicleInfoModel;

@Repository("pinganSearchCarInfoDaoImpl")
public class SearchCarInfoDaoImpl extends MockDBDaoImpl implements SearchCarInfoDao {

	@Override
	public PersonInfoModel searchPersonInfo(Map<String, Object> paramMap) {
		return (PersonInfoModel) this.getSqlSession().selectOne("SearchCarInfo.searchPersonInfo", paramMap);
	}

	@Override
	public VehicleInfoModel searchVehicleInfo(Map<String, Object> paramMap) {
		return (VehicleInfoModel) this.getSqlSession().selectOne("SearchCarInfo.searchVehicleInfo", paramMap);
	}

	@Override
	public int updatePersonInfo(Map<String, Object> paramMap) {
		return this.getSqlSession().update("SearchCarInfo.updatePersonInfo", paramMap);
	}

	@Override
	public int insertPersonInfo(PersonInfoModel personInfoModel) {
		return this.getSqlSession().insert("SearchCarInfo.insertPersonInfo", personInfoModel);
	}

	@Override
	public int updateVehicleInfo(Map<String, Object> paramMap) {
		return this.getSqlSession().update("SearchCarInfo.updateVehicleInfo", paramMap);
	}

	@Override
	public int insertVehicleInfo(VehicleInfoModel vehicleInfoModel) {
		return this.getSqlSession().insert("SearchCarInfo.insertVehicleInfo", vehicleInfoModel);
	}

}