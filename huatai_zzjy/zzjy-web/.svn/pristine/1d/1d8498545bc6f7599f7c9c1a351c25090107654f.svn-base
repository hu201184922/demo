package com.fairyland.jdp.framework.quartz.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fairyland.jdp.framework.quartz.domain.JobDetail;
import com.fairyland.jdp.framework.quartz.domain.JobDetailPk;
import com.fairyland.jdp.framework.quartz.domain.Triggers;

public interface JobDetailService {

	Page<JobDetail> findAllJobs(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortType);
	
	Triggers getTrigger(JobDetail jobDetail);
	
	Long getTriggersNum(JobDetail jobDetail);
	
	List<Triggers> getTriggers(JobDetail jobDetail);
	
	JobDetail findOne(JobDetailPk jobDetailPk);
	
	JobDetail findOneByIdString(String idString);
}
