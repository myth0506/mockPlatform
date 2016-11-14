package com.mockCommon.util.freeMarker.impl.pingan;

import org.springframework.stereotype.Repository;

import com.mockCommon.model.mock.pingan.SendPolicyAddressMockModel;
import com.mockCommon.util.freeMarker.impl.BaseFreeMarkerDataMaker;

@Repository("sendPolicyAddressFreeMarker")
public class SendPolicyAddressFreeMarker extends
		BaseFreeMarkerDataMaker<SendPolicyAddressMockModel> {
	public SendPolicyAddressFreeMarker() {
		super();
	}
}