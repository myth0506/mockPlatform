package com.mockCommon.constant;

/**
 * @author li_zhe
 * 此类可用于定义内存cache中的key值,通过CacheUtil存入缓存。
 */
public class SessionKey {
	
	public static final String XBSR_IS_XBYH = "isXbyh";
	
	public static final String XBSR_BIRTHDAY = "birthday";

	public static final String SEARCH_CAR_INFO_VEHICLE_NO = "vehicleNo";
	
	public static final String JQXBJ_IS_JQX = "isJqx";
	public static final String SUNSHINE_JQXBJ_IS_JQX = "sunshine_isJqx";
	
	public static final String JQXBJ_JQXJG = "jqxjg";
	public static final String SUNSHINE_JQXBJ_JQXJG = "forcePremium";
	
	public static final String JQXBJ_CCSJG = "ccsjg";
	public static final String SUNSHINE_JQXBJ_CCSJG = "vehicleTaxPremium";
	
	public static final String TO_AUDIT = "toAudit";
	public static final String PAY_STATUS = "payStatus";
	
	public static final String GET_SPECIAL_PROMISE_BIZ = "getSpecialPromiseBiz";
	
	public static final String GET_SPECIAL_PROMISE_JQX = "getSpecialPromiseJqx";
	
	public static final String SEND_POLICY_ADDRESS = "sendPolicyAddress";
	
	public static final String POLICY_AND_ORDER = "policyAndOrder";

	public static final String SUNSHINE_AUDIT_STATUS = "sunshineAuditStatus";
	
	public static final String SUNSHINE_AUDIT_SMS = "sunshineAuditSms";
	
	public static final String SUNSHINE_AUDIT_SMS_CODE = "sunshineAuditSmsCode";
	
	public static final String SUNSHINE_BIZ_PROPOSALNO = "sunshineBizProposalNo";
	
	public static final String SUNSHINE_FORCE_PROPOSALNO = "sunshineForceProposalNo";
	
	public static final String SUNSHINE_TOTAL_PREMIUM = "sunshineTotalPremium";
	
	public static final String SUNSHINE_PACKAGETYPE_BEFORE_SAVE_BAOJIA = "sunshinePackageBeforeSaveBaojia";
	/**
	 * 商业险总金额
	 */
	public static final String SHYX_TOTAL_FEE = "shangyexianTotalFee";
	//阳光
	public static final String SUNSHINE_BIZ_TOTAL_PREMIUM = "bizTotalPremium";
	/**
	 * 交强险总金额
	 */
	public static final String JQX_TOTAL_FEE = "forceTotalFee";
	public static final String IS_APPLY_BIZ = "isApplyBiz";
	public static final String IS_APPLY_FORCE = "isApplyForce";
	public static final String ADDRESS_NAME= "addressName";
	public static final String ADDRESS_MOBILE= "addressMobile";
	public static final String ADDRESS_PROV_NAME= "addressProvinceName";
	public static final String ADDRESS_CITY_NAME= "addressCityName";
	public static final String ADDRESS_TOWN_NAME= "addressTownCityName";
	public static final String ADDRESS_DETAIL= "addressDetail";
	public static final String AUDIT_STATUS = "auditStatus";
	public static final String AUDIT_SMS = "smsCode";
	public static final String AUDIT_SMS_CODE = "auditSmsCode";
	public static final String CD_PAY_CHECK = "cdPayCheck";
	public static final String CD_RET_STATUS = "cdRetStatus";
	public static final String CD_BIZ = "cdBiz";
	public static final String CD_JQX = "cdJqx";
	public static final String IS_NPS_FLOW = "isNpsFlow";
	
	/**
	 * 平安灾备测试
	 */
	public static final String XBZB_PA = "xuBaoZaiBeiPingAn";
	public static final String XBZB_PA_DT = "xuBaoZaiBeiPingAnDelayTime";
	public static final String SRZB_PA = "shengRiZaiBeiPingAn";
	public static final String SRZB_PA_DT = "shengRiZaiBeiPingAnDelayTime";
	public static final String SCIZB_PA = "searchCarInfoZaiBeiPingAn";
	public static final String SCIZB_PA_DT = "searchCarInfoZaiBeiPingAnDelayTime";
	
	public static final String BJZB_PA = "baoJiaZaiBeiPingAn";
	public static final String BJZB_PA_DT = "baoJiaZaiBeiPingAnDelayTime";
	public static final String BBJZB_PA = "businessBaoJiaZaiBeiPingAn";
	public static final String BBJZB_PA_DT = "businessBaoJiaZaiBeiPingAnDelayTime";
	public static final String JQBJZB_PA = "jiaoQiangBaoJiaZaiBeiPingAn";
	public static final String JQBJZB_PA_DT = "jiaoQiangBaoJiaZaiBeiPingAnDelayTime";
	
	public static final String SBJIZB_PA = "saveBaoJiaInfoZaiBeiPingAn";
	public static final String SBJIZB_PA_DT = "saveBaoJiaInfoZaiBeiPingAnDelayTime";
	public static final String GAZB_PA = "getAddressZaiBeiPingAn";
	public static final String GAZB_PA_DT = "getAddressZaiBeiPingAnDelayTime";
	public static final String GSPZB_PA = "getSpecialPromiseZaiBeiPingAn";
	public static final String GSPZB_PA_DT = "getSpecialPromiseZaiBeiPingAnDelayTime";
	
	public static final String PAZB_PA = "phoneAssertZaiBeiPingAn";
	public static final String PAZB_PA_DT = "phoneAssertZaiBeiPingAnDelayTime";
	public static final String HBZB_PA = "heBaoZaiBeiPingAn";
	public static final String HBZB_PA_DT = "heBaoZaiBeiPingAnDelayTime";
	public static final String PCZB_PA = "payCheckZaiBeiPingAn";
	public static final String PCZB_PA_DT = "payCheckZaiBeiPingAnDelayTime";
	
