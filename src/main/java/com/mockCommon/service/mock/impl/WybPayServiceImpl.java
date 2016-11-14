package com.mockCommon.service.mock.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.pay.WybTransferMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.WybPayService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.HttpXmlClient;
import com.mockCommon.util.freeMarker.IDataMaker;
import com.mockCommon.util.yangguang.KeyPairer;
import com.mockCommon.util.yangguang.PartnerSignerImpl;

@Service
public class WybPayServiceImpl implements WybPayService {

	@Autowired
	private BusiConfigService busiConfigService;
	
	@Autowired
	private IDataMaker<WybTransferMockModel> wybTransferFreeMarker;
	
	@Override
	public void addTradeServlet(String sellerId, String sellerNickName,
			String buyerNickName, String goodsName, String goodsUrl,
			String tradeAmount, String platformTradeUrl, String platformId,
			String platformTradeId, String tradeType, String logisticsType,
			String platformTradeTime, String notifyUrl, String payAccountId, String platformPrivateField) {
		// 读取是否支付成功，若支付失败不会发送通知
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.PAY_STATUS);
		ini = busiConfigService.selectIni(ini);
		if(ini == null || ini.getIniValue().equals("0") ){
			return;
		}
		String notifyType = "back";
		String ewTradeId = "2016011215JY00001027";
		String ewTime = "201601121543";
		String tradeStatus = "1";
		String currencyType = "00";
		String tradeMode = "1";
		String handFee = "0.00";
		
		try{
			sellerId = URLEncoder.encode(sellerId, "UTF-8");
			sellerNickName = URLEncoder.encode(sellerNickName, "UTF-8");
			if(goodsUrl != null)
				goodsUrl = URLEncoder.encode(goodsUrl, "UTF-8");
			tradeAmount = URLEncoder.encode(tradeAmount, "UTF-8");
			if(platformTradeUrl != null)
				platformTradeUrl = URLEncoder.encode(platformTradeUrl, "UTF-8");
			platformId = URLEncoder.encode(platformId, "UTF-8");
			platformTradeId = URLEncoder.encode(platformTradeId, "UTF-8");
			tradeType = URLEncoder.encode(tradeType, "UTF-8");
			logisticsType = URLEncoder.encode(logisticsType, "UTF-8");
			platformTradeTime = URLEncoder.encode(platformTradeTime, "UTF-8");
			if(payAccountId != null)
				payAccountId = URLEncoder.encode(payAccountId, "UTF-8");
			if(platformPrivateField != null)
				platformPrivateField = URLEncoder.encode(platformPrivateField, "UTF-8");
			
			notifyType = URLEncoder.encode(notifyType, "UTF-8");
			ewTradeId = URLEncoder.encode(ewTradeId, "UTF-8");
			ewTime = URLEncoder.encode(ewTime, "UTF-8");
			tradeStatus = URLEncoder.encode(tradeStatus, "UTF-8");
			currencyType = URLEncoder.encode(currencyType, "UTF-8");
			tradeMode = URLEncoder.encode(tradeMode, "UTF-8");
			handFee = URLEncoder.encode(handFee, "UTF-8");
		}catch(UnsupportedEncodingException e){
			LogConstant.debugLog.info("网易宝支付时，URLEncoder编码参数出错！");
			return;
		}
		
		String signatureStr = sellerId + payAccountId + platformId + platformTradeId + ewTradeId + 
				ewTime + tradeType + platformTradeTime + platformPrivateField + tradeStatus + tradeAmount + 
				tradeAmount + currencyType + tradeMode + logisticsType + handFee;
		// 生产签名串 和 验证类
		PartnerSignerImpl signer = new PartnerSignerImpl();
		// 得到私钥
		PrivateKey ygPrivate = KeyPairer
				.getPrivateKey(KeyPairer.PARTNER_PRIVATE_KEY);
		String signature = null;
		try {
			signature = signer.sign(signatureStr.getBytes("UTF-8"), ygPrivate);
		} catch (UnsupportedEncodingException e) {
			LogConstant.debugLog.info("网易宝支付时, 签名编码异常：" + e.getMessage());
			e.printStackTrace();
		}
		
