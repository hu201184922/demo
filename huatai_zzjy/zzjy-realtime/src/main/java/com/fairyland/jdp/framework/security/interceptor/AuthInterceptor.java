package com.fairyland.jdp.framework.security.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.AuthInfo;
import com.fairyland.jdp.framework.security.domain.JsonResult;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.service.CacheService;
import com.fairyland.jdp.framework.security.service.ResourceService;
import com.fairyland.jdp.framework.security.service.TokenService;
import com.fairyland.jdp.framework.security.service.UserAuthService;
import com.fairyland.jdp.framework.util.DateUtil;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
//	@Autowired
//	private CacheService cacheService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserAuthService userAuthService;
	@Autowired
	private ResourceService resourceService;
	
	@Value("${spring.fairyland.auth.expire}")
	private Integer expire;
	
	@Value("${spring.fairyland.auth.path}")
	private String pathPattern;
	
	private static final String ResourceCacheKey = "ResourceCacheKey";
	
	private static ThreadLocal<AuthInfo> authInfoThreadLocal = new ThreadLocal<AuthInfo>(); 
	
	public String getPathPattern() {
		return pathPattern;
	}
	public void setPathPattern(String pathPattern) {
		this.pathPattern = pathPattern;
	}
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {
			boolean isLogin = checkLogin(request,response);
			if(!isLogin)
				return false;
			boolean isPermitted = checkPermission(request,response);
			if(!isPermitted)
				return false;
		} finally {
			authInfoThreadLocal.remove();
		}
		return true;
	}
	private boolean checkLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String accessToken = SessionContextUtils.getAccessToken();
		boolean success = true;
//		if(!userAuthService.verifyToken(accessToken))
//			success = false;
//		if(success){
			AuthInfo authInfo = tokenService.getAuthInfo(accessToken);
			if(authInfo == null)
				success = false;
			else{
				authInfoThreadLocal.set(authInfo);
				authInfo.setExpireTime(DateUtil.getOffsetTime(expire));
				tokenService.setAuthInfo(accessToken, authInfo);
			}
//		}
		if(!success){
			JsonResult json = new JsonResult();
			json.setSuccess(0);
//			json.setErrorCode("redirect");
			json.setErrorCode("needlogin");
//			json.setErrorMsg(PropsUtil.get("spring.fairyland.shiro.loginUrl"));
			output(response,JSON.toJSONString(json));
			return false;
		}
		return true;
	}
	private boolean checkPermission(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String requestUri = request.getRequestURI();
//		String accessToken = AuthUtil.getAccessToken(request);
		AuthInfo authInfo = authInfoThreadLocal.get();
//		User user = authInfo.getUser();
		if(authInfo.getIsAdmin()!=null && authInfo.getIsAdmin()==1){
			return true;
		}
		Set<String> resourceStrings = authInfo.getResourceStrings();
//		Map<String,String> resourceMap = getResourceMap();
//		String perString = resourceMap.get(requestUri);
		if(resourceStrings != null && resourceStrings.contains(requestUri))
			return true;
		
		JsonResult json = new JsonResult();
		json.setSuccess(0);
		json.setErrorCode("unauthorized");
		json.setErrorMsg("未授权");
		output(response,JSON.toJSONString(json));
		return false;
	}
	private void output(HttpServletResponse response,String value) throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(value);
		writer.close();
	}
//	private Map<String,String> getResourceMap(){
//		Object obj = cacheService.get(ResourceCacheKey);
//		if(obj!=null)
//			return (Map<String,String>)obj;
//		else{
//			List<Resource> list = resourceService.findAllResource();
//			Map<String,String> resourceMap = new HashMap<String,String>();
//			for (Resource resource : list) {
//				resourceMap.put(resource.getResString(), resource.getPerString());
//			}
//			cacheService.set(ResourceCacheKey, resourceMap);
//			return resourceMap;
//		}
//	}
	public static void main(String[] args) {
		
	}
}
