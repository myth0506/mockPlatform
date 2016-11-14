package com.mockCommon.dao.yangguang;

import java.util.Map;

import com.mockCommon.model.web.yangguang.SearchCarInfoModel;

public interface SearchCarInfoDao {

	SearchCarInfoModel searchCarInfo(Map<String, Object> paramMap);
	
	int updateSearchCarInfo(Map<String, Object> paramMap);
	
	int insertSearchCarInfo(SearchCarInfoModel searchCarInfo);
}