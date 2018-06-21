package com.fairyland.jdp.framework.i18n.hibernate;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyland.jdp.framework.i18n.LocaleMode;
import com.fairyland.jdp.framework.i18n.spi.I18NSupport;
import com.fairyland.jdp.framework.util.SpringUtil;

public class LocalizationInterceptorHelper {

	static private I18NSupport i18NSupport = null;

	static private I18NSupport getI18NSupport() {
		if (i18NSupport == null) {
			i18NSupport = SpringUtil.getBean(I18NSupport.class);
		}
		return i18NSupport;
	}

	private static final Logger log = LoggerFactory
			.getLogger(LocalizationInterceptorHelper.class);

	static public void load(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (getI18NSupport() == null)
			return;

		try {
			Locale currentLocale = i18NSupport.getCurrentLocale();
			Locale defaultLocale = i18NSupport.getDefaultLocale();
			Class cls = entity.getClass();
			// 判断当前类是否支持国际化以及是否需要国际化
			if (i18NSupport.checkI18Nable(cls.getName())
					&& !currentLocale.equals(defaultLocale)) {
				List<String> pNames = Arrays.asList(propertyNames);
				List<String> i18NFields = i18NSupport.getI18NFields(cls
						.getName());
				if (LocaleMode.ALL_IN_ONE.equals(i18NSupport.getLocaleMode())) {
					String i18NResString = (String) state[pNames
							.indexOf(I18NSupport.I18N_RES_STRING_PROPERTY)];
					Properties prop = new Properties();
					if (StringUtils.isNotEmpty(i18NResString)) {
						StringReader reader = new StringReader(i18NResString);
						prop.load(reader);
						for (String i18NField : i18NFields) {
							String localeField = i18NField + "_"
									+ currentLocale.toString();
							// 将对应的语言值赋给默认字段，表现层使用默认字段
							state[pNames.indexOf(i18NField)] = prop
									.getProperty(localeField);
						}
					}
				} else {
					for (String i18NField : i18NFields) {
						String localeField = i18NField + "_"
								+ currentLocale.toString();
						// 将对应的语言值赋给默认字段，表现层使用默认字段
						state[pNames.indexOf(i18NField)] = state[pNames
								.indexOf(localeField)];
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Localization error!", e);
		}
	}

	static public void save(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (getI18NSupport() == null)
			return;
		try {
			Locale currentLocale = i18NSupport.getCurrentLocale();
			Locale defaultLocale = i18NSupport.getDefaultLocale();
			Class cls = entity.getClass();
			// 判断当前类是否支持国际化
			if (false) {
//				List<String> pNames = Arrays.asList(propertyNames);
//				List<String> i18NFields = i18NSupport.getI18NFields(cls
//						.getName());
//				if (LocaleMode.ALL_IN_ONE.equals(i18NSupport.getLocaleMode())) {
//					Properties prop = new Properties();
//					for (String i18NField : i18NFields) {
//						List<Locale> locales = i18NSupport.getSupportLocales();
//						for (Locale locale : locales) {
//							String localeField = i18NField + "_"
//									+ locale.toString();
//							// 创建时，将每种语言的值设置成一样，方便切换语种时修改
//							String value = (String) state[pNames
//									.indexOf(i18NField)];
//							if (StringUtils.isNotEmpty(value))
//								prop.setProperty(localeField, value);
//						}
//					}
//					StringWriter writer = new StringWriter();
//					prop.store(writer, null);
//					String i18NResString = writer.toString();
//					state[pNames.indexOf(i18NSupport.I18N_RES_STRING_PROPERTY)] = i18NResString;
//				} else {
//					for (String i18NField : i18NFields) {
//						List<Locale> locales = i18NSupport.getSupportLocales();
//						for (Locale locale : locales) {
//							String localeField = i18NField + "_"
//									+ locale.toString();
//							// 创建时，将每种语言的值设置成一样，方便切换语种时修改
//							state[pNames.indexOf(localeField)] = state[pNames
//									.indexOf(i18NField)];
//						}
//					}
//				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Localization error!", e);
		}
	}

	static public void flushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (getI18NSupport() == null)
			return;
		try {
			Locale currentLocale = i18NSupport.getCurrentLocale();
			Locale defaultLocale = i18NSupport.getDefaultLocale();
			Class cls = entity.getClass();
			// 判断当前类是否支持国际化以及是否需要国际化
			// && !currentLocale.equals(defaultLocale)
			if (i18NSupport.checkI18Nable(cls.getName())) {
				List<String> pNames = Arrays.asList(propertyNames);
				List<String> i18NFields = i18NSupport.getI18NFields(cls
						.getName());
				// 获取当前Locale

				if (LocaleMode.ALL_IN_ONE.equals(i18NSupport.getLocaleMode())) {
					// 取出之前的i18NResString
					String i18NResString = (String) previousState[pNames
							.indexOf(I18NSupport.I18N_RES_STRING_PROPERTY)];
					Properties prop = new Properties();
					if (StringUtils.isEmpty(i18NResString))
						i18NResString = "";
					StringReader reader = new StringReader(i18NResString);
					prop.load(reader);
					for (String i18NField : i18NFields) {
						String defaultLocaleField = i18NField + "_"
								+ defaultLocale.toString();
						String localeField = i18NField + "_"
								+ currentLocale.toString();
						// 创建时，将每种语言的值设置成一样，方便切换语种时修改
						String currentValue = (String) currentState[pNames
								.indexOf(i18NField)];
						if (StringUtils.isNotEmpty(currentValue))
							prop.setProperty(localeField, currentValue);
						// 默认属性还原成原有值，此处只起一个数据载体作用，用来将其它语种的值从表现层传递过来
						// previousState[pNames .indexOf(i18NField)]

						// 默认属性始终用来记录默认语种的值
						currentState[pNames.indexOf(i18NField)] = prop
								.getProperty(defaultLocaleField);
					}
					StringWriter writer = new StringWriter();
					prop.store(writer, null);
					String newI18NResString = writer.toString();
					currentState[pNames
							.indexOf(i18NSupport.I18N_RES_STRING_PROPERTY)] = newI18NResString;
				} else {
					for (String i18NField : i18NFields) {
						String localeField = i18NField + "_"
								+ currentLocale.toString();
						// 修改具体的语种字段
						currentState[pNames.indexOf(localeField)] = currentState[pNames
								.indexOf(i18NField)];
						// 默认属性还原成原有值，此处只起一个数据载体作用，用来将其它语种的值从表现层传递过来
						currentState[pNames.indexOf(i18NField)] = previousState[pNames
								.indexOf(i18NField)];
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Localization error!", e);
		}
	}
}
