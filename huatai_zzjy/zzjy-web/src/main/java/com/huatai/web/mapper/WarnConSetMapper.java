package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.WarnConSet;
import com.huatai.web.model.WarnConSetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface WarnConSetMapper {
    int countByExample(WarnConSetExample example);

    int deleteByExample(WarnConSetExample example);

    int deleteByPrimaryKey(String warnCode);

    int insert(WarnConSet record);

    int insertSelective(WarnConSet record);

    List<WarnConSet> selectByExample(WarnConSetExample example);

    WarnConSet selectByPrimaryKey(String warnCode);

    int updateByExampleSelective(@Param("record") WarnConSet record, @Param("example") WarnConSetExample example);

    int updateByExample(@Param("record") WarnConSet record, @Param("example") WarnConSetExample example);

    int updateByPrimaryKeySelective(WarnConSet record);

    int updateByPrimaryKey(WarnConSet record);
}