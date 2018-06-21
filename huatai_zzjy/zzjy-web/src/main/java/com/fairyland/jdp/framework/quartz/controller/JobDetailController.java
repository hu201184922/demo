package com.fairyland.jdp.framework.quartz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.constant.Constants;
import com.fairyland.jdp.framework.quartz.domain.CronTrigger;
import com.fairyland.jdp.framework.quartz.domain.JobDetail;
import com.fairyland.jdp.framework.quartz.domain.Triggers;
import com.fairyland.jdp.framework.quartz.domain.TriggersPk;
import com.fairyland.jdp.framework.quartz.service.CronTriggerService;
import com.fairyland.jdp.framework.quartz.service.JobDetailService;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/admin/qrtz/job")
public class JobDetailController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JobDetailService jobDetailService;
	@Autowired
	private CronTriggerService cronTriggerService;
	@Autowired
	SchedulerFactoryBean schedulerFactory;
	
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("pk_ASC", "自动");
		sortTypes.put("pk.name_ASC", "名称");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String main(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "pk_ASC") String sortType,
			Model model, ServletRequest request){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<JobDetail> jobDetails = jobDetailService.findAllJobs(searchParams, pageNumber,
				pageSize, sortType);
		
		Map<String, Triggers> triggerData = new HashedMap();
		Map<String, CronTrigger> cronTriggerData = new HashMap<String, CronTrigger>();
//		Map<String, Long> triggerData2 = new HashedMap();
		for (JobDetail jobDetail : jobDetails) {
			triggerData.put(jobDetail.getIdString(), jobDetailService.getTrigger(jobDetail));
			cronTriggerData.put(jobDetail.getIdString(), cronTriggerService.getCronTrigger(jobDetail.getPk().getName(), jobDetail.getPk().getGroup()));
//			triggerData2.put(jobDetail.getIdString(), jobDetailService.getTriggersNum(jobDetail));
		}
		model.addAttribute("triggerData", triggerData);
		model.addAttribute("cronTriggerData",cronTriggerData);
//		model.addAttribute("triggerData2", triggerData2);
		model.addAttribute("jobDetails", jobDetails);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		 
		return "/jdp-framework/quartz/jobDetail-main";
	}
	
	//挂起任务 
	@RequestMapping(value="pause/{idString}", method = RequestMethod.GET)
	public String pauseJob(@PathVariable("idString") String idString, RedirectAttributes redirectAttributes){
		String[] arr = idString.split(",");
		if(arr.length == 3){
			Scheduler scheduler = schedulerFactory.getScheduler();
			try {
				scheduler.pauseJob(new JobKey(arr[1], arr[2]));
				redirectAttributes.addFlashAttribute("message", "挂起成功");
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
			}
		}
		return "redirect:/admin/qrtz/job";
	}
	
	//中断任务
	@RequestMapping(value="interrupt/{idString}", method = RequestMethod.GET)
	public String interruptJob(@PathVariable("idString") String idString, RedirectAttributes redirectAttributes){
		String[] arr = idString.split(",");
		if(arr.length == 3){
			Scheduler scheduler = schedulerFactory.getScheduler();
			try {
				scheduler.interrupt(new JobKey(arr[1], arr[2]));
				redirectAttributes.addFlashAttribute("message", "中断成功");
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
			}
		}
		return "redirect:/admin/qrtz/job";
	}
	
	//激活任务
	@RequestMapping(value="resume/{idString}", method = RequestMethod.GET)
	public String resumeJob(@PathVariable("idString") String idString, RedirectAttributes redirectAttributes){
		String[] arr = idString.split(",");
		if(arr.length == 3){
			Scheduler scheduler = schedulerFactory.getScheduler();
			try {
				scheduler.resumeJob(new JobKey(arr[1], arr[2]));
				redirectAttributes.addFlashAttribute("message", "激活成功");
			} catch (SchedulerException e) {
				redirectAttributes.addFlashAttribute("message", "激活失败");
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
			}
		}
		return "redirect:/admin/qrtz/job";
	}
	
	//执行任务
	@RequestMapping(value="sched/{idString}", method = RequestMethod.GET)
	public String schedJob(@PathVariable("idString") String idString, RedirectAttributes redirectAttributes){
		String[] arr = idString.split(",");
		if(arr.length == 3){
			Scheduler scheduler = schedulerFactory.getScheduler();
			try {
				scheduler.triggerJob(new JobKey(arr[1], arr[2]));
				redirectAttributes.addFlashAttribute("message", "执行成功");
			} catch (SchedulerException e) {
				redirectAttributes.addFlashAttribute("message", "执行失败");
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
			}
		}
		return "redirect:/admin/qrtz/job";
	}
	
	//终止任务
	@RequestMapping(value="stop/{idString}", method = RequestMethod.GET)
	public String stopJob(@PathVariable("idString") String idString, RedirectAttributes redirectAttributes){
		String[] arr = idString.split(",");
		if(arr.length == 3){
			Scheduler scheduler = schedulerFactory.getScheduler();
			List<Triggers> triggers = jobDetailService.getTriggers(jobDetailService.findOneByIdString(idString));
			List<TriggerKey> triggerKeys = new ArrayList<TriggerKey>();
			try{
				for (Triggers trigger : triggers) {
					TriggersPk triggersPk = trigger.getPk();
					TriggerKey triggerKey = new TriggerKey(triggersPk.getName(), triggersPk.getGroup());
					triggerKeys.add(triggerKey);
					scheduler.pauseTrigger(triggerKey);
				}
				scheduler.unscheduleJobs(triggerKeys);
				redirectAttributes.addFlashAttribute("message", "终止成功");
			}catch (SchedulerException e) {
				log.error(e.getMessage(),e);
			}
		}
		return "redirect:/admin/qrtz/job";
	}
	
	//删除任务
	@RequestMapping(value="delete/{idString}", method = RequestMethod.GET)
	public String deleteJob(@PathVariable("idString") String idString, RedirectAttributes redirectAttributes){
		String[] arr = idString.split(",");
		if(arr.length == 3){
			Scheduler scheduler = schedulerFactory.getScheduler();
			List<Triggers> triggers = jobDetailService.getTriggers(jobDetailService.findOneByIdString(idString));
			List<TriggerKey> triggerKeys = new ArrayList<TriggerKey>();
			try{
				for (Triggers trigger : triggers) {
					TriggersPk triggersPk = trigger.getPk();
					TriggerKey triggerKey = new TriggerKey(triggersPk.getName(), triggersPk.getGroup());
					triggerKeys.add(triggerKey);
					scheduler.pauseTrigger(triggerKey);
				}
				scheduler.unscheduleJobs(triggerKeys);
				scheduler.deleteJob(new JobKey(arr[1], arr[2]));
				redirectAttributes.addFlashAttribute("message", "删除成功");
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
			}
		}
		return "redirect:/admin/qrtz/job";
	}

	
}
