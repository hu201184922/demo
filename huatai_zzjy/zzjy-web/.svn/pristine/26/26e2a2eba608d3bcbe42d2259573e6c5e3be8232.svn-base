package com.fairyland.jdp.framework.menu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.menu.domain.MenuItem;
import com.fairyland.jdp.framework.menu.service.MenuItemService;
import com.fairyland.jdp.framework.rest.RestException;

@Controller
@RequestMapping(value = "/mobile/v1/menu")
public class MenuRestController {
	
	private static Logger logger = LoggerFactory.getLogger(MenuRestController.class);
	
	@Autowired
	private  MenuItemService menuItemService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public MenuItem get(Long id) {
		MenuItem item =menuItemService.readMenuItem(id);
		if (item == null) {
			String message = "菜单不存在(id:" + id + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		return item;
	}
	
	
}
