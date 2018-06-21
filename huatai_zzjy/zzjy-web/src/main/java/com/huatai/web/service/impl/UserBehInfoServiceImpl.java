package com.huatai.web.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.ChartBean;
import com.huatai.web.bean.ChartData;
import com.huatai.web.bean.FunAnListTotalBean;
import com.huatai.web.bean.ModuleBean;
import com.huatai.web.bean.UserBehBean;
import com.huatai.web.bean.UserUseAnBean;
import com.huatai.web.mapper.AccPageInfoMapper;
import com.huatai.web.mapper.UserBehInfoMapper;
import com.huatai.web.mapper.VersionMapper;
import com.huatai.web.model.AccPageInfo;
import com.huatai.web.model.UserBehInfo;
import com.huatai.web.model.UserBehInfoExample;
import com.huatai.web.model.Version;
import com.huatai.web.service.UserBehInfoService;

@Service
public class UserBehInfoServiceImpl implements UserBehInfoService {

	@Autowired
	public UserBehInfoMapper userBehInfoMapper;

	@Autowired
	public AccPageInfoMapper accPageInfoMapper;

	@Autowired
	public VersionMapper versionMapper;

	@Override
	public Pager<UserBehBean> findUserBehInfoByPage(Pager<UserBehBean> pager, UserBehBean record) {
		return this.userBehInfoMapper.findPager(pager, record);
	}

	@Override
	public List<UserBehInfo> findAll() {
		return this.userBehInfoMapper.findAll();
	}

	@Override
	public Pager<UserUseAnBean> findByPager(Pager<UserUseAnBean> pager, UserUseAnBean record) {
		Pager<UserUseAnBean> result = new Pager<UserUseAnBean>();
		List<UserUseAnBean> list = new ArrayList();
		int day = 24 * 60 * 60 * 1000;
		int h = 60 * 60 * 1000;
		Pager<UserBehInfo> page = this.userBehInfoMapper.findByPager(pager, record);
		if (page.getPageItems() != null) {
			for (UserBehInfo u : page.getPageItems()) {
				long useData = (u.getOutTime().getTime() - u.getLoginTimeNow().getTime());
				UserUseAnBean user = new UserUseAnBean();
				user.setUserName(u.getUserName());
				user.setTerminalType(u.getTerminalType());
				long sum = u.getOutTime().getTime() - u.getLoginTimeNow().getTime();
				if ((sum) / h >= 1) {
					if ((sum) / h >= 24) {
						user.setUseTime(
								useData / h / 24 + "day" + useData / h % 24 + "h" + useData % h / (60 * 1000) + "min");
					} else {
						user.setUseTime(useData / h + "h" + useData % h / (60 * 1000) + "min");
					}
				} else if (useData / h < 1 && useData / (60 * 1000) > 1) {
					user.setUseTime(useData / (60 * 1000) + "min");
				} else {
					user.setUseTime((useData / (60 * 1000) > 0 ? useData / (60 * 1000)
							: (u.getLoginTimeNow().getTime() - u.getOutTime().getTime()) / (60 * 1000)) + "min");
				}
				user.setUseGapTime(((u.getLoginTimeNow().getTime() - u.getLoginTimeLast().getTime()) / day < 0
						? (u.getLoginTimeLast().getTime() - u.getLoginTimeNow().getTime()) / day
						: (u.getLoginTimeNow().getTime() - u.getLoginTimeLast().getTime()) / day) + "");
				// user.setCallPage(this.userBehInfoMapper.findCountPage(u.getBehaviorid())
				// +
				// "");
				user.setVersion(u.getVersion());
				Integer count = this.userBehInfoMapper.findCallPage(u.getBehaviorid());
				user.setCallPage(count.toString());
				list.add(user);
			}
		}
		result.setPageItems(list);
		result.setCurrentPage(page.getCurrentPage());
		result.setTotalCount(page.getTotalCount());
		return result;
	}

	@Override
	public UserBehInfo findAccUser(String dateDim) {
		UserBehInfo findAddUser = this.userBehInfoMapper.findAccUser(dateDim);
		return findAddUser;
	}

	@Override
	public UserBehInfo findErrRate(String dateDim) {
		UserBehInfo findErrRate = this.userBehInfoMapper.findErrRate(dateDim);
		UserBehInfo user = new UserBehInfo();
		if (findErrRate != null) {
			user.setRatio(findErrRate.getRatio());
		}
		return user;
	}

	@Override
	public List<ChartBean> findMinalterType(String dateDim) {
		return this.userBehInfoMapper.findMinalterType(dateDim);
	}

