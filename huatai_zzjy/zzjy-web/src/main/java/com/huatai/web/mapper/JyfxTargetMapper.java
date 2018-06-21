package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.JyfxTarget;
import com.huatai.web.model.JyfxTargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface JyfxTargetMapper {
    int countByExample(JyfxTargetExample example);

    int deleteByExample(JyfxTargetExample example);

    int deleteByPrimaryKey(Long jtId);

    int insert(JyfxTarget record);

    int insertSelective(JyfxTarget record);

    List<JyfxTarget> selectByExample(JyfxTargetExample example);

    JyfxTarget selectByPrimaryKey(Long jtId);

    int updateByExampleSelective(@Param("record") JyfxTarget record, @Param("example") JyfxTargetExample example);

    int updateByExample(@Param("record") JyfxTarget record, @Param("example") JyfxTargetExample example);

    int updateByPrimaryKeySelective(JyfxTarget record);

    int updateByPrimaryKey(JyfxTarget record);

	int addTarMan(JyfxTarget record);

	int deleteTarMan(String targetCode);

	List<JyfxTarget> findByAnTargetCode(@Param("anTargetCode")String anTargetCode);

	List<JyfxTarget> findByIsAnTargetCode(@Param("anTargetCode")String anTargetCode,@Param("depts") String depts);

	int updateByTargetCodeAndAnTargetCode(JyfxTarget record);
}