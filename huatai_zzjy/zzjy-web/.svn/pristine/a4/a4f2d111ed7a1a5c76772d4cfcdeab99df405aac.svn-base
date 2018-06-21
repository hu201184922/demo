package com.fairyland.jdp.framework.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

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

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.constant.Constants;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.framework.util.DictionaryUtils;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/admin/sec/adminRole")
public class RoleAdminController  {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
	sortTypes.put("id_ASC", "自动");
	sortTypes.put("code_ASC", "角色标志");
	sortTypes.put("name_ASC", "名称");
    }
	
    @RequestMapping(value = "index")
   	public ModelAndView index(
   			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
   		    @RequestParam(value = "page.size", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
   		    @RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
   		    Model model, ServletRequest request) {
       	ModelAndView view = new ModelAndView();
   		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
   				request, "search_");
   		searchParams.put("ISNULL_type", "type");
   		Page<Role> roles = roleService.getAllRoles(searchParams, pageNumber, pageSize, sortType);
   		view.addObject("pager", ObjectUtil.prasePager(roles));
//   		view.addObject("roleType", DictionaryUtils
//   				.getDictItemsWithOptionSELECT("crm.system.roletype"));
   		view.setViewName("/admin/roleAdmin/index");
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
   		searchParams.put("ISNULL_type", "type");
   		Page<Role> role = roleService.getAllRoles(searchParams, pageNumber,
   				pageSize, sortType);
   		return ObjectUtil.prasePager(role);
   	}
	
	@RequestMapping(value="createPage",method = RequestMethod.GET)
	public String createPage(){
		return "jdp-framework/security/role/role-create";
	}
	@RequestMapping( value="create",method = RequestMethod.POST)
	public String create(@Valid Role role,RedirectAttributes redirectAttributes){
		roleService.createRole(role);
		redirectAttributes.addFlashAttribute("message", "创建成功");
		return "redirect:/admin/sec/role";
	}
	@RequestMapping(value="updatePage/{roleId}",method = RequestMethod.GET)
	public String updatePage(@PathVariable("roleId") Long roleId,Model model){
		Role role=roleService.readRole(roleId);
		model.addAttribute("role", role);
		return "jdp-framework/security/role/role-edit";
	}
	@ModelAttribute
	public void getRole(@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model){
		if (id.longValue() != -1) {
			model.addAttribute("role", roleService.readRole(id));
		}
	}
	@RequestMapping(value="delete/{roleIds}",method = RequestMethod.GET)
	public String delete(@PathVariable("roleIds") String roleIds,RedirectAttributes redirectAttributes){
		String[] rolesIds=roleIds.split(",");
		for(String role:rolesIds){
			roleService.deleteRole(Long.parseLong(role));
		}
		redirectAttributes.addFlashAttribute("message", "删除成功");
		/*return "redirect:/admin/sec/role";*/
		return "redirect:/admin/sec/roleCategory";
	}
	
	@RequestMapping(value="checkCode",method=RequestMethod.POST)
	@ResponseBody
	public String checkCode(String code,String initialcode){
		if(code.equals(initialcode)){
			return "true";
		}
		
		Role Role=roleService.getRoleByCode(code);
		if(Role==null){
			return "true";
		}
		return "false";
	}
	
	    @RequestMapping(value="check",method=RequestMethod.POST)
		@ResponseBody
		public String check(String name,String initalname){
	    	if(name.equals(initalname)){
	    		return "true";
	    	}
			Role Role=roleService.getRole(name);
			if(Role==null){
				return "true";
			}
			return "false";
		}
	    @SuppressWarnings("unchecked")
	    @RequestMapping(value="getRoleArray",produces = MediaTypes.JSON_UTF_8)
		@ResponseBody
		 public List<Map<String,Object>> getRoleArray(){
	    	List<Role> roles=roleService.getRoleByRoleCategory();
	    	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	    	for(Role role:roles){
	    		Map<String,Object> map=new HashMap<String,Object>();
	    		map.put("id",role.getId());
	    		map.put("name",role.getName());
	    		map.put("code",role.getCode());
	    		if(role.getDescript()==null)
	    			map.put("descript","");
	    		else
	    			map.put("descript",role.getDescript());
	    		list.add(map);
	    	}
	    	return list;
	    	
	    }
	    
}

