package com.ehuatai.biz.tool.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehuatai.biz.tool.service.ToolService;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value="/api/tool",name="工具")
public class ToolController {

	@Autowired
	private ToolService toolService;
	
	/**
	 * 六、工具  -> 1、我的预警接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warn/my")
	public Object browseNavsApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolWarnMy(reqParams);
	}
	/**
	 * 六、工具  -> 1. 我的预警接口 -> 1.1、关闭/开启预警接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warn/my/toggle")
	public Object toolWarnToggleApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolWarnToggle(reqParams);
	}
	/**
	 * 六、工具  -> 1、我的预警接口-> 1.2、编辑预警接口(GET) -> 1.2.1 GET获取详情
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warn/my/editid")
	public Object toolWarnEditGetApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolWarnEditGet(reqParams);
	}
	/**
	 * 六、工具  -> 1、我的预警接口-> 1.2、编辑预警接口(GET) -> 1.2.2 POST保存编辑结果
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warn/my/edit", method = RequestMethod.POST)
	public Object toolWarnEditPostApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolWarnEditPost(reqParams);
	}
	/**
	 * 六、工具  -> 1、我的预警接口-> 1.2、编辑预警接口(GET) -> 1.3、删除预警接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warn/my/delete")
	public Object toolWarnDeleteApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolWarnDelete(reqParams);
	}
	/**
	 * 六、工具  -> 1、我的预警接口-> 1.2、编辑预警接口(GET) -> 1.4、新增预警接口 -> 1.4.1、GET 获取预警的参数信息
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warn/my/addid")
	public Object toolWarnAddGetApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolWarnAddGet(reqParams);
	}
	/**
	 * 六、工具  -> 1、我的预警接口-> 1.2、编辑预警接口(GET) -> 1.4、新增预警接口 -> 1.4.1、POST 保存预警信息
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warn/my/add", method = RequestMethod.POST)
	public Object toolWarnAddPostApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolWarnAddPost(reqParams);
	}
	/**
	 * 六、工具  ->  2、预警结果接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warn/result")
	public Object toolWarnResultApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolWarnResult(reqParams);
	}
	/**
	 * 六、工具  -> 3、消息盒子接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/notice")
	public Object toolNoticeApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolNotice(reqParams);
	}
	/**
	 * 六、工具  ->  3、消息盒子接口 -> 3.1、标记已读提醒接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/notice/read")
	public Object toolNoticeReadApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolNoticeRead(reqParams);
	}
	/**
	 * 六、工具  ->  4、更新预警信息
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warn/update")
	public Object toolUpdateInfoApi(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return toolService.toolUpdateInfo(reqParams);
	}
}
