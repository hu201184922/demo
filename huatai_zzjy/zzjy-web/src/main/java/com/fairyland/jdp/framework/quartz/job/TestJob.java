package com.fairyland.jdp.framework.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

//@DisallowConcurrentExecution
public class TestJob extends QuartzJobBean{
	private Logger log = LoggerFactory.getLogger(getClass());
	private ApplicationContext applicationContext;

	/**
	 * 从SchedulerFactoryBean注入的applicationContext.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		log.info("TestJob Started.JobKey.Group="+jobKey.getGroup()+" JobKey.Name="+jobKey.getName());
//		for (int i=0; i<10 ; i++) {
//			try {
//				System.out.println("TestJob ");
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
	}

}
