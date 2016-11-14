package com.mockCommon.dao.youbi;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.youbi.BusinessDictModel;

public interface YoubiBusiConfigDao {
	int insertConfig(BusinessDictModel bc);
	List<BusinessDictModel> queryConfigList(Map<String, String> bc);
	int updateConfig(BusinessDictModel bc);
	int modifyConfig(BusinessDictModel bc);

}
