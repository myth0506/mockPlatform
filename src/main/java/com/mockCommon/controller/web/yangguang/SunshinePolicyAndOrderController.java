package com.mockCommon.controller.web.yangguang;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;

@Controller
public class SunshinePolicyAndOrderController {

	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigService;

	@RequestMapping("/yangguang/InsrPolicyAndOrder/submitPolicyAndOrder")
	@ResponseBody
	public Map<String, Object> submitPolicyAndOrder(
			@RequestParam("policyStatus") String policyStatus,
			@RequestParam("setOrderStatus") String setOrderStatus) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		int status = 0;
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.CD_PAY_CHECK);
		ini.setIniValue(policyStatus);
		ini.setIniDesc("出单支付检查");
		status = sunshineBusiConfigService.mergeIni(ini);
		if(status > 0){
			ini = new BusinessIni();
			ini.setIniName(SessionKey.CD_RET_STATUS);
			ini.setIniValue(setOrderStatus);
			ini.setIniDesc("出单返回状态");
			status = sunshineBusiConfigService.mergeIni(ini);
		}

		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "失败");
		}
		return retMap;
	}
	@RequestMapping("/yangguang/InsrPolicyAndOrder/iniPolicyAndOrder")
	@ResponseBody
	public Map<String, Object> iniPolicyAndOrder(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.CD_PAY_CHECK);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("payCheck", ini.getIniValue());
		}
		ini = new BusinessIni();
		ini.setIniName(SessionKey.CD_RET_STATUS);
		ini = sunshineBusiConfigService.selectIni(ini);
		if(ini != null){
			retMap.put("retStatus", ini.getIniValue());
		}
		retMap.put("retCode", "200");
		return retMap;
	}
}