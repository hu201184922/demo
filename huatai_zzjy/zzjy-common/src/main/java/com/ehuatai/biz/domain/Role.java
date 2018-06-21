package com.ehuatai.biz.domain;

import java.util.List;

public class Role {

	private String role;

	private String roleName;

	private String roleOrg;

	private String roleOrgName;

	private String roleDept;

	private String roleDeptName;

	private List<Nodes> nodes;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public List<Nodes> getNodes() {
		return nodes;
	}

	public void setNodes(List<Nodes> nodes) {
		this.nodes = nodes;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleOrgName() {
		return roleOrgName;
	}

	public void setRoleOrgName(String roleOrgName) {
		this.roleOrgName = roleOrgName;
	}

	public String getRoleDeptName() {
		return roleDeptName;
	}

	public void setRoleDeptName(String roleDeptName) {
		this.roleDeptName = roleDeptName;
	}

}
