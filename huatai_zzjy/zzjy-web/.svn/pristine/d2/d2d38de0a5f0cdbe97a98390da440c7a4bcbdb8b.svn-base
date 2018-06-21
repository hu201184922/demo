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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.fairyland.jdp.framework.domain.TreeNodeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "JDP_RESOURCE")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_RESOURCE_ID", table = "jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE")
@SequenceGenerator(name="RESOURCE_SEQ",sequenceName="SEQ_JDP_RESOURCE",allocationSize = 1)
public class Resource extends TreeNodeEntity<Resource> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2298082502016300839L;
	static public final String RES_TYPE_URL = "U";
	static public final String RES_TYPE_CONTROL = "C";
	static public final String RES_TYPE_ELEMENT = "E";
	static public final String RES_TYPE_GROUP = "G";

//	private String name;//资源名称
	private Long id;
	private String resType;//资源类型
	private String resString;//资源唯一字符串
	private String perString;//资源的shiro  permission字符串
	private String descript;//备注
	private Boolean enabled;//资源是否可用
	private Set<RoleResource> roleResources;
	private String checked;
	
	@Transient
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	
	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResString() {
		return resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	@Column(name="DESCRIPT_")
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Column(name="ENABLED_")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "RES_ID")
	@JsonIgnore
	@JSONField(serialize=false)
	@org.codehaus.jackson.annotate.JsonIgnore
	public Set<RoleResource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(Set<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}



	public String getPerString() {
	    return perString;
	}

	public void setPerString(String perString) {
	    this.perString = perString;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="RESOURCE_SEQ")
	@Column(name = "CID")
	public Long getId() {
		return id;
	}
	
}
