package com.mockCommon.service.web.youbi.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.youbi.ZaiBeiYouBiDao;
import com.mockCommon.dao.youbi.impl.ZaiBeiYouBiDaoImpl;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.youbi.ZaiBeiYouBiService;


@Service
public class ZaiBeiYouBiServiceImpl implements ZaiBeiYouBiService {

    @Autowired
    private ZaiBeiYouBiDao zaiBeiYouBiDao;

	public Map<String, String> queryZaiBeiYouBi() {
		Map<String, String> map = new HashMap<String, String>();
		//城市配置相关设置
		List<BusinessIni> iniModels = zaiBeiYouBiDao.queryIni(SessionKey.CCZB_YB);
		if(iniModels.size() == 0){
			map.put(SessionKey.CCZB_YB, "success");
		}else{
			map.put(SessionKey.CCZB_YB, iniModels.get(0).getIniValue());
		}
		
		iniModels = zaiBeiYouBiDao.queryIni(SessionKey.CCZB_YB_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.CCZB_YB_DT, "");
		}else{
			map.put(SessionKey.CCZB_YB_DT, iniModels.get(0).getIniValue());
		}
		
		//查询车型相关配置
//		iniModels = zaiBeiYouBiDao.queryIni(SessionKey.CXCX_YB);
//		if (iniModels.size() == 0) {
//			map.put(SessionKey.CXCX_YB, "success");
//		} else {
//			map.put(SessionKey.CXCX_YB, iniModels.get(0).getIniValue());
//		}
//
//		iniModels = zaiBeiYouBiDao.queryIni(SessionKey.CXCX_YB_DT);
//		if (iniModels.size() == 0) {
//			map.put(SessionKey.CXCX_YB_DT, "");
//		} else {
//			map.put(SessionKey.CXCX_YB_DT, iniModels.get(0).getIniValue());
//		}
//		
//		//输入车型相关配置
//		iniModels = zaiBeiYouBiDao.queryIni(SessionKey.SRCX_YB);
//		if (iniModels.size() == 0) {
//			map.put(SessionKey.SRCX_YB, "success");
//		} else {
//			map.put(SessionKey.SRCX_YB, iniModels.get(0).getIniValue());
//		}
//
//		iniModels = zaiBeiYouBiDao.queryIni(SessionKey.SRCX_YB_DT);
//		if (iniModels.size() == 0) {
//			map.put(SessionKey.SRCX_YB_DT, "");
//		} else {
//			map.put(SessionKey.SRCX_YB_DT, iniModels.get(0).getIniValue());
//		}
//		
//		//创建报价相关配置
//		iniModels = zaiBeiYouBiDao.queryIni(SessionKey.CJBJ_YB);
//		if (iniModels.size() == 0) {
//			map.put(SessionKey.CJBJ_YB, "success");
//		} else {
//			map.put(SessionKey.CJBJ_YB, iniModels.get(0).getIniValue());
//		}
//
//		iniModels = zaiBeiYouBiDao.queryIni(SessionKey.CJBJ_YB_DT);
//		if (iniModels.size() == 0) {
//			map.put(SessionKey.CJBJ_YB_DT, "");
//		} else {
//			map.put(SessionKey.CJBJ_YB_DT, iniModels.get(0).getIniValue());
//		}
//		
//		//获取报价相关配置
//		iniModels = zaiBeiYouBiDao.queryIni(SessionKey.HQBJ_YB);
//		if (iniModels.size() == 0) {
//			map.put(SessionKey.HQBJ_YB, "success");
//		} else {
//			map.put(SessionKey.HQBJ_YB, iniModels.get(0).getIniValue());
//		}
//
//		iniModels = zaiBeiYouBiDao.queryIni(SessionKey.HQBJ_YB_DT);
//		if (iniModels.size() == 0) {
//			map.put(SessionKey.HQBJ_YB_DT, "");
//		} else {
//			map.put(SessionKey.HQBJ_YB_DT, iniModels.get(0).getIniValue());
//		}
				
		return map;
	}

	@Override
	public int setIniValue(Map<String, Object> map) {
		//城市配置相关设置
		List<BusinessIni> iniModels = zaiBeiYouBiDao.queryIni(map.get("iniName").toString());
		if(iniModels.size() == 0){
			return zaiBeiYouBiDao.insertIniZaiBei(map);
		}else{
			return zaiBeiYouBiDao.updateIniZaiBei(map);
		}
	}

	@Override
	public int setBackUp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setAllIni(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}



}
