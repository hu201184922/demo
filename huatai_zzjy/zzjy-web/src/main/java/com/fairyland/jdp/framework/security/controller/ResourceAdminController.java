package com.fairyland.jdp.framework.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.constant.Constants;
import com.fairyland.jdp.framework.scanner.service.ScannerHandler;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.filterchain.service.ShiroFilterChainDefinitionManager;
import com.fairyland.jdp.framework.security.service.ResourceService;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.fairyland.jdp.framework.tree.controller.TreeController;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/admin/sec/resource")
public class ResourceAdminController extends TreeController<Resource> {
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ScannerHandler scannerHandler;

	@Autowired
	private ShiroFilterChainDefinitionManager shiroFilterChainDefinitionManager;

	@Autowired
	private RoleService roleService;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("id_ASC", "自动");
		sortTypes.put("name_ASC", "名称");
		sortTypes.put("resType_ASC", "类型");
		sortTypes.put("resString_ASC", "资源字符串");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		searchParams.put("EQ_resType", "U");
		Page<Resource> resources = resourceService.getAllResource(searchParams,
				pageNumber, pageSize, sortType);
		model.addAttribute("pager", ObjectUtil.prasePager(resources));
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "/admin/resource/index";
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "list")
	public Pager<Resource> getPager(
			@RequestParam(value = "currentPage", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		searchParams.put("EQ_resType", "U");
		Page<Resource> resources = resourceService.getAllResource(searchParams,
				pageNumber, pageSize, sortType);
		return ObjectUtil.prasePager(resources);
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		Resource resource = new Resource();
		model.addAttribute("action", "create");
		model.addAttribute("resource", resource);
		return "jdp-framework/security/resource/resource-edit";
	}

	@ResponseBody
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public Resource createResource(@Valid Resource res) {
		if (res.getPid() != null) {
			res.setParent(resourceService.readResource(res.getPid()));
		}
		resourceService.createResource(res);
		return res;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Resource resource = resourceService.readResource(id);

		model.addAttribute("action", "update");
		model.addAttribute("resource", resource);
		return "jdp-framework/security/resource/resource-edit";
	}

	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Resource updateResource(@Valid @ModelAttribute("res") Resource res,
			String categoryIds, RedirectAttributes redirectAttributes) {
		resourceService.updateResource(res);
		return res;
	}

	@RequestMapping("/urlbox")
	public ModelAndView urlbox(Long id) {
		ModelAndView view = new ModelAndView();
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_resType", "U");
		Page<Resource> resources = resourceService.getAllResource(searchParams,
				1, 10, "id_ASC");
		view.addObject("page", ObjectUtil.prasePager(resources));
		view.setViewName("/admin/resource/urlbox");
		return view;
	}

	@RequestMapping("/urlbox/list")
	@ResponseBody
	public Pager<Resource> listByBox(
			Pager<Resource> pagination,
			Resource resource,
			@RequestParam(value = "currentPage", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			boolean unreferenced, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		searchParams.put("EQ_resType", "U");
		Page<Resource> resources = resourceService.getAllResource(searchParams,
				pageNumber, pageSize, "id_ASC");
		return ObjectUtil.prasePager(resources);
	}

	@RequestMapping("/saveUrls")
	@ResponseBody
	public Boolean saveUrls(Long[] ids,Long groupId){
			for (Long id : ids) {
				resourceService.createRelation(id, groupId);
			}
		return true;
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Boolean deleteAllResource(Long id){
		return resourceService.deleteAllResource(id);
	}
	/**
	 * 保存并关闭请求
	 * 
	 * @param res
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save-close", method = RequestMethod.POST)
	@ResponseBody
	public String saveAndClose(@Valid @ModelAttribute("res") Resource res,
			String categoryIds, RedirectAttributes redirectAttributes) {
		resourceService.updateResource(res, categoryIds);
		return "success";
	}

	@ModelAttribute
	public void getResource(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id.longValue() != -1) {
			model.addAttribute("res", resourceService.readResource(id));
		}
	}



	@RequestMapping(value = "dis-en-abled/{ids}", method = RequestMethod.GET)
	public String disabledOrNot(@PathVariable("ids") String ids,
			RedirectAttributes redirectAttributes) {
		String[] resourceIds = ids.split(",");
		List<Resource> resources = new ArrayList<Resource>();
		for (String resourceId : resourceIds) {
			Resource resource = resourceService.readResource(Long
					.parseLong(resourceId));
			resource.setEnabled(!BooleanUtils.toBoolean(resource.getEnabled()));
			resources.add(resource);
		}
		resourceService.updateResources(resources);
		redirectAttributes.addFlashAttribute("message", "禁用/启用成功");
		return "redirect:/admin/sec/resource";
	}

	@RequestMapping(value = "check", method = RequestMethod.POST)
	@ResponseBody
	public String check(String name, String initalname) {
		if (initalname != null && initalname.equals(name)) {
			return "true";
		}
		Resource resource = resourceService.getResource(name);
		if (resource == null) {
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "checkRes", method = RequestMethod.POST)
	@ResponseBody
	public String checkRes(String resString, String initalRestring) {
		if (initalRestring != null && initalRestring.equals(resString)) {
			return "true";
		}
		Resource resource = resourceService.getResourceByResString(resString);
		if (resource == null) {
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "checkPer", method = RequestMethod.POST)
	@ResponseBody
	public String checkPer(String perString, String initalPerstring) {
		if (initalPerstring != null && initalPerstring.equals(perString)) {
			return "true";
		}
		Resource resource = resourceService.getResourceByPerString(perString);
		if (resource == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 扫描资源
	 */
	@ResponseBody
	@RequestMapping(value = "scanner")
	public Boolean scannerResource() {
		scannerHandler.scanner();
		return true;
	}

	/**
	 * 刷新过滤器
	 */
	@RequestMapping(value = "refresh/filters")
	public String refreshFilterChains() {
		shiroFilterChainDefinitionManager.updateFilterChains();
		return "redirect:/admin/sec/resource";
	}

	@RequestMapping("/tree")
	@ResponseBody
	public List<Resource> tree(Long id) {
		List<Resource> res = resourceService.getResourceByGroup(id);
		
		return res;
	}

}
