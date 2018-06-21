package com.fairyland.jdp.framework.quartz.service;

import java.text.ParseException;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import com.fairyland.jdp.framework.quartz.domain.CronTrigger;

public interface CronTriggerService {

	List<CronTrigger> getCronTriggers(Long groupId);

	CronTrigger getCronTrigger(Long id);
	
	CronTrigger getCronTrigger(String triggerCode,String groupCode);

	void updateCronTrigger(CronTrigger cronTrigger);

	void deleteCronTrigger(Long id);

	CronTrigger getQrtzDefinitionByName(String name);

	void release(CronTrigger cronTrigger);

	void generate(CronTrigger cronTrigger) throws ClassNotFoundException, SchedulerException, ParseException;

	void stop(CronTrigger cronTrigger) throws SchedulerException, ClassNotFoundException, ParseException;

	void run(CronTrigger cronTrigger) throws SchedulerException, ClassNotFoundException, ParseException;
	
	String getState(CronTrigger cronTrigger) throws SchedulerException, ClassNotFoundException, ParseException;

	void deleteTableTriggle(Long qrtzGroupId, String qrtzCode);
	
	List<JobExecutionContext> getCurrentlyExecutingJobs();
	
	List<CronTrigger> findAll();
}
