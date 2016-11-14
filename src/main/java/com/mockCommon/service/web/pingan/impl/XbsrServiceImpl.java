package com.mockCommon.service.web.pingan.impl;

import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.service.web.pingan.XbsrService;
import com.mockCommon.util.CacheUtil;

@Service
public class XbsrServiceImpl implements XbsrService {

	@Override
	public int renewalCheck(int isXbyh) {
		CacheUtil.put(SessionKey.XBSR_IS_XBYH, isXbyh);
		return 1;
	}

	@Override
	public int renewalConfirm(String birthday) {
		CacheUtil.put(SessionKey.XBSR_BIRTHDAY, birthday);
		return 1;
	}

}