		try{
			signature = URLEncoder.encode(signature, "UTF-8");
		}catch(UnsupportedEncodingException e){
			LogConstant.debugLog.info("网易宝支付时，URLEncoder编码signature参数出错！");
			return;
		}
		String url = notifyUrl + "?notifyType=" + notifyType +
				"&sellerId=" + sellerId +
				"&payAccountId=" + payAccountId +
				"&platformId=" + platformId +
				"&platformTradeId=" + platformTradeId +
				"&ewTradeId=" + ewTradeId +
				"&ewTime=" + ewTime +
				"&tradeType=" + tradeType +
				"&platformTime=" + platformTradeTime +
				"&platformPrivateField=" + platformPrivateField +
				"&tradeStatus=" + tradeStatus +
				"&tradeAmout=" + tradeAmount +
				"&trueAmount=" + tradeAmount +
				"&currencyType=" + currencyType +
				"&tradeMode=" + tradeMode +
				"&logisticType=" + logisticsType +
				"&handFee=" + handFee +
				"&signature=" + signature;
		HttpXmlClient.get(url);
	}

	@Override
	public String transferServlet(String transType, String productId,
			String productTime, String platformId, String outAccount,
			String inAccount, String amount) {
		// 读取是否支付成功
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.PAY_STATUS);
		ini = busiConfigService.selectIni(ini);
		if(ini == null || ini.getIniValue().equals("0") ){
			return String.valueOf(-19);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String transferTime = sdf.format(now);
		String accountType = "0";
		String currencyType = "00";
		String transferStatus = "0";
		String transResult = "0";
		
//		try{
//			transferTime = URLEncoder.encode(transferTime, "UTF-8");
//			accountType = URLEncoder.encode(accountType, "UTF-8");
//			currencyType = URLEncoder.encode(currencyType, "UTF-8");
//			transferStatus = URLEncoder.encode(transferStatus, "UTF-8");
//			transResult = URLEncoder.encode(transResult, "UTF-8");
//		}catch(UnsupportedEncodingException e){
//			LogConstant.debugLog.info("网易宝转账时，URLEncoder编码参数出错！");
//			return String.valueOf(-19);
//		}
		String signatureStr = platformId + outAccount + inAccount +
				productId + productTime + transferTime + amount +
				accountType + currencyType + transferStatus + transType +
				transResult;
		// 生产签名串 和 验证类
		PartnerSignerImpl signer = new PartnerSignerImpl();
		// 得到私钥
		PrivateKey ygPrivate = KeyPairer
				.getPrivateKey(KeyPairer.PARTNER_PRIVATE_KEY);
		String sign = null;
		try {
			sign = signer.sign(signatureStr.getBytes("UTF-8"), ygPrivate);
		} catch (UnsupportedEncodingException e) {
			LogConstant.debugLog.info("网易宝转账时, 签名编码异常：" + e.getMessage());
			e.printStackTrace();
		}
		
//		try{
//			sign = URLEncoder.encode(sign, "UTF-8");
//		}catch(UnsupportedEncodingException e){
//			LogConstant.debugLog.info("网易宝转账时，URLEncoder编码sign参数出错！");
//			return String.valueOf(-19);
//		}
		
		WybTransferMockModel transfer = new WybTransferMockModel();
		transfer.setPlatformId(platformId);
		transfer.setOutAccount(outAccount);
		transfer.setInAccount(inAccount);
		transfer.setProductId(productId);
		transfer.setProductTime(productTime);
		transfer.setTransferTime(transferTime);
		transfer.setTransferAmount(amount);
		transfer.setAccountType(accountType);
		transfer.setCurrencyType(currencyType);
		transfer.setTransferStatus(transferStatus);
		transfer.setTransferType(transType);
		transfer.setTransResult(transResult);
		transfer.setSign(sign);
		String result = wybTransferFreeMarker.generateData2output(ContextConstant.PREFIX_WYB_TRANSFER, transfer);
		LogConstant.debugLog.info("网易宝转账返回：\n" + result);
		return result;
	}
}