package com.fairyland.jdp.framework.log.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.fairyland.jdp.framework.log.domain.HyLog;
import com.fairyland.jdp.framework.log.domain.LogBean;
import com.fairyland.jdp.framework.log.domain.RunLogBean;
import com.fairyland.jdp.orm.Pager;

public interface HyLogService {
	
	void createLog(HyLog hyLog);
	
	void createLog(HttpServletRequest request, String handler,
			Exception ex,String descript);
	
	void createLog(HttpServletRequest request, String handler,
			String errorMessage,String descript);

	 Page<HyLog> getAllLogs(Map<String, Object> searchParams,
 		    int pageNumber, int pageSize, String sortType);

	void createLogForApp(HttpServletRequest request,Map<String,Object> map);
	
	Pager<LogBean> getLogQuery(Pager<LogBean> pager,Map<String,Object> map);

	Pager<RunLogBean> getRunLogQuery(Pager<RunLogBean> pager,Map<String,Object> map);

	List<String> getRunLogDealTab();
}
