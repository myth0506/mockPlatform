package com.mockCommon.service.web.yangguang.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.yangguang.SunshineIniDao;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.yangguang.SunshineIniService;

@Service("sunshineIniServiceImpl")
public class SunshineIniServiceImpl implements SunshineIniService {

	@Autowired
	private SunshineIniDao sunshineIniDao;
	
	public BusinessIni selectIni(BusinessIni ini) {

		return sunshineIniDao.selectIni(ini);
	}

	public int batchInsert(List<BusinessIni> insertList) {

		return sunshineIniDao.batchInsert(insertList);
	}

	public int batchUpdate(List<BusinessIni> updateList) {

		return sunshineIniDao.batchUpdate(updateList);
	}

	public List<BusinessIni> queryAllInis() {

		return sunshineIniDao.queryAllInis();
	}

	public int mergeIni(BusinessIni ini) {
		
		return sunshineIniDao.mergeIni(ini);
	}
}