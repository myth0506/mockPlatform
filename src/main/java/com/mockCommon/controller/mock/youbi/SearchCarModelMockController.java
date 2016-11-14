package com.mockCommon.controller.mock.youbi;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.LogConstant;
import com.mockCommon.service.mock.youbi.impl.SearchCarModelMockServiceImpl;

@Controller
public class SearchCarModelMockController {
	@Autowired
	private SearchCarModelMockServiceImpl searchCarModelMockServiceImpl;
	
	@RequestMapping(value = "/vehicle", method = RequestMethod.POST)
	@ResponseBody
	public String searchCarModel(@RequestBody Map<String, Object> params){
		LogConstant.runLog.info("[JiekouSearchCarModel]parameter license_no:" + params.get("license_no") + ", license_owner:" + params.get("license_owner") + ", city_code:" + params.get("city_code"));
		String result = null;
		if(params.get("license_no")==null || params.get("license_owner")==null|| params.get("city_code")==null){
			return "传递参数不正确";
		}
		result = searchCarModelMockServiceImpl.getResult(params.get("license_no").toString(), params.get("license_owner").toString(), params.get("city_code").toString());
		return result;
	}
}
