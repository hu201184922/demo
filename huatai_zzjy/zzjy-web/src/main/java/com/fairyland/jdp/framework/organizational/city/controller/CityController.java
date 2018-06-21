package com.fairyland.jdp.framework.organizational.city.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fairyland.jdp.framework.organizational.city.domain.City;
import com.fairyland.jdp.framework.organizational.city.service.CityService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.User;

@Controller
@RequestMapping("admin/city")
public class CityController {
	
	@Autowired
	private CityService cityService;

	/**
	 * 获取所有城市信息列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "getCity")
	public List<City> getCitys(ServletRequest request) {
		User u=SessionContextUtils.getCurrentUser();
		String orgCode = u.getOrgCode();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("lv", 0);
		if (!"86".equals(orgCode)&&!"".equals(orgCode)) {
			map.put("orgCode", orgCode);
		}
		List<City> list=cityService.getCitys(map);
		return list;
	}
	
	
	/**
	 * 获取所有城市信息列表
	 * @param roleCode : CAO | GM | AH
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "getCitysByRole")
	public List<City> getCitysByRole(String roleCode, ServletRequest request) {
		//获取当前用户分公司CODE
		List<City> list= cityService.getCitysByRoleCode(roleCode);
		return list;
	}
	
	
	


}
