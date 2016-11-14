package com.mockCommon.model.mock.youbi;

import com.mockCommon.util.freeMarker.DataTemplate;


@DataTemplate(template = "cartype_template.json", output = "cartype_out.json")
public class CarTypeMockModel {

 private String vehicleId;

public String getVehicleId() {
	return vehicleId;
}

public void setVehicleId(String vehicleId) {
	this.vehicleId = vehicleId;
}

}
