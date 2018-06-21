package com.fairyland.jdp.framework.quartz.service;

import java.text.ParseException;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.framework.quartz.dao.CronTriggerDao;
import com.fairyland.jdp.framework.quartz.domain.CronTrigger;
import com.fairyland.jdp.framework.quartz.utils.SchedulerUtil;
import com.huatai.web.model.TableTriggle;
import com.huatai.web.service.TableTriggleService;

@Service(value="cronTriggerService")
public class CronTriggerServiceImpl implements CronTriggerService {

	@Autowired
	private CronTriggerDao cronTriggerDao;
	
	@Autowired
	SchedulerFactoryBean schedulerFactory;
	
	@Autowired
	private TableTriggleService tableTriggleService;
	
	@Override
	public List<CronTrigger> getCronTriggers(Long groupId) {
		return cronTriggerDao.findByQrtzGroupId(groupId);
	}

	@Override
	public CronTrigger getCronTrigger(Long id) {
		return cronTriggerDao.findOne(id);
	}

	@Override
	public void updateCronTrigger(CronTrigger cronTrigger) {
		cronTriggerDao.save(cronTrigger);
	}

	@Override
	public void deleteCronTrigger(Long id) {
		CronTrigger cronTrigger = getCronTrigger(id);
		deleteTableTriggle(cronTrigger.getQrtzGroup().getId(),cronTrigger.getCode());
		cronTriggerDao.delete(id);
	}
	@Override
	public void deleteTableTriggle(Long qrtzGroupId,String qrtzCode){
		TableTriggle tableTriggle = new TableTriggle();
		tableTriggle.setQrtzGroupId(qrtzGroupId);
		tableTriggle.setQrtzCode(qrtzCode);
		List<TableTriggle> oldList = tableTriggleService.findAll(tableTriggle);
		for (TableTriggle tableTriggle2 : oldList) {
			tableTriggleService.delete(tableTriggle2);
		}
	}
	@Override
	public CronTrigger getQrtzDefinitionByName(String name) {
		return cronTriggerDao.findByName(name);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void release(CronTrigger cronTrigger) {
		cronTrigger.setState("发布");
		cronTriggerDao.save(cronTrigger);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void generate(CronTrigger cronTrigger) throws ClassNotFoundException, SchedulerException, ParseException {
		try {
			SchedulerUtil.schedulerJob(schedulerFactory, cronTrigger);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				//有时已经生成过的时候会报错，先停止，重新生成
				SchedulerUtil.stopJob(schedulerFactory, cronTrigger);
				SchedulerUtil.schedulerJob(schedulerFactory, cronTrigger);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		cronTrigger.setState("已生成");
		cronTriggerDao.save(cronTrigger);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void stop(CronTrigger cronTrigger) throws SchedulerException, ClassNotFoundException, ParseException {
		cronTrigger.setState("终止");
		SchedulerUtil.stopJob(schedulerFactory, cronTrigger);
	}

	@Override
	public CronTrigger getCronTrigger(String triggerCode, String groupCode) {
		return cronTriggerDao.findByTriggerCodeAndGroupCode(triggerCode,groupCode);
	}

	@Override
	public void run(CronTrigger cronTrigger) throws SchedulerException, ClassNotFoundException, ParseException {
		SchedulerUtil.runJob(schedulerFactory, cronTrigger);
	}

	@Override
	public String getState(CronTrigger cronTrigger) throws SchedulerException, ClassNotFoundException, ParseException {
		String state = SchedulerUtil.getJobState(schedulerFactory, cronTrigger);
		if("NORMAL".equals(state)){
			return "等待执行";
		}else if("BLOCKED".equals(state)){
			return "执行中";
		}else if("COMPLETE".equals(state)){
			return "完成";
		}else if("ERROR".equals(state)){
			return "出错";
		}else if("PAUSED".equals(state)){
			return "暂停";
		}else{
			return "无触发器";
		}
	}
	@Override
	public List<JobExecutionContext> getCurrentlyExecutingJobs() {
		return SchedulerUtil.getCurrentlyExecutingJobs(schedulerFactory);
	}

	@Override
	public List<CronTrigger> findAll() {
		return (List)cronTriggerDao.findAll();
	}

}
