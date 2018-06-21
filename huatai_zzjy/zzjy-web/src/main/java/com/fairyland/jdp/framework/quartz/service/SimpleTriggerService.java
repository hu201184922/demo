package com.fairyland.jdp.framework.quartz.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.fairyland.jdp.framework.quartz.domain.SimpleTrigger;

public interface SimpleTriggerService {

	List<SimpleTrigger> getSimpleTriggers(Long id);

	void deleteSimpleTrigger(Long id);

	SimpleTrigger getSimpleTrigger(Long id);

	void updateSimpleTrigger(SimpleTrigger simpleTrigger);

	SimpleTrigger getQrtzDefinitionByName(String name);

	void release(SimpleTrigger simpleTrigger);

	void generate(SimpleTrigger simpleTrigger) throws ClassNotFoundException, SchedulerException;

	void stop(SimpleTrigger simpleTrigger) throws ClassNotFoundException, SchedulerException;

}
