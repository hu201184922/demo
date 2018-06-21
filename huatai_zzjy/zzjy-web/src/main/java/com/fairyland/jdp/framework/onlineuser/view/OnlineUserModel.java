package com.fairyland.jdp.framework.onlineuser.view;

import java.util.Date;

import org.apache.shiro.session.Session;

import com.fairyland.jdp.framework.security.domain.User;

public class OnlineUserModel {

	public OnlineUserModel() {
	}

	public OnlineUserModel(Session session) {
		this.host=session.getHost();
		this.sessionId=session.getId().toString();
		this.startTimestamp=session.getStartTimestamp();
		this.lastAccessTime=session.getLastAccessTime();
	}

	private String loginName;

	private String name;

	private String org;

	private String host;

	private String sessionId;

	private Date lastAccessTime;// 最近一次访问时间

	private Date startTimestamp;// 会话开始时间

	private Long timeout;// 会话超时

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Date getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}
}
