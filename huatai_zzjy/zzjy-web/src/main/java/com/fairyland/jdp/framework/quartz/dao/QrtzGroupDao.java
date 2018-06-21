package com.fairyland.jdp.framework.quartz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.quartz.domain.QrtzGroup;

public interface QrtzGroupDao  extends PagingAndSortingRepository<QrtzGroup, Long>,JpaSpecificationExecutor<QrtzGroup>{

	@Query("SELECT a FROM QrtzGroup a WHERE a.pid = ?1")
	List<QrtzGroup> getChild(Long id);
	
	QrtzGroup findByCode(String code);

}
