package com.ehuatai.biz.browse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ehuatai.biz.browse.bean.OrgBean;
import com.ehuatai.ret.RestResult;

public interface BrowseService {
	/**
	 * 五 浏览清单-> 1. 浏览清单菜单接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseNavs(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 2.2、固定清单接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseListDept(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 2.2、固定清单接口 -> 2.2.1 、固定清单下载接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseListDeptDownload(Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response);

	public RestResult browseListCustomInfo(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 2.3 自定义清单接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseListCustom(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 2.3 自定义清单接口 ->2.3.1 、自定义清单下载接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseListCustomDownload(Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 五 浏览清单-> 2、浏览清单列表接口
	 * 
	 * @param reqParams
	 * @return
	 */
	@Deprecated
	public RestResult browseListData(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 2.1 、浏览清单下载接口
	 * 
	 * @param reqParams
	 * @return
	 */
	@Deprecated
	public RestResult browseDownload(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 3、我的清单接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseMy(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 3、我的清单接口->3.1、添加清单->3.1.2、获取所有的指标接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseMyTargets(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 3、我的清单接口->3.1、添加清单->3.1.3、设置维度接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseEditDimen(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 3、我的清单接口->3.1、添加清单
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseSave(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 3、我的清单接口->3.2 编辑清单
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseEdit(Map<String, Object> reqParams);

	/**
	 * 五 浏览清单-> 3、我的清单接口->3.3、删除清单
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult browseDelete(Map<String, Object> reqParams);

	// 查询机构列表
	List<HashMap<String, Object>> getOrgList(int type, String code, Boolean isServer);

	// 查询机构信息
	OrgBean getOrg(int type, String code);

	// 机构数据
	RestResult getOrgData(Map<String, Object> reqParams);
}
