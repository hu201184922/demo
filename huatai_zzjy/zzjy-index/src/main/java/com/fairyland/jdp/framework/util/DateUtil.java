package com.fairyland.jdp.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static String pattern="yyyy-MM-dd HH:mm:ss";
	
	private static SimpleDateFormat usersf;
	
	public static Date String2Date(Object datestr) throws ParseException{
		usersf=new SimpleDateFormat(pattern);
		return usersf.parse((String) datestr);
	}
	public static Date getExpireDate(){
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MINUTE, 30);
		return cal.getTime();
	}
	public static Date getOffsetTime(Integer seconds){
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}
}
