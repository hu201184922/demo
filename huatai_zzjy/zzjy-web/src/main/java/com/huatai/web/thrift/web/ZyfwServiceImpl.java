package com.huatai.web.thrift.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.bean.DataBean;
import com.huatai.web.bean.GroDimDetailBean;
import com.huatai.web.bean.NoticeBean;
import com.huatai.web.bean.TarRegBean;
import com.huatai.web.model.CoreTarget;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.JyfxTarget;
import com.huatai.web.model.Notice;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.sql.SqlConstants;
import com.huatai.web.sql.SqlFactory;
import com.huatai.web.sql.SqlParameter;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.CommonService;
import com.huatai.web.thrift.service.IndexService;
import com.huatai.web.utils.DateUtil;

/**
 * @功能 {主页服务}
 * @作者 MaxBill
 * @时间 2017年7月25日 下午5:24:55
 */
public class ZyfwServiceImpl implements ZyfwService.Iface {

	Logger log = Logger.getLogger(ZyfwServiceImpl.class);

	private SqlFactory sqlFactory;
	private CommonUtil commonUtil;
	private SqlParameter sqlParameter;
	private Map deptMap;

	public ZyfwServiceImpl() {
		deptMap = new HashMap();
		deptMap.put("trade", "130107");
		deptMap.put("premium", "130105");
		deptMap.put("org", "130101");
		deptMap.put("train", "101402");
		sqlFactory = SpringUtil.getBean(SqlFactory.class);
		commonUtil = SpringUtil.getBean(CommonUtil.class);
		sqlParameter = SpringUtil.getBean(SqlParameter.class);
	}

