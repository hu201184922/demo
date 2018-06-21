package com.huatai.web.thrift.web;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.thrift.TException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.TargetDetailBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.bean.InsurProcessBean;
import com.huatai.web.bean.OrganizationBean;
import com.huatai.web.bean.QueryDimDetailBean;
import com.huatai.web.bean.TargetDASBean;
import com.huatai.web.model.ManageCom;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.TemReg;
import com.huatai.web.sql.SqlParameter;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.DashService;
import com.huatai.web.thrift.service.SubjectAnalysisService;
import com.huatai.web.utils.DateUtil;

/**
 * 达时报
 */
public class DashbordServiceImpl implements DashbordService.Iface {

	public static final String DAS0101 = "DAS0101"; // 新单保费图形区域
	public static final String DAS010101 = "DAS010101"; // 新单保费排名区域
	public static final String DAS0102 = "DAS0102"; // 人力区域
	public static final String DAS0103 = "DAS0103"; // 产品区域
	public static final String DAS0104 = "DAS0104"; // 绩优区域
	public static final String DAS0105 = "DAS0105"; // 投产比区域
	public static final String DAS0106 = "DAS0106"; // 机构区域
	public static final String DAS0107 = "DAS0107"; // 主管区域
	public static final String DAS0108 = "DAS0108"; // K2区域

