package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TarWarn;
import com.huatai.web.model.TarWarnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface TarWarnMapper {
    int countByExample(TarWarnExample example);

    int deleteByExample(TarWarnExample example);

    int deleteByPrimaryKey(Integer twId);

    int insert(TarWarn record);

    int insertSelective(TarWarn record);

    List<TarWarn> selectByExample(TarWarnExample example);

    TarWarn selectByPrimaryKey(Integer twId);

    int updateByExampleSelective(@Param("record") TarWarn record, @Param("example") TarWarnExample example);

    int updateByExample(@Param("record") TarWarn record, @Param("example") TarWarnExample example);

    int updateByPrimaryKeySelective(TarWarn record);

    int updateByPrimaryKey(TarWarn record);
}