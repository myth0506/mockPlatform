package com.mockCommon.util.freeMarker.impl.pingan;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.pingan.AuditInfoMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("toAuditFreeMarker")
public class ToAuditFreeMarker extends
		BaseFreeMarkerDataMaker<AuditInfoMockModel> {
	public ToAuditFreeMarker() {
		super();
	}
}