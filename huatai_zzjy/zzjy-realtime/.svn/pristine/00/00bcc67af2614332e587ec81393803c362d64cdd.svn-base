package com.fairyland.jdp.framework.security.domain;

public class JsonResult<T> {
	private int success;
	private String errorCode;
	private String errorMsg;
	private T value;
	
	public JsonResult() {
		this.success = 1;
	}
	public JsonResult(int success, String errorCode, String errorMsg, T value) {
		super();
		this.success = success;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.value = value;
	}
	public JsonResult(T value) {
		this.success = 1;
		this.value = value;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
	
}
