package com.mockCommon.util.freeMarker.impl.pingan;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.pingan.SaveBaoJiaMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("saveBaoJiaFreeMarker")
public class SaveBaoJiaFreeMarker extends BaseFreeMarkerDataMaker<SaveBaoJiaMockModel> {

	public SaveBaoJiaFreeMarker(){
		super();
	}
}
