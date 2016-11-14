package com.mockCommon.controller.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.ChargeMockService;

@Controller("chargeMockController")
public class ChargeMockController {
	
	@Autowired
	private ChargeMockService chargeMockService;
	
	@RequestMapping("sinopec/onlineorder.do")
	@ResponseBody
	public String onlineOrder(
			@RequestParam("cardid") String cardid,
			@RequestParam("cardnum") String cardnum, 
			@RequestParam("sporder_id") String sporder_id,
			@RequestParam("game_userid") String game_userid) {
		String res = chargeMockService.onlineOrder(cardid, cardnum, sporder_id, game_userid);
		return res;
	}
	
	@RequestMapping("sinopec/queryCardInfo.do")
	@ResponseBody
	public String queryCardInfo(
			@RequestParam("game_userid") String game_userid) {
		String res = chargeMockService.queryCardInfo(game_userid);
		return res;
	}
}