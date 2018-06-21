package com.huatai.web.thrift.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.bean.FixedBillBean;
import com.huatai.web.model.BillSql;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.GroDim;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.BrowseListService;
import com.huatai.web.utils.DateUtil;

public class AppLlqdServiceImpl implements AppLlqdService.Iface {

	/**
	 * @功能 {查询多个指标的最小共同分组维度}
	 * @作者 MaxBill
	 * @时间 2017年8月2日 下午5:58:59
	 */
	public ResponseBeanDto getCommonGroup(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String targets = jsonObject.getString("targets");
			Map resultMap = new HashMap();
			TreeSet treeSet = new TreeSet();
			if (null != targets) {
				List<String> targetList = JSON.parseArray(targets, String.class);
				if (null != targetList && targetList.size() > 0) {
					for (String target : targetList) {
						TarGroDim tarGroDim = browseListService.findTarGroDimByTar(target);
						if (null != tarGroDim) {
							treeSet.add(tarGroDim.getGddId());
						}
					}
				}
			}
			List<GroDimDetail> groDimDetail = browseListService.findGroDimDetailList("ORG_GROUP",
					(Integer) treeSet.first());
			// 查询多个指标的最小共同机构维度
			resultMap.put("group", groDimDetail);
			responseBeanDto.setJson(JSON.toJSONString(resultMap));
			responseBeanDto.setErrCode("200");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setSuccess("true");
		} catch (Exception e) {
			// 异常响应处理
			e.printStackTrace();
			responseBeanDto.setJson("");
			responseBeanDto.setErrCode("500");
			responseBeanDto.setErrMsgs(e.toString());
			responseBeanDto.setSuccess("false");
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

	/**
	 * @功能 {浏览清单菜单接口}
	 * @作者 MaxBill
	 * @时间 2017年8月2日 下午5:59:05
	 */
	public ResponseBeanDto getMenuList(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String username = jsonObject.getString("username");
			// 用户权限内的部门信息
			List<String> orgList = JSON.parseArray(jsonObject.getString("groupByOrg"), String.class);
			Map resultMap = new HashMap();
			List<Object> fixedList = new ArrayList<Object>();
			// 查询固定实时清单
			FixedBillBean fixedBill = new FixedBillBean();
			fixedBill.setTitle("实时指标");
			fixedBill.setCode("REAL_TIME_TITLE");
			fixedBill.setList(null);
			fixedList.add(fixedBill);
			// 查询读固定部分清单
			if (null != orgList) {
				for (String org : orgList) {
					FixedBillBean fixedBillBean = browseListService.findFixedBillList(org);
					fixedList.add(fixedBillBean);
				}
			}
			resultMap.put("fixed", fixedList);
			responseBeanDto.setJson(JSON.toJSONString(resultMap));
			responseBeanDto.setErrCode("200");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setSuccess("true");
		} catch (Exception e) {
			// 异常响应处理
			e.printStackTrace();
			responseBeanDto.setJson("");
			responseBeanDto.setErrCode("500");
			responseBeanDto.setErrMsgs(e.toString());
			responseBeanDto.setSuccess("false");
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

	/**
	 * @功能 {浏览清单列表接口}
	 * @作者 MaxBill
	 * @时间 2017年8月2日 下午5:59:11
	 */
	public ResponseBeanDto getDataList(String json) {
		// :TODo
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BrowseListService browseListService = SpringUtil.getBean(BrowseListService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			// 固定参数
			Boolean isCustom = jsonObject.getBoolean("isCustom");// 是否是自定义清单
			Boolean isRealTime = jsonObject.getBoolean("isRealTime");// 是否是实时固定清单
			Integer browseCode = jsonObject.getInteger("browseCode");// 清单code
			String dept = jsonObject.getString("dept");// 部门编码
			// 非固定参数
			String channel = jsonObject.getString("isCustom");
			String org = jsonObject.getString("isCustom");
			String insure = jsonObject.getString("isCustom");
			String payment = jsonObject.getString("isCustom");
			String startTime = jsonObject.getString("isCustom");
			String endTime = jsonObject.getString("isCustom");
			List<Object> resultList = new ArrayList<Object>();
			// 列表标题数据
			GroDim gro = browseListService.findTarGroDimByCode("groupbyOrg");
			List<GroDimDetail> orgTitleList = browseListService.findGroDimDetailList("ORG_GROUP", gro.getGdId());
			List<String> dataTitleList = new ArrayList<String>();
			for (GroDimDetail groDimDetail : orgTitleList) {
				dataTitleList.add(groDimDetail.getGroDimName());
			}
			if (isRealTime) {
				// 处理实时固定清单
			} else {
				// 自定义清单或者部门固定清单
				// BillSql billSql = browseListService.findBillSqlByBill(browseCode);
				List<BillTarget> billTargets = browseListService.findBillTargetByBill(browseCode);
				if (null != billTargets) {
					for (BillTarget billTarget : billTargets) {
						dataTitleList
								.add(browseListService.findTargetByCode(billTarget.getTargetCode()).getTargetName());
					}
				}
				resultList.add(dataTitleList);
				// resultList.add(billSql.getSqlCode());
			}
			responseBeanDto.setJson(JSON.toJSONString(resultList));
			responseBeanDto.setErrCode("200");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setSuccess("true");
		} catch (Exception e) {
			// 异常响应处理
			e.printStackTrace();
			responseBeanDto.setJson("");
			responseBeanDto.setErrCode("500");
			responseBeanDto.setErrMsgs(e.toString());
			responseBeanDto.setSuccess("false");
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

}
