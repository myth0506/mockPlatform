package com.mockCommon.service.mock.pingan.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.PersonType;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.pingan.SearchCarInfoDao;
import com.mockCommon.model.mock.pingan.SaveBaoJiaMockModel;
import com.mockCommon.model.mock.pingan.SearchCarInfoMockModel;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessDict;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.BaoJiaConfig;
import com.mockCommon.model.web.pingan.PersonInfoModel;
import com.mockCommon.model.web.pingan.VehicleInfoModel;
import com.mockCommon.service.mock.pingan.SaveBaoJiaService;
import com.mockCommon.service.web.BusinessDictService;
import com.mockCommon.service.web.pingan.BaoJiaService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.CacheUtil;
import com.mockCommon.util.MaskUtil;
import com.mockCommon.util.freeMarker.IDataMaker;

@Service
public class SaveBaoJiaServiceImpl implements SaveBaoJiaService {

	@Autowired
	private SearchCarInfoDao searchCarInfoDao;
	@Autowired
	private BaoJiaService baoJiaService;
	@Autowired
	private BusinessDictService dictService;
	@Autowired
	private BusiConfigService busiConfigService;
	@Autowired
	private IDataMaker<SaveBaoJiaMockModel> saveBaoJiaFreeMarker;
	
	public String saveBaoJia(String pkgName, String isApplyForce,
			String isNeedRuleCheck, String flowId, String isApplyBiz) {

		SaveBaoJiaMockModel saveMockModel = new SaveBaoJiaMockModel();
		Map<String, BusinessConfig> busiConfigMap = new HashMap<String, BusinessConfig>();
		Map<String, String> iniMap = new HashMap<String, String>();
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
		SearchCarInfoMockModel searchCarInfoMockModel = new SearchCarInfoMockModel();
		searchCarInfoMockModel.setFlowId(flowId);
		if(isXbyh == 1){    //是续保用户
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
					searchCarInfoMockModel.setResultCode("C0000");
				} else {
					searchCarInfoMockModel.setResultCode("C0001");
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
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageName", pkgName);
		BaoJiaConfig baoJiaConfig = baoJiaService.queryConfig(map);
		List<BusinessIni> iniList = busiConfigService.queryAllInis();
		for(int i=0; i<iniList.size(); i++){
			BusinessIni ini = iniList.get(i);
			String iniName = ini.getIniName();
			String iniValue = ini.getIniValue();
			iniMap.put(iniName, iniValue);
		}
		List<BusinessDict> dictList = dictService.queryAllBusiDict();
		for(int i=0; i<dictList.size(); i++){
			BusinessDict dict = dictList.get(i);
			String dictCode = dict.getBusinessCode();
			String dictType = dict.getType();
			if("1".equals(dictType)){
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("businessCode", dictCode);
				if("amount02".equals(dictCode)){
					paramMap.put("baoE", baoJiaConfig.getAmount02());
				}else if("amount04".equals(dictCode)){
					paramMap.put("baoE", baoJiaConfig.getAmount04());
				}else if("amount05".equals(dictCode)){
					paramMap.put("baoE", baoJiaConfig.getAmount05());
				}else if("amount17".equals(dictCode)){
					paramMap.put("baoE", baoJiaConfig.getAmount17());
				}
				List<BusinessConfig> dictConfigList = busiConfigService.queryConfigList(paramMap);
				if(dictConfigList != null && dictConfigList.size() > 0){
					BusinessConfig bc = dictConfigList.get(0);
					busiConfigMap.put(dictCode, bc);
				}
			}
		}
		saveMockModel.setSearchCarInfoMockModel(searchCarInfoMockModel);
		saveMockModel.setFlowId(flowId);
		saveMockModel.setBaoJiaConfig(baoJiaConfig);
		saveMockModel.setIniMap(iniMap);
		saveMockModel.setDynamicIniMap(busiConfigMap);
		saveMockModel.setResultCode("C0000");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp tsForce = baoJiaConfig.getJqInsrStartTime();
		long tsLong = tsForce.getTime();
		String jqxBeginDate = sdf.format(new Date(tsLong));
		Timestamp tsShyx = baoJiaConfig.getBusiInsrStartTime();
		long shyxLong = tsShyx.getTime();
		String shyxBeginDate = sdf.format(new Date(shyxLong));
		saveMockModel.setJqxBeginDate(jqxBeginDate);
		saveMockModel.setShyxBeginDate(shyxBeginDate);
		String isJqx = (String) CacheUtil.get(SessionKey.JQXBJ_IS_JQX);
		if("1".equals(isJqx)){
			saveMockModel.setIsApplyForce("true");
		}else{
			saveMockModel.setIsApplyForce("false");
		}
		saveMockModel.setForceFee((String)CacheUtil.get(SessionKey.JQXBJ_JQXJG));
		saveMockModel.setTaxFee((String)CacheUtil.get(SessionKey.JQXBJ_CCSJG));
		saveMockModel.setIsApplyForce(isApplyForce);
		saveMockModel.setIsApplyBiz(isApplyBiz);
		CacheUtil.put(SessionKey.IS_APPLY_BIZ, isApplyBiz);
		CacheUtil.put(SessionKey.IS_APPLY_FORCE, isApplyForce);
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.SBJIZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.SBJIZB_PA_DT);
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
		}else if(zbValue != null && zbValue.equals("failure")){
			saveMockModel.setResultCode("S0001");
		}else if(zbValue != null && zbValue.equals("session")){
			saveMockModel.setResultCode("S0003");
		}
		
		result = saveBaoJiaFreeMarker.generateData2output(ContextConstant.PREFIX_SAVE_BAOJIA, saveMockModel);
		LogConstant.runLog.info("[saveBaoJia]mock result is:" + result);
		return result;
	}

}
