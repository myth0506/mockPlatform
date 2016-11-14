package com.mockCommon.service.web.yangguang.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.dao.yangguang.SunshineBusiConfigDao;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;

@Service("sunshineBusiConfigServiceImpl")
public class SunshineBusiConfigServiceImpl implements SunshineBusiConfigService {

	@Autowired
	private SunshineBusiConfigDao sunshineBusiConfigDaoImpl;
	
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	
	@Override
	public List<BusinessConfig> queryConfigList(Map<String, String> paramMap) {
		return sunshineBusiConfigDaoImpl.queryConfigList(paramMap);
	}

	@Override
	public BusinessConfig insertConfig(BusinessConfig config) {
		String busiConfigId = commonDaoImpl.querySeqId("SBC");
		config.setId(busiConfigId);
		int retCode = sunshineBusiConfigDaoImpl.insertConfig(config);
		if(retCode > 0){
			return config;
		}
		return null;
	}

	@Override
	public BusinessIni selectIni(BusinessIni ini) {
		return sunshineBusiConfigDaoImpl.selectIni(ini);
	}

	@Override
	public int batchInsert(List<BusinessIni> insertList) {
		return sunshineBusiConfigDaoImpl.batchInsert(insertList);
	}

	@Override
	public int batchUpdate(List<BusinessIni> updateList) {
		return sunshineBusiConfigDaoImpl.batchUpdate(updateList);
	}

	@Override
	public int updateConfig(BusinessConfig config) {
		return sunshineBusiConfigDaoImpl.updateConfig(config);
	}

	@Override
	public List<BusinessIni> queryAllInis() {
		return sunshineBusiConfigDaoImpl.queryAllInis();
	}

	@Override
	public int mergeIni(BusinessIni ini) {
		return sunshineBusiConfigDaoImpl.mergeIni(ini);
	}

	@Override
	public List<BusinessConfig> queryAllBusiConfigs() {
		return sunshineBusiConfigDaoImpl.queryAllBusiConfigs();
	}

}