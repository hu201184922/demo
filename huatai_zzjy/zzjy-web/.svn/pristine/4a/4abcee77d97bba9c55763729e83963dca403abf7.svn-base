package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.RenewalTarget;
import com.huatai.web.model.RenewalTargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DataSource(name="dataSource2")
@MyBatisRepository
public interface RenewalTargetMapper {
    int countByExample(RenewalTargetExample example);

    int deleteByExample(RenewalTargetExample example);

    int insert(RenewalTarget record);

    int insertSelective(RenewalTarget record);

    List<RenewalTarget> selectByExample(RenewalTargetExample example);

    int updateByExampleSelective(@Param("record") RenewalTarget record, @Param("example") RenewalTargetExample example);

    int updateByExample(@Param("record") RenewalTarget record, @Param("example") RenewalTargetExample example);
}