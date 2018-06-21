package com.huatai.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.JyfxTarget;
import com.huatai.web.model.Target;
import com.huatai.web.service.TarManService;
import com.huatai.web.service.TargetService;

@RequestMapping("admin/tarMan")
@Controller
public class TarManController {
	@Autowired
	private TargetService targetService;
	@Autowired
	private TarManService tarManService;

	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/tarMan/index");
		return model;
	}

	@RequestMapping("listManTarget")
	@ResponseBody
	public Pager<Target> listManTarget(Pager<Target> pager, Target target) {
		pager = targetService.findTarManByPage(pager, target);
		return pager;
	}

	@ResponseBody
	@RequestMapping(value = "tree")
	public List<Target> findTree(String targetCode) {
		List<JyfxTarget> tarMan = tarManService.findByAnTargetCode(targetCode);
		List<Target> targets = targetService.findTargetByTarMan();
		if (tarMan == null || tarMan.size() == 0) {
			for (Target tar : targets) {
				tar.setpId(tar.getTargetType() == "0" ? "0" : tar.getTargetCode().substring(0, 4));
				tar.setId(tar.getTargetCode());
				tar.setName(tar.getTargetName());
			}
			return targets;
		}
		List<String> targetList = new ArrayList<>();
		List<String> tarStatisList = new ArrayList<>();

		for (JyfxTarget tar : tarMan) {
			tarStatisList.add(tar.getAnTargetCode());
		}

		for (Target tars : targets) {
			if (tarStatisList.size() == 0) {
				tars.setpId(tars.getTargetType());
				tars.setId(tars.getTargetCode());
			}
			targetList.add(tars.getTargetCode());
		}
		for (Target tar : targets) {
			for (String tarSta : tarStatisList) {
				tar.setpId(tar.getTargetType() == "0" ? "0" : tar.getTargetCode().substring(0, 4));
				tar.setId(tar.getTargetCode());
				tar.setName(tar.getTargetName());

				if (tar.getTargetCode().equals(tarSta)) {
					tar.setChecked(true);
				}
			}
		}

		return targets;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Boolean create(String[] anTargetCodes, String targetCode, JyfxTarget record, Integer size, Model model) {
		try {
			if (anTargetCodes == null) {
				if (!(size > 1)) {
					delete(targetCode);
					return true;
				}
				return true;
			}
			String depts = null;// SessionContextUtils.getCurrentUser().getDeptCode().toString();
			delete(targetCode);
			List<JyfxTarget> roleTargets = this.tarManService.findByAnTargetCode(targetCode, depts);
			List<JyfxTarget> isRoleTargets = this.tarManService.findByIsAnTargetCode(targetCode, depts);
			List<String> isDelStr = new ArrayList<>();
			for (JyfxTarget is : isRoleTargets) {
				isDelStr.add(is.getAnTargetCode());
			}
			if (roleTargets == null || roleTargets.size() == 0) {
				delete(targetCode);
				for (String anTargetCode : anTargetCodes) {
					Target t = this.targetService.findByTargetCode(anTargetCode);
					if (!isRoleTargets.isEmpty()) {
						if (!(isDelStr.contains(anTargetCode))) {
							if (t.getTargetType().equals("1") || t.getTargetType().equals("3")) {
								record.setTargetCode(targetCode);
								record.setAnTargetCode(anTargetCode);
								record.setOpType("A");
								record.setModifyTime(new Date());
								record.setCreateTime(new Date());
								record.setModifierId(SessionContextUtils.getLoginName().toString());
								record.setCreatorId(SessionContextUtils.getLoginName().toString());
								tarManService.addTarMan(record);
							}
						} else {
							record.setTargetCode(targetCode);
							record.setAnTargetCode(anTargetCode);
							record.setOpType("U");
							record.setModifyTime(new Date());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							tarManService.updateByTargetCodeAndAnTargetCode(record);
						}
					} else {
						if (t.getTargetType().equals("1") || t.getTargetType().equals("3")) {
							record.setTargetCode(targetCode);
							record.setAnTargetCode(anTargetCode);
							record.setOpType("A");
							record.setModifyTime(new Date());
							record.setCreateTime(new Date());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							record.setCreatorId(SessionContextUtils.getLoginName().toString());
							tarManService.addTarMan(record);
						}
					}
				}
				return true;
			} else {
				List<String> list = new ArrayList<>();
				for (JyfxTarget roleTarget : roleTargets) {
					list.add(roleTarget.getTargetCode());
				}
				for (String anTargetCode : anTargetCodes) {
					if (!list.contains(targetCode)) {
						Target t = this.targetService.findByTargetCode(targetCode);
						if (t.getTargetType().equals("1") || t.getTargetType().equals("3")) {
							record.setTargetCode(targetCode);
							record.setAnTargetCode(anTargetCode);
							record.setOpType("A");
							record.setModifyTime(new Date());
							record.setCreateTime(new Date());
							record.setCreatorId(SessionContextUtils.getLoginName().toString());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							tarManService.addTarMan(record);
							continue;
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

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(String targetCode) {
		int result = tarManService.deleteTarMan(targetCode);
		return result;
	}
}
