package com.fairyland.jdp.framework.quartz.domain;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="QRTZ_JOB_DETAILS")
public class JobDetail {
	
	private JobDetailPk pk;
	private String description;
	private String jobClass;
	private String durability;
	private String isNonconcurrent;
	private String isUpdateDate;
	private String requestsRecovery;
	private Blob jobData;
	
	@Id
	public JobDetailPk getPk() {
		return pk;
	}
	public void setPk(JobDetailPk pk) {
		this.pk = pk;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="JOB_CLASS_NAME", nullable=false)
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	
	@Column(name="IS_DURABLE", length=1, nullable=false)
	public String getDurability() {
		return durability;
	}
	public void setDurability(String durability) {
		this.durability = durability;
	}
	
	@Column(name="IS_NONCONCURRENT", length=1, nullable=false)
	public String getIsNonconcurrent() {
		return isNonconcurrent;
	}
	public void setIsNonconcurrent(String isNonconcurrent) {
		this.isNonconcurrent = isNonconcurrent;
	}
	
	@Column(name="IS_UPDATE_DATA", length=1, nullable=false)
	public String getIsUpdateDate() {
		return isUpdateDate;
	}
	public void setIsUpdateDate(String isUpdateDate) {
		this.isUpdateDate = isUpdateDate;
	}
	
	@Column(name="REQUESTS_RECOVERY", length=1, nullable=false)
	public String getRequestsRecovery() {
		return requestsRecovery;
	}
	public void setRequestsRecovery(String requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}
	
	@Column(name="JOB_DATA")
	public Blob getJobData() {
		return jobData;
	}
	public void setJobData(Blob jobData) {
		this.jobData = jobData;
	}
	
	//=========================================================
	
	@Transient
	public String getIdString(){
		return pk.getSchedName()+","+pk.getName()+","+pk.getGroup();
	}
	
}
