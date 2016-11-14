package com.mockCommon.service.mock;

public interface ChargeMockService {
	public String onlineOrder(String cardid, String cardnum, String sporder_id, String game_userid);
	
	public String queryCardInfo(String game_userid);
}