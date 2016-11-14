package com.mockCommon.util.freeMarker.impl.yangguang;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.yangguang.SunshineSearchCarInfoMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("sunshineSearchCarInfoFreeMarker")
public class SunshineSearchCarInfoFreeMarker extends
		BaseFreeMarkerDataMaker<SunshineSearchCarInfoMockModel> {
	public SunshineSearchCarInfoFreeMarker() {
		super();
	}
}