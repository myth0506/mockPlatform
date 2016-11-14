package com.mockCommon.controller.mock;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.service.mock.WybPayService;

@Controller
public class WybPayMockController {

	@Autowired
	private WybPayService wybPayServiceImpl;

	/**
	 * 网易宝支付
	 */
	@RequestMapping("/addTradeServlet")
	@ResponseBody
	public void addTradeServlet(
			@RequestParam("sellerId") String sellerId,
			@RequestParam("sellerNickName") String sellerNickName,
			@RequestParam("buyerNickName") String buyerNickName,
			@RequestParam("goodsName") String goodsName,
			@RequestParam(value = "goodsUrl", required = false) String goodsUrl,
			@RequestParam("tradeAmount") String tradeAmount,
			@RequestParam(value = "platformTradeUrl", required = false) String platformTradeUrl,
			@RequestParam("platformId") String platformId,
			@RequestParam("platformTradeId") String platformTradeId,
			@RequestParam("tradeType") String tradeType,
			@RequestParam("logisticsType") String logisticsType,
			@RequestParam("platformTradeTime") String platformTradeTime,
			@RequestParam("notifyUrl") String notifyUrl,
			@RequestParam(value = "payAccountId", required = false) String payAccountId,
			@RequestParam(value = "platformPrivateField", required = false) String platformPrivateField) {

//		try {
//			sellerId = URLDecoder.decode(sellerId, "UTF-8");
//			sellerNickName = URLDecoder.decode(sellerNickName, "UTF-8");
//			buyerNickName = URLDecoder.decode(buyerNickName, "UTF-8");
//			goodsName = URLDecoder.decode(goodsName, "UTF-8");
//			goodsUrl = URLDecoder.decode(goodsUrl, "UTF-8");
//			tradeAmount = URLDecoder.decode(tradeAmount, "UTF-8");
//			platformTradeUrl = URLDecoder.decode(platformTradeUrl, "UTF-8");
//			platformId = URLDecoder.decode(platformId, "UTF-8");
//			platformTradeId = URLDecoder.decode(platformTradeId, "UTF-8");
//			tradeType = URLDecoder.decode(tradeType, "UTF-8");
//			logisticsType = URLDecoder.decode(logisticsType, "UTF-8");
//			platformTradeTime = URLDecoder.decode(platformTradeTime, "UTF-8");
//			notifyUrl = URLDecoder.decode(notifyUrl, "UTF-8");
//			payAccountId = URLDecoder.decode(payAccountId, "UTF-8");
//			platformPrivateField = URLDecoder.decode(platformPrivateField,
//					"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			LogConstant.debugLog.info("网易宝支付时，URLDecoder解码参数出错！");
//			return;
//		}
		LogConstant.debugLog.info("网易宝支付请求参数：[sellerId]= " + sellerId
				+ "  [sellerNickName]= " + sellerNickName + "  [buyerNickName]= "
				+ buyerNickName + "  [goodsName]= " + goodsName + "  [goodsUrl]= "
				+ goodsUrl + "  [tradeAmount]= " + tradeAmount
				+ "  [platformTradeUrl]= " + platformTradeUrl + "  [platformId]= "
				+ platformId + "  [platformTradeId]= " + platformTradeId
				+ "  [tradeType]= " + tradeType + "  [logisticsType]= "
				+ logisticsType + "  [platformTradeTime]= " + platformTradeTime
				+ "  [notifyUrl]= " + notifyUrl + "  [payAccountId]= "
				+ payAccountId + "  [platformPrivateField]= "
				+ platformPrivateField);
		
		wybPayServiceImpl.addTradeServlet(sellerId, sellerNickName,
				buyerNickName, goodsName, goodsUrl, tradeAmount,
				platformTradeUrl, platformId, platformTradeId, tradeType,
				logisticsType, platformTradeTime, notifyUrl, payAccountId,
				platformPrivateField);
	}
	
	/**
	 * 网易宝转账
	 */
	@RequestMapping("/TransferServlet")
	@ResponseBody
	public String transferServlet(
			@RequestParam("transType") String transType,
			@RequestParam("productId") String productId,
			@RequestParam("productTime") String productTime,
			@RequestParam("platformId") String platformId,
			@RequestParam("outAccount") String outAccount,
			@RequestParam("inAccount") String inAccount,
			@RequestParam("amount") String amount) {
		try {
			transType = URLDecoder.decode(transType, "UTF-8");
			productId = URLDecoder.decode(productId, "UTF-8");
			productTime = URLDecoder.decode(productTime, "UTF-8");
			platformId = URLDecoder.decode(platformId, "UTF-8");
			outAccount = URLDecoder.decode(outAccount, "UTF-8");
			inAccount = URLDecoder.decode(inAccount, "UTF-8");
			amount = URLDecoder.decode(amount, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LogConstant.debugLog.info("网易宝转账时，URLDecoder解码参数出错！");
			return String.valueOf(-19);
		}
		LogConstant.debugLog.info("网易宝转账请求参数：[transType]= " + transType
				+ "  [productId]= " + productId + "  [productTime]= "
				+ productTime + "  [platformId]= " + platformId + "  [outAccount]= "
				+ outAccount + "  [inAccount]= " + inAccount
				+ "  [amount]= " + amount);
		
		String result = wybPayServiceImpl.transferServlet(transType, productId,
				productTime, platformId, outAccount, inAccount, amount);
		return result;
	}
}