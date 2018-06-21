package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.RiskTarget;
import com.huatai.web.model.RiskTargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DataSource(name="dataSource2")
@MyBatisRepository
public interface RiskTargetMapper {
    int countByExample(RiskTargetExample example);

    int deleteByExample(RiskTargetExample example);

    int insert(List<RiskTarget> list);

    int insertSelective(RiskTarget record);

    List<RiskTarget> selectByExample(RiskTargetExample example);

    int updateByExampleSelective(@Param("record") RiskTarget record, @Param("example") RiskTargetExample example);

    int updateByExample(@Param("record") RiskTarget record, @Param("example") RiskTargetExample example);

	List<RiskTarget> findRisk();

	int updateByOrgCodeAndDateCode(RiskTarget rt);
}