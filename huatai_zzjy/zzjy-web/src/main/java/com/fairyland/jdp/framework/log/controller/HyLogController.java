package com.fairyland.jdp.framework.log.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.framework.log.domain.LogBean;
import com.fairyland.jdp.framework.log.domain.RunLogBean;
import com.fairyland.jdp.framework.log.service.HyLogService;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value="/admin/logqry")
public class HyLogController {
	@Autowired
	private HyLogService hyLogService;
	
	@Autowired
	private UserService userService;

	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("id_ASC", "自动");
		sortTypes.put("loginName_ASC", "登录用户");
		sortTypes.put("time_DESC", "访问日期");
	}
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/log/index");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("logQuery")
	public Pager<LogBean> getLogQuery(Pager<LogBean> pager,HttpServletRequest request){
		pager.setPageSize(10);
		Map<String, Object> map = new HashMap<String, Object>();
		String isType = request.getParameter("isType");
		if(StringUtil.isNotNull(isType)){
			map.put("isType", isType);
		}
		String beginDate = request.getParameter("beginDate");
		if(StringUtil.isNotNull(beginDate)){
			map.put("beginDate", beginDate);
		}
		String endDate = request.getParameter("endDate");
		if(StringUtil.isNotNull(endDate)){
			map.put("endDate", endDate);
		}
		pager = hyLogService.getLogQuery(pager, map);
		return pager;
	}
	
	@RequestMapping("logQueryReport")
	public void logQueryReport(HttpServletRequest request,HttpServletResponse response){
		Pager<LogBean> pager = new Pager<LogBean>();
		pager.setPageSize(Integer.MAX_VALUE);
		Map<String, Object> map = new HashMap<String, Object>();
		String isType = request.getParameter("isType");
		if(StringUtil.isNotNull(isType)){
			map.put("isType", isType);
		}
		String beginDate = request.getParameter("beginDate");
		if(StringUtil.isNotNull(beginDate)){
			map.put("beginDate", beginDate);
		}
		String endDate = request.getParameter("endDate");
		if(StringUtil.isNotNull(endDate)){
			map.put("endDate", endDate);
		}
		pager = hyLogService.getLogQuery(pager, map);
		
		String[] headList = new String[]{"机构（城市）","渠道","客户经理代码","客户经理名称","登录次数","最近登录时间","最近系统数据更新时间","网点数据","理财经理数"};
		String[] dataList = new String[]{"orgName","channel","loginName","userName","loginCount","lastLoginTime","lastUpdateTime","compCount","lacomtoagentCount"};
		
//		CrmUtil.exportExcel(request, response, pager.getPageItems(), headList, dataList);
	}
	
	@RequestMapping("runLog")
	public ModelAndView RunLog(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		List<String> dealTabs = hyLogService.getRunLogDealTab();
		mav.addObject("dealTabs", dealTabs);
		mav.setViewName("admin/log/runLog");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("runLogQuery")
	public Pager<RunLogBean> getRunLogQuery(Pager<RunLogBean> pager,HttpServletRequest request){
		pager.setPageSize(10);
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotNull(request.getParameter("dealTab"))){
			map.put("dealTab", request.getParameter("dealTab"));
		}
		String beginDate = request.getParameter("beginDate");
		if(StringUtil.isNotNull(beginDate)){
			map.put("beginDate", beginDate);
		}
		String endDate = request.getParameter("endDate");
		if(StringUtil.isNotNull(endDate)){
			map.put("endDate", endDate);
		}
		pager = hyLogService.getRunLogQuery(pager,map);
		return pager;
	}
	
	@RequestMapping("runLogReport")
	public void runLogReport(HttpServletRequest request,HttpServletResponse response){
		Pager<RunLogBean> pager = new Pager<RunLogBean>();
		pager.setPageSize(Integer.MAX_VALUE);
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotNull(request.getParameter("dealTab"))){
			map.put("dealTab", request.getParameter("dealTab"));
		}
		String beginDate = request.getParameter("beginDate");
		if(StringUtil.isNotNull(beginDate)){
			map.put("beginDate", beginDate);
		}
		String endDate = request.getParameter("endDate");
		if(StringUtil.isNotNull(endDate)){
			map.put("endDate", endDate);
		}
		pager = hyLogService.getRunLogQuery(pager,map);
		
		String[] headList = new String[]{"DEAL_ACTION","DEAL_TAB","ID","NAME","OWNER","DEAL_TIME"};
		String[] dataList = new String[]{"dealAction","dealTab","id","name","owner","dealTime"};
		
//		CrmUtil.exportExcel(request, response, pager.getPageItems(), headList, dataList);
	}
	
	
	
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String list(
//			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
//			@RequestParam(value = "page.size", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
//			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
//			Model model, ServletRequest request) {
//		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
//				request, "search_");
//		Page<HyLog> logs = hyLogService.getAllLogs(searchParams, pageNumber,
//				pageSize, sortType);
//	
//
//		model.addAttribute("logs", logs);
//		model.addAttribute("sortType", sortType);
//		model.addAttribute("sortTypes", sortTypes);
//		model.addAttribute("searchParams", Servlets
//				.encodeParameterStringWithPrefix(searchParams, "search_"));
//		return "jdp-framework/log/log-list";
//	}
}
