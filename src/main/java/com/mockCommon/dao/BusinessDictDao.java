package com.mockCommon.dao;

import java.util.List;

import com.mockCommon.model.web.BusinessDict;

public interface BusinessDictDao {

	BusinessDict queryBusiDict(BusinessDict dict);
	
	BusinessDict querySunshineBusiDict(BusinessDict dict);
	
	BusinessDict queryYouBiBusiDict(BusinessDict dict);

	List<BusinessDict> queryAllBusiDict();
	
	List<BusinessDict> queryAllSunshineBusiDict();
	
	List<BusinessDict> queryAllYouBiBusiDict();
	
	int insertBusiDict(BusinessDict dict);
	
	int insertSunshineBusiDict(BusinessDict dict);
	
	int insertYouBiBusiDict(BusinessDict dict);

}