package com.mockCommon.service.mock.yangguang.impl;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.yangguang.ResponsePackageModel;
import com.mockCommon.model.mock.yangguang.SunshineBaoJiaMockModel;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.yangguang.SunshineBaoJiaModel;
import com.mockCommon.service.mock.yangguang.SunshineBaoJiaMockService;
import com.mockCommon.service.web.yangguang.SunshineBaoJiaService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.service.web.yangguang.SunshineIniService;
import com.mockCommon.util.CacheUtil;
import com.mockCommon.util.ParseXmlUtil;
import com.mockCommon.util.WipeTabEnterSpaceUtil;
import com.mockCommon.util.freeMarker.IDataMaker;
import com.mockCommon.util.yangguang.KeyPairer;
import com.mockCommon.util.yangguang.PartnerSignerImpl;

@Service("sunshineBaoJiaMockServiceImpl")
public class SunshineBaoJiaMockServiceImpl implements SunshineBaoJiaMockService {

	@Autowired
	private SunshineBaoJiaService sunshineBaoJiaServiceImpl;

	@Autowired
	private SunshineIniService sunshineIniServiceImpl;
	
	@Autowired
	private SunshineBusiConfigService sunshineBusiConfigServiceImpl;

	@Autowired
	private IDataMaker<ResponsePackageModel> packageFreeMarker;

	@Autowired
	private IDataMaker<SunshineBaoJiaMockModel> sunshineBaoJiaFreeMarker;

