package com.ehuatai.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Long getTime(String date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			
			Date d = sdf.parse(date);
			
			return d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getDate(Long time,String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(cal.getTime());
	}

}


