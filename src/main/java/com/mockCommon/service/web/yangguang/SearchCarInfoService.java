package com.mockCommon.service.web.yangguang;

import java.util.Map;

import com.mockCommon.model.web.yangguang.SearchCarInfoModel;

public interface SearchCarInfoService {

	SearchCarInfoModel searchCarInfo(Map<String, Object> paramMap);
	
	int updateSearchCarInfo(Map<String, Object> paramMap);
	
	int insertSearchCarInfo(SearchCarInfoModel searchCarInfo);
	
}