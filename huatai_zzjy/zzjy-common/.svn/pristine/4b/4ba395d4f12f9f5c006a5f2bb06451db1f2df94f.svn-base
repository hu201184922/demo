package com.fairyland.jdp.framework.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.fairyland.jdp.framework.domain.IdEntity;

public interface ICommonDao<T extends IdEntity> {
//
//	IdEntity findOne(Specification<IdEntity> spec);
//
//	List<IdEntity> findAll(Specification<IdEntity> spec);
//
//	Page<IdEntity> findAll(Specification<IdEntity> spec, Pageable pageable);

	List<T> findAll(Class clazz,Specification<T> spec, Sort sort);

//	long count(Specification<IdEntity> spec);
//
	T save(T entity);
//
//	Iterable<IdEntity> save(Iterable<IdEntity> entities);
//
	IdEntity findOne(Class clazz,Long id);
//
//	boolean exists(Long id);
//
//	Iterable<IdEntity> findAll();
//
//	Iterable<IdEntity> findAll(Iterable<Long> ids);
//
//	long count();
//
	void delete(Class clazz,Long id);
//
	void delete(IdEntity entity);
//
//	void delete(Iterable<IdEntity> entities);
//
//	void deleteAll();
//	
//	Iterable<IdEntity> findAll(Sort sort);
//
//	Page<IdEntity> findAll(Pageable pageable);
}
