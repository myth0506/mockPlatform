package com.mockCommon.dao.pingan.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.pingan.BaoJiaDao;
import com.mockCommon.model.web.pingan.BaoJiaConfig;

@Repository
public class BaoJiaDaoImpl extends MockDBDaoImpl implements BaoJiaDao {

	public BaoJiaConfig queryLatestConfig() {
		SqlSession sqlSession = this.getSqlSession();
		return (BaoJiaConfig) sqlSession.selectOne("BaoJia.queryLatestConfig");
	}

	public BaoJiaConfig queryConfig(Map<String, String> map) {
		SqlSession sqlSession = this.getSqlSession();
		return (BaoJiaConfig) sqlSession.selectOne("BaoJia.queryConfig", map);
	}

	public int insertConfig(BaoJiaConfig newConfig) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("BaoJia.insertConfig", newConfig);
	}

	public int updateConfig(BaoJiaConfig newConfig) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("BaoJia.updateConfig", newConfig);
	}

	public int updateDefaultPk(Map<String, String> map) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("BaoJia.updateDefaultPk", map);
	}

	public int updateReturnPk(List<String> list) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("BaoJia.updateReturnPk", list);
	}

	public int updateStartTime(BaoJiaConfig config) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("BaoJia.updateStartTime", config);
	}

	@SuppressWarnings("unchecked")
	public List<BaoJiaConfig> queryAllConfigs() {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("BaoJia.queryAllConfigs");
	}

}
