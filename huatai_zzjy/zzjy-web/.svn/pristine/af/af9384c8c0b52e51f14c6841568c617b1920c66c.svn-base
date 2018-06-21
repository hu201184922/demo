package com.fairyland.jdp.framework.quartz.service;

import java.util.List;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import com.fairyland.jdp.framework.quartz.domain.JobDetail;


public interface TriggersService {

	List<Trigger> getTriggersByJob(JobDetail jobDetail) throws SchedulerException;
	
	void deleteTrigger(TriggerKey triggerKey) throws SchedulerException;

	Trigger getTriggerByKey(TriggerKey triggerKey) throws SchedulerException;

}
