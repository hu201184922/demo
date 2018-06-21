package com.fairyland.jdp.webbindsupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

/**
 * 时间戳转换器
 * 用于配置springMVC3.1以后  全局日期转换
 * @author XiongMiao
 *
 */
public class TimestampConverter implements Converter<String, java.sql.Timestamp> {

	@Override
	public java.sql.Timestamp convert(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
	    try {    
	    	java.util.Date date = dateFormat.parse(source);
	        return new java.sql.Timestamp(date.getTime());    
	    } catch (ParseException e) {    
			throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
	    }
	}
}
