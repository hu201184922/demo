package com.fairyland.jdp.framework.quartz.utils;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.fairyland.jdp.framework.quartz.domain.CronTrigger;
import com.fairyland.jdp.framework.quartz.domain.QrtzDefinition;
import com.fairyland.jdp.framework.quartz.domain.QrtzGroup;
import com.fairyland.jdp.framework.quartz.domain.SimpleTrigger;
import com.fairyland.jdp.framework.quartz.service.CronTriggerConverter;
import com.fairyland.jdp.framework.quartz.service.QrtzDefinitionConverter;
import com.fairyland.jdp.framework.quartz.service.SimpleTriggerConverter;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class SchedulerUtil {

	private static SchedulerFactoryBean schedulerFactory;
	
	static{
		schedulerFactory = SpringUtil.getBean(SchedulerFactoryBean.class);
	}
	
	public static void schedulerJob(SchedulerFactoryBean schedulerFactory,QrtzDefinition qrtzDefinition) throws SchedulerException, ClassNotFoundException, ParseException{
		Scheduler scheduler = schedulerFactory.getScheduler();
		QrtzDefinitionConverter qrtzDefinitionConverter = new QrtzDefinitionConverter();
		JobDetailImpl jobDetail = qrtzDefinitionConverter.getJobDetail(qrtzDefinition);
		CronTriggerImpl trigger = qrtzDefinitionConverter.getCronTrigger(qrtzDefinition);
		scheduler.scheduleJob(jobDetail, trigger);
//		scheduler.start();
	}
	
	public static void stopJob(SchedulerFactoryBean schedulerFactory,QrtzDefinition qrtzDefinition) throws SchedulerException, ClassNotFoundException{
		Scheduler scheduler = schedulerFactory.getScheduler();
		QrtzGroup qrtzGroup = qrtzDefinition.getQrtzGroup();
		if(qrtzGroup != null){
			JobKey jobkey = new JobKey(qrtzDefinition.getCode(), qrtzDefinition.getQrtzGroup().getCode());
//			JobKey jobkey = new JobKey("timerJobDetail","DEFAULT");
			scheduler.pauseJob(jobkey);
			TriggerKey triggerKey = new TriggerKey(qrtzDefinition.getCode(), qrtzDefinition.getQrtzGroup().getCode());
			scheduler.unscheduleJob(triggerKey);
		}
	}

	public static List<String> getJobCodes(String groupName) throws SchedulerException{
		
		if(schedulerFactory != null){
			Scheduler scheduler = schedulerFactory.getScheduler();
			GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupEquals(groupName);
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			

			List<String> jobs = Lists.newArrayList(
					Collections2.transform(jobKeys, new Function<JobKey, String>() {

						@Override
						public String apply(JobKey input) {
							return input == null? null : input.getName();
						}
					}));
			return jobs;
		}
		
		return null;
		
	}

	public static void schedulerJob(SchedulerFactoryBean schedulerFactory2,
			CronTrigger cronTrigger) throws SchedulerException, ParseException, ClassNotFoundException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		CronTriggerConverter cronTriggerConverter = new CronTriggerConverter();
		JobDetailImpl jobDetail = cronTriggerConverter.getJobDetail(cronTrigger);
		CronTriggerImpl trigger = cronTriggerConverter.getCronTrigger(cronTrigger);
		scheduler.scheduleJob(jobDetail, trigger);
	}

	public static void schedulerJob(SchedulerFactoryBean schedulerFactory2,
			SimpleTrigger simpleTrigger) throws SchedulerException, ClassNotFoundException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		SimpleTriggerConverter simpleTriggerConverter = new SimpleTriggerConverter();
		JobDetailImpl jobDetail = simpleTriggerConverter.getJobDetail(simpleTrigger);
		SimpleTriggerImpl trigger = simpleTriggerConverter.getCronTrigger(simpleTrigger);
		scheduler.scheduleJob(jobDetail, trigger);
		
	}

	public static void stopJob(SchedulerFactoryBean schedulerFactory2,
			SimpleTrigger simpleTrigger) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		QrtzGroup qrtzGroup = simpleTrigger.getQrtzGroup();
		if(qrtzGroup != null){
			JobKey jobkey = new JobKey(simpleTrigger.getCode(), simpleTrigger.getQrtzGroup().getCode());
//			JobKey jobkey = new JobKey("timerJobDetail","DEFAULT");
			scheduler.pauseJob(jobkey);
			TriggerKey triggerKey = new TriggerKey(simpleTrigger.getCode(), simpleTrigger.getQrtzGroup().getCode());
			scheduler.unscheduleJob(triggerKey);
		}
	}

	public static void stopJob(SchedulerFactoryBean schedulerFactory2,
			CronTrigger cronTrigger) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		QrtzGroup qrtzGroup = cronTrigger.getQrtzGroup();
		if(qrtzGroup != null){
			JobKey jobkey = new JobKey(cronTrigger.getCode(), cronTrigger.getQrtzGroup().getCode());
//			JobKey jobkey = new JobKey("timerJobDetail","DEFAULT");
			scheduler.pauseJob(jobkey);
			TriggerKey triggerKey = new TriggerKey(cronTrigger.getCode(), cronTrigger.getQrtzGroup().getCode());
			scheduler.unscheduleJob(triggerKey);
		}
	}
	
	public static void runJob(SchedulerFactoryBean schedulerFactory2,
			CronTrigger cronTrigger) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		QrtzGroup qrtzGroup = cronTrigger.getQrtzGroup();
		if(qrtzGroup != null){
			JobKey jobkey = new JobKey(cronTrigger.getCode(), cronTrigger.getQrtzGroup().getCode());
			scheduler.triggerJob(jobkey);
		}
	}
	public static String getJobState(SchedulerFactoryBean schedulerFactory2,
			CronTrigger cronTrigger) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		QrtzGroup qrtzGroup = cronTrigger.getQrtzGroup();
		if(qrtzGroup != null){
			TriggerKey triggerKey = new TriggerKey(cronTrigger.getCode(), cronTrigger.getQrtzGroup().getCode());
			TriggerState state = scheduler.getTriggerState(triggerKey);
			return state.name();
		}
		return null;
	}
	public static List<JobExecutionContext> getCurrentlyExecutingJobs(SchedulerFactoryBean schedulerFactory2){
		Scheduler scheduler = schedulerFactory.getScheduler();
		try {
			return scheduler.getCurrentlyExecutingJobs();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return null;
	}
}
