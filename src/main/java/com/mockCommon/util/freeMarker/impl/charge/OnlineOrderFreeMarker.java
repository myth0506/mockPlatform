package com.mockCommon.util.freeMarker.impl.charge;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.charge.OnlineOrderMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("onlineOrderFreeMarker")
public class OnlineOrderFreeMarker extends BaseFreeMarkerDataMaker<OnlineOrderMockModel> {
	public OnlineOrderFreeMarker() {
		super();
	}
}