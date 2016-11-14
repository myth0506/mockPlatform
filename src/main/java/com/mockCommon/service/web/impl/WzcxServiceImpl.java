package com.mockCommon.service.web.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mockCommon.dao.WzcxDao;
import com.mockCommon.model.web.Wzcx;
import com.mockCommon.model.web.WzcxInfoModel;
import com.mockCommon.model.web.WzcxRecordModel;
import com.mockCommon.service.web.WzcxService;

@Service
public class WzcxServiceImpl implements WzcxService {
	
	@Autowired
	private WzcxDao wzcxDao;
	
	@SuppressWarnings("rawtypes")
	public List<Wzcx> selectWzcxStatus(Map map) {
		return wzcxDao.selectWzcxStatus(map);
	}
	
	@Transactional(rollbackFor={Exception.class})
	public int updateWzStatus(Map<String, Object> paramMap) {
		int retCode = wzcxDao.updateOtherStatus(paramMap);
		if(retCode > 0){
			retCode = wzcxDao.updateStatus(paramMap);
		}
		return retCode;
	}
	
	@Override
	public WzcxInfoModel searchWzcxInfoModel(Map<String, Object> paramMap) {
		return wzcxDao.searchWzcxInfoModel(paramMap);
	}
	
	@Override
	public List<WzcxRecordModel> searchWzcxRecordModel(
			Map<String, Object> paramMap) {
		return wzcxDao.searchWzcxRecordModel(paramMap);
	}

	@Override
	public WzcxRecordModel searchWzcxRecordByRecordId(
			Map<String, Object> paramMap) {
		return wzcxDao.searchWzcxRecordByRecordId(paramMap);
	}

	@Override
	public int updateWzcxRecord(Map<String, Object> paramMap) {
		return wzcxDao.updateWzcxRecord(paramMap);
	}

	@Override
	public int deleteRecord(Map<String, Object> paramMap) {
		return wzcxDao.deleteRecord(paramMap);
	}

	@Override
	public int insertWzcxRecord(WzcxRecordModel wzcxRecordModel) {
		return wzcxDao.insertWzcxRecord(wzcxRecordModel);
	}

	@Override
	public int updateWzcxInfo(Map<String, Object> paramMap) {
		return wzcxDao.updateWzcxInfo(paramMap);
	}

	@Override
	public int insertWzcxInfo(WzcxInfoModel wzcxInfoModel) {
		return wzcxDao.insertWzcxInfo(wzcxInfoModel);
	}

}