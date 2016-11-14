package com.mockCommon.service.web.youbi.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.dao.youbi.YoubiBusiConfigInfoDao;
import com.mockCommon.model.web.youbi.BusinessConfigInfo;
import com.mockCommon.service.web.youbi.YoubiBusiConfigInfoService;

@Repository
public class YoubiBusiConfigInfoServiceImpl implements YoubiBusiConfigInfoService{
 
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	
	@Autowired
	private YoubiBusiConfigInfoDao youbiBusiConfigInfoDao;

	@Override
	public List<BusinessConfigInfo> queryConfigInfoList(BusinessConfigInfo bc) {

		return youbiBusiConfigInfoDao.queryConfigInfoList(bc);
	}


	@Override
	public BusinessConfigInfo insertConfigInfo(BusinessConfigInfo bci) {
		// TODO Auto-generated method stub
		String id = commonDaoImpl.querySeqId("BC");
		bci.setId(id);
		
		int retCode = youbiBusiConfigInfoDao.insertConfig(bci);
		if (retCode > 0){
			return bci;
		}
		return null;
	}


	@Override
	public int modifyConfigInfo(BusinessConfigInfo bci) {
		// TODO Auto-generated method stub
		return youbiBusiConfigInfoDao.modifyConfigInfo(bci);
	}


	@Override
	public int updateConfigInfo(BusinessConfigInfo bci) {
		// TODO Auto-generated method stub
		return youbiBusiConfigInfoDao.updateConfigInfo(bci);
	}
	
	

	

}
