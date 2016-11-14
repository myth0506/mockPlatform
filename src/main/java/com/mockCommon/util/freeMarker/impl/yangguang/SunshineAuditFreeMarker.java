package com.mockCommon.util.freeMarker.impl.yangguang;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.yangguang.SunshineAuditMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("sunshineAuditFreeMarker")
public class SunshineAuditFreeMarker extends
		BaseFreeMarkerDataMaker<SunshineAuditMockModel> {
	public SunshineAuditFreeMarker() {
		super();
	}
}