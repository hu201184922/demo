package com.fairyland.jdp.framework.i18n.spi;

import java.util.List;
import java.util.Locale;

import com.fairyland.jdp.framework.i18n.LocaleMode;

public interface I18NSupport {
	/*
	 * 约定: domain类支持国际化，必须在类中加一个可持久化的资源字符串字段,由I18N_RES_STRING_PROPERTY常量指定
	 * domain类本身的字段，存储默认Locale的值，I18N_RES_STRING_PROPERTY设置为长度4000的String，用来存储类中
	 * 所有支持国际化的属性的非默认Locale的值，格式和普通properties文件一样。
	 */
	String I18N_RES_STRING_PROPERTY = "properties_string";

	LocaleMode getLocaleMode();

	Locale getCurrentLocale();

	Locale getDefaultLocale();

	List<Locale> getSupportLocales();

	/**
	 * Hibernate中entityName表示领域模型Java类，MyBatis中entityName表示表名
	 * @param entityName
	 * @return
	 */
	List<String> getI18NFields(String entityName);

	boolean checkI18Nable(String entityName);
}
