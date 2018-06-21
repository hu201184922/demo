package com.fairyland.jdp.framework.security.controller;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.constant.Constants;
import com.fairyland.jdp.framework.organizational.agent.service.OrgRoleService;
import com.fairyland.jdp.framework.organizational.city.domain.City;
import com.fairyland.jdp.framework.organizational.city.service.CityService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.Organization;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.OrganizationService;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;

/**
 * 外勤用户管理
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/admin/sales")
public class SalesController {
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private CityService cityService;

	
	@RequestMapping("index")
	public ModelAndView index() {
		//获取当前用户
		User u=SessionContextUtils.getCurrentUser();
		String orgCode = u.getOrgCode();
		//
		ModelAndView view = new ModelAndView();
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_type", "02");
		Page<Role> rolesPage = roleService.getAllRoles(searchParams, 1, 2000, "id_ASC");
		List<Role> roles = rolesPage.getContent();
		view.addObject("searchRoles", roles);
		List<City> list=new ArrayList<City>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("lv", 2);
		if (!"86".equals(orgCode)&&!"".equals(orgCode)) {
			map.put("orgCode", orgCode);
		}
		list=cityService.getCitys(map);
		view.addObject("orglist", list);
		return view;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/tree")
	@ResponseBody
	public List<Organization> getTree(Long id, Long levels, String orgCode,String account) {
		List<Organization> list = new ArrayList();
		list = organizationService.getOrganizationByPid(id);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "list")
	public Pager<User> getPager(
			@RequestParam(value = "currentPage", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			ServletRequest request) {
		//获取当前用户分公司CODE
		User u=SessionContextUtils.getCurrentUser();
		String orgCode = u.getOrgCode();
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		if (!"86".equals(orgCode)&&!"".equals(orgCode) && "".equals(searchParams.get("LIKE_orgCode"))) {
			//限制分公司
			searchParams.put("IN_orgCode", orgCode);
		}
		if ("".equals(searchParams.get("LIKE_orgGrade"))) {
			//限制为LP/SM/AM/DM
			searchParams.put("IN_orgGrade", "LP,SM,AM,DM");
		}
		// searchParams.put("ISNOTNULL_roleType", "roleType");
		Page<User> resources = userService.getAllUsers(searchParams,pageNumber, pageSize, sortType);
		return ObjectUtil.prasePager(resources);
	}
	
	/*@ResponseBody
	@RequestMapping("/list")
	public Pager<User> getSalesUserList(Pager<User> pager,String orgCode,String account,String roleId,String accountstate,String puserId,String userName){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		user.setIsType("1");//map.put("istype", "1");
		if(!StringUtil.nullOrSpace(orgCode)){
			user.setOrgCode(orgCode);//map.put("orgCode", orgCode);
		}
		if(!StringUtil.nullOrSpace(account)){
			user.setAccount(account.trim());//map.put("account", account.trim());
		}
		if(!StringUtil.nullOrSpace(accountstate)){
			user.setAccountstate(accountstate);//map.put("accountstate", accountstate);
		}
		if(!StringUtil.nullOrSpace(roleId)){
			user.setRoleId(Long.valueOf(roleId));//map.put("roleId", roleId);
		}
		if(!StringUtil.nullOrSpace(puserId)){
			user.setPuserId(Long.valueOf(puserId.trim()));//map.put("puserId", puserId.trim());
		}
		if(!StringUtil.nullOrSpace(userName)){
			user.setUserName(userName.trim());//map.put("userName", userName.trim());
		}
		pager.setPageSize(10);
		//pager = userService.getUsersByParams(pager,map);
		pager = userService.getUsersByExample(pager, user);
		//user.setAccount("8601000444");
		//List<User> user2 = userService.getUsersByExample2(user);
		return pager;
	}*/
	
}
