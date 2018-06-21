package com.fairyland.jdp.framework.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.service.ResourceService;
import com.fairyland.jdp.framework.tree.controller.TreeController;

@Controller
@RequestMapping(value="/admin/sec/res")
public class ResourceController extends TreeController<Resource> {
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(method = RequestMethod.GET)
	private String main(Model model) {
		return "/jdp-framework/security/res/resource";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Resource create(Resource resource) {
		resource.setName("新菜单");
		resourceService.createResource(resource);
	     return resource;
	}
	
	@RequestMapping(value = "create/{menuId}",method = RequestMethod.GET)
	public String createByMenuId(@PathVariable("menuId") Long menuId) {
		resourceService.createResourceByMenuId(menuId);
		return "redirect:/admin/sec/resource";
	}

	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		resourceService.deleteResource(id);
	}

	@RequestMapping(value = "move/{id}")
	@ResponseBody
	public void moveTo(@PathVariable("id") Long id
			,@RequestParam("parentId") Long parentId) {
		Resource resource = 
				resourceService.readResource(id);
		if(parentId == null){
			resource.setParent(null);
		}else {
			resource.setParent(resourceService.readResource(parentId));
		}
		
		resourceService.updateResource(resource);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("resource")Resource resource,
			RedirectAttributes redirectAttributes) {
		if(resource.getPid()==null){
			resource.setParent(null);
		}
		resourceService.updateResource(resource);
		return "redirect:/admin/sec/res";
	}
	@ModelAttribute
	public void getResource(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id.longValue() != -1) {
			model.addAttribute("resource",resourceService.readResource(id));
		}
	}
}
