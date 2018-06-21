package com.fairyland.jdp.framework.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.domain.TreeNodeEntity;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.RoleResource;
import com.fairyland.jdp.framework.security.service.ResourceService;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.fairyland.jdp.framework.tree.controller.TreeController;
import com.fairyland.jdp.framework.tree.service.TreeNodeService;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@RestController
@RequestMapping(value = "/admin/sec/permission")
public class PermissionController extends TreeController<Resource> {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	@javax.annotation.Resource
	private TreeNodeService<Resource> treeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String main(Model model) {
		List<Role> roles=roleService.findAll();
		model.addAttribute("roles",roles);
		return "jdp-framework/security/permission/permission";
	}
	
	@RequestMapping(value="search-role",method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> findResource(HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<Role> roles = roleService.getAllRoles(searchParams, 1, Integer.MAX_VALUE, "id_ASC");
		List<Map<String,Object>> roleMapList = new ArrayList<Map<String,Object>>();
		for (Role role : roles) {
			Map<String,Object> roleMap = new HashMap<String,Object>();
			roleMap.put("id", role.getId());
			roleMap.put("name", role.getName());
			roleMapList.add(roleMap);
		}
		return roleMapList;
	}
	
	
	/**
	 * 通过角色id找到资源
	 * @param roleId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="role-resource/{roleId}",method = RequestMethod.GET)
	@ResponseBody
	public Collection<String> findResourceById(@PathVariable("roleId") Long roleId,RedirectAttributes redirectAttributes){
		List<RoleResource> roleResources = resourceService.findRoleResources(roleId);
		Collection<String> resources = Collections2.transform(roleResources, new Function<RoleResource, String>() {
			@Override
			public String apply(RoleResource input) {
				return String.valueOf(input.getResource().getId());
			}
		});
		return resources;
	}
	/**
	 * 通过角色id更新资源
	 * @param roleId
	 * @param resIds
	 * @return
	 */
	@RequestMapping(value="updateResource/{roleId}",method = RequestMethod.POST)
	@ResponseBody
	public String updateResource(@PathVariable("roleId") Long roleId,
			@RequestParam("resourceIds")String resIds){
		List<Long> resourceIds = null;
		if(StringUtils.isNotEmpty(resIds)){
			resourceIds = 
					Lists.transform(Arrays.asList(resIds.split(",")), new Function<String, Long>() {
						@Override
						public Long apply(String resId) {
							return Long.valueOf(resId);
						}
					});
		}else{
			resourceIds = Lists.newArrayList();
		}
		
		resourceService.updateRoleResources(roleId, resourceIds);
		return "";
	}
	

	//begin tree-grid
	@RequestMapping(value="get-tree-grid", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getTreeGrid() throws Exception{
		List<Map<String, Object>> tree = getTree(TreeNodeEntity.ROOT_PARENT_ID);
		//tree.add(getDefaultTreeNode());
		return tree;
	}
	
	public List<Map<String, Object>> getTree(Long parentId) throws Exception {
		List<Resource> items = treeService.findByParentId(Resource.class, parentId);

		List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();

		for (Resource item : items) {
			Map<String, Object> treeNode = BeanUtils.describe(item);

			treeNode.put("urlResources", getResourceMap(item,Resource.RES_TYPE_URL));
			treeNode.put("elementResources", getResourceMap(item,Resource.RES_TYPE_ELEMENT));
			treeNodes.add(treeNode);

		}
		return treeNodes;
	}
	
	private List<Map<String, Object>> getResourceMap(Resource resourceCateReses,String type) throws Exception{

		List<Map<String, Object>> resourceNodes = new ArrayList<Map<String, Object>>();


				if(resourceCateReses.getResType().equals(type)){
					Map<String, Object> resourceNode = BeanUtils.describe(resourceCateReses);
					resourceNodes.add(resourceNode);
				}
		
		
		return resourceNodes;
	}

	private List<Map<String, Object>> getResourceMap(List<Resource> resources,String type) throws Exception{

		List<Map<String, Object>> resourceNodes = new ArrayList<Map<String, Object>>();
		for (Resource resource : resources) {
			if(resource.getResType().equals(type)){
				Map<String, Object> resourceNode = BeanUtils.describe(resource);
				resourceNodes.add(resourceNode);
			}
		}
		return resourceNodes;
	}


	
	//end tree-grid
}
