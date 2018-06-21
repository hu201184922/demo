package com.fairyland.jdp.framework.quartz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.quartz.domain.CronTrigger;

public interface CronTriggerDao extends PagingAndSortingRepository<CronTrigger, Long>,JpaSpecificationExecutor<CronTrigger>{

	List<CronTrigger> findByQrtzGroupId(Long qrtzGroupId);

	CronTrigger findByName(String name);
	
	CronTrigger findByCode(String code);

	@Query("select c from CronTrigger c where c.code=?1 and c.qrtzGroup.code=?2")
	CronTrigger findByTriggerCodeAndGroupCode(String triggerCode,String groupCode);
}
