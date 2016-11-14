package com.mockCommon.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.BusinessDictDao;
import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.model.web.BusinessDict;

@Repository
public class BusinessDictDaoImpl extends MockDBDaoImpl implements
		BusinessDictDao {

	public BusinessDict queryBusiDict(BusinessDict dict) {
		SqlSession sqlSession = this.getSqlSession();
		return (BusinessDict) sqlSession.selectOne("BusinessDict.queryBusiDict", dict);
	}

	@SuppressWarnings("unchecked")
	public List<BusinessDict> queryAllBusiDict() {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("BusinessDict.queryAllBusiDict");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessDict> queryAllSunshineBusiDict() {
		return this.getSqlSession().selectList("BusinessDict.queryAllSunshineBusiDict");
	}

	@Override
	public BusinessDict querySunshineBusiDict(BusinessDict dict) {
		return (BusinessDict) this.getSqlSession().selectOne("BusinessDict.querySunshineBusiDict", dict);
	}

	@Override
	public BusinessDict queryYouBiBusiDict(BusinessDict dict) {
		return (BusinessDict) this.getSqlSession().selectOne("BusinessDict.queryYouBiBusiDict", dict);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessDict> queryAllYouBiBusiDict() {
		return this.getSqlSession().selectList("BusinessDict.queryAllYouBiBusiDict");
	}

	@Override
	public int insertBusiDict(BusinessDict dict) {
		return this.getSqlSession().insert("BusinessDict.insertBusiDict", dict);
	}

	@Override
	public int insertSunshineBusiDict(BusinessDict dict) {
		return this.getSqlSession().insert("BusinessDict.insertSunshineBusiDict", dict);
	}

	@Override
	public int insertYouBiBusiDict(BusinessDict dict) {
		return this.getSqlSession().insert("BusinessDict.insertYouBiBusiDict", dict);
	}

}