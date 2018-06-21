package com.ehuatai.interceptors;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.ehuatai.biz.domain.Nodes;
import com.ehuatai.biz.domain.Role;
import com.ehuatai.biz.domain.User;
import com.ehuatai.commonTest.entity.AccPageInfo;
import com.ehuatai.commonTest.entity.UserBehInfo;
import com.ehuatai.commonTest.service.InterceptorService;
import com.ehuatai.ret.RestResult;
import com.ehuatai.util.DateUtils;
import com.ehuatai.util.RequestHeaderUtil;
import com.fairyland.jdp.framework.util.Base64Util;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private InterceptorService interceptorService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static ThreadLocal<Long> time = new ThreadLocal<Long>();

	private static Map<String, Object> moduName = new HashMap<String, Object>();

	private static Map<String, Object> getModuName() {
		moduName.put("/api/analysis", "分析管理");
		moduName.put("/api/browse", "浏览清单");
		moduName.put("/api/block", "板块");
		moduName.put("/api/tool", "工具");
		moduName.put("/app/analysis", "分析管理");
		moduName.put("/app/browse", "浏览清单");
		moduName.put("/app/block", "板块");
		moduName.put("/app/tool", "工具");
		moduName.put("/index", "首页");
		moduName.put("/api/roles", "角色");
		moduName.put("/api/report/page", "导出页面");
		return moduName;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long currTime = System.currentTimeMillis();
		time.set(currTime);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse arg1, Object handler, Exception arg3)
			throws Exception {
		try {
			RestResult attribute = null;
			String data = null;
			User user = null;
			User users = RequestHeaderUtil.getUser(request);
			long min = 10 * 1000 * 60;
			if (request.getRequestURI().equals("/api/login")) {
				attribute = (RestResult) request.getAttribute("login");
				data = Base64Util.decode((String) attribute.data);
				user = JSON.parseObject(data, User.class);
			}
			String userName = users.getUsername() == null ? user.getUsername() : users.getUsername();
			String behaviorid = users.getToken() == null ? user.getToken().substring(0, 32)
					: users.getToken().substring(0, 32);
			// 判断是否登录或者正在登录页面
			if (users.getToken() != null || request.getRequestURI().equals("/api/login")) {
				Date date = new Date();
				// 如果是正在登录
				if (request.getAttribute("login") != null) {
					UserBehInfo userBehInfo = new UserBehInfo();
					AccPageInfo accPageInfo = new AccPageInfo();
					// 使用板块报表
					if (request.getRequestURI().equals("/api/block/target")) {
						System.out.println("==============/api/block/target=================");
						String obj = request.getAttribute("subject").toString();
						accPageInfo.setSubCode(obj);
						accPageInfo.setType("2");
					}
					// 使用浏览清单报表
					if (request.getRequestURI().equals("/api/browse/list/custom/info")
							|| request.getRequestURI().equals("/app/browse/list/custom/info")) {
						String obj = request.getAttribute("subject").toString();
						System.out.println("----------------" + obj + "-------------");
						accPageInfo.setSubCode(obj);
						accPageInfo.setType("2");
					}
					// 使用分析模块
					if (request.getRequestURI().equals("/api/analysis/common")
							|| request.getRequestURI().equals("/app/analysis/common")) {
						String obj = request.getAttribute("subject").toString();
						System.out.println("----------------" + obj + "-------------");
						accPageInfo.setSubCode(obj);
						accPageInfo.setType("1");
					}

					// 1.登录成功,开始记录参数
					if (attribute.isSuccess()) {
						if (request.getAttribute("version") != null) {
							moduName.put("version", request.getAttribute("version") + "");
						}
						if (handler instanceof HandlerMethod) {
							UserBehInfo userBehList = interceptorService.findUserDataInfo(user.getUsername(),
									users == null ? (user == null ? null : user.getEqp().toUpperCase())
											: users.getEqp().toUpperCase(),
									null);
							long currentTimeMillis = System.currentTimeMillis();
							String resTime = (currentTimeMillis - time.get()) + "";
							// 用户首次登录时插入的数据
							if ((userBehList == null)) {
								try {
									logger.debug("===============用户首次登录时==================start");
									accPageInfo.setAccessname(getModuName().get("/index").toString());
									accPageInfo.setAccessurl("/" + users.getEqp() + "/index");
									accPageInfo.setAccessTime(getDate(System.currentTimeMillis()));
									accPageInfo.setBehaviorid(behaviorid);
									accPageInfo.setUserId(user.getUsername());
									accPageInfo.setDateCode(DateUtils.formatDate(new Date()));
									accPageInfo.setFmName("首页");
									accPageInfo.setIsOut(0);
									accPageInfo.setOutTime(getDate(System.currentTimeMillis(), min));
									accPageInfo.setResTime(resTime);
									accPageInfo.setType("0");
									userBehInfo.setTerminalType(
											users == null ? (user == null ? null : user.getEqp().toUpperCase())
													: users.getEqp().toUpperCase());
									int len = Integer.MAX_VALUE;
									int tmp = 0;
									if (user.getRoles() != null) {
										for (Role r : user.getRoles()) {
											tmp = r.getRoleOrg().length();
											if (tmp <= len) {
												len = tmp;
											}
										}
										for (Role r1 : user.getRoles()) {
											if (r1.getRoleOrg().length() == len) {
												userBehInfo.setLevel(r1.getRoleOrg());
												break;
											}
										}
									}
									userBehInfo.setBehaviorid(behaviorid);
									userBehInfo.setUserId(user.getUsername());
									userBehInfo.setRegeditDate(date);
									userBehInfo.setUserName(user.getNick());
									userBehInfo.setDateCode(DateUtils.formatDate(new Date()));
									userBehInfo.setErrorInfo("正常");
									userBehInfo.setLoginTimeNow(date);
									userBehInfo.setLoginTimeFirst(date);
									userBehInfo.setLoginTimeLast(date);
									userBehInfo.setOutTime(getDate(System.currentTimeMillis(), min));
									userBehInfo.setIsRecent("1");
									userBehInfo.setIsExc("0");
									userBehInfo.setIsOnline("1");
									userBehInfo.setVersion(users.getVersion() == null
											? moduName.get("version").toString() : users.getVersion());

									len = Integer.MAX_VALUE;
									tmp = 0;
									/*
									 * if (user.getRoles() != null) { for (Role
									 * r : user.getRoles()) { tmp =
									 * r.getRoleOrg().length(); if (tmp <= len)
									 * { len = tmp; } } for (Role r1 :
									 * user.getRoles()) { if
									 * (r1.getRoleOrg().length() == len) {
									 * 
									 * userBehInfo.setRoleCode(r1.getRole());
									 * break; } } }
									 */
									if (users.getRole() != null) {
										userBehInfo.setRoleCode(users.getRole());
									} else {
										userBehInfo.setRoleCode("");
									}

									// userBehInfo.setRoleCode(obj.toString().substring(0,
									// obj.toString().indexOf(",")));
									logger.debug("userBehInfo=" + userBehInfo.toString());
									logger.debug("accPageInfo=" + accPageInfo.toString());
									setUserData(userBehInfo, "insert");
									setAccData(accPageInfo, "insert");
									logger.debug("====================响应时间:" + resTime + "ms===========");
									logger.debug("====================用户首次登录时==================end");
								} catch (Exception e) {
									e.printStackTrace();
									logger.debug("====================首次登录时,获取用户行为数据错误===========");
								}
							} else if ((userBehList != null) && !behaviorid.equals(userBehList.getBehaviorid())) {
								try {
									logger.debug("====================用户多次登录时==================start");
									if (userBehList.getIsRecent().equals("1")) {
										accPageInfo = new AccPageInfo();
										accPageInfo.setBehaviorid(userBehList.getBehaviorid());
										accPageInfo.setIsOut(1);
										accPageInfo.setOutTime(userBehList.getOutTime());
										this.interceptorService.updateAccDataById(accPageInfo);
										UserBehInfo online = new UserBehInfo();
										online.setIsOnline("0");
										online.setUserId(userName);
										online.setIsRecent("0");
										online.setTerminalType(users.getEqp() == null
												? (user == null ? null : user.getEqp().toUpperCase())
												: users.getEqp().toUpperCase());
										interceptorService.updateOtherUserData(online, 1);
									}
									accPageInfo.setAccessname(getModuName().get("/index").toString());
									accPageInfo.setAccessurl("/" + users.getEqp() + "/index");
									accPageInfo.setAccessTime(getDate(System.currentTimeMillis()));
									accPageInfo.setBehaviorid(behaviorid);
									accPageInfo.setUserId(user.getUsername());
									accPageInfo.setDateCode(DateUtils.formatDate(date));
									userBehInfo.setLoginTimeLast(userBehList.getLoginTimeNow());
									accPageInfo.setFmName("首页");
									accPageInfo.setIsOut(0);
									accPageInfo.setOutTime(getDate(System.currentTimeMillis(), min));
									accPageInfo.setResTime(resTime);
									accPageInfo.setType("0");
									userBehInfo.setTerminalType(
											users == null ? (user == null ? null : user.getEqp().toUpperCase())
													: users.getEqp().toUpperCase());
									userBehInfo.setBehaviorid(behaviorid);
									int len = Integer.MAX_VALUE;
									int tmp = 0;
									/*
									 * if (user.getRoles() != null) { for (Role
									 * r : user.getRoles()) { tmp =
									 * r.getRoleOrg().length(); if (tmp <= len)
									 * { len = tmp; } } for (Role r1 :
									 * user.getRoles()) { if
									 * (r1.getRoleOrg().length() == len) {
									 * userBehInfo.setLevel(r1.getRoleOrg());
									 * break; } } }
									 */
									userBehInfo.setUserId(user.getUsername());
									userBehInfo.setUserName(user.getNick());
									userBehInfo.setDateCode(DateUtils.formatDate(date));
									userBehInfo.setErrorInfo("正常");
									userBehInfo.setLoginTimeNow(date);
									userBehInfo.setLoginTimeFirst(userBehList.getLoginTimeFirst());
									userBehInfo.setLoginTimeLast(userBehList.getLoginTimeNow());
									userBehInfo.setOutTime(getDate(System.currentTimeMillis(), min));
									userBehInfo.setIsRecent("1");
									userBehInfo.setIsExc("0");
									userBehInfo.setIsOnline("1");
									userBehInfo.setVersion(users.getVersion() == null
											? moduName.get("version").toString() : users.getVersion());
									userBehInfo.setRegeditDate(userBehList.getRegeditDate());
									//Object obj = request.getAttribute("roles");
									len = Integer.MAX_VALUE;
									tmp = 0;
									/*
									 * if (user.getRoles() != null) { for (Role
									 * r : user.getRoles()) { tmp =
									 * r.getRoleOrg().length(); if (tmp <= len)
									 * { len = tmp; } } for (Role r1 :
									 * user.getRoles()) { if
									 * (r1.getRoleOrg().length() == len) {
									 * 
									 * userBehInfo.setRoleCode(r1.getRole());
									 * break; } } }
									 */
									if (users.getRole() != null) {
										userBehInfo.setRoleCode(users.getRole());
									} else {
										userBehInfo.setRoleCode("");
									}
									// userBehInfo.setRoleCode(obj.toString().substring(0,
									// obj.toString().indexOf(",")));
									logger.debug("userBehInfo=" + userBehInfo.toString());
									logger.debug("accPageInfo=" + accPageInfo.toString());
									logger.debug("====================用户多次登录时==================end");
									setAccData(accPageInfo, "insert");
								} catch (Exception e) {
									userBehInfo.setErrorInfo("数据错误");
									userBehInfo.setIsExc("1");
									logger.debug("===============用户存在时,获取用户行为数据错误=============");
								} finally {
									try {
										setUserData(userBehInfo, "insert");
									} catch (Exception e) {
										logger.debug("===============common-数据ID存在，无需插入=============");
									}
								}
							} else {
								try {
									userBehInfo.setTerminalType(
											users == null ? (user == null ? null : user.getEqp().toUpperCase())
													: users.getEqp().toUpperCase());
									for (Role r : user.getRoles()) {
										for (Nodes node : r.getNodes()) {
											getModuName().put(node.getRunscript(), node.getNodeName());
										}
									}
									userBehInfo.setBehaviorid(behaviorid);
									userBehInfo.setUserId(userBehList.getUserId());
									userBehInfo.setUserName(userBehList.getUserName());
									userBehInfo.setDateCode(DateUtils.formatDate(date));
									userBehInfo.setIsRecent("1");
									userBehInfo.setIsExc("0");
									userBehInfo.setIsOnline("1");
									userBehInfo.setRegeditDate(userBehList.getRegeditDate());
									String roleCode = userBehList.getRoleCode();
									String[] str = roleCode.split(",");
									String strs = null;
									List<String> arr = Arrays.asList(str);
									if (roleCode.indexOf(",") != -1) {
										if (!arr.contains(users.getRole())) {
											strs = roleCode + "," + users.getRole();
										}
										userBehInfo.setRoleCode(strs);
									} else {
										if (!arr.contains(users.getRole())) {
											userBehInfo.setRoleCode(users.getRole() != null
													? roleCode + "," + users.getRole() : roleCode);
										}
									}
									setUserData(userBehInfo, "update");
									
									UserBehInfo userBehLists = interceptorService.findUserDataInfo(user.getUsername(),
											users == null ? (user == null ? null : user.getEqp().toUpperCase())
													: users.getEqp().toUpperCase(),
											null);
									accPageInfo.setAccessname(getModuName().get("/index").toString());
									accPageInfo.setAccessurl("/" + users.getEqp() + "/index");
									accPageInfo.setAccessTime(date);
									accPageInfo.setBehaviorid(userBehLists.getBehaviorid());
									accPageInfo.setUserId(user.getUsername());
									accPageInfo.setDateCode(DateUtils.formatDate(date));
									accPageInfo.setFmName("首页");
									accPageInfo.setIsOut(0);
									accPageInfo.setOutTime(getDate(System.currentTimeMillis(), min));
									accPageInfo.setResTime(resTime);
									logger.debug("userBehInfo=" + userBehInfo.toString());
									logger.debug("accPageInfo=" + accPageInfo.toString());
									setAccData(accPageInfo, "insert");
								} catch (Exception e) {
									logger.debug("====================登录时,获取用户行为数据错误=============");
								}
							}
						}
					}
					// 如果不是正在登录说明已获取users数据,退出时动作
				} else if (request.getAttribute("logout") != null) {
					try {
						UserBehInfo userBehInfo = new UserBehInfo();
						AccPageInfo accPageInfo = new AccPageInfo();
						UserBehInfo userBehList = interceptorService.findUserDataInfo(userName, users == null
								? (user == null ? null : user.getEqp().toUpperCase()) : users.getEqp().toUpperCase(),
								null);
						userBehInfo.setBehaviorid(userBehList == null ? "" : userBehList.getBehaviorid());
						userBehInfo.setDateCode(DateUtils.formatDate(date));
						userBehInfo.setOutTime(getDate(System.currentTimeMillis()));
						userBehInfo.setIsOnline("0");
						userBehInfo.setIsRecent("1");
						userBehInfo.setLoginTimeLast(date);
						String roleCode = userBehList.getRoleCode();
						String[] str = roleCode.split(",");
						String strs = null;
						List<String> arr = Arrays.asList(str);
						if (roleCode.indexOf(",") != -1) {
							if (!arr.contains(users.getRole())) {
								strs = roleCode + "," + users.getRole();
							}
							userBehInfo.setRoleCode(strs);
						} else {
							if (!arr.contains(users.getRole())) {
								userBehInfo.setRoleCode(
										users.getRole() != null ? roleCode + "," + users.getRole() : roleCode);
							}
						}
						setUserData(userBehInfo, "update");
						accPageInfo.setOutTime(getDate(System.currentTimeMillis()));
						accPageInfo.setBehaviorid(userBehList.getBehaviorid());
						accPageInfo.setIsOut(1);
						logger.debug("userBehInfo=" + userBehInfo.toString());
						logger.debug("accPageInfo=" + accPageInfo.toString());
						setAccData(accPageInfo, "update");
					} catch (Exception e) {
						logger.debug("====================用户退出时,获取用户行为数据错误===============");
					}
					// 登录成功后的其他操作
				} else {
					if (userName != null) {
						long currentTimeMillis = System.currentTimeMillis();
						String resTime = (currentTimeMillis - time.get()) + "";
						UserBehInfo userBehList;
						UserBehInfo userBehInfo = null;
						AccPageInfo accPageInfo = null;
						try {
							userBehList = interceptorService.findUserDataInfo(userName,
									users == null ? (user == null ? null : user.getEqp().toUpperCase())
											: users.getEqp().toUpperCase(),
									null);
							// 点击另一个菜单栏时，更新页面离开标识1.表示已离开
							if (userBehList != null) {
								if (behaviorid.equals(userBehList.getBehaviorid())) {
									accPageInfo = new AccPageInfo();
									accPageInfo.setBehaviorid(userBehList.getBehaviorid());
									accPageInfo.setIsOut(1);
									// accPageInfo.setDateCode(DateUtils.formatDate(new
									// Date()));
									accPageInfo.setOutTime(getDate(System.currentTimeMillis()));
									this.interceptorService.updateAccDataById(accPageInfo);
								} else {
									try {
										logger.debug("===============与最新token不一致==================start");
										accPageInfo = new AccPageInfo();
										accPageInfo.setAccessname("首页");
										accPageInfo.setAccessurl("/" + users.getEqp() + "/index");
										accPageInfo.setAccessTime(date);
										accPageInfo.setBehaviorid(behaviorid);
										accPageInfo.setUserId(userName);
										accPageInfo.setDateCode(DateUtils.formatDate(date));
										accPageInfo.setFmName("首页");
										accPageInfo.setIsOut(0);
										accPageInfo.setOutTime(getDate(System.currentTimeMillis(), min));
										accPageInfo.setResTime(resTime);
										accPageInfo.setType("0");
										// 使用报表
										if (request.getRequestURI().equals("/api/block/target")) {
											System.out.println("==============/api/block/target=================");
											String obj = request.getAttribute("subject").toString();
											System.out.println("----------------" + obj + "-------------");
											accPageInfo.setSubCode(obj);
											accPageInfo.setType("2");
										}
										// 使用浏览清单报表
										if (request.getRequestURI().equals("/api/browse/list/custom/info")
												|| request.getRequestURI().equals("/app/browse/list/custom/info")) {
											System.out.println(
													"==============/api/browse/list/custom/info=================");
											String obj = request.getAttribute("subject").toString();
											System.out.println("----------------" + obj + "-------------");
											accPageInfo.setSubCode(obj);
											accPageInfo.setType("2");
										}
										// 使用分析模块
										if (request.getRequestURI().equals("/api/analysis/common")
												|| request.getRequestURI().equals("/app/analysis/common")) {
											System.out.println("==============/api/analysis/common=================");
											String obj = request.getAttribute("subject").toString();
											System.out.println("----------------" + obj + "-------------");
											accPageInfo.setSubCode(obj);
											accPageInfo.setType("1");
										}
										userBehInfo = new UserBehInfo();
										userBehInfo.setTerminalType(
												users == null ? (user == null ? null : user.getEqp().toUpperCase())
														: users.getEqp().toUpperCase());
										userBehInfo.setLevel(users == null ? user.getRoles().get(0).getRoleOrg()
												: users.getRoleOrg());
										userBehInfo.setBehaviorid(behaviorid);
										userBehInfo.setUserId(userName);
										userBehInfo.setRegeditDate(userBehList.getRegeditDate());
										userBehInfo.setUserName(users == null ? (user != null ? user.getNick() : null)
												: users.getNick());
										userBehInfo
												.setDateCode(DateUtils.formatDate(getDate(System.currentTimeMillis())));
										userBehInfo.setErrorInfo("正常");
										userBehInfo.setLoginTimeNow(date);
										userBehInfo.setLoginTimeFirst(userBehList.getLoginTimeFirst());
										userBehInfo.setLoginTimeLast(userBehList.getLoginTimeNow());
										userBehInfo.setOutTime(getDate(System.currentTimeMillis(), min));
										userBehInfo.setIsRecent("1");
										userBehInfo.setIsExc("0");
										userBehInfo.setIsOnline("1");
										userBehInfo.setVersion(users.getVersion() == null
												? moduName.get("version").toString() : users.getVersion());
										String roleCode = userBehList.getRoleCode();
										userBehInfo.setRoleCode(roleCode);
										/*
										 * Object obj =
										 * request.getAttribute("roles"); if
										 * (obj != null) {
										 * //userBehInfo.setRoleCode(obj.
										 * toString()); } else {
										 * userBehInfo.setRoleCode( userBehList
										 * == null ? users.getRole() :
										 * userBehList.getRoleCode()); }
										 */
										logger.debug("userBehInfo=" + userBehInfo.toString());
										logger.debug("accPageInfo=" + accPageInfo.toString());
										try {
											setUserData(userBehInfo, "insert");
										} catch (Exception e) {
											logger.debug("===============common-数据ID存在，无需插入=============");
										}
										setAccData(accPageInfo, "insert");
										// 这里有两种结果1.插入成功,需要把以前的数据的最近标识修改为0
										// 2.插入失败,原因id存在
										if (true) {
											AccPageInfo accPageInfoTemp = new AccPageInfo();
											accPageInfoTemp.setBehaviorid(userBehList.getBehaviorid());
											accPageInfoTemp.setIsOut(1);
											accPageInfoTemp.setOutTime(userBehList.getOutTime());
											this.interceptorService.updateAccDataById(accPageInfoTemp);
											UserBehInfo online = new UserBehInfo();
											online.setIsOnline("0");
											online.setUserId(userName);
											online.setIsRecent("0");
											online.setTerminalType(users.getEqp() == null
													? (user == null ? null : user.getEqp().toUpperCase())
													: users.getEqp().toUpperCase());
											interceptorService.updateOtherUserData(online, 2);
										}
										logger.debug("====================响应时间:" + resTime + "ms===========");
										logger.debug("===============与最新token不一致==================end");
										return;
									} catch (Exception e) {
										logger.debug("===============与最新token不一致时，获取用户行为数据错误！==================");
									}

								}
								// 没有当前登录人信息，需一次性再次插入
							} else {
								try {
									logger.debug("===============登陆失效重新获取token==================start");
									// 当没有当前用户数据时，第一次插入
									accPageInfo = new AccPageInfo();
									accPageInfo.setAccessname("首页");
									accPageInfo.setAccessurl("/" + users.getEqp() + "/index");
									accPageInfo.setAccessTime(date);
									accPageInfo.setBehaviorid(behaviorid);
									accPageInfo.setUserId(userName);
									accPageInfo.setDateCode(DateUtils.formatDate(date));
									accPageInfo.setFmName("首页");
									accPageInfo.setIsOut(0);
									accPageInfo.setOutTime(getDate(System.currentTimeMillis(), min));
									accPageInfo.setResTime(resTime);
									accPageInfo.setType("0");
									// 使用报表
									if (request.getRequestURI().equals("/api/block/target")) {
										System.out.println("==============/api/block/target=================");
										String obj = request.getAttribute("subject").toString();
										System.out.println("----------------" + obj + "-------------");
										accPageInfo.setSubCode(obj);
										accPageInfo.setType("2");
									}
									// 使用浏览清单报表
									if (request.getRequestURI().equals("/api/browse/list/custom/info")
											|| request.getRequestURI().equals("/app/browse/list/custom/info")) {
										System.out
												.println("==============/api/browse/list/custom/info=================");
										String obj = request.getAttribute("subject").toString();
										System.out.println("----------------" + obj + "-------------");
										accPageInfo.setSubCode(obj);
										accPageInfo.setType("2");
									}
									// 使用分析模块
									if (request.getRequestURI().equals("/api/analysis/common")
											|| request.getRequestURI().equals("/app/analysis/common")) {
										System.out.println("==============/api/analysis/common=================");
										String obj = request.getAttribute("subject").toString();
										System.out.println("----------------" + obj + "-------------");
										accPageInfo.setSubCode(obj);
										accPageInfo.setType("1");
									}
									userBehInfo = new UserBehInfo();
									userBehInfo.setTerminalType(
											users == null ? (user == null ? null : user.getEqp().toUpperCase())
													: users.getEqp().toUpperCase());
									userBehInfo.setLevel(
											users == null ? user.getRoles().get(0).getRoleOrg() : users.getRoleOrg());
									userBehInfo.setBehaviorid(behaviorid);
									userBehInfo.setUserId(userName);
									userBehInfo.setRegeditDate(date);
									userBehInfo.setUserName(
											users == null ? (user != null ? user.getNick() : null) : users.getNick());
									userBehInfo.setDateCode(DateUtils.formatDate(date));
									userBehInfo.setErrorInfo("正常");
									userBehInfo.setLoginTimeNow(date);
									userBehInfo.setLoginTimeFirst(date);
									userBehInfo.setLoginTimeLast(date);
									userBehInfo.setOutTime(getDate(System.currentTimeMillis(), min));
									userBehInfo.setIsRecent("1");
									userBehInfo.setIsExc("0");
									userBehInfo.setIsOnline("1");
									userBehInfo.setVersion(users.getVersion() == null
											? moduName.get("version").toString() : users.getVersion());
									/*
									 * Object obj =
									 * request.getAttribute("roles"); if (obj !=
									 * null) {
									 * userBehInfo.setRoleCode(obj.toString());
									 * } else {
									 * userBehInfo.setRoleCode(userBehList ==
									 * null ? users.getRole() :
									 * userBehList.getRoleCode().substring(0,
									 * obj.toString().indexOf(","))); }
									 */
									if (users.getRole() != null) {
										userBehInfo.setRoleCode(users.getRole());
									} else {
										userBehInfo.setRoleCode("");
									}
									logger.debug("userBehInfo=" + userBehInfo.toString());
									logger.debug("accPageInfo=" + accPageInfo.toString());
									try {
										setUserData(userBehInfo, "insert");
									} catch (Exception e) {
										logger.debug("===============common-数据ID存在，无需插入=============");
									}
									setAccData(accPageInfo, "insert");
									logger.debug("====================响应时间:" + resTime + "ms===========");
									logger.debug("===============登陆失效重新获取token==================end");
									return;
								} catch (Exception e) {
									logger.debug("===============登陆失效重新获取token时，获取用户行为数据错误！==================");
								}
							}
							// 主业务
							userBehInfo = new UserBehInfo();
							accPageInfo = null;
							accPageInfo = new AccPageInfo();
							userBehInfo.setBehaviorid(userBehList == null ? users.getToken().substring(0, 32)
									: userBehList.getBehaviorid());
							userBehInfo.setErrorInfo("正常");
							if (behaviorid.equals(userBehList.getBehaviorid())) {
								userBehInfo.setOutTime(getDate(System.currentTimeMillis(), min));
								accPageInfo.setOutTime(getDate(System.currentTimeMillis(), min));
							} else {
								accPageInfo.setOutTime(userBehList.getOutTime());
							}
							userBehInfo.setIsExc("0");
							int len = Integer.MAX_VALUE;
							int tmp = 0;
							//role='1_100101_1' roleDept='100101' roleOrg='1'
							if (users.getRole() != null) {
								len = userBehList.getLevel()==null?Integer.MAX_VALUE:userBehList.getLevel().length();
								tmp = users.getRoleOrg().length();
									if (tmp <= len) {
										len = tmp;
										userBehInfo.setLevel(users.getRoleOrg());
									}else{
										userBehInfo.setLevel(userBehList.getLevel());
									}
								
									/*if (r1.getRoleOrg().length() == len) {
										userBehInfo.setLevel(r1.getRoleOrg());
										break;
									}*/
							}
							String roleCode = userBehList.getRoleCode();
							String[] str = roleCode.split(",");
							String strs = null;
							List<String> arr = Arrays.asList(str);
							if (roleCode.indexOf(",") != -1) {
								if (!arr.contains(users.getRole())) {
									strs = roleCode + "," + users.getRole();
								}
								userBehInfo.setRoleCode(strs);
							} else {
								if (!arr.contains(users.getRole())) {
									userBehInfo.setRoleCode(users.getRole() != null
											? ("".equals(roleCode) ? "" : (roleCode + ",")) + users.getRole() : roleCode);
								}
							}

							accPageInfo.setAccessname(request.getRequestURI());
							accPageInfo.setAccessurl(request.getRequestURI());
							accPageInfo.setAccessTime(getDate(System.currentTimeMillis()));
							accPageInfo.setBehaviorid(userBehList == null ? users.getToken().substring(0, 32)
									: userBehList.getBehaviorid());
							accPageInfo.setUserId(userBehList == null ? users.getUsername() : userBehList.getUserId());
							accPageInfo.setDateCode(DateUtils.formatDate(date));
							String[] mName = request.getRequestURI().split("/");
							String fmName1 = "/" + mName[1];
							String fmName = "/" + mName[1] + "/" + mName[2];
							accPageInfo.setFmName(getModuName().get(fmName) == null
									? (getModuName().get(fmName1) == null ? request.getRequestURI()
											: getModuName().get(fmName1).toString())
									: getModuName().get(fmName).toString());
							accPageInfo.setIsOut(0);
							accPageInfo.setResTime(resTime);
							accPageInfo.setType("0");
							// 使用报表
							if (request.getRequestURI().equals("/api/block/target")) {
								System.out.println("==============/api/block/target=================");
								String obj = request.getAttribute("subject").toString();
								System.out.println("----------------" + obj + "-------------");
								accPageInfo.setSubCode(obj);
								accPageInfo.setType("2");
							}
							// 使用浏览清单报表
							if (request.getRequestURI().equals("/api/browse/list/custom/info")
									|| request.getRequestURI().equals("/app/browse/list/custom/info")) {
								System.out.println("==============/api/browse/list/custom/info=================");
								String obj = request.getAttribute("subject").toString();
								System.out.println("----------------" + obj + "-------------");
								accPageInfo.setSubCode(obj);
								accPageInfo.setType("2");
							}
							// 使用分析模块
							if (request.getRequestURI().equals("/api/analysis/common")
									|| request.getRequestURI().equals("/app/analysis/common")) {
								System.out.println("==============/api/analysis/common=================");
								String obj = request.getAttribute("subject").toString();
								System.out.println("----------------" + obj + "-------------");
								accPageInfo.setSubCode(obj);
								accPageInfo.setType("1");
							}
							logger.debug("userBehInfo=" + userBehInfo.toString());
							logger.debug("accPageInfo=" + accPageInfo.toString());
							setAccData(accPageInfo, "insert");
						} catch (Exception e) {
							e.printStackTrace();
							userBehInfo.setErrorInfo("数据错误");
							userBehInfo.setIsExc("1");
							logger.debug("===============登录成功后触发其他操作时,获取行为分析错误=============");
						} finally {
							if (!(date == userBehInfo.getRegeditDate())) {
								setUserData(userBehInfo, "update");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.debug("===============zzjy-common模块,获取行为分析错误=============");
		}
	}

	private Date getDate(Long date) {
		return new Date(date);
	}

	private Date getDate(Long date, Long appendTime) {
		return new Date(date + appendTime);
	}

	private void setUserData(UserBehInfo newUserBehInfo, String type) {
		switch (type) {
		case "insert":
			if (interceptorService.insertUserData(newUserBehInfo) != 0) {
				logger.debug("=====================已获得用户行为分析数据=========================");
			}
			break;
		case "update":
			if (interceptorService.updateUserData(newUserBehInfo) != 0) {
				logger.debug("=====================已改正用户行为分析数据=========================");
			}
			break;

		default:
			break;
		}

	}

	private void setAccData(AccPageInfo newAccPageInfo, String type) {
		switch (type) {
		case "insert":
			if (interceptorService.insertAccData(newAccPageInfo) != 0) {
				logger.debug("=====================已获得用户访问页面数据=========================");
			}
			break;
		case "update":
			if (interceptorService.updateAccData(newAccPageInfo) != 0) {
				logger.debug("=====================已改正用户访问页面数据=========================");
			}
			break;

		default:
			break;
		}
	}

	public static void main(String args[]) {
		String code = "Mobile/13B143 Safari/601.1 SINO_ANDROID_APP/0.1.0";
		Pattern pattern = Pattern.compile("SINO_([\\w]+)_APP\\/([\\d.]+)");
		Matcher matcher = pattern.matcher(code);
		boolean isFind = matcher.find();
		if (isFind) {
			System.out.println(matcher.group(2));
		}
	}
}
