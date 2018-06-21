package com.fairyland.jdp.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static String pattern="yyyy-MM-dd HH:mm:ss";
	
	private static SimpleDateFormat usersf;
	
	public static Date String2Date(Object datestr) throws ParseException{
		usersf=new SimpleDateFormat(pattern);
		return usersf.parse((String) datestr);
	}
}
