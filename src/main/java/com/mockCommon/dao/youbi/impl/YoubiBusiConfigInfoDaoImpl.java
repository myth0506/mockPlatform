package com.mockCommon.dao.youbi.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.youbi.YoubiBusiConfigInfoDao;
import com.mockCommon.model.web.youbi.BusinessConfigInfo;


@Repository
public class YoubiBusiConfigInfoDaoImpl extends MockDBDaoImpl implements YoubiBusiConfigInfoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessConfigInfo> queryConfigInfoList(BusinessConfigInfo bc) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("YoubiBusinessConfigInfo.queryConfigInfoList", bc);
	}

	@Override
	public int insertConfig(BusinessConfigInfo bci) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("YoubiBusinessConfigInfo.insertConfigInfo", bci);
	}

	@Override
	public int modifyConfigInfo(BusinessConfigInfo bci) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("YoubiBusinessConfigInfo.modifyConfigInfo", bci);
	}

	@Override
	public int updateConfigInfo(BusinessConfigInfo bci) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("YoubiBusinessConfigInfo.updateConfigInfo", bci);
	}



}
