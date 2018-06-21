package com.huatai.web.thrift.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.bean.GroDimDetailBean;
import com.huatai.web.bean.TarRegBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.sql.SqlFactory;
import com.huatai.web.sql.SqlParameter;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.CommonService;
import com.huatai.web.thrift.service.SubjectAnalysisService;
import com.huatai.web.utils.DateUtil;

public class AppZtfxServiceImpl implements AppZtfxService.Iface {

	Logger log = Logger.getLogger(AppZtfxServiceImpl.class);
	private static final String TEMP03_REG02 = "TEMP03_REG02";// 一级指标区域
	private static final String TEMP03_REG03 = "TEMP03_REG03";// 二级指标区域
	private static final String TEMP03_REG06 = "TEMP03_REG06";// 特殊区域
	private static final String TEMP12_REG01 = "TEMP12_REG01";// 虚拟区域
	private SqlFactory sqlFactory;
	private SqlParameter sqlParameter;
	private CommonUtil commonUtil;

	public AppZtfxServiceImpl() {
		sqlFactory = SpringUtil.getBean(SqlFactory.class);
		sqlParameter = SpringUtil.getBean(SqlParameter.class);
		commonUtil = SpringUtil.getBean(CommonUtil.class);
	}

	/**
	 * 1、主题菜单
	 */
	public ResponseBeanDto getMenu(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			Map ruseltMap = new HashMap();
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			List<TargetBean> realtime = subjectAnalysisService.findSubjectByTypeAndRole("0", role, "1");
			List<TargetBean> subjects = subjectAnalysisService.findSubjectByTypeAndRole("0", role, "0");
			List<TargetBean> blocks = subjectAnalysisService.findSubjectByTypeAndRole("1", role, "0");
			ruseltMap.put("realTimes", realtime);
			ruseltMap.put("subjects", subjects);
			ruseltMap.put("blocks", blocks);
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(ruseltMap), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

	/**
	 * 2、指标页面接口
	 */
	public ResponseBeanDto getPage(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap<>();
		SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
		SqlFactory sqlFactory = SpringUtil.getBean(SqlFactory.class);
		try {
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String subject = jsonObject.getString("subject");
			String userName = jsonObject.getString("username"); // 用户名
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String roleDept = jsonObject.getString("roleDept");// 角色部门
			String role = jsonObject.getString("role");
			// 查询一级指标
			TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP03_REG02");
			TemReg temRegSp = subjectAnalysisService.findRegionByCode(TEMP03_REG06);
			List<TargetBean> targetBeanList = subjectAnalysisService.findTargetByRoleAndSubAndRegAndGraph(role, "1",
					temReg.getRegId(), subject);
			List<TargetBean> oneTargetBean = new ArrayList<TargetBean>();// 返回的一級指标
			TemReg temRegApp = subjectAnalysisService.findRegionByCode(TEMP12_REG01);
			if (null != targetBeanList) {
				for (TargetBean target : targetBeanList) {
					TargetBean targetBeans = new TargetBean();
					targetBeans.setName(target.getName());
					targetBeans.setCode(target.getCode());
					boolean isSetDefault = subjectAnalysisService.isdefaultTarget(userName, target.getCode(), subject,
							role);
					targetBeans.setChecked(isSetDefault);
					TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5, target.getCode(),
							temRegApp.getRegId(), null, null, "MONTH");
					if (null != tarRegSql) {
						String sqlCode = sqlFactory.getAppFxglSql(tarRegSql.getSqlCode(), roleDept, roleOrg);
						targetBeans.setSql(sqlCode);
					}
					oneTargetBean.add(targetBeans);
				}
			}
			// 查询二级指标分组
			List<Target> targetTwoList = subjectAnalysisService.findTargetByTypeAndRoleAndPlate("2", role, subject);
			List twoTargetList = new ArrayList();// 返回的二级指标组以及指标
			List specialTaregt = new ArrayList();// 返回的特殊指标
			if (null != targetTwoList) {
				for (Target target : targetTwoList) {
					TargetBean targetBeans = new TargetBean();
					targetBeans.setName(target.getTargetName());
					targetBeans.setCode(target.getTargetCode());
					// 查询二级指标分组下的二级指标
					List<TargetBean> twoTargets = subjectAnalysisService.findTargetByRoleAndSubAndRegAndGraph(role, "3",
							null, target.getTargetCode());
					if (null != twoTargets) {
						List targetTwoLists = new ArrayList();
						for (TargetBean twoTarget : twoTargets) {
							TargetBean targetb = new TargetBean();
							targetb.setName(twoTarget.getName());
							targetb.setCode(twoTarget.getCode());
							TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5, twoTarget.getCode(),
									temRegApp.getRegId(), null, null, "MONTH");
							if (null != tarRegSql) {
								String sqlCode = sqlFactory.getAppFxglSql(tarRegSql.getSqlCode(), roleDept, roleOrg);
								targetb.setSql(sqlCode);
							}
							boolean isSetDefault = subjectAnalysisService.isdefaultTarget(userName, twoTarget.getCode(),
									subject, role);
							targetb.setChecked(isSetDefault);
							targetTwoLists.add(targetb);
						}
						targetBeans.setTarget(targetTwoLists);
					}

					// 查询二级指标分组下的二级指标(特殊指标)

					List<TargetBean> defaultTwoTargets = subjectAnalysisService
							.findTargetByRoleAndSubAndRegAndGraphType(role, "3", null, target.getTargetCode());
					if (null != defaultTwoTargets) {
						for (TargetBean targets : defaultTwoTargets) {
							TargetBean targetb = new TargetBean();
							targetb.setName(targets.getName());
							targetb.setCode(targets.getCode());
							TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5, targets.getCode(),
									temRegSp.getRegId(), null, null, "MONTH");
							if (null != tarRegSql) {
								String sqlCode = sqlFactory.getAppFxglSql(tarRegSql.getSqlCode(), roleDept, roleOrg);
								targetb.setSql(sqlCode);
							}
							specialTaregt.add(targetb);// 返回二级指标组下的特殊指标
						}
					}
					twoTargetList.add(targetBeans);
				}

			}
			// 查询特殊指标
			List<TargetBean> defaultTargetBean = subjectAnalysisService.findTargetByRoleAndSubAndRegAndGraphType(role,
					"1", temRegSp.getRegId(), subject);// 一级特殊指标
			if (null != defaultTargetBean) {
				for (TargetBean targetBeans : defaultTargetBean) {
					TargetBean targetb = new TargetBean();
					TarReg taReg  =subjectAnalysisService.findOrgByTargtAndRen(targetBeans.getCode(), subject, temRegSp.getRegId());
					// 按最低权限类型过滤
					String roleOrgType = taReg.getRoleOrgType();
					if (commonUtil.hasRoleOrg(Integer.parseInt(roleOrgType), roleOrg.length())) {
						targetb.setName(targetBeans.getName());
						targetb.setCode(targetBeans.getCode());
						TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5, targetBeans.getCode(),
								temRegSp.getRegId(), null, null, "MONTH");
						if (null != tarRegSql) {
							String sqlCode = sqlFactory.getAppFxglSql(tarRegSql.getSqlCode(), roleDept, roleOrg);
							targetb.setSql(sqlCode);
						}
						specialTaregt.add(targetb);
					}
					
				}
			}
			// 查询时间指标
			List<GroDimDetailBean> timeReqs = new ArrayList<GroDimDetailBean>();
			CommonService commonService = SpringUtil.getBean(CommonService.class);
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
			resultMap.put("timeReqs", timeReqs);
			resultMap.put("oneTargetBean", oneTargetBean);
			resultMap.put("twoTargetList", twoTargetList);
			resultMap.put("specialTaregt", specialTaregt);
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

	/**
	 * 2.1、一级指标趋势图接口
	 */
	public ResponseBeanDto getMainData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String roleDept = jsonObject.getString("roleDept");// 角色部门
			String groupbyDate = jsonObject.getString("groupbyDate"); // 时间维度
			String targetCode = jsonObject.getString("targetCode");// 选中的指标数据
			// 查询区域信息
			List<Object> resultList = new ArrayList<>();
			TemReg temReg = subjectAnalysisService.findRegionByCode(TEMP03_REG02);
			if (StringUtils.isNotEmpty(targetCode)) {
				TarRegBean tarRegBean = new TarRegBean();
				TarReg tarReg = subjectAnalysisService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
				TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(2, targetCode, temReg.getRegId(),
						"DATE_GROUP", groupbyDate, null);
				if (null != tarRegSql) {
					String sqlCode = sqlFactory.getAppFxglSql(tarRegSql.getSqlCode(), roleDept, roleOrg);
					tarRegBean.setSql(sqlCode);
				} else {
					tarRegBean.setSql(null);
				}
				tarRegBean.setCode(targetCode);
				tarRegBean.setName(subjectAnalysisService.findTargetNameByCode(targetCode));
				tarRegBean.setType(tarReg.getGraphType());
				tarRegBean.setTitle(tarReg.getGraphTitle());
				tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
				tarRegBean.setStack("");
				tarRegBean.setColor(tarReg.getColor());

				resultList.add(tarRegBean);
			}

			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(resultList), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

	/**
	 * 2.2、二级指标趋势图接口
	 */
	public ResponseBeanDto getSubData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String roleDept = jsonObject.getString("roleDept");// 角色部门
			String subject = jsonObject.getString("subject");// 当前主题
			String level = jsonObject.getString("level");// 二级指标分组
			String groupbyDate = jsonObject.getString("groupbyDate"); // 时间维度
			String targetCode = jsonObject.getString("targetCode");// 选中的指标数据
			TemReg temReg = subjectAnalysisService.findRegionByCode(TEMP03_REG03);
			// 查询区域信息
			List<Object> resultList = new ArrayList<>();
			if (StringUtils.isNotEmpty(targetCode)) {
				TarRegBean tarRegBean = new TarRegBean();
				TarReg tarReg = subjectAnalysisService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
				TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(2, targetCode, temReg.getRegId(),
						"DATE_GROUP", groupbyDate, null);
				if (null != tarRegSql) {
					String sqlCode = sqlFactory.getAppFxglSql(tarRegSql.getSqlCode(), roleDept, roleOrg);
					tarRegBean.setSql(sqlCode);
				} else {
					tarRegBean.setSql(null);
				}
				tarRegBean.setCode(targetCode);
				tarRegBean.setName(subjectAnalysisService.findTargetNameByCode(targetCode));
				tarRegBean.setType(tarReg.getGraphType());
				tarRegBean.setTitle(tarReg.getGraphTitle());
				tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
				tarRegBean.setStack("");
				tarRegBean.setColor(tarReg.getColor());
				resultList.add(tarRegBean);

			}

			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(resultList), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
		return responseBeanDto;
	}

	/**
	 * 2.3、特殊指标趋势图接口
	 */
	public ResponseBeanDto getSpecData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 当前登录人角色
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String roleDept = jsonObject.getString("roleDept");// 角色部门
			String groupbyDate = jsonObject.getString("groupbyDate"); // 时间维度
			String targetCode = jsonObject.getString("target");// 选中的指标数据
			TemReg temReg = subjectAnalysisService.findRegionByCode(TEMP03_REG06);
			List<Object> resultList = new ArrayList<>();
			if (StringUtils.isNotEmpty(targetCode)) {
				TarRegBean tarRegBean = new TarRegBean();
				TarReg tarReg = subjectAnalysisService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
				TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5, targetCode, tarReg.getRegId(), null, null,
						groupbyDate);
				if (null != tarRegSql) {
					String sqlCode = sqlFactory.getAppFxglSql(tarRegSql.getSqlCode(), roleDept, roleOrg);
					tarRegBean.setSql(sqlCode);
				} else {
					tarRegBean.setSql(null);
				}
				tarRegBean.setCode(targetCode);
				tarRegBean.setName(subjectAnalysisService.findTargetNameByCode(targetCode));
				tarRegBean.setType(tarReg.getGraphType());
				tarRegBean.setTitle(tarReg.getGraphTitle());
				tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
				tarRegBean.setStack("");
				tarRegBean.setColor(tarReg.getColor());
				resultList.add(tarRegBean);
			}

			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(resultList), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
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
		responseBeanDto.setErrMsgs("");
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
		responseBeanDto.setErrCode("500");
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
	 * @Describtion 获得一级二级指标
	 * @return
	 */
	public static List<TargetBean> getTarList(String tempType, String subject, String role, String dept, String org) {
		SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
		SqlFactory sqlFactory = SpringUtil.getBean(SqlFactory.class);
		TemReg temReg = subjectAnalysisService.findRegionByCode(tempType);
		// 查询一级指标区域信息
		List<TargetBean> targetList = subjectAnalysisService.findTargetByRoleAndSubAndRegWithTarRegBack(role, subject,
				temReg.getRegId());
		// 根据PC端判断是否默认选中
		for (TargetBean targetBean : targetList) {
			String targetCode = targetBean.getCode();
			boolean isSetDefault = subjectAnalysisService.isTarSetDefault("TEMP03", subject, targetCode, role, "D");
			targetBean.setChecked(isSetDefault);
			TemReg temRegApp = subjectAnalysisService.findRegionByCode(TEMP12_REG01);
			TarRegSql tarRegSql = subjectAnalysisService.findTarRegSql(5, targetCode, temRegApp.getRegId(), null, null,
					"MONTH");
			if (TEMP03_REG03.equals(tempType)) {
				Target pTar = subjectAnalysisService.findPTarInfoByTar(targetCode);
				targetBean.setPcode(pTar.getTargetCode());
				targetBean.setPname(pTar.getTargetName());
			}
			if (null != tarRegSql) {
				String sqlCode = sqlFactory.getAppFxglSql(tarRegSql.getSqlCode(), dept, org);
				targetBean.setSql(sqlCode);
			}

		}
		return targetList;
	}

	@Override
	public ResponseBeanDto getRankData(String json) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * 实时指标
	 */
	@Override
	public ResponseBeanDto getRealtimePageData(String json) throws TException {
		return null;
	}

	/**
	 * 
	 * 实时指标趋势图
	 */
	@Override
	public ResponseBeanDto getRealtimeData(String json) throws TException {
		// TODO Auto-generated method stub
		return null;
	}
}
