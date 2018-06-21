package com.huatai.web.job.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.util.RedisUtil;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.job.constants.RedisConstants;
import com.huatai.web.job.factory.RedisSqlFactory;
import com.huatai.web.job.service.ZyjkService;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.thrift.service.RedisService;

/**
 * @功能 {首页达时报数据缓存服务}
 * @作者 MaxBill
 * @时间 2017年9月1日 下午3:08:02
 */
@Service
public class ZyjkServiceImpl implements ZyjkService {

	Logger log = Logger.getLogger(GdzbServiceImpl.class);

	private RedisService redisService;

	private RedisSqlFactory redisSqlFactory;

	public ZyjkServiceImpl() {
		redisService = SpringUtil.getBean(RedisService.class);
		redisSqlFactory = SpringUtil.getBean(RedisSqlFactory.class);
	}

	private static final Integer redisTime = 0;

	private static final String dateGroupType = "DATE_GROUP";

	private static final String orgGroupType = "ORG_GROUP";

	private static final String dateType2 = "MONTH";

	/*------------------------------1.图形区域------------------------------*/

	/**
	 * @状态 ok
	 * @功能 {达时报图形区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午11:39:50
	 */
	public void getDAS0101(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 主页达时报图形区域[" + subCode + "]定时任务开始执行...");
		String regCode = "DAS0101";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), dateGroupType,
							dateType2, null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.DSB_TX + "-" + targetCode + "-" + orgCode;
						if (null != tarRegSql) {
							String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 达时报图形区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 达时报图形区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getDAS0101_DAS0101() {
		this.getDAS0101("DAS0101");
	}

	/*------------------------------2.排名区域------------------------------*/

	/**
	 * @状态
	 * @功能 {达时报排名区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午11:39:50
	 */
	public void getDAS010101(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 主页达时报排名区域[" + subCode + "]定时任务开始执行...");
		String regCode = "DAS010101";
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
						String redisKey = RedisConstants.DSB_PM + "-" + targetCode + "-" + orgCode;
						if (null != tarRegSql) {
							String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 达时报排名区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 达时报排名区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getDAS0101_DAS010101() {
		this.getDAS010101("DAS0101");
	}

	/*------------------------------3.人力区域------------------------------*/

	/**
	 * @状态
	 * @功能 {达时报人力区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午11:39:50
	 */
	public void getDAS0102(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 主页达时报人力区域[" + subCode + "]定时任务开始执行...");
		String regCode = "DAS0102";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), dateGroupType,
							dateType2, null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode,
							tarReg.getRoleOrgType());
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.DSB_RL + "-" + targetCode + "-" + orgCode;
						if (null != tarRegSql) {
							String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 达时报人力区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 达时报人力区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getDAS0102_DAS0102() {
		this.getDAS0102("DAS0102");
	}

	/*------------------------------4.产品区域------------------------------*/

	/**
	 * @状态
	 * @功能 {达时报产品区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午11:39:50
	 */
	public void getDAS0103(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 主页达时报产品区域[" + subCode + "]定时任务开始执行...");
		String regCode = "DAS0103";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					String parentCode = target.getParentCode();
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null, null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						if (null != tarRegSql) {
							String redisKey = RedisConstants.DSB_CP + "-" + targetCode + "-" + orgCode;
							String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode, target.getDeptCode());
							sqlCode = redisSqlFactory.replaceProduct(sqlCode, null);
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 达时报产品区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
						List<Map<String, Object>> productList = this.redisService.getTopThreeProduct(parentCode,
								orgCode);
						if (null != productList && !productList.isEmpty()) {
							// 缓存产品前三名数据
							String redisKey_Pros = RedisConstants.DSB_PT + "-" + parentCode + "-" + orgCode;
							RedisUtil.set(redisKey_Pros, redisTime, productList);
							for (Map<String, Object> proMap : productList) {
								String proCode = proMap.get("CODE").toString();
								// 缓存指标数据
								String redisKey_Data = RedisConstants.DSB_CP + "-" + targetCode + "-" + orgCode + "-"
										+ proCode;
								if (null != tarRegSql) {
									String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode,
											target.getDeptCode());
									sqlCode = redisSqlFactory.replaceProduct(sqlCode, proCode);
									try {
										RedisUtil.set(redisKey_Data, redisTime, this.redisService.getSqlData(sqlCode));
									} catch (Exception e) {
										log.error("System Message: 达时报产品前三名区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message:达时报产品区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getDAS0103_DAS0103() {
		this.getDAS0103("DAS0103");
	}

	/*------------------------------5.绩优区域------------------------------*/

	/**
	 * @状态
	 * @功能 {达时报绩优区域定时任务}51
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午11:39:50
	 */
	public void getDAS0104(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 主页达时报绩优区域[" + subCode + "]定时任务开始执行...");
		String regCode = "DAS0104";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), dateGroupType,
							dateType2, null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.DSB_JY + "-" + targetCode + "-" + orgCode;
						if (null != tarRegSql) {
							String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 达时报绩优区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 达时报绩优区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getDAS0104_DAS0104() {
		this.getDAS0104("DAS0104");
	}

	/*------------------------------6.投产区域------------------------------*/

	/**
	 * @状态
	 * @功能 {达时报投产区域定时任务}52
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午11:39:50
	 */
	public void getDAS0105(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 主页达时报投产区域[" + subCode + "]定时任务开始执行...");
		String regCode = "DAS0105";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), dateGroupType,
							dateType2, null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.DSB_TC + "-" + targetCode + "-" + orgCode;
						if (null != tarRegSql) {
							String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 达时报投产区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message:达时报投产区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getDAS0105_DAS0105() {
		this.getDAS0106("DAS0105");
	}

	/*------------------------------7.机构区域------------------------------*/

	/**
	 * @状态
	 * @功能 {达时报机构区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午11:39:50
	 */
	public void getDAS0106(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 主页达时报机构区域[" + subCode + "]定时任务开始执行...");
		String regCode = "DAS0106";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), dateGroupType,
							dateType2, null);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.DSB_JG + "-" + targetCode + "-" + orgCode;
						if (null != tarRegSql) {
							String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 达时报机构区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 达时报机构区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getDAS0106_DAS0106() {
		this.getDAS0106("DAS0106");
	}

	/*------------------------------8.主管区域------------------------------*/
	/**
	 * @状态
	 * @功能 {达时报主管区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午11:39:50
	 */
	public void getDAS0107(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 主页达时报主管区域[" + subCode + "]定时任务开始执行...");
		String regCode = "DAS0107";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), orgGroupType, null,
							dateType2);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.DSB_ZG + "-" + targetCode + "-" + orgCode;
						if (null != tarRegSql) {
							String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 达时报主管区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 达时报主管区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getDAS0107_DAS0107() {
		this.getDAS0107("DAS0107");
	}

	/*------------------------------9.K2区域-------------------------------*/

	/**
	 * @状态
	 * @功能 {达时报主管区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午11:39:50
	 */
	public void getDAS0108(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 主页达时报K2区域[" + subCode + "]定时任务开始执行...");
		String regCode = "DAS0108";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					String groupDetail = tarReg.getRoleOrgType();
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), orgGroupType, null,
							dateType2);
					List<HashMap<String, String>> orgList = redisService.getOrgListByIndex(subCode, groupDetail);
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						String redisKey = RedisConstants.DSB_K2 + "-" + targetCode + "-" + orgCode;
						if (null != tarRegSql) {
							String sqlCode = redisSqlFactory.getDsbSql(tarRegSql, orgCode, target.getDeptCode());
							try {
								RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
							} catch (Exception e) {
								log.error("System Message: 达时报K2区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 达时报K2区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getDAS0108_DAS0108() {
		this.getDAS0107("DAS0108");
	}
}
