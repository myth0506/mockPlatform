package com.mockCommon.dao.youbi.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.youbi.YoubiBusiConfigDao;
import com.mockCommon.model.web.youbi.BusinessDictModel;

@Repository
public class YoubiBusiConfigDaoImpl extends MockDBDaoImpl implements YoubiBusiConfigDao{

	@Override
	public int insertConfig(BusinessDictModel bc) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.insert("YoubiBusinessConfigModel.insert", bc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessDictModel> queryConfigList(Map<String, String> bc) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("YoubiBusinessConfigModel.queryConfigList", bc);
	}

	@Override
	public int updateConfig(BusinessDictModel bc) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("YoubiBusinessConfigModel.updateConfig", bc);
	}

	@Override
	public int modifyConfig(BusinessDictModel bc) {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.update("YoubiBusinessConfigModel.modifyConfig", bc);
	}


}
