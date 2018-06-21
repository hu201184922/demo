package com.huatai.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.bean.InsurProcessBean;
import com.huatai.web.bean.TargetDASBean;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.Target;
import com.huatai.web.model.TargetExample;

@MyBatisRepository
public interface TargetMapper {

	int countByExample(TargetExample example);

	int deleteByExample(TargetExample example);

	int deleteByPrimaryKey(String targetCode);

	int insert(Target record);

	int insertSelective(Target record);

	List<Target> selectByExample(TargetExample example);

	Target selectByPrimaryKey(String targetCode);

	int updateByExampleSelective(@Param("record") Target record, @Param("example") TargetExample example);

	int updateByExample(@Param("record") Target record, @Param("example") TargetExample example);

	int updateByPrimaryKeySelective(@Param("record") Target record);

	int updateByPrimaryKey(Target target);

	List<Target> selectTargetByTree(String treeNode);

	Pager<Target> findTargetByPage(Pager<Target> pager, Target record);

	// 根据isHomeCoreTarget查询获得首页核心指标列表
	List<Target> selectByIsHomeCoreTarget(String isHomeCoreTarget);

	Target selectByTargetName(String targetName);

	List<Target> findTargetByDeptCodeOrQueryCode(@Param("deptCode") String deptCode, @Param("regCode") String regCode,
			@Param("targetName") String targetName);

	List<Target> findTargetByRegId(TarReg tarReg);

	List<Target> findSubjectByTypeAndRole(@Param("isPlate") String isPlate, @Param("role") String role,
			@Param("isRealtime") String isRealtime);

	List<Target> findTargetByTypeAndRole(@Param("type") String type, @Param("role") String role,
			@Param("pid") String pid);

	List<Target> findTargetByTypeAndRoleAndPlate(@Param("type") String type, @Param("role") String role,
			@Param("pid") String pid);

	List<Target> findTargetByTypeAndRoleAndReg(@Param("type") String type, @Param("role") String role,
			@Param("pid") String pid, @Param("reg") Integer reg);

	List<Target> findAll();

	List<Target> findTargetByTargetType(String targetType);

	List<Target> selectByParentCode(String parentCode);

	List<Target> findTargetSelective(Target target);

	int updateByOldTargetCode(@Param("record") Target record, @Param("oldTargetCode") String oldTargetCode);

	Pager<Target> findTargetStatisByPage(Pager<Target> pager, Target record);

	Pager<Target> findRefTarget(Pager<Target> pager, Map<String, Object> params);

	List<Target> findTargetParentId(@Param("parentCode") String parentCode);

	List<Target> findTargetByRoleAndType(@Param("role") String role, @Param("type") String type,
			@Param("blockCode") String blockCode, @Param("parentCode") String parentCode);

	List<Target> findTargetByRoleAndTypeAndReg(@Param("role") String role, @Param("type") String type,
			@Param("blockCode") String blockCode, @Param("regId") Integer regId);

	List<Target> findParentTargetById(@Param("parentCode") String parentCode);

	Pager<Target> findTargetByPage2(Pager<Target> pager, Target record);

	String findTargetCodeByTargetName(@Param("targetName") String targetName);

	List<Target> findTargetBysubAndType(@Param("role") String role, @Param("type") String type,
			@Param("sub") String sub, @Param("dateType") String dateType);

	List<Target> findTargetBysubAndTypeBack(@Param("role") String role, @Param("type") String type,
			@Param("sub") String sub);

	List<Target> findDeptCodeByGroup();

	Pager<Target> findtargetByRole(Pager<Target> pager, @Param("role") String role, @Param("type") String type,
			@Param("targetName") String targetName, @Param("targetCode") String targetCode);

	List<Target> findTargetByIsWarnType(@Param("dept") String dept);

	String findFieldByTargetCode(@Param("targetCode") String targetCode);

	List<Target> findTargetByDept(@Param("depts") String depts);

	List<Target> selectTargetByRole(@Param("role") String role);

	List<Target> findByIsStaTar(@Param("depts") String depts);

