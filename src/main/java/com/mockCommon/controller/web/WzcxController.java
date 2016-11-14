package com.mockCommon.controller.web;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockCommon.constant.CommonConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.dao.CommonDaoImpl;
import com.mockCommon.model.web.Wzcx;
import com.mockCommon.model.web.WzcxInfoModel;
import com.mockCommon.model.web.WzcxRecordModel;
import com.mockCommon.service.web.WzcxService;
import com.mockCommon.util.NullOrEmpty;

@Controller
public class WzcxController {

	@Autowired
	private WzcxService wzcxService;

	@Resource(name = "commonDaoImpl")
	private CommonDaoImpl commonDaoImpl;

	@RequestMapping("/InsrWzcx/selectWzStatus")
	@ResponseBody
	public Map<String, Object> selectWzStatus() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("api_code", "INSR_WZCX");
		List<Wzcx> list = wzcxService.selectWzcxStatus(paramMap);
		LogConstant.runLog.info("违章查询的状态为：" + list);
		if (list == null || list.size() == 0) {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "违章查询状态加载失败");
		} else {
			retMap.put("retCode", "200");
			retMap.put("retDesc", list);
		}
		return retMap;
	}

	@RequestMapping("/InsrWzcx/updateWzStatus")
	@ResponseBody
	public Map<String, Object> updateWzStatus(
			@RequestParam("statusId") String statusId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("responseId", statusId);
		int retCode = wzcxService.updateWzStatus(paramMap);
		if (retCode > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "违章状态设置成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "违章状态设置失败");
		}
		return retMap;
	}

	@RequestMapping("/InsrWzcx/searchWzcx")
	@ResponseBody
	public Map<String, Object> searchWzcx(
			@RequestParam("wzcx_car_num") String wzcx_car_num) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("wzcx_car_num", wzcx_car_num);
		WzcxInfoModel wzcxInfoModel = wzcxService.searchWzcxInfoModel(paramMap);
		List<WzcxRecordModel> wzcxRecordModelList = null;
		if (wzcxInfoModel != null && wzcxInfoModel.getWzcx_sfwz() == 1) {
			paramMap.clear();
			paramMap.put("wzcx_id", wzcxInfoModel.getWzcx_id());
			wzcxRecordModelList = wzcxService.searchWzcxRecordModel(paramMap);
		}
		if (wzcxInfoModel != null) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "违章查询成功");
			retMap.put("retWzcxInfo", wzcxInfoModel);
			LogConstant.runLog.info("违章查询的信息为：" + wzcxInfoModel);
			if (wzcxRecordModelList != null && wzcxRecordModelList.size() > 0) {
				retMap.put("retWzcxRecord", wzcxRecordModelList);
				LogConstant.runLog.info("违章查询的记录为：" + wzcxRecordModelList);
			}
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "违章查询失败");
		}
		return retMap;
	}

	@RequestMapping("/InsrWzcx/searchWzcxRecord")
	@ResponseBody
	public Map<String, Object> searchWzcxRecord(
			@RequestParam("record_id") String record_id) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("record_id", record_id);
		WzcxRecordModel wzcxRecordModel = wzcxService
				.searchWzcxRecordByRecordId(paramMap);

		if (wzcxRecordModel != null) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "违章查询成功");

			if (wzcxRecordModel != null) {
				retMap.put("retWzcxRecord", wzcxRecordModel);
				LogConstant.runLog.info("违章查询的记录为：" + wzcxRecordModel);
			}
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "违章查询失败");
		}
		return retMap;
	}

	@RequestMapping("/InsrWzcx/submitWzcx")
	@ResponseBody
	public Map<String, Object> submitWzcx(
			@RequestParam("wzcx_car_num") String wzcx_car_num,
			@RequestParam("wzcx_status") int wzcx_status,
			@RequestParam("wzcx_sfwz") int wzcx_sfwz) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("wzcx_car_num", wzcx_car_num);
		paramMap.put("wzcx_status", wzcx_status);
		paramMap.put("wzcx_sfwz", wzcx_sfwz);
		int status = 0;
		WzcxInfoModel wzcxInfo = wzcxService.searchWzcxInfoModel(paramMap);
		String wzcx_id = "";
		if(wzcxInfo != null){
			wzcx_id = wzcxInfo.getWzcx_id();
			status = wzcxService
					.updateWzcxInfo(paramMap);
		}else {
			WzcxInfoModel wzcxInfoModel = new WzcxInfoModel();
			wzcxInfoModel.setWzcx_car_num(wzcx_car_num);
			wzcx_id = commonDaoImpl
					.querySeqId(CommonConstant.WZCX_INFO_PREFIX);
			wzcxInfoModel.setWzcx_id(wzcx_id);
			wzcxInfoModel.setWzcx_status(wzcx_status);
			wzcxInfoModel.setWzcx_sfwz(wzcx_sfwz);
			status = wzcxService
					.insertWzcxInfo(wzcxInfoModel);
		}

		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "提交违章查询成功");
			retMap.put("wzcx_id", wzcx_id);
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "提交违章查询失败");
		}
		return retMap;
	}

	@RequestMapping("/InsrWzcx/saveRecord")
	@ResponseBody
	public Map<String, Object> saveRecord(
			@RequestParam("record_id") String record_id,
			@RequestParam("record_wzsj") String record_wzsj,
			@RequestParam("record_wzdd") String record_wzdd,
			@RequestParam("record_wzyy") String record_wzyy,
			@RequestParam("record_fk") String record_fk,
			@RequestParam("record_kf") String record_kf,
			@RequestParam("record_wzzt") String record_wzzt) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("record_id", record_id);
		Format simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (record_wzsj != null && !record_wzsj.equals("")) {
				Date startDate = (Date) simpleDateFormat
						.parseObject(record_wzsj);
				Timestamp wzsj = new Timestamp(startDate.getTime());
				paramMap.put("record_wzsj", wzsj);
			}
		} catch (ParseException e) {
			LogConstant.runLog.info("Date ParseException：" + e);
		}
		paramMap.put("record_wzdd", record_wzdd);
		paramMap.put("record_wzyy", record_wzyy);
		try {
			double fk = Double.parseDouble(record_fk);
			paramMap.put("record_fk", fk);
			int kf = Integer.parseInt(record_kf);
			paramMap.put("record_kf", kf);
			int wzzt = Integer.parseInt(record_wzzt);
			paramMap.put("record_wzzt", wzzt);
		} catch (NumberFormatException e) {
			LogConstant.runLog.info("Date ParseException：" + e);
		}
		int status = wzcxService.updateWzcxRecord(paramMap);

		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "违章查询成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "违章查询失败");
		}
		return retMap;
	}

	@RequestMapping("/InsrWzcx/insertRecord")
	@ResponseBody
	public Map<String, Object> insertRecord(
			@RequestParam("wzcx_id") String wzcx_id,
			@RequestParam("record_wzsj") String record_wzsj,
			@RequestParam("record_wzdd") String record_wzdd,
			@RequestParam("record_wzyy") String record_wzyy,
			@RequestParam("record_fk") String record_fk,
			@RequestParam("record_kf") String record_kf,
			@RequestParam("record_wzzt") String record_wzzt) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		WzcxRecordModel wzcxRecordModel = new WzcxRecordModel();
		wzcxRecordModel.setWzcx_id(wzcx_id);
		String record_id = commonDaoImpl
				.querySeqId(CommonConstant.WZCX_RECORD_PREFIX);
		wzcxRecordModel.setRecord_id(record_id);
		Format simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (!NullOrEmpty.isNullOrEmpty(record_wzsj)) {
				Date startDate = (Date) simpleDateFormat
						.parseObject(record_wzsj);
				Timestamp wzsj = new Timestamp(startDate.getTime());
				wzcxRecordModel.setRecord_wzsj(wzsj);
			}
		} catch (ParseException e) {
			LogConstant.runLog.info("Date ParseException：" + e);
		}
		wzcxRecordModel.setRecord_wzdd(record_wzdd);
		wzcxRecordModel.setRecord_wzyy(record_wzyy);
		try {
			double fk = Double.parseDouble(record_fk);
			wzcxRecordModel.setRecord_fk(fk);
			int kf = Integer.parseInt(record_kf);
			wzcxRecordModel.setRecord_kf(kf);
			int wzzt = Integer.parseInt(record_wzzt);
			wzcxRecordModel.setRecord_wzzt(wzzt);
		} catch (NumberFormatException e) {
			LogConstant.runLog.info("Data ParseException：" + e);
		}
		wzcxRecordModel.setRecord_status(1);
		int status = wzcxService.insertWzcxRecord(wzcxRecordModel);

		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "违章记录添加成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "违章记录添加失败");
		}
		return retMap;
	}

	@RequestMapping("/InsrWzcx/deleteRecord")
	@ResponseBody
	public Map<String, Object> deleteRecord(
			@RequestParam("record_id") String record_id) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("record_id", record_id);
		int status = wzcxService.deleteRecord(paramMap);

		if (status > 0) {
			retMap.put("retCode", "200");
			retMap.put("retDesc", "违章查询成功");
		} else {
			retMap.put("retCode", "-1");
			retMap.put("retDesc", "违章查询失败");
		}
		return retMap;
	}
}