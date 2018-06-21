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
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.bean.DataPageBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.bean.WarnBean;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.UserSetWarn;
import com.huatai.web.model.WarnResult;
import com.huatai.web.service.UserSetWarnService;
import com.huatai.web.sql.SqlFactory;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.WarningService;
import com.huatai.web.utils.DateUtil;

/**
 * @功能 {工具服务}
 * @作者 MaxBill
 * @时间 2017年7月24日 下午5:43:47
 */
public class GjfwServiceImpl implements GjfwService.Iface {

	private SqlFactory sqlFactory;

	public GjfwServiceImpl() {
		sqlFactory = SpringUtil.getBean(SqlFactory.class);
		;
	}

	/**
	 * @功能 {我的预警信息列表}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 上午9:52:03
	 */
	public ResponseBeanDto myListWarning(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String user = jsonObject.getString("username");
			String roleOrg = jsonObject.getString("roleOrg");
			String target = jsonObject.getString("target");
			String status = jsonObject.getString("status");
			Integer start = jsonObject.getInteger("curPage");
			Integer limit = jsonObject.getInteger("pageSize");
			// 封装查询模型
			UserSetWarn userSetWarn = new UserSetWarn();
			userSetWarn.setTargetName(target);
			userSetWarn.setOrgCode(roleOrg);
			userSetWarn.setRoleCode(role);
			userSetWarn.setUsername(user);
			userSetWarn.setWarnStatus(status);
			// 封装分页参数
			Pager<UserSetWarn> pager = new Pager<UserSetWarn>(start, limit);
			DataPageBean dataPage = warningService.myWarnings(pager, userSetWarn);
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(dataPage));
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
	 * @功能 {新增預警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月28日 下午3:02:29
	 */
	public ResponseBeanDto saveMyWarning(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String user = jsonObject.getString("username");
			String target = jsonObject.getString("target");
			String role = jsonObject.getString("role");
			String roleOrg = jsonObject.getString("roleOrg");
			String type = jsonObject.getString("type");
			JSONObject termObj = jsonObject.getJSONObject("term");
			String warnCode = termObj.getString("code");
			Float minVal = termObj.getFloat("min");
			Float maxVal = termObj.getFloat("max");
			UserSetWarn userSetWarn = new UserSetWarn();
			userSetWarn.setTargetCode(target);
			userSetWarn.setWarnSettingType("1");
			userSetWarn.setUsername(user);
			userSetWarn.setRoleCode(role);
			userSetWarn.setWarnCode(warnCode);
			userSetWarn.setMinVal(minVal);
			userSetWarn.setMaxVal(maxVal);
			userSetWarn.setAlertType(type);
			int orgLen = roleOrg.length();
			userSetWarn.setOrgType(
					orgLen == 3 ? "7" : (orgLen == 5 ? "6" : (orgLen == 7 ? "5" : (orgLen == 1 ? "8" : ""))));
			userSetWarn.setOrgCode(roleOrg);
			userSetWarn.setOrgName(warningService.findOrgNameByOrgId(roleOrg));
			userSetWarn.setWarnStatus("1");
			userSetWarn.setOpType("A");
			userSetWarn.setCreateTime(new Date());
			userSetWarn.setModifyTime(new Date());
			int addFlag = warningService.saveWarning(userSetWarn);
			// 成功响应处理
			if (addFlag == 1) {
				responseBeanDto.setJson("{\"msg\":\"添加成功\"}");
			} else {
				responseBeanDto.setJson("{\"msg\":\"添加失败\"}");
			}
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setErrCode("200");
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
	 * @功能 {查找预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 上午9:51:57
	 */
	public ResponseBeanDto selectWarning(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Integer id = jsonObject.getInteger("id");
			String role = jsonObject.getString("role");
			UserSetWarn userSetWarn = new UserSetWarn();
			if (id != null) {
				userSetWarn = warningService.findWarning(id);
			}
			WarnBean warnBean = new WarnBean();
			if (null != userSetWarn) {
				warnBean.setId(userSetWarn.getBsId());
				// 预警状态：0.已关闭，1.运行中
				warnBean.setStatus(userSetWarn.getWarnStatus());
				// 提醒方式：1.消息盒子，2.持续监控，3.消息盒子+持续监控
				warnBean.setType(userSetWarn.getAlertType());
				warnBean.setOrg(userSetWarn.getOrgCode());
				warnBean.setTargetCode(userSetWarn.getTargetCode());
				// 封装指标数据
				List<TargetBean> targetList = warningService.findTargetList(userSetWarn.getTargetCode(), role);
				warnBean.setTarget(targetList);
				List<Map<String, Object>> termList = new ArrayList<>();
				Map<String, Object> map = new HashMap<>();
				map.put("code", userSetWarn.getWarnCode());
				map.put("min", userSetWarn.getMinVal());
				map.put("max", userSetWarn.getMaxVal());
				termList.add(map);
				warnBean.setTerm(termList);
				responseBeanDto.setJson(JSON.toJSONString(warnBean));
			} else {
				responseBeanDto.setJson("");
			}
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

	/**
	 * @功能 {删除预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 上午9:52:11
	 */
	public ResponseBeanDto deleteWarning(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			// String user = jsonObject.getString("username");
			Integer id = jsonObject.getInteger("id");
			int delFlag = warningService.deleteWarning(id);
			// 成功响应处理
			if (delFlag == 1) {
				responseBeanDto.setJson("{\"msg\":\"删除成功\"}");
			} else {
				responseBeanDto.setJson("{\"msg\":\"删除失败\"}");
			}
			// 成功响应处理
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setErrCode("200");
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
	 * @功能 {更新预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 上午9:52:18
	 */
	public ResponseBeanDto updateWarning(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Integer id = jsonObject.getInteger("id");
			String user = jsonObject.getString("username");
			String role = jsonObject.getString("role");
			String roleOrg = jsonObject.getString("roleOrg");
			String type = jsonObject.getString("type");
			String target = jsonObject.getString("target");
			JSONObject termObj = jsonObject.getJSONObject("term");
			String warnCode = termObj.getString("code");
			Float minVal = termObj.getFloat("min");
			Float maxVal = termObj.getFloat("max");
			UserSetWarn userSetWarn = warningService.findWarning(id);
			userSetWarn.setTargetCode(target);
			userSetWarn.setUsername(user);
			userSetWarn.setRoleCode(role);
			userSetWarn.setAlertType(type);
			userSetWarn.setTargetCode(target);
			userSetWarn.setWarnCode(warnCode);
			userSetWarn.setMinVal(minVal);
			userSetWarn.setMaxVal(maxVal);
			int orgLen = roleOrg.length();
			userSetWarn.setOrgType(
					orgLen == 3 ? "7" : (orgLen == 5 ? "6" : (orgLen == 7 ? "5" : (orgLen == 1 ? "8" : ""))));
			userSetWarn.setOrgName(warningService.findOrgNameByOrgId(roleOrg));
			userSetWarn.setOpType("U");
			userSetWarn.setModifyTime(new Date());
			int addFlag = warningService.updateWarning(userSetWarn);
			// 成功响应处理
			if (addFlag == 1) {
				responseBeanDto.setJson("{\"msg\":\"修改成功\"}");
			} else {
				responseBeanDto.setJson("{\"msg\":\"修改失败\"}");
			}
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setErrCode("200");
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
	 * @功能 {更新预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 上午9:52:18
	 */
	public ResponseBeanDto switchWarning(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			// String user = jsonObject.getString("username");
			Integer id = jsonObject.getInteger("id");
			String status = jsonObject.getString("status");
			UserSetWarn userSetWarn = warningService.findWarning(id);
			if (null != userSetWarn) {
				String statusInfo = "";
				if (status.equals("0")) {
					statusInfo = "关闭";
				} else {
					statusInfo = "开启";
				}
				userSetWarn.setWarnStatus(status);
				int updFlag = warningService.updateWarning(userSetWarn);
				// 成功响应处理
				if (updFlag == 1) {
					responseBeanDto.setJson("{\"msg\":\"" + statusInfo + "预警成功\"}");
				} else {
					responseBeanDto.setJson("{\"msg\":\"" + statusInfo + "预警失败\"}");
				}
			} else {
				responseBeanDto.setJson("");
			}
			// 成功响应处理
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setErrCode("200");
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
	 * @功能 {预警结果接口}
	 * @作者 MaxBill
	 * @时间 2017年7月27日 下午6:00:16
	 */
	public ResponseBeanDto resultWarning(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String user = jsonObject.getString("username");
			String targetName = jsonObject.getString("target");
			Integer start = jsonObject.getInteger("curPage");
			Integer limit = jsonObject.getInteger("pageSize");
			// 封装查询模型
			WarnResult warnResult = new WarnResult();
			warnResult.setRoleCode(role);
			warnResult.setUsername(user);
			warnResult.setTargetName(targetName);
			// 封装分页参数
			Pager<WarnResult> pager = new Pager<WarnResult>(start, limit);
			DataPageBean dataPage = warningService.findWarnResult(pager, warnResult);
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(dataPage));
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
	 * @功能 {预警通知接口}
	 * @作者 MaxBill
	 * @时间 2017年7月27日 下午6:00:20
	 */
	public ResponseBeanDto noticeWarning(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String user = jsonObject.getString("username");
			String role = jsonObject.getString("role");
			Integer start = jsonObject.getInteger("curPage");
			Integer limit = jsonObject.getInteger("pageSize");
			// 封装分页参数
			Pager<WarnResult> pager = new Pager<WarnResult>(start, limit);
			DataPageBean dataPage = warningService.findWarnMsg(pager, user, role);
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(dataPage));
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setErrCode("200");
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
	 * @功能 {标记阅读了预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月28日 下午3:34:21
	 */
	public ResponseBeanDto readMyWarning(String json) throws TException {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			UserSetWarnService userSetWarnService = SpringUtil.getBean(UserSetWarnService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Integer id = jsonObject.getInteger("id");
			int updFlag = warningService.readWarnResult(id);
			// 成功响应处理
			if (updFlag == 1) {
				// 更新我的预警列表状态
				WarnResult warnResult = warningService.selectWarnResultByWrId(id);
				userSetWarnService.updateStatus("0", warnResult.getBsId().toString());
				userSetWarnService.deleteWarnById(warnResult.getBsId());
				responseBeanDto.setJson("{\"msg\":\"标记成功\"}");
			} else {
				responseBeanDto.setJson("{\"msg\":\"标记失败\"}");
			}
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setErrCode("200");
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
	 * 更新预警信息
	 */
	@Override
	public ResponseBeanDto updateWarnInfo(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			UserSetWarnService userSetWarnService = SpringUtil.getBean(UserSetWarnService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String roleOrg = jsonObject.getString("roleOrg");
			String username = jsonObject.getString("username");
			// 更新 预警结果 中预警是否超时
			List<WarnResult> warns = warningService.selectByUserRoleBsId(username, role, null);
			if (!warns.isEmpty() && null != warns) {
				for (WarnResult warnResult : warns) {
					int num = DateUtil.getDateNum(warnResult.getFirstWarnTime(), new Date());
					String alertType = userSetWarnService.findWarnById(warnResult.getBsId()).getAlertType();
					if (("1".equals(alertType) && num >= 3) || ("2".equals(alertType) && num >= 30)) {
						warnResult.setIsRead("1");
						warnResult.setReadTime(new Date());
						userSetWarnService.updateStatus("0", warnResult.getBsId().toString());
						userSetWarnService.deleteWarnById(warnResult.getBsId());
					}
					warningService.updateWarnResult(warnResult);
				}
			}
			// 获取状态进行中的预警 和管理员设置的预警 并且 未点知道了
			List<UserSetWarn> uswList = warningService.selectNoResOnWarn(role, roleOrg);
			if (null != uswList && !uswList.isEmpty()) {
				List<Map<String, Object>> ls = new ArrayList<>();
				for (UserSetWarn usw : uswList) {
					Map<String, Object> map = new HashMap<>();
					TarInitSql tarInitSql = warningService.getSqlCodeByTarget(usw.getTargetCode(), "03");
					if (tarInitSql != null) {
						String sqlCode = sqlFactory.getGjfwSql(tarInitSql.getSqlCode(), roleOrg);
						map.put("target", usw.getTargetCode());
						map.put("sqlCode", sqlCode);
						map.put("username", username);
						map.put("roleOrg", roleOrg);
						map.put("role", role);
						map.put("bsId", usw.getBsId());
						map.put("warnCode", usw.getWarnCode());
						map.put("min", usw.getMinVal());
						map.put("max", usw.getMaxVal());
						ls.add(map);
					}
				}
				responseBeanDto.setJson(JSON.toJSONString(ls));
			} 
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setErrCode("200");
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

	@Override
	public ResponseBeanDto saveWarnInfo(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			WarningService warningService = SpringUtil.getBean(WarningService.class);
			UserSetWarnService userSetWarnService = SpringUtil.getBean(UserSetWarnService.class);
			JSONObject jsonObject = JSON.parseObject(json);
			String target = jsonObject.getString("target");
			Float actVal = (float) (jsonObject.getFloat("actVal") == null ? 0 : jsonObject.getFloat("actVal"));
			String showVal = jsonObject.getString("showVal");
			String username = jsonObject.getString("username");
			String role = jsonObject.getString("role");
			Integer bsId = jsonObject.getInteger("bsId");
			String warnCode = jsonObject.getString("warnCode");
			Float min = jsonObject.getFloat("min");
			Float max = jsonObject.getFloat("max");
			boolean result = false;
			switch (warnCode) {
			case "1":
				if (actVal > min) {
					result = true;
				}
				break;
			case "2":
				if (actVal < max) {
					result = true;
				}
				break;
			case "3":
				if (actVal >= min && actVal <= max) {
					result = true;
				}
				break;
			}
			if (result) {
				List<WarnResult> warns = warningService.selectByUserRoleBsId(username, role, bsId);
				WarnResult warnResult = new WarnResult();
				if (warns.size() == 0) {
					warnResult.setBsId(bsId);
					warnResult.setUsername(username);
					warnResult.setRoleCode(role);
					warnResult.setWarnVal(actVal);
					warnResult.setFirstWarnTime(new Date());
					warnResult.setWarnTime(new Date());
					warnResult.setIsRead("0");
					warnResult.setReadTime(null);
					warningService.insertWarnResult(warnResult);
				} else {
					warnResult = warns.get(0);
					if ("0".equals(warnResult.getIsRead())) {
						warnResult.setWarnVal(actVal);
						int num = DateUtil.getDateNum(warnResult.getFirstWarnTime(), new Date());
						UserSetWarn usw = userSetWarnService.findWarnById(warnResult.getBsId());
						String alertType = usw.getAlertType();
						if ("1".equals(alertType) && num >= 3) {
							warnResult.setIsRead("1");
							warnResult.setReadTime(new Date());
							// 更新我的预警列表状态
							userSetWarnService.updateStatus("0", warnResult.getBsId().toString());
						} else if ("2".equals(alertType) && num >= 30) {
							warnResult.setIsRead("1");
							warnResult.setReadTime(new Date());
							// 更新我的预警列表状态
							userSetWarnService.updateStatus("0", warnResult.getBsId().toString());
						}
						warnResult.setWarnTime(new Date());
						warningService.updateWarnResult(warnResult);
					}
				}
			}
			responseBeanDto.setJson("{\"msg\":\"预警信息更新成功\"}");
			responseBeanDto.setErrMsgs("");
			responseBeanDto.setErrCode("200");
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
