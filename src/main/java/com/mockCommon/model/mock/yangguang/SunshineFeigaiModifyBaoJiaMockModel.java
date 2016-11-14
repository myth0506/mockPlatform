package com.mockCommon.model.mock.yangguang;

import com.mockCommon.model.web.yangguang.SunshineBaoJiaModel;
import com.mockCommon.util.freeMarker.DataTemplate;

/**
 * @author li_zhe
    SunshineBaoJiaModel 
	forceFlag	交强险
	forcePremium	交强险
	vehicleTaxPremium	车船税
	forceTotalPremium	交强险总保费
	bizTotalPremium	商业保费
	standardPremium	市场价(应缴总保费)
	totalPremium	网购价(实际保费)
	bizBeginDate	商业险保险起期
	forceBeginDate	交强险保险起期
	lastClaimText	上年出险信息
	presentVal	积分值
	hasAddPresent	是否有加投
	presentType	投保礼或UN积分标识
	isImmevalid	是否及时生效
	immeValidHoursStart	生效时间(小时)
 */
@DataTemplate(template = "sunshine_feigai_modify_baoJia.xml", output = "feigai_modify_baoJia_out.xml")
public class SunshineFeigaiModifyBaoJiaMockModel {
	private SunshineBaoJiaModel sunshineBaoJiaModel;
	private String packageType;	
	private String forceFlag;	
	private String forcePremium;
	private String vehicleTaxPremium;
	private String forceTotalPremium;
	private String bizTotalPremium;
	private String standardPremium;
	private String totalPremium;
	private String bizBeginDate;
	private String forceBeginDate;
	private String lastClaimText;
	private String presentVal;
	private String hasAddPresent;
	private String presentType;
	private String isImmevalid;
	private String immeValidHoursStart;
	
	// cov_xxx对应的value值
	private String cov200Value;
	private String cov600Value;
	private String cov500Value;
	private String cov701Value;
	private String cov702Value;
	private String cov321Value;
	private String cov310Value;
	private String cov231Value;
	private String cov210Value;
	private String cov390Value;
	private String cov291Value;
	private String cov640Value;
	private String cov921Value;
	private String cov922Value;
	private String cov911Value;
	private String cov912Value;
	private String cov928Value;
	private String cov929Value;
	
	private String cov734Value;
	private String cov731Value;
	private String cov732Value;
	private String cov733Value;
	
	//cov_xxx对应的下拉选项
	private String cov200Data;
	private String cov600Data;
	private String cov500Data;
	private String cov701Data;
	private String cov702Data;
	private String cov321Data;
	private String cov310Data;
	private String cov231Data;
	private String cov210Data;
	private String cov390Data;
	private String cov291Data;
	private String cov640Data;
	private String cov921Data;
	private String cov922Data;
	private String cov911Data;
	private String cov912Data;
	private String cov928Data;
	private String cov929Data;
	
	private String cov734Data;
	private String cov733Data;
	
