package com.mockCommon.dao.yangguang.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.yangguang.SearchCarInfoDao;
import com.mockCommon.model.web.yangguang.SearchCarInfoModel;

@Repository("yangguangSearchCarInfoDaoImpl")
public class SearchCarInfoDaoImpl extends MockDBDaoImpl implements SearchCarInfoDao {

	@Override
	public SearchCarInfoModel searchCarInfo(Map<String, Object> paramMap) {
		return (SearchCarInfoModel) this.getSqlSession().selectOne("Sunshine.searchCarInfo", paramMap);
	}

	@Override
	public int updateSearchCarInfo(Map<String, Object> paramMap) {
		return this.getSqlSession().update("Sunshine.updateSearchCarInfo", paramMap);
	}

	@Override
	public int insertSearchCarInfo(SearchCarInfoModel searchCarInfo) {
		return this.getSqlSession().insert("Sunshine.insertSearchCarInfo", searchCarInfo);
	}

}