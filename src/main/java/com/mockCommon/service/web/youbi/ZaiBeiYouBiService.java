package com.mockCommon.service.web.youbi;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ZaiBeiYouBiService {

	Map<String, String> queryZaiBeiYouBi();

	int setIniValue(Map<String, Object> map);

	int setBackUp();

	int setAllIni(HttpServletRequest request);

}
