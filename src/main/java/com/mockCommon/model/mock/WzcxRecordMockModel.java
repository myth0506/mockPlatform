package com.mockCommon.model.mock;

public class WzcxRecordMockModel {
	/*
		date	String	违章时间
	    area	String	违章地点
	    act	String	违章行为
	    code	String	违章代码(仅供参考)
	    fen	String	违章扣分(仅供参考)
	    money	String	违章罚款(仅供参考)
	    handled	String	是否处理
	    PayNo	String	交款编号
	    longitude	Double	经度
	    latitude	Double	纬度
    */
	private String date;
	private String area;
	private String act;
	private String code;
	private String fen;
	private String money;
	private String handled;
	private String PayNo;
	private double longitude;
	private double latitude;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFen() {
		return fen;
	}
	public void setFen(String fen) {
		this.fen = fen;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getHandled() {
		return handled;
	}
	public void setHandled(String handled) {
		this.handled = handled;
	}
	public String getPayNo() {
		return PayNo;
	}
	public void setPayNo(String payNo) {
		PayNo = payNo;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString() {
		return "WzcxRecordMockModel [date=" + date + ", area=" + area
				+ ", act=" + act + ", code=" + code + ", fen=" + fen
				+ ", money=" + money + ", handled=" + handled + ", PayNo="
				+ PayNo + ", longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}
	
}