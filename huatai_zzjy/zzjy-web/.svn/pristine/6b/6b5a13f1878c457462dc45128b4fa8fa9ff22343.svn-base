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
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Target;
import com.huatai.web.model.TargetInter;
import com.huatai.web.service.TargetInterService;
import com.huatai.web.service.TargetService;

/**
 * @author 胡智辉 2017年7月27日
 */
@Controller
@RequestMapping("admin/targetInter")
public class TargetInterController {
	@Autowired
	private TargetInterService targetInterService;

	@Autowired
	private TargetService targetService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/targetInter/index");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<TargetInter> getPager(Pager<TargetInter> pager, TargetInter record) {
		pager = targetInterService.findByPager(pager, record);
		return pager;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Boolean create(String[] targetCodes, Integer interId, int size, TargetInter record, Model model) {
		try {
			if (targetCodes == null) {
				if (!(size > 1)) {
					delete(interId, null);
					return true;
				}
				return true;
			}
			if (targetCodes != null) {
				delete(interId, null);
				List<TargetInter> targetInters = this.targetInterService.findTargetInterByInterId(interId.toString());
				List<TargetInter> isRoleTargets = this.targetInterService.findByIsTargetInter(interId);
				List<String> isDelStr = new ArrayList<>();
				for (TargetInter is : isRoleTargets) {
					isDelStr.add(is.getTargetCode());
				}
				if (targetInters.size() == 0) {
					for (String targetCode : targetCodes) {
						if (!isRoleTargets.isEmpty()) {
							if (!(isDelStr.contains(targetCode))) {
								record.setTargetCode(targetCode);
								record.setInterId(interId);
								record.setOpType("A");
								record.setCreateTime(new Date());
								record.setModifyTime(new Date());
								record.setCreatorId(SessionContextUtils.getLoginName().toString());
								targetInterService.insertSelective(record);
							} else {
								record.setTargetCode(targetCode);
								record.setInterId(interId);
								record.setOpType("U");
								record.setCreateTime(new Date());
								record.setModifyTime(new Date());
								record.setCreatorId(SessionContextUtils.getLoginName().toString());
								targetInterService.updateTargetInter(record);
							}
						} else {
							record.setTargetCode(targetCode);
							record.setInterId(interId);
							record.setOpType("A");
							record.setCreateTime(new Date());
							record.setModifyTime(new Date());
							record.setCreatorId(SessionContextUtils.getLoginName().toString());
							targetInterService.insertSelective(record);
						}
					}
					return true;

				} else {
					if (targetCodes != null) {
						List<String> list = new ArrayList<>();
						for (TargetInter roleTarget : targetInters) {
							list.add(roleTarget.getTargetCode());
						}
						for (String targetCode : targetCodes) {
							if (!list.contains(targetCode)) {
								record.setTargetCode(targetCode);
								record.setInterId(interId);
								record.setCreateTime(new Date());
								record.setModifyTime(new Date());
								record.setOpType("A");
								record.setCreatorId(SessionContextUtils.getLoginName().toString());
								targetInterService.insertSelective(record);
								continue;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@Valid TargetInter record, RedirectAttributes redirectAttributes) {
		record.setOpType("U");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		int result = targetInterService.updateByPrimaryKeySelective(record);
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(Integer interId, String targetCode) {
		int result = 0;
		if (targetCode == null) {
			TargetInter record = new TargetInter();
			record.setInterId(interId);
			record.setOpType("D");
			record.setModifyTime(new Date());
			record.setModifierId(SessionContextUtils.getLoginName().toString());
			result = targetInterService.updateInterId(record);
		} else {
			result = targetInterService.updateByInterIdAndTargetCode(interId, targetCode);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "tree")
	public List<Target> findTree(String interId) {
		List<TargetInter> targetInters = targetInterService.findTargetInterByInterId(interId);
		List<Target> targets = targetService.findTargetParentId("0");
		List<String> targetList = new ArrayList<>();
		List<String> roleTargetList = new ArrayList<>();
		if (targetInters.size() != 0) {
			for (TargetInter roleTarget : targetInters) {
				roleTargetList.add(roleTarget.getTargetCode());
			}
		}
		if (targetInters.size() != 0) {
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

}
