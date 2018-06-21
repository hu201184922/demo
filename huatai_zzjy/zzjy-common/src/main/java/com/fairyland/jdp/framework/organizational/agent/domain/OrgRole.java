package com.fairyland.jdp.framework.organizational.agent.domain;

/**
 * 组织权限类
 * @author jiangbingbin
 */
public class OrgRole {
	
	private long id;
	private String code; //权限code
	private String name; //权限名称
	private String tip;  //描述
	private String type; //类型 01内勤 02外勤
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
