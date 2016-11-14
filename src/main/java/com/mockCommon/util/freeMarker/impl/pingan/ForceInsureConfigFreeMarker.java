package com.mockCommon.util.freeMarker.impl.pingan;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.pingan.ForceConfigMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("forceInsureConfigFreeMarker")
public class ForceInsureConfigFreeMarker extends BaseFreeMarkerDataMaker<ForceConfigMockModel> {

	public ForceInsureConfigFreeMarker(){
		super();
	}
}
