package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillFiltDimExample;
@MyBatisRepository
public interface BillFiltDimMapper {
    int countByExample(BillFiltDimExample example);

    int deleteByExample(BillFiltDimExample example);

    int deleteByPrimaryKey(Integer bfdId);

    int insert(BillFiltDim record);

    int insertSelective(BillFiltDim record);

    List<BillFiltDim> selectByExample(BillFiltDimExample example);

    BillFiltDim selectByPrimaryKey(Integer bfdId);

    int updateByExampleSelective(@Param("record") BillFiltDim record, @Param("example") BillFiltDimExample example);

    int updateByExample(@Param("record") BillFiltDim record, @Param("example") BillFiltDimExample example);

    int updateByPrimaryKeySelective(BillFiltDim record);

    int updateByPrimaryKey(BillFiltDim record);

	Pager<BillFiltDim> findByPager(Pager pager, BillFiltDim record);
	
	List<BillFiltDim> findBillFiltDimByBill(@Param("billId") Integer billId);
}