package com.huatai.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Role;
import com.huatai.web.service.RoleService;

/**
 * @author 胡智辉 2017年7月17日
 */
@Controller
@RequestMapping("admin/roles")
public class RolesController {

	final static Logger logger = LoggerFactory.getLogger(RolesController.class);

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView main() throws Exception {
		ModelAndView view = new ModelAndView();
		//角色关联固定清单
		/*List<String> deptCode=new ArrayList();
		List<FixedList> deptCodes = fixedListService.findDeptCodeByGroup();
		for (FixedList target : deptCodes) {
			deptCode.add(target.getFlDeptCode());
		}	
		view.addObject("deptCode",deptCode);
		*/
		view.setViewName("/admin/roles/index");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<Role> find(Pager<Role> pager, Role record) {
		Pager<Role> result = roleService.findByPager(pager, record);
		logger.debug("---------------find success--------------");
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public int create(@Valid Role record) {
		int result = 0;
		try {
			record.setOpType("A");
			record.setCreatorId(SessionContextUtils.getLoginName().toString());
			record.setModifierId(SessionContextUtils.getLoginName().toString());
			record.setCreateTime(new Date());
			record.setModifyTime(new Date());
			result = roleService.addRole(record);
			logger.debug("---------------add success--------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@Valid Role record) {
		record.setOpType("U");
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setModifyTime(new Date());
		int result = roleService.updateRole(record);
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(@Valid Role record) {
		record.setOpType("D");
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setModifyTime(new Date());
		int result = roleService.updateRole(record);
		return result;
	}

}
