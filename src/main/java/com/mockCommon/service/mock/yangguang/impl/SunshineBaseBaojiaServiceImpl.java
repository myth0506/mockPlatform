package com.mockCommon.service.mock.yangguang.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.yangguang.SunshineBaseBaoJiaMockModel;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.yangguang.SunshineBaoJiaModel;
import com.mockCommon.service.mock.yangguang.SunshineBaseBaojiaService;
import com.mockCommon.service.web.yangguang.SunshineBaoJiaService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.util.CacheUtil;
import com.mockCommon.util.freeMarker.IDataMaker;

@Service("sunshineXubaoBaojiaServiceImpl")
public class SunshineBaseBaojiaServiceImpl implements
		SunshineBaseBaojiaService {

	@Autowired
	private SunshineBaoJiaService sunshineBaoJiaServiceImpl;

	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigServiceImpl;

	@Autowired
	private IDataMaker<SunshineBaseBaoJiaMockModel> sunshineBaseBaoJiaFreeMarker;
	
	@Override
	public String baseBaojia(String type) {
		String result = null;
		String packageType = type;
		if (packageType != null) {
			CacheUtil.put(SessionKey.SUNSHINE_PACKAGETYPE_BEFORE_SAVE_BAOJIA,
					packageType);
		}
		
		SunshineBaseBaoJiaMockModel sunshineBaseBaoJiaMockModel = new SunshineBaseBaoJiaMockModel();
		sunshineBaseBaoJiaMockModel.setPackageType(packageType);
		
		// 设置从数据库中取出的数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("packageName", packageType);
		SunshineBaoJiaModel sunshineBaoJiaModel = sunshineBaoJiaServiceImpl
				.queryConfig(map);
		SunshineBaoJiaModel baoJiaModel = new SunshineBaoJiaModel(); // 返回的mock数据中SunshineBaoJiaModel数据
		double bizTotalPremium = 0; // 商业险总价
		int forceTotalPremiumInt = 0; // 网购价
		if (sunshineBaoJiaModel != null) {
			Map<String, BusinessIni> iniMap = getAllSunshineIni();
			Map<String, BusinessConfig> configMap = getAllSunshineBusinessConfig();
			Map<String, List<BusinessConfig>> configListMap = getAllSunshineBusinessConfigList();

			// 设置商业险和交强险起保日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sunshineBaseBaoJiaMockModel.setBizBeginDate(sdf
					.format(sunshineBaoJiaModel.getBusiInsrStartTime()));
			sunshineBaseBaoJiaMockModel.setForceBeginDate(sdf
					.format(sunshineBaoJiaModel.getJqInsrStartTime()));

			if (sunshineBaoJiaModel.getCov_200() != null) {
				if (!sunshineBaoJiaModel.getCov_200().equals("-1")) { // 勾选了该险种
					BusinessConfig config = configMap.get("cov_200"
							+ sunshineBaoJiaModel.getCov_200());
					if (config != null) {
						String cov_200 = config.getBaoFei();
						baoJiaModel.setCov_200(cov_200);
						sunshineBaseBaoJiaMockModel
								.setCov200Value(config.getBaoE());
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
						sunshineBaseBaoJiaMockModel.setCov200Data(cov200Data);
						bizTotalPremium += Double.parseDouble(cov_200);
					}
				} else {
					sunshineBaseBaoJiaMockModel.setCov200Value("0");
					sunshineBaseBaoJiaMockModel.setCov200Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_600() != null) {
				if (!sunshineBaoJiaModel.getCov_600().equals("-1")) {
					BusinessConfig config = configMap.get("cov_600"
							+ sunshineBaoJiaModel.getCov_600());
					if (config != null) {
						String cov_600 = config.getBaoFei();
						baoJiaModel.setCov_600(cov_600);
						sunshineBaseBaoJiaMockModel
								.setCov600Value(config.getBaoE());
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
						sunshineBaseBaoJiaMockModel.setCov600Data(cov600Data);
						bizTotalPremium += Double.parseDouble(cov_600);
					}
				} else {
					sunshineBaseBaoJiaMockModel.setCov600Value("0");
					sunshineBaseBaoJiaMockModel.setCov600Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_500() != null) {
				if (sunshineBaoJiaModel.getCov_500().equals("1")) {
					BusinessIni ini = iniMap.get("cov_500");
					if (ini != null) {
						String cov_500 = ini.getIniValue();
						baoJiaModel.setCov_500(cov_500);
						bizTotalPremium += Double.parseDouble(cov_500);
					}
					sunshineBaseBaoJiaMockModel.setCov500Value("60300.00");
					sunshineBaseBaoJiaMockModel
							.setCov500Data("不投保:0.00;投保:60300.00");
				} else if (sunshineBaoJiaModel.getCov_500().equals("0")) {
					baoJiaModel.setCov_500("0");
					sunshineBaseBaoJiaMockModel.setCov500Value("0.00");
					sunshineBaseBaoJiaMockModel
							.setCov500Data("不投保:0.00;投保:60300.00");
				} else {
					sunshineBaseBaoJiaMockModel.setCov500Value("0");
					sunshineBaseBaoJiaMockModel.setCov500Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_701() != null) {
				if (!sunshineBaoJiaModel.getCov_701().equals("-1")) {
					BusinessConfig config = configMap.get("cov_701"
							+ sunshineBaoJiaModel.getCov_701());
					if (config != null) {
						String cov_701 = config.getBaoFei();
						baoJiaModel.setCov_701(cov_701);
						sunshineBaseBaoJiaMockModel
								.setCov701Value(config.getBaoE());
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
						sunshineBaseBaoJiaMockModel.setCov701Data(cov701Data);
						bizTotalPremium += Double.parseDouble(cov_701);
					}
				} else {
					sunshineBaseBaoJiaMockModel.setCov701Value("0");
					sunshineBaseBaoJiaMockModel.setCov701Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_702() != null) {
				if (!sunshineBaoJiaModel.getCov_702().equals("-1")) {
					BusinessConfig config = configMap.get("cov_702"
							+ sunshineBaoJiaModel.getCov_702());
					if (config != null) {
						String cov_702 = config.getBaoFei();
						baoJiaModel.setCov_702(cov_702);
						sunshineBaseBaoJiaMockModel
								.setCov702Value(config.getBaoE());
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
						sunshineBaseBaoJiaMockModel.setCov702Data(cov702Data);
						bizTotalPremium += Double.parseDouble(cov_702);
					}
				} else {
					sunshineBaseBaoJiaMockModel.setCov702Value("0");
					sunshineBaseBaoJiaMockModel.setCov702Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_321() != null) {
				if (sunshineBaoJiaModel.getCov_321().equals("1")) {
					BusinessIni ini = iniMap.get("cov_321");
					if (ini != null) {
						String cov_321 = ini.getIniValue();
						baoJiaModel.setCov_321(cov_321);
						bizTotalPremium += Double.parseDouble(cov_321);
					}
					sunshineBaseBaoJiaMockModel.setCov321Value("10.00");
					sunshineBaseBaoJiaMockModel.setCov321Data("不投保:0.00;投保:10.00");
				} else if (sunshineBaoJiaModel.getCov_321().equals("0")) {
					baoJiaModel.setCov_321("0");
					sunshineBaseBaoJiaMockModel.setCov321Value("0.00");
					sunshineBaseBaoJiaMockModel.setCov321Data("不投保:0.00;投保:10.00");
				} else {
					sunshineBaseBaoJiaMockModel.setCov321Value("0");
					sunshineBaseBaoJiaMockModel.setCov321Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_310() != null) {
				if (sunshineBaoJiaModel.getCov_310().equals("1")) {
					BusinessIni ini = iniMap.get("cov_310");
					if (ini != null) {
						String cov_310 = ini.getIniValue();
						baoJiaModel.setCov_310(cov_310);
						bizTotalPremium += Double.parseDouble(cov_310);
					}
					sunshineBaseBaoJiaMockModel.setCov310Value("60300.00");
					sunshineBaseBaoJiaMockModel
							.setCov310Data("不投保:0.00;投保:60300.00");
				} else if (sunshineBaoJiaModel.getCov_310().equals("0")) {
					baoJiaModel.setCov_310("0");
					sunshineBaseBaoJiaMockModel.setCov310Value("0.00");
					sunshineBaseBaoJiaMockModel
							.setCov310Data("不投保:0.00;投保:60300.00");
				} else {
					sunshineBaseBaoJiaMockModel.setCov310Value("0");
					sunshineBaseBaoJiaMockModel.setCov310Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_231() != null) {
				String cov_231 = null;
				if (sunshineBaoJiaModel.getCov_231().equals("1")) {
					BusinessIni ini = iniMap.get("gcCov_231");
					if (ini != null) {
						cov_231 = ini.getIniValue();
					}
					sunshineBaseBaoJiaMockModel.setCov231Value("1");
					sunshineBaseBaoJiaMockModel
							.setCov231Data("不投保:0;国产玻璃:1;进口玻璃:2");
				} else if (sunshineBaoJiaModel.getCov_231().equals("2")) {
					BusinessIni ini = iniMap.get("jkCov_231");
					if (ini != null) {
						cov_231 = ini.getIniValue();
					}
					sunshineBaseBaoJiaMockModel.setCov231Value("2");
					sunshineBaseBaoJiaMockModel
							.setCov231Data("不投保:0;国产玻璃:1;进口玻璃:2");
				} else if (sunshineBaoJiaModel.getCov_231().equals("0")) {
					cov_231 = "0";
					sunshineBaseBaoJiaMockModel.setCov231Value("0");
					sunshineBaseBaoJiaMockModel
							.setCov231Data("不投保:0;国产玻璃:1;进口玻璃:2");
				} else {
					sunshineBaseBaoJiaMockModel.setCov231Value("0");
					sunshineBaseBaoJiaMockModel.setCov231Data("不可投保:0");
				}
				if (cov_231 != null) {
					baoJiaModel.setCov_231(cov_231);
					bizTotalPremium += Double.parseDouble(cov_231);
				}
			}
			if (sunshineBaoJiaModel.getCov_210() != null) {
				if (!sunshineBaoJiaModel.getCov_210().equals("-1")) {
					BusinessConfig config = configMap.get("cov_210"
							+ sunshineBaoJiaModel.getCov_210());
					if (config != null) {
						String cov_210 = config.getBaoFei();
						baoJiaModel.setCov_210(cov_210);
						sunshineBaseBaoJiaMockModel
								.setCov210Value(config.getBaoE());
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
						sunshineBaseBaoJiaMockModel.setCov210Data(cov210Data);
						bizTotalPremium += Double.parseDouble(cov_210);
					}
				} else {
					sunshineBaseBaoJiaMockModel.setCov210Value("0");
					sunshineBaseBaoJiaMockModel.setCov210Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_390() != null) {
				if (sunshineBaoJiaModel.getCov_390().equals("1")) {
					BusinessIni ini = iniMap.get("cov_390");
					if (ini != null) {
						String cov_390 = ini.getIniValue();
						baoJiaModel.setCov_390(cov_390);
						bizTotalPremium += Double.parseDouble(cov_390);
					}
					sunshineBaseBaoJiaMockModel.setCov390Value("1");
					sunshineBaseBaoJiaMockModel.setCov390Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_390().equals("0")) {
					baoJiaModel.setCov_390("0");
					sunshineBaseBaoJiaMockModel.setCov390Value("0");
					sunshineBaseBaoJiaMockModel.setCov390Data("不投保:0;投保:1");
				} else {
					sunshineBaseBaoJiaMockModel.setCov390Value("0");
					sunshineBaseBaoJiaMockModel.setCov390Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_291() != null) {
				if (sunshineBaoJiaModel.getCov_291().equals("1")) {
					BusinessIni ini = iniMap.get("cov_291");
					if (ini != null) {
						String cov_291 = ini.getIniValue();
						baoJiaModel.setCov_291(cov_291);
						bizTotalPremium += Double.parseDouble(cov_291);
					}
					sunshineBaseBaoJiaMockModel.setCov291Value("1");
					sunshineBaseBaoJiaMockModel.setCov291Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_291().equals("0")) {
					baoJiaModel.setCov_291("0");
					sunshineBaseBaoJiaMockModel.setCov291Value("0");
					sunshineBaseBaoJiaMockModel.setCov291Data("不投保:0;投保:1");
				} else {
					sunshineBaseBaoJiaMockModel.setCov291Value("0");
					sunshineBaseBaoJiaMockModel.setCov291Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_640() != null) {
				if (!sunshineBaoJiaModel.getCov_640().equals("-1")) {
					BusinessConfig config = configMap.get("cov_640"
							+ sunshineBaoJiaModel.getCov_640());
					if (config != null) {
						String cov_640 = config.getBaoFei();
						baoJiaModel.setCov_640(cov_640);
						sunshineBaseBaoJiaMockModel
								.setCov640Value(config.getBaoE());
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
						sunshineBaseBaoJiaMockModel.setCov640Data(cov640Data);
						bizTotalPremium += Double.parseDouble(cov_640);
					}
				} else {
					sunshineBaseBaoJiaMockModel.setCov640Value("0");
					sunshineBaseBaoJiaMockModel.setCov640Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_921() != null) {
				if (sunshineBaoJiaModel.getCov_921().equals("1")) {
					BusinessIni ini = iniMap.get("cov_921");
					if (ini != null) {
						String cov_921 = ini.getIniValue();
						baoJiaModel.setCov_921(cov_921);
						bizTotalPremium += Double.parseDouble(cov_921);
					}
					sunshineBaseBaoJiaMockModel.setCov921Value("1");
					sunshineBaseBaoJiaMockModel.setCov921Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_921().equals("0")) {
					baoJiaModel.setCov_921("0");
					sunshineBaseBaoJiaMockModel.setCov921Value("0");
					sunshineBaseBaoJiaMockModel.setCov921Data("不投保:0;投保:1");
				} else {
					sunshineBaseBaoJiaMockModel.setCov921Value("0");
					sunshineBaseBaoJiaMockModel.setCov921Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_922() != null) {
				if (sunshineBaoJiaModel.getCov_922().equals("1")) {
					BusinessIni ini = iniMap.get("cov_922");
					if (ini != null) {
						String cov_922 = ini.getIniValue();
						baoJiaModel.setCov_922(cov_922);
						bizTotalPremium += Double.parseDouble(cov_922);
					}
					sunshineBaseBaoJiaMockModel.setCov922Value("1");
					sunshineBaseBaoJiaMockModel.setCov922Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_922().equals("0")) {
					baoJiaModel.setCov_922("0");
					sunshineBaseBaoJiaMockModel.setCov922Value("0");
					sunshineBaseBaoJiaMockModel.setCov922Data("不投保:0;投保:1");
				} else {
					sunshineBaseBaoJiaMockModel.setCov922Value("0");
					sunshineBaseBaoJiaMockModel.setCov922Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_911() != null) {
				if (sunshineBaoJiaModel.getCov_911().equals("1")) {
					BusinessIni ini = iniMap.get("cov_911");
					if (ini != null) {
						String cov_911 = ini.getIniValue();
						baoJiaModel.setCov_911(cov_911);
						bizTotalPremium += Double.parseDouble(cov_911);
					}
					sunshineBaseBaoJiaMockModel.setCov911Value("1");
					sunshineBaseBaoJiaMockModel.setCov911Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_911().equals("0")) {
					baoJiaModel.setCov_911("0");
					sunshineBaseBaoJiaMockModel.setCov911Value("0");
					sunshineBaseBaoJiaMockModel.setCov911Data("不投保:0;投保:1");
				} else {
					sunshineBaseBaoJiaMockModel.setCov911Value("0");
					sunshineBaseBaoJiaMockModel.setCov911Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_912() != null) {
				if (sunshineBaoJiaModel.getCov_912().equals("1")) {
					BusinessIni ini = iniMap.get("cov_912");
					if (ini != null) {
						String cov_912 = ini.getIniValue();
						baoJiaModel.setCov_912(cov_912);
						bizTotalPremium += Double.parseDouble(cov_912);
					}
					sunshineBaseBaoJiaMockModel.setCov912Value("1");
					sunshineBaseBaoJiaMockModel.setCov912Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_912().equals("0")) {
					baoJiaModel.setCov_912("0");
					sunshineBaseBaoJiaMockModel.setCov912Value("0");
					sunshineBaseBaoJiaMockModel.setCov912Data("不投保:0;投保:1");
				} else {
					sunshineBaseBaoJiaMockModel.setCov912Value("0");
					sunshineBaseBaoJiaMockModel.setCov912Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_928() != null) {
				if (sunshineBaoJiaModel.getCov_928().equals("1")) {
					BusinessIni ini = iniMap.get("cov_928");
					if (ini != null) {
						String cov_928 = ini.getIniValue();
						baoJiaModel.setCov_928(cov_928);
						bizTotalPremium += Double.parseDouble(cov_928);
					}
					sunshineBaseBaoJiaMockModel.setCov928Value("1");
					sunshineBaseBaoJiaMockModel.setCov928Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_928().equals("0")) {
					baoJiaModel.setCov_928("0");
					sunshineBaseBaoJiaMockModel.setCov928Value("0");
					sunshineBaseBaoJiaMockModel.setCov928Data("不投保:0;投保:1");
				} else {
					sunshineBaseBaoJiaMockModel.setCov928Value("0");
					sunshineBaseBaoJiaMockModel.setCov928Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_929() != null) {
				if (sunshineBaoJiaModel.getCov_929().equals("1")) {
					BusinessIni ini = iniMap.get("cov_929");
					if (ini != null) {
						String cov_929 = ini.getIniValue();
						baoJiaModel.setCov_929(cov_929);
						bizTotalPremium += Double.parseDouble(cov_929);
					}
					sunshineBaseBaoJiaMockModel.setCov929Value("1");
					sunshineBaseBaoJiaMockModel.setCov929Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_929().equals("0")) {
					baoJiaModel.setCov_929("0");
					sunshineBaseBaoJiaMockModel.setCov929Value("0");
					sunshineBaseBaoJiaMockModel.setCov929Data("不投保:0;投保:1");
				} else {
					sunshineBaseBaoJiaMockModel.setCov929Value("0");
					sunshineBaseBaoJiaMockModel.setCov929Data("不可投保:0");
				}
			}

			// 设置交强险、车船税
			BusinessIni forceInsr = iniMap.get(SessionKey.SUNSHINE_JQXBJ_JQXJG);
			double force = 0;
			double vehicleTax = 0;
			if (forceInsr != null) {
				String forcePremium = forceInsr.getIniValue();
				sunshineBaseBaoJiaMockModel.setForceFlag("1");
				sunshineBaseBaoJiaMockModel.setForcePremium(forcePremium);
				force = Double.parseDouble(forcePremium);
			} else {
				sunshineBaseBaoJiaMockModel.setForceFlag("0");
				sunshineBaseBaoJiaMockModel.setForcePremium("0");
			}
			BusinessIni ccsInsr = iniMap.get(SessionKey.SUNSHINE_JQXBJ_CCSJG);
			if (ccsInsr != null) {
				String vehicleTaxPremium = ccsInsr.getIniValue();
				sunshineBaseBaoJiaMockModel.setVehicleTaxPremium(vehicleTaxPremium);
				vehicleTax = Double.parseDouble(vehicleTaxPremium);
			}
			forceTotalPremiumInt = (int) Math.round(force + vehicleTax);
			String forceTotalPremium = String.valueOf(forceTotalPremiumInt);
			sunshineBaseBaoJiaMockModel.setForceTotalPremium(forceTotalPremium);

		}
		int bizTotalPremiumInt = (int) Math.round(bizTotalPremium);
		sunshineBaseBaoJiaMockModel.setBizTotalPremium(String
				.valueOf(bizTotalPremiumInt));
		int totalPremiumInt = bizTotalPremiumInt + forceTotalPremiumInt;
		sunshineBaseBaoJiaMockModel.setTotalPremium(String.valueOf(totalPremiumInt));
		sunshineBaseBaoJiaMockModel.setStandardPremium("548102");
		
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
		sunshineBaseBaoJiaMockModel.setSunshineBaoJiaModel(baoJiaModel);

		// 生成返回数据
		result = sunshineBaseBaoJiaFreeMarker
				.generateData2output(ContextConstant.PREFIX_SUNSHINE_BASE_BAOJIA,
						sunshineBaseBaoJiaMockModel);
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
}