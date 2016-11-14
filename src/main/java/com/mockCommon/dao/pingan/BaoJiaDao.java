package com.mockCommon.dao.pingan;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.pingan.BaoJiaConfig;

public interface BaoJiaDao {

	BaoJiaConfig queryLatestConfig();

	BaoJiaConfig queryConfig(Map<String, String> map);

	int insertConfig(BaoJiaConfig newConfig);

	int updateConfig(BaoJiaConfig newConfig);

	int updateDefaultPk(Map<String, String> map);

	int updateReturnPk(List<String> list);

	int updateStartTime(BaoJiaConfig config);

	List<BaoJiaConfig> queryAllConfigs();

}
