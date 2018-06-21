package com.ehuatai.app.browse.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ehuatai.biz.browse.service.BrowseService;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value = "/app/browse", name = "浏览清单")
public class AppBrowseController {

	@Autowired
	private BrowseService browseServiceImpl;

	/**
	 * 1. 浏览清单菜单接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/navs")
	public Object browseNavsApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseNavs(reqParams);
	}

	/**
	 * 2.1. 浏览清单列表接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/custom")
	public Object browseListCustomApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseListCustom(reqParams);
	}

	/**
	 * 2.2 固定清单接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/dept")
	public Object browseListDeptApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseListDept(reqParams);
	}
	/**
	 * @状态 OK
	 * @功能 {自定义浏览清单信息接口}
	 * @作者 MaxBill
	 * @时间 2017年11月2日 上午10:44:10
	 */
	@RequestMapping(value = "/list/custom/info")
	public Object browseListInfoApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		request.setAttribute("subject",reqParams.get("browseCode").toString());
		return browseServiceImpl.browseListCustomInfo(reqParams);
	}
	/**
	 * @功能 {浏览清单机构联动}
	 * @作者 MaxBill
	 * @时间 2017年9月11日 上午11:14:41
	 */
	@PostMapping("/org")
	public Object getOrg(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.getOrgData(reqParams);
	}

}
