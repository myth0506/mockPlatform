package com.mockCommon.dao.pingan.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.pingan.BusiConfigDao;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;

@Repository
public class BusiConfigDaoImpl extends MockDBDaoImpl implements BusiConfigDao {

	public int insertConfig(BusinessConfig config) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("BusiConfig.insert", config);
	}

	public BusinessIni selectIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return (BusinessIni) sqlSession.selectOne("BusiConfig.queryIni", ini);
	}

	public int batchInsert(List<BusinessIni> insertList) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("BusiConfig.batchInsertIni", insertList);
	}

	public int batchUpdate(List<BusinessIni> updateList) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("BusiConfig.batchUpdateIni", updateList);
	}

	public int updateConfig(BusinessConfig config) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("BusiConfig.updateConfig", config);
	}

	@SuppressWarnings("unchecked")
	public List<BusinessConfig> queryConfigList(Map<String, String> paramMap) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("BusiConfig.queryConfigList", paramMap);
	}

	@SuppressWarnings("unchecked")
	public List<BusinessIni> queryAllInis() {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("BusiConfig.queryAllIni");
	}

	public int insertIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("BusiConfig.insertIni", ini);
	}

	public int updateIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("BusiConfig.updateIni", ini);
	}

	public int mergeIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("BusiConfig.mergeIni", ini);
	}

}