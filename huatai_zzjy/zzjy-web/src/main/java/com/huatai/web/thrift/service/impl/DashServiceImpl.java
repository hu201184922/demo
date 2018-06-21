package com.huatai.web.thrift.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.TargetDetailBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.mapper.RoleTargetMapper;
import com.huatai.web.mapper.TarRegMapper;
import com.huatai.web.mapper.TarRegSqlMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.sql.SqlFactory;

import com.fairyland.jdp.framework.security.mapper.OrganizationMapper;
import com.huatai.web.bean.InsurProcessBean;
import com.huatai.web.bean.OrganizationBean;
import com.huatai.web.bean.QueryDimDetailBean;
import com.huatai.web.bean.TargetDASBean;
import com.huatai.web.mapper.ManageComMapper;
import com.huatai.web.mapper.QueryDimDetailMapper;
import com.huatai.web.model.ManageCom;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.QueryDimDetailExample;
import com.huatai.web.thrift.service.DashService;

@Service
public class DashServiceImpl implements DashService {

	@Autowired
	private RoleTargetMapper roleTargetMapper;
	@Autowired
	private TarRegMapper tarRegMapper;
	@Autowired
	private TarRegSqlMapper tarRegSqlMapper;
	@Autowired
	private TargetMapper targetMapper;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private SqlFactory sqlFactory;
	@Autowired
	private QueryDimDetailMapper queryDimDetailMapper;
	@Autowired
	private ManageComMapper manageComMapper;
	@Autowired
	private OrganizationMapper organizationMapper;

	// 符号编码
	private static final String unitType = "UNIT_TYPE";

	@Override
	public List<TargetBean> getPremiumDetail(String role, String premium) {
		return roleTargetMapper.getPreDeatailByRoleCode(role, premium);
	}

	@Override
	public List<QueryDimDetailBean> findQueryDimDetailByCode() {
		return queryDimDetailMapper.findQueryDimDetailById();
	}

	@Override
	public TargetDetailBean getInitPremiumDetail(String role, String roleOrg, String dept, String code,
			JSONObject channel, String regCode, String riskCode ,String targetCode) {
		Target targetTemp = this.targetMapper.selectByPrimaryKey(code);
		int orgType = 0;
		if (StringUtil.isNotNull(roleOrg)) {
			switch (roleOrg.length()) {
			case 1:
				orgType = 8;
				break;
			case 3:
				orgType = 7;
				break;
			case 5:
				orgType = 6;
				break;
			default:
				orgType = 5;
				break;
			}
		}
		TargetDetailBean tarRegBean = tarRegMapper.getTarRegByCOC(code, orgType, regCode);
		if (null != tarRegBean) {
			tarRegBean.setTargetName(targetMapper.findByTargetCode(tarRegBean.getTargetCode()).getTargetName());
			String unit = tarRegBean.getUnit();
			if (StringUtil.isNotNull(unit)) {
				String unStr = dictionaryService.findByDictCodeAndDictItemCode(unitType, unit).getName();
				if (StringUtil.isNotNull(unStr)) {
					tarRegBean.setUnit(unStr);
				}
			}
			String color = tarRegBean.getColor() == null ? "" : tarRegBean.getColor();
			tarRegBean.setColor(color);
			List<TarRegSql> trs = tarRegSqlMapper.getSqlByTarRegCode(code, regCode);
			if (trs.size() > 0) {
				TarRegSql tarRegSql = trs.get(0);
				String sql = "";
				sql = sqlFactory.getDashbordSql(tarRegSql.getTargetCode(), targetTemp.getDeptCode(), tarRegSql, roleOrg,
						orgType, null, riskCode, targetCode); //2017-10-26修改：经讨论渠道系数在SQL中写死。
				tarRegBean.setSqlCode(sql);
				tarRegBean.setGroupSqlDim(tarRegSql.getGroupCode());
			}
		}
		return tarRegBean;
	}

	@Override
	public List<ManageCom> findManageComByOrgCode(String orgCode, int leng) {
		return manageComMapper.findManageComByOrgCode(orgCode, leng);
	}

	@Override
	public List<OrganizationBean> findOrganizationComByOrgCode(String orgCode, int leng) {
		return manageComMapper.findOrganizationComByOrgCode(orgCode, leng);
	}

	@Override
	public List<InsurProcessBean> findInsurProcess() {
		return targetMapper.findInsurProcess();
	}

	@Override
	public List<TargetDASBean> findTargetByRoleCode(String code, String role) {
		return targetMapper.findTargetByRoleCode(code, role);
	}

	@Override
	public List<InsurProcessBean> findToTargetByParentCodeAndRoleCode(String parnetCode, String roleCode) {
		return targetMapper.findToTargetByParentCodeAndRoleCode(parnetCode, roleCode);
	}

	@Override
	public List<QueryDimDetail> findAllChannelList() {
		QueryDimDetailExample queryDimDetailExample = new QueryDimDetailExample();
		queryDimDetailExample.createCriteria().andQdIdEqualTo(2);
		return queryDimDetailMapper.selectByExample(queryDimDetailExample);
	}

}
