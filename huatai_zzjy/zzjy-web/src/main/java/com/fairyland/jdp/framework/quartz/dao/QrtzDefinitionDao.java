package com.fairyland.jdp.framework.quartz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.quartz.domain.QrtzDefinition;

public interface QrtzDefinitionDao extends PagingAndSortingRepository<QrtzDefinition, Long>,JpaSpecificationExecutor<QrtzDefinition>{

	QrtzDefinition findByCode(String code);
	QrtzDefinition findByName(String name);
	
	
	List<QrtzDefinition> findByqrtzGroupId(Long qrtzGroupId);
	List<QrtzDefinition> findByState(String state);

}
