package com.fairyland.jdp.framework.quartz.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.quartz.domain.JobDetail;
import com.fairyland.jdp.framework.quartz.domain.JobDetailPk;

public interface JobDetailDao extends PagingAndSortingRepository<JobDetail, JobDetailPk>, JpaSpecificationExecutor<JobDetail>{

	

}
