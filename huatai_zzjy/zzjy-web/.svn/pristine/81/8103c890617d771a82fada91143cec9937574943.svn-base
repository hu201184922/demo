package com.huatai.web.job;

import java.text.ParseException;
import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.fairyland.jdp.framework.util.RedisUtil;
import com.huatai.web.model.TriggleExecute;
import com.huatai.web.service.TriggleExecuteService;
import com.huatai.web.utils.RedisDataSwitch;

public class ClearRedisJob extends QuartzJobBean {

	private Logger log = LoggerFactory.getLogger(getClass());
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		TriggleExecuteService triggleExecuteService = applicationContext.getBean(TriggleExecuteService.class);
		// 清除昨天的脏数据
		triggleExecuteService.deleteByPrimaryKey(35L, "CLEAR");
		Date now = new Date();
		TriggleExecute teToday = new TriggleExecute();
		teToday.setQrtzGroupId(35L);
		teToday.setQrtzCode("CLEAR");
		teToday.setExecBeginTime(now);
		teToday.setExecEndTime(null);
		teToday.setExecStatus("0");
		triggleExecuteService.insert(teToday);
		// 关闭系统读取缓存开关
		RedisDataSwitch.closeRedisData();
		// 清除全部系统缓存数据
		RedisUtil.delByPattern("ZZJY*");
		log.info("System info：清除全部系统缓存数据成功！");
		// 生成redis任务开关
		try {
			RedisDataSwitch.startRedisTask();
		} catch (ClassNotFoundException | SchedulerException | ParseException e) {
			e.printStackTrace();
		}
		// --------------
		teToday.setExecStatus("1");
		Date endTime = new Date();
		teToday.setExecEndTime(endTime);
		triggleExecuteService.updateByPrimaryKey(teToday);
	}
}