	List<Target> findTargetStatis(Target record);

	List<Target> findByIsStatis();

	List<Target> findByTargetType(@Param("target") Target target);

	Target findByTargetCode(@Param("targetCode") String targetCode);

	Pager<Target> findSaveListTargetByRoleWithPage(Pager<Target> pager, @Param("role") String role,
			@Param("roleOrg") String roleOrg, @Param("parentCode") String parentCode,
			@Param("targetCode") String targetCode, @Param("targetName") String targetName);

	Pager<Target> findEditListTargetByRoleWithPage(Pager<Target> pager, @Param("role") String role,
			@Param("roleOrg") String roleOrg, @Param("parentCode") String parentCode,
			@Param("targetCode") String targetCode, @Param("targetName") String targetName,
			@Param("billId") Integer billId);

	List<Target> findTargetTypeByRole(@Param("role") String role, @Param("roleOrg") String roleOrg);

	List<Target> findTargetByRoleAndTypeWithTarReg(@Param("regCode") String regCode, @Param("role") String role,
			@Param("type") String type);

	List<Target> findTargetByparentCode(@Param("regCode") String regCode, @Param("role") String role,
			@Param("type") String type, @Param("parentCode") String parentCode);

	List<Target> findTargetByparentCodes(@Param("role") String role, @Param("type") String type,
			@Param("parentCode") String parentCode);

	List<TargetDASBean> findTargetByRoleCode(@Param("code") String code, @Param("role") String role);

	List<InsurProcessBean> findToTargetByParentCodeAndRoleCode(@Param("parnetCode") String parnetCode,
			@Param("roleCode") String roleCode);

	List<Target> findBlockByRole(@Param("role") String role);

	List<Target> findTargetByRoleAndRegWithTarReg(@Param("role") String role, @Param("reg") Integer reg);

	List<Target> findTargetByRoleAndSubAndRegWithTarReg(@Param("role") String role, @Param("sub") String sub,
			@Param("reg") Integer reg, @Param("dateType") String dateType);

	List<Target> findTargetByRoleAndSubAndRegWithTarRegBack(@Param("role") String role, @Param("sub") String sub,
			@Param("reg") Integer regs);

	List<InsurProcessBean> findInsurProcess();

	Pager<Target> findTarManByPage(Pager<Target> pager, Target target);

	List<Target> findTargetByTarMan();

	List<Target> findTargetByRoleAndBlockAndType(@Param("role") String role, @Param("regId") Integer regId,
			@Param("parentCode") String parentCode, @Param("type") String type);

	List<Target> findTargetBySubject();

	List<Target> findBlockOneTargetTypeByParam(@Param("role") String role, @Param("blockId") Integer blockId,
			@Param("orgType") Integer orgType);

	List<Target> findSaveBlockOneTargetByParam(@Param("regCode") String regCode, @Param("role") String role,
			@Param("orgType") Integer orgType);

	List<Target> findSaveBlockTwoTargetByParam(@Param("regCode") String regCode, @Param("role") String role,
			@Param("orgType") Integer orgType);

	List<Target> findEditBlockOneTargetByParam(@Param("role") String role, @Param("blockId") Integer blockId,
			@Param("orgType") Integer orgType);

	List<Target> findEditBlockTwoTargetByParam(@Param("role") String role, @Param("blockId") Integer blockId,
			@Param("orgType") Integer orgType);

	List<Target> findTargetByRoleAndSubAndRegAndGraph(@Param("role") String role, @Param("type") String type,
			@Param("reg") Integer reg, @Param("sub") String sub);

	List<Target> findTargetByRoleAndSubAndRegAndGraphType(@Param("role") String role, @Param("type") String type,
			@Param("reg") Integer reg, @Param("sub") String sub);

	List<Target> findBlockTwoTargetTypeByParam(@Param("role") String role, @Param("blockId") Integer blockId,
			@Param("orgType") Integer orgType);

	List<Target> findDefaultTargetByLevel(@Param("pid") String pid, @Param("type") String type,
			@Param("dateType") String dateType);

}
