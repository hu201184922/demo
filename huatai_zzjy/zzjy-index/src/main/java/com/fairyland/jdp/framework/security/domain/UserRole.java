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

import com.fairyland.jdp.framework.domain.IdEntity;

@Entity
@Table(name = "JDP_MT_USER_ROLE")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_MT_USER_ROLE_ID", table = "jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE")
@SequenceGenerator(name="USERROLE_SEQ",sequenceName="SEQ_JDP_MT_USER_ROLE",allocationSize = 1)
public class UserRole {

	private Long id;
	private User user;
	private Role role;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="USERROLE_SEQ")
	@Column(name = "CID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