	@Override
	public UserBehInfo findByBehaviorId(String id) {
		return this.userBehInfoMapper.findByBehaviorId(id);
	}

	@Override
	public List<AccPageInfo> findAccAll(Pager<UserBehBean> pager, UserBehBean record, String orgs, String userId,
			String module, String isNode, String datetime1, String datetime2) {
		return this.accPageInfoMapper.findAll(pager, record, orgs, userId, module, isNode, datetime1, datetime2);
	}

	@Override
	public Pager<ModuleBean> findFunanList(Pager<ModuleBean> pager, ModuleBean record, String funDate) {
		pager = this.accPageInfoMapper.findFunanList(pager, record, funDate);
		DecimalFormat df = new DecimalFormat("######0.00");
		try {
			FunAnListTotalBean totals = accPageInfoMapper.findTotals(funDate);
			List<ModuleBean> list = pager.getPageItems();
			if (list.size() > 0) {
				for (ModuleBean moduleBean : list) {
					// 访问次数&占比
					if (StringUtil.isNull(totals.getTotal1()) || StringUtil.isNull(moduleBean.getVisitNum())) {
						moduleBean.setVisitNum(0);
						moduleBean.setVnRatio(0.0);
					} else {
						BigDecimal bd = new BigDecimal(moduleBean.getVisitNum() / totals.getTotal1() * 100);
						bd = bd.setScale(2, RoundingMode.HALF_UP);
						moduleBean.setVnRatio(
								Double.parseDouble(df.format(moduleBean.getVisitNum() / totals.getTotal1() * 100)));

					}
					// 访问人数&占比
					if (StringUtil.isNull(totals.getTotal2()) || StringUtil.isNull(moduleBean.getVisitPeo())) {
						moduleBean.setVisitPeo(0);
						moduleBean.setVpRatio(0.0);
					} else {
						moduleBean.setVpRatio(
								Double.parseDouble(df.format(moduleBean.getVisitPeo() / totals.getTotal2() * 100)));
					}
					// 停留时间&占比
					if (StringUtil.isNull(totals.getTotal3()) || StringUtil.isNull(moduleBean.getStayTime())) {
						moduleBean.setStayTime(0.0);
						moduleBean.setStRatio(0.0);
					} else {
						double stayTime = Double.parseDouble(df.format(moduleBean.getStayTime()));
						double stRatio = Double
								.parseDouble(df.format((moduleBean.getStayTime() / totals.getTotal3()) * 100));
						if (stayTime >= 0) {
							moduleBean.setStayTime(stayTime);
							moduleBean.setStRatio(stRatio);
						} else {
							moduleBean.setStayTime(stayTime * -1);
							moduleBean.setStRatio(stRatio * -1);
						}
					}
					// 跳出率
					if (StringUtil.isNull(totals.getTotal4()) || StringUtil.isNull(moduleBean.getOutRatio())) {
						moduleBean.setOutRatio(0.0);
					} else {
						moduleBean.setOutRatio(
								Double.parseDouble(df.format(moduleBean.getOutRatio() / totals.getTotal4() * 100)));
					}
					// 转化率
					if (StringUtil.isNull(totals.getTotal5()) || StringUtil.isNull(moduleBean.getConvertRatio())) {
						moduleBean.setConvertRatio(0.0);
					} else {
						moduleBean.setConvertRatio(
								Double.parseDouble(df.format(moduleBean.getConvertRatio() / totals.getTotal5() * 100)));
					}
				}
			}
			pager.setPageItems(list);
		} catch (Exception e) {
			e.printStackTrace(System.out.printf("============findFunanList方法错误================="));
		}
		return pager;
	}

	@Override
	public List<String> findfmNameAll() {
		return this.accPageInfoMapper.findfmNameAll();
	}

	@Override
	public List<String> findNodeByFmName(String data) {
		return this.accPageInfoMapper.findNodeByFmName(data);
	}

	@Override
	public AccPageInfo findModuRat(String dateDim) {
		AccPageInfo acc = new AccPageInfo();
		List<AccPageInfo> findModuRat = this.userBehInfoMapper.findModuRat(dateDim);
		if (findModuRat.size() > 0) {
			Double tem = findModuRat.get(0).getRatio();
			for (AccPageInfo accPageInfo : findModuRat) {
				Double ratio = accPageInfo.getRatio();
				if (ratio >= tem) {
					tem = ratio;
					acc.setAccessname(accPageInfo.getAccessname());
				}
			}
			acc.setRatio(tem);
		}
		return acc;
	}

