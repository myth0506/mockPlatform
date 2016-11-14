package com.mockCommon.service.web.pingan.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.dao.pingan.BaoJiaDao;
import com.mockCommon.dao.pingan.BusiConfigDao;
import com.mockCommon.model.web.pingan.BaoJiaConfig;
import com.mockCommon.service.web.pingan.BaoJiaService;

@Service
public class BaoJiaServiceImpl implements BaoJiaService {

	@Autowired
	private BaoJiaDao baoJiaDao;
	
	@Autowired
	private CommonDaoImpl commonDao;
	
	@Autowired
	private BusiConfigDao busiConfigDao;

	public BaoJiaConfig queryLatestConfig() {

		return baoJiaDao.queryLatestConfig();
	}

	public BaoJiaConfig queryConfig(Map<String, String> map) {

		return baoJiaDao.queryConfig(map);
	}

	public int insertConfig(BaoJiaConfig newConfig) {
		String id = commonDao.querySeqId("BJ");
		newConfig.setId(id);
		return baoJiaDao.insertConfig(newConfig);
	}

	public int updateConfig(BaoJiaConfig newConfig) {
		
		return baoJiaDao.updateConfig(newConfig);
	}

	public int updateDefaultPk(Map<String, String> map) {

		return baoJiaDao.updateDefaultPk(map);
	}

	public int updateReturnPk(List<String> list) {

		return baoJiaDao.updateReturnPk(list);
	}

	public int updateStartTime(BaoJiaConfig config) {

		return baoJiaDao.updateStartTime(config);
	}

	public List<BaoJiaConfig> queryAllConfigs() {
		
		return baoJiaDao.queryAllConfigs();
	}
}