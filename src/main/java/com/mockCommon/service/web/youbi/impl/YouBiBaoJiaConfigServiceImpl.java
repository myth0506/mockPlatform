package com.mockCommon.service.web.youbi.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.youbi.BusinessInfoModel;
import com.mockCommon.service.web.youbi.YouBiBaoJiaConfigService;
import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.dao.youbi.YouBiBaoJiaInfoDao;
import com.mockCommon.dao.youbi.YoubiIniDao;

@Service
public class YouBiBaoJiaConfigServiceImpl implements YouBiBaoJiaConfigService{

    @Autowired
    private YoubiIniDao youbiIniDao;
    
    @Autowired
    private CommonDaoImpl commonDao;
    
    @Autowired
    private YouBiBaoJiaInfoDao youBiBaoJiaInfoDao;
    
	@Override
	public List<BusinessInfoModel> queryBaoJiaConfig() {
		return youBiBaoJiaInfoDao.queryBaoJiaConfig();
	}

	@Override
	public List<BusinessConfig> queryAllYouBiBaoJiaConfig() {
		return youBiBaoJiaInfoDao.queryAllYouBiBaoJiaConfig();
	}

	@Override
	public int insertYouBiBaoJiaConfig(BusinessConfig config) {
		String id = commonDao.querySeqId("YBJ");
		config.setId(id);
		return youBiBaoJiaInfoDao.insertYouBiBaoJiaConfig(config);
	}

	@Override
	public int updateYouBiBaoJiaConfig(BusinessConfig config) {
		return youBiBaoJiaInfoDao.updateYouBiBaoJiaConfig(config);
	}

	@Override
	public List<BusinessConfig> queryConfigList(Map<String, String> paramMap) {
		return youBiBaoJiaInfoDao.queryConfigList(paramMap);
	}

	@Override
	public BusinessConfig queryConfigByConfigId(Map<String, String> paramMap) {
		return youBiBaoJiaInfoDao.queryConfigByConfigId(paramMap);
	}

	@Override
	public BusinessConfig queryConfigByCand(Map<String, String> paramMap) {
		return youBiBaoJiaInfoDao.queryConfigByCand(paramMap);
	}

	@Override
	public List<BusinessConfig> queryConfigListByIds(List<String> list) {
		return youBiBaoJiaInfoDao.queryConfigListByIds(list);
	}

}
