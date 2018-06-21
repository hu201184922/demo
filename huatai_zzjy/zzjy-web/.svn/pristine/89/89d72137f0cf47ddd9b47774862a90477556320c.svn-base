package com.fairyland.jdp.framework.quartz.service;

import java.text.ParseException;
import java.util.TimeZone;

import org.quartz.Job;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.framework.quartz.domain.QrtzDefinition;
import com.fairyland.jdp.framework.quartz.domain.QrtzGroup;

@Transactional
@Service(value="qrtzDefinitionConverter")
public class QrtzDefinitionConverter {
	
	public JobDetailImpl getJobDetail(QrtzDefinition qrtzDefinition) throws ClassNotFoundException{
		JobDetailImpl jobDetail = new JobDetailImpl();
		jobDetail.setName(qrtzDefinition.getCode());
		QrtzGroup qrtzGroup = qrtzDefinition.getQrtzGroup();
		if(qrtzGroup != null){
			jobDetail.setGroup(qrtzGroup.getCode());
		}
		
		jobDetail.setJobClass((Class<? extends Job>) Class.forName(qrtzDefinition.getJobClassName()));
//		jobDetail.setJobClass((Class<? extends Job>) Class.forName("com.fairyland.jdp.framework.schedule.quartz.QuartzClusterableJob"));
		
		return jobDetail;
	}
	
	public CronTriggerImpl getCronTrigger(QrtzDefinition qrtzDefinition) throws ParseException{
		CronTriggerImpl trigger = new CronTriggerImpl();
		trigger.setName(qrtzDefinition.getCode());
		trigger.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		QrtzGroup qrtzGroup = qrtzDefinition.getQrtzGroup();
		if(qrtzGroup != null){
			trigger.setGroup(qrtzGroup.getCode());
		}
		String cronExpression = qrtzDefinition.getCronExpression();
		System.out.println(cronExpression);
		trigger.setCronExpression(cronExpression);
		return trigger;
	}

}
