package com.mockCommon.dao.yangguang.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.yangguang.ZaiBeiYangGuangDao;
import com.mockCommon.model.web.BusinessIni;

@Repository
public class ZaiBeiYangGuangDaoImpl extends MockDBDaoImpl implements ZaiBeiYangGuangDao {

	@SuppressWarnings("unchecked")
	public List<BusinessIni> queryIni(String iniName) {
		return this.getSqlSession().selectList("SunshineBusinessConfig.selectIni", iniName);
	}

	@Override
	public int insertIniZaiBei(Map<String, Object> map) {
		
		return this.getSqlSession().insert("SunshineBusinessConfig.insertIni", map);
			
	}

	public int updateIniZaiBei(Map<String, Object> map) {
		
		return this.getSqlSession().update("SunshineBusinessConfig.updateIni", map);
	}

	public int updateAllIniZaiBei(Map<String, Object> map) {
		
		return this.getSqlSession().update("SunshineBusinessConfig.updateIniLike", map);
	}

	

}
