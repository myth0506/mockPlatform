package com.mockCommon.service.mock.pingan.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.pingan.XbsrMockService;
import com.mockCommon.service.web.pingan.BusiConfigService;

@Service
public class XbsrMockServiceImpl implements XbsrMockService {

	@Autowired
	private BusiConfigService busiConfigService;
	@Override
	public Map<String, Object> renewalCheck() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		// 生成随机流水号，时间戳 + 随机数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		//产生400-500间的随机数
		String flowId = "f848a391-943b-"
				+ Math.round(Math.random() * (500 - 400) + 400) + "c-"
				+ sdf.format(new Date());
		retMap.put("flowId", flowId);
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.XBSR_IS_XBYH);
		ini = busiConfigService.selectIni(ini);
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.XBZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.XBZB_PA_DT);
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
		}
		
		if (ini != null && zbValue != null && !zbValue.equals("failure")) {
			
			int isXbyh = 0;
			try {
				isXbyh = Integer.parseInt(ini.getIniValue());
			} catch (NullPointerException e) {
				isXbyh = 0;
			}
			LogConstant.runLog.info("Mock后台------是否续保：" + isXbyh);
			LogConstant.runLog.info("Mock后台---随机生成的flowId：" + flowId);
			if(zbValue.equals("session")){
				retMap.put("resultCode", "S0003");
				retMap.put("resultMessage", "flow<" + flowId + ">不存在或已超时！");
			}else{
				if (isXbyh == 1) {
					retMap.put("resultCode", "C0001");
					retMap.put("resultMessage", "续保");
				} else {
					retMap.put("resultCode", "C0000");
					retMap.put("resultMessage", "接口调用成功，既为新保");
				}
			}
		} else {
			retMap.put("resultCode", "S0001");
			retMap.put("resultMessage", "接口调用异常，系统错误");
		}
		return retMap;
	}

	@Override
	public Map<String, Object> renewalConfirm(String flowId, String idNo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("flowId", flowId);
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.XBSR_BIRTHDAY);
		ini = busiConfigService.selectIni(ini);
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.SRZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.SRZB_PA_DT);
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
		}
		
		if (ini != null && zbValue != null && !zbValue.equals("failure")) {
			if(zbValue.equals("session")){
				retMap.put("resultCode", "S0003");
				retMap.put("resultMessage", "flow<" + flowId + ">不存在或已超时！");
			}else{
				String birthday = ini.getIniValue();
				if (birthday.equals(idNo)) {
					retMap.put("resultCode", "C0000");
					retMap.put("resultMessage", "续保确认成功");
				} else {
					retMap.put("resultCode", "C0006");
					retMap.put("resultMessage", "续保确认失败");
				}
			}
		} else {
			retMap.put("resultCode", "S0005");
			retMap.put("resultMessage", "数据校验失败");
		}
		return retMap;
	}

}