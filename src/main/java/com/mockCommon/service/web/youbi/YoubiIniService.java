package com.mockCommon.service.web.youbi;

import java.util.List;

import com.mockCommon.model.web.BusinessIni;

public interface YoubiIniService {
	int saveBusinessIni(BusinessIni ini);
	
	BusinessIni selectIni(String iniName);

	int batchInsert(List<BusinessIni> insertList);

	int batchUpdate(List<BusinessIni> updateList);

	List<BusinessIni> queryAllInis();

	int mergeIni(BusinessIni ini);
}
