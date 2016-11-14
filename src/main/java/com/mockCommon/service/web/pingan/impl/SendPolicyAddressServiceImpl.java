package com.mockCommon.service.web.pingan.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.pingan.SendPolicyAddressDao;
import com.mockCommon.model.mock.pingan.SendPolicyAddressMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.AddressModel;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.service.web.pingan.SendPolicyAddressService;

@Service
public class SendPolicyAddressServiceImpl implements SendPolicyAddressService {

	@Autowired
	private SendPolicyAddressDao sendPolicyAddress;
	@Autowired
	private BusiConfigService busiConfigService;
	@Override
	public List<AddressModel> selectProvinceModel(Map<String, Object> paramMap) {
		return sendPolicyAddress.selectProvinceModel(paramMap);
	}
	@Override
	public List<AddressModel> selectCityModel(Map<String, Object> paramMap) {
		return sendPolicyAddress.selectCityModel(paramMap);
	}
	@Override
	public List<AddressModel> selectTownModel(Map<String, Object> paramMap) {
		return sendPolicyAddress.selectTownModel(paramMap);
	}
	@Override
	public int sendPolicyAddress(
			SendPolicyAddressMockModel sendPolicyAddressMockModel) {
		int status = 0;
		if(sendPolicyAddressMockModel != null){
			/*CacheUtil.put(SessionKey.SEND_POLICY_ADDRESS, sendPolicyAddressMockModel);
			status = 1;*/
			String name = sendPolicyAddressMockModel.getName();
			String mobile = sendPolicyAddressMockModel.getMobile();
			String province = sendPolicyAddressMockModel.getProvinceName();
			String city = sendPolicyAddressMockModel.getCityName();
			String town = sendPolicyAddressMockModel.getTownCityCode();
			String detail = sendPolicyAddressMockModel.getDetail();
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.ADDRESS_NAME);
			ini.setIniValue(name);
			ini.setIniDesc("收件人姓名");
			status = busiConfigService.mergeIni(ini);
			if(status > 0){
				ini.setIniName(SessionKey.ADDRESS_MOBILE);
				ini.setIniValue(mobile);
				ini.setIniDesc("收件人手机号");
				status = busiConfigService.mergeIni(ini);
			}
			if(status > 0){
				ini.setIniName(SessionKey.ADDRESS_PROV_NAME);
				ini.setIniValue(province);
				ini.setIniDesc("收件人所在省份");
				status = busiConfigService.mergeIni(ini);
			}
			if(status > 0){
				ini.setIniName(SessionKey.ADDRESS_CITY_NAME);
				ini.setIniValue(city);
				ini.setIniDesc("收件人所在市");
				status = busiConfigService.mergeIni(ini);
			}
			if(status > 0){
				ini.setIniName(SessionKey.ADDRESS_TOWN_NAME);
				ini.setIniValue(town);
				ini.setIniDesc("收件人所在区");
				status = busiConfigService.mergeIni(ini);
			}
			if(status > 0){
				ini.setIniName(SessionKey.ADDRESS_DETAIL);
				ini.setIniValue(detail);
				ini.setIniDesc("收件人所在详细地址");
				status = busiConfigService.mergeIni(ini);
			}
		}
		return status;
	}

}