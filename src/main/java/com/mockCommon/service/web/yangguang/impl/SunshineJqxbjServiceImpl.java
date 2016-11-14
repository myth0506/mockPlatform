package com.mockCommon.service.web.yangguang.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.yangguang.SunshineBusiConfigDao;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.yangguang.SunshineJqxbjService;

@Service
public class SunshineJqxbjServiceImpl implements SunshineJqxbjService {

	@Autowired
	private SunshineBusiConfigDao sunshineBusiConfigDao;
	public int forceQuote(String isJqx, String jqxjg, String ccsjg) {
		int retCode = 0;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_JQXBJ_IS_JQX);
		ini = sunshineBusiConfigDao.selectIni(ini);
		if(ini == null){
			ini = new BusinessIni();
			ini.setIniName(SessionKey.SUNSHINE_JQXBJ_IS_JQX);
			ini.setIniValue(isJqx);
			ini.setIniDesc("交强险失败原因");
			retCode = sunshineBusiConfigDao.insertIni(ini);
		}else{
			ini.setIniValue(isJqx);
			ini.setIniDesc("交强险失败原因");
			retCode = sunshineBusiConfigDao.updateIni(ini);
		}
		if(retCode > 0){
			ini.setIniName(SessionKey.SUNSHINE_JQXBJ_JQXJG);
			ini = sunshineBusiConfigDao.selectIni(ini);
			if(ini == null){
				ini = new BusinessIni();
				ini.setIniName(SessionKey.SUNSHINE_JQXBJ_JQXJG);
				ini.setIniValue(jqxjg);
				ini.setIniDesc("交强险价格");
				retCode = sunshineBusiConfigDao.insertIni(ini);
			}else{
				ini.setIniValue(jqxjg);
				ini.setIniDesc("交强险价格");
				retCode = sunshineBusiConfigDao.updateIni(ini);
			}
		}
		if(retCode > 0){
			ini.setIniName(SessionKey.SUNSHINE_JQXBJ_CCSJG);
			ini = sunshineBusiConfigDao.selectIni(ini);
			if(ini == null){
				ini = new BusinessIni();
				ini.setIniName(SessionKey.SUNSHINE_JQXBJ_CCSJG);
				ini.setIniValue(ccsjg);
				ini.setIniDesc("车船税价格");
				retCode = sunshineBusiConfigDao.insertIni(ini);
			}else{
				ini.setIniValue(ccsjg);
				ini.setIniDesc("车船税价格");
				retCode = sunshineBusiConfigDao.updateIni(ini);
			}
		}
		return retCode;
	}

}