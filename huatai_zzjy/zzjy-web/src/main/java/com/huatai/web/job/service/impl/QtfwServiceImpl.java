package com.huatai.web.job.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.util.RedisUtil;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.job.constants.RedisConstants;
import com.huatai.web.job.factory.RedisSqlConstants;
import com.huatai.web.job.factory.RedisSqlFactory;
import com.huatai.web.job.factory.RedisSqlParameter;
import com.huatai.web.job.service.QtfwService;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.UserSetWarn;
import com.huatai.web.thrift.service.RedisService;

@Service
public class QtfwServiceImpl implements QtfwService {

	Logger log = Logger.getLogger(GdzbServiceImpl.class);

	private RedisService redisService;

	private RedisSqlFactory redisSqlFactory;

	private RedisSqlParameter redisSqlParameter;

	public QtfwServiceImpl() {
		redisService = SpringUtil.getBean(RedisService.class);
		redisSqlFactory = SpringUtil.getBean(RedisSqlFactory.class);
		redisSqlParameter = SpringUtil.getBean(RedisSqlParameter.class);
	}

	private static final Integer redisTime = 0;

	private static final String dateGroupType = "DATE_GROUP";

	private static final String dateType1 = "DAY";

	private static final String dateType2 = "MONTH";

	private static final String dateType3 = "YEAR";

	/*------------------------------指标预警区域------------------------------*/

