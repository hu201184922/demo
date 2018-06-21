package com.ehuatai.biz.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;

/**
 * @功能 {基础mapper查询}
 * @作者 MaxBill
 * @时间 2017年9月11日 上午11:05:15
 */
@DataSource(name = "dataSource2", value = "dataSource2")
@MyBatisRepository
public interface BaseMapper {

	@Select("${sqlCode}")
	List<HashMap<String, Object>> getDataResult(@Param("sqlCode") String sqlCode);

	@Select("SELECT DISTINCT TEAMCOMSHORTNAME AS NAME, PROVCOMCODE AS CODE FROM D_ORGANIZATION WHERE HEADCOMCODE = #{code} AND PROVCOMCODE IS NOT NULL AND PROVCOMCODE <> #{code} AND STATUS='1' ORDER BY CODE")
	List<HashMap<String, Object>> getCompanyByManage(String code);

	@Select("SELECT DISTINCT TEAMCOMCODE AS CODE, TEAMCOMNAME AS NAME FROM D_ORGANIZATION WHERE COUNTCOMCODE = #{code} AND TEAMCOMCODE IS NOT NULL AND STATUS='1' AND CHANNELFLAG IN('1','5') ORDER BY CODE ")
	List<HashMap<String, Object>> getServerByBranch(String code);

	@Select("SELECT DISTINCT COST_CENTERSTDCODE AS CODE, COST_CENTERDEVNAME AS NAME FROM D_ORGANIZATION WHERE COUNTCOMCODE = #{code} AND COST_CENTERSTDCODE IS NOT NULL AND STATUS='1' ORDER BY CODE ")
	List<HashMap<String, Object>> getCenterByBranch(String code);

	@Select("SELECT DISTINCT HEADGROUPCODE AS CODE , HEADGROUPNAME AS NAME  FROM D_ORGANIZATION WHERE TEAMCOMCODE =  #{code} AND TEAMCOMCODE IS NOT NULL AND HEADGROUPCODE <> #{code} AND STATUS='1' ORDER BY CODE ")
	List<HashMap<String, Object>> getRegionByServer(String code);

	@Select("SELECT DISTINCT HEADGROUPCODE AS CODE, HEADGROUPNAME AS NAME FROM D_ORGANIZATION WHERE COST_CENTERSTDCODE = #{code} AND HEADGROUPCODE NOT IN ( SELECT DISTINCT TEAMCOMCODE FROM D_ORGANIZATION WHERE COST_CENTERSTDCODE = #{code} AND STATUS='1' ) AND STATUS='1' ORDER BY CODE ")
	List<HashMap<String, Object>> getRegionByCenter(String code);

	@Select("SELECT DISTINCT PROVGROUPCODE AS CODE , PROVGROUPNAME AS NAME  FROM D_ORGANIZATION WHERE HEADGROUPCODE = #{code} AND PROVGROUPCODE <> #{code} AND STATUS='1' ORDER BY CODE ")
	List<HashMap<String, Object>> getSectionByRegion(String code);

	@Select("SELECT COUNTGROUPCODE AS CODE , COUNTGROUPNAME AS NAME FROM D_ORGANIZATION WHERE PROVGROUPCODE = #{code} AND EFFECTENDDATE = DATE '9999-12-31' AND COUNTGROUPCODE <> #{code} AND STATUS='1' ORDER BY CODE")
	List<HashMap<String, Object>> getGroupsBySection(String code);

	@Select("SELECT D.AGENT_ID AS CODE, D.AGENTNAME AS NAME FROM D_AGENT D JOIN D_ORGANIZATION DO ON D.AGENTGROUPID = DO.AGENTGROUPID  WHERE DO.AGENTGROUPCODE = #{code} AND D.AGENTSTATE = '1' AND DO.EFFECTENDDATE = DATE '9999-12-31' AND D.EFFECTENDDATE = DATE '9999-12-31' ORDER BY CODE ")
	List<HashMap<String, Object>> getSellersByGroups(String code);

}
