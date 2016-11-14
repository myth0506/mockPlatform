package com.mockCommon.service.mock.pingan.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.pingan.GetSpecialPromiseMockService;
import com.mockCommon.service.web.pingan.BusiConfigService;

@Service
public class GetSpecialPromiseMockServiceImpl implements
		GetSpecialPromiseMockService {

	@Autowired
	private BusiConfigService busiConfigService;
	@Override
	public Map<String, Object> getSpecialPromise(String flowId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String bizSpecialPromise = null;
		String jqxSpecialPromise = null;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.GET_SPECIAL_PROMISE_BIZ);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			bizSpecialPromise = ini.getIniValue();
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.GET_SPECIAL_PROMISE_JQX);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			jqxSpecialPromise = ini.getIniValue();
		}
		if (bizSpecialPromise != null && jqxSpecialPromise != null) {
			retMap.put("resultCode", "C0000");
			retMap.put("bizPromise", bizSpecialPromise);
			retMap.put("forcePromise", jqxSpecialPromise);
			retMap.put("taxPrintNo", "");
		} else {
			retMap.put("resultCode", "S0001");
		}
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.GSPZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.GSPZB_PA_DT);
			iniDelayTime = busiConfigService.selectIni(iniDelayTime);
			int delayTimeValue = 1000;
			if(iniDelayTime != null){
				delayTimeValue = Integer.parseInt(iniDelayTime.getIniValue());
			}
			try {
				Thread.sleep(delayTimeValue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(zbValue != null && zbValue.equals("failure")){
			retMap.put("resultCode", "S0001");
		}else if(zbValue != null && zbValue.equals("session")){
			retMap.put("resultCode", "S0003");
		}
		
		LogConstant.debugLog.info("getSpecialPromise Mock 数据： "
				+ retMap.toString());
		return retMap;
	}
}