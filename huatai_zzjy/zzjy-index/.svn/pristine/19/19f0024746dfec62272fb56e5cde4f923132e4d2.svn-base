package com.fairyland.jdp.webbindsupport.propertyeditors;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

/**
 * 支持java.sql.Time的属性注入编辑器
 * @author XiongMiao
 *
 */
public class CustomTimeEditor extends AbstractDateEditor{

	public CustomTimeEditor(DateFormat dateFormat, boolean allowEmpty) {
		super(dateFormat, allowEmpty);
	}

	@Override
	public Date getValue(Date date) {
		return new Time(date.getTime());
	}
	
}
