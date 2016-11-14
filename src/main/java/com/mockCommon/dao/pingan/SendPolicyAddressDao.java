package com.mockCommon.dao.pingan;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.pingan.AddressModel;

public interface SendPolicyAddressDao {
	
	List<AddressModel> selectProvinceModel(Map<String, Object> paramMap);
	
	List<AddressModel> selectCityModel(Map<String, Object> paramMap);
	
	List<AddressModel> selectTownModel(Map<String, Object> paramMap);
	
}