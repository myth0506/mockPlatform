package com.mockCommon.dao;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.Wzcx;
import com.mockCommon.model.web.WzcxInfoModel;
import com.mockCommon.model.web.WzcxRecordModel;

public interface WzcxDao {

	List<Wzcx> selectWzcxStatus(Map<?, ?> map);

	int updateOtherStatus(Map<String, Object> paramMap);

	int updateStatus(Map<String, Object> paramMap);

	Wzcx searchWzcxById(Map<String, Object> paramMap);
	
	WzcxInfoModel searchWzcxInfoModel(Map<String, Object> paramMap);
	
	WzcxRecordModel searchWzcxRecordByRecordId(Map<String, Object> paramMap);
	
	List<WzcxRecordModel> searchWzcxRecordModel(Map<String, Object> paramMap);
	
	int updateWzcxRecord(Map<String, Object> paramMap);
	
	int deleteRecord(Map<String, Object> paramMap);
	
	int insertWzcxRecord(WzcxRecordModel wzcxRecordModel);
	
	int insertWzcxInfo(WzcxInfoModel wzcxInfoModel);
	
	int updateWzcxInfo(Map<String, Object> paramMap);
}