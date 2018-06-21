package com.huatai.web.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.mapper.TableExecuteMapper;
import com.huatai.web.model.TableExecute;
import com.huatai.web.service.TableExecuteService;

@Service
public class TableExecuteServiceImpl implements TableExecuteService {
	@Autowired
	private TableExecuteMapper tableExecuteMapper;

	@Override
	public List<TableExecute> findCompletedOfToday(String tableCode) {
		Integer type = 0;
		String newTableCode = tableCode;
		if (tableCode.startsWith("V_"))
			newTableCode = tableCode.substring(2);
		// 判断是否年月日表
		if (newTableCode.indexOf("DAY") > 0) {
			type = 1;
		} else if (newTableCode.indexOf("MONTH") > 0) {
			type = 2;
		} else {
			type = 3;
		}
		Calendar cal = Calendar.getInstance();
		Date dateCode = null;
		if (newTableCode.contains("HT_INPUTOUTPUT_RATIO_MONTH_TB")) {
			cal.add(Calendar.DATE, -1); // 得到前一天
			cal.add(Calendar.MONTH, -2); // 得到前一个月
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			dateCode = cal.getTime();
		} else {
			cal.add(Calendar.DATE, -1); // 得到前一天
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			dateCode = cal.getTime();
		}
		List<TableExecute> list = tableExecuteMapper.findCompletedOfToday(newTableCode, dateCode, type);
		return list;
	}

	public TableExecute findTableExecuteByTable(String tableCode) {
		Integer type = 0;
		if (tableCode.startsWith("V_")) {
			tableCode = tableCode.substring(2);
		}
		// 判断是否年月日表
		if (tableCode.indexOf("DAY") > 0) {
			type = 1;
		} else if (tableCode.indexOf("MONTH") > 0) {
			type = 2;
		} else {
			type = 3;
		}
		Calendar cal = Calendar.getInstance();
		Date dateCode = null;
		if (tableCode.contains("HT_INPUTOUTPUT_RATIO_MONTH_TB")) {
			cal.add(Calendar.DATE, -1); // 得到前一天
			cal.add(Calendar.MONTH, -2); // 得到前来两个月
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			dateCode = cal.getTime();
		} else {
			cal.add(Calendar.DATE, -1); // 得到前一天
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			dateCode = cal.getTime();
		}
		List<TableExecute> tableExecuteList = tableExecuteMapper.findTableExecuteByTable(tableCode, dateCode, type);
		if (null != tableExecuteList && tableExecuteList.size() == 1) {
			return tableExecuteList.get(0);
		} else {
			return null;
		}
	}

}
