package com.mockCommon.controller.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.pingan.BusiConfigService;

@Controller("chargeController")
public class ChargeController {
	
	@Autowired
	private BusiConfigService busiConfigService;

	@RequestMapping("/charge/ini")
	@ResponseBody
	public Map<String, Object> ini() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.CHARGE_CARDINFO);
		BusinessIni cardInfo = busiConfigService.selectIni(ini);
		ini.setIniName(SessionKey.CHARGE_CHARGE);
		BusinessIni charge = busiConfigService.selectIni(ini);
		ini.setIniName(SessionKey.CHARGE_CHARGERES);
		BusinessIni chargeRes = busiConfigService.selectIni(ini);
		if (cardInfo != null && charge != null && chargeRes != null) {
			retMap.put("retCode", "200");
			retMap.put("cardInfo", cardInfo.getIniValue());
			retMap.put("charge", charge.getIniValue());
			retMap.put("chargeRes", chargeRes.getIniValue());
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "加载失败");
		}
		return retMap;
	}

	@RequestMapping("/charge/saveCharge")
	@ResponseBody
	public Map<String, Object> saveCharge(
			@RequestParam("cardInfo") String cardInfo,
			@RequestParam("charge") String charge,
			@RequestParam("chargeRes") String chargeRes) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.CHARGE_CARDINFO);
		ini.setIniValue(cardInfo);
		ini.setIniDesc("充值卡类型");
	    int status1 = busiConfigService.mergeIni(ini);
	    ini.setIniName(SessionKey.CHARGE_CHARGE);
		ini.setIniValue(charge);
		ini.setIniDesc("充值金额");
	    int status2 = busiConfigService.mergeIni(ini);
	    ini.setIniName(SessionKey.CHARGE_CHARGERES);
		ini.setIniValue(chargeRes);
		ini.setIniDesc("充值结果");
	    int status3 = busiConfigService.mergeIni(ini);
		if (status1 > 0 && status2 > 0 && status3 > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "加油卡充值设置成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "加油卡充值设置失败");
		}
		return retMap;
	}
}