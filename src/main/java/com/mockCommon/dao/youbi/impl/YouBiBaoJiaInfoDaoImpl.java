package com.mockCommon.dao.youbi.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.youbi.YouBiBaoJiaInfoDao;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.youbi.BusinessInfoModel;

@Repository("youBiBaoJiaInfoDaoImpl")
public class YouBiBaoJiaInfoDaoImpl extends MockDBDaoImpl  implements YouBiBaoJiaInfoDao{

	@Override
	public BusinessInfoModel selectBaoJiaInfo(String business_name) {
        SqlSession sqlSession = this.getSqlSession();
        return (BusinessInfoModel) sqlSession.selectOne("YouBiBaoJia.queryBusinessInfo", business_name);
	}

	@Override
	public int insertBaoJiaInfo(BusinessInfoModel bi) {
		SqlSession sqlSession = this.getSqlSession();
		 return  sqlSession.insert("YouBiBaoJia.insertBaoJiaInfo", bi);
	}

	@Override
	public int updateBaoJiaInfo(BusinessInfoModel bi) {
		SqlSession sqlSession = this.getSqlSession();
		 return  sqlSession.update("YouBiBaoJia.updateBaoJiaInfo", bi);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessInfoModel> queryBaoJiaConfig() {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("YouBiBaoJia.queryBaoJiaConfig");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessConfig> queryAllYouBiBaoJiaConfig() {
		return this.getSqlSession().selectList("YouBiBaoJia.queryAllYouBiBaoJiaConfig");
	}

	@Override
	public int insertYouBiBaoJiaConfig(BusinessConfig config) {
		return this.getSqlSession().insert("YouBiBaoJia.insertYouBiBaoJiaConfig", config);
	}

	@Override
	public int updateYouBiBaoJiaConfig(BusinessConfig config) {
		return this.getSqlSession().update("YouBiBaoJia.updateYouBiBaoJiaConfig", config);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessConfig> queryConfigList(Map<String, String> paramMap) {
		return this.getSqlSession().selectList("YouBiBaoJia.queryConfigList", paramMap);
	}

	@Override
	public BusinessConfig queryConfigByConfigId(Map<String, String> paramMap) {
		return (BusinessConfig) this.getSqlSession().selectOne("YouBiBaoJia.queryConfigByConfigId", paramMap);
	}

	@Override
	public BusinessConfig queryConfigByCand(Map<String, String> paramMap) {
		return (BusinessConfig) this.getSqlSession().selectOne("YouBiBaoJia.queryConfigByCand", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessConfig> queryConfigListByIds(List<String> list) {
		return this.getSqlSession().selectList("YouBiBaoJia.queryConfigListByIds", list);
	}

}
