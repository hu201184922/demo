package com.fairyland.jdp.framework.onlineuser.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.onlineuser.service.OnlineUserService;
import com.fairyland.jdp.framework.onlineuser.view.OnlineUserModel;
import com.fairyland.jdp.framework.security.Constants;
import com.fairyland.jdp.framework.security.service.UserService;

@Controller
@RequestMapping(value = "/admin/onlineuser")
public class OnlineUserController {

	@Resource
	private OnlineUserService onlineUserService;
	@Resource
	private DefaultWebSessionManager sessionManager;
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		List<OnlineUserModel> onlineUsers = onlineUserService.getOnlineUsers();
		model.addAttribute("onlineUsers", onlineUsers);
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "jdp-framework/onlineuser/onlineuser-list";
	}

	@RequestMapping(value = "forcelogout/{sessionId}", method = RequestMethod.GET)
	public String update(RedirectAttributes redirectAttributes,
			@PathVariable("sessionId") String sessionId) {
		Session forcelogoutSession=sessionManager.getSession(new DefaultSessionKey(sessionId));
		forcelogoutSession.setAttribute(Constants.FORCE_LOGOUT_KEY, true);
		return "redirect:/admin/onlineuser";
	}

}