	public static final String CDZB_PA = "orderZaiBeiPingAn";
	public static final String CDZB_PA_DT = "orderZaiBeiPingAnDelayTime";
	
	/**
	 * 阳光灾备测试
	 */
	public static final String SCIZB_YG = "searchCarInfoZaiBeiYangGuang";
	public static final String SCIZB_YG_DT = "searchCarInfoZaiBeiYangGuangDelayTime";
	
	public static final String GBZB_YG = "getBaoJiaZaiBeiYangGuang";
	public static final String GBZB_YG_DT = "getBaoJiaZaiBeiYangGuangDelayTime";
	public static final String CBZB_YG = "changeBaoJiaZaiBeiYangGuang";
	public static final String CBZB_YG_DT = "changeBaoJiaZaiBeiYangGuangDelayTime";
	public static final String SBZB_YG = "baoCunBaoFeiZaiBeiYangGuang";
	public static final String SBZB_YG_DT = "baoCunBaoFeiZaiBeiYangGuangDelayTime";
	
	public static final String HBZB_YG = "heBaoZaiBeiYangGuang";
	public static final String HBZB_YG_DT = "heBaoZaiBeiYangGuangDelayTime";
	public static final String PCZB_YG = "payCheckZaiBeiYangGuang";
	public static final String PCZB_YG_DT = "payCheckZaiBeiYangGuangDelayTime";
	public static final String CDZB_YG = "orderZaiBeiYangGuang";
	public static final String CDZB_YG_DT = "orderZaiBeiYangGuangDelayTime";
	
	public static final String GVCZB_YG = "getVerifyCodeZaiBeiYangGuang";
	public static final String GVCZB_YG_DT = "getVerifyCodeZaiBeiYangGuangDelayTime";
	public static final String SVCZB_YG = "saveVerifyCodeZaiBeiYangGuang";
	public static final String SVCZB_YG_DT = "saveVerifyCodeZaiBeiYangGuangDelayTime";
	
	/**
	 * 优比灾备测试
	 */
	public static final String CCZB_YB = "cityConfigZaiBeiYouBi";
	public static final String CCZB_YB_DT = "cityConfigZaiBeiYouBiDelayTime";
	
	
	
	
	// 加油卡充值
	public static final String CHARGE_CARDINFO = "chargeCardInfo";
	public static final String CHARGE_CHARGE = "chargeCharge";
	public static final String CHARGE_CHARGERES = "chargeChargeRes";
	
	//优比车险
	public static final String CITY_CONFIG_MOCK = "cityConfigMock";
	public static final String CITY_CODE = "cityCode";
	public static final String PROVINCE_CODE = "provinceCode";
	public static final String LICENSE_PREFIX = "license_prefix";
	public static final String LOCAL_CAR = "local_car";
	public static final String OUT_CAR = "out_car";
	public static final String NEED_PIC = "need_pic";
	public static final String FORCE_FLAG = "force_flag";
	public static final String BIZ_DAYS = "biz_days";
	public static final String SUPPORT_DRIVING_AREA = "support_driving_area";
	public static final String SUPPORT_ASSIGN_DRIVER = "support_assign_driver";
	public static final String SEARCH_CAR_MODEL_MOCK = "searchCarModelMock";
	public static final String SEARCH_CAR_MODEL = "searchCarModel";
	
	//优比车险获取报价
	public static final String BJKG = "bjkg";
	public static final String SFFG = "sffg";
	public static final String JQXQBRQ = "jqxqbrq";
	public static final String JQXBF = "jqxbf";
	public static final String CCS = "ccs";
	public static final String JQXQNZBRQ = "jqxqnzbrq";
	public static final String JQXDQTS = "jqxdqts";
	public static final String SYXQBRQ = "syxqbrq";
	public static final String JQXSBXX = "jqxsbxx";
	public static final String JQXSBXXMA = "jqxsbxxma";
	public static final String SBJQXSBXX = "sbjqxsbxx";
	public static final String SBJQXSBXXMA = "sbjqxsbxxma";
	public static final String DEFAULTRADIO = "defaultRadio";
	public static final String NEWBAOINFO = "newbaoInfo";
	public static final String XUBAOINFO = "xubaoInfo";
	
	public static final String CLSSX = "车辆损失险";
	public static final String QCDQX = "全车盗抢险";
	public static final String SYDSZCRX = "商业第三者责任险";
	public static final String SJZWZRX = "司机座位责任险";
	public static final String CKZWZRX = "乘客座位责任险";
	public static final String CSHHSX = "车身划痕损险";
	public static final String BLDDPSX = "玻璃单独破碎险";
	public static final String SSX = "涉水险";
	public static final String ZRSSX = "自燃损失险";
	public static final String DCJCDSHX = "倒车镜、车灯单独损坏险";
	public static final String JDCSSTYX = "机动车损失保险无法找到第三方特约险";
	public static final String BJMPCSX = "不计免赔车损险";
	public static final String BJMPSZX = "不计免赔三者险";
	public static final String BUMPJDCDQX = "不计免赔机动车盗抢险";
	public static final String BJMPSJX = "不计免赔司机险";
	public static final String BJMPCKX = "不计免赔乘客险";
	public static final String BJMPCSHHSSX = "不计免赔车身划痕损失险";
	public static final String BJMPZRSSX = "不计免赔自燃损失险";
	public static final String BJMPSSX = "不计免赔涉水险";
	public static final String BJMPDCJCDSHX = "不计免赔倒车镜、车灯单独损坏险";
}