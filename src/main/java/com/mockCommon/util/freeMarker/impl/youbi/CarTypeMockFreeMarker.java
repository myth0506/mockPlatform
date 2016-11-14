package com.mockCommon.util.freeMarker.impl.youbi;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.youbi.CarTypeMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;


@Repository("carTypeMockFreeMarker")
public class CarTypeMockFreeMarker extends BaseFreeMarkerDataMaker<CarTypeMockModel>{

	public CarTypeMockFreeMarker() {
		super();
	}
}