	@Override
	public Integer findActUser(String dateDim) {
		return this.userBehInfoMapper.findActUser(dateDim);
	}

	@Override
	public Integer findLogUser(String dateDim) {
		return this.userBehInfoMapper.findLogUser(dateDim);
	}

	@Override
	public Integer findRegUser(String dateDim) {
		return this.userBehInfoMapper.findRegUser(dateDim);
	}

	@Override
	public Integer findLifeUser(String dateDim) {
		UserBehInfo findLifeUser = this.userBehInfoMapper.findLifeUser(dateDim);
		return Integer.valueOf(findLifeUser.getDateCode());
	}

	@Override
	public ChartBean getAccUserInfo(String dateDim, String type) {
		List num = new ArrayList();
		ChartData c = new ChartData();
		ChartBean cOnce = null;
		try {
			cOnce = this.userBehInfoMapper.findCOnceUser(dateDim);
			cOnce.setType("pie");
			if (cOnce.getRatio() != 0) {
			}
			c.setName(cOnce.getName());
			c.setY(cOnce.getRatio());
			num.add(c);
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			ChartBean cAdd = this.userBehInfoMapper.findCAddUser(dateDim);
			ChartData c1 = new ChartData();
			c1.setName(cAdd.getName());
			c1.setY(cAdd.getRatio());
			num.add(c1);
		} catch (Exception e) {
			e.getMessage();
		}
		// 存留用户
		try {
			ChartBean cKeep = null;
			switch (type) {
			// 周
			case "week":
				cKeep = this.userBehInfoMapper.findCRetUser(dateDim);
				break;
			// 月
			case "month":
				cKeep = this.userBehInfoMapper.findCRetUser(dateDim);
				break;
			// 年
			case "year":
				cKeep = this.userBehInfoMapper.findCRetUserByYear(dateDim);
				break;
			}
			ChartData c2 = new ChartData();
			c2.setName(cKeep.getName());
			c2.setY(cKeep.getRatio());
			num.add(c2);
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			ChartBean cSile = this.userBehInfoMapper.findCSileUser(dateDim);
			ChartData c3 = new ChartData();
			c3.setName(cSile.getName());
			c3.setY(cSile.getRatio());
			num.add(c3);
		} catch (Exception e) {
			e.getMessage();
		}
		// 回流用户
		try {
			ChartBean cRef = null;
			switch (type) {
			// 周
			case "week":
				cRef = this.userBehInfoMapper.findCRefUser(dateDim);
				break;
			// 月
			case "month":
				cRef = this.userBehInfoMapper.findCRefUserMonth(dateDim);
				break;
			// 年
			case "year":
				cRef = this.userBehInfoMapper.findCRefUserYear(dateDim);
				break;
			}
			ChartData c4 = new ChartData();
			c4.setName(cRef.getName());
			c4.setY(cRef.getRatio());
			num.add(c4);
		} catch (Exception e) {
			e.getMessage();
		}
		// 流失用户
		try {
			ChartBean cAway = null;
			switch (type) {
			// 周
			case "week":
				cAway = this.userBehInfoMapper.findCAwayUser(dateDim);
				break;
			// 月
			case "month":
				cAway = this.userBehInfoMapper.findCAwayUserMonth(dateDim);
				break;
			// 年
			case "year":
				cAway = this.userBehInfoMapper.findCAwayUserYear(dateDim);
				break;
			}
			ChartData c5 = new ChartData();
			c5.setName(cAway.getName());
			c5.setY(cAway.getRatio());
			num.add(c5);
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			ChartBean cOther = this.userBehInfoMapper.findCOtherUser(dateDim);
			ChartData c6 = new ChartData();
			c6.setName(cOther.getName());
			c6.setY(cOther.getRatio());
			num.add(c6);
		} catch (Exception e) {
			e.getMessage();
		}

		cOnce.setData(num);
		return cOnce;
	}

	@Override
	public Pager<UserBehInfo> findErrList(Pager<UserBehInfo> pager, UserBehBean record, String date) {
		return this.userBehInfoMapper.findErrList(pager, record, date);
	}

	@Override
	public String findErrNum(String dateDim) {
		return this.userBehInfoMapper.findErrNum(dateDim);
	}

	@Override
	public String findErrNumDay() {
		return this.userBehInfoMapper.findErrNumDay();
	}

	@Override
	public String findErrNumWeek() {
		return this.userBehInfoMapper.findErrNumWeek();
	}

