package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.BillRole;
import com.huatai.web.model.BillRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface BillRoleMapper {
    int countByExample(BillRoleExample example);

    int deleteByExample(BillRoleExample example);

    int deleteByPrimaryKey(Integer twId);

    int insert(BillRole record);

    int insertSelective(BillRole record);

    List<BillRole> selectByExample(BillRoleExample example);

    BillRole selectByPrimaryKey(Integer twId);

    int updateByExampleSelective(@Param("record") BillRole record, @Param("example") BillRoleExample example);

    int updateByExample(@Param("record") BillRole record, @Param("example") BillRoleExample example);

    int updateByPrimaryKeySelective(BillRole record);

    int updateByPrimaryKey(BillRole record);
}