package com.huatai.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.Target;
import com.huatai.web.model.UserSetWarn;
import com.huatai.web.service.GroDimDetailService;
import com.huatai.web.service.OrganizationsService;
import com.huatai.web.service.TargetService;
import com.huatai.web.service.UserSetWarnService;

/**
 * 用户设置预警的Controller
 *
 */

@Controller
@RequestMapping("admin/userSetWarn")
public class UserSetWarnController {
	@Autowired
	private UserSetWarnService userSetWarnService;

	@Autowired
	private TargetService targetService;

	@Autowired
	private OrganizationsService organizationsService;

	@Autowired
	private GroDimDetailService groDimDetailService;

	// 进入预警首页
	@ResponseBody
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(UserSetWarn record) {
		String dept = SessionContextUtils.getCurrentUser().getDeptCode().toString();
		List<Target> targets = new ArrayList<Target>();
		if (StringUtil.isNotNull(dept)) {
			List<Target> tarList = targetService.findTargetByIsWarnType(dept);
			targets.addAll(tarList);
		}
		ModelAndView model = new ModelAndView();
		// List<Target> list = new ArrayList<Target>();
		// if(StringUtil.isNotNull(targets)){
		// for (Target t1 : targets) {
		// String code = t1.getTargetCode().substring(0,3);
		// Target target = targetService.findByTargetCode(code);
		// if(StringUtil.isNull(target)){
		// code = t1.getTargetCode().substring(0,7);
		// target = targetService.findByTargetCode(code);
		// }
		// t1.setParentCode(target.getTargetCode());
		// if(StringUtil.isNotNull(target)){
		// if(list.size() != 0){
		// for (int i=0;i<list.size();i++) {
		// if(list.get(i).getTargetCode().equals(target.getTargetCode())){
		// break;
		// }
		// if(i == list.size()-1){
		// list.add(target);
		// }
		// }
		// }else{
		// list.add(target);
		// }
		// }
		// }
		// targets.addAll(list);
		// }
		model.addObject("targets", JSON.toJSON(targets));

		List<DeptInfo> organization = organizationsService.findOrganization();
		model.addObject("organization", JSON.toJSON(organization));
		model.setViewName("/admin/userSetWarn/index");
		return model;
	}

	// 预警列表
	@RequestMapping(value = "list")
	@ResponseBody
	public Pager<UserSetWarn> getPager(Pager<UserSetWarn> pager, UserSetWarn record, String orgName, String warnTarget,
			String warnStatus) {
		String userName = SessionContextUtils.getLoginName();
		Pager<UserSetWarn> Pager = userSetWarnService.findByPager(pager, record, orgName, warnTarget, warnStatus,
				userName);
		for (int i = 0; i < Pager.getPageItems().size(); i++) {
			String WarnCode = Pager.getPageItems().get(i).getWarnCode();
			String TargetName = Pager.getPageItems().get(i).getTargetName();
			Float MinVal = Pager.getPageItems().get(i).getMinVal();
			Float MaxVal = Pager.getPageItems().get(i).getMaxVal();
			String targetCode = Pager.getPageItems().get(i).getTargetCode();
			String field = targetService.findFieldByTargetCode(targetCode);
			if (StringUtil.isNull(field)) {
				field = "  ";
			}

			if (WarnCode.equals("1")) {
				WarnCode = TargetName + "大于" + MinVal + field;
				Pager.getPageItems().get(i).setWarnCode(WarnCode);
			} else if (WarnCode.equals("2")) {
				WarnCode = TargetName + "小于" + MaxVal + field;
				Pager.getPageItems().get(i).setWarnCode(WarnCode);
			} else if (WarnCode.equals("3")) {
				WarnCode = TargetName + "介于" + MaxVal + field + "到" + MinVal + field + "之间";
				Pager.getPageItems().get(i).setWarnCode(WarnCode);
			}
		}
		return Pager;
	}

