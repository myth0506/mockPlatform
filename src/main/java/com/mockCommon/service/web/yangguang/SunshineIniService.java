package com.mockCommon.service.web.yangguang;

import java.util.List;

import com.mockCommon.model.web.BusinessIni;

public interface SunshineIniService {

	BusinessIni selectIni(BusinessIni ini);

	int batchInsert(List<BusinessIni> insertList);

	int batchUpdate(List<BusinessIni> updateList);

	List<BusinessIni> queryAllInis();

	int mergeIni(BusinessIni ini);

}