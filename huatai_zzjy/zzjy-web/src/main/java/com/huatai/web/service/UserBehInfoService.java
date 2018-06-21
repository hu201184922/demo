package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.bean.ChartBean;
import com.huatai.web.bean.ModuleBean;
import com.huatai.web.bean.UserBehBean;
import com.huatai.web.bean.UserUseAnBean;
import com.huatai.web.model.AccPageInfo;
import com.huatai.web.model.UserBehInfo;
import com.huatai.web.model.Version;

public interface UserBehInfoService {

	Pager<UserBehBean> findUserBehInfoByPage(Pager<UserBehBean> pager, UserBehBean record);

	List<UserBehInfo> findAll();

	Pager<UserUseAnBean> findByPager(Pager<UserUseAnBean> pager, UserUseAnBean record);

	UserBehInfo findAccUser(String dateDim);

	UserBehInfo findErrRate(String dateDim);

	List<ChartBean> findMinalterType(String dateDim);

	List<AccPageInfo> findAccAll(Pager<UserBehBean> pager, UserBehBean record, String orgs, String userId,
			String module, String isNode, String datetime1, String datetime2);

	UserBehInfo findByBehaviorId(String id);

	AccPageInfo findModuRat(String dateDim);

	List<String> findfmNameAll();

	List<String> findNodeByFmName(String data);

	Pager<ModuleBean> findFunanList(Pager<ModuleBean> pager, ModuleBean record, String funDate);

	Integer findActUser(String dateDim);

	Integer findLifeUser(String dateDim);

	Integer findLogUser(String dateDim);

	Integer findRegUser(String dateDim);

	ChartBean getAccUserInfo(String dateDim,String type);

	ChartBean getHieUserInfo(String dateDim);

	Pager<UserBehInfo> findErrList(Pager<UserBehInfo> pager, UserBehBean record, String date);

	String findErrNum(String dateDim);

	String findErrNumDay();

	String findErrNumWeek();

	List<Integer> getDatePerInfo(String dateDim);

	ChartBean getHieErrInfo(String dateDim);

	int findOnlinePeople();

	List<Version> getVersionList(String type);

	int findLAccUser(String dateDim);

	int findLErrRate(String dateDim);

	List<UserBehInfo> findUserBehInfoByOnline();

	int updateUserInfo(UserBehInfo userBehInfo);

	List<UserBehInfo> findDateRegionByYear();

	List<UserBehInfo> findDateRegionByMonth();

	List<UserBehInfo> findDateRegionByWeek();

	ChartBean findLMinalterType(String dateDim, String type);

	Integer findCallPage(String behaviorid);

	int updateAccPageInfoByBehaviorid(AccPageInfo acc);

	com.fairyland.jdp.orm.Pager<UserBehBean> findAccPager(Pager<UserBehBean> pager, UserBehBean record, String orgs,
			String userId, String module, String isNode, String datetime1, String datetime2);

	List<ModuleBean> findFunanAll(Pager<ModuleBean> funpager, ModuleBean funrecord, String dateDim);

	int findLAccUserMonth(String dateDim);

	ChartBean findLMinalterTypeMonth(String dateDim, String maxName);

	int findLAccUserWeek(String dateDim);
}
