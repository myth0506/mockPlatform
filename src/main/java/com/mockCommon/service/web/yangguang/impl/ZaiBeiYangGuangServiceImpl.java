package com.mockCommon.service.web.yangguang.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.dao.yangguang.ZaiBeiYangGuangDao;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.yangguang.ZaiBeiYangGuangService;

@Service
public class ZaiBeiYangGuangServiceImpl implements ZaiBeiYangGuangService {

	@Autowired
	private CommonDaoImpl commonDao;
	
	@Autowired
	private ZaiBeiYangGuangDao zaiBeiDao;


	@Override
	public Map<String, String> queryzaiBeiYangGuang() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		
		//查询车辆信息相关设置
		List<BusinessIni> iniModels = zaiBeiDao.queryIni(SessionKey.SCIZB_YG);
		if(iniModels.size() == 0){
			map.put(SessionKey.SCIZB_YG, "success");
		}else{
			map.put(SessionKey.SCIZB_YG, iniModels.get(0).getIniValue());
		}
						
		iniModels = zaiBeiDao.queryIni(SessionKey.SCIZB_YG_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.SCIZB_YG_DT, "");
		}else{
			map.put(SessionKey.SCIZB_YG_DT, iniModels.get(0).getIniValue());
		}
		
		
		//获取报价相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.GBZB_YG);
		if(iniModels.size() == 0){
			map.put(SessionKey.GBZB_YG, "success");
		}else{
			map.put(SessionKey.GBZB_YG, iniModels.get(0).getIniValue());
		}
								
		iniModels = zaiBeiDao.queryIni(SessionKey.GBZB_YG_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.GBZB_YG_DT, "");
		}else{
			map.put(SessionKey.GBZB_YG_DT, iniModels.get(0).getIniValue());
		}
		
		//修改报价相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.CBZB_YG);
		if(iniModels.size() == 0){
			map.put(SessionKey.CBZB_YG, "success");
		}else{
			map.put(SessionKey.CBZB_YG, iniModels.get(0).getIniValue());
		}
										
		iniModels = zaiBeiDao.queryIni(SessionKey.CBZB_YG_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.CBZB_YG_DT, "");
		}else{
			map.put(SessionKey.CBZB_YG_DT, iniModels.get(0).getIniValue());
		}
		
		//保存保费相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.SBZB_YG);
		if(iniModels.size() == 0){
			map.put(SessionKey.SBZB_YG, "success");
		}else{
			map.put(SessionKey.SBZB_YG, iniModels.get(0).getIniValue());
		}
												
		iniModels = zaiBeiDao.queryIni(SessionKey.SBZB_YG_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.SBZB_YG_DT, "");
		}else{
			map.put(SessionKey.SBZB_YG_DT, iniModels.get(0).getIniValue());
		}
		
		//核保
		iniModels = zaiBeiDao.queryIni(SessionKey.HBZB_YG);
		if(iniModels.size() == 0){
			map.put(SessionKey.HBZB_YG, "success");
		}else{
			map.put(SessionKey.HBZB_YG, iniModels.get(0).getIniValue());
		}
																						
		iniModels = zaiBeiDao.queryIni(SessionKey.HBZB_YG_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.HBZB_YG_DT, "");
		}else{
			map.put(SessionKey.HBZB_YG_DT, iniModels.get(0).getIniValue());
		}
		
		//支出检查
		iniModels = zaiBeiDao.queryIni(SessionKey.PCZB_YG);
		if(iniModels.size() == 0){
			map.put(SessionKey.PCZB_YG, "success");
		}else{
			map.put(SessionKey.PCZB_YG, iniModels.get(0).getIniValue());
		}
																								
		iniModels = zaiBeiDao.queryIni(SessionKey.PCZB_YG_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.PCZB_YG_DT, "");
		}else{
			map.put(SessionKey.PCZB_YG_DT, iniModels.get(0).getIniValue());
		}
		
		//出单
		iniModels = zaiBeiDao.queryIni(SessionKey.CDZB_YG);
		if(iniModels.size() == 0){
			map.put(SessionKey.CDZB_YG, "success");
		}else{
			map.put(SessionKey.CDZB_YG, iniModels.get(0).getIniValue());
		}
																										
		iniModels = zaiBeiDao.queryIni(SessionKey.CDZB_YG_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.CDZB_YG_DT, "");
		}else{
			map.put(SessionKey.CDZB_YG_DT, iniModels.get(0).getIniValue());
		}
		
		//获取验证码相关设置
		iniModels = zaiBeiDao.queryIni(SessionKey.GVCZB_YG);
		if(iniModels.size() == 0){
			map.put(SessionKey.GVCZB_YG, "success");
		}else{
			map.put(SessionKey.GVCZB_YG, iniModels.get(0).getIniValue());
		}
														
		iniModels = zaiBeiDao.queryIni(SessionKey.GVCZB_YG_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.GVCZB_YG_DT, "");
		}else{
			map.put(SessionKey.GVCZB_YG_DT, iniModels.get(0).getIniValue());
		}
		
		//保存验证码相关配置
		iniModels = zaiBeiDao.queryIni(SessionKey.SVCZB_YG);
		if(iniModels.size() == 0){
			map.put(SessionKey.SVCZB_YG, "success");
		}else{
			map.put(SessionKey.SVCZB_YG, iniModels.get(0).getIniValue());
		}
																
		iniModels = zaiBeiDao.queryIni(SessionKey.SVCZB_YG_DT);
		if(iniModels.size() == 0){
			map.put(SessionKey.SVCZB_YG_DT, "");
		}else{
			map.put(SessionKey.SVCZB_YG_DT, iniModels.get(0).getIniValue());
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
		map.put("iniName", "%ZaiBeiYangGuang");
		map.put("iniValue", "success");
		int ret1 = zaiBeiDao.updateAllIniZaiBei(map);
		
		map.put("iniName", "%ZaiBeiYangGuangDelayTime");
		map.put("iniValue", "");
		int ret2 = zaiBeiDao.updateAllIniZaiBei(map);
		if(ret1 == 9 && ret2 == 9){
			ret = 1;
		}
		return ret;
	}


	@Override
	public int setAllIni(HttpServletRequest request) {
		int ret = 1;
		Map<String, Object> map = new HashMap<String,Object>();		
		
		//检查车辆信息
		map.put("iniName", "searchCarInfoZaiBeiYangGuang");
		map.put("iniValue", request.getParameter("searchCarInfo"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "searchCarInfoZaiBeiYangGuangDelayTime");
		map.put("iniValue", request.getParameter("searchCarInfoDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		
		//获取报价
		map.put("iniName", "getBaoJiaZaiBeiYangGuang");
		map.put("iniValue", request.getParameter("getBaoJia"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "getBaoJiaZaiBeiYangGuangDelayTime");
		map.put("iniValue", request.getParameter("getBaoJiaDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		
		//修改报价
		map.put("iniName", "changeBaoJiaZaiBeiYangGuang");
		map.put("iniValue", request.getParameter("changeBaoJia"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "changeBaoJiaZaiBeiYangGuangDelayTime");
		map.put("iniValue", request.getParameter("changeBaoJiaDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//保存保费
		map.put("iniName", "baoCunBaoFeiZaiBeiYangGuang");
		map.put("iniValue", request.getParameter("baoCunBaoFei"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "baoCunBaoFeiZaiBeiYangGuangDelayTime");
		map.put("iniValue", request.getParameter("baoCunBaoFeiDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//核保
		map.put("iniName", "heBaoZaiBeiYangGuang");
		map.put("iniValue", request.getParameter("heBao"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "heBaoZaiBeiYangGuangDelayTime");
		map.put("iniValue", request.getParameter("heBaoDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//支付检查
		map.put("iniName", "payCheckZaiBeiYangGuang");
		map.put("iniValue", request.getParameter("payCheck"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "payCheckZaiBeiYangGuangDelayTime");
		map.put("iniValue", request.getParameter("payCheckDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//出单
		map.put("iniName", "orderZaiBeiYangGuang");
		map.put("iniValue", request.getParameter("order"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "orderZaiBeiYangGuangDelayTime");
		map.put("iniValue", request.getParameter("orderDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//获取验证码
		map.put("iniName", "getVerifyCodeZaiBeiYangGuang");
		map.put("iniValue", request.getParameter("getVerifyCode"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "getVerifyCodeZaiBeiYangGuangDelayTime");
		map.put("iniValue", request.getParameter("getVerifyCodeDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		//保存验证码
		map.put("iniName", "saveVerifyCodeZaiBeiYangGuang");
		map.put("iniValue", request.getParameter("saveVerifyCode"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		map.put("iniName", "saveVerifyCodeZaiBeiYangGuangDelayTime");
		map.put("iniValue", request.getParameter("saveVerifyCodeDelayTime"));
		ret = zaiBeiDao.updateIniZaiBei(map);
		if(ret != 1){
			return 0;
		}
		
		return ret;
	}

}
