package com.huatai.web.thrift.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.RedisUtil;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.BlockBean;
import com.huatai.web.bean.DataBean;
import com.huatai.web.bean.DataPageBean;
import com.huatai.web.bean.GroDimDetailBean;
import com.huatai.web.bean.OrgBean;
import com.huatai.web.bean.QueryDimBean;
import com.huatai.web.bean.TarRegBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.job.constants.RedisConstants;
import com.huatai.web.model.CustPlate;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.PlateOneTar;
import com.huatai.web.model.PlateTwoTar;
import com.huatai.web.model.PlateTwoTarGro;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.TarStatis;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.sql.CommonSqlService;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.sql.SqlConstants;
import com.huatai.web.sql.SqlFactory;
import com.huatai.web.sql.SqlParameter;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.BlockService;
import com.huatai.web.thrift.service.CommonService;
import com.huatai.web.utils.DateUtil;

/**
 * @功能 {板块服务}
 * @作者 MaxBill
 * @时间 2017年7月24日 下午3:34:31
 */
public class BkfwServiceImpl implements BkfwService.Iface {

	private static final String TEMPLET_CODE_ZT = "TEMP03";

	private static final String TEMPLET_CODE_BK = "TEMP04";

	Logger log = Logger.getLogger(ZyfwServiceImpl.class);

	private SqlFactory sqlFactory;
	private CommonUtil commonUtil;
	private SqlParameter sqlParameter;
	private CommonSqlService commonSqlService;

	public BkfwServiceImpl() {
		sqlFactory = SpringUtil.getBean(SqlFactory.class);
		commonUtil = SpringUtil.getBean(CommonUtil.class);
		sqlParameter = SpringUtil.getBean(SqlParameter.class);
		commonSqlService = SpringUtil.getBean(CommonSqlService.class);
	}

