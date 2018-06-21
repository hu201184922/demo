package com.fairyland.jdp.framework.security.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserInfo implements Serializable{
	private Long id;
	private String account;// 帐号
	private String userName;// 名称
	private String orgCode;// 机构编号
	private Long puserId;// 上级用户ID
	private String puserName;//上级用户名称
	private String province;// 省份
	private String city;// 城市
	private String channel; // 渠道
	private String sex;// 性别
	private String description;// 描述
	private Boolean isAdmin;// 是否超级管理员
	private Date registerDate;// 注册日期
	private Date firstLoginedTime;// 首次登录时间
	private Date lastLoginedTime;// 最后一次登录时间
	private Date expiredTime;// 过期时间
	private Date lockedTime;// 锁定时间
	private Date pswChangeDate;// 密码修改时间
	private Date credentialsExpiredTime;// 任据过期时间
	private Boolean enabled = Boolean.TRUE;// 帐号是否可用
	private Boolean locked = Boolean.FALSE;// 帐号是否锁定
	private Boolean expired = Boolean.FALSE;// 帐号是否过期
	private Boolean firstLogined;// 是否登录过系统
	private Boolean credentialsExpired;// 凭据过期
	private String phone;
	private String email;
	private Set<UserRole> userRoles;
	private Long roleId;
	private String isType;
	private Map<String, Object> data = new HashMap<String, Object>();
	private String roleName;
	private String accountstate;
	private String channelStr;
	private String idNo; //身份证号
	private Long loginNum; //记录用户登陆次数
	private Date errorTryTime; //错误尝试时间   记录策略第一次错误尝试时间
	private String roleType; //角色类型
	private String userType;	
	
	private String smcode ; // varchar2(12) 
	private String smname ; // varchar2(60) 
	private String amcdoe ; // varchar2(12) 
	private String amname ; // varchar2(60) 
	private String rmcode ; // varchar2(12) 
	private String rmname ; // varchar2(60) 
	private String orgGrade ; // varchar2(4)  业务职级：cao|gm|ah|dm|am|sm|lp 管理：sa 
	private String branchattr ; // varchar2(20)  机构code
	
	private String smbrach; //VARCHAR2(20) 组号
	private String ambrach; //VARCHAR2(20) 处号
	private String rmbrach; //VARCHAR2(20) 区号
	
	private String orgName;//分公司名称，与orgCode对应
	private String orgShortName;//分公司简称，与orgCode对应
	private String picPath;//头像路径
	private Date cusUpdateTime;//客户信息同步时间
	private String adcode;
	private String adname;
	private String adbrach;
	
	private String ah1Name;
	private String ah2Name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Long getPuserId() {
		return puserId;
	}
	public void setPuserId(Long puserId) {
		this.puserId = puserId;
	}
	public String getPuserName() {
		return puserName;
	}
	public void setPuserName(String puserName) {
		this.puserName = puserName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getFirstLoginedTime() {
		return firstLoginedTime;
	}
	public void setFirstLoginedTime(Date firstLoginedTime) {
		this.firstLoginedTime = firstLoginedTime;
	}
	public Date getLastLoginedTime() {
		return lastLoginedTime;
	}
	public void setLastLoginedTime(Date lastLoginedTime) {
		this.lastLoginedTime = lastLoginedTime;
	}
	public Date getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	public Date getLockedTime() {
		return lockedTime;
	}
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}
	public Date getPswChangeDate() {
		return pswChangeDate;
	}
	public void setPswChangeDate(Date pswChangeDate) {
		this.pswChangeDate = pswChangeDate;
	}
	public Date getCredentialsExpiredTime() {
		return credentialsExpiredTime;
	}
	public void setCredentialsExpiredTime(Date credentialsExpiredTime) {
		this.credentialsExpiredTime = credentialsExpiredTime;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public Boolean getExpired() {
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
	public Boolean getFirstLogined() {
		return firstLogined;
	}
	public void setFirstLogined(Boolean firstLogined) {
		this.firstLogined = firstLogined;
	}
	public Boolean getCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getIsType() {
		return isType;
	}
	public void setIsType(String isType) {
		this.isType = isType;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAccountstate() {
		return accountstate;
	}
	public void setAccountstate(String accountstate) {
		this.accountstate = accountstate;
	}
	public String getChannelStr() {
		return channelStr;
	}
	public void setChannelStr(String channelStr) {
		this.channelStr = channelStr;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Long getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(Long loginNum) {
		this.loginNum = loginNum;
	}
	public Date getErrorTryTime() {
		return errorTryTime;
	}
	public void setErrorTryTime(Date errorTryTime) {
		this.errorTryTime = errorTryTime;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSmcode() {
		return smcode;
	}
	public void setSmcode(String smcode) {
		this.smcode = smcode;
	}
	public String getSmname() {
		return smname;
	}
	public void setSmname(String smname) {
		this.smname = smname;
	}
	public String getAmcdoe() {
		return amcdoe;
	}
	public void setAmcdoe(String amcdoe) {
		this.amcdoe = amcdoe;
	}
	public String getAmname() {
		return amname;
	}
	public void setAmname(String amname) {
		this.amname = amname;
	}
	public String getRmcode() {
		return rmcode;
	}
	public void setRmcode(String rmcode) {
		this.rmcode = rmcode;
	}
	public String getRmname() {
		return rmname;
	}
	public void setRmname(String rmname) {
		this.rmname = rmname;
	}
	public String getOrgGrade() {
		return orgGrade;
	}
	public void setOrgGrade(String orgGrade) {
		this.orgGrade = orgGrade;
	}
	public String getBranchattr() {
		return branchattr;
	}
	public void setBranchattr(String branchattr) {
		this.branchattr = branchattr;
	}
	public String getSmbrach() {
		return smbrach;
	}
	public void setSmbrach(String smbrach) {
		this.smbrach = smbrach;
	}
	public String getAmbrach() {
		return ambrach;
	}
	public void setAmbrach(String ambrach) {
		this.ambrach = ambrach;
	}
	public String getRmbrach() {
		return rmbrach;
	}
	public void setRmbrach(String rmbrach) {
		this.rmbrach = rmbrach;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgShortName() {
		return orgShortName;
	}
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public Date getCusUpdateTime() {
		return cusUpdateTime;
	}
	public void setCusUpdateTime(Date cusUpdateTime) {
		this.cusUpdateTime = cusUpdateTime;
	}
	public String getAdcode() {
		return adcode;
	}
	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getAdbrach() {
		return adbrach;
	}
	public void setAdbrach(String adbrach) {
		this.adbrach = adbrach;
	}
	public String getAh1Name() {
		return ah1Name;
	}
	public void setAh1Name(String ah1Name) {
		this.ah1Name = ah1Name;
	}
	public String getAh2Name() {
		return ah2Name;
	}
	public void setAh2Name(String ah2Name) {
		this.ah2Name = ah2Name;
	}
	
	
}
