package com.mockCommon.service.web.pingan.impl;

import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.pingan.AuditInfoModel;
import com.mockCommon.service.web.pingan.ToAuditService;
import com.mockCommon.util.CacheUtil;

@Service
public class ToAuditServiceImpl implements ToAuditService {

	@Override
	public int submitAuditInfo(AuditInfoModel auditInfoModel) {
		int status = 0;
		if(auditInfoModel != null){
			CacheUtil.put(SessionKey.TO_AUDIT, auditInfoModel);
			status = 1;
		}
		return status;
	}

}