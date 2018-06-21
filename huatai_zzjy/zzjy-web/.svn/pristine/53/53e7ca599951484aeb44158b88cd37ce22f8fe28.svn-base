package com.huatai.web.thrift.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.bean.DataPageBean;
import com.huatai.web.bean.OrgBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.model.CustPlate;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.PlateBill;
import com.huatai.web.model.PlateOneTar;
import com.huatai.web.model.PlateTwoTar;
import com.huatai.web.model.PlateTwoTarGro;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.TarStatis;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;

/**
 * @功能 {板块业务接口}
 * @作者 MaxBill
 * @时间 2017年7月24日 下午4:02:24
 */
public interface BlockService {

	CustPlate findCustPlateById(Integer plateId);

	int deletePlate(Integer plateId);

	List<Target> findFixedPlateByRole(String role);

	Target findDefaultTargetByLevel(String blockCode, String type, String dateType);

	Target findDefaultTargetByLevelGroup(String blockCode, String type);

	String findTargetNameByCode(String code);

	List<TarReg> findTarRegsBySubAndRole(String temp, String sub, String role);

	DataPageBean myPlateList(Pager pager, String role, String userName, String blockName);

	TarRegSql findTarRegSql(int type, String tar, Integer regId, String groupType, String groupDetail, String dateType);

	PlateBill findPlateBillSql(int type, Integer blockId, Integer regId, String groupType, String groupDetail,
			String dateType);

	TemReg findRegionByCode(String regCode);

	List<String> findTarRegTabHeadByRegAndTar(Integer regId, String target);

	List<CustPlate> findCustPlateByUser(String role, String userName);

	CustPlate findCustPlateByUserNameAndPlate(String userName, String role, String plate);

	List<TargetBean> findCustPlateByRole(String role);

	List<TargetBean> findTargetByRoleAndTypeWithTarReg(String regCode, String role, String roleOrg, String type);

	List<PlateOneTar> findPlateOneTarByBlockWithDate(Integer blockId, String dateType);

	List<PlateOneTar> findPlateOneTarByBlock(Integer blockId);

	List<PlateTwoTar> findPlateTwoTarByGroup(Integer groupId);

	List<PlateTwoTarGro> findPlateTwoTarGroByBlock(Integer blockId);

	TarReg findTarRegByRegAndSub(Integer regId, String subject);

	int savePlate(CustPlate custPlate, String target1, String target2, String roleOrg);

	int editPlate(CustPlate custPlate, String target1, String target2, String roleOrg);

	int saveUserDefTarget(List<String> targetList, String user, String role, String subject, String level);

	List<GroDimDetail> findGroDimDetailListByGdid(String code);

	List<String> findRegionByRoleorgAndSubject(String temp, String roleOrg, String subject, String role);

	PlateOneTar findBlockDefaultOneTarget(Integer blockId);

	PlateTwoTarGro findBlockDefaultTwoTarget(Integer blockId);

	List<TemReg> findTemRegListByBlock(Integer blockId);

	TarReg findTarRegByRegAndTarget(Integer regId, String targetCode);

	Target findTargetByCode(String code);

	List<String> findTargetByRegAndRoleAndOrgAndDept(Integer regId, String role, String roleOrg, String roleDept);

	List<String> findTargetByRegAndRoleAndOrgAndDeptAndSubject(Integer regId, String role, String roleOrg,
			String roleDept, String subject);

	List<Target> findTargetBysubAndType(String role, String type, String sub, String dateType);

	List<Target> findTargetBysubAndTypeBack(String role, String type, String sub);

	List<TargetBean> findTargetByRoleAndSubAndRegWithTarReg(String role, String sub, Integer reg, String dateType);

	TarReg findTarRegByRegAndSubAndTar(Integer regId, String subject, String targetCode);

	List<TarStatis> findTarStatisBySub(String subject);

	List<String> findTarRegTabHeadByReg(Integer regId);

	List<OrgBean> getOrgList(int type, String code, String roleDept);

	String findGroDetailOrgCodeByTarget(String target);

	TarInitSql findFinalTarInitSql(String targetCode);

	TarInitSql findBelowTarInitSql(String targetCode);

	List<TargetBean> findTargetByParentCode(String regCode, String role, String type, String parentCode);

	List<TargetBean> findTargetByParentCodes(String role, String type, String parentCode);

	List<TarReg> findPlateTarRegByRegAndPlate(Integer regId, Integer blockId);

	GroDimDetail findGroDimDetailById(Integer gddId);

	List<String> findDefaultTarByTempAndSubAndUser(String temp, String sub, String username, String role, String level);

	TarGroDim findTarGroDimByTar(String tar);

	TarGroDim findUnionTarGroDimByTar(String tar);

	List<TargetBean> findBlockOneTargetTypeByParam(String role, Integer blockId, String roleOrg);

	List<TargetBean> findSaveBlockTargetByParam(String regCode, String role, String roleOrg);

	List<TargetBean> findEditBlockOneTargetByParam(String role, Integer blockId, String roleOrg);

	List<Object> findEditBlockTwoTargetByParam(String role, Integer blockId, String roleOrg);

	List<TargetBean> findBlockTwoTargetTypeByParam(String role, Integer blockId, String roleOrg);

	List<QueryDim> findQueryDimByTar(Integer blockId);

	List<PlateTwoTar> findPlateTwoTarByGroupWithDate(Integer groupId, String dateType);

	// 固定板块机构对比下拉框
	List<TarReg> findMoreTarRegsBySubAndRole(List<String> temps, String blockCode, String role, String groupbyDate);

	// 自定义板块机构对比下拉框
	List<PlateOneTar> findAllPlateTarByBlock(String blockId, String groupbyDate, String roleCate);

}