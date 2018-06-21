package com.ehuatai.biz.browse.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehuatai.biz.browse.service.BrowseService;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value = "/api/browse", name = "浏览清单")
public class BrowseController {

	@Autowired
	private BrowseService browseServiceImpl;

	/**
	 * 1. 浏览清单菜单接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 * 
	 * 菜单栏
	 */
	@RequestMapping(value = "/navs")
	public Object browseNavsApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseNavs(reqParams);
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
	 * 2.2 固定清单接口 ->2.2.1 、固定清单下载接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/dept/download")
	public Object browseListDeptDownloadApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseListDeptDownload(reqParams, request, response);
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
	 * 2.3 自定义清单接口
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
	 * 2.3 自定义清单接口 -> 2.3.1 、自定义清单下载接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/custom/download")
	public Object browseListCustomDownloadApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseListCustomDownload(reqParams, request, response);
	}

	/**
	 * 2. 浏览清单列表接口 该接口废弃
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/list")
	public Object browseListApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseListData(reqParams);
	}

	/**
	 * 2.1. 浏览清单菜单接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/download")
	public Object browseDowloadsApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseDownload(reqParams);
	}

	/**
	 * 3、我的清单接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my")
	public Object browseMyApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseMy(reqParams);
	}

	/**
	 * 3.1.2、获取所有的指标接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my/edit/targets")
	public Object browseMyTargetsApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseMyTargets(reqParams);
	}

	/**
	 * 3.1.3、设置维度接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my/edit/targets/dimen")
	public Object browseEditDimenApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseEditDimen(reqParams);
	}

	/**
	 * 3.1.4、保存操作接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my/save")
	public Object browseSaveApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseSave(reqParams);
	}

	/**
	 * 3.2 编辑操作接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my/edit")
	public Object browseEditApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseEdit(reqParams);
	}

	/**
	 * 3.3、删除清单
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my/delete")
	public Object browseDeleteApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return browseServiceImpl.browseDelete(reqParams);
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