	/**
	 * @状态 ok
	 * @功能 {获取区域的指标}01
	 * @作者 MaxBill
	 * @时间 2017年8月31日 下午4:58:25
	 */
	public ResponseBeanDto getRegTarget(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String module = jsonObject.getString("module");// 区域编码
			Boolean isCustom = jsonObject.getBoolean("isCustom");// 是否自定义板块
			TemReg temReg = blockService.findRegionByCode(module);
			if (isCustom) {
				// 自定义板块
				Integer blockId = jsonObject.getInteger("blockCode");// 板块编号
				List<TarReg> tarRegList = blockService.findPlateTarRegByRegAndPlate(temReg.getRegId(), blockId);
				List<String> targetList = new ArrayList<String>();
				if (null != tarRegList) {
					for (TarReg tarReg : tarRegList) {
						targetList.add(tarReg.getTargetCode());
					}
				}
				this.dataSuccessTips(JSON.toJSONString(targetList), responseBeanDto);
				dataPrintTips(1, targetList);
			} else {
				// 固定板块
				String subject = jsonObject.getString("blockCode");// 角色部门
				List<String> targetList = blockService.findTargetByRegAndRoleAndOrgAndDeptAndSubject(temReg.getRegId(),
						role, roleOrg, null, subject);
				this.dataSuccessTips(JSON.toJSONString(targetList), responseBeanDto);
				dataPrintTips(1, targetList);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {板块菜单接口}02
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午8:54:20
	 */
	public ResponseBeanDto getBlockMenu(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		BlockService blockService = SpringUtil.getBean(BlockService.class);
		Map resultMap = new HashMap();
		try {
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String userName = jsonObject.getString("username");// 用户名
			String role = jsonObject.getString("role");// 角色
			List<CustPlate> custPlateList = blockService.findCustPlateByUser(role, userName);
			// 自定义板块
			List<BlockBean> plateList = new ArrayList<BlockBean>();
			if (null != custPlateList) {
				for (CustPlate custPlate : custPlateList) {
					BlockBean custPlateBean = new BlockBean();
					custPlateBean.setCode(custPlate.getPlateId().toString());
					custPlateBean.setBlockName(custPlate.getPlateName());
					plateList.add(custPlateBean);
				}
				resultMap.put("custom", plateList);
			}
			// 固定板块
			List<Target> fixedPlate = blockService.findFixedPlateByRole(role);
			List<BlockBean> fixed = new ArrayList<BlockBean>();
			if (null != fixedPlate) {
				for (Target target : fixedPlate) {
					BlockBean custPlateBean = new BlockBean();
					custPlateBean.setCode(target.getTargetCode());
					custPlateBean.setTargetName(target.getTargetName());
					fixed.add(custPlateBean);
				}
				resultMap.put("fixed", fixed);
			}
			// 成功响应处理
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
	 * @功能 {板块指标值接口}03
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午4:13:55
	 */
	public ResponseBeanDto getBlockTarget(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		BlockService blockService = SpringUtil.getBean(BlockService.class);
		Map resultMap = new HashMap();
		try {
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Boolean isCustom = jsonObject.getBoolean("isCustom");// 是否是自定义板块
			String roleOrg = jsonObject.getString("roleOrg");// 角色所在机构
			List<String> tarRegTabHeadList = null;
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP04_REG01");
			if (isCustom) {
				Integer blockId = jsonObject.getInteger("blockCode");// 板块id
				// 查询板块信息
				CustPlate custPlate = blockService.findCustPlateById(blockId);
				// 查询一级指标
				List<PlateOneTar> plateOneTarList = blockService.findPlateOneTarByBlock(blockId);
				// 查询二级指标
				List<PlateTwoTar> plateTwoTarList = new ArrayList<PlateTwoTar>();
				// 查询二级指标组
				List<PlateTwoTarGro> plateTwoTarGroList = blockService.findPlateTwoTarGroByBlock(blockId);
				if (null != plateTwoTarGroList) {
					for (PlateTwoTarGro plateTwoTarGro : plateTwoTarGroList) {
						// 查询二级指标分组下的二级指标
						List<PlateTwoTar> plateTwoTarListTemp = blockService
								.findPlateTwoTarByGroup(plateTwoTarGro.getPttgId());
						plateTwoTarList.addAll(plateTwoTarListTemp);
					}
				}
				// 是否含有三级机构的指标
				Boolean hasThreeOrg = false;
				// 是否含有四级机构的指标
				Boolean hasFourOrg = false;
				// 判断是营销服务部的还是成本中心的:如果所有的指标含有培训部，个业部的，则显示为营销服务部，否则显示为成本中心
				Boolean isServer = false;
				for (int i = 0; i < plateOneTarList.size(); i++) {
					String targetCode = plateOneTarList.get(i).getTargetCode();
					String subCode = targetCode.substring(0, 3);
					if (commonUtil.isGeYeAndPeiXunDept(blockService.findTargetByCode(targetCode).getDeptCode())) {
						isServer = true;
					}
					if (subCode.equals("T07")) {
						hasThreeOrg = true;
					}
					if (subCode.equals("T08") || subCode.equals("T09") || subCode.equals("T10")) {
						hasFourOrg = true;
					}
				}
				for (int i = 0; i < plateTwoTarList.size(); i++) {
					String targetCode = plateTwoTarList.get(i).getTargetCode();
					String subCode = targetCode.substring(0, 3);
					if (commonUtil.isGeYeAndPeiXunDept(blockService.findTargetByCode(targetCode).getDeptCode())) {
						isServer = true;
					}
					if (subCode.equals("T07")) {
						hasThreeOrg = true;
					}
					if (subCode.equals("T08") || subCode.equals("T09") || subCode.equals("T10")) {
						hasFourOrg = true;
					}
				}
				// 封装伪sql总区域月参数
				String finalSql = "";
				String finalTarsSql = "SELECT '板块' AS VAL0, ";
				String finalDataSql = "SELECT '" + custPlate.getPlateName() + "' AS VAL0, ";
				String finalTempSql = " FROM ";
				if (null != plateOneTarList) {
					for (PlateOneTar target1Obj : plateOneTarList) {
						String targetCode = target1Obj.getTargetCode();
						Target targetBean = blockService.findTargetByCode(targetCode);
						// 该指标的最低机构
						int minOrg = Integer.parseInt(blockService.findGroDetailOrgCodeByTarget(targetCode));
						// 总区域一级指标月sql拼装
						TarInitSql finalTarInitSql = blockService.findFinalTarInitSql(targetCode);
						int finalIndex = plateOneTarList.indexOf(target1Obj) + 1;
						String finalSqlCode = finalTarInitSql.getSqlCode();
						if (commonUtil.hasRoleOrg(minOrg, roleOrg.length())) {
							finalSqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_01, finalSqlCode, roleOrg, isServer);
						} else {
							finalSqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_03, finalSqlCode, roleOrg, isServer);
						}
						finalTarsSql = finalTarsSql + "'" + targetBean.getTargetName() + "' AS VAL" + finalIndex + ", ";
						finalDataSql = finalDataSql + "T0" + finalIndex + ".VAL AS VAL" + finalIndex + ", ";
						finalTempSql = finalTempSql + "( " + finalSqlCode + " ) T0" + finalIndex + ", ";
					}
				}
				if (null != plateTwoTarList) {
					for (PlateTwoTar target2Obj : plateTwoTarList) {
						String targetCode = target2Obj.getTargetCode();
						Target targetBean = blockService.findTargetByCode(targetCode);
						// 该指标的最低机构
						int minOrg = Integer.parseInt(blockService.findGroDetailOrgCodeByTarget(targetCode));
						// 总区域二级指标月sql拼装
						TarInitSql finalTarInitSql = blockService.findFinalTarInitSql(targetCode);
						int finalIndex = plateOneTarList.size() + plateTwoTarList.indexOf(target2Obj) + 1;
						String finalSqlCode = finalTarInitSql.getSqlCode();
						if (commonUtil.hasRoleOrg(minOrg, roleOrg.length())) {
							finalSqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_01, finalSqlCode, roleOrg, isServer);
						} else {
							finalSqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_03, finalSqlCode, roleOrg, isServer);
						}
						finalTarsSql = finalTarsSql + "'" + targetBean.getTargetName() + "' AS VAL" + finalIndex + ", ";
						finalDataSql = finalDataSql + "T0" + finalIndex + ".VAL AS VAL" + finalIndex + ", ";
						finalTempSql = finalTempSql + "( " + finalSqlCode + " ) T0" + finalIndex + ", ";
					}
				}
				// 总区域月sql拼装
				finalTarsSql = finalTarsSql.substring(0, finalTarsSql.length() - 2) + " FROM DUAL UNION ALL ";
				finalDataSql = finalDataSql.substring(0, finalDataSql.length() - 2) + " ";
				finalTempSql = finalTempSql.substring(0, finalTempSql.length() - 2) + " ";
				finalSql = finalTarsSql + finalDataSql + finalTempSql;
				resultMap.put("sql", finalSql);
				resultMap.put("isServer", isServer);
				resultMap.put("hasThreeOrg", hasThreeOrg);
				resultMap.put("hasFourOrg", hasFourOrg);
			} else {
				// 是否含有三级机构的指标
				Boolean hasThreeOrg = false;
				// 是否含有三级机构的指标
				Boolean hasFourOrg = false;
				String blockCode = jsonObject.getString("blockCode");// 板块code
				if (blockCode.equals("P01")) {
					hasThreeOrg = true;
				}
				if (blockCode.equals("P02")) {
					hasFourOrg = true;
				}
				// 固定板块
				TarReg tarReg = blockService.findTarRegByRegAndSub(temReg.getRegId(), blockCode);
				TarRegSql tarRegSql = blockService.findTarRegSql(4, tarReg.getTargetCode(), temReg.getRegId(), null,
						null, "MONTH");
				// 判断是营销服务部的还是成本中心的:如果所有的指标中有营销服务部的（培训部，个业部）的，则显示为营销服务部，否则显示为成本中心
				Boolean isServer = false;
				if (commonUtil
						.isGeYeAndPeiXunDept(blockService.findTargetByCode(tarReg.getTargetCode()).getDeptCode())) {
					isServer = true;
				}
				String sqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_01, tarRegSql.getSqlCode(), roleOrg, isServer);
				tarRegTabHeadList = blockService.findTarRegTabHeadByRegAndTar(temReg.getRegId(),
						tarReg.getTargetCode());
				resultMap.put("sql", sqlCode);
				resultMap.put("head", tarRegTabHeadList);
				resultMap.put("isServer", isServer);
				resultMap.put("hasThreeOrg", hasThreeOrg);
				resultMap.put("hasFourOrg", hasFourOrg);
			}
			// 是否有趋势图
			if (roleOrg.length() > 10) {
				resultMap.put("isChart", false);
			} else {
				resultMap.put("isChart", true);
			}
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
	 * @功能 {板块机构指标值接口}04
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午10:35:54
	 */
	public ResponseBeanDto getBlockOrgTarget(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		BlockService blockService = SpringUtil.getBean(BlockService.class);
		Map resultMap = new HashMap();
		try {
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色所在机构
			Boolean isCustom = jsonObject.getBoolean("isCustom");
			String ptOrg = jsonObject.getString("ptOrg");// 上级机构
			String isNextChart = jsonObject.getString("isNextChart");// 上级机构
			int nextOrg = 0;
			int currOrg = 0;
			switch (roleOrg.length()) {
			case 1:
				nextOrg = 7;
				currOrg = 8;
				break;
			case 3:
				nextOrg = 6;
				currOrg = 7;
				break;
			case 5:
				nextOrg = 5;
				currOrg = 6;
				break;
			case 10:
				nextOrg = 4;
				currOrg = 5;
				break;
			}
			List<String> tarRegTabHeadList = null;
			// 是否有趋势图
			if (!StringUtils.isEmpty(isNextChart)) {
				resultMap.put("isChart", isNextChart);
			} else {
				if (roleOrg.length() > 5) {
					resultMap.put("isChart", false);
				} else {
					resultMap.put("isChart", true);
				}
			}
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP04_REG02");
			if ((null == ptOrg || ptOrg.equals("")) || ptOrg.equals(roleOrg)) {
				resultMap.put("drilldown", false);// 默认下转
				if (isCustom) {
					// 自定义板块
					Integer blockId = jsonObject.getInteger("blockCode");// 板块id
					// 查询一级指标
					List<PlateOneTar> plateOneTarList = blockService.findPlateOneTarByBlock(blockId);
					// 查询二级指标
					List<PlateTwoTar> plateTwoTarList = new ArrayList<PlateTwoTar>();
					// 查询二级指标组
					List<PlateTwoTarGro> plateTwoTarGroList = blockService.findPlateTwoTarGroByBlock(blockId);
					if (null != plateTwoTarGroList) {
						for (PlateTwoTarGro plateTwoTarGro : plateTwoTarGroList) {
							// 查询二级指标分组下的二级指标
							List<PlateTwoTar> plateTwoTarListTemp = blockService
									.findPlateTwoTarByGroup(plateTwoTarGro.getPttgId());
							plateTwoTarList.addAll(plateTwoTarListTemp);
						}
					}
					List<String> targetList = new ArrayList<String>();
					// 是否只含有四级机构
					Boolean hasOnlyFour = true;
					// 是否只含有三级机构
					Boolean hasOnlyThree = true;
					// 是否含有三级机构的指标
					Boolean hasThreeOrg = false;
					// 是否含有四级机构的指标
					Boolean hasFourOrg = false;
					// 判断是营销服务部的还是成本中心的:如果所有的指标中有营销服务部的（培训部，个业部）的，则显示为营销服务部，否则显示为成本中心
					Boolean isServer = false;
					for (PlateOneTar plateOneTar : plateOneTarList) {
						String targetCode = plateOneTar.getTargetCode();
						String subCode = targetCode.substring(0, 3);
						if (commonUtil.isGeYeAndPeiXunDept(blockService.findTargetByCode(targetCode).getDeptCode())) {
							isServer = true;
						}
						if (subCode.equals("T07")) {
							hasThreeOrg = true;
						} else if (subCode.equals("T08") || subCode.equals("T09") || subCode.equals("T10")) {
							hasFourOrg = true;
							hasOnlyThree = false;
						} else {
							hasOnlyThree = false;
							hasOnlyFour = false;
						}
						targetList.add(plateOneTar.getTargetCode());
					}
					for (PlateTwoTar plateTwoTar : plateTwoTarList) {
						String targetCode = plateTwoTar.getTargetCode();
						String subCode = targetCode.substring(0, 3);
						if (commonUtil.isGeYeAndPeiXunDept(blockService.findTargetByCode(targetCode).getDeptCode())) {
							isServer = true;
						}
						if (subCode.equals("T07")) {
							hasThreeOrg = true;
						} else if (subCode.equals("T08") || subCode.equals("T09") || subCode.equals("T10")) {
							hasFourOrg = true;
							hasOnlyThree = false;
						} else {
							hasOnlyThree = false;
							hasOnlyFour = false;
						}
						targetList.add(plateTwoTar.getTargetCode());
					}
					// 封装伪sql下转区域月参数
					String belowSql = "";
					String belowTarsSql = "SELECT '机构' AS VAL0, '' AS P_VAL, ";
					String belowBaseSql = "FROM (SELECT DISTINCT {orgcode_group_code} AS P_VAL, {orgcode_group_name} AS X_VAL FROM D_ORGANIZATION WHERE {orgcode_condition}) T00 ";
					String belowDataSql = "SELECT T00.X_VAL AS VAL0, T00.P_VAL AS P_VAL, ";
					String belowTempSql = "";
					// belowBaseSql = sqlFactory.getBkfwSql(SqlConstants.BKFW_00, belowBaseSql,
					// roleOrg, isServer);
					belowBaseSql = sqlFactory.getBkfwBaseSql(belowBaseSql, roleOrg, isServer, hasOnlyThree,
							hasOnlyFour);
					if (null != plateOneTarList) {
						for (PlateOneTar target1Obj : plateOneTarList) {
							String targetCode = target1Obj.getTargetCode();
							Target targetBean = blockService.findTargetByCode(targetCode);
							// 该指标的最低机构
							int minOrg = Integer.parseInt(blockService.findGroDetailOrgCodeByTarget(targetCode));
							TarInitSql belowTarInitSql = blockService.findBelowTarInitSql(targetCode);
							int belowIndex = plateOneTarList.indexOf(target1Obj) + 1;
							String belowSqlCode = belowTarInitSql.getSqlCode();
							// if (commonUtil.isDrilldown(minOrg, nextOrg)) {
							if (minOrg < currOrg) {
								belowSqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_02, belowSqlCode, roleOrg,
										isServer);
							} else {
								belowSqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_03, belowSqlCode, roleOrg,
										isServer);
							}
							belowTarsSql = belowTarsSql + "'" + targetBean.getTargetName() + "' AS VAL" + belowIndex
									+ ", ";
							belowDataSql = belowDataSql + "NVL(T0" + belowIndex + ".Y_VAL,'--') AS VAL" + belowIndex
									+ ", ";
							belowTempSql = belowTempSql + " LEFT JOIN ( " + belowSqlCode + " ) T0" + belowIndex
									+ " ON T00.P_VAL  = T0" + belowIndex + ".P_VAL";
						}
					}
					if (null != plateTwoTarList) {
						for (PlateTwoTar target2Obj : plateTwoTarList) {
							String targetCode = target2Obj.getTargetCode();
							Target targetBean = blockService.findTargetByCode(targetCode);
							// 该指标的最低机构
							int minOrg = Integer.parseInt(blockService.findGroDetailOrgCodeByTarget(targetCode));
							// 总区域二级指标月sql拼装
							TarInitSql belowTarInitSql = blockService.findBelowTarInitSql(targetCode);
							int belowIndex = plateOneTarList.size() + plateTwoTarList.indexOf(target2Obj) + 1;
							String belowSqlCode = belowTarInitSql.getSqlCode();
							if (minOrg < currOrg) {
								belowSqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_02, belowSqlCode, roleOrg,
										isServer);
							} else {
								belowSqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_03, belowSqlCode, roleOrg,
										isServer);
							}
							belowTarsSql = belowTarsSql + "'" + targetBean.getTargetName() + "' AS VAL" + belowIndex
									+ ", ";
							belowDataSql = belowDataSql + "NVL(T0" + belowIndex + ".Y_VAL,'--') AS VAL" + belowIndex
									+ ", ";
							belowTempSql = belowTempSql + " LEFT JOIN ( " + belowSqlCode + " ) T0" + belowIndex
									+ " ON T00.P_VAL  = T0" + belowIndex + ".P_VAL";
						}
					}
					String unionOrg = commonSqlService.getUnionOrg(1, targetList);
					if (Integer.parseInt(unionOrg) < nextOrg) {
						resultMap.put("drilldown", true);
					}
					// 下转区域月sql拼装
					belowTarsSql = belowTarsSql.substring(0, belowTarsSql.length() - 2) + " FROM DUAL UNION ALL ";
					belowDataSql = belowDataSql.substring(0, belowDataSql.length() - 2) + " ";
					belowSql = belowTarsSql + belowDataSql + belowBaseSql + belowTempSql;
					resultMap.put("sql", belowSql);
					resultMap.put("isServer", isServer);
					resultMap.put("hasThreeOrg", hasThreeOrg);
					resultMap.put("hasFourOrg", hasFourOrg);
					this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
					dataPrintTips(4, resultMap);
				} else {
					// 是否含有三级机构的指标
					Boolean hasThreeOrg = false;
					// 是否含有三级机构的指标
					Boolean hasFourOrg = false;
					String blockCode = jsonObject.getString("blockCode");// 板块code
					if (blockCode.equals("P01")) {
						hasThreeOrg = true;
					}
					if (blockCode.equals("P02")) {
						hasFourOrg = true;
					}
					// 固定板块
					TarReg tarReg = blockService.findTarRegByRegAndSub(temReg.getRegId(), blockCode);
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
						TarGroDim tarGroDim = blockService.findUnionTarGroDimByTar(tarReg.getTargetCode());
						if (commonUtil.isDrilldown(
								Integer.parseInt(
										blockService.findGroDimDetailById(tarGroDim.getGddId()).getGroDimCode()),
								nextOrg)) {
							resultMap.put("drilldown", true);
						}
						String targetCode = tarReg.getTargetCode();
						Target target = blockService.findTargetByCode(targetCode);
						Boolean isServer = commonUtil.isGeYeAndPeiXunDept(target.getDeptCode());
						TarRegSql tarRegSql = blockService.findTarRegSql(3, targetCode, temReg.getRegId(), "ORG_GROUP",
								null, "MONTH");
						String sqlCode = sqlFactory.getBkfwSql(SqlConstants.BKFW_02, tarRegSql.getSqlCode(), roleOrg,
								isServer);
						tarRegTabHeadList = blockService.findTarRegTabHeadByRegAndTar(temReg.getRegId(),
								tarReg.getTargetCode());
						resultMap.put("sql", sqlCode);
						resultMap.put("head", tarRegTabHeadList);
						resultMap.put("isServer", isServer);
						resultMap.put("hasThreeOrg", hasThreeOrg);
						resultMap.put("hasFourOrg", hasFourOrg);
						this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
						dataPrintTips(4, resultMap);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				}
			} else {
				resultMap.put("drilldown", false);
				// 走下转数据
				if (isCustom) {
					// 自定义板块
					Integer blockId = jsonObject.getInteger("blockCode");// 板块id
					// 查询一级指标
					List<PlateOneTar> plateOneTarList = blockService.findPlateOneTarByBlock(blockId);
					// 查询二级指标
					List<PlateTwoTar> plateTwoTarList = new ArrayList<PlateTwoTar>();
					// 查询二级指标组
					List<PlateTwoTarGro> plateTwoTarGroList = blockService.findPlateTwoTarGroByBlock(blockId);
					if (null != plateTwoTarGroList) {
						for (PlateTwoTarGro plateTwoTarGro : plateTwoTarGroList) {
							// 查询二级指标分组下的二级指标
							List<PlateTwoTar> plateTwoTarListTemp = blockService
									.findPlateTwoTarByGroup(plateTwoTarGro.getPttgId());
							plateTwoTarList.addAll(plateTwoTarListTemp);
						}
					}
					List<String> targetList = new ArrayList<String>();
					// 是否含有三级机构的指标
					Boolean hasThreeOrg = false;
					// 是否含有四级机构的指标
					Boolean hasFourOrg = false;
					// 判断是营销服务部的还是成本中心的:如果所有的指标中有营销服务部的（培训部，个业部）的，则显示为营销服务部，否则显示为成本中心
					Boolean isServer = false;
					for (PlateOneTar plateOneTar : plateOneTarList) {
						String targetCode = plateOneTar.getTargetCode();
						if (commonUtil.isGeYeAndPeiXunDept(blockService.findTargetByCode(targetCode).getDeptCode())) {
							isServer = true;
						}
						if (targetCode.substring(0, 3).equals("T07")) {
							hasThreeOrg = true;
						}
						if (targetCode.substring(0, 3).equals("T08")) {
							hasFourOrg = true;
						}
						targetList.add(plateOneTar.getTargetCode());
					}
					for (PlateTwoTar plateTwoTar : plateTwoTarList) {
						String targetCode = plateTwoTar.getTargetCode();
						if (commonUtil.isGeYeAndPeiXunDept(blockService.findTargetByCode(targetCode).getDeptCode())) {
							isServer = true;
						}
						if (targetCode.substring(0, 3).equals("T07")) {
							hasThreeOrg = true;
						}
						if (targetCode.substring(0, 3).equals("T08")) {
							hasFourOrg = true;
						}
						targetList.add(plateTwoTar.getTargetCode());
					}
					// 封装伪sql下转区域月参数
					String belowSql = "";
					String belowTarsSql = "SELECT '机构' AS VAL0, '' AS P_VAL, ";
					String belowBaseSql = "FROM (SELECT DISTINCT {orgcode_group_code} AS P_VAL, {orgcode_group_name} AS X_VAL FROM D_ORGANIZATION WHERE {orgcode_condition}) T00 ";
					String belowDataSql = "SELECT T00.X_VAL AS VAL0, T00.P_VAL AS P_VAL, ";
					String belowTempSql = "";
					if (roleOrg.length() == 5) {
						belowBaseSql = sqlFactory.getBkfwSql(SqlConstants.BKFW_04, belowBaseSql, ptOrg, isServer);
					} else {
						belowBaseSql = sqlFactory.getBkfwSql(SqlConstants.BKFW_02, belowBaseSql, ptOrg, isServer);
					}
					if (null != plateOneTarList) {
						for (PlateOneTar target1Obj : plateOneTarList) {
							String targetCode = target1Obj.getTargetCode();
							Target targetBean = blockService.findTargetByCode(targetCode);
							// 该指标的最低机构
							int minOrg = Integer.parseInt(blockService.findGroDetailOrgCodeByTarget(targetCode));
							// 总区域一级指标月sql拼装
							TarInitSql belowTarInitSql = blockService.findBelowTarInitSql(targetCode);
							int belowIndex = plateOneTarList.indexOf(target1Obj) + 1;
							String belowSqlCode = belowTarInitSql.getSqlCode();
							if (roleOrg.length() == 5) {
								if (minOrg < nextOrg) {
									belowSqlCode = sqlFactory.getBkfwDownSql(SqlConstants.BKFW_DOWN03, belowSqlCode,
											ptOrg, isServer);
								} else {
									belowSqlCode = sqlFactory.getBkfwDownSql(SqlConstants.BKFW_DOWN04, belowSqlCode,
											ptOrg, isServer);
								}
							} else {
								if (minOrg < nextOrg) {
									belowSqlCode = sqlFactory.getBkfwDownSql(SqlConstants.BKFW_DOWN01, belowSqlCode,
											ptOrg, isServer);
								} else {
									belowSqlCode = sqlFactory.getBkfwDownSql(SqlConstants.BKFW_DOWN02, belowSqlCode,
											ptOrg, isServer);
								}
							}
							belowTarsSql = belowTarsSql + "'" + targetBean.getTargetName() + "' AS VAL" + belowIndex
									+ ", ";
							belowDataSql = belowDataSql + "NVL(T0" + belowIndex + ".Y_VAL,'--') AS VAL" + belowIndex
									+ ", ";
							belowTempSql = belowTempSql + " LEFT JOIN ( " + belowSqlCode + " ) T0" + belowIndex
									+ " ON T00.P_VAL  = T0" + belowIndex + ".P_VAL";
						}
					}
					if (null != plateTwoTarList) {
						for (PlateTwoTar target2Obj : plateTwoTarList) {
							String targetCode = target2Obj.getTargetCode();
							Target targetBean = blockService.findTargetByCode(targetCode);
							// 该指标的最低机构
							int minOrg = Integer.parseInt(blockService.findGroDetailOrgCodeByTarget(targetCode));
							// 总区域二级指标月sql拼装
							TarInitSql belowTarInitSql = blockService.findBelowTarInitSql(targetCode);
							int belowIndex = plateOneTarList.size() + plateTwoTarList.indexOf(target2Obj) + 1;
							String belowSqlCode = belowTarInitSql.getSqlCode();
							if (roleOrg.length() == 5) {
								if (commonUtil.isDrilldown(minOrg, nextOrg)) {
									belowSqlCode = sqlFactory.getBkfwDownSql(SqlConstants.BKFW_DOWN03, belowSqlCode,
											ptOrg, isServer);
								} else {
									belowSqlCode = sqlFactory.getBkfwDownSql(SqlConstants.BKFW_DOWN04, belowSqlCode,
											ptOrg, isServer);
								}
							} else {
								if (commonUtil.isDrilldown(minOrg, nextOrg)) {
									belowSqlCode = sqlFactory.getBkfwDownSql(SqlConstants.BKFW_DOWN01, belowSqlCode,
											ptOrg, isServer);
								} else {
									belowSqlCode = sqlFactory.getBkfwDownSql(SqlConstants.BKFW_DOWN02, belowSqlCode,
											ptOrg, isServer);
								}
							}
							belowTarsSql = belowTarsSql + "'" + targetBean.getTargetName() + "' AS VAL" + belowIndex
									+ ", ";
							belowDataSql = belowDataSql + "NVL(T0" + belowIndex + ".Y_VAL,'--') AS VAL" + belowIndex
									+ ", ";
							belowTempSql = belowTempSql + " LEFT JOIN ( " + belowSqlCode + " ) T0" + belowIndex
									+ " ON T00.P_VAL  = T0" + belowIndex + ".P_VAL";
						}
					}
					// 下转区域月sql拼装
					belowTarsSql = belowTarsSql.substring(0, belowTarsSql.length() - 2) + " FROM DUAL UNION ALL ";
					belowDataSql = belowDataSql.substring(0, belowDataSql.length() - 2) + " ";
					belowSql = belowTarsSql + belowDataSql + belowBaseSql + belowTempSql;
					resultMap.put("sql", belowSql);
					resultMap.put("isServer", isServer);
					resultMap.put("hasThreeOrg", hasThreeOrg);
					resultMap.put("hasFourOrg", hasFourOrg);
					this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
					dataPrintTips(4, resultMap);
				} else {
					// 是否含有三级机构的指标
					Boolean hasThreeOrg = false;
					// 是否含有四级机构的指标
					Boolean hasFourOrg = false;
					// 板块code
					String blockCode = jsonObject.getString("blockCode");
					if (blockCode.equals("P01")) {
						hasThreeOrg = true;
					}
					if (blockCode.equals("P02")) {
						hasFourOrg = true;
					}
					// 固定板块
					TarReg tarReg = blockService.findTarRegByRegAndSub(temReg.getRegId(), blockCode);
					TarRegSql tarRegSql = blockService.findTarRegSql(3, tarReg.getTargetCode(), temReg.getRegId(),
							"ORG_GROUP", null, "MONTH");
					Target target = blockService.findTargetByCode(tarReg.getTargetCode());
					Boolean isServer = commonUtil.isGeYeAndPeiXunDept(target.getDeptCode());
					String sqlCode = sqlFactory.getBkfwDownSql(SqlConstants.BKFW_DOWN01, tarRegSql.getSqlCode(), ptOrg,
							isServer);
					tarRegTabHeadList = blockService.findTarRegTabHeadByRegAndTar(temReg.getRegId(),
							tarReg.getTargetCode());
					resultMap.put("sql", sqlCode);
					resultMap.put("head", tarRegTabHeadList);
					resultMap.put("isServer", isServer);
					resultMap.put("hasThreeOrg", hasThreeOrg);
					resultMap.put("hasFourOrg", hasFourOrg);
					this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
					dataPrintTips(4, resultMap);
				}
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {我的板块列表}05
	 * @作者 MaxBill
	 * @时间 2017年7月24日 下午4:44:44
	 */
	public ResponseBeanDto myBlockList(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String userName = jsonObject.getString("username");
			String role = jsonObject.getString("role");
			String blockName = jsonObject.getString("blockName");
			Integer curPage = jsonObject.getInteger("curPage");
			Integer pageSize = jsonObject.getInteger("pageSize");
			Pager<Target> pager = new Pager<Target>(curPage, pageSize);
			DataPageBean dataPageBean = blockService.myPlateList(pager, role, userName, blockName);
			// 成功响应处理
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
	 * @功能 {获取指标数据}06
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午6:39:00
	 */
	public ResponseBeanDto getTarget(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String roleOrg = jsonObject.getString("roleOrg");
			Integer blockId = jsonObject.getInteger("blockId");// 板块编号
			if (null != blockId) {
				// 编辑
				List<Object> objectList = blockService.findEditBlockTwoTargetByParam(role, blockId, roleOrg);
				// 查询一级指标
				List<TargetBean> target1List = blockService.findEditBlockOneTargetByParam(role, blockId, roleOrg);
				List<TargetBean> subject1List = blockService.findBlockOneTargetTypeByParam(role, blockId, roleOrg);
				List<TargetBean> subject2List = blockService.findBlockTwoTargetTypeByParam(role, blockId, roleOrg);
				resultMap.put("target1", target1List);
				resultMap.put("target2", objectList.get(0));
				resultMap.put("subject1", subject1List);
				resultMap.put("subject2", subject2List);
				resultMap.put("groups", objectList.get(1));
			} else {
				// 添加
				// 查询一级指标
				List<TargetBean> target1List = blockService.findSaveBlockTargetByParam("TEMP03_REG02", role, roleOrg);
				List<TargetBean> subject1List = blockService.findBlockOneTargetTypeByParam(role, blockId, roleOrg);
				// 查询二级指标
				List<TargetBean> target2List = blockService.findSaveBlockTargetByParam("TEMP03_REG03", role, roleOrg);
				List<TargetBean> subject2List = blockService.findBlockTwoTargetTypeByParam(role, blockId, roleOrg);
				resultMap.put("target1", target1List);
				resultMap.put("target2", target2List);
				resultMap.put("subject1", subject1List);
				resultMap.put("subject2", subject2List);
				resultMap.put("groups", new ArrayList());
			}
			// 成功响应处理
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
	 * @功能 {保存板块接口}07
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午10:37:55
	 */
	public ResponseBeanDto saveBlock(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String username = jsonObject.getString("username");
			String roleOrg = jsonObject.getString("roleOrg");
			String roleCode = jsonObject.getString("role");
			String blockName = jsonObject.getString("blockName");
			String target1 = jsonObject.getString("target1");// 一级指标
			String target2 = jsonObject.getString("target2");// 关联的二级指标
			CustPlate blockBean = new CustPlate();
			blockBean.setUsername(username);
			blockBean.setPlateName(blockName);
			blockBean.setRoleCode(roleCode);
			blockBean.setOpType("A");
			blockBean.setCreateTime(new Date());
			int addFlag = blockService.savePlate(blockBean, target1, target2, roleOrg);
			// 成功响应处理
			if (addFlag == 1) {
				this.dataSuccessTips("{\"msg\":\"添加成功\"}", responseBeanDto);
			} else {
				this.dataSuccessTips("{\"msg\":\"添加失败\"}", responseBeanDto);
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {编辑板块接口}08
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午10:37:55
	 */
	public ResponseBeanDto editBlock(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");
			Integer blockId = jsonObject.getInteger("blockId");
			String username = jsonObject.getString("username");
			String blockName = jsonObject.getString("blockName");
			String target1 = jsonObject.getString("target1");// 一级指标
			String target2 = jsonObject.getString("target2");// 关联的二级指标
			CustPlate blockBean = blockService.findCustPlateById(blockId);
			blockBean.setUsername(username);
			blockBean.setPlateName(blockName);
			blockBean.setOpType("U");
			blockBean.setModifyTime(new Date());
			int addFlag = blockService.editPlate(blockBean, target1, target2, roleOrg);
			// 成功响应处理
			if (addFlag == 1) {
				// 去除自定义板块REDIS缓存
				String redisKeyOver = RedisConstants.BKFW_OVER + "-" + blockId + "-" + roleOrg;
				String redisKeyDown = RedisConstants.BKFW_OVER + "-" + blockId + "-" + roleOrg;
				RedisUtil.delByPattern(redisKeyOver + "*");
				RedisUtil.delByPattern(redisKeyDown + "*");
				System.out.println(">>>>>>>>>>:用户修改了[" + blockName + "(" + blockId + ")]自定义板块");
				System.out.println(">>>>>>>>>>:系统清除了[" + blockName + "(" + blockId + ")]自定义板块上部区域缓存数据...");
				System.out.println(">>>>>>>>>>:" + redisKeyOver);
				System.out.println(">>>>>>>>>>:系统清除了[" + blockName + "(" + blockId + ")]自定义板块下钻区域缓存数据...");
				System.out.println(">>>>>>>>>>:" + redisKeyDown);
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
	 * @功能 {删除板块信息}09
	 * @作者 MaxBill
	 * @时间 2017年7月24日 下午4:48:12
	 */
	public ResponseBeanDto deleteBlock(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			Integer id = jsonObject.getInteger("id");// 板块编号
			int delFlag = blockService.deletePlate(id);
			// 成功响应处理
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
	 * @功能 {主区域的指标数据}10
	 * @作者 MaxBill
	 * @时间 2017年9月8日 下午1:37:51
	 */
	public ResponseBeanDto getMainTarget(String json) {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String blockCode = jsonObject.getString("blockCode");
			String groupbyDate = jsonObject.getString("groupbyDate");
			Boolean isCustom = jsonObject.getBoolean("isCustom");// 是否是自定义板块
			if (isCustom) {
				// 自定义板块
				Integer blockId = jsonObject.getInteger("blockCode");
				List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
				List<PlateOneTar> plateOneTarList = blockService.findPlateOneTarByBlockWithDate(blockId, groupbyDate);
				if (null != plateOneTarList) {
					for (PlateOneTar plateOneTar : plateOneTarList) {
						TargetBean targetBeans = new TargetBean();
						String plateOneTarCode = plateOneTar.getTargetCode();
						targetBeans.setCode(plateOneTarCode);
						targetBeans.setName(blockService.findTargetNameByCode(plateOneTar.getTargetCode()));
						targetBeans.setChecked(false);
						targetBeanList.add(targetBeans);
					}
				}
				resultMap.put("targets", targetBeanList);
			} else {
				// 查询区域信息
				TemReg temReg = blockService.findRegionByCode("TEMP03_REG02");
				List<TargetBean> targetList = blockService.findTargetByRoleAndSubAndRegWithTarReg(role, blockCode,
						temReg.getRegId(), groupbyDate);
				resultMap.put("targets", targetList);
			}
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			dataPrintTips(10, resultMap);
		} catch (Exception e) {
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {sub区域的指标数据}11
	 * @作者 MaxBill
	 * @时间 2017年9月8日 下午1:37:51
	 */
	public ResponseBeanDto getSubTarget(String json) {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		BlockService blockService = SpringUtil.getBean(BlockService.class);
		try {
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String username = jsonObject.getString("username");
			String groupbyDate = jsonObject.getString("groupbyDate");
			Boolean isCustom = jsonObject.getBoolean("isCustom");// 是否是自定义板块
			if (isCustom) {
				Integer level = jsonObject.getInteger("level");
				// 自定义板块
				Integer blockId = jsonObject.getInteger("blockCode");
				// 查询二级指标分组
				List<TargetBean> targetBeanLevelList = new ArrayList<TargetBean>();
				List<PlateTwoTarGro> plateTwoTarGroList = blockService.findPlateTwoTarGroByBlock(blockId);
				if (null != plateTwoTarGroList) {
					for (PlateTwoTarGro plateTwoTarGro : plateTwoTarGroList) {
						TargetBean targetBeans = new TargetBean();
						targetBeans.setCode(plateTwoTarGro.getPttgId().toString());
						targetBeans.setName(plateTwoTarGro.getTwoGroupName());
						targetBeans.setChecked(false);
						targetBeanLevelList.add(targetBeans);
					}
				}
				// 查询二级分组下的默认指标
				List<String> defaultTarList = blockService.findDefaultTarByTempAndSubAndUser(TEMPLET_CODE_BK,
						blockId.toString(), username, role, level.toString());
				if (null != defaultTarList && !defaultTarList.isEmpty()) {
					resultMap.put("defaultTarget", defaultTarList);
				} else {
					String defaultTarget2 = blockService.findPlateTwoTarByGroup(level).get(0).getTargetCode();
					resultMap.put("defaultTarget", new String[] { defaultTarget2 });
				}
				// 查询二级指标
				List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
				List<PlateTwoTar> plateTwoTarList = blockService.findPlateTwoTarByGroupWithDate(level, groupbyDate);
				for (PlateTwoTar plateTwoTar : plateTwoTarList) {
					TargetBean targetBean = new TargetBean();
					targetBean.setCode(plateTwoTar.getTargetCode());
					targetBean.setName(blockService.findTargetNameByCode(plateTwoTar.getTargetCode()));
					targetBean.setChecked(false);
					targetBeanList.add(targetBean);
				}
				resultMap.put("levels", targetBeanLevelList);
				resultMap.put("targets", targetBeanList);
			} else {
				String blockCode = jsonObject.getString("blockCode");
				String level = jsonObject.getString("level");
				List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
				List<Target> targetList = blockService.findTargetBysubAndType(role, "3", level, groupbyDate);
				if (null != targetList) {
					for (Target target : targetList) {
						TargetBean targetBean = new TargetBean();
						targetBean.setCode(target.getTargetCode());
						targetBean.setName(target.getTargetName());
						targetBean.setChecked(false);
						targetBeanList.add(targetBean);
					}
				}
				resultMap.put("targets", targetBeanList);
				// 查询二级分组下的默认指标
				List<String> defaultTarList = blockService.findDefaultTarByTempAndSubAndUser(TEMPLET_CODE_BK, blockCode,
						username, role, level);
				if (null != defaultTarList && !defaultTarList.isEmpty()) {
					resultMap.put("defaultTarget", defaultTarList);
				} else {
					resultMap.put("defaultTarget", Collections.EMPTY_LIST);
				}
				// 查询二级指标分类数据
				List<TargetBean> targetBeanLevelList = new ArrayList<TargetBean>();
				List<Target> levelTarget = blockService.findTargetBysubAndTypeBack(role, "2", blockCode);
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
			}
			this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
			dataPrintTips(11, resultMap);
		} catch (Exception e) {
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {板块趋势图基本接口}12
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午4:08:48
	 */
	public ResponseBeanDto getBaseTrend(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map map = new HashMap<>();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			CommonService commonService = SpringUtil.getBean(CommonService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String roleDept = jsonObject.getString("roleDept");// 角色部门
			Boolean isCustom = jsonObject.getBoolean("isCustom");// 板块标识
			String username = jsonObject.getString("username");// 用户名
			String ptOrg = jsonObject.getString("ptOrg");
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			// 判断机构
			String org = "".equals(ptOrg) ? roleOrg : ptOrg;
			String orgTemp = org;
			if (!StringUtils.isEmpty(orgCode)) {
				org = orgCode;
			}
			if (isCustom) {
				// 是否含有三级机构的指标
				Boolean hasThreeOrg = false;
				// 是否含有四级机构的指标
				Boolean hasFourOrg = false;
				// 自定义板块
				Integer blockId = jsonObject.getInteger("blockCode");// 自定义版块id
				// 查询时间指标
				List<GroDimDetailBean> timeReqs = new ArrayList<GroDimDetailBean>();
				List<GroDimDetail> groDimDetails = commonService.findGroDetailListBySubWithDateAndPlate(blockId);
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
				List<String> moduleCode = new ArrayList<>();
				List<TemReg> temRegList = blockService.findTemRegListByBlock(blockId);
				for (TemReg temReg : temRegList) {
					if (!temReg.getRegCode().equals("TEMP03_REG06")) {
						moduleCode.add(temReg.getRegCode());
					}
				}
				map.put("modules", moduleCode);
				// 查询默认一级指标
				List<String> defaultTarList = blockService.findDefaultTarByTempAndSubAndUser(TEMPLET_CODE_BK,
						blockId.toString(), username, role, "");
				if (null != defaultTarList && !defaultTarList.isEmpty()) {
					map.put("defaultTarget1", defaultTarList);
				} else {
					String defaultTarget1 = blockService.findBlockDefaultOneTarget(blockId).getTargetCode();
					map.put("defaultTarget1", new String[] { defaultTarget1 });
				}
				// 查询默认二级指标分类
				PlateTwoTarGro defaultTarget2 = blockService.findBlockDefaultTwoTarget(blockId);
				map.put("defaultTarget2", defaultTarget2.getPttgId());
				// 判断是否显示机构对比
				// 查询一级指标
				List<PlateOneTar> plateOneTarList = blockService.findPlateOneTarByBlock(blockId);
				// 查询二级指标
				List<PlateTwoTar> plateTwoTarList = new ArrayList<PlateTwoTar>();
				// 查询二级指标组
				List<PlateTwoTarGro> plateTwoTarGroList = blockService.findPlateTwoTarGroByBlock(blockId);
				if (null != plateTwoTarGroList) {
					for (PlateTwoTarGro plateTwoTarGro : plateTwoTarGroList) {
						// 查询二级指标分组下的二级指标
						List<PlateTwoTar> plateTwoTarListTemp = blockService
								.findPlateTwoTarByGroup(plateTwoTarGro.getPttgId());
						plateTwoTarList.addAll(plateTwoTarListTemp);
					}
				}
				boolean isOrgCompare = false;
				int num = 0;
				for (PlateOneTar plateOneTar : plateOneTarList) {
					String plateOneTarget = plateOneTar.getTargetCode();
					TarGroDim tarGroDim = blockService.findTarGroDimByTar(plateOneTarget);
					if (commonUtil.hasRoleOrgCompare(
							Integer.parseInt(blockService.findGroDimDetailById(tarGroDim.getGddId()).getGroDimCode()),
							roleOrg.length())) {
						num += 1;
					}
					if (plateOneTarget.contains("T07")) {
						hasThreeOrg = true;
					}
					if (plateOneTarget.contains("T08")) {
						hasFourOrg = true;
					}
				}
				if (num > 0) {
					isOrgCompare = true;
				}
				if (isOrgCompare) {
					int num_2 = 0;
					for (PlateTwoTar plateTwoTar : plateTwoTarList) {
						String plateTwoTarget = plateTwoTar.getTargetCode();
						TarGroDim tarGroDim = blockService.findTarGroDimByTar(plateTwoTarget);
						if (commonUtil.hasRoleOrgCompare(
								Integer.parseInt(
										blockService.findGroDimDetailById(tarGroDim.getGddId()).getGroDimCode()),
								roleOrg.length())) {
							num_2 += 1;
						}
						if (plateTwoTarget.contains("T07")) {
							hasThreeOrg = true;
						}
						if (plateTwoTarget.contains("T08")) {
							hasFourOrg = true;
						}
					}
					if (num_2 == 0) {
						isOrgCompare = false;
					}
				}
				// 判断角色机构下是否有多个下级，大于1个则展示
				if (isOrgCompare) {
					List<OrgBean> orgList = null;
					if (!StringUtils.isEmpty(orgCode)) {
						String orgType = jsonObject.getString("orgType");
						switch (orgType) {
						case "7":
							// 分公司
							orgList = blockService.getOrgList(2, orgCode, null);
							break;
						case "6":
							// 中支
							orgList = blockService.getOrgList(3, orgCode, roleDept);
							break;
						case "501":
							// 营销服务部
							orgList = blockService.getOrgList(41, orgCode, null);
							break;
						case "502":
							// 成本中心
							orgList = blockService.getOrgList(42, orgCode, null);
							break;
						}
						/*
						 * if(hasThreeOrg && orgCode.length()>=5) { isOrgCompare = false; }
						 * if(hasThreeOrg && hasFourOrg) { isOrgCompare = true; }
						 */
					} else {
						if (!StringUtils.isEmpty(ptOrg)) {
							switch (ptOrg.length()) {
							case 1:
								// 总公司
								orgList = blockService.getOrgList(1, ptOrg, null);
								break;
							case 3:
								// 分公司
								orgList = blockService.getOrgList(2, ptOrg, null);
								break;
							case 5:
								// 中支
								orgList = blockService.getOrgList(3, ptOrg, roleDept);
								break;
							default:
								// 营销服务部
								orgList = blockService.getOrgList(43, ptOrg, roleDept);
								isOrgCompare = false;
								break;
							}
							if (hasThreeOrg && ptOrg.length() >= 5) {
								isOrgCompare = false;
							}
							if (hasThreeOrg && hasFourOrg) {
								isOrgCompare = true;
							}
						} else {
							switch (roleOrg.length()) {
							case 1:
								// 总公司
								orgList = blockService.getOrgList(1, roleOrg, null);
								break;
							case 3:
								// 分公司
								orgList = blockService.getOrgList(2, roleOrg, null);
								break;
							case 5:
								// 中支
								orgList = blockService.getOrgList(3, roleOrg, roleDept);
								break;
							default:
								// 成本中心
								orgList = blockService.getOrgList(42, roleOrg, null);
								isOrgCompare = false;
								break;
							}
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
						if (hasThreeOrg || hasFourOrg) {
							map.put("isSearch", false);
						} else {
							map.put("isSearch", true);
						}
					} else {
						map.put("isSearch", true);
					}
				}
				// 处理筛选维度
				Map<String, Object> queryDimBeanMap = new HashMap<String, Object>();
				// 查询筛选维度
				List<QueryDim> queryDimList = blockService.findQueryDimByTar(blockId);
				if (null != queryDimList) {
					for (QueryDim queryDim : queryDimList) {
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
								if (!queryDimBeanMap.containsKey(queryDimBean.getValue())) {
									queryDimBeanMap.put((String) queryDimBean.getValue(), queryDimBean);
								}
							}
						}
					}
				}
				map.put("filterReq", queryDimBeanMap.values());
			} else {
				// 固定板块
				String blockCode = jsonObject.getString("blockCode");// 版块编码
				// 查询时间指标
				List<GroDimDetailBean> timeReqs = new ArrayList<GroDimDetailBean>();
				List<GroDimDetail> groDimDetails = commonService.findGroDetailListBySubWithDate(blockCode);
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
				List<String> modules = blockService.findRegionByRoleorgAndSubject(TEMPLET_CODE_ZT, roleOrg, blockCode,
						role);
				map.put("modules", modules);
				// 查询默认一级指标
				List<String> defaultTarList = blockService.findDefaultTarByTempAndSubAndUser(TEMPLET_CODE_BK, blockCode,
						username, role, "");
				if (null != defaultTarList && !defaultTarList.isEmpty()) {
					map.put("defaultTarget1", defaultTarList);
				} else {
					String defaultTarget1 = blockService.findDefaultTargetByLevel(blockCode, "1", "MONTH")
							.getTargetCode();
					map.put("defaultTarget1", new String[] { defaultTarget1 });
					// List<String> targetList = new ArrayList();
					// targetList.add(defaultTarget1);
					// blockService.saveUserDefTarget(targetList, username, role, blockCode, "");
				}
				// 查询默认二级指标分类
				Target defaultTarget2 = blockService.findDefaultTargetByLevelGroup(blockCode, "2");
				map.put("defaultTarget2", defaultTarget2.getTargetCode());
				// 查询主题数据
				Target subBean = blockService.findTargetByCode(blockCode);
				// 是否显示区间统计
				if (subBean.getIsStatis().equals("1")) {
					map.put("isRegion", true);
				} else {
					map.put("isRegion", false);
				}
				// 判断是否显示机构对比
				boolean isOrgCompare = false;
				List<TarReg> tarRegList = blockService.findTarRegsBySubAndRole(TEMPLET_CODE_ZT, blockCode, role);
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
					if (!StringUtils.isEmpty(orgCode)) {
						String orgType = jsonObject.getString("orgType");
						switch (orgType) {
						case "7":
							// 分公司
							orgList = blockService.getOrgList(2, orgCode, null);
							break;
						case "6":
							// 中支
							orgList = blockService.getOrgList(3, orgCode, roleDept);
							break;
						case "501":
							// 营销服务部
							orgList = blockService.getOrgList(41, orgCode, null);
							break;
						case "502":
							// 成本中心
							orgList = blockService.getOrgList(42, orgCode, null);
							break;
						}
					} else {
						if (!StringUtils.isEmpty(ptOrg)) {
							switch (ptOrg.length()) {
							case 1:
								// 总公司
								orgList = blockService.getOrgList(1, ptOrg, null);
								break;
							case 3:
								// 分公司
								orgList = blockService.getOrgList(2, ptOrg, null);
								break;
							case 5:
								// 中支
								orgList = blockService.getOrgList(3, ptOrg, roleDept);
								break;
							default:
								// 营销服务部/成本中心
								orgList = blockService.getOrgList(43, ptOrg, roleDept);
								break;
							}
						} else {
							switch (roleOrg.length()) {
							case 1:
								// 总公司
								orgList = blockService.getOrgList(1, roleOrg, null);
								break;
							case 3:
								// 分公司
								orgList = blockService.getOrgList(2, roleOrg, null);
								break;
							case 5:
								// 中支
								orgList = blockService.getOrgList(3, roleOrg, roleDept);
								break;
							default:
								// 成本中心
								orgList = blockService.getOrgList(42, roleOrg, null);
								break;
							}
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
						if (blockCode.equals("P01") || blockCode.equals("P02")) {
							map.put("isSearch", false);
						} else {
							map.put("isSearch", true);
						}
					} else {
						map.put("isSearch", true);
					}
				}
			}
			// 成功响应处理
			this.dataSuccessTips(JSON.toJSONString(map), responseBeanDto);
			dataPrintTips(12, map);
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {常用核心指标接口}13
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午7:45:46
	 */
	public ResponseBeanDto getCoreTrend(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String ptOrg = jsonObject.getString("ptOrg");// 机构编码
			String groupbyDate = jsonObject.getString("groupbyDate");// 时间维度
			String targetCode = jsonObject.getString("targetCode");// 指标编码
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选条件
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP03_REG01");
			TarReg tarReg = blockService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			Target target = blockService.findTargetByCode(targetCode);
			Boolean isServer = commonUtil.isGeYeAndPeiXunDept(target.getDeptCode());
			if (StringUtils.isEmpty(orgCode)) {
				if (null == ptOrg || ptOrg.equals("")) {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
						// 封装返回数据
						TarRegBean tarRegBean = new TarRegBean();
						TarRegSql tarRegSql = blockService.findTarRegSql(4, tarReg.getTargetCode(), temReg.getRegId(),
								null, null, groupbyDate);
						if (null != tarRegSql) {
							// sql语句参数拼装处理
							String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
									tarRegSql.getTrsId(), filters);
							sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, sqlCode, roleOrg, isServer);
							tarRegBean.setName(tarReg.getGraphTitle());
							tarRegBean.setSql(sqlCode);
							this.dataSuccessTips(JSON.toJSONString(tarRegBean), responseBeanDto);
							dataPrintTips(13, tarRegBean);
						} else {
							this.dataIsEmptyTips(jsonObject, responseBeanDto);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), ptOrg.length())) {// 原为roleOrg
						// 特殊处理北京
						if (ptOrg.equals("10101")) {
							ptOrg = "101";
						}
						// 封装返回数据
						TarRegBean tarRegBean = new TarRegBean();
						TarRegSql tarRegSql = blockService.findTarRegSql(4, tarReg.getTargetCode(), temReg.getRegId(),
								null, null, groupbyDate);
						if (null != tarRegSql) {
							String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
									tarRegSql.getTrsId(), filters);
							TarGroDim tarGroDim = blockService.findTarGroDimByTar(tarReg.getTargetCode());
							if (commonUtil.hasRoleOrg(Integer.parseInt(tarGroDim.getGroDimDetail().getGroDimCode()),
									ptOrg.length())) {
								if (roleOrg.length() == 5) {
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00401, sqlCode, ptOrg, isServer);
								} else {
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00102, sqlCode, ptOrg, isServer);
								}
							} else {
								sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00501, sqlCode, ptOrg, isServer);
							}
							tarRegBean.setName(tarReg.getGraphTitle());
							tarRegBean.setSql(sqlCode);
							this.dataSuccessTips(JSON.toJSONString(tarRegBean), responseBeanDto);
							dataPrintTips(13, tarRegBean);
						} else {
							this.dataIsEmptyTips(jsonObject, responseBeanDto);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				}
			} else {
				if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), orgCode.length())) {// 原为roleOrg
					// 封装返回数据
					TarRegBean tarRegBean = new TarRegBean();
					TarRegSql tarRegSql = blockService.findTarRegSql(4, tarReg.getTargetCode(), temReg.getRegId(), null,
							null, groupbyDate);
					if (null != tarRegSql) {
						String orgType = jsonObject.getString("orgType");
						String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
								tarRegSql.getTrsId(), filters);
						sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0101, sqlCode, orgCode, isServer,
								orgType);
						tarRegBean.setName(tarReg.getGraphTitle());
						tarRegBean.setSql(sqlCode);
						this.dataSuccessTips(JSON.toJSONString(tarRegBean), responseBeanDto);
						dataPrintTips(13, tarRegBean);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {一级指标区域接口}14
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午10:34:50
	 */
	public ResponseBeanDto getMainTrend(String json) {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色所在机构
			String targetCode = jsonObject.getString("targetCode");
			String ptOrg = jsonObject.getString("ptOrg");// 机构编码
			String groupbyDate = jsonObject.getString("groupbyDate");
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选维度
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP03_REG02");
			TarReg tarReg = blockService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			Target target = blockService.findTargetByCode(targetCode);
			Boolean isServer = commonUtil.isGeYeAndPeiXunDept(target.getDeptCode());
			if (StringUtils.isEmpty(orgCode)) {
				if (null == ptOrg || ptOrg.equals("")) {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
						TarRegSql tarRegSql = blockService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
								groupbyDate, null);
						if (null != tarRegSql) {
							String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
									tarRegSql.getTrsId(), filters);
							sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, sqlCode, roleOrg, isServer);
							TarRegBean tarRegBean = new TarRegBean();
							tarRegBean.setCode(targetCode);
							tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
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
							dataPrintTips(14, resultMap);
						} else {
							this.dataIsEmptyTips(jsonObject, responseBeanDto);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), ptOrg.length())) {
						// 特殊处理北京
						if (ptOrg.equals("10101")) {
							ptOrg = "101";
						}
						TarRegSql tarRegSql = blockService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
								groupbyDate, null);
						if (null != tarRegSql) {
							String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
									tarRegSql.getTrsId(), filters);
							TarGroDim tarGroDim = blockService.findTarGroDimByTar(tarReg.getTargetCode());
							if (commonUtil.hasRoleOrg(Integer.parseInt(tarGroDim.getGroDimDetail().getGroDimCode()),
									ptOrg.length())) {
								if (roleOrg.length() == 5) {
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00401, sqlCode, ptOrg, isServer);
								} else {
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00102, sqlCode, ptOrg, isServer);
								}
							} else {
								sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00501, sqlCode, ptOrg, isServer);
							}
							TarRegBean tarRegBean = new TarRegBean();
							tarRegBean.setCode(targetCode);
							tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
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
							dataPrintTips(14, resultMap);
						} else {
							this.dataIsEmptyTips(jsonObject, responseBeanDto);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				}
			} else {
				if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), orgCode.length())) {// 原为roleOrg
					TarRegSql tarRegSql = blockService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
							groupbyDate, null);
					if (null != tarRegSql) {
						String orgType = jsonObject.getString("orgType");
						String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
								tarRegSql.getTrsId(), filters);
						sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0101, sqlCode, orgCode, isServer,
								orgType);
						TarRegBean tarRegBean = new TarRegBean();
						tarRegBean.setCode(targetCode);
						tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
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
						dataPrintTips(14, resultMap);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
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
	 * @功能 {二级指标区域接口}15
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午11:09:46
	 */
	public ResponseBeanDto getSubsTrend(String json) {
		Map resultMap = new HashMap<>();
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		BlockService blockService = SpringUtil.getBean(BlockService.class);
		try {
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");
			String targetCode = jsonObject.getString("targetCode");
			String groupbyDate = jsonObject.getString("groupbyDate");
			String ptOrg = jsonObject.getString("ptOrg");// 机构编码
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选维度
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP03_REG03");
			TarReg tarReg = blockService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			Target target = blockService.findTargetByCode(targetCode);
			Boolean isServer = commonUtil.isGeYeAndPeiXunDept(target.getDeptCode());
			if (StringUtils.isEmpty(orgCode)) {
				if (null == ptOrg || ptOrg.equals("")) {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
						TarRegSql tarRegSql = blockService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
								groupbyDate, null);
						if (null != tarRegSql) {
							String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
									tarRegSql.getTrsId(), filters);
							sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, sqlCode, roleOrg, isServer);
							TarRegBean tarRegBean = new TarRegBean();
							tarRegBean.setCode(targetCode);
							tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
							tarRegBean.setType(tarReg.getGraphType());
							tarRegBean.setTitle(tarReg.getGraphTitle());
							tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
							tarRegBean.setStack("");
							tarRegBean.setColor(tarReg.getColor());
							tarRegBean.setSql(sqlCode);
							resultMap.put("targetData", tarRegBean);
							this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
							dataPrintTips(15, resultMap);
						} else {
							this.dataIsEmptyTips(jsonObject, responseBeanDto);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), ptOrg.length())) {
						// 特殊处理北京
						if (ptOrg.equals("10101")) {
							ptOrg = "101";
						}
						TarRegSql tarRegSql = blockService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
								groupbyDate, null);
						if (null != tarRegSql) {
							String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
									tarRegSql.getTrsId(), filters);
							TarGroDim tarGroDim = blockService.findTarGroDimByTar(tarReg.getTargetCode());
							if (commonUtil.hasRoleOrg(Integer.parseInt(tarGroDim.getGroDimDetail().getGroDimCode()),
									ptOrg.length())) {
								if (roleOrg.length() == 5) {
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00401, sqlCode, ptOrg, isServer);
								} else {
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00102, sqlCode, ptOrg, isServer);
								}
							} else {
								sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00501, sqlCode, ptOrg, isServer);
							}
							TarRegBean tarRegBean = new TarRegBean();
							tarRegBean.setCode(targetCode);
							tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
							tarRegBean.setType(tarReg.getGraphType());
							tarRegBean.setTitle(tarReg.getGraphTitle());
							tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
							tarRegBean.setStack("");
							tarRegBean.setColor(tarReg.getColor());
							tarRegBean.setSql(sqlCode);
							resultMap.put("targetData", tarRegBean);
							this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
							dataPrintTips(15, resultMap);
						} else {
							this.dataIsEmptyTips(jsonObject, responseBeanDto);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				}
			} else {
				if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), orgCode.length())) {// 原为roleOrg
					TarRegSql tarRegSql = blockService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
							groupbyDate, null);
					if (null != tarRegSql) {
						String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
								tarRegSql.getTrsId(), filters);
						String orgType = jsonObject.getString("orgType");
						sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0101, sqlCode, orgCode, isServer,
								orgType);
						TarRegBean tarRegBean = new TarRegBean();
						tarRegBean.setCode(targetCode);
						tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
						tarRegBean.setType(tarReg.getGraphType());
						tarRegBean.setTitle(tarReg.getGraphTitle());
						tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
						tarRegBean.setStack("");
						tarRegBean.setColor(tarReg.getColor());
						tarRegBean.setSql(sqlCode);
						resultMap.put("targetData", tarRegBean);
						this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
						dataPrintTips(15, resultMap);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
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
	 * @功能 {分公司/机构区域接口}16
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午8:28:26
	 */
	public ResponseBeanDto getDistTrend(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			Map resultMap = new HashMap();
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String ptOrg = jsonObject.getString("ptOrg");// 上级机构代码
			String groupbyDate = jsonObject.getString("groupbyDate");// 时间维度
			String targetCode = jsonObject.getString("targetCode");// 指标代码
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选维度
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP03_REG04");
			TarReg tarReg = blockService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			Target target = blockService.findTargetByCode(targetCode);
			String targetDeptCode = target.getDeptCode();
			String subject = targetCode.substring(0, 3);// 主题编码
			Boolean isServer = commonUtil.isGeYeAndPeiXunDept(targetDeptCode);
			if (null == ptOrg || ptOrg.equals("")) {
				if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
					TarRegSql tarRegSql = blockService.findTarRegSql(3, targetCode, temReg.getRegId(), "ORG_GROUP",
							null, groupbyDate);
					if (null != tarRegSql) {
						TarRegBean tarRegBean = new TarRegBean();// 封装参数
						resultMap.put("name", tarReg.getGraphTitle());
						resultMap.put("stacking", "");
						resultMap.put("xAxis", "");
						tarRegBean.setCode(tarReg.getTargetCode());
						tarRegBean.setName(target.getTargetName());
						tarRegBean.setStack("");
						tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
						tarRegBean.setType(tarReg.getGraphType());
						tarRegBean.setColor(tarReg.getColor());
						// 参数处理
						String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
								tarRegSql.getTrsId(), filters);
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
									sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0201, sqlCode, orgCode,
											isServer, orgType);
									tarRegBean.setData(sqlCode);
									resultMap.put("series", Arrays.asList(tarRegBean));
									this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
									dataPrintTips(16, resultMap);
									break;
								// 中支
								case "6":
									// 此处是四级机构
									if (subject.equals("T07") || subject.equals("P01")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0202, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
									}
									break;
								// 四级机构
								default:
									// 此处是区机构
									if (subject.equals("T07") || subject.equals("P01") || subject.equals("T08")
											|| subject.equals("P02")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0203, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
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
									if (subject.equals("T07") || subject.equals("P01")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0202, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
									}
									break;
								// 四级机构
								default:
									// 此处是区机构
									if (subject.equals("T07") || subject.equals("P01") || subject.equals("T08")
											|| subject.equals("P02")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0203, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
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
									if (subject.equals("T07") || subject.equals("P01") || subject.equals("T08")
											|| subject.equals("P02")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0203, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
									}
									break;
								}
								break;
							}
							// 按搜索机构处理
							if (orgCode.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
						} else {
							// 按用户登陆机构处理
							sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00201, sqlCode, roleOrg, isServer);
							if (roleOrg.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
							tarRegBean.setData(sqlCode);
							resultMap.put("series", Arrays.asList(tarRegBean));
							this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
							dataPrintTips(16, resultMap);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}
			} else {
				if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), ptOrg.length())) {
					TarRegSql tarRegSql = blockService.findTarRegSql(3, targetCode, temReg.getRegId(), "ORG_GROUP",
							null, groupbyDate);
					if (null != tarRegSql) {
						TarRegBean tarRegBean = new TarRegBean();// 封装参数
						resultMap.put("name", tarReg.getGraphTitle());
						resultMap.put("stacking", "");
						resultMap.put("xAxis", "");
						tarRegBean.setCode(tarReg.getTargetCode());
						tarRegBean.setName(target.getTargetName());
						tarRegBean.setStack("");
						tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
						tarRegBean.setType(tarReg.getGraphType());
						tarRegBean.setColor(tarReg.getColor());
						// 参数处理
						String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
								tarRegSql.getTrsId(), filters);
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
									sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0201, sqlCode, orgCode,
											isServer, orgType);
									tarRegBean.setData(sqlCode);
									resultMap.put("series", Arrays.asList(tarRegBean));
									this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
									dataPrintTips(16, resultMap);
									break;
								// 中支
								case "6":
									// 此处是四级机构
									if (subject.equals("T07") || subject.equals("P01")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0202, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
									}
									break;
								// 四级机构
								default:
									// 此处是区机构
									if (subject.equals("T07") || subject.equals("P01") || subject.equals("T08")
											|| subject.equals("P02")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0203, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
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
									if (subject.equals("T07") || subject.equals("P01")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0202, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
									}
									break;
								// 四级机构
								default:
									// 此处是区机构
									if (subject.equals("T07") || subject.equals("P01") || subject.equals("T08")
											|| subject.equals("P02")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0203, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
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
									if (subject.equals("T07") || subject.equals("P01") || subject.equals("T08")
											|| subject.equals("P02")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0203, sqlCode,
												orgCode, isServer, orgType);
										tarRegBean.setData(sqlCode);
										resultMap.put("series", Arrays.asList(tarRegBean));
										this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
										dataPrintTips(16, resultMap);
									}
									break;
								}
								break;
							}
							// 搜索机构处理
							if (orgCode.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
						} else {
							// 按上级机构处理
							if (roleOrg.length() == 5) {
								sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00401, sqlCode, ptOrg, isServer);
							} else {
								sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00202, sqlCode, ptOrg, isServer);
							}
							if (ptOrg.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
							tarRegBean.setData(sqlCode);
							resultMap.put("series", Arrays.asList(tarRegBean));
							this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
							dataPrintTips(16, resultMap);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {机构列表区域接口}17
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午9:31:02
	 */
	public ResponseBeanDto getOrgsTrend(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String distOrg = jsonObject.getString("distOrg");// 机构编码
			String targetCode = jsonObject.getString("targetCode");// 一级指标
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选维度
			String orgMark = jsonObject.getString("orgRange");// 机构层级
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP03_REG05");
			TarReg tarReg = blockService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			Target target = blockService.findTargetByCode(targetCode);
			String targetDeptCode = target.getDeptCode();
			String subject = targetCode.substring(0, 3);// 主题编码
			Boolean isServer = commonUtil.isGeYeAndPeiXunDept(targetDeptCode);
			if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
				TarRegSql tarRegSql = blockService.findTarRegSql(3, targetCode, temReg.getRegId(), "ORG_GROUP", null,
						groupbyDate);
				if (null != tarRegSql) {
					// 判断机构勾选是否能到
					TarGroDim tarGroDim = blockService.findTarGroDimByTar(tarReg.getTargetCode());
					if (commonUtil.isDrilldown(Integer.parseInt(tarGroDim.getGroDimDetail().getGroDimCode()),
							distOrg.length())) {
						List<Object> dataList = new ArrayList<Object>();
						// 封装表格头部信息
						List<String> tarRegTabHeadList = blockService.findTarRegTabHeadByRegAndTar(temReg.getRegId(),
								targetCode);
						dataList.add(tarRegTabHeadList);
						TarRegBean tarRegBean = new TarRegBean();
						String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
								tarRegSql.getTrsId(), filters);
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
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0301, sqlCode,
												distOrg, isServer, orgType);
										tarRegBean.setTitle(tarReg.getGraphTitle());
										tarRegBean.setSql(sqlCode);
										tarRegBean.setColor(tarReg.getColor());
										dataList.add(tarRegBean);
										this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
										dataPrintTips(17, dataList);
									}
									break;
								// 中支
								case "6":
									// 此处是四级机构
									if (subject.equals("T07") || subject.equals("T08")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0302, sqlCode,
												distOrg, isServer, orgType);
										tarRegBean.setTitle(tarReg.getGraphTitle());
										tarRegBean.setSql(sqlCode);
										tarRegBean.setColor(tarReg.getColor());
										dataList.add(tarRegBean);
										this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
										dataPrintTips(17, dataList);
									}
									break;
								default:
									// 此处是区机构
									if (subject.equals("T07") || subject.equals("T08")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0303, sqlCode,
												distOrg, isServer, orgType);
										tarRegBean.setTitle(tarReg.getGraphTitle());
										tarRegBean.setSql(sqlCode);
										tarRegBean.setColor(tarReg.getColor());
										dataList.add(tarRegBean);
										this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
										dataPrintTips(17, dataList);
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
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0302, sqlCode,
												distOrg, isServer, orgType);
										tarRegBean.setTitle(tarReg.getGraphTitle());
										tarRegBean.setSql(sqlCode);
										tarRegBean.setColor(tarReg.getColor());
										dataList.add(tarRegBean);
										this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
										dataPrintTips(17, dataList);
									}
									break;
								default:
									// 此处是区机构
									if (subject.equals("T07") || subject.equals("T08")) {
										this.dataIsEmptyTips(jsonObject, responseBeanDto);
									} else {
										sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0303, sqlCode,
												distOrg, isServer, orgType);
										tarRegBean.setTitle(tarReg.getGraphTitle());
										tarRegBean.setSql(sqlCode);
										tarRegBean.setColor(tarReg.getColor());
										dataList.add(tarRegBean);
										this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
										dataPrintTips(17, dataList);
									}
									break;
								}
								break;
							// 中支
							case 5:
								// 此处是区机构
								if (subject.equals("T07") || subject.equals("T08")) {
									this.dataIsEmptyTips(jsonObject, responseBeanDto);
								} else {
									sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0303, sqlCode, distOrg,
											isServer, orgType);
									tarRegBean.setTitle(tarReg.getGraphTitle());
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(17, dataList);
								}
								break;
							}
							if (distOrg.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
						} else {
							if (!orgMark.equals("0")) {
								switch (orgMark) {
								case "7":
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00301, sqlCode, distOrg,
											isServer);
									break;
								case "6":
									// 四级机构
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00401, sqlCode, distOrg,
											isServer);
									break;
								case "5":
									// 区
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00701, sqlCode, distOrg,
											isServer);
									break;
								case "4":
									// 部
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00801, sqlCode, distOrg,
											isServer);
									break;
								}
							} else {
								// 按登陆机构处理
								if (roleOrg.length() == 5) {
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00401, sqlCode, distOrg,
											isServer);
								}
								if (roleOrg.length() == 10) {
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00701, sqlCode, distOrg,
											isServer);
								} else {
									sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00301, sqlCode, distOrg,
											isServer);
								}
							}
							if (distOrg.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
							tarRegBean.setTitle(tarReg.getGraphTitle());
							tarRegBean.setSql(sqlCode);
							tarRegBean.setColor(tarReg.getColor());
							dataList.add(tarRegBean);
							this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
							dataPrintTips(17, dataList);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
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
	 * @状态 ok
	 * @功能 {保费部接口}18
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午9:55:37
	 */
	public ResponseBeanDto getTorgTrend(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String ptOrg = jsonObject.getString("ptOrg");// 机构编码
			String targetCode = jsonObject.getString("targetCode");// 一级指标
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选维度
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP03_REG07");
			TarReg tarReg = blockService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			Target target = blockService.findTargetByCode(targetCode);
			String targetDeptCode = target.getDeptCode();
			Boolean isServer = commonUtil.isGeYeAndPeiXunDept(targetDeptCode);
			if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
				TarRegSql tarRegSql = blockService.findTarRegSql(3, targetCode, temReg.getRegId(), "ORG_GROUP", null,
						groupbyDate);
				if (null != tarRegSql) {
					List<Object> dataList = new ArrayList<Object>();
					// 封装表格头部信息
					List<String> tarRegTabHeadList = blockService.findTarRegTabHeadByRegAndTar(temReg.getRegId(),
							targetCode);
					dataList.add(tarRegTabHeadList);
					TarRegBean tarRegBean = new TarRegBean();
					String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
							filters);
					if (!StringUtils.isEmpty(orgCode)) {
						// 判断机构勾选是否能到
						TarGroDim tarGroDim = blockService.findTarGroDimByTar(tarReg.getTargetCode());
						if (commonUtil.isDrilldown(Integer.parseInt(tarGroDim.getGroDimDetail().getGroDimCode()),
								orgCode.length())) {
							// 按搜索机构处理
							String orgType = jsonObject.getString("orgType");
							switch (roleOrg.length()) {
							// 总公司
							case 1:
								switch (orgType) {
								// 分公司
								case "7":
									// 此处是中支机构
									sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0401, sqlCode, orgCode,
											isServer, orgType);
									tarRegBean.setTitle(tarReg.getGraphTitle());
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(18, dataList);
									break;
								// 中支
								case "6":
									// 此处是四级机构
									sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0402, sqlCode, orgCode,
											isServer, orgType);
									tarRegBean.setTitle(tarReg.getGraphTitle());
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(18, dataList);
									break;
								default:
									// 此处是区机构
									sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0403, sqlCode, orgCode,
											isServer, orgType);
									tarRegBean.setTitle(tarReg.getGraphTitle());
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(18, dataList);
									break;
								}
								break;
							// 分公司
							case 3:
								switch (orgType) {
								// 中支
								case "6":
									// 此处是四级机构
									sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0402, sqlCode, orgCode,
											isServer, orgType);
									tarRegBean.setTitle(tarReg.getGraphTitle());
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(18, dataList);
									break;
								default:
									// 此处是区机构
									sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0403, sqlCode, orgCode,
											isServer, orgType);
									tarRegBean.setTitle(tarReg.getGraphTitle());
									tarRegBean.setSql(sqlCode);
									tarRegBean.setColor(tarReg.getColor());
									dataList.add(tarRegBean);
									this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
									dataPrintTips(18, dataList);
									break;
								}
								break;
							// 中支
							case 5:
								sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0403, sqlCode, orgCode,
										isServer, orgType);
								tarRegBean.setTitle(tarReg.getGraphTitle());
								tarRegBean.setSql(sqlCode);
								tarRegBean.setColor(tarReg.getColor());
								dataList.add(tarRegBean);
								this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
								dataPrintTips(18, dataList);
								break;
							}
							if (orgCode.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
						} else {
							this.dataIsEmptyTips(jsonObject, responseBeanDto);
						}
					} else {
						if (!StringUtils.isEmpty(ptOrg)) {
							sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00301, sqlCode, ptOrg, isServer);
							if (ptOrg.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
						} else {
							sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00301, sqlCode, roleOrg, isServer);
							if (roleOrg.length() == 5) {
								sqlCode = sqlParameter.rankSqlParam(sqlCode, targetCode, filters, targetDeptCode);
							}
						}
						tarRegBean.setTitle(tarReg.getGraphTitle());
						tarRegBean.setSql(sqlCode);
						tarRegBean.setColor(tarReg.getColor());
						dataList.add(tarRegBean);
						this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
						dataPrintTips(18, dataList);
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
	 * @状态 ok
	 * @功能 {特殊指标区域接口}19
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午9:34:15
	 */
	public ResponseBeanDto getSpecTrend(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String ptOrg = jsonObject.getString("ptOrg");// 上级机构代码
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			String targetCode = jsonObject.getString("targetCode");// 指标编码
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选维度
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP03_REG06");
			TarReg tarReg = blockService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
			Target target = blockService.findTargetByCode(targetCode);
			Boolean isServer = commonUtil.isGeYeAndPeiXunDept(target.getDeptCode());
			if (StringUtils.isEmpty(orgCode)) {
				if (null == ptOrg || ptOrg.equals("")) {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
						TarRegSql tarRegSql = blockService.findTarRegSql(4, tarReg.getTargetCode(), temReg.getRegId(),
								null, null, groupbyDate);
						if (null != tarRegSql) {
							DataBean dataBean = new DataBean();
							dataBean.setxAxis("");
							dataBean.setName(tarReg.getGraphTitle());
							dataBean.setStacking("");
							// 封装指标数据
							TarRegBean tarRegBean = new TarRegBean();
							tarRegBean.setName(blockService.findTargetByCode(tarReg.getTargetCode()).getTargetName());
							tarRegBean.setTitle(tarReg.getGraphTitle());
							tarRegBean.setType(tarReg.getGraphType());
							tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
							tarRegBean.setCode(tarReg.getTargetCode());
							tarRegBean.setX(tarReg.getxName());
							tarRegBean.setY(tarReg.getyName());
							tarRegBean.setColor(tarReg.getColor());
							String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
									tarRegSql.getTrsId(), filters);
							sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, sqlCode, roleOrg, isServer);
							tarRegBean.setSql(sqlCode);
							dataBean.setSeries(Arrays.asList(tarRegBean));
							this.dataSuccessTips(JSON.toJSONString(dataBean), responseBeanDto);
							dataPrintTips(19, dataBean);
						} else {
							this.dataIsEmptyTips(jsonObject, responseBeanDto);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), ptOrg.length())) {
						// 特殊处理北京
						if (ptOrg.equals("10101")) {
							ptOrg = "101";
						}
						TarRegSql tarRegSql = blockService.findTarRegSql(4, tarReg.getTargetCode(), temReg.getRegId(),
								null, null, groupbyDate);
						if (null != tarRegSql) {
							DataBean dataBean = new DataBean();
							dataBean.setxAxis("");
							dataBean.setName(tarReg.getGraphTitle());
							dataBean.setStacking("");
							// 封装指标数据
							TarRegBean tarRegBean = new TarRegBean();
							tarRegBean.setName(blockService.findTargetByCode(tarReg.getTargetCode()).getTargetName());
							tarRegBean.setTitle(tarReg.getGraphTitle());
							tarRegBean.setType(tarReg.getGraphType());
							tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
							tarRegBean.setCode(tarReg.getTargetCode());
							tarRegBean.setX(tarReg.getxName());
							tarRegBean.setY(tarReg.getyName());
							tarRegBean.setColor(tarReg.getColor());
							String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
									tarRegSql.getTrsId(), filters);
							sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, sqlCode, roleOrg, isServer);
							tarRegBean.setSql(sqlCode);
							dataBean.setSeries(Arrays.asList(tarRegBean));
							this.dataSuccessTips(JSON.toJSONString(dataBean), responseBeanDto);
							dataPrintTips(19, dataBean);
						} else {
							this.dataIsEmptyTips(jsonObject, responseBeanDto);
						}
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				}
			} else {
				if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), orgCode.length())) {
					TarRegSql tarRegSql = blockService.findTarRegSql(4, tarReg.getTargetCode(), temReg.getRegId(), null,
							null, groupbyDate);
					if (null != tarRegSql) {
						String orgType = jsonObject.getString("orgType");
						DataBean dataBean = new DataBean();
						dataBean.setxAxis("");
						dataBean.setName(tarReg.getGraphTitle());
						dataBean.setStacking("");
						// 封装指标数据
						TarRegBean tarRegBean = new TarRegBean();
						tarRegBean.setName(blockService.findTargetByCode(tarReg.getTargetCode()).getTargetName());
						tarRegBean.setTitle(tarReg.getGraphTitle());
						tarRegBean.setType(tarReg.getGraphType());
						tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
						tarRegBean.setCode(tarReg.getTargetCode());
						tarRegBean.setX(tarReg.getxName());
						tarRegBean.setY(tarReg.getyName());
						tarRegBean.setColor(tarReg.getColor());
						String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
								tarRegSql.getTrsId(), filters);
						sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0101, sqlCode, orgCode, isServer,
								orgType);
						tarRegBean.setSql(sqlCode);
						dataBean.setSeries(Arrays.asList(tarRegBean));
						this.dataSuccessTips(JSON.toJSONString(dataBean), responseBeanDto);
						dataPrintTips(19, dataBean);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}
			}
		} catch (Exception e) {
			// 异常响应处理
			this.dataExceptionTips(e, responseBeanDto);
		}
		return responseBeanDto;
	}

	/**
	 * @状态 ok
	 * @功能 {指标设为默认接口}20
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午3:27:57
	 */
	public ResponseBeanDto setDefTarget(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 用户名
			String username = jsonObject.getString("username");// 用户名
			String subject = jsonObject.getString("blockCode");// 主题编码
			String level = jsonObject.getString("level");// 主题编码
			String target1 = jsonObject.getString("target1");// 一级指标
			String target2 = jsonObject.getString("target2");// 二级指标
			List<String> target1List = JSON.parseArray(target1, String.class);
			List<String> target2List = JSON.parseArray(target2, String.class);
			int addFlag = 0;
			if (!target1List.isEmpty() && null != target1List && target2List.isEmpty()) {
				addFlag = blockService.saveUserDefTarget(target1List, username, role, subject, level);
			} else if (target1List.isEmpty() && null != target2List && !target2List.isEmpty()) {
				addFlag = blockService.saveUserDefTarget(target2List, username, role, subject, level);
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
	 * @状态 ok
	 * @功能 {区间统计}21
	 * @作者 MaxBill
	 * @时间 2017年9月8日 下午3:20:07
	 */
	public ResponseBeanDto tallestCow(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String ptOrg = jsonObject.getString("ptOrg");// 上级机构代码
			String subject = jsonObject.getString("blockCode");// 主题编码
			String groupbyDate = jsonObject.getString("groupbyDate");// 分组
			String startTime = jsonObject.getString("startTime");// 开始时间
			String endTime = jsonObject.getString("endTime");// 结束时间
			Boolean isServer = jsonObject.getBoolean("isServer");// 是否营销服务部
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选维度
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP05_REG01");
			// 按主题查询区间统计的指标
			List<TarStatis> tarStatisList = blockService.findTarStatisBySub(subject);
			List<TarRegBean> tarRegBeanList = new ArrayList<TarRegBean>();
			if (null != tarStatisList) {
				List<Object> dataList = new ArrayList<Object>();
				// 封装表格头部信息
				List<String> tarRegTabHeadList = blockService.findTarRegTabHeadByReg(temReg.getRegId());
				dataList.add(tarRegTabHeadList);
				for (TarStatis tarStatis : tarStatisList) {
					String targetCode = tarStatis.getTargetCode();
					boolean isHas = commonUtil.isHasGroByTarAndGro(targetCode, groupbyDate);
					if (isHas) {
						TarRegSql tarRegSql = blockService.findTarRegSql(3, targetCode, temReg.getRegId(), "", null,
								groupbyDate);
						String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode,
								tarRegSql.getTrsId(), filters);
						// 区间统计sql替换规则
						if (StringUtils.isEmpty(orgCode)) {
							if (StringUtils.isEmpty(ptOrg)) {
								String sql = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, sqlCode, roleOrg, isServer);
								sql = sql.replace("{start_time}", "'" + startTime + "'");
								sql = sql.replace("{end_time}", "'" + endTime + "'");
								sql = sql.replace("{org_code}", "'" + roleOrg + "'");
								TarRegBean tarRegBean = new TarRegBean();
								tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
								tarRegBean.setSql(sql);
								tarRegBeanList.add(tarRegBean);
							} else {
								String sql = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, sqlCode, ptOrg, isServer);
								sql = sql.replace("{start_time}", "'" + startTime + "'");
								sql = sql.replace("{end_time}", "'" + endTime + "'");
								sql = sql.replace("{org_code}", "'" + ptOrg + "'");
								TarRegBean tarRegBean = new TarRegBean();
								tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
								tarRegBean.setSql(sql);
								tarRegBeanList.add(tarRegBean);
							}
						} else {
							String orgType = jsonObject.getString("orgType");
							String sql = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0101, sqlCode, orgCode,
									isServer, orgType);
							sql = sql.replace("{start_time}", "'" + startTime + "'");
							sql = sql.replace("{end_time}", "'" + endTime + "'");
							sql = sql.replace("{org_code}", "'" + orgCode + "'");
							TarRegBean tarRegBean = new TarRegBean();
							tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
							tarRegBean.setSql(sql);
							tarRegBeanList.add(tarRegBean);
						}
					}
				}
				dataList.add(tarRegBeanList);
				this.dataSuccessTips(JSON.toJSONString(dataList), responseBeanDto);
				dataPrintTips(21, dataList);
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
	 * @状态 ok
	 * @功能 {获取机构对比的参数}22
	 * @作者 MaxBill
	 * @时间 2017年9月8日 下午12:44:34
	 */
	public ResponseBeanDto orgCompareParam(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");// 角色
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			Boolean isCustom = jsonObject.getBoolean("isCustom");// 是否自定义板块
			String ptOrg = jsonObject.getString("ptOrg");// 上级机构代码
			String groupbyDate = jsonObject.getString("groupbyDate");// 分组维度
			List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
			String blockCode = jsonObject.getString("blockCode");// 板块编码
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String tarCode = jsonObject.getString("targetCode");// 机构编码
			String org = StringUtil.isNotNull(ptOrg) ? ptOrg : roleOrg;
			// 获取该板块下的全部一级二级指标
			if (isCustom) {
				// 自定义板块
				// 此处查出所有自定义版块指标组
				String roleCate = roleOrg.length() == 1 ? "8"
						: (roleOrg.length() == 3 ? "7"
								: (roleOrg.length() == 5 ? "6" : (roleOrg.length() == 10 ? "5" : "5")));
				List<PlateOneTar> plateTarList = blockService.findAllPlateTarByBlock(blockCode, groupbyDate, roleCate);
				for (PlateOneTar plateTar : plateTarList) {
					if (ptOrg.length() >= 5 && plateTar.getTargetCode().contains("T07")) {
						break;
					}
					if (orgCode.length() >= 5 && plateTar.getTargetCode().contains("T07")) {
						break;
					}
					String targetCode = plateTar.getTargetCode();
					TargetBean targetBean = new TargetBean();
					targetBean.setCode(targetCode);
					targetBean.setName(blockService.findTargetNameByCode(targetCode));
					targetBeanList.add(targetBean);
				}
			} else {
				// 固定板块
				List<String> temps = new ArrayList<>();
				temps.add("TEMP06_REG01");
				List<TarReg> tarRegList = blockService.findMoreTarRegsBySubAndRole(temps, blockCode, role, groupbyDate);
				if (null != tarRegList) {
					for (TarReg tarReg : tarRegList) {
						String targetCode = tarReg.getTargetCode();
						TargetBean targetBean = new TargetBean();
						targetBean.setCode(targetCode);
						targetBean.setName(blockService.findTargetNameByCode(targetCode));
						List<String> codeS = new ArrayList<>();
						for (TargetBean tB : targetBeanList) {
							codeS.add(tB.getCode());
						}
						if (!codeS.contains(targetCode)) {
							targetBeanList.add(targetBean);
						}
					}
				}
			}
			if (targetBeanList.size() > 0 && null != targetBeanList) {
				resultMap.put("targets", targetBeanList);
				String tarDept = "";
				if (StringUtils.isEmpty(tarCode)) {
					String defaultTar = targetBeanList.get(0).getCode();
					tarDept = blockService.findTargetByCode(defaultTar).getDeptCode();
				} else {
					tarDept = blockService.findTargetByCode(tarCode).getDeptCode();
				}
				List<OrgBean> orgList = null;
				if (!StringUtils.isEmpty(orgCode)) {
					String orgType = jsonObject.getString("orgType");
					switch (orgType) {
					case "7":
						// 分公司
						orgList = blockService.getOrgList(2, orgCode, tarDept);
						break;
					case "6":
						// 中支
						orgList = blockService.getOrgList(3, orgCode, tarDept);
						break;
					case "501":
						// 营销服务部
						orgList = blockService.getOrgList(41, orgCode, tarDept);
						break;
					case "502":
						// 成本中心
						orgList = blockService.getOrgList(42, orgCode, tarDept);
						break;
					}
				} else {
					if (!StringUtils.isEmpty(ptOrg)) {
						switch (ptOrg.length()) {
						case 1:
							// 总公司
							orgList = blockService.getOrgList(1, ptOrg, null);
							break;
						case 3:
							// 分公司
							orgList = blockService.getOrgList(2, ptOrg, null);
							break;
						case 5:
							// 中支
							orgList = blockService.getOrgList(3, ptOrg, tarDept);
							break;
						default:
							// 营销服务部/成本总中心
							orgList = blockService.getOrgList(43, ptOrg, tarDept);
							break;
						}
					} else {
						switch (roleOrg.length()) {
						case 1:
							// 总公司
							orgList = blockService.getOrgList(1, roleOrg, null);
							break;
						case 3:
							// 分公司
							orgList = blockService.getOrgList(2, roleOrg, null);
							break;
						case 5:
							// 中支
							orgList = blockService.getOrgList(3, roleOrg, tarDept);
							break;
						default:
							// 成本中心
							orgList = blockService.getOrgList(42, org, null);
							break;
						}
					}
				}
				resultMap.put("org", orgList);
				this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
				dataPrintTips(22, resultMap);
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
	 * @状态 ok
	 * @功能 {机构对比接口}23
	 * @作者 MaxBill
	 * @时间 2017年9月8日 下午2:24:14
	 */
	public ResponseBeanDto orgCompare(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String targetCode = jsonObject.getString("target");// 指标编码
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			JSONArray orgList = jsonObject.getJSONArray("orgs");
			String orgType = jsonObject.getString("orgType");
			String filters = jsonObject.getString("filters");// 筛选维度
			String ptOrg = jsonObject.getString("ptOrg");
			roleOrg = "".equals(ptOrg) ? roleOrg : ptOrg;
			// 查询区域信息
			Target target = blockService.findTargetByCode(targetCode);
			Boolean isServerTemp = commonUtil.isGeYeAndPeiXunDept(target.getDeptCode());
			TemReg temReg = blockService.findRegionByCode("TEMP06_REG01");
			TarReg tarReg = blockService.findTarRegByRegAndSubAndTar(temReg.getRegId(), null, targetCode);
			TarRegSql tarRegSql = blockService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
					groupbyDate, null);
			if (null != tarRegSql) {
				String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), targetCode, tarRegSql.getTrsId(),
						filters);
				List<DataBean> dataBeanList = new ArrayList<DataBean>();
				// 封装机构一数据
				DataBean dataBean01 = new DataBean();
				dataBean01.setStacking("");
				dataBean01.setxAxis("");
				TarRegBean tarRegBean01 = new TarRegBean();
				tarRegBean01.setCode(targetCode);
				tarRegBean01.setName(blockService.findTargetNameByCode(targetCode));
				tarRegBean01.setType(tarReg.getGraphType());
				tarRegBean01.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
				tarRegBean01.setColor(tarReg.getColor());
				tarRegBean01.setStack("");
				String sql01 = sqlFactory.getBkfxJgdbSql(sqlCode, roleOrg, orgList.get(0).toString(), isServerTemp,
						orgType);
				tarRegBean01.setSql(sql01);
				dataBean01.setSeries(tarRegBean01);
				dataBeanList.add(dataBean01);
				// 封装机构二数据
				DataBean dataBean02 = new DataBean();
				dataBean02.setStacking("");
				dataBean02.setxAxis("");
				TarRegBean tarRegBean02 = new TarRegBean();
				tarRegBean02.setCode(targetCode);
				tarRegBean02.setName(blockService.findTargetNameByCode(targetCode));
				tarRegBean02.setType(tarReg.getGraphType());
				tarRegBean02.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
				tarRegBean02.setColor(tarReg.getColor());
				tarRegBean02.setStack("");
				String sql02 = sqlFactory.getBkfxJgdbSql(sqlCode, roleOrg, orgList.get(1).toString(), isServerTemp,
						orgType);
				tarRegBean02.setSql(sql02);
				dataBean02.setSeries(tarRegBean02);
				dataBeanList.add(dataBean02);
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(dataBeanList), responseBeanDto);
				dataPrintTips(23, dataBeanList);
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
	 * @状态 ok
	 * @功能 {导出清单}24
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午3:42:54
	 */
	public ResponseBeanDto exportBill(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String subject = jsonObject.getString("blockCode");// 主题编码
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			Boolean isServer = jsonObject.getBoolean("isServer");// 是否营销服务部
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选维度
			String ptOrg = jsonObject.getString("ptOrg");// 上级机构代码
			String org = StringUtil.isNotNull(ptOrg) ? ptOrg : roleOrg;
			List<Object> dataList = new ArrayList<Object>();
			Map resultMap = new HashMap<>();
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP07_REG01");
			TarReg tarReg = blockService.findTarRegByRegAndSub(temReg.getRegId(), subject);
			// 封装表格头部信息
			List<String> tarRegTabHeadList = blockService.findTarRegTabHeadByRegAndTar(temReg.getRegId(),
					tarReg.getTargetCode());
			TarRegSql tarRegSql = blockService.findTarRegSql(2, tarReg.getTargetCode(), temReg.getRegId(), "DATE_GROUP",
					groupbyDate, null);
			dataList.add(tarRegTabHeadList);
			resultMap.put("head", tarRegTabHeadList);
			if (null != tarRegSql) {
				String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), tarReg.getTargetCode(),
						tarRegSql.getTrsId(), filters);
				if (!StringUtils.isEmpty(orgCode)) {
					String orgType = jsonObject.getString("orgType");
					sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0101, sqlCode, orgCode, isServer,
							orgType);
				} else {
					sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, sqlCode, org, isServer);
				}
				resultMap.put("sql", sqlCode);
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
				dataPrintTips(24, resultMap);
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
	 * @状态 ok
	 * @功能 {导出清单列表下载}25
	 * @作者 MaxBill
	 * @时间 2017年9月8日 下午3:21:21
	 */
	public ResponseBeanDto downloadBill(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String subject = jsonObject.getString("blockCode");// 主题编码
			String groupbyDate = jsonObject.getString("groupbyDate");// 根据时间分组
			Boolean isServer = jsonObject.getBoolean("isServer");// 是否营销服务部
			String orgCode = jsonObject.getString("orgCode");// 机构编码
			String filters = jsonObject.getString("filters");// 筛选维度
			String ptOrg = jsonObject.getString("ptOrg");// 上级机构代码
			String org = StringUtil.isNotNull(ptOrg) ? ptOrg : roleOrg;
			List<Object> dataList = new ArrayList<Object>();
			Map resultMap = new HashMap<>();
			// 查询区域信息
			TemReg temReg = blockService.findRegionByCode("TEMP07_REG01");
			TarReg tarReg = blockService.findTarRegByRegAndSub(temReg.getRegId(), subject);
			// 封装表格头部信息
			List<String> tarRegTabHeadList = blockService.findTarRegTabHeadByRegAndTar(temReg.getRegId(),
					tarReg.getTargetCode());
			TarRegSql tarRegSql = blockService.findTarRegSql(2, tarReg.getTargetCode(), temReg.getRegId(), "DATE_GROUP",
					groupbyDate, null);
			dataList.add(tarRegTabHeadList);
			resultMap.put("head", tarRegTabHeadList);
			if (null != tarRegSql) {
				String sqlCode = sqlParameter.fxglSqlParam(tarRegSql.getSqlCode(), tarReg.getTargetCode(),
						tarRegSql.getTrsId(), filters);
				if (!StringUtils.isEmpty(orgCode)) {
					String orgType = jsonObject.getString("orgType");
					sqlCode = sqlFactory.getBkfxSqlByOrg(SqlConstants.BKFX_ORG0101, sqlCode, orgCode, isServer,
							orgType);
				} else {
					sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, sqlCode, org, isServer);
				}
				resultMap.put("sql", sqlCode);
				// 成功响应处理
				this.dataSuccessTips(JSON.toJSONString(resultMap), responseBeanDto);
				dataPrintTips(24, resultMap);
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
		log.info("------------------------BKFW(" + type + ")-S------------------------");
		log.info(DateUtil.formatDateTime(new Date()) + ": " + JSON.toJSONString(data));
		log.info("------------------------BKFW(" + type + ")-E------------------------");
	}

}
