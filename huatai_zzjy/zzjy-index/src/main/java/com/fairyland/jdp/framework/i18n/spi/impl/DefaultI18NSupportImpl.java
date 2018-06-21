package com.fairyland.jdp.framework.i18n.spi.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.fairyland.jdp.framework.i18n.LocaleMode;
import com.fairyland.jdp.framework.i18n.LocaleUtil;
import com.fairyland.jdp.framework.i18n.spi.I18NSupport;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DefaultI18NSupportImpl implements I18NSupport {
	private Logger log = LoggerFactory.getLogger(getClass());
	private LocaleMode localeMode = LocaleMode.ALL_IN_ONE;

	private List<Locale> supportLocales = Lists.newArrayList(
			Locale.SIMPLIFIED_CHINESE, Locale.US);

	private Map<String, List<String>> i18NConfigMap = Maps.newHashMap();

	public void setLocalePersistentMode(String localeMode) {
		if (StringUtils.isNotEmpty(localeMode)
				&& LocaleMode.EACH_ALONE.value().equals(localeMode.trim())) {
			this.localeMode = LocaleMode.EACH_ALONE;
		}
	}

	public void setSupportLocales(List<Locale> supportLocales) {
		this.supportLocales = supportLocales;
	}

	public void setI18NableConfig(Properties i18NableConfig) {
		for (Object keyObj : i18NableConfig.keySet()) {
			String key = (String) keyObj;
			String value = i18NableConfig.getProperty(key);
			if (StringUtils.isNotEmpty(value)) {
				String[] i18NFields=value.trim().split(",");
				i18NConfigMap.put(key, Arrays.asList(i18NFields));
			}
		}
	}

	public void setI18NableConfigFile(Resource i18NableConfigFile) {
		Properties p = new Properties();
		try {
			p.load(i18NableConfigFile.getInputStream());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		this.setI18NableConfig(p);
	}

	@Override
	public LocaleMode getLocaleMode() {
		return localeMode;
	}

	@Override
	public Locale getCurrentLocale() {
		Locale locale = LocaleUtil.getCurrentLocale();
		return locale != null ? locale : getDefaultLocale();
	}

	@Override
	public Locale getDefaultLocale() {
		return Locale.SIMPLIFIED_CHINESE;
	}

	@Override
	public List<Locale> getSupportLocales() {
		return supportLocales;
	}

	@Override
	public List<String> getI18NFields(String entityName) {
		return i18NConfigMap.get(entityName);
	}

	@Override
	public boolean checkI18Nable(String entityName) {
		return i18NConfigMap.containsKey(entityName);
	}

}
