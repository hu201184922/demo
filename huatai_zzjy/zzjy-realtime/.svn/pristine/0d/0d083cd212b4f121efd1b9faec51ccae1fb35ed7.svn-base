package com.fairyland.jdp.webbindsupport.propertyeditors;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;


/**
 * 支持java.sql.Timestamp的属性注入编辑器
 * @author XiongMiao
 *
 */
public class CustomTimestampEditor extends AbstractDateEditor{

	public CustomTimestampEditor(DateFormat dateFormat, boolean allowEmpty) {
		super(dateFormat, allowEmpty);
	}

	@Override
	public Date getValue(Date date) {
		return new Timestamp(date.getTime());
	}


}
