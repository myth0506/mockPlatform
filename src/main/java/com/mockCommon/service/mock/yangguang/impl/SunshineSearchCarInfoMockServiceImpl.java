package com.mockCommon.service.mock.yangguang.impl;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.dao.yangguang.SearchCarInfoDao;
import com.mockCommon.model.mock.yangguang.ResponsePackageModel;
import com.mockCommon.model.mock.yangguang.SunshineSearchCarInfoMockModel;
import com.mockCommon.model.mock.yangguang.SunshineXubaoBaoJiaMockModel;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.yangguang.SearchCarInfoModel;
import com.mockCommon.service.mock.yangguang.SunshineBaseBaojiaService;
import com.mockCommon.service.mock.yangguang.SunshineSearchCarInfoMockService;
import com.mockCommon.service.web.yangguang.SunshineIniService;
import com.mockCommon.util.WipeTabEnterSpaceUtil;
import com.mockCommon.util.freeMarker.IDataMaker;
import com.mockCommon.util.yangguang.KeyPairer;
import com.mockCommon.util.yangguang.RSASigner;

@Service()
public class SunshineSearchCarInfoMockServiceImpl implements
		SunshineSearchCarInfoMockService {

	@Autowired
	private SunshineIniService sunshineIniServiceImpl;

	@Autowired
	private SearchCarInfoDao searchCarInfoDaoImpl;

	@Autowired
	private SunshineBaseBaojiaService sunshineBaseBaojiaServiceImpl;

	@Autowired
	private IDataMaker<SunshineSearchCarInfoMockModel> sunshineSearchCarInfoFreeMarker;

	@Autowired
	private IDataMaker<SunshineXubaoBaoJiaMockModel> sunshineXubaoBaoJiaFreeMarker;

	@Autowired
	private IDataMaker<ResponsePackageModel> packageFreeMarker;

	@Override
	public String toQueryInfo() {

		String result = null;
		int isXbyh = 0;
		try {
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.XBSR_IS_XBYH);
			ini = sunshineIniServiceImpl.selectIni(ini);
			if (ini != null) {
				isXbyh = Integer.parseInt(ini.getIniValue());
			}
		} catch (NullPointerException e) {
			isXbyh = 0;
		}
		SunshineSearchCarInfoMockModel sunshineSearchCarInfoMockModel = new SunshineSearchCarInfoMockModel();
		SunshineXubaoBaoJiaMockModel sunshineXubaoBaJiaMockModel = new SunshineXubaoBaoJiaMockModel();
		ResponsePackageModel responsePackage = new ResponsePackageModel();

		String packageBody = null;
		if (isXbyh == 1) { // 是续保用户，直接返回报价信息
			String vehicleNo = null;
			BusinessIni ini = new BusinessIni();
			ini.setIniName(SessionKey.SEARCH_CAR_INFO_VEHICLE_NO);
			ini = sunshineIniServiceImpl.selectIni(ini);
			if (ini != null) {
				vehicleNo = ini.getIniValue();
			}
			if (vehicleNo != null) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("vehicleNo", vehicleNo);
				SearchCarInfoModel searchCarInfo = searchCarInfoDaoImpl
						.searchCarInfo(paramMap);
				if (searchCarInfo != null) {
					sunshineSearchCarInfoMockModel.setOwnerName(searchCarInfo
							.getOwnerName());
					sunshineSearchCarInfoMockModel.setOwnerIdNo(searchCarInfo
							.getOwnerIdNo());
					sunshineSearchCarInfoMockModel.setOwnerMobile(searchCarInfo
							.getOwnerMobile());
					sunshineSearchCarInfoMockModel.setOwnerEmail(searchCarInfo
							.getOwnerEmail());
					sunshineSearchCarInfoMockModel.setApplicantName(searchCarInfo
							.getApplicantName());
					sunshineSearchCarInfoMockModel.setApplicantIdNo(searchCarInfo
							.getApplicantIdNo());
					sunshineSearchCarInfoMockModel.setApplicantMobile(searchCarInfo
							.getApplicantMobile());
					sunshineSearchCarInfoMockModel.setApplicantEmail(searchCarInfo
							.getApplicantEmail());
					sunshineSearchCarInfoMockModel.setInsuredName(searchCarInfo
							.getInsuredName());
					sunshineSearchCarInfoMockModel.setInsuredIdNo(searchCarInfo
							.getInsuredIdNo());
					sunshineSearchCarInfoMockModel.setInsuredMobile(searchCarInfo
							.getInsuredMobile());
					sunshineSearchCarInfoMockModel.setInsuredEmail(searchCarInfo
							.getInsuredEmail());
					sunshineSearchCarInfoMockModel.setVehicleId(searchCarInfo
							.getVehicleId());
					sunshineSearchCarInfoMockModel.setVehicleFrameNo(searchCarInfo
							.getVehicleFrameNo());
					sunshineSearchCarInfoMockModel.setVehicleModelName(searchCarInfo
							.getVehicleModelName());
					sunshineSearchCarInfoMockModel.setEngineNo(searchCarInfo
							.getEngineNo());
					sunshineSearchCarInfoMockModel.setRegisterDate(searchCarInfo
							.getRegisterDate());
					sunshineSearchCarInfoMockModel.setSpecialCarFlag(searchCarInfo
							.getSpecialCarFlag());
					sunshineSearchCarInfoMockModel.setSpecialCarDate(searchCarInfo
							.getSpecialCarDate());

					String baseBaojiaPackage = sunshineBaseBaojiaServiceImpl
							.baseBaojia("renewal");
					LogConstant.debugLog.info("BaseBaojiaPackage的生成数据为：\n"
							+ baseBaojiaPackage);
					sunshineXubaoBaJiaMockModel
							.setBaseBaojiaPackage(baseBaojiaPackage);
					sunshineXubaoBaJiaMockModel
							.setSunshineSearchCarInfoMockModel(sunshineSearchCarInfoMockModel);

					packageBody = sunshineXubaoBaoJiaFreeMarker
							.generateData2output(
									ContextConstant.PREFIX_SUNSHINE_XUBAO_BAOJIA,
									sunshineXubaoBaJiaMockModel);

					responsePackage.setStatus("100");
				}
			}

		} else { // 非续保用户
			packageBody = sunshineSearchCarInfoFreeMarker.generateData2output(
					ContextConstant.PREFIX_SEARCH_CAR_INFO,
					sunshineSearchCarInfoMockModel);
			responsePackage.setStatus("200");
		}
		LogConstant.debugLog.info("阳光保险mock查询车辆信息数据报文体：" + packageBody);

		//灾备测试
		BusinessIni iniZb = new BusinessIni();
		iniZb.setIniName(SessionKey.SCIZB_YG);
		iniZb = sunshineIniServiceImpl.selectIni(iniZb);
		String zbValue = iniZb.getIniValue();
		
		if(zbValue != null && zbValue.equals("delay")){
			BusinessIni iniDelayTime = new BusinessIni();
			iniDelayTime.setIniName(SessionKey.SCIZB_YG_DT);
			iniDelayTime = sunshineIniServiceImpl.selectIni(iniDelayTime);
			int delayTimeValue = 1000;
			if(iniDelayTime != null){
				delayTimeValue = Integer.parseInt(iniDelayTime.getIniValue());
			}
			try {
				Thread.sleep(delayTimeValue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(zbValue != null && zbValue.equals("failure")){
			responsePackage.setStatus("400");
			responsePackage.setErrorMessage("检索车辆信息错误");
			packageBody="";
		}
		
		
		responsePackage.setRequestType("100");
		responsePackage.setInsureType("100");
		Date now = new Date();
		Timestamp stamp = new Timestamp(now.getTime());
		responsePackage.setSendTime(stamp);

		// 得到私钥
		PrivateKey ygPrivate = KeyPairer
				.getPrivateKey(KeyPairer.PARTNER_PRIVATE_KEY);
		String sign = null;
		try {
			packageBody = WipeTabEnterSpaceUtil.wipe(packageBody);
			responsePackage.setPackageBody(packageBody);

			sign = RSASigner.sign(packageBody.getBytes("GBK"), ygPrivate);
		} catch (UnsupportedEncodingException e) {
			LogConstant.debugLog.info("阳光保险mock查询车辆信息签名编码异常：" + e.getMessage());
			e.printStackTrace();
		}
		responsePackage.setSign(sign);
		result = packageFreeMarker.generateData2output(
				ContextConstant.PREFIX_RESPONSE_PACKAGE, responsePackage);
		LogConstant.debugLog.info("阳光保险mock查询车辆信息返回数据：" + result);

		return result;
	}

}