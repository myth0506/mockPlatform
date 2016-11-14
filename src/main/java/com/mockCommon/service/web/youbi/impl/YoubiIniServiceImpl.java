package com.mockCommon.service.web.youbi.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.youbi.YoubiIniDao;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.youbi.YoubiIniService;

@Service
public class YoubiIniServiceImpl implements YoubiIniService{
	
	@Autowired
	private YoubiIniDao youbiIniDao;
	
	public int saveBusinessIni(BusinessIni ini){
		BusinessIni businessIni = selectIni(ini.getIniName());
		if(businessIni == null){
			return youbiIniDao.insertIni(ini);
		}else{
			return youbiIniDao.updateIni(ini);
		}
	}
	
	public BusinessIni selectIni(String iniName) {

		return youbiIniDao.selectIni(iniName);
	}

	public int batchInsert(List<BusinessIni> insertList) {

		return youbiIniDao.batchInsert(insertList);
	}

	public int batchUpdate(List<BusinessIni> updateList) {

		return youbiIniDao.batchUpdate(updateList);
	}

	public List<BusinessIni> queryAllInis() {

		return youbiIniDao.queryAllInis();
	}

	public int mergeIni(BusinessIni ini) {
		
		return youbiIniDao.mergeIni(ini);
	}

}
