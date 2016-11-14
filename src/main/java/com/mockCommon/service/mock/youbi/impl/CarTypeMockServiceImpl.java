/**
 * 
 */
package com.mockCommon.service.mock.youbi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.model.mock.pingan.BaoJiaConfigMockModel;
import com.mockCommon.model.mock.youbi.CarTypeMockModel;
import com.mockCommon.service.mock.youbi.CarTypeMockService;
import com.mockCommon.util.freeMarker.IDataMaker;

/**
 * @author kaixie
 *
 */
@Service
public class CarTypeMockServiceImpl implements CarTypeMockService{

	@Autowired
	private IDataMaker<CarTypeMockModel> carTypeMockFreeMarker;
	
	@Override
	public String vehicleInput(String citycode, String licenseNo,
			String licenseOwner, String frameNo, String engineNo,
			String vehivleName, String enrollDate, String seatCount) {
		
		String result = null;
		CarTypeMockModel carTypeMockModel = new CarTypeMockModel();
		carTypeMockModel.setVehicleId("287ncekk7lj3qkpw");
		LogConstant.runLog.info("[baoxian-cartype]mock result is:" + carTypeMockModel);
		result = carTypeMockFreeMarker.generateData2output(ContextConstant.PREFIX_CARTYPE_MOCK, carTypeMockModel);
		LogConstant.runLog.info("[baoxian-cartype]mock result is:" + result);
		return result;
	}
}
