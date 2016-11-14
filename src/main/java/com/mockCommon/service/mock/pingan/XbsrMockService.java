package com.mockCommon.service.mock.pingan;

import java.util.Map;

public interface XbsrMockService {
	
	Map<String, Object> renewalCheck();
	
	Map<String, Object> renewalConfirm(String flowId, String idNo);
}