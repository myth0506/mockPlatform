package com.mockCommon.service.web.yangguang;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ZaiBeiYangGuangService {

	Map<String, String> queryzaiBeiYangGuang();

	int setIniValue(Map<String, Object> map);

	int setBackUp();

	int setAllIni(HttpServletRequest request);

}
