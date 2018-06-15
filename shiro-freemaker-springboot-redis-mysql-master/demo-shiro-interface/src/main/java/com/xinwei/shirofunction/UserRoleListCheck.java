package com.xinwei.shirofunction;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 给用户添加role时使用。
 * @author ouburikou
 *
 */
@Slf4j
@Data
public class UserRoleListCheck {

	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	private String roleName;
	private boolean check=false;
	
	
}
