package com.mockCommon.dao.youbi.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.youbi.YoubiIniDao;
import com.mockCommon.model.web.BusinessIni;

@Repository("youbiIniDaoImpl")
public class YoubiIniDaoImpl extends MockDBDaoImpl implements YoubiIniDao{

	@Override
	public BusinessIni selectIni(String iniName) {
		SqlSession sqlSession = this.getSqlSession();
		return (BusinessIni) sqlSession.selectOne("Youbi.queryIni", iniName);
	}

	@Override
	public int batchInsert(List<BusinessIni> insertList) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("Youbi.batchInsertIni", insertList);
	}

	@Override
	public int batchUpdate(List<BusinessIni> updateList) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("Youbi.batchUpdateIni", updateList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BusinessIni> queryAllInis() {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("Youbi.queryAllIni");
	}

	@Override
	public int insertIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("Youbi.insertIni", ini);
	}

	@Override
	public int updateIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("Youbi.updateIni", ini);
	}

	@Override
	public int mergeIni(BusinessIni ini) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("Youbi.mergeIni", ini);
	}
}
