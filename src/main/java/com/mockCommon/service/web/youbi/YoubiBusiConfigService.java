package com.mockCommon.service.web.youbi;

import java.util.List;
import java.util.Map;



import com.mockCommon.model.web.youbi.BusinessConfigModel;
import com.mockCommon.model.web.youbi.BusinessDictModel;


public interface YoubiBusiConfigService {

    BusinessDictModel insertConfig(BusinessDictModel config); 
    
	List<BusinessDictModel> queryConfigList(Map<String, String> bc);

	int updateConfig(BusinessDictModel config);
	
	int modifyConfig(BusinessDictModel config);
	
}
