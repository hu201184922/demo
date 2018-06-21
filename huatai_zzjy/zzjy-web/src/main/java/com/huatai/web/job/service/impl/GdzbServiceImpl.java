package com.huatai.web.job.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.util.RedisUtil;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.job.constants.RedisConstants;
import com.huatai.web.job.factory.RedisSqlConstants;
import com.huatai.web.job.factory.RedisSqlFactory;
import com.huatai.web.job.service.GdzbService;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.thrift.service.RedisService;
import com.huatai.web.utils.DateUtil;

/**
 * @功能 {固定指标数据缓存服务}
 * @作者 MaxBill
 * @时间 2017年9月1日 下午3:04:53
 */
@Service
public class GdzbServiceImpl implements GdzbService {

	Logger log = Logger.getLogger(GdzbServiceImpl.class);

	private RedisService redisService;

	private RedisSqlFactory redisSqlFactory;

	@SuppressWarnings("unused")
	private CommonUtil commonUtil;

	public GdzbServiceImpl() {
		commonUtil = SpringUtil.getBean(CommonUtil.class);
		redisService = SpringUtil.getBean(RedisService.class);
		redisSqlFactory = SpringUtil.getBean(RedisSqlFactory.class);
	}

	private static final Integer redisTime = 0;

	private static final String orgGroupType = "ORG_GROUP";

	private static final String dateGroupType = "DATE_GROUP";

	private static final String dateType1 = "DAY";

	private static final String dateType2 = "MONTH";

	private static final String dateType3 = "YEAR";

