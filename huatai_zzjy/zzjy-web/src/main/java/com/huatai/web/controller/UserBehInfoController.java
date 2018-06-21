package com.huatai.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.ChartBean;
import com.huatai.web.bean.ModuleBean;
import com.huatai.web.bean.UserBehBean;
import com.huatai.web.bean.UserUseAnBean;
import com.huatai.web.model.AccPageInfo;
import com.huatai.web.model.UserBehInfo;
import com.huatai.web.model.Version;
import com.huatai.web.service.UserBehInfoService;
import com.huatai.web.utils.DateUtil;

/**
 * @author 胡智辉 2017年7月18日
 */
@Controller
@RequestMapping("admin/userBehInfo")
public class UserBehInfoController {

	final static Logger logger = LoggerFactory.getLogger(UserBehInfoController.class);

	@Autowired
	private UserBehInfoService userBehInfoService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		try {
			DecimalFormat df = new DecimalFormat("######0.00");
			String dateDim = DateUtil.getCurrentYearFirstDay();// 本年
			// 查询累计用户
			try {
				UserBehInfo aUser = userBehInfoService.findAccUser(dateDim);
				mv.addObject("aUser", aUser.getRatio() == 0 ? "0" : aUser.getRatio().intValue());
				try {
					int aLUser = userBehInfoService.findLAccUser(dateDim);
					System.out.println("-------------------"+aLUser+"-----------------");
					if (aLUser != 0) {
						double sum = (int) (new Double(aUser.getRatio()) - aLUser) / new Double(aLUser) / 0.01;
						mv.addObject("aUserRatio", sum >= 0 ? ("+" + (sum == 0 ? "0" : df.format(sum))) : df.format(sum));
					}else{
						mv.addObject("aUserRatio", "+0");
					}
				} catch (Exception e) {
					logger.debug("=================去年同比累计用户，计算错误===================");
					mv.addObject("aUserRatio", "+0");
				}
			} catch (Exception e) {
				logger.debug("=================累计用户，计算错误===================");
				mv.addObject("aUser", "0");
			}
			try {
				UserBehInfo eRate = userBehInfoService.findErrRate(dateDim);
				mv.addObject("eRate", eRate.getRatio() == null ? "0" : df.format(100 * eRate.getRatio()));
				int eLRate = userBehInfoService.findLErrRate(dateDim);
				double sum = ((new Double(eRate.getRatio()) - eLRate) / eLRate / 0.01);
				mv.addObject("eRateRatio", sum >= 0 ? ("+" + (sum == 0 ? "0" : sum)) : sum);
			} catch (Exception e) {
				mv.addObject("eRateRatio", "+0");
			}
			try {
				AccPageInfo moduRat = userBehInfoService.findModuRat(dateDim);
				mv.addObject("moduRat", moduRat.getRatio() == 0 ? "0" : df.format(100 * moduRat.getRatio()));
				mv.addObject("moduRatName", moduRat.getAccessname());
			} catch (Exception e) {
				logger.debug("=================模块占比，计算错误===================");
				mv.addObject("moduRat", "0");
				mv.addObject("moduRatName", "- -");
			}
			try {
				List<ChartBean> minalterType = userBehInfoService.findMinalterType(dateDim);
				double max = 0, min = Integer.MAX_VALUE;
				String maxCount, minCount, maxName = null, minName = null;
				int count = 0, minalterCount = 0;
				if (minalterType.size() > 0) {
					for (ChartBean integer : minalterType) {
						if (integer.getRatio() > max) {
							max = integer.getRatio();
							minalterCount = integer.getRatio();
							maxName = integer.getName();
						}
						if (integer.getRatio() < min) {
							min = integer.getRatio();
							if (maxName.equals("PC")) {
								minName = "APP";
							} else {
								minName = integer.getName();
							}
						}
						count += integer.getRatio();
					}
					maxCount = df.format((max / count) * 100) + "%";
					minCount = df.format((1 - max / count) * 100) + "%";
				} else {
					maxName = "PC";
					minName = "APP";
					maxCount = "0%";
					minCount = "0%";
				}

				ChartBean lMinalterType = userBehInfoService.findLMinalterType(dateDim, maxName);

				mv.addObject("maxCount", maxCount);
				mv.addObject("minCount", minCount);
				mv.addObject("maxName", maxName);
				mv.addObject("minName", minName);
				mv.addObject("minalterType", minalterCount);
				try {
					double sum = (int) (new Double(minalterCount - lMinalterType.getRatio()) / lMinalterType.getRatio()
							/ 0.0001) / 100.0;
					mv.addObject("minalterRatio", sum >= 0 ? ("+" + (sum == 0 ? "0" : sum)) : sum);
				} catch (Exception e) {
					logger.debug("=================去年同比，计算错误===================");
					mv.addObject("minalterRatio", "0");
				}
			} catch (Exception e) {
				mv.addObject("pc", "--");
				mv.addObject("app", "--");
				mv.addObject("minalterType", "--");
			}

			try {
				Integer actUser = userBehInfoService.findActUser(dateDim);
				mv.addObject("actUser", actUser);
			} catch (Exception e) {
				logger.debug("=================活跃用户数，计算错误===================");
			}
			try {
				Integer logUser = userBehInfoService.findLogUser(dateDim);
				mv.addObject("logUser", logUser == null ? 0 : (logUser / 7) + ((logUser % 7) > 0 ? 1 : 0));
			} catch (Exception e) {
				mv.addObject("logUser", 0);
				logger.debug("=================近七日登录数，计算错误===================");
			}
			/*
			 * try { Integer regUser = userBehInfoService.findRegUser(dateDim);
			 * mv.addObject("regUser", regUser / 7); } catch (Exception e) {
			 * logger.debug("=================近七日注册数，计算错误===================");
			 * }
			 */
			try {
				Integer lifeUser = userBehInfoService.findLifeUser(dateDim);
				mv.addObject("lifeUser", lifeUser);
			} catch (Exception e) {
				mv.addObject("lifeUser", "0");
				logger.debug("=================用户生命周期，计算错误===================");
			}
			mv.setViewName("/admin/userBehInfo/index");
		} catch (Exception e) {
			logger.debug("=================用户行为分析模块，计算错误===================");
		}
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<UserUseAnBean> getPager(Pager<UserUseAnBean> pager, UserUseAnBean record) {
		Pager<UserUseAnBean> page = userBehInfoService.findByPager(pager, record);
		return page;
	}

