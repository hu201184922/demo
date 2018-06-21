package com.fairyland.jdp.framework.quartz.service;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.framework.quartz.dao.SimpleTriggerDao;
import com.fairyland.jdp.framework.quartz.domain.SimpleTrigger;
import com.fairyland.jdp.framework.quartz.utils.SchedulerUtil;

@Service(value="simpleTriggerService")
public class SimpleTriggerServiceImpl implements SimpleTriggerService {

	@Autowired
	private SimpleTriggerDao simpleTriggerDao;
	
	@Autowired
	SchedulerFactoryBean schedulerFactory;
	
	@Override
	public List<SimpleTrigger> getSimpleTriggers(Long groupId) {
		return simpleTriggerDao.findByQrtzGroupId(groupId);
	}

	@Override
	public void deleteSimpleTrigger(Long id) {
		// TODO Auto-generated method stub
		simpleTriggerDao.delete(id);
	}

	@Override
	public SimpleTrigger getSimpleTrigger(Long id) {
		return simpleTriggerDao.findOne(id);
	}

	@Override
	public void updateSimpleTrigger(SimpleTrigger simpleTrigger) {
		// TODO Auto-generated method stub
		simpleTriggerDao.save(simpleTrigger);
	}

	@Override
	public SimpleTrigger getQrtzDefinitionByName(String name) {
		// TODO Auto-generated method stub
		return simpleTriggerDao.findByName(name);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void release(SimpleTrigger simpleTrigger) {
		// TODO Auto-generated method stub
		simpleTrigger.setState("发布");
		simpleTriggerDao.save(simpleTrigger);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void generate(SimpleTrigger simpleTrigger) throws ClassNotFoundException, SchedulerException {
		// TODO Auto-generated method stub
		SchedulerUtil.schedulerJob(schedulerFactory, simpleTrigger);
		simpleTrigger.setState("已生成");
		simpleTriggerDao.save(simpleTrigger);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void stop(SimpleTrigger simpleTrigger) throws ClassNotFoundException, SchedulerException {
		// TODO Auto-generated method stub
		simpleTrigger.setState("终止");
		SchedulerUtil.stopJob(schedulerFactory, simpleTrigger);
	}

}
