package com.huatai.web.thrift.service;

import java.util.List;

import com.huatai.web.bean.NoticeBean;
import com.huatai.web.model.CoreTarget;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.JyfxTarget;
import com.huatai.web.model.Notice;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;

public interface IndexService {

	List<GroDimDetail> findGroDimDetailListByGdid(String code);

	List<Notice> listNotice(String roleDept);

	NoticeBean lookNotice(Integer noticeId);

	TemReg findRegionByCode(String regCode);

	List<TarReg> findTarRegsByRegion(Integer regId, String role, String roleOrg, String roleDept);

	TarRegSql findTarRegSql(int type, String tar, Integer regId, String groupType, String groupDetail, String dateType);

	TarReg findTarRegByRegAndTarget(Integer regId, String targetCode);

	List<String> findTarRegTabHeadByReg(Integer regId);

	List<String> findTarRegTabHeadByRegAndTar(Integer regId, String tar);

	List<TemReg> findRegionByTemp(String temp);

	List<String> findTargetByRegAndRoleAndOrgAndDept(Integer regId, String role, String roleOrg, String roleDept);

	Target findTargetByCode(String code);

	List<JyfxTarget> findJyfxTargetByTarget(String target);

	TarGroDim findUnionTarGroDimByTar(String tar);

	List<CoreTarget> findCoreTargetByPid(String target);

	CoreTarget findCoreTargetByTarget(String target);

	String findDeptCodeByTar(String targetCode);

	List<GroDimDetail> findIndexGroDetailListBySubWithDate(String subCode);
}
