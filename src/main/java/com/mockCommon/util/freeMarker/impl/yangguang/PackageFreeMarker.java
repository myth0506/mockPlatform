package com.mockCommon.util.freeMarker.impl.yangguang;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.yangguang.ResponsePackageModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("packageFreeMarker")
public class PackageFreeMarker extends
		BaseFreeMarkerDataMaker<ResponsePackageModel> {
	public PackageFreeMarker() {
		super();
	}
}