	/**
	 * 根据不同的二级分组获取所有子指标
	 */
	public ResponseBeanDto getShortCut(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		DashService dashService = SpringUtil.getBean(DashService.class);
		try {
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleCode = jsonObject.getString("roleCode");// 角色代码
			String groupTargetCode = jsonObject.getString("groupTargetCode");// 用户机构代码
			List<InsurProcessBean> list = new ArrayList<InsurProcessBean>();
			list = dashService.findToTargetByParentCodeAndRoleCode(groupTargetCode, roleCode);
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(list));
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
	 * 保险流程详情接口
	 */
	@Override
	public ResponseBeanDto getDashAnalysis(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		DashService dashService = SpringUtil.getBean(DashService.class);
		try {
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			// roleOrg=10403
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			// DAS01 代表营销分析
			String code = jsonObject.getString("code");// 保险流程代码，目前只有一个
			if (StringUtil.isNull(code)) {
				code = "DAS01";
			}
			// 渠道 channel-->items、name = "CHANNEL_TYPE"
			List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
			// 查询出渠道：1#5-->个险、2-->银险、3-->团险
			List<QueryDimDetailBean> channelList = dashService.findQueryDimDetailByCode();
			Map<String, Object> channelMap = new HashMap<String, Object>();
			channelMap.put("name", "全部");
			channelMap.put("code", "all");
			// 加入List<Map<String, Object>>集合中去
			items.add(channelMap);
			// 把查询出的渠道也加入List<Map<String, Object>>集合中去
			for (QueryDimDetailBean queryDimDetailBean : channelList) {
				channelMap = new HashMap<String, Object>();
				channelMap.put("name", queryDimDetailBean.getValue());
				channelMap.put("code", queryDimDetailBean.getName());
				items.add(channelMap);
			}
			// 清空channelMap 把渠道封装成JSON格式
			channelMap = new HashMap<String, Object>();
			channelMap.put("name", "CHANNEL_TYPE");
			channelMap.put("items", items);

			List<Map<String, Object>> insurProcessList = new ArrayList<Map<String, Object>>();
			// 查询出保险流程：营销分析、销售分析、客户服务分析、承保分析等等
			List<InsurProcessBean> insurProcessBeanList = dashService.findInsurProcess();
			// 保险流程，封装成JSON形式
			for (InsurProcessBean insurProcessBean : insurProcessBeanList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", insurProcessBean.getCode());
				map.put("name", insurProcessBean.getName());
				map.put("shortName", "");
				map.put("desc", "");
				insurProcessList.add(map);
			}
			// 获取机构，根据传的roleOrg的长度获取不同的机构
			// 保存机构集合的JSON形式
			List<Map<String, Object>> orgList = new ArrayList<Map<String, Object>>();
			// 判断roleOrg（机构）是否为空、不是空进入if
			if (StringUtil.isNotNull(roleOrg)) {
				// 长度为1是总公司、长度为3是分公司、长度为5是中支，其他的是营销服务部
				if (roleOrg.length() == 1) {
					roleOrg = "1";
					int leng = 1;
					// 查询出机构
					List<ManageCom> manageList = dashService.findManageComByOrgCode(roleOrg, leng);
					if (manageList != null) {
						// 循环出机构封装成JSON形式
						for (ManageCom manageCom : manageList) {
							Map<String, Object> org = new HashMap<String, Object>();
							org.put("name", manageCom.getTeamComShortName());
							org.put("code", manageCom.getProvComCode());
							orgList.add(org);
						}
					}

				} else if (roleOrg.length() == 3) {
					int leng = roleOrg.length();
					List<ManageCom> manageList = dashService.findManageComByOrgCode(roleOrg, leng);
					if (manageList != null) {
						for (int i = 0; i < manageList.size(); i++) {
							Map<String, Object> org = new HashMap<String, Object>();
							org.put("name", manageList.get(i).getActuteamComShortName());
							org.put("code", manageList.get(i).getCountComCode());
							orgList.add(org);
						}
					}

				} else if (roleOrg.length() == 5) {
					// roleOrg-->10403
					int leng = roleOrg.length();
					List<OrganizationBean> manageList = dashService.findOrganizationComByOrgCode(roleOrg, leng);

					if (manageList != null) {
						for (OrganizationBean organization : manageList) {
							Map<String, Object> org = new HashMap<String, Object>();
							if (orgList.size() == 0) {
								org.put("name", organization.getPname());
								org.put("code", roleOrg);
								orgList.add(org);
								org = new HashMap<String, Object>();
							}
							org.put("name", organization.getName());
							org.put("code", organization.getCode());
							orgList.add(org);
						}
					}
				} else {
					int leng = roleOrg.length();
					List<OrganizationBean> manageList = dashService.findOrganizationComByOrgCode(roleOrg, leng);
					if (manageList != null) {
						for (OrganizationBean organization : manageList) {
							Map<String, Object> org = new HashMap<String, Object>();
							if (orgList.size() == 0) {
								org.put("name", organization.getPname());
								org.put("code", roleOrg);
								orgList.add(org);
								org = new HashMap<String, Object>();
							}
							org.put("name", organization.getName());
							org.put("code", organization.getCode());
							orgList.add(org);
						}
					}
				}
			}

			// 获取区域指标
			// 封装成JSON格式
			List<Map<String, Object>> modulesList = new ArrayList<Map<String, Object>>();
			// code=DAS01 代表营销分析
			if (StringUtil.isNotNull(code)) {
				// 查询出区域指标：新单保费、人力、产品、绩优、投产比、机构、K2等
				List<TargetDASBean> targetList = dashService.findTargetByRoleCode(code, role);

				if (targetList.size() != 0 || targetList != null) {
					for (TargetDASBean targetBean : targetList) {
						Map<String, Object> targetmap = new HashMap<String, Object>();
						// 第二步：遍历各开发主题获取二级分组
						String parnetCode = targetBean.getCode();
						// 封装区域指标下层指标
						List<Map<String, Object>> childTargetList = new ArrayList<Map<String, Object>>();
						// 查询出区域指标下层指标
						List<InsurProcessBean> targetGroupList = dashService
								.findToTargetByParentCodeAndRoleCode(parnetCode, role);
						for (InsurProcessBean insurProcessBean : targetGroupList) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("code", insurProcessBean.getCode());
							map.put("name", insurProcessBean.getName());
							childTargetList.add(map);
						}
						// 将数据一起封装
						// 格式：
						// {code=DAS0101, name=新单保费,
						// target=[
						// {code=DAS0101G01, name=承保标准保费(不含卡单)},
						// {code=DAS0101G02, name=受理标准保费(不含卡单)},
						// {code=DAS0101G03, name=价值保费}]}
						targetmap.put("code", targetBean.getCode());
						targetmap.put("name", targetBean.getName());
						targetmap.put("target", childTargetList);
						modulesList.add(targetmap);
					}
				}
			}
			// 封装所有结果
			Map<String, Object> Map = new HashMap<String, Object>();
			// insurProcessList为保险流程：营销分析、销售分析、客户服务分析、承保分析等等
			Map.put("shortcut", insurProcessList);
			// channelMap为渠道：个险、银险、团险
			Map.put("channel", channelMap);
			// orgList为机构
			Map.put("org", orgList);
			// modulesList为区域指标：新单保费、人力、产品、绩优、投产比、机构、K2等，及其下级指标
			Map.put("modules", modulesList);
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(Map));
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
	 * 保费指标接口
	 */
	public ResponseBeanDto getPremiumDetail(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		// "org":"1302040302","channel":{"code":"CHANNEL_TYPE","list":["1#5"]},"groupTargetCode":"","username":"10000066","role":"3_100101_1","roleDept":"100101","roleOrg":"10403","eqp":"pc"
		// "org":"1302040303","channel":{"code":"CHANNEL_TYPE","list":["1#5"]},"groupTargetCode":"","username":"10000066","role":"3_100101_1","roleDept":"100101","roleOrg":"10403","eqp":"pc"
		// "org":"1302040304","channel":{"code":"CHANNEL_TYPE","list":["1#5"]},"groupTargetCode":"","username":"10000066","role":"3_100101_1","roleDept":"100101","roleOrg":"10403","eqp":"pc"
		// "org":"1302040305","channel":{"code":"CHANNEL_TYPE","list":["1#5"]},"groupTargetCode":"","username":"10000066","role":"3_100101_1","roleDept":"100101","roleOrg":"10403","eqp":"pc"
		DashService dashService = SpringUtil.getBean(DashService.class);
		try {
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			// roleDept=100101
			String roleDept = jsonObject.getString("roleDept");// 角色所在部门
			String groupTargetCode = jsonObject.getString("groupTargetCode"); // 分组指标
			JSONObject channel = jsonObject.getJSONObject("channel");// 渠道
			String org = jsonObject.getString("org");
			if (StringUtil.isNull(channel)) {
				channel.put("code", "CHANNEL_TYPE");
				List<QueryDimDetail> qdds = dashService.findAllChannelList();
				List keyList = new ArrayList<>();
				for (QueryDimDetail queryDimDetail : qdds) {
					keyList.add(queryDimDetail.getKeyCode());
				}
				channel.put("list", keyList);
			}
			// {"channel":{"name":"筛选渠道类型","list":[{"value":"个险","key":"1"}],"code":"CHANNEL_TYPE"},"groupTargetCode":"DAS0101G01"}
			List<TargetBean> targetBs = dashService.getPremiumDetail(role, groupTargetCode);
			Set<String> pwdSet = new HashSet<>();
			List<TargetDetailBean> targetDetailList = new ArrayList<TargetDetailBean>();
			if (targetBs.size() > 0) {
				targetBs.forEach(target -> {
					TargetDetailBean targeDetailBean = dashService.getInitPremiumDetail(role, org, roleDept,
							target.getCode(), channel, DAS0101, null, null);
					if (null != targeDetailBean) {
						pwdSet.add(targeDetailBean.getPwd());
						targetDetailList.add(targeDetailBean);
					}
				});
			}
			Map<String, Object> returnMap = new HashMap<String, Object>();
			// 第三步：按照合并标识合并,并按照排序分成不同的组
			if (pwdSet.size() > 0) {
				for (String pwd : pwdSet) {
					List<TargetDetailBean> returnList = new ArrayList<TargetDetailBean>();
					String targetCode = "";
					for (TargetDetailBean targetDetailBean : targetDetailList) {
						if (targetDetailBean.getPwd().equals(pwd)) {
							returnList.add(targetDetailBean);
							targetCode = targetDetailBean.getTargetCode();// 获取指标编码
						}
					}
					if (StringUtil.isNotNull(targetCode)) {
						if ("DAS0101001".equals(targetCode) || "DAS0101002".equals(targetCode)
								|| "DAS0101003".equals(targetCode) || "DAS0101010".equals(targetCode)
								|| "DAS0101011".equals(targetCode) || "DAS0101012".equals(targetCode)
								|| "DAS0101019".equals(targetCode) || "DAS0101020".equals(targetCode)
								|| "DAS0101021".equals(targetCode)) {
							returnMap.put("premium", returnList);
						} else if ("DAS0101004".equals(targetCode) || "DAS0101005".equals(targetCode)
								|| "DAS0101006".equals(targetCode) || "DAS0101013".equals(targetCode)
								|| "DAS0101014".equals(targetCode) || "DAS0101015".equals(targetCode)
								|| "DAS0101022".equals(targetCode) || "DAS0101023".equals(targetCode)
								|| "DAS0101024".equals(targetCode)) {
							returnMap.put("agent", returnList);
						} else {
							returnMap.put("renewal", returnList);
						}
					}
				}
			}
			if (!returnMap.containsKey("premium")) {
				returnMap.put("premium", new ArrayList<TargetDetailBean>());
			}
			if (!returnMap.containsKey("agent")) {
				returnMap.put("agent", new ArrayList<TargetDetailBean>());
			}
			if (!returnMap.containsKey("renewal")) {
				returnMap.put("renewal", new ArrayList<TargetDetailBean>());
			}
			// 第四步：获取排名数据
			String rankTargetCode = "";
			if (groupTargetCode.equals("DAS0101G01")) {
				rankTargetCode = "DAS0101003";// 获取排名指标编码
			} else if (groupTargetCode.equals("DAS0101G02")) {
				rankTargetCode = "DAS0101012";
			} else {
				rankTargetCode = "DAS0101021";
			}
			// 替换Sql
			TargetDetailBean targetRankBean = dashService.getInitPremiumDetail(role, org, roleDept, rankTargetCode,
					channel, DAS010101, null,null);
			if (targetRankBean != null) {
				List<TargetDetailBean> targetRankBeanList = new ArrayList<TargetDetailBean>();
				targetRankBeanList.add(targetRankBean);
				returnMap.put("rank", targetRankBeanList);
			}
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(returnMap));
			responseBeanDto.setErrCode("200");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setSuccess("true");
		} catch (Exception e) {
			// 异常响应处理
			e.printStackTrace();
			responseBeanDto.setJson(JSON.toJSONString(e));
			responseBeanDto.setErrCode("500");
			responseBeanDto.setErrMsgs(e.toString());
			responseBeanDto.setSuccess("false");
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

	/**
	 * 其他指标
	 */
	public ResponseBeanDto getOrtherTarget(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		DashService dashService = SpringUtil.getBean(DashService.class);
		try {
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String roleDept = jsonObject.getString("roleDept");// 角色所在部门
			String targetCode = jsonObject.getString("target"); // 主题指标
			// DAS0102G01 DAS0102001
			String regCode = jsonObject.getString("subject"); // 区域
			// 前台传入的渠道，个险、银险等
			JSONObject channel = jsonObject.getJSONObject("channel");// 渠道
			String riskCode = jsonObject.getString("riskCode");// 前三名
			// 代号
			String org = jsonObject.getString("org");
			if (StringUtil.isNull(channel)) {
				channel.put("code", "CHANNEL_TYPE");
				List<QueryDimDetail> qdds = dashService.findAllChannelList();
				List keyList = new ArrayList<>();
				for (QueryDimDetail queryDimDetail : qdds) {
					keyList.add(queryDimDetail.getKeyCode());
				}
				channel.put("list", keyList);
			}
			Map<String, Object> returnMap = new HashMap<>();
			List<TargetBean> targetBs = dashService.getPremiumDetail(role, targetCode);
			List<TargetDetailBean> targetDetailList = new ArrayList<TargetDetailBean>();
			if (targetBs.size() > 0) {
				for (TargetBean target : targetBs) {
					// 替换Sql
					TargetDetailBean targetDetailBean = null;
					if(org.length()<=5){
						targetDetailBean = dashService.getInitPremiumDetail(role, org, roleDept, target.getCode(),
								channel, regCode, riskCode, targetCode);
					}else{
						if(!("DAS0106G01").equals(targetCode)){
							if(!target.getCode().equals("DAS0106005")) {
								targetDetailBean = dashService.getInitPremiumDetail(role, org, roleDept, target.getCode(),
										channel, regCode, riskCode, targetCode);
							}
						}
					}
					/*
					 * TargetDetailBean targetDetailBean =
					 * dashService.getInitPremiumDetail(role, org, roleDept,
					 * target.getCode(), channel, regCode, riskCode, targetCode);
					 */
					if (targetDetailBean != null) {
						targetDetailList.add(targetDetailBean);
					} else {
						TargetDetailBean tBean = new TargetDetailBean();
						tBean.setTargetCode(target.getCode());
						tBean.setTargetName(target.getName());
						tBean.setSort(target.getSort().toString());
						targetDetailList.add(tBean);
					}
				}
			}
			returnMap.put("targets", targetDetailList);
			if (StringUtil.isNotNull(riskCode)) {
				returnMap.put("riskCode", riskCode);
			}
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(returnMap));
			responseBeanDto.setErrCode("200");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setSuccess("true");
		} catch (Exception e) {
			// 异常响应处理
			e.printStackTrace();
			responseBeanDto.setJson(JSON.toJSONString(e));
			responseBeanDto.setErrCode("500");
			responseBeanDto.setErrMsgs(e.toString());
			responseBeanDto.setSuccess("false");
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

	// 达时报-实时指标
	public ResponseBeanDto getRealTimeTarget(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			SqlParameter sqlParameter = SpringUtil.getBean(SqlParameter.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色所在机构
			TemReg temReg = subjectAnalysisService.findRegionByCode("R01_REG01");
			TarReg tarReg = subjectAnalysisService.findTarRegByRegAndSub(temReg.getRegId(), "R01");
			String targetCode = tarReg.getTargetCode();
			Integer regId = temReg.getRegId();
			TarRegSql tarRegSqlday = subjectAnalysisService.findTarRegSql(5, targetCode, regId, null, null, "DAY");
			TarRegSql tarRegSqlmon = subjectAnalysisService.findTarRegSql(5, targetCode, regId, null, null, "MONTH");
			TarRegSql tarRegSqlyear = subjectAnalysisService.findTarRegSql(5, targetCode, regId, null, null, "YEAR");
			Map returnMap = new HashMap<>();
			Map<String, Object> sqls = new HashMap<>();
			sqls.put("DAY", sqlParameter.getSszbSqlByRole(tarRegSqlday.getSqlCode(), roleOrg));
			sqls.put("MONTH", sqlParameter.getSszbSqlByRole(tarRegSqlmon.getSqlCode(), roleOrg));
			sqls.put("YEAR", sqlParameter.getSszbSqlByRole(tarRegSqlyear.getSqlCode(), roleOrg));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			returnMap.put("endTime", sdf.format(new Date()));
			returnMap.put("unit", "万元");
			returnMap.put("sql", sqls);
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(returnMap));
			responseBeanDto.setErrCode("200");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setSuccess("true");
		} catch (Exception e) {
			// 异常响应处理
			e.printStackTrace();
			responseBeanDto.setJson(JSON.toJSONString(e));
			responseBeanDto.setErrCode("500");
			responseBeanDto.setErrMsgs(e.toString());
			responseBeanDto.setSuccess("false");
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}
}
