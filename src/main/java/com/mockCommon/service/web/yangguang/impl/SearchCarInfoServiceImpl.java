package com.mockCommon.service.web.yangguang.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.yangguang.SearchCarInfoDao;
import com.mockCommon.model.web.yangguang.SearchCarInfoModel;
import com.mockCommon.service.web.yangguang.SearchCarInfoService;

@Service("yangguangSearchCarInfoServiceImpl")
public class SearchCarInfoServiceImpl implements SearchCarInfoService {

	@Autowired
	private SearchCarInfoDao searchCarInfoDao;
	
	@Override
	public SearchCarInfoModel searchCarInfo(Map<String, Object> paramMap) {
		return searchCarInfoDao.searchCarInfo(paramMap);
	}

	@Override
	public int updateSearchCarInfo(Map<String, Object> paramMap) {
		return searchCarInfoDao.updateSearchCarInfo(paramMap);
	}

	@Override
	public int insertSearchCarInfo(SearchCarInfoModel searchCarInfo) {
		return searchCarInfoDao.insertSearchCarInfo(searchCarInfo);
	}

}