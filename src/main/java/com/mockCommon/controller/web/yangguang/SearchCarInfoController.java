package com.mockCommon.controller.web.yangguang;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.yangguang.SearchCarInfoModel;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.service.web.yangguang.SearchCarInfoService;
import com.mockCommon.util.DateUtil;
import com.mockCommon.util.NullOrEmpty;

@Controller("yangguangSearchCarInfoController")
public class SearchCarInfoController {

	@Autowired
	private SearchCarInfoService searchCarInfoService;
	
	@Autowired
	private SunshineBusiConfigService busiConfigServiceImpl;

	@RequestMapping("/yangguang/queryNpsInfo")
	@ResponseBody 
	public Map<String, Object> queryNpsInfo() {
		Map<String, Object> retMap = new HashMap<String, Object>();

		int status = 0;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.IS_NPS_FLOW);
		ini = busiConfigServiceImpl.selectIni(ini);
		if (ini != null) {
			retMap.put("isNpsFlow", ini.getIniValue());
			status = 1;
		}
		
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}
	
	@RequestMapping("/yangguang/searchCarInfo")
	@ResponseBody 
	public Map<String, Object> searchCarInfo(
			@RequestParam("vehicleNo") String vehicleNo) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		int status = 0;
		if (vehicleNo != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("vehicleNo", vehicleNo);

			SearchCarInfoModel searchCarInfo = searchCarInfoService.searchCarInfo(paramMap);
			if (searchCarInfo != null) {
				retMap.put("searchCarInfo", searchCarInfo);
				status = 1;
			}
		}
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}

	@RequestMapping("/yangguang/submitSearchCarInfo")
	@ResponseBody
	public Map<String, Object> submitVehicleInfo(
			@RequestParam("vehicleNo") String vehicleNo,
			@RequestParam("ownerName") String ownerName,
			@RequestParam("ownerIdNo") String ownerIdNo,
			@RequestParam("ownerMobile") String ownerMobile,
			@RequestParam("ownerEmail") String ownerEmail,
			@RequestParam("applicantName") String applicantName,
			@RequestParam("applicantIdNo") String applicantIdNo,
			@RequestParam("applicantMobile") String applicantMobile,
			@RequestParam("applicantEmail") String applicantEmail,
			@RequestParam("insuredName") String insuredName,
			@RequestParam("insuredIdNo") String insuredIdNo,
			@RequestParam("insuredMobile") String insuredMobile,
			@RequestParam("insuredEmail") String insuredEmail,
			@RequestParam("vehicleFrameNo") String vehicleFrameNo,
			@RequestParam("engineNo") String engineNo,
			@RequestParam("registerDate") String registerDate,
			@RequestParam("vehicleModelName") String vehicleModelName,
			@RequestParam("vehicleId") String vehicleId,
			@RequestParam("specialCarFlag") String specialCarFlag,
			@RequestParam(value = "specialCarDate", required = false) String specialCarDate,
			@RequestParam("isNpsFlow") String isNpsFlow) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		int status = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("vehicleNo", vehicleNo);

		SearchCarInfoModel carInfo = searchCarInfoService
				.searchCarInfo(paramMap);
		if (carInfo != null) {
			paramMap.put("ownerName", ownerName);
			paramMap.put("ownerIdNo", ownerIdNo);
			paramMap.put("ownerMobile", ownerMobile);
			paramMap.put("ownerEmail", ownerEmail);
			paramMap.put("applicantName", applicantName);
			paramMap.put("applicantIdNo", applicantIdNo);
			paramMap.put("applicantMobile", applicantMobile);
			paramMap.put("applicantEmail", applicantEmail);
			paramMap.put("insuredName", insuredName);
			paramMap.put("insuredIdNo", insuredIdNo);
			paramMap.put("insuredMobile", insuredMobile);
			paramMap.put("insuredEmail", insuredEmail);
			paramMap.put("vehicleFrameNo", vehicleFrameNo);
			paramMap.put("engineNo", engineNo);
			Timestamp vehicleRegisterDate = DateUtil.getTimestamp(registerDate);
			if (vehicleRegisterDate != null)
				paramMap.put("registerDate", vehicleRegisterDate);
			paramMap.put("vehicleModelName", vehicleModelName);
			paramMap.put("vehicleId", vehicleId);
			paramMap.put("specialCarFlag", specialCarFlag);
			if (specialCarFlag != null
					&& specialCarFlag.equals("1")
					&& !NullOrEmpty.isNullOrEmpty(specialCarDate)) {
				Timestamp vehicleSpecialCarDate = DateUtil
						.getTimestamp(specialCarDate);
				if (vehicleSpecialCarDate != null)
					paramMap.put("specialCarDate", vehicleSpecialCarDate);
			}
			status = searchCarInfoService.updateSearchCarInfo(paramMap);
		} else {
			SearchCarInfoModel searchCarInfo = new SearchCarInfoModel();

			searchCarInfo.setOwnerName(ownerName);
			searchCarInfo.setOwnerIdNo(ownerIdNo);
			searchCarInfo.setOwnerMobile(ownerMobile);
			searchCarInfo.setOwnerEmail(ownerEmail);
			searchCarInfo.setApplicantName(applicantName);
			searchCarInfo.setApplicantIdNo(applicantIdNo);
			searchCarInfo.setApplicantMobile(applicantMobile);
			searchCarInfo.setApplicantEmail(applicantEmail);
			searchCarInfo.setInsuredName(insuredName);
			searchCarInfo.setInsuredIdNo(insuredIdNo);
			searchCarInfo.setInsuredMobile(insuredMobile);
			searchCarInfo.setInsuredEmail(insuredEmail);
			searchCarInfo.setVehicleId(vehicleId);
			searchCarInfo.setVehicleNo(vehicleNo);
			searchCarInfo.setVehicleFrameNo(vehicleFrameNo);
			searchCarInfo.setEngineNo(engineNo);
			searchCarInfo.setVehicleModelName(vehicleModelName);
			Timestamp vehicleRegiterDate = DateUtil.getTimestamp(registerDate);
			if (vehicleRegiterDate != null)
				searchCarInfo.setRegisterDate(vehicleRegiterDate);
			searchCarInfo.setVehicleId(vehicleId);
			searchCarInfo.setSpecialCarFlag(specialCarFlag);
			if (specialCarFlag != null
					&& specialCarFlag.equals("1")) {
				Timestamp vehicleSpecialCarDate = DateUtil
						.getTimestamp(specialCarDate);
				if (vehicleSpecialCarDate != null)
					searchCarInfo.setSpecialCarDate(vehicleSpecialCarDate);
			}

			status = searchCarInfoService.insertSearchCarInfo(searchCarInfo);
		}
		
		if(status > 0){
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.IS_NPS_FLOW);
			ini.setIniValue(isNpsFlow);
			ini.setIniDesc("阳光是否费该地区标志");
			status = busiConfigServiceImpl.mergeIni(ini);
		}
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