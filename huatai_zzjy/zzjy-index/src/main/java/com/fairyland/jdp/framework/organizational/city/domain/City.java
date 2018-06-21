package com.fairyland.jdp.framework.organizational.city.domain;

/**
 * 城市类
 * @author jiangbingbin
 */
public class City {
	
	private long lv; // 级别 1全国 2城市
	private String cityCode; //code
	private String cityName; //原名
	private String shortName; //缩写
	private String enable; //是否可用 0不可用 1可用
	
	public long getLv() {
		return lv;
	}
	public void setLv(long lv) {
		this.lv = lv;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}

}
