package com.mockCommon.util.freeMarker.impl.pingan;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.pingan.BaoJiaConfigMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("baoJiaConfigFreeMarker")
public class BaoJiaConfigFreeMarker extends BaseFreeMarkerDataMaker<BaoJiaConfigMockModel> {

	public BaoJiaConfigFreeMarker(){
		super();
	}
}
