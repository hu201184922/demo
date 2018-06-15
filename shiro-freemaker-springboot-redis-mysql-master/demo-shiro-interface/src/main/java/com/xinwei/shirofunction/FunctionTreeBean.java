package com.xinwei.shirofunction;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 展示功能Tree时使用。
 * @author ouburikou
 *
 */
@Slf4j
@Data
public class FunctionTreeBean {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return Pid;
	}
	public void setPid(Integer pid) {
		Pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	private Integer Pid;
	private String name;
	private boolean checked=false;
	private boolean open=true;
	
	
}
