package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.BillSql;
import com.huatai.web.model.BillSqlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface BillSqlMapper {
    int countByExample(BillSqlExample example);

    int deleteByExample(BillSqlExample example);

    int deleteByPrimaryKey(Integer bsId);

    int insert(BillSql record);

    int insertSelective(BillSql record);

    List<BillSql> selectByExample(BillSqlExample example);

    BillSql selectByPrimaryKey(Integer bsId);

    int updateByExampleSelective(@Param("record") BillSql record, @Param("example") BillSqlExample example);

    int updateByExample(@Param("record") BillSql record, @Param("example") BillSqlExample example);

    int updateByPrimaryKeySelective(BillSql record);

    int updateByPrimaryKey(BillSql record);
}