	/**
	 * @状态 ok
	 * @功能 {获取所有区域的指标}01
	 * @作者 MaxBill
	 * @时间 2017年8月31日 下午4:58:25
	 */
	public ResponseBeanDto getTarget(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色部门
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String dept = jsonObject.getString("dept");// 角色部门
			String parentTarget = jsonObject.getString("parentTarget");// 角色部门
			String module = jsonObject.getString("module");// 角色部门
			if (null == parentTarget || parentTarget.equals("")) {
				TemReg temReg = indexService.findRegionByCode(module);
				List<String> targetList = indexService.findTargetByRegAndRoleAndOrgAndDept(temReg.getRegId(), role,
						roleOrg, deptMap.get(dept).toString());
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(targetList), responseBeanDto);
			} else {
				List<CoreTarget> coreTargetList = indexService.findCoreTargetByPid(parentTarget);
				List<String> targetList = new ArrayList<String>();
				for (CoreTarget coreTarget : coreTargetList) {
					targetList.add(coreTarget.getTargetCode());
				}
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(targetList), responseBeanDto);
				dataPrintTips(1, targetList);
			}
		} catch (Exception e) {
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {固定数据}02
	 * @作者 MaxBill
	 * @时间 2017年8月22日 上午11:25:37
	 */
	public ResponseBeanDto fixedMenu(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap<>();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String subCode = "";
			switch (jsonObject.getString("dept")) {
			case "trade":
				subCode = "'T01','T02'";
				break;
			case "premium":
				subCode = "'T09'";
				break;
			case "org":
				subCode = "'T07','T08','P01','P02'";
				break;
			case "train":
				subCode = "'P04','P05'";
				break;
			}
			// 查询时间指标
			List<GroDimDetailBean> timeReqs = new ArrayList<GroDimDetailBean>();
			List<GroDimDetail> groDimDetails = indexService.findIndexGroDetailListBySubWithDate(subCode);
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
				resultMap.put("date", new String[] {});
				resultMap.put("org", timeReqs);
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
				dataPrintTips(2, resultMap);
			} else {
				// 数据异常
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
			// List<GroDimDetail> groDimDetails =
			// indexService.findGroDimDetailListByGdid("DATE_GROUP");
			// if (null != groDimDetails) {
			// for (GroDimDetail groDimDetail : groDimDetails) {
			// GroDimDetailBean groDimDetailBean = new GroDimDetailBean();
			// groDimDetailBean.setName(groDimDetail.getGroDimName());
			// groDimDetailBean.setValue(groDimDetail.getGroDimCode());
			// timeReqs.add(groDimDetailBean);
			// }
			// resultMap.put("date", new String[] {});
			// resultMap.put("org", timeReqs);
			// // 成功响应处理
			// this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			// dataPrintTips(2, resultMap);
			// } else {
			// // 数据异常
			// this.dataIsEmptyTips(jsonObject, responseBeanDto);
			// }
		} catch (Exception e) {
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {核心指标}03
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午3:03:16
	 */
	public ResponseBeanDto getCoreData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			CommonService commonService = SpringUtil.getBean(CommonService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String dept = jsonObject.getString("dept");// 当前点击部门
			String by = jsonObject.getString("by");// 条件类型
			String code = jsonObject.getString("code");// 条件值
			String module = jsonObject.getString("module");// 区域code
			String targetCode = jsonObject.getString("targetCode");// 指标code
			// 查询区域信息
			TemReg temReg = indexService.findRegionByCode(module);
			TarReg tarReg = indexService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			if (null != tarReg) {
				switch (by) {
				case "org":
					TarRegSql tarRegSqlByOrg = indexService.findTarRegSql(3, targetCode, temReg.getRegId(), "ORG_GROUP",
							null, code);
					if (null != tarRegSqlByOrg) {
						DataBean dataBean = new DataBean();
						// 封装数据模型
						dataBean.setName("");
						dataBean.setStacking("");
						dataBean.setxAxis("category");
						// 封装指标图形数据
						TarRegBean tarRegBean = new TarRegBean();
						tarRegBean.setType(tarReg.getGraphType());
						tarRegBean.setName(tarReg.getGraphTitle());
						tarRegBean.setCode(targetCode);
						tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
						tarRegBean.setColor(tarReg.getColor());
						tarRegBean.setStack("");
						int nextOrg = 0;
						switch (roleOrg.length()) {
						case 1:
							nextOrg = 3;
							break;
						case 3:
							nextOrg = 5;
							break;
						case 5:
							nextOrg = 10;
							break;
						default:
							nextOrg = 11;
							break;
						}
						// 数据处理
						String sqlCodeByOrg = sqlFactory.getGdzbSql(SqlConstants.GDZB_02, tarRegSqlByOrg, roleOrg,
								deptMap.get(dept).toString(), "ORG_GROUP", nextOrg);
						sqlCodeByOrg = sqlParameter.gdzbCoreSqlParam(sqlCodeByOrg, targetCode, roleOrg,
								deptMap.get(dept).toString());
						// 判断能否下转
						TarGroDim tarGroDim = indexService.findUnionTarGroDimByTar(targetCode);
						if (commonUtil.isGdzbDrilldown(Integer.parseInt(tarGroDim.getGroDimDetail().getGroDimCode()),
								nextOrg)) {
							tarRegBean.setDrilldown(true);
						} else {
							tarRegBean.setDrilldown(false);
						}
						tarRegBean.setData(sqlCodeByOrg);
						List<Object> tarRegBeanList = new ArrayList<Object>();
						tarRegBeanList.add(tarRegBean);
						dataBean.setSeries(tarRegBeanList);
						// 成功响应处理
						this.dataSuccessTips(JSON.toJSONString(dataBean), responseBeanDto);
						dataPrintTips(3, dataBean);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
					break;
				case "date":
					TarRegSql tarRegSqlByDate = indexService.findTarRegSql(2, targetCode, temReg.getRegId(),
							"DATE_GROUP", "MONTH", null);
					if (null != tarRegSqlByDate) {
						DataBean dataBean = new DataBean();
						// 封装数据模型
						dataBean.setName("");
						dataBean.setStacking("");
						dataBean.setxAxis("category");
						// 封装指标图形数据
						TarRegBean tarRegBean = new TarRegBean();
						tarRegBean.setType(tarReg.getGraphType());
						tarRegBean.setName(tarReg.getGraphTitle());
						tarRegBean.setCode(targetCode);
						tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
						tarRegBean.setColor(tarReg.getColor());
						tarRegBean.setStack("");
						int nextOrg = 0;
						switch (roleOrg.length()) {
						case 1:
							nextOrg = 3;
							break;
						case 3:
							nextOrg = 5;
							break;
						case 5:
							nextOrg = 10;
							break;
						default:
							nextOrg = 11;
							break;
						}
						// 数据处理
						String sqlCodeByDate = "";
						if (code.startsWith("ALL-")) {
							sqlCodeByDate = sqlFactory.getGdzbSqlWithAll(tarRegSqlByDate, roleOrg,
									deptMap.get(dept).toString());
						} else {
							sqlCodeByDate = sqlFactory.getGdzbSql(SqlConstants.GDZB_02, tarRegSqlByDate, code,
									deptMap.get(dept).toString(), "DATE_GROUP", nextOrg);
						}
						tarRegBean.setDateType("MONTH");
						tarRegBean.setDrilldown(true);
						// 判断是否有下转到天维度
						Boolean isHasGroDim = commonService.findTarGroDimByGroAndTar("DAY", targetCode);
						if (!isHasGroDim) {
							tarRegBean.setDrilldown(false);
						}
						tarRegBean.setData(sqlCodeByDate);
						List<Object> tarRegBeanList = new ArrayList<Object>();
						tarRegBeanList.add(tarRegBean);
						dataBean.setSeries(tarRegBeanList);
						// 成功响应处理
						this.dataSuccessTips(JSON.toJSONString(dataBean), responseBeanDto);
						dataPrintTips(3, dataBean);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
					break;
				}
			} else {
				// 数据异常
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {核心指标下钻}04
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午3:03:16
	 */
	public ResponseBeanDto coreDataDown(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap<>();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			//"roleOrg":"1302040101"
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String dept = jsonObject.getString("dept");// 当前点击部门
			String by = jsonObject.getString("by");// 条件类型
			String code = jsonObject.getString("code");// 条件值
			String targetCode = jsonObject.getString("target");// 指标编码
			String parent = jsonObject.getString("parent");// 下钻的机构编码
			String dateType = "";
			// 判断是否是个业部
			if (!dept.equals("130107")) {
				dateType = "MONTH";
			} else {
				dateType = jsonObject.getString("dateType");// 时间类型
			}
			// 查询区域信息
			TemReg temReg = indexService.findRegionByCode("TEMP02_REG01");
			TarReg tarReg = indexService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			if (null != tarReg) {
				resultMap.put("name", tarReg.getGraphTitle());
				resultMap.put("type", tarReg.getGraphType());
				TarRegSql tarRegSql = null;
				String sqlCode = "";
				int nextOrg = 0;
				switch (by) {
				//按机构查询
				case "org":
					tarRegSql = indexService.findTarRegSql(3, targetCode, temReg.getRegId(), "ORG_GROUP", null, code);
					if (null != tarRegSql) {
						roleOrg = parent;
						switch (roleOrg.length()) {
						case 1:
							nextOrg = 3;
							break;
						case 3:
							nextOrg = 5;
							break;
						case 5:
							nextOrg = 10;
							break;
						default:
							nextOrg = 11;
							break;
						}
						TarGroDim tarGroDim = indexService.findUnionTarGroDimByTar(targetCode);
						if (commonUtil.isGdzbDrilldown(Integer.parseInt(tarGroDim.getGroDimDetail().getGroDimCode()),
								nextOrg)) {
							resultMap.put("drilldown", true);
						} else {
							resultMap.put("drilldown", false);
						}
						sqlCode = sqlFactory.getGdzbDownSql(tarRegSql, roleOrg, deptMap.get(dept).toString(),
								"ORG_GROUP", null, parent, null);
						sqlCode = sqlParameter.gdzbCoreSqlParam(sqlCode, targetCode, roleOrg,
								deptMap.get(dept).toString());
						resultMap.put("sql", sqlCode);
						resultMap.put("code", targetCode);
						// 成功响应处理
						this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
						dataPrintTips(4, resultMap);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
					break;
					//按日期查询
				case "date":
					tarRegSql = indexService.findTarRegSql(2, tarReg.getTargetCode(), temReg.getRegId(), "DATE_GROUP",
							"DAY", null);
					if (null != tarRegSql) {
						if (dateType.equals("MONTH")) {
							resultMap.put("drilldown", false);
							resultMap.put("dateType", "DAY");
						} else if (dateType.equals("DAY")) {
							resultMap.put("drilldown", false);
							resultMap.put("dateType", "DAY");
						}
						switch (roleOrg.length()) {
						case 1:
							nextOrg = 3;
							break;
						case 3:
							nextOrg = 5;
							break;
						case 5:
							nextOrg = 10;
							break;
						default:
							nextOrg = 11;
							break;
						}
						if (code.startsWith("ALL-")) {
							sqlCode = sqlFactory.getGdzbDownSqlWithAll(tarRegSql, roleOrg, deptMap.get(dept).toString(),
									dateType, parent);
						} else {
							sqlCode = sqlFactory.getGdzbDownSql(tarRegSql, code, deptMap.get(dept).toString(),
									"DATE_GROUP", dateType, parent, nextOrg);
						}
						resultMap.put("sql", sqlCode);
						resultMap.put("code", targetCode);
						// 成功响应处理
						this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
						dataPrintTips(4, resultMap);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
					break;
				}
			} else {
				// 数据异常
				this.dataIsEmptyTips(jsonObject, responseBeanDto);
			}
		} catch (Exception e) {
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {月指标}05
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午3:03:16
	 */
	public ResponseBeanDto getMonthData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String parentTarget = jsonObject.getString("parentTarget");// 核心指标代码
			String module = jsonObject.getString("module");// 区域code
			String targetCode = jsonObject.getString("targetCode");// 指标code
			Target target = indexService.findTargetByCode(targetCode);
			// 查询区域信息
			TemReg temReg = indexService.findRegionByCode(module);
			if (null == parentTarget || parentTarget.equals("")) {
				TarReg tarReg = indexService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
				if (null != tarReg) {
					TarRegSql tarRegSql = indexService.findTarRegSql(6, targetCode, temReg.getRegId(), null, null,
							null);
					if (null != tarRegSql) {
						TarRegBean tarRegBean = new TarRegBean();
						String sqlCode = tarRegSql.getSqlCode();
						sqlCode = sqlFactory.getGdzbSql(SqlConstants.GDZB_01, tarRegSql, roleOrg, target.getDeptCode(),
								null, null);
						tarRegBean.setName(tarReg.getGraphTitle());
						tarRegBean.setData(sqlCode);
						if (null != target.getIsManageTarget() && target.getIsManageTarget().equals("1")) {
							tarRegBean.setHasInfo(true);
							List<JyfxTarget> jyfxTargetList = indexService
									.findJyfxTargetByTarget(tarReg.getTargetCode());
							String strJyfx = "";
							if (null != jyfxTargetList) {
								for (JyfxTarget jyfxTarget : jyfxTargetList) {
									strJyfx = strJyfx + jyfxTarget.getAnTargetCode() + ",";
								}
							}
							tarRegBean.setTarget(strJyfx.substring(0, strJyfx.length() - 1));
							tarRegBean.setCode(tarReg.getTargetCode());
						} else {
							tarRegBean.setHasInfo(false);
							tarRegBean.setCode(tarReg.getTargetCode());
						}
						// 成功响应处理
						this.dataSuccessTips(JSON.toJSONString(tarRegBean), responseBeanDto);
						dataPrintTips(5, tarRegBean);
					} else {
						// 数据异常
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				}
			} else {
				TarReg tarReg = indexService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
				TarRegSql tarRegSql = indexService.findTarRegSql(6, targetCode, temReg.getRegId(), null, null, null);
				if (null != tarRegSql) {
					String sqlCode = tarRegSql.getSqlCode();
					sqlCode = sqlFactory.getGdzbSql(SqlConstants.GDZB_01, tarRegSql, roleOrg, target.getDeptCode(),
							null, null);
					TarRegBean tarRegBean = new TarRegBean();
					tarRegBean.setName(tarReg.getGraphTitle());
					tarRegBean.setData(sqlCode);
					if (target.getIsManageTarget().equals("1")) {
						tarRegBean.setHasInfo(true);
						List<JyfxTarget> jyfxTargetList = indexService.findJyfxTargetByTarget(tarReg.getTargetCode());
						String strJyfx = "";
						if (null != jyfxTargetList) {
							for (JyfxTarget jyfxTarget : jyfxTargetList) {
								strJyfx = strJyfx + jyfxTarget.getAnTargetCode() + ",";
							}
						}
						tarRegBean.setTarget(strJyfx.substring(0, strJyfx.length() - 1));
						tarRegBean.setCode(tarReg.getTargetCode());
					} else {
						tarRegBean.setHasInfo(false);
						tarRegBean.setCode(tarReg.getTargetCode());
					}
					// 成功响应处理
					this.dataSuccessTips(JSON.toJSONString(tarRegBean), responseBeanDto);
					dataPrintTips(5, tarRegBean);
				} else {
					// 数据异常
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}
			}
		} catch (Exception e) {
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {月指标经营信息}06
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午3:03:16
	 */
	public ResponseBeanDto monthDataInfo(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String target = jsonObject.getString("jyfx");// 指标编码
			// 查询区域信息
			TemReg temReg = indexService.findRegionByCode("TEMP11_REG01");
			TarReg tarReg = indexService.findTarRegByRegAndTarget(temReg.getRegId(), target);
			if (null != tarReg) {
				TarRegSql tarRegSql = indexService.findTarRegSql(6, target, temReg.getRegId(), null, null, null);
				Target targetBean = indexService.findTargetByCode(tarReg.getTargetCode());
				String sqlCode = sqlFactory.getOperateInfo(tarRegSql.getSqlCode(), roleOrg, targetBean.getDeptCode(),
						"");
				TarRegBean tarRegBean = new TarRegBean();
				tarRegBean.setType(tarReg.getGraphType());
				tarRegBean.setTitle(indexService.findTargetByCode(target).getTargetName());
				tarRegBean.setName(tarReg.getGraphTitle());
				tarRegBean.setCode(tarReg.getTargetCode());
				tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
				tarRegBean.setStack("");
				tarRegBean.setColor("");
				tarRegBean.setSql(sqlCode);
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(tarRegBean), responseBeanDto);
				dataPrintTips(6, tarRegBean);
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
	 * @功能 {排名指标}07
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午3:03:16
	 */
	public ResponseBeanDto getRankData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap<>();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String monthTarget = jsonObject.getString("monthTarget");// 月度指标代码
			// 查询地图数据
			TarRegSql tarRegSql;
			String sqlCode;
			TemReg temRegMap = indexService.findRegionByCode("TEMP02_REG03");
			TarReg tarRegMap = indexService.findTarRegByRegAndTarget(temRegMap.getRegId(), monthTarget);
			if (null != tarRegMap
					&& commonUtil.hasRoleOrg(Integer.parseInt(tarRegMap.getRoleOrgType()), roleOrg.length())) {
				Target target = indexService.findTargetByCode(tarRegMap.getTargetCode());
				tarRegSql = indexService.findTarRegSql(6, tarRegMap.getTargetCode(), tarRegMap.getRegId(), null, null,
						null);
				sqlCode = sqlFactory.getGdzbSql("", tarRegSql, roleOrg, target.getDeptCode(), null, null);
				resultMap.put("map", sqlCode);
			} else {
				resultMap.put("map", "");
			}
			// 查询光荣榜区域数据
			TemReg temRegHonor = indexService.findRegionByCode("TEMP02_REG04");
			TarReg tarRegHonor = indexService.findTarRegByRegAndTarget(temRegHonor.getRegId(), monthTarget);
			if (null != tarRegHonor
					&& commonUtil.hasRoleOrg(Integer.parseInt(tarRegHonor.getRoleOrgType()), roleOrg.length())) {
				Target target = indexService.findTargetByCode(tarRegHonor.getTargetCode());
				tarRegSql = indexService.findTarRegSql(6, tarRegHonor.getTargetCode(), tarRegHonor.getRegId(), null,
						null, null);
				sqlCode = sqlFactory.getGdzbSql(SqlConstants.GDZB_01, tarRegSql, roleOrg, target.getDeptCode(), null,
						null);
				resultMap.put("honor", sqlCode);
			} else {
				resultMap.put("honor", "");
			}
			// 查询排行榜区域数据
			TemReg temRegRank = indexService.findRegionByCode("TEMP02_REG05");
			TarReg tarRegRank = indexService.findTarRegByRegAndTarget(temRegRank.getRegId(), monthTarget);
			if (null != tarRegRank
					&& commonUtil.hasRoleOrg(Integer.parseInt(tarRegRank.getRoleOrgType()), roleOrg.length())) {
				Target target = indexService.findTargetByCode(tarRegRank.getTargetCode());
				tarRegSql = indexService.findTarRegSql(6, tarRegRank.getTargetCode(), tarRegRank.getRegId(), null, null,
						null);
				sqlCode = sqlFactory.getGdzbSql(SqlConstants.GDZB_03, tarRegSql, roleOrg, target.getDeptCode(), null,
						null);
				resultMap.put("rank", sqlCode);
			} else {
				resultMap.put("rank", "");
			}
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			dataPrintTips(7, resultMap);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {月指标统计列表}08
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午3:03:16
	 */
	public ResponseBeanDto getMonthList(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String module = jsonObject.getString("module");// 区域code
			String target = jsonObject.getString("targetCode");// 指标code
			// 查询区域信息
			TemReg temReg = indexService.findRegionByCode(module);
			TarReg tarReg = indexService.findTarRegByRegAndTarget(temReg.getRegId(), target);
			Target targetBean = indexService.findTargetByCode(tarReg.getTargetCode());
			List<TarRegBean> tarRegBeanList = new ArrayList<TarRegBean>();
			if (null != tarReg && commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
				List<Object> dataList = new ArrayList<>();
				// 封装表格头部信息
				List<String> tarRegTabHeadList = indexService.findTarRegTabHeadByRegAndTar(temReg.getRegId(), target);
				dataList.add(tarRegTabHeadList);
				// 封装列表数据
				TarRegBean tarRegBean = new TarRegBean();
				TarRegSql tarRegSql = indexService.findTarRegSql(5, tarReg.getTargetCode(), tarReg.getRegId(), null,
						null, "MONTH");
				String sqlCode = sqlFactory.getGdzbSql(SqlConstants.GDZB_01, tarRegSql, roleOrg,
						targetBean.getDeptCode(), null, null);
				tarRegBean.setData(sqlCode);
				tarRegBeanList.add(tarRegBean);
				dataList.add(tarRegBeanList);
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
				dataPrintTips(8, dataList);
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
	 * @功能 {公告列表}09
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午3:58:40
	 */
	public ResponseBeanDto listNotice(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String dept = jsonObject.getString("dept");// 角色部门
			List<Notice> noticeList = indexService.listNotice(deptMap.get(dept).toString());
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(noticeList), responseBeanDto);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {公告详情}10
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午3:59:22
	 */
	public ResponseBeanDto lookNotice(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			IndexService indexService = SpringUtil.getBean(IndexService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Integer id = jsonObject.getInteger("id");
			NoticeBean noticeBean = indexService.lookNotice(id);
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(noticeBean), responseBeanDto);
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
		log.info("------------------------GDZB(" + type + ")-S------------------------");
		log.info(DateUtil.formatDateTime(new Date()) + ": " + JSON.toJSONString(data));
		log.info("------------------------GDZB(" + type + ")-E------------------------");
	}
}
