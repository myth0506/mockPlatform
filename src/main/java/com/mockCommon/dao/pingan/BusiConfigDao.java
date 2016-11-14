package com.mockCommon.dao.pingan;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;

public interface BusiConfigDao {

	int insertConfig(BusinessConfig config);

	BusinessIni selectIni(BusinessIni ini);

	int batchInsert(List<BusinessIni> insertList);

	int batchUpdate(List<BusinessIni> updateList);

	int updateConfig(BusinessConfig config);

	List<BusinessConfig> queryConfigList(Map<String, String> paramMap);

	List<BusinessIni> queryAllInis();

	int insertIni(BusinessIni ini);
	
	int updateIni(BusinessIni ini);

	int mergeIni(BusinessIni ini);

}
