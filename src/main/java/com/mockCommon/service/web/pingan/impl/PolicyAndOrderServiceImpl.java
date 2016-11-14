package com.mockCommon.service.web.pingan.impl;

import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.pingan.PolicyAndOrderInfoModel;
import com.mockCommon.service.web.pingan.PolicyAndOrderService;
import com.mockCommon.util.CacheUtil;

@Service
public class PolicyAndOrderServiceImpl implements PolicyAndOrderService {

	@Override
	public int submitPolicyAndOrder(
			PolicyAndOrderInfoModel policyAndOrderInfoModel) {
		int status = 0;
		if (policyAndOrderInfoModel != null) {
			CacheUtil.put(SessionKey.POLICY_AND_ORDER, policyAndOrderInfoModel);
			status = 1;
		}
		return status;
	}

}