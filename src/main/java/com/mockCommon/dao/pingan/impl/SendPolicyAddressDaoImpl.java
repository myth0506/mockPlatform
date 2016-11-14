package com.mockCommon.dao.pingan.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mockCommon.dao.InsrDBDaoImpl;
import com.mockCommon.dao.pingan.SendPolicyAddressDao;
import com.mockCommon.model.web.pingan.AddressModel;

@Repository
public class SendPolicyAddressDaoImpl extends InsrDBDaoImpl implements SendPolicyAddressDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<AddressModel> selectProvinceModel(Map<String, Object> paramMap) {
		return this.getSqlSession().selectList("sendPolicyAddress.selectProvinceModel", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AddressModel> selectCityModel(Map<String, Object> paramMap) {
		return this.getSqlSession().selectList("sendPolicyAddress.selectCityModel", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AddressModel> selectTownModel(Map<String, Object> paramMap) {
		return this.getSqlSession().selectList("sendPolicyAddress.selectTownModel", paramMap);
	}

}