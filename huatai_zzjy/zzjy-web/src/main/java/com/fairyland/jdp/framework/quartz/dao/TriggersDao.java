package com.fairyland.jdp.framework.quartz.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.quartz.domain.Triggers;
import com.fairyland.jdp.framework.quartz.domain.TriggersPk;

public interface TriggersDao extends PagingAndSortingRepository<Triggers, TriggersPk>,JpaSpecificationExecutor<Triggers>{
	
	@Query("SELECT a FROM Triggers a WHERE a.jobName = ?1 AND a.jobGroup = ?2")
	public List<Triggers> findTriggersList(String jobName, String group);
	
	@Query("SELECT COUNT(a) FROM Triggers a WHERE a.jobName = ?1 AND a.jobGroup = ?2")
	public Long getTriggersNum(String jobName, String group);
	
}
