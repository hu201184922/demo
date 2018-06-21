package org.apache.shiro.web.filter.mgt;
/**
 * 此处重写源码，为了支持多过虑器匹配
 * 
 * 如：
 * /admin/**=user
 *  /admin/party/org=perms[org:view]
 *  
 *  默认情况下，Shrio只按顺序匹配第一个过虑器执行。现修改为执行所有匹配的过虑器
 */

