package com.mockCommon.util.freeMarker.impl.yangguang;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.yangguang.SunshineVerifyCodeMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("sunshineVerifyCodeFreeMarker")
public class SunshineVerifyCodeFreeMarker extends
		BaseFreeMarkerDataMaker<SunshineVerifyCodeMockModel> {
	public SunshineVerifyCodeFreeMarker() {
		super();
	}
}