	@Override
	public ChartBean getHieUserInfo(String dateDim) {
		List<ChartData> chartData = this.userBehInfoMapper.findHieUser(dateDim);
		List<ChartData> newchartData = new ArrayList<ChartData>();
		ChartBean chartBean = new ChartBean();
		chartBean.setType("pie");
		int i = 0;
		for (ChartData chart : chartData) {
			chartBean.setName("访问来源");
			chartBean.setRatio(chart.getY());
			if (Integer.valueOf(chart.getName()) >= 9) {
				chart.setName("营销服务部");
				i += chart.getY();
				chart.setY(i);
				if (chartData.size() - 1 != chartData.indexOf(chart)) {
					continue;
				}
			}
			switch (chart.getName()) {
			case "1":
				chart.setName("总公司");
				break;
			case "3":
				chart.setName("分公司");
				break;
			case "5":
				chart.setName("中支");
				break;
			default:
				// chart.setName("营销服务部");
				break;
			}
			newchartData.add(chart);

		}
		chartBean.setData(newchartData);
		return chartBean;
	}

	@Override
	public List<Integer> getDatePerInfo(String dateDim) {
		List<Integer> list = this.userBehInfoMapper.getDatePerInfo();
		return list;
	}

	@Override
	public ChartBean getHieErrInfo(String dateDim) {
		List<ChartData> list = new ArrayList<ChartData>();
		List<ChartData> chartData = this.userBehInfoMapper.getHieErrInfo(dateDim);
		ChartBean chartBean = new ChartBean();
		chartBean.setType("column");
		for (int i = 1; i <= 12; i++) {
			ChartData chartdata = new ChartData();
			for (ChartData chart : chartData) {
				String str = chart.getName().substring(4);
				if (i < 10) {
					String j = "0" + i;
					if (j.equals(str)) {
						chartBean.setRatio(chart.getY());
						chartdata.setName(chart.getName());
						chartdata.setY(chart.getY());
						break;
					}
				} else {
					String j = i + "";
					if (j.equals(str)) {
						chartBean.setRatio(chart.getY());
						chartdata.setName(chart.getName());
						chartdata.setY(chart.getY());
						break;
					}
				}
				if (StringUtil.isNull(chartdata.getName())) {
					if (i < 10) {
						chartdata.setName(chart.getName().substring(0, 3) + "0" + i);
					} else {
						chartdata.setName(chart.getName().substring(0, 3) + i);
					}
					chartdata.setY(0);
				}
			}
			list.add(chartdata);
			chartBean.setName("错误");
			chartBean.setData(list);
		}
		return chartBean;
	}

	@Override
	public int findOnlinePeople() {
		return this.userBehInfoMapper.findOnlinePeople();
	}

	public List<Version> getVersionList(String type) {
		return this.versionMapper.getVersionList(type);
	}

	@Override
	public int findLAccUser(String dateDim) {
		int findAddUser = this.userBehInfoMapper.findLAccUser(dateDim);
		return findAddUser;
	}

	@Override
	public int findLErrRate(String dateDim) {
		int findErrRate = this.userBehInfoMapper.findLErrRate(dateDim);
		return findErrRate;
	}

	public List<UserBehInfo> findUserBehInfoByOnline() {
		UserBehInfoExample userBehInfoExample = new UserBehInfoExample();
		userBehInfoExample.createCriteria().andIsOnlineEqualTo("1");
		return this.userBehInfoMapper.selectByExample(userBehInfoExample);
	}

	public int updateUserInfo(UserBehInfo userBehInfo) {
		return this.userBehInfoMapper.updateByPrimaryKeySelective(userBehInfo);
	}

	public List<UserBehInfo> findDateRegionByYear() {
		return this.userBehInfoMapper.findDateRegionByYear();
	}

	public List<UserBehInfo> findDateRegionByMonth() {
		return this.userBehInfoMapper.findDateRegionByMonth();
	}

	public List<UserBehInfo> findDateRegionByWeek() {
		return this.userBehInfoMapper.findDateRegionByWeek();
	}

	@Override
	public ChartBean findLMinalterType(String dateDim, String type) {
		return this.userBehInfoMapper.findLMinalterType(dateDim, type);
	}

	@Override
	public Integer findCallPage(String behaviorid) {
		Integer count = this.userBehInfoMapper.findCallPage(behaviorid);
		return count;
	}

	@Override
	public int updateAccPageInfoByBehaviorid(AccPageInfo acc) {
		return this.accPageInfoMapper.updateAccPageInfoByBehaviorid(acc);
	}

