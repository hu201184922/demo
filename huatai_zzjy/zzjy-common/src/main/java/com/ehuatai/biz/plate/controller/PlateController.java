package com.ehuatai.biz.plate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehuatai.biz.plate.service.PlateService;
import com.ehuatai.conts.Module;
import com.ehuatai.ret.RestResult;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value = "/api/block", name = "板块")
public class PlateController {

	@Autowired
	private PlateService plateService;

	@Autowired
	private Module module;

	/**
	 * 1、板块菜单接口
	 * 
	 * @param reqParams
	 * @return
	 * 
	 * 菜单栏-----固定板块和我的板块
	 */
	@RequestMapping(value = "/navs")
	public RestResult blockNavs(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.blockNavs(reqParams);
	}

	/**
	 * 2、板块指标值接口
	 * 
	 * @param reqParams
	 * @return
	 * 
	 * 板块首页数据
	 */
	@RequestMapping(value = "/target")
	public RestResult blockTarget(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		if((boolean) reqParams.get("isCustom")){
			request.setAttribute("subject",reqParams.get("blockCode").toString());
		}else{
			request.setAttribute("subject","");
		}
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.blockTarget(reqParams);
	}

	/**
	 * 3、板块机构指标值接口
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value = "/org/target")
	public RestResult blockOrgTarget(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.blockOrgTarget(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.1、基本接口
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value = "/trend/base")
	public RestResult getTrendBase(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.getTrendBase(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.2、常用指标接口（common）
	 * 
	 * @param reqParams
	 * @return
	 * 
	 * 顶部蜜蜂图案数据
	 */
	@RequestMapping(value = "/trend/common")
	public RestResult getTrendCommon(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		reqParams.put("module", module.analysisCommon);

		return plateService.getTrendCommon(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.3、一级指标区域接口（main）
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value = "/trend/main")
	public RestResult getTrendMain(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		reqParams.put("module", module.analysisMain);

		return plateService.getTrendMain(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.4、二级指标区域接口（sub）
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value = "/trend/sub")
	public RestResult getTrendSub(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		reqParams.put("module", module.analysisSub);

		return plateService.getTrendSub(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.5、分公司/机构区域接口（dist）
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping("/trend/dist")
	public RestResult getTrendDist(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		reqParams.put("module", module.analysisDist);

		return plateService.getTrendDist(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.6、机构列表区域接口（org）
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping("/trend/org")
	public RestResult getTrendOrg(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		reqParams.put("module", module.analysisOrg);

		return plateService.getTrendOrg(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.7、保费部（torg）
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping("/trend/torg")
	public RestResult getTrendTorg(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		reqParams.put("module", module.analysisTorg);

		return plateService.getTrendTorg(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.8、特殊指标区域接口（spec）
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping("/trend/spec")
	public RestResult getTrendSpec(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		reqParams.put("module", module.analysisSpec);

		return plateService.getTrendSpec(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.9、指标设为默认接口
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping("/trend/def")
	public RestResult setTrendDef(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		return plateService.setTrendDef(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.10 区间统计
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value = "/trend/region")
	public RestResult getTrendRegion(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.getTrendRegion(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.11 机构对比 ->4.11.1 获取机构对比的参数
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value = "/trend/org/get")
	public RestResult getTrendOrgGet(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.getTrendOrgGet(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.11 机构对比 ->4.11.2 机构对比接口
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value = "/trend/org/compare")
	public RestResult getTrendOrgCompare(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.getTrendOrgCompare(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.12 导出清单接口->4.12.1 导出清单列表接口
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value = "/trend/report/list")
	public RestResult getTrendReportList(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.getTrendReportList(reqParams);
	}

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.12 导出清单接口->4.12.2 导出清单列表下载接口
	 * 
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value = "/trend/report/download")
	public RestResult getTrendReportDownload(@RequestBody Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.getTrendReportDownload(reqParams, request, response);
	}

	/**
	 * 5、我的板块接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/my")
	public RestResult blockMyApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.blockMyApi(reqParams);
	}

	/**
	 * 5、我的板块接口 -> 5.1、删除自定义板块
	 * 
	 * @return
	 */
	@RequestMapping(value = "/my/delete")
	public RestResult blockMyDelete(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.blockMyDelete(reqParams);
	}

	/**
	 * 5、我的板块接口 -> 5.2、新增板块 || 5.3、编辑板块
	 * 
	 * @return
	 */
	@RequestMapping(value = "/my/edit/target")
	public RestResult blockMyEditTarget(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.blockMyEditTarget(reqParams);
	}

	/**
	 * 5、我的板块接口 -> 5.2、新增板块 || 5.3、编辑板块 -> 5.2.1、保存操作接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/my/edit/save")
	public RestResult blockMyEditSave(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return plateService.blockMyEditSave(reqParams);
	}

	@RequestMapping(value = "/org/search")
	public Object orgSearchApi(String orgKey, Boolean hasThreeOrg, Boolean hasFourOrg, HttpServletRequest request) {
		Map<String, Object> reqParams = new HashMap<String, Object>();
		reqParams.put("orgKey", orgKey);
		reqParams.put("hasThreeOrg", hasThreeOrg);
		reqParams.put("hasFourOrg", hasFourOrg);
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = plateService.blockOrgSearch(reqParams);
		return retResult;
	}
}
