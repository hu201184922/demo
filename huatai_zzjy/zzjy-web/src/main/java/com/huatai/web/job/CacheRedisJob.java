package com.huatai.web.job;

import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.fairyland.jdp.framework.quartz.service.QrtzGroupService;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.job.service.BkfwService;
import com.huatai.web.job.service.FxglService;
import com.huatai.web.job.service.GdzbService;
import com.huatai.web.job.service.QtfwService;
import com.huatai.web.job.service.ZyjkService;
import com.huatai.web.model.TableExecute;
import com.huatai.web.model.TableTriggle;
import com.huatai.web.model.TriggleExecute;
import com.huatai.web.service.TableExecuteService;
import com.huatai.web.service.TableTriggleService;
import com.huatai.web.service.TriggleExecuteService;
import com.huatai.web.utils.DateUtil;

public class CacheRedisJob extends QuartzJobBean {

	private Logger log = LoggerFactory.getLogger(getClass());
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	private ZyjkService zyjkService;

	private GdzbService gdzbService;

	private FxglService fxglService;

	private QtfwService qtfwService;

	private BkfwService bkfwService;

	public CacheRedisJob() {
		zyjkService = SpringUtil.getBean(ZyjkService.class);
		gdzbService = SpringUtil.getBean(GdzbService.class);
		fxglService = SpringUtil.getBean(FxglService.class);
		qtfwService = SpringUtil.getBean(QtfwService.class);
		bkfwService = SpringUtil.getBean(BkfwService.class);
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		boolean forceRun = false;
		JobKey jobKey = context.getJobDetail().getKey();
		log.info("CacheRedisJob Started.JobKey.Group=" + jobKey.getGroup() + " JobKey.Name=" + jobKey.getName());
		String forceRunStr = (String) context.getJobDetail().getJobDataMap().get("forceRun");
		if ("1".equals(forceRunStr) || "true".equals(forceRunStr)) {
			forceRun = true;
		}
		QrtzGroupService qrtzGroupService = applicationContext.getBean(QrtzGroupService.class);
		TableExecuteService tableExecuteService = applicationContext.getBean(TableExecuteService.class);
		TableTriggleService tableTriggleService = applicationContext.getBean(TableTriggleService.class);
		TriggleExecuteService triggleExecuteService = applicationContext.getBean(TriggleExecuteService.class);
		Date now = new Date();
		Long qrtzGroupId = qrtzGroupService.findByCode(jobKey.getGroup()).getId();
		String qrtzCode = jobKey.getName();
		Date todayStartTime = DateUtil.getTodayStartDate();
		// Date todayEndTime = DateUtil.addDate(todayStartTime, 1);
		// 判断今天redis是否已经完成缓存
		boolean syncedToday = false;
		boolean syncInProcess = false;
		List<TriggleExecute> teList = triggleExecuteService.findAll(qrtzGroupId, qrtzCode, null, null);
		if (teList != null && teList.size() > 0) {
			TriggleExecute te = teList.get(0);
			if (te.getExecBeginTime().getTime() > todayStartTime.getTime() && te.getExecStatus().equals("1")) {
				syncedToday = true;
			} else if (te.getExecBeginTime().getTime() > todayStartTime.getTime() && !te.getExecStatus().equals("1")) {
				syncInProcess = true;
			}
		}
		TriggleExecute teToday = null;
		// 今天redis已完成缓存或者正在缓存，返回
		if (!forceRun && (syncedToday || syncInProcess)) {
			return;
		} else {
			// 准备缓存，修改第一条记录的状态，删除可能存在的脏数据
			for (int i = 0; i < teList.size(); i++) {
				TriggleExecute te = teList.get(i);
				triggleExecuteService.deleteByPrimaryKey(te.getQrtzGroupId(), te.getQrtzCode());
			}
			teToday = new TriggleExecute();
			teToday.setQrtzGroupId(qrtzGroupId);
			teToday.setQrtzCode(qrtzCode);
			teToday.setExecBeginTime(now);
			teToday.setExecEndTime(null);
			teToday.setExecStatus("0");
			triggleExecuteService.insert(teToday);
		}
		// 开始进行缓存任务
		TableTriggle tableTriggle = new TableTriggle();
		tableTriggle.setQrtzGroupId(qrtzGroupId);
		tableTriggle.setQrtzCode(qrtzCode);
		// 找到任务关联的表
		List<TableTriggle> ttList = tableTriggleService.findAll(tableTriggle);
		// 判断华泰那边是否所有表都同步完成了
		boolean syncCompleted = true;
		for (TableTriggle tr : ttList) {
			List<TableExecute> taeList = tableExecuteService.findCompletedOfToday(tr.getTableCode());
			if (taeList == null || taeList.size() == 0) {
				syncCompleted = false;
				break;
			}
		}
		if (!syncCompleted) {
			triggleExecuteService.deleteByPrimaryKey(qrtzGroupId, qrtzCode);
		}
		// 华泰表同步完成，开始缓存redis
		if (forceRun || syncCompleted) {
			// -------------缓存开始----------------
			switch (qrtzCode) {
			// 虚拟区域
			case "T01_TEMP12_REG01":
				this.qtfwService.getT01_TEMP12_REG01();
				break;
			case "T02_TEMP12_REG01":
				this.qtfwService.getT02_TEMP12_REG01();
				break;
			case "T03_TEMP12_REG01":
				this.qtfwService.getT03_TEMP12_REG01();
				break;
			case "T04_TEMP12_REG01":
				this.qtfwService.getT04_TEMP12_REG01();
				break;
			case "T07_TEMP12_REG01":
				this.qtfwService.getT07_TEMP12_REG01();
				break;
			case "T08_TEMP12_REG01":
				this.qtfwService.getT08_TEMP12_REG01();
				break;
			case "T09_TEMP12_REG01":
				this.qtfwService.getT09_TEMP12_REG01();
				break;
			case "P01_TEMP12_REG01":
				this.qtfwService.getP01_TEMP12_REG01();
				break;
			case "P02_TEMP12_REG01":
				this.qtfwService.getP02_TEMP12_REG01();
				break;
			case "P03_TEMP12_REG01":
				this.qtfwService.getP03_TEMP12_REG01();
				break;
			case "P04_TEMP12_REG01":
				this.qtfwService.getP04_TEMP12_REG01();
				break;
			case "P05_TEMP12_REG01":
				this.qtfwService.getP05_TEMP12_REG01();
				break;
			case "P06_TEMP12_REG01":
				this.qtfwService.getP06_TEMP12_REG01();
				break;
			case "P07_TEMP12_REG01":
				this.qtfwService.getP07_TEMP12_REG01();
				break;
			// 预警监控
			case "WARN":
				this.qtfwService.getWarnData();
				break;
			/*--------------------------------------------------------------*/
			// 经营分析
			case "JYFX":
				this.qtfwService.getJYFX_TEMP11_REG01();
				break;
			/*--------------------------------------------------------------*/
			// 机构对比
			case "T01_TEMP06_REG01":
				this.qtfwService.getT01_TEMP06_REG01();
				break;
			case "T02_TEMP06_REG01":
				this.qtfwService.getT02_TEMP06_REG01();
				break;
			case "T03_TEMP06_REG01":
				this.qtfwService.getT03_TEMP06_REG01();
				break;
			case "T04_TEMP06_REG01":
				this.qtfwService.getT04_TEMP06_REG01();
				break;
			case "T07_TEMP06_REG01":
				this.qtfwService.getT07_TEMP06_REG01();
				break;
			case "T08_TEMP06_REG01":
				this.qtfwService.getT08_TEMP06_REG01();
				break;
			case "T09_TEMP06_REG01":
				this.qtfwService.getT09_TEMP06_REG01();
				break;
			case "T10_TEMP06_REG01":
				this.qtfwService.getT10_TEMP06_REG01();
				break;
			case "P01_TEMP06_REG01":
				this.qtfwService.getP01_TEMP06_REG01();
				break;
			case "P02_TEMP06_REG01":
				this.qtfwService.getP02_TEMP06_REG01();
				break;
			case "P03_TEMP06_REG01":
				this.qtfwService.getP03_TEMP06_REG01();
				break;
			case "P04_TEMP06_REG01":
				this.qtfwService.getP04_TEMP06_REG01();
				break;
			case "P05_TEMP06_REG01":
				this.qtfwService.getP05_TEMP06_REG01();
				break;
			case "P06_TEMP06_REG01":
				this.qtfwService.getP06_TEMP06_REG01();
				break;
			case "P07_TEMP06_REG01":
				this.qtfwService.getP07_TEMP06_REG01();
				break;
			/*--------------------------------------------------------------*/
			// 导出清单
			case "T01_TEMP07_REG01":
				this.qtfwService.getT01_TEMP07_REG01();
				break;
			case "T02_TEMP07_REG01":
				this.qtfwService.getT02_TEMP07_REG01();
				break;
			case "T03_TEMP07_REG01":
				this.qtfwService.getT03_TEMP07_REG01();
				break;
			case "T04_TEMP07_REG01":
				this.qtfwService.getT04_TEMP07_REG01();
				break;
			case "T07_TEMP07_REG01":
				this.qtfwService.getT07_TEMP07_REG01();
				break;
			case "T08_TEMP07_REG01":
				this.qtfwService.getT08_TEMP07_REG01();
				break;
			case "T09_TEMP07_REG01":
				this.qtfwService.getT09_TEMP07_REG01();
				break;
			case "T10_TEMP07_REG01":
				this.qtfwService.getT10_TEMP07_REG01();
				break;
			case "P01_TEMP07_REG01":
				this.qtfwService.getP01_TEMP07_REG01();
				break;
			case "P02_TEMP07_REG01":
				this.qtfwService.getP02_TEMP07_REG01();
				break;
			case "P03_TEMP07_REG01":
				this.qtfwService.getP03_TEMP07_REG01();
				break;
			case "P04_TEMP07_REG01":
				this.qtfwService.getP04_TEMP07_REG01();
				break;
			case "P05_TEMP07_REG01":
				this.qtfwService.getP05_TEMP07_REG01();
				break;
			case "P06_TEMP07_REG01":
				this.qtfwService.getP06_TEMP07_REG01();
				break;
			case "P07_TEMP07_REG01":
				this.qtfwService.getP07_TEMP07_REG01();
				break;
			/*--------------------------------------------------------------*/
			// 达时报-图形区域
			case "DAS0101_DAS0101":
				this.zyjkService.getDAS0101_DAS0101();
				break;
			// 达时报-排名区域
			case "DAS0101_DAS010101":
				this.zyjkService.getDAS0101_DAS010101();
				break;
			// 达时报-人力区域
			case "DAS0102_DAS0102":
				this.zyjkService.getDAS0102_DAS0102();
				break;
			// 达时报-产品区域
			case "DAS0103_DAS0103":
				this.zyjkService.getDAS0103_DAS0103();
				break;
			// 达时报-绩优区域
			case "DAS0104_DAS0104":
				this.zyjkService.getDAS0104_DAS0104();
				break;
			// 达时报-投产区域
			case "DAS0105_DAS0105":
				this.zyjkService.getDAS0105_DAS0105();
				break;
			// 达时报-机构区域
			case "DAS0106_DAS0106":
				this.zyjkService.getDAS0106_DAS0106();
				break;
			// 达时报-主管区域
			case "DAS0107_DAS0107":
				this.zyjkService.getDAS0107_DAS0107();
				break;
			// 达时报-K2区域
			case "DAS0108_DAS0108":
				this.zyjkService.getDAS0108_DAS0108();
				break;
			/*--------------------------------------------------------------*/
			// 固定指标-核心区域
			case "T01_TEMP02_REG01":
				this.gdzbService.getT01_TEMP02_REG01();
				break;
			case "T02_TEMP02_REG01":
				this.gdzbService.getT02_TEMP02_REG01();
				break;
			case "T07_TEMP02_REG01":
				this.gdzbService.getT07_TEMP02_REG01();
				break;
			case "T08_TEMP02_REG01":
				this.gdzbService.getT08_TEMP02_REG01();
				break;
			case "T09_TEMP02_REG01":
				this.gdzbService.getT09_TEMP02_REG01();
				break;
			case "P01_TEMP02_REG01":
				this.gdzbService.getP01_TEMP02_REG01();
				break;
			case "P02_TEMP02_REG01":
				this.gdzbService.getP02_TEMP02_REG01();
				break;
			case "P04_TEMP02_REG01":
				this.gdzbService.getP04_TEMP02_REG01();
				break;
			case "P05_TEMP02_REG01":
				this.gdzbService.getP05_TEMP02_REG01();
				break;
			// 固定指标-统计区域
			case "T07_TEMP02_REG02":
				this.gdzbService.getT07_TEMP02_REG02();
				break;
			case "T08_TEMP02_REG02":
				this.gdzbService.getT08_TEMP02_REG02();
				break;
			case "T09_TEMP02_REG02":
				this.gdzbService.getT09_TEMP02_REG02();
				break;
			case "P04_TEMP02_REG02":
				this.gdzbService.getP04_TEMP02_REG02();
				break;
			case "P05_TEMP02_REG02":
				this.gdzbService.getP05_TEMP02_REG02();
				break;
			case "P06_TEMP02_REG02":
				this.gdzbService.getP06_TEMP02_REG02();
				break;
			case "GYB_TEMP02_REG02":
				this.gdzbService.getGYB_TEMP02_REG02();
				break;
			// 固定指标-地图区域
			case "T07_TEMP02_REG03":
				this.gdzbService.getT07_TEMP02_REG03();
				break;
			case "T08_TEMP02_REG03":
				this.gdzbService.getT08_TEMP02_REG03();
				break;
			case "T09_TEMP02_REG03":
				this.gdzbService.getT09_TEMP02_REG03();
				break;
			case "P04_TEMP02_REG03":
				this.gdzbService.getP04_TEMP02_REG03();
				break;
			case "P05_TEMP02_REG03":
				this.gdzbService.getP05_TEMP02_REG03();
				break;
			case "P06_TEMP02_REG03":
				this.gdzbService.getP06_TEMP02_REG03();
				break;
			case "GYB_TEMP02_REG03":
				this.gdzbService.getGYB_TEMP02_REG03();
				break;
			// 固定指标-光荣区域
			case "GYB_TEMP02_REG04":
				this.gdzbService.getGYB_TEMP02_REG04();
				break;
			// 固定指标-排行区域
			case "T07_TEMP02_REG05":
				this.gdzbService.getT07_TEMP02_REG05();
				break;
			case "T08_TEMP02_REG05":
				this.gdzbService.getT08_TEMP02_REG05();
				break;
			case "T09_TEMP02_REG05":
				this.gdzbService.getT09_TEMP02_REG05();
				break;
			case "P04_TEMP02_REG05":
				this.gdzbService.getP04_TEMP02_REG05();
				break;
			case "P05_TEMP02_REG05":
				this.gdzbService.getP05_TEMP02_REG05();
				break;
			case "P06_TEMP02_REG05":
				this.gdzbService.getP06_TEMP02_REG05();
				break;
			case "GYB_TEMP02_REG05":
				this.gdzbService.getGYB_TEMP02_REG05();
				break;
			// 固定指标-列表区域
			case "T08_TEMP02_REG06":
				this.gdzbService.getT08_TEMP02_REG06();
				break;
			case "T09_TEMP02_REG06":
				this.gdzbService.getT09_TEMP02_REG06();
				break;
			case "P04_TEMP02_REG06":
				this.gdzbService.getP04_TEMP02_REG06();
				break;
			case "GYB_TEMP02_REG06":
				this.gdzbService.getGYB_TEMP02_REG06();
				break;
			/*--------------------------------------------------------------*/
			// 分析管理-核心区域
			case "T01_TEMP03_REG01":
				this.fxglService.getT01_TEMP03_REG01();
				break;
			case "T02_TEMP03_REG01":
				this.fxglService.getT02_TEMP03_REG01();
				break;
			case "T03_TEMP03_REG01":
				this.fxglService.getT03_TEMP03_REG01();
				break;
			case "T04_TEMP03_REG01":
				this.fxglService.getT04_TEMP03_REG01();
				break;
			case "T07_TEMP03_REG01":
				this.fxglService.getT07_TEMP03_REG01();
				break;
			case "T08_TEMP03_REG01":
				this.fxglService.getT08_TEMP03_REG01();
				break;
			case "T09_TEMP03_REG01":
				this.fxglService.getT09_TEMP03_REG01();
				break;
			case "T10_TEMP03_REG01":
				this.fxglService.getT10_TEMP03_REG01();
				break;
			case "P01_TEMP03_REG01":
				this.fxglService.getP01_TEMP03_REG01();
				break;
			case "P02_TEMP03_REG01":
				this.fxglService.getP02_TEMP03_REG01();
				break;
			case "P03_TEMP03_REG01":
				this.fxglService.getP03_TEMP03_REG01();
				break;
			case "P04_TEMP03_REG01":
				this.fxglService.getP04_TEMP03_REG01();
				break;
			case "P05_TEMP03_REG01":
				this.fxglService.getP05_TEMP03_REG01();
				break;
			case "P06_TEMP03_REG01":
				this.fxglService.getP06_TEMP03_REG01();
				break;
			case "P07_TEMP03_REG01":
				this.fxglService.getP07_TEMP03_REG01();
				break;
			// 分析管理-一级区域
			case "T01_TEMP03_REG02":
				this.fxglService.getT01_TEMP03_REG02();
				break;
			case "T02_TEMP03_REG02":
				this.fxglService.getT02_TEMP03_REG02();
				break;
			case "T03_TEMP03_REG02":
				this.fxglService.getT03_TEMP03_REG02();
				break;
			case "T04_TEMP03_REG02":
				this.fxglService.getT04_TEMP03_REG02();
				break;
			case "T07_TEMP03_REG02":
				this.fxglService.getT07_TEMP03_REG02();
				break;
			case "T08_TEMP03_REG02":
				this.fxglService.getT08_TEMP03_REG02();
				break;
			case "T09_TEMP03_REG02":
				this.fxglService.getT09_TEMP03_REG02();
				break;
			case "T10_TEMP03_REG02":
				this.fxglService.getT10_TEMP03_REG02();
				break;
			case "P01_TEMP03_REG02":
				this.fxglService.getP01_TEMP03_REG02();
				break;
			case "P02_TEMP03_REG02":
				this.fxglService.getP02_TEMP03_REG02();
				break;
			case "P03_TEMP03_REG02":
				this.fxglService.getP03_TEMP03_REG02();
				break;
			case "P04_TEMP03_REG02":
				this.fxglService.getP04_TEMP03_REG02();
				break;
			case "P05_TEMP03_REG02":
				this.fxglService.getP05_TEMP03_REG02();
				break;
			case "P06_TEMP03_REG02":
				this.fxglService.getP06_TEMP03_REG02();
				break;
			case "P07_TEMP03_REG02":
				this.fxglService.getP07_TEMP03_REG02();
				break;
			// 分析管理-二级区域
			case "T01_TEMP03_REG03":
				this.fxglService.getT01_TEMP03_REG03();
				break;
			case "T02_TEMP03_REG03":
				this.fxglService.getT02_TEMP03_REG03();
				break;
			case "T03_TEMP03_REG03":
				this.fxglService.getT03_TEMP03_REG03();
				break;
			case "T04_TEMP03_REG03":
				this.fxglService.getT04_TEMP03_REG03();
				break;
			case "T07_TEMP03_REG03":
				this.fxglService.getT07_TEMP03_REG03();
				break;
			case "T08_TEMP03_REG03":
				this.fxglService.getT08_TEMP03_REG03();
				break;
			case "T09_TEMP03_REG03":
				this.fxglService.getT09_TEMP03_REG03();
				break;
			case "T10_TEMP03_REG03":
				this.fxglService.getT10_TEMP03_REG03();
				break;
			case "P01_TEMP03_REG03":
				this.fxglService.getP01_TEMP03_REG03();
				break;
			case "P02_TEMP03_REG03":
				this.fxglService.getP02_TEMP03_REG03();
				break;
			case "P03_TEMP03_REG03":
				this.fxglService.getP03_TEMP03_REG03();
				break;
			case "P04_TEMP03_REG03":
				this.fxglService.getP04_TEMP03_REG03();
				break;
			case "P05_TEMP03_REG03":
				this.fxglService.getP05_TEMP03_REG03();
				break;
			case "P06_TEMP03_REG03":
				this.fxglService.getP06_TEMP03_REG03();
				break;
			case "P07_TEMP03_REG03":
				this.fxglService.getP07_TEMP03_REG03();
				break;
			// 分析管理-排行区域
			case "T01_TEMP03_REG04":
				this.fxglService.getT01_TEMP03_REG04();
				break;
			case "T02_TEMP03_REG04":
				this.fxglService.getT02_TEMP03_REG04();
				break;
			case "T03_TEMP03_REG04":
				this.fxglService.getT03_TEMP03_REG04();
				break;
			case "T04_TEMP03_REG04":
				this.fxglService.getT04_TEMP03_REG04();
				break;
			case "T07_TEMP03_REG04":
				this.fxglService.getT07_TEMP03_REG04();
				break;
			case "T08_TEMP03_REG04":
				this.fxglService.getT08_TEMP03_REG04();
				break;
			case "T09_TEMP03_REG04":
				this.fxglService.getT09_TEMP03_REG04();
				break;
			case "T10_TEMP03_REG04":
				this.fxglService.getT10_TEMP03_REG04();
				break;
			case "P01_TEMP03_REG04":
				this.fxglService.getP01_TEMP03_REG04();
				break;
			case "P02_TEMP03_REG04":
				this.fxglService.getP02_TEMP03_REG04();
				break;
			case "P03_TEMP03_REG04":
				this.fxglService.getP03_TEMP03_REG04();
				break;
			case "P04_TEMP03_REG04":
				this.fxglService.getP04_TEMP03_REG04();
				break;
			case "P05_TEMP03_REG04":
				this.fxglService.getP05_TEMP03_REG04();
				break;
			case "P06_TEMP03_REG04":
				this.fxglService.getP06_TEMP03_REG04();
				break;
			case "P07_TEMP03_REG04":
				this.fxglService.getP07_TEMP03_REG04();
				break;
			// 分析管理-列表区域
			case "T01_TEMP03_REG05":
				this.fxglService.getT01_TEMP03_REG05();
				break;
			case "T02_TEMP03_REG05":
				this.fxglService.getT02_TEMP03_REG05();
				break;
			case "T03_TEMP03_REG05":
				this.fxglService.getT03_TEMP03_REG05();
				break;
			case "T04_TEMP03_REG05":
				this.fxglService.getT04_TEMP03_REG05();
				break;
			case "T07_TEMP03_REG05":
				this.fxglService.getT07_TEMP03_REG05();
				break;
			case "T08_TEMP03_REG05":
				this.fxglService.getT08_TEMP03_REG05();
				break;
			case "P01_TEMP03_REG05":
				this.fxglService.getP01_TEMP03_REG05();
				break;
			case "P02_TEMP03_REG05":
				this.fxglService.getP02_TEMP03_REG05();
				break;
			case "P03_TEMP03_REG05":
				this.fxglService.getP03_TEMP03_REG05();
				break;
			case "P04_TEMP03_REG05":
				this.fxglService.getP04_TEMP03_REG05();
				break;
			case "P05_TEMP03_REG05":
				this.fxglService.getP05_TEMP03_REG05();
				break;
			case "P06_TEMP03_REG05":
				this.fxglService.getP06_TEMP03_REG05();
				break;
			case "P07_TEMP03_REG05":
				this.fxglService.getP07_TEMP03_REG05();
				break;
			// 分析管理-特殊区域
			case "T03_TEMP03_REG06":
				this.fxglService.getT03_TEMP03_REG06();
				break;
			case "T04_TEMP03_REG06":
				this.fxglService.getT04_TEMP03_REG06();
				break;
			case "T07_TEMP03_REG06":
				this.fxglService.getT07_TEMP03_REG06();
				break;
			case "T08_TEMP03_REG06":
				this.fxglService.getT08_TEMP03_REG06();
				break;
			case "P01_TEMP03_REG06":
				this.fxglService.getP01_TEMP03_REG06();
				break;
			case "P02_TEMP03_REG06":
				this.fxglService.getP02_TEMP03_REG06();
				break;
			// 分析管理-保费列表
			case "T09_TEMP03_REG07":
				this.fxglService.getT09_TEMP03_REG07();
				break;
			case "T10_TEMP03_REG07":
				this.fxglService.getT10_TEMP03_REG07();
				break;
			/*--------------------------------------------------------------*/
			// 板块上面区域
			case "P01_TEMP04_REG01":
				this.bkfwService.getP01_TEMP04_REG01();
				break;
			case "P02_TEMP04_REG01":
				this.bkfwService.getP02_TEMP04_REG01();
				break;
			case "P03_TEMP04_REG01":
				this.bkfwService.getP03_TEMP04_REG01();
				break;
			case "P04_TEMP04_REG01":
				this.bkfwService.getP04_TEMP04_REG01();
				break;
			case "P05_TEMP04_REG01":
				this.bkfwService.getP05_TEMP04_REG01();
				break;
			case "P06_TEMP04_REG01":
				this.bkfwService.getP06_TEMP04_REG01();
				break;
			case "P07_TEMP04_REG01":
				this.bkfwService.getP07_TEMP04_REG01();
				break;
			// 板块下面区域
			case "P01_TEMP04_REG02":
				this.bkfwService.getP01_TEMP04_REG02();
				break;
			case "P02_TEMP04_REG02":
				this.bkfwService.getP02_TEMP04_REG02();
				break;
			case "P03_TEMP04_REG02":
				this.bkfwService.getP03_TEMP04_REG02();
				break;
			case "P04_TEMP04_REG02":
				this.bkfwService.getP04_TEMP04_REG02();
				break;
			case "P05_TEMP04_REG02":
				this.bkfwService.getP05_TEMP04_REG02();
				break;
			case "P06_TEMP04_REG02":
				this.bkfwService.getP06_TEMP04_REG02();
				break;
			case "P07_TEMP04_REG02":
				this.bkfwService.getP07_TEMP04_REG02();
				break;
			}
			// -------------缓存结束----------------
			Date endTime = new Date();
			teToday.setExecEndTime(endTime);
			teToday.setExecStatus("1");
			triggleExecuteService.updateByPrimaryKey(teToday);
		}
	}
}
