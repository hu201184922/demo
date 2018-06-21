package com.huatai.web.thrift.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.DataPageBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.bean.WarnBean;
import com.huatai.web.bean.WarnResultBean;
import com.huatai.web.mapper.OrganizationsMapper;
import com.huatai.web.mapper.TarInitSqlMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.mapper.UserSetWarnMapper;
import com.huatai.web.mapper.WarnResultMapper;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.TarInitSqlExample;
import com.huatai.web.model.Target;
import com.huatai.web.model.UserSetWarn;
import com.huatai.web.model.UserSetWarnExample;
import com.huatai.web.model.WarnResult;
import com.huatai.web.model.WarnResultExample;
import com.huatai.web.thrift.service.WarningService;

/**
 * @功能 {工具业务实现类}
 * @作者 MaxBill
 * @时间 2017年7月25日 下午4:20:41
 */
@Service
public class WarningServiceImpl implements WarningService {

	@Autowired
	private UserSetWarnMapper userSetWarnMapper;

	@Autowired
	private TargetMapper targetMapper;

	@Autowired
	private WarnResultMapper warnResultMapper;

	@Autowired
	private OrganizationsMapper organizationsMapper;

	@Autowired
	private TarInitSqlMapper tarInitSqlMapper;

	@Autowired
	private DictionaryService dictionaryServiceImpl;

	/**
	 * @功能 {我的预警信息,分页}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 下午3:24:17
	 */
	public DataPageBean myWarnings(Pager<UserSetWarn> pager, UserSetWarn userSetWarn) {
		DataPageBean dataPage = new DataPageBean();
		Pager<UserSetWarn> userSetWarnPage;
		if (StringUtils.isNotEmpty(userSetWarn.getTargetName())) {
			userSetWarnPage = this.userSetWarnMapper.findUserSetWarnByNamePage(pager, userSetWarn);
		} else {
			userSetWarnPage = this.userSetWarnMapper.findUserSetWarnByPage(pager, userSetWarn);
		}
		dataPage.setCurPage(userSetWarnPage.getCurrentPage());
		dataPage.setPageSize(userSetWarnPage.getPageSize());
		dataPage.setTotal(userSetWarnPage.getTotalCount());
		dataPage.setTotalPage(userSetWarnPage.getTotalPage());
		List<UserSetWarn> userSetWarnList = userSetWarnPage.getPageItems();
		List<WarnBean> warnBeanList = new ArrayList<WarnBean>();
		if (null != userSetWarnList) {
			for (UserSetWarn userSetWarnTemp : userSetWarnList) {
				WarnBean warnBean = new WarnBean();
				warnBean.setId(userSetWarnTemp.getBsId());
				warnBean.setOrg(userSetWarnTemp.getOrgCode());
				warnBean.setStatus(userSetWarnTemp.getWarnStatus());
				Target target = targetMapper.selectByPrimaryKey(userSetWarnTemp.getTargetCode());
				String targetName = target.getTargetName();
				String unitCode = (StringUtil.isNull(target.getFieldCode()) ? "" : target.getFieldCode());
				String sign = "";
				if (StringUtil.isNotNull(unitCode)) {
					DictionaryItem item = dictionaryServiceImpl.findByDictCodeAndDictItemCode("UNIT_TYPE", unitCode);
					sign = item.getName();
				}
				warnBean.setTarget(targetName);
				warnBean.setTargetCode(target.getTargetCode());
				String formulaStr = "";
				switch (userSetWarnTemp.getWarnCode()) {
				case "1":
					formulaStr = "大于" + userSetWarnTemp.getMinVal() + sign;
					break;
				case "2":
					formulaStr = "小于" + userSetWarnTemp.getMaxVal() + sign;
					break;
				case "3":
					formulaStr = "在" + userSetWarnTemp.getMinVal() + sign + "和" + userSetWarnTemp.getMaxVal() + sign
							+ "之间";
					break;
				}
				warnBean.setTerm(targetName + formulaStr);
				warnBean.setType(userSetWarnTemp.getAlertType());
				warnBeanList.add(warnBean);
			}
		}
		dataPage.setList(warnBeanList);
		return dataPage;
	}

