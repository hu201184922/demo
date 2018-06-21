package com.fairyland.jdp.framework.quartz.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fairyland.jdp.framework.quartz.domain.JobDetail;
import com.fairyland.jdp.framework.quartz.domain.JobDetailPk;
import com.fairyland.jdp.framework.quartz.service.JobDetailService;
import com.fairyland.jdp.framework.quartz.service.TriggersService;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/admin/qrtz/trigger")
public class TriggersController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private TriggersService triggersService;
	
	@Autowired
	SchedulerFactoryBean schedulerFactory;
	
	@Autowired
	private JobDetailService jobDetailService;
	
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("pk_ASC", "自动");
		sortTypes.put("pk.name_ASC", "名称");
	    }
	
	@RequestMapping(value = "job/trigger/{idString}")
	public String getTriggersByJob(@PathVariable("idString") String idString,
    		Model model){
		String[] arr = idString.split(",");
		JobDetailPk jobDetailPk = new JobDetailPk();
		jobDetailPk.setSchedName(arr[0]);
		jobDetailPk.setName(arr[1]);
		jobDetailPk.setGroup(arr[2]);
		JobDetail jobDetail = jobDetailService.findOne(jobDetailPk);
		List<Trigger> triggers = null;
		try {
			triggers = triggersService.getTriggersByJob(jobDetail);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		model.addAttribute("triggers", triggers);
		return "/jdp-framework/quartz/triggers-list";
	}
	
	@RequestMapping(value = "delete/{idString}")
	public String deleteTrigger(@PathVariable("idString") String idString,
    		Model model){
		String[] arr = idString.split(",");
		TriggerKey triggerKey = new TriggerKey(arr[0], arr[1]);
		try {
			triggersService.deleteTrigger(triggerKey);
			model.addAttribute("message", "删除成功");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		return "success";
	}
	
	@RequestMapping(value = "edit/{idString}")
	public String edit(@PathVariable("idString") String idString,
    		Model model) throws SchedulerException{
		String[] arr = idString.split(",");
		TriggerKey triggerKey = new TriggerKey(arr[0], arr[1]);
		Trigger trigger = triggersService.getTriggerByKey(triggerKey);
		model.addAttribute("trigger", trigger);
		model.addAttribute("jobIdString", idString);
		if(trigger instanceof CronTriggerImpl){
			return "/jdp-framework/quartz/updateCronTrigger-edit";
		}else{
			return "/jdp-framework/quartz/updateSimpleTrigger-edit";
		}
	}
	
	@RequestMapping(value = "saveCron")
	public String saveCron(@RequestParam(value="jobIdString") String jobIdString,
			@RequestParam(value = "key.name") String keyName,
			@RequestParam(value = "key.group") String keyGroup,
			@RequestParam(value = "startTime") Date startTime,
			@RequestParam(value = "endTime") Date endTime,
			@RequestParam(value = "nextFireTime") Date nextFireTime,
			@RequestParam(value = "priority") int priority,
			@RequestParam(value = "cronExp") String cronExp,
			Model model) throws SchedulerException, ParseException{
		Scheduler scheduler =  schedulerFactory.getScheduler();
		TriggerKey triggerKey = new TriggerKey(keyName, keyGroup);
		CronTriggerImpl trigger = new CronTriggerImpl(keyName, keyGroup);
		trigger.setStartTime(startTime);
		trigger.setEndTime(endTime);
		trigger.setNextFireTime(nextFireTime);
		trigger.setPriority(priority);
		trigger.setCronExpression(cronExp);
		String[] arr = jobIdString.split(",");
		JobKey jobKey = new JobKey(arr[0], arr[1]);
		org.quartz.JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		scheduler.rescheduleJob(triggerKey, trigger);
//		scheduler.unscheduleJob(triggerKey);
//		scheduler.scheduleJob(jobDetail, trigger);
		model.addAttribute("message", "修改成功");
		return "redirect:/admin/qrtz/job";
	}
	
	@RequestMapping(value = "saveSimple")
	public String saveSimple(@RequestParam(value="jobIdString") String jobIdString,
			@RequestParam(value = "key.name") String keyName,
			@RequestParam(value = "key.group") String keyGroup,
			@RequestParam(value = "startTime") Date startTime,
			@RequestParam(value = "endTime") Date endTime,
			@RequestParam(value = "nextFireTime") Date nextFireTime,
			@RequestParam(value = "priority") int priority,
			@RequestParam(value = "priority") Long repeatInterval,
			@RequestParam(value = "priority") int repeatCount,
    		Model model) throws SchedulerException{
		Scheduler scheduler =  schedulerFactory.getScheduler();
		TriggerKey triggerKey = new TriggerKey(keyName, keyGroup);
		SimpleTriggerImpl trigger = new SimpleTriggerImpl(keyName, keyGroup);
		trigger.setStartTime(startTime);
		trigger.setEndTime(endTime);
		trigger.setNextFireTime(nextFireTime);
		trigger.setPriority(priority);
		trigger.setRepeatInterval(repeatInterval);
		trigger.setRepeatCount(repeatCount);
		String[] arr = jobIdString.split(",");
		JobKey jobKey = new JobKey(arr[0], arr[1]);
		org.quartz.JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		scheduler.rescheduleJob(triggerKey, trigger);
//		scheduler.unscheduleJob(triggerKey);
//		scheduler.scheduleJob(jobDetail, trigger);
		model.addAttribute("message", "修改成功");
		return "redirect:/admin/qrtz/job";
	}

}
