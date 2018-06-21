package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.WealAnnConfig;
import com.huatai.web.model.WealAnnConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DataSource(name="dataSource2")
@MyBatisRepository
public interface WealAnnConfigMapper {
    int countByExample(WealAnnConfigExample example);

    int deleteByExample(WealAnnConfigExample example);

    int insert(List<WealAnnConfig> list);

    int insertSelective(WealAnnConfig record);

    List<WealAnnConfig> selectByExample(WealAnnConfigExample example);

    int updateByExampleSelective(@Param("record") WealAnnConfig record, @Param("example") WealAnnConfigExample example);

    int updateByExample(@Param("record") WealAnnConfig record, @Param("example") WealAnnConfigExample example);

	List<WealAnnConfig> findWealAnnConfig();

	int updateByOrgCodeAndDateCode(WealAnnConfig wc);
}