	/**
	 * @功能 {预警监控数据缓存定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午12:29:20
	 */
	public void getWarnData() {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 预警监控定时任务开始执行...");
		// 查询已经开启的预警集合
		List<UserSetWarn> userSetWarnList = redisService.getUserSetWarnListByOn();
		if (null != userSetWarnList) {
			try {
				for (UserSetWarn userSetWarn : userSetWarnList) {
					String targetCode = userSetWarn.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarInitSql tarInitSql = (TarInitSql) this.redisService.getTarInitSql("03", targetCode, null, null,
							dateType3);
					List<HashMap<String, String>> orgList = null;
					String subCode = targetCode.substring(0, 3);
					orgList = redisService.getOrgList(subCode, null);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.WARN + "-" + targetCode + "-" + orgCode;
						if (null != tarInitSql) {
							String sqlCode = redisSqlFactory.getYjjkSql(tarInitSql.getSqlCode(), orgCode,
									target.getDeptCode());
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 预警监控数据缓存定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 预警监控数据缓存定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	/*------------------------------手机虚拟区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {分析管理虚拟区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年8月30日 下午7:04:35
	 */
	public void getTEMP12_REG01(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理虚拟区域定时任务开始执行...");
		String regCode = "TEMP12_REG01";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null, null);
					List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, tarReg.getRoleOrgType());
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							String redisKey = RedisConstants.FXGL_VIRT + "-" + targetCode + "-" + orgCode;
							if (null != tarRegSql) {
								String sqlCode = redisSqlFactory.getAppFxglSql(tarRegSql.getSqlCode(),
										target.getDeptCode(), orgCode);
								try {
									RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
								} catch (Exception e) {
									log.error("System Message:分析管理虚拟区域" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message:分析管理虚拟区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT01_TEMP12_REG01() {
		this.getTEMP12_REG01("T01");
	}

	public void getT02_TEMP12_REG01() {
		this.getTEMP12_REG01("T02");
	}

	public void getT03_TEMP12_REG01() {
		this.getTEMP12_REG01("T03");
	}

	public void getT04_TEMP12_REG01() {
		this.getTEMP12_REG01("T04");
	}

	public void getT07_TEMP12_REG01() {
		this.getTEMP12_REG01("T07");
	}

	public void getT08_TEMP12_REG01() {
		this.getTEMP12_REG01("T08");
	}

	public void getT09_TEMP12_REG01() {
		this.getTEMP12_REG01("T09");
	}

	public void getP01_TEMP12_REG01() {
		this.getTEMP12_REG01("P01");
	}

	public void getP02_TEMP12_REG01() {
		this.getTEMP12_REG01("P02");
	}

	public void getP03_TEMP12_REG01() {
		this.getTEMP12_REG01("P03");
	}

	public void getP04_TEMP12_REG01() {
		this.getTEMP12_REG01("P04");
	}

	public void getP05_TEMP12_REG01() {
		this.getTEMP12_REG01("P05");
	}

	public void getP06_TEMP12_REG01() {
		this.getTEMP12_REG01("P06");
	}

	public void getP07_TEMP12_REG01() {
		this.getTEMP12_REG01("P07");
	}

	/**
	 * @状态 ok
	 * @功能 {经营分析区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年8月30日 下午7:04:35
	 */
	public void getJYFX_TEMP11_REG01() {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 经营分析区域定时任务开始执行...");
		String regCode = "TEMP11_REG01";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg("JYFX", temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					String subCode = targetCode.substring(0, 3);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null, null);
					List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, tarReg.getRoleOrgType());
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							String redisKey = RedisConstants.GDZB_JYFX + "-" + targetCode + "-" + orgCode;
							if (null != tarRegSql) {
								String sqlCode = redisSqlFactory.getJyfxSql(tarRegSql, orgCode, target.getDeptCode());
								try {
									RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
								} catch (Exception e) {
									log.error("System Message:经营分析定时任务" + targetCode + "指标SSQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message:经营分析定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	/*------------------------------机构对比区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {机构对比区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP06_REG01(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理机构对比区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP06_REG01";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					List<GroDimDetail> groDimDetailList = this.redisService.findGroDimDetailListByTarget("DATE_GROUP",
							targetCode);
					Target target = redisService.findTargetByCode(targetCode);
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql_Day = null;
					TarRegSql tarRegSql_Month = null;
					TarRegSql tarRegSql_Year = null;
					if (null != groDimDetailList && !groDimDetailList.isEmpty()) {
						for (GroDimDetail groDimDetail : groDimDetailList) {
							switch (groDimDetail.getGroDimCode()) {
							case dateType1:
								tarRegSql_Day = redisService.findTarRegSql(targetCode, temReg.getRegId(), dateGroupType,
										dateType1, null);
								break;
							case dateType2:
								tarRegSql_Month = redisService.findTarRegSql(targetCode, temReg.getRegId(),
										dateGroupType, dateType2, null);
								break;
							case dateType3:
								tarRegSql_Year = redisService.findTarRegSql(targetCode, temReg.getRegId(),
										dateGroupType, dateType3, null);
								break;
							}
						}
					}
					List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + orgCode + "-";
						// 无条件
						if (null != tarRegSql_Day) {
							String sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
									tarRegSql_Day.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType1, redisTime,
										this.redisService.getSqlData(sqlCode_Day));
							} catch (Exception e) {
								log.error("System Message:机构对比区域定时任务天维度" + targetCode + "指标SSQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						if (null != tarRegSql_Month) {
							String sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
									tarRegSql_Month.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType2, redisTime,
										this.redisService.getSqlData(sqlCode_Month));
							} catch (Exception e) {
								log.error("System Message:机构对比区域定时任务月维度" + targetCode + "指标SSQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						if (null != tarRegSql_Year) {
							String sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
									tarRegSql_Year.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType3, redisTime,
										this.redisService.getSqlData(sqlCode_Year));
							} catch (Exception e) {
								log.error("System Message:机构对比区域定时任务年维度" + targetCode + "指标SSQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						// 查询指标的筛选维度
						List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(subCode,
								"TEMP06", tarReg.getRegId(), targetCode);
						if (null != tarRegQueList && !tarRegQueList.isEmpty()) {
							// 有条件
							for (TarRegQue tarRegQue : tarRegQueList) {
								// 查询
								QueryDim queryDim = this.redisService
										.findQueryDimById(Integer.parseInt(tarRegQue.getQdId() + ""));
								List<String> queryDimValList = new ArrayList<String>();
								if (null != queryDim) {
									List<QueryDimDetail> queryDimDetailList = (List<QueryDimDetail>) queryDim
											.getQueryDimDetail();
									for (QueryDimDetail queryDimDetail : queryDimDetailList) {
										queryDimValList.add(queryDimDetail.getKeyCode());
									}
								}
								List<List<String>> valueList = null;
								if (null != queryDimValList && !queryDimValList.isEmpty()) {
									valueList = querySortList(queryDimValList);
								}
								if (null != tarRegSql_Day) {
									if (null != valueList) {
										for (List<String> valList : valueList) {
											String tarRegSql_Day_Key = queryDim.getQueryDimCode() + "_"
													+ listToString(valList);
											// 拼装sql
											String sqlCode_Day = redisSqlParameter.fxglRedisSqlParam(
													tarRegSql_Day.getSqlCode(), targetCode, tarRegSql_Day.getTrsId(),
													queryDim.getQueryDimCode(), valList);
											sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
													sqlCode_Day, orgCode, target.getDeptCode());
											try {
												RedisUtil.set(redisKey + dateType1 + "-" + tarRegSql_Day_Key, redisTime,
														this.redisService.getSqlData(sqlCode_Day));
											} catch (Exception e) {
												log.error("System Message:机构对比区域定时任务天维度" + targetCode
														+ "指标SSQL语句执行发生异常！");
												e.printStackTrace();
											}
										}
									}
								}
								if (null != tarRegSql_Month) {
									if (null != valueList) {
										for (List<String> valList : valueList) {
											String tarRegSql_Month_Key = queryDim.getQueryDimCode() + "_"
													+ listToString(valList);
											// 拼装sql
											String sqlCode_Month = redisSqlParameter.fxglRedisSqlParam(
													tarRegSql_Month.getSqlCode(), targetCode,
													tarRegSql_Month.getTrsId(), queryDim.getQueryDimCode(), valList);
											sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
													sqlCode_Month, orgCode, target.getDeptCode());
											try {
												RedisUtil.set(redisKey + dateType2 + "-" + tarRegSql_Month_Key,
														redisTime, this.redisService.getSqlData(sqlCode_Month));
											} catch (Exception e) {
												log.error("System Message:机构对比区域定时任务月维度" + targetCode
														+ "指标SSQL语句执行发生异常！");
												e.printStackTrace();
											}
										}
									}
								}
								if (null != tarRegSql_Year) {
									if (null != valueList) {
										for (List<String> valList : valueList) {
											String tarRegSql_Year_Key = queryDim.getQueryDimCode() + "_"
													+ listToString(valList);
											// 拼装sql
											String sqlCode_Year = redisSqlParameter.fxglRedisSqlParam(
													tarRegSql_Year.getSqlCode(), targetCode, tarRegSql_Year.getTrsId(),
													queryDim.getQueryDimCode(), valList);
											sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
													sqlCode_Year, orgCode, target.getDeptCode());
											try {
												RedisUtil.set(redisKey + dateType3 + "-" + tarRegSql_Year_Key,
														redisTime, this.redisService.getSqlData(sqlCode_Year));
											} catch (Exception e) {
												log.error("System Message:机构对比区域定时任务年维度" + targetCode
														+ "指标SSQL语句执行发生异常！");
												e.printStackTrace();
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message:机构对比区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT01_TEMP06_REG01() {
		this.getTEMP06_REG01("T01");
	}

	public void getT02_TEMP06_REG01() {
		this.getTEMP06_REG01("T02");
	}

	public void getT03_TEMP06_REG01() {
		this.getTEMP06_REG01("T03");
	}

	public void getT04_TEMP06_REG01() {
		this.getTEMP06_REG01("T04");
	}

	public void getT07_TEMP06_REG01() {
		this.getTEMP06_REG01("T07");
	}

	public void getT08_TEMP06_REG01() {
		this.getTEMP06_REG01("T08");
	}

	public void getT09_TEMP06_REG01() {
		this.getTEMP06_REG01("T09");
	}

	public void getT10_TEMP06_REG01() {
		this.getTEMP06_REG01("T10");
	}

	public void getP01_TEMP06_REG01() {
		this.getTEMP06_REG01("P01");
	}

	public void getP02_TEMP06_REG01() {
		this.getTEMP06_REG01("P02");
	}

	public void getP03_TEMP06_REG01() {
		this.getTEMP06_REG01("P03");
	}

	public void getP04_TEMP06_REG01() {
		this.getTEMP06_REG01("P04");
	}

	public void getP05_TEMP06_REG01() {
		this.getTEMP06_REG01("P05");
	}

	public void getP06_TEMP06_REG01() {
		this.getTEMP06_REG01("P06");
	}

	public void getP07_TEMP06_REG01() {
		this.getTEMP06_REG01("P07");
	}

	/*------------------------------导出清单区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {导出清单区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP07_REG01(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理导出清单区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP07_REG01";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					List<GroDimDetail> groDimDetailList = this.redisService.findGroDimDetailListByTarget("DATE_GROUP",
							targetCode);
					Target target = redisService.findTargetByCode(targetCode);
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql_Day = null;
					TarRegSql tarRegSql_Month = null;
					TarRegSql tarRegSql_Year = null;
					if (null != groDimDetailList && !groDimDetailList.isEmpty()) {
						for (GroDimDetail groDimDetail : groDimDetailList) {
							switch (groDimDetail.getGroDimCode()) {
							case dateType1:
								tarRegSql_Day = redisService.findTarRegSql(targetCode, temReg.getRegId(), dateGroupType,
										dateType1, null);
								break;
							case dateType2:
								tarRegSql_Month = redisService.findTarRegSql(targetCode, temReg.getRegId(),
										dateGroupType, dateType2, null);
								break;
							case dateType3:
								tarRegSql_Year = redisService.findTarRegSql(targetCode, temReg.getRegId(),
										dateGroupType, dateType3, null);
								break;
							}
						}
					}
					List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + orgCode + "-";
						if (null != groDimDetailList) {
							String sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
									tarRegSql_Day.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType1, redisTime,
										this.redisService.getSqlData(sqlCode_Day));
							} catch (Exception e) {
								log.error("System Message:导出清单区域定时任务天维度" + targetCode + "指标SSQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						if (null != groDimDetailList) {
							String sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
									tarRegSql_Month.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType2, redisTime,
										this.redisService.getSqlData(sqlCode_Month));
							} catch (Exception e) {
								log.error("System Message:导出清单区域定时任务月维度" + targetCode + "指标SSQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						if (null != groDimDetailList) {
							String sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
									tarRegSql_Year.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType3, redisTime,
										this.redisService.getSqlData(sqlCode_Year));
							} catch (Exception e) {
								log.error("System Message:导出清单区域定时任务年维度" + targetCode + "指标SSQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(subCode,
								"TEMP07", tarReg.getRegId(), targetCode);
						if (null != tarRegQueList && !tarRegQueList.isEmpty()) {
							// 有条件
							for (TarRegQue tarRegQue : tarRegQueList) {
								// 查询
								QueryDim queryDim = this.redisService
										.findQueryDimById(Integer.parseInt(tarRegQue.getQdId() + ""));
								List<String> queryDimValList = new ArrayList<String>();
								if (null != queryDim) {
									List<QueryDimDetail> queryDimDetailList = (List<QueryDimDetail>) queryDim
											.getQueryDimDetail();
									for (QueryDimDetail queryDimDetail : queryDimDetailList) {
										queryDimValList.add(queryDimDetail.getKeyCode());
									}
								}
								List<List<String>> valueList = null;
								if (null != queryDimValList && !queryDimValList.isEmpty()) {
									valueList = querySortList(queryDimValList);
								}
								if (null != tarRegSql_Day) {
									if (null != valueList) {
										for (List<String> valList : valueList) {
											String tarRegSql_Day_Key = queryDim.getQueryDimCode() + "_"
													+ listToString(valList);
											// 拼装sql
											String sqlCode_Day = redisSqlParameter.dcqdRedisSqlParam(
													tarRegSql_Day.getSqlCode(), targetCode, tarRegSql_Day.getTrsId(),
													queryDim.getQueryDimCode(), valList);
											sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
													sqlCode_Day, orgCode, target.getDeptCode());
											try {
												RedisUtil.set(redisKey + dateType1 + "-" + tarRegSql_Day_Key, redisTime,
														this.redisService.getSqlData(sqlCode_Day));
											} catch (Exception e) {
												log.error("System Message:导出清单区域定时任务天维度" + targetCode
														+ "指标SSQL语句执行发生异常！");
												e.printStackTrace();
											}
										}
									}
								}
								if (null != tarRegSql_Month) {
									if (null != valueList) {
										for (List<String> valList : valueList) {
											String tarRegSql_Month_Key = queryDim.getQueryDimCode() + "_"
													+ listToString(valList);
											// 拼装sql
											String sqlCode_Month = redisSqlParameter.dcqdRedisSqlParam(
													tarRegSql_Month.getSqlCode(), targetCode,
													tarRegSql_Month.getTrsId(), queryDim.getQueryDimCode(), valList);
											sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
													sqlCode_Month, orgCode, target.getDeptCode());
											try {
												RedisUtil.set(redisKey + dateType2 + "-" + tarRegSql_Month_Key,
														redisTime, this.redisService.getSqlData(sqlCode_Month));
											} catch (Exception e) {
												log.error("System Message:导出清单区域定时任务月维度" + targetCode
														+ "指标SSQL语句执行发生异常！");
												e.printStackTrace();
											}
										}
									}
								}
								if (null != tarRegSql_Year) {
									if (null != valueList) {
										for (List<String> valList : valueList) {
											String tarRegSql_Year_Key = queryDim.getQueryDimCode() + "_"
													+ listToString(valList);
											// 拼装sql
											String sqlCode_Year = redisSqlParameter.dcqdRedisSqlParam(
													tarRegSql_Year.getSqlCode(), targetCode, tarRegSql_Year.getTrsId(),
													queryDim.getQueryDimCode(), valList);
											sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
													sqlCode_Year, orgCode, target.getDeptCode());
											try {
												RedisUtil.set(redisKey + dateType3 + "-" + tarRegSql_Year_Key,
														redisTime, this.redisService.getSqlData(sqlCode_Year));
											} catch (Exception e) {
												log.error(
														"System Message:导出清单区域定时任务年维度" + targetCode + "指标SQL语句执行发生异常！");
												e.printStackTrace();
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 导出清单区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT01_TEMP07_REG01() {
		this.getTEMP07_REG01("T01");
	}

	public void getT02_TEMP07_REG01() {
		this.getTEMP07_REG01("T02");
	}

	public void getT03_TEMP07_REG01() {
		this.getTEMP07_REG01("T03");
	}

	public void getT04_TEMP07_REG01() {
		this.getTEMP07_REG01("T04");
	}

	public void getT07_TEMP07_REG01() {
		this.getTEMP07_REG01("T07");
	}

	public void getT08_TEMP07_REG01() {
		this.getTEMP07_REG01("T08");
	}

	public void getT09_TEMP07_REG01() {
		this.getTEMP07_REG01("T09");
	}

	public void getT10_TEMP07_REG01() {
		this.getTEMP07_REG01("T10");
	}

	public void getP01_TEMP07_REG01() {
		this.getTEMP07_REG01("P01");
	}

	public void getP02_TEMP07_REG01() {
		this.getTEMP07_REG01("P02");
	}

	public void getP03_TEMP07_REG01() {
		this.getTEMP07_REG01("P03");
	}

	public void getP04_TEMP07_REG01() {
		this.getTEMP07_REG01("P04");
	}

	public void getP05_TEMP07_REG01() {
		this.getTEMP07_REG01("P05");
	}

	public void getP06_TEMP07_REG01() {
		this.getTEMP07_REG01("P06");
	}

	public void getP07_TEMP07_REG01() {
		this.getTEMP07_REG01("P07");
	}

	/**
	 * @状态 ok
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public static List<List<String>> querySortList(List<String> list) {
		List<List<String>> result = new ArrayList<List<String>>();
		long size = (long) Math.pow(2, list.size());
		List<String> combine;
		for (long l = 0L; l < size; l++) {
			combine = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				if ((l >>> i & 1) == 1)
					combine.add(list.get(i));
			}
			result.add(combine);
		}
		result.remove(result.get(0));
		return result;
	}

	/**
	 * @状态 ok
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public static String listToString(List<String> list) {
		return org.apache.commons.lang.StringUtils.join(list.toArray(), ",");
	}
}