	@ResponseBody
	@RequestMapping(value = "dateUserAn")
	public Map<String, Object> dateUserAn(String dateDim) {
		// dateDim = DecideDate(dateDim);
		Map<String, Object> map = new HashMap();
		DecimalFormat df = new DecimalFormat("######0.00");
		try {
			// 查询累计用户
			try {
				UserBehInfo aUser = userBehInfoService.findAccUser(DecideDate(dateDim));
				map.put("aUser", aUser.getRatio() == 0 ? "0" : aUser.getRatio());
				int aLUser = 0;
				switch (dateDim) {
				case "week":
					aLUser = userBehInfoService.findLAccUserWeek(DecideDate(dateDim));
					break;
				case "month":
					aLUser = userBehInfoService.findLAccUserMonth(DecideDate(dateDim));
					break;
				default:
					aLUser = userBehInfoService.findLAccUser(DecideDate(dateDim));
					break;
				}
				if (aLUser != 0) {
					double sum = (int) (new Double(aUser.getRatio()) - aLUser) / new Double(aLUser) / 0.01;
					map.put("aUserRatio", sum >= 0 ? ("+" + (sum == 0 ? "0" : df.format(sum))) : df.format(sum));
				} else {
					map.put("aUserRatio", "+0");
				}
			} catch (Exception e) {
				logger.debug("=================累计用户，计算错误===================");
				map.put("aUserRatio", "+0");
			}
			try {
				UserBehInfo eRate = userBehInfoService.findErrRate(DecideDate(dateDim));
				map.put("eRate", eRate.getRatio() == null ? "0" : df.format(100 * eRate.getRatio()));
				int eLRate = userBehInfoService.findLErrRate(DecideDate(dateDim));
				double sum = (+(new Double(eRate.getRatio()) - eLRate) / eLRate / 0.01);
				map.put("eRateRatio", sum >= 0 ? ("+" + (sum == 0 ? "0" : sum)) : sum);
			} catch (Exception e) {
				map.put("eRateRatio", "+0");
			}
			try {
				AccPageInfo moduRat = userBehInfoService.findModuRat(DecideDate(dateDim));
				map.put("moduRat", moduRat.getRatio() == 0 ? "0" : df.format(100 * moduRat.getRatio()));
				map.put("moduRatName", moduRat.getAccessname());
			} catch (Exception e) {
				logger.debug("=================模块占比，计算错误===================");
				map.put("moduRat", "0");
				map.put("moduRatName", "- -");
			}
			try {
				List<ChartBean> minalterType = userBehInfoService.findMinalterType(DecideDate(dateDim));
				double max = 0, min = Integer.MAX_VALUE;
				String maxCount = null, minCount = null, maxName = null, minName = null;
				int count = 0, minalterCount = 0;
				if (minalterType.size() > 0) {
					for (ChartBean integer : minalterType) {
						if (integer.getRatio() > max) {
							max = integer.getRatio();
							minalterCount = integer.getRatio();
							maxName = integer.getName();
						}
						if (integer.getRatio() < min) {
							min = integer.getRatio();
							if (maxName.equals("PC")) {
								minName = "APP";
							} else {
								minName = integer.getName();
							}
						}
						count += integer.getRatio();
						maxCount = df.format((max / count) * 100) + "%";
						minCount = df.format((1 - max / count) * 100) + "%";
					}
				} else {
					maxName = "PC";
					minName = "APP";
					maxCount = "0%";
					minCount = "0%";
				}
				map.put("maxCount", maxCount);
				map.put("minCount", minCount);
				map.put("maxName", maxName);
				map.put("minName", minName);
				map.put("minalterType", minalterCount);
				try {
					ChartBean lMinalterType;
					switch (dateDim) {
					case "month":
						lMinalterType = userBehInfoService.findLMinalterTypeMonth(DecideDate(dateDim), maxName);
						break;
					default:
						lMinalterType = userBehInfoService.findLMinalterType(DecideDate(dateDim), maxName);
						break;
					}
					double sum = (int) (new Double(minalterCount - lMinalterType.getRatio()) / lMinalterType.getRatio()
							/ 0.0001) / 100.0;
					map.put("minalterRatio", sum >= 0 ? ("+" + (sum == 0 ? "0" : sum)) : sum);
				} catch (Exception e) {
					map.put("minalterRatio", "+0");
				}
			} catch (Exception e) {
				map.put("pc", "--");
				map.put("app", "--");
				map.put("minalterType", "--");
			}

			try {
				Integer actUser = userBehInfoService.findActUser(DecideDate(dateDim));
				map.put("actUser", actUser);
			} catch (Exception e) {
				logger.debug("=================活跃用户数，计算错误===================");
			}
			try {
				Integer logUser = userBehInfoService.findLogUser(DecideDate(dateDim));
				map.put("logUser", logUser == null ? 0 : (logUser / 7) + ((logUser % 7) > 0 ? 1 : 0));
			} catch (Exception e) {
				map.put("logUser", 0);
				logger.debug("=================近七日登录数，计算错误===================");
			}
			/*
			 * try { Integer regUser =
			 * userBehInfoService.findRegUser(DecideDate(dateDim));
			 * map.put("regUser", regUser / 7); } catch (Exception e) {
			 * logger.debug("=================近七日注册数，计算错误===================");
			 * }
			 */
			try {
				Integer lifeUser = userBehInfoService.findLifeUser(DecideDate(dateDim));
				map.put("lifeUser", lifeUser);
			} catch (Exception e) {
				map.put("lifeUser", "0");
				logger.debug("=================用户生命周期，计算错误===================");
			}
			return map;
		} catch (Exception e) {
			logger.debug("=================功能错误===================");
		}
		return map;

	}

