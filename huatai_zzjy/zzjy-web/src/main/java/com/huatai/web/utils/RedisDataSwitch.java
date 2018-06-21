package com.huatai.web.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.quartz.domain.CronTrigger;
import com.fairyland.jdp.framework.quartz.service.CronTriggerService;
import com.huatai.web.model.TableExecute;
import com.huatai.web.model.TableTriggle;
import com.huatai.web.model.TriggleExecute;
import com.huatai.web.service.TableExecuteService;
import com.huatai.web.service.TableTriggleService;
import com.huatai.web.service.TriggleExecuteService;

/**
 * @func 开启redis开关
 * @user MaxBill
 * @date 2017/11/14 10:03
 * @mail 1370581389@qq.com
 */
@Component
public class RedisDataSwitch {

	@Autowired
	private DictionaryService dictionaryService;// 数据字典表

	@Autowired
	private TableTriggleService tableTriggleService;// MYSQL库的任务关系关联

	@Autowired
	private TableExecuteService tableExecuteService;// ORACLE库数据推送记录

	@Autowired
	private TriggleExecuteService triggleExecuteService;

	@Autowired
	private CronTriggerService cronTriggerService;

	private static RedisDataSwitch redisDataSwitch;

	public RedisDataSwitch() {
	}

	@PostConstruct
	public void init() {
		redisDataSwitch = this;
		redisDataSwitch.dictionaryService = this.dictionaryService;
		redisDataSwitch.tableTriggleService = this.tableTriggleService;
		redisDataSwitch.tableExecuteService = this.tableExecuteService;
		redisDataSwitch.cronTriggerService = this.cronTriggerService;
		redisDataSwitch.triggleExecuteService = this.triggleExecuteService;

	}

	/**
	 * @func 打开redis开关处理
	 * @user MaxBill
	 * @date 2017/11/14 10:03
	 * @mail 1370581389@qq.com
	 */
	public static void startRedisData() {
		try {
			List<TableTriggle> tableTriggleList = redisDataSwitch.tableTriggleService.queryTableTriggleList();
			if (null != tableTriggleList && !tableTriggleList.isEmpty()) {
				Integer isFinish = 0;
				List<String> isFinishTables = new ArrayList<String>();
				List<String> noFinishTables = new ArrayList<String>();
				for (TableTriggle tableTriggle : tableTriggleList) {
					String tableCode = tableTriggle.getTableCode();
					if (tableCode.startsWith("V_")) {
						tableCode = tableCode.substring(2);
					}
					TableExecute tableExecute = redisDataSwitch.tableExecuteService.findTableExecuteByTable(tableCode);
					if (null != tableExecute && tableExecute.getExecStatus().equals("1")) {
						isFinish++;
						isFinishTables.add(tableCode);
					} else {
						noFinishTables.add(tableCode);
					}
				}
				if (isFinish >= 20) {
					DictionaryItem dictionaryItem = redisDataSwitch.dictionaryService
							.findByDictCodeAndDictItemCode("redis", "enabled");
					if (null != dictionaryItem) {
						dictionaryItem.setDescript("true");
						redisDataSwitch.dictionaryService.updateDictionaryItem(dictionaryItem);
					}
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>:表数据已经推动完成，已经开启系统REDIS数据缓存开关......");
					// 向结果表记录
					// 1.删除原来的脏数据
					redisDataSwitch.triggleExecuteService.deleteByPrimaryKey(37L, "START");
					// 2.记录今天的记录
					TriggleExecute teToday = new TriggleExecute();
					teToday.setQrtzGroupId(37L);
					teToday.setQrtzCode("START");
					teToday.setExecBeginTime(new Date());
					teToday.setExecEndTime(new Date());
					teToday.setExecStatus("1");
					redisDataSwitch.triggleExecuteService.insert(teToday);
					// 3.关闭论询开启缓存任务
					closeRedisTask();
				} else {
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>:表数据没有推动完成，暂未开启系统REDIS数据缓存开关......");
				}
				for (String tabCode : isFinishTables) {
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>:今日" + tabCode + "表的数据已经正常推送......");
				}
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
				for (String tabCode : noFinishTables) {
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>:今日" + tabCode + "表的数据没有正常推送......");
				}
				Integer totalTable = tableTriggleList.size();
				Integer noFinish = totalTable - isFinish;
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>:需要推送：" + totalTable + "张表");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>:成功推送：" + isFinish + "张表");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>:暂未推送：" + noFinish + "张表");
			} else {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>:任务关联表配置异常，请检查......");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @func 关闭redis开关处理
	 * @user MaxBill
	 * @date 2017/11/14 10:03
	 * @mail 1370581389@qq.com
	 */
	public static void closeRedisData() {
		try {
			DictionaryItem dictionaryItem = redisDataSwitch.dictionaryService.findByDictCodeAndDictItemCode("redis",
					"enabled");
			if (null != dictionaryItem) {
				dictionaryItem.setDescript("false");
				redisDataSwitch.dictionaryService.updateDictionaryItem(dictionaryItem);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>:系统REDIS数据缓存关闭成功......");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @func 生成redis开关任务
	 * @user MaxBill
	 * @date 2017/11/14 10:03
	 * @mail 1370581389@qq.com
	 */
	public static void startRedisTask() throws ClassNotFoundException, SchedulerException, ParseException {
		CronTrigger cronTrigger = redisDataSwitch.cronTriggerService.getCronTrigger("START", "KQRW");
		if (null != cronTrigger) {
			redisDataSwitch.cronTriggerService.generate(cronTrigger);
		}
	}

	/**
	 * @func 关闭redis开关任务
	 * @user MaxBill
	 * @date 2017/11/14 10:03
	 * @mail 1370581389@qq.com
	 */
	public static void closeRedisTask() throws ClassNotFoundException, SchedulerException, ParseException {
		CronTrigger cronTrigger = redisDataSwitch.cronTriggerService.getCronTrigger("START", "KQRW");
		if (null != cronTrigger) {
			redisDataSwitch.cronTriggerService.stop(cronTrigger);
		}
	}
}
