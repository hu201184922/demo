package com.huatai.web.bean;

import java.util.List;

public class TargetData {
	private String name;
	private String stacking;
	private String xAxis;
	private List<TarRegBean> series;

	public List<TarRegBean> getSeries() {
		return series;
	}

	public void setSeries(List<TarRegBean> series) {
		this.series = series;
	}

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

	public String getxAxis() {
		return xAxis;
	}

	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

}
