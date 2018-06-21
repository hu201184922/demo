package com.fairyland.jdp.framework.onlineuser.domain;

import java.util.Date;

import com.fairyland.jdp.framework.security.domain.User;

public class OnlineUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 869583625954517734L;

	private Long id;
	
	private String host;
	
	private String sessionId;
	
	private Date lastAccessTime;
	
	private Date startTimestamp;
	
	private Long timeout;
	
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	
}
