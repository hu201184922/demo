package com.ehuatai.biz.domain;

public class ChartSerie {

	private String type;//图形类型
    private String name;//图形名称
    private String code;//标识 一般是指标代码
    private String unit;//单位
    private String stack;//分组堆叠图标识
    private String color;//颜色信息
    private Object data;//数据 可能的值/一维数组/二位数组/一维数组对象
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
}
