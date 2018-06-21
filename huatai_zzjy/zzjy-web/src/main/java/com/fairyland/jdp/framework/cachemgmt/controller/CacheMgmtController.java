package com.fairyland.jdp.framework.cachemgmt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.cachemgmt.service.CacheMgmtService;
import com.fairyland.jdp.framework.cachemgmt.view.CacheModel;

@Controller
@RequestMapping(value = "/admin/cachemgmt")
public class CacheMgmtController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private CacheMgmtService cacheMgmtService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		String cacheName = MapUtils.getString(searchParams, "cacheName");
		String itemKey = MapUtils.getString(searchParams, "cacheKey");
		String searchKeyWord = MapUtils
				.getString(searchParams, "KeyWord");
		log.debug(cacheName+"..."+itemKey+"..."+searchKeyWord);
		List<CacheModel> cacheModels = cacheMgmtService.getCaches(cacheName,
				itemKey, searchKeyWord);

		model.addAttribute("cacheModels", cacheModels);
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "jdp-framework/cachemgmt/cachemgmt-list";
	}
	
	@RequestMapping(value="delete")
	public String clearAll(){
		cacheMgmtService.clearAll();
		return "jdp-framework/cachemgmt/cachemgmt-list";
	}
	
	@RequestMapping(value="deleteShiro")
	public String clearShiroAll(){
		cacheMgmtService.clearShiroAll();
		return "redirect:/admin/cachemgmt";
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		if(StringUtils.isEmpty(id)){
			cacheMgmtService.clearAll();
		}else{
			String[] cacheNameAndKeys = id.split(",");
			for (String nameAndKey : cacheNameAndKeys) {
				String[] str = nameAndKey.split("_");
				String cacheName=str[0];
				String key=str[1];
				cacheMgmtService.clearCache(cacheName,key);
			}
		}
		redirectAttributes.addFlashAttribute("message", "清除成功");
//		return "jdp-framework/cachemgmt/cachemgmt-list";
		return "redirect:/admin/cachemgmt";
	}
}
