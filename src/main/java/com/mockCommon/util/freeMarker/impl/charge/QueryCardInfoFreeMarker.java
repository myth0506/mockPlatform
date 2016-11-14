package com.mockCommon.util.freeMarker.impl.charge;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.charge.QueryCardInfoMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("queryCardInfoFreeMarker")
public class QueryCardInfoFreeMarker extends BaseFreeMarkerDataMaker<QueryCardInfoMockModel> {
	public QueryCardInfoFreeMarker(){
		super();
	}
}