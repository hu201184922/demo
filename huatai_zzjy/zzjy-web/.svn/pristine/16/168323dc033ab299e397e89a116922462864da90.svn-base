package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.Bill;
import com.huatai.web.model.BillExample;
import com.huatai.web.model.BillTargetDetail;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.GroDimCodeStr;

@MyBatisRepository
public interface BillMapper {

	int countByExample(BillExample example);

	int deleteByExample(BillExample example);

	int deleteByPrimaryKey(Integer billId);

	int insert(Bill record);

	int insertSelective(Bill record);

	List<Bill> selectByExample(BillExample example);

	Bill selectByPrimaryKey(Integer billId);

	int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

	int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);

	int updateByPrimaryKeySelective(Bill record);

	int updateByPrimaryKey(Bill record);

	Pager<Bill> findByPager(Pager<Bill> pager, Bill record);

	List<GroDimCodeStr> selectGroDimByTars(@Param("tarsStr") String tarsStr);

	List<BillTargetDetail> getTarListByQid(@Param("qid") String qid);

	List<DeptInfo> selectDeptInfo();

	Pager<Bill> findBillByPage(Pager<Bill> pager, @Param("role") String role, @Param("username") String username,
			@Param("billname") String billname);
}