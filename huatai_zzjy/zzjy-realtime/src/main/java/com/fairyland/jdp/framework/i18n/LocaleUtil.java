package com.fairyland.jdp.framework.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.fairyland.jdp.framework.util.SpringUtil;

public class LocaleUtil {

	static public Locale getCurrentLocale(){
		Locale locale = null;
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes != null) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			LocaleResolver localeResolver = (LocaleResolver) SpringUtil
					.getBean("localeResolver");
			locale = localeResolver.resolveLocale(request);
		}
		return locale;
	}
}
