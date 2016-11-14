package com.mockCommon.service.mock.pingan;

import java.util.Map;

public interface PolicyAndOrderMockService {
	
	Map<String, Object> queryPolicyStatus();
	
	Map<String, Object> acceptShopOrder(String bizOrderNo, String forceOrderNo);
} 