package com.mockCommon.controller.web.pingan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.pingan.SendPolicyAddressMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.AddressModel;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.service.web.pingan.SendPolicyAddressService;

@Controller
public class SendPolicyAddressController {

	@Autowired
	private SendPolicyAddressService sendPolicyAddressService;
	@Autowired
	private BusiConfigService busiConfigService;

	@RequestMapping("/InsrSendPolicyAddress/getProvinceInfo")
	@ResponseBody
	public Map<String, Object> getProvinceInfo() {
		Map<String, Object> retMap = new HashMap<String, Object>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<AddressModel> provinceNameList = sendPolicyAddressService
				.selectProvinceModel(paramMap);
		if (provinceNameList != null && provinceNameList.size() > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
			retMap.put("provinceNameList", provinceNameList);
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}

	@RequestMapping("/InsrSendPolicyAddress/getCityInfo")
	@ResponseBody
	public Map<String, Object> getCityInfo(
			@RequestParam("parent_code") String parent_code) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parent_code", parent_code);
		List<AddressModel> cityNameList = sendPolicyAddressService
				.selectCityModel(paramMap);
		if (cityNameList != null && cityNameList.size() > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
			retMap.put("cityNameList", cityNameList);
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}
	
	@RequestMapping("/InsrSendPolicyAddress/getTownInfo")
	@ResponseBody
	public Map<String, Object> getTownInfo(
			@RequestParam("parent_code") String parent_code) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parent_code", parent_code);
		List<AddressModel> townNameList = sendPolicyAddressService
				.selectTownModel(paramMap);
		if (townNameList != null && townNameList.size() > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
			retMap.put("townNameList", townNameList);
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}
	@RequestMapping("/InsrSendPolicyAddress/iniPolicyAddress")
	@ResponseBody
	public Map<String, Object> iniPolicyAddress(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.ADDRESS_NAME);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("name", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.ADDRESS_MOBILE);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("mobile", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.ADDRESS_PROV_NAME);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("province", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.ADDRESS_CITY_NAME);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("city", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.ADDRESS_TOWN_NAME);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("town", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.ADDRESS_DETAIL);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("detail", ini.getIniValue());
		}
		retMap.put("retCode", "200");
		return retMap;
	}
	@RequestMapping("/InsrSendPolicyAddress/sendPolicyAddress")
	@ResponseBody
	public Map<String, Object> sendPolicyAddress(
			@RequestParam("addressName") String addressName,
			@RequestParam("addressMobile") String addressMobile,
			@RequestParam("addressProvinceName") String addressProvinceName,
			@RequestParam("addressCityName") String addressCityName,
			@RequestParam("addressTownCityName") String addressTownCityName,
			@RequestParam("addressDetail") String addressDetail) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		SendPolicyAddressMockModel sendPolicyAddressModel = new SendPolicyAddressMockModel();
		sendPolicyAddressModel.setName(addressName);     //不用判断是否为空，如果为空，到时FreeMarker不做替换
		sendPolicyAddressModel.setMobile(addressMobile);
		sendPolicyAddressModel.setProvinceName(addressProvinceName);
		sendPolicyAddressModel.setCityName(addressCityName);
		sendPolicyAddressModel.setTownCityCode(addressTownCityName);
		sendPolicyAddressModel.setDetail(addressDetail);
		
		int status = sendPolicyAddressService.sendPolicyAddress(sendPolicyAddressModel);
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}
}