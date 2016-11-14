package com.mockCommon.service.web.pingan.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.dao.pingan.BusiConfigDao;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.pingan.BusiConfigService;
@Service
public class BusiConfigServiceImpl implements BusiConfigService {

	@Autowired
	private BusiConfigDao busiConfigDao;
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	public BusinessConfig insertConfig(BusinessConfig config) {
		String busiConfigId = commonDaoImpl.querySeqId("BC");
		config.setId(busiConfigId);
		int retCode = busiConfigDao.insertConfig(config);
		if(retCode > 0){
			return config;
		}
		return null;
	}
	
	public BusinessIni selectIni(BusinessIni ini) {

		return busiConfigDao.selectIni(ini);
	}

	public int batchInsert(List<BusinessIni> insertList) {

		return busiConfigDao.batchInsert(insertList);
	}

	public int batchUpdate(List<BusinessIni> updateList) {

		return busiConfigDao.batchUpdate(updateList);
	}

	public int updateConfig(BusinessConfig config) {

		return busiConfigDao.updateConfig(config);
	}

	public List<BusinessConfig> queryConfigList(Map<String, String> paramMap) {

		return busiConfigDao.queryConfigList(paramMap);
	}

	public List<BusinessIni> queryAllInis() {

		return busiConfigDao.queryAllInis();
	}

	public int mergeIni(BusinessIni ini) {
		
		return busiConfigDao.mergeIni(ini);
	}
}
