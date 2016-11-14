package com.mockCommon.util.freeMarker.impl.youbi;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.youbi.SearchCarModelMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("searchCarModelFreeMarker")
public class SearchCarModelFreeMarker extends BaseFreeMarkerDataMaker<SearchCarModelMockModel> {
	public SearchCarModelFreeMarker(){
		super();
	}
}
