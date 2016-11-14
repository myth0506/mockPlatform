package com.mockCommon.util.freeMarker.impl.youbi;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.youbi.CityConfigMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("cityConfigFreeMarker")
public class CityConfigFreeMarker extends BaseFreeMarkerDataMaker<CityConfigMockModel>{
	public CityConfigFreeMarker() {
		super();
	}
}
