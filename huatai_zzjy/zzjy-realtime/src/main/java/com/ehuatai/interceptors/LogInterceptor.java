package com.ehuatai.interceptors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ehuatai.biz.domain.AccPageInfo;
import com.ehuatai.biz.domain.User;
import com.ehuatai.biz.domain.UserBehInfo;
import com.ehuatai.biz.service.InterceptorService;
import com.ehuatai.util.DateUtils;
import com.ehuatai.util.RequestHeaderUtil;

@Component
@SuppressWarnings("unused")
public class LogInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private InterceptorService interceptorService;

	private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

	private static ThreadLocal<Long> time = new ThreadLocal<Long>();

	private static Map<String, Object> moduName = new HashMap<String, Object>();

	private static Map<String, Object> deptName = new HashMap<String, Object>();

	private static Map<String, Object> getModuName() {
		moduName.put("/api/analysis", "分析管理");
		moduName.put("/api/browse", "浏览清单");
		moduName.put("/api/block", "板块");
		moduName.put("/api/tool", "工具");
		moduName.put("/api/index", "首页");
		moduName.put("/api/index/dashbord", "达时报");
		moduName.put("/api/index/fixed", "固定指标");
		moduName.put("/app/analysis", "分析管理");
		moduName.put("/app/browse", "浏览清单");
		moduName.put("/app/block", "板块");
		moduName.put("/app/tool", "工具");
		moduName.put("/app/index/dashbord", "达时报");
		moduName.put("/app/index/fixed", "固定指标");
		moduName.put("/index", "首页");
		return moduName;
	}

	private static Map<String, Object> getDeptName() {
		deptName.put("trade", "个业部固定指标");
		deptName.put("premium", "收展部固定指标");
		deptName.put("org", "机构部固定指标");
		deptName.put("train", "培训部固定指标");
		return deptName;
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
			logger.warn("===============实时指标记录日志==================start");
			User users = RequestHeaderUtil.getUser(request);
			long currentTimeMillis = System.currentTimeMillis();
			String resTime = (currentTimeMillis - time.get()) + "";
			long min = 10 * 1000 * 60;
			// 判断是否登录或者正在登录页面
			if (users.getUsername() != null) {
				String behaviorid = users.getToken().substring(0, 32);
				Date date = new Date();
				UserBehInfo userBehInfo = new UserBehInfo();
				AccPageInfo accPageInfo = new AccPageInfo();
				try {
					UserBehInfo userBehList = interceptorService.findUserDataInfo(users.getUsername(),
							users == null ? null : users.getEqp().toUpperCase(), null);
					// 点击另一个菜单栏时，更新页面离开标识1.表示已离开
					if (users != null) {
						if (userBehList == null) {
							try {
								logger.debug("===============用户首次登录时==================start");
								accPageInfo.setAccessname(getModuName().get("/index").toString());
								accPageInfo.setAccessurl("/" + users.getEqp() + "/index");
								accPageInfo.setAccessTime(getDate(System.currentTimeMillis()));
								accPageInfo.setBehaviorid(behaviorid);
								accPageInfo.setUserId(users.getUsername());
								accPageInfo.setDateCode(DateUtils.formatDate(new Date()));
								accPageInfo.setFmName("首页");
								accPageInfo.setIsOut(0);
								accPageInfo.setOutTime(getDate(System.currentTimeMillis(), min));
								accPageInfo.setResTime(resTime);
								accPageInfo.setType("0");

								userBehInfo.setTerminalType(users == null ? "" : users.getEqp().toUpperCase());
								userBehInfo.setLevel(users.getRoleOrg());
								userBehInfo.setBehaviorid(behaviorid);
								userBehInfo.setUserId(users.getUsername());
								userBehInfo.setRegeditDate(date);
								userBehInfo.setUserName(users.getNick());
								userBehInfo.setDateCode(DateUtils.formatDate(new Date()));
								userBehInfo.setErrorInfo("正常");
								userBehInfo.setLoginTimeNow(date);
								userBehInfo.setLoginTimeFirst(date);
								userBehInfo.setLoginTimeLast(date);
								userBehInfo.setOutTime(getDate(System.currentTimeMillis(), min));
								userBehInfo.setIsRecent("1");
								userBehInfo.setIsExc("0");
								userBehInfo.setIsOnline("1");
								userBehInfo.setVersion(users.getVersion());

								// Object obj = request.getAttribute("roles");
								userBehInfo.setRoleCode(users.getRole());
								// userBehInfo.setRoleCode(users.getRole());
								logger.debug("userBehInfo=" + userBehInfo.toString());
								logger.debug("accPageInfo=" + accPageInfo.toString());
								try{
									setUserData(userBehInfo, "insert");
								}catch (Exception e) {
									logger.debug("===============realtime-数据ID存在，无需插入=============");
								}
								setAccData(accPageInfo, "insert");
								logger.debug("====================响应时间:" + resTime + "ms===========");
								logger.debug("====================用户首次登录时==================end");
							} catch (Exception e) {
								e.printStackTrace();
								logger.debug("====================首次登录时,获取用户行为数据错误===========");
							}
						}else if ((userBehList != null) && !behaviorid.equals(userBehList.getBehaviorid())) {
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
									online.setUserId(users.getUsername());
									online.setIsRecent("0");
									online.setTerminalType(users.getEqp().toUpperCase());
									interceptorService.updateOtherUserData(online, 1);
								}
								accPageInfo.setAccessname(getModuName().get("/index").toString());
								accPageInfo.setAccessurl("/" + users.getEqp() + "/index");
								accPageInfo.setAccessTime(getDate(System.currentTimeMillis()));
								accPageInfo.setBehaviorid(behaviorid);
								accPageInfo.setUserId(users.getUsername());
								accPageInfo.setDateCode(DateUtils.formatDate(date));
								userBehInfo.setLoginTimeLast(userBehList.getLoginTimeNow());
								accPageInfo.setFmName("首页");
								accPageInfo.setIsOut(0);
								accPageInfo.setOutTime(getDate(System.currentTimeMillis(), min));
								accPageInfo.setResTime(resTime);
								accPageInfo.setType("0");
								userBehInfo.setTerminalType(users.getEqp().toUpperCase());
								userBehInfo.setBehaviorid(behaviorid);
								userBehInfo.setLevel(userBehList.getLevel());
								userBehInfo.setUserId(users.getUsername());
								userBehInfo.setUserName(users.getNick());
								userBehInfo.setDateCode(DateUtils.formatDate(date));

								userBehInfo.setLevel(users.getRoleOrg());
								userBehInfo.setErrorInfo("正常");
								userBehInfo.setLoginTimeNow(date);
								userBehInfo.setLoginTimeFirst(userBehList.getLoginTimeFirst());
								userBehInfo.setLoginTimeLast(userBehList.getLoginTimeNow());
								userBehInfo.setOutTime(getDate(System.currentTimeMillis(), min));
								userBehInfo.setIsRecent("1");
								userBehInfo.setIsExc("0");
								userBehInfo.setIsOnline("1");
								userBehInfo.setVersion(users.getVersion());

								userBehInfo.setRegeditDate(userBehList.getRegeditDate());
								// Object obj = request.getAttribute("roles");
								// userBehInfo.setRoleCode(users.getRole());
								String roleCode = userBehList.getRoleCode();
								userBehInfo.setRoleCode(users.getRole());
								logger.debug("userBehInfo=" + userBehInfo.toString());
								logger.debug("accPageInfo=" + accPageInfo.toString());
								logger.debug("====================用户多次登录时==================end");
								setAccData(accPageInfo, "insert");
							} catch (Exception e) {
								userBehInfo.setErrorInfo("数据错误");
								userBehInfo.setIsExc("1");
								logger.debug("===============用户存在时,获取用户行为数据错误=============");
							} finally {
								try{
									setUserData(userBehInfo, "insert");
								}catch (Exception e) {
									logger.debug("===============realtime-数据ID存在，无需插入=============");
								}
							}
						} else {
							if (userBehList != null) {
								try {
									String roleCode = userBehList.getRoleCode();
									if (roleCode.indexOf(",") != -1) {
										String[] str = roleCode.split(",");
										for (String str1 : str) {
											if (!str1.equals(users.getRoleOrg())) {
												userBehInfo.setRoleCode(users.getRole());
											}
										}
									}
									accPageInfo = new AccPageInfo();
									accPageInfo.setBehaviorid(userBehList.getBehaviorid());
									accPageInfo.setIsOut(1);
									accPageInfo.setOutTime(new Date());
									this.interceptorService.updateAccDataById(accPageInfo);

									// 主业务
									userBehInfo = new UserBehInfo();
									accPageInfo = null;
									accPageInfo = new AccPageInfo();
									userBehInfo.setBehaviorid(userBehList.getBehaviorid());
									userBehInfo.setErrorInfo("正常");
									if (behaviorid.equals(userBehList.getBehaviorid())) {
										userBehInfo.setOutTime(new Date(System.currentTimeMillis() + min));
										accPageInfo.setOutTime(new Date(System.currentTimeMillis() + min));
									} else {
										accPageInfo.setOutTime(userBehList.getOutTime());
									}
									userBehInfo.setIsExc("0");
									userBehInfo.setTerminalType(users == null ? null : users.getEqp().toUpperCase());
									accPageInfo.setAccessname(request.getRequestURI());
									accPageInfo.setAccessurl(request.getRequestURI());
									accPageInfo.setAccessTime(date);
									accPageInfo.setBehaviorid(userBehList.getBehaviorid());
									accPageInfo.setUserId(
											userBehList == null ? users.getUsername() : userBehList.getUserId());
									accPageInfo.setDateCode(new SimpleDateFormat("yyyy-MM-dd").format(date));
									String[] mName = request.getRequestURI().split("/");
									String fmName = "/" + mName[1] + "/" + mName[2] + "/" + mName[3];
									String fmName1 = "/" + mName[1] + "/" + mName[2];
									accPageInfo.setFmName(getModuName().get(fmName) == null
											? (getModuName().get(fmName1) == null ? request.getRequestURI()
													: getModuName().get(fmName1).toString())
											: getModuName().get(fmName).toString());
									accPageInfo.setIsOut(0);
									accPageInfo.setResTime(System.currentTimeMillis() - time.get() + "");
									accPageInfo.setType("0");
									// 使用模块
									if (request.getRequestURI().equals("/api/index/fixed")
											|| request.getRequestURI().equals("/app/index/fixed")) {
										System.out.println("==============/api/index/fixed=================");
										String obj = request.getAttribute("deptCode").toString();
										System.out.println("----------------" + obj + "-------------");
										accPageInfo.setSubCode(getDeptName().get(obj) == null ? ""
												: getDeptName().get(obj).toString());
										accPageInfo.setType("3");
									}
									interceptorService.insertAccData(accPageInfo);
								} catch (Exception e) {
									userBehInfo.setErrorInfo("数据错误");
									userBehInfo.setIsExc("1");
								} finally {
									interceptorService.updateUserData(userBehInfo);
								}
							}
						}
					}
				} catch (Exception e) {
					logger.debug("===============加载用户数据错误=============");
				}
			}
		} catch (Exception e) {
			logger.debug("===============加载用户数据=============");
		}
	}

	private Date getDate() {
		return new Date();
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

	private Date getDate(Long date) {
		return new Date(date);
	}

	private Date getDate(Long date, Long appendTime) {
		return new Date(date + appendTime);
	}
}
