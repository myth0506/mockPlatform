package com.mockCommon.dao.youbi.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.pingan.ZaiBeiDao;
import com.mockCommon.dao.youbi.ZaiBeiYouBiDao;
import com.mockCommon.model.web.BusinessIni;

@Repository
public class ZaiBeiYouBiDaoImpl extends MockDBDaoImpl implements ZaiBeiYouBiDao {

	@SuppressWarnings("unchecked")
	public List<BusinessIni> queryIni(String iniName) {
		return this.getSqlSession().selectList("YouBiBusiConfig.queryIni", iniName);
	}

	@Override
	public int insertIniZaiBei(Map<String, Object> map) {
		
		return this.getSqlSession().insert("YouBiBusiConfig.insertIni", map);
			
	}

	public int updateIniZaiBei(Map<String, Object> map) {
		
		return this.getSqlSession().update("YouBiBusiConfig.updateIni", map);
	}

	public int updateAllIniZaiBei(Map<String, Object> map) {
		
		return this.getSqlSession().update("YouBiBusiConfig.updateIniLike", map);
	}
}
