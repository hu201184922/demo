package com.huatai.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.FixedList;
import com.huatai.web.model.RoleFixedList;
import com.huatai.web.model.Target;
import com.huatai.web.service.FixedListService;
import com.huatai.web.service.RoleFixedListService;

/**
 * @author 胡智辉 2017年7月17日
 */
@Controller
@RequestMapping("admin/roleFixedList")
public class RoleFixedListController {

	final static Logger logger = LoggerFactory.getLogger(RoleFixedListController.class);

	@Autowired
	private RoleFixedListService roleFixedListService;

	@Autowired
	private FixedListService fixedListService;

	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView main() throws Exception {
		ModelAndView view = new ModelAndView();
		List<Target> targets = roleFixedListService.selectTargetByTree(null);
		view.addObject("targets", JSON.toJSON(targets));
		view.setViewName("/admin/roleRoleFixedList/index");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<RoleFixedList> find(Pager<RoleFixedList> pager, RoleFixedList record) {
		Pager<RoleFixedList> result = roleFixedListService.findByPager(pager, record);
		logger.debug("---------------find success--------------");
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Boolean create(@Valid RoleFixedList record, String[] targetCodes, String roleCode, Integer size) {
		@SuppressWarnings("unused")
		int result = 0;
		List<String> lists = new ArrayList<>();
		try {
			if (targetCodes == null) {
				if (!(size > 1)) {
					delete(roleCode);
					return true;
				}
				return true;
			}
			if (targetCodes.length != 0) {
				for (String string : targetCodes) {
					if (!string.equals("[object Window]"))
						lists.add(string);
				}
				result = delete(roleCode);
				List<RoleFixedList> roleTargets = this.roleFixedListService.findByRoleCode(roleCode);
				List<RoleFixedList> isRoleTargets = this.roleFixedListService.findByIsRoleCode(roleCode);
				List<String> isDelStr = new ArrayList<>();
				for (RoleFixedList is : isRoleTargets) {
					isDelStr.add(is.getFlCode());
				}
				if (roleTargets.size() == 0) {
					for (String targetCode : lists) {
						// 如果op_type等于D的数据不存在,说明是第一次插入
						if (!isRoleTargets.isEmpty()) {
							if (!(isDelStr.contains(targetCode))) {
								record.setFlCode(targetCode);
								record.setOpType("A");
								record.setCreateTime(new Date());
								record.setModifyTime(new Date());
								record.setCreatorId(SessionContextUtils.getLoginName().toString());
								record.setModifierId(SessionContextUtils.getLoginName().toString());
								result = roleFixedListService.insert(record);
							} else {
								record.setFlCode(targetCode);
								record.setOpType("U");
								record.setModifyTime(new Date());
								record.setModifierId(SessionContextUtils.getLoginName().toString());
								result = roleFixedListService.updateRoleFixed(record);
							}
						} else {
							record.setFlCode(targetCode);
							record.setOpType("A");
							record.setCreateTime(new Date());
							record.setModifyTime(new Date());
							record.setCreatorId(SessionContextUtils.getLoginName().toString());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							result = roleFixedListService.insert(record);
						}
					}
					return true;
				} else {
					List<String> list = new ArrayList<>();
					for (RoleFixedList roleTarget : roleTargets) {
						list.add(roleTarget.getFlCode());
					}
					for (String targetCode : lists) {
						if (!list.contains(targetCode)) {
							record.setFlCode(targetCode);
							record.setRoleCode(roleCode);
							record.setOpType("A");
							record.setCreatorId(SessionContextUtils.getLoginName().toString());
							record.setModifyTime(new Date());
							record.setModifierId(SessionContextUtils.getLoginName().toString());
							roleFixedListService.insert(record);
							continue;
						}
					}
				}
			}
			logger.debug("---------------add success--------------");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@Valid RoleFixedList record) {
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setOpType("U");
		int result = roleFixedListService.update(record);
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(String roleCode) {
		RoleFixedList record = new RoleFixedList();
		record.setRoleCode(roleCode);
		record.setOpType("D");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		int result = roleFixedListService.update(record);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "tree")
	public List<FixedList> findTree(String roleCode, String flDeptCode) {
		List<FixedList> deptCodes = fixedListService.findDeptCodeByGroup();
		// String[] depts =
		// SessionContextUtils.getCurrentUser().getDeptCode().toString().split(",");
		List<FixedList> fixedLists = new ArrayList<>();
		List<FixedList> result = new ArrayList<>();

		for (FixedList dept : deptCodes) {
			result = roleFixedListService.findByFlDeptCode(dept.getFlDeptCode(), roleCode);
			List<RoleFixedList> roleFixedLists = roleFixedListService.findByRoleCode(roleCode);
			List<String> fixedListList = new ArrayList<>();
			List<String> roleFixedList = new ArrayList<>();
			fixedLists.addAll(result);

			for (RoleFixedList roleTarget : roleFixedLists) {
				roleFixedList.add(roleTarget.getFlCode());
			}
			for (FixedList fixedList : fixedLists) {
				if (roleFixedLists.size() == 0) {
					fixedList.setpId(fixedList.getFlDeptCode());
					fixedList.setId(fixedList.getFlCode());
				}
				fixedListList.add(fixedList.getFlCode());
			}
			for (FixedList fixedList : fixedLists) {
				for (String roleTarget : roleFixedList) {
					fixedList.setpId(fixedList.getFlDeptCode());
					fixedList.setId(fixedList.getFlCode());
					if (fixedList.getFlCode().equals(roleTarget)) {
						fixedList.setChecked(true);
					}
				}
			}
		}
		for (FixedList dept : deptCodes) {
			FixedList fixedList = new FixedList();
			fixedList.setpId("0");
			fixedList.setId(dept.getFlDeptCode());
			DictionaryItem dictItem = dictionaryService.findByDictCodeAndDictItemCode("DETP_DICT",
					dept.getFlDeptCode());
			fixedList.setFlName(dictItem.getName());
			fixedLists.add(fixedList);
		}
		return fixedLists;
	}

}