	/*------------------------------核心指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {固定指标核心指标定时任务}
	 * @作者 MaxBill
	 * @时间 2017年8月30日 下午7:04:35
	 */
	public void getTEMP02_REG01(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 固定指标核心区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP02_REG01";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					log.error("System Message: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					// 1.1向redis存入天的缓存数据
					TarRegSql tarRegSql_Org_Day = redisService.findTarRegSql(targetCode, temReg.getRegId(),
							orgGroupType, null, dateType1);
					// 1.2向redis存入月的缓存数据
					TarRegSql tarRegSql_Org_Month = redisService.findTarRegSql(targetCode, temReg.getRegId(),
							orgGroupType, null, dateType2);
					// 1.3向redis存入年的缓存数据
					TarRegSql tarRegSql_Org_Year = redisService.findTarRegSql(targetCode, temReg.getRegId(),
							orgGroupType, null, dateType3);
					// 2.1向redis存入年到月的缓存数据
					TarRegSql tarRegSql_Date_Year_Month = redisService.findTarRegSql(targetCode, temReg.getRegId(),
							dateGroupType, dateType2, null);
					// 2.2向redis存入月到天的缓存数据
					TarRegSql tarRegSql_Date_Month_Day = redisService.findTarRegSql(targetCode, temReg.getRegId(),
							dateGroupType, dateType1, null);
					log.error("System Message: <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					// 按机构分组
					List<HashMap<String, String>> orgGroupList = redisService.getOrgListByIndex(subCode,
							tarReg.getRoleOrgType());
					for (HashMap map : orgGroupList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							// 1.按机构分组
							String orgKey = RedisConstants.GDZB_CORE + "-" + targetCode + "-G1" + "-" + orgCode + "-";
							if (null != tarRegSql_Org_Day) {
								String sql_org_day = redisSqlFactory.getGdzbSql(RedisSqlConstants.GDZB_02,
										tarRegSql_Org_Day, orgCode, target.getDeptCode(), orgGroupType);
								try {
									RedisUtil.set(orgKey + dateType1, redisTime,
											this.redisService.getSqlData(sql_org_day));
								} catch (Exception e) {
									log.error("System Message: 固定指标核心指标定时任务天维度按机构分组" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
							if (null != tarRegSql_Org_Month) {
								String sql_org_month = redisSqlFactory.getGdzbSql(RedisSqlConstants.GDZB_02,
										tarRegSql_Org_Month, orgCode, target.getDeptCode(), orgGroupType);
								try {
									RedisUtil.set(orgKey + dateType2, redisTime,
											this.redisService.getSqlData(sql_org_month));
								} catch (Exception e) {
									log.error("System Message: 固定指标核心指标定时任务月维度按机构分组" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
							if (null != tarRegSql_Org_Year) {
								String sql_org_year = redisSqlFactory.getGdzbSql(RedisSqlConstants.GDZB_02,
										tarRegSql_Org_Year, orgCode, target.getDeptCode(), orgGroupType);
								try {
									RedisUtil.set(orgKey + dateType3, redisTime,
											this.redisService.getSqlData(sql_org_year));
								} catch (Exception e) {
									log.error("System Message: 固定指标核心指标定时任务年维度按机构分组" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
						}
					}
					// 按日期分组
					List<HashMap<String, String>> dateGroupList = redisService.getOrgListByIndex(subCode,
							tarReg.getRoleOrgType());
					for (HashMap map : dateGroupList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							// 2.按时间分组
							String dateKey01 = RedisConstants.GDZB_CORE + "-" + targetCode + "-G2" + "-MONTH-" + orgCode
									+ "-";
							String dateKey02 = RedisConstants.GDZB_CORE + "-" + targetCode + "-G2" + "-DAY-" + orgCode
									+ "-";
							if (null != tarRegSql_Date_Year_Month) {
								Calendar calendar = Calendar.getInstance();
								calendar.add(Calendar.DATE, -1);
								int year = calendar.get(Calendar.YEAR);
								String sql_date_year_month = redisSqlFactory.getGdzbSql(RedisSqlConstants.GDZB_02,
										tarRegSql_Date_Year_Month, orgCode, target.getDeptCode(), dateGroupType);
								try {
									RedisUtil.set(dateKey01 + year, redisTime,
											this.redisService.getSqlData(sql_date_year_month));
								} catch (Exception e) {
									log.error("System Message: 固定指标核心指标定时任务月维度按时间分组" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
							if (null != tarRegSql_Date_Month_Day) {
								Calendar calendar = Calendar.getInstance();
								calendar.add(Calendar.DATE, -1);
								int year = calendar.get(Calendar.YEAR);
								int month = calendar.get(Calendar.MONTH) + 1;
								String monthCount = "";
								if (month < 10) {
									monthCount = "0" + month;
								} else {
									monthCount = month + "";
								}
								List<String> dateMonthList = DateUtil.getDateBetweenByMonth(year + "-01",
										year + "-" + monthCount);
								if (null != dateMonthList && dateMonthList.size() > 0) {
									for (String dateStr : dateMonthList) {
										String sql_org_month_day = redisSqlFactory.getGdzbSql(RedisSqlConstants.GDZB_02,
												tarRegSql_Date_Month_Day, orgCode, target.getDeptCode(), dateGroupType);
										if (null != dateStr) {
											sql_org_month_day = sql_org_month_day.replace("{month_condition}",
													"'" + dateStr + "'");
										}
										try {
											RedisUtil.set(dateKey02 + dateStr, redisTime,
													this.redisService.getSqlData(sql_org_month_day));
										} catch (Exception e) {
											log.error("System Message: 固定指标核心指标定时任务天维度按时间分组" + targetCode
													+ "指标SQL语句执行发生异常！");
											e.printStackTrace();
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 固定指标核心指标定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT01_TEMP02_REG01() {
		this.getTEMP02_REG01("T01");
	}

	public void getT02_TEMP02_REG01() {
		this.getTEMP02_REG01("T02");
	}

	public void getT07_TEMP02_REG01() {
		this.getTEMP02_REG01("T07");
	}

	public void getT08_TEMP02_REG01() {
		this.getTEMP02_REG01("T08");
	}

	public void getT09_TEMP02_REG01() {
		this.getTEMP02_REG01("T09");
	}

	public void getP01_TEMP02_REG01() {
		this.getTEMP02_REG01("P01");
	}

	public void getP02_TEMP02_REG01() {
		this.getTEMP02_REG01("P02");
	}

	public void getP04_TEMP02_REG01() {
		this.getTEMP02_REG01("P04");
	}

	public void getP05_TEMP02_REG01() {
		this.getTEMP02_REG01("P05");
	}

	/*------------------------------月度统计区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {月度指标统计定时任务}
	 * @作者 MaxBill
	 * @时间 2017年8月30日 下午7:04:35
	 */
	public void getTEMP02_REG02(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 固定指标月度区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP02_REG02";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null, null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode,
							tarReg.getRoleOrgType());
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							String redisKey = RedisConstants.GDZB_MONT + "-" + targetCode + "-" + orgCode;
							if (null != tarRegSql) {
								String sqlCode = redisSqlFactory.getGdzbSql(RedisSqlConstants.GDZB_01, tarRegSql,
										orgCode, target.getDeptCode(), null);
								try {
									RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
								} catch (Exception e) {
									log.error("System Message: 月度指标统计定时任务" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 月度指标统计定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT07_TEMP02_REG02() {
		this.getTEMP02_REG02("T07");
	}

	public void getT08_TEMP02_REG02() {
		this.getTEMP02_REG02("T08");
	}

	public void getT09_TEMP02_REG02() {
		this.getTEMP02_REG02("T09");
	}

	public void getP04_TEMP02_REG02() {
		this.getTEMP02_REG02("P04");
	}

	public void getP05_TEMP02_REG02() {
		this.getTEMP02_REG02("P05");
	}

	public void getP06_TEMP02_REG02() {
		this.getTEMP02_REG02("P06");
	}

	public void getGYB_TEMP02_REG02() {
		this.getTEMP02_REG02("GYB");
	}

	/*------------------------------地图指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {地图区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP02_REG03(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 固定指标地图区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP02_REG03";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), orgGroupType, null,
							null);
					String redisKey = RedisConstants.GDZB_MAPS + "-" + targetCode;
					if (null != tarRegSql) {
						String sqlCode = redisSqlFactory.getGdzbSql("", tarRegSql, "1", target.getDeptCode(),
								orgGroupType);
						try {
							RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
						} catch (Exception e) {
							log.error("System Message: 地图区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 地图区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT07_TEMP02_REG03() {
		this.getTEMP02_REG03("T07");
	}

	public void getT08_TEMP02_REG03() {
		this.getTEMP02_REG03("T08");
	}

	public void getT09_TEMP02_REG03() {
		this.getTEMP02_REG03("T09");
	}

	public void getP04_TEMP02_REG03() {
		this.getTEMP02_REG03("P04");
	}

	public void getP05_TEMP02_REG03() {
		this.getTEMP02_REG03("P05");
	}

	public void getP06_TEMP02_REG03() {
		this.getTEMP02_REG03("P06");
	}

	public void getGYB_TEMP02_REG03() {
		this.getTEMP02_REG03("GYB");
	}

	/*------------------------------光荣指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {光荣区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP02_REG04(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 固定指标光荣区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP02_REG04";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null, null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode,
							tarReg.getRoleOrgType());
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							String redisKey = RedisConstants.GDZB_HONO + "-" + targetCode + "-" + orgCode;
							if (null != tarRegSql) {
								String sqlCode = redisSqlFactory.getGdzbSql(RedisSqlConstants.GDZB_01, tarRegSql,
										orgCode, target.getDeptCode(), orgGroupType);
								try {
									RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
								} catch (Exception e) {
									log.error("System Message: 光荣区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 光荣区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getGYB_TEMP02_REG04() {
		this.getTEMP02_REG04("GYB");
	}

	/*------------------------------排行指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {排行区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP02_REG05(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 固定指标排行区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP02_REG05";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), orgGroupType, null,
							null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode,
							tarReg.getRoleOrgType());
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							String redisKey = RedisConstants.GDZB_RANK + "-" + targetCode + "-" + orgCode;
							if (null != tarRegSql) {
								String sqlCode = redisSqlFactory.getGdzbSql(RedisSqlConstants.GDZB_03, tarRegSql,
										orgCode, target.getDeptCode(), orgGroupType);
								try {
									RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
								} catch (Exception e) {
									log.error("System Message: 排行区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 排行区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT07_TEMP02_REG05() {
		this.getTEMP02_REG05("T07");
	}

	public void getT08_TEMP02_REG05() {
		this.getTEMP02_REG05("T08");
	}

	public void getT09_TEMP02_REG05() {
		this.getTEMP02_REG05("T09");
	}

	public void getP04_TEMP02_REG05() {
		this.getTEMP02_REG05("P04");
	}

	public void getP05_TEMP02_REG05() {
		this.getTEMP02_REG05("P05");
	}

	public void getP06_TEMP02_REG05() {
		this.getTEMP02_REG05("P06");
	}

	public void getGYB_TEMP02_REG05() {
		this.getTEMP02_REG05("GYB");
	}

	/*------------------------------列表指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {列表区域定时任务-T07三级机构}
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP02_REG06(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 固定指标列表区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP02_REG06";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null, null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode,
							tarReg.getRoleOrgType());
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							String redisKey = RedisConstants.GDZB_LIST + "-" + targetCode + "-" + orgCode;
							if (null != tarRegSql) {
								String sqlCode = redisSqlFactory.getGdzbSql(RedisSqlConstants.GDZB_01, tarRegSql,
										orgCode, target.getDeptCode(), orgGroupType);
								try {
									RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
								} catch (Exception e) {
									log.error("System Message: 列表区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 列表区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT08_TEMP02_REG06() {
		this.getTEMP02_REG06("T08");
	}

	public void getT09_TEMP02_REG06() {
		this.getTEMP02_REG06("T09");
	}

	public void getP04_TEMP02_REG06() {
		this.getTEMP02_REG06("P04");
	}

	public void getGYB_TEMP02_REG06() {
		this.getTEMP02_REG06("GYB");
	}
}
