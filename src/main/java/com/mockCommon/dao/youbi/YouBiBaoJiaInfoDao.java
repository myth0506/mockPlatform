package com.mockCommon.dao.youbi;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.youbi.BusinessInfoModel;

public interface YouBiBaoJiaInfoDao {

	BusinessInfoModel selectBaoJiaInfo(String business_name);
	
	int insertBaoJiaInfo(BusinessInfoModel bi);
	
	int updateBaoJiaInfo(BusinessInfoModel bi);
	
	List<BusinessInfoModel> queryBaoJiaConfig();
	
	List<BusinessConfig> queryAllYouBiBaoJiaConfig();
	
	int insertYouBiBaoJiaConfig(BusinessConfig config);
	
	int updateYouBiBaoJiaConfig(BusinessConfig config);

	List<BusinessConfig> queryConfigList(Map<String, String> paramMap);

	BusinessConfig queryConfigByConfigId(Map<String, String> paramMap);

	BusinessConfig queryConfigByCand(Map<String, String> paramMap);

	List<BusinessConfig> queryConfigListByIds(List<String> list);

}
