package com.fairyland.jdp.framework.quartz.service;

import java.util.TimeZone;

import org.quartz.Job;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import com.fairyland.jdp.framework.quartz.domain.QrtzGroup;
import com.fairyland.jdp.framework.quartz.domain.SimpleTrigger;

public class SimpleTriggerConverter {

	public JobDetailImpl getJobDetail(SimpleTrigger simpleTrigger) throws ClassNotFoundException {
		JobDetailImpl jobDetail = new JobDetailImpl();
		jobDetail.setName(simpleTrigger.getCode());
		QrtzGroup qrtzGroup = simpleTrigger.getQrtzGroup();
		if(qrtzGroup != null){
			jobDetail.setGroup(qrtzGroup.getCode());
		}
		jobDetail.setJobClass((Class<? extends Job>) Class.forName(simpleTrigger.getJobClassName()));
		return jobDetail;
	}

	public SimpleTriggerImpl getCronTrigger(SimpleTrigger simpleTrigger) {
		SimpleTriggerImpl trigger = new SimpleTriggerImpl();
		trigger.setName(simpleTrigger.getCode());
		QrtzGroup qrtzGroup = simpleTrigger.getQrtzGroup();
		if(qrtzGroup != null){
			trigger.setGroup(qrtzGroup.getCode());
		}
		trigger.setStartTime(simpleTrigger.getStartTime());
		trigger.setEndTime(trigger.getEndTime());
		trigger.setRepeatInterval(simpleTrigger.getRepeatInterval());
		trigger.setRepeatCount(simpleTrigger.getRepeatCount());
		return trigger;
	}

}
