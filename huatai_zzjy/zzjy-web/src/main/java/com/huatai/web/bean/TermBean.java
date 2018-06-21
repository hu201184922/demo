package com.huatai.web.bean;

/**
 * @功能 {条件公式数据实体}
 * @作者 MaxBill
 * @时间 2017年8月26日 下午2:42:39
 */
public class TermBean {

	public TermBean(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public TermBean(Boolean checked, Object value) {
		super();
		this.checked = checked;
		this.value = value;
	}

	public TermBean(String name, String code, Boolean checked, Object value) {
		super();
		this.name = name;
		this.code = code;
		this.checked = checked;
		this.value = value;
	}

	private String code;

	private String name;

	private Boolean checked;

	private Object value;

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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
