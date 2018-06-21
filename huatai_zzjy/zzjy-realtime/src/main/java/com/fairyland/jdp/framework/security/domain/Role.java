package com.fairyland.jdp.framework.security.domain;

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

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JDP_ROLE")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_ROLE_ID", table = "jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE")
@SequenceGenerator(name="ROLE_SEQ",sequenceName="SEQ_JDP_ROLE",allocationSize = 1)
public class Role {

	private Long id;
	private String code;//角色标识
	private String name;//角色名称
	private String descript;//备注
	private Set<UserRole> userRoles;//角色包含用户
	private Set<RoleResource> roleResources;//角色资源权限
	private String type;//类型
	private String href;//链接

	
	public Role() { }

	public Role(Long id)
	{
		this.setId(id);
	}
	
	@Column(name="CODE_")
	@Index(name="JDP_INX_CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="ROLENAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="DESCRIPTION")
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	@JsonIgnore
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	@JsonIgnore
	
	public Set<RoleResource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(Set<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="ROLE_SEQ")
	@Column(name = "CID")
	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	

}
