package com.mockCommon.service.mock.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ChargeCardName;
import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.charge.OnlineOrderMockModel;
import com.mockCommon.model.mock.charge.QueryCardInfoMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.mock.ChargeMockService;
import com.mockCommon.service.web.pingan.BusiConfigService;
import com.mockCommon.util.freeMarker.impl.charge.OnlineOrderFreeMarker;
import com.mockCommon.util.freeMarker.impl.charge.QueryCardInfoFreeMarker;
import com.netease.common.util.StringUtil;

@Service("chargeMockServiceImpl")
public class ChargeMockServiceImpl implements ChargeMockService {

	@Autowired
	private BusiConfigService busiConfigService;
	
	@Autowired
	private OnlineOrderFreeMarker onlineOrderFreeMarker;
	
	@Autowired
	private QueryCardInfoFreeMarker queryCardInfoFreeMarker;
	
	@Override
	public String onlineOrder(String cardid, String cardnum, String sporder_id,
			String game_userid) {
		String result = null;
		if(StringUtil.isEmpty(cardid) || StringUtil.isEmpty(cardnum) ||StringUtil.isEmpty(sporder_id) ||StringUtil.isEmpty(game_userid)){
			LogConstant.runLog.info("加油卡充值时，请求参数为空！");
			return result;
		}
		OnlineOrderMockModel model = new OnlineOrderMockModel();
		model.setCardId(cardid);
		model.setCardNum(cardnum);
		model.setSporderId(sporder_id);
		model.setGameUserId(game_userid);
		
		String orderCrash = "";
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.CHARGE_CHARGE);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			orderCrash = ini.getIniValue();
		}
		model.setOrderCash(orderCrash);
		
		String gameState = "1"; // 如果成功将为1，澈消(充值失败)为9，充值中为0,只能当状态为9时，商户才可以退款给用户。
		ini.setIniName(SessionKey.CHARGE_CHARGERES);
		ini = busiConfigService.selectIni(ini);
		if(ini != null){
			gameState = ini.getIniValue();
		}
		model.setGameState(gameState);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMddhhmmss");
		String orderId = "S140" + sdf.format(new Date());
		model.setOrderId(orderId);
		String cardName = ChargeCardName.getValue("C" + cardid);
		model.setCardName(cardName);
		
		model.setRetCode("1"); // 成功
		result = onlineOrderFreeMarker.generateData2output(ContextConstant.PREFIX_CHARGE_ONLINE_ORDER, model);
		LogConstant.runLog.info("加油卡充值onlineOrder请求返回的信息：\n" + result);
		return result;
	}

	@Override
	public String queryCardInfo(String game_userid) {
		String result = null;
		if(StringUtil.isEmpty(game_userid)){
			LogConstant.runLog.info("查询加油卡信息时，请求参数为空！");
			return result;
		}
		QueryCardInfoMockModel model = new QueryCardInfoMockModel();
		
		BusinessIni ini = new BusinessIni();
		ini.setIniName(SessionKey.CHARGE_CARDINFO);
		ini = busiConfigService.selectIni(ini);
		String cardInfo = null;
		if(ini != null){
			cardInfo = ini.getIniValue();
		}
		if(!StringUtil.isEmpty(cardInfo)){
			if("1".equals(cardInfo)){
				model.setRetCode("1");
				model.setUserName("邹*");
				model.setGameUserId(game_userid);
			}else if("2".equals(cardInfo)){
				model.setRetCode("1");
				model.setUserName("不记名卡");
				model.setGameUserId(game_userid);
			}else if("3".equals(cardInfo)){
				model.setRetCode("1018");
				model.setErrMsg("副卡不能进行充值!");
			}else{
				model.setRetCode("1018");
				model.setErrMsg("您输入的加油卡号码不存在!");
			}
		}
		
		result = queryCardInfoFreeMarker.generateData2output(ContextConstant.PREFIX_CHARGE_QUERY_CARD_INFO, model);
		LogConstant.runLog.info("加油卡充值queryCardInfo请求返回的信息：\n" + result);
		return result;
	}

}