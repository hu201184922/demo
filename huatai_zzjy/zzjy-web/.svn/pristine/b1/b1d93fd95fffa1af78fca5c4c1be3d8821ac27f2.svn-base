package com.fairyland.jdp.springboot.config.quartz;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.fairyland.jdp.framework.quartz.domain.QrtzDefinition;
import com.fairyland.jdp.framework.quartz.service.QrtzDefinitionService;
import com.fairyland.jdp.framework.quartz.utils.SchedulerUtil;
import com.fairyland.jdp.framework.util.SpringUtil;

public class StartAutoScanJob extends QuartzJobBean{
	private Logger log = LoggerFactory.getLogger(getClass());
	protected QrtzDefinitionService qrtzDefinitionService = SpringUtil.getBean(QrtzDefinitionService.class);
	
	protected SchedulerFactoryBean schedulerFactory = SpringUtil.getBean(SchedulerFactoryBean.class);

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		Long currentTime = System.currentTimeMillis();
		
		checkEnableGenerate(currentTime); 
		
		checkEnableStop(currentTime);
		
	}

	private void checkEnableStop(Long currentTime) {
		List<QrtzDefinition> gQrtzDefinitions = qrtzDefinitionService.getQrtzDefByState("已生成");
		for (QrtzDefinition qrtzDefinition : gQrtzDefinitions) {
			Date endJobDate = qrtzDefinition.getEndJobDate();
			if(endJobDate != null){
				if(currentTime >= endJobDate.getTime()){
					try {
						SchedulerUtil.stopJob(schedulerFactory, qrtzDefinition);
						qrtzDefinition.setState("终止");
						qrtzDefinitionService.updateQrtzDefinition(qrtzDefinition);
						log.info(qrtzDefinition.getCode() + "--->工作结束");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage(),e);
					}catch (SchedulerException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage(),e);
					}
				}
			}
		}
	}

	private void checkEnableGenerate(Long currentTime) {
		List<QrtzDefinition> rQrtzDefinitions = qrtzDefinitionService.getQrtzDefByState("发布");
		for (QrtzDefinition qrtzDefinition : rQrtzDefinitions) {
			Date startJobDate = qrtzDefinition.getStartJobDate();
			if(startJobDate != null){
				if(currentTime <= startJobDate.getTime()){
					try {
						SchedulerUtil.schedulerJob(schedulerFactory, qrtzDefinition);
						qrtzDefinition.setState("已生成");
						qrtzDefinitionService.updateQrtzDefinition(qrtzDefinition);
						log.info(qrtzDefinition.getCode() + "--->工作开始");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage(),e);
					}catch (SchedulerException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage(),e);
					}catch (ParseException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage(),e);
					}
				}
			}
		}
	}
	
	

	
}
