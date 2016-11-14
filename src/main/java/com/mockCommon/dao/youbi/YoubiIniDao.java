package com.mockCommon.dao.youbi;

import java.util.List;

import com.mockCommon.model.web.BusinessIni;

public interface YoubiIniDao {
	
	BusinessIni selectIni(String iniName);

	int batchInsert(List<BusinessIni> insertList);

	int batchUpdate(List<BusinessIni> updateList);

	List<BusinessIni> queryAllInis();

	int insertIni(BusinessIni ini);
	
	int updateIni(BusinessIni ini);

	int mergeIni(BusinessIni ini);
}
