package com.mockCommon.service.mock.pingan.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.pingan.SendPolicyAddressMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.pingan.SendPolicyAddressMockService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.MaskUtil;
import com.mockCommon.util.freeMarker.IDataMaker;

@Service
public class SendPolicyAddressMockServiceImpl implements
		SendPolicyAddressMockService {

	@Autowired
	private IDataMaker<SendPolicyAddressMockModel> sendPolicyAddressFreeMarker;
	@Autowired
	private BusiConfigService busiConfigService;

	@Override
	public String sendPolicyAddress(String flowId) {
		String result = null;
		int isXbyh = 0;
		try {
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.XBSR_IS_XBYH);
			ini = busiConfigService.selectIni(ini);
			if(ini != null){
				isXbyh = Integer.parseInt(ini.getIniValue());
			}
		} catch (NullPointerException e) {
			isXbyh = 0;
		}
		SendPolicyAddressMockModel sendPolicyAddressMockModel = null;
		if (isXbyh == 1) { // 用户为续保用户
			sendPolicyAddressMockModel = new SendPolicyAddressMockModel();
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.ADDRESS_NAME);
			ini = busiConfigService.selectIni(ini);
			if(ini != null){
				sendPolicyAddressMockModel.setName(ini.getIniValue());
			}
			ini = new BusinessIni();
			ini.setIniName(SessionKey.ADDRESS_MOBILE);	
			ini = busiConfigService.selectIni(ini);
			if(ini != null){
				sendPolicyAddressMockModel.setMobile(ini.getIniValue());
			}
			ini = new BusinessIni();
			ini.setIniName(SessionKey.ADDRESS_CITY_NAME);
			ini = busiConfigService.selectIni(ini);
			if(ini != null){
				sendPolicyAddressMockModel.setCityName(ini.getIniValue());
			}
			ini = new BusinessIni();
			ini.setIniName(SessionKey.ADDRESS_TOWN_NAME);
			ini = busiConfigService.selectIni(ini);
			if(ini != null){
				sendPolicyAddressMockModel.setTownCityCode(ini.getIniValue());
			}
			ini = new BusinessIni();
			ini.setIniName(SessionKey.ADDRESS_DETAIL);
			ini = busiConfigService.selectIni(ini);
			if(ini != null){
				sendPolicyAddressMockModel.setDetail(ini.getIniValue());
			}
			if (sendPolicyAddressMockModel != null) {
				String mobile = sendPolicyAddressMockModel.getMobile();
				if (mobile != null) { // 手机号码做掩码处理
					sendPolicyAddressMockModel.setMobile(MaskUtil.mask(mobile));
				}
			}
		} else { // sendPolicyAddressMockModel为空，新建一个对象FreeMarker解析时不会空指针
			sendPolicyAddressMockModel = new SendPolicyAddressMockModel();
		}
		result = sendPolicyAddressFreeMarker.generateData2output(
				ContextConstant.PREFIX_SEND_POLICY_ADDRESS,
				sendPolicyAddressMockModel);
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.GAZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.GAZB_PA_DT);
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
			result = "";
		}
		
		LogConstant.debugLog.info("SendPolicyAddress Mock 返回数据： " + result);
		return result;
	}

}