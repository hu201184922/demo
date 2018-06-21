package com.huatai.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.huatai.web.model.TarStatis;
import com.huatai.web.model.Target;
import com.huatai.web.service.TarStatisService;
import com.huatai.web.service.TargetService;

@RequestMapping("admin/tarStatis")
@Controller
public class TarStatisController {
	private static final String[] RegCodes = new String[] { "TEMP03_REG02", "TEMP03_REG03" };
	@Autowired
	private TargetService targetService;
	@Autowired
	private TarStatisService tarStatisService;

	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/tarStatis/index");
		return model;
	}

	@RequestMapping("listStatisTarget")
	@ResponseBody
	public Pager<Target> listStatisTarget(Pager<Target> pager, Target target) {
		pager = targetService.findTargetStatisByPage(pager, target);
		return pager;
	}

	@RequestMapping("listRefTargets")
	@ResponseBody
	public Pager<Target> listRefTargets(Pager<Target> pager, String targetCodeSelected, Target targetQuery) {
		pager = targetService.findRefTarget(pager, RegCodes, targetQuery);
		TarStatis tarStatisQuery = new TarStatis();
		tarStatisQuery.setSubCode(targetCodeSelected);
		List<TarStatis> oldList = tarStatisService.findAll(tarStatisQuery);
		Set<String> targetCodeList = new HashSet<String>();
		for (TarStatis tarStatis : oldList) {
			targetCodeList.add(tarStatis.getTargetCode());
		}
		List<Target> list = pager.getPageItems();
		for (Target target : list) {
			if (targetCodeList.contains(target.getTargetCode())) {
				target.setChecked(true);
			} else {
				target.setChecked(false);
			}
		}
		return pager;
	}

	@RequestMapping("saveTarStatis")
	@ResponseBody
	public String saveTarStatis(String[] targetCode, String targetCodeSelected) {
		TarStatis tarStatisQuery = new TarStatis();
		tarStatisQuery.setSubCode(targetCodeSelected);
		List<TarStatis> oldList = tarStatisService.findAll(tarStatisQuery);
		for (TarStatis tarStatisOld : oldList) {
			tarStatisService.deleteByPrimaryKey(tarStatisOld.getTsId());
		}
		for (String targetCode1 : targetCode) {
			TarStatis tarStatis = new TarStatis();
			tarStatis.setCreateTime(new Date());
			tarStatis.setCreatorId(SessionContextUtils.getLoginName().toString());
			tarStatis.setOpType("A");
			tarStatis.setSubCode(targetCodeSelected);
			tarStatis.setTargetCode(targetCode1);
			tarStatisService.insert(tarStatis);
		}
		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "tree")
	public List<Target> findTree(String subCode) {
		// 不需要部门权限,设置空
		String depts = null;// SessionContextUtils.getCurrentUser().getDeptCode().toString();
		// 数据库存在，要回显的数据
		List<TarStatis> tarStatis = tarStatisService.findBySubCode(subCode, depts);
		List<Target> targets = targetService.findTargetStatis(null);
		if (tarStatis.size() == 0) {
			for (Target tar : targets) {
				tar.setpId(tar.getTargetType() == "0" ? "0" : tar.getTargetCode().substring(0, 3));
				tar.setId(tar.getTargetCode());
				tar.setName(tar.getTargetName());
			}
			return targets;
		}
		List<String> targetList = new ArrayList<>();
		List<String> tarStatisList = new ArrayList<>();

		for (TarStatis tar : tarStatis) {
			tarStatisList.add(tar.getTargetCode());
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
				tar.setpId(tar.getTargetType() == "0" ? "0" : tar.getTargetCode().substring(0, 3));
				tar.setId(tar.getTargetCode());
				tar.setName(tar.getTargetName());

				if (tar.getTargetCode().equals(tarSta)) {
					tar.setChecked(true);
				}
			}
		}

		return targets;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Boolean create(String[] targetCodes, String subCode, TarStatis record, Integer size, Model model) {
		int result = 0;
		try {
			if (targetCodes == null) {
				if (!(size > 1)) {
					delete(subCode);
					return true;
				}
				return true;
			}
			String depts = null;// SessionContextUtils.getCurrentUser().getDeptCode().toString();
			result = delete(subCode);
			List<TarStatis> roleTargets = this.tarStatisService.findBySubCode(subCode, depts);
			List<TarStatis> isRoleTargets = this.tarStatisService.findByIsSubCode(subCode, depts);
			List<String> isDelStr = new ArrayList<>();
			for (TarStatis is : isRoleTargets) {
				isDelStr.add(is.getTargetCode());
			}
			if (roleTargets.size() == 0) {
				for (String targetCode : targetCodes) {
					Target t = this.targetService.findByTargetCode(targetCode);
					if (!isRoleTargets.isEmpty()) {
						if (!(isDelStr.contains(targetCode))) {
							if (t.getTargetType().equals("1") || t.getTargetType().equals("3")) {
								record.setTargetCode(targetCode);
								record.setSubCode(subCode);
								record.setOpType("A");
								record.setModifyTime(new Date());
								record.setCreateTime(new Date());
								record.setModifierId(SessionContextUtils.getLoginName().toString());
								record.setCreatorId(SessionContextUtils.getLoginName().toString());
								tarStatisService.addTarStatis(record);
							}
						} else {
							record.setTargetCode(targetCode);
							record.setSubCode(subCode);
							record.setOpType("U");
							record.setModifyTime(new Date());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							tarStatisService.updateByTargetCodeAndAnSubCode(record);
						}
					} else {
						if (t.getTargetType().equals("1") || t.getTargetType().equals("3")) {
							record.setTargetCode(targetCode);
							record.setSubCode(subCode);
							record.setOpType("A");
							record.setModifyTime(new Date());
							record.setCreateTime(new Date());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							record.setCreatorId(SessionContextUtils.getLoginName().toString());
							tarStatisService.addTarStatis(record);
						}
					}
				}
				return true;
			} else {
				List<String> list = new ArrayList<>();
				for (TarStatis roleTarget : roleTargets) {
					list.add(roleTarget.getTargetCode());
				}
				for (String targetCode : targetCodes) {
					if (!list.contains(targetCode)) {
						Target t = this.targetService.findByTargetCode(targetCode);
						if (t.getTargetType().equals("1") || t.getTargetType().equals("3")) {
							record.setTargetCode(targetCode);
							record.setSubCode(subCode);
							record.setOpType("A");
							record.setModifyTime(new Date());
							record.setCreateTime(new Date());
							record.setCreatorId(SessionContextUtils.getLoginName().toString());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							tarStatisService.addTarStatis(record);
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
	public int delete(String subCode) {
		int result = tarStatisService.deleteTarStatis(subCode);
		return result;
	}
}
