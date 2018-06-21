package com.fairyland.jdp.framework.quartz.domain;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="QRTZ_TRIGGERS")
public class Triggers {

	private TriggersPk pk;
	private String jobName;
	private String jobGroup;
	private String description;
	private Long nextFireTime;
	private Long prevFireTime;
	private Long priority;
	private String triggerState;
	private String triggerType;
	private Long startTime;
	private Long endTime;
	private String calendarName;
	private Long misfireInstr;
	private Blob jobData;
	
	@Id
	public TriggersPk getPk() {
		return pk;
	}
	public void setPk(TriggersPk pk) {
		this.pk = pk;
	}
	
	@Column(name="JOB_NAME")
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Column(name="JOB_GROUP")
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="NEXT_FIRE_TIME")
	public Long getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(Long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	
	@Column(name="PREV_FIRE_TIME")
	public Long getPrevFireTime() {
		return prevFireTime;
	}
	public void setPrevFireTime(Long prevFireTime) {
		this.prevFireTime = prevFireTime;
	}
	
	@Column(name="PRIORITY")
	public Long getPriority() {
		return priority;
	}
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	
	@Column(name="TRIGGER_STATE")
	public String getTriggerState() {
		return triggerState;
	}
	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}
	
	@Column(name="TRIGGER_TYPE")
	public String getTriggerType() {
		return triggerType;
	}
	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
	
	@Column(name="START_TIME")
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	
	@Column(name="END_TIME")
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="CALENDAR_NAME")
	public String getCalendarName() {
		return calendarName;
	}
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}
	
	@Column(name="MISFIRE_INSTR")
	public Long getMisfireInstr() {
		return misfireInstr;
	}
	public void setMisfireInstr(Long misfireInstr) {
		this.misfireInstr = misfireInstr;
	}
	
	@Column(name="JOB_DATA")
	public Blob getJobData() {
		return jobData;
	}
	public void setJobData(Blob jobData) {
		this.jobData = jobData;
	}
	
	
	
}
