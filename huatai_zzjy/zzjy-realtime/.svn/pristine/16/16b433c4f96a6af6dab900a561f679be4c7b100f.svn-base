package com.fairyland.jdp.framework.security.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.constant.Constants;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.service.RoleService;

@RestController
@RequestMapping(value="/admin/sec/roleCategory")
public class RoleCategoryController {

	@Autowired
	private RoleService roleService;
	@RequestMapping(method = RequestMethod.GET)
	private String main(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
		    @RequestParam(value = "page.size", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
		    @RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
		    Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<Role> roles = roleService.getAllRoles(searchParams, pageNumber, pageSize, sortType);
		model.addAttribute("roles", roles);
		model.addAttribute("sortType", sortType);
		model.addAttribute("searchParams", Servlets
			.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "/jdp-framework/security/role/roleCategory";
	}






}
