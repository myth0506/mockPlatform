package com.mockCommon.service.mock.pingan.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.PersonType;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.pingan.SearchCarInfoDao;
import com.mockCommon.model.mock.pingan.SearchCarInfoMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.PersonInfoModel;
import com.mockCommon.model.web.pingan.VehicleInfoModel;
import com.mockCommon.service.mock.pingan.SearchCarInfoMockService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.MaskUtil;
import com.mockCommon.util.freeMarker.IDataMaker;

@Service
public class SearchCarInfoMockServiceImpl implements SearchCarInfoMockService {

	@Autowired
	private SearchCarInfoDao searchCarInfoDao;
	@Autowired
	private BusiConfigService busiConfigService;

	@Autowired
	private IDataMaker<SearchCarInfoMockModel> searchCarInfoFreeMarker;

	@Override
	public String toQueryInfo(String flowId, String renewalJump) {
		String result = null;
		int isXbyh = 0;
		try{
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.XBSR_IS_XBYH);
			ini = busiConfigService.selectIni(ini);
			if(ini != null){
				isXbyh = Integer.parseInt(ini.getIniValue());
			}
		}catch(NullPointerException e){
			isXbyh = 0;
		}
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.SCIZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.SCIZB_PA_DT);
			iniDelayTime = busiConfigService.selectIni(iniDelayTime);
			int delayTimeValue = 1000;
			if(iniDelayTime != null){
				delayTimeValue = Integer.parseInt(iniDelayTime.getIniValue());
			}
			try {
				Thread.sleep(delayTimeValue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		SearchCarInfoMockModel searchCarInfoMockModel = new SearchCarInfoMockModel();
		searchCarInfoMockModel.setFlowId(flowId);
		if(isXbyh == 1){    //是续保用户
			//String vehicleNo = (String) CacheUtil.get(SessionKey.SEARCH_CAR_INFO_VEHICLE_NO);
			String vehicleNo = null;
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.SEARCH_CAR_INFO_VEHICLE_NO);
			ini = busiConfigService.selectIni(ini);
			if(ini != null){
				vehicleNo = ini.getIniValue();
			}
			if (vehicleNo != null) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("vehicleNo", vehicleNo);
				paramMap.put("personType", PersonType.REGISTER);
				int registerStatus = 0;
				PersonInfoModel registerPersonInfo = searchCarInfoDao
						.searchPersonInfo(paramMap);
				if (registerPersonInfo != null) {       //设置车主信息
					registerStatus = 1;
					registerPersonInfo.setPersonIdNo(MaskUtil.mask(registerPersonInfo.getPersonIdNo()));
					searchCarInfoMockModel.setRegisterPersonInfo(registerPersonInfo);
				}else{
					PersonInfoModel registerPersonInfoModel = new PersonInfoModel();
					searchCarInfoMockModel.setRegisterPersonInfo(registerPersonInfoModel);
				}
	
				int applicantStatus = 0;
				paramMap.put("personType", PersonType.APPLICANT);
				PersonInfoModel applicantPersonInfo = searchCarInfoDao
						.searchPersonInfo(paramMap);
				if (applicantPersonInfo != null) {      //设置投保人信息
					applicantStatus = 1;
					applicantPersonInfo.setPersonIdNo(MaskUtil.mask(applicantPersonInfo.getPersonIdNo()));
					applicantPersonInfo.setPersonMobile(MaskUtil.mask(applicantPersonInfo.getPersonMobile()));
					searchCarInfoMockModel.setApplicantPersonInfo(applicantPersonInfo);
				}else{
					PersonInfoModel applicantPersonInfoModel = new PersonInfoModel();
					searchCarInfoMockModel.setApplicantPersonInfo(applicantPersonInfoModel);
				}
	
				int insuredStatus = 0;
				paramMap.put("personType", PersonType.INSURED);
				PersonInfoModel insuredPersonInfo = searchCarInfoDao
						.searchPersonInfo(paramMap);
				if (insuredPersonInfo != null) {      //设置被投保人信息
					insuredStatus = 1;
					insuredPersonInfo.setPersonMobile(MaskUtil.mask(insuredPersonInfo.getPersonMobile()));
					insuredPersonInfo.setPersonIdNo(MaskUtil.mask(insuredPersonInfo.getPersonIdNo()));
					searchCarInfoMockModel.setInsuredPersonInfo(insuredPersonInfo);
				}else{
					PersonInfoModel insuredPersonInfoModel = new PersonInfoModel();
					searchCarInfoMockModel.setInsuredPersonInfo(insuredPersonInfoModel);
				}
	
				int vehicleStatus = 0;
				VehicleInfoModel vehicleInfo = searchCarInfoDao
						.searchVehicleInfo(paramMap);
				if (vehicleInfo != null) {          //设置车辆信息
					vehicleStatus = 1;
					//设置掩码数据
					vehicleInfo.setVehicleFrameNo(MaskUtil.mask(vehicleInfo.getVehicleFrameNo()));
					vehicleInfo.setVehicleEngineNo(MaskUtil.mask(vehicleInfo.getVehicleEngineNo()));
					searchCarInfoMockModel.setVehicleInfo(vehicleInfo);
				}else{
					VehicleInfoModel vehicleInfoModel = new VehicleInfoModel();
					searchCarInfoMockModel.setVehicleInfo(vehicleInfoModel);
				}
	
				searchCarInfoMockModel.setResultCode("C0000");
				searchCarInfoMockModel.setRenewalConfirm("5");
				if (registerStatus == 1 && applicantStatus == 1
						&& insuredStatus == 1 && vehicleStatus == 1) {
					LogConstant.debugLog.info("searchCarInfo 数据查询成功.");
				} 
			} 
		}else{
			searchCarInfoMockModel.setResultCode("C0000");
			searchCarInfoMockModel.setRenewalConfirm("0");
			PersonInfoModel registerPersonInfo = new PersonInfoModel();
			PersonInfoModel applicantPersonInfo = new PersonInfoModel();
			PersonInfoModel insuredPersonInfo = new PersonInfoModel();
			searchCarInfoMockModel.setRegisterPersonInfo(registerPersonInfo);
			searchCarInfoMockModel.setApplicantPersonInfo(applicantPersonInfo);
			searchCarInfoMockModel.setInsuredPersonInfo(insuredPersonInfo);
			VehicleInfoModel vehicleInfo = new VehicleInfoModel();
			searchCarInfoMockModel.setVehicleInfo(vehicleInfo);
		}
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.IS_NPS_FLOW);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			String value = ini.getIniValue();
			if("1".equals(value)){
				searchCarInfoMockModel.setIsNpsFlow("true");
			}else{
				searchCarInfoMockModel.setIsNpsFlow("false");
			}
		}else{
			searchCarInfoMockModel.setIsNpsFlow("false");
		}
		
		if(zbValue != null && zbValue.equals("failure")){
			searchCarInfoMockModel.setResultCode("S0001");
		}else if(zbValue != null && zbValue.equals("session")){
			searchCarInfoMockModel.setResultCode("S0003");
		}
		result = searchCarInfoFreeMarker.generateData2output(
				ContextConstant.PREFIX_QUEYR_CAR_INFO, searchCarInfoMockModel);
		LogConstant.debugLog.info("Mock返回数据："+result);
		return result;
	}

}