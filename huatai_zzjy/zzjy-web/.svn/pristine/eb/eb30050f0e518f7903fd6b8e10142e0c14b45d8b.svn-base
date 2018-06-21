package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.SetDefaultTar;
import com.huatai.web.model.SetDefaultTarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface SetDefaultTarMapper {
    int countByExample(SetDefaultTarExample example);

    int deleteByExample(SetDefaultTarExample example);

    int deleteByPrimaryKey(Integer sdtId);

    int insert(SetDefaultTar record);

    int insertSelective(SetDefaultTar record);

    List<SetDefaultTar> selectByExample(SetDefaultTarExample example);

    SetDefaultTar selectByPrimaryKey(Integer sdtId);

    int updateByExampleSelective(@Param("record") SetDefaultTar record, @Param("example") SetDefaultTarExample example);

    int updateByExample(@Param("record") SetDefaultTar record, @Param("example") SetDefaultTarExample example);

    int updateByPrimaryKeySelective(SetDefaultTar record);

    int updateByPrimaryKey(SetDefaultTar record);
	
	List<SetDefaultTar> findDefaultTarByTempAndSubAndUser(@Param("temp") String temp, @Param("sub") String sub,
			@Param("username") String username, @Param("role") String role, @Param("level") String level);
}