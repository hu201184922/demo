package com.fairyland.jdp.framework.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.constant.Constants;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.exception.MetLifeCrmException;
import com.fairyland.jdp.framework.organizational.agent.service.OrgRoleService;
import com.fairyland.jdp.framework.organizational.city.service.CityService;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.ObjectUtil;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrgRoleService orgRoleService;
	@Autowired
	private CityService cityService;
	@Autowired
	private DictionaryService dictionaryService;
//	@Autowired
//	private WechatService wechatService;
//	@Autowired
//	private CompTaskService compTaskService;

	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView();
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("IN_type", "00,01");
		Page<Role> rolesPage = roleService.getAllRoles(searchParams, 1, 2000, "id_ASC");
		List<Role> roles = rolesPage.getContent();
		view.addObject("searchRoles", roles);
		view.setViewName("/admin/user/index");
		return view;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "list")
	public Pager<User> getPager(
			@RequestParam(value = "currentPage", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE_10) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "id_ASC") String sortType,
			ServletRequest request) {
		//获取当前用户分公司CODE
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");		
		
		
		Page<User> resources = userService.getAllUsers(searchParams,pageNumber, pageSize, sortType);
		return ObjectUtil.prasePager(resources);
	}

	@RequestMapping(method = RequestMethod.POST, value = "add")
	public ModelAndView add(User user, Model model) {
		ModelAndView view = new ModelAndView();
		view.addObject("user", user);
//		String channel = user.getChannel();
//		List<String> channels = new ArrayList<String>();
//		if (StringUtil.isNotNull(channel)) {
//			String[] chaArr = channel.split(",");
//			for (String string : chaArr) {
//				channels.add(string);
//			}
//		}
		/* 获取内勤  */
//		List<OrgRole> roleList= orgRoleService.getOfficeRoles();
//		view.addObject("officeRoles", roleList);
//		view.addObject("channels", channels);
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Role> rolesPage = roleService.getAllRoles(searchParams, 1, 2000, "id_ASC");
		List<Role> roles = rolesPage.getContent();
		view.addObject("searchRoles", roles);
		view.setViewName("/admin/user/addUser");
		return view;
	}
	
	@RequestMapping(value = "getOrgInfo")
	@ResponseBody
	public List<DictionaryItem> getOrgInfo(){
		List<DictionaryItem> orgInfo = dictionaryService.findByDictionaryCodeOrderByCode("DETP_DICT");
		return orgInfo;
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public User save(@Valid @ModelAttribute("user") User user, String roleId,String[] deptCodes) {
		String account=user.getAccount().trim();
		String deptCode = "";
		if(deptCodes!=null && deptCodes.length>0){
			for (String str : deptCodes) {
				if(deptCode!=null && deptCode.length()>1){
					deptCode+=",";
				}
				deptCode+=str;
			}
		}
		user.setDeptCode(deptCode);
		user.setAccount(account);
		//处理职级与权限对应关系
//		String[] grades={"SA","CAO","GM","AH","DM","AM","SM","LP"};
//		int rid=user.getRoleId().intValue();
//		if(rid>0 && rid<=7){
//			user.setOrgGrade(grades[rid]);
//			if(rid>4){
//				user.setIsType("02");	//外勤
//			}else{
//				user.setIsType("01");	//内勤
//			}
//		}else {
//			user.setOrgGrade("SA");
//			user.setIsType("00");	//管理 
//		}
		
		//应增加校验机制 来校验城市没被其他同级用户使用
//		if ("03".equals(user.getAccountstate()) && !user.getExpired()) {
//			user.setExpired(true);
//			user.setExpiredTime(new Date());
//		} else if ("01".equals(user.getAccountstate())) {
//			user.setExpired(false);
//			user.setExpiredTime(null);
//		}
		if (user.getId() != null) {
			userService.updateUser(user);
		} else {
			User usertmp = userService.findUserByLoginName(account);
			if (usertmp != null) {
				throw new MetLifeCrmException("已存在该用户");
			}
			// 新增account 去空
			user.setAccount(account);
			userService.registerUser(user);
		}
		userService.updateUser(user, roleId);
		//微信端注册用户
//		String domain = compTaskService.getWechatDomain();
//		String msg=addWechatUser(user,domain);
//		if (!"".equals(msg)) {
//			log.debug(msg);
//		}
		return user;
	}
	
//	/**
//	 * 微信端注册用户
//	 * @param user
//	 * @param domain
//	 * @return
//	 */
//	private String addWechatUser(User user,String domain){
//		String resultStr="";
//		List<String> orgCode=Arrays.asList( user.getOrgCode().split(",") );
//		List<String> departments=new ArrayList<String>();
//		String[] ds=null;
//		List<Department> departs=wechatService.departmentList(domain, null);
//		if (departs!=null&&departs.size()>0) {
//			for(Department item:departs){
//				if(orgCode.contains(item.getName())){
//					departments.add(item.getId());
//				}
//			}
//		}
//		ds=departments.toArray(new String[departments.size()]);
//		log.debug(user.getOrgCode()+"====>{"+Arrays.toString(ds)+"}");
//		boolean hasUser=false;  //存在此记录
//		JSONObject obj=wechatService.getUserInfo(user.getAccount(), domain);
//		hasUser = ( obj!=null && "0".equals(obj.getString("errcode")) );
//		WcUser wcUser=new WcUser();
//		wcUser.setUserid(user.getAccount());
//		wcUser.setName(user.getUserName());
//		wcUser.setEmail(user.getEmail());
//		wcUser.setMobile(user.getPhone());
//		wcUser.setDepartment(ds);
//		wcUser.setGender("0".equals(user.getSex())?1:2);//[0男 1女]--> 【1表示男性，2表示女性】
//		Result r=null;
//		if(hasUser){
//			r=wechatService.updateWcUser(wcUser, domain);
//		}else{
//			r=wechatService.createWcUser(wcUser, domain);
//		}
//		if(r.getStatus()!=200){
//			resultStr = r.getStatus() +":"+ r.getMessage();
//		}
//		return resultStr;
//	}
	

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "delete")
	public Boolean del(Long id) {
		userService.deleteUser(id);
		return true;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "resetPsw")
	public String resetPsw(Long id) {
		return userService.resetPswUser(id);
	}

	@RequestMapping("auth")
	public ModelAndView auth(Long id) {
		ModelAndView view = new ModelAndView();
		List<Role> matchRoles = roleService.matchRoles(id);
		List<Role> roles = roleService.findAll();
		// if(roles != null){
		// for(int i=0; i < roles.size(); i++){
		// if(StringUtil.nullOrSpace(roles.get(i).getType())){
		// roles.remove(i);
		// i--;
		// }
		// }
		// }
		User user = userService.getUser(id);
		view.addObject("matchRoles", matchRoles);
		view.addObject("roles", roles);
		view.addObject("user", user);
		return view;
	}

	@RequestMapping("updateauth")
	public void updateauth(Long id, String[] roleIds) {
		String roleId = "";
		for (String str : roleIds) {
			roleId += str + ",";
		}
		User user = userService.getUser(id);
		roleId = roleId.substring(0, roleId.length() - 1);
		userService.updateUser(user, roleId);
	}

	@ModelAttribute
	public void getUser(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id.longValue() != -1) {
			model.addAttribute("user", userService.getUser(id));
		}
	}

	@ResponseBody
	@RequestMapping("changeEnable")
	public String changeEnable(String account, Boolean enable) {
		User user = userService.findUserByLoginName(account.trim());
		if (user != null) {
			user.setEnabled(enable);
			if (user.getEnabled() != null && user.getEnabled()) {
				user.setLoginNum(0L);
				user.setErrorTryTime(null);
			}
			userService.updateUser(user);
			return user.getEnabled().toString();
		}
		return "";
	}

	@RequestMapping(value = "modifypsw", method = RequestMethod.GET)
	public ModelAndView modifypsw(String account) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user/modifypsw");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "modifypsw", method = RequestMethod.POST)
	public Boolean modifyPsw(Long id, String newPassword, String oldPassword) {
		return userService.modifyPassword(id, oldPassword, newPassword);
	}

}
