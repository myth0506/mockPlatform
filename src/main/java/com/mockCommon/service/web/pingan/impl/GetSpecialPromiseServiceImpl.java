package com.mockCommon.service.web.pingan.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.pingan.BusiConfigDao;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.pingan.GetSpecialPromiseService;
import com.mockCommon.util.NullOrEmpty;

@Service
public class GetSpecialPromiseServiceImpl implements GetSpecialPromiseService {

	@Autowired
	private BusiConfigDao busiConfigDao;
	@Override
	public int getSpecialPromise(String bizSpecialPromise,
			String jqxSpecialPromise) {
		int status = 0;
		if (!NullOrEmpty.isNullOrEmpty(bizSpecialPromise)
				&& !NullOrEmpty.isNullOrEmpty(jqxSpecialPromise)) {
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.GET_SPECIAL_PROMISE_BIZ);
			ini = busiConfigDao.selectIni(ini);
			if(ini == null){
				ini = new BusinessIni();
				ini.setIniName(SessionKey.GET_SPECIAL_PROMISE_BIZ);
				ini.setIniValue(bizSpecialPromise);
				ini.setIniDesc("商业险特别约定");
				status = busiConfigDao.insertIni(ini);
			}else{
				ini.setIniValue(bizSpecialPromise);
				ini.setIniDesc("商业险特别约定");
				status = busiConfigDao.updateIni(ini);
			}
			if(status > 0){
				ini.setIniName(SessionKey.GET_SPECIAL_PROMISE_JQX);
				ini = busiConfigDao.selectIni(ini);
				if(ini == null){
					ini = new BusinessIni();
					ini.setIniName(SessionKey.GET_SPECIAL_PROMISE_JQX);
					ini.setIniValue(jqxSpecialPromise);
					ini.setIniDesc("交强险特别约定");
					status = busiConfigDao.insertIni(ini);
				}else{
					ini.setIniValue(jqxSpecialPromise);
					ini.setIniDesc("交强险特别约定");
					status = busiConfigDao.updateIni(ini);
				}
			}
		}
		return status;
	}

}