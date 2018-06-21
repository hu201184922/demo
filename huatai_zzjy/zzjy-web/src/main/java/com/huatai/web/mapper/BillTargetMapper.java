package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.BillTargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface BillTargetMapper {

	int countByExample(BillTargetExample example);

	int deleteByExample(BillTargetExample example);

	int deleteByPrimaryKey(Integer btId);

	int insert(BillTarget record);

	int insertSelective(BillTarget record);

	List<BillTarget> selectByExample(BillTargetExample example);

	BillTarget selectByPrimaryKey(Integer btId);

	int updateByExampleSelective(@Param("record") BillTarget record, @Param("example") BillTargetExample example);

	int updateByExample(@Param("record") BillTarget record, @Param("example") BillTargetExample example);

	int updateByPrimaryKeySelective(BillTarget record);

	int updateByPrimaryKey(BillTarget record);

	List<BillTarget> findBillTargetByBill(@Param("billId") Integer billId);
}