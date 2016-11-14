package com.mockCommon.util.freeMarker.impl.yangguang;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.yangguang.SunshinePaymentMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("sunshinePaymentFreeMarker")
public class SunshinePaymentFreeMarker extends
		BaseFreeMarkerDataMaker<SunshinePaymentMockModel> {
	public SunshinePaymentFreeMarker() {
		super();
	}
}