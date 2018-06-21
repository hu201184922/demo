package com.huatai.web.mapper;

import com.huatai.web.model.BillGroDim;
import com.huatai.web.model.BillGroDimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BillGroDimMapper {
    int countByExample(BillGroDimExample example);

    int deleteByExample(BillGroDimExample example);

    int deleteByPrimaryKey(Integer bgdId);

    int insert(BillGroDim record);

    int insertSelective(BillGroDim record);

    List<BillGroDim> selectByExample(BillGroDimExample example);

    BillGroDim selectByPrimaryKey(Integer bgdId);

    int updateByExampleSelective(@Param("record") BillGroDim record, @Param("example") BillGroDimExample example);

    int updateByExample(@Param("record") BillGroDim record, @Param("example") BillGroDimExample example);

    int updateByPrimaryKeySelective(BillGroDim record);

    int updateByPrimaryKey(BillGroDim record);
}