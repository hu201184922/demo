package com.ehuatai.realtime.mapper.dma;

import org.apache.ibatis.annotations.Param;

import com.ehuatai.realtime.domain.dma.PersonInsuredPremium;
import com.ehuatai.realtime.domain.dma.PersonInsuredPremiumExample;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;

@DataSource(name="dataSource3",value="dataSource3")
@MyBatisRepository
public interface PersonInsuredPremiumMapper {
    int countByExample(PersonInsuredPremiumExample example);

    int deleteByExample(PersonInsuredPremiumExample example);

    int insert(PersonInsuredPremium record);

    int insertSelective(PersonInsuredPremium record);

    java.util.List<com.ehuatai.realtime.domain.dma.PersonInsuredPremium> selectByExample(PersonInsuredPremiumExample example);

    int updateByExampleSelective(@Param("record") PersonInsuredPremium record, @Param("example") PersonInsuredPremiumExample example);

    int updateByExample(@Param("record") PersonInsuredPremium record, @Param("example") PersonInsuredPremiumExample example);
}