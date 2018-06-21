package com.huatai.web.thrift.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.DataBean;
import com.huatai.web.bean.GroDimDetailBean;
import com.huatai.web.bean.OrgBean;
import com.huatai.web.bean.QueryDimBean;
import com.huatai.web.bean.TarRegBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.TarStatis;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.sql.SqlConstants;
import com.huatai.web.sql.SqlFactory;
import com.huatai.web.sql.SqlParameter;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.CommonService;
import com.huatai.web.thrift.service.SubjectAnalysisService;
import com.huatai.web.utils.DateUtil;

public class ZtfxServiceImpl implements ZtfxService.Iface {

	private static final String TEMPLET_CODE = "TEMP03";
	private SqlFactory sqlFactory;
	private SqlParameter sqlParameter;
	private CommonUtil commonUtil;
	private List<String> bfbSubList = null;

	public ZtfxServiceImpl() {
		sqlFactory = SpringUtil.getBean(SqlFactory.class);
		sqlParameter = SpringUtil.getBean(SqlParameter.class);
		commonUtil = SpringUtil.getBean(CommonUtil.class);
		bfbSubList = new ArrayList<String>();
		bfbSubList.add("T09");
	}

	Logger log = Logger.getLogger(ZtfxServiceImpl.class);

	/**
	 * @状态 ok
	 * @功能 {获取区域的指标}01
	 * @作者 MaxBill
	 * @时间 2017年8月31日 下午4:58:25
	 * 
	 * 分析管理下----蜜蜂图案数据统计处
	 */
	public ResponseBeanDto getRegTarget(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String subject = jsonObject.getString("subject");// 角色部门
			String module = jsonObject.getString("module");// 区域编码
			TemReg temReg = subjectAnalysisService.findRegionByCode(module);
			List<String> targetList = subjectAnalysisService
					.findTargetByRegAndRoleAndOrgAndDeptAndSubject(temReg.getRegId(), role, roleOrg, null, subject);
			// 成功响应处理
			dataPrintTips(1, targetList);
			this.dataSuccessTips(JSON.toJSONString(targetList), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {主题、板块菜单快捷键}02
	 * @作者 MaxBill
	 * @时间 2017年8月18日 上午10:37:02
	 */
	public ResponseBeanDto getFastMenu(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			Map ruseltMap = new HashMap();
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			// 主题标识0,板块标识1;非实时指标0,实时指标1
			List<TargetBean> realtime = subjectAnalysisService.findSubjectByTypeAndRole("0", role, "1");
			List<TargetBean> subjects = subjectAnalysisService.findSubjectByTypeAndRole("0", role, "0");
			List<TargetBean> blocks = subjectAnalysisService.findSubjectByTypeAndRole("1", role, "0");
			ruseltMap.put("realTimes", realtime);
			ruseltMap.put("subjects", subjects);
			ruseltMap.put("blocks", blocks);
			// 成功响应处理
			dataPrintTips(2, ruseltMap);
			this.dataSuccessTips(JSON.toJSONString(ruseltMap), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {主题接口}03
	 * @作者 MaxBill
	 * @时间 2017年7月18日 下午14:16:48
	 */
	public ResponseBeanDto getSubject(String json) {
		log.info("接收到数据：" + json);
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			CommonService commonService = SpringUtil.getBean(CommonService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String roleDept = jsonObject.getString("roleDept");// 角色部门
			String subject = jsonObject.getString("subject");// 主题编码
			String username = jsonObject.getString("username");// 用户名
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String orgTemp = roleOrg;
			Map map = new HashMap<>();
			if (!StringUtils.isEmpty(orgCode)) {
				roleOrg = orgCode;
			}
			// 查询筛选维度
			List<TarRegQue> tarRegQueList = subjectAnalysisService.findTarRegQueBySubAndTemp(subject, TEMPLET_CODE);
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
			List<GroDimDetail> groDimDetails = commonService.findGroDetailListBySubWithDate(subject);
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
			// 查询当前页面模板区域
			List<String> modules = subjectAnalysisService.findRegionByRoleorgAndSubject(TEMPLET_CODE, roleOrg, subject,
					role);
			map.put("modules", modules);
			// 查询默认一级指标
			List<String> defaultTarList = subjectAnalysisService.findDefaultTarByTempAndSubAndUser(TEMPLET_CODE,
					subject, username, role, "");
			if (null != defaultTarList && !defaultTarList.isEmpty()) {
				map.put("defaultTarget1", defaultTarList);
			} else {
				String defaultTarget1 = subjectAnalysisService.findDefaultTargetByLevel(subject, "1", "MONTH")
						.getTargetCode();
				map.put("defaultTarget1", new String[] { defaultTarget1 });
			}
			// 查询默认二级指标分类
			Target defaultTarget2 = subjectAnalysisService.findDefaultTargetByLevelGroup(subject, "2");
			map.put("defaultTarget2", defaultTarget2.getTargetCode());
			// 查询主题数据
			Target subBean = subjectAnalysisService.findTargetByCode(subject);
			// 是否显示区间统计
			if (subBean.getIsStatis().equals("1")) {
				map.put("isRegion", true);
			} else {
				map.put("isRegion", false);
			}
			// 判断是否显示机构对比
			boolean isOrgCompare = false;
			List<TarReg> tarRegList = subjectAnalysisService.findTarRegsBySubAndRole(TEMPLET_CODE, subject, role); //
			int num = 0;
			for (TarReg tarReg : tarRegList) {
				if (commonUtil.hasRoleOrgCompare(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
					num = num + 1;
				}
			}
			if (num > 0) {
				isOrgCompare = true;
			}
			// 判断角色机构下是否有多个下级，大于1个则展示
			if (isOrgCompare) {
				List<OrgBean> orgList = null;
				if (StringUtils.isEmpty(orgCode)) {
					switch (orgTemp.length()) {
					case 1:
						// 总公司
						orgList = subjectAnalysisService.getOrgList(1, roleOrg, null);
						break;
					case 3:
						// 分公司
						orgList = subjectAnalysisService.getOrgList(2, roleOrg, null);
						break;
					case 5:
						// 中支
						orgList = subjectAnalysisService.getOrgList(3, roleOrg, roleDept);
						break;
					default:
						// 营销服务部
						orgList = subjectAnalysisService.getOrgList(42, roleOrg, null);
						break;
					}
				} else {
					String orgType = jsonObject.getString("orgType");
					switch (orgType) {
					case "7":
						// 分公司
						orgList = subjectAnalysisService.getOrgList(2, orgCode, null);
						break;
					case "6":
						// 中支
						orgList = subjectAnalysisService.getOrgList(3, orgCode, roleDept);
						break;
					case "501":
						// 营销服务部
						orgList = subjectAnalysisService.getOrgList(41, orgCode, null);
						break;
					case "502":
						// 成本中心
						orgList = subjectAnalysisService.getOrgList(42, orgCode, null);
						break;
					}
				}
				if (null == orgList || orgList.size() < 2) {
					isOrgCompare = false;
				}
			}
			if (isOrgCompare) {
				map.put("isOrgCompare", true);
			} else {
				map.put("isOrgCompare", false);
			}
			// 判断是否显示搜索框
			if (orgTemp.length() > 5) {
				map.put("isSearch", false);
			} else {
				if (orgTemp.length() == 5) {
					if (subject.equals("T07") || subject.equals("T08")) {
						map.put("isSearch", false);
					} else {
						map.put("isSearch", true);
					}
				} else {
					map.put("isSearch", true);
				}
			}
			// 成功响应处理
			dataPrintTips(3, map);
			this.dataSuccessTips(JSON.toJSONString(map), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {主区域的指标数据}04
	 * @作者 MaxBill
	 * @时间 2017年9月8日 下午1:37:51
	 */
	public ResponseBeanDto getMainTarget(String json) {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String groupbyDate = jsonObject.getString("groupbyDate");
			String subject = jsonObject.getString("subject");
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP03_REG02");
			List<TargetBean> targetList = subjectAnalysisService.findTargetByRoleAndSubAndRegWithTarReg(role, subject,
					temReg.getRegId(), groupbyDate);
			List<TargetBean> newTarList = new ArrayList<>();
			for (int i = 0; i < targetList.size(); i++) {
				boolean isHas = commonUtil.isHasGroByTarAndGro(targetList.get(i).getCode(), groupbyDate);
				if (isHas) {
					newTarList.add(targetList.get(i));
				}
			}
			resultMap.put("targets", newTarList);
			// 成功响应处理
			dataPrintTips(4, resultMap);
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {sub区域的指标数据}05
	 * @作者 MaxBill
	 * @时间 2017年9月8日 下午1:37:51
	 */
	public ResponseBeanDto getSubTarget(String json) {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
		try {
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String level = jsonObject.getString("level");
			String groupbyDate = jsonObject.getString("groupbyDate");
			String subject = jsonObject.getString("subject");
			String username = jsonObject.getString("username");// 用户名
			List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
			// 查询二级分组下的全部指标
			List<Target> targetList = subjectAnalysisService.findTargetBysubAndType(role, "3", level, groupbyDate);
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
					subject, username, role, level);
			if (null != defaultTarList && !defaultTarList.isEmpty()) {
				resultMap.put("defaultTarget", defaultTarList);
			} else {
				Target defaultTarget = subjectAnalysisService.findDefaultTargetByLevel(level, "3", groupbyDate);
				if (null != defaultTarget) {
					resultMap.put("defaultTarget", new String[] { defaultTarget.getTargetCode() });
				} else {
					resultMap.put("defaultTarget", new String[] {});
				}
			}
			// 查询二级指标分类数据
			List<TargetBean> targetBeanLevelList = new ArrayList<TargetBean>();
			List<Target> levelTarget = subjectAnalysisService.findTargetBysubAndTypeBack(role, "2", subject);
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
			dataPrintTips(5, resultMap);
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {常用指标接口}06
	 * @作者 MaxBill
	 * @时间 2017年7月20日 上午9:22:23
	 */
	public ResponseBeanDto getCommonData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String targetCode = jsonObject.getString("targetCode");// 指标编码
			String filters = jsonObject.getString("filters");// 筛选维度
			String groupbyDate = jsonObject.getString("groupbyDate");// 时间维度
			String module = jsonObject.getString("module");// 区域code
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode(module);
			TarReg tarReg = subjectAnalysisService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			// 封装返回数据
			TarRegBean tarRegBean = new TarRegBean();
			TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5, tarReg.getTargetCode(), temReg.getRegId(),
					null, null, groupbyDate);
			if (null != tarRegSql) {
				// sql语句参数拼装处理
				String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
						filters);
				if (!StringUtils.isEmpty(orgCode)) {
					// 按搜索机构处理
					String orgType = jsonObject.getString("orgType");
					sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0101, sqlCode, orgCode, null, orgType);
				} else {
					// 按用户机构处理
					sqlCode = sqlFactory.getZtfxSql(SqlConstants.FXGL_01, sqlCode, roleOrg, null);
				}
				tarRegBean.setName(tarReg.getGraphTitle());
				tarRegBean.setSql(sqlCode);
				// 成功响应处理
				dataPrintTips(6, tarRegBean);
				this.dataSuccessTips(JSON.toJSONString(tarRegBean), responseBeanDto);
			} else {
				// 数据异常
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {一级指标区域}07
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午2:26:42
	 */
	public ResponseBeanDto getMainData(String json) {
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
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String orgType = "";// 机构类型
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
				if (!StringUtils.isEmpty(orgCode)) {
					// 按搜索机构处理
					orgType = jsonObject.getString("orgType");
					sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0101, sqlCode, orgCode, null, orgType);
				} else {
					// 按用户机构处理
					sqlCode = sqlFactory.getZtfxSql(SqlConstants.FXGL_01, sqlCode, roleOrg, null);
				}
				log.info("分析管理一级区域替换后SQL:" + sqlCode);
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
				dataPrintTips(7, resultMap);
			} else {
				// 数据异常
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {二级指标区域}08
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午2:27:10
	 */
	public ResponseBeanDto getSubData(String json) {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
		try {
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");
			String targetCode = jsonObject.getString("targetCode");
			String groupbyDate = jsonObject.getString("groupbyDate");
			String filters = jsonObject.getString("filters");// 筛选维度
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP03_REG03");
			TarReg tarReg = subjectAnalysisService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
					groupbyDate, null);
			if (null != tarRegSql) {
				// sql语句参数拼装处理
				String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
						filters);
				if (!StringUtils.isEmpty(orgCode)) {
					// 按搜索机构处理
					String orgType = jsonObject.getString("orgType");
					sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0101, sqlCode, orgCode, null, orgType);
				} else {
					// 按用户机构处理
					sqlCode = sqlFactory.getZtfxSql(SqlConstants.FXGL_01, sqlCode, roleOrg, null);
				}
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
				dataPrintTips(8, resultMap);
			} else {
				// 数据异常
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {分公司/机构区域接口（dist）}09
	 * @作者 MaxBill
	 * @时间 2017年7月19日 上午10:09:28
	 */
	public ResponseBeanDto getDistData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			Map resultMap = new HashMap();
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String groupbyDate = jsonObject.getString("groupbyDate");// 时间维度
			String targetCode = jsonObject.getString("targetCode");// 指标代码
			String filters = jsonObject.getString("filters");// 筛选维度
			String module = jsonObject.getString("module");// 区域code
			String subject = jsonObject.getString("subject");// 主题编码
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode(module);
			TarReg tarReg = subjectAnalysisService.findTarReg(targetCode, temReg.getRegId());
			if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
				TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(3, targetCode, temReg.getRegId(),
						"ORG_GROUP", null, groupbyDate);
				if (null != tarRegSql) {
					TarRegBean tarRegBean = new TarRegBean();// 封装参数
					resultMap.put("name", tarReg.getGraphTitle());
					resultMap.put("stacking", "");
					resultMap.put("xAxis", "");
					tarRegBean.setCode(tarReg.getTargetCode());
					Target target = subjectAnalysisService.findTargetByCode(tarReg.getTargetCode());
					String targetDeptCode = target.getDeptCode();
					tarRegBean.setName(target.getTargetName());
					tarRegBean.setStack("");
					tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
					tarRegBean.setType(tarReg.getGraphType());
					tarRegBean.setColor(tarReg.getColor());
					if (null != tarRegSql) {
						// sql语句参数拼装处理
						String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
								tarRegSql.getTrsId(), filters);
						// 按排名区域渠道处理
						if (!StringUtils.isEmpty(orgCode)) {
							// 按搜索机构处理
							String orgType = jsonObject.getString("orgType");
							switch (roleOrg.length()) {
							// 总公司
							case 1:
								switch (orgType) {
								// 分公司
								case "7":
									// 此处是中支机构
									sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0201, sqlCode, orgCode,
											targetDeptCode, orgType);
									tarRegBean.setData(sqlCode);
									resultMap.put("series", Arrays.asList(tarRegBean));
									// 成功响应处理
									this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
									dataPrintTips(9, resultMap);
									break;
								// 中支
								case "6":
									// 此处是四级机构
									if (subject.equals("T07")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0202, sqlCode,
												orgCode, targetDeptCode, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										// 成功响应处理
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(9, resultMap);
									}
									break;
								// 四级机构
								default:
									// 此处是区机构
									if (subject.equals("T07") || subject.equals("T08")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0203, sqlCode,
												orgCode, targetDeptCode, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										// 成功响应处理
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(9, resultMap);
									}
									break;
								}
								break;
							// 分公司
							case 3:
								switch (orgType) {
								// 中支
								case "6":
									// 此处是四级机构
									if (subject.equals("T07")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0202, sqlCode,
												orgCode, targetDeptCode, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										// 成功响应处理
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(9, resultMap);
									}
									break;
								// 四级机构
								default:
									// 此处是区机构
									if (subject.equals("T07") || subject.equals("T08")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0203, sqlCode,
												orgCode, targetDeptCode, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										// 成功响应处理
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(9, resultMap);
									}
									break;
								}
								break;
							// 中支
							case 5:
								switch (orgType) {
								// 四级机构
								default:
									// 此处是区机构
									if (subject.equals("T07") || subject.equals("T08")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0203, sqlCode,
												orgCode, targetDeptCode, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										// 成功响应处理
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(9, resultMap);
									}
									break;
								}
								break;
							}
							if (orgCode.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
						} else {
							// 按用户机构处理
							sqlCode = sqlFactory.getZtfxSql(SqlConstants.FXGL_02, sqlCode, roleOrg, targetDeptCode);
							if (roleOrg.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
							tarRegBean.setData(sqlCode);
							resultMap.put("series", Arrays.asList(tarRegBean));
							// 成功响应处理
							this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
							dataPrintTips(9, resultMap);
						}
					}
				} else {
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}
			} else {
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {机构列表区域接口（org）}10
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午1:59:56
	 */
	public ResponseBeanDto getOrgData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String pOrg = jsonObject.getString("pOrg");// 上级机构代码
			String targetCode = jsonObject.getString("targetCode");// 一级指标
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			String filters = jsonObject.getString("filters");// 筛选条件
			String subject = jsonObject.getString("subject");// 主题编码
			String module = jsonObject.getString("module");// 区域code
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			Target target = subjectAnalysisService.findTargetByCode(targetCode);
			String targetDeptCode = target.getDeptCode();
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode(module);
			TarReg tarReg = subjectAnalysisService.findTarReg(targetCode, temReg.getRegId());
			if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
				TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(3, targetCode, temReg.getRegId(),
						"ORG_GROUP", null, groupbyDate);
				if (null != tarRegSql) {
					List<Object> dataList = new ArrayList<Object>();
					// 封装表格头部信息
					List<String> tarRegTabHeadList = subjectAnalysisService
							.findTarRegTabHeadByRegAndTar(temReg.getRegId(), targetCode);
					dataList.add(tarRegTabHeadList);
					TarRegBean tarRegBean = new TarRegBean();
					tarRegBean.setName(tarReg.getGraphTitle());
					tarRegBean.setType(tarReg.getGraphType());
					tarRegBean.setX(tarReg.getxName());
					tarRegBean.setY(tarReg.getyName());
					// sql语句参数拼装处理
					String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
							filters);
					if (!StringUtils.isEmpty(orgCode)) {
						// 按搜索机构处理
						String orgType = jsonObject.getString("orgType");
						switch (roleOrg.length()) {
						// 总公司
						case 1:
							switch (orgType) {
							// 分公司
							case "7":
								// 此处是中支机构
								if (subject.equals("T07") || subject.equals("T08")) {
									this.dataIsEmptyTips(jsonObject, responseBeanDto);
								} else {
									sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0301, sqlCode, pOrg,
											targetDeptCode, orgType);
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									// 成功响应处理
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(10, dataList);
								}
								break;
							// 中支
							case "6":
								// 此处是四级机构
								if (subject.equals("T07") || subject.equals("T08")) {
									this.dataIsEmptyTips(jsonObject, responseBeanDto);
								} else {
									sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0302, sqlCode, pOrg,
											targetDeptCode, orgType);
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									// 成功响应处理
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(10, dataList);
								}
								break;
							default:
								// 此处是区机构
								if (subject.equals("T07") || subject.equals("T08")) {
									this.dataIsEmptyTips(jsonObject, responseBeanDto);
								} else {
									sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0303, sqlCode, pOrg,
											targetDeptCode, orgType);
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									// 成功响应处理
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(10, dataList);
								}
								break;
							}
							break;
						// 分公司
						case 3:
							switch (orgType) {
							// 中支
							case "6":
								// 此处是四级机构
								if (subject.equals("T07") || subject.equals("T08")) {
									this.dataIsEmptyTips(jsonObject, responseBeanDto);
								} else {
									sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0302, sqlCode, pOrg,
											targetDeptCode, orgType);
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									// 成功响应处理
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(10, dataList);
								}
								break;
							default:
								// 此处是区机构
								if (subject.equals("T07") || subject.equals("T08")) {
									this.dataIsEmptyTips(jsonObject, responseBeanDto);
								} else {
									sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0303, sqlCode, pOrg,
											targetDeptCode, orgType);
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									// 成功响应处理
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(10, dataList);
								}
								break;
							}
							break;
						// 中支
						case 5:
							switch (orgType) {
							default:
								// 此处是区机构
								if (subject.equals("T07") || subject.equals("T08")) {
									this.dataIsEmptyTips(jsonObject, responseBeanDto);
								} else {
									sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0303, sqlCode, pOrg,
											targetDeptCode, orgType);
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									// 成功响应处理
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(10, dataList);
								}
								break;
							}
							break;
						}
						if (pOrg.length() == 5) {
							sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
						}
					} else {
						// 按用户机构处理
						switch (roleOrg.length()) {
						case 5:
							sqlCode = sqlFactory.getZtfxSql(SqlConstants.FXGL_05, sqlCode, pOrg, targetDeptCode);
							break;
						case 10:
							sqlCode = sqlFactory.getZtfxSql(SqlConstants.FXGL_06, sqlCode, pOrg, targetDeptCode);
							break;
						default:
							sqlCode = sqlFactory.getZtfxSql(SqlConstants.FXGL_03, sqlCode, pOrg, targetDeptCode);
							break;
						}
						if (pOrg.length() == 5) {
							// 上级机构是中支
							sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
						}
						tarRegBean.setSql(sqlCode);
						tarRegBean.setColor(tarReg.getColor());
						dataList.add(tarRegBean);
						// 成功响应处理
						this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
						dataPrintTips(10, dataList);
					}
				} else {
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}
			} else {
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {保费部区域（torg）}11
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午2:00:05
	 */
	public ResponseBeanDto getTorgData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String targetCode = jsonObject.getString("targetCode");// 一级指标
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			String filters = jsonObject.getString("filters");// 筛选条件
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			Target target = subjectAnalysisService.findTargetByCode(targetCode);
			String targetDeptCode = target.getDeptCode();
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP03_REG07");
			TarReg tarReg = subjectAnalysisService.findTarReg(targetCode, temReg.getRegId());
			if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
				TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(3, targetCode, temReg.getRegId(),
						"ORG_GROUP", null, groupbyDate);
				if (null != tarRegSql) {
					List<Object> dataList = new ArrayList<Object>();
					// 封装表格头部信息
					List<String> tarRegTabHeadList = subjectAnalysisService
							.findTarRegTabHeadByRegAndTar(temReg.getRegId(), targetCode);
					dataList.add(tarRegTabHeadList);
					TarRegBean tarRegBean = new TarRegBean();
					tarRegBean.setName(tarReg.getGraphTitle());
					tarRegBean.setType(tarReg.getGraphType());
					tarRegBean.setX(tarReg.getxName());
					tarRegBean.setY(tarReg.getyName());
					// 特殊处理T10100
					JSONArray jsonObjArray = JSON.parseArray(filters);
					if (targetCode.equals("T10100") && filters.contains("IS_JOB_TYPE")) {
						for (int i = 0; i < jsonObjArray.size(); i++) {
							JSONObject job = jsonObjArray.getJSONObject(i);
							if (job.getString("group").contains("IS_JOB_TYPE")) {
								jsonObjArray.remove(i);
							}
						}

					}
					// sql语句参数拼装处理
					String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
							jsonObjArray.toJSONString());

					if (!StringUtils.isEmpty(orgCode)) {
						// 按搜索机构处理
						String orgType = jsonObject.getString("orgType");
						switch (roleOrg.length()) {
						// 总公司
						case 1:
							switch (orgType) {
							// 分公司
							case "7":
								// 此处是中支机构
								sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0401, sqlCode, orgCode,
										targetDeptCode, orgType);
								tarRegBean.setSql(sqlCode);
								tarRegBean.setColor(tarReg.getColor());
								dataList.add(tarRegBean);
								// 成功响应处理
								this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
								dataPrintTips(11, dataList);
								break;
							// 中支
							case "6":
								// 此处是四级机构
								sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0402, sqlCode, orgCode,
										targetDeptCode, orgType);
								tarRegBean.setSql(sqlCode);
								tarRegBean.setColor(tarReg.getColor());
								dataList.add(tarRegBean);
								// 成功响应处理
								this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
								dataPrintTips(11, dataList);
								break;
							default:
								// 此处是区机构
								sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0403, sqlCode, orgCode,
										targetDeptCode, orgType);
								tarRegBean.setSql(sqlCode);
								tarRegBean.setColor(tarReg.getColor());
								dataList.add(tarRegBean);
								// 成功响应处理
								this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
								dataPrintTips(11, dataList);
								break;
							}
							break;
						// 分公司
						case 3:
							switch (orgType) {
							// 中支
							case "6":
								// 此处是四级机构
								sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0402, sqlCode, orgCode,
										targetDeptCode, orgType);
								tarRegBean.setSql(sqlCode);
								tarRegBean.setColor(tarReg.getColor());
								dataList.add(tarRegBean);
								// 成功响应处理
								this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
								dataPrintTips(11, dataList);
								break;
							default:
								// 此处是区机构
								sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0403, sqlCode, orgCode,
										targetDeptCode, orgType);
								tarRegBean.setSql(sqlCode);
								tarRegBean.setColor(tarReg.getColor());
								dataList.add(tarRegBean);
								// 成功响应处理
								this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
								dataPrintTips(11, dataList);
								break;
							}
							break;
						// 中支
						case 5:
							switch (orgType) {
							default:
								// 此处是区机构
								sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0403, sqlCode, orgCode,
										targetDeptCode, orgType);
								tarRegBean.setSql(sqlCode);
								tarRegBean.setColor(tarReg.getColor());
								dataList.add(tarRegBean);
								// 成功响应处理
								this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
								dataPrintTips(11, dataList);
								break;
							}
							break;
						}
						if (orgCode.length() == 5) {
							sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
						}
					} else {
						// 按用户机构处理
						sqlCode = sqlFactory.getZtfxSql(SqlConstants.FXGL_03, sqlCode, roleOrg, targetDeptCode);
						if (roleOrg.length() == 5) {
							sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
						}
						tarRegBean.setSql(sqlCode);
						tarRegBean.setColor(tarReg.getColor());
						dataList.add(tarRegBean);
						// 成功响应处理
						this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
						dataPrintTips(11, dataList);
					}
				} else {
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}
			} else {
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {特殊指标区域接口（spec）}12
	 * @作者 MaxBill
	 * @时间 2017年7月19日 下午5:06:45
	 */
	public ResponseBeanDto getSpecData(String json) {
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
				String orgCode = jsonObject.getString("orgCode");// 机构编码
				// 按搜索机构处理
				if (!StringUtils.isEmpty(orgCode)) {
					String orgType = jsonObject.getString("orgType");
					sqlCode = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0101, sqlCode, orgCode, null, orgType);
				} else {
					sqlCode = sqlFactory.getZtfxSql(SqlConstants.FXGL_01, sqlCode, roleOrg, null);
				}
				tarRegBean.setSql(sqlCode);
				tarRegBean.setTitle(tarReg.getGraphTitle());
				tarRegBean.setColor(tarReg.getColor());
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(tarRegBean), responseBeanDto);
				dataPrintTips(12, tarRegBean);
			} else {
				// 数据异常
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {设置默认指标}13
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午2:00:51
	 */
	public ResponseBeanDto setDefTarget(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 用户名
			String username = jsonObject.getString("username");// 用户名
			String subject = jsonObject.getString("subject");// 主题编码
			String level = jsonObject.getString("level");// 主题编码
			String target1 = jsonObject.getString("target1");// 一级指标
			String target2 = jsonObject.getString("target2");// 二级指标
			List<String> target1List = JSON.parseArray(target1, String.class);
			List<String> target2List = JSON.parseArray(target2, String.class);
			int addFlag = 0;
			if (!target1List.isEmpty() && null != target1List && target2List.isEmpty()) {
				addFlag = subjectAnalysisService.saveUserDefTarget(target1List, username, role, subject, level);
			} else if (target1List.isEmpty() && null != target2List && !target2List.isEmpty()) {
				addFlag = subjectAnalysisService.saveUserDefTarget(target2List, username, role, subject, level);
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

	/**
	 * @功能 {区间统计}14
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午2:00:59
	 */
	public ResponseBeanDto tallestCow(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String subject = jsonObject.getString("subject");// 主题编码
			String groupbyDate = jsonObject.getString("groupbyDate");// 分组
			String startTime = jsonObject.getString("startTime");// 开始时间
			String endTime = jsonObject.getString("endTime");// 结束时间
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			JSONArray filters = jsonObject.getJSONArray("filters");
			List<Object> dataList = new ArrayList<Object>();
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP05_REG01");
			// 封装表格头部信息
			List<String> tarRegTabHeadList = subjectAnalysisService.findTarRegTabHeadByReg(temReg.getRegId());
			dataList.add(tarRegTabHeadList);
			// 按主题查询区间统计的指标
			List<TarStatis> tarStatisList = subjectAnalysisService.findTarStatisBySub(subject);
			List<TarRegBean> tarRegBeanList = new ArrayList<TarRegBean>();
			if (null != tarStatisList) {
				for (TarStatis tarStatis : tarStatisList) {
					String targetCode = tarStatis.getTargetCode();
					boolean isHas = commonUtil.isHasGroByTarAndGro(targetCode, groupbyDate);
					if (isHas) {
						TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5, targetCode, temReg.getRegId(),
								null, null, groupbyDate);
						// sql语句参数拼装处理
						String sql = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
								filters.toJSONString());
						// 按搜索机构处理
						if (!StringUtils.isEmpty(orgCode)) {
							String orgType = jsonObject.getString("orgType");
							sql = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0101, sql, orgCode, null, orgType);
							// 区间统计sql替换规则
							sql = sql.replace("{start_time}", "'" + startTime + "'");
							sql = sql.replace("{end_time}", "'" + endTime + "'");
							sql = sql.replace("{org_code}", "'" + orgCode + "'");
						} else {
							sql = sqlFactory.getZtfxSql(SqlConstants.FXGL_01, sql, roleOrg, null); // 区间统计sql替换规则
							sql = sql.replace("{start_time}", "'" + startTime + "'");
							sql = sql.replace("{end_time}", "'" + endTime + "'");
							sql = sql.replace("{org_code}", "'" + roleOrg + "'");
						}
						TarRegBean tarRegBean = new TarRegBean();
						tarRegBean.setName(subjectAnalysisService.findTargetNameByCode(targetCode));
						tarRegBean.setSql(sql);
						tarRegBeanList.add(tarRegBean);
					}
				}
			}
			dataList.add(tarRegBeanList);
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
			dataPrintTips(14, dataList);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {机构对比参数}15
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午2:01:08
	 */
	public ResponseBeanDto orgCompareParam(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String subject = jsonObject.getString("subject");// 主题编码
			String groupbyDate = jsonObject.getString("groupbyDate");// 分组维度
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String tarCode = jsonObject.getString("targetCode");// 机构编码
			Map resultMap = new HashMap();
			List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
			Set<String> targetSet = new HashSet<String>();
			List<String> temps = new ArrayList<>();
			temps.add("TEMP06_REG01");
			List<TarReg> tarRegList = subjectAnalysisService.findMoreTarRegsBySubAndRole(temps, subject, role,
					groupbyDate);
			if (null != tarRegList && !tarRegList.isEmpty()) {
				tarRegList.stream().forEach(tarReg -> {
					String targetCode = tarReg.getTargetCode();
					if (commonUtil.hasRoleOrg(
							Integer.parseInt(
									StringUtil.isNull(tarReg.getRoleOrgType()) ? "0" : tarReg.getRoleOrgType()),
							roleOrg.length())) {
						targetSet.add(targetCode);
					}
				});
				if (null != targetSet) {
					for (String targetCode : targetSet) {
						TargetBean targetBean = new TargetBean();
						Target target = subjectAnalysisService.findTargetByCode(targetCode);
						targetBean.setName(target.getTargetName());
						targetBean.setCode(target.getTargetCode());
						targetBeanList.add(targetBean);

					}
					Collections.sort(targetBeanList, new Comparator<TargetBean>() {
						public int compare(TargetBean arg0, TargetBean arg1) {
							return arg0.getCode().compareTo(arg1.getCode());
						}
					});
				}
				resultMap.put("targets", targetBeanList);
				if (targetBeanList.size() > 0 && null != targetBeanList) {
					String tarDept = "";
					if (StringUtils.isEmpty(tarCode)) {
						String defaultTar = targetBeanList.get(0).getCode();
						tarDept = subjectAnalysisService.findTargetByCode(defaultTar).getDeptCode();
					} else {
						tarDept = subjectAnalysisService.findTargetByCode(tarCode).getDeptCode();
					}
					List<OrgBean> orgList = null;
					if (StringUtils.isEmpty(orgCode)) {
						switch (roleOrg.length()) {
						case 1:
							// 分公司
							orgList = subjectAnalysisService.getOrgList(1, roleOrg, null);
							break;
						case 3:
							// 中支
							orgList = subjectAnalysisService.getOrgList(2, roleOrg, null);
							break;
						case 5:
							//
							orgList = subjectAnalysisService.getOrgList(3, roleOrg, tarDept);
							break;
						default:
							// 成本中心
							orgList = subjectAnalysisService.getOrgList(42, roleOrg, null);
							break;
						}
					} else {
						String orgType = jsonObject.getString("orgType");
						switch (orgType) {
						case "7":
							// 分公司
							orgList = subjectAnalysisService.getOrgList(2, orgCode, null);
							break;
						case "6":
							// 中支
							orgList = subjectAnalysisService.getOrgList(3, orgCode, tarDept);
							break;
						case "501":
							// 营销服务部
							orgList = subjectAnalysisService.getOrgList(41, orgCode, null);
							break;
						case "502":
							// 成本中心
							orgList = subjectAnalysisService.getOrgList(42, orgCode, null);
							break;
						}
					}
					resultMap.put("org", orgList);
				}
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
				dataPrintTips(15, resultMap);
			} else {
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {机构对比}16
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午2:01:16
	 */
	public ResponseBeanDto orgCompare(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String subject = jsonObject.getString("subject");// 是否自定义板块
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String orgType = jsonObject.getString("orgType");
			String target = jsonObject.getString("target");// 指标编码
			String filters = jsonObject.getString("filters");// 筛选条件
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			List<String> orgList = JSON.parseArray(jsonObject.getString("orgs"), String.class);
			// 查询区域信息
			Target targetBean = subjectAnalysisService.findTargetByCode(target);
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP06_REG01");
			TarReg tarReg = subjectAnalysisService.findTarRegByRegAndSubAndTar(temReg.getRegId(), subject, target);
			TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(2, target, temReg.getRegId(), "DATE_GROUP",
					groupbyDate, null);
			List<DataBean> dataBeanList = new ArrayList<DataBean>();
			// 封装机构一数据
			DataBean dataBean01 = new DataBean();
			dataBean01.setStacking("");
			dataBean01.setxAxis("");
			TarRegBean tarRegBean01 = new TarRegBean();
			tarRegBean01.setCode(target);
			tarRegBean01.setName(subjectAnalysisService.findTargetNameByCode(target));
			tarRegBean01.setType(tarReg.getGraphType());
			tarRegBean01.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
			tarRegBean01.setColor("");
			tarRegBean01.setStack("");
			// sql语句参数拼装处理
			String sql01 = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), target, tarRegSql.getTrsId(), filters);
			// sql语句正则替换处理
			sql01 = sqlFactory.getFxglJgdbSql(sql01, roleOrg, orgList.get(0), targetBean.getDeptCode(), orgType);
			tarRegBean01.setSql(sql01);
			dataBean01.setSeries(tarRegBean01);
			dataBeanList.add(dataBean01);
			// 封装机构二数据
			DataBean dataBean02 = new DataBean();
			dataBean02.setStacking("");
			dataBean02.setxAxis("");
			TarRegBean tarRegBean02 = new TarRegBean();
			tarRegBean02.setCode(target);
			tarRegBean02.setName(subjectAnalysisService.findTargetNameByCode(target));
			tarRegBean02.setType(tarReg.getGraphType());
			tarRegBean02.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
			tarRegBean02.setColor("");
			tarRegBean02.setStack("");
			String sql02 = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), target, tarRegSql.getTrsId(), filters);
			sql02 = sqlFactory.getFxglJgdbSql(sql02, roleOrg, orgList.get(1), targetBean.getDeptCode(), orgType);
			tarRegBean02.setSql(sql02);
			dataBean02.setSeries(tarRegBean02);
			dataBeanList.add(dataBean02);
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(dataBeanList), responseBeanDto);
			dataPrintTips(16, dataBeanList);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {导出清单}17
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午2:01:23
	 */
	public ResponseBeanDto exportBill(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			Map resultMap = new HashMap<>();
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String subject = jsonObject.getString("subject");// 主题编码
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			JSONArray filters = jsonObject.getJSONArray("filters");
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP07_REG01");
			TarReg tarReg = subjectAnalysisService.findTarRegByRegAndSub(temReg.getRegId(), subject);
			TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(4, tarReg.getTargetCode(), temReg.getRegId(),
					null, groupbyDate, null);
			resultMap.put("head", "");
			if (null != tarRegSql) {
				// sql语句参数拼装处理
				String sql = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), tarReg.getTargetCode(),
						tarRegSql.getTrsId(), filters.toJSONString());
				// 按搜索机构处理
				if (!StringUtils.isEmpty(orgCode)) {
					String orgType = jsonObject.getString("orgType");
					sql = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0101, sql, orgCode, null, orgType);
				} else {
					sql = sqlFactory.getZtfxSql(SqlConstants.FXGL_01, sql, roleOrg, null);
				}
				resultMap.put("sql", sql);
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
				dataPrintTips(17, resultMap);
			} else {
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @功能 {清单下载}18
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午2:02:26
	 */
	public ResponseBeanDto downloadBill(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			Map resultMap = new HashMap<>();
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String subject = jsonObject.getString("subject");// 主题编码
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			JSONArray filters = jsonObject.getJSONArray("filters");
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP07_REG01");
			TarReg tarReg = subjectAnalysisService.findTarRegByRegAndSub(temReg.getRegId(), subject);
			TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(4, tarReg.getTargetCode(), temReg.getRegId(),
					null, groupbyDate, null);
			resultMap.put("head", "");
			if (null != tarRegSql) {
				// sql语句参数拼装处理
				String sql = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), tarReg.getTargetCode(),
						tarRegSql.getTrsId(), filters.toJSONString());
				// 按搜索机构处理
				if (!StringUtils.isEmpty(orgCode)) {
					String orgType = jsonObject.getString("orgType");
					sql = sqlFactory.getZtfxSqlByOrg(SqlConstants.FXGL_ORG0101, sql, orgCode, null, orgType);
				} else {
					sql = sqlFactory.getZtfxSql(SqlConstants.FXGL_01, sql, roleOrg, null);
				}
				resultMap.put("sql", sql);
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
				dataPrintTips(18, resultMap);
			} else {
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
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
		log.info("------------------------ZTFX(" + type + ")-S------------------------");
		log.info(DateUtil.formatDateTime(new Date()) + ": " + JSON.toJSONString(data));
		log.info("------------------------ZTFX(" + type + ")-E------------------------");
	}

}
