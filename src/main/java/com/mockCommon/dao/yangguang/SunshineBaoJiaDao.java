package com.mockCommon.dao.yangguang;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.yangguang.SunshineBaoJiaModel;

public interface SunshineBaoJiaDao {
	List<SunshineBaoJiaModel> queryAllConfigs();
	
	SunshineBaoJiaModel queryConfig(Map<String, String> map);
	
	int insertConfig(SunshineBaoJiaModel sunshineBaojia);
	
	int updateConfig(SunshineBaoJiaModel sunshineBaojia);
	
	SunshineBaoJiaModel queryLatestConfig();
	
	int updateDefaultPk(Map<String, String> map);
	
	int updateReturnPk(List<String> list);
	
	int updateStartTime(SunshineBaoJiaModel sunshineBaoJiaModel);
}