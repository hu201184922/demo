package com.fairyland.jdp.framework.security.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "JDP_LOGIN_TOKEN")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_LOGIN_TOKEN_ID", table = "jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE")
public class LoginToken implements Serializable {
	private static final long serialVersionUID = 7741727258909213406L;
	private String accessToken;
	private String authInfo;
	private Date expireTime;
	
	@Id
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(String authInfo) {
		this.authInfo = authInfo;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
	
}