	@Override
	public Pager<UserBehBean> findAccPager(Pager<UserBehBean> pager, UserBehBean record, String orgs, String userId,
			String module, String isNode, String datetime1, String datetime2) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pager = this.accPageInfoMapper.findAccPager(pager, record, orgs, userId, module, isNode, datetime1, datetime2);
		for (UserBehBean userBehbean : pager.getPageItems()) {
			try {
				Long outTime = (sf.parse(userBehbean.getOutTime()).getTime()
						- sf.parse((userBehbean.getAccessTime())).getTime());
				if ((outTime / 1000 / 60) > 0) {
					userBehbean.setStayTime(outTime / 1000 / 60 + "min" + outTime / 1000 % 60 + "s");
				} else {
					userBehbean.setStayTime(outTime >= 0 ? outTime / 1000 + "s" : (-(outTime / 1000)) + "s");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return pager;
	}

	@Override
	public List<ModuleBean> findFunanAll(Pager<ModuleBean> funpager, ModuleBean funrecord, String dateDim) {
		List<ModuleBean> list = this.accPageInfoMapper.findFunanAll(null, funrecord, dateDim);
		DecimalFormat df = new DecimalFormat("######0.00");
		try {
			FunAnListTotalBean totals = accPageInfoMapper.findTotals(dateDim);
			if (list.size() > 0) {
				for (ModuleBean moduleBean : list) {
					// 访问次数&占比
					if (StringUtil.isNull(totals.getTotal1()) || StringUtil.isNull(moduleBean.getVisitNum())) {
						moduleBean.setVisitNum(0);
						moduleBean.setVnRatio(0.0);
					} else {
						BigDecimal bd = new BigDecimal(moduleBean.getVisitNum() / totals.getTotal1() * 100);
						bd = bd.setScale(2, RoundingMode.HALF_UP);
						moduleBean.setVnRatio(
								Double.parseDouble(df.format(moduleBean.getVisitNum() / totals.getTotal1() * 100)));

					}
					// 访问人数&占比
					if (StringUtil.isNull(totals.getTotal2()) || StringUtil.isNull(moduleBean.getVisitPeo())) {
						moduleBean.setVisitPeo(0);
						moduleBean.setVpRatio(0.0);
					} else {
						moduleBean.setVpRatio(
								Double.parseDouble(df.format(moduleBean.getVisitPeo() / totals.getTotal2() * 100)));
					}
					// 停留时间&占比
					if (StringUtil.isNull(totals.getTotal3()) || StringUtil.isNull(moduleBean.getStayTime())) {
						moduleBean.setStayTime(0.0);
						moduleBean.setStRatio(0.0);
					} else {
						double stayTime = Double.parseDouble(df.format(moduleBean.getStayTime()));
						double stRatio = Double
								.parseDouble(df.format((moduleBean.getStayTime() / totals.getTotal3()) * 100));
						if (stayTime >= 0) {
							moduleBean.setStayTime(stayTime);
							moduleBean.setStRatio(stRatio);
						} else {
							moduleBean.setStayTime(stayTime * -1);
							moduleBean.setStRatio(stRatio * -1);
						}
					}
					// 跳出率
					if (StringUtil.isNull(totals.getTotal4()) || StringUtil.isNull(moduleBean.getOutRatio())) {
						moduleBean.setOutRatio(0.0);
					} else {
						moduleBean.setOutRatio(
								Double.parseDouble(df.format(moduleBean.getOutRatio() / totals.getTotal4() * 100)));
					}
					// 转化率
					if (StringUtil.isNull(totals.getTotal5()) || StringUtil.isNull(moduleBean.getConvertRatio())) {
						moduleBean.setConvertRatio(0.0);
					} else {
						moduleBean.setConvertRatio(
								Double.parseDouble(df.format(moduleBean.getConvertRatio() / totals.getTotal5() * 100)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out.printf("============findFunanList方法错误================="));
		}
		return list;
	}

	@Override
	public int findLAccUserWeek(String dateDim) {
		int findAddUser = this.userBehInfoMapper.findLAccUserWeek(dateDim);
		return findAddUser;
	}

	@Override
	public int findLAccUserMonth(String dateDim) {
		int findAddUser = this.userBehInfoMapper.findLAccUserMonth(dateDim);
		return findAddUser;
	}

	@Override
	public ChartBean findLMinalterTypeMonth(String dateDim, String type) {
		return this.userBehInfoMapper.findLMinalterTypeMonth(dateDim, type);
	}
}
