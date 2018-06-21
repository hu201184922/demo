package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.Target;

public interface TargetService {
	// 添加
	int addTarget(Target target);

	// 删除
	int deleteTarget(String id);

	// 更新
	int updateTarget(Target target);

	// 分页
	Pager<Target> findTargetByPage(Pager<Target> pager, Target target);

	List<Target> selectByIsHomeCoreTarget(String string);
	
	Target selectByPrimaryKey(String targetCode);

	Target selectByTargetName(String targetName);
	
	List<Target> findTargetByDeptCodeOrQueryCode(String deptCode,String regCode);

	List<Target> findAllTarget();

	List<Target> selectTargetByTree(String treeNode);
	
	List<Target> findTargetByTargetType(String string);
	
	List<Target> findTargetByRegId(TarReg tarReg);

	List<Target> findTargetSelective(Target target);

	int updateTarget(Target record, String oldTargetCode, String[] gdIds, String[] gddIds, String[] qdIds);
	
	Pager<Target> findTargetByPage2(Pager<Target> pager, Target record);
	
	Pager<Target> findTargetStatisByPage(Pager<Target> pager, Target record);
	
	Pager<Target> findRefTarget(Pager<Target> pager,String[] regCodes,Target target);

	List<Target> findTargetParentId(String string);

	List<Target> findParentTargetById(String parentCode);

	String findTargetCodeByTargetName(String targetName);

	List<Target> findDeptCodeByGroup();
	
	List<Target> findTargetByIsWarnType(String dept);

	String findFieldByTargetCode(String targetCode);
	
	List<Target> findTargetByDept(String depts);

	List<Target> findByIsStaTar(String depts);

	List<Target> findTargetStatis(Target target);

	List<Target> findByIsStatis();

	List<Target> findByTargetType(Target target);

	Target findByTargetCode(String targetCode);

	Pager<Target> findTarManByPage(Pager<Target> pager, Target target);

	List<Target> findTargetByTarMan();

	List<Target> findTargetBySubject();

	List<Target> findTargetBySubTarget(String subCode);

}
