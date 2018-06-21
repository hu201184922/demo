package com.huatai.web.bean;

/**
 * @功能 {预警数据传输实体}
 * @作者 MaxBill
 * @时间 2017年7月27日 下午1:23:51
 */
public class WarnBean {

	private Integer id;

	private String org;

	private Object target;

	private Object term;

	private String type;

	private String status;
	
	private String targetCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Object getTerm() {
		return term;
	}

	public void setTerm(Object term) {
		this.term = term;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	
}
