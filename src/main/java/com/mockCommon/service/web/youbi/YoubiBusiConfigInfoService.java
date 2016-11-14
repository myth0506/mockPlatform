package com.mockCommon.service.web.youbi;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.youbi.BusinessConfigInfo;
import com.mockCommon.model.web.youbi.BusinessDictModel;

public interface YoubiBusiConfigInfoService {


	List<BusinessConfigInfo> queryConfigInfoList(BusinessConfigInfo bci);
	BusinessConfigInfo insertConfigInfo(BusinessConfigInfo bci);
	int modifyConfigInfo(BusinessConfigInfo bci);
	int updateConfigInfo(BusinessConfigInfo bci);

}
