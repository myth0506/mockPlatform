package com.mockCommon.util.freeMarker.impl.youbi;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.youbi.LastYearTouBaoMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("lastYearTouBaoFreeMarker")
public class LastYearTouBaoFreeMarker extends BaseFreeMarkerDataMaker<LastYearTouBaoMockModel>{
	public LastYearTouBaoFreeMarker() {
		super();
	}
}
