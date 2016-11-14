package com.mockCommon.service.mock.pingan.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.pingan.BaoJiaDao;
import com.mockCommon.model.mock.pingan.ForceConfigMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.BaoJiaConfig;
import com.mockCommon.service.mock.pingan.ForceInsureConfigService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.CacheUtil;
import com.mockCommon.util.freeMarker.impl.pingan.ForceInsureConfigFreeMarker;

@Service
public class ForceInsureConfigServiceImpl implements ForceInsureConfigService {

	@Autowired
	private ForceInsureConfigFreeMarker forceConfigFreeMarker;
	@Autowired
	private BusiConfigService busiConfigService;
	@Autowired
	private BaoJiaDao baoJiaDao;
	public String forceQuote(String beginDate, String flowId) {
		
		String jqxBeginDate = null;
		String result = null;
		ForceConfigMockModel configModel = new ForceConfigMockModel();
		if(StringUtils.isEmpty(beginDate)){
			List<BaoJiaConfig> configList = baoJiaDao.queryAllConfigs();
			for(int i=0; i<configList.size(); i++){
				BaoJiaConfig config = configList.get(i);
				String defaultType = config.getPackageDefault();
				if("1".equals(defaultType)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Timestamp tsForce = config.getJqInsrStartTime();
					long tsLong = tsForce.getTime();
					jqxBeginDate = sdf.format(new Date(tsLong));
				}
			}
		}else{
			jqxBeginDate = beginDate;
		}
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.JQXBJ_IS_JQX);
		ini = busiConfigService.selectIni(ini);
		String isJqx = null;
		if(ini != null){
			isJqx = ini.getIniValue();
		}
		
		if(StringUtils.isEmpty(isJqx)){
			configModel.setIsApplyForce("true");
			configModel.setResultCode("C0000");
		}else{
			configModel.setIsApplyForce("false");
			configModel.setResultCode("C0000");
		}
		configModel.setErrorMessage(isJqx);
		configModel.setBeginDate(jqxBeginDate);
		ini.setIniName(SessionKey.JQXBJ_JQXJG);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			configModel.setForceFee(ini.getIniValue());
		}
		ini.setIniName(SessionKey.JQXBJ_CCSJG);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			configModel.setTaxFee(ini.getIniValue());
		}
		setTotalFee(configModel);
		configModel.setFlowId(flowId);
		
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
			configModel.setResultCode("S0001");
		}
		
		if(zbValue != null && zbValue.equals("session")){
			configModel.setResultCode("S0003");
			configModel.setErrorMessage("flow<" + flowId + ">不存在或已超时！");
		}
		
		result = forceConfigFreeMarker.generateData2output(ContextConstant.PREFIX_FORCE_INSURE, configModel);
		LogConstant.runLog.info("[forceInsure]mock result is:" + result);
		return result;
	}

	private void setTotalFee(ForceConfigMockModel model){
		String forceFee = model.getForceFee();
		String taxFee = model.getTaxFee();
		LogConstant.runLog.info("[forceInsure]force fee is:" + forceFee + ", taxFee is: " + taxFee);
		if(StringUtils.isEmpty(forceFee) || StringUtils.isEmpty(taxFee)){
			return;
		}
		BigDecimal bdForceFee = new BigDecimal(forceFee);
		BigDecimal bdTaxFee = new BigDecimal(taxFee);
		BigDecimal total = bdForceFee.add(bdTaxFee);
		CacheUtil.put(SessionKey.JQX_TOTAL_FEE, String.valueOf(total));
	}
}