	// 累计用户饼图
	@ResponseBody
	@RequestMapping(value = "getAccUserInfo")
	public Map<String, List<ChartBean>> getAccUserInfo(String dateDim) {
		Map<String, List<ChartBean>> map = new HashMap();
		String type = dateDim;
		try {
			if (dateDim == null) {
				dateDim = "";
			}
			dateDim = DecideDate(dateDim);
			ChartBean accUserInfo = userBehInfoService.getAccUserInfo(dateDim, type);
			map.put("accUserInfo", Arrays.asList(accUserInfo));
			return map;
		} catch (Exception e) {
			logger.debug("=================用户饼图，数据错误===================");
		}
		map.put("result", null);
		return map;
	}

	// 层级饼图
	@ResponseBody
	@RequestMapping(value = "getHieUserInfo")
	public Map<String, List<ChartBean>> getHieUserInfo(String dateDim) {
		Map<String, List<ChartBean>> map = new HashMap();
		try {
			if (dateDim == null) {
				dateDim = "";
			}
			dateDim = DecideDate(dateDim);
			ChartBean hieUserInfo = userBehInfoService.getHieUserInfo(dateDim);
			map.put("hieUserInfo", Arrays.asList(hieUserInfo));
		} catch (Exception e) {
			logger.debug("=================层级用户，数据错误===================");
		}
		return map;
	}

