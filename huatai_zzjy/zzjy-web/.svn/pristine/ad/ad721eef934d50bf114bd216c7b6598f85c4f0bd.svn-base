package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegQueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface TarRegQueMapper {

	int countByExample(TarRegQueExample example);

	int deleteByExample(TarRegQueExample example);

	int deleteByPrimaryKey(Long trqId);

	int insert(TarRegQue record);

	int insertSelective(TarRegQue record);

	List<TarRegQue> selectByExample(TarRegQueExample example);

	TarRegQue selectByPrimaryKey(Long trqId);

	int updateByExampleSelective(@Param("record") TarRegQue record, @Param("example") TarRegQueExample example);

	int updateByExample(@Param("record") TarRegQue record, @Param("example") TarRegQueExample example);

	int updateByPrimaryKeySelective(TarRegQue record);

	int updateByPrimaryKey(TarRegQue record);

	List<TarRegQue> findAll(TarRegQue tarRegQue);

	List<TarRegQue> findTarRegQueBySubAndTarAndTempAndQue(@Param("sub") String sub, @Param("tar") String tar,
			@Param("temp") String temp, @Param("que") String que);

	List<TarRegQue> findTarRegQueBySubAndTemp(@Param("sub") String sub, @Param("temp") String temp);

	List<TarRegQue> findTarRegQueBySubAndTempAndRegAndTar(@Param("sub") String sub, @Param("temp") String temp,
			@Param("regId") Integer regId, @Param("tar") String tar);

}