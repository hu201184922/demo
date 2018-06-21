package com.fairyland.jdp.orm.hibernate.interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.fairyland.jdp.framework.i18n.hibernate.LocalizationInterceptorHelper;

public class HibernateInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		//TODO 增加国际化开关
		LocalizationInterceptorHelper.load(entity, id, state, propertyNames, types);
		return false;
	}
	
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		//LocalizationInterceptorHelper.save(entity, id, state, propertyNames, types);
		return false;
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		LocalizationInterceptorHelper.flushDirty(entity, id, currentState, previousState, propertyNames, types);
		return false;
	}
}
