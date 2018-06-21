package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.UserSetWarn;
import com.huatai.web.model.UserSetWarnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface UserSetWarnMapper {
	int countByExample(UserSetWarnExample example);

	int deleteByExample(UserSetWarnExample example);

	int deleteByPrimaryKey(@Param("bsId") Integer bsId);

	int insert(UserSetWarn record);

	int insertSelective(UserSetWarn record);

	List<UserSetWarn> selectByExample(UserSetWarnExample example);

	UserSetWarn selectByPrimaryKey(Integer bsId);
	
	UserSetWarn selectByPrimaryKeyAndNoOp(Integer bsId);

	int updateByExampleSelective(@Param("record") UserSetWarn record, @Param("example") UserSetWarnExample example);

	int updateByExample(@Param("record") UserSetWarn record, @Param("example") UserSetWarnExample example);

	int updateByPrimaryKeySelective(UserSetWarn record);

	int updateByPrimaryKey(UserSetWarn record);

	Pager<UserSetWarn> findByPager(Pager<UserSetWarn> pager, UserSetWarn record, @Param("orgName")String orgName, @Param("warnTarget")String warnTarget, 
													@Param("warnStatus")String warnStatus,@Param("userName")String userName);

	void updateStatus(@Param("status") String status, @Param("bsid") String bsid);

	Pager<UserSetWarn> findUserSetWarnByPage(Pager<UserSetWarn> pager, UserSetWarn record);

	/**
	 * 根据预警id获取预警信息
	 * 
	 * @param bsid
	 * @return
	 */
	UserSetWarn findWarnById(@Param("bsId") Integer bsId);

	/**
	 * 分页查询预警结果
	 * 
	 * @param pager
	 * @param record
	 * @param warnTarget 
	 * @param orgName 
	 * @return
	 */
	Pager<UserSetWarn> findResultByPager(Pager<UserSetWarn> pager, UserSetWarn record, @Param("RorgName")String RorgName,  @Param("RwarnTarget")String RwarnTarget);

	/**
	 * 保存更新
	 * 
	 * @param userWarn
	 */
	void updateWarn(UserSetWarn userWarn);

	List<UserSetWarn> findNoResOnWarn(@Param("role") String role,@Param("roleOrg") String roleOrg);

	Pager<UserSetWarn> findUserSetWarnByNamePage(Pager<UserSetWarn> pager, UserSetWarn userSetWarn);

	List<String> findAlertType();

}