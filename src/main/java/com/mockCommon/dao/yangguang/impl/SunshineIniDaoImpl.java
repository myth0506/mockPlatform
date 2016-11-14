package com.mockCommon.dao.yangguang.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.yangguang.SunshineIniDao;
import com.mockCommon.model.web.BusinessIni;

@Repository("sunshineIniDaoImpl")
public class SunshineIniDaoImpl extends MockDBDaoImpl implements SunshineIniDao {

	@Override
	public BusinessIni selectIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return (BusinessIni) sqlSession.selectOne("Sunshine.queryIni", ini);
	}

	@Override
	public int batchInsert(List<BusinessIni> insertList) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("Sunshine.batchInsertIni", insertList);
	}

	@Override
	public int batchUpdate(List<BusinessIni> updateList) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("Sunshine.batchUpdateIni", updateList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BusinessIni> queryAllInis() {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("Sunshine.queryAllIni");
	}

	@Override
	public int insertIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("Sunshine.insertIni", ini);
	}

	@Override
	public int updateIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("Sunshine.updateIni", ini);
	}

	@Override
	public int mergeIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("Sunshine.mergeIni", ini);
	}

}