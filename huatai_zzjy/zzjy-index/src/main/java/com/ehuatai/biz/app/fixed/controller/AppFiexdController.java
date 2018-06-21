package com.ehuatai.biz.app.fixed.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ehuatai.biz.index.fixed.service.FixedService;
import com.ehuatai.conts.Module;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value = "/app/index")
public class AppFiexdController {
	@Autowired
	private FixedService fixedServiceImpl;
	
	@Autowired
	private Module module;
	
	/**
	 * 首页->2、固定指标
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fixed")
	public Object indexFixedApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		
		request.setAttribute("deptCode", reqParams.get("dept").toString());
		
		return fixedServiceImpl.fixed(reqParams);
	}
	
	/**
	 * 首页->2、固定指标 ->2.1 主要指标接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fixed/main")
	public Object indexFixedMainApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		
		reqParams.put("module", module.indexFixedMain);
		
		return fixedServiceImpl.fixedMain(reqParams);
	}
	
	/**
	 * 首页->2、固定指标->2.1 主要指标接口 -> 2.1.1 主要指标下钻接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fixed/main/drilldown")
	public Object indexFixedMainDrilldown(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		reqParams.put("module", module.indexFixedMain);
		return fixedServiceImpl.fixedMainDrilldown(reqParams);
		
	}
	
	/**
	 * 首页->2、固定指标-> 2.2、月度指标统计接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fixed/mcount")
	public Object indexFixedMcount(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		
		reqParams.put("module", module.indexFixedMonth);
		
		return fixedServiceImpl.fixedMcount(reqParams);
		
	}
	
	/**
	 * 首页->2、固定指标-> 2.2、月度指标统计接口 -> 2.2.1、经营信息接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fixed/mcount/info")
	public Object indexFixedMcountInfo(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		reqParams.put("module", module.indexFixedMonth);
		return fixedServiceImpl.fixedMcountInfo(reqParams);
		
	}
	
	
	/**
	 * 首页->2、固定指标-> 2.2、月度指标统计接口 -> 2.2.2、月度排行榜接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fixed/mcount/ranking")
	public Object indexFixedMcountRanking(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		
		reqParams.put("modules", new String[] {module.indexFixedMap,module.indexFixedHonor,module.indexFixedRank});
		
		return fixedServiceImpl.fixedMcountRanking(reqParams);
		
	}
	
	/**
	 * 首页->2、固定指标-> 2.3、月度指标列表(核心指标统计)接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fixed/core")
	public Object indexFixedCore(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		
		reqParams.put("module",module.indexFixedMonthlist);
		
		return fixedServiceImpl.fixedCore(reqParams);
		
	}

}
