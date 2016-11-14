package com.mockCommon.service.mock;

public interface WybPayService {
	void addTradeServlet(String sellerId, String sellerNickName, String buyerNickName, String goodsName, 
			String goodsUrl, String tradeAmount, String platformTradeUrl, String platformId, String platformTradeId,
			String tradeType, String logisticsType, String platformTradeTime, String notifyUrl, String payAccountId,
			String platformPrivateField);
	
	String transferServlet(String transType, String productId, String productTime, String platformId, String outAccount,
			String inAccount, String amount);
}