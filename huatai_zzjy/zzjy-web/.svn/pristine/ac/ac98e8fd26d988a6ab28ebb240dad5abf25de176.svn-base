package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TriggleExecute extends TriggleExecuteKey {

	private String code;

	private String name;

	private String execStatus;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date execBeginTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date execEndTime;

	private String resTime;

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

	public String getExecStatus() {
		return execStatus;
	}

	public void setExecStatus(String execStatus) {
		this.execStatus = execStatus == null ? null : execStatus.trim();
	}

	public Date getExecBeginTime() {
		return execBeginTime;
	}

	public void setExecBeginTime(Date execBeginTime) {
		this.execBeginTime = execBeginTime;
	}

	public Date getExecEndTime() {
		return execEndTime;
	}

	public void setExecEndTime(Date execEndTime) {
		this.execEndTime = execEndTime;
	}

	public String getResTime() {
		return resTime;
	}

	public void setResTime(String resTime) {
		this.resTime = resTime;
	}
}