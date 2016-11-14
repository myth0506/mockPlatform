package com.mockCommon.controller.mock.yangguang;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.yangguang.SunshineAuditMockService;
import com.mockCommon.service.mock.yangguang.SunshineBaoJiaMockService;
import com.mockCommon.service.mock.yangguang.SunshineModifyBaoJiaMockService;
import com.mockCommon.service.mock.yangguang.SunshinePayCheckMockService;
import com.mockCommon.service.mock.yangguang.SunshinePaymentMockService;
import com.mockCommon.service.mock.yangguang.SunshineSavePremiumMockService;
import com.mockCommon.service.mock.yangguang.SunshineSearchCarInfoMockService;
import com.mockCommon.service.mock.yangguang.SunshineVerifyCodeMockService;
import com.mockCommon.service.web.yangguang.SunshineBusiConfigService;
import com.mockCommon.util.ParseXmlUtil;
import com.netease.common.util.StringUtil;

@Controller("sunshineMockController")
public class SunshineMockController {

	@Autowired
	private SunshineSearchCarInfoMockService searchCarInfoMockServiceImpl;

	@Autowired @Qualifier("sunshineBaoJiaMockServiceImpl")
	private SunshineBaoJiaMockService sunshineBaoJiaMockServiceImpl;
	
	@Autowired @Qualifier("sunshineFeigaiBaoJiaMockServiceImpl")
	private SunshineBaoJiaMockService sunshineFeigaiBaoJiaMockServiceImpl;

	@Autowired
	private SunshineAuditMockService sunshineAuditMockServiceImpl;

	@Autowired
	private SunshineVerifyCodeMockService sunshineVerifyCodeServiceImpl;
	
	@Autowired
	private SunshinePaymentMockService sunshinePaymentMockServiceImpl;
	
	@Autowired @Qualifier("sunshineModifyBaoJiaMockServiceImpl")
	private SunshineModifyBaoJiaMockService sunshineModifyBaoJiaMockServiceImpl;
	
	@Autowired @Qualifier("sunshineFeigaiModifyBaoJiaMockServiceImpl")
	private SunshineModifyBaoJiaMockService sunshineFeigaiModifyBaoJiaMockServiceImpl;
	
	@Autowired
	private SunshineSavePremiumMockService savePremiumMockServiceImpl;
	
	@Autowired
	private SunshinePayCheckMockService payCheckMockServiceImpl;
	
	@Autowired
	private SunshineBusiConfigService busiConfigServiceImpl;

	@RequestMapping("/InterFaceServlet")
	@ResponseBody
	public String sunshineMock(HttpServletRequest request) {
		LogConstant.debugLog
				.info("============================阳光保险请求==========================");
		String xmlParams = null;
		// 获取请求的xml数据包
		try {
			BufferedReader buffReader = request.getReader();
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = buffReader.readLine()) != null)
				sb.append(line);
			xmlParams = sb.toString();
			if (!StringUtil.isEmpty(xmlParams)) {
				LogConstant.debugLog.info("获取请求的xml为：\n" + xmlParams);
			} else {
				LogConstant.debugLog.info("获取请求的xml为空！");
				return null;
			}
			buffReader.close();
		} catch (IOException e) {
			LogConstant.debugLog.info("阳光保险获取request.getReader()时异常："
					+ e.getMessage());
			e.printStackTrace();
		}

		String requestType = ParseXmlUtil.queryElementByXPath(xmlParams,
				"/PackageList/Package/Header/RequestType").getTextTrim();
		String result = null;
		if (requestType == null) {
			LogConstant.debugLog.info("阳光保险获取请求的参数RequestType为空！");
			return result;
		}
		LogConstant.debugLog.info("阳光保险获取请求的参数[RequestType]为：" + requestType);
		switch (requestType) {
		case "100": // 基础信息接口
			result = searchCarInfoMockServiceImpl.toQueryInfo();
			break;
		case "105": // 获取报价
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.IS_NPS_FLOW);
			ini = busiConfigServiceImpl.selectIni(ini);
			if("1".equals(ini.getIniValue())){
				result = sunshineFeigaiBaoJiaMockServiceImpl.baoJia(xmlParams);
			}else{
				result = sunshineBaoJiaMockServiceImpl.baoJia(xmlParams);
			}
			break;
		case "110": // 修改报价
			Element cov_390Element = ParseXmlUtil.queryElementByXPath(xmlParams,
					"/PackageList/Package/Request/InputsList/Inputs/Input[@name=\"cov_390\"]");
			// 如果请求中有cov_390返回费改前修改报价
			if(cov_390Element != null && !StringUtil.isEmpty(cov_390Element.getTextTrim())){
				result = sunshineModifyBaoJiaMockServiceImpl.modifyBaoJia(xmlParams);
			}else{
				result = sunshineFeigaiModifyBaoJiaMockServiceImpl.modifyBaoJia(xmlParams);
			}
			break;
		case "115": // 保存保费获取投保礼
			result = savePremiumMockServiceImpl.savePremium(xmlParams);
			break;
		case "120": // 核保请求
			result = sunshineAuditMockServiceImpl.audit(xmlParams);
			break;
		case "125": // 支付校验
			result = payCheckMockServiceImpl.payCheck(xmlParams);
			break;
		case "130": // 出单请求
			result = sunshinePaymentMockServiceImpl.payment(xmlParams);
			break;
		case "126": // 获取验证码
			result = sunshineVerifyCodeServiceImpl.verifyCode(null);
			break;
		case "127": // 保存验证码
			String code = ParseXmlUtil.queryElementByXPath(xmlParams,
					"/PackageList/Package/Request/IssueCode").getTextTrim();
			result = sunshineVerifyCodeServiceImpl.verifyCode(code);
			break;
		}

		LogConstant.debugLog.info("[RequestType]= " + requestType
				+ "  请求返回的xml数据为：\n" + result);
		return result;
	}
}