	@Override
	public String baoJia(String xmlParams) {
		String result = null;

		String xPath = "/PackageList/Package/Request/InputsList/Inputs/Input";
		Map<String, String> params = ParseXmlUtil.parseXmlNodes(xmlParams,
				xPath);
		if (params == null) {
			LogConstant.debugLog.info("解析xml后的参数map为空！");
			return result;
		}
		LogConstant.debugLog.info("阳光保险请求参数为：\n" + params);
		String ownerIdNo = params.get("ownerIdNo");
		String ownerName = params.get("ownerName");
		String vehicleId = params.get("vehicleId");
		String vehicleFrameNo = params.get("vehicleFrameNo");
		String engineNo = params.get("engineNo");
		String firstRegisterDate = params.get("firstRegisterDate");
		String packageType = params.get("packageType");
		if (packageType != null) {
			CacheUtil.put(SessionKey.SUNSHINE_PACKAGETYPE_BEFORE_SAVE_BAOJIA,
					packageType);
		}

		SunshineBaoJiaMockModel sunshineBaoJiaMockModel = new SunshineBaoJiaMockModel();
		// 设置从请求中获得的参数
		sunshineBaoJiaMockModel.setOwnerIdNo(ownerIdNo);
		sunshineBaoJiaMockModel.setOwnerName(ownerName);
		sunshineBaoJiaMockModel.setVehicleId(vehicleId);
		sunshineBaoJiaMockModel.setVehicleFrameNo(vehicleFrameNo);
		sunshineBaoJiaMockModel.setEngineNo(engineNo);
		sunshineBaoJiaMockModel.setFirstRegisterDate(firstRegisterDate);
		sunshineBaoJiaMockModel.setPackageType(packageType);

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
			sunshineBaoJiaMockModel.setBizBeginDate(sdf
					.format(sunshineBaoJiaModel.getBusiInsrStartTime()));
			sunshineBaoJiaMockModel.setForceBeginDate(sdf
					.format(sunshineBaoJiaModel.getJqInsrStartTime()));

			if (sunshineBaoJiaModel.getCov_200() != null) {
				if (!sunshineBaoJiaModel.getCov_200().equals("-1")) { // 勾选了该险种
					BusinessConfig config = configMap.get("cov_200"
							+ sunshineBaoJiaModel.getCov_200());
					if (config != null) {
						String cov_200 = config.getBaoFei();
						baoJiaModel.setCov_200(cov_200);
						sunshineBaoJiaMockModel
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
						sunshineBaoJiaMockModel.setCov200Data(cov200Data);
						bizTotalPremium += Double.parseDouble(cov_200);
					}
				} else {
					sunshineBaoJiaMockModel.setCov200Value("0");
					sunshineBaoJiaMockModel.setCov200Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_600() != null) {
				if (!sunshineBaoJiaModel.getCov_600().equals("-1")) {
					BusinessConfig config = configMap.get("cov_600"
							+ sunshineBaoJiaModel.getCov_600());
					if (config != null) {
						String cov_600 = config.getBaoFei();
						baoJiaModel.setCov_600(cov_600);
						sunshineBaoJiaMockModel
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
						sunshineBaoJiaMockModel.setCov600Data(cov600Data);
						bizTotalPremium += Double.parseDouble(cov_600);
					}
				} else {
					sunshineBaoJiaMockModel.setCov600Value("0");
					sunshineBaoJiaMockModel.setCov600Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov500Value("60300.00");
					sunshineBaoJiaMockModel
							.setCov500Data("不投保:0.00;投保:60300.00");
				} else if (sunshineBaoJiaModel.getCov_500().equals("0")) {
					baoJiaModel.setCov_500("0");
					sunshineBaoJiaMockModel.setCov500Value("0.00");
					sunshineBaoJiaMockModel
							.setCov500Data("不投保:0.00;投保:60300.00");
				} else {
					sunshineBaoJiaMockModel.setCov500Value("0");
					sunshineBaoJiaMockModel.setCov500Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_701() != null) {
				if (!sunshineBaoJiaModel.getCov_701().equals("-1")) {
					BusinessConfig config = configMap.get("cov_701"
							+ sunshineBaoJiaModel.getCov_701());
					if (config != null) {
						String cov_701 = config.getBaoFei();
						baoJiaModel.setCov_701(cov_701);
						sunshineBaoJiaMockModel
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
						sunshineBaoJiaMockModel.setCov701Data(cov701Data);
						bizTotalPremium += Double.parseDouble(cov_701);
					}
				} else {
					sunshineBaoJiaMockModel.setCov701Value("0");
					sunshineBaoJiaMockModel.setCov701Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_702() != null) {
				if (!sunshineBaoJiaModel.getCov_702().equals("-1")) {
					BusinessConfig config = configMap.get("cov_702"
							+ sunshineBaoJiaModel.getCov_702());
					if (config != null) {
						String cov_702 = config.getBaoFei();
						baoJiaModel.setCov_702(cov_702);
						sunshineBaoJiaMockModel
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
						sunshineBaoJiaMockModel.setCov702Data(cov702Data);
						bizTotalPremium += Double.parseDouble(cov_702);
					}
				} else {
					sunshineBaoJiaMockModel.setCov702Value("0");
					sunshineBaoJiaMockModel.setCov702Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov321Value("10.00");
					sunshineBaoJiaMockModel.setCov321Data("不投保:0.00;投保:10.00");
				} else if (sunshineBaoJiaModel.getCov_321().equals("0")) {
					baoJiaModel.setCov_321("0");
					sunshineBaoJiaMockModel.setCov321Value("0.00");
					sunshineBaoJiaMockModel.setCov321Data("不投保:0.00;投保:10.00");
				} else {
					sunshineBaoJiaMockModel.setCov321Value("0");
					sunshineBaoJiaMockModel.setCov321Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov310Value("60300.00");
					sunshineBaoJiaMockModel
							.setCov310Data("不投保:0.00;投保:60300.00");
				} else if (sunshineBaoJiaModel.getCov_310().equals("0")) {
					baoJiaModel.setCov_310("0");
					sunshineBaoJiaMockModel.setCov310Value("0.00");
					sunshineBaoJiaMockModel
							.setCov310Data("不投保:0.00;投保:60300.00");
				} else {
					sunshineBaoJiaMockModel.setCov310Value("0");
					sunshineBaoJiaMockModel.setCov310Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_231() != null) {
				String cov_231 = null;
				if (sunshineBaoJiaModel.getCov_231().equals("1")) {
					BusinessIni ini = iniMap.get("gcCov_231");
					if (ini != null) {
						cov_231 = ini.getIniValue();
					}
					sunshineBaoJiaMockModel.setCov231Value("1");
					sunshineBaoJiaMockModel
							.setCov231Data("不投保:0;国产玻璃:1;进口玻璃:2");
				} else if (sunshineBaoJiaModel.getCov_231().equals("2")) {
					BusinessIni ini = iniMap.get("jkCov_231");
					if (ini != null) {
						cov_231 = ini.getIniValue();
					}
					sunshineBaoJiaMockModel.setCov231Value("2");
					sunshineBaoJiaMockModel
							.setCov231Data("不投保:0;国产玻璃:1;进口玻璃:2");
				} else if (sunshineBaoJiaModel.getCov_231().equals("0")) {
					cov_231 = "0";
					sunshineBaoJiaMockModel.setCov231Value("0");
					sunshineBaoJiaMockModel
							.setCov231Data("不投保:0;国产玻璃:1;进口玻璃:2");
				} else {
					sunshineBaoJiaMockModel.setCov231Value("0");
					sunshineBaoJiaMockModel.setCov231Data("不可投保:0");
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
						sunshineBaoJiaMockModel
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
						sunshineBaoJiaMockModel.setCov210Data(cov210Data);
						bizTotalPremium += Double.parseDouble(cov_210);
					}
				} else {
					sunshineBaoJiaMockModel.setCov210Value("0");
					sunshineBaoJiaMockModel.setCov210Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov390Value("1");
					sunshineBaoJiaMockModel.setCov390Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_390().equals("0")) {
					baoJiaModel.setCov_390("0");
					sunshineBaoJiaMockModel.setCov390Value("0");
					sunshineBaoJiaMockModel.setCov390Data("不投保:0;投保:1");
				} else {
					sunshineBaoJiaMockModel.setCov390Value("0");
					sunshineBaoJiaMockModel.setCov390Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov291Value("1");
					sunshineBaoJiaMockModel.setCov291Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_291().equals("0")) {
					baoJiaModel.setCov_291("0");
					sunshineBaoJiaMockModel.setCov291Value("0");
					sunshineBaoJiaMockModel.setCov291Data("不投保:0;投保:1");
				} else {
					sunshineBaoJiaMockModel.setCov291Value("0");
					sunshineBaoJiaMockModel.setCov291Data("不可投保:0");
				}
			}
			if (sunshineBaoJiaModel.getCov_640() != null) {
				if (!sunshineBaoJiaModel.getCov_640().equals("-1")) {
					BusinessConfig config = configMap.get("cov_640"
							+ sunshineBaoJiaModel.getCov_640());
					if (config != null) {
						String cov_640 = config.getBaoFei();
						baoJiaModel.setCov_640(cov_640);
						sunshineBaoJiaMockModel
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
						sunshineBaoJiaMockModel.setCov640Data(cov640Data);
						bizTotalPremium += Double.parseDouble(cov_640);
					}
				} else {
					sunshineBaoJiaMockModel.setCov640Value("0");
					sunshineBaoJiaMockModel.setCov640Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov921Value("1");
					sunshineBaoJiaMockModel.setCov921Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_921().equals("0")) {
					baoJiaModel.setCov_921("0");
					sunshineBaoJiaMockModel.setCov921Value("0");
					sunshineBaoJiaMockModel.setCov921Data("不投保:0;投保:1");
				} else {
					sunshineBaoJiaMockModel.setCov921Value("0");
					sunshineBaoJiaMockModel.setCov921Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov922Value("1");
					sunshineBaoJiaMockModel.setCov922Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_922().equals("0")) {
					baoJiaModel.setCov_922("0");
					sunshineBaoJiaMockModel.setCov922Value("0");
					sunshineBaoJiaMockModel.setCov922Data("不投保:0;投保:1");
				} else {
					sunshineBaoJiaMockModel.setCov922Value("0");
					sunshineBaoJiaMockModel.setCov922Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov911Value("1");
					sunshineBaoJiaMockModel.setCov911Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_911().equals("0")) {
					baoJiaModel.setCov_911("0");
					sunshineBaoJiaMockModel.setCov911Value("0");
					sunshineBaoJiaMockModel.setCov911Data("不投保:0;投保:1");
				} else {
					sunshineBaoJiaMockModel.setCov911Value("0");
					sunshineBaoJiaMockModel.setCov911Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov912Value("1");
					sunshineBaoJiaMockModel.setCov912Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_912().equals("0")) {
					baoJiaModel.setCov_912("0");
					sunshineBaoJiaMockModel.setCov912Value("0");
					sunshineBaoJiaMockModel.setCov912Data("不投保:0;投保:1");
				} else {
					sunshineBaoJiaMockModel.setCov912Value("0");
					sunshineBaoJiaMockModel.setCov912Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov928Value("1");
					sunshineBaoJiaMockModel.setCov928Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_928().equals("0")) {
					baoJiaModel.setCov_928("0");
					sunshineBaoJiaMockModel.setCov928Value("0");
					sunshineBaoJiaMockModel.setCov928Data("不投保:0;投保:1");
				} else {
					sunshineBaoJiaMockModel.setCov928Value("0");
					sunshineBaoJiaMockModel.setCov928Data("不可投保:0");
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
					sunshineBaoJiaMockModel.setCov929Value("1");
					sunshineBaoJiaMockModel.setCov929Data("不投保:0;投保:1");
				} else if (sunshineBaoJiaModel.getCov_929().equals("0")) {
					baoJiaModel.setCov_929("0");
					sunshineBaoJiaMockModel.setCov929Value("0");
					sunshineBaoJiaMockModel.setCov929Data("不投保:0;投保:1");
				} else {
					sunshineBaoJiaMockModel.setCov929Value("0");
					sunshineBaoJiaMockModel.setCov929Data("不可投保:0");
				}
			}

			// 设置交强险、车船税
			BusinessIni forceInsr = iniMap.get(SessionKey.SUNSHINE_JQXBJ_JQXJG);
			double force = 0;
			double vehicleTax = 0;
			if (forceInsr != null) {
				String forcePremium = forceInsr.getIniValue();
				sunshineBaoJiaMockModel.setForceFlag("1");
				sunshineBaoJiaMockModel.setForcePremium(forcePremium);
				force = Double.parseDouble(forcePremium);
			} else {
				sunshineBaoJiaMockModel.setForceFlag("0");
				sunshineBaoJiaMockModel.setForcePremium("0");
			}
			BusinessIni ccsInsr = iniMap.get(SessionKey.SUNSHINE_JQXBJ_CCSJG);
			if (ccsInsr != null) {
				String vehicleTaxPremium = ccsInsr.getIniValue();
				sunshineBaoJiaMockModel.setVehicleTaxPremium(vehicleTaxPremium);
				vehicleTax = Double.parseDouble(vehicleTaxPremium);
			}
			forceTotalPremiumInt = (int) Math.round(force + vehicleTax);
			String forceTotalPremium = String.valueOf(forceTotalPremiumInt);
			sunshineBaoJiaMockModel.setForceTotalPremium(forceTotalPremium);

		}
		int bizTotalPremiumInt = (int) Math.round(bizTotalPremium);
		sunshineBaoJiaMockModel.setBizTotalPremium(String
				.valueOf(bizTotalPremiumInt));
		int totalPremiumInt = bizTotalPremiumInt + forceTotalPremiumInt;
		sunshineBaoJiaMockModel.setTotalPremium(String.valueOf(totalPremiumInt));
		sunshineBaoJiaMockModel.setStandardPremium("548102");
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
		sunshineBaoJiaMockModel.setSunshineBaoJiaModel(baoJiaModel);

		// 生成最终返回数据
		String packageBody = sunshineBaoJiaFreeMarker
				.generateData2output(ContextConstant.PREFIX_SUNSHINE_BAOJIA,
						sunshineBaoJiaMockModel);
		LogConstant.debugLog.info("阳光保险mock报价接口数据报文体：\n" + packageBody);

		ResponsePackageModel responsePackage = new ResponsePackageModel();
		responsePackage.setRequestType("105");
		responsePackage.setInsureType("100");
		responsePackage.setStatus("100");
		Date now = new Date();
		Timestamp stamp = new Timestamp(now.getTime());
		responsePackage.setSendTime(stamp);
		
		//灾备测试
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.GBZB_YG);
		iniZb = sunshineIniServiceImpl.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.GBZB_YG_DT);
			iniDelayTime = sunshineIniServiceImpl.selectIni(iniDelayTime);
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
			responsePackage.setErrorMessage("报价错误");
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
			LogConstant.debugLog.info("阳光保险mock报价接口签名编码异常：" + e.getMessage());
			e.printStackTrace();
		}
		responsePackage.setSign(sign);
		result = packageFreeMarker.generateData2output(
				ContextConstant.PREFIX_RESPONSE_PACKAGE, responsePackage);
		LogConstant.debugLog.info("阳光保险mock报价接口返回数据：\n" + result);

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

	// 将从数据库中取出的保费转换成按分为单位返回
	public String dateParse(String baofei) {
		String res = null;
		try {
			double fee = Double.parseDouble(baofei);
			res = String.valueOf((int) Math.round(fee * 100));
		} catch (NumberFormatException e) {
			LogConstant.debugLog.info("阳光保险mock报价接口保费转换异常");
			e.printStackTrace();
		}
		return res;
	}
}