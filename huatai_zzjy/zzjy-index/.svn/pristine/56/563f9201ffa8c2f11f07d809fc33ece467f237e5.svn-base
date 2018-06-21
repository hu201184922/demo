package com.fairyland.jdp.webbindsupport.propertyeditors;

import java.text.DateFormat;
import java.util.Date;

/**
 * 支持java.sql.Date的属性注入编辑器
 * @author XiongMiao
 *
 */
public class CustomDateEditor extends AbstractDateEditor{

	public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty) {
		super(dateFormat, allowEmpty);
	}

	@Override
	public Date getValue(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
}
