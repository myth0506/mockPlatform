package com.mockCommon.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mockCommon.constant.AcceptShopOrder;
import com.mockCommon.constant.IdType;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String domain() {
		return "index";
	}
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	//违章查询
	@RequestMapping("/insurance/wzcx")
	public String wzcxIndex(){
		return "/insurance/wzcx";
	}
	
	//--------------平安保险--------------
	//续保用户和生日校验
	@RequestMapping("/insurance/pingan/xbsr")
	public String xbsrIndex(){
		return "/insurance/pingan/xbsr";
	}
	
	//查询车辆信息
	@RequestMapping("/insurance/pingan/searchCarInfo")
	public String searchCarInfoIndex(ModelMap map){
		map.put("idTypeList", IdType.getAllTypes());
		return "/insurance/pingan/searchCarInfo";
	}
	
	//报价接口配置
	@RequestMapping("/insurance/pingan/baojia")
	public String baoJia(){
		return "/insurance/pingan/baojia";
	}
	
	/*//商业保险报价配置
	@RequestMapping("/insurance/pingan/shybxpz")
	public String shangyeBaoxian(){
		return "/insurance/pingan/shybxpz";
	}*/
		
	//交强险报价设置
	@RequestMapping("/insurance/pingan/jqxbj")
	public String jqxbjIndex(){
		return "/insurance/pingan/jqxbj";
	}
	
	//获取配送地址接口
	@RequestMapping("/insurance/pingan/sendPolicyAddress")
	public String sendPolicyAddressIndex(){
		return "/insurance/pingan/sendPolicyAddress";
	}
	
	//获取特别约定接口
	@RequestMapping("/insurance/pingan/getSpecialPromise")
	public String getSpecialPromiseIndex(){
		return "/insurance/pingan/getSpecialPromise";
	}
	
	//核保接口和手机验证
	@RequestMapping("/insurance/pingan/toAudit")
	public String toAuditIndex(){
		return "/insurance/pingan/toAudit";
	}
	
	//支付检查接口和出单
	@RequestMapping("/insurance/pingan/policyAndOrder")
	public String queryPolicyStatusIndex(ModelMap map){
		map.put("orderStatusList", AcceptShopOrder.getAllTypes());
		return "/insurance/pingan/policyAndOrder";
	}
	
	//--------------阳光保险--------------
	//续保用户
	@RequestMapping("/insurance/yangguang/xubao")
	public String xubaoIndex(){
		return "/insurance/yangguang/xubao";
	}
	
	//查询车辆信息
	@RequestMapping("/insurance/yangguang/searchCarInfo")
	public String searchCarInfoIndex(){
		return "/insurance/yangguang/searchCarInfo";
	}
	
	//交强险报价
	@RequestMapping("/insurance/yangguang/jqxbj")
	public String jqxIndex(){
		return "/insurance/yangguang/jqxbj";
	}
	
	//报价接口配置
	@RequestMapping("/insurance/yangguang/baojia")
	public String baoJiaIndex(){
		return "/insurance/yangguang/baojia";
	}
	
	//核保接口和手机验证
	@RequestMapping("/insurance/yangguang/toAudit")
	public String sunshineToAuditIndex(){
		return "/insurance/yangguang/toAudit";
	}
	
	//支付检查接口和出单
	@RequestMapping("/insurance/yangguang/policyAndOrder")
	public String policyAndOrderIndex(ModelMap map){
		return "/insurance/yangguang/policyAndOrder";
	}
	
	//----------------优比接口-------------------------
	//获取城市配置
	@RequestMapping("/insurance/youbi/cityConfig")
	public String cityConfigIndex(ModelMap map){
		return "/insurance/youbi/cityConfig";
	}
	
	//查询车型
	@RequestMapping("/insurance/youbi/searchCarModel")
	public String searchCarModel(ModelMap map){
		return "/insurance/youbi/searchCarModel";
	}
	
	//输入车型
		@RequestMapping("/insurance/youbi/inputCarModel")
		public String inputCarModel(ModelMap map){
			return "/insurance/youbi/inputCarModel";
		}
	
	//创建报价请求
	@RequestMapping("/insurance/youbi/cjbjqq")
	public String cjbjqq(ModelMap map){
		return "/insurance/youbi/cjbjqq";
	}
	
	//获取报价
		@RequestMapping("/insurance/youbi/getBaoJiaConfig")
		public String getBaoJiaConfig(ModelMap map){
			return "/insurance/youbi/getBaoJiaConfig";
		}
		
	//商业险报价配置
	@RequestMapping("/insurance/youbi/syxbj")
	public String syxbjIndex(ModelMap map) {
		return "/insurance/youbi/syxbj";
	}
	
	// 支付
	@RequestMapping("/insurance/pay")
	public String pay(){
		return "/insurance/pay";
	}
	
	// 充值
	@RequestMapping("/insurance/charge")
	public String charge(){
		return "/insurance/charge";
	}
}