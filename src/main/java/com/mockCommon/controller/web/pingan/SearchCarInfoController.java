package com.mockCommon.controller.web.pingan;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.CommonConstant;
import com.mockCommon.constant.PersonType;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.PersonInfoModel;
import com.mockCommon.model.web.pingan.VehicleInfoModel;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.service.web.pingan.SearchCarInfoService;
import com.mockCommon.util.DateUtil;
import com.mockCommon.util.NullOrEmpty;

@Controller("pinganSearchCarInfoController")
public class SearchCarInfoController {

	@Autowired
	private SearchCarInfoService searchCarInfoService;
	@Autowired
	private BusiConfigService busiConfigService;

	@Resource(name = "commonDaoImpl")
	private CommonDaoImpl commonDaoImpl;

	@RequestMapping("/InsrSearchCarInfo/iniNpsInfo")
	@ResponseBody
	public Map<String, Object> iniNpsInfo(@RequestParam("isNpsFlow")String isNpsFlow){
		Map<String, Object> retMap = new HashMap<String, Object>();
		int retCode = 0;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.IS_NPS_FLOW);
		ini.setIniValue(isNpsFlow);
		ini.setIniDesc("是否是费改区域");
		retCode = busiConfigService.mergeIni(ini);
		if(retCode>0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}
	@RequestMapping("/InsrSearchCarInfo/queryNpsInfo")
	@ResponseBody
	public Map<String, Object> queryNpsInfo(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.IS_NPS_FLOW);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", ini.getIniValue());
		}else{
			retMap.put("retCode", "-1");
		}
		return retMap;
	}
	@RequestMapping("/InsrSearchCarInfo/searchCarInfo")
	@ResponseBody
	public Map<String, Object> searchCarInfo(
			@RequestParam("vehicleNo") String vehicleNo) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		int status = 0;
		if (vehicleNo != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("vehicleNo", vehicleNo);

			paramMap.put("personType", PersonType.REGISTER);
			PersonInfoModel registerPerson = searchCarInfoService
					.searchPersonInfo(paramMap);
			if (registerPerson != null) {
				retMap.put("registerPerson", registerPerson);
				status = 1;
			}

			paramMap.put("personType", PersonType.INSURED);
			PersonInfoModel insuredPerson = searchCarInfoService
					.searchPersonInfo(paramMap);
			if (registerPerson != null) {
				retMap.put("insuredPerson", insuredPerson);
				status = 1;
			}

			paramMap.put("personType", PersonType.APPLICANT);
			PersonInfoModel applicantPerson = searchCarInfoService
					.searchPersonInfo(paramMap);
			if (applicantPerson != null) {
				retMap.put("applicantPerson", applicantPerson);
				status = 1;
			}

			VehicleInfoModel vehicleInfo = searchCarInfoService
					.searchVehicleInfo(paramMap);
			if (vehicleInfo != null) {
				retMap.put("vehicleInfo", vehicleInfo);
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

	@RequestMapping("/InsrSearchCarInfo/submitPersonInfo")
	@ResponseBody
	public Map<String, Object> submitPersonInfo(
			@RequestParam("vehicleNo") String vehicleNo,
			@RequestParam("personName") String personName,
			@RequestParam("personGender") String personGender,
			@RequestParam("personMobile") String personMobile,
			@RequestParam("personIdType") String personIdType,
			@RequestParam("personIdNo") String personIdNo,
			@RequestParam("personBirthday") String personBirthday,
			@RequestParam("personEmail") String personEmail,
			@RequestParam("personAddress") String personAddress,
			@RequestParam("personType") String personType) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		int status = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("vehicleNo", vehicleNo);

		paramMap.put("personType", personType);
		PersonInfoModel personInfo = searchCarInfoService
				.searchPersonInfo(paramMap);
		if (personInfo != null) {
			// 数据库中已经存在，做更新操作
			paramMap.put("personIdType", personIdType);
			paramMap.put("personIdNo", personIdNo);
			paramMap.put("personMobile", personMobile);
			paramMap.put("personName", personName);
			paramMap.put("personGender", personGender);
			paramMap.put("personAddress", personAddress);
			paramMap.put("personEmail", personEmail);

			Timestamp birth = DateUtil.getTimestamp(personBirthday);
			if (birth != null) {
				paramMap.put("personBirthday", birth);
			}

			status = searchCarInfoService.updatePersonInfo(paramMap);
		} else {
			// 数据库中不存在，做插入操作
			PersonInfoModel personInfoModel = new PersonInfoModel();
			String personId = commonDaoImpl
					.querySeqId(CommonConstant.SEARCH_CAR_INFO_PERSON_PREFIX);
			personInfoModel.setPersonId(personId);
			personInfoModel.setLicenseNo(vehicleNo);
			personInfoModel.setPersonIdType(Integer.parseInt(personIdType));
			personInfoModel.setPersonIdNo(personIdNo);
			personInfoModel.setPersonMobile(personMobile);
			personInfoModel.setPersonName(personName);
			personInfoModel.setPersonGender(personGender);
			personInfoModel.setPersonType(personType);
			personInfoModel.setPersonAddress(personAddress);
			personInfoModel.setPersonEmail(personEmail);

			Timestamp birth = DateUtil.getTimestamp(personBirthday);
			if (birth != null) {
				personInfoModel.setPersonBirthday(birth);
			}

			status = searchCarInfoService.insertPersonInfo(personInfoModel);
		}

		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
			//CacheUtil.put(SessionKey.SEARCH_CAR_INFO_VEHICLE_NO, vehicleNo);
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.SEARCH_CAR_INFO_VEHICLE_NO);
			ini.setIniValue(vehicleNo);
			ini.setIniDesc("设置车牌号");
			busiConfigService.mergeIni(ini);
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}

	@RequestMapping("/InsrSearchCarInfo/submitVehicleInfo")
	@ResponseBody
	public Map<String, Object> submitVehicleInfo(
			@RequestParam("vehicleNo") String vehicleNo,
			@RequestParam("vehicleFrameNo") String vehicleFrameNo,
			@RequestParam("vehicleEngineNo") String vehicleEngineNo,
			@RequestParam("vehicleRegisterDate") String vehicleRegisterDate,
			@RequestParam("vehicleModel") String vehicleModel,
			@RequestParam("vehicleModelName") String vehicleModelName,
			@RequestParam("vehicleVehicleId") String vehicleVehicleId,
			@RequestParam("vehicleSpecialCarFlag") String vehicleSpecialCarFlag,
			@RequestParam(value = "vehicleSpecialCarDate", required = false) String vehicleSpecialCarDate) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		int status = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("vehicleNo", vehicleNo);

		VehicleInfoModel vehicleInfo = searchCarInfoService
				.searchVehicleInfo(paramMap);
		if (vehicleInfo != null) {
			paramMap.put("vehicleFrameNo", vehicleFrameNo);
			paramMap.put("vehicleEngineNo", vehicleEngineNo);
			Timestamp registerDate = DateUtil.getTimestamp(vehicleRegisterDate);
			if (registerDate != null)
				paramMap.put("vehicleRegisterDate", registerDate);
			paramMap.put("vehicleModel", vehicleModel);
			paramMap.put("vehicleModelName", vehicleModelName);
			paramMap.put("vehicleVehicleId", vehicleVehicleId);
			paramMap.put("vehicleSpecialCarFlag", vehicleSpecialCarFlag);
			if (vehicleSpecialCarFlag != null
					&& vehicleSpecialCarFlag.equals("1")
					&& !NullOrEmpty.isNullOrEmpty(vehicleSpecialCarDate)) {
				Timestamp specialCarDate = DateUtil
						.getTimestamp(vehicleSpecialCarDate);
				if (specialCarDate != null)
					paramMap.put("vehicleSpecialCarDate", specialCarDate);
			}
			status = searchCarInfoService.updateVehicleInfo(paramMap);
		} else {
			VehicleInfoModel vehicleInfoModel = new VehicleInfoModel();

			String vehicleId = commonDaoImpl
					.querySeqId(CommonConstant.SEARCH_CAR_INFO_VEHICLE_PREFIX);
			vehicleInfoModel.setVehicleId(vehicleId);
			vehicleInfoModel.setLicenseNo(vehicleNo);
			vehicleInfoModel.setVehicleFrameNo(vehicleFrameNo);
			vehicleInfoModel.setVehicleEngineNo(vehicleEngineNo);
			vehicleInfoModel.setVehicleModel(vehicleModel);
			vehicleInfoModel.setVehicleModelName(vehicleModelName);
			Timestamp regiterDate = DateUtil.getTimestamp(vehicleRegisterDate);
			if (regiterDate != null)
				vehicleInfoModel.setVehicleRegisterDate(regiterDate);
			vehicleInfoModel.setVehicleVehicleId(vehicleVehicleId);
			vehicleInfoModel.setVehicleSpecialCarFlag(vehicleSpecialCarFlag);
			if (vehicleSpecialCarFlag != null
					&& vehicleSpecialCarFlag.equals("1")) {
				Timestamp specialCarDate = DateUtil
						.getTimestamp(vehicleSpecialCarDate);
				if (specialCarDate != null)
					vehicleInfoModel.setVehicleSpecialCarDate(specialCarDate);
			}

			status = searchCarInfoService.insertVehicleInfo(vehicleInfoModel);
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