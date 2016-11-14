package com.mockCommon.controller.mock;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.service.mock.WzcxMockService;

@Controller
public class WzcxMockController {

	@Autowired
	private WzcxMockService wzcxMockService;

	/*
	 * 违章查询接口 
	 * city String 是 城市代码 ; 
	 * hphm String 是 号牌号码完整7位 ; 
	 * hpzl String是号牌种类编号(参考号牌种类接口)
	 */
	@RequestMapping("/weizhang/EasyQuery")
	@ResponseBody
	public Map<String, Object> wzcxQuery(
			@RequestParam("cityName") String city,
			@RequestParam("plateNumber") String hphm, 
			@RequestParam(value = "hpzl", required = false) String hpzl) {
		Map<String, Object> retMap = wzcxMockService.wzcxQuery(city, hphm, hpzl);
		return retMap;
	}
}