package com.fairyland.jdp.framework.service;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.support.LockMetadataProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fairyland.jdp.framework.domain.IdEntity;

@Component
public class CommonDao<T extends IdEntity> implements ICommonDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

//	private LockMetadataProvider lockMetadataProvider;

//	public LockMetadataProvider getLockMetadataProvider() {
//		return lockMetadataProvider;
//	}
//
//	public void setLockMetadataProvider(
//			LockMetadataProvider lockMetadataProvider) {
//		this.lockMetadataProvider = lockMetadataProvider;
//	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<T> findAll(Class clazz, Specification<T> spec, Sort sort) {
		return getQuery(clazz, spec, sort).getResultList();
	}

	public T save(T entity) {
		if (entity.getId() == null) {
			entityManager.persist(entity);
			return entity;
		} else {
			return entityManager.merge(entity);
		}
	}

	private TypedQuery<T> getQuery(Class clazz, Specification spec, Sort sort) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(clazz);

		Root root = applySpecificationToCriteria(clazz, spec, query);
		query.select(root);

		if (sort != null) {
			query.orderBy(toOrders(sort, root, builder));
		}

		return applyLockMode(entityManager.createQuery(query));
	}

	private TypedQuery getQuery(Class clazz, Specification spec,
			Pageable pageable) {

		Sort sort = pageable == null ? null : pageable.getSort();
		return getQuery(clazz, spec, sort);
	}

	private Root applySpecificationToCriteria(Class clazz, Specification spec,
			CriteriaQuery query) {

		Assert.notNull(query);
		Root root = query.from(clazz);

		if (spec == null) {
			return root;
		}

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);

		if (predicate != null) {
			query.where(predicate);
		}

		return root;
	}

	private TypedQuery applyLockMode(TypedQuery query) {
//		LockModeType type = lockMetadataProvider == null ? null
//				: lockMetadataProvider.getLockModeType();
//		return type == null ? query : query.setLockMode(type);
		return query;
	}

	@Override
	public T findOne(Class clazz,Long id) {
		Assert.notNull(id, "The given id must not be null!");

//		LockModeType type = lockMetadataProvider == null ? null : lockMetadataProvider.getLockModeType();
		Class<T> domainType = clazz;

//		return type == null ? entityManager.find(domainType, id) : entityManager.find(domainType, id, type);
		return entityManager.find(domainType, id);
	}

	@Override
	public void delete(Class clazz,Long id) {
		Assert.notNull(id, "The given id must not be null!");

		T entity = findOne(clazz,id);

		if (entity == null) {
			throw new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!",
					clazz, id), 1);
		}

		delete(entity);
		
	}

	@Override
	public void delete(IdEntity entity) {
		// TODO Auto-generated method stub
		
	}
}
