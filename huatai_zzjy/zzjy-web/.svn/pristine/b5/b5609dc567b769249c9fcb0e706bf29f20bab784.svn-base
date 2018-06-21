package com.fairyland.jdp.framework.quartz.service;

import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.quartz.domain.JobDetail;

@Service(value="triggersService")
public class TriggersServiceImpl implements TriggersService {
	
	@Autowired
	SchedulerFactoryBean schedulerFactory;

	@Override
	public List<Trigger> getTriggersByJob(JobDetail jobDetail) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		JobKey jobkey = new JobKey(jobDetail.getPk().getName(), jobDetail.getPk().getGroup());
		List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobkey);
		return (List<Trigger>) triggers;
	}

	@Override
	public void deleteTrigger(TriggerKey triggerKey) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.unscheduleJob(triggerKey);
	}

	@Override
	public Trigger getTriggerByKey(TriggerKey triggerKey) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		return scheduler.getTrigger(triggerKey);
	}

	
}