	// 预警结果列表显示
	@RequestMapping(value = "resultlist")
	@ResponseBody
	public Pager<UserSetWarn> getPagerResult(Pager<UserSetWarn> pager, UserSetWarn record, String RorgName,
			String RwarnTarget) {
		Pager<UserSetWarn> page = userSetWarnService.findResultByPager(pager, record, RorgName, RwarnTarget);
		for (int i = 0; i < page.getPageItems().size(); i++) {
			String WarnCode = page.getPageItems().get(i).getWarnCode();
			String TargetName = page.getPageItems().get(i).getTargetName();
			Float MinVal = page.getPageItems().get(i).getMinVal();
			Float MaxVal = page.getPageItems().get(i).getMaxVal();
			String targetCode = pager.getPageItems().get(i).getTargetCode();
			String field = targetService.findFieldByTargetCode(targetCode);

			if (WarnCode.equals("1")) {
				WarnCode = TargetName + "大于" + MinVal + field;
				page.getPageItems().get(i).setWarnCode(WarnCode);
			} else if (WarnCode.equals("2")) {
				WarnCode = TargetName + "小于" + MaxVal + field;
				page.getPageItems().get(i).setWarnCode(WarnCode);
			} else if (WarnCode.equals("3")) {
				WarnCode = TargetName + "介于" + MaxVal + field + "到" + MinVal + field + "之间";
				page.getPageItems().get(i).setWarnCode(WarnCode);
			}
		}
		return page;
	}

	// 跳转至预警结果页面
	@RequestMapping(value = "result")
	public ModelAndView result() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/userSetWarn/result");
		return model;
	}

	// 修改预警状态
	@ResponseBody
	@RequestMapping(value = "updateStatus")
	public void updateStatus(String status, String bsId) {
		String tar = status;
		if (tar.equals("0")) {
			tar = "1";
		} else if (tar.equals("1")) {
			tar = "0";
		}
		userSetWarnService.updateStatus(tar, bsId);
	}

	// 添加预警
	@RequestMapping(value = "addwarn", method = RequestMethod.POST)
	public ModelAndView AddWarn(@RequestParam("bsId") Integer bsId, @RequestParam("targetName") String targetName,
			@RequestParam("targetCode") String targetCode, @RequestParam("warnCode") String warnCode,
			@RequestParam("orgNames") String orgNames, @RequestParam("orgCodes") String orgCodes,
			@RequestParam("alertType") String alertType, @RequestParam("valMin") Float valMin,
			@RequestParam("valMax") Float valMax) {
		ModelAndView model = new ModelAndView();
		String userName = SessionContextUtils.getLoginName().toString();
		UserSetWarn userWarn = new UserSetWarn();
		userWarn.setTargetCode(targetCode);
		userWarn.setWarnCode(warnCode);
		userWarn.setMinVal(valMin);
		userWarn.setMaxVal(valMax);
		userWarn.setAlertType(alertType);
		userWarn.setWarnStatus("1");
		userWarn.setOpType("A");
		userWarn.setModifyTime(new Date());
		userWarn.setWarnSettingType("2");
		userWarn.setCreatorId(userName);
		userWarn.setModifierId(userName);
		String[] name = orgNames.split(",");
		String[] code = orgCodes.split(",");
		for (int i = 0; i < name.length; i++) {
			String orgName = name[i];
			String orgCode = code[i];
			String groDimName = "";
			if (orgCode.length() == 1) {
				groDimName = "总公司";
			} else if (orgCode.length() == 3) {
				groDimName = "分公司";
			} else if (orgCode.length() == 5) {
				groDimName = "中支";
			}
			String orgType = groDimDetailService.findGroDimCodeByGroDimName(groDimName);
			userWarn.setOrgCode(orgCode);
			userWarn.setOrgName(orgName);
			userWarn.setOrgType(orgType);
			if (StringUtil.isNull(bsId)) {
				userWarn.setCreateTime(new Date());
				userSetWarnService.addWarn(userWarn);
			} else {
				userWarn.setBsId(bsId);
				userWarn.setOpType("U");
				userSetWarnService.updateWarn(userWarn);
			}
		}
		model.setViewName("/admin/userSetWarn/index");
		return model;
	}

	// 根据id查询预警
	@RequestMapping(value = "findById")
	@ResponseBody
	public UserSetWarn UpdateWarn(@RequestParam("bsId") Integer bsId) {
		UserSetWarn user = userSetWarnService.findWarnById(bsId);
		return user;
	}

	// 删除预警
	@RequestMapping(value = "delete")
	@ResponseBody
	public void delete(Integer id) throws ParseException {
		UserSetWarn userWarn = userSetWarnService.findWarnById(id);
		userWarn.setOpType("D");
		userWarn.setModifyTime(new Date());
		userWarn.setModifierId(SessionContextUtils.getLoginName().toString());
		userSetWarnService.updateWarn(userWarn);
	}

	// 查询指标单位
	@RequestMapping(value = "findField")
	@ResponseBody
	public String findField(String targetCode) throws ParseException {
		return targetService.findFieldByTargetCode(targetCode);
	}

	// 查询预警方式
	@RequestMapping(value = "alertType")
	@ResponseBody
	public List<String> alertType() throws ParseException {
		return userSetWarnService.findAlertType();
	}

}
