package com.mockCommon.service.mock.pingan.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.pingan.BusinessConfigMockModel;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessDict;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.pingan.BaoJiaConfig;
import com.mockCommon.service.mock.pingan.BusinessConfigService;
import com.mockCommon.service.web.BusinessDictService;
import com.mockCommon.service.web.pingan.BaoJiaService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.CacheUtil;
import com.mockCommon.util.freeMarker.impl.pingan.BusinessConfigFreeMarker;
@Service
public class BusinessConfigServiceImpl implements BusinessConfigService {

	@Autowired
	private BaoJiaService baoJiaService;
	@Autowired
	private BusiConfigService busiConfigService;
	@Autowired
	private BusinessDictService businessDictService;
	@Autowired
	private BusinessConfigFreeMarker businessConfigFreeMarker;
	
	public String bizQuote(String flowId, String pkgName, String beginDate,
			String inputAmount, String amount01, String amount02,
			String amount03, String amount04, String amount05, String amount08,
			String amount17, String amount18, String amount27, String amount28,
			String amount48, String amount49, String amount50, String amount41,
			String amount57, String amount59, String amount63) {
		
		BusinessConfigMockModel config = new BusinessConfigMockModel();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> iniMap = new HashMap<String, String>();
		Map<String, BusinessConfig> busiConfigMap = new HashMap<String, BusinessConfig>();
		BigDecimal totalPremium = new BigDecimal(0);
		map.put("packageName", pkgName);
		BaoJiaConfig baoJiaConfig = baoJiaService.queryConfig(map);
		LogConstant.runLog.info("[BusinessConfigMock]BaoJiaConfig is:" + baoJiaConfig);
		if(baoJiaConfig != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Timestamp ts = baoJiaConfig.getBusiInsrStartTime();
			if(ts != null){
				long dateLong = ts.getTime();
				Date date = new Date(dateLong);
				String dateStr = sdf.format(date);
				if(StringUtils.isEmpty(beginDate)){
					beginDate = dateStr;
				}
			}
		}
		LogConstant.runLog.info("[BusinessConfigMock] begin date is:" + beginDate);
		List<BusinessIni> iniList = busiConfigService.queryAllInis();
		for(int i=0; i<iniList.size(); i++){
			BusinessIni ini = iniList.get(i);
			String iniName = ini.getIniName();
			String iniValue = ini.getIniValue();
			iniMap.put(iniName, iniValue);
		}
		List<BusinessDict> dictList = businessDictService.queryAllBusiDict();
		for(int i=0; i<dictList.size(); i++){
			BusinessDict dict = dictList.get(i);
			String dictCode = dict.getBusinessCode();
			String dictType = dict.getType();
			if("1".equals(dictType)){
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("businessCode", dictCode);
				if("amount02".equals(dictCode)){
					paramMap.put("baoE", baoJiaConfig.getAmount02());
				}else if("amount04".equals(dictCode)){
					paramMap.put("baoE", baoJiaConfig.getAmount04());
				}else if("amount05".equals(dictCode)){
					paramMap.put("baoE", baoJiaConfig.getAmount05());
				}else if("amount17".equals(dictCode)){
					paramMap.put("baoE", baoJiaConfig.getAmount17());
				}
				List<BusinessConfig> dictConfigList = busiConfigService.queryConfigList(paramMap);
				if(dictConfigList != null && dictConfigList.size() > 0){
					BusinessConfig bc = dictConfigList.get(0);
					busiConfigMap.put(dictCode, bc);
					totalPremium = totalPremium.add(new BigDecimal(bc.getBaoFei()));
				}
			}
			
		}
		if("optional".equals(pkgName)){
			BaoJiaConfig optionConfig = new BaoJiaConfig();
			Map<String, String> paramMap = new HashMap<String, String>();
			if(StringUtils.isNotEmpty(amount01)){
				optionConfig.setAmount01(amount01);
			}else{
				optionConfig.setAmount01("-1");
			}
			if(StringUtils.isNotEmpty(amount02)){
				optionConfig.setAmount02(amount02);
				paramMap.put("businessCode", "amount02");
				paramMap.put("baoE", amount02);
				dynamicFee("amount02", busiConfigMap, totalPremium, paramMap);
			}else{
				optionConfig.setAmount02("-1");
			}
			if(StringUtils.isNotEmpty(amount03)){
				optionConfig.setAmount03(amount03);
			}else{
				optionConfig.setAmount03("-1");
			}
			if(StringUtils.isNotEmpty(amount04)){
				optionConfig.setAmount04(amount04);
				paramMap.put("businessCode", "amount04");
				paramMap.put("baoE", amount04);
				dynamicFee("amount04", busiConfigMap, totalPremium, paramMap);
			}else{
				optionConfig.setAmount04("-1");
			}
			if(StringUtils.isNotEmpty(amount05)){
				optionConfig.setAmount05(amount05);
				paramMap.put("businessCode", "amount05");
				paramMap.put("baoE", amount05);
				dynamicFee("amount05", busiConfigMap, totalPremium, paramMap);
			}else{
				optionConfig.setAmount05("-1");
			}
			if(StringUtils.isNotEmpty(amount08)){
				optionConfig.setAmount08(amount08);
			}else{
				optionConfig.setAmount08("-1");
			}
			if(StringUtils.isNotEmpty(amount17)){
				optionConfig.setAmount17(amount17);
				paramMap.put("businessCode", "amount17");
				paramMap.put("baoE", amount17);
				dynamicFee("amount17", busiConfigMap, totalPremium, paramMap);
			}else{
				optionConfig.setAmount17("-1");
			}
			if(StringUtils.isNotEmpty(amount18)){
				optionConfig.setAmount18(amount18);
			}else{
				optionConfig.setAmount18("-1");
			}
			if(StringUtils.isNotEmpty(amount27)){
				optionConfig.setAmount27(amount27);
			}else{
				optionConfig.setAmount27("-1");
			}
			if(StringUtils.isNotEmpty(amount28)){
				optionConfig.setAmount28(amount28);
			}else{
				optionConfig.setAmount28("-1");
			}
			if(StringUtils.isNotEmpty(amount48)){
				optionConfig.setAmount48(amount48);
			}else{
				optionConfig.setAmount48("-1");
			}
			if(StringUtils.isNotEmpty(amount49)){
				optionConfig.setAmount49(amount49);
			}else{
				optionConfig.setAmount49("-1");
			}
			if(StringUtils.isNotEmpty(amount50)){
				optionConfig.setAmount50(amount50);
			}else{
				optionConfig.setAmount50("-1");
			}
			if(StringUtils.isNotEmpty(amount41)){
				optionConfig.setAmount41(amount41);
			}else{
				optionConfig.setAmount41("-1");
			}
			if(StringUtils.isNotEmpty(amount57)){
				optionConfig.setAmount57(amount57);
			}else{
				optionConfig.setAmount57("-1");
			}
			if(StringUtils.isNotEmpty(amount59)){
				optionConfig.setAmount59(amount59);
			}else{
				optionConfig.setAmount59("-1");
			}
			if(StringUtils.isNotEmpty(amount63)){
				optionConfig.setAmount63(amount63);
			}else{
				optionConfig.setAmount63("-1");
			}
			config.setBaoJiaConfig(optionConfig);
		}else{
			config.setBaoJiaConfig(baoJiaConfig);
		}
		config.setFlowId(flowId);
		config.setPkgName(pkgName);
		config.setIniMap(iniMap);
		config.setDynamicIniMap(busiConfigMap);
		config.setInputAmount(inputAmount);
		setTotalFee(config);
		
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.BBJZB_PA);
		iniZb = busiConfigService.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.BBJZB_PA_DT);
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
			config.setResultCode("S0001");
		}else{
			config.setResultCode("C0000");
		}
		
		if(zbValue != null && zbValue.equals("session")){
			config.setResultCode("S0003");
		}
		
		String result = businessConfigFreeMarker.generateData2output(ContextConstant.PREFIX_SHANGYEXIAN_CONFIG, config);
		LogConstant.runLog.info("[BusinessConfigMock]result is:" + result);
		return result;
	}
	private void dynamicFee(String string, Map<String, BusinessConfig> busiConfigMap, BigDecimal totalPremium, Map<String, String> paramMap){
		List<BusinessConfig> dictConfigList = busiConfigService.queryConfigList(paramMap);
		if(dictConfigList != null && dictConfigList.size() > 0){
			BusinessConfig bc = dictConfigList.get(0);
			busiConfigMap.put(string, bc);
			totalPremium = totalPremium.add(new BigDecimal(bc.getBaoFei()));
		}
	}
	private void setTotalFee(BusinessConfigMockModel model){
		BigDecimal total = new BigDecimal(0);
		BaoJiaConfig config = model.getBaoJiaConfig();
		Map<String, String> iniMap = model.getIniMap();
		Map<String, BusinessConfig> dynamicMap = model.getDynamicIniMap();
		if("1".equals(config.getAmount01())){
			BigDecimal bd01 = new BigDecimal(iniMap.get("amount01"));
			total = total.add(bd01);
		}
		if(!"-1".equals(config.getAmount02()) && !"0".equals(config.getAmount02())){
			BusinessConfig amount02Config = dynamicMap.get("amount02");
			BigDecimal bd02 = new BigDecimal(amount02Config.getBaoFei());
			total = total.add(bd02);
		}
		if("1".equals(config.getAmount03())){
			BigDecimal bd03 = new BigDecimal(iniMap.get("amount03"));
			total = total.add(bd03);
		}
		if(!"-1".equals(config.getAmount04()) && !"0".equals(config.getAmount04())){
			BusinessConfig amount04Config = dynamicMap.get("amount04");
			BigDecimal bd04 = new BigDecimal(amount04Config.getBaoFei());
			total = total.add(bd04);
		}
		if(!"-1".equals(config.getAmount05()) && !"0".equals(config.getAmount05())){
			BusinessConfig amount05Config = dynamicMap.get("amount05");
			BigDecimal bd05 = new BigDecimal(amount05Config.getBaoFei());
			total = total.add(bd05);
		}
		if("1".equals(config.getAmount08())){
			BigDecimal bd08 = new BigDecimal(iniMap.get("gcAmount08"));
			total = total.add(bd08);
		}
		if("2".equals(config.getAmount08())){
			BigDecimal bd08 = new BigDecimal(iniMap.get("jkAmount08"));
			total = total.add(bd08);
		}
		if(!"-1".equals(config.getAmount17()) && !"0".equals(config.getAmount17())){
			BusinessConfig amount17Config = dynamicMap.get("amount17");
			BigDecimal bd17 = new BigDecimal(amount17Config.getBaoFei());
			total = total.add(bd17);
		}
		if("1".equals(config.getAmount18())){
			BigDecimal bd18 = new BigDecimal(iniMap.get("amount18"));
			total = total.add(bd18);
		}
		if("1".equals(config.getAmount27())){
			BigDecimal bd27 = new BigDecimal(iniMap.get("amount27"));
			total = total.add(bd27);
		}
		if("1".equals(config.getAmount28())){
			BigDecimal bd28 = new BigDecimal(iniMap.get("amount28"));
			total = total.add(bd28);
		}
		if("1".equals(config.getAmount48())){
			BigDecimal bd48 = new BigDecimal(iniMap.get("amount48"));
			total = total.add(bd48);
		}
		if("1".equals(config.getAmount49())){
			BigDecimal bd49 = new BigDecimal(iniMap.get("amount49"));
			total = total.add(bd49);
		}
		if("1".equals(config.getAmount50())){
			BigDecimal bd50 = new BigDecimal(iniMap.get("amount50"));
			total = total.add(bd50);
		}
		if("1".equals(config.getAmount57())){
			BigDecimal bd57 = new BigDecimal(iniMap.get("amount57"));
			total = total.add(bd57);
		}
		if("1".equals(config.getAmount41())){
			BigDecimal bd41 = new BigDecimal(iniMap.get("amount41"));
			total = total.add(bd41);
		}
		if("1".equals(config.getAmount59())){
			BigDecimal bd59 = new BigDecimal(iniMap.get("amount59"));
			total = total.add(bd59);
		}
		if("1".equals(config.getAmount63())){
			BigDecimal bd63 = new BigDecimal(iniMap.get("amount63"));
			total = total.add(bd63);
		}
		CacheUtil.put(SessionKey.SHYX_TOTAL_FEE, String.valueOf(total));
	}
}
