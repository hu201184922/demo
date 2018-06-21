package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.GroDim;
import com.huatai.web.model.GroDimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface GroDimMapper {
    int countByExample(GroDimExample example);

    int deleteByExample(GroDimExample example);

    int deleteByPrimaryKey(Integer gdId);

    int insert(GroDim record);

    int insertSelective(GroDim record);

    List<GroDim> selectByExample(GroDimExample example);

    GroDim selectByPrimaryKey(Integer gdId);

    int updateByExampleSelective(@Param("record") GroDim record, @Param("example") GroDimExample example);

    int updateByExample(@Param("record") GroDim record, @Param("example") GroDimExample example);

    int updateByPrimaryKeySelective(GroDim record);

    int updateByPrimaryKey(GroDim record);

	Pager<GroDim> findGroDimByPage(Pager<GroDim> pager, GroDim record);

	List<GroDim> findGroDimAll();
	
	List<GroDim> findGroDimByTargetCode(String targetCode);

}