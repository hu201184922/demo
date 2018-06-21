/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.security.authc.filter;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.log.service.HyLogService;
import com.fairyland.jdp.framework.security.authc.util.StatelessToken;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.framework.util.PropsUtil;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 上午10:58:07
 * @version V1.0
 */
@Component
@Scope("prototype")
public class CaptchaAuthenticationFilter extends FormAuthenticationFilter {
	private Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * 登录：域验证类型
	 */
	private static final String DOMAIN_LOGIN_TYPE = "domainLoginType";
	/**
	 * 域验证成功
	 */
	private static final String LDAP_TRUE = "true";
	/**
	 * 域验证失败
	 */
	private static final String LDAP_FALSE = "false";
	/**
	 * 本系统不存在该域用户
	 */
	private static final String LDAP_NOTEXIST = "notexist";
	/**
	 * 登录用户名
	 */
	private static final String LOGIN_USERNAME = "username";
	/**
	 * 登录密码
	 */
	private static final String LOGIN_PASSWORD = "password";
	/**
	 * 登录类型
	 */
	private static final String LOGIN_TYPE = "loginType";
	/**
	 * 提交方式
	 */
	private static final String SUBMIT_TYPE = "POST";
	/**
	 * 本系统该用户被锁定
	 */
	private static final String LDAP_LOCKED = "locked";
	
	/**------------------------------------------------------**/
	private static final String USE_LDAP = PropsUtil.get("ldap.use_ldap");
	private static final String LDAP_URL = PropsUtil.get("ldap.url");
	private static final String USER_PREFIX = PropsUtil.get("ldap.user.prefix");
	/**
	 * 默认验证码参数名称
	 */
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	/**
	 * 登录次数超出allowLoginNum时，存储在session记录是否展示验证码的key默认名称
	 */
	public static final String DEFAULT_SHOW_CAPTCHA_KEY = "showCaptcha";
	
	public static final String USER_LOGIN_ID_KEY = "LoginID";

	/**
	 * 默认在session中存储的登录次数名称
	 */
	private static final String DEFAULT_LOGIN_NUM_KEY = "loginNum";

	private static final String EXCESSIVE_ATTEMPTS_POLICY_LOCKED = "LOCKED";
	private static final String EXCESSIVE_ATTEMPTS_POLICY_CAPTCHA = "CAPTCHA";

	// 验证码参数名称
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	// 在session中的存储验证码的key名称
	private String sessionCaptchaKeyAttribute = DEFAULT_CAPTCHA_PARAM;
	// 在session中存储的登录次数名称
	private String loginNumKeyAttribute = DEFAULT_LOGIN_NUM_KEY;
	// 登录次数超出allowLoginNum时，存储在session记录是否展示验证码的key名称
	private String sessionShowCaptchaKeyAttribute = DEFAULT_SHOW_CAPTCHA_KEY;
	// 允许登录次数，当登录次数大于该数值时，自动锁定帐号
	@Value("${spring.fairyland.shiro.passwordAttemptTimes}")
	private Integer passwordAttemptTimes = 1;
	
	// 过度尝试密码策略
	@Value("${spring.fairyland.shiro.excessiveAttemptsPolicy}")
	private String excessiveAttemptsPolicy = EXCESSIVE_ATTEMPTS_POLICY_LOCKED;
	
	// 锁定持续时间
	@Value("${spring.fairyland.shiro.lockedDuration}")
	private Long lockedDuration;
	@Autowired
	private UserService userService;
	@Autowired
	private HyLogService hyLogService;
	
	@Value("${spring.fairyland.shiro.failureKeyAttribute}")
	public void setFailureKeyAttribute(String failureKeyAttribute) {
        super.setFailureKeyAttribute(failureKeyAttribute);
    }
	/**
	 * 重写父类方法，在shiro执行登录时先对比验证码，正确后在登录，否则直接登录失败
	 */
	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		System.out.println("---------------Got Login Request-----------------");
		Session session = getSubject(request, response).getSession();
		session.setAttribute(getSessionShowCaptchaKeyAttribute(), false);

