package com.huatai.web.thrift.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.GroDimDetailBean;
import com.huatai.web.bean.QueryDimBean;
import com.huatai.web.bean.TarRegBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.sql.SqlParameter;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.CommonService;
import com.huatai.web.thrift.service.SubjectAnalysisService;
import com.huatai.web.utils.DateUtil;

public class SsTargetServiceImpl implements SsTargetService.Iface {

	private static final String TEMPLET_CODE = "TEMP03";
	private static final String SUB_CODE = "R01";
	private static final Integer SPEC_CODE = 44; // 特殊区域
	private static final String LIST_CODE = "R01001";

	private SqlParameter sqlParameter;
	private CommonUtil commonUtil;

	public SsTargetServiceImpl() {
		commonUtil = SpringUtil.getBean(CommonUtil.class);
		sqlParameter = SpringUtil.getBean(SqlParameter.class);
	}

	public ResponseBeanDto analysisRealtime(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
		CommonService commonService = SpringUtil.getBean(CommonService.class);
		try {
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String username = jsonObject.getString("username");// 用户名
			String key = jsonObject.getString("key");
			Map map = new HashMap<>();
			// 查询筛选维度
			List<TarRegQue> tarRegQueList = subjectAnalysisService.findTarRegQueBySubAndTemp(SUB_CODE, TEMPLET_CODE);
			List<QueryDimBean> queryDimBeanList = new ArrayList<QueryDimBean>();
			if (null != tarRegQueList) {
				for (TarRegQue tarRegQue : tarRegQueList) {
					QueryDim queryDim = subjectAnalysisService
							.findQueryDimById(Integer.parseInt(tarRegQue.getQdId().toString()));
					List<QueryDimDetail> queryDimDetailList = (List<QueryDimDetail>) queryDim.getQueryDimDetail();
					if (null != queryDimDetailList) {
						for (QueryDimDetail queryDimDetail : queryDimDetailList) {
							QueryDimBean queryDimBean = new QueryDimBean();
							queryDimBean.setGroup(queryDim.getQueryDimCode());
							queryDimBean.setName(queryDimDetail.getVal());
							queryDimBean.setValue(queryDimDetail.getKeyCode());
							if (queryDimDetail.getIsDefault().equals("1")) {
								queryDimBean.setActive(true);
							} else {
								queryDimBean.setActive(false);
							}
							queryDimBeanList.add(queryDimBean);
						}
					}
				}
			}
			map.put("filterReq", queryDimBeanList);
			// 查询时间指标
			List<GroDimDetailBean> timeReqs = new ArrayList<GroDimDetailBean>();
			List<GroDimDetail> groDimDetails = commonService.findGroDetailListBySubWithDate(SUB_CODE);
			if (null != groDimDetails) {
				for (GroDimDetail groDimDetail : groDimDetails) {
					GroDimDetailBean groDimDetailBean = new GroDimDetailBean();
					groDimDetailBean.setName(groDimDetail.getGroDimName());
					groDimDetailBean.setValue(groDimDetail.getGroDimCode());
					if (groDimDetail.getGroDimCode().equals("MONTH")) {
						groDimDetailBean.setActive(true);
					} else {
						groDimDetailBean.setActive(false);
					}
					timeReqs.add(groDimDetailBean);
				}
			}
			map.put("timeReq", timeReqs);

			if ("api".equals(key)) {
				List<String> defaultTarList = subjectAnalysisService.findDefaultTarByTempAndSubAndUser(TEMPLET_CODE,
						SUB_CODE, username, role, "");
				if (null != defaultTarList && !defaultTarList.isEmpty()) {
					map.put("target1", defaultTarList);
				} else {
					String defaultTarget1 = subjectAnalysisService.findDefaultTargetByLevel(SUB_CODE, "1", "MONTH")
							.getTargetCode();
					map.put("target1", new String[] { defaultTarget1 });
				}
				Target defaultTarget2 = subjectAnalysisService.findDefaultTargetByLevelGroup(SUB_CODE, "2");
				map.put("target2", defaultTarget2.getTargetCode());
				List<TargetBean> defaultTargetBean = subjectAnalysisService
						.findTargetByRoleAndSubAndRegAndGraphType(role, "1", SPEC_CODE, SUB_CODE);// 特殊指标
				if (defaultTargetBean.size() > 0 && StringUtil.isNotNull(defaultTargetBean)) {
					map.put("spec", true);
				} else {
					map.put("spec", false);
				}
			} else {
				// 查询区域信息
				TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP03_REG02");
				TemReg temRegApp = subjectAnalysisService.findRegionByCode("TEMP12_REG01");
				List<TargetBean> targetList = subjectAnalysisService.findTargetByRoleAndSubAndRegWithTarRegBack(role,
						SUB_CODE, temReg.getRegId());
				List<TargetBean> newTarList = new ArrayList<>();
				for (int i = 0; i < targetList.size(); i++) {
					TargetBean tarBean = targetList.get(i);
					// TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5,
					// tarBean.getCode(),
					// temRegApp.getRegId(), null, null, "MONTH");
					// if (null != tarRegSql) {
					// tarBean.setSql(sqlParameter.getSszbSqlByRole(tarRegSql.getSqlCode(),
					// roleOrg));
					// }
					newTarList.add(tarBean);
				}
				// 一级默认指标
				List<String> defaultTarList = subjectAnalysisService.findDefaultTarByTempAndSubAndUser(TEMPLET_CODE,
						SUB_CODE, username, role, "");
				if (null != defaultTarList && !defaultTarList.isEmpty()) {
					for (TargetBean targetBean : newTarList) {
						String targetCode = targetBean.getCode();
						if (defaultTarList.contains(targetCode)) {
							targetBean.setChecked(true);
						} else {
							targetBean.setChecked(false);
						}
					}
				} else {
					String defaultTarget1 = subjectAnalysisService.findDefaultTargetByLevel(SUB_CODE, "1", "MONTH")
							.getTargetCode();
					for (TargetBean targetBean : newTarList) {
						if (defaultTarget1.equals(targetBean.getCode())) {
							targetBean.setChecked(true);
						} else {
							targetBean.setChecked(false);
						}
					}
				}
				map.put("target1", newTarList);

				// 查询默认二级指标分类
				Target defaultTarget2 = subjectAnalysisService.findDefaultTargetByLevelGroup(SUB_CODE, "2");
				List<TargetBean> targetBeanLevelList = new ArrayList<TargetBean>();
				List<Target> levelTarget = subjectAnalysisService.findTargetBysubAndTypeBack(role, "2", SUB_CODE);
				if (null != levelTarget) {
					for (Target targets : levelTarget) {
						String level = targets.getTargetCode();
						TargetBean targetBeans = new TargetBean();
						targetBeans.setCode(level);
						targetBeans.setName(targets.getTargetName());
						if (defaultTarget2.getTargetCode().equals(level)) {
							targetBeans.setChecked(true);
						} else {
							targetBeans.setChecked(false);
						}
						List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
						// 查询二级分组下的全部指标
						List<Target> targetList3 = subjectAnalysisService.findTargetBysubAndTypeBack(role, "3", level);
						if (null != targetList) {
							// 查询二级分组下的默认指标
							List<String> defaultTarList3 = subjectAnalysisService
									.findDefaultTarByTempAndSubAndUser(TEMPLET_CODE, SUB_CODE, username, role, level);
							if (null == defaultTarList3 || defaultTarList3.isEmpty()) {
								defaultTarList3.add(subjectAnalysisService.findDefaultTargetByLevel(level, "3", "MONTH")
										.getTargetCode());
							}
							for (Target target : targetList3) {
								TargetBean targetBean = new TargetBean();
								targetBean.setCode(target.getTargetCode());
								targetBean.setName(target.getTargetName());
								// TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5,
								// target.getTargetCode(),
								// temRegApp.getRegId(), null, null, "MONTH");
								// if (null != tarRegSql) {
								// targetBean.setSql(sqlParameter.getSszbSqlByRole(tarRegSql.getSqlCode(),
								// roleOrg));
								// }
								targetBean.setChecked(defaultTarList3.contains(target.getTargetCode()) ? true : false);
								targetBeanList.add(targetBean);
							}
						}
						targetBeans.setTarget(targetBeanList);
						targetBeanLevelList.add(targetBeans);
					}
				}
				map.put("target2", targetBeanLevelList);
				// 特殊指标
				List<TargetBean> specialTaregt = new ArrayList<TargetBean>();
				List<TargetBean> defaultTargetBean = subjectAnalysisService
						.findTargetByRoleAndSubAndRegAndGraphType(role, "1", SPEC_CODE, SUB_CODE);// 特殊指标
				if (null != defaultTargetBean) {
					for (TargetBean targetBeans : defaultTargetBean) {
						TargetBean targetb = new TargetBean();
						targetb.setName(targetBeans.getName());
						targetb.setCode(targetBeans.getCode());
						specialTaregt.add(targetb);
					}
				}
				map.put("spec", specialTaregt);

				// 1225修改，app分析管理指标改成一个6个sql
				List<TarRegSql> tarRegSqls = subjectAnalysisService.findTarRegSqlList(null, 61, "MONTH", SUB_CODE);
				List<String> sqls = new ArrayList<>();
				for (TarRegSql tarRegSql : tarRegSqls) {
					sqls.add(sqlParameter.getSszbSqlByRole(tarRegSql.getSqlCode(), roleOrg));
				}
				map.put("sqls", sqls);
			}
			responseBeanDto.setJson(JSON.toJSONString(map));
			// 成功响应处理
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

	public ResponseBeanDto analysisRealMain(String json) throws TException {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String groupbyDate = jsonObject.getString("groupbyDate");
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP03_REG02");
			List<TargetBean> targetList = subjectAnalysisService.findTargetByRoleAndSubAndRegWithTarRegBack(role,
					SUB_CODE, temReg.getRegId());
			List<TargetBean> newTarList = new ArrayList<>();
			for (int i = 0; i < targetList.size(); i++) {
				boolean isHas = commonUtil.isHasGroByTarAndGro(targetList.get(i).getCode(), groupbyDate);
				if (isHas) {
					newTarList.add(targetList.get(i));
				}
			}
			resultMap.put("targets", newTarList);
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	public ResponseBeanDto analysisRealSub(String json) throws TException {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
		try {
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String level = jsonObject.getString("level");
			String groupbyDate = jsonObject.getString("groupbyDate");
			String username = jsonObject.getString("username");// 用户名
			List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
			// 查询二级分组下的全部指标
			List<Target> targetList = subjectAnalysisService.findTargetBysubAndTypeBack(role, "3", level);
			if (null != targetList) {
				for (Target target : targetList) {
					boolean isHas = commonUtil.isHasGroByTarAndGro(target.getTargetCode(), groupbyDate);
					if (isHas) {
						TargetBean targetBean = new TargetBean();
						targetBean.setCode(target.getTargetCode());
						targetBean.setName(target.getTargetName());
						targetBean.setChecked(false);
						targetBeanList.add(targetBean);
					}
				}
			}
			resultMap.put("targets", targetBeanList);
			// 查询二级分组下的默认指标
			List<String> defaultTarList = subjectAnalysisService.findDefaultTarByTempAndSubAndUser(TEMPLET_CODE,
					SUB_CODE, username, role, level);
			if (null != defaultTarList && !defaultTarList.isEmpty()) {
				resultMap.put("defaultTarget", defaultTarList);
			} else {
				String defaultTarget2 = subjectAnalysisService.findDefaultTargetByLevel(level, "3", groupbyDate)
						.getTargetCode();
				resultMap.put("defaultTarget", new String[] { defaultTarget2 });
			}
			// 查询二级指标分类数据
			List<TargetBean> targetBeanLevelList = new ArrayList<TargetBean>();
			List<Target> levelTarget = subjectAnalysisService.findTargetBysubAndTypeBack(role, "2", SUB_CODE);
			if (null != levelTarget) {
				for (Target targets : levelTarget) {
					TargetBean targetBeans = new TargetBean();
					targetBeans.setCode(targets.getTargetCode());
					targetBeans.setName(targets.getTargetName());
					if (level.equals(targets.getTargetCode())) {
						targetBeans.setChecked(true);
					} else {
						targetBeans.setChecked(false);
					}
					targetBeanLevelList.add(targetBeans);
				}
			}
			resultMap.put("levels", targetBeanLevelList);
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(resultMap));
			responseBeanDto.setErrCode("200");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setSuccess("true");
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	public ResponseBeanDto analysisRealList(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			// 分析管理指标列表编码为R01001
			TarRegSql head = subjectAnalysisService.findTarRegSql(5, LIST_CODE, 58, null, null, "");
			TarRegSql tarRegSqlDay = subjectAnalysisService.findTarRegSql(5, LIST_CODE, 58, null, null, "DAY");
			List<TarRegSql> tarRegSqls = subjectAnalysisService.findTarRegSqlList(LIST_CODE, 58, "MONTH", null);
			TarRegSql tarRegSqlYear = subjectAnalysisService.findTarRegSql(5, LIST_CODE, 58, null, null, "YEAR");
			String sqlD = sqlParameter.getSszbSqlByRole(tarRegSqlDay.getSqlCode(), roleOrg);
			String sqlY = sqlParameter.getSszbSqlByRole(tarRegSqlYear.getSqlCode(), roleOrg);
			String sqlH = sqlParameter.getSszbSqlByRole(head.getSqlCode(), roleOrg);
			List<String> sqlMs = new ArrayList<>();
			for (TarRegSql tarRegSql : tarRegSqls) {
				sqlMs.add(sqlParameter.getSszbSqlByRole(tarRegSql.getSqlCode(), roleOrg));
			}
			Map map = new HashMap<>();
			map.put("day", sqlD);
			map.put("month", sqlMs);
			map.put("year", sqlY);
			map.put("head", sqlH);
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(map));
			responseBeanDto.setErrCode("200");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setSuccess("true");
		} catch (Exception e) {
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	public ResponseBeanDto analysisRealDownload(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {

		} catch (Exception e) {
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	public ResponseBeanDto analysisRealDef(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 用户名
			String username = jsonObject.getString("username");// 用户名
			String level = jsonObject.getString("level");// 主题编码
			String target1 = jsonObject.getString("target1");// 一级指标
			String target2 = jsonObject.getString("target2");// 二级指标
			List<String> target1List = JSON.parseArray(target1, String.class);
			List<String> target2List = JSON.parseArray(target2, String.class);
			int addFlag = 0;
			if (!target1List.isEmpty() && null != target1List && target2List.isEmpty()) {
				addFlag = subjectAnalysisService.saveUserDefTarget(target1List, username, role, SUB_CODE, level);
			} else if (target1List.isEmpty() && null != target2List && !target2List.isEmpty()) {
				addFlag = subjectAnalysisService.saveUserDefTarget(target2List, username, role, SUB_CODE, level);
			}
			// 成功响应处理
			if (addFlag == 1) {
				this.dataSuccessTips("{\"msg\":\"设置成功\"}", responseBeanDto);
			} else {
				this.dataSuccessTips("{\"msg\":\"设置失败\"}", responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	public ResponseBeanDto analysisRealSpec(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String module = jsonObject.getString("module");// 区域编码
			String key = jsonObject.getString("key");
			TemReg temReg = subjectAnalysisService.findRegionByCode(module);
			List<String> targetList = new ArrayList<>();
			if ("api".equals(key)) {
				targetList = subjectAnalysisService.findTargetByRegAndRoleAndOrgAndDeptAndSubject(temReg.getRegId(),
						role, roleOrg, null, SUB_CODE);
			} else {
				List<Object> targets = jsonObject.getJSONArray("target");
				for (Object target : targets) {
					targetList.add(target.toString());
				}
			}
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(targetList), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	public ResponseBeanDto analysisGetMainData(String json) throws TException {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色所在机构
			String targetCode = jsonObject.getString("targetCode");
			String groupbyDate = jsonObject.getString("groupbyDate");
			String filters = jsonObject.getString("filters");// 筛选维度
			String module = jsonObject.getString("module");// 区域code
			Target target = subjectAnalysisService.findTargetByCode(targetCode);
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode(module);
			TarReg tarReg = subjectAnalysisService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
					groupbyDate, null);
			if (null != tarRegSql) {
				// sql语句参数拼装处理
				String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
						filters);
				sqlCode = sqlParameter.getSszbSqlByRole(sqlCode, roleOrg);
				TarRegBean tarRegBean = new TarRegBean();
				tarRegBean.setCode(targetCode);
				tarRegBean.setName(target.getTargetName());
				tarRegBean.setType(tarReg.getGraphType());
				tarRegBean.setTitle(tarReg.getGraphTitle());
				tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
				tarRegBean.setStack("");
				tarRegBean.setColor(tarReg.getColor());
				tarRegBean.setSql(sqlCode);
				if (target.getDeptCode().equals("130105")) {
					tarRegBean.setTorg(true);
				} else {
					tarRegBean.setTorg(false);
				}
				resultMap.put("targetData", tarRegBean);
				this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			} else {
				// 异常响应处理
				// 数据异常
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	public ResponseBeanDto analysisSubData(String json) throws TException {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
		try {
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");
			String targetCode = jsonObject.getString("targetCode");
			String groupbyDate = jsonObject.getString("groupbyDate");
			String filters = jsonObject.getString("filters");// 筛选维度
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP03_REG03");
			TarReg tarReg = subjectAnalysisService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
					groupbyDate, null);
			if (null != tarRegSql) {
				// sql语句参数拼装处理
				String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
						filters);
				sqlCode = sqlParameter.getSszbSqlByRole(sqlCode, roleOrg);
				TarRegBean tarRegBean = new TarRegBean();
				tarRegBean.setCode(targetCode);
				tarRegBean.setName(subjectAnalysisService.findTargetNameByCode(targetCode));
				tarRegBean.setType(tarReg.getGraphType());
				tarRegBean.setTitle(tarReg.getGraphTitle());
				tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
				tarRegBean.setStack("");
				tarRegBean.setColor(tarReg.getColor());
				tarRegBean.setSql(sqlCode);
				resultMap.put("targetData", tarRegBean);
				this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	public ResponseBeanDto analysisSpecData(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			String targetCode = jsonObject.getString("targetCode");// 指标编码
			String filters = jsonObject.getString("filters");// 筛选条件
			String module = jsonObject.getString("module");// 区域code
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode(module);
			TarReg tarReg = subjectAnalysisService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			// 封装指标数据
			TarRegBean tarRegBean = new TarRegBean();
			tarRegBean.setName(subjectAnalysisService.findTargetByCode(tarReg.getTargetCode()).getTargetName());
			tarRegBean.setType(tarReg.getGraphType());
			tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
			tarRegBean.setCode(tarReg.getTargetCode());
			TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5, tarReg.getTargetCode(), temReg.getRegId(),
					null, null, groupbyDate);
			if (null != tarRegSql) {
				// sql语句参数拼装处理
				String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
						filters);
				sqlCode = sqlParameter.getSszbSqlByRole(sqlCode, roleOrg);
				tarRegBean.setSql(sqlCode);
				tarRegBean.setTitle(tarReg.getGraphTitle());
				tarRegBean.setColor(tarReg.getColor());
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(tarRegBean), responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * 成功提醒
	 * 
	 * @param jsonDta
	 * @param responseBeanDto
	 */
	public void dataSuccessTips(String jsonDta, ResponseBeanDto responseBeanDto) {
		responseBeanDto.setJson(jsonDta);
		responseBeanDto.setErrCode("200");
		responseBeanDto.setErrMsgs("System info: request success!");
		responseBeanDto.setSuccess("true");
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
	}

	/**
	 * 失败提醒
	 * 
	 * @param e
	 * @param responseBeanDto
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
	 * @功能 {数据为空提醒}
	 * @param jsonObject
	 * @param responseBeanDto
	 */
	public void dataIsEmptyTips(JSONObject jsonObject, ResponseBeanDto responseBeanDto) {
		responseBeanDto.setErrCode("E00001");
		responseBeanDto.setErrMsgs("System info: The target sql data is empty!");
		responseBeanDto.setSuccess("false");
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
	}
}