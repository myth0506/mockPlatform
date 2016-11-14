package com.mockCommon.service.mock.pingan.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.AcceptShopOrder;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.PolicyAndOrderInfoModel;
import com.mockCommon.service.mock.pingan.PolicyAndOrderMockService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.DateUtil;

@Service
public class PolicyAndOrderMockServiceImpl implements PolicyAndOrderMockService {

	@Autowired
	private BusiConfigService busiConfigService;
	@Override
	public Map<String, Object> queryPolicyStatus() {
		//PolicyAndOrderInfoModel policyAndOrderInfoModel = (PolicyAndOrderInfoModel) CacheUtil.get(SessionKey.POLICY_AND_ORDER);
		PolicyAndOrderInfoModel policyAndOrderInfoModel = queryPolicyAndOrder();
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (policyAndOrderInfoModel != null) {
			String policyStatus = policyAndOrderInfoModel.getPolicyStatus();
			if (policyStatus != null) {
				retMap.put("resultCode", policyStatus);
				retMap.put("message", com.mockCommon.constant.queryPolicyStatus
						.getValue(policyStatus));
				retMap.put("payNo", "上海同业工会Id");
				Random ran = new Random();
				// 随机返回当前日期后7天的日期
				retMap.put("expirePayTime", DateUtil.getDate(ran.nextInt(7)));
			}
		} else { // 缓存中不存在
			retMap.put("resultCode", "1");
			retMap.put("message", "全部不可以支付");
			retMap.put("payNo", "");
			retMap.put("expirePayTime", "");
		}
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.PCZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.PCZB_PA_DT);
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
			retMap.put("message", "系统错误");
			retMap.put("payNo", "");
			retMap.put("expirePayTime", "");
		}
		
		LogConstant.debugLog.info("queryPolicyStatus mock数据："
				+ retMap.toString());
		return retMap;
	}

	@Override
	public Map<String, Object> acceptShopOrder(String bizOrderNo,
			String forceOrderNo) {
		PolicyAndOrderInfoModel policyAndOrderInfoModel = queryPolicyAndOrder();
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (bizOrderNo != null) {
			retMap.put("bizOrderNo", bizOrderNo);
		} else {
			retMap.put("bizOrderNo", "");
		}
		if (forceOrderNo != null) {
			retMap.put("forceOrderNo", forceOrderNo);
		} else {
			retMap.put("forceOrderNo", "");
		}
		if (policyAndOrderInfoModel != null) {
			String setOrderStatus = policyAndOrderInfoModel.getSetOrderStatus();
			if (setOrderStatus != null) {
				retMap.put("resultCode", setOrderStatus);
				retMap.put("message",
						AcceptShopOrder.getTypeVal(setOrderStatus));
				if (setOrderStatus.equals("0") || setOrderStatus.equals("4")) { // 成功或部分成功部分失败,失败的订单不会存在policyNo节点
					String bizOrder = policyAndOrderInfoModel.getBizOrder();
					String forceOrder = policyAndOrderInfoModel.getJqxOrder();
					if (bizOrder != null && bizOrder.equals("0")) { // 商业险出单成功
						retMap.put("bizPolicyNo",
								"Biz" + DateUtil.getTimestamp());
					} else {
						retMap.put("bizPolicyNo", "");
					}
					if (forceOrder != null && forceOrder.equals("0")) { // 交强险出单成功
						retMap.put("forcePolicyNo",
								"Force" + DateUtil.getTimestamp());
					} else {
						retMap.put("forcePolicyNo", "");
					}
				} else {
					retMap.put("bizPolicyNo", "");
					retMap.put("forcePolicyNo", "");
				}
			}
		} else { // 缓存中不存在
			retMap.put("resultCode", "1");
			retMap.put("message", "验签失败");
			retMap.put("biz.policyNo", "");
			retMap.put("force.policyNo", "");
		}
		
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.CDZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.CDZB_PA_DT);
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
			retMap.put("message", "系统错误");
			retMap.put("biz.policyNo", "");
			retMap.put("force.policyNo", "");
		}else if(zbValue != null && zbValue.equals("session")){
			retMap.put("resultCode", "S0003");
			retMap.put("message", "不存在或已超时！");
			retMap.put("biz.policyNo", "");
			retMap.put("force.policyNo", "");
		}
		
		
		LogConstant.debugLog
				.info("acceptShopOrder mock数据：" + retMap.toString());
		return retMap;
	}
	
	private PolicyAndOrderInfoModel queryPolicyAndOrder(){
		PolicyAndOrderInfoModel model = new PolicyAndOrderInfoModel();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.CD_PAY_CHECK);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			model.setPolicyStatus(ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.CD_RET_STATUS);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			model.setSetOrderStatus(ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.CD_BIZ);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			model.setBizOrder(ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.CD_JQX);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			model.setJqxOrder(ini.getIniValue());
		}
		return model;
	}

}