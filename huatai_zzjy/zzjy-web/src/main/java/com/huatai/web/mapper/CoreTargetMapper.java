package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.CoreTarget;
import com.huatai.web.model.CoreTargetExample;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.RelaTarget;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface CoreTargetMapper {

	int countByExample(CoreTargetExample example);

	int deleteByExample(CoreTargetExample example);

	int deleteByPrimaryKey(Integer ctId);

	int insert(CoreTarget record);

	int insertSelective(CoreTarget record);

	List<CoreTarget> selectByExample(CoreTargetExample example);

	CoreTarget selectByPrimaryKey(Integer ctId);

	int updateByExampleSelective(@Param("record") CoreTarget record, @Param("example") CoreTargetExample example);

	int updateByExample(@Param("record") CoreTarget record, @Param("example") CoreTargetExample example);

	int updateByPrimaryKeySelective(CoreTarget record);

	int updateByPrimaryKey(CoreTarget record);

	Pager<CoreTarget> findByPager(Pager<CoreTarget> pager, CoreTarget record);

	CoreTarget selectByRoleCodeAndTargetCode(@Param("roleCode") String roleCode,
			@Param("targetCode") String targetCode);

	void deleteByRoleCode(String roleCode);

	// List<TreeResult> findCoreTargetByPid(Integer id);

	void deleteByRoleCodeAndTargetCode();

	void deleteByPid(Integer pid);

	List<CoreTarget> selectByPid(Integer pid);

	Pager<RelaTarget> findByPagerRela(Pager<RelaTarget> pager, RelaTarget record);

	List<CoreTarget> findByPid(@Param("pid") String pid,@Param("deptCode") String deptCode);
	
	int deleteByPidAndTargetCode(@Param("pid") String pid, @Param("targetCode")String targetCode);

	List<CoreTarget> findByTargetCodeAndDeptCode(@Param("targetCode") String targetCode,@Param("deptCode") String deptCode);

	List<DeptInfo> findByDeptCodeStr(@Param("dstr") String dstr);

}