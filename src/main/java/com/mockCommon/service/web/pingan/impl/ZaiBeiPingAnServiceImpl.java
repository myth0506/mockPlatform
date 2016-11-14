package com.mockCommon.service.web.pingan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.dao.pingan.impl.ZaiBeiDaoImpl;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.pingan.ZaiBeiPingAnService;

@Service
public class ZaiBeiPingAnServiceImpl implements ZaiBeiPingAnService {

	@Autowired
	private CommonDaoImpl commonDao;
	
	@Autowired
	private ZaiBeiDaoImpl zaiBeiDao;


	@Override
	public Map<String, String> queryZaiBeiPingAn() {
		
		Map<String, String> map = new HashMap<String, String>();
		//续保灾备相关设置
		List<BusinessIni> iniModels = zaiBeiDao.queryIni(SessionKey.XBZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.XBZB_PA, "success");
		}else{
			map.put(SessionKey.XBZB_PA, iniModels.get(0).getIniValue());
		}
		
		iniModels = zaiBeiDao.queryIni(SessionKey.XBZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.XBZB_PA_DT, "");
		}else{
			map.put(SessionKey.XBZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//生日灾备相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.SRZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.SRZB_PA, "success");
		}else{
			map.put(SessionKey.SRZB_PA, iniModels.get(0).getIniValue());
		}
				
		iniModels = zaiBeiDao.queryIni(SessionKey.SRZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.SRZB_PA_DT, "");
		}else{
			map.put(SessionKey.SRZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//查询车辆信息相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.SCIZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.SCIZB_PA, "success");
		}else{
			map.put(SessionKey.SCIZB_PA, iniModels.get(0).getIniValue());
		}
						
		iniModels = zaiBeiDao.queryIni(SessionKey.SCIZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.SCIZB_PA_DT, "");
		}else{
			map.put(SessionKey.SCIZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		
		//报价相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.BJZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.BJZB_PA, "success");
		}else{
			map.put(SessionKey.BJZB_PA, iniModels.get(0).getIniValue());
		}
								
		iniModels = zaiBeiDao.queryIni(SessionKey.BJZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.BJZB_PA_DT, "");
		}else{
			map.put(SessionKey.BJZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//商业险报价相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.BBJZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.BBJZB_PA, "success");
		}else{
			map.put(SessionKey.BBJZB_PA, iniModels.get(0).getIniValue());
		}
										
		iniModels = zaiBeiDao.queryIni(SessionKey.BBJZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.BBJZB_PA_DT, "");
		}else{
			map.put(SessionKey.BBJZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//交强险报价相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.JQBJZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.JQBJZB_PA, "success");
		}else{
			map.put(SessionKey.JQBJZB_PA, iniModels.get(0).getIniValue());
		}
												
		iniModels = zaiBeiDao.queryIni(SessionKey.JQBJZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.JQBJZB_PA_DT, "");
		}else{
			map.put(SessionKey.JQBJZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//保存报价信息相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.SBJIZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.SBJIZB_PA, "success");
		}else{
			map.put(SessionKey.SBJIZB_PA, iniModels.get(0).getIniValue());
		}
														
		iniModels = zaiBeiDao.queryIni(SessionKey.SBJIZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.SBJIZB_PA_DT, "");
		}else{
			map.put(SessionKey.SBJIZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//获取配送地址
		iniModels = zaiBeiDao.queryIni(SessionKey.GAZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.GAZB_PA, "success");
		}else{
			map.put(SessionKey.GAZB_PA, iniModels.get(0).getIniValue());
		}
																
		iniModels = zaiBeiDao.queryIni(SessionKey.GAZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.GAZB_PA_DT, "");
		}else{
			map.put(SessionKey.GAZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//获取特别约定
		iniModels = zaiBeiDao.queryIni(SessionKey.GSPZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.GSPZB_PA, "success");
		}else{
			map.put(SessionKey.GSPZB_PA, iniModels.get(0).getIniValue());
		}
																		
		iniModels = zaiBeiDao.queryIni(SessionKey.GSPZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.GSPZB_PA_DT, "");
		}else{
			map.put(SessionKey.GSPZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//手机验证
		iniModels = zaiBeiDao.queryIni(SessionKey.PAZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.PAZB_PA, "success");
		}else{
			map.put(SessionKey.PAZB_PA, iniModels.get(0).getIniValue());
		}
																				
		iniModels = zaiBeiDao.queryIni(SessionKey.PAZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.PAZB_PA_DT, "");
		}else{
			map.put(SessionKey.PAZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//核保
		iniModels = zaiBeiDao.queryIni(SessionKey.HBZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.HBZB_PA, "success");
		}else{
			map.put(SessionKey.HBZB_PA, iniModels.get(0).getIniValue());
		}
																						
		iniModels = zaiBeiDao.queryIni(SessionKey.HBZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.HBZB_PA_DT, "");
		}else{
			map.put(SessionKey.HBZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//支出检查
		iniModels = zaiBeiDao.queryIni(SessionKey.PCZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.PCZB_PA, "success");
		}else{
			map.put(SessionKey.PCZB_PA, iniModels.get(0).getIniValue());
		}
																								
		iniModels = zaiBeiDao.queryIni(SessionKey.PCZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.PCZB_PA_DT, "");
		}else{
			map.put(SessionKey.PCZB_PA_DT, iniModels.get(0).getIniValue());
		}
		
		//出单
		iniModels = zaiBeiDao.queryIni(SessionKey.CDZB_PA);
		if(iniModels.size() == 0){
			map.put(SessionKey.CDZB_PA, "success");
		}else{
			map.put(SessionKey.CDZB_PA, iniModels.get(0).getIniValue());
		}
																										
		iniModels = zaiBeiDao.queryIni(SessionKey.CDZB_PA_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.CDZB_PA_DT, "");
		}else{
			map.put(SessionKey.CDZB_PA_DT, iniModels.get(0).getIniValue());
		}
				
		return map;
	}


	@Override
	public int setIniValue(Map<String, Object> map) {
		
		List<BusinessIni> iniModels = zaiBeiDao.queryIni(map.get("iniName").toString());
		if(iniModels.size()==0){
			return zaiBeiDao.insertIniZaiBei(map);
		}else{
			return zaiBeiDao.updateIniZaiBei(map);
		}
		
	}


	@Override
	public int setBackUp() {
		Map<String, Object> map = new HashMap<String, Object>();
		int ret = 0;
		map.put("iniName", "%ZaiBeiPingAn");
		map.put("iniValue", "success");
		int ret1 = zaiBeiDao.updateAllIniZaiBei(map);
		
		map.put("iniName", "%ZaiBeiPingAnDelayTime");
		map.put("iniValue", "");
		int ret2 = zaiBeiDao.updateAllIniZaiBei(map);
		if(ret1 == 13 && ret2 == 13){
			ret = 1;
		}
		return ret;
	}


	@Override
	public int setAllIni(HttpServletRequest request) {
		int ret = 1;
		Map<String, Object> map = new HashMap<String,Object>();
		//续保相关
		map.put("iniName", "xuBaoZaiBeiPingAn");
		map.put("iniValue", request.getParameter("xuBao"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "xuBaoZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("xuBaoDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//生日相关
		map.put("iniName", "shengRiZaiBeiPingAn");
		map.put("iniValue", request.getParameter("shengRi"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "shengRiZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("shengRiDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		
		//检查车辆信息
		map.put("iniName", "searchCarInfoZaiBeiPingAn");
		map.put("iniValue", request.getParameter("searchCarInfo"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "searchCarInfoZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("searchCarInfoDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		
		//报价
		map.put("iniName", "baoJiaZaiBeiPingAn");
		map.put("iniValue", request.getParameter("baoJia"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "baoJiaZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("baoJiaDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		
		//商业险报价
		map.put("iniName", "businessBaoJiaZaiBeiPingAn");
		map.put("iniValue", request.getParameter("businessBaoJia"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "businessBaoJiaZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("businessBaoJiaDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//交强险报价
		map.put("iniName", "jiaoQiangBaoJiaZaiBeiPingAn");
		map.put("iniValue", request.getParameter("jiaoQiangBaoJia"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "jiaoQiangBaoJiaZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("jiaoQiangBaoJiaDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//保存报价信息
		map.put("iniName", "saveBaoJiaInfoZaiBeiPingAn");
		map.put("iniValue", request.getParameter("saveBaoJiaInfo"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "saveBaoJiaInfoZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("saveBaoJiaInfoDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//获取地址
		map.put("iniName", "getAddressZaiBeiPingAn");
		map.put("iniValue", request.getParameter("getAddress"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "getAddressZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("getAddressDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//获取特殊约定
		map.put("iniName", "getSpecialPromiseZaiBeiPingAn");
		map.put("iniValue", request.getParameter("getSpecialPromise"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "getSpecialPromiseZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("getSpecialPromiseDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//手机验证
		map.put("iniName", "phoneAssertZaiBeiPingAn");
		map.put("iniValue", request.getParameter("phoneAssert"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "phoneAssertZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("phoneAssertDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		
		//核保
		map.put("iniName", "heBaoZaiBeiPingAn");
		map.put("iniValue", request.getParameter("heBao"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "heBaoZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("heBaoDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//支付检查
		map.put("iniName", "payCheckZaiBeiPingAn");
		map.put("iniValue", request.getParameter("payCheck"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "payCheckZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("payCheckDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//出单
		map.put("iniName", "orderZaiBeiPingAn");
		map.put("iniValue", request.getParameter("order"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "orderZaiBeiPingAnDelayTime");
		map.put("iniValue", request.getParameter("orderDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		return ret;
	}

}
