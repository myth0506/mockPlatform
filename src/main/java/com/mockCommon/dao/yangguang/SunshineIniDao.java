package com.mockCommon.dao.yangguang;

import java.util.List;

import com.mockCommon.model.web.BusinessIni;

public interface SunshineIniDao {
	
	BusinessIni selectIni(BusinessIni ini);

	int batchInsert(List<BusinessIni> insertList);

	int batchUpdate(List<BusinessIni> updateList);

	List<BusinessIni> queryAllInis();

	int insertIni(BusinessIni ini);
	
	int updateIni(BusinessIni ini);

	int mergeIni(BusinessIni ini);
	
}