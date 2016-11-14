package com.mockCommon.dao.youbi;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.youbi.BusinessConfigInfo;


public interface YoubiBusiConfigInfoDao {

	int insertConfig(BusinessConfigInfo bci);
	List<BusinessConfigInfo> queryConfigInfoList(BusinessConfigInfo bc);
	//int updateConfig(BusinessDictModel bc);
	int modifyConfigInfo(BusinessConfigInfo bci);
	int updateConfigInfo(BusinessConfigInfo bci);

}
