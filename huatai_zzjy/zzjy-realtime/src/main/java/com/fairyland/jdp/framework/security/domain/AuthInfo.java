package com.fairyland.jdp.framework.security.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthInfo implements Serializable{
	private static final long serialVersionUID = -854224231325988522L;
	private Long userId;
	private Integer isAdmin;
	private String accessToken;
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	private Date expireTime;
	@JsonIgnore
	private Set<String> resourceStrings;
	private Set<String> permStrings;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Set<String> getResourceStrings() {
		return resourceStrings;
	}

	public void setResourceStrings(Set<String> resourceStrings) {
		this.resourceStrings = resourceStrings;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Set<String> getPermStrings() {
		return permStrings;
	}

	public void setPermStrings(Set<String> permStrings) {
		this.permStrings = permStrings;
	}
	
}
