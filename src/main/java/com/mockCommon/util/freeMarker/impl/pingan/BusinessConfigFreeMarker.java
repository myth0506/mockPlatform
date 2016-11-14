package com.mockCommon.util.freeMarker.impl.pingan;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.pingan.BusinessConfigMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("businessConfigFreeMarker")
public class BusinessConfigFreeMarker extends BaseFreeMarkerDataMaker<BusinessConfigMockModel> {

	public BusinessConfigFreeMarker(){
		super();
	}
}
