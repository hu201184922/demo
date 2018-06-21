package com.fairyland.jdp.framework.security.filterchain.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fairyland.jdp.framework.security.dao.ResourceDao;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

@Component("bizfilterChainDefinitionService")
@AutoConfigureAfter(DefaultFilterChainDefinitionService.class)
public class BizFilterChainDefinitionService implements FilterChainDefinitionService{

	/**
	 * 默认roles字符串
	 */
	public static final String ROLE_STRING = "roles[\"{0}\"]";

	public static final String PERMISSION_STRING = "perms[\"{0}\"]";
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private RoleService roleService;

	@Value("${spring.fairyland.shiro.biz.filterChainDefinitions}")
	protected String filterChainDefinitions;
	
	@SuppressWarnings("unused")
	private String appendFilterChainDefinitions;

	public void setAppendFilterChainDefinitions(String appendFilterChainDefinitions) {
		this.appendFilterChainDefinitions = appendFilterChainDefinitions;
	}

	/**
	 * 通过filterChainDefinitions对默认的url过滤定义
	 * 
	 * @param filterChainDefinitions
	 *            默认的url过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	/**
	 * 加载权限资源
	 * @return
	 * @throws Exception
	 */
	public Section loadFilterChains() {
		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		// section就是filterChainDefinitionMap
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		// 装载其他类型的资源
		Map<String, String> filterChainMap = loadDynamicFilterChains();  
        if (!CollectionUtils.isEmpty(filterChainMap)) {  
            section.putAll(filterChainMap);  
        } 
		return section;
	}
	
    /** 加载动态权限资源配置 */ 
	public Map<String, String> loadDynamicFilterChains() {
		List<Resource> resources = (List<Resource>) resourceDao
				.findEnableResourcesByResType(Resource.RES_TYPE_URL);
		Map<String, String> filterChains = new HashMap<String, String>();
		// 装载URL类型的资源
		for (Resource resource : resources) {
			String resString = resource.getResString();
			String permString = resource.getPerString();
			if(resString.indexOf("login")>=0 
					|| resString.indexOf("changepsw/findPsw")>=0 
					|| resString.indexOf("changepsw/confrimFindPsw")>=0
					|| resString.indexOf("admin/file")>=0)
				continue;
			if (StringUtils.isNotEmpty(resString)) {
//				log.debug(resString+"============="+permString);
				if (StringUtils.isNotEmpty(permString)) {
					//必须以“/”开头
					for (String res : getResStrings(resString)) {
						filterChains.put(res,
								MessageFormat.format(PERMISSION_STRING, permString));
					}
				} else {
					List<Role> roles = roleService.matchResourceRoles(resource
							.getId());
					String roleString = Joiner.on(",").join(
							Sets.newHashSet(
									Collections2.transform(roles,
											new Function<Role, String>() {
												@Override
												public String apply(Role input) {
													return input.getCode();
												}
											})).iterator());
					for (String res : getResStrings(resString)) {
						filterChains.put(res,
								MessageFormat.format(ROLE_STRING, roleString));
					}
					
				}
			}
		}
		return filterChains;
	}
	
	/**
	 * 获取一个url资源的url列表
	 * 一般会产生两个url
	 * @param resString
	 * @return
	 */
	private List<String> getResStrings(String resString){
		List<String> resList=new ArrayList<String>();
		if(resString.endsWith("/")){
			String nonTrail = resString.replaceAll("/+", "/");
			nonTrail = nonTrail.substring(0, nonTrail.length()-1);
			if(StringUtils.isEmpty(nonTrail)){
				return resList;
			}
			resList.add("/"+nonTrail);
		}else{
			resList.add("/"+resString+"/");
		}
		
		resList.add("/"+resString);
		return resList;
	}
}