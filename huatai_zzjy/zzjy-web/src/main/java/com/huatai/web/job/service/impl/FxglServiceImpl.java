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
import com.huatai.web.job.service.FxglService;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.thrift.service.RedisService;

/**
 * @功能 {分析管理数据缓存服务}
 * @作者 MaxBill
 * @时间 2017年9月1日 下午3:08:02
 */
@Service
public class FxglServiceImpl implements FxglService {

	Logger log = Logger.getLogger(GdzbServiceImpl.class);

	private RedisService redisService;

	private RedisSqlFactory redisSqlFactory;

	private RedisSqlParameter redisSqlParameter;

	public FxglServiceImpl() {
		redisService = SpringUtil.getBean(RedisService.class);
		redisSqlFactory = SpringUtil.getBean(RedisSqlFactory.class);
		redisSqlParameter = SpringUtil.getBean(RedisSqlParameter.class);
	}

	private static final Integer redisTime = 0;

	private static final String orgGroupType = "ORG_GROUP";

	private static final String dateGroupType = "DATE_GROUP";

	private static final String dateType1 = "DAY";

	private static final String dateType2 = "MONTH";

	private static final String dateType3 = "YEAR";

	private static final String tempCode = "TEMP03";

	/*------------------------------常用指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {常用指标区域定时任务}39
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP03_REG01(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理常用指标区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP03_REG01";
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
							// 无条件的
							switch (groDimDetail.getGroDimCode()) {
							case dateType1:
								tarRegSql_Day = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null,
										dateType1);
							case dateType2:
								tarRegSql_Month = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null,
										dateType2);
								break;
							case dateType3:
								tarRegSql_Year = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null,
										dateType3);
								break;
							}
							// 有条件的
						}
						List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, groupDetail);
						for (HashMap map : orgList) {
							String orgCode = (String) map.get("CODE");
							// 无条件的
							String redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + orgCode + "-";
							if (null != tarRegSql_Day) {
								String sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
										tarRegSql_Day.getSqlCode(), orgCode, target.getDeptCode());
								try {
									RedisUtil.set(redisKey + dateType1, redisTime,
											this.redisService.getSqlData(sqlCode_Day));
								} catch (Exception e) {
									e.printStackTrace();
									log.error("System Message: 常用指标区域定时任务天维度" + targetCode + "指标SQL语句执行发生异常！");
								}
							}
							if (null != tarRegSql_Month) {
								String sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
										tarRegSql_Month.getSqlCode(), orgCode, target.getDeptCode());
								try {
									RedisUtil.set(redisKey + dateType2, redisTime,
											this.redisService.getSqlData(sqlCode_Month));
								} catch (Exception e) {
									e.printStackTrace();
									log.error("System Message: 常用指标区域定时任务月维度" + targetCode + "指标SQL语句执行发生异常！");
								}
							}
							if (null != tarRegSql_Year) {
								String sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
										tarRegSql_Year.getSqlCode(), orgCode, target.getDeptCode());
								try {
									RedisUtil.set(redisKey + dateType3, redisTime,
											this.redisService.getSqlData(sqlCode_Year));
								} catch (Exception e) {
									e.printStackTrace();
									log.error("System Message: 常用指标区域定时任务年维度" + targetCode + "指标SQL语句执行发生异常！");
								}
							}
							// 查询指标的筛选维度
							List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(
									subCode, tempCode, tarReg.getRegId(), targetCode);
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
												// 拼装key
												String tarRegSql_Day_Key = queryDim.getQueryDimCode() + "_"
														+ listToString(valList);
												// 拼装sql
												String sqlCode_Day = redisSqlParameter.fxglRedisSqlParam(
														tarRegSql_Day.getSqlCode(), targetCode,
														tarRegSql_Day.getTrsId(), queryDim.getQueryDimCode(), valList);
												sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
														sqlCode_Day, orgCode, target.getDeptCode());
												try {
													RedisUtil.set(redisKey + dateType1 + "-" + tarRegSql_Day_Key,
															redisTime, this.redisService.getSqlData(sqlCode_Day));
												} catch (Exception e) {
													e.printStackTrace();
													log.error("System Message: 常用指标区域定时任务天维度" + targetCode
															+ "指标SQL语句执行发生异常！");
												}
											}
										}
									}
									if (null != tarRegSql_Month) {
										if (null != valueList) {
											for (List<String> valList : valueList) {
												// 拼装key
												String tarRegSql_Month_Key = queryDim.getQueryDimCode() + "_"
														+ listToString(valList);
												// 拼装sql
												String sqlCode_Month = redisSqlParameter.fxglRedisSqlParam(
														tarRegSql_Month.getSqlCode(), targetCode,
														tarRegSql_Month.getTrsId(), queryDim.getQueryDimCode(),
														valList);
												sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
														sqlCode_Month, orgCode, target.getDeptCode());
												try {
													RedisUtil.set(redisKey + dateType2 + "-" + tarRegSql_Month_Key,
															redisTime, this.redisService.getSqlData(sqlCode_Month));
												} catch (Exception e) {
													e.printStackTrace();
													log.error("System Message: 常用指标区域定时任务月维度" + targetCode
															+ "指标SQL语句执行发生异常！");
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
														tarRegSql_Year.getSqlCode(), targetCode,
														tarRegSql_Year.getTrsId(), queryDim.getQueryDimCode(), valList);
												sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
														sqlCode_Year, orgCode, target.getDeptCode());
												try {
													RedisUtil.set(redisKey + dateType3 + "-" + tarRegSql_Year_Key,
															redisTime, this.redisService.getSqlData(sqlCode_Year));
												} catch (Exception e) {
													e.printStackTrace();
													log.error("System Message: 常用指标区域定时任务年维度" + targetCode
															+ "指标SQL语句执行发生异常！");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 常用指标区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT01_TEMP03_REG01() {
		this.getTEMP03_REG01("T01");
	}

	public void getT02_TEMP03_REG01() {
		this.getTEMP03_REG01("T02");
	}

	public void getT03_TEMP03_REG01() {
		this.getTEMP03_REG01("T03");
	}

	public void getT04_TEMP03_REG01() {
		this.getTEMP03_REG01("T04");
	}

	public void getT07_TEMP03_REG01() {
		this.getTEMP03_REG01("T07");
	}

	public void getT08_TEMP03_REG01() {
		this.getTEMP03_REG01("T08");
	}

	public void getT09_TEMP03_REG01() {
		this.getTEMP03_REG01("T09");
	}

	public void getT10_TEMP03_REG01() {
		this.getTEMP03_REG01("T10");
	}

	public void getP01_TEMP03_REG01() {
		this.getTEMP03_REG01("P01");
	}

	public void getP02_TEMP03_REG01() {
		this.getTEMP03_REG01("P02");
	}

	public void getP03_TEMP03_REG01() {
		this.getTEMP03_REG01("P03");
	}

	public void getP04_TEMP03_REG01() {
		this.getTEMP03_REG01("P04");
	}

	public void getP05_TEMP03_REG01() {
		this.getTEMP03_REG01("P05");
	}

	public void getP06_TEMP03_REG01() {
		this.getTEMP03_REG01("P06");
	}

	public void getP07_TEMP03_REG01() {
		this.getTEMP03_REG01("P07");
	}

	/*------------------------------一级指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {一级指标区域定时任务}40
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP03_REG02(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理一级指标区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP03_REG02";
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
						String redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + orgCode + "-";
						if (null != tarRegSql_Day) {
							String sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
									tarRegSql_Day.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType1, redisTime,
										this.redisService.getSqlData(sqlCode_Day));
							} catch (Exception e) {
								log.error("System Message: 一级指标区域定时任务天维度" + targetCode + "指标SQL语句发生异常！");
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
								log.error("System Message: 一级指标区域定时任务月维度" + targetCode + "指标SQL语句发生异常！");
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
								log.error("System Message: 一级指标区域定时任务年维度" + targetCode + "指标SQL语句发生异常！");
								e.printStackTrace();
							}
						}
						// 查询指标的筛选维度
						List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(subCode,
								tempCode, tarReg.getRegId(), targetCode);
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
												log.error(
														"System Message: 一级指标区域定时任务天维度" + targetCode + "指标SQL语句发生异常！");
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
												log.error(
														"System Message: 一级指标区域定时任务月维度" + targetCode + "指标SQL语句发生异常！");
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
												log.error(
														"System Message: 一级指标区域定时任务年维度" + targetCode + "指标SQL语句发生异常！");
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
				log.error("System Message: 一级指标区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT01_TEMP03_REG02() {
		this.getTEMP03_REG02("T01");
	}

	public void getT02_TEMP03_REG02() {
		this.getTEMP03_REG02("T02");
	}

	public void getT03_TEMP03_REG02() {
		this.getTEMP03_REG02("T03");
	}

	public void getT04_TEMP03_REG02() {
		this.getTEMP03_REG02("T04");
	}

	public void getT07_TEMP03_REG02() {
		this.getTEMP03_REG02("T07");
	}

	public void getT08_TEMP03_REG02() {
		this.getTEMP03_REG02("T08");
	}

	public void getT09_TEMP03_REG02() {
		this.getTEMP03_REG02("T09");
	}

	public void getT10_TEMP03_REG02() {
		this.getTEMP03_REG02("T10");
	}

	public void getP01_TEMP03_REG02() {
		this.getTEMP03_REG02("P01");
	}

	public void getP02_TEMP03_REG02() {
		this.getTEMP03_REG02("P02");
	}

	public void getP03_TEMP03_REG02() {
		this.getTEMP03_REG02("P03");
	}

	public void getP04_TEMP03_REG02() {
		this.getTEMP03_REG02("P04");
	}

	public void getP05_TEMP03_REG02() {
		this.getTEMP03_REG02("P05");
	}

	public void getP06_TEMP03_REG02() {
		this.getTEMP03_REG02("P06");
	}

	public void getP07_TEMP03_REG02() {
		this.getTEMP03_REG02("P07");
	}

	/*------------------------------二级指标区域------------------------------*/
	/**
	 * @状态 ok
	 * @功能 {二级指标区域定时任务}41
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP03_REG03(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理二级指标区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP03_REG03";
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
						String redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + orgCode + "-";
						if (null != tarRegSql_Day) {
							String sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
									tarRegSql_Day.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType1, redisTime,
										this.redisService.getSqlData(sqlCode_Day));
							} catch (Exception e) {
								log.error("System Message: 二级指标区域定时任务天维度" + targetCode + "指标SQL语句执行发生异常！");
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
								log.error("System Message: 二级指标区域定时任务月维度" + targetCode + "指标SQL语句执行发生异常！");
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
								log.error("System Message: 二级指标区域定时任务年维度" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						// 如果是保费达成指标，特殊处理
						if (subCode.equals("T01")) {
							// 查询指标的筛选维度
							List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(
									subCode, tempCode, tarReg.getRegId(), targetCode);
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
											List<String> valList = valueList.get(valueList.size() - 1);
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
												log.error("System Message: 二级指标区域定时任务天维度" + targetCode
														+ "指标SQL语句执行发生异常！");
												e.printStackTrace();
											}
										}
									}
									if (null != tarRegSql_Month) {
										if (null != valueList) {
											List<String> valList = valueList.get(valueList.size() - 1);
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
												log.error("System Message: 二级指标区域定时任务月维度" + targetCode
														+ "指标SQL语句执行发生异常！");
												e.printStackTrace();
											}
										}
									}
									if (null != tarRegSql_Year) {
										if (null != valueList) {
											List<String> valList = valueList.get(valueList.size() - 1);
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
												log.error("System Message: 二级指标区域定时任务年维度" + targetCode
														+ "指标SQL语句执行发生异常！");
												e.printStackTrace();
											}
										}
									}
								}
							}

						} else {
							// 查询指标的筛选维度
							List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(
									subCode, tempCode, tarReg.getRegId(), targetCode);
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
														tarRegSql_Day.getSqlCode(), targetCode,
														tarRegSql_Day.getTrsId(), queryDim.getQueryDimCode(), valList);
												sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
														sqlCode_Day, orgCode, target.getDeptCode());
												try {
													RedisUtil.set(redisKey + dateType1 + "-" + tarRegSql_Day_Key,
															redisTime, this.redisService.getSqlData(sqlCode_Day));
												} catch (Exception e) {
													log.error("System Message: 二级指标区域定时任务天维度" + targetCode
															+ "指标SQL语句执行发生异常！");
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
														tarRegSql_Month.getTrsId(), queryDim.getQueryDimCode(),
														valList);
												sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
														sqlCode_Month, orgCode, target.getDeptCode());
												try {
													RedisUtil.set(redisKey + dateType2 + "-" + tarRegSql_Month_Key,
															redisTime, this.redisService.getSqlData(sqlCode_Month));
												} catch (Exception e) {
													log.error("System Message: 二级指标区域定时任务月维度" + targetCode
															+ "指标SQL语句执行发生异常！");
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
														tarRegSql_Year.getSqlCode(), targetCode,
														tarRegSql_Year.getTrsId(), queryDim.getQueryDimCode(), valList);
												sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
														sqlCode_Year, orgCode, target.getDeptCode());
												try {
													RedisUtil.set(redisKey + dateType3 + "-" + tarRegSql_Year_Key,
															redisTime, this.redisService.getSqlData(sqlCode_Year));
												} catch (Exception e) {
													log.error("System Message: 二级指标区域定时任务年维度" + targetCode
															+ "指标SQL语句执行发生异常！");
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 二级指标区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT01_TEMP03_REG03() {
		this.getTEMP03_REG03("T01");
	}

	public void getT02_TEMP03_REG03() {
		this.getTEMP03_REG03("T02");
	}

	public void getT03_TEMP03_REG03() {
		this.getTEMP03_REG03("T03");
	}

	public void getT04_TEMP03_REG03() {
		this.getTEMP03_REG03("T04");
	}

	public void getT07_TEMP03_REG03() {
		this.getTEMP03_REG03("T07");
	}

	public void getT08_TEMP03_REG03() {
		this.getTEMP03_REG03("T08");
	}

	public void getT09_TEMP03_REG03() {
		this.getTEMP03_REG03("T09");
	}

	public void getT10_TEMP03_REG03() {
		this.getTEMP03_REG03("T10");
	}

	public void getP01_TEMP03_REG03() {
		this.getTEMP03_REG03("P01");
	}

	public void getP02_TEMP03_REG03() {
		this.getTEMP03_REG03("P02");
	}

	public void getP03_TEMP03_REG03() {
		this.getTEMP03_REG03("P03");
	}

	public void getP04_TEMP03_REG03() {
		this.getTEMP03_REG03("P04");
	}

	public void getP05_TEMP03_REG03() {
		this.getTEMP03_REG03("P05");
	}

	public void getP06_TEMP03_REG03() {
		this.getTEMP03_REG03("P06");
	}

	public void getP07_TEMP03_REG03() {
		this.getTEMP03_REG03("P07");
	}

	/*------------------------------排名指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {排名指标区域定时任务}42
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP03_REG04(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理排名指标区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP03_REG04";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					List<GroDimDetail> groDimDetailList = this.redisService.findGroDimDetailListByTarget("DATE_GROUP",
							targetCode);
					Target target = redisService.findTargetByCode(targetCode);
					String deptCode = target.getDeptCode();
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql_Day = null;
					TarRegSql tarRegSql_Month = null;
					TarRegSql tarRegSql_Year = null;
					if (null != groDimDetailList && !groDimDetailList.isEmpty()) {
						for (GroDimDetail groDimDetail : groDimDetailList) {
							switch (groDimDetail.getGroDimCode()) {
							case dateType1:
								tarRegSql_Day = redisService.findTarRegSql(targetCode, temReg.getRegId(), orgGroupType,
										null, dateType1);
								break;
							case dateType2:
								tarRegSql_Month = redisService.findTarRegSql(targetCode, temReg.getRegId(),
										orgGroupType, null, dateType2);
								break;
							case dateType3:
								tarRegSql_Year = redisService.findTarRegSql(targetCode, temReg.getRegId(), orgGroupType,
										null, dateType3);
								break;
							}
						}
					}
					List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + orgCode + "-";
						if (null != tarRegSql_Day) {
							String sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_02,
									tarRegSql_Day.getSqlCode(), orgCode, deptCode);
							try {
								RedisUtil.set(redisKey + dateType1, redisTime,
										this.redisService.getSqlData(sqlCode_Day));
							} catch (Exception e) {
								log.error("System Message: 排名指标区域定时任务天维度" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						if (null != tarRegSql_Month) {
							String sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_02,
									tarRegSql_Month.getSqlCode(), orgCode, deptCode);
							try {
								RedisUtil.set(redisKey + dateType2, redisTime,
										this.redisService.getSqlData(sqlCode_Month));
							} catch (Exception e) {
								log.error("System Message: 排名指标区域定时任务月维度" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						if (null != tarRegSql_Year) {
							String sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_02,
									tarRegSql_Year.getSqlCode(), orgCode, deptCode);
							try {
								RedisUtil.set(redisKey + dateType3, redisTime,
										this.redisService.getSqlData(sqlCode_Year));
							} catch (Exception e) {
								log.error("System Message: 排名指标区域定时任务年维度" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						// 查询指标的筛选维度
						List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(subCode,
								tempCode, tarReg.getRegId(), targetCode);
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
											sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_02,
													sqlCode_Day, orgCode, deptCode);
											try {
												RedisUtil.set(redisKey + dateType1 + "-" + tarRegSql_Day_Key, redisTime,
														this.redisService.getSqlData(sqlCode_Day));
											} catch (Exception e) {
												log.error("System Message: 排名指标区域定时任务天维度" + targetCode
														+ "指标SQL语句执行发生异常！");
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
											sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_02,
													sqlCode_Month, orgCode, deptCode);
											try {
												RedisUtil.set(redisKey + dateType2 + "-" + tarRegSql_Month_Key,
														redisTime, this.redisService.getSqlData(sqlCode_Month));
											} catch (Exception e) {
												log.error("System Message: 排名指标区域定时任务月维度" + targetCode
														+ "指标SQL语句执行发生异常！");
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
											sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_02,
													sqlCode_Year, orgCode, deptCode);
											try {
												RedisUtil.set(redisKey + dateType3 + "-" + tarRegSql_Year_Key,
														redisTime, this.redisService.getSqlData(sqlCode_Year));
											} catch (Exception e) {
												log.error("System Message: 排名指标区域定时任务年维度" + targetCode
														+ "指标SQL语句执行发生异常！");
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
				log.error("System Message: 排名指标区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT01_TEMP03_REG04() {
		this.getTEMP03_REG04("T01");
	}

	public void getT02_TEMP03_REG04() {
		this.getTEMP03_REG04("T02");
	}

	public void getT03_TEMP03_REG04() {
		this.getTEMP03_REG04("T03");
	}

	public void getT04_TEMP03_REG04() {
		this.getTEMP03_REG04("T04");
	}

	public void getT07_TEMP03_REG04() {
		this.getTEMP03_REG04("T07");
	}

	public void getT08_TEMP03_REG04() {
		this.getTEMP03_REG04("T08");
	}

	public void getT09_TEMP03_REG04() {
		this.getTEMP03_REG04("T09");
	}

	public void getT10_TEMP03_REG04() {
		this.getTEMP03_REG04("T10");
	}

	public void getP01_TEMP03_REG04() {
		this.getTEMP03_REG04("P01");
	}

	public void getP02_TEMP03_REG04() {
		this.getTEMP03_REG04("P02");
	}

	public void getP03_TEMP03_REG04() {
		this.getTEMP03_REG04("P03");
	}

	public void getP04_TEMP03_REG04() {
		this.getTEMP03_REG04("P04");
	}

	public void getP05_TEMP03_REG04() {
		this.getTEMP03_REG04("P05");
	}

	public void getP06_TEMP03_REG04() {
		this.getTEMP03_REG04("P06");
	}

	public void getP07_TEMP03_REG04() {
		this.getTEMP03_REG04("P07");
	}

	/*------------------------------列表指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {列表指标区域定时任务}43
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP03_REG05(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理列表指标区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP03_REG05";
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
								tarRegSql_Day = redisService.findTarRegSql(targetCode, temReg.getRegId(), orgGroupType,
										null, dateType1);
								break;
							case dateType2:
								tarRegSql_Month = redisService.findTarRegSql(targetCode, temReg.getRegId(),
										orgGroupType, null, dateType2);
								break;
							case dateType3:
								tarRegSql_Year = redisService.findTarRegSql(targetCode, temReg.getRegId(), orgGroupType,
										null, dateType3);
								break;
							}
						}
					}
					List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + orgCode + "-";
						if (null != tarRegSql_Day) {
							String sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
									tarRegSql_Day.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType1, redisTime,
										this.redisService.getSqlData(sqlCode_Day));
							} catch (Exception e) {
								log.error("System Message: 列表指标区域定时任务天维度" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						if (null != tarRegSql_Month) {
							String sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
									tarRegSql_Month.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType2, redisTime,
										this.redisService.getSqlData(sqlCode_Month));
							} catch (Exception e) {
								log.error("System Message: 列表指标区域定时任务月维度" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						if (null != tarRegSql_Year) {
							String sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
									tarRegSql_Year.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType3, redisTime,
										this.redisService.getSqlData(sqlCode_Year));
							} catch (Exception e) {
								log.error("System Message: 列表指标区域定时任务年维度" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						// 查询指标的筛选维度
						List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(subCode,
								tempCode, tarReg.getRegId(), targetCode);
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
											sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
													sqlCode_Day, orgCode, target.getDeptCode());
											try {
												RedisUtil.set(redisKey + dateType1 + "-" + tarRegSql_Day_Key, redisTime,
														this.redisService.getSqlData(sqlCode_Day));
											} catch (Exception e) {
												log.error("System Message: 列表指标区域定时任务天维度" + targetCode
														+ "指标SQL语句执行发生异常！");
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
											sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
													sqlCode_Month, orgCode, target.getDeptCode());
											try {
												RedisUtil.set(redisKey + dateType2 + "-" + tarRegSql_Month_Key,
														redisTime, this.redisService.getSqlData(sqlCode_Month));
											} catch (Exception e) {
												log.error("System Message: 列表指标区域定时任务月维度" + targetCode
														+ "指标SQL语句执行发生异常！");
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
											sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
													sqlCode_Year, orgCode, target.getDeptCode());
											try {
												RedisUtil.set(redisKey + dateType3 + "-" + tarRegSql_Year_Key,
														redisTime, this.redisService.getSqlData(sqlCode_Year));
											} catch (Exception e) {
												log.error("System Message: 列表指标区域定时任务年维度" + targetCode
														+ "指标SQL语句执行发生异常！");
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
				log.error("System Message: 列表指标区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT01_TEMP03_REG05() {
		this.getTEMP03_REG05("T01");
	}

	public void getT02_TEMP03_REG05() {
		this.getTEMP03_REG05("T02");
	}

	public void getT03_TEMP03_REG05() {
		this.getTEMP03_REG05("T03");
	}

	public void getT04_TEMP03_REG05() {
		this.getTEMP03_REG05("T04");
	}

	public void getT07_TEMP03_REG05() {
		this.getTEMP03_REG05("T07");
	}

	public void getT08_TEMP03_REG05() {
		this.getTEMP03_REG05("T08");
	}

	public void getP01_TEMP03_REG05() {
		this.getTEMP03_REG05("P01");
	}

	public void getP02_TEMP03_REG05() {
		this.getTEMP03_REG05("P02");
	}

	public void getP03_TEMP03_REG05() {
		this.getTEMP03_REG05("P03");
	}

	public void getP04_TEMP03_REG05() {
		this.getTEMP03_REG05("P04");
	}

	public void getP05_TEMP03_REG05() {
		this.getTEMP03_REG05("P05");
	}

	public void getP06_TEMP03_REG05() {
		this.getTEMP03_REG05("P06");
	}

	public void getP07_TEMP03_REG05() {
		this.getTEMP03_REG05("P07");
	}

	/*------------------------------特殊指标区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {特殊指标区域定时任务}44
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP03_REG06(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理特殊指标区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP03_REG06";
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
								tarRegSql_Day = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null,
										dateType1);
								break;
							case dateType2:
								tarRegSql_Month = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null,
										dateType2);
								break;
							case dateType3:
								tarRegSql_Year = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null,
										dateType3);
								break;
							}
						}
					}
					List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + orgCode + "-";
						if (null != groDimDetailList) {
							String sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_01,
									tarRegSql_Day.getSqlCode(), orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey + dateType1, redisTime,
										this.redisService.getSqlData(sqlCode_Day));
							} catch (Exception e) {
								log.error("System Message: 特殊指标区域定时任务天维度" + targetCode + "指标SQL语句执行发生异常！");
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
								log.error("System Message: 特殊指标区域定时任务月维度" + targetCode + "指标SQL语句执行发生异常！");
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
								log.error("System Message: 特殊指标区域定时任务年维度" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						// 查询指标的筛选维度
						List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(subCode,
								tempCode, tarReg.getRegId(), targetCode);
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
												log.error("System Message: 特殊指标区域定时任务天维度" + targetCode
														+ "指标SQL语句执行发生异常！");
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
												log.error("System Message: 特殊指标区域定时任务月维度" + targetCode
														+ "指标SQL语句执行发生异常！");
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
												log.error("System Message: 特殊指标区域定时任务年维度" + targetCode
														+ "指标SQL语句执行发生异常！");
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
				log.error("System Message: 特殊指标区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT03_TEMP03_REG06() {
		this.getTEMP03_REG06("T03");
	}

	public void getT04_TEMP03_REG06() {
		this.getTEMP03_REG06("T04");
	}

	public void getT07_TEMP03_REG06() {
		this.getTEMP03_REG06("T07");
	}

	public void getT08_TEMP03_REG06() {
		this.getTEMP03_REG06("T08");
	}

	public void getP01_TEMP03_REG06() {
		this.getTEMP03_REG06("P01");
	}

	public void getP02_TEMP03_REG06() {
		this.getTEMP03_REG06("P02");
	}

	/*------------------------------保费列表区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {列表指标区域定时任务}43
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午12:52:20
	 */
	public void getTEMP03_REG07(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 分析管理保费列表区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP03_REG07";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					if (!targetCode.equals("T10100")) {
						List<GroDimDetail> groDimDetailList = this.redisService
								.findGroDimDetailListByTarget("DATE_GROUP", targetCode);
						Target target = redisService.findTargetByCode(targetCode);
						String groupDetail = tarReg.getRoleOrgType();
						TarRegSql tarRegSql_Day = null;
						TarRegSql tarRegSql_Month = null;
						TarRegSql tarRegSql_Year = null;
						if (null != groDimDetailList && !groDimDetailList.isEmpty()) {
							for (GroDimDetail groDimDetail : groDimDetailList) {
								switch (groDimDetail.getGroDimCode()) {
								case dateType1:
									tarRegSql_Day = redisService.findTarRegSql(targetCode, temReg.getRegId(),
											orgGroupType, null, dateType1);
									break;
								case dateType2:
									tarRegSql_Month = redisService.findTarRegSql(targetCode, temReg.getRegId(),
											orgGroupType, null, dateType2);
									break;
								case dateType3:
									tarRegSql_Year = redisService.findTarRegSql(targetCode, temReg.getRegId(),
											orgGroupType, null, dateType3);
									break;
								}
							}
						}
						List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, groupDetail);
						for (HashMap map : orgList) {
							String orgCode = (String) map.get("CODE");
							String redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + orgCode + "-";
							if (null != tarRegSql_Day) {
								String sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
										tarRegSql_Day.getSqlCode(), orgCode, target.getDeptCode());
								try {
									RedisUtil.set(redisKey + dateType1, redisTime,
											this.redisService.getSqlData(sqlCode_Day));
								} catch (Exception e) {
									log.error("System Message: 保费列表区域定时任务天维度" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
							if (null != tarRegSql_Month) {
								String sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
										tarRegSql_Month.getSqlCode(), orgCode, target.getDeptCode());
								try {
									RedisUtil.set(redisKey + dateType2, redisTime,
											this.redisService.getSqlData(sqlCode_Month));
								} catch (Exception e) {
									log.error("System Message: 保费列表区域定时任务月维度" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
							if (null != tarRegSql_Year) {
								String sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
										tarRegSql_Year.getSqlCode(), orgCode, target.getDeptCode());
								try {
									RedisUtil.set(redisKey + dateType3, redisTime,
											this.redisService.getSqlData(sqlCode_Year));
								} catch (Exception e) {
									log.error("System Message: 保费列表区域定时任务年维度" + targetCode + "指标SQL语句执行发生异常！");
									e.printStackTrace();
								}
							}
							// 查询指标的筛选维度
							List<TarRegQue> tarRegQueList = this.redisService.findTarRegQueBySubAndTempAndRegAndTar(
									subCode, tempCode, tarReg.getRegId(), targetCode);
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
														tarRegSql_Day.getSqlCode(), targetCode,
														tarRegSql_Day.getTrsId(), queryDim.getQueryDimCode(), valList);
												sqlCode_Day = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
														sqlCode_Day, orgCode, target.getDeptCode());
												try {
													RedisUtil.set(redisKey + dateType1 + "-" + tarRegSql_Day_Key,
															redisTime, this.redisService.getSqlData(sqlCode_Day));
												} catch (Exception e) {
													log.error("System Message: 保费列表区域定时任务天维度" + targetCode
															+ "指标SQL语句执行发生异常！");
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
														tarRegSql_Month.getTrsId(), queryDim.getQueryDimCode(),
														valList);
												sqlCode_Month = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
														sqlCode_Month, orgCode, target.getDeptCode());
												try {
													RedisUtil.set(redisKey + dateType2 + "-" + tarRegSql_Month_Key,
															redisTime, this.redisService.getSqlData(sqlCode_Month));
												} catch (Exception e) {
													log.error("System Message: 保费列表区域定时任务月维度" + targetCode
															+ "指标SQL语句执行发生异常！");
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
														tarRegSql_Year.getSqlCode(), targetCode,
														tarRegSql_Year.getTrsId(), queryDim.getQueryDimCode(), valList);
												sqlCode_Year = redisSqlFactory.getZtfxSql(RedisSqlConstants.FXGL_03,
														sqlCode_Year, orgCode, target.getDeptCode());
												try {
													RedisUtil.set(redisKey + dateType3 + "-" + tarRegSql_Year_Key,
															redisTime, this.redisService.getSqlData(sqlCode_Year));
												} catch (Exception e) {
													log.error("System Message: 保费列表区域定时任务年维度" + targetCode
															+ "指标SQL语句执行发生异常！");
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 保费列表区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getT09_TEMP03_REG07() {
		this.getTEMP03_REG07("T09");
	}

	public void getT10_TEMP03_REG07() {
		this.getTEMP03_REG07("T10");
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

	public static void main(String args[]) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		for (List<String> objList : querySortList(list)) {
			System.out.println(objList.toString());
		}
	}
}
