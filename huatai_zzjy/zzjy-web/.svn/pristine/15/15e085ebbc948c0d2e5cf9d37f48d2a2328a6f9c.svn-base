package com.huatai.web.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huatai.web.model.AccPageInfo;
import com.huatai.web.model.UserBehInfo;
import com.huatai.web.service.UserBehInfoService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @func 用户信息定时处理
 * @user MaxBill
 * @date 2017/11/14 10:03
 * @mail 1370581389@qq.com
 */
@Component
public class UserInfoTimer {

	@Resource
	private UserBehInfoService userInfoService;
	
	private static UserInfoTimer userInfoTimer;

	public UserInfoTimer() {
	}

	@PostConstruct
	public void init() {
		userInfoTimer = this;
		userInfoTimer.userInfoService = this.userInfoService;
	}

	/**
	 * @func 用户信息定时处理
	 * @user MaxBill
	 * @date 2017/11/14 10:03
	 * @mail 1370581389@qq.com
	 */
	@Scheduled(cron = "0 */10 * * * ?")
	public void updateUserInfoTimer() {
		try {
			List<UserBehInfo> userInfoList = userInfoTimer.userInfoService.findUserBehInfoByOnline();
			if (null != userInfoList) {
				Calendar currDate = Calendar.getInstance();
				Calendar userDate = Calendar.getInstance();
				currDate.setTime(new Date());
				long currTime = currDate.getTimeInMillis();
				AccPageInfo acc=new AccPageInfo();
				for (UserBehInfo userInfo : userInfoList) {
					if (null != userInfo.getOutTime()) {
						userDate.setTime(userInfo.getOutTime());
						long userTime = userDate.getTimeInMillis();
						long minute = (currTime - userTime) / 60000;
						if (minute > 30) {
							userInfo.setIsOnline("0");
							this.userInfoService.updateUserInfo(userInfo);
							acc.setBehaviorid(userInfo.getBehaviorid());
							acc.setIsOut(1);
							this.userInfoService.updateAccPageInfoByBehaviorid(acc);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
