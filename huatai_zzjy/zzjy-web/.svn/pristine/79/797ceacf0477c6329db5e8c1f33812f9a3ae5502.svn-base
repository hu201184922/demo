package com.fairyland.jdp.framework.security.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.constant.Constants;
import com.fairyland.jdp.framework.dictionary.view.DictItemModel;
import com.fairyland.jdp.framework.exception.MetLifeCrmException;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.domain.UserRole;
import com.fairyland.jdp.framework.security.service.HashService;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.framework.util.DictionaryUtils;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.google.common.collect.Maps;

/**
 * 管理员管理用户的Controller.
 * 
 */
@Controller
@RequestMapping(value = "/admin/sec/user")
public class UserAdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private HashService hashService;
	
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("id_ASC", "自动");
		sortTypes.put("loginName_ASC", "账户");
		sortTypes.put("name_ASC", "用户名");
		sortTypes.put("registerDate_ASC", "注册日期");
	}

	// public boolean modifyPassword(Long userId,String oldPassword,String
	// newPassword);

	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/userAdmin/index");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "list")
	public Pager<User> getPager(
			@RequestParam(value = "currentPage", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		searchParams.put("ISNULL_roleType", "roleType");
		Page<User> resources = userService.getAllUsers(searchParams,
				pageNumber, pageSize, sortType);
		return ObjectUtil.prasePager(resources);
	}
	
	@RequestMapping("/reportExport")
	public void reportExport(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		searchParams.put("ISNULL_roleType", "roleType");
		Page<User> page = userService.getAllUsers(searchParams,1, Integer.MAX_VALUE, "id_ASC");
		List<User> users = page.getContent();
		for (User user : users) {
			if("M".equals(user.getSex()) || "0".equals(user.getSex())){
				user.setSex("男");
			}else if("F".equals(user.getSex()) || "1".equals(user.getSex())){
				user.setSex("女");
			}
			if("01".equals(user.getAccountstate())){
				user.setAccountstate("在职");
			}else{
				user.setAccountstate("离职");
			}
		}
		String[] headList = new String[]{"账号","姓名","性别","电话","角色","邮箱","描述"};
		String[] dataList = new String[]{"account","userName","sex","phone","roleName","email","description"};
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "add")
	public ModelAndView add(User user,Model model) {
		ModelAndView view = new ModelAndView();
		List<Role> roles = roleService.findAll();
		if(roles != null){
			for (int i = 0; i < roles.size(); i++) {
				if(StringUtil.isNotNull(roles.get(i).getType())){
					roles.remove(i);
					i--;
				}
			}
		}
		List<DictItemModel> list = DictionaryUtils.getDictItems("crm.system.cmg");
		view.addObject("cmg", list);
		view.addObject("rolesList", roles);
		view.addObject("user",user);
		String channel = user.getChannel();
		List<String> channels = new ArrayList<String>();
		if(StringUtil.isNotNull(channel)){
			String[] chaArr = channel.split(",");
			for (String string : chaArr) {
				channels.add(string);
			}
		}
		view.addObject("channels", channels);
		view.setViewName("/admin/user/addUser");
		return view;
	}
	
	@RequestMapping("auth")
	public ModelAndView auth(Long id) {
		ModelAndView view = new ModelAndView();
		List<Role> matchRoles = roleService.matchRoles(id);
		List<Role> roles = roleService.findAll();
		if(roles != null){
			for(int i=0; i<roles.size(); i++){
				if(StringUtil.isNotNull(roles.get(i).getType())){
					roles.remove(i);
					i--;
				}
			}
		}
		User user = userService.getUser(id);
		view.addObject("matchRoles", matchRoles);
		view.addObject("roles", roles);
		view.addObject("user", user);
		view.setViewName("/admin/userAdmin/auth");
		return view;
	}
	

	@RequestMapping(value = "changepsw", method = RequestMethod.GET)
	public String changePswForm(Long id, String currentPath, Model model,String msg) {
		User user = SessionContextUtils.getCurrentUser();
		model.addAttribute("action", "update");
		model.addAttribute("user", user);
		if(currentPath != null && currentPath.indexOf("/admin") == 0){
			model.addAttribute("firstStage", 0);
		}else{
			currentPath = "/index";
			model.addAttribute("firstStage", 1);
		}
		model.addAttribute("rdictCurrentPath", currentPath);
		return "jdp-framework/security/account/change-psw";
	}

	@RequestMapping(value = "changepsw", method = RequestMethod.POST)
	public String changepsw(@Valid @ModelAttribute("user") User user,
			String currentPath,String oldPassword,Model model) {
		Boolean isBackground = (Boolean)SecurityUtils.getSubject().getSession().getAttribute("isBackground");
		if(isBackground!=null && isBackground==true){
			currentPath = "/admin/desktop";
		}
		Hash hash=hashService.computeHash(oldPassword, user.getSalt());
		if(user.getPassword().equals(hash.toHex())){
			try {
				userService.updateFirstLoginPsw(user);
			} catch (Exception e) {
				model.addAttribute("msg", e.getMessage());
				model.addAttribute("rdictCurrentPath", currentPath);
				model.addAttribute("user", user);
				model.addAttribute("action", "update");
				return  "jdp-framework/security/account/change-psw";
			}
		}else{
			model.addAttribute("msg", "旧密码错误");
			model.addAttribute("rdictCurrentPath", currentPath);
			model.addAttribute("user", user);
			model.addAttribute("action", "update");
			return  "jdp-framework/security/account/change-psw";
		}
		return "redirect:"+currentPath;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		List<Role> roles = roleService.findAll();
		User user = userService.getUser(id);
		model.addAttribute("matchRoles", getMatchRole(user.getUserRoles()));
		model.addAttribute("roles", roles);
		model.addAttribute("action", "update");
		model.addAttribute("user", userService.getUser(id));
		return "jdp-framework/security/account/user-edit";
	}

	private String getMatchRole(Set<UserRole> roles) {
		if (roles == null) {
			return "";
		}
		List<Long> roleIds = new ArrayList<Long>();
		for (UserRole userRole : roles) {
			roleIds.add(userRole.getRole().getId());
		}
		return StringUtils.join(roleIds, ",");
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user,
			RedirectAttributes redirectAttributes, Long personId) {

		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("message",
				"更新用户" + user.getAccount() + "成功");
		return "redirect:/admin/sec/user";
	}

	// @RequestMapping(value = "delete/{id}")
	// public String delete(@PathVariable("id") Long id, RedirectAttributes
	// redirectAttributes) {
	// User user = userService.getUser(id);
	// userService.deleteUser(id);
	// redirectAttributes.addFlashAttribute("message", "删除用户" +
	// user.getLoginName() + "成功");
	// return "redirect:/admin/sec/user";
	// }

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id.longValue() != -1) {
			model.addAttribute("user", userService.getUser(id));
		}
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		User user = new User();
		model.addAttribute("action", "create");
		model.addAttribute("user", user);
		return "jdp-framework/security/account/user-register";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("user") User user,
			@RequestParam("account") String account,
			RedirectAttributes redirectAttributes) {
		if (userService.findUserByLoginName(account) == null) {
			user.setPlainPassword("111111");
			userService.registerUser(user);
			redirectAttributes.addFlashAttribute("message", "创建成功");
		} else {
			redirectAttributes.addFlashAttribute("message", "创建失败");
		}
		return "redirect:/admin/sec/user/create";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		String[] userIds = id.split(",");
		for (String userId : userIds) {
			userService.deleteUser(Long.parseLong(userId));
		}
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/sec/user";
	}

	@RequestMapping(value = "check", method = RequestMethod.POST)
	@ResponseBody
	public String check(String account, String initalname) {
		if (initalname != null && initalname.equals(account)) {
			return "true";
		}
		User user = userService.findUserByLoginName(account);
		if (user == null) {
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "repassword/{id}", method = RequestMethod.GET)
	public String rePassword(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		User user = null;
		String[] userIds = id.split(",");
		for (String userId : userIds) {
			user = userService.getUser(Long.parseLong(userId));
			user.setPswChangeDate(new Date());
			user.setPlainPassword("111111");
			userService.updateUser(user);
		}
		redirectAttributes.addFlashAttribute("message", "重置成功");
		return "redirect:/admin/sec/user";
	}

	@RequestMapping(value = "modifypwd/{id}", method = RequestMethod.GET)
	public String modifyPwd(@PathVariable("id") Long id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", userService.getUser(id));
		model.addAttribute("action", "modifypwd");
		return "jdp-framework/security/account/user-pwd-modify";
	}

	@RequestMapping(value = "modifypwd", method = RequestMethod.POST)
	public String modifyPass(@Valid User user,
			@RequestParam("password") String password,
			RedirectAttributes redirectAttributes) {
		user.setPswChangeDate(new Date());
		user.setPlainPassword(password);
		// user.setPassword(password);
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/admin/sec/user";
	}

	@RequestMapping(value = "modifypassword", method = RequestMethod.POST)
	public String modifyPassword(String plainPassword, String password,
			RedirectAttributes redirectAttributes) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if (user == null) {
			redirectAttributes.addFlashAttribute("message", "请先登录系统");
		} else {
			user.setPlainPassword(plainPassword);
			user.setPassword(password);
			user.setPswChangeDate(new Date());
			userService.updateUser(user);
			redirectAttributes.addFlashAttribute("message", "修改成功，请先登录系统");
		}
		return "redirect:/admin/logout";
	}

	@RequestMapping(value = "user/single", method = RequestMethod.GET)
	public String orgSelectOne(Model model) {
		return "/jdp-framework/general/user/user-single";
	}

	@RequestMapping(value = "user/multi", method = RequestMethod.GET)
	public String orgSelectMulti(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			@RequestParam(value = "pageDatas", defaultValue = "") String pageDatas,
			ServletRequest request, Model model) {
		// List<User> users = userService.getAllUser();
		// model.addAttribute("userList", users);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		model.addAttribute("users", userService.getAllUsers(searchParams,
				pageNumber, pageSize, sortType));
		model.addAttribute("pageDatas", pageDatas);
		model.addAttribute("sortTypes", sortTypes);
		return "/jdp-framework/general/user/user-multi";
	}

	@RequestMapping(value = "setExpired/{id}", method = RequestMethod.GET)
	public String setExpired(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		User user = null;
		String[] userIds = id.split(",");
		for (String userId : userIds) {
			user = userService.getUser(Long.parseLong(userId));
			user.setExpiredTime(new Date());
			user.setExpired(true);
		}
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("message", "设置成功");
		return "redirect:/admin/sec/user";
	}

	@RequestMapping(value = "cancelExpired/{id}", method = RequestMethod.GET)
	public String cancelExpired(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		User user = null;
		String[] userIds = id.split(",");
		for (String userId : userIds) {
			user = userService.getUser(Long.parseLong(userId));
			user.setExpiredTime(null);
			user.setExpired(false);
		}
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("message", "成功取消");
		return "redirect:/admin/sec/user";
	}

	@RequestMapping(value = "ifLocked/{id}", method = RequestMethod.GET)
	public String ifLocked(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		User user = null;
		String[] userIds = id.split(",");
		for (String userId : userIds) {
			user = userService.getUser(Long.parseLong(userId));
			Boolean locked = user.getLocked();
			if (locked == null || user.getLocked() == false) {
				user.setLockedTime(new Date());
				user.setLocked(true);
			} else {
				user.setLockedTime(null);
				user.setLocked(false);
			}
		}
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("message", "操作成功");
		return "redirect:/admin/sec/user";
	}

	@RequestMapping(value = "selectRole/{id}", method = RequestMethod.GET)
	public String selectRole(@PathVariable("id") Long id, Model model) {
		List<Role> roles = roleService.findAll();
		User user = userService.getUser(id);
		List<Role> matchRoles = roleService.matchRoles(id);
		List<Role> unmatchRoles = roleService.unmatchRoles(id);
		model.addAttribute("roles", roles);
		model.addAttribute("user", userService.getUser(id));
		model.addAttribute("action", "selectRole");
		model.addAttribute("matchRoles", matchRoles);
		model.addAttribute("unmatchRoles", unmatchRoles);
		return "jdp-framework/security/account/user-role-edit";
	}

	@RequestMapping(value = "selectRole", method = RequestMethod.POST)
	public String selectRole(@RequestParam("userId") String userId,
			@RequestParam("roles_id") String roles_id,
			RedirectAttributes redirectAttributes) {
		User user = userService.getUser(Long.parseLong(userId));
		userService.updateUser(user, roles_id);
		redirectAttributes.addFlashAttribute("message", "分配成功");
		return "redirect:/admin/sec/user";
	}
}
