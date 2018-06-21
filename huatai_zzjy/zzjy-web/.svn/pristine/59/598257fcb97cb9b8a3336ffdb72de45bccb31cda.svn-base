package com.fairyland.jdp.framework.quartz.service;

import java.text.ParseException;
import java.util.List;

import org.quartz.SchedulerException;

import com.fairyland.jdp.framework.quartz.domain.QrtzDefinition;

public interface QrtzDefinitionService {

	void addQrtzDefinition(QrtzDefinition qrtzDefinition);
	
	void updateQrtzDefinition(QrtzDefinition qrtzDefinition);
	
	void deleteQrtzDefinition(Long qrtzDefinitionId);
	
	List<QrtzDefinition> findAll();
	
	QrtzDefinition getQrtzDefinitionById(Long qrtzDefinitionId);
	
	QrtzDefinition getQrtzDefinitionByName(String qrtzDefinitionName);
	
	QrtzDefinition getQrtzDefinitionByCode(String qrtzDefinitionCode);
	
	List<QrtzDefinition> getGroupQrtzDefinitions(Long qrtzGroupId);

	List<QrtzDefinition> getQrtzDefByState(String state);
	
	void generate(QrtzDefinition qrtzDefinition) throws ClassNotFoundException, SchedulerException, ParseException;
	
	void release(QrtzDefinition qrtzDefinition);
	
	void stop(QrtzDefinition qrtzDefinition) throws ClassNotFoundException, SchedulerException;
}
