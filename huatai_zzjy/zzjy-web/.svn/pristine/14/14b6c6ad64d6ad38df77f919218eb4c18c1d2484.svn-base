package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.RealtimeTarget;
import com.huatai.web.model.RealtimeTargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DataSource(name="dataSource2")
@MyBatisRepository
public interface RealtimeTargetMapper {
    int countByExample(RealtimeTargetExample example);

    int deleteByExample(RealtimeTargetExample example);

    int insert(List<RealtimeTarget> rt);

    int insertSelective(RealtimeTarget record);

    List<RealtimeTarget> selectByExample(RealtimeTargetExample example);

    int updateByExampleSelective(@Param("record") RealtimeTarget record, @Param("example") RealtimeTargetExample example);

    int updateByExample(@Param("record") RealtimeTarget record, @Param("example") RealtimeTargetExample example);

	List<RealtimeTarget> findGXBOnTime();

	int updateByOrgCodeAndDateCode(RealtimeTarget rt);
}