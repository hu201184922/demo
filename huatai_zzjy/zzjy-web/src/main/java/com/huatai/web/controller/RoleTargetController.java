package com.huatai.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.huatai.web.model.RoleTarget;
import com.huatai.web.model.Target;
import com.huatai.web.service.RoleTargetService;
import com.huatai.web.service.TargetService;

/**
 * @author 胡智辉 2017年7月21日
 */
@Controller
@RequestMapping("admin/roleTarget")
public class RoleTargetController {
	@Autowired
	private RoleTargetService roleTargetService;

	@Autowired
	private TargetService targetService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/roleTarget/index");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "tree")
	public List<Target> findTree(String roleCode) {
		// 不需要部门权限,设置空
		String depts = null;// SessionContextUtils.getCurrentUser().getDeptCode().toString();
		List<RoleTarget> roleTargets = roleTargetService.findRoleTargetByRoleCode(roleCode, depts);
		List<Target> targets = targetService.findTargetByDept(depts);
		List<String> targetList = new ArrayList<>();
		List<String> roleTargetList = new ArrayList<>();
		if (roleTargets.size() != 0) {
			for (RoleTarget roleTarget : roleTargets) {
				roleTargetList.add(roleTarget.getTargetCode());
			}
			for (int i = 0; i <= targets.size() - 1; i++) {
				targetList.add(targets.get(i).getTargetCode());
			}
			for (int i = 0; i <= targets.size() - 1; i++) {
				for (int j = 0; j <= roleTargetList.size() - 1; j++) {
					if (targetList.get(i).equals(roleTargetList.get(j))) {
						targets.get(i).setChecked(true);
					}
				}
			}
		}

		return targets;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Boolean create(String[] targetCodes, String roleCode, RoleTarget record, Integer size, Model model) {
		try {
			if (targetCodes == null) {
				if (!(size > 1)) {
					delete(roleCode);
					return true;
				}
				return true;
			}
			if (targetCodes != null) {
				delete(roleCode);
				// 不需要部门权限,设置空
				String depts = null;// SessionContextUtils.getCurrentUser().getDeptCode().toString();
				List<RoleTarget> roleTargets = this.roleTargetService.findRoleTargetByRoleCode(roleCode, depts);
				List<RoleTarget> isRoleTargets = this.roleTargetService.findByIsRoleCode(roleCode);
				List<String> isDelStr = new ArrayList<>();
				for (RoleTarget is : isRoleTargets) {
					isDelStr.add(is.getTargetCode());
				}
				if (roleTargets.size() == 0) {
					for (String targetCode : targetCodes) {
						if (!isRoleTargets.isEmpty()) {
							if (!(isDelStr.contains(targetCode))) {
								record.setTargetCode(targetCode);
								record.setRoleCode(roleCode);
								record.setOpType("A");
								record.setModifyTime(new Date());
								record.setCreateTime(new Date());
								record.setModifierId(SessionContextUtils.getLoginName().toString());
								record.setCreatorId(SessionContextUtils.getLoginName().toString());
								roleTargetService.addRoleTarget(record);
							} else {
								record.setTargetCode(targetCode);
								record.setRoleCode(roleCode);
								record.setOpType("U");
								record.setModifyTime(new Date());
								record.setModifierId(SessionContextUtils.getLoginName().toString());
								roleTargetService.updateByRoleTarget(record);
							}
						} else {
							record.setTargetCode(targetCode);
							record.setRoleCode(roleCode);
							record.setOpType("A");
							record.setModifyTime(new Date());
							record.setCreateTime(new Date());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							record.setCreatorId(SessionContextUtils.getLoginName().toString());
							roleTargetService.addRoleTarget(record);
						}
					}
					return true;
				} else {
					List<String> list = new ArrayList<>();
					for (RoleTarget roleTarget : roleTargets) {
						list.add(roleTarget.getTargetCode());
					}
					for (String targetCode : targetCodes) {
						if (!list.contains(targetCode)) {
							record.setTargetCode(targetCode);
							record.setRoleCode(roleCode);
							record.setOpType("A");
							record.setModifyTime(new Date());
							record.setCreateTime(new Date());
							record.setCreatorId(SessionContextUtils.getLoginName().toString());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							roleTargetService.addRoleTarget(record);
							continue;
						}
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@Valid RoleTarget roleTarget, RedirectAttributes redirectAttributes) {
		roleTarget.setOpType("U");
		roleTarget.setModifierId(SessionContextUtils.getLoginName().toString());
		int result = roleTargetService.addRoleTarget(roleTarget);
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(String roleCode) {
		RoleTarget record = new RoleTarget();
		record.setRoleCode(roleCode);
		record.setOpType("D");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		int result = roleTargetService.update(record);
		return result;
	}

}
