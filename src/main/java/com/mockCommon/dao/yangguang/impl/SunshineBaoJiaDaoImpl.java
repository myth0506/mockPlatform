package com.mockCommon.dao.yangguang.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.yangguang.SunshineBaoJiaDao;
import com.mockCommon.model.web.yangguang.SunshineBaoJiaModel;

@Repository("sunshineBaoJiaDaoImpl")
public class SunshineBaoJiaDaoImpl extends MockDBDaoImpl implements SunshineBaoJiaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SunshineBaoJiaModel> queryAllConfigs() {
		return this.getSqlSession().selectList("Sunshine.queryAllConfigs");
	}

	@Override
	public SunshineBaoJiaModel queryConfig(Map<String, String> map) {
		return (SunshineBaoJiaModel) this.getSqlSession().selectOne("Sunshine.queryConfig", map);
	}

	@Override
	public int insertConfig(SunshineBaoJiaModel sunshineBaojia) {
		return this.getSqlSession().insert("Sunshine.insertConfig", sunshineBaojia);
	}

	@Override
	public int updateConfig(SunshineBaoJiaModel sunshineBaojia) {
		return this.getSqlSession().update("Sunshine.updateConfig", sunshineBaojia);
	}

	@Override
	public SunshineBaoJiaModel queryLatestConfig() {
		return (SunshineBaoJiaModel) this.getSqlSession().selectOne("Sunshine.queryLatestConfig");
	}

	@Override
	public int updateDefaultPk(Map<String, String> map) {
		return this.getSqlSession().update("Sunshine.updateDefaultPk", map);
	}

	@Override
	public int updateReturnPk(List<String> list) {
		return this.getSqlSession().update("Sunshine.updateReturnPk", list);
	}

	@Override
	public int updateStartTime(SunshineBaoJiaModel sunshineBaoJiaModel) {
		return this.getSqlSession().update("Sunshine.updateStartTime", sunshineBaoJiaModel);
	}
}