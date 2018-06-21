package com.ehuatai.biz.browse.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ehuatai.biz.browse.bean.OrgBean;
import com.ehuatai.biz.browse.service.BrowseSQLCacheService;
import com.ehuatai.biz.browse.service.BrowseService;
import com.ehuatai.biz.mapper.BaseMapper;
import com.ehuatai.biz.mapper.SQLMapper;
import com.ehuatai.biz.service.SQLService;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.LlqdService;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.LlqdService.Client;
import com.ehuatai.thrift.util.ThriftUtil;
import com.ehuatai.util.ExcelUtils;
import com.fairyland.jdp.framework.util.PropsUtil;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;

@Service
public class BrowseServiceImpl implements BrowseService {

	@Autowired
	private BrowseSQLCacheService sqlCacheService;

	@Autowired
	private SQLService sqlServiceImpl;

	@Autowired
	private BaseMapper baseMapper;

	@Autowired
	private SQLMapper sqlMapper;

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private Client getClient() {
		Client client = null;
		try {
			client = ThriftUtil.getService(LlqdService.Client.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public RestResult browseNavs(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.browseListMenu(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult browseListDept(Map<String, Object> reqParams) {
		Object code = reqParams.get("browseCode");
		if (StringUtils.isEmpty(code)) {
			log.error("清单的code为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		if (null != reqParams.get("startTime")) {
			reqParams.put("start_time", reqParams.get("startTime").toString());
		}
		String endTime = reqParams.get("endTime").toString();
		reqParams.remove("startTime");
		reqParams.remove("endTime");
		reqParams.put("end_time", endTime);
		Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
		pager.setCurrentPage(Integer.parseInt(reqParams.get("curPage").toString()));
		pager.setPageSize(Integer.parseInt(reqParams.get("pageSize").toString()));
		pager = sqlServiceImpl.findGdqdData(code.toString(), pager, (HashMap<String, Object>) reqParams);
		if (pager == null) {
			log.error("pager 对象为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		// 结果集
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("curPage", pager.getCurrentPage());
		restMap.put("pageSize", pager.getPageSize());
		restMap.put("total", pager.getTotalCount());
		restMap.put("totalPage", pager.getTotalPage());
		if(code.equals("R1301010005")||code.equals("R1301010006")) {
			restMap.put("top", reqParams.get("jgtopnumber").toString());
		}	
		// TODO 特殊情况待处理

		List<Map<String, Object>> lists = pager.getPageItems();

		List<Object> resultList = new ArrayList<Object>();
		if (lists != null && !lists.isEmpty()) {
			for (Map<String, Object> item : lists) {
				List<Object> items = new ArrayList<Object>();
				Set<String> keys = item.keySet();
				for (int i = 0, len = keys.size(); i < len; i++) {
					items.add(item.get("VAL" + i));
				}
				resultList.add(items);
			}
		}
		restMap.put("list", resultList);
		if(code.equals("R1301010001")||code.equals("R1301010002")) {
			List<HashMap<String,Object>> orgList= baseMapper.getOrgAll();
			if(code.equals("R1301010001")){
				List<String> gradeList= baseMapper.getGradeAll();
				restMap.put("grade", gradeList);
			}else{
				List<String> gradeList= baseMapper.getGradeAll();
				restMap.put("grade", gradeList);
			}
			restMap.put("org", orgList);
		}
		return RestResult.getSuccess(restMap);
	}

	public RestResult browseListDeptDownload(Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		String realname = "";
		String startDate = "";
		Object code = reqParams.get("browseCode");
		String endDate = reqParams.get("endTime").toString();
		if (null != reqParams.get("startTime")) {
			startDate = reqParams.get("startTime").toString();
			reqParams.put("start_time", startDate);
		}
		reqParams.put("end_time", endDate);
		if (StringUtils.isEmpty(code)) {
			log.error("清单的code为空");
		}
		Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
		pager.setCurrentPage(Integer.parseInt(reqParams.get("curPage").toString()));
		pager.setPageSize(Integer.parseInt(reqParams.get("pageSize").toString()));
		pager = sqlServiceImpl.findGdqdData(code.toString(), pager, (HashMap<String, Object>) reqParams);
		List<Map<String, Object>> lists = pager.getPageItems();

		List<Map<String, Object>> targetMaps = new ArrayList<>();
		List<Map<String, Object>> sortMaps = new ArrayList<>();
		if (lists != null && !lists.isEmpty()) {
			for (Map<String, Object> item : lists) {
				Map<String, Object> items = new HashMap<>();
				Set<String> keys = lists.get(0).keySet();
				for (int i = 0, len = keys.size(); i < len; i++) {
					items.put("VAL" + i, item.get("VAL" + i) == null ? "" : item.get("VAL" + i));
				}
				targetMaps.add(items);
			}
			for (Map<String, Object> targetMap : targetMaps) {
				List<Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(targetMap.entrySet());
				Collections.sort(list, new Comparator<Entry<String, Object>>() {
					@Override
					public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
						String o1key = o1.getKey();
						String o2key = o2.getKey();
						Integer o1kv = Integer.parseInt(o1key.substring(3, o1key.length()));
						Integer o2kv = Integer.parseInt(o2key.substring(3, o2key.length()));
						int flag = o1kv.compareTo(o2kv);
						if (flag == 0) {
							return o1kv.compareTo(o2kv);
						}
						return flag;
					}
				});
				Map<String, Object> tarmap = new LinkedHashMap<>();
				for (Entry<String, Object> en : list) {
					tarmap.put(en.getKey(), en.getValue());
				}
				sortMaps.add(tarmap);
			}
			if ("R1301070005".equals(code.toString())) {
				List<Map<String, Object>> sortMap = new ArrayList<>();
				for (int x = 0; x < sortMaps.size(); x++) {
					Integer startNum = Integer.parseInt(startDate.substring(5, 7));
					Integer endNum = Integer.parseInt(endDate.substring(5, 7));
					for (int i = 0; i <= 26; i++) {
						if ((i < (startNum * 2 - 1) || i > endNum * 2) && i != 0 && i != 25 && i != 26) {
							sortMaps.get(x).remove("VAL" + i);
						}
					}
					sortMap.add(x, sortMaps.get(x));
				}
				sortMaps = sortMap;
			}
		}
		List<Object> gdzbInfo = getGdzbHeadList(code.toString(), (String) reqParams.get("startTime"),
				(String) reqParams.get("endTime"),
				StringUtil.isNotNull(reqParams.get("orgType")) ? reqParams.get("orgType").toString() : null);
		Object[] titleArray = (Object[]) gdzbInfo.get(1);
		if (null != sortMaps && !sortMaps.isEmpty()) {
			Map<String, Object> titleMap = sortMaps.get(0);
			if (code.toString().contains("R130105") && !reqParams.get("orgType").toString().equals("1")) {
				titleMap.remove("VAL1");
			}
			Object[] fieldArray = titleMap.keySet().toArray();
			int type = (int) gdzbInfo.get(2);
			if (type == 1) {
				realname = ExcelUtils.getFileNameUrl(gdzbInfo.get(0).toString(), response, titleArray, fieldArray,
						sortMaps.subList(0, sortMaps.size()));

			} else {
				realname = ExcelUtils.getexportExcelUrl((int) gdzbInfo.get(4), (int[][]) gdzbInfo.get(3),
						gdzbInfo.get(0).toString(), response, titleArray, fieldArray,
						sortMaps.subList(0, sortMaps.size()));
			}
		} else {
			realname = ExcelUtils.getexportExcelUrl((int) gdzbInfo.get(4), (int[][]) gdzbInfo.get(3),
					gdzbInfo.get(0).toString(), response, titleArray, new String[] {},
					new ArrayList<>());
		}
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("fileurl", PropsUtil.get("base.url") + "/ceph/comdownloadfile/" + realname);
		resMap.put("filename", realname);
		return RestResult.getSuccess(resMap);
	}

	/**
	 * @状态 OK
	 * @功能 {自定义浏览清单信息接口}
	 * @作者 MaxBill
	 * @时间 2017年11月2日 上午10:44:10
	 */
	public RestResult browseListCustomInfo(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.browseListInfo(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (dto.getSuccess().equals("false")) {
			log.error(dto.getErrMsgs());
			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("接口返回数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		JSONObject resultJSONObject = JSONObject.parseObject(resultJSONString);
		return RestResult.getSuccess(resultJSONObject);
	}

	@Override
	public RestResult browseListCustom(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.browseListData(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (dto.getSuccess().equals("false")) {
			log.error(dto.getErrMsgs());
			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("接口返回数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		JSONObject resultJSONObject = JSONObject.parseObject(resultJSONString);
		String sql = resultJSONObject.getString("sql");
		if (StringUtils.isEmpty(sql)) {
			log.warn("SQL 为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		// 分页获取数据
		Pager<Map<String, Object>> pager = new Pager<>();
		pager.setCurrentPage(Integer.parseInt((reqParams.get("curPage").toString())));
		pager.setPageSize(Integer.parseInt((reqParams.get("pageSize").toString())));
		Long reqStartTime = System.currentTimeMillis();
		pager = sqlCacheService.browseListCustom(reqParams, pager, sql);
		Long reqEndTime = System.currentTimeMillis();
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>:该清单数据查询时间：" + (reqEndTime - reqStartTime) / 1000 + "秒");
		// 移除SQL
		resultJSONObject.remove("sql");
		resultJSONObject.put("curPage", pager.getCurrentPage());
		resultJSONObject.put("pageSize", pager.getPageSize());
		resultJSONObject.put("total", pager.getTotalCount());
		resultJSONObject.put("totalPage", pager.getTotalPage());
		List<Object> list = new ArrayList<Object>();
		List<Map<String, Object>> items = pager.getPageItems();
		if (items != null && !items.isEmpty()) {
			int cols = items.get(0).keySet().size() - 1;
			for (Map<String, Object> item : items) {
				List<Object> row = new ArrayList<Object>();
				for (int i = 0; i < cols; i++) {
					row.add(item.get("VAL" + i));
				}
				list.add(row);
			}
		}
		resultJSONObject.put("list", list);
		return RestResult.getSuccess(resultJSONObject);
	}

	@Override
	public RestResult browseListCustomDownload(Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		String realname = "";
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.browseListDown(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
		}
		JSONObject resultJSONObject = JSONObject.parseObject(resultJSONString);
		String sql = resultJSONObject.getString("sql");
		String billName = resultJSONObject.getString("billName");
		List<Map<String, Object>> targetMaps = sqlCacheService.browseListCustomDownload(reqParams, sql);
		if (null != targetMaps) {
			Map<String, Object> titleMap = targetMaps.get(0);
			int dataSize = titleMap.size();
			Object[] titleArray = new Object[dataSize];
			Object[] fieldArray = new Object[dataSize];
			for (int i = 0; i < dataSize; i++) {
				titleArray[i] = titleMap.get("VAL" + i);
			}
			for (int i = 0; i < dataSize; i++) {
				fieldArray[i] = "VAL" + i;
			}
			realname = ExcelUtils.getFileNameUrl(billName, response, titleArray, fieldArray,
					targetMaps.subList(1, targetMaps.size()));
		}
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("fileurl", PropsUtil.get("base.url") + "/ceph/comdownloadfile/" + realname);
		resMap.put("filename", realname);
		return RestResult.getSuccess(resMap);
	}

	@Override
	@Deprecated
	public RestResult browseListData(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String rdStr = (String) reqParams.get("roleDept");
			String[] roleDept = rdStr.split(",");
			reqParams.remove("roleDept");
			reqParams.put("roleDept", roleDept);
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.browseListData(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	@Deprecated
	public RestResult browseDownload(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String rdStr = (String) reqParams.get("roleDept");
			String[] roleDept = rdStr.split(",");
			reqParams.remove("roleDept");
			reqParams.put("roleDept", roleDept);
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.browseListDown(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult browseMy(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.myListBrowseList(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult browseMyTargets(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getAllTargetList(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult browseEditDimen(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.setTargetDimInfo(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult browseSave(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			Object browseId = reqParams.get("browseId");
			if (StringUtils.isEmpty(browseId)) {
				dto = client.saveMyBrowseList(reqParamsJSON);
			} else {
				dto = client.updateBrowseList(reqParamsJSON);
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult browseEdit(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.updateBrowseList(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult browseDelete(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.deleteBrowseList(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	/**
	 * @功能 {查询机构}
	 * @作者 MaxBill
	 * @时间 2017年9月11日 下午1:08:11
	 */
	public List<HashMap<String, Object>> getOrgList(int type, String code, Boolean isServer) {
		List<HashMap<String, Object>> mapData = null;
		switch (type) {
		case 1:
			mapData = this.baseMapper.getCompanyByManage(code);
			break;
		case 2:
			mapData = this.sqlMapper.getBranchByCompany(code);
			break;
		case 3:
			if (isServer) {
				mapData = this.baseMapper.getServerByBranch(code);
			} else {
				mapData = this.baseMapper.getCenterByBranch(code);
			}
			break;
		case 4:
			if (!StringUtils.isEmpty(code) && code.length() == 10) {
				mapData = this.baseMapper.getRegionByCenetr(code);
			} else {
				if (isServer) {
					mapData = this.baseMapper.getRegionByServer(code);
				} else {
					mapData = this.baseMapper.getRegionByCenetr(code);
				}
			}
			break;
		case 5:
			mapData = this.baseMapper.getSectionByRegion(code);
			break;
		case 6:
			mapData = this.baseMapper.getGroupsBySection(code);
			break;
		case 7:
			mapData = this.baseMapper.getSellersByGroups(code);
			break;
		}
		return mapData;
	}

	/**
	 * @功能 {查询机构}
	 * @作者 MaxBill
	 * @时间 2017年9月11日 下午1:08:11
	 */
	public OrgBean getOrg(int type, String code) {
		OrgBean org = new OrgBean();
		HashMap<String, Object> mapData = null;
		switch (type) {
		case 1:
			org.setCode("1");
			org.setName("总公司");
			break;
		case 2:
			mapData = this.baseMapper.getCompanyByCode(code);
			org.setCode((String) mapData.get("CODE"));
			org.setName((String) mapData.get("NAME"));
			break;
		case 3:
			mapData = this.baseMapper.getBranchsByCode(code);
			if (null != mapData) {
				org.setCode((String) mapData.get("CODE"));
				org.setName((String) mapData.get("NAME"));
				org.setProv((String) mapData.get("PROV"));
			} else {
				org = null;
			}
			break;
		case 41:
			mapData = this.baseMapper.getServiceByServerCode(code);
			if (null != mapData) {
				org.setCode((String) mapData.get("CODE"));
				org.setName((String) mapData.get("NAME"));
				org.setProv((String) mapData.get("PROV"));
				org.setCoun((String) mapData.get("COUN"));
			} else {
				org = null;
			}
			break;
		case 42:
			mapData = this.baseMapper.getServiceByCenterCode(code);
			if (null != mapData) {
				org.setCode((String) mapData.get("CODE"));
				org.setName((String) mapData.get("NAME"));
				org.setProv((String) mapData.get("PROV"));
				org.setCoun((String) mapData.get("COUN"));
			} else {
				org = null;
			}
			break;
		}
		return org;
	}

	@Override
	public RestResult getOrgData(Map<String, Object> reqParams) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Object org = reqParams.get("org");
		Object type = reqParams.get("type");
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.browseIsServer(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		Boolean isServer = JSON.parseObject(resultJSONString).getBoolean("isServer");
		String roleOrg = reqParams.get("roleOrg").toString();
		List<HashMap<String, Object>> orgList = null;
		List<OrgBean> nextOrgBeanList = new ArrayList<OrgBean>();
		List<OrgBean> currOrgBeanList = new ArrayList<OrgBean>();
		if (null == org || org.equals("")) {
			switch (roleOrg.length()) {
			case 1:
				// 总公司
				orgList = this.getOrgList(1, roleOrg, isServer);
				break;
			case 3:
				// 分公司
				OrgBean currOrg = this.getOrg(2, roleOrg);// 查询自己
				orgList = this.getOrgList(2, roleOrg, isServer);
				currOrgBeanList.add(currOrg);
				break;
			case 5:
				// 中支
				OrgBean currOrg51 = this.getOrg(3, roleOrg);// 查询自己（中支）
				OrgBean currOrg52 = this.getOrg(2, currOrg51.getProv());// 查询分公司
				orgList = this.getOrgList(3, roleOrg, isServer);
				currOrgBeanList.add(currOrg52);
				currOrgBeanList.add(currOrg51);
				break;
			default:
				// 营销服务部
				OrgBean currOrgDef1 = this.getOrg(41, roleOrg);// 查询自己（营销服务部）
				if (null == currOrgDef1) {
					currOrgDef1 = this.getOrg(42, roleOrg);// 查询自己（成本中心）
				}
				OrgBean currOrgDef2 = this.getOrg(3, currOrgDef1.getCoun());// 查询中支
				OrgBean currOrgDef3 = this.getOrg(2, currOrgDef1.getProv());// 查询分公司
				orgList = this.getOrgList(4, roleOrg, isServer);
				currOrgBeanList.add(currOrgDef3);
				currOrgBeanList.add(currOrgDef2);
				currOrgBeanList.add(currOrgDef1);
				break;
			}
		} else {
			orgList = this.getOrgList((Integer) type, org.toString(), isServer);
		}
		for (HashMap<String, Object> map : orgList) {
			String orgCode = (String) map.get("CODE").toString();
			String orgName = (String) map.get("NAME");
			OrgBean nextOrg = new OrgBean();
			nextOrg.setName(orgName);
			nextOrg.setCode(orgCode);
			nextOrgBeanList.add(nextOrg);
		}
		resultMap.put("currOrg", currOrgBeanList);
		resultMap.put("nextOrg", nextOrgBeanList);
		return RestResult.getSuccess(resultMap);
	}

	// 获得固定指标的表头
	public static List<Object> getGdzbHeadList(String code, String startTime, String endTime, String orgType) {
		Calendar now = Calendar.getInstance();
		String patten = endTime.length() > 8 == true ? "yyyy-MM-dd" : "yyyy-MM";
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		Date date = new Date();
		try {
			date = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		now.setTime(date);
		String year = now.get(Calendar.YEAR) + "";
		String month = (now.get(Calendar.MONTH) + 1) + "";
		String lastyear = (now.get(Calendar.YEAR) - 1) + "";
		String day = now.get(Calendar.DAY_OF_MONTH) + "";
		List<Object> list = new ArrayList<>();
		Object[] objList = null;
		int[][] mesh = null;
		int hbrows = 0; // 头文件行数
		int type = 1;
		String billName = "固定清单";
		if (code.contains("R130105")) {
			if ("1".equals(orgType)) {
				objList = new Object[] { "代理人名称", "代理人工号", "退保保费", "失效保费", "金额", "影响总体继续率点数", "影响K2点数", "金额",
						"影响总体继续率点数", "影响K2点数" };
				mesh = new int[][] { { 0, 1, 0, 0 }, { 0, 1, 1, 1 }, { 0, 0, 2, 3 }, { 0, 0, 5, 7 }, { 1, 2 }, { 1, 3 },
						{ 1, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 } };
			} else {
				objList = new Object[] { "机构", "退保保费", "失效保费", "金额", "影响总体继续率点数", "影响K2点数", "金额", "影响总体继续率点数",
						"影响K2点数" };
				mesh = new int[][] { { 0, 1, 0, 0 }, { 0, 0, 1, 3 }, { 0, 0, 4, 6 }, { 1, 1 }, { 1, 2 }, { 1, 3 },
						{ 1, 4 }, { 1, 5 }, { 1, 6 } };
			}
			type = 2;
			hbrows = 2;
		}
		switch (code) {
		case "R1301010004":
			billName = "当年千万登峰专项报表清单";
			objList = new Object[] { "序号", "分公司", "三级机构", "四级机构", "机构级别", "首次承保时间", lastyear + "年实际", year + "年晋级目标",
					year + "年实际", "个险评级", "序时标保(万元)", "月均标保(万元)", "月均折算有效人力(人)", "K2(%)", "个险评级", "月均标保(万元)",
					"月均折算有效人力(人)", "K2(%)", "标保", "月均折算有效人力(人)", "K2(%)", "个险评级", "是否晋级", "合计(万元)", "月均(万元)",
					"增长率(%)" };
			mesh = new int[][] { { 0, 2, 0, 0 }, { 0, 2, 1, 1 }, { 0, 2, 2, 2 }, { 0, 2, 3, 3 }, { 0, 2, 4, 4 },
					{ 0, 2, 5, 5 }, { 0, 0, 6, 10 }, { 0, 0, 11, 14 }, { 0, 0, 15, 21 }, { 1, 2, 6, 6 }, { 1, 2, 7, 7 },
					{ 1, 2, 8, 8 }, { 1, 2, 9, 9 }, { 1, 2, 10, 10 }, { 1, 2, 11, 11 }, { 1, 2, 12, 12 },
					{ 1, 2, 13, 13 }, { 1, 2, 14, 14 }, { 1, 1, 15, 17 }, { 1, 2, 18, 18 }, { 1, 2, 19, 19 },
					{ 1, 2, 20, 20 }, { 1, 2, 21, 21 }, { 2, 15 }, { 2, 16 }, { 2, 17 } };
			type = 2;
			hbrows = 3;
			break;
		case "R1301010003":
			billName = "当年跨越五千专项报表清单";
			objList = new Object[] { "序号", "分公司", "三级机构", "首次承保时间", lastyear + "年实际", year + "年晋级目标", year + "年实际",
					"个险评级", "机构达标率(%)", "序时标保(万元)", "月均标保(万元)", "月均折算有效人力(人)", "K2(%)", "个险评级", "月均标保(万元)",
					"月均折算有效人力(人)", "机构达标率(%)", "K2(%)", "机构达标率", "标保", "月均折算有效人力(人)", "K2(%)", "个险评级", "是否晋级", "辖下机构",
					"达标机构", "机构达标率(%)", "合计(万元)", "月均(万元)", "增长率(%)" };
			mesh = new int[][] { { 0, 2, 0, 0 }, { 0, 2, 1, 1 }, { 0, 2, 2, 2 }, { 0, 2, 3, 3 }, { 0, 0, 4, 9 },
					{ 0, 0, 10, 14 }, { 0, 0, 15, 24 }, { 1, 2, 4, 4 }, { 1, 2, 5, 5 }, { 1, 2, 6, 6 }, { 1, 2, 7, 7 },
					{ 1, 2, 8, 8 }, { 1, 2, 9, 9 }, { 1, 2, 10, 10 }, { 1, 2, 11, 11 }, { 1, 2, 12, 12 },
					{ 1, 2, 13, 13 }, { 1, 2, 14, 14 }, { 1, 1, 15, 17 }, { 1, 1, 18, 20 }, { 1, 2, 21, 21 },
					{ 1, 2, 22, 22 }, { 1, 2, 23, 23 }, { 1, 2, 24, 24 }, { 2, 15 }, { 2, 16 }, { 2, 17 }, { 2, 18 },
					{ 2, 19 }, { 2, 20 } };
			type = 2;
			hbrows = 3;
			break;
		case "R1301010002":
			billName = "当年四级机构评级报表清单";
			objList = new Object[] { "序号", "分公司", "三级机构", "四级机构", "机构级别", "首次承保时间", "上次评级结果", "序时个险评级", year + "年实际",
					"标保", "折算有效人力", "K2(%)", "EA渠道承保保费（万元）", "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月",
					"月均(万元)", "合计(万元)", "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月",
					"月均(人)", "合计(人)" };
			mesh = new int[][] { { 0, 2, 0, 0 }, { 0, 2, 1, 1 }, { 0, 2, 2, 2 }, { 0, 2, 3, 3 }, { 0, 2, 4, 4 },
					{ 0, 2, 5, 5 }, { 0, 2, 6, 6 }, { 0, 2, 7, 7 }, { 0, 0, 6 + 2, 36 }, { 1, 1, 9 - 1, 22 },
					{ 1, 1, 23, 35 }, { 1, 2, 36, 36 },{1, 2, 37, 37}, { 2, 9 - 1 }, { 2, 10 - 1 }, { 2, 11 - 1 }, { 2, 12 - 1 },
					{ 2, 13 - 1 }, { 2, 14 - 1 }, { 2, 15 - 1 }, { 2, 16 - 1 }, { 2, 17 - 1 }, { 2, 18 - 1 },
					{ 2, 19 - 1 }, { 2, 20 - 1 }, { 2, 21 - 1 }, { 2, 22 - 1 }, { 2, 23 - 1 }, { 2, 24 - 1 },
					{ 2, 25 - 1 }, { 2, 26 - 1 }, { 2, 27 - 1 }, { 2, 28 - 1 }, { 2, 29 - 1 }, { 2, 30 - 1 },
					{ 2, 31 - 1 }, { 2, 32 - 1 }, { 2, 33 - 1 }, { 2, 34 - 1 }, { 2, 35 - 1 }, { 2, 36 - 1 },
					{ 2, 37 - 1 } };
			type = 2;
			hbrows = 3;
			break;
		case "R1301010001":
			billName = "当年三级机构个险评级报表清单";
			objList = new Object[] { "序号", "分公司", "三级机构", "首次承保时间", "上次评级结果", "序时个险评级", year + "年实际", "机构达标率", "标保",
					"折算有效人力", "K2(%)","EA渠道承保保费（万元）", "辖下机构", "达标机构", "机构达标率(%)", "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月",
					"10月", "11月", "12月", "月均(万元)", "合计(万元)", "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月",
					"10月", "11月", "12月", "月均(人)", "合计(人)" };
			mesh = new int[][] { { 0, 2, 0, 0 }, { 0, 2, 1, 1 }, { 0, 2, 2, 2 }, { 0, 2, 3, 3 }, { 0, 2, 4, 4 },
					{ 0, 2, 5, 5 }, { 0, 0, 6, 38 }, { 1, 1, 6, 8 }, { 1, 1, 9, 22 }, { 1, 1, 23, 36 },
					{ 1, 2, 37, 37 }, { 1, 2, 38, 38}, { 2, 6 }, { 2, 7 }, { 2, 8 }, { 2, 9 }, { 2, 10 }, { 2, 11 }, { 2, 12 },
					{ 2, 13 }, { 2, 14 }, { 2, 15 }, { 2, 16 }, { 2, 17 }, { 2, 18 }, { 2, 19 }, { 2, 20 }, { 2, 21 },
					{ 2, 22 }, { 2, 23 }, { 2, 24 }, { 2, 25 }, { 2, 26 }, { 2, 27 }, { 2, 28 }, { 2, 29 }, { 2, 30 },
					{ 2, 31 }, { 2, 32 }, { 2, 33 }, { 2, 34 }, { 2, 35 }, { 2, 36 } };
			type = 2;
			hbrows = 3;
			break;
		case "R1301070016":
			billName = "月度达成最终结果";
			objList = new Object[] { "序号", "机构", month + "月预算(万元)", month + "月承保(万元)", "达成率(%)", "同期达成(万元)",
					"同期增长率(%)" };
			break;
		case "R1301070015":
			billName = "年度达成";
			objList = new Object[] { "序号", "机构", "年度预算(万元)", "年累计承保(万元)", "达成率(%)" };

			break;
		case "R1301070014":
			billName = "有效人力";
			objList = new Object[] { "序号", "机构", year + "年" + month + "月", lastyear + "年" + month + "月", "增长率(%)" };

			break;
		case "R1301070013":
			billName = "出单人力";
			objList = new Object[] { "序号", "机构", year + "年" + month + "月", lastyear + "年" + month + "月", "增长率(%)" };

			break;
		case "R1301070012":
			billName = "期末在职人力";
			objList = new Object[] { "序号", "机构", year + "年" + month + "月", lastyear + "年" + month + "月", "增长率(%)" };
			break;
		case "R1301070011":
			billName = "主管达蜂";
			objList = new Object[] { "主管达蜂(是达成准金)", "数据截止:" + year + "年" + month + "月" + day + "日", "序号", "机构",
					"主管达蜂目标(人)", "期末主管数(人)", "日受理主管达蜂人数(人)", "当日主管达蜂占比(%)", "月受理主管达蜂数(人)", "受理缺口(人)", "受理主管达蜂率(%)" };
			mesh = new int[][] { { 0, 0, 0, 8 }, { 1, 1, 0, 8 }, { 2, 0 }, { 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 },
					{ 2, 5 }, { 2, 6 }, { 2, 7 }, { 2, 8 } };
			type = 2;
			hbrows = 3;
			break;
		case "R1301070010":
			billName = "有效人力";
			objList = new Object[] { "机构", "有效人力目标(人)", "日受理有效人力(人)", "日受理有效占比(%)", "月受理有效人力(人)", "受理缺口(人)",
					"受理有效达成率(%)", "数据截止:" + year + "年" + month + "月" + day + "日" };
			break;
		case "R1301070009":
			billName = "当月份业绩报表";
			objList = new Object[] { month + "月预算及年度达成追踪", "数据截止:" + year + "年" + month + "月" + day + "日（单位：万元）", "机构",
					month + "月预算(万元)", "当日业绩", "月累计业绩", "年累计业绩", "受理标保(万元)", "承保标保(万元)", "受理标保(万元)", "受理达成率(%)",
					"承保标保(万元)", "承保达成率(%)", "去年序时承保(万元)", "同期增长率(%)", "年度承保标保(万元)", "年度达成率(%)", "年度同比(%)" };
			mesh = new int[][] { { 0, 0, 0, 13 }, { 1, 1, 0, 13 }, { 2, 3, 0, 0 }, { 2, 3, 1, 1 }, { 2, 2, 2, 3 },
					{ 2, 2, 4, 9 }, { 2, 2, 10, 12 }, { 3, 2 }, { 3, 3 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 },
					{ 3, 8 }, { 3, 9 }, { 3, 10 }, { 3, 11 }, { 3, 12 } };
			type = 2;
			hbrows = 4;
			break;
		case "R1301070005":
			billName = "新增人力保费贡献报表";
			Integer fmon = Integer.parseInt(startTime.substring(5, 7));
			Integer mon = Integer.parseInt(month);
			List<String> olist = new ArrayList<>();
			List<int[]> melist = new ArrayList<>();
			olist.add("机构");
			int[] me = new int[] { 0, 1, 0, 0 };
			melist.add(me);
			// 第一行
			int x = 1;
			for (int i = fmon; i <= mon; i++) {
				olist.add(i + "月");
				melist.add(new int[] { 0, 0, 2 * x - 1, 2 * x });
				x++;
			}
			if (mon != 1) {
				olist.add(fmon + "~" + mon + "月");
				melist.add(new int[] { 0, 0, 2 * x - 1, 2 * x });
			}
			// 第二行
			int y = 1;
			for (int i = fmon; i <= mon; i++) {
				olist.add("新增人力(人)");
				olist.add("当月贡献标保占比(%)");
				melist.add(new int[] { 1, 2 * y - 1 });
				melist.add(new int[] { 1, 2 * y });
				y++;
			}
			if (mon != 1) {
				olist.add("新增人力(人)");
				olist.add(fmon + "~" + mon + "月累计贡献标保占比(%)");
				melist.add(new int[] { 1, 2 * y - 1 });
				melist.add(new int[] { 1, 2 * y });
			}
			objList = olist.toArray();
			mesh = melist.toArray(new int[melist.size()][]);
			type = 2;
			hbrows = 2;
			break;
		case "R1301070002":
			billName = "主管增员实动率及达蜂率报表";
			objList = new Object[] { "机构", "TM及TUM增员实动率(%)", "TM及TUM达蜂率(%)", "UM增员实动率(%)", "UM达蜂率(%)", "AM及SAM增员实动率(%)",
					"AM及SAM达蜂率(%)", "主管增员实动率(%)", "主管达蜂率(%)" };
			break;
		case "R1301070001":
			billName = "人力关键指标达成情况";
			objList = new Object[] { "机构", "年初人力(人)",
					"新增人力" + Integer.parseInt(startTime.substring(5, 7)) + "-"
							+ Integer.parseInt(endTime.substring(5, 7)) + "月(人)",
					"脱落人力" + Integer.parseInt(startTime.substring(5, 7)) + "-"
							+ Integer.parseInt(endTime.substring(5, 7)) + "月(人)",
					"净增人力(人)", "期末在职人力" + year + "年" + month + "月(人)", "期末在职人力" + lastyear + "年" + month + "月(人)",
					"同比(%)", "年新增目标(人)", "新增达成率(%)", "年净增目标(人)", "净增达成率(%)" };
			break;
		case "R1301050001":
			billName = "收展部12个月固定清单";
			break;
		case "R1301050002":
			billName = "收展部累计固定清单";
			break;
		case "R1301050003":
			billName = "收展部月度固定清单";
			break;
		case "R1301010005":
			billName = "三级机构标保TOP";
			objList = new Object[] { "数据截止:" + year + "年" + month + "月" + day + "日（单位：万元）", "分公司", "中支", "年度", "标保",
					"排名" };
			mesh = new int[][] { { 0, 0, 0, 3 }, { 1, 2, 0, 0 }, { 1, 2, 1, 1 }, { 1, 1, 2, 3 }, { 2, 2 }, { 2, 3 } };
			type = 2;
			hbrows = 3;
			break;
		case "R1301010006":
			billName = "四级机构标保TOP";
			objList = new Object[] { "数据截止:" + year + "年" + month + "月" + day + "日（单位：万元）", "分公司", "中支", "四级机构", "年度",
					"标保", "排名" };
			mesh = new int[][] { { 0, 0, 0, 3 }, { 1, 2, 0, 0 }, { 1, 2, 1, 1 }, { 1, 2, 2, 2 }, { 1, 1, 3, 4 },
					{ 2, 3 }, { 2, 4 } };
			type = 2;
			hbrows = 3;
			break;
		default:
			objList = new Object[] { "此为新增固定清单，请联系开发人员" };
			break;
		}
		list.add(billName);
		list.add(objList);
		list.add(type);
		list.add(mesh);
		list.add(hbrows);
		return list;
	}
}
