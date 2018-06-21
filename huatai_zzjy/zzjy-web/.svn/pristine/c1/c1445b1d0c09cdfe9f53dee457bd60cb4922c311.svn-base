package com.huatai.web.thrift.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.bean.DataPageBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.UserSetWarn;
import com.huatai.web.model.WarnResult;

/**
 * @功能 {工具业务接口}
 * @作者 MaxBill
 * @时间 2017年7月25日 下午4:15:12
 */
public interface WarningService {

	int saveWarning(UserSetWarn userSetWarn);
	
	List<UserSetWarn> queryWarning(String username, String orgCode, String targetCode);

	UserSetWarn findWarning(Integer warnId);

	int deleteWarning(Integer warnId);

	int updateWarning(UserSetWarn userSetWarn);

	DataPageBean myWarnings(Pager<UserSetWarn> pager, UserSetWarn userSetWarn);

	List<TargetBean> findTargetList(String targetCode, String role);

	DataPageBean findWarnMsg(Pager<WarnResult> pager, String username, String role);

	DataPageBean findWarnResult(Pager<WarnResult> pager, WarnResult warnResult);

	int readWarnResult(Integer id);

	String findOrgNameByOrgId(String roleOrg);

	List<UserSetWarn> selectNoResOnWarn(String role, String roleOrg);

	TarInitSql getSqlCodeByTarget(String targetCode, String funId);

	List<WarnResult> selectByUserRoleBsId(String username, String role, Integer bsId);

	void insertWarnResult(WarnResult warnResult);

	void updateWarnResult(WarnResult warnResult);

	WarnResult selectWarnResultByWrId(Integer warnId);
}
