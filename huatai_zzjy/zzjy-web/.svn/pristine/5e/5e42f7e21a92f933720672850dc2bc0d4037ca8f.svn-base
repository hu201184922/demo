package com.fairyland.jdp.springboot.config.quartz;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.alibaba.druid.util.StringUtils;
import com.fairyland.jdp.framework.util.PropsUtil;

@Configuration
public class FairylandSchedulerFactoryBean{
	
	@Resource(name="dataSource1")
	private DataSource dataSource;
	
	@Resource(name="timerTrigger")
	private Trigger timerTrigger;
	
	@Resource(name="scanTimerTrigger")
	private Trigger scanTimerTrigger;
	
//	@Resource(name="timerJobDetail")
//	private JobDetail timerJobDetail;
	
//	@Resource(name="scanJobDetail")
//	private JobDetail scanJobDetail;
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(){
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		String quartzFactoryName=PropsUtil.get("quartz.factoryName");
		if(!StringUtils.isEmpty(quartzFactoryName))
			schedulerFactoryBean.setBeanName(quartzFactoryName);
		schedulerFactoryBean.setTriggers(timerTrigger,scanTimerTrigger);
		schedulerFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:quartz-cluster.properties"));
		schedulerFactoryBean.setStartupDelay(2);
		schedulerFactoryBean.setDataSource(dataSource);
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
		return schedulerFactoryBean;
	}
	
	@Bean(name="timerTrigger")
	public SimpleTriggerFactoryBean timerTrigger(){
		SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
		simpleTriggerFactoryBean.setName("timerTrigger");
		simpleTriggerFactoryBean.setBeanName("timerTrigger");
		simpleTriggerFactoryBean.setJobDetail(timerJobDetail().getObject());
		simpleTriggerFactoryBean.setRepeatInterval(300000);
		return simpleTriggerFactoryBean;
	}
	
	@Bean(name="timerJobDetail")
	public JobDetailFactoryBean timerJobDetail(){
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setBeanName("timerJobDetail");
		jobDetailFactoryBean.setName("timerJobDetail");
		jobDetailFactoryBean.setDurability(true);
		jobDetailFactoryBean.setJobClass(com.fairyland.jdp.framework.schedule.quartz.QuartzClusterableJob.class);
		return jobDetailFactoryBean;
	}
	@Bean(name="scanTimerTrigger")
	public SimpleTriggerFactoryBean scanTimerTrigger(){
		SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
		simpleTriggerFactoryBean.setName("scanTimerTrigger");
		simpleTriggerFactoryBean.setBeanName("scanTimerTrigger");
		simpleTriggerFactoryBean.setJobDetail(scanJobDetail().getObject());
		simpleTriggerFactoryBean.setRepeatInterval(300000);
		return simpleTriggerFactoryBean;
	}
	
	@Bean(name="scanJobDetail")
	public JobDetailFactoryBean scanJobDetail(){
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setBeanName("scanJobDetail");
		jobDetailFactoryBean.setName("scanJobDetail");
		jobDetailFactoryBean.setDurability(true);
		jobDetailFactoryBean.setJobClass(StartAutoScanJob.class);
		return jobDetailFactoryBean;
	}
	
	@Bean
	public Map timerJobConfig(){
		Map map = new HashMap();
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();
	        map.put("nodeName", ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}  
        return map;
	}
}
