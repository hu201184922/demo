package com.huatai.web.thrift.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.bean.BlockBean;
import com.huatai.web.bean.GroDimDetailBean;
import com.huatai.web.bean.TarRegBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.bean.TargetListBean;
import com.huatai.web.model.CustPlate;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.PlateOneTar;
import com.huatai.web.model.PlateTwoTar;
import com.huatai.web.model.PlateTwoTarGro;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.sql.SqlConstants;
import com.huatai.web.sql.SqlFactory;
import com.huatai.web.thrift.bean.ResponseBeanDto;
import com.huatai.web.thrift.service.BlockService;
import com.huatai.web.thrift.service.CommonService;
import com.huatai.web.thrift.service.SubjectAnalysisService;
import com.huatai.web.thrift.web.ZyfwServiceImpl;
import com.huatai.web.utils.DateUtil;

public class AppBkfwServiceImpl implements AppBkfwService.Iface {

	private SqlFactory sqlFactory;
	private CommonUtil commonUtil;
	private static final String TEMP12_REG01 = "TEMP12_REG01";// 虚拟区域
	Logger log = Logger.getLogger(ZyfwServiceImpl.class);

	public AppBkfwServiceImpl() {
		sqlFactory = SpringUtil.getBean(SqlFactory.class);
		commonUtil = SpringUtil.getBean(CommonUtil.class);
	}

	/**
	 * @功能 {板块菜单}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 下午5:46:57
	 */
	public ResponseBeanDto getMenu(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String role = jsonObject.getString("role");
			String userName = jsonObject.getString("username");// 用户名
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
			}
			// 自定义板块
			List<CustPlate> custPlateList = blockService.findCustPlateByUser(role, userName);
			List<BlockBean> custPlates = new ArrayList<>();
			if (null != custPlateList) {
				for (CustPlate custPlate : custPlateList) {
					BlockBean custPlateBean = new BlockBean();
					custPlateBean.setCode(custPlate.getPlateId().toString());
					custPlateBean.setBlockName(custPlate.getPlateName());
					custPlates.add(custPlateBean);
				}
			}

			resultMap.put("blocks", fixed);
			resultMap.put("custPlates", custPlates);
			// 成功响应处理
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
	 * @功能 {具体的指标页面}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 下午6:45:18
	 */
	public ResponseBeanDto getPage(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		Map resultMap = new HashMap<>();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			CommonService commonService = SpringUtil.getBean(CommonService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String block = jsonObject.getString("blockCode");// 板块ID
			String role = jsonObject.getString("role");
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String userName = jsonObject.getString("username");// 用户名
			Boolean isCustom = jsonObject.getBoolean("isCustom");// 是否是自定义板块
			// 查询默认指标
			if (isCustom) {// 自定义板块
				Integer plateId = Integer.parseInt(block);
				// 查询自定义板块下的一级指标
				List<PlateOneTar> plateOneTarList = blockService.findPlateOneTarByBlock(plateId);
				List<TargetListBean> targetBean = new ArrayList<TargetListBean>();
				if (null != plateOneTarList) {
					for (PlateOneTar plateOneTar : plateOneTarList) {
						TargetListBean targetListBean = new TargetListBean();
						targetListBean.setCode(plateOneTar.getTargetCode());
						targetListBean.setName(blockService.findTargetNameByCode(plateOneTar.getTargetCode()));
						TemReg temRegApp = subjectAnalysisService.findRegionByCode(TEMP12_REG01);
						TarRegSql tarRegSql = blockService.findTarRegSql(4, plateOneTar.getTargetCode(),
								temRegApp.getRegId(), null, null, "MONTH");
						if (null != tarRegSql) {
							String sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, tarRegSql.getSqlCode(),
									roleOrg, null);
							targetListBean.setValue(sqlCode);
						} else {
							targetListBean.setValue(null);
						}
						boolean isSetDefault = subjectAnalysisService.isdefaultTarget(userName,
								plateOneTar.getTargetCode(), block, role);
						targetListBean.setChecked(isSetDefault);
						targetBean.add(targetListBean);
					}
				}
				// 查询自定义板块下的二级指标分组
				List<PlateTwoTarGro> plateTwoGros = blockService.findPlateTwoTarGroByBlock(plateId);
				List targetList = new ArrayList();
				if (null != plateTwoGros) {
					for (PlateTwoTarGro plateTwoTarGro : plateTwoGros) {
						Integer plateTwoGroId = plateTwoTarGro.getPttgId();
						TargetBean targeTwotBean = new TargetBean();
						targeTwotBean.setName(plateTwoTarGro.getTwoGroupName());
						targeTwotBean.setCode(String.valueOf(plateTwoTarGro.getPttgId()));
						// 二级指标组下的二级指标
						List<PlateTwoTar> plateTwoTars = blockService.findPlateTwoTarByGroup(plateTwoGroId);
						if (null != plateTwoTars) {
							List targetLists = new ArrayList();
							for (PlateTwoTar plateTwoTar : plateTwoTars) {
								TargetListBean targetListBean = new TargetListBean();
								targetListBean.setName(blockService.findTargetNameByCode(plateTwoTar.getTargetCode()));
								targetListBean.setCode(plateTwoTar.getTargetCode());
								TemReg temRegApp = subjectAnalysisService.findRegionByCode(TEMP12_REG01);
								TarRegSql tarRegSql = blockService.findTarRegSql(4, plateTwoTar.getTargetCode(),
										temRegApp.getRegId(), null, null, "MONTH");
								if (null != tarRegSql) {
									String sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101,
											tarRegSql.getSqlCode(), roleOrg, null);
									targetListBean.setValue(sqlCode);
								} else {
									targetListBean.setValue(null);
								}
								boolean isSetDefault = subjectAnalysisService.isdefaultTarget(userName,
										plateTwoTar.getTargetCode(), block, role);
								targetListBean.setChecked(isSetDefault);
								targetLists.add(targetListBean);
							}
							targeTwotBean.setTarget(targetLists);
						}
						targetList.add(targeTwotBean);
					}
					resultMap.put("targetBean", targetBean);
					resultMap.put("targetList", targetList);
				}
				// 查询时间指标
				List<GroDimDetailBean> timeReqs = new ArrayList<GroDimDetailBean>();
				List<GroDimDetail> groDimDetails = commonService.findGroDetailListBySubWithDateAndPlate(plateId);
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
			} else {// 固定板块
				// 查询一级指标
				TemReg temReg = subjectAnalysisService.findRegionByCode("TEMP03_REG02");
				List<Target> targetLists = subjectAnalysisService.findTargetByRoleAndTypeAndReg(role, "1", block,
						temReg.getRegId());
				List<TargetListBean> targetBean = new ArrayList<TargetListBean>();
				TemReg temRegApp = subjectAnalysisService.findRegionByCode(TEMP12_REG01);
				if (null != targetLists) {
					for (Target target : targetLists) {
						TargetListBean targetListBean = new TargetListBean();
						targetListBean.setCode(target.getTargetCode());
						targetListBean.setName(target.getTargetName());
						TarRegSql tarRegSql = blockService.findTarRegSql(4, target.getTargetCode(),
								temRegApp.getRegId(), null, null, "MONTH");
						if (null != tarRegSql) {
							String sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, tarRegSql.getSqlCode(),
									roleOrg, null);
							targetListBean.setValue(sqlCode);
						} else {
							targetListBean.setValue(null);
						}

						boolean isSetDefault = subjectAnalysisService.isdefaultTarget(userName, target.getTargetCode(),
								block, role);
						targetListBean.setChecked(isSetDefault);
						targetBean.add(targetListBean);
					}
					// 查询二级指标分组
					List<Target> targetTwoList = subjectAnalysisService.findTargetByTypeAndRoleAndBlock("2", role,
							block);
					List targetList = new ArrayList();
					if (null != targetTwoList) {
						for (Target target : targetTwoList) {
							TargetBean targeTwotBean = new TargetBean();
							targeTwotBean.setName(target.getTargetName());
							targeTwotBean.setCode(target.getTargetCode());
							// 查询二级指标分组下的二级指标
							List<TargetBean> targetTwoLevelList = blockService.findTargetByParentCode("TEMP03_REG03",
									role, "3", target.getTargetCode());
							if (null != targetTwoLevelList) {
								List targetTwoLists = new ArrayList();
								for (TargetBean twoTarget : targetTwoLevelList) {
									TargetListBean targetListBean = new TargetListBean();
									if(twoTarget.getCode().equals("P07070")||twoTarget.getCode().equals("P07072")) {
										continue;
									}
									targetListBean.setCode(twoTarget.getCode());
									targetListBean.setName(twoTarget.getName());
									TarRegSql tarRegSql = blockService.findTarRegSql(4, twoTarget.getCode(),
											temRegApp.getRegId(), null, null, "MONTH");

									if (null != tarRegSql) {
										String sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101,
												tarRegSql.getSqlCode(), roleOrg, null);
										targetListBean.setValue(sqlCode);
									} else {
										targetListBean.setValue(null);
									}

									boolean isSetDefault = subjectAnalysisService.isdefaultTarget(userName,
											twoTarget.getCode(), block, role);
									targetListBean.setChecked(isSetDefault);
									targetTwoLists.add(targetListBean);
									
								}
								targeTwotBean.setTarget(targetTwoLists);

							}
							targetList.add(targeTwotBean);
						}
						resultMap.put("targetBean", targetBean);
						resultMap.put("targetList", targetList);
					}
				}
				// 查询时间指标
				List<GroDimDetailBean> timeReqs = new ArrayList<GroDimDetailBean>();

				List<GroDimDetail> groDimDetails = commonService.findGroDetailListBySubWithDate(block);
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
						resultMap.put("timeReqs", timeReqs);
					}
				}

			}

			responseBeanDto.setJson(JSON.toJSONString(resultMap));// 返回数据
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
	 * @功能 {一级指标接口}
	 * @作者 MaxBill
	 * @时间 2017年8月2日 上午10:33:46
	 */
	public ResponseBeanDto getMainData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String groupbyDate = jsonObject.getString("groupbyDate"); // 时间维度
			String targetCode = jsonObject.getString("targetCode");// 选中的指标数据
			// 查询区域信息
			List<Object> resultList = new ArrayList<>();
			TemReg temReg = blockService.findRegionByCode("TEMP03_REG02");
			if (StringUtils.isNotEmpty(targetCode)) {
				TarRegBean tarRegBean = new TarRegBean();
				TarReg tarReg = blockService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
				if (null != tarReg) {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
						TarRegSql tarRegSql = blockService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
								groupbyDate, null);
						if (null != tarRegSql) {
							String sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, tarRegSql.getSqlCode(),
									roleOrg, null);
							tarRegBean.setSql(sqlCode);
						} else {
							tarRegBean.setSql(null);
						}
						tarRegBean.setCode(targetCode);
						tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
						tarRegBean.setType(tarReg.getGraphType());
						tarRegBean.setTitle(tarReg.getGraphTitle());
						tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
						tarRegBean.setStack("");
						tarRegBean.setColor(tarReg.getColor());

						resultList.add(tarRegBean);
					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}
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
	 * @功能 {二级指标接口}
	 * @作者 MaxBill
	 * @时间 2017年8月2日 下午1:46:02
	 */
	public ResponseBeanDto getSubData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			BlockService blockService = SpringUtil.getBean(BlockService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String roleOrg = jsonObject.getString("roleOrg");// 角色机构
			String groupbyDate = jsonObject.getString("groupbyDate"); // 时间维度
			String targetCode = jsonObject.getString("targetCode");// 选中的指标数据
			TemReg temReg = blockService.findRegionByCode("TEMP03_REG03");
			// 查询区域信息
			List<Object> resultList = new ArrayList<>();
			if (StringUtils.isNotEmpty(targetCode)) {
				TarRegBean tarRegBean = new TarRegBean();
				TarReg tarReg = blockService.findTarRegByRegAndTarget(temReg.getRegId(), targetCode);
				if (null != tarReg) {
					if (commonUtil.hasRoleOrg(Integer.parseInt(tarReg.getRoleOrgType()), roleOrg.length())) {
						TarRegSql tarRegSql = blockService.findTarRegSql(2, targetCode, temReg.getRegId(), "DATE_GROUP",
								groupbyDate, null);
						if (null != tarRegSql) {
							String sqlCode = sqlFactory.getBkfxSql(SqlConstants.BKFX_00101, tarRegSql.getSqlCode(),
									roleOrg, null);
							tarRegBean.setSql(sqlCode);
						} else {
							tarRegBean.setSql(null);
						}
						tarRegBean.setCode(targetCode);
						tarRegBean.setName(blockService.findTargetNameByCode(targetCode));
						tarRegBean.setType(tarReg.getGraphType());
						tarRegBean.setTitle(tarReg.getGraphTitle());
						tarRegBean.setUnit(commonUtil.getTargetUnit(tarReg.getUnit()));
						tarRegBean.setStack("");
						tarRegBean.setColor(tarReg.getColor());

						resultList.add(tarRegBean);

					} else {
						this.dataIsEmptyTips(jsonObject, responseBeanDto);
					}
				} else {
					this.dataIsEmptyTips(jsonObject, responseBeanDto);
				}

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
	 * @功能 {指标排名接口}
	 * @作者 MaxBill
	 * @时间 2017年8月2日 下午2:27:53
	 */
	public ResponseBeanDto getRankData(String json) {
		ResponseBeanDto responseBeanDto = new ResponseBeanDto();
		try {
			SubjectAnalysisService subjectAnalysisService = SpringUtil.getBean(SubjectAnalysisService.class);
			// 参数获取
			JSONObject jsonObject = JSON.parseObject(json);
			String subject = jsonObject.getString("block");// 当前主题
			String role = jsonObject.getString("role");// 当前登录人角色
			String target1 = jsonObject.getString("target1");// 一级指标
			String groupbyDate = jsonObject.getString("groupbyDate"); // 时间维度
			String groupbyOrg = jsonObject.getString("groupbyOrg");// 当前登录人机构
			List<TarRegBean> tarRegBeanList = new ArrayList<TarRegBean>();
			// 查询区域信息
			TemReg temReg = subjectAnalysisService.findRegionByCode("dist");
			List<TarReg> tarRegList = subjectAnalysisService.findTarRegsBySubAndRegAndRole(subject, temReg.getRegId(),
					role);
			if (null != tarRegList) {
				for (TarReg tarReg : tarRegList) {
					Target target = subjectAnalysisService.findTargetByCode(tarReg.getTargetCode());
					// 封装指标数据
					TarRegBean tarRegBean = new TarRegBean();
					tarRegBean.setName(target.getTargetName());
					tarRegBean.setCode(target.getTargetCode());
					tarRegBean.setType(tarReg.getGraphType());
					tarRegBean.setUnit(tarReg.getUnit());
					tarRegBean.setX(tarReg.getxName());
					tarRegBean.setY(tarReg.getyName());
					// TarRegSql tarRegSql =
					// subjectAnalysisService.findTarRegSql(tarReg.getTargetCode(),
					// temReg.getRegId(), groupbyDate);
					// tarRegBean.setSql(tarRegSql.getSqlCode());
					tarRegBeanList.add(tarRegBean);
				}
			}
			// 成功响应处理
			responseBeanDto.setJson(JSON.toJSONString(tarRegList));
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
	 * @功能 {数据为空提醒}
	 * @作者 MaxBill
	 * @时间 2017年9月18日 下午5:12:05
	 */
	public void dataIsEmptyTips(JSONObject jsonObject, ResponseBeanDto responseBeanDto) {
		log.info("指标SQL数据为空,请求参数为：" + jsonObject.toJSONString());
		responseBeanDto.setErrCode("500");
		responseBeanDto.setErrMsgs("System info: The target sql data is empty!");
		responseBeanDto.setSuccess("false");
		responseBeanDto.setTimeStamp(DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT));
	}

	/**
	 * @功能 {数据正常返回}
	 * @作者 MaxBill
	 * @时间 2017年9月18日 下午5:12:05
	 */
	public void dataSuccessTips(String jsonDta, ResponseBeanDto responseBeanDto) {
		responseBeanDto.setJson(jsonDta);
		responseBeanDto.setErrCode("200");
		responseBeanDto.setErrMsgs("");
		responseBeanDto.setSuccess("true");
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
}
