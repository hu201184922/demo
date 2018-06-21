package com.ehuatai.biz.domain;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.framework.util.Base64Util;

public class User {

	private String username;	//用户code
	
	private String nick;		//用户名称
	
	private String token;		//token
	
	private String role;
	
	private String roleOrg;
	
	private String roleDept;
	
	private List<Role> roles;
	
	private String eqp;
	
	private String version;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleOrg() {
		return roleOrg;
	}

	public void setRoleOrg(String roleOrg) {
		this.roleOrg = roleOrg;
	}

	public String getRoleDept() {
		return roleDept;
	}

	public void setRoleDept(String roleDept) {
		this.roleDept = roleDept;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	

	public String getEqp() {
		return eqp;
	}

	public void setEqp(String eqp) {
		this.eqp = eqp;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public static void main(String[] args) {
		User user = new User();
		user.setUsername("10000066");
		user.setRole("1_100101_1");
		user.setRoleDept("130107");
		user.setRoleOrg("1");
		String base64JSON = Base64Util.encode(JSON.toJSONString(user));
		System.out.println(base64JSON);
	}
}
