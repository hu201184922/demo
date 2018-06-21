package com.huatai.web.thrift.service;

import java.util.List;

import com.huatai.web.bean.OrgBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterField;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.RoleTarget;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.model.TarQueryDim;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.TarStatis;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.Templet;

public interface SubjectAnalysisService {

	List<Templet> findAllTemplet();

	Target findTargetByCode(String code);

	List<GroDimDetail> findGroDimDetailListByGdid(String code);

	Target findDefaultTargetByLevel(String pid, String type, String dateType);

	Target findDefaultTargetByLevelGroup(String pid, String type);

	List<String> findRegionByTemp(String temp);

	TemReg findRegionByCode(String regCode);

	List<TarReg> findtagetCodeByRegionId(Integer regId);

	TarReg findTarReg(String tar, Integer regId);

	TarRegSql findTarRegSql(int type, String tar, Integer regId, String groupType, String groupDetail, String dateType);

	SqlAlias findSqlAliasBySqlId(Integer sqlId);

	Inter findInterByTableName(String tableNmae);

	InterField findInterFieldByInterAndDim(Integer interId, String dim);

	List<TarReg> findTarRegsBySubAndReg(String sub, Integer regId);

	List<QueryDimDetail> findQueryDimDetailByQDcode(String dimCode);

	List<TarReg> findTarRegBySubAndReg(String subject, Integer regId);

	List<Target> findTargetLevel(String subject, String type);

	List<TargetBean> findSubjectByTypeAndRole(String isPlate, String role, String isRealtime);

	List<TargetBean> findTargetByTypeAndRole(String type, String role, String pid);

	List<Target> findTargetByTypeAndRoleAndPlate(String type, String role, String pid);

	List<TargetBean> findTargetByTypeAndRoleAndReg(String type, String role, String pid, Integer reg);

	List<TarReg> findTarRegsBySubAndRegAndRole(String sub, Integer regId, String role);

	List<TarQueryDim> findTarQueryDim(String targetCode, String queryCode);

	int saveUserDefTarget(List<String> targetList, String user, String role, String subject, String level);

	List<TarReg> findTarRegsBySubAndRole(String temp, String sub, String role);

	TarReg findTarRegSubAndTar(String sub, String tar);

	RoleTarget findtargetByrole(String role, String targetStr);

	List<Target> findTargetByRoleAndType(String role, String type, String blockCode, String parentCode);

	List<Target> findTargetByRoleAndTypeAndReg(String role, String type, String blockCode, Integer regId);

	TarReg findOrgByTargtAndRen(String targetCode, String subject, Integer regId);

	List<String> findRegionByRoleorgAndSubject(String temp, String roleOrg, String subject, String role);

	List<Target> findTargetBysubAndType(String role, String type, String sub, String dateType);

	List<Target> findTargetBysubAndTypeBack(String role, String type, String sub);

	List<String> findTarRegTabHeadByReg(Integer regId);

	List<String> findTarRegTabHeadByRegAndTar(Integer regId, String target);

	TarReg findTarRegByRegAndSub(Integer regId, String subject);

	String findTargetNameByCode(String code);

	TarReg findTarRegByRegAndSubAndTar(Integer regId, String subject, String targetCode);

	List<TarStatis> findTarStatisBySub(String subject);

	List<String> findTargetByRegAndRoleAndOrgAndDeptAndSubject(Integer regId, String role, String roleOrg,
			String roleDept, String subject);

	List<TargetBean> findTargetByRoleAndSubAndRegWithTarReg(String role, String sub, Integer reg, String dateType);

	List<TargetBean> findTargetByRoleAndSubAndRegWithTarRegBack(String role, String sub, Integer reg);

	List<TargetBean> findTargetByRoleAndSubAndRegAndGraph(String role, String type, Integer reg, String sub);

	List<TargetBean> findTargetByRoleAndSubAndRegAndGraphType(String role, String type, Integer reg, String sub);

	TarReg findTarRegByRegAndTarget(Integer regId, String targetCode);

	List<OrgBean> getOrgList(int type, String code, String roleDept);

	TarRegQue findTarRegQueBySubAndTarAndTempAndQue(String sub, String target, String temp, String que);

	List<TarRegQue> findTarRegQueBySubAndTemp(String sub, String temp);

	QueryDim findQueryDimById(Integer id);

	List<String> findRoleDefaultTarget(String role, String userName, String subject);

	List<Target> findTargetByRoleAndBlockAndType(String role, Integer reId, String type, String parentCode);

	boolean isTarSetDefault(String temp, String subject, String targetCode, String role, String opType);

	Target findPTarInfoByTar(String targetCode);

	List<String> findDefaultTarByTempAndSubAndUser(String temp, String sub, String username, String role, String level);

	List<Target> findTargetByTypeAndRoleAndBlock(String type, String role, String block);

	List<TarReg> findMoreTarRegsBySubAndRole(List<String> temps, String subject, String role, String groupbyDate);

	boolean isdefaultTarget(String username, String target, String block, String role);

	List<Target> findDefaultTargetByLevelByTop(String pid, String type, int size);

	List<TarRegSql> findTarRegSqlList(String tar, Integer regId, String dateType, String subcode);
}
