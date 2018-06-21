package com.huatai.web.mapper;

import com.huatai.web.model.PlateBill;
import com.huatai.web.model.PlateBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlateBillMapper {
    int countByExample(PlateBillExample example);

    int deleteByExample(PlateBillExample example);

    int deleteByPrimaryKey(Integer pbId);

    int insert(PlateBill record);

    int insertSelective(PlateBill record);

    List<PlateBill> selectByExample(PlateBillExample example);

    PlateBill selectByPrimaryKey(Integer pbId);

    int updateByExampleSelective(@Param("record") PlateBill record, @Param("example") PlateBillExample example);

    int updateByExample(@Param("record") PlateBill record, @Param("example") PlateBillExample example);

    int updateByPrimaryKeySelective(PlateBill record);

    int updateByPrimaryKey(PlateBill record);
}