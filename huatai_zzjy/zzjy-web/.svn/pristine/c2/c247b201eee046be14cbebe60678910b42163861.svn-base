package com.fairyland.jdp.framework.menu.interceptor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fairyland.jdp.framework.domain.TreeNodeEntity;
import com.fairyland.jdp.framework.menu.service.MenuItemService;
import com.fairyland.jdp.framework.menu.view.MenuModel;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.orm.util.common.StringUtil;

@Component
public class MenuInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory
			.getLogger(MenuInterceptor.class);
	
	@Value("${spring.fairyland.waf.menuExcludePathPatterns}")
	private String  excludePathPatterns;
	
	public String getExcludePathPatterns() {
		return excludePathPatterns;
	}
	@Resource
	private MenuItemService menuItemService;
	@Resource
	private RoleService roleService;
	@Autowired
	public UserService userService;
//	@Autowired
//	private RemindService remindService;
//	@Autowired
//	private SignInService signInService;
//	@Autowired
//	private CustomerService customerService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("---------------MenuInterceptor preHandle start-----------------");
		String currentPath = request.getRequestURI().substring(
				request.getContextPath().length() + 1);
		if (SecurityUtils.getSubject().isAuthenticated()&&SessionContextUtils.getCurrentUser().getIsAdmin() != null
				&& SessionContextUtils.getCurrentUser().getIsAdmin()
				&&currentPath.indexOf("admin")<0&&currentPath.indexOf("fileManager")<0&&currentPath.indexOf("trainingActivity")<0){
			response.sendRedirect(request.getContextPath()+"/admin/desktop");
			return false;
		}
		//System.out.println("---------------MenuInterceptor preHandle start-----------------");
		return true;
	};
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object args, ModelAndView modelAndView)
			throws Exception {
		//System.out.println("---------------MenuInterceptor postHandle start-----------------");
		if (modelAndView != null&&SecurityUtils.getSubject().isAuthenticated()) {
			MenuModel model = menuItemService
					.getMenuModel(TreeNodeEntity.ROOT_PARENT_ID);
			List<MenuModel> list = new ArrayList<MenuModel>(model.getChildren());
			String currentPath = request.getRequestURI().substring(
					request.getContextPath().length() + 1);
			String uri = request.getRequestURI();
			modelAndView.addObject("uri", uri);
			for (int i = 0; i < list.size(); i++) {
				if ("menu".equals(list.get(i).getDisableIcon()) || !list.get(i).getEnabled()) {
					list.remove(i);
					i--;
				} 
			}
			if ("admin".equals(currentPath.split("/")[0])) {
				for (int i = 0; i < list.size(); i++) {
					if (!"admin".equals(list.get(i).getUrl().split("/")[0])) {
						list.remove(i);
						i--;
					}
				}
			} else {
				if (SessionContextUtils.getCurrentUser().getIsAdmin() != null
						&& SessionContextUtils.getCurrentUser().getIsAdmin())
					modelAndView.setViewName("redirect:/admin/desktop");
				for (int i = 0; i < list.size(); i++) {
					if ("admin".equals(list.get(i).getUrl().split("/")[0])) {
						list.remove(i);
						i--;
					}
				}

			}
			modelAndView.addObject("menuItems", list);
			modelAndView.addObject("currentPath", currentPath);
           
			//查询当前登陆人的信息：姓名、头像、分公司、职级
			User user = SessionContextUtils.getCurrentUser();
			modelAndView.addObject("currentUser", user);
		    User user1 = new User();
			user1 = userService.getUser(user.getId());
		    if(StringUtil.isNotBlank(user1.getPicPath())){
		    	modelAndView.addObject("picPath", user1.getPicPath());
		    }else{
		    	//modelAndView.addObject("picPath", "/static/ezt/web/images/defualtHead.png");
		    }
		    
		    if(StringUtil.isNotBlank(user.getUserName())){
		    	modelAndView.addObject("userName", user.getUserName());
		    }else{
		    	modelAndView.addObject("userName", "");
		    }
		    
		    if(StringUtil.isNotNull(user.getOrgGrade())){
		    	modelAndView.addObject("gradeName", user.getOrgGrade());
		    }else{
		    	modelAndView.addObject("gradeName", "");
		    }
		    if(StringUtil.isNotNull(user.getOrgShortName())){
		    	modelAndView.addObject("orgShortName", user.getOrgShortName());
		    }
		  
			//当前日期
			Calendar c = Calendar.getInstance(); 		 	
			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH)+1; 
			int date = c.get(Calendar.DATE);
			int weekNum = c.get(Calendar.DAY_OF_WEEK)-1;
			String weekDay = weekNum == 1 ? "星期一" : weekNum == 2 ? "星期二" : weekNum == 3 ? "星期三" : weekNum == 4 ? "星期四" : weekNum == 5 ? "星期五" : weekNum == 6 ? "星期六" : "星期日";
	        String today = year+"年"+month+"月"+date+"日 "+weekDay;
	        modelAndView.addObject("today", today);
	        
//	        //是否签到
//	        int isSign = 0;
//	        isSign = signInService.isSignIn(user.getAccount());
//	        modelAndView.addObject("isSign", isSign);
//	        
//	        
//	        //计划提醒消息
//	        Map<String, String> planParamMap = new HashMap<String, String>();
//	        Pager<MyPlanAlertInfo> pager = new Pager<MyPlanAlertInfo>();
//	        planParamMap.put("agentCode", user.getAccount());
//			Pager<MyPlanAlertInfo> planAlertList = new Pager<MyPlanAlertInfo>();
//			planAlertList = remindService.getMyPlanAlerts(pager,planParamMap);
//			int planAlertNum = 0;
//			planAlertNum = planAlertList.getTotalCount();
//			modelAndView.addObject("planAlertNum",planAlertNum);
//			
//			//保单周年提醒消息
//			Map<String,Object> policyMap = new HashMap<String, Object>();
//		    Pager<MapPolicyInfo> policypager = new Pager<MapPolicyInfo>();
//		    policyMap.put("agentCode", user.getAccount());
//			Pager<MapPolicyInfo> policyInfo = new Pager<MapPolicyInfo>();
//			policyInfo = customerService.getYearMapPolicyInfo(policypager, policyMap);
//			int policyAlertNum = 0;
//			policyAlertNum = policyInfo.getTotalCount();
//			modelAndView.addObject("policyAlertNum",policyAlertNum);
//			
//			//销售轨迹提醒消息
//			Map<String, String> saleTrackMap = new HashMap<String, String>();
//			saleTrackMap.put("agentCode",user.getAccount());
//			Pager<MyPlanTrackAlertInfo> planTrackPager = new Pager<MyPlanTrackAlertInfo>();
//			Pager<MyPlanTrackAlertInfo> planTrackAlertInfo = new Pager<MyPlanTrackAlertInfo>();
//			planTrackAlertInfo = remindService.getMyPlanTrackAlerts(planTrackPager, saleTrackMap);
//			int planTrackAlertNum = 0;
//			planTrackAlertNum = planTrackAlertInfo.getTotalCount();
//			modelAndView.addObject("planTrackAlertNum",planTrackAlertNum);
//			
//			//特殊纪念日提醒消息
//			Map<String, String> specialDayMap = new HashMap<String, String>();
//			specialDayMap.put("agentCode", user.getAccount());
//			List<MySpecialDayAlertInfo> specialDayInfoList = new ArrayList<MySpecialDayAlertInfo>();
//			specialDayInfoList = remindService.getMySpecialAlerts(specialDayMap);
//			int specialDayAlertNum = 0; 
//			specialDayAlertNum = specialDayInfoList.size();
//			modelAndView.addObject("specialDayAlertNum", specialDayAlertNum);
//			
//			int allAlertNum = 0;
//			//所有提醒数量
//			allAlertNum = planAlertNum + policyAlertNum + planTrackAlertNum + specialDayAlertNum ;
//			//提醒数量
//			//allAlertNum = (planAlertNum==0?0:1) + (policyAlertNum==0?0:1) + (planTrackAlertNum==0?0:1) + (specialDayAlertNum==0?0:1) ;
//			modelAndView.addObject("allAlertNum", allAlertNum);
	        System.out.println("---------------MenuInterceptor postHandle end-----------------");
		}
	}
}
