package com.mockCommon.service.web.pingan;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.mock.pingan.SendPolicyAddressMockModel;
import com.mockCommon.model.web.pingan.AddressModel;

public interface SendPolicyAddressService {
	
	List<AddressModel> selectProvinceModel(Map<String, Object> paramMap);
	
	List<AddressModel> selectCityModel(Map<String, Object> paramMap);
	
	List<AddressModel> selectTownModel(Map<String, Object> paramMap);
	
	int sendPolicyAddress(SendPolicyAddressMockModel sendPolicyAddressMockModel);

}