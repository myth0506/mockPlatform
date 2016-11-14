package com.mockCommon.service.web.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.BusinessDictDao;
import com.mockCommon.model.web.BusinessDict;
import com.mockCommon.service.web.BusinessDictService;

@Service
public class BusinessDictServiceImpl implements BusinessDictService {

	@Autowired
	private BusinessDictDao businessDictDao;
	
	public BusinessDict queryBusiDict(BusinessDict dict) {
		return businessDictDao.queryBusiDict(dict);
	}

	public List<BusinessDict> queryAllBusiDict() {
		return businessDictDao.queryAllBusiDict();
	}

	@Override
	public List<BusinessDict> queryAllSunshineBusiDict() {
		return businessDictDao.queryAllSunshineBusiDict();
	}

	@Override
	public BusinessDict querySunshineBusiDict(BusinessDict dict) {
		return businessDictDao.querySunshineBusiDict(dict);
	}

	@Override
	public BusinessDict queryYouBiBusiDict(BusinessDict dict) {
		return businessDictDao.queryYouBiBusiDict(dict);
	}

	@Override
	public List<BusinessDict> queryAllYouBiBusiDict() {
		return businessDictDao.queryAllYouBiBusiDict();
	}

	@Override
	public int insertBusiDict(BusinessDict dict) {
		return businessDictDao.insertBusiDict(dict);
	}

	@Override
	public int insertSunshineBusiDict(BusinessDict dict) {
		return businessDictDao.insertSunshineBusiDict(dict);
	}

	@Override
	public int insertYouBiBusiDict(BusinessDict dict) {
		return businessDictDao.insertYouBiBusiDict(dict);
	}

}