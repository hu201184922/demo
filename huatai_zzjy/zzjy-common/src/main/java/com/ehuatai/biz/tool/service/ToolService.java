package com.ehuatai.biz.tool.service;

import java.util.Map;

import com.ehuatai.ret.RestResult;

public interface ToolService {

	/**
	 * 六 工具-> 1. 我的预警接口
	 * @param reqParams
	 * @return
	 */
	public RestResult toolWarnMy(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 1. 我的预警接口 -> 1.1、关闭/开启预警接口
	 * @param reqParams
	 * @return
	 */
	public RestResult toolWarnToggle(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 1. 我的预警接口 -> 1.2、编辑预警接口(GET) -> 1.2.1 GET获取详情
	 * @param reqParams
	 * @return
	 */
	public RestResult toolWarnEditGet(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 1. 我的预警接口 -> 1.2、编辑预警接口(GET) -> 1.2.2 POST保存编辑结果
	 * @param reqParams
	 * @return
	 */
	public RestResult toolWarnEditPost(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 1. 我的预警接口 -> 1.2、编辑预警接口(GET) -> 1.3、删除预警接口
	 * @param reqParams
	 * @return
	 */
	public RestResult toolWarnDelete(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 1. 我的预警接口 -> 1.2、编辑预警接口(GET) -> 1.4、新增预警接口 -> 1.4.1、GET 获取预警的参数信息
	 * @param reqParams
	 * @return
	 */
	public RestResult toolWarnAddGet(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 1. 我的预警接口-> 1.2、编辑预警接口(GET) -> 1.4、新增预警接口 -> 1.4.1、POST 保存预警信息
	 * @param reqParams
	 * @return
	 */
	public RestResult toolWarnAddPost(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 2、预警结果接口
	 * @param reqParams
	 * @return
	 */
	public RestResult toolWarnResult(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 3、消息盒子接口
	 * @param reqParams
	 * @return
	 */
	public RestResult toolNotice(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 3、消息盒子接口 -> 3.1、标记已读提醒接口
	 * @param reqParams
	 * @return
	 */
	public RestResult toolNoticeRead(Map<String, Object> reqParams);
	/**
	 * 六 工具-> 3、更新预警信息
	 * @param reqParams
	 * @return
	 */
	public RestResult toolUpdateInfo(Map<String, Object> reqParams);
}
