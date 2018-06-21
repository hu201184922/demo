package com.fairyland.jdp.common.persistent.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@MappedSuperclass
public abstract class SelfAssociationEntity<T extends SelfAssociationEntity<T>> extends IdEntity
		implements Comparable<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static public final Long ROOT_PARENT_ID = -1L;

	static public final Long ROOT_ID = 1L;

	protected T parent;

	protected List<T> children;

	protected Integer sortIndex = 0;

	protected String name;

	protected String code;

	protected Integer childCount;

	protected Long pid;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@OrderBy("sortIndex  ASC")
	public List<T> getChildren() {
		return children;
	}

	public void setChildren(List<T> children) {
		this.children = children;
	}

	public Integer getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}

	@Column(name = "NAME_")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public abstract Integer getChildCount();

	// @Formula("(select count(*) from JDP_MENU_ITEM m where m.PARENT_ID =
	// ID_)")
	public Integer getChildCount() {
		return childCount;

	}

	@Column(name = "CODE_")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	@Column(name = "PARENT_ID", insertable = false, updatable = false)
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public boolean hasChild() {
		return children != null && !children.isEmpty();
	}

	@Override
	public int compareTo(T obj) {
		if (obj != null && this.getSortIndex() != null) {
			if (this.getSortIndex().compareTo(obj.getSortIndex()) == 1)
				return 1;
			if (this.getId().equals(obj.getId()))
				return 0;
		}
		return -1;
	}
}
