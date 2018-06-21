package com.huatai.web.job.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.framework.util.RedisUtil;
import com.huatai.web.job.service.BkfwService;
import com.huatai.web.job.service.FxglService;
import com.huatai.web.job.service.GdzbService;
import com.huatai.web.job.service.QtfwService;
import com.huatai.web.job.service.ZyjkService;

@RestController
@RequestMapping("/admin/cache")
public class StartRedisAction {

	@Autowired
	private GdzbService gdzbService;

	@Autowired
	private FxglService fxglService;

	@Autowired
	private QtfwService qtfwService;

	@Autowired
	private ZyjkService zyjkService;

	@Autowired
	private BkfwService bkfwService;

	/**
	 * @功能 {按key查询redis缓存数据}
	 * @作者 MaxBill
	 * @时间 2017年11月2日 上午9:56:56
	 */
	@RequestMapping("/redisValue")
	public Object redisValue(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String keys = request.getParameter("keys");
			if (!StringUtils.isEmpty(keys)) {
				String[] keyArray = keys.split(" ");
				if (null != keyArray && keyArray.length > 0) {
					for (String key : keyArray) {
						resultMap.put(key, JSON.toJSONString(RedisUtil.get(key)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * @状态 OK
	 * @功能 {开始预警监控的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/startYjjk")
	public void startYjjk() {
		this.qtfwService.getWarnData();
	}

	/**
	 * @状态 OK
	 * @功能 {开始机构对比的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/startJgdb")
	public void startJgdb() {
		this.qtfwService.getT01_TEMP06_REG01();
		this.qtfwService.getT02_TEMP06_REG01();
		this.qtfwService.getT03_TEMP06_REG01();
		this.qtfwService.getT04_TEMP06_REG01();
		this.qtfwService.getT07_TEMP06_REG01();
		this.qtfwService.getT08_TEMP06_REG01();
		this.qtfwService.getP01_TEMP06_REG01();
		this.qtfwService.getP02_TEMP06_REG01();
		this.qtfwService.getP03_TEMP06_REG01();
		this.qtfwService.getP04_TEMP06_REG01();
		this.qtfwService.getP05_TEMP06_REG01();
		this.qtfwService.getP06_TEMP06_REG01();
		this.qtfwService.getP07_TEMP06_REG01();
	}

	/**
	 * @状态 OK
	 * @功能 {开始导出清单的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/startDcqd")
	public void startDcqd() {
		this.qtfwService.getT01_TEMP07_REG01();
		this.qtfwService.getT02_TEMP07_REG01();
		this.qtfwService.getT03_TEMP07_REG01();
		this.qtfwService.getT04_TEMP07_REG01();
		this.qtfwService.getT07_TEMP07_REG01();
		this.qtfwService.getT08_TEMP07_REG01();
		this.qtfwService.getP01_TEMP07_REG01();
		this.qtfwService.getP02_TEMP07_REG01();
		this.qtfwService.getP03_TEMP07_REG01();
		this.qtfwService.getP04_TEMP07_REG01();
		this.qtfwService.getP05_TEMP07_REG01();
		this.qtfwService.getP06_TEMP07_REG01();
		this.qtfwService.getP07_TEMP07_REG01();
	}

	/**
	 * @状态 OK
	 * @功能 {开始经营分析的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/startJyfx")
	public void startJyfx() {
		this.qtfwService.getJYFX_TEMP11_REG01();
	}

	/**
	 * @状态 OK
	 * @功能 {开始手机虚拟区域的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/startVirt")
	public void startVirt() {
		this.qtfwService.getT01_TEMP12_REG01();
		this.qtfwService.getT02_TEMP12_REG01();
		this.qtfwService.getT04_TEMP12_REG01();
		this.qtfwService.getT07_TEMP12_REG01();
		this.qtfwService.getT08_TEMP12_REG01();
		this.qtfwService.getT09_TEMP12_REG01();
		this.qtfwService.getP01_TEMP12_REG01();
		this.qtfwService.getP02_TEMP12_REG01();
		this.qtfwService.getP03_TEMP12_REG01();
		this.qtfwService.getP04_TEMP12_REG01();
		this.qtfwService.getP05_TEMP12_REG01();
		this.qtfwService.getP06_TEMP12_REG01();
	}

	/**
	 * @状态 OK
	 * @功能 {开始全部固定指标的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	public void startGdzbAll() {
		// 固定指标-核心区域
		this.gdzbService.getT01_TEMP02_REG01();
		this.gdzbService.getT02_TEMP02_REG01();
		this.gdzbService.getT07_TEMP02_REG01();
		this.gdzbService.getT08_TEMP02_REG01();
		this.gdzbService.getP01_TEMP02_REG01();
		this.gdzbService.getP02_TEMP02_REG01();
		this.gdzbService.getP04_TEMP02_REG01();
		this.gdzbService.getP05_TEMP02_REG01();
		// 固定指标-统计区域
		this.gdzbService.getT07_TEMP02_REG02();
		this.gdzbService.getT08_TEMP02_REG02();
		this.gdzbService.getP04_TEMP02_REG02();
		this.gdzbService.getP05_TEMP02_REG02();
		this.gdzbService.getP06_TEMP02_REG02();
		this.gdzbService.getGYB_TEMP02_REG02();
		// 固定指标-地图区域
		this.gdzbService.getT07_TEMP02_REG03();
		this.gdzbService.getT08_TEMP02_REG03();
		this.gdzbService.getP04_TEMP02_REG03();
		this.gdzbService.getP05_TEMP02_REG03();
		this.gdzbService.getP06_TEMP02_REG03();
		this.gdzbService.getGYB_TEMP02_REG03();
		// 固定指标-光荣区域
		this.gdzbService.getGYB_TEMP02_REG04();
		// 固定指标-排行区域
		this.gdzbService.getT07_TEMP02_REG05();
		this.gdzbService.getT08_TEMP02_REG05();
		this.gdzbService.getP04_TEMP02_REG05();
		this.gdzbService.getP05_TEMP02_REG05();
		this.gdzbService.getP06_TEMP02_REG05();
		this.gdzbService.getGYB_TEMP02_REG05();
		// 固定指标-列表区域
		this.gdzbService.getT08_TEMP02_REG06();
		this.gdzbService.getP04_TEMP02_REG06();
		this.gdzbService.getGYB_TEMP02_REG06();
	}

	@RequestMapping("/startGdzb")
	public void startGdzb(HttpServletRequest request) {
		String reg = request.getParameter("reg");
		switch (reg) {
		case "0":
			// 固定指标-全部区域
			startGdzbAll();
			break;
		case "1":
			// 固定指标-核心区域
			// this.gdzbService.getT01_TEMP02_REG01();
			this.gdzbService.getT02_TEMP02_REG01();
			this.gdzbService.getT07_TEMP02_REG01();
			this.gdzbService.getT08_TEMP02_REG01();
			this.gdzbService.getP01_TEMP02_REG01();
			this.gdzbService.getP02_TEMP02_REG01();
			this.gdzbService.getP04_TEMP02_REG01();
			this.gdzbService.getP05_TEMP02_REG01();
			break;
		case "2":
			// 固定指标-统计区域
			this.gdzbService.getT07_TEMP02_REG02();
			this.gdzbService.getT08_TEMP02_REG02();
			this.gdzbService.getP04_TEMP02_REG02();
			this.gdzbService.getP05_TEMP02_REG02();
			this.gdzbService.getP06_TEMP02_REG02();
			this.gdzbService.getGYB_TEMP02_REG02();
			break;
		case "3":
			// 固定指标-地图区域
			this.gdzbService.getT07_TEMP02_REG03();
			this.gdzbService.getT08_TEMP02_REG03();
			this.gdzbService.getP04_TEMP02_REG03();
			this.gdzbService.getP05_TEMP02_REG03();
			this.gdzbService.getP06_TEMP02_REG03();
			this.gdzbService.getGYB_TEMP02_REG03();
			break;
		case "4":
			// 固定指标-光荣区域
			this.gdzbService.getGYB_TEMP02_REG04();
			break;
		case "5":
			// 固定指标-排行区域
			this.gdzbService.getT07_TEMP02_REG05();
			this.gdzbService.getT08_TEMP02_REG05();
			this.gdzbService.getP04_TEMP02_REG05();
			this.gdzbService.getP05_TEMP02_REG05();
			this.gdzbService.getP06_TEMP02_REG05();
			this.gdzbService.getGYB_TEMP02_REG05();
			break;
		case "6":
			// 固定指标-列表区域
			this.gdzbService.getT08_TEMP02_REG06();
			this.gdzbService.getP04_TEMP02_REG06();
			this.gdzbService.getGYB_TEMP02_REG06();
			break;
		}
	}

	/**
	 * @状态 OK
	 * @功能 {开始全部主页监控的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	public void startZdsbAll() {
		// 达时报-图形区域
		this.zyjkService.getDAS0101_DAS0101();
		// 达时报-排名区域
		this.zyjkService.getDAS0101_DAS010101();
		// 达时报-人力区域
		this.zyjkService.getDAS0102_DAS0102();
		// 达时报-产品区域
		this.zyjkService.getDAS0103_DAS0103();
		// 达时报-绩优区域
		this.zyjkService.getDAS0104_DAS0104();
		// 达时报-投产区域
		this.zyjkService.getDAS0105_DAS0105();
		// 达时报-机构区域
		this.zyjkService.getDAS0106_DAS0106();
		// 达时报-主管区域
		this.zyjkService.getDAS0107_DAS0107();
		// 达时报-K2区域
	}

	/**
	 * @状态 OK
	 * @功能 {开始主页达时报的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/startZdsb")
	public void startZdsb(HttpServletRequest request) {
		String reg = request.getParameter("reg");
		switch (reg) {
		case "0":
			// 达时报-全部区域
			startZdsbAll();
			break;
		case "DAS0101":
			// 达时报-图形区域
			this.zyjkService.getDAS0101_DAS0101();
			break;
		case "DAS010101":
			// 达时报-排名区域
			this.zyjkService.getDAS0101_DAS010101();
			break;
		case "DAS0102":
			// 达时报-人力区域
			this.zyjkService.getDAS0102_DAS0102();
			break;
		case "DAS0103":
			// 达时报-产品区域
			this.zyjkService.getDAS0103_DAS0103();
			break;
		case "DAS0104":
			// 达时报-绩优区域
			this.zyjkService.getDAS0104_DAS0104();
			break;
		case "DAS0105":
			// 达时报-投产区域
			this.zyjkService.getDAS0105_DAS0105();
			break;
		case "DAS0106":
			// 达时报-机构区域
			this.zyjkService.getDAS0106_DAS0106();
			break;
		case "DAS0107":
			// 达时报-主管区域
			this.zyjkService.getDAS0107_DAS0107();
			break;
		case "DAS0108":
			// 达时报-K2区域
			this.zyjkService.getDAS0108_DAS0108();
			break;
		}
	}

	/**
	 * @状态 OK
	 * @功能 {开始分析管理的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	public void startFxglAll() {
		// 常用区域
		this.fxglService.getT01_TEMP03_REG01();
		this.fxglService.getT02_TEMP03_REG01();
		this.fxglService.getT03_TEMP03_REG01();
		this.fxglService.getT04_TEMP03_REG01();
		this.fxglService.getT07_TEMP03_REG01();
		this.fxglService.getT08_TEMP03_REG01();
		this.fxglService.getP01_TEMP03_REG01();
		this.fxglService.getP02_TEMP03_REG01();
		this.fxglService.getP03_TEMP03_REG01();
		this.fxglService.getP04_TEMP03_REG01();
		this.fxglService.getP05_TEMP03_REG01();
		this.fxglService.getP06_TEMP03_REG01();
		this.fxglService.getP07_TEMP03_REG01();

		// 一级区域
		this.fxglService.getT01_TEMP03_REG02();
		this.fxglService.getT02_TEMP03_REG02();
		this.fxglService.getT03_TEMP03_REG02();
		this.fxglService.getT04_TEMP03_REG02();
		this.fxglService.getT07_TEMP03_REG02();
		this.fxglService.getT08_TEMP03_REG02();
		this.fxglService.getP01_TEMP03_REG02();
		this.fxglService.getP02_TEMP03_REG02();
		this.fxglService.getP03_TEMP03_REG02();
		this.fxglService.getP04_TEMP03_REG02();
		this.fxglService.getP05_TEMP03_REG02();
		this.fxglService.getP06_TEMP03_REG02();
		this.fxglService.getP07_TEMP03_REG02();

		// 二级区域
		this.fxglService.getT01_TEMP03_REG03();
		this.fxglService.getT02_TEMP03_REG03();
		this.fxglService.getT03_TEMP03_REG03();
		this.fxglService.getT04_TEMP03_REG03();
		this.fxglService.getT07_TEMP03_REG03();
		this.fxglService.getT08_TEMP03_REG03();
		this.fxglService.getP01_TEMP03_REG03();
		this.fxglService.getP02_TEMP03_REG03();
		this.fxglService.getP03_TEMP03_REG03();
		this.fxglService.getP04_TEMP03_REG03();
		this.fxglService.getP05_TEMP03_REG03();
		this.fxglService.getP06_TEMP03_REG03();
		this.fxglService.getP07_TEMP03_REG03();

		// 排名区域
		this.fxglService.getT01_TEMP03_REG04();
		this.fxglService.getT02_TEMP03_REG04();
		this.fxglService.getT03_TEMP03_REG04();
		this.fxglService.getT04_TEMP03_REG04();
		this.fxglService.getT07_TEMP03_REG04();
		this.fxglService.getT08_TEMP03_REG04();
		this.fxglService.getP01_TEMP03_REG04();
		this.fxglService.getP02_TEMP03_REG04();
		this.fxglService.getP03_TEMP03_REG04();
		this.fxglService.getP04_TEMP03_REG04();
		this.fxglService.getP05_TEMP03_REG04();
		this.fxglService.getP06_TEMP03_REG04();
		this.fxglService.getP07_TEMP03_REG04();

		// 列表区域
		this.fxglService.getT01_TEMP03_REG05();
		this.fxglService.getT02_TEMP03_REG05();
		this.fxglService.getT03_TEMP03_REG05();
		this.fxglService.getT04_TEMP03_REG05();
		this.fxglService.getT07_TEMP03_REG05();
		this.fxglService.getT08_TEMP03_REG05();
		this.fxglService.getP01_TEMP03_REG05();
		this.fxglService.getP02_TEMP03_REG05();
		this.fxglService.getP03_TEMP03_REG05();
		this.fxglService.getP04_TEMP03_REG05();
		this.fxglService.getP05_TEMP03_REG05();
		this.fxglService.getP06_TEMP03_REG05();
		this.fxglService.getP07_TEMP03_REG05();

		// 特殊区域
		this.fxglService.getT03_TEMP03_REG06();
		this.fxglService.getT04_TEMP03_REG06();
		this.fxglService.getT07_TEMP03_REG06();
		this.fxglService.getT08_TEMP03_REG06();
		this.fxglService.getP01_TEMP03_REG06();
		this.fxglService.getP02_TEMP03_REG06();

		// 保费区域
		this.fxglService.getT09_TEMP03_REG07();
	}

	/**
	 * @状态 OK
	 * @功能 {开始分析管理的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/startFxgl")
	public void startFxgl(HttpServletRequest request) {
		String type = request.getParameter("reg");
		switch (type) {
		case "0":
			startFxglAll();
			break;
		case "1":
			this.fxglService.getT01_TEMP03_REG01();
			this.fxglService.getT02_TEMP03_REG01();
			this.fxglService.getT04_TEMP03_REG01();
			this.fxglService.getT07_TEMP03_REG01();
			this.fxglService.getT08_TEMP03_REG01();
			this.fxglService.getP01_TEMP03_REG01();
			this.fxglService.getP02_TEMP03_REG01();
			this.fxglService.getP03_TEMP03_REG01();
			this.fxglService.getP04_TEMP03_REG01();
			this.fxglService.getP05_TEMP03_REG01();
			this.fxglService.getP06_TEMP03_REG01();
			this.fxglService.getP07_TEMP03_REG01();
			break;
		case "2":
			this.fxglService.getT01_TEMP03_REG02();
			this.fxglService.getT02_TEMP03_REG02();
			this.fxglService.getT04_TEMP03_REG02();
			this.fxglService.getT07_TEMP03_REG02();
			this.fxglService.getT08_TEMP03_REG02();
			this.fxglService.getP01_TEMP03_REG02();
			this.fxglService.getP02_TEMP03_REG02();
			this.fxglService.getP03_TEMP03_REG02();
			this.fxglService.getP04_TEMP03_REG02();
			this.fxglService.getP05_TEMP03_REG02();
			this.fxglService.getP06_TEMP03_REG02();
			this.fxglService.getP07_TEMP03_REG02();
			break;
		case "3":
			this.fxglService.getT01_TEMP03_REG03();
			this.fxglService.getT02_TEMP03_REG03();
			this.fxglService.getT04_TEMP03_REG03();
			this.fxglService.getT07_TEMP03_REG03();
			this.fxglService.getT08_TEMP03_REG03();
			this.fxglService.getP01_TEMP03_REG03();
			this.fxglService.getP02_TEMP03_REG03();
			this.fxglService.getP03_TEMP03_REG03();
			this.fxglService.getP04_TEMP03_REG03();
			this.fxglService.getP05_TEMP03_REG03();
			this.fxglService.getP06_TEMP03_REG03();
			this.fxglService.getP07_TEMP03_REG03();
			break;
		case "4":
			this.fxglService.getT01_TEMP03_REG04();
			this.fxglService.getT02_TEMP03_REG04();
			this.fxglService.getT04_TEMP03_REG04();
			this.fxglService.getT07_TEMP03_REG04();
			this.fxglService.getT08_TEMP03_REG04();
			this.fxglService.getP01_TEMP03_REG04();
			this.fxglService.getP02_TEMP03_REG04();
			this.fxglService.getP03_TEMP03_REG04();
			this.fxglService.getP04_TEMP03_REG04();
			this.fxglService.getP05_TEMP03_REG04();
			this.fxglService.getP06_TEMP03_REG04();
			this.fxglService.getP07_TEMP03_REG04();
			break;
		case "5":
			this.fxglService.getT01_TEMP03_REG05();
			this.fxglService.getT02_TEMP03_REG05();
			this.fxglService.getT04_TEMP03_REG05();
			this.fxglService.getT07_TEMP03_REG05();
			this.fxglService.getT08_TEMP03_REG05();
			this.fxglService.getP01_TEMP03_REG05();
			this.fxglService.getP02_TEMP03_REG05();
			this.fxglService.getP03_TEMP03_REG05();
			this.fxglService.getP04_TEMP03_REG05();
			this.fxglService.getP05_TEMP03_REG05();
			this.fxglService.getP06_TEMP03_REG05();
			this.fxglService.getP07_TEMP03_REG05();
			break;
		case "6":
			this.fxglService.getT04_TEMP03_REG06();
			this.fxglService.getT07_TEMP03_REG06();
			this.fxglService.getT08_TEMP03_REG06();
			this.fxglService.getP01_TEMP03_REG06();
			this.fxglService.getP02_TEMP03_REG06();
			break;
		case "7":
			this.fxglService.getT09_TEMP03_REG07();
			break;
		}
	}

	/**
	 * @状态 OK
	 * @功能 {开始分析管理的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/startBkfwAll")
	public void startBkfwAll() {
		// 上面区域
		this.bkfwService.getP01_TEMP04_REG01();
		this.bkfwService.getP02_TEMP04_REG01();
		this.bkfwService.getP03_TEMP04_REG01();
		this.bkfwService.getP04_TEMP04_REG01();
		this.bkfwService.getP05_TEMP04_REG01();
		this.bkfwService.getP06_TEMP04_REG01();
		this.bkfwService.getP07_TEMP04_REG01();

		// 下面区域
		this.bkfwService.getP01_TEMP04_REG02();
		this.bkfwService.getP02_TEMP04_REG02();
		this.bkfwService.getP03_TEMP04_REG02();
		this.bkfwService.getP04_TEMP04_REG02();
		this.bkfwService.getP05_TEMP04_REG02();
		this.bkfwService.getP06_TEMP04_REG02();
		this.bkfwService.getP07_TEMP04_REG02();

	}

	/**
	 * @状态 OK
	 * @功能 {板块服务的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/startBkfwByReg")
	public void startBkfwByReg(HttpServletRequest request) {
		String reg = request.getParameter("reg");
		switch (reg) {
		case "0":
			startBkfwAll();
			break;
		case "1":
			this.bkfwService.getP01_TEMP04_REG01();
			this.bkfwService.getP02_TEMP04_REG01();
			this.bkfwService.getP03_TEMP04_REG01();
			this.bkfwService.getP04_TEMP04_REG01();
			this.bkfwService.getP05_TEMP04_REG01();
			this.bkfwService.getP06_TEMP04_REG01();
			this.bkfwService.getP07_TEMP04_REG01();
			break;
		case "2":
			this.bkfwService.getP01_TEMP04_REG02();
			this.bkfwService.getP02_TEMP04_REG02();
			this.bkfwService.getP03_TEMP04_REG02();
			this.bkfwService.getP04_TEMP04_REG02();
			this.bkfwService.getP05_TEMP04_REG02();
			this.bkfwService.getP06_TEMP04_REG02();
			this.bkfwService.getP07_TEMP04_REG02();
			break;
		}
	}

	/**
	 * @状态 OK
	 * @功能 {清除数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@PostMapping("/startAll")
	public void startAll(HttpServletRequest request) {
		startYjjk();
		startJgdb();
		startDcqd();
		startVirt();
		startBkfwAll();
		startFxglAll();
		startZdsbAll();
		startGdzbAll();
	}

}
