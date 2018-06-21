package com.fairyland.jdp.framework.quartz.service;

import java.text.ParseException;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.framework.quartz.domain.CronTrigger;
import com.fairyland.jdp.framework.quartz.domain.QrtzGroup;

public class CronTriggerConverter {

	public JobDetailImpl getJobDetail(CronTrigger cronTrigger) throws ClassNotFoundException {
		JobDetailImpl jobDetail = new JobDetailImpl();
		jobDetail.setName(cronTrigger.getCode());
		QrtzGroup qrtzGroup = cronTrigger.getQrtzGroup();
		if(qrtzGroup != null){
			jobDetail.setGroup(qrtzGroup.getCode());
		}
		Map jsonObj = JSON.parseObject(cronTrigger.getJobDataMap());
		jobDetail.setJobDataMap(new JobDataMap(jsonObj));
		jobDetail.setJobClass((Class<? extends Job>) Class.forName(cronTrigger.getJobClassName()));
		return jobDetail;
	}

	public CronTriggerImpl getCronTrigger(CronTrigger cronTrigger) throws ParseException {
		CronTriggerImpl trigger = new CronTriggerImpl();
		trigger.setName(cronTrigger.getCode());
		QrtzGroup qrtzGroup = cronTrigger.getQrtzGroup();
		if(qrtzGroup != null){
			trigger.setGroup(qrtzGroup.getCode());
		}
		String cronExpression = cronTrigger.getCronExp();
		trigger.setCronExpression(cronExpression);
		if(cronTrigger.getStartTime()!=null)
			trigger.setStartTime(cronTrigger.getStartTime());
		if(cronTrigger.getEndTime()!=null)
			trigger.setEndTime(cronTrigger.getEndTime());
		return trigger;
	}

}
