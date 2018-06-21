package com.huatai.web.bean;

/**
 * @功能 {指标区域数据传输模型}
 * @作者 MaxBill
 * @时间 2017年7月19日 上午10:53:59
 */
public class TarRegBean {

	private String sql;
	private String title;
	private String name;
	private String type;
	private String x;
	private String y;
	private String unit;
	private String color;
	private String code;
	private String target;
	private String data;
	private String stack;
	private String dateType;
	private Boolean drilldown;
	private Boolean hasInfo;
	private Boolean torg;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public Boolean getHasInfo() {
		return hasInfo;
	}

	public void setHasInfo(Boolean hasInfo) {
		this.hasInfo = hasInfo;
	}

	public Boolean getDrilldown() {
		return drilldown;
	}

	public void setDrilldown(Boolean drilldown) {
		this.drilldown = drilldown;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean getTorg() {
		return torg;
	}

	public void setTorg(Boolean torg) {
		this.torg = torg;
	}

}
