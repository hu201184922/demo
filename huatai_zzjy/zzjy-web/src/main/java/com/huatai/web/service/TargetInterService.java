package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.TargetInter;

public interface TargetInterService {

	int insertSelective(TargetInter record);

	Pager<TargetInter> findByPager(Pager<TargetInter> pager, TargetInter record);

	int updateByPrimaryKeySelective(TargetInter record);

	int deleteByPrimaryKey(Integer record);

	List<TargetInter> findTargetInterByInterId(String roleCode);

	int updateTargetInter(TargetInter record);

	List<TargetInter> findByIsTargetInter(Integer interId);

	int updateInterId(TargetInter record);

	int updateByInterIdAndTargetCode(Integer interId, String targetCode);
}
