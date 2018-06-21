package com.huatai.web.service;

import java.text.ParseException;
import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.CoreTarget;
import com.huatai.web.model.CoreTargetExample;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.RelaTarget;

/**
 * @description:
 * @author     ：沈从会
 * @datetime   : 2017年7月21日 下午4:21:06
 */
public interface CoreTargetService {

	int countByExample(CoreTargetExample example);

    int deleteByExample(CoreTargetExample example);

    void deleteByPrimaryKey(Integer ctId) throws ParseException;

    void insert(CoreTarget record) throws ParseException;

    int insertSelective(CoreTarget record);

    List<CoreTarget> selectByExample(CoreTargetExample example);

    CoreTarget selectByPrimaryKey(Integer ctId);

    int updateByExampleSelective( CoreTarget record,  CoreTargetExample example);

    int updateByExample( CoreTarget record,  CoreTargetExample example);

    void updateByPrimaryKeySelective(CoreTarget record) throws ParseException;

    int updateByPrimaryKey(CoreTarget record);
    
    Pager<CoreTarget> findByPager(Pager<CoreTarget> pager, CoreTarget record);

    Pager<RelaTarget> findByPagerRela(Pager<RelaTarget> pager, RelaTarget record);
	
	List<CoreTarget> findByPid(String pid, String deptCode);

	int deleteByPidAndTargetCode(String pid, String targetCode);

	List<CoreTarget> findByPidAndDeptCode(String pid, String deptCode);
	
	List<CoreTarget> findByTargetCodeAndDeptCode(String targetCode, String deptCode);

	List<DeptInfo> findByDeptCodeStr(String dstr);

	int update(CoreTarget record);
}
