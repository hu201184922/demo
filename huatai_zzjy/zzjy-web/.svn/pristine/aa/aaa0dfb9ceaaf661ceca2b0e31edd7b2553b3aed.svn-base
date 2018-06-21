package com.huatai.web.bean;


import java.util.List;

import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ChartBean {

	private String type;
	
	private String name;
	
	@Transient
	@JsonIgnore
	@JSONField(serialize=false)
	private Integer ratio;
	
	private List<ChartData> data;
	
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
	public Integer getRatio() {
		return ratio;
	}
	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}
	public List<ChartData> getData() {
		return data;
	}
	public void setData(List<ChartData> num) {
		this.data = num;
	}
}