		Boolean showCaptcha = (Boolean) session.getAttribute(DEFAULT_SHOW_CAPTCHA_KEY);
		if (BooleanUtils.isTrue(showCaptcha)) {
			// 获取当前验证码
			String currentCaptcha = (String) session.getAttribute(getSessionCaptchaKeyAttribute());
			// 获取用户输入的验证码
			String submitCaptcha = getCaptcha(request);
			// 如果验证码不匹配，登录失败
			if (StringUtils.isEmpty(submitCaptcha)
					|| !StringUtils.equals(currentCaptcha,
							submitCaptcha.toLowerCase())) {
				return onLoginFailure(this.createToken(request, response),
						new AccountException("验证码不正确"), request, response);
			}
		}
//		String loginType = request.getParameter(LOGIN_TYPE);
		String username1 = request.getParameter(LOGIN_USERNAME);
		User user = userService.findUserByLoginName(username1);
		System.out.println("---------------user="+user+"-----------------");
		if(user!=null){
			String isType = user.getIsType();
			String orgGrade = user.getOrgGrade();
//			user.get
			//判断如果是域登录，则验证并根据用户名登录，否则执行原登录
			if ("02".equals(isType) || "DM".equals(orgGrade)) {
				HttpServletRequest rq = (HttpServletRequest) request;
				String flag = LDAP_FALSE;
				Subject subject =SecurityUtils.getSubject();
				StatelessToken token = new StatelessToken();
				if (SUBMIT_TYPE.equals(rq.getMethod())) {
					//POST方式提交为登录动作
					String username = rq.getParameter(LOGIN_USERNAME);
					String password = rq.getParameter(LOGIN_PASSWORD);
					String LDAP_result = checkLDAP(username,password);
					if (LDAP_TRUE.equals(LDAP_result)) {
						//域验证通过
						token.setUsername(username);
						token.setTicket("1");
						subject.login(token);
						flag = LDAP_TRUE;
					} else if (LDAP_NOTEXIST.equals(LDAP_result)) {
						//该域用户名本系统不存在
						flag = LDAP_NOTEXIST;
					} else if (LDAP_LOCKED.equals(LDAP_result)) {
						flag = LDAP_LOCKED;
					}
				}
				if (LDAP_TRUE.equals(flag)) {
					return onLoginSuccess(token, subject, request, response);
				} else if (LDAP_FALSE.equals(flag)) {
					return onLoginFailure(this.createToken(request, response), new AccountException("域登录验证未通过"), request, response);
				} else if (LDAP_LOCKED.equals(flag)) {
					return onLoginFailure(this.createToken(request, response), new AccountException("该域用户已在本系统锁定"), request, response);
				} else {
					return onLoginFailure(this.createToken(request, response), new AccountException("本系统不存在该域用户"), request, response);
				}
			} else {
				return super.executeLogin(request, response);
			}
		}else{
			return onLoginFailure(this.createToken(request, response), new UnknownAccountException(), request, response);
//			return super.executeLogin(request, response);
		}
		

	}

	@Override
	public boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
//		System.out.println(((HttpServletRequest)request).getRequestURI());
//		System.out.println("LoginUrl="+getLoginUrl());
		if(SecurityUtils.getSubject().isAuthenticated()){
			//如果已经登录，再次登录时
			HttpServletRequest req = (HttpServletRequest) request;
			if(isLoginRequest(req)) {
				//可以对登录的get、post方式做不同处理
				//if("get".equalsIgnoreCase(req.getMethod())) {//form表单提交
					SavedRequest savedRequest = 
							WebUtils.getSavedRequest(request);
					String redirectToUrl = 
							savedRequest!=null ?
									savedRequest.getRequestUrl():super.getSuccessUrl();
					WebUtils.redirectToSavedRequest(request, response, redirectToUrl);
					return false;
//				}
//				return true;
			}
		}
		return super.onPreHandle(request, response, mappedValue);
	}
	
	private boolean isLoginRequest(HttpServletRequest req) {
		return pathsMatch(super.getLoginUrl(), WebUtils.getPathWithinApplication(req));
	}
	
	/**
	 * 设置验证码提交的参数名称
	 * 
	 * @param captchaParam
	 *            验证码提交的参数名称
	 */
	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	/**
	 * 获取验证码提交的参数名称
	 * 
	 * @return String
	 */
	public String getCaptchaParam() {
		return captchaParam;
	}

	/**
	 * 设置在session中的存储验证码的key名称
	 * 
	 * @param sessionCaptchaKeyAttribute
	 *            存储验证码的key名称
	 */
	public void setSessionCaptchaKeyAttribute(String sessionCaptchaKeyAttribute) {
		this.sessionCaptchaKeyAttribute = sessionCaptchaKeyAttribute;
	}

	/**
	 * 获取设置在session中的存储验证码的key名称
	 * 
	 * @return Sting
	 */
	public String getSessionCaptchaKeyAttribute() {
		return sessionCaptchaKeyAttribute;
	}

	/**
	 * 获取在session中存储的登录次数名称
	 * 
	 * @return Stromg
	 */
	public String getLoginNumKeyAttribute() {
		return loginNumKeyAttribute;
	}

	/**
	 * 设置在session中存储的登录次数名称
	 * 
	 * @param loginNumKeyAttribute
	 *            登录次数名称
	 */
	public void setLoginNumKeyAttribute(String loginNumKeyAttribute) {
		this.loginNumKeyAttribute = loginNumKeyAttribute;
	}

	/**
	 * 获取用户输入的验证码
	 * 
	 * @param request
	 *            ServletRequest
	 * 
	 * @return String
	 */
	public String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	/**
	 * 获取登录次数超出allowLoginNum时，存储在session记录是否展示验证码的key名称
	 * 
	 * @return String
	 */
	public String getSessionShowCaptchaKeyAttribute() {
		return sessionShowCaptchaKeyAttribute;
	}

	/**
	 * 设置登录次数超出allowLoginNum时，存储在session记录是否展示验证码的key名称
	 * 
	 * @param sessionShowCaptchaKeyAttribute
	 *            是否展示验证码的key名称
	 */
	public void setSessionShowCaptchaKeyAttribute(
			String sessionShowCaptchaKeyAttribute) {
		this.sessionShowCaptchaKeyAttribute = sessionShowCaptchaKeyAttribute;
	}

	/**
	 * 获取允许登录次数
	 * 
	 * @return Integer
	 */
	public Integer getPasswordAttemptTimes() {
//		return PreferenceUtils.getInteger(PreferenceCode.CODE_PWD_ATT_TIMES, passwordAttemptTimes);
		return passwordAttemptTimes;
	}

	/**
	 * 设置允许登录次数，当登录次数大于该数值时，会在页面中显示验证码
	 * 
	 * @param allowLoginNum
	 *            允许登录次数
	 */
	public void setPasswordAttemptTimes(Integer passwordAttemptTimes) {
		this.passwordAttemptTimes = passwordAttemptTimes;
	}

	/**
	 * 重写父类方法，当登录失败将异常信息设置到request的attribute中
	 */
	@Override
	protected void setFailureAttribute(ServletRequest request,
			AuthenticationException ae) {
		if (ae instanceof UnknownAccountException
				|| ae instanceof IncorrectCredentialsException) {
			request.setAttribute(getFailureKeyAttribute(), "用户名或密码不正确");
		} else {
			request.setAttribute(getFailureKeyAttribute(), ae.getMessage());
		}
	}

	/**
	 * 重写父类方法，当登录失败次数大于allowLoginNum（允许登录次）时，将显示验证码
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		Session session = getSubject(request, response).getSession(false);

		// 登录失败次数大于allowLoginNum时，锁定帐号
		if (e instanceof IncorrectCredentialsException) {
			// 获取登录次数
//			Integer number = (Integer) session
//					.getAttribute(getLoginNumKeyAttribute());

			// 首次登录，将该数量记录在session中
//			if (number == null) {
//				number = 0;
//				session.setAttribute(getLoginNumKeyAttribute(), number);
//			}
			
			String loginName = (String) token.getPrincipal();
			User user = userService.findUserByLoginName(loginName);
			
			Long number = user.getLoginNum();
			
			if(number == null){
				number = 0L;
			}

			if (number >= getPasswordAttemptTimes()) {
				if (EXCESSIVE_ATTEMPTS_POLICY_CAPTCHA
						.equals(getExcessiveAttemptsPolicy())) {
					session.setAttribute(getSessionShowCaptchaKeyAttribute(),
							true);
				} else {
					
					//用enabled字段启用或禁用来表示用户启动或禁用
					user.setEnabled(Boolean.FALSE);
//					userService.lock(loginName);
					e = new AccountException("密码尝试次数过多，帐号已经被锁定，请联系管理员");
				}
			}
//			session.setAttribute(getLoginNumKeyAttribute(), ++number);
			Date errorTryTime = user.getErrorTryTime();
			Date now = new Date();
			if(errorTryTime != null && now.getTime()-errorTryTime.getTime() < 24*60*60*1000){
				user.setLoginNum(++number);
			}else{
				user.setErrorTryTime(now);
				user.setLoginNum(1L);
			}
			userService.updateUser(user);
//			request.setAttribute(getLoginNumKeyAttribute(), user.getLoginNum());
		}
		hyLogService.createLog((HttpServletRequest)request, null, e,String.format("用户'%s'登录失败", token.getPrincipal()));
		return super.onLoginFailure(token, e, request, response);
	}

	/**
	 * 重写父类方法，当登录成功后，将allowLoginNum（允许登录次）设置为0，重置下一次登录的状态
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		System.out.println("---------------onLoginSuccess start-----------------");
		Session session = subject.getSession(false);
	
		if(request.getParameter("remember")==null){
			removeCookie("username", request,(HttpServletResponse) response);
		}else{
			addCookie("username", request,(HttpServletResponse) response);
		}
		session.removeAttribute(getLoginNumKeyAttribute());
		session.removeAttribute(getSessionShowCaptchaKeyAttribute());
		session.setAttribute(USER_LOGIN_ID_KEY, token.getPrincipal());
		String uri = ((HttpServletRequest)request).getRequestURI();
		Boolean isBackground = false;
		if(uri.indexOf("/admin")>=0){
			isBackground = true;
		}
		session.setAttribute("isBackground", isBackground);
		hyLogService.createLog((HttpServletRequest)request, null, (String)null,String.format("用户'%s'登录成功", token.getPrincipal()));
		System.out.println("---------------onLoginSuccess end-----------------");
		return super.onLoginSuccess(token, subject, request, response);
	}

	private void addCookie(String key,ServletRequest request,HttpServletResponse response){
		String value = request.getParameter(key);
		 Cookie cookie = new Cookie(key, value);
		 cookie.setMaxAge(365*24*60*60);
		response.addCookie(cookie);
	}
	
	private void removeCookie(String key,ServletRequest request,HttpServletResponse response){
		String value = request.getParameter(key);
		 Cookie cookie = new Cookie(key, value);
		 cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	/**
	 * 重写父类方法，创建一个自定义的{@link UsernamePasswordTokeExtend}
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {

		String username = getUsername(request);
		String password = getPassword(request);
		String host = getHost(request);

		boolean rememberMe = false;
		String rememberMeValue = request.getParameter(getRememberMeParam());
		Integer rememberMeCookieValue = null;
		// 如果提交的rememberMe参数存在值,将rememberMe设置成true
		if (StringUtils.isNotEmpty(rememberMeValue)) {
			rememberMe = true;

			rememberMeCookieValue = NumberUtils.toInt(rememberMeValue);
		}
			
		return new UsernamePasswordTokeExtend(username, password, rememberMe,
				host, rememberMeCookieValue);
	}

	public Long getLockedDuration() {
//		return PreferenceUtils.getLong(PreferenceCode.CODE_LOCKED_DURATION, lockedDuration);
		 return lockedDuration;
	}

	public void setLockedDuration(Long lockedDuration) {
		this.lockedDuration = lockedDuration;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getExcessiveAttemptsPolicy() {
//		return PreferenceUtils.getString(PreferenceCode.CODE_PWD_EXC_ATT_POLICY, excessiveAttemptsPolicy);
		return excessiveAttemptsPolicy;
	}

	public void setExcessiveAttemptsPolicy(String excessiveAttemptsPolicy) {
		this.excessiveAttemptsPolicy = excessiveAttemptsPolicy;
	}

	/**
	 * UsernamePasswordToke扩展，添加一个rememberMeValue字段，获取提交上来的rememberMe值
	 * 根据该rememberMe值去设置Cookie的有效时间。
	 * 
	 * @author vincent
	 * 
	 */
	@SuppressWarnings("serial")
	protected class UsernamePasswordTokeExtend extends UsernamePasswordToken {
		private String sucessUrl;

		// rememberMe cookie的有效时间
		private Integer rememberMeCookieValue;

		public UsernamePasswordTokeExtend() {

		}

		public UsernamePasswordTokeExtend(String username, String password,
				boolean rememberMe, String host, Integer rememberMeCookieValue) {
			super(username, password, rememberMe, host);
			this.rememberMeCookieValue = rememberMeCookieValue;
			this.sucessUrl = getSuccessUrl();
		}

		/**
		 * 获取rememberMe cookie的有效时间
		 * 
		 * @return Integer
		 */
		public Integer getRememberMeCookieValue() {
			return rememberMeCookieValue;
		}

		/**
		 * 设置rememberMe cookie的有效时间
		 * 
		 * @param rememberMeCookieValue
		 *            cookie的有效时间
		 */
		public void setRememberMeCookieValue(Integer rememberMeCookieValue) {
			this.rememberMeCookieValue = rememberMeCookieValue;
		}

		public void setSucessUrl(String sucessUrl) {
			this.sucessUrl = sucessUrl;
		}
	}
	
	/**
	 * 验证LDAP
	 * 
	 * @return
	 */
	protected String checkLDAP (String username, String password) {
		if("true".equals(USE_LDAP)){
			//判断user为内勤or外勤
			User user = userService.findUserByLoginName(username);
			if (user != null) {
				String adminName = "";
				String adminPassword = password;
				adminName = USER_PREFIX+username;
				//adminPassword = "Metlife001";
				//外勤账号密码修改，认证不通过，暂用内勤
				/*LDAP_URL = "ldap://"+"CNSUZDOMV01.alico.corp"+":389";
				adminName = "ALICOCORP\\THE";
				adminPassword = "Metlife12^";*/
				Hashtable env = new Hashtable();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
				env.put(Context.PROVIDER_URL, LDAP_URL);
				env.put(Context.SECURITY_AUTHENTICATION, "simple");
				env.put(Context.SECURITY_PRINCIPAL, adminName);
				env.put(Context.SECURITY_CREDENTIALS, adminPassword);
				try {
					DirContext dc = new InitialDirContext(env);
					//判断该用户本系统是否已被锁定，锁定则提示已被锁定无法登录
					if ("1".equals(user.getLocked())) {
						//已被锁定
						return LDAP_LOCKED;
					} else {
						return LDAP_TRUE;					
					}
				} catch (NamingException e) {
					log.error(e.getMessage());
					return LDAP_FALSE;
				}
			} else {
				//该用户不存在
				return LDAP_NOTEXIST;
			}

		}else{
			return LDAP_TRUE;
		}
	}
}
