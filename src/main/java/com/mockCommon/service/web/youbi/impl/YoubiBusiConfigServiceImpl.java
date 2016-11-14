package com.mockCommon.service.web.youbi.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.dao.youbi.YoubiBusiConfigDao;
import com.mockCommon.dao.youbi.YoubiBusiConfigInfoDao;
import com.mockCommon.model.web.youbi.BusinessConfigInfo;
import com.mockCommon.model.web.youbi.BusinessConfigModel;
import com.mockCommon.model.web.youbi.BusinessDictModel;
import com.mockCommon.service.web.youbi.YoubiBusiConfigService;

@Service
public class YoubiBusiConfigServiceImpl implements YoubiBusiConfigService{
	
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	
	@Autowired
	private YoubiBusiConfigDao youbiBusiConfigDao;
	

	@Override
	public BusinessDictModel insertConfig(BusinessDictModel bc) {
	
		String busiConfigId = commonDaoImpl.querySeqId("BC");
		bc.setId(busiConfigId);
		int retCode = youbiBusiConfigDao.insertConfig(bc);
		if (retCode > 0){
			return bc;
		}
		return null;
	}

	@Override
	public List<BusinessDictModel> queryConfigList(Map<String,String> bc) {

		return youbiBusiConfigDao.queryConfigList(bc);
	}

	@Override
	public int updateConfig(BusinessDictModel config) {

		return youbiBusiConfigDao.updateConfig(config);
	}

	@Override
	public int modifyConfig(BusinessDictModel config) {
	
		return youbiBusiConfigDao.modifyConfig(config);
	}


}
