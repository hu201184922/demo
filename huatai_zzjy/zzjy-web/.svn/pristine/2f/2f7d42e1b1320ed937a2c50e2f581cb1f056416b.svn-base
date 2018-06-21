package com.fairyland.jdp.framework.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JDP_MT_ROLE_RES")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_MT_ROLE_RES_ID", table = "jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE")
@SequenceGenerator(name="ROLERESOURCE_SEQ",sequenceName="SEQ_JDP_MT_ROLE_RES",allocationSize = 1)
public class RoleResource {
	
	private Long id;
	private Role role;
	private Resource resource;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	@JsonIgnore
	@JSONField(serialize=false)
	@org.codehaus.jackson.annotate.JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "RES_ID")
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="ROLERESOURCE_SEQ")
	@Column(name = "CID")
	public Long getId() {
		return id;
	}
}