	// 使用时段表图
	@ResponseBody
	@RequestMapping(value = "getDatePerInfo")
	public Map<String, List<Integer>> getDatePerInfo(String dateDim) {
		if (dateDim == null) {
			dateDim = "";
		}
		dateDim = DecideDate(dateDim);
		Map<String, List<Integer>> map = new HashMap();
		List<Integer> datePerInfo = userBehInfoService.getDatePerInfo(dateDim);
		map.put("datePerInfo", datePerInfo);
		return map;
	}

	// 用户分析
	@ResponseBody
	@RequestMapping(value = "onlinePeople")
	public int onlinePeople() {
		int result = 0;
		try {
			result = userBehInfoService.findOnlinePeople();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "download", method = RequestMethod.GET)
	@ResponseBody
	public void getDownload(Pager<UserBehInfo> pager, UserBehBean record, Pager<ModuleBean> funpager,
			ModuleBean funrecord, String modelName, String date, HttpServletRequest request,
			HttpServletResponse response) {
		String dateDim = DecideDate(date);
		Map<String, Object> map = new HashMap<String, Object>();
		String sheetName = null;
		String[] title = null;
		if (modelName.equals("all") || modelName.equals("userAn")) {
			sheetName = "用户使用分析报表";
			String[] title1 = { "序号", "用户", "终端", "使用时长", "使用间隔", "访问页数", "使用版本" };
			List<UserBehInfo> list = userBehInfoService.findAll();
			List<List<String>> userList = new ArrayList<List<String>>();
			int i = 1;
			int day = 24 * 60 * 60 * 1000;
			int h = 60 * 60 * 1000;
			for (UserBehInfo userBehInfo : list) {
				long useData = (userBehInfo.getOutTime().getTime() - userBehInfo.getLoginTimeNow().getTime());
				List<String> info = new ArrayList<String>();
				info.add(i + "");
				info.add(userBehInfo.getUserName());
				info.add(userBehInfo.getTerminalType());
				long sum = userBehInfo.getOutTime().getTime() - userBehInfo.getLoginTimeNow().getTime();
				if ((sum) / h >= 1) {
					if ((sum) / h >= 24) {
						info.add(useData / h / 24 + "day" + useData / h % 24 + "h" + useData / h / 24 % (60 * 1000)
								+ "min");
					} else {
						info.add(useData / h + "h" + useData / h % (60 * 1000) + "min");
					}
				} else if (useData / h < 1 && useData / (60 * 1000) > 1) {
					info.add(useData / (60 * 1000) + "min");
				} else {
					info.add((useData / (60 * 1000) > 0 ? useData / (60 * 1000)
							: (userBehInfo.getLoginTimeNow().getTime() - userBehInfo.getOutTime().getTime())
									/ (60 * 1000))
							+ "min");
				}
				info.add(((userBehInfo.getLoginTimeNow().getTime() - userBehInfo.getLoginTimeLast().getTime()) / day < 0
						? (userBehInfo.getLoginTimeLast().getTime() - userBehInfo.getLoginTimeNow().getTime()) / day
						: (userBehInfo.getLoginTimeNow().getTime() - userBehInfo.getLoginTimeLast().getTime()) / day)
						+ "");
				Integer count = this.userBehInfoService.findCallPage(userBehInfo.getBehaviorid());
				info.add(count.toString());
				info.add(userBehInfo.getVersion());
				i++;
				userList.add(info);
			}
			title = title1;
			map.put("list", userList);
		} else if (modelName.equals("funAn")) {
			sheetName = "用户使用分析报表";
			String[] title2 = { "功能模块", "所属菜单", "访问次数", "占比", "访问人数", "占比", "停留时间", "占比", "跳出率", "转化率" };
			List<ModuleBean> fmNameList = userBehInfoService.findFunanAll(funpager, funrecord, dateDim);
			List<List<String>> userList = new ArrayList<List<String>>();
			int i = 1;
			for (ModuleBean fun : fmNameList) {
				List<String> info = new ArrayList<String>();
				info.add(fun.getName());
				info.add(fun.getpName());
				info.add(fun.getVisitNum().toString());
				info.add(fun.getVnRatio() + "%");
				info.add(fun.getVisitPeo().toString());
				info.add(fun.getVpRatio() + "%");
				info.add(fun.getStayTime().toString());
				info.add(fun.getStRatio() + "%");
				info.add(fun.getOutRatio() + "%");
				info.add(fun.getConvertRatio() + "%");
				i++;
				userList.add(info);
			}
			title = title2;
			map.put("list", userList);
		} else if (modelName.equals("errAn")) {
			sheetName = "错误分析报表";
			String[] title3 = { "序号", "错误类型", "备注" };
			Pager<UserBehInfo> errList = userBehInfoService.findErrList(pager, record, dateDim);
			List<UserBehInfo> list = errList.getPageItems();
			List<List<String>> userList = new ArrayList<List<String>>();
			int i = 1;
			for (UserBehInfo err : list) {
				List<String> info = new ArrayList<String>();
				info.add(i + "");
				info.add(err.getTerminalType());
				info.add(err.getErrorInfo());
				i++;
				userList.add(info);
			}
			title = title3;
			map.put("list", userList);
		}

		download(sheetName, title, map, request, response);
	}

	@ResponseBody
	@RequestMapping(value = "funanlist")
	public Pager<ModuleBean> Funanlist(Pager<ModuleBean> pager, ModuleBean record, String funDate) {
		if (StringUtil.isNull(funDate)) {
			funDate = "year";
		}
		funDate = DecideDate(funDate);
		Pager<ModuleBean> fmNameList = userBehInfoService.findFunanList(pager, record, funDate);
		return fmNameList;
	}

	@ResponseBody
	@RequestMapping(value = "errlist")
	public Pager<UserBehInfo> errList(Pager<UserBehInfo> pager, UserBehBean record, String errDate) {
		String dateDim = DecideDate(errDate);
		Pager<UserBehInfo> errList = userBehInfoService.findErrList(pager, record, dateDim);
		return errList;
	}

	@ResponseBody
	@RequestMapping(value = "errinfo")
	public Map<String, Object> errMap(String date) {
		String dateDim = DecideDate(date);
		DecimalFormat df = new DecimalFormat("######0.00");
		Map<String, Object> map = new HashMap<String, Object>();
		UserBehInfo errRate = userBehInfoService.findErrRate(dateDim);
		String errNum = userBehInfoService.findErrNum(dateDim);
		String errNumDay = userBehInfoService.findErrNumDay();
		String errNumWeek = userBehInfoService.findErrNumWeek();
		if (StringUtil.isNotNull(errRate.getRatio())) {
			map.put("errRate", df.format(errRate.getRatio() * 100));
		} else {
			map.put("errRate", 0);
		}
		map.put("num", errNum);
		map.put("numDay", errNumDay);
		map.put("numWeek", errNumWeek);
		return map;
	}

	// 错误柱状图
	@ResponseBody
	@RequestMapping(value = "getHieErrInfo")
	public Map<String, List<ChartBean>> getHieErrInfo(String dateDim) {
		if (dateDim == null) {
			dateDim = "";
		}
		dateDim = DecideDate(dateDim);
		Map<String, List<ChartBean>> map = new HashMap();
		ChartBean dateErrInfo = userBehInfoService.getHieErrInfo(dateDim);
		map.put("dateErrInfo", Arrays.asList(dateErrInfo));
		return map;
	}

	public String DecideDate(String date) {
		if (StringUtil.isNull(date)) {
			date = "year";
		}
		String dateDim = DateUtil.getCurrentYearFirstDay();
		switch (date) {
		case "year":
			dateDim = DateUtil.getCurrentYearFirstDay();
			break;
		case "month":
			dateDim = DateUtil.getCurrentMonthFirstDay();
			break;
		case "week":
			dateDim = DateUtil.getCurrentWeekFirstDay();
			break;
		default:
			break;
		}
		return dateDim;
	}

	@SuppressWarnings("resource")
	public void download(String sheetName, String[] title, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet(sheetName);
		Row row = sheet.createRow((short) 0);
		int i = 0;
		for (String s : title) {
			Cell cell = row.createCell(i);
			cell.setCellValue(s);
			i++;
		}
		int j = 1;
		List<List<String>> list = (List<List<String>>) map.get("list");
		if (list.size() > 0) {
			for (List<String> info : list) {
				Row rowData = sheet.createRow((short) j);
				for (int x = 0; x < info.size(); x++) {
					Cell cell = rowData.createCell(x);
					cell.setCellValue(info.get(x));
					sheet.setColumnWidth((short) 0, (short) 10000);
				}
				j++;
			}
		}
		OutputStream out = null;// 创建一个输出流对象
		try {
			out = response.getOutputStream();
			String headerStr = "new";// 文件名
			headerStr = new String(headerStr.getBytes("gb2312"), "ISO8859-1");// headerString为中文时转码
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xlsx");// filename是下载的xls的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			wb.write(out);
			out.flush();
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @功能 {查询版本}
	 * @作者 MaxBill
	 * @时间 2017年9月25日 下午1:20:22
	 */
	@ResponseBody
	@RequestMapping("/getVersionList")
	public List<Version> getVersionList(String type) {
		// 0：pc端，1：app端
		return this.userBehInfoService.getVersionList(type);
	}

	/**
	 * @功能 {查询时间段人使用}
	 * @作者 MaxBill
	 * @时间 2017年9月25日 下午1:20:22
	 */
	@ResponseBody
	@RequestMapping(value = "getDateRegionInfo")
	public List<UserBehInfo> getDateRegionInfo(String dateDim) {
		List<UserBehInfo> userInfoList = null;
		switch (dateDim) {
		// 周
		case "week":
			userInfoList = this.userBehInfoService.findDateRegionByWeek();
			break;
		// 月
		case "month":
			userInfoList = this.userBehInfoService.findDateRegionByMonth();
			break;
		// 年
		case "year":
			userInfoList = this.userBehInfoService.findDateRegionByYear();
			break;
		}
		return userInfoList;
	}

}
