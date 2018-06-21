package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.bean.TargetDetailBean;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegExample;

@MyBatisRepository
public interface TarRegMapper {

	int countByExample(TarRegExample example);

	int deleteByExample(TarRegExample example);

	int deleteByPrimaryKey(Integer trId);

	int insert(TarReg record);

	int insertSelective(TarReg record);

	List<TarReg> selectByExample(TarRegExample example);

	TarReg selectByPrimaryKey(Integer trId);

	int updateByExampleSelective(@Param("record") TarReg record, @Param("example") TarRegExample example);

	int updateByExample(@Param("record") TarReg record, @Param("example") TarRegExample example);

	int updateByPrimaryKeySelective(TarReg record);

	int updateByPrimaryKey(TarReg record);

	List<TarReg> findTarRegsBySubAndRegAndRole(@Param("sub") String sub, @Param("regId") Integer regId,
			@Param("role") String role);

	List<TarReg> findTarRegsBySubAndRole(@Param("sub") String sub, @Param("role") String role,
			@Param("temp") String temp);

	List<TarReg> findRegionBySubAndRole(@Param("sub") String sub, @Param("role") String role,
			@Param("temp") String temp);

	List<TarReg> findAll(TarReg tarReg);

	List<TarReg> findAllLike(TarReg tarReg);

	List<TarReg> findTarRegsByRegion(@Param("regId") Integer regId, @Param("role") String role,
			@Param("roleDept") String roleDept);

	List<TarReg> findTarRegsByRegionAndSubject(@Param("regId") Integer regId, @Param("role") String role,
			@Param("roleDept") String roleDept, @Param("subject") String subject);

	TargetDetailBean getTarRegByCOC(@Param("code") String code, @Param("orgType") int orgType,
			@Param("regCode") String regCode);

	List<TarReg> findPlateTarRegByRegAndPlate(@Param("regId") Integer regId, @Param("blockId") Integer blockId);

	List<TarReg> findMoreTarRegsBySubAndRole(@Param("temps") List<String> temps, @Param("subject") String subject,
			@Param("role") String role,@Param("groupbyDate") String groupbyDate);

}