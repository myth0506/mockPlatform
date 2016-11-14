package com.mockCommon.service.mock.youbi.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.constant.SessionKey;
import com.mockCommon.model.mock.youbi.GetBaoJiaMockModel;
import com.mockCommon.model.mock.youbi.LastYearTouBaoMockModel;
import com.mockCommon.model.web.BusinessConfig;
import com.mockCommon.model.web.BusinessIni;
import com.mockCommon.model.web.youbi.BusinessInfoModel;
import com.mockCommon.service.mock.youbi.GetBaoJiaConfigMockService;
import com.mockCommon.service.web.youbi.YouBiBaoJiaConfigService;
import com.mockCommon.service.web.youbi.YoubiIniService;
import com.mockCommon.util.freeMarker.IDataMaker;
import com.netease.common.util.StringUtil;

@Service
public class GetBaoJiaConfigMockServiceImpl implements GetBaoJiaConfigMockService{

	@Autowired
	private YoubiIniService youbiIniService;
	
    @Autowired
    private IDataMaker<GetBaoJiaMockModel> getBaoJiaConfigFreeMarker;
    
    @Autowired
    private IDataMaker<LastYearTouBaoMockModel> lastYearTouBaoFreeMarker;
    
    @Autowired
    private YouBiBaoJiaConfigService youBiBaoJiaConfigService;
    
	@SuppressWarnings("static-access")
	@Override
	public String getBaoJiaConfig(String request_id) {
		
		GetBaoJiaMockModel getBaoJia = new GetBaoJiaMockModel();
		
		//设置保险种类的数据
		List<BusinessInfoModel> baoJiaConfigList = youBiBaoJiaConfigService.queryBaoJiaConfig();
		getBaoJia.setBusinessInfoModel(baoJiaConfigList);
		
		for(int i = 0; i < baoJiaConfigList.size(); i++){
			if ((Double)baoJiaConfigList.get(i).getBaofei()==null){
				baoJiaConfigList.get(i).setBaofei(0);
			}
		}
		
		//设置key-value的值
		BusinessIni bi;
		bi = youbiIniService.selectIni(SessionKey.SFFG);
		if (bi != null){
			getBaoJia.setFgRadio(bi.getIniValue());
		}
		int fg = Integer.parseInt(bi.getIniValue());
		bi = youbiIniService.selectIni(SessionKey.JQXBF);
		double jqxbf = 0;
		double ccs = 0;
		if (bi != null){
			getBaoJia.setJqxbf(bi.getIniValue());
			jqxbf = Integer.parseInt(bi.getIniValue());
		}
		//设置车船税
		bi = youbiIniService.selectIni(SessionKey.CCS);
		if (bi != null){
			getBaoJia.setCcs(bi.getIniValue());
			ccs = Integer.parseInt(bi.getIniValue());
		}
		
		//设置支付信息里面的数据
		double biz_total = 0;
		double biz_original = 0;
		double original = 0;
		for (int i = 0; i < baoJiaConfigList.size(); i++){
		LogConstant.runLog.info("baoJiaConfigList.get(i).getIs_checked()"+baoJiaConfigList.get(i).getIs_checked());
			if (baoJiaConfigList.get(i).getIs_checked().equals("true")
					&& baoJiaConfigList.get(i).getIs_valid().equals("true")
					&& baoJiaConfigList.get(i).getIs_vote().equals("true")) {
			double a = baoJiaConfigList.get(i).getBaofei();
			double b = 0;
			    if((Double)a!=null){
				 b = a;
				}else{
			     b=0;
				}
			double baofei = b;
			biz_total = biz_total + baofei;
			}
		}
		biz_original = biz_total;
		double total = biz_total + jqxbf + ccs;
	    original = biz_original + jqxbf + ccs;
	
        if ( fg == 0){
        	 biz_original = biz_original/0.85;
        	 original = biz_original + jqxbf + ccs;
        }
        getBaoJia.setBiz_total(biz_total);
        getBaoJia.setBiz_original(biz_original);
        getBaoJia.setTotal(total);
        getBaoJia.setOriginal(original);
       	
		bi = youbiIniService.selectIni(SessionKey.JQXBF);
		if (bi != null){
			getBaoJia.setJqxbf(bi.getIniValue());
		}
		
		bi = youbiIniService.selectIni(SessionKey.JQXQNZBRQ);
		if (bi != null){
			getBaoJia.setJqxend(bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.JQXDQTS);
		if (bi != null){
			getBaoJia.setJqxdqts(bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.JQXSBXX);
		if (bi != null){
			getBaoJia.setJqxsbxx(bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.JQXSBXXMA);
		if (bi != null){
			getBaoJia.setJqxsbxxma(bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.BJKG);
		if (bi != null){
			getBaoJia.setBjkg(bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.SBJQXSBXX);
		if (bi != null){
			getBaoJia.setSbjqxsbxx(bi.getIniValue());
		}
		bi = youbiIniService.selectIni(SessionKey.SBJQXSBXXMA);
		if (bi != null){
			getBaoJia.setSbjqxsbxxma(bi.getIniValue());
		}
		
		//设置默认时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();//当前时间
    	Date tom = new Date();
    	Calendar cd = Calendar.getInstance();//得到日历
    	cd.setTime(date);//把当前时间赋给日历
        cd.add(cd.DAY_OF_MONTH, +1);
        tom = cd.getTime();
        String  defaultTime= sdf.format(tom);
        getBaoJia.setDefaultTime(defaultTime);
        
        //设置到期时间
        Date ny = new Date();
        cd.add(cd.YEAR,+1);
        cd.add(cd.DAY_OF_MONTH,-1);
        ny = cd.getTime();
        String  endTime= sdf.format(ny);
        getBaoJia.setEndTime(endTime);
        
        //设置discount
        double discount = 0.855;
        getBaoJia.setDiscount(discount);
        			
        //设置standrad
        String standrad = Double.toString(total/discount);
        getBaoJia.setStandrad(standrad);
        
        //设置商业险、交强险起保日期
        bi = youbiIniService.selectIni(SessionKey.SYXQBRQ);
		if (bi != null){
			getBaoJia.setSyxstart(bi.getIniValue());
		}
		
		bi = youbiIniService.selectIni(SessionKey.JQXQBRQ);
		if (bi != null){
			getBaoJia.setJqxstart(bi.getIniValue());
		}
           
		String result = null;
		result = getBaoJiaConfigFreeMarker.generateData2output(ContextConstant.PREFIX_YOUBIBAOJIA_CONFIG, getBaoJia);

		return result;
	}

	@Override
	public String getLastYearBaoJiaConfig(String request_id) {
		LastYearTouBaoMockModel model = new LastYearTouBaoMockModel();
		model.setStatus("1");
		List<BusinessConfig> selection = new ArrayList<BusinessConfig>();
		BusinessIni ini = youbiIniService.selectIni(SessionKey.XUBAOINFO);
		if(ini != null){
			String configIds = ini.getIniValue();
			if(!StringUtil.isEmpty(configIds)){
				String[] ids = configIds.split(",");
				List<String> list = Arrays.asList(ids);
				selection = youBiBaoJiaConfigService.queryConfigListByIds(list);
			}
		}
		model.setSelection(selection);
		String result = null;
		result = lastYearTouBaoFreeMarker.generateData2output(ContextConstant.PREFIX_YOUBILASTYEARTOUBAO_CONFIG, model);
		return result;
	}
}
