package com.huatai.web.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.huatai.web.utils.RedisDataSwitch;

public class StartRedisJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		RedisDataSwitch.startRedisData();
	}

}
