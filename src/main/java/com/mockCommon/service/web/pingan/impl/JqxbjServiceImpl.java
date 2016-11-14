package com.mockCommon.service.web.pingan.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.pingan.BusiConfigDao;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.pingan.JqxbjService;

@Service
public class JqxbjServiceImpl implements JqxbjService {

	@Autowired
	private BusiConfigDao busiConfigDao;
	public int forceQuote(String isJqx, String jqxjg, String ccsjg) {
		int retCode = 0;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.JQXBJ_IS_JQX);
		ini = busiConfigDao.selectIni(ini);
		if(ini == null){
			ini = new BusinessIni();
			ini.setIniName(SessionKey.JQXBJ_IS_JQX);
			ini.setIniValue(isJqx);
			ini.setIniDesc("交强险失败原因");
			retCode = busiConfigDao.insertIni(ini);
		}else{
			ini.setIniValue(isJqx);
			ini.setIniDesc("交强险失败原因");
			retCode = busiConfigDao.updateIni(ini);
		}
		if(retCode > 0){
			ini.setIniName(SessionKey.JQXBJ_JQXJG);
			ini = busiConfigDao.selectIni(ini);
			if(ini == null){
				ini = new BusinessIni();
				ini.setIniName(SessionKey.JQXBJ_JQXJG);
				ini.setIniValue(jqxjg);
				ini.setIniDesc("交强险价格");
				retCode = busiConfigDao.insertIni(ini);
			}else{
				ini.setIniValue(jqxjg);
				ini.setIniDesc("交强险价格");
				retCode = busiConfigDao.updateIni(ini);
			}
		}
		if(retCode > 0){
			ini.setIniName(SessionKey.JQXBJ_CCSJG);
			ini = busiConfigDao.selectIni(ini);
			if(ini == null){
				ini = new BusinessIni();
				ini.setIniName(SessionKey.JQXBJ_CCSJG);
				ini.setIniValue(ccsjg);
				ini.setIniDesc("车船税价格");
				retCode = busiConfigDao.insertIni(ini);
			}else{
				ini.setIniValue(ccsjg);
				ini.setIniDesc("车船税价格");
				retCode = busiConfigDao.updateIni(ini);
			}
		}
		return retCode;
	}


}