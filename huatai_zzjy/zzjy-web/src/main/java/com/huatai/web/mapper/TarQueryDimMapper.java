package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TarQueryDim;
import com.huatai.web.model.TarQueryDimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * @功能 
 * @作者    胡智辉
 * @时间 2017年8月3日
 * @版本 v1.0.0
 */
@MyBatisRepository
public interface TarQueryDimMapper {
    int countByExample(TarQueryDimExample example);

    int deleteByExample(TarQueryDimExample example);

    int deleteByPrimaryKey(Integer tqdId);

    int insert(TarQueryDim record);

    int insertSelective(TarQueryDim record);

    List<TarQueryDim> selectByExample(TarQueryDimExample example);

    TarQueryDim selectByPrimaryKey(Integer tqdId);

    int updateByExampleSelective(@Param("record") TarQueryDim record, @Param("example") TarQueryDimExample example);

    int updateByExample(@Param("record") TarQueryDim record, @Param("example") TarQueryDimExample example);

    int updateByPrimaryKeySelective(TarQueryDim record);

    int updateByPrimaryKey(TarQueryDim record);

	List<TarQueryDim> findAll();

	List<TarQueryDim> findTarQueryDimByTargetCode(String targetCode);

	int deleteByTargetCode(String targetCode);

	int updateByTargetCode(TarQueryDim tarQueryDim);
}