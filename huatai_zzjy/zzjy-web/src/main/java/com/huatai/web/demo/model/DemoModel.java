package com.huatai.web.demo.model;

import java.io.Serializable;

public class DemoModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String code;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public DemoModel(Long id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
	
}
