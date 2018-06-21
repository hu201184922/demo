package com.fairyland.jdp.framework.i18n.spi.impl.jpa;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyland.jdp.framework.domain.IdEntity;
import com.fairyland.jdp.framework.i18n.annotation.I18NField;
import com.fairyland.jdp.framework.i18n.annotation.I18Nable;
import com.fairyland.jdp.framework.i18n.spi.impl.DefaultI18NSupportImpl;
import com.google.common.collect.Lists;

public class AnnotationI18NSupportImpl extends DefaultI18NSupportImpl {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public List<String> getI18NFields(String clsName) {
		List<String> fields = Lists.newArrayList();
		try {
			Class cls = Class.forName(clsName);
			Field[] allFields = cls.getDeclaredFields();
			for (Field field : allFields) {
				I18NField i18NField = field.getAnnotation(I18NField.class);
				if (i18NField != null) {
					fields.add(field.getName());
				}
			}

			Class supCls = cls.getSuperclass();
			while (!IdEntity.class.equals(supCls)) {
				Field[] allSupFields = supCls.getDeclaredFields();
				for (Field field : allSupFields) {
					I18NField i18NField = field.getAnnotation(I18NField.class);
					if (i18NField != null) {
						fields.add(field.getName());
					}
				}
				supCls = supCls.getSuperclass();
			}
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}
		return fields;
	}

	@Override
	public boolean checkI18Nable(String clsName) {
		try {
			Class cls = Class.forName(clsName);
			Annotation i18Nable = cls.getAnnotation(I18Nable.class);
			return i18Nable == null ? false : true;
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}
		return false;
	}
}
