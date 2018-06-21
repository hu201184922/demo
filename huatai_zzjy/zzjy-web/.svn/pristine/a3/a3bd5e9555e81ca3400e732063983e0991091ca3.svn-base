package com.fairyland.jdp.framework.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.RoleResource;
import com.fairyland.jdp.framework.security.service.ResourceService;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.fairyland.jdp.framework.util.DictionaryUtils;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.ObjectUtil;

@Controller
@RequestMapping(value = "/admin/sec/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "index")
	public ModelAndView index(
			@RequestParam(value = "currentPage", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			ServletRequest request) {
		ModelAndView view = new ModelAndView();
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
//		searchParams.put("ISNOTNULL_type", "type");
		Page<Role> role = roleService.getAllRoles(searchParams, pageNumber,
				pageSize, sortType);
		view.addObject("pager", ObjectUtil.prasePager(role));
		view.addObject("roleType", DictionaryUtils
				.getDictItemsWithOptionSELECT("ROLE_TYPE"));
//		view.addObject("roleType", DictionaryUtils.getDictItems("crm.system.roletype"));
		view.setViewName("/admin/role/index");
		return view;
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public Pager<Role> list(@RequestParam(value = "currentPage", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			ServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
//		searchParams.put("ISNOTNULL_type", "type");
		Page<Role> role = roleService.getAllRoles(searchParams, pageNumber,
				pageSize, sortType);
		return ObjectUtil.prasePager(role);
	}
	@RequestMapping(value = "editauthority")
	public ModelAndView authority(Long id) {
		ModelAndView view = new ModelAndView();
		Role role = roleService.readRole(id);
		view.addObject("role", role);
		view.addObject("treeResources",
				resourceService.getResourceByGroup(null));
		view.setViewName("/admin/role/authority");
		return view;
	}

	@ResponseBody
	@RequestMapping("saveauthority")
	public Boolean saveAuthority(Long[] resourceId, Long roleId) {
		List<Long> resourceIds = Arrays.asList(resourceId);
		resourceService.updateRoleResources(roleId, resourceIds);
		return true;
	}

	@ResponseBody
	@RequestMapping("create")
	public void create(Role role) {
		roleService.createRole(role);
	}

	@RequestMapping(value = "createPage/{catagoryid}", method = RequestMethod.GET)
	public String createPage(Model model) {
		return "jdp-framework/security/role/role-create";
	}

	@RequestMapping(value = "createPage/", method = RequestMethod.POST)
	public String createPage(Role role,
			@RequestParam("resources_id") String resIds) {
		roleService.createRole(role);
		return "redirect:/admin/sec/role/createPage/";
	}

	@ResponseBody
	@RequestMapping(value = "delete")
	public void delete(Long id) {
		roleService.deleteRole(id);
	}

	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Role update(Role role) {
		roleService.updateRole(role);
		return role;
	}

	@ModelAttribute
	public void getRole(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id.longValue() != -1) {
			model.addAttribute("role", roleService.readRole(id));
		}
	}

	@RequestMapping(value = "update/{roleId}", method = RequestMethod.GET)
	public String updatePage(Model model, @PathVariable("roleId") Long id) {
		Role role = roleService.readRole(id);
		model.addAttribute("role", role);
		model.addAttribute("resIds", getResource(role.getRoleResources()));
		return "jdp-framework/security/role/role-edit";
	}

	private String getResource(Set<RoleResource> resources) {
		if (resources == null) {
			return "";
		}
		List<Long> ids = new ArrayList<Long>();
		for (RoleResource roleResource : resources) {
			ids.add(roleResource.getResource().getId());
		}
		return StringUtils.join(ids, ",");
	}

	@RequestMapping(value = "updatePage", method = RequestMethod.POST)
	public String updatePage(@ModelAttribute("role") Role role,
			@RequestParam("resources_id") String resIds) {
		roleService.updateRole(role, resIds);
		return "redirect:/admin/sec/roleCategory";

	}
	 
	@RequestMapping("addHref")
	public ModelAndView addHref(Long id){
		ModelAndView mav = new ModelAndView();
		Role role = roleService.readRole(id);
		mav.addObject("role", role);
		mav.setViewName("/admin/role/addHref");
		return mav;
	}

}
