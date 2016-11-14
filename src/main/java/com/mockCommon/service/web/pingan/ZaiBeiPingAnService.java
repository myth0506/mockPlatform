package com.mockCommon.service.web.pingan;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ZaiBeiPingAnService {

	Map<String, String> queryZaiBeiPingAn();

	int setIniValue(Map<String, Object> map);

	int setBackUp();

	int setAllIni(HttpServletRequest request);

}
