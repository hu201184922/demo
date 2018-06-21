/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.organizational.city.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.framework.organizational.city.domain.City;
import com.fairyland.jdp.framework.organizational.city.mapper.CityMapper;
import com.fairyland.jdp.framework.security.domain.User;

/**
 * @author jiangbingbin
 */	
@Service(value = "cityService")
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityMapper cityMapper;

	/**
	 * 获取城市
	 * @param lv : null/0 全部 1 全国 2 城市
	 * @return
	 */
	public List<City> getCitys(Map<String,Object> map){
		return cityMapper.getCitys(map);
	}
	
	/**
	 * 根据内勤CODE获取城市
	 * @param roleCode : CAO / GM / AH
	 * @return
	 */
	public List<City> getCitysByRoleCode(String roleCode){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("roleCode", roleCode);
		return cityMapper.getCitysByRoleCode(map);
	}

	@Override
	public String getCityNameByCode(@Param("orgCode") String orgCode){
		return cityMapper.getCityNameByCode(orgCode);
	}

}
