package com.fairyland.jdp.framework.security.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JDP_USER")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_USER_ID", table = "jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "SEQ_JDP_USER", allocationSize = 1)
public class User implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 9031144327261059454L;

	private Long id;
	private String account;// 帐号
	private String userName;// 名称
	private String plainPassword;
	private String password;// 密码
	private String salt;// 盐
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

	@Transient
	@Formula("(SELECT distinct ah1Name FROM EZT_AGENTHEAD EA WHERE ORG_CODE = EA.ah1Code )")
	public String getAh1Name() {
		return ah1Name;
	}

	public void setAh1Name(String ah1Name) {
		this.ah1Name = ah1Name;
	}
	@Transient
	@Formula("(SELECT distinct ah2Name FROM EZT_AGENTHEAD EA WHERE ORG_CODE = EA.ah2Code )")
	public String getAh2Name() {
		return ah2Name;
	}

	public void setAh2Name(String ah2Name) {
		this.ah2Name = ah2Name;
	}

	@Transient
	public String getPuserName() {
		return puserName;
	}

	public void setPuserName(String puserName) {
		this.puserName = puserName;
	}

	@Transient
	public String getChannelStr() {
		return channelStr;
	}

	public void setChannelStr(String channelStr) {
		this.channelStr = channelStr;
	}

	@Column(name = "ACCOUNTSTATE")
	public String getAccountstate() {
		return accountstate;
	}

	public void setAccountstate(String accountstate) {
		this.accountstate = accountstate;
	}

	public User() {
	}

	public User(Long id) {
		this.setId(id);
	}

	@NotBlank
	@Index(name = "JDP_INX_LOGIN_NAME")
	@Column(length = 32)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@NotBlank
	@Column(name = "USERNAME", length = 32)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// 不持久化到数据库，也不显示在Restful接口的属性.
	@Transient
	@JsonIgnore
	@JSONField(serialize = false)
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	@JSONField(serialize = false)
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "SALT_")
	@JsonIgnore
	@JSONField(serialize = false)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getFirstLoginedTime() {
		return firstLoginedTime;
	}

	public void setFirstLoginedTime(Date firstLoginedTime) {
		this.firstLoginedTime = firstLoginedTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getLastLoginedTime() {
		return lastLoginedTime;
	}

	public void setLastLoginedTime(Date lastLoginedTime) {
		this.lastLoginedTime = lastLoginedTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getPswChangeDate() {
		return pswChangeDate;
	}

	public void setPswChangeDate(Date pswChangeDate) {
		this.pswChangeDate = pswChangeDate;
	}

	public Date getLockedTime() {
		return lockedTime;
	}

	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}

	@Column(name = "ENABLED_")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	@JsonIgnore
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Column(name = "LOCKED_")
	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Column(name = "EXPIRED_")
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

	public Date getCredentialsExpiredTime() {
		return credentialsExpiredTime;
	}

	public void setCredentialsExpiredTime(Date credentialsExpiredTime) {
		this.credentialsExpiredTime = credentialsExpiredTime;
	}

	public Boolean getCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(length = 10)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(length = 10)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPuserId() {
		return puserId;
	}

	public void setPuserId(Long puserId) {
		this.puserId = puserId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(length = 1)
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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
	@Column(name = "CID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ISTYPE")
	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	public void data(String key, Object value) {
		data.put(key, value);
	}

	public Object data(String key) {
		return data.get(key);
	}


	@Formula("(select m.ROLENAME from JDP_ROLE m where m.cid = role_Id)")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Formula("(select m.TYPE from JDP_ROLE m where m.cid = role_Id)")
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Column(name = "IDNO")
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "LOGINNUM")
	public Long getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Long loginNum) {
		this.loginNum = loginNum;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Column(name = "ERRORTRYTIME")
	public Date getErrorTryTime() {
		return errorTryTime;
	}

	public void setErrorTryTime(Date errorTryTime) {
		this.errorTryTime = errorTryTime;
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

	@Column(name = "ORG_GRADE")
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

	public String getAmname() {
		return amname;
	}

	public void setAmname(String amname) {
		this.amname = amname;
	}

	public String getOrgName() {
		String ah1Name = getAh1Name();
		if(ah1Name==null){
			String ah2Name = getAh2Name();
			return ah2Name;
		}
		return ah1Name;
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

	@Column(name = "PIC_PATH")
	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@Formula("(select m.CODE_ from JDP_ROLE m where m.cid = role_Id)")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCusUpdateTime() {
		return cusUpdateTime;
	}

	public void setCusUpdateTime(Date cusUpdateTime) {
		this.cusUpdateTime = cusUpdateTime;
	}
	
	
	
	
	
}