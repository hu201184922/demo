package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.Templet;
import com.huatai.web.model.TempletExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface TempletMapper {
    int countByExample(TempletExample example);

    int deleteByExample(TempletExample example);

    int deleteByPrimaryKey(Integer tempId);

    int insert(Templet record);

    int insertSelective(Templet record);

    List<Templet> selectByExample(TempletExample example);

    Templet selectByPrimaryKey(Integer tempId);

    int updateByExampleSelective(@Param("record") Templet record, @Param("example") TempletExample example);

    int updateByExample(@Param("record") Templet record, @Param("example") TempletExample example);

    int updateByPrimaryKeySelective(Templet record);

    int updateByPrimaryKey(Templet record);

	Pager<Templet> findTempletByPage(Pager<Templet> pager, Templet record);
}