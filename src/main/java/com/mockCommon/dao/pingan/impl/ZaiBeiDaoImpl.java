package com.mockCommon.dao.pingan.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mockCommon.dao.MockDBDaoImpl;
import com.mockCommon.dao.pingan.ZaiBeiDao;
import com.mockCommon.model.web.BusinessIni;

@Repository
public class ZaiBeiDaoImpl extends MockDBDaoImpl implements ZaiBeiDao {

	@SuppressWarnings("unchecked")
	public List<BusinessIni> queryIni(String iniName) {
		return this.getSqlSession().selectList("BusiConfig.queryIni", iniName);
	}

	@Override
	public int insertIniZaiBei(Map<String, Object> map) {
		
		return this.getSqlSession().insert("BusiConfig.insertIni", map);
			
	}

	public int updateIniZaiBei(Map<String, Object> map) {
		
		return this.getSqlSession().update("BusiConfig.updateIni", map);
	}

	public int updateAllIniZaiBei(Map<String, Object> map) {
		
		return this.getSqlSession().update("BusiConfig.updateIniLike", map);
	}
}
