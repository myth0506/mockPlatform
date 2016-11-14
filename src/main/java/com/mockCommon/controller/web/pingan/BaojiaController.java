package com.mockCommon.controller.web.pingan;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.controller.web.BaseController;
import com.mockCommon.model.web.BusinessDict;
import com.mockCommon.model.web.pingan.BaoJiaConfig;
import com.mockCommon.model.web.youbi.BusinessInfoModel;
import com.mockCommon.service.web.BusinessDictService;
import com.mockCommon.service.web.pingan.BaoJiaService;

@Controller
public class BaojiaController extends BaseController {

	@Autowired
	private BaoJiaService baoJiaService;
	@Autowired
	private BusinessDictService busiDictService;

	
	@RequestMapping("/business/iniAllDict")
	@ResponseBody
	public Map<String, Object> iniAllDict(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<String> returnList = new ArrayList<String>();
		List<BaoJiaConfig> configList = baoJiaService.queryAllConfigs();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(configList != null && configList.size() > 0){
			BaoJiaConfig config0 = configList.get(0);
			Timestamp tsShyx = config0.getBusiInsrStartTime();
			Timestamp tsJqx = config0.getJqInsrStartTime();
			Date dateShyx = new Date(tsShyx.getTime());
			Date dateJqx = new Date(tsJqx.getTime());
			retMap.put("dateShyx", sdf.format(dateShyx));
			retMap.put("dateJqx", sdf.format(dateJqx));
			for(int i=0; i<configList.size(); i++){
				BaoJiaConfig config = configList.get(i);
				String configType = config.getPackageType();
				String configDefault = config.getPackageDefault();
				String configReturn = config.getPackageReturn();
				if("1".equals(configDefault)){
					retMap.put("defaultConfig", configType);
				}
				if("1".equals(configReturn)){
					returnList.add(configType);
				}
			}
		}
		retMap.put("returnList", returnList);
		return retMap;
	}
	@RequestMapping("/baojia/getLatestConfigs")
	@ResponseBody
	public Map<String, Object> getLatestConfigs(@RequestParam("tcType")String tcType){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageType", tcType);
		BaoJiaConfig config = baoJiaService.queryConfig(map);
		List<BusinessDict> dictList = busiDictService.queryAllBusiDict();
		BaoJiaConfig chkConfig = baoJiaService.queryLatestConfig();
		if(config != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", config);
			retMap.put("retDict", dictList);
			retMap.put("retChk", chkConfig);
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "There is no package data in db");
		}
		return retMap;
	}
	@RequestMapping("/baojia/getLatestChkConfigs")
	@ResponseBody
	public Map<String, Object> getLatestChkConfigs(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		BaoJiaConfig config = baoJiaService.queryLatestConfig();
		List<BusinessDict> dictList = busiDictService.queryAllBusiDict();
		if(config != null){
			retMap.put("retCode", "200");
			retMap.put("retDesc", config);
			retMap.put("retDict", dictList);
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "There is no package data in db");
		}
		return retMap;
	}
	
	@RequestMapping("/baojia/updateConfigs")
	@ResponseBody
	public Map<String, Object> updateConfigs(@RequestParam("tcType")String tcType, @RequestParam("tcName")String tcName,
						@RequestParam("codeStr")String codeStr, @RequestParam("valStr")String valStr){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageType", tcType);
		BaoJiaConfig config = baoJiaService.queryConfig(map);
		String[] codeArr = codeStr.split(",");
		String[] valArr = valStr.split(",");
		BaoJiaConfig newConfig = new BaoJiaConfig();
		newConfig.setPackageType(tcType);
		for(int i=0; i<codeArr.length; i++){
			String code = codeArr[i];
			String value = valArr[i];
			if("amount01".equals(code)){
				newConfig.setAmount01(value);
			}else if("amount02".equals(code)){
				newConfig.setAmount02(value);
			}else if("amount03".equals(code)){
				newConfig.setAmount03(value);
			}else if("amount04".equals(code)){
				newConfig.setAmount04(value);
			}else if("amount05".equals(code)){
				newConfig.setAmount05(value);
			}else if("amount08".equals(code)){
				newConfig.setAmount08(value);
			}else if("amount17".equals(code)){
				newConfig.setAmount17(value);
			}else if("amount18".equals(code)){
				newConfig.setAmount18(value);
			}else if("amount27".equals(code)){
				newConfig.setAmount27(value);
			}else if("amount28".equals(code)){
				newConfig.setAmount28(value);
			}else if("amount41".equals(code)){
				newConfig.setAmount41(value);
			}else if("amount48".equals(code)){
				newConfig.setAmount48(value);
			}else if("amount49".equals(code)){
				newConfig.setAmount49(value);
			}else if("amount50".equals(code)){
				newConfig.setAmount50(value);
			}else if("amount57".equals(code)){
				newConfig.setAmount57(value);
			}else if("amount59".equals(code)){
				newConfig.setAmount59(value);
			}else if("amount63".equals(code)){
				newConfig.setAmount63(value);
			}
		}
		int retCode = 0;
		if(config == null){
			retCode = baoJiaService.insertConfig(newConfig);
		}else{
			retCode = baoJiaService.updateConfig(newConfig);
		}
		if(retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "update config succeed");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "update config failed");
		}
		return retMap;
	}
	@RequestMapping("/baojia/saveReturnInsures")
	@ResponseBody
	public Map<String, Object> saveReturnInsures(@RequestParam("defaultTclb")String defaultTclb, @RequestParam("returnlb")String returnlb,
						@RequestParam("syxqbrq")String syxqbrq, @RequestParam("jqxqbrq")String jqxqbrq){
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();

		List<String> lbList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int retCode = 0;
		map.put("package_type", defaultTclb);
		retCode = baoJiaService.updateDefaultPk(map);
		String[] lbArr = returnlb.split(",");
		for(int i=0; i<lbArr.length; i++){
			lbList.add(lbArr[i]);
		}
		retCode = baoJiaService.updateReturnPk(lbList);
		Timestamp tsShyx = null;
		Timestamp tsJqx = null;
		try {
			tsShyx = new Timestamp(sdf.parse(syxqbrq).getTime());
			tsJqx = new Timestamp(sdf.parse(jqxqbrq).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		BaoJiaConfig config = new BaoJiaConfig();
		if(tsShyx != null && tsJqx != null){
			config.setBusiInsrStartTime(tsShyx);
			config.setJqInsrStartTime(tsJqx);
		}
		retCode = baoJiaService.updateStartTime(config);
		if(retCode > 0){
			retMap.put("retCode", "200");
			retMap.put("retDesc", "save config succeed");
		}else{
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "save config failed");
		}
		return retMap;
	}
}
