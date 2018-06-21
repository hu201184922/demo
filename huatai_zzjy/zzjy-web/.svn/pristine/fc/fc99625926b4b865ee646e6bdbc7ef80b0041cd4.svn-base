package com.fairyland.jdp.framework.quartz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JobDetailPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String schedName;
	private String name;
	private String group;
	
	@Column(name="SCHED_NAME")
	public String getSchedName() {
		return schedName;
	}
	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}
	
	@Column(name = "JOB_NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="JOB_GROUP")
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((schedName == null) ? 0 : schedName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobDetailPk other = (JobDetailPk) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (schedName == null) {
			if (other.schedName != null)
				return false;
		} else if (!schedName.equals(other.schedName))
			return false;
		return true;
	}
	
	
	
	
}
