package com.huatai.web.service;

import java.util.List;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.GroDimDetailExample;


public interface GroDimDetailService {
	
	int countByExample(GroDimDetailExample example);

    int deleteByExample(GroDimDetailExample example);

    int deleteByPrimaryKey(Integer gddId);

    int insert(GroDimDetail record);

    int insertSelective(GroDimDetail record);

    List<GroDimDetail> selectByExample(GroDimDetailExample example);

    GroDimDetail selectByPrimaryKey(Integer gddId);

    int updateByExampleSelective( GroDimDetail record,  GroDimDetailExample example);

    int updateByExample( GroDimDetail record,  GroDimDetailExample example);

    int updateByPrimaryKeySelective(GroDimDetail record);

    int updateByPrimaryKey(GroDimDetail record);
    
    Pager<GroDimDetail> findByPager(Pager<GroDimDetail> pager, GroDimDetail record);

	List<GroDimDetail> findAllGroDimDetail();

	GroDimDetail selectByGroDimName(String groDimName);

	//指标区域详情
	List<GroDimDetail> findGroDimDetailByGdId(String gdId);
	
	List<GroDimDetail> findByTargetCodeAndGdId(String targetCode,Integer gdId);
	
	List<GroDimDetail> findByGroDimTypeCode(String code);
	
	String findGroDimCodeByGroDimName(String groDimName);

	List<GroDimDetail> findByTargetCodeAndGdId(Integer gdId);

	int update(GroDimDetail record);

}
