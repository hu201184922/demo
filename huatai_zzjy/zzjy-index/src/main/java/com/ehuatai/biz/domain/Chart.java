package com.ehuatai.biz.domain;

import java.util.List;

public class Chart {
	private String name;//图形名称
	
	private String stacking;//堆叠图标识，若为堆叠图固定为normal
	
	private Object xAxis;//可能的值有数组/null/category
	
	private List<ChartSerie>series;//数据列

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStacking() {
		return stacking;
	}

	public void setStacking(String stacking) {
		this.stacking = stacking;
	}

	public Object getxAxis() {
		return xAxis;
	}

	public void setxAxis(Object xAxis) {
		this.xAxis = xAxis;
	}

	public List<ChartSerie> getSeries() {
		return series;
	}

	public void setSeries(List<ChartSerie> series) {
		this.series = series;
	}
	
}
