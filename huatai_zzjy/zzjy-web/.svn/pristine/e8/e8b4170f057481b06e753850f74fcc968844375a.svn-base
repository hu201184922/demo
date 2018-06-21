package com.huatai.web.thrift.service;

import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.huatai.web.bean.TargetDetailBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.bean.InsurProcessBean;
import com.huatai.web.bean.OrganizationBean;
import com.huatai.web.bean.QueryDimDetailBean;
import com.huatai.web.bean.TargetDASBean;
import com.huatai.web.model.ManageCom;
import com.huatai.web.model.QueryDimDetail;

public interface DashService {

	List<TargetBean> getPremiumDetail(String role, String premium);

	TargetDetailBean getInitPremiumDetail(String role, String roleOrg, String dept, String code, JSONObject channel, String regCode, String riskCode,String targetCode);

	List<QueryDimDetailBean> findQueryDimDetailByCode();

	List<ManageCom> findManageComByOrgCode(String orgCode,int leng);

	List<OrganizationBean> findOrganizationComByOrgCode(String orgCode,int leng);

	List<InsurProcessBean> findInsurProcess();

	List<TargetDASBean> findTargetByRoleCode(String code,String role);

	List<InsurProcessBean> findToTargetByParentCodeAndRoleCode(String parnetCode, String roleCode);

	List<QueryDimDetail> findAllChannelList();

}
