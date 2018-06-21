package com.huatai.web.thrift.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.bean.BillBean;
import com.huatai.web.bean.DataPageBean;
import com.huatai.web.bean.FilterBean;
import com.huatai.web.bean.FixedBillBean;
import com.huatai.web.bean.QueryDimBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.bean.TermBean;
import com.huatai.web.model.Bill;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillGroDim;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.Target;
import com.huatai.web.sql.CommonSqlService;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.sql.SqlFactory;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.BrowseListService;
import com.huatai.web.utils.DateUtil;

/**
 * @功能 {浏览清单服务}
 * @作者 MaxBill
 * @时间 2017年8月26日 下午2:07:48
 */
public class LlqdServiceImpl implements LlqdService.Iface {

	private SqlFactory sqlFactory;
	private CommonUtil commonUtil;
	private CommonSqlService commonSqlService;

	public LlqdServiceImpl() {
		sqlFactory = SpringUtil.getBean(SqlFactory.class);
		commonUtil = SpringUtil.getBean(CommonUtil.class);
		commonSqlService = SpringUtil.getBean(CommonSqlService.class);
	}

	Logger log = Logger.getLogger(ZtfxServiceImpl.class);

	/**
	 * @状态 ok
	 * @功能 {查询清单是否营销服务部}01
	 * @作者 MaxBill
	 * @时间 2017年7月18日 下午2:31:32
	 */
	public ResponseBeanDto browseIsServer(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			Map resultMap = new HashMap();
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Integer billId = jsonObject.getInteger("browseCode");
			// 判断是营销服务部的还是成本中心的:如果所有的指标中有营销服务部的（培训部，个业部）的，则显示为营销服务部，否则显示为成本中心
			Boolean isServer = false;
			List<BillTarget> billTargetList = browseListService.findBillTargetByBillEasy(billId);
			for (int i = 0; i < billTargetList.size(); i++) {
				String targetCode = billTargetList.get(i).getTargetCode();
				if (commonUtil.isGeYeAndPeiXunDept(browseListService.findTargetByCode(targetCode).getDeptCode())) {
					isServer = true;
				}
			}
			resultMap.put("isServer", isServer);
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			dataPrintTips(1, resultMap);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {浏览清单菜单接口}02
	 * @作者 MaxBill
	 * @时间 2017年7月28日 上午9:39:43
	 */
	public ResponseBeanDto browseListMenu(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String username = jsonObject.getString("username");// 用户名
			Map resultMap = new HashMap();
			List<Object> fixedList = new ArrayList<Object>();
			// 查询固定实时清单
			FixedBillBean fixedBill = new FixedBillBean();
			fixedBill.setTitle("实时指标");
			fixedBill.setCode("REAL_TIME_TITLE");
			fixedBill.setList(new ArrayList<>());
			fixedList.add(fixedBill);
			// 查询读固定部分清单
			List<FixedBillBean> fixedBillBeanList = browseListService.findFixedListByRole(role);
			fixedList.add(fixedBillBeanList);
			resultMap.put("fixed", fixedList);
			// 查询个人清单
			List<BillBean> billList = browseListService.findMyBillList(role, username);
			resultMap.put("custom", billList);
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			dataPrintTips(2, resultMap);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {浏览清单信息接口}03
	 * @作者 MaxBill
	 * @时间 2017年7月28日 上午9:43:41
	 */
	public ResponseBeanDto browseListInfo(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Integer browseId = jsonObject.getInteger("browseCode");// 自定义清单ID
			// 查询维度数据封装
			List<BillFiltDim> billFiltDimList = browseListService.findBillFiltDimByBill(browseId);
			List<QueryDimBean> queryDimList = new ArrayList<QueryDimBean>();
			if (null != billFiltDimList) {
				for (BillFiltDim billFiltDim : billFiltDimList) {
					QueryDim queryDim = browseListService.findQueryDimByCode(billFiltDim.getFiltDimCode());
					QueryDimBean queryDimBean = new QueryDimBean();
					queryDimBean.setName(queryDim.getQueryDimName());
					queryDimBean.setCode(queryDim.getQueryDimCode());
					List<FilterBean> filterBeanList = browseListService
							.findQueryDimDetailListByDimId(queryDim.getQdId(), queryDim.getQueryDimShowType());
					queryDimBean.setValue(filterBeanList);
					queryDimList.add(queryDimBean);
				}
			}
			resultMap.put("form", queryDimList);
			// 判断是营销服务部的还是成本中心的:如果所有的指标中有营销服务部的（培训部，个业部）的，则显示为营销服务部，否则显示为成本中心
			Boolean isServer = false;
			List<BillTarget> billTargetList = browseListService.findBillTargetByBill(browseId);
			for (int i = 0; i < billTargetList.size(); i++) {
				String deptCode = billTargetList.get(i).getDeptCode();
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					isServer = true;
				}
			}
			// 按选择中的指标取交集的最低机构
			BillGroDim billGroDim = browseListService.findBillGroDimByBill(browseId);
			String commonOrg = billGroDim.getGroupDetailCode();
			// 返回机构联动显示范围
			resultMap.put("orgRang", commonUtil.getOrgType(commonOrg));
			// 按指标交集封装伪sql参数
			Map<String, String> sqlMap = commonSqlService.getListInfoTitle(isServer, commonOrg);
			String index = sqlMap.get("index");
			String title = sqlMap.get("title");
			String value = sqlMap.get("value");
			String codes = sqlMap.get("value");
			// 封装指标sql
			for (int i = 0; i < billTargetList.size(); i++) {
				BillTarget billTarget = billTargetList.get(i);
				String targetName = billTarget.getTargetName();
				String targetCode = billTarget.getTargetCode();
				String unit = billTarget.getUnit();
				if (!StringUtils.isEmpty(unit)) {
					unit = commonUtil.getTargetUnit(unit);
					unit = "(" + unit + "),";
				} else {
					unit = ",";
				}
				// 拼装列表标题头
				int indexVals = i + Integer.parseInt(index);
				title = title + targetName + unit;
				value = value + "VAL" + indexVals + ",";
				codes = codes + targetCode + ",";
			}
			// 处理列表标题头
			title = title.substring(0, title.length() - 1);
			// 处理列表标题值
			value = value.substring(0, value.length() - 1);
			// 处理列指标代码
			codes = codes.substring(0, codes.length() - 1);
			List<Object> headersList = new ArrayList<Object>();
			headersList.add(title.split(","));
			headersList.add(value.split(","));
			headersList.add(codes.split(","));
			resultMap.put("headers", headersList);
			resultMap.put("isServer", isServer);
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			dataPrintTips(3, resultMap);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {浏览清单列表接口}03
	 * @作者 MaxBill
	 * @时间 2017年7月28日 上午9:43:41
	 */
	public ResponseBeanDto browseListData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			Long reqStartTime = System.currentTimeMillis();
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 用户机构
			Integer browseId = jsonObject.getInteger("browseCode");// 自定义清单ID
			JSONObject formObj = jsonObject.getJSONObject("form");// 表单数据
			String orgObj = jsonObject.getString("org");// 机构数据
			JSONObject orderObj = jsonObject.getJSONObject("order");// 排序数据
			// 处理机构参数
			String orgType = "";
			List<String> orgCodeList = new ArrayList<String>();
			List<String> orgList = null;
			if (null == orgObj) {
				switch (roleOrg.length()) {
				case 1:
					orgCodeList.add(roleOrg);
					orgType = "1";
					break;
				case 3:
					orgCodeList.add(roleOrg);
					orgType = "2";
					break;
				case 5:
					orgCodeList.add(roleOrg.substring(0, 3));
					orgCodeList.add(roleOrg);
					orgType = "3";
					break;
				default:
					orgCodeList.add(roleOrg.substring(0, 3));
					orgCodeList.add(roleOrg.substring(0, 5));
					orgCodeList.add(roleOrg);
					orgType = "4";
					break;
				}
			} else {
				orgList = JSON.parseArray(orgObj, String.class);
				orgType = orgList.size() + 1 + "";
				orgCodeList.addAll(orgList);
			}
			// 判断是营销服务部的还是成本中心的:如果所有的指标中有营销服务部的（培训部，个业部）的，则显示为营销服务部，否则显示为成本中心
			Boolean isServer = false;
			List<BillTarget> billTargetList = browseListService.findBillTargetByBill(browseId);
			for (int i = 0; i < billTargetList.size(); i++) {
				String deptCode = billTargetList.get(i).getDeptCode();
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					isServer = true;
				}
			}
			// 按选择中的指标取交集的最低机构
			BillGroDim billGroDim = browseListService.findBillGroDimByBill(browseId);
			String commonOrg = billGroDim.getGroupDetailCode();
			// 最终sql
			String finalSql = "";
			// 按指标交集封装伪sql参数
			Map<String, String> sqlMap = commonSqlService.getListDataTitle(isServer, commonOrg);
			String index = sqlMap.get("index");
			String baseStr = sqlMap.get("baseStr");
			String tempSql = "";
			// 临时表
			String tempTab = "WITH ";
			String baseTab = "";
			// 封装指标sql
			for (int i = 0; i < billTargetList.size(); i++) {
				BillTarget billTarget = billTargetList.get(i);
				String targetCode = billTarget.getTargetCode();
				String sql = billTarget.getSqlCode();
				// 单条sql拼装参数
				if (null == formObj) {
					sql = sql.replace("{start_time}", "'" + DateUtil.getCurrentMonthFirstDay() + "'");
					sql = sql.replace("{end_time}", "'" + DateUtil.formatDate(new Date()) + "'");
				} else {
					sql = commonSqlService.llqdSqlParam(sql, browseId, targetCode, formObj);
				}
				String baseParm = "";// sql拼装val参数
				// 指标值条件处理
				String valTemp = "DECODE(VAL,'--',0,VAL)";
				String valType = billTarget.getFormulaType();
				switch (valType) {
				case "no":// 公式
					break;
				case "lt":// 指标值小于
					baseParm = valTemp + " < " + billTarget.getMaxVal();
					break;
				case "gt":// 指标值大于
					baseParm = valTemp + " > " + billTarget.getMinVal();
					break;
				case "range":
					baseParm = valTemp + " >= " + billTarget.getMinVal() + " AND " + valTemp + " <= "
							+ billTarget.getMaxVal();
					break;
				}
				// 拼装临时表
				String tempTabSql = commonSqlService.tempTableSql(commonOrg, sql, isServer);
				// 判断是否有公式设置
				if (baseParm.equals("")) {
					tempTab = tempTab + "TAB" + (i + 1) + " AS (  SELECT * FROM ( " + tempTabSql + " ) ) , ";
				} else {
					tempTab = tempTab + "TAB" + (i + 1) + " AS (  SELECT * FROM ( " + tempTabSql + " ) WHERE "
							+ baseParm + " ) , ";
				}
				// 拼装基础表
				baseTab = baseTab + "SELECT {orgcode_group_names} FROM TAB" + (i + 1) + " UNION ";
				baseTab = commonSqlService.baseTableSql(commonOrg, baseTab, isServer);
				int indexVals = i + Integer.parseInt(index);
				int indexJoin = i + 1;
				baseStr = baseStr + "NVL2(T0" + indexJoin + ".VAL,T0" + indexJoin + ".VAL||'','--') AS VAL" + indexVals
						+ ", ";
				tempSql = tempSql + "LEFT JOIN ( SELECT * FROM TAB" + indexJoin + " ) T0" + indexJoin + " ";
				String joinSql = commonSqlService.dealJoinSql(commonOrg, isServer, indexJoin);
				tempSql = tempSql + joinSql + " ";
			}
			// 处理拼装临时表
			tempTab = tempTab.substring(0, tempTab.length() - 2);
			// 处理查询的字段
			baseStr = baseStr.substring(0, baseStr.length() - 2) + " ";
			// 处理拼装基础表
			baseTab = baseTab.substring(0, baseTab.length() - 6);
			// 是否按字段进行排序
			String rankSql = "SELECT * FROM ( SELECT * FROM (";
			if (null != orderObj) {
				String rankCode = orderObj.getString("code");
				String rankType = orderObj.getString("sort");
				String targetCode = orderObj.getString("targetCode");
				Target rankTarget = browseListService.findTargetByCode(targetCode);
				// 判断是否数字
				String rankTemp = "";
				if (rankTarget.getIsMath().equals("1")) {
					rankTemp = " ) WHERE 1=1 ORDER BY TO_NUMBER(REPLACE(" + rankCode + ",'--','-9999999')) " + rankType
							+ " ) ";
				} else {
					rankTemp = " ) WHERE 1=1 ORDER BY REPLACE(" + rankCode + ",'--',NULL) " + rankType
							+ " NULLS LAST) ";
				}
				finalSql = tempTab + rankSql + baseStr + " FROM ( " + baseTab + " ) T00 " + tempSql + rankTemp;
			} else {
				finalSql = tempTab + baseStr + " FROM ( " + baseTab + " ) T00 " + tempSql;
			}
			// 替换规则
			finalSql = sqlFactory.getLlqdSql(finalSql, orgType, orgCodeList, isServer);
			resultMap.put("sql", finalSql);
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			dataPrintTips(3, resultMap);
			Long reqEndTime = System.currentTimeMillis();
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>:该清单请求组合查询时间：" + (reqEndTime - reqStartTime) / 1000 + "秒");
			System.err.println("已替换后清单SQL:" + finalSql);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {浏览清单列表下载}04
	 * @作者 MaxBill
	 * @时间 2017年7月28日 上午9:43:41
	 */
	public ResponseBeanDto browseListDown(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 用户机构
			Integer browseId = jsonObject.getInteger("browseCode");// 自定义清单ID
			JSONObject formObj = jsonObject.getJSONObject("form");// 表单数据
			String orgObj = jsonObject.getString("org");// 机构数据
			JSONObject orderObj = jsonObject.getJSONObject("order");// 排序数据
			Bill bill = browseListService.findBillById(browseId);
			// 处理机构参数
			String orgType = "";
			List<String> orgCodeList = new ArrayList<String>();
			List<String> orgList = null;
			if (null == orgObj) {
				switch (roleOrg.length()) {
				case 1:
					orgCodeList.add(roleOrg);
					orgType = "1";
					break;
				case 3:
					orgCodeList.add(roleOrg);
					orgType = "2";
					break;
				case 5:
					orgCodeList.add(roleOrg.substring(0, 3));
					orgCodeList.add(roleOrg);
					orgType = "3";
					break;
				default:
					orgCodeList.add(roleOrg.substring(0, 3));
					orgCodeList.add(roleOrg.substring(0, 5));
					orgCodeList.add(roleOrg);
					orgType = "4";
					break;
				}
			} else {
				orgList = JSON.parseArray(orgObj, String.class);
				orgType = orgList.size() + 1 + "";
				orgCodeList.addAll(orgList);
			}
			// 查询维度数据封装
			List<BillFiltDim> billFiltDimList = browseListService.findBillFiltDimByBill(browseId);
			List<QueryDimBean> queryDimList = new ArrayList<QueryDimBean>();
			if (null != billFiltDimList) {
				for (BillFiltDim billFiltDim : billFiltDimList) {
					QueryDim queryDim = browseListService.findQueryDimByCode(billFiltDim.getFiltDimCode());
					QueryDimBean queryDimBean = new QueryDimBean();
					queryDimBean.setName(queryDim.getQueryDimName());
					queryDimBean.setCode(queryDim.getQueryDimCode());
					List<FilterBean> filterBeanList = browseListService
							.findQueryDimDetailListByDimId(queryDim.getQdId(), queryDim.getQueryDimShowType());
					queryDimBean.setValue(filterBeanList);
					queryDimList.add(queryDimBean);
				}
			}
			resultMap.put("form", queryDimList);
			// 判断是营销服务部的还是成本中心的:如果所有的指标中有营销服务部的（培训部，个业部）的，则显示为营销服务部，否则显示为成本中心
			Boolean isServer = false;
			List<BillTarget> billTargetList = browseListService.findBillTargetByBill(browseId);
			for (int i = 0; i < billTargetList.size(); i++) {
				String deptCode = billTargetList.get(i).getDeptCode();
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					isServer = true;
				}
			}
			// 按选择中的指标取交集的最低机构
			BillGroDim billGroDim = browseListService.findBillGroDimByBill(browseId);
			String commonOrg = billGroDim.getGroupDetailCode();
			// 返回机构联动显示范围
			resultMap.put("orgRang", commonUtil.getOrgType(commonOrg));
			// 最终sql
			String finalSql = "";
			// 按指标交集封装伪sql参数
			Map<String, String> sqlMap = commonSqlService.getListDataTitle(isServer, commonOrg);
			String index = sqlMap.get("index");
			String headSql = sqlMap.get("headSql");
			String baseStr = sqlMap.get("baseStr");
			String tempSql = "";
			// 临时表
			String tempTab = "WITH ";
			String baseTab = "";
			// 封装指标sql
			for (int i = 0; i < billTargetList.size(); i++) {
				BillTarget billTarget = billTargetList.get(i);
				String targetCode = billTarget.getTargetCode();
				String targetName = billTarget.getTargetName();
				String sql = billTarget.getSqlCode();
				// 指标单位显示
				String unit = billTarget.getUnit();
				// 单条sql拼装参数
				if (null == formObj) {
					sql = sql.replace("{start_time}", "'" + DateUtil.getCurrentMonthFirstDay() + "'");
					sql = sql.replace("{end_time}", "'" + DateUtil.formatDate(new Date()) + "'");
				} else {
					sql = commonSqlService.llqdSqlParam(sql, browseId, targetCode, formObj);
				}
				String baseParm = "";// sql拼装val参数
				// 指标值条件处理
				String valTemp = "DECODE(VAL,'--',0,VAL)";
				String valType = billTarget.getFormulaType();
				switch (valType) {
				case "no":// 公式
					break;
				case "lt":// 指标值小于
					baseParm = valTemp + " < " + billTarget.getMaxVal();
					break;
				case "gt":// 指标值大于
					baseParm = valTemp + " > " + billTarget.getMinVal();
					break;
				case "range":
					baseParm = valTemp + " >= " + billTarget.getMinVal() + " AND " + valTemp + " <= "
							+ billTarget.getMaxVal();
					break;
				}
				// 拼装临时表
				String tempTabSql = commonSqlService.tempTableSql(commonOrg, sql, isServer);
				// 判断是否有公式设置
				if (baseParm.equals("")) {
					tempTab = tempTab + "TAB" + (i + 1) + " AS (  SELECT * FROM ( " + tempTabSql + " ) ) , ";
				} else {
					tempTab = tempTab + "TAB" + (i + 1) + " AS (  SELECT * FROM ( " + tempTabSql + " ) WHERE "
							+ baseParm + " ) , ";
				}
				// 拼装基础表
				baseTab = baseTab + "SELECT {orgcode_group_names} FROM TAB" + (i + 1) + " UNION ";
				baseTab = commonSqlService.baseTableSql(commonOrg, baseTab, isServer);
				if (!StringUtils.isEmpty(unit)) {
					unit = commonUtil.getTargetUnit(unit);
					unit = "(" + unit + ")";
				} else {
					unit = "";
				}
				// 拼装列表标题头
				int indexVals = i + Integer.parseInt(index);
				int indexJoin = i + 1;
				headSql = headSql + "'" + targetName + unit + "' AS VAL" + indexVals + ", ";
				baseStr = baseStr + "NVL2(T0" + indexJoin + ".VAL,T0" + indexJoin + ".VAL||'','--') AS VAL" + indexVals
						+ ", ";
				tempSql = tempSql + "LEFT JOIN ( SELECT * FROM TAB" + indexJoin + " ) T0" + indexJoin + " ";
				String joinSql = commonSqlService.dealJoinSql(commonOrg, isServer, indexJoin);
				tempSql = tempSql + joinSql + " ";
			}
			// 处理拼装临时表
			tempTab = tempTab.substring(0, tempTab.length() - 2);
			// 处理列表标题头
			headSql = headSql.substring(0, headSql.length() - 2) + " FROM DUAL UNION ALL ";
			// 处理查询的字段
			baseStr = baseStr.substring(0, baseStr.length() - 2) + " ";
			// 处理拼装基础表
			baseTab = baseTab.substring(0, baseTab.length() - 6);
			// 是否按字段进行排序
			String rankSql = "SELECT * FROM ( SELECT * FROM (";
			if (null != orderObj) {
				String rankCode = orderObj.getString("code");
				String rankType = orderObj.getString("sort");
				String targetCode = orderObj.getString("targetCode");
				Target rankTarget = browseListService.findTargetByCode(targetCode);
				// 判断是否数字
				String rankTemp = "";
				if (rankTarget.getIsMath().equals("1")) {
					rankTemp = " ) WHERE 1=1 ORDER BY TO_NUMBER(REPLACE(" + rankCode + ",'--','-9999999')) " + rankType
							+ " ) ";
				} else {
					rankTemp = " ) WHERE 1=1 ORDER BY REPLACE(" + rankCode + ",'--',NULL) " + rankType
							+ " NULLS LAST) ";
				}
				finalSql = tempTab + headSql + rankSql + baseStr + " FROM ( " + baseTab + " ) T00 " + tempSql
						+ rankTemp;
			} else {
				finalSql = tempTab + headSql + baseStr + " FROM ( " + baseTab + " ) T00 " + tempSql;
			}
			// 替换规则
			finalSql = sqlFactory.getLlqdSql(finalSql, orgType, orgCodeList, isServer);
			System.err.println("已替换后下载清单SQL[" + bill.getBillName() + "]:" + finalSql);
			resultMap.put("sql", finalSql);
			resultMap.put("isServer", isServer);
			resultMap.put("billName", bill.getBillName());
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			dataPrintTips(4, resultMap);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {获取所有的指标接口}05
	 * @作者 MaxBill
	 * @时间 2017年8月26日 上午10:57:29
	 */
	public ResponseBeanDto getAllTargetList(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String roleOrg = jsonObject.getString("roleOrg");
			Integer id = jsonObject.getInteger("browseId");
			String targetCode = jsonObject.getString("targetCode");
			String parentCode = jsonObject.getString("subject");
			String targetName = jsonObject.getString("targetName");
			Integer curPage = jsonObject.getInteger("curPage");// 当前页数
			Integer pageSize = jsonObject.getInteger("pageSize");// 每页数据量
			Pager<Target> pager = new Pager<Target>(curPage, pageSize);
			DataPageBean dataPageBean;
			if (StringUtils.isEmpty(targetCode) && StringUtils.isEmpty(targetName) && StringUtils.isEmpty(parentCode)) {
				// 编辑时获取
				if (null != id) {
					dataPageBean = browseListService.findListTargetByRole(true, pager, role, roleOrg, null, null, null,
							id);
				} else {
					dataPageBean = browseListService.findListTargetByRole(false, pager, role, roleOrg, null, null, null,
							null);
					List<TargetBean> targetBeanList = (List<TargetBean>) dataPageBean.getList();
					for (TargetBean targetBean : targetBeanList) {
						targetBean.setChecked(false);
					}
				}
			} else {
				if (null != id) {
					dataPageBean = browseListService.findListTargetByRole(true, pager, role, roleOrg, parentCode,
							targetCode, targetName, id);
				} else {
					dataPageBean = browseListService.findListTargetByRole(false, pager, role, roleOrg, parentCode,
							targetCode, targetName, null);
					List<TargetBean> targetBeanList = (List<TargetBean>) dataPageBean.getList();
					for (TargetBean targetBean : targetBeanList) {
						targetBean.setChecked(false);
					}
				}
			}
			dataPageBean.setSubject(browseListService.findTargetTypeByRole(role, roleOrg));
			this.dataSuccessTips(JSON.toJSONString(dataPageBean), responseBeanDto);
			dataPrintTips(5, dataPageBean);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {设置维度接口}06
	 * @作者 MaxBill
	 * @时间 2017年8月26日 上午10:57:35
	 */
	public ResponseBeanDto setTargetDimInfo(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Integer id = jsonObject.getInteger("browseId");
			String targetCode = jsonObject.getString("targetCode");
			// 判断该指标值是否是数值型
			Target target = browseListService.findTargetByCode(targetCode);
			Map termMap = new HashMap();
			if (target.getIsMath().equals("1")) {
				// 封装条件公式数据
				termMap.put("lt", new TermBean(false, new Float[] {}));
				termMap.put("gt", new TermBean(false, new Float[] {}));
				termMap.put("range", new TermBean(false, new Float[] {}));
				if (null != id) {
					// 编辑
					Iterator<Map.Entry<String, TermBean>> entries = termMap.entrySet().iterator();
					BillTarget billTarget = browseListService.findBillTargetByBillAndTarget(id, targetCode);
					if (null != billTarget) {
						while (entries.hasNext()) {
							Entry<String, TermBean> entry = entries.next();
							if (entry.getKey().equals(billTarget.getFormulaType())) {
								entry.getValue().setChecked(true);
								switch (entry.getKey()) {
								case "lt":
									entry.getValue().setValue(new Float[] { billTarget.getMaxVal() });
									break;
								case "gt":
									entry.getValue().setValue(new Float[] { billTarget.getMinVal() });
									break;
								case "range":
									entry.getValue()
											.setValue(new Float[] { billTarget.getMinVal(), billTarget.getMaxVal() });
									break;
								}
							}
						}
					}
				}
			}
			resultMap.put("term", termMap);
			// 封装查询维度信息
			List<QueryDimBean> queryDimBeanList = browseListService.findQueryDimListByTarget(targetCode);
			if (null != id) {
				// 编辑
				List<BillFiltDim> billFiltDimList = browseListService.findBillFiltDimByBillAndTarget(id, targetCode);
				if (null != billFiltDimList && !billFiltDimList.isEmpty()) {
					for (BillFiltDim billFiltDim : billFiltDimList) {
						for (QueryDimBean queryDimBean : queryDimBeanList) {
							if (billFiltDim.getFiltDimCode().equals(queryDimBean.getCode())) {
								queryDimBean.setChecked(true);
								queryDimBean.setActive(true);
							}
						}
					}
				}
			}
			if (null != queryDimBeanList && !queryDimBeanList.isEmpty()) {
				resultMap.put("req", queryDimBeanList);
			} else {
				resultMap.put("req", new String[] {});
			}
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			dataPrintTips(6, resultMap);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {我的清单接口}07
	 * @作者 MaxBill
	 * @时间 2017年7月28日 上午9:43:48
	 */
	public ResponseBeanDto myListBrowseList(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String username = jsonObject.getString("username");// 用户名
			String browseName = jsonObject.getString("browseName");// 清单名称
			Integer curPage = jsonObject.getInteger("curPage");// 当前页数
			Integer pageSize = jsonObject.getInteger("pageSize");// 每页数据量
			// 封装分页参数
			Pager<Bill> pager = new Pager<Bill>(curPage, pageSize);
			DataPageBean dataPageBean = browseListService.queryMyBillList(pager, role, username, browseName);
			this.dataSuccessTips(JSON.toJSONString(dataPageBean), responseBeanDto);
			dataPrintTips(7, dataPageBean);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {删除我的清单}08
	 * @作者 MaxBill
	 * @时间 2017年7月28日 上午9:44:22
	 */
	public ResponseBeanDto deleteBrowseList(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Integer id = jsonObject.getInteger("browseId");
			int delFlag = browseListService.deleteBill(id);
			// 成功响应处理
			if (delFlag == 1) {
				this.dataSuccessTips("{\"msg\":\"删除成功\"}", responseBeanDto);
			} else {
				this.dataSuccessTips("{\"msg\":\"删除失败\"}", responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {编辑我的清单}09
	 * @作者 MaxBill
	 * @时间 2017年7月28日 上午9:44:30
	 */
	public ResponseBeanDto updateBrowseList(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String username = jsonObject.getString("username");
			String browseName = jsonObject.getString("browseName");
			Integer browseId = jsonObject.getInteger("browseId");
			String targets = jsonObject.getString("targets");
			int updFlag = browseListService.updateBill(username, browseId, browseName, targets);
			// 成功响应处理
			if (updFlag == 1) {
				this.dataSuccessTips("{\"msg\":\"更新成功\"}", responseBeanDto);
			} else {
				this.dataSuccessTips("{\"msg\":\"更新失败\"}", responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {保存我的清单}10
	 * @作者 MaxBill
	 * @时间 2017年7月28日 上午9:44:36
	 */
	public ResponseBeanDto saveMyBrowseList(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String username = jsonObject.getString("username");
			String browseName = jsonObject.getString("browseName");
			String targets = jsonObject.getString("targets");
			int addFlag = browseListService.saveBill(username, role, browseName, targets);
			// 成功响应处理
			if (addFlag == 1) {
				this.dataSuccessTips("{\"msg\":\"保存成功\"}", responseBeanDto);
			} else {
				this.dataSuccessTips("{\"msg\":\"保存失败\"}", responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {数据正常返回}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 下午5:12:05
	 */
	public void dataSuccessTips(String jsonDta, ResponseBeanDto responseBeanDto) {
		responseBeanDto.setJson(jsonDta);
		responseBeanDto.setErrCode("200");
		responseBeanDto.setErrMsgs("System info: request success!");
		responseBeanDto.setSuccess("true");
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
	}

	/**
	 * @功能 {数据为空提醒}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 下午5:12:05
	 */
	public void dataIsEmptyTips(JSONObject jsonObject, ResponseBeanDto responseBeanDto) {
		log.info("指标SQL数据为空,请求参数为：" + jsonObject.toJSONString());
		responseBeanDto.setErrCode("E00001");
		responseBeanDto.setErrMsgs("System info: The target sql data is empty!");
		responseBeanDto.setSuccess("false");
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
	}

	/**
	 * @功能 {数据异常提醒}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 下午5:16:05
	 */
	public void dataExceptionTips(Exception e, ResponseBeanDto responseBeanDto) {
		// 异常响应处理
		e.printStackTrace();
		responseBeanDto.setJson("");
		responseBeanDto.setErrCode("500");
		responseBeanDto.setErrMsgs(e.toString());
		responseBeanDto.setSuccess("false");
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
	}

	/**
	 * @功能 {数据日志提醒}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 下午5:16:05
	 */
	public void dataPrintTips(int type, Object data) {
		log.info("------------------------LLQD(" + type + ")-S------------------------");
		log.info(DateUtil.formatDateTime(new Date()) + ": " + JSON.toJSONString(data));
		log.info("------------------------LLQD(" + type + ")-E------------------------");
	}

	/**
	 * @功能 {正则判断是否数字}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 下午5:16:05
	 */
	public boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
