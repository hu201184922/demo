package com.xinwei.control;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.authority.model.Data;
import com.authority.model.UserInfo;
import com.xinwei.common.FileLoadUtils;
import com.xinwei.ms2.SM2Utils;
import com.xinwei.ms2.Util;
import com.xinwei.service.ShiroRoleService;
import com.xinwei.service.ShiroUserService;
import com.xinwei.shiro.MyShiroRealm;
import com.xinwei.spring.boot.autoconfigure.shiro.ShiroProperties;
import com.xinwei.utils.ResponseJson;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ApiController {

	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	private static final Logger log = LoggerFactory.getLogger(ApiController.class);
	 
	@Autowired
	MyShiroRealm myShiroRealm;
	
	/*@Autowired
	UserService userService;*/

	@Autowired
	ShiroUserService shiroUserService;
	 
	@Autowired
	ShiroRoleService shiroRoleService;

	@Autowired
	private ShiroProperties shiroProperties;
	
 
	@RequestMapping(value = "/doLogin", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String doLogin(@RequestBody String jsonObject,RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		UserInfo userInfo = JSONObject.parseObject(jsonObject,UserInfo.class);
		Data dataInfo = JSONObject.parseObject(userInfo.getData(),Data.class);
		String userCode = dataInfo.getUserCode();
		String prik = "008D56FF9B0DB66334FC2C3D840BA6A4F25246BC81C17720F8C1F3FAE446FBE3DB";
		String passWord = null;
		try {
			passWord = new String(SM2Utils.decrypt(Util.hexToByte(prik),Util.hexToByte(dataInfo.getPassWord())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ResponseJson responseJson = new ResponseJson();
		UsernamePasswordToken token = new UsernamePasswordToken(userCode, passWord.toString(),userInfo.getIp());
		// token.setRememberMe(true);
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();

		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + userCode + "]进行登录验证..验证开始");
			currentUser.login(token);
			 
			logger.info("对用户[" + userCode + "]进行登录验证..验证通过");
			
			//begin 添加记录日志功能
			String logOperatorName = currentUser.getPrincipal().toString();
			//end   添加记录日志功能
			
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + userCode + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addFlashAttribute("message", "账户不存在");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + userCode + "]进行登录验证..验证未通过,错误的凭证");
			redirectAttributes.addFlashAttribute("message", "密码不正确");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + userCode + "]进行登录验证..验证未通过,账户已锁定");
			redirectAttributes.addFlashAttribute("message", "账户已锁定");
		} catch (DisabledAccountException lae) {
			logger.info("对用户[" + userCode + "]进行登录验证..验证未通过,账户未审核");
			redirectAttributes.addFlashAttribute("message", "账户未审核");
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + userCode + "]进行登录验证..验证未通过,错误次数过多");
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + userCode + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			logger.info("用户[" + userCode + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			String toKen = currentUser.getSession().getId().toString();
			System.out.println("===================================   "+toKen);
			SavedRequest re = WebUtils.getAndClearSavedRequest(request);
			responseJson.setMsg("success");
			//return JSONObject.toJSONString(responseJson);
			
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {// 以此取出头信息
				String headerName = (String) headerNames.nextElement();
				String headerValue = request.getHeader(headerName);// 取出头信息内容
				System.out.println("key:"+headerName);
				System.out.println("headerValue:"+headerValue);
			}
			String data = "";
			FileLoadUtils fileLoadUtils;
			try {
				fileLoadUtils = new FileLoadUtils();
				data = fileLoadUtils.getString("login.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return data;
		} else {
			token.clear();
			Map<String, String> map = (Map) redirectAttributes.getFlashAttributes();
			String reason = map.get("message");

			responseJson.setMsg("false");
			responseJson.setReturnStr(reason);
			return JSONObject.toJSONString(responseJson);// "{\"success\":true,\"msg\":false,\"returnStr\":\""+reason+"\"}"
															// ;
		}
	}

	@RequestMapping(value = "/doRegist")
	public String regist() {
		
		return "regist";
	}

}