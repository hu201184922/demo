package com.fairyland.jdp.webbindsupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

/**
 * 时间转换器
 * 用于配置springMVC3.1以后  全局日期转换
 * @author XiongMiao
 *
 */
public class TimeConverter implements Converter<String, java.sql.Time> {

	@Override
	public java.sql.Time convert(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");    
	    try {    
	    	java.util.Date date = dateFormat.parse(source);
	        return new java.sql.Time(date.getTime());    
	    } catch (ParseException e) {    
			throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
	    }
	}
}
