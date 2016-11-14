package com.mockCommon.service.mock.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.dao.WzcxDao;
import com.mockCommon.model.mock.WzcxRecordMockModel;
import com.mockCommon.model.web.Wzcx;
import com.mockCommon.model.web.WzcxInfoModel;
import com.mockCommon.model.web.WzcxRecordModel;
import com.mockCommon.service.mock.WzcxMockService;
import com.mockCommon.util.DateUtil;
import com.mockCommon.util.NullOrEmpty;
import com.mockCommon.util.WzcxUtil;

@Service
public class WzcxMockServiceImpl implements WzcxMockService {

	@Autowired
	private WzcxDao wzcxDao;

	@Override
	public Map<String, Object> wzcxQuery(String city, String hphm, String hpzl) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (hpzl == null)
			hpzl = "02"; // 默认值02
		if (NullOrEmpty.isNullOrEmpty(city) || NullOrEmpty.isNullOrEmpty(hphm)) {
			retMap.put("error_code", 10001);
			retMap.put("reason", "错误的请求KEY");
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("wzcx_car_num", hphm);
			WzcxInfoModel wzcxInfoModel = wzcxDao.searchWzcxInfoModel(paramMap);
			if (wzcxInfoModel != null) {
				Map<String, Object> param = new HashMap<String, Object>();
				// 查询违章状态
				param.put("id", wzcxInfoModel.getWzcx_status());
				Wzcx wzcx = wzcxDao.searchWzcxById(param);
				if (wzcx != null) {

					if("1".equals(wzcx.getId())){               //查询成功
						Map<String, Object> resultMap = new HashMap<String, Object>();
						resultMap.put("province", "S1X");
						resultMap.put("city", city);
						resultMap.put("hphm", hphm);
						resultMap.put("hpzl", hpzl);
						if (wzcxInfoModel.getWzcx_sfwz() == 1) {  //查询成功且有违章记录
								
							paramMap.clear();
							paramMap.put("wzcx_id", wzcxInfoModel.getWzcx_id());
							List<WzcxRecordModel> lists = wzcxDao
									.searchWzcxRecordModel(paramMap);
	
							List<WzcxRecordMockModel> mockLists = new ArrayList<WzcxRecordMockModel>();
							for (int i = 0; i < lists.size(); i++) {
								WzcxRecordModel wzcxRecordModel = lists.get(i);
								if (wzcxRecordModel != null) {
									WzcxRecordMockModel wzcxRecordMockModel = new WzcxRecordMockModel();
									wzcxRecordMockModel.setDate(DateUtil
											.getDate(wzcxRecordModel
													.getRecord_wzsj()));
									wzcxRecordMockModel.setArea(wzcxRecordModel
											.getRecord_wzdd());
									wzcxRecordMockModel.setAct(wzcxRecordModel
											.getRecord_wzyy());
									wzcxRecordMockModel
											.setFen(String.valueOf(wzcxRecordModel
													.getRecord_kf()));
									wzcxRecordMockModel
											.setMoney(String
													.valueOf(wzcxRecordModel
															.getRecord_fk()));
									wzcxRecordMockModel.setHandled(WzcxUtil
											.getWzcxHandleStatus(wzcxRecordModel
													.getRecord_wzzt()));
									wzcxRecordMockModel.setLatitude(37.710136);
									wzcxRecordMockModel.setLongitude(112.737809);
									wzcxRecordMockModel.setCode("1352");
									wzcxRecordMockModel.setPayNo("067900104603");
									mockLists.add(wzcxRecordMockModel);
								}
							}
	
							resultMap.put("lists", mockLists);
	
							retMap.put("error_code", 0);
							retMap.put("reason", "成功返回");
							retMap.put("result", resultMap);
						} else {            // 查询成功，没有违章记录
							retMap.put("error_code", wzcx.getResp_retCode());
							retMap.put("reason", wzcx.getResp_retDesc());
							resultMap.put("lists", null);
							retMap.put("result", resultMap);
						}
					}else{                  //查询状态为不成功
						retMap.put("error_code", wzcx.getResp_retCode());
						retMap.put("reason", wzcx.getResp_retDesc());
						retMap.put("result", null);
					}
				} else { // 违章查询状态为空
					retMap.put("error_code", 10025);
					retMap.put("reason", "没有查询到结果");
				}
			} else {
				retMap.put("error_code", 10025);
				retMap.put("reason", "没有查询到结果");
			}
		}
		return retMap;
	}

}