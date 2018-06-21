package com.fairyland.jdp.framework.menu.controller;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.constant.Constants;
import com.fairyland.jdp.framework.menu.domain.MenuItem;
import com.fairyland.jdp.framework.menu.service.MenuItemService;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.tree.controller.TreeController;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.ObjectUtil;

@Controller
@RequestMapping(value = "/admin/menu")
public class MenuController extends TreeController<MenuItem> {

	@Autowired
	private MenuItemService menuItemService;

	@RequestMapping(method = RequestMethod.GET)
	public String main() throws Exception {
		return "/admin/menu/index";
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "list")
	public Pager<MenuItem> getPager(
			@RequestParam(value = "currentPage", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<MenuItem> menu = menuItemService.getAllMenu(searchParams,
				pageNumber, pageSize, sortType);
		return ObjectUtil.prasePager(menu);
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public MenuItem create(@Valid MenuItem menuItem) {
		menuItemService.createMenuItem(menuItem);
		return menuItem;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public MenuItem update(@Valid @ModelAttribute("menu") MenuItem menuItem,
			RedirectAttributes redirectAttributes) {
		menuItem.buildPermString();
		menuItemService.updateMenuItem(menuItem);
		return menuItem;
	}

	@RequestMapping(value = "delete",method=RequestMethod.POST)
	@ResponseBody
	public void delete(Long id) {
		menuItemService.deleteMenuItem(id);
	}

	@RequestMapping(value = "move/{id}")
	@ResponseBody
	public void moveTo(@PathVariable("id") Long id,
			@RequestParam("parentId") Long parentId) {
		MenuItem menu = menuItemService.readMenuItem(id);
		if (parentId == null) {
			menu.setParent(null);
		} else {
			menu.setParent(menuItemService.readMenuItem(parentId));
		}
		menuItemService.updateMenuItem(menu);
	}
}
