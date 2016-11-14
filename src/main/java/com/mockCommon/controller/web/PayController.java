package com.mockCommon.controller.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.pingan.BusiConfigService;

@Controller
public class PayController {

	@Autowired
	private BusiConfigService busiConfigService;
	
	@Resource(name = "commonDaoImpl")
	private CommonDaoImpl commonDaoImpl;

	@RequestMapping("/pay/ini")
	@ResponseBody
	public Map<String, Object> ini() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.PAY_STATUS);
		ini = busiConfigService.selectIni(ini);
		if (ini != null) {
			retMap.put("retCode", "200");
			retMap.put("payStatus", ini.getIniValue());
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "支付状态加载失败");
		}
		return retMap;
	}

	@RequestMapping("/pay/savePay")
	@ResponseBody
	public Map<String, Object> savePay(
			@RequestParam("payStatus") String payStatus) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.PAY_STATUS);
		ini.setIniValue(payStatus);
		ini.setIniDesc("支付状态");
	    int status = busiConfigService.mergeIni(ini);
		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "支付状态设置成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "支付状态设置失败");
		}
		return retMap;
	}
}