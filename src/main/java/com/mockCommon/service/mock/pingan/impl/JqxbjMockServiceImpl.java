package com.mockCommon.service.mock.pingan.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.pingan.JqxbjMockService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.CacheUtil;

@Service
public class JqxbjMockServiceImpl implements JqxbjMockService {

	@Autowired
	private BusiConfigService busiConfigService;
	
	@Override
	public Map<String, Object> forceQuote(String flowId,String beginDate) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("flowId", flowId);
		
		if(CacheUtil.get(SessionKey.JQXBJ_IS_JQX) != null &&
		   CacheUtil.get(SessionKey.JQXBJ_JQXJG) != null &&
		   CacheUtil.get(SessionKey.JQXBJ_CCSJG) != null){
			int isJqx = (int)CacheUtil.get(SessionKey.JQXBJ_IS_JQX);
			int jqxjg = (int)CacheUtil.get(SessionKey.JQXBJ_JQXJG);
			int ccsjg = (int)CacheUtil.get(SessionKey.JQXBJ_CCSJG);
			if(isJqx == 1){
				retMap.put("resultCode", "C0000");
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("forceInfo.beginDate", beginDate);
					paramMap.put("resultCode", "C0000");
					paramMap.put("message", "");
					paramMap.put("isApplyForce", "true");
					paramMap.put("forcePremium", jqxjg);
					paramMap.put("taxPremium", ccsjg);
					paramMap.put("totalPremium", jqxjg+ccsjg);
					paramMap.put("isRepForce", "false");
					paramMap.put("lastBeginDate", "");
					paramMap.put("lastEndDate", "");
						Map<String, Object> taxMap = new HashMap<String, Object>();
						taxMap.put("curYearTax",ccsjg);
						taxMap.put("lastYearPay","0");
						taxMap.put("delayPayMoney","0");
						taxMap.put("promptText","按国家规定须与交强险同时购买");
						taxMap.put("promptRemark","上年度未缴税、本年度已完税、减税、免税等客户暂时不能通过网上投保，如有疑问，请联系客服。");
						taxMap.put("code","1");
					paramMap.put("tax", taxMap);
				retMap.put("forcePremium", paramMap);
			}else{
				retMap.put("resultCode", "S0001");
			}
		}else{
			retMap.put("resultCode", "S0005");
		}
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.JQBJZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.JQBJZB_PA_DT);
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
		
		return retMap;
	}
}