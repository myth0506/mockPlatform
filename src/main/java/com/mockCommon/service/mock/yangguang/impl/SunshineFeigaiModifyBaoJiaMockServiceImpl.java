package com.mockCommon.service.mock.yangguang.impl;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.yangguang.ResponsePackageModel;
import com.mockCommon.model.mock.yangguang.SunshineFeigaiModifyBaoJiaMockModel;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.yangguang.SunshineBaoJiaModel;
import com.mockCommon.service.mock.yangguang.SunshineModifyBaoJiaMockService;
import com.mockCommon.service.web.yangguang.SunshineBaoJiaService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.util.CacheUtil;
import com.mockCommon.util.ParseXmlUtil;
import com.mockCommon.util.WipeTabEnterSpaceUtil;
import com.mockCommon.util.freeMarker.IDataMaker;
import com.mockCommon.util.yangguang.KeyPairer;
import com.mockCommon.util.yangguang.PartnerSignerImpl;
import com.netease.common.util.StringUtil;

@Service("sunshineFeigaiModifyBaoJiaMockServiceImpl")
public class SunshineFeigaiModifyBaoJiaMockServiceImpl implements
		SunshineModifyBaoJiaMockService {

	@Autowired
	private SunshineBaoJiaService sunshineBaoJiaServiceImpl;

	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigServiceImpl;

	@Autowired
	private IDataMaker<ResponsePackageModel> packageFreeMarker;

	@Autowired
	private IDataMaker<SunshineFeigaiModifyBaoJiaMockModel> sunshineFeigaiModifyBaoJiaFreemarker;

	@Override
	public String modifyBaoJia(String xml) {
		String result = null;
		if (StringUtils.isEmpty(xml)) {
			LogConstant.debugLog
					.error("[SunshineFeigaiModifyBaoJiaMockServiceImpl-->modifyBaoJia] xml can not be empty!");
			return result;
		}

		// 解析xml参数
		String xPath = "/PackageList/Package/Request/InputsList/Inputs/Input";
		Map<String, String> xmlParams = ParseXmlUtil.parseXmlNodes(xml, xPath);
		if (xmlParams == null) {
			LogConstant.debugLog
					.error("[SunshineFeigaiModifyBaoJiaMockServiceImpl-->modifyBaoJia] parse xml parameters return empty!");
			return result;
		}
		LogConstant.debugLog.info("阳光保险请求参数为：\n" + xmlParams);

		String packageType = xmlParams.get("packageType");
		String cov_200 = xmlParams.get("cov_200");
		String cov_600 = xmlParams.get("cov_600");
		String cov_500 = xmlParams.get("cov_500");
		String cov_701 = xmlParams.get("cov_701");
		String cov_702 = xmlParams.get("cov_702");
		String cov_321 = xmlParams.get("cov_321");
		String cov_310 = xmlParams.get("cov_310");
		String cov_231 = xmlParams.get("cov_231");
		String cov_210 = xmlParams.get("cov_210");
		String cov_291 = xmlParams.get("cov_291");
		String cov_640 = xmlParams.get("cov_640");
		String cov_921 = xmlParams.get("cov_921");
		String cov_922 = xmlParams.get("cov_922");
		String cov_911 = xmlParams.get("cov_911");
		String cov_912 = xmlParams.get("cov_912");
		String cov_928 = xmlParams.get("cov_928");
		String cov_929 = xmlParams.get("cov_929");
		String cov_734 = xmlParams.get("cov_734");
		String cov_733 = xmlParams.get("cov_733");
		String bizBeginDate = xmlParams.get("bizBeginDate");
		String forceBeginDate = xmlParams.get("forceBeginDate");
		String forceFlag = xmlParams.get("forceFlag");

		SunshineFeigaiModifyBaoJiaMockModel sunshineModifyBaoJiaMockModel = new SunshineFeigaiModifyBaoJiaMockModel();
		if (!StringUtil.isEmpty(packageType)) {
			sunshineModifyBaoJiaMockModel.setPackageType(packageType);
			CacheUtil.put(SessionKey.SUNSHINE_PACKAGETYPE_BEFORE_SAVE_BAOJIA,
					packageType);
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口请求时[packageType]为空！");
			return result;
		}

		Map<String, BusinessIni> iniMap = getAllSunshineIni();
		Map<String, BusinessConfig> configMap = getAllSunshineBusinessConfig();
		Map<String, List<BusinessConfig>> configListMap = getAllSunshineBusinessConfigList();
		// 设置商业险，交强险起保日期
		sunshineModifyBaoJiaMockModel.setBizBeginDate(bizBeginDate);
		sunshineModifyBaoJiaMockModel.setForceBeginDate(forceBeginDate);
		// 设置交强险，车船税
		BusinessIni forceInsr = iniMap.get(SessionKey.SUNSHINE_JQXBJ_JQXJG);
		double force = 0;
		double vehicleTax = 0;
		sunshineModifyBaoJiaMockModel.setForceFlag(forceFlag);
		if (forceInsr != null) {
			String forcePremium = forceInsr.getIniValue();
			sunshineModifyBaoJiaMockModel.setForcePremium(forcePremium);
			force = Double.parseDouble(forcePremium);
		} else {
			sunshineModifyBaoJiaMockModel.setForceFlag("0");
			sunshineModifyBaoJiaMockModel.setForcePremium("0");
		}
		BusinessIni ccsInsr = iniMap.get(SessionKey.SUNSHINE_JQXBJ_CCSJG);
		if (ccsInsr != null) {
			String vehicleTaxPremium = ccsInsr.getIniValue();
			sunshineModifyBaoJiaMockModel
					.setVehicleTaxPremium(vehicleTaxPremium);
			vehicleTax = Double.parseDouble(vehicleTaxPremium);
		}
		int forceTotalPremiumInt = (int) Math.round(force + vehicleTax);
		String forceTotalPremium = String.valueOf(forceTotalPremiumInt);
		sunshineModifyBaoJiaMockModel.setForceTotalPremium(forceTotalPremium);

		// 设置从数据库中取出的数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageName", packageType);
		SunshineBaoJiaModel sunshineBaoJiaModel = sunshineBaoJiaServiceImpl
				.queryConfig(map);
		if (sunshineBaoJiaModel == null) {
			LogConstant.debugLog.info("阳光保险修改报价接口查询数据库中packageType的记录为空！");
			return result;
		}
		SunshineBaoJiaModel baoJiaModel = new SunshineBaoJiaModel(); // 返回的mock数据中SunshineBaoJiaModel数据
		double bizTotalPremium = 0;
		SunshineBaoJiaModel sunshineBaoJia = new SunshineBaoJiaModel(); // 用于最后向数据库中插入修改的报价
		if (!StringUtil.isEmpty(cov_200)) {
			sunshineModifyBaoJiaMockModel.setCov200Value(cov_200);
			if (!cov_200.equals("0")) { // cov_200可报价
				sunshineBaoJia.setCov_200(cov_200);
				BusinessConfig config = configMap.get("cov_200" + cov_200);
				if (config != null) {
					if(!cov_200.equals("0.00")){
						String cov_200Baofei = config.getBaoFei();
						baoJiaModel.setCov_200(cov_200Baofei);
						bizTotalPremium += Double.parseDouble(cov_200Baofei);
					}else{
						baoJiaModel.setCov_200("0");
					}
					String cov200Data = "";
					if (configListMap != null) {
						List<BusinessConfig> configList = configListMap
								.get("cov_200");
						for (BusinessConfig business : configList) {
							cov200Data += business.getWenAn() + ":"
									+ business.getBaoE() + ";";
						}
						if (cov200Data.length() > 0)
							cov200Data = cov200Data.substring(0,
									cov200Data.length() - 1);
					}
					sunshineModifyBaoJiaMockModel.setCov200Data(cov200Data);
				}
			} else {
				sunshineModifyBaoJiaMockModel.setCov200Data("不可投保:0");
				sunshineBaoJia.setCov_200("-1");
			}
		} else { // cov_200为空
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_200]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_600)) {
			sunshineModifyBaoJiaMockModel.setCov600Value(cov_600);
			if (!cov_600.equals("0")) {
				sunshineBaoJia.setCov_600(cov_600);
				BusinessConfig config = configMap.get("cov_600" + cov_600);
				if (config != null) {
					if(!cov_600.equals("0.00")){
						String cov_600Baofei = config.getBaoFei();
						baoJiaModel.setCov_600(cov_600Baofei);
						bizTotalPremium += Double.parseDouble(cov_600Baofei);
					}else{
						baoJiaModel.setCov_600("0");
					}
					String cov600Data = "";
					if (configListMap != null) {
						List<BusinessConfig> configList = configListMap
								.get("cov_600");
						for (BusinessConfig business : configList) {
							cov600Data += business.getWenAn() + ":"
									+ business.getBaoE() + ";";
						}
						if (cov600Data.length() > 0)
							cov600Data = cov600Data.substring(0,
									cov600Data.length() - 1);
					}
					sunshineModifyBaoJiaMockModel.setCov600Data(cov600Data);
				}
			} else {
				sunshineModifyBaoJiaMockModel.setCov600Data("不可投保:0");
				sunshineBaoJia.setCov_600("-1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_600]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_500)) {
			sunshineModifyBaoJiaMockModel.setCov500Value(cov_500);
			if (!cov_500.equals("0")) {
				sunshineModifyBaoJiaMockModel
						.setCov500Data("不投保:0.00;投保:60300.00");
				if(cov_500.equals("0.00")){
					sunshineBaoJia.setCov_500("0");
					baoJiaModel.setCov_500("0");
				}else{
					sunshineBaoJia.setCov_500("1");
					BusinessIni ini = iniMap.get("cov_500");
					if (ini != null) {
						String cov_500Baofei = ini.getIniValue();
						baoJiaModel.setCov_500(cov_500Baofei);
						bizTotalPremium += Double.parseDouble(cov_500Baofei);
					}
				}
			} else {
				sunshineModifyBaoJiaMockModel.setCov500Data("不可投保:0");
				sunshineBaoJia.setCov_500("-1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_500]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_701)) {
			sunshineModifyBaoJiaMockModel.setCov701Value(cov_701);
			if (!cov_701.equals("0")) {
				sunshineBaoJia.setCov_701(cov_701);
				BusinessConfig config = configMap.get("cov_701" + cov_701);
				if (config != null) {
					if(!cov_701.equals("0.00")){
						String cov_701Baofei = config.getBaoFei();
						baoJiaModel.setCov_701(cov_701Baofei);
						bizTotalPremium += Double.parseDouble(cov_701Baofei);
					}else{
						baoJiaModel.setCov_701("0");
					}
					String cov701Data = "";
					if (configListMap != null) {
						List<BusinessConfig> configList = configListMap
								.get("cov_701");
						for (BusinessConfig business : configList) {
							cov701Data += business.getWenAn() + ":"
									+ business.getBaoE() + ";";
						}
						if (cov701Data.length() > 0)
							cov701Data = cov701Data.substring(0,
									cov701Data.length() - 1);
					}
					sunshineModifyBaoJiaMockModel.setCov701Data(cov701Data);
				}
			} else {
				sunshineModifyBaoJiaMockModel.setCov701Data("不可投保:0");
				sunshineBaoJia.setCov_701("-1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_701]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_702)) {
			sunshineModifyBaoJiaMockModel.setCov702Value(cov_702);
			if (!cov_702.equals("0")) {
				sunshineBaoJia.setCov_702(cov_702);
				BusinessConfig config = configMap.get("cov_702" + cov_702);
				if (config != null) {
					if(!cov_702.equals("0.00")){
						String cov_702Baofei = config.getBaoFei();
						baoJiaModel.setCov_702(cov_702Baofei);
						bizTotalPremium += Double.parseDouble(cov_702Baofei);
					}else{
						baoJiaModel.setCov_702("0");
					}
					String cov702Data = "";
					if (configListMap != null) {
						List<BusinessConfig> configList = configListMap
								.get("cov_702");
						for (BusinessConfig business : configList) {
							cov702Data += business.getWenAn() + ":"
									+ business.getBaoE() + ";";
						}
						if (cov702Data.length() > 0)
							cov702Data = cov702Data.substring(0,
									cov702Data.length() - 1);
					}
					sunshineModifyBaoJiaMockModel.setCov702Data(cov702Data);
				}
			} else {
				sunshineModifyBaoJiaMockModel.setCov702Data("不可投保:0");
				sunshineBaoJia.setCov_702("-1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_702]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_321)) {
			sunshineModifyBaoJiaMockModel.setCov321Value(cov_321);
			if (!cov_321.equals("0")) {
				sunshineModifyBaoJiaMockModel
						.setCov321Data("不投保:0.00;投保:10.00");
				if(cov_321.equals("0.00")){
					sunshineBaoJia.setCov_321("0");
					baoJiaModel.setCov_321("0");
				}else{
					sunshineBaoJia.setCov_321("1");
					BusinessIni ini = iniMap.get("cov_321");
					if (ini != null) {
						String cov_321Baofei = ini.getIniValue();
						baoJiaModel.setCov_321(cov_321Baofei);
						bizTotalPremium += Double.parseDouble(cov_321Baofei);
					}
				}
			} else {
				sunshineModifyBaoJiaMockModel.setCov321Data("不可投保:0");
				sunshineBaoJia.setCov_321("-1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_321]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_310)) {
			sunshineModifyBaoJiaMockModel.setCov310Value(cov_310);
			if (!cov_310.equals("0")) {
				sunshineModifyBaoJiaMockModel
						.setCov310Data("不投保:0.00;投保:60300.00");
				if(cov_310.equals("0.00")){
					sunshineBaoJia.setCov_310("0");
					baoJiaModel.setCov_310("0");
				}else{
					sunshineBaoJia.setCov_310("1");
					BusinessIni ini = iniMap.get("cov_310");
					if (ini != null) {
						String cov_310Baofei = ini.getIniValue();
						baoJiaModel.setCov_310(cov_310Baofei);
						bizTotalPremium += Double.parseDouble(cov_310Baofei);
					}
				}
			} else {
				sunshineModifyBaoJiaMockModel.setCov310Data("不可投保:0");
				sunshineBaoJia.setCov_310("-1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_310]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_231)) {
			sunshineModifyBaoJiaMockModel.setCov231Value(cov_231);
			if ("-1".equals(sunshineBaoJiaModel.getCov_231())) { // 没有勾选该险种
				sunshineModifyBaoJiaMockModel.setCov231Data("不可投保:0");
				sunshineBaoJia.setCov_231("-1");
			} else {
				String cov_231Baifei = null;
				sunshineModifyBaoJiaMockModel
						.setCov231Data("不投保:0;国产玻璃:1;进口玻璃:2");
				if (cov_231.equals("1")) {
					BusinessIni ini = iniMap.get("gcCov_231");
					if (ini != null) {
						cov_231Baifei = ini.getIniValue();
					}
					sunshineBaoJia.setCov_231("1");
				} else if (cov_231.equals("2")) {
					BusinessIni ini = iniMap.get("jkCov_231");
					if (ini != null) {
						cov_231Baifei = ini.getIniValue();
					}
					sunshineBaoJia.setCov_231("2");
				}else if(cov_231.equals("0")){
					sunshineBaoJia.setCov_231("0");
					cov_231Baifei = "0";
				}
				if (cov_231Baifei != null) {
					baoJiaModel.setCov_231(cov_231Baifei);
					bizTotalPremium += Double.parseDouble(cov_231Baifei);
				}
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_231]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_210)) {
			sunshineModifyBaoJiaMockModel.setCov210Value(cov_210);
			if (!cov_210.equals("0")) {
				sunshineBaoJia.setCov_210(cov_210);
				BusinessConfig config = configMap.get("cov_210" + cov_210);
				if (config != null) {
					if(!cov_210.equals("0.00")){
						String cov_210Baofei = config.getBaoFei();
						baoJiaModel.setCov_210(cov_210Baofei);
						bizTotalPremium += Double.parseDouble(cov_210Baofei);
					}else{
						baoJiaModel.setCov_210("0");
					}
					String cov210Data = "";
					if (configListMap != null) {
						List<BusinessConfig> configList = configListMap
								.get("cov_210");
						for (BusinessConfig business : configList) {
							cov210Data += business.getWenAn() + ":"
									+ business.getBaoE() + ";";
						}
						if (cov210Data.length() > 0)
							cov210Data = cov210Data.substring(0,
									cov210Data.length() - 1);
					}
					sunshineModifyBaoJiaMockModel.setCov210Data(cov210Data);
				}
			} else {
				sunshineModifyBaoJiaMockModel.setCov210Data("不可投保:0");
				sunshineBaoJia.setCov_210("-1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_210]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_291)) {
			sunshineModifyBaoJiaMockModel.setCov291Value(cov_291);
			if ("-1".equals(sunshineBaoJiaModel.getCov_291())) {
				sunshineModifyBaoJiaMockModel.setCov291Data("不可投保:0");
				sunshineBaoJia.setCov_291("-1");
			} else {
				sunshineBaoJia.setCov_291(cov_291);
				if(!cov_291.equals("0")){
					BusinessIni ini = iniMap.get("cov_291");
					if (ini != null) {
						String cov_291Baofei = ini.getIniValue();
						baoJiaModel.setCov_291(cov_291Baofei);
						bizTotalPremium += Double.parseDouble(cov_291Baofei);
					}
				}else{
					baoJiaModel.setCov_291("0");
				}
				sunshineModifyBaoJiaMockModel.setCov291Data("不投保:0;投保:1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_291]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_640)) {
			sunshineModifyBaoJiaMockModel.setCov640Value(cov_640);
			if (!cov_640.equals("0")) {
				sunshineBaoJia.setCov_640(cov_640);
				BusinessConfig config = configMap.get("cov_640" + cov_640);
				if (config != null) {
					if(!cov_640.equals("0.00")){
						String cov_640Baofei = config.getBaoFei();
						baoJiaModel.setCov_640(cov_640Baofei);
						bizTotalPremium += Double.parseDouble(cov_640Baofei);
					}else{
						baoJiaModel.setCov_640("0");
					}
					String cov640Data = "";
					if (configListMap != null) {
						List<BusinessConfig> configList = configListMap
								.get("cov_640");
						for (BusinessConfig business : configList) {
							cov640Data += business.getWenAn() + ":"
									+ business.getBaoE() + ";";
						}
						if (cov640Data.length() > 0)
							cov640Data = cov640Data.substring(0,
									cov640Data.length() - 1);
					}
					sunshineModifyBaoJiaMockModel.setCov640Data(cov640Data);
				}
			} else {
				sunshineModifyBaoJiaMockModel.setCov640Data("不可投保:0");
				sunshineBaoJia.setCov_640("-1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_640]请求值为空！");
			return result;
		}
		
		if (!StringUtil.isEmpty(cov_734)) {
			sunshineModifyBaoJiaMockModel.setCov734Value(cov_734);
			if ("-1".equals(sunshineBaoJiaModel.getCov_734())) {
				sunshineModifyBaoJiaMockModel.setCov734Data("不可投保:0");
				sunshineBaoJia.setCov_734("-1");
			} else {
				sunshineBaoJia.setCov_734(cov_734);
				if(!cov_734.equals("0")){
					BusinessIni ini = iniMap.get("cov_734");
					if (ini != null) {
						String cov_734Baofei = ini.getIniValue();
						baoJiaModel.setCov_734(cov_734Baofei);
						bizTotalPremium += Double.parseDouble(cov_734Baofei);
					}
				}else{
					baoJiaModel.setCov_734("0");
				}
				sunshineModifyBaoJiaMockModel.setCov734Data("不投保:0;投保:1");
			}
			// 设置修理期间费用补偿险的保额和天数
			BusinessIni ini = iniMap.get("cov_731");
			if(ini != null){
				baoJiaModel.setCov_731(ini.getIniValue());
			}else{
				baoJiaModel.setCov_731("0");
			}
			String cov_731 = xmlParams.get("cov_731");
			if(!StringUtil.isEmpty(cov_731)){
				sunshineModifyBaoJiaMockModel.setCov731Value(cov_731);
			}else{
				sunshineModifyBaoJiaMockModel.setCov731Value("0");
			}
			ini = iniMap.get("cov_732");
			if(ini != null){
				baoJiaModel.setCov_732(ini.getIniValue());
			}else{
				baoJiaModel.setCov_732("0");
			}
			String cov_732 = xmlParams.get("cov_732");
			if(StringUtil.isEmpty(cov_732)){
				sunshineModifyBaoJiaMockModel.setCov732Value(cov_732);
			}else{
				sunshineModifyBaoJiaMockModel.setCov732Value("0");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_734]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_733)) {
			sunshineModifyBaoJiaMockModel.setCov733Value(cov_733);
			if ("-1".equals(sunshineBaoJiaModel.getCov_733())) {
				sunshineModifyBaoJiaMockModel.setCov733Data("不可投保:0");
				sunshineBaoJia.setCov_733("-1");
			} else {
				sunshineBaoJia.setCov_733(cov_733);
				if(!cov_733.equals("0")){
					BusinessIni ini = iniMap.get("cov_733");
					if (ini != null) {
						String cov_733Baofei = ini.getIniValue();
						baoJiaModel.setCov_733(cov_733Baofei);
						bizTotalPremium += Double.parseDouble(cov_733Baofei);
					}
				}else{
					baoJiaModel.setCov_733("0");
				}
				sunshineModifyBaoJiaMockModel.setCov733Data("不投保:0;投保:1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_733]请求值为空！");
			return result;
		}
		
		if (!StringUtil.isEmpty(cov_921)) {
			sunshineModifyBaoJiaMockModel.setCov921Value(cov_921);
			if ("-1".equals(sunshineBaoJiaModel.getCov_921())) {
				sunshineModifyBaoJiaMockModel.setCov921Data("不可投保:0");
				sunshineBaoJia.setCov_921("-1");
			} else {
				sunshineBaoJia.setCov_921(cov_921);
				if(!cov_921.equals("0")){
					BusinessIni ini = iniMap.get("cov_921");
					if (ini != null) {
						String cov_921Baofei = ini.getIniValue();
						baoJiaModel.setCov_921(cov_921Baofei);
						bizTotalPremium += Double.parseDouble(cov_921Baofei);
					}
				}else{
					baoJiaModel.setCov_921("0");
				}
				sunshineModifyBaoJiaMockModel.setCov921Data("不投保:0;投保:1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_921]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_922)) {
			sunshineModifyBaoJiaMockModel.setCov922Value(cov_922);
			if ("-1".equals(sunshineBaoJiaModel.getCov_922())) {
				sunshineModifyBaoJiaMockModel.setCov922Data("不可投保:0");
				sunshineBaoJia.setCov_922("-1");
			} else {
				sunshineBaoJia.setCov_922(cov_922);
				if(!cov_922.equals("0")){
					BusinessIni ini = iniMap.get("cov_922");
					if (ini != null) {
						String cov_922Baofei = ini.getIniValue();
						baoJiaModel.setCov_922(cov_922Baofei);
						bizTotalPremium += Double.parseDouble(cov_922Baofei);
					}
				}else{
					baoJiaModel.setCov_922("0");
				}
				sunshineModifyBaoJiaMockModel.setCov922Data("不投保:0;投保:1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_922]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_911)) {
			sunshineModifyBaoJiaMockModel.setCov911Value(cov_911);
			if ("-1".equals(sunshineBaoJiaModel.getCov_911())) {
				sunshineModifyBaoJiaMockModel.setCov911Data("不可投保:0");
				sunshineBaoJia.setCov_911("-1");
			} else {
				sunshineBaoJia.setCov_911(cov_911);
				if(!cov_911.equals("0")){
					BusinessIni ini = iniMap.get("cov_911");
					if (ini != null) {
						String cov_911Baofei = ini.getIniValue();
						baoJiaModel.setCov_911(cov_911Baofei);
						bizTotalPremium += Double.parseDouble(cov_911Baofei);
					}
				}else{
					baoJiaModel.setCov_911("0");
				}
				sunshineModifyBaoJiaMockModel.setCov911Data("不投保:0;投保:1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_911]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_912)) {
			sunshineModifyBaoJiaMockModel.setCov912Value(cov_912);
			if ("-1".equals(sunshineBaoJiaModel.getCov_912())) {
				sunshineModifyBaoJiaMockModel.setCov912Data("不可投保:0");
				sunshineBaoJia.setCov_912("-1");
			} else {
				sunshineBaoJia.setCov_912(cov_912);
				if(!cov_912.equals("0")){
					BusinessIni ini = iniMap.get("cov_912");
					if (ini != null) {
						String cov_912Baofei = ini.getIniValue();
						baoJiaModel.setCov_912(cov_912Baofei);
						bizTotalPremium += Double.parseDouble(cov_912Baofei);
					}
				}else{
					baoJiaModel.setCov_912("0");
				}
				sunshineModifyBaoJiaMockModel.setCov912Data("不投保:0;投保:1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_912]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_928)) {
			sunshineModifyBaoJiaMockModel.setCov928Value(cov_928);
			if ("-1".equals(sunshineBaoJiaModel.getCov_928())) {
				sunshineModifyBaoJiaMockModel.setCov928Data("不可投保:0");
				sunshineBaoJia.setCov_928("-1");
			} else {
				sunshineBaoJia.setCov_928(cov_928);
				if(!cov_928.equals("0")){
					BusinessIni ini = iniMap.get("cov_928");
					if (ini != null) {
						String cov_928Baofei = ini.getIniValue();
						baoJiaModel.setCov_928(cov_928Baofei);
						bizTotalPremium += Double.parseDouble(cov_928Baofei);
					}
				}else{
					baoJiaModel.setCov_928("0");
				}
				sunshineModifyBaoJiaMockModel.setCov928Data("不投保:0;投保:1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_928]请求值为空！");
			return result;
		}

		if (!StringUtil.isEmpty(cov_929)) {
			sunshineModifyBaoJiaMockModel.setCov929Value(cov_929);
			if ("-1".equals(sunshineBaoJiaModel.getCov_929())) {
				sunshineModifyBaoJiaMockModel.setCov929Data("不可投保:0");
				sunshineBaoJia.setCov_929(cov_929);
			} else {
				sunshineBaoJia.setCov_929(cov_929);
				if(!cov_929.equals("0")){
					BusinessIni ini = iniMap.get("cov_929");
					if (ini != null) {
						String cov_929Baofei = ini.getIniValue();
						baoJiaModel.setCov_929(cov_929Baofei);
						bizTotalPremium += Double.parseDouble(cov_929Baofei);
					}
				}else{
					baoJiaModel.setCov_929("0");
				}
				sunshineModifyBaoJiaMockModel.setCov929Data("不投保:0;投保:1");
			}
		} else {
			LogConstant.debugLog.info("阳光保险修改报价接口[cov_929]请求值为空！");
			return result;
		}

		int bizTotalPremiumInt = (int) Math.round(bizTotalPremium);
		sunshineModifyBaoJiaMockModel.setBizTotalPremium(String
				.valueOf(bizTotalPremiumInt));
		int totalPremiumInt = bizTotalPremiumInt + forceTotalPremiumInt;
		sunshineModifyBaoJiaMockModel.setTotalPremium(String.valueOf(totalPremiumInt));
		sunshineModifyBaoJiaMockModel.setStandardPremium("548102");
		// 将商业险总价保存到ini表中
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.SUNSHINE_BIZ_TOTAL_PREMIUM);
		ini.setIniValue(String.valueOf(bizTotalPremiumInt));
		ini.setIniDesc("商业险总保费");
		sunshineBusiConfigServiceImpl.mergeIni(ini);
		// 将网购价保存到ini表中
		ini.setIniName(SessionKey.SUNSHINE_TOTAL_PREMIUM);
		ini.setIniValue(String.valueOf(totalPremiumInt));
		ini.setIniDesc("网购价");
		sunshineBusiConfigServiceImpl.mergeIni(ini);

		sunshineModifyBaoJiaMockModel.setSunshineBaoJiaModel(baoJiaModel);

		// 保存修改后的信息
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("packageType", getPackageType(packageType));
		SunshineBaoJiaModel config = sunshineBaoJiaServiceImpl
				.queryConfig(paramMap);
		sunshineBaoJia.setPackageType(getPackageType(packageType));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp insrStartTime = null;
		Timestamp jqInsrStartTime = null;
		try {
			insrStartTime = new Timestamp(sdf.parse(bizBeginDate).getTime());
			jqInsrStartTime = new Timestamp(sdf.parse(forceBeginDate).getTime());
		} catch (ParseException e) {
			LogConstant.debugLog.info("阳光保险修改报价接口转换商业险交强险起始日期异常：" + e);
		}
		if (insrStartTime != null)
			sunshineBaoJia.setBusiInsrStartTime(insrStartTime);
		if (jqInsrStartTime != null)
			sunshineBaoJia.setJqInsrStartTime(jqInsrStartTime);
		int retCode = 0;
		if (config == null) {
			retCode = sunshineBaoJiaServiceImpl.insertConfig(sunshineBaoJia);
		} else {
			retCode = sunshineBaoJiaServiceImpl.updateConfig(sunshineBaoJia);
		}
		if (retCode == 0) {
			LogConstant.debugLog.info("阳光保险修改报价接口插入修改信息到数据库失败！");
			return result;
		}

		// 生成最终返回数据
		String packageBody = sunshineFeigaiModifyBaoJiaFreemarker
				.generateData2output(
						ContextConstant.PREFIX_SUNSHINE_FEIGAI_MODIFY_BAOJIA,
						sunshineModifyBaoJiaMockModel);
		LogConstant.debugLog.info("阳光保险mock修改报价接口数据报文体：\n" + packageBody);

		ResponsePackageModel responsePackage = new ResponsePackageModel();
		responsePackage.setRequestType("110");
		responsePackage.setInsureType("100");
		responsePackage.setStatus("100");
		Date now = new Date();
		Timestamp stamp = new Timestamp(now.getTime());
		responsePackage.setSendTime(stamp);

		//灾备测试
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.CBZB_YG);
		iniZb = sunshineBusiConfigServiceImpl.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.CBZB_YG_DT);
			iniDelayTime = sunshineBusiConfigServiceImpl.selectIni(iniDelayTime);
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
			responsePackage.setStatus("400");
			responsePackage.setErrorMessage("检索车辆信息错误");
			packageBody="";
		}
		
		
		// 生产签名串 和 验证类
		PartnerSignerImpl signer = new PartnerSignerImpl();
		// 得到私钥
		PrivateKey ygPrivate = KeyPairer
				.getPrivateKey(KeyPairer.PARTNER_PRIVATE_KEY);
		String sign = null;
		try {
			packageBody = WipeTabEnterSpaceUtil.wipe(packageBody);
			responsePackage.setPackageBody(packageBody);
			sign = signer.sign(packageBody.getBytes("GBK"), ygPrivate);
		} catch (UnsupportedEncodingException e) {
			LogConstant.debugLog.info("阳光保险mock修改报价接口签名编码异常：" + e.getMessage());
			e.printStackTrace();
		}
		responsePackage.setSign(sign);
		result = packageFreeMarker.generateData2output(
				ContextConstant.PREFIX_RESPONSE_PACKAGE, responsePackage);
		LogConstant.debugLog.info("阳光保险mock修改报价接口返回数据：\n" + result);

		return result;
	}

	private Map<String, BusinessIni> getAllSunshineIni() {
		Map<String, BusinessIni> map = new HashMap<String, BusinessIni>();
		List<BusinessIni> businessIniList = sunshineBusiConfigServiceImpl
				.queryAllInis();
		if (businessIniList != null) {
			for (BusinessIni ini : businessIniList) {
				map.put(ini.getIniName(), ini);
			}
		}
		return map;
	}

	private Map<String, BusinessConfig> getAllSunshineBusinessConfig() {
		Map<String, BusinessConfig> map = new HashMap<String, BusinessConfig>();
		List<BusinessConfig> businessConfigList = sunshineBusiConfigServiceImpl
				.queryAllBusiConfigs();
		if (businessConfigList != null) {
			for (BusinessConfig config : businessConfigList) {
				map.put(config.getBusinessCode() + config.getBaoE(), config);
			}
		}
		return map;
	}

	private Map<String, List<BusinessConfig>> getAllSunshineBusinessConfigList() {
		Map<String, List<BusinessConfig>> map = new HashMap<String, List<BusinessConfig>>();
		List<BusinessConfig> businessConfigList = sunshineBusiConfigServiceImpl
				.queryAllBusiConfigs();
		if (businessConfigList != null) {
			List<BusinessConfig> cov200List = new ArrayList<BusinessConfig>();
			List<BusinessConfig> cov600List = new ArrayList<BusinessConfig>();
			List<BusinessConfig> cov701List = new ArrayList<BusinessConfig>();
			List<BusinessConfig> cov702List = new ArrayList<BusinessConfig>();
			List<BusinessConfig> cov640List = new ArrayList<BusinessConfig>();
			List<BusinessConfig> cov210List = new ArrayList<BusinessConfig>();
			for (BusinessConfig config : businessConfigList) {
				switch (config.getBusinessCode()) {
				case "cov_200":
					cov200List.add(config);
					break;
				case "cov_600":
					cov600List.add(config);
					break;
				case "cov_701":
					cov701List.add(config);
					break;
				case "cov_702":
					cov702List.add(config);
					break;
				case "cov_640":
					cov640List.add(config);
					break;
				case "cov_210":
					cov210List.add(config);
					break;
				}
			}
			map.put("cov_200", cov200List);
			map.put("cov_600", cov600List);
			map.put("cov_701", cov701List);
			map.put("cov_702", cov702List);
			map.put("cov_640", cov640List);
			map.put("cov_210", cov210List);
		}
		return map;
	}
	
	private String getPackageType(String packageType){
		String type = null;
		switch(packageType){
			case "luxury":
				type = "0";
				break;
			case "affordable":
				type = "1";
				break;
			case "renewal":
				type = "2";
				break;
			case "optional":
				type = "3";
				break;
		}
		return type == null ? "3":type;
	}
}