	/**
	 * @功能 {查询一二级别指标}
	 * @作者 MaxBill
	 * @时间 2017年7月28日 上午11:06:31
	 */
	public List<TargetBean> findTargetList(String targetCode, String role) {
		List<Target> targetList = this.targetMapper.selectTargetByRole(role);
		if (null != targetList) {
			List<TargetBean> TargetBeanList = new ArrayList<TargetBean>();
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setId(target.getTargetCode());
				targetBean.setSign(target.getFieldCode() == null ? "--" : target.getFieldCode());
				targetBean.setPid(target.getParentCode());
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				if (null != targetCode) {
					if (targetCode.equals(target.getTargetCode())) {
						targetBean.setChecked(true);
					} else {
						targetBean.setChecked(false);
					}
				}
				TargetBeanList.add(targetBean);
			}
			return TargetBeanList;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {新增预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 下午3:22:23
	 */
	public int saveWarning(UserSetWarn userSetWarn) {
		return this.userSetWarnMapper.insertSelective(userSetWarn);
	}

	/**
	 * @功能 {检索预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 下午3:22:52
	 */
	public List<UserSetWarn> queryWarning(String username, String orgCode, String targetCode) {
		UserSetWarnExample userSetWarnExample = new UserSetWarnExample();
		userSetWarnExample.createCriteria().andUsernameEqualTo(username).andOrgCodeEqualTo(orgCode)
				.andTargetCodeEqualTo(targetCode);
		return this.userSetWarnMapper.selectByExample(userSetWarnExample);
	}

	/**
	 * 
	 * @功能 {查找预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 下午3:23:18
	 */
	public UserSetWarn findWarning(Integer warnId) {
		UserSetWarn userSetWarn = new UserSetWarn();
		if (warnId != null) {
			userSetWarn = this.userSetWarnMapper.selectByPrimaryKey(warnId);
		}
		return userSetWarn;
	}

	/**
	 * @功能 {删除预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 下午3:23:37
	 */
	public int deleteWarning(Integer warnId) {
		// WarnResultExample exa = new WarnResultExample();
		// exa.createCriteria().andBsIdEqualTo(warnId);
		// warnResultMapper.deleteByExample(exa);
		return this.userSetWarnMapper.deleteByPrimaryKey(warnId);
	}

	/**
	 * @功能 {更新预警信息}
	 * @作者 MaxBill
	 * @时间 2017年7月25日 下午3:23:59
	 */
	public int updateWarning(UserSetWarn userSetWarn) {
		return this.userSetWarnMapper.updateByPrimaryKeySelective(userSetWarn);
	}

	/**
	 * @功能 {我的預警消息提醒}
	 * @作者 MaxBill
	 * @时间 2017年7月28日 下午1:39:11
	 */
	public DataPageBean findWarnResult(Pager<WarnResult> pager, WarnResult warnResult) {
		Pager<WarnResult> warnResultPage;
		if (StringUtils.isNotEmpty(warnResult.getTargetName())) {
			warnResultPage = this.warnResultMapper.findWarnResultByName(pager, warnResult);
		} else {
			warnResultPage = this.warnResultMapper.findWarnResult(pager, warnResult);
		}
		DataPageBean dataPage = new DataPageBean();
		dataPage.setCurPage(warnResultPage.getCurrentPage());
		dataPage.setPageSize(warnResultPage.getPageSize());
		dataPage.setTotal(warnResultPage.getTotalCount());
		dataPage.setTotalPage(warnResultPage.getTotalPage());
		List<WarnResult> warnResultList = warnResultPage.getPageItems();
		List<WarnResultBean> warnResultBeanList = new ArrayList<WarnResultBean>();
		if (null != warnResultList) {
			for (WarnResult result : warnResultList) {
				UserSetWarn usw = userSetWarnMapper.selectByPrimaryKeyAndNoOp(result.getBsId());
				Target target = targetMapper.selectByPrimaryKey(usw.getTargetCode());
				if (null != target && null != usw) {
					String targetName = target.getTargetName();
					String unitCode = (target.getFieldCode() == null ? "" : target.getFieldCode());
					String sign = "";
					if (StringUtil.isNotNull(unitCode)) {
						DictionaryItem item = dictionaryServiceImpl.findByDictCodeAndDictItemCode("UNIT_TYPE",
								unitCode);
						sign = item.getName();
					}
					WarnResultBean warnResultBean = new WarnResultBean();
					warnResultBean.setId(result.getWrId());
					warnResultBean.setOrg(usw.getOrgCode());
					warnResultBean.setTarget(targetName);
					switch (usw.getWarnCode()) {
					case "1":
						warnResultBean.setTerm(targetName + "大于" + usw.getMinVal() + sign);
						break;
					case "2":
						warnResultBean.setTerm(targetName + "小于" + usw.getMaxVal() + sign);
						break;
					case "3":
						warnResultBean.setTerm(
								targetName + "在" + usw.getMinVal() + sign + "和" + usw.getMaxVal() + sign + "之间");
						break;
					}
					DecimalFormat df = new DecimalFormat("#.##");
					warnResultBean.setValue(df.format(Double.parseDouble(result.getWarnVal().toString())) + sign);
					warnResultBean.setDate(result.getWarnTime());
					warnResultBeanList.add(warnResultBean);
				}
			}
		}
		dataPage.setList(warnResultBeanList);
		return dataPage;
	}

	/**
	 * @功能 {我的預警消息提醒}
	 * @作者 MaxBill
	 * @时间 2017年7月28日 下午1:39:11
	 */
	public DataPageBean findWarnMsg(Pager<WarnResult> pager, String username, String role) {
		Pager<WarnResult> warnResultPage = this.warnResultMapper.findWarnMsg(pager, username, role);
		DataPageBean dataPage = new DataPageBean();
		dataPage.setCurPage(warnResultPage.getCurrentPage());
		dataPage.setPageSize(warnResultPage.getPageSize());
		dataPage.setTotal(warnResultPage.getTotalCount());
		dataPage.setTotalPage(warnResultPage.getTotalPage());
		List<WarnResult> warnResultList = warnResultPage.getPageItems();
		List<WarnResultBean> warnMsgBeanList = new ArrayList<WarnResultBean>();
		if (null != warnResultList) {
			for (WarnResult result : warnResultList) {
				UserSetWarn usw = this.userSetWarnMapper.selectByPrimaryKey(result.getBsId());
				Target target = targetMapper.selectByPrimaryKey(usw.getTargetCode());
				String targetName = target.getTargetName();
				String unitCode = (target.getFieldCode() == null ? "" : target.getFieldCode());
				DictionaryItem item = dictionaryServiceImpl.findByDictCodeAndDictItemCode("UNIT_TYPE", unitCode);
				String sign = "";
				if (null != item) {
					sign = item.getName();
				}
				WarnResultBean warnMsgBean = new WarnResultBean();
				warnMsgBean.setId(result.getWrId());
				warnMsgBean.setOrg(usw.getOrgCode());
				warnMsgBean.setTarget(targetName);
				switch (usw.getWarnCode()) {
				case "1":
					warnMsgBean.setTerm(targetName + "大于" + usw.getMinVal() + sign);
					break;
				case "2":
					warnMsgBean.setTerm(targetName + "小于" + usw.getMaxVal() + sign);
					break;
				case "3":
					warnMsgBean
							.setTerm(targetName + "在" + usw.getMinVal() + sign + "和" + usw.getMaxVal() + sign + "之间");
					break;
				}
				DecimalFormat df = new DecimalFormat("#.##");
				String val = "人".equals(sign) ? result.getWarnVal().intValue() + sign
						: df.format(Double.parseDouble(result.getWarnVal().toString())) + sign;
				warnMsgBean.setValue(val);
				warnMsgBean.setDate(result.getWarnTime());
				warnMsgBeanList.add(warnMsgBean);
			}
		}
		dataPage.setList(warnMsgBeanList);
		return dataPage;
	}

	/**
	 * @功能 {按id查询预警消息}
	 * @作者 MaxBill
	 * @时间 2017年7月28日 下午3:39:13
	 */
	public int readWarnResult(Integer id) {
		WarnResult warnResult = this.warnResultMapper.selectByPrimaryKey(id);
		if (null != warnResult) {
			warnResult.setIsRead("1");
			warnResult.setReadTime(new Date());
			return this.warnResultMapper.updateByPrimaryKeySelective(warnResult);
		} else {
			return 0;
		}
	}

	@Override
	public String findOrgNameByOrgId(String roleOrg) {
		return organizationsMapper.findOrgNameByOrgCode(roleOrg);
	}

	@Override
	public List<UserSetWarn> selectNoResOnWarn(String role, String roleOrg) {
		return userSetWarnMapper.findNoResOnWarn(role, roleOrg);
	}

	@Override
	public TarInitSql getSqlCodeByTarget(String targetCode, String funId) {
		TarInitSqlExample tarInitSqlExample = new TarInitSqlExample();
		tarInitSqlExample.createCriteria().andTargetCodeEqualTo(targetCode).andFunIdsEqualTo(funId);
		List<TarInitSql> tarInitSqls = tarInitSqlMapper.selectByExample(tarInitSqlExample);
		if (!tarInitSqls.isEmpty() && null != tarInitSqls) {
			return tarInitSqls.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<WarnResult> selectByUserRoleBsId(String username, String role, Integer bsId) {
		WarnResultExample warnResultExample = new WarnResultExample();
		if (bsId != null) {
			warnResultExample.createCriteria().andUsernameEqualTo(username).andRoleCodeEqualTo(role)
					.andBsIdEqualTo(bsId).andIsReadEqualTo("0");
		} else {
			warnResultExample.createCriteria().andUsernameEqualTo(username).andRoleCodeEqualTo(role)
					.andIsReadEqualTo("0");
		}
		return warnResultMapper.selectByExample(warnResultExample);
	}

	@Override
	public void insertWarnResult(WarnResult warnResult) {
		warnResultMapper.insert(warnResult);
	}

	@Override
	public void updateWarnResult(WarnResult warnResult) {
		warnResultMapper.updateByPrimaryKey(warnResult);
	}

	@Override
	public WarnResult selectWarnResultByWrId(Integer warnId) {
		return warnResultMapper.selectByPrimaryKey(warnId);
	}

}
