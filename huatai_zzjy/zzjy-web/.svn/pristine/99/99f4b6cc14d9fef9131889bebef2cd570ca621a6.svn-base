package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.PremTarget;
import com.huatai.web.model.PremTargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DataSource(name="dataSource2")
@MyBatisRepository
public interface PremTargetMapper {
    int countByExample(PremTargetExample example);

    int deleteByExample(PremTargetExample example);

    int insert(List<PremTarget> premTarget);

    int insertSelective(PremTarget record);

    List<PremTarget> selectByExample(PremTargetExample example);

    int updateByExampleSelective(@Param("record") PremTarget record, @Param("example") PremTargetExample example);

    int updateByExample(@Param("record") PremTarget record, @Param("example") PremTargetExample example);

	List<PremTarget> findPrem();

	int updatePremByOrgCodeAndDateCode(PremTarget pt);
}