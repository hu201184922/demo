package com.fairyland.jdp.framework.quartz.service;

import java.text.ParseException;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.framework.quartz.dao.QrtzDefinitionDao;
import com.fairyland.jdp.framework.quartz.domain.QrtzDefinition;
import com.fairyland.jdp.framework.quartz.utils.SchedulerUtil;

@Transactional
@Service(value="qrtzDefinitionService")
public class QrtzDefinitionServiceImpl implements QrtzDefinitionService{

	@Autowired
	private QrtzDefinitionDao qrtzDefinitionDao;
	
	@Autowired
	SchedulerFactoryBean schedulerFactory;

	
	@Override
	public void addQrtzDefinition(QrtzDefinition qrtzDefinition) {
		qrtzDefinitionDao.save(qrtzDefinition);
	}

	@Override
	@CacheEvict(value="QrtzDefinitionCache", allEntries = true)
	public void updateQrtzDefinition(QrtzDefinition qrtzDefinition) {
		qrtzDefinitionDao.save(qrtzDefinition);
	}

	@Override
	@CacheEvict(value="QrtzDefinitionCache", allEntries = true)
	public void deleteQrtzDefinition(Long qrtzDefinitionId) {
		qrtzDefinitionDao.delete(qrtzDefinitionId);
	}

	@Override
	public List<QrtzDefinition> findAll() {
		return (List<QrtzDefinition>) qrtzDefinitionDao.findAll();
	}

	@Override
	public QrtzDefinition getQrtzDefinitionById(Long qrtzDefinitionId) {
		return qrtzDefinitionDao.findOne(qrtzDefinitionId);
	}

	@Override
	public QrtzDefinition getQrtzDefinitionByCode(String qrtzDefinitionCode) {
		return qrtzDefinitionDao.findByCode(qrtzDefinitionCode);
	}

	@Override
	public QrtzDefinition getQrtzDefinitionByName(String qrtzDefinitionName) {
		return qrtzDefinitionDao.findByName(qrtzDefinitionName);
	}

	@Override
	public List<QrtzDefinition> getGroupQrtzDefinitions(Long qrtzGroupId) {
		return qrtzDefinitionDao.findByqrtzGroupId(qrtzGroupId);
	}

	@Override
	@Cacheable(value = "QrtzDefinitionCache", key="#state")
	public List<QrtzDefinition> getQrtzDefByState(String state) {
		return qrtzDefinitionDao.findByState(state);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void generate(QrtzDefinition qrtzDefinition) throws ClassNotFoundException, SchedulerException, ParseException {
		SchedulerUtil.schedulerJob(schedulerFactory, qrtzDefinition);
		qrtzDefinition.setState("已生成");
		qrtzDefinitionDao.save(qrtzDefinition);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void release(QrtzDefinition qrtzDefinition) {
		qrtzDefinition.setState("发布");
		qrtzDefinitionDao.save(qrtzDefinition);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void stop(QrtzDefinition qrtzDefinition) throws ClassNotFoundException, SchedulerException {
		qrtzDefinition.setState("终止");
		SchedulerUtil.stopJob(schedulerFactory, qrtzDefinition);
	}

}
