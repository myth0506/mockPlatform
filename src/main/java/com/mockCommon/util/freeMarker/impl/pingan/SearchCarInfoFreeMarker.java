package com.mockCommon.util.freeMarker.impl.pingan;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.pingan.SearchCarInfoMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("searchCarInfoFreeMarker")
public class SearchCarInfoFreeMarker extends
		BaseFreeMarkerDataMaker<SearchCarInfoMockModel> {
	public SearchCarInfoFreeMarker() {
		super();
	}
}