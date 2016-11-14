package com.mockCommon.dao.yangguang.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.yangguang.SunshineBusiConfigDao;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;

@Repository("sunshineBusiConfigDaoImpl")
public class SunshineBusiConfigDaoImpl extends MockDBDaoImpl implements
		SunshineBusiConfigDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessConfig> queryConfigList(Map<String, String> paramMap) {
		return this.getSqlSession().selectList(
				"SunshineBusinessConfig.queryConfigList", paramMap);
	}

	@Override
	public int insertConfig(BusinessConfig config) {
		return this.getSqlSession().insert(
				"SunshineBusinessConfig.insertConfig", config);
	}

	@Override
	public BusinessIni selectIni(BusinessIni ini) {
		return (BusinessIni) this.getSqlSession().selectOne(
				"SunshineBusinessConfig.selectIni", ini);
	}

	@Override
	public int batchInsert(List<BusinessIni> insertList) {
		return this.getSqlSession().insert(
				"SunshineBusinessConfig.batchInsert", insertList);
	}

	@Override
	public int batchUpdate(List<BusinessIni> updateList) {
		return this.getSqlSession().update(
				"SunshineBusinessConfig.batchUpdate", updateList);
	}

	@Override
	public int updateConfig(BusinessConfig config) {
		return this.getSqlSession().update(
				"SunshineBusinessConfig.updateConfig", config);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessIni> queryAllInis() {
		return this.getSqlSession().selectList(
				"SunshineBusinessConfig.queryAllInis");
	}

	@Override
	public int mergeIni(BusinessIni ini) {
		return this.getSqlSession().update("SunshineBusinessConfig.mergeIni",
				ini);
	}

	@Override
	public int insertIni(BusinessIni ini) {
		return this.getSqlSession().insert("SunshineBusinessConfig.insertIni",
				ini);
	}

	@Override
	public int updateIni(BusinessIni ini) {
		return this.getSqlSession().update("SunshineBusinessConfig.updateIni",
				ini);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessConfig> queryAllBusiConfigs() {
		return this.getSqlSession().selectList(
				"SunshineBusinessConfig.queryAllBusiConfigs");
	}
}