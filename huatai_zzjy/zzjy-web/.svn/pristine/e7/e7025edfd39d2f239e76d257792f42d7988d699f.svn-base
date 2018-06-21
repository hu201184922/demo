package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TarRegTabHead;
import com.huatai.web.model.TarRegTabHeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface TarRegTabHeadMapper {
    int countByExample(TarRegTabHeadExample example);

    int deleteByExample(TarRegTabHeadExample example);

    int deleteByPrimaryKey(Integer trthId);

    int insert(TarRegTabHead record);

    int insertSelective(TarRegTabHead record);

    List<TarRegTabHead> selectByExample(TarRegTabHeadExample example);

    TarRegTabHead selectByPrimaryKey(Integer trthId);

    int updateByExampleSelective(@Param("record") TarRegTabHead record, @Param("example") TarRegTabHeadExample example);

    int updateByExample(@Param("record") TarRegTabHead record, @Param("example") TarRegTabHeadExample example);

    int updateByPrimaryKeySelective(TarRegTabHead record);

    int updateByPrimaryKey(TarRegTabHead record);
    
    List<TarRegTabHead> findAll(TarRegTabHead record);
}