package com.mockCommon.service.mock.pingan.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.pingan.BaoJiaConfigMockModel;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessDict;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.BaoJiaConfig;
import com.mockCommon.model.web.pingan.VehicleInfoModel;
import com.mockCommon.service.mock.pingan.BaoJiaConfigService;
import com.mockCommon.service.web.BusinessDictService;
import com.mockCommon.service.web.pingan.BaoJiaService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.service.web.pingan.SearchCarInfoService;
import com.mockCommon.util.freeMarker.IDataMaker;

@Service
public class BaoJiaConfigServiceImpl implements BaoJiaConfigService {

	@Autowired
	private IDataMaker<BaoJiaConfigMockModel> baoJiaConfigFreeMarker;
	@Autowired
	private BaoJiaService baoJiaService;
	@Autowired
	private BusinessDictService dictService;
	@Autowired
	private BusiConfigService busiConfigService;
	@Autowired
	private SearchCarInfoService searchCarInfoService;
	
	public String quoteInfo(String vehicleId, String frameNo,
			String engineNo, String registerDate, String modelName,
			String name, String flowId) {
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.BJZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.BJZB_PA_DT);
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
		
		String result = null;
		VehicleInfoModel vehicle = getVehicleInfo();
		BaoJiaConfigMockModel configModel = new BaoJiaConfigMockModel();
		if(zbValue != null && zbValue.equals("failure")){
			configModel.setResultCode("S0001");
		}else{
			configModel.setResultCode("C0000");
		}
		
		if(zbValue != null && zbValue.equals("session")){
			configModel.setResultCode("S0003");
		}
			
		configModel.setVehicleId(vehicleId);
		if(StringUtils.isEmpty(frameNo) && vehicle!=null){
			configModel.setFrameNo(vehicle.getVehicleFrameNo());
		}
		if(StringUtils.isEmpty(engineNo) && vehicle!=null){
			configModel.setEngineNo(vehicle.getVehicleEngineNo());
		}
		configModel.setRegisterDate(registerDate);
		configModel.setModelName(modelName);
		configModel.setName(name);
		configModel.setFlowId(flowId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BaoJiaConfig defaultConfig = new BaoJiaConfig();
		List<BaoJiaConfig> configList = baoJiaService.queryAllConfigs();
		List<BaoJiaConfig> returnList = new ArrayList<BaoJiaConfig>();
		Map<String, List<BusinessConfig>> optionMap = new HashMap<String, List<BusinessConfig>>();
		for(int i=0; i<configList.size(); i++){
			BaoJiaConfig config = configList.get(i);
			String pkgDefault = config.getPackageDefault();
			String pkgReturn = config.getPackageReturn();
			if("1".equals(pkgDefault)){
				configModel.setSelectedPkgName(config.getPackageTypeDesc());
				configModel.setBaojiaConfig(config);
				defaultConfig = config;
			}
			if("1".equals(pkgReturn)){
				returnList.add(config);
			}
		}
		List<BusinessDict> dictList = dictService.queryAllBusiDict();
		for(int i=0; i<dictList.size(); i++){
			BusinessDict dict = dictList.get(i);
			String dictCode = dict.getBusinessCode();
			String dictType = dict.getType();
			if("1".equals(dictType)){
				Map<String, String> map = new HashMap<String, String>();
				map.put("businessCode", dictCode);
				List<BusinessConfig> dictConfigList = busiConfigService.queryConfigList(map);
				optionMap.put(dictCode, dictConfigList);
			}
		}
		List<BusinessIni> iniList = busiConfigService.queryAllInis();
		for(int i=0; i<iniList.size(); i++){
			BusinessIni ini = iniList.get(i);
			String iniName = ini.getIniName();
			String iniValue = ini.getIniValue();
			if("lowAmount01".equals(iniName)){
				configModel.setLowAmount01(iniValue);
			}
			if("heighAmount01".equals(iniName)){
				configModel.setHeighAmount01(iniValue);
			}
			if("defaultAmount01".equals(iniName)){
				configModel.setDefaultAmount01(iniValue);
			}
			if("zidingyiAmount01".equals(iniName)){
				configModel.setZidingyiAmount01(iniValue);
			}
		}
		long shyxStartTime = defaultConfig.getBusiInsrStartTime().getTime();
		long jqxStartTime = defaultConfig.getJqInsrStartTime().getTime();
		Date shyxTime = new Date(shyxStartTime);
		Date jqxTime = new Date(jqxStartTime);
		//设置商业保险起保日期
		configModel.setSyxBeginDate(sdf.format(shyxTime));
		//设置交强险起保日期
		configModel.setJqxBeginDate(sdf.format(jqxTime));
		//显示页面勾选的套餐种类
		configModel.setConfigList(returnList);
		//显示页面配置某些险种的可选项
		configModel.setOptionInsureMap(optionMap);
		result = baoJiaConfigFreeMarker.generateData2output(ContextConstant.PREFIX_BAOJIA_CONFIG, configModel);
		LogConstant.runLog.info("[baoxian-baojia]mock result is:" + result);
		return result;
	}
	private VehicleInfoModel getVehicleInfo(){
		VehicleInfoModel vehicleInfo = null;
		BusinessIni ini = new BusinessIni();
		ini.setIniName("vehicleNo");
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("vehicleNo", ini.getIniValue());
			vehicleInfo = searchCarInfoService.searchVehicleInfo(paramMap);
		}
		return vehicleInfo;
	}
}
