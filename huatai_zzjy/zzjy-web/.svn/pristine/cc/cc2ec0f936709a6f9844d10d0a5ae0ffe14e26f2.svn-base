package com.fairyland.jdp.webbindsupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换器
 * 用于配置springMVC3.1以后  全局日期转换
 * @author XiongMiao
 *
 */
public class SqlDateConverter implements Converter<String, java.sql.Date> {

	@Override
	public java.sql.Date convert(String source) {
		if(StringUtils.isEmpty(source)){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    try {    
	    	java.util.Date date = dateFormat.parse(source);
	        return new java.sql.Date(date.getTime());    
	    } catch (ParseException e) {    
			throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
	    }
	}
}
