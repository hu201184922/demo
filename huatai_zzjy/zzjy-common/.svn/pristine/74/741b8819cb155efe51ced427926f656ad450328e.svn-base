package com.ehuatai.biz.browse.service;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.orm.Pager;

public interface BrowseSQLCacheService {

	/**
	 * 五 浏览清单-> 1. 浏览清单菜单接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> browseNavs(Map<String, Object> reqParams,String sql);
	
	
	/**
	 * 五 浏览清单-> -> 2.3 自定义清单接口
	 * @param reqParams
	 * @return
	 */
	public Pager<Map<String, Object>> browseListCustom(Map<String, Object> reqParams,Pager<Map<String, Object>> pager, String sql) ;
	/**
	 * 五 浏览清单-> -> 2.3 自定义清单接口 ->2.3.1 、自定义清单下载接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> browseListCustomDownload(Map<String, Object> reqParams,String sql);
	/**
	 * 五 浏览清单-> 2、浏览清单列表接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> browseList(Map<String, Object> reqParams,String sql);
	/**
	 * 五 浏览清单-> 2.1 、浏览清单下载接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> browseDownload(Map<String, Object> reqParams,String sql);
	/**
	 * 五 浏览清单-> 3、我的清单接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> browseMy(Map<String, Object> reqParams,String sql);
	/**
	 * 五 浏览清单-> 3、我的清单接口->3.1、添加清单->3.1.2、获取所有的指标接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> browseMyTargets(Map<String, Object> reqParams,String sql);
	/**
	 * 五 浏览清单-> 3、我的清单接口->3.1、添加清单->3.1.3、设置维度接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> browseEditDimen(Map<String, Object> reqParams,String sql);
	/**
	 * 五 浏览清单-> 3、我的清单接口->3.1、添加清单->3.1.4、保存操作接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> browseEditSave(Map<String, Object> reqParams,String sql);
	/**
	 * 五 浏览清单-> 3、我的清单接口->3.3、删除清单
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> browseDelete(Map<String, Object> reqParams,String sql);
}
