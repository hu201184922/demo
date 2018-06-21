package com.fairyland.jdp.framework.quartz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QRTZ_CRON_TRIGGERS")
public class JobCronTrigger {
	
	private JobCronTriggerPk pk;
	private String cronExpression;
	private String timeZoneId;
	
	@Id
	public JobCronTriggerPk getPk() {
		return pk;
	}
	public void setPk(JobCronTriggerPk pk) {
		this.pk = pk;
	}
	
	@Column(name="CRON_EXPRESSION", length=120)
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	@Column(name="TIME_ZONE_ID", length=80)
	public String getTimeZoneId() {
		return timeZoneId;
	}
	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

}
