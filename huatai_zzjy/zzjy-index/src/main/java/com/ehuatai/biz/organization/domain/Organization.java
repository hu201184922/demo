package com.ehuatai.biz.organization.domain;

public class Organization {

	private String teamcomshortname;
	private String managecomcode;
	private String managecomname;
	private String provcomname;
	private String provcomcod;

	public String getManagecomcode() {
		return managecomcode;
	}

	public void setManagecomcode(String managecomcode) {
		this.managecomcode = managecomcode;
	}

	public String getManagecomname() {
		return managecomname;
	}

	public void setManagecomname(String managecomname) {
		this.managecomname = managecomname;
	}

	public String getProvcomname() {
		return provcomname;
	}

	public void setProvcomname(String provcomname) {
		this.provcomname = provcomname;
	}

	public String getProvcomcod() {
		return provcomcod;
	}

	public void setProvcomcod(String provcomcod) {
		this.provcomcod = provcomcod;
	}

	public String getTeamcomshortname() {
		return teamcomshortname;
	}

	public void setTeamcomshortname(String teamcomshortname) {
		this.teamcomshortname = teamcomshortname;
	}

}
