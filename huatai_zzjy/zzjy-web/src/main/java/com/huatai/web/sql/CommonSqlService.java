package com.huatai.web.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.mapper.TarRegQueMapper;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.InterField;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.Target;
import com.huatai.web.thrift.service.BrowseListService;
import com.huatai.web.thrift.service.CommonService;
import com.huatai.web.utils.DateUtil;

/**
 * @作者 MaxBill
 * @时间 2017年8月22日 下午2:13:04
 */
@Component
public class CommonSqlService {

	private BrowseListService browseListService;
	private TarRegQueMapper tarRegQueMapper;
	private CommonService commonService;

	public CommonSqlService() {
		browseListService = SpringUtil.getBean(BrowseListService.class);
		commonService = SpringUtil.getBean(CommonService.class);
		tarRegQueMapper = SpringUtil.getBean(TarRegQueMapper.class);
	}

	/**
	 * @功能 {分析管理判断是否有此查询维度}
	 * @作者 MaxBill
	 * @时间 2017年8月3日 下午4:53:44
	 */
	public boolean fxglHasQuery(String targetCode, String queryCode) {
		List<TarRegQue> tarRegQueList = tarRegQueMapper
				.findTarRegQueBySubAndTarAndTempAndQue(targetCode.substring(0, 3), targetCode, "TEMP03", queryCode);
		if (null != tarRegQueList && tarRegQueList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @功能 {达时报判断是否有此查询维度}
	 * @作者 MaxBill
	 * @时间 2017年8月3日 下午4:53:44
	 */
	public boolean zdsbHasQuery(String targetCode, String queryCode) {
		List<TarRegQue> tarRegQueList = tarRegQueMapper
				.findTarRegQueBySubAndTarAndTempAndQue(targetCode.substring(0, 3), targetCode, "DAS01", queryCode);
		if (null != tarRegQueList && tarRegQueList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @功能 {清单头部显示处理-根据机构交集}
	 * @作者 MaxBill
	 * @时间 2017年9月22日 上午10:03:51
	 */
	public Map<String, String> getListInfoTitle(Boolean isServer, String commonOrg) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String title = "";
		String value = "";
		String index = "";
		switch (commonOrg) {
		case "7":
			index = "1";
			title = "分公司,";
			value = "VAL0,";
			break;
		case "6":
			index = "2";
			title = "分公司,中支,";
			value = "VAL0,VAL1,";
			break;
		case "5":
			index = "3";
			if (isServer) {
				title = "分公司,中支,营销服务部,";
				value = "VAL0,VAL1,VAL2,";
			} else {
				title = "分公司,中支,成本中心,";
				value = "VAL0,VAL1,VAL2,";
			}
			break;
		case "4":
			index = "4";
			if (isServer) {
				title = "分公司,中支,营销服务部,区,";
				value = "VAL0,VAL1,VAL2,VAL3,";
			} else {
				title = "分公司,中支,成本中心,区,";
				value = "VAL0,VAL1,VAL2,VAL3,";
			}
			break;
		case "3":
			index = "5";
			if (isServer) {
				title = "分公司,中支,营销服务部,区,部,";
				value = "VAL0,VAL1,VAL2,VAL3,VAL4,";
			} else {
				title = "分公司,中支,成本中心,区,部,";
				value = "VAL0,VAL1,VAL2,VAL3,VAL4,";
			}
			break;
		case "2":
			index = "6";
			if (isServer) {
				title = "分公司,中支,营销服务部,区,部,组,";
				value = "VAL0,VAL1,VAL2,VAL3,VAL4,VAL5,";
			} else {
				title = "分公司,中支,成本中心,区,部,组,";
				value = "VAL0,VAL1,VAL2,VAL3,VAL4,VAL5,";
			}
			break;
		// 包含代理编号
		case "1":
			index = "8";
			if (isServer) {
				title = "分公司,中支,营销服务部,区,部,组,代理人编号,代理人,";
				value = "VAL0,VAL1,VAL2,VAL3,VAL4,VAL5,VAL6,VAL7,";
			} else {
				title = "分公司,中支,成本中心,区,部,组,代理人编号,代理人,";
				value = "VAL0,VAL1,VAL2,VAL3,VAL4,VAL5,VAL6,VAL7,";
			}
			break;
		// 不包含代理人编号
		case "0":
			index = "7";
			if (isServer) {
				title = "分公司,中支,营销服务部,区,部,组,代理人,";
				value = "VAL0,VAL1,VAL2,VAL3,VAL4,VAL5,VAL6,";
			} else {
				title = "分公司,中支,成本中心,区,部,组,代理人,";
				value = "VAL0,VAL1,VAL2,VAL3,VAL4,VAL5,VAL6,";
			}
			break;
		}
		resultMap.put("index", index);
		resultMap.put("title", title);
		resultMap.put("value", value);
		return resultMap;
	}

	/**
	 * @功能 {清单头部显示处理-根据机构交集}
	 * @作者 MaxBill
	 * @时间 2017年9月22日 上午10:03:51
	 */
	public Map<String, String> getListDataTitle(Boolean isServer, String commonOrg) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String headSql = "";
		String baseStr = "";
		String index = "";
		switch (commonOrg) {
		case "7":
			index = "1";
			headSql = "SELECT '分公司' AS VAL0, ";
			baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') AS VAL0, ";
			break;
		case "6":
			index = "2";
			headSql = "SELECT '分公司' AS VAL0, '中支' AS VAL1, ";
			baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') AS VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') AS VAL1, ";
			break;
		case "5":
			index = "3";
			if (isServer) {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '营销服务部' VAL2, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.TEAMCOMNAME,'--') VAL2, ";
			} else {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '成本中心' VAL2, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.COST_CENTERDEVNAME,'--') VAL2, ";
			}
			break;
		case "4":
			index = "4";
			if (isServer) {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '营销服务部' VAL2, '区' VAL3, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.TEAMCOMNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, ";
			} else {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '成本中心' VAL2, '区' VAL3, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.COST_CENTERDEVNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, ";
			}
			break;
		case "3":
			index = "5";
			if (isServer) {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '营销服务部' VAL2, '区' VAL3, '部' VAL4, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.TEAMCOMNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, NVL(T00.PROVGROUPNAME,'--') VAL4, ";
			} else {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '成本中心' VAL2, '区' VAL3, '部' VAL4, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.COST_CENTERDEVNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, NVL(T00.PROVGROUPNAME,'--') VAL4, ";
			}
			break;
		case "2":
			index = "6";
			if (isServer) {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '营销服务部' VAL2, '区' VAL3, '部' VAL4, '组' VAL5, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') AS VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.TEAMCOMNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, NVL(T00.PROVGROUPNAME,'--') VAL4, NVL(T00.COUNTGROUPNAME,'--') VAL5, ";
			} else {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '成本中心' VAL2, '区' VAL3, '部' VAL4, '组' VAL5, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.COST_CENTERDEVNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, NVL(T00.PROVGROUPNAME,'--') VAL4, NVL(T00.COUNTGROUPNAME,'--') VAL5, ";
			}
			break;
		// 包含代理人编号
		case "1":
			index = "8";
			if (isServer) {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '营销服务部' VAL2, '区' VAL3, '部' VAL4, '组' VAL5, '代理人编号' VAL6, '代理人' VAL7, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.TEAMCOMNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, NVL(T00.PROVGROUPNAME,'--') VAL4, NVL(T00.COUNTGROUPNAME,'--') VAL5, TO_CHAR(T00.AGENTCODE) VAL6, NVL(T00.AGENTNAME,'--') VAL7, ";
			} else {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '成本中心' VAL2, '区' VAL3, '部' VAL4, '组' VAL5, '代理人编号' VAL6, '代理人' VAL7, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.COST_CENTERDEVNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, NVL(T00.PROVGROUPNAME,'--') VAL4, NVL(T00.COUNTGROUPNAME,'--') VAL5, TO_CHAR(T00.AGENTCODE) VAL6, NVL(T00.AGENTNAME,'--') VAL7, ";
			}
			break;
		// 不包含代理人编号
		case "0":
			index = "7";
			if (isServer) {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '营销服务部' VAL2, '区' VAL3, '部' VAL4, '组' VAL5, '代理人' VAL6, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.TEAMCOMNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, NVL(T00.PROVGROUPNAME,'--') VAL4, NVL(T00.COUNTGROUPNAME,'--') VAL5, NVL(T00.AGENTNAME,'--') VAL6, ";
			} else {
				headSql = "SELECT '分公司' VAL0, '中支' VAL1, '成本中心' VAL2, '区' VAL3, '部' VAL4, '组' VAL5, '代理人' VAL6, ";
				baseStr = "SELECT DISTINCT NVL(T00.TEAMCOMSHORTNAME,'--') VAL0, NVL(T00.ACTUTEAMCOMSHORTNAME,'--') VAL1, NVL(T00.COST_CENTERDEVNAME,'--') VAL2, NVL(T00.HEADGROUPNAME,'--') VAL3, NVL(T00.PROVGROUPNAME,'--') VAL4, NVL(T00.COUNTGROUPNAME,'--') VAL5, NVL(T00.AGENTNAME,'--') VAL6, ";
			}
			break;
		}
		resultMap.put("index", index);
		resultMap.put("headSql", headSql);
		resultMap.put("baseStr", baseStr);
		return resultMap;
	}

	/**
	 * @功能 {清单处理分组SQL-按交集}
	 * @作者 MaxBill
	 * @时间 2017年9月21日 上午9:32:27
	 */
	public String baseTableSql(String commonOrg, String sql, Boolean isServer) {
		String teamcom_fields = "";
		switch (commonOrg) {
		// 分公司
		case "7":
			String paramSql101 = "PROVCOMCODE,TEAMCOMSHORTNAME";
			sql = sql.replace("{orgcode_group_names}", paramSql101);
			sql = sql.replace("{orgcode_group_names1}", " D.PROVCOMCODE,D.TEAMCOMSHORTNAME ");
			sql = sql.replace("{orgcode_group_names2}", " X.PROVCOMCODE,X.TEAMCOMSHORTNAME ");
			sql = sql.replace("{orgcode_group_names_join1}", " X.PROVCOMCODE = Y.PROVCOMCODE ");
			break;
		// 中支
		case "6":
			String paramSql201 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME";
			sql = sql.replace("{orgcode_group_names}", paramSql201);
			sql = sql.replace("{orgcode_group_names1}",
					" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME ");
			sql = sql.replace("{orgcode_group_names2}",
					" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME ");
			sql = sql.replace("{orgcode_group_names_join1}",
					" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE ");
			break;
		// 营销服务部
		case "5":
			String paramSql301 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME";
			String paramSql302 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql301);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.TEAMCOMCODE,D.TEAMCOMNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.TEAMCOMCODE = Y.TEAMCOMCODE ");
				teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql302);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.COST_CENTERSTDCODE,D.COST_CENTERDEVNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.COST_CENTERSTDCODE = Y.COST_CENTERSTDCODE ");
			}
			break;
		// 区
		case "4":
			String paramSql401 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME,HEADGROUPCODE,HEADGROUPNAME";
			String paramSql402 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME,HEADGROUPCODE,HEADGROUPNAME";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql401);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.TEAMCOMCODE,D.TEAMCOMNAME,D.HEADGROUPCODE,D.HEADGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME,X.HEADGROUPCODE,X.HEADGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.TEAMCOMCODE = Y.TEAMCOMCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE ");
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql402);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.COST_CENTERSTDCODE,D.COST_CENTERDEVNAME,D.HEADGROUPCODE,D.HEADGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME,X.HEADGROUPCODE,X.HEADGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.COST_CENTERSTDCODE = Y.COST_CENTERSTDCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE ");
			}
			break;
		// 部
		case "3":
			String paramSql501 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME";
			String paramSql502 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql501);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.TEAMCOMCODE,D.TEAMCOMNAME,D.HEADGROUPCODE,D.HEADGROUPNAME,D.PROVGROUPCODE,D.PROVGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.TEAMCOMCODE = Y.TEAMCOMCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE AND X.PROVGROUPCODE = Y.PROVGROUPCODE ");
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql502);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.COST_CENTERSTDCODE,D.COST_CENTERDEVNAME,D.HEADGROUPCODE,D.HEADGROUPNAME,D.PROVGROUPCODE,D.PROVGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.COST_CENTERSTDCODE = Y.COST_CENTERSTDCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE AND X.PROVGROUPCODE = Y.PROVGROUPCODE ");
			}
			break;
		// 组
		case "2":
			String paramSql601 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME,COUNTGROUPCODE,COUNTGROUPNAME";
			String paramSql602 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME,COUNTGROUPCODE,COUNTGROUPNAME";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql601);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.TEAMCOMCODE,D.TEAMCOMNAME,D.HEADGROUPCODE,D.HEADGROUPNAME,D.PROVGROUPCODE,D.PROVGROUPNAME,D.COUNTGROUPCODE,D.COUNTGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME,X.COUNTGROUPCODE,X.COUNTGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.TEAMCOMCODE = Y.TEAMCOMCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE AND X.PROVGROUPCODE = Y.PROVGROUPCODE AND X.COUNTGROUPNAME = Y.COUNTGROUPNAME ");
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql602);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.COST_CENTERSTDCODE,D.COST_CENTERDEVNAME,D.HEADGROUPCODE,D.HEADGROUPNAME,D.PROVGROUPCODE,D.PROVGROUPNAME,D.COUNTGROUPCODE,D.COUNTGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME,X.COUNTGROUPCODE,X.COUNTGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.COST_CENTERSTDCODE = Y.COST_CENTERSTDCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE AND X.PROVGROUPCODE = Y.PROVGROUPCODE AND X.COUNTGROUPNAME = Y.COUNTGROUPNAME ");
			}
			break;
		// 代理人
		case "1":
			String paramSql701 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME,COUNTGROUPCODE,COUNTGROUPNAME,AGENT_ID,AGENTNAME,AGENTCODE";
			String paramSql702 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME,COUNTGROUPCODE,COUNTGROUPNAME,AGENT_ID,AGENTNAME,AGENTCODE";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql701);
				sql = sql.replace("{orgcode_group_names1}", paramSql701);
				sql = sql.replace("{orgcode_group_names2}",
						"X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME,X.COUNTGROUPCODE,X.COUNTGROUPNAME,X.AGENT_ID,X.AGENTNAME,X.AGENTCODE");
				sql = sql.replace("{orgcode_group_names_join1}",
						"X.PROVCOMCODE=Y.PROVCOMCODE AND X.TEAMCOMSHORTNAME=Y.TEAMCOMSHORTNAME AND X.COUNTCOMCODE=Y.COUNTCOMCODE AND X.ACTUTEAMCOMSHORTNAME=Y.ACTUTEAMCOMSHORTNAME AND X.TEAMCOMCODE=Y.TEAMCOMCODE AND X.TEAMCOMNAME=Y.TEAMCOMNAME AND X.HEADGROUPCODE=Y.HEADGROUPCODE AND X.HEADGROUPNAME=Y.HEADGROUPNAME AND X.PROVGROUPCODE=Y.PROVGROUPCODE AND X.PROVGROUPNAME=Y.PROVGROUPNAME AND X.COUNTGROUPCODE=Y.COUNTGROUPCODE AND X.COUNTGROUPNAME=Y.COUNTGROUPNAME AND X.AGENT_ID=Y.AGENT_ID AND X.AGENTNAME=Y.AGENTNAME AND X.AGENTCODE=Y.AGENTCODE");
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql702);
				sql = sql.replace("{orgcode_group_names1}", paramSql702);
				sql = sql.replace("{orgcode_group_names2}",
						"X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME,X.COUNTGROUPCODE,X.COUNTGROUPNAME,X.AGENT_ID,X.AGENTNAME,X.AGENTCODE");
				sql = sql.replace("{orgcode_group_names_join1}",
						"X.PROVCOMCODE=Y.PROVCOMCODE AND X.TEAMCOMSHORTNAME=Y.TEAMCOMSHORTNAME AND X.COUNTCOMCODE=Y.COUNTCOMCODE AND X.ACTUTEAMCOMSHORTNAME=Y.ACTUTEAMCOMSHORTNAME AND X.COST_CENTERSTDCODE=Y.COST_CENTERSTDCODE AND X.COST_CENTERDEVNAME=Y.COST_CENTERDEVNAME AND X.HEADGROUPCODE=Y.HEADGROUPCODE AND X.HEADGROUPNAME=Y.HEADGROUPNAME AND X.PROVGROUPCODE=Y.PROVGROUPCODE AND X.PROVGROUPNAME=Y.PROVGROUPNAME AND X.COUNTGROUPCODE=Y.COUNTGROUPCODE AND X.COUNTGROUPNAME=Y.COUNTGROUPNAME AND X.AGENT_ID=Y.AGENT_ID AND X.AGENTNAME=Y.AGENTNAME AND X.AGENTCODE=Y.AGENTCODE");
			}
			break;
		}
		sql = sql.replace("{teamcom_fields}", teamcom_fields);
		return sql;
	}

	/**
	 * @功能 {清单处理分组SQL-按交集}
	 * @作者 MaxBill
	 * @时间 2017年9月21日 上午9:32:27
	 */
	public String tempTableSql(String commonOrg, String sql, Boolean isServer) {
		// 去除sql显示样式
		sql = sql.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		String teamcom_fields = "";
		switch (commonOrg) {
		// 分公司
		case "7":
			String paramSql101 = "PROVCOMCODE,TEAMCOMSHORTNAME";
			sql = sql.replace("{orgcode_group_names}", paramSql101);
			sql = sql.replace("{orgcode_group_names1}", " D.PROVCOMCODE,D.TEAMCOMSHORTNAME ");
			sql = sql.replace("{orgcode_group_names2}", " X.PROVCOMCODE,X.TEAMCOMSHORTNAME ");
			sql = sql.replace("{orgcode_group_names_join1}", " X.PROVCOMCODE = Y.PROVCOMCODE ");
			break;
		// 中支
		case "6":
			String paramSql201 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME";
			sql = sql.replace("{orgcode_group_names}", paramSql201);
			sql = sql.replace("{orgcode_group_names1}",
					" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME ");
			sql = sql.replace("{orgcode_group_names2}",
					" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME ");
			sql = sql.replace("{orgcode_group_names_join1}",
					" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE ");
			break;
		// 营销服务部
		case "5":
			String paramSql301 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME";
			String paramSql302 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql301);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.TEAMCOMCODE,D.TEAMCOMNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.TEAMCOMCODE = Y.TEAMCOMCODE ");
				teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql302);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.COST_CENTERSTDCODE,D.COST_CENTERDEVNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.COST_CENTERSTDCODE = Y.COST_CENTERSTDCODE ");
			}
			break;
		// 区
		case "4":
			String paramSql401 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME,HEADGROUPCODE,HEADGROUPNAME";
			String paramSql402 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME,HEADGROUPCODE,HEADGROUPNAME";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql401);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.TEAMCOMCODE,D.TEAMCOMNAME,D.HEADGROUPCODE,D.HEADGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME,X.HEADGROUPCODE,X.HEADGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.TEAMCOMCODE = Y.TEAMCOMCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE ");
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql402);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.COST_CENTERSTDCODE,D.COST_CENTERDEVNAME,D.HEADGROUPCODE,D.HEADGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME,X.HEADGROUPCODE,X.HEADGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.COST_CENTERSTDCODE = Y.COST_CENTERSTDCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE ");
			}
			break;
		// 部
		case "3":
			String paramSql501 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME";
			String paramSql502 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql501);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.TEAMCOMCODE,D.TEAMCOMNAME,D.HEADGROUPCODE,D.HEADGROUPNAME,D.PROVGROUPCODE,D.PROVGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.TEAMCOMCODE = Y.TEAMCOMCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE AND X.PROVGROUPCODE = Y.PROVGROUPCODE ");
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql502);
				sql = sql.replace("{orgcode_group_names}", paramSql501);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.COST_CENTERSTDCODE,D.COST_CENTERDEVNAME,D.HEADGROUPCODE,D.HEADGROUPNAME,D.PROVGROUPCODE,D.PROVGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.COST_CENTERSTDCODE = Y.COST_CENTERSTDCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE AND X.PROVGROUPCODE = Y.PROVGROUPCODE ");
			}
			break;
		// 组
		case "2":
			String paramSql601 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME,COUNTGROUPCODE,COUNTGROUPNAME";
			String paramSql602 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME,COUNTGROUPCODE,COUNTGROUPNAME";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql601);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.TEAMCOMCODE,D.TEAMCOMNAME,D.HEADGROUPCODE,D.HEADGROUPNAME,D.PROVGROUPCODE,D.PROVGROUPNAME,D.COUNTGROUPCODE,D.COUNTGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME,X.COUNTGROUPCODE,X.COUNTGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.TEAMCOMCODE = Y.TEAMCOMCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE AND X.PROVGROUPCODE = Y.PROVGROUPCODE AND X.COUNTGROUPNAME = Y.COUNTGROUPNAME ");
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql602);
				sql = sql.replace("{orgcode_group_names1}",
						" D.PROVCOMCODE,D.TEAMCOMSHORTNAME,D.COUNTCOMCODE,D.ACTUTEAMCOMSHORTNAME,D.COST_CENTERSTDCODE,D.COST_CENTERDEVNAME,D.HEADGROUPCODE,D.HEADGROUPNAME,D.PROVGROUPCODE,D.PROVGROUPNAME,D.COUNTGROUPCODE,D.COUNTGROUPNAME ");
				sql = sql.replace("{orgcode_group_names2}",
						" X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME,X.COUNTGROUPCODE,X.COUNTGROUPNAME ");
				sql = sql.replace("{orgcode_group_names_join1}",
						" X.PROVCOMCODE = Y.PROVCOMCODE AND X.COUNTCOMCODE = Y.COUNTCOMCODE AND X.COST_CENTERSTDCODE = Y.COST_CENTERSTDCODE AND X.HEADGROUPCODE = Y.HEADGROUPCODE AND X.PROVGROUPCODE = Y.PROVGROUPCODE AND X.COUNTGROUPNAME = Y.COUNTGROUPNAME ");
			}
			break;
		// 代理人
		case "1":
			String paramSql701 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,TEAMCOMCODE,TEAMCOMNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME,COUNTGROUPCODE,COUNTGROUPNAME,AGENT_ID,AGENTNAME,AGENTCODE";
			String paramSql702 = "PROVCOMCODE,TEAMCOMSHORTNAME,COUNTCOMCODE,ACTUTEAMCOMSHORTNAME,COST_CENTERSTDCODE,COST_CENTERDEVNAME,HEADGROUPCODE,HEADGROUPNAME,PROVGROUPCODE,PROVGROUPNAME,COUNTGROUPCODE,COUNTGROUPNAME,AGENT_ID,AGENTNAME,AGENTCODE";
			if (isServer) {
				sql = sql.replace("{orgcode_group_names}", paramSql701);
				sql = sql.replace("{orgcode_group_names1}", paramSql701);
				sql = sql.replace("{orgcode_group_names2}",
						"X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.TEAMCOMCODE,X.TEAMCOMNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME,X.COUNTGROUPCODE,X.COUNTGROUPNAME,X.AGENT_ID,X.AGENTNAME,X.AGENTCODE");
				sql = sql.replace("{orgcode_group_names_join1}",
						"X.PROVCOMCODE=Y.PROVCOMCODE AND X.TEAMCOMSHORTNAME=Y.TEAMCOMSHORTNAME AND X.COUNTCOMCODE=Y.COUNTCOMCODE AND X.ACTUTEAMCOMSHORTNAME=Y.ACTUTEAMCOMSHORTNAME AND X.TEAMCOMCODE=Y.TEAMCOMCODE AND X.TEAMCOMNAME=Y.TEAMCOMNAME AND X.HEADGROUPCODE=Y.HEADGROUPCODE AND X.HEADGROUPNAME=Y.HEADGROUPNAME AND X.PROVGROUPCODE=Y.PROVGROUPCODE AND X.PROVGROUPNAME=Y.PROVGROUPNAME AND X.COUNTGROUPCODE=Y.COUNTGROUPCODE AND X.COUNTGROUPNAME=Y.COUNTGROUPNAME AND X.AGENT_ID=Y.AGENT_ID AND X.AGENTNAME=Y.AGENTNAME AND X.AGENTCODE=Y.AGENTCODE");
			} else {
				sql = sql.replace("{orgcode_group_names}", paramSql702);
				sql = sql.replace("{orgcode_group_names1}", paramSql702);
				sql = sql.replace("{orgcode_group_names2}",
						"X.PROVCOMCODE,X.TEAMCOMSHORTNAME,X.COUNTCOMCODE,X.ACTUTEAMCOMSHORTNAME,X.COST_CENTERSTDCODE,X.COST_CENTERDEVNAME,X.HEADGROUPCODE,X.HEADGROUPNAME,X.PROVGROUPCODE,X.PROVGROUPNAME,X.COUNTGROUPCODE,X.COUNTGROUPNAME,X.AGENT_ID,X.AGENTNAME,X.AGENTCODE");
				sql = sql.replace("{orgcode_group_names_join1}",
						"X.PROVCOMCODE=Y.PROVCOMCODE AND X.TEAMCOMSHORTNAME=Y.TEAMCOMSHORTNAME AND X.COUNTCOMCODE=Y.COUNTCOMCODE AND X.ACTUTEAMCOMSHORTNAME=Y.ACTUTEAMCOMSHORTNAME AND X.COST_CENTERSTDCODE=Y.COST_CENTERSTDCODE AND X.COST_CENTERDEVNAME=Y.COST_CENTERDEVNAME AND X.HEADGROUPCODE=Y.HEADGROUPCODE AND X.HEADGROUPNAME=Y.HEADGROUPNAME AND X.PROVGROUPCODE=Y.PROVGROUPCODE AND X.PROVGROUPNAME=Y.PROVGROUPNAME AND X.COUNTGROUPCODE=Y.COUNTGROUPCODE AND X.COUNTGROUPNAME=Y.COUNTGROUPNAME AND X.AGENT_ID=Y.AGENT_ID AND X.AGENTNAME=Y.AGENTNAME AND X.AGENTCODE=Y.AGENTCODE");
			}
			String[] sqlStr01 = null;
			if (sql.contains("WHERE 1=1")) {
				sqlStr01 = sql.split("WHERE 1=1");
			}
			if (sql.contains("WHERE 1 = 1")) {
				sqlStr01 = sql.split("WHERE 1 = 1");
			}
			if (sql.contains("WHERE  1=1")) {
				sqlStr01 = sql.split("WHERE  1=1");
			}
			if (sql.contains("WHERE  1 = 1")) {
				sqlStr01 = sql.split("WHERE  1 = 1");
			}
			sql = sqlStr01[0] + " JOIN D_AGENT DA ON A.AGENT_CODE = DA.AGENTID WHERE 1=1 " + sqlStr01[1];
			String[] sqlStr02 = null;
			if (sql.contains("WHERE 2 = 2")) {
				sqlStr02 = sql.split("WHERE 2 = 2");
				sql = sqlStr02[0] + " JOIN D_AGENT DA ON A.AGENT_CODE = DA.AGENTID WHERE 2 = 2 " + sqlStr02[1];
			}
			break;
		}
		sql = sql.replace("{teamcom_fields}", teamcom_fields);
		return sql;
	}

	/**
	 * @功能 {清单处理关联SQL-按交集}
	 * @作者 MaxBill
	 * @时间 2017年9月21日 上午9:56:56
	 */
	public String dealJoinSql(String commonOrg, Boolean isServer, Integer index) {
		String joinSql = "";
		switch (commonOrg) {
		// 分公司
		case "7":
			joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE";
			break;
		// 中支
		case "6":
			joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
					+ ".COUNTCOMCODE";
			break;
		// 营销服务部
		case "5":
			if (isServer) {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.TEAMCOMCODE=T0" + index + ".TEAMCOMCODE";
			} else {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.COST_CENTERSTDCODE=T0" + index + ".COST_CENTERSTDCODE";
			}
			break;
		// 区
		case "4":
			if (isServer) {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.TEAMCOMCODE=T0" + index + ".TEAMCOMCODE AND T00.HEADGROUPCODE=T0"
						+ index + ".HEADGROUPCODE";
			} else {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.COST_CENTERSTDCODE=T0" + index
						+ ".COST_CENTERSTDCODE AND T00.HEADGROUPCODE=T0" + index + ".HEADGROUPCODE";
			}
			break;
		// 部
		case "3":
			if (isServer) {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.TEAMCOMCODE=T0" + index + ".TEAMCOMCODE AND T00.HEADGROUPCODE=T0"
						+ index + ".HEADGROUPCODE AND T00.PROVGROUPCODE=T0" + index + ".PROVGROUPCODE";
			} else {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.COST_CENTERSTDCODE=T0" + index
						+ ".COST_CENTERSTDCODE AND T00.HEADGROUPCODE=T0" + index
						+ ".HEADGROUPCODE AND T00.PROVGROUPCODE=T0" + index + ".PROVGROUPCODE";
			}
			break;
		// 组
		case "2":
			if (isServer) {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.TEAMCOMCODE=T0" + index + ".TEAMCOMCODE AND T00.HEADGROUPCODE=T0"
						+ index + ".HEADGROUPCODE AND T00.PROVGROUPCODE=T0" + index
						+ ".PROVGROUPCODE AND T00.COUNTGROUPCODE=T0" + index + ".COUNTGROUPCODE";
			} else {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.COST_CENTERSTDCODE=T0" + index
						+ ".COST_CENTERSTDCODE AND T00.HEADGROUPCODE=T0" + index
						+ ".HEADGROUPCODE AND T00.PROVGROUPCODE=T0" + index + ".PROVGROUPCODE AND T00.COUNTGROUPCODE=T0"
						+ index + ".COUNTGROUPCODE";
			}
			break;
		// 代理人
		case "1":
			if (isServer) {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.TEAMCOMCODE=T0" + index + ".TEAMCOMCODE AND T00.HEADGROUPCODE=T0"
						+ index + ".HEADGROUPCODE AND T00.PROVGROUPCODE=T0" + index
						+ ".PROVGROUPCODE AND T00.COUNTGROUPCODE=T0" + index + ".COUNTGROUPCODE AND T00.AGENT_ID=T0"
						+ index + ".AGENT_ID AND T00.AGENTNAME=T0" + index + ".AGENTNAME";
			} else {
				joinSql = joinSql + "ON T00.PROVCOMCODE = T0" + index + ".PROVCOMCODE AND T00.COUNTCOMCODE = T0" + index
						+ ".COUNTCOMCODE AND T00.COST_CENTERSTDCODE=T0" + index
						+ ".COST_CENTERSTDCODE AND T00.HEADGROUPCODE=T0" + index
						+ ".HEADGROUPCODE AND T00.PROVGROUPCODE=T0" + index + ".PROVGROUPCODE AND T00.COUNTGROUPCODE=T0"
						+ index + ".COUNTGROUPCODE AND T00.AGENT_ID=T0" + index + ".AGENT_ID AND T00.AGENTNAME=T0"
						+ index + ".AGENTNAME";
			}
			break;
		}
		return joinSql;
	}

	/**
	 * @功能 {封装清单单条sql的筛选参数}
	 * @作者 MaxBill
	 * @时间 2017年9月21日 下午12:39:07
	 */
	public String llqdSqlParam(String sql, Integer billId, String target, JSONObject formObj) {
		String finalSqlCode01 = "";
		sql = sql.replace("1 = 1", "1=1");
		// 解析JSON数据
		JSONObject dateObj = formObj.getJSONObject("dateRange");
		String beginDate = "";// 开始时间
		String endDate = "";// 结束时间
		if (null == dateObj) {
			beginDate = DateUtil.getCurrentMonthFirstDay();
			endDate = DateUtil.formatDate(new Date());
		} else {
			beginDate = dateObj.getString("beginDate");
			endDate = dateObj.getString("endDate");
		}
		// 替换查询时间参数
		sql = sql.replace("{start_time}", "'" + beginDate + "'");
		sql = sql.replace("{end_time}", "'" + endDate + "'");
		List<BillFiltDim> billFiltDimList = browseListService.findBillFiltDimByBillAndTar(billId, target);
		if (sql.contains("1=1")) {
			if (null != billFiltDimList && !billFiltDimList.isEmpty()) {
				// 封装查询参数
				String querySql = "";
				for (BillFiltDim billFiltDim : billFiltDimList) {
					String filtDim = billFiltDim.getFiltDimCode();
					String filtDimJson = formObj.getString(filtDim);
					if (null != filtDimJson && !filtDimJson.equals("")) {
						List<String> dimValue = JSON.parseArray(filtDimJson, String.class);
						// 查询对应的字段
						InterField interField = commonService.findInterFieldByTabAndTar(billFiltDim.getTargetCode(),
								filtDim);
						if (null != interField) {
							// 查询筛选维度信息
							QueryDim queryDimTemp = this.commonService.findQueryDimByCode(filtDim);
							switch (queryDimTemp.getQueryDimShowType()) {
							case "checkbox":
								querySql = querySql + " AND " + interField.getFieldCode() + " IN(";
								for (String val : dimValue) {
									if (val.contains("#")) {
										String[] param01Array = val.split("#");
										String tempParm = "";
										for (String parm : param01Array) {
											tempParm = tempParm + "'" + parm + "', ";
										}
										querySql = querySql + tempParm;
									} else {
										querySql = querySql + "'" + val + "' , ";
									}
								}
								querySql = querySql.substring(0, querySql.length() - 2) + ")";
								break;
							case "select":
								querySql = querySql + " AND " + interField.getFieldCode() + " IN(";
								for (String val : dimValue) {
									if (val.contains("#")) {
										String[] param01Array = val.split("#");
										String tempParm = "";
										for (String parm : param01Array) {
											tempParm = tempParm + "'" + parm + "', ";
										}
										querySql = querySql + tempParm;
									} else {
										querySql = querySql + "'" + val + "' ";
									}
								}
								querySql = querySql.substring(0, querySql.length() - 1) + ")";
								break;
							case "select2":
								if (!dimValue.get(0).equals("-1") && !dimValue.get(1).equals("-1")) {
									querySql = querySql + " AND " + interField.getFieldCode() + " BETWEEN "
											+ dimValue.get(0) + " AND " + dimValue.get(1);
								} else {
									if (dimValue.get(0).equals("-1")) {
										querySql = querySql + " AND " + interField.getFieldCode() + " <= "
												+ dimValue.get(1);
									}
									if (dimValue.get(1).equals("-1")) {
										querySql = querySql + " AND " + interField.getFieldCode() + " >= "
												+ dimValue.get(0);
									}
								}
								break;
							}
						} else {
							System.err.println(billFiltDim.getTargetCode() + "指标的[" + filtDim + "]维度的接口字段未配置...");
						}
					}
				}
				if (!querySql.equals("")) {
					querySql = " 1=1 " + querySql;
				} else {
					querySql = " 1=1 ";
				}
				String[] sqlArray = sql.split("1=1");
				for (int i = 0; i < sqlArray.length; i++) {
					if (i == sqlArray.length - 1) {
						finalSqlCode01 += sqlArray[i];
					} else {
						finalSqlCode01 += (sqlArray[i] + querySql);
					}
				}
			} else {
				finalSqlCode01 = sql;
			}
		} else {
			finalSqlCode01 = sql;
		}
		String finalSqlCode02 = "";
		// 目标表2=2情况处理
		if (finalSqlCode01.contains("2 = 2")) {
			if (null != billFiltDimList && !billFiltDimList.isEmpty()) {
				String querySql = "GRADETYPECODE IN (";
				for (BillFiltDim billFiltDim : billFiltDimList) {
					String filtDim = billFiltDim.getFiltDimCode();
					if (filtDim.equals("CHANNEL_TYPE")) {
						String filtDimJson = formObj.getString(filtDim);
						if (null != filtDimJson && !filtDimJson.equals("")) {
							List<String> dimValue = JSON.parseArray(filtDimJson, String.class);
							for (String param : dimValue) {
								if (param.contains("#")) {
									String[] param01Array = param.split("#");
									String tempParm = "";
									for (String parm : param01Array) {
										tempParm = tempParm + "'" + parm + "', ";
									}
									querySql = querySql + tempParm;
								} else {
									querySql = querySql + "'" + param + "', ";
								}
							}
							querySql = querySql.substring(0, querySql.length() - 2) + ") ";
						}
					}
				}
				String[] sqlArray = sql.split("2 = 2");
				if (!querySql.equals("GRADETYPECODE IN (")) {
					finalSqlCode02 = sqlArray[0] + " 2 = 2 AND " + querySql + sqlArray[1];
				} else {
					finalSqlCode02 = sqlArray[0] + " 2 = 2 " + sqlArray[1];
				}
			} else {
				finalSqlCode02 = finalSqlCode01;
			}
		} else {
			finalSqlCode02 = finalSqlCode01;
		}
		return finalSqlCode02;
	}

	/**
	 * 
	 * @功能 {查询指标公共机构-交集}
	 * @作者 MaxBill
	 * @时间 2017年8月25日 下午2:54:14
	 */
	public String getUnionOrg(int type, Object targes) {
		// 1:字符串集合，2:指标对象集合，3:JSONObject集合，4:清单指标对象集合
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		switch (type) {
		case 1:
			List<String> target1List = (List<String>) targes;
			if (null != target1List && target1List.size() > 0) {
				for (String target : target1List) {
					TarGroDim tarGroDim = browseListService.findUnionTarGroDimByTar(target);
					if (null != tarGroDim) {
						treeSet.add(tarGroDim.getGddId());
					}
				}
			}
			break;
		case 2:
			List<Target> target2List = (List<Target>) targes;
			if (null != target2List && target2List.size() > 0) {
				for (Target target : target2List) {
					TarGroDim tarGroDim = browseListService.findUnionTarGroDimByTar(target.getTargetCode());
					if (null != tarGroDim) {
						treeSet.add(tarGroDim.getGddId());
					}
				}
			}
			break;
		case 3:
			List<JSONObject> target3List = (List<JSONObject>) targes;
			if (null != target3List && target3List.size() > 0) {
				for (JSONObject targetJson : target3List) {
					TarGroDim tarGroDim = browseListService.findUnionTarGroDimByTar(targetJson.getString("code"));
					if (null != tarGroDim) {
						treeSet.add(tarGroDim.getGddId());
					}
				}
			}
			break;
		case 4:
			List<BillTarget> target4List = (List<BillTarget>) targes;
			if (null != target4List && target4List.size() > 0) {
				for (BillTarget billTarget : target4List) {
					TarGroDim tarGroDim = browseListService.findUnionTarGroDimByTar(billTarget.getTargetCode());
					if (null != tarGroDim) {
						treeSet.add(tarGroDim.getGddId());
					}
				}
			}
			break;
		}
		return browseListService.findGroDimDetailById(treeSet.last()).getGroDimCode();
	}

	/**
	 * 
	 * @功能 {查询指标公共机构-交集}
	 * @作者 MaxBill
	 * @时间 2017年8月25日 下午2:54:14
	 */
	public String getCommonOrg(Integer type, Object targes) {
		// 1:字符串集合，2:指标对象集合，3:JSONObject集合，4:清单指标对象集合
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		switch (type) {
		case 1:
			List<String> target1List = (List<String>) targes;
			if (null != target1List && target1List.size() > 0) {
				for (String target : target1List) {
					TarGroDim tarGroDim = browseListService.findTarGroDimByTar(target);
					if (null != tarGroDim) {
						treeSet.add(tarGroDim.getGddId());
					}
				}
			}
			break;
		case 2:
			List<Target> target2List = (List<Target>) targes;
			if (null != target2List && target2List.size() > 0) {
				for (Target target : target2List) {
					TarGroDim tarGroDim = browseListService.findTarGroDimByTar(target.getTargetCode());
					if (null != tarGroDim) {
						treeSet.add(tarGroDim.getGddId());
					}
				}
			}
			break;
		case 3:
			List<JSONObject> target3List = (List<JSONObject>) targes;
			if (null != target3List && target3List.size() > 0) {
				for (JSONObject targetJson : target3List) {
					TarGroDim tarGroDim = browseListService.findTarGroDimByTar(targetJson.getString("code"));
					if (null != tarGroDim) {
						treeSet.add(tarGroDim.getGddId());
					}
				}
			}
			break;
		case 4:
			List<BillTarget> target4List = (List<BillTarget>) targes;
			if (null != target4List && target4List.size() > 0) {
				for (BillTarget billTarget : target4List) {
					TarGroDim tarGroDim = browseListService.findTarGroDimByTar(billTarget.getTargetCode());
					if (null != tarGroDim) {
						treeSet.add(tarGroDim.getGddId());
					}
				}
			}
			break;
		}
		return browseListService.findGroDimDetailById(treeSet.first()).getGroDimCode();
	}
}
