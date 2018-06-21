package com.fairyland.jdp.framework.security.filterchain.service;

import org.apache.shiro.config.Ini.Section;


/**
 * shiro 过滤器链服务类
 * @author XiongMiao
 *
 */
public interface FilterChainDefinitionService {
  
    /** 加载框架权限资源配置 */  
    public Section loadFilterChains();  
    
}
