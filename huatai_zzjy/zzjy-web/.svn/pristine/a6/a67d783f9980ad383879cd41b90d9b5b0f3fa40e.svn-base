package com.huatai.web.job.action;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.fairyland.jdp.framework.util.RedisUtil;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.job.constants.RedisConstants;
import com.huatai.web.service.TriggleExecuteService;

@RestController
@RequestMapping("/admin/cache")
public class ClearRedisAction {

	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * @状态 OK
	 * @功能 {开始预警监控的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/clearYjjk")
	public void clearYjjk() {
		RedisUtil.delByPattern("ZZJY-WARN*");
		log.info("System info：清除预警监控全部缓存数据成功！");
	}

	/**
	 * @状态 OK
	 * @功能 {开始清除机构对比的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/clearJgdb")
	public void clearJgdb() {
		RedisUtil.delByPattern("ZZJY-TEMP06_REG01*");
		log.info("System info：清除分析管理机构对比区域缓存数据成功！");
	}

	/**
	 * @状态 OK
	 * @功能 {开始清除清单的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/clearDcqd")
	public void clearDcqd() {
		RedisUtil.delByPattern("ZZJY-TEMP07_REG01*");
		log.info("System info：清除分析管理导出清单区域缓存数据成功！");
	}

	/**
	 * @状态 OK
	 * @功能 {开始清除经营分析的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/clearJyfx")
	public void clearJyfx() {
		RedisUtil.delByPattern("ZZJY-TEMP11_REG01*");
		log.info("System info：清除固定指标经营分析缓存数据成功！");
	}

	/**
	 * @状态 OK
	 * @功能 {开始清除经营分析的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/clearVirt")
	public void clearVirt() {
		RedisUtil.delByPattern("ZZJY-TEMP12_REG01*");
		log.info("System info：清除分析管理虚拟区域缓存数据成功！");
	}

	@RequestMapping("/clearGdzb")
	public void startGdzbByReg(HttpServletRequest request) {
		String reg = request.getParameter("reg");
		switch (reg) {
		case "0":
			// 固定指标-核心区域
			RedisUtil.delByPattern("ZZJY-TEMP02_REG*");
			log.info("System info：清除固定指标全部缓存数据成功！");
			break;
		case "1":
			// 固定指标-核心区域
			RedisUtil.delByPattern("ZZJY-TEMP02_REG01*");
			log.info("System info：清除固定指标核心区域缓存数据成功！");
			break;
		case "2":
			// 固定指标-统计区域
			RedisUtil.delByPattern("ZZJY-TEMP02_REG02*");
			log.info("System info：清除固定指标统计区域缓存数据成功！");
			break;
		case "3":
			// 固定指标-地图区域
			RedisUtil.delByPattern("ZZJY-TEMP02_REG03*");
			log.info("System info：清除固定指标地图区域缓存数据成功！");
			break;
		case "4":
			// 固定指标-光荣区域
			RedisUtil.delByPattern("ZZJY-TEMP02_REG04*");
			log.info("System info：清除固定指标光荣区域缓存数据成功！");
			break;
		case "5":
			// 固定指标-排行区域
			RedisUtil.delByPattern("ZZJY-TEMP02_REG05*");
			log.info("System info：清除固定指标排行区域缓存数据成功！");
			break;
		case "6":
			// 固定指标-列表区域
			RedisUtil.delByPattern("ZZJY-TEMP02_REG06*");
			log.info("System info：清除固定指标列表区域缓存数据成功！");
			break;
		}
	}

	/**
	 * @状态 OK
	 * @功能 {开始清除主页监控的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/clearZdsb")
	public void clearZdsb(HttpServletRequest request) {
		String reg = request.getParameter("reg");
		switch (reg) {
		case "0":
			// 达时报-全部区域
			RedisUtil.delByPattern("ZZJY-DAS*");
			log.info("System info：清除达时报全部缓存数据成功！");
			break;
		case "DAS0101":
			// 达时报-图形区域
			RedisUtil.delByPattern("ZZJY-DAS0101*");
			log.info("System info：清除达时报图形区域缓存数据成功！");
			break;
		case "DAS010101":
			// 达时报-排名区域
			RedisUtil.delByPattern("ZZJY-DAS010101*");
			log.info("System info：清除达时报排名区域缓存数据成功！");
			break;
		case "DAS0102":
			// 达时报-人力区域
			RedisUtil.delByPattern("ZZJY-DAS0102*");
			log.info("System info：清除达时报人力区域缓存数据成功！");
			break;
		case "DAS0103":
			// 达时报-产品区域
			RedisUtil.delByPattern("ZZJY-DAS0103*");
			RedisUtil.delByPattern("ZZJY-DASPROS*");
			log.info("System info：清除达时报产品区域缓存数据成功！");
			break;
		case "DAS0104":
			// 达时报-绩优区域
			RedisUtil.delByPattern("ZZJY-DAS0104*");
			log.info("System info：清除达时报绩优区域缓存数据成功！");
			break;
		case "DAS0105":
			// 达时报-投产区域
			RedisUtil.delByPattern("ZZJY-DAS0105*");
			log.info("System info：清除达时报投产区域缓存数据成功！");
			break;
		case "DAS0106":
			// 达时报-机构区域
			RedisUtil.delByPattern("ZZJY-DAS0106*");
			log.info("System info：清除达时报机构区域缓存数据成功！");
			break;
		case "DAS0107":
			// 达时报-主管区域
			RedisUtil.delByPattern("ZZJY-DAS0107*");
			log.info("System info：清除达时报主管区域缓存数据成功！");
			break;
		case "DAS0108":
			// 达时报-K2区域
			RedisUtil.delByPattern("ZZJY-DAS0108*");
			log.info("System info：清除达时报K2区域缓存数据成功！");
			break;
		}
	}

	/**
	 * @状态 OK
	 * @功能 {开始清除分析管理的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/clearFxgl")
	public void startFxglByReg(HttpServletRequest request) {
		String reg = request.getParameter("reg");
		switch (reg) {
		case "0":
			RedisUtil.delByPattern("ZZJY-TEMP03_REG*");
			log.info("System info：清除分析管理全部缓存数据成功！");
			break;
		case "1":
			RedisUtil.delByPattern("ZZJY-TEMP03_REG01*");
			log.info("System info：清除分析管理常用区域缓存数据成功！");
			break;
		case "2":
			RedisUtil.delByPattern("ZZJY-TEMP03_REG02*");
			log.info("System info：清除分析管理一级区域缓存数据成功！");
			break;
		case "3":
			RedisUtil.delByPattern("ZZJY-TEMP03_REG03*");
			log.info("System info：清除分析管理二级区域缓存数据成功！");
			break;
		case "4":
			RedisUtil.delByPattern("ZZJY-TEMP03_REG04*");
			log.info("System info：清除分析管理排名区域缓存数据成功！");
			break;
		case "5":
			RedisUtil.delByPattern("ZZJY-TEMP03_REG05*");
			log.info("System info：清除分析管理列表区域缓存数据成功！");
			break;
		case "6":
			RedisUtil.delByPattern("ZZJY-TEMP03_REG06*");
			log.info("System info：清除分析管理特殊区域缓存数据成功！");
			break;
		}
	}

	/**
	 * @状态 OK
	 * @功能 {板块清除服务的数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/clearBkfwByReg")
	public void clearBkfw(HttpServletRequest request) {
		String reg = request.getParameter("reg");
		switch (reg) {
		case "0":
			RedisUtil.delByPattern("ZZJY-TEMP04_REG*");
			log.info("System info：清除板块全部数据成功！");
			break;
		case "1":
			RedisUtil.delByPattern("ZZJY-TEMP04_REG01*");
			log.info("System info：清除板块总的区域缓存数据成功！");
			break;
		case "2":
			RedisUtil.delByPattern("ZZJY-TEMP04_REG02*");
			log.info("System info：清除板块下钻缓存数据成功！");
			break;
		}
	}

	/**
	 * @状态 OK
	 * @功能 {清除数据缓存服务}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@PostMapping("/clearAll")
	public void clearAll(HttpServletRequest request) {
		RedisUtil.delByPattern("ZZJY*");
		log.info("System info：清除全部系统缓存数据成功！");
	}

	/**
	 * @状态 OK
	 * @功能 {查詢redis键值}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("/queryKey")
	public Object queryKey(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String regCode = request.getParameter("regCode");
		String subCode = request.getParameter("subCode");
		String tarCode = request.getParameter("tarCode");
		if (!StringUtils.isEmpty(regCode) && !StringUtils.isEmpty(tarCode)) {
			long start = System.currentTimeMillis();
			String redisKey = "";
			if (StringUtils.isEmpty(subCode)) {
				redisKey = RedisConstants.baseName + regCode + "-" + tarCode + "*";
			} else {
				redisKey = RedisConstants.baseName + regCode + "-" + subCode + "*";
			}
			TreeSet<String> keySet = RedisUtil.keys(redisKey);
			long end = System.currentTimeMillis();
			map.put("resCode", "true");
			map.put("data", keySet);
			map.put("msgCode", "查询完成...");
			map.put("exeTime", end - start + "ms");
			log.info("System info：按条件查询键值成功...");
		} else {
			map.put("resCode", "false");
			map.put("msgCode", "参数不完整...");
			log.info("System info：按条件查询键值异常...");
		}
		return map;
	}

	/**
	 * @状态 OK
	 * @功能 {删除redis键值}
	 * @作者 MaxBill
	 * @时间 2017年9月13日 上午10:44:10
	 */
	@RequestMapping("deleteKey")
	public Object deleteKey(HttpServletRequest request, String[] keyStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		String regCode = request.getParameter("regCode");
		String subCode = request.getParameter("subCode");
		if (keyStr.length > 0) {
			long start = System.currentTimeMillis();
			for (String key : keyStr) {
				RedisUtil.del(key);
			}
			// 删除对应的任务
			TriggleExecuteService triggleExecuteService = SpringUtil.getBean(TriggleExecuteService.class);
			triggleExecuteService.deleteByQrtzCode(subCode + "_" + regCode);
			long end = System.currentTimeMillis();
			map.put("resCode", "true");
			map.put("msgCode", "删除完成...");
			map.put("exeTime", end - start + "ms");
			log.info("System info：按条件删除键值成功...");
		} else {
			map.put("resCode", "false");
			map.put("errCode", "参数不完整...");
			log.info("System info：按条件删除键值异常...");
		}
		return map;
	}

}
