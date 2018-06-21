package com.huatai.web.job.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.util.RedisUtil;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.job.constants.RedisConstants;
import com.huatai.web.job.factory.RedisSqlConstants;
import com.huatai.web.job.factory.RedisSqlFactory;
import com.huatai.web.job.service.BkfwService;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.thrift.service.RedisService;

@Service
public class BkfwServiceImpl implements BkfwService {

	Logger log = Logger.getLogger(GdzbServiceImpl.class);

	private RedisService redisService;

	private RedisSqlFactory redisSqlFactory;

	public BkfwServiceImpl() {
		redisService = SpringUtil.getBean(RedisService.class);
		redisSqlFactory = SpringUtil.getBean(RedisSqlFactory.class);
	}

	private static final Integer redisTime = 0;

	private static final String orgGroupType = "ORG_GROUP";

	private static final String dateType2 = "MONTH";

	/*------------------------------板块总的区域------------------------------*/
	/**
	 * @状态 ok
	 * @功能 {板块总的区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年8月30日 下午7:04:35
	 */
	public void getTEMP04_REG01(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 板块上面区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP04_REG01";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), null, null,
							dateType2);
					List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, tarReg.getRoleOrgType());
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							String redisKey = RedisConstants.BKFW_OVER + "-" + subCode + "-" + orgCode;
							if (null != tarRegSql) {
								String sqlCode = redisSqlFactory.getBkfwSql(RedisSqlConstants.BKFW_01,
										tarRegSql.getSqlCode(), orgCode, target.getDeptCode());
								try {
									RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
								} catch (Exception e) {
									e.printStackTrace();
									log.error("System Message: 板块总的区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 板块总的区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getP01_TEMP04_REG01() {
		this.getTEMP04_REG01("P01");
	}

	public void getP02_TEMP04_REG01() {
		this.getTEMP04_REG01("P02");
	}

	public void getP03_TEMP04_REG01() {
		this.getTEMP04_REG01("P03");
	}

	public void getP04_TEMP04_REG01() {
		this.getTEMP04_REG01("P04");
	}

	public void getP05_TEMP04_REG01() {
		this.getTEMP04_REG01("P05");
	}

	public void getP06_TEMP04_REG01() {
		this.getTEMP04_REG01("P06");
	}

	public void getP07_TEMP04_REG01() {
		this.getTEMP04_REG01("P07");
	}

	/*------------------------------板块下钻区域------------------------------*/
	/**
	 * @状态 ok
	 * @功能 {板块下钻区域定时任务}
	 * @作者 MaxBill
	 * @时间 2017年8月30日 下午7:04:35
	 */
	public void getTEMP04_REG02(String subCode) {
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System Message: 板块下钻区域[" + subCode + "]定时任务开始执行...");
		String regCode = "TEMP04_REG02";
		TemReg temReg = redisService.findRegionByCode(regCode);
		List<TarReg> tarRegList = redisService.findTarRegsBySubAndReg(subCode, temReg.getRegId());
		if (null != tarRegList) {
			try {
				for (TarReg tarReg : tarRegList) {
					String targetCode = tarReg.getTargetCode();
					Target target = redisService.findTargetByCode(targetCode);
					TarRegSql tarRegSql = redisService.findTarRegSql(targetCode, temReg.getRegId(), orgGroupType, null,
							dateType2);
					List<HashMap<String, String>> orgList = redisService.getOrgList(subCode, tarReg.getRoleOrgType());
					for (HashMap map : orgList) {
						String orgCode = (String) map.get("CODE");
						if (null != orgCode && null != targetCode && !orgCode.equals("")) {
							String redisKey = RedisConstants.BKFW_DOWN + "-" + subCode + "-" + orgCode;
							if (null != tarRegSql) {
								String sqlCode = redisSqlFactory.getBkfwSql(RedisSqlConstants.BKFW_02,
										tarRegSql.getSqlCode(), orgCode, target.getDeptCode());
								try {
									RedisUtil.set(redisKey, redisTime, this.redisService.getSqlData(sqlCode));
								} catch (Exception e) {
									e.printStackTrace();
									log.error("System Message: 板块下钻区域定时任务" + targetCode + "指标SQL语句执行发生异常！");
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("System Message: 板块下钻区域定时任务发生异常！");
				e.printStackTrace();
			}
		}
	}

	public void getP01_TEMP04_REG02() {
		this.getTEMP04_REG02("P01");
	}

	public void getP02_TEMP04_REG02() {
		this.getTEMP04_REG02("P02");
	}

	public void getP03_TEMP04_REG02() {
		this.getTEMP04_REG02("P03");
	}

	public void getP04_TEMP04_REG02() {
		this.getTEMP04_REG02("P04");
	}

	public void getP05_TEMP04_REG02() {
		this.getTEMP04_REG02("P05");
	}

	public void getP06_TEMP04_REG02() {
		this.getTEMP04_REG02("P06");
	}

	public void getP07_TEMP04_REG02() {
		this.getTEMP04_REG02("P07");
	}

}
