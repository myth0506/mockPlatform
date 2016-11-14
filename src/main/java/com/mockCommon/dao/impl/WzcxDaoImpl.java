package com.mockCommon.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.WzcxDao;
import com.mockCommon.model.web.Wzcx;
import com.mockCommon.model.web.WzcxInfoModel;
import com.mockCommon.model.web.WzcxRecordModel;

@Repository
public class WzcxDaoImpl extends MockDBDaoImpl implements WzcxDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Wzcx> selectWzcxStatus(Map map) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("Wzcx.selectWzcxStatus", map);
	}

	public int updateOtherStatus(Map<String, Object> paramMap) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("Wzcx.updateOtherStatus", paramMap);
	}

	public int updateStatus(Map<String, Object> paramMap) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("Wzcx.updateStatus", paramMap);
	}

	@Override
	public WzcxInfoModel searchWzcxInfoModel(Map<String, Object> paramMap) {
		return (WzcxInfoModel)this.getSqlSession().selectOne("Wzcx.searchWzcxInfoModel", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WzcxRecordModel> searchWzcxRecordModel(
			Map<String, Object> paramMap) {
		return this.getSqlSession().selectList("Wzcx.searchWzcxRecordModel", paramMap);
	}

	@Override
	public WzcxRecordModel searchWzcxRecordByRecordId(
			Map<String, Object> paramMap) {
		return (WzcxRecordModel)this.getSqlSession().selectOne("Wzcx.searchWzcxRecordByRecordId", paramMap);
	}

	@Override
	public int updateWzcxRecord(Map<String, Object> paramMap) {
		return this.getSqlSession().update("Wzcx.updateWzcxRecord", paramMap);
	}

	@Override
	public int deleteRecord(Map<String, Object> paramMap) {
		return this.getSqlSession().update("Wzcx.deleteWzcxRecord", paramMap);
	}

	@Override
	public int insertWzcxRecord(WzcxRecordModel wzcxRecordModel) {
		return this.getSqlSession().insert("Wzcx.insertWzcxRecord", wzcxRecordModel);
	}

	@Override
	public int updateWzcxInfo(Map<String, Object> paramMap) {
		return this.getSqlSession().update("Wzcx.updateWzcxInfo", paramMap);
	}

	@Override
	public int insertWzcxInfo(WzcxInfoModel wzcxInfoModel) {
		return this.getSqlSession().insert("Wzcx.insertWzcxInfo", wzcxInfoModel);
	}

	@Override
	public Wzcx searchWzcxById(Map<String, Object> paramMap) {
		SqlSession sqlSession = this.getSqlSession();
		return (Wzcx) sqlSession.selectOne("Wzcx.searchWzcxById", paramMap);
	}

}
