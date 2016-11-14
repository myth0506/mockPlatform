package com.mockCommon.dao.yangguang;

import java.util.List;
import java.util.Map;

import com.mockCommon.model.web.BusinessIni;

public interface ZaiBeiYangGuangDao {
	
	public List<BusinessIni> queryIni(String string);
	public int insertIniZaiBei(Map<String, Object> map);
	public int updateIniZaiBei(Map<String, Object> map);
	public int updateAllIniZaiBei(Map<String, Object> map);
}
