package com.mockCommon.util.freeMarker.impl.pay;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.pay.WybTransferMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("wybTransferFreeMarker")
public class WybTransferFreeMarker extends
		BaseFreeMarkerDataMaker<WybTransferMockModel> {
	public WybTransferFreeMarker() {
		super();
	}
}