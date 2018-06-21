package com.fairyland.jdp.framework.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil2 {
private static String pattern="yyyy-MM-dd";
	
	private static SimpleDateFormat usersf;
	
	public static Date StringToDate(Object datestr) throws ParseException{
		usersf=new SimpleDateFormat(pattern);
		return usersf.parse((String) datestr);
	}
}
