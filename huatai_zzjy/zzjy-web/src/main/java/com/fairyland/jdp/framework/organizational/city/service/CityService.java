/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.organizational.city.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.framework.organizational.city.domain.City;
import com.fairyland.jdp.framework.security.domain.User;

/**
 * @author jiangbingbin
 */
public interface CityService {

	/**
	 * 获取城市
	 * @param lv : null/0 全部 1 全国 2 城市
	 * @return
	 */
	public List<City> getCitys(Map<String,Object> map);
	
	/**
	 * 根据内勤CODE获取城市
	 * @param roleCode : CAO / GM / AH
	 * @return
	 */
	public List<City> getCitysByRoleCode(String roleCode);
	
	/**
	 * 根据分公司code查找分公司名称
	 * @param orgCode 代理人的工号
	 * @return String 分公司名称
	 */
	public String getCityNameByCode(@Param("orgCode") String orgCode);
}