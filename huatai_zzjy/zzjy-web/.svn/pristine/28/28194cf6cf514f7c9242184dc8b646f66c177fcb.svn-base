package com.fairyland.jdp.framework.quartz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.quartz.domain.SimpleTrigger;

public interface SimpleTriggerDao  extends PagingAndSortingRepository<SimpleTrigger, Long>,JpaSpecificationExecutor<SimpleTrigger>{

	List<SimpleTrigger> findByQrtzGroupId(Long qrtzGroupId);
	
	SimpleTrigger findByName(String name);
	
	SimpleTrigger findByCode(String code);

}