	public SunshineBaoJiaModel getSunshineBaoJiaModel() {
		return sunshineBaoJiaModel;
	}
	public void setSunshineBaoJiaModel(SunshineBaoJiaModel sunshineBaoJiaModel) {
		this.sunshineBaoJiaModel = sunshineBaoJiaModel;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getForceFlag() {
		return forceFlag;
	}
	public void setForceFlag(String forceFlag) {
		this.forceFlag = forceFlag;
	}
	public String getForcePremium() {
		return forcePremium;
	}
	public void setForcePremium(String forcePremium) {
		this.forcePremium = forcePremium;
	}
	public String getVehicleTaxPremium() {
		return vehicleTaxPremium;
	}
	public void setVehicleTaxPremium(String vehicleTaxPremium) {
		this.vehicleTaxPremium = vehicleTaxPremium;
	}
	public String getForceTotalPremium() {
		return forceTotalPremium;
	}
	public void setForceTotalPremium(String forceTotalPremium) {
		this.forceTotalPremium = forceTotalPremium;
	}
	public String getBizTotalPremium() {
		return bizTotalPremium;
	}
	public void setBizTotalPremium(String bizTotalPremium) {
		this.bizTotalPremium = bizTotalPremium;
	}
	public String getStandardPremium() {
		return standardPremium;
	}
	public void setStandardPremium(String standardPremium) {
		this.standardPremium = standardPremium;
	}
	public String getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getBizBeginDate() {
		return bizBeginDate;
	}
	public void setBizBeginDate(String bizBeginDate) {
		this.bizBeginDate = bizBeginDate;
	}
	public String getForceBeginDate() {
		return forceBeginDate;
	}
	public void setForceBeginDate(String forceBeginDate) {
		this.forceBeginDate = forceBeginDate;
	}
	public String getLastClaimText() {
		return lastClaimText;
	}
	public void setLastClaimText(String lastClaimText) {
		this.lastClaimText = lastClaimText;
	}
	public String getPresentVal() {
		return presentVal;
	}
	public void setPresentVal(String presentVal) {
		this.presentVal = presentVal;
	}
	public String getHasAddPresent() {
		return hasAddPresent;
	}
	public void setHasAddPresent(String hasAddPresent) {
		this.hasAddPresent = hasAddPresent;
	}
	public String getPresentType() {
		return presentType;
	}
	public void setPresentType(String presentType) {
		this.presentType = presentType;
	}
	public String getIsImmevalid() {
		return isImmevalid;
	}
	public void setIsImmevalid(String isImmevalid) {
		this.isImmevalid = isImmevalid;
	}
	public String getImmeValidHoursStart() {
		return immeValidHoursStart;
	}
	public void setImmeValidHoursStart(String immeValidHoursStart) {
		this.immeValidHoursStart = immeValidHoursStart;
	}
	public String getCov200Value() {
		return cov200Value;
	}
	public void setCov200Value(String cov200Value) {
		this.cov200Value = cov200Value;
	}
	public String getCov600Value() {
		return cov600Value;
	}
	public void setCov600Value(String cov600Value) {
		this.cov600Value = cov600Value;
	}
	public String getCov500Value() {
		return cov500Value;
	}
	public void setCov500Value(String cov500Value) {
		this.cov500Value = cov500Value;
	}
	public String getCov701Value() {
		return cov701Value;
	}
	public void setCov701Value(String cov701Value) {
		this.cov701Value = cov701Value;
	}
	public String getCov702Value() {
		return cov702Value;
	}
	public void setCov702Value(String cov702Value) {
		this.cov702Value = cov702Value;
	}
	public String getCov321Value() {
		return cov321Value;
	}
	public void setCov321Value(String cov321Value) {
		this.cov321Value = cov321Value;
	}
	public String getCov310Value() {
		return cov310Value;
	}
	public void setCov310Value(String cov310Value) {
		this.cov310Value = cov310Value;
	}
	public String getCov231Value() {
		return cov231Value;
	}
	public void setCov231Value(String cov231Value) {
		this.cov231Value = cov231Value;
	}
	public String getCov210Value() {
		return cov210Value;
	}
	public void setCov210Value(String cov210Value) {
		this.cov210Value = cov210Value;
	}
	public String getCov390Value() {
		return cov390Value;
	}
	public void setCov390Value(String cov390Value) {
		this.cov390Value = cov390Value;
	}
	public String getCov291Value() {
		return cov291Value;
	}
	public void setCov291Value(String cov291Value) {
		this.cov291Value = cov291Value;
	}
	public String getCov640Value() {
		return cov640Value;
	}
	public void setCov640Value(String cov640Value) {
		this.cov640Value = cov640Value;
	}
	public String getCov921Value() {
		return cov921Value;
	}
	public void setCov921Value(String cov921Value) {
		this.cov921Value = cov921Value;
	}
	public String getCov922Value() {
		return cov922Value;
	}
	public void setCov922Value(String cov922Value) {
		this.cov922Value = cov922Value;
	}
	public String getCov911Value() {
		return cov911Value;
	}
	public void setCov911Value(String cov911Value) {
		this.cov911Value = cov911Value;
	}
	public String getCov912Value() {
		return cov912Value;
	}
	public void setCov912Value(String cov912Value) {
		this.cov912Value = cov912Value;
	}
	public String getCov928Value() {
		return cov928Value;
	}
	public void setCov928Value(String cov928Value) {
		this.cov928Value = cov928Value;
	}
	public String getCov929Value() {
		return cov929Value;
	}
	public void setCov929Value(String cov929Value) {
		this.cov929Value = cov929Value;
	}
	public String getCov200Data() {
		return cov200Data;
	}
	public void setCov200Data(String cov200Data) {
		this.cov200Data = cov200Data;
	}
	public String getCov600Data() {
		return cov600Data;
	}
	public void setCov600Data(String cov600Data) {
		this.cov600Data = cov600Data;
	}
	public String getCov500Data() {
		return cov500Data;
	}
	public void setCov500Data(String cov500Data) {
		this.cov500Data = cov500Data;
	}
	public String getCov701Data() {
		return cov701Data;
	}
	public void setCov701Data(String cov701Data) {
		this.cov701Data = cov701Data;
	}
	public String getCov702Data() {
		return cov702Data;
	}
	public void setCov702Data(String cov702Data) {
		this.cov702Data = cov702Data;
	}
	public String getCov321Data() {
		return cov321Data;
	}
	public void setCov321Data(String cov321Data) {
		this.cov321Data = cov321Data;
	}
	public String getCov310Data() {
		return cov310Data;
	}
	public void setCov310Data(String cov310Data) {
		this.cov310Data = cov310Data;
	}
	public String getCov231Data() {
		return cov231Data;
	}
	public void setCov231Data(String cov231Data) {
		this.cov231Data = cov231Data;
	}
	public String getCov210Data() {
		return cov210Data;
	}
	public void setCov210Data(String cov210Data) {
		this.cov210Data = cov210Data;
	}
	public String getCov390Data() {
		return cov390Data;
	}
	public void setCov390Data(String cov390Data) {
		this.cov390Data = cov390Data;
	}
	public String getCov291Data() {
		return cov291Data;
	}
	public void setCov291Data(String cov291Data) {
		this.cov291Data = cov291Data;
	}
	public String getCov640Data() {
		return cov640Data;
	}
	public void setCov640Data(String cov640Data) {
		this.cov640Data = cov640Data;
	}
	public String getCov921Data() {
		return cov921Data;
	}
	public void setCov921Data(String cov921Data) {
		this.cov921Data = cov921Data;
	}
	public String getCov922Data() {
		return cov922Data;
	}
	public void setCov922Data(String cov922Data) {
		this.cov922Data = cov922Data;
	}
	public String getCov911Data() {
		return cov911Data;
	}
	public void setCov911Data(String cov911Data) {
		this.cov911Data = cov911Data;
	}
	public String getCov912Data() {
		return cov912Data;
	}
	public void setCov912Data(String cov912Data) {
		this.cov912Data = cov912Data;
	}
	public String getCov928Data() {
		return cov928Data;
	}
	public void setCov928Data(String cov928Data) {
		this.cov928Data = cov928Data;
	}
	public String getCov929Data() {
		return cov929Data;
	}
	public void setCov929Data(String cov929Data) {
		this.cov929Data = cov929Data;
	}
	public String getCov734Value() {
		return cov734Value;
	}
	public void setCov734Value(String cov734Value) {
		this.cov734Value = cov734Value;
	}
	public String getCov731Value() {
		return cov731Value;
	}
	public void setCov731Value(String cov731Value) {
		this.cov731Value = cov731Value;
	}
	public String getCov732Value() {
		return cov732Value;
	}
	public void setCov732Value(String cov732Value) {
		this.cov732Value = cov732Value;
	}
	public String getCov733Value() {
		return cov733Value;
	}
	public void setCov733Value(String cov733Value) {
		this.cov733Value = cov733Value;
	}
	public String getCov734Data() {
		return cov734Data;
	}
	public void setCov734Data(String cov734Data) {
		this.cov734Data = cov734Data;
	}
	public String getCov733Data() {
		return cov733Data;
	}
	public void setCov733Data(String cov733Data) {
		this.cov733Data = cov733Data;
	}
}