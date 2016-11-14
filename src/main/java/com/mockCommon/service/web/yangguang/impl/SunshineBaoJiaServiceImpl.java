package com.mockCommon.service.web.yangguang.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.dao.yangguang.SunshineBaoJiaDao;
import com.mockCommon.model.web.yangguang.SunshineBaoJiaModel;
import com.mockCommon.service.web.yangguang.SunshineBaoJiaService;

@Service("sunshineBaoJiaServiceImpl")
public class SunshineBaoJiaServiceImpl implements SunshineBaoJiaService {

	@Autowired
	private CommonDaoImpl commonDao;
	
	@Autowired
	private SunshineBaoJiaDao SunshineBaoJiaDaoImpl;
	
	@Override
	public List<SunshineBaoJiaModel> queryAllConfigs() {
		return SunshineBaoJiaDaoImpl.queryAllConfigs();
	}

	@Override
	public SunshineBaoJiaModel queryConfig(Map<String, String> map) {
		return SunshineBaoJiaDaoImpl.queryConfig(map);
	}

	@Override
	public int insertConfig(SunshineBaoJiaModel sunshineBaojia) {
		String id = commonDao.querySeqId("SBJ");
		sunshineBaojia.setId(id);
		return SunshineBaoJiaDaoImpl.insertConfig(sunshineBaojia);
	}

	@Override
	public int updateConfig(SunshineBaoJiaModel sunshineBaojia) {
		return SunshineBaoJiaDaoImpl.updateConfig(sunshineBaojia);
	}

	@Override
	public SunshineBaoJiaModel queryLatestConfig() {
		return SunshineBaoJiaDaoImpl.queryLatestConfig();
	}

	@Override
	public int updateDefaultPk(Map<String, String> map) {
		return SunshineBaoJiaDaoImpl.updateDefaultPk(map);
	}

	@Override
	public int updateReturnPk(List<String> list) {
		return SunshineBaoJiaDaoImpl.updateReturnPk(list);
	}

	@Override
	public int updateStartTime(SunshineBaoJiaModel sunshineBaoJiaModel) {
		return SunshineBaoJiaDaoImpl.updateStartTime(sunshineBaoJiaModel);
	}
}