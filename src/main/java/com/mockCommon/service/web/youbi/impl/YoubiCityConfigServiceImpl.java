package com.mockCommon.service.web.youbi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.youbi.YoubiIniDao;
import com.mockCommon.model.mock.youbi.CityConfig;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.service.web.youbi.YoubiCityConfigService;

@Service
public class YoubiCityConfigServiceImpl implements YoubiCityConfigService{
	
	@Autowired
	private YoubiIniDao youbiIniDao;

	@Override
	public int saveCityCode(String cityCode, String isMock) {
		
		int retCode1 = saveIniParam(SessionKey.CITY_CONFIG_MOCK,isMock,"是否开启城市配置mock");
		int retCode2 = saveIniParam(SessionKey.CITY_CODE,cityCode,"城市代码");
		
		if(retCode1>0 && retCode2>0){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int saveCityConfig(String province_code, String license_prefix, String local_car, String out_car,
			String need_pic, String force_flag, String biz_days, String support_driving_area,
			String support_assign_driver) {
        
		int r1 = saveIniParam(SessionKey.PROVINCE_CODE,province_code,"城市代码");
		int r2 = saveIniParam(SessionKey.LICENSE_PREFIX,license_prefix,"车牌前缀");
		int r3 = saveIniParam(SessionKey.LOCAL_CAR,local_car,"本地车输入所需参数");
		int r4 = saveIniParam(SessionKey.OUT_CAR,out_car,"外地车输入所需参数");
		int r5 = saveIniParam(SessionKey.NEED_PIC,need_pic,"投保所需照片列表");
		int r6 = saveIniParam(SessionKey.FORCE_FLAG,force_flag,"交强险标志位");
		int r7 = saveIniParam(SessionKey.BIZ_DAYS,biz_days,"商业险提前投保天数");
		int r8 = saveIniParam(SessionKey.SUPPORT_DRIVING_AREA,support_driving_area,"指定驾驶区域");
		int r9 = saveIniParam(SessionKey.SUPPORT_ASSIGN_DRIVER,support_assign_driver,"指定驾驶人");

		return (r1*r2*r3*r4*r5*r6*r7*r8*r9);
	}
	
	private int saveIniParam(String key,String val,String desc){
		
		int retCode = 0;
		BusinessIni ini = new BusinessIni();
		ini = youbiIniDao.selectIni(key);
		if(ini == null){
			ini = new BusinessIni();
			ini.setIniName(key);
			ini.setIniValue(val);
			ini.setIniDesc(desc);
			retCode = youbiIniDao.insertIni(ini);
		}else{
			ini.setIniName(key);
			ini.setIniValue(val);
			ini.setIniDesc(desc);
			retCode = youbiIniDao.updateIni(ini);
		}
		
		return retCode;
	}
	
	@Override
	public CityConfig getCityConfig(){
		CityConfig cityConfig = new CityConfig();
		String forceFlag = youbiIniDao.selectIni(SessionKey.FORCE_FLAG).getIniValue();
		String bizDays = youbiIniDao.selectIni(SessionKey.BIZ_DAYS).getIniValue();
		String supportDrivingArea = youbiIniDao.selectIni(SessionKey.SUPPORT_DRIVING_AREA).getIniValue();
		String supportAssignDriver = youbiIniDao.selectIni(SessionKey.SUPPORT_ASSIGN_DRIVER).getIniValue();
		if(forceFlag==null || bizDays==null || supportDrivingArea==null || supportAssignDriver==null)
			return null;
		else{
			cityConfig.setForceFlag(forceFlag);
			cityConfig.setBizDays(bizDays);
			cityConfig.setSupportDrivingArea(supportDrivingArea);
			cityConfig.setSupportAssignDriver(supportAssignDriver);
			return cityConfig;
		}
	}

}
