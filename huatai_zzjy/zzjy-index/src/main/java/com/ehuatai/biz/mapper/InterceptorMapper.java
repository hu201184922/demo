package com.ehuatai.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehuatai.biz.domain.AccPageInfo;
import com.ehuatai.biz.domain.UserBehInfo;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;

@DataSource(name = "dataSource1", value = "dataSource1")
@MyBatisRepository
public interface InterceptorMapper {

	int insertUserData(UserBehInfo userBehInfo);

	int insertAccData(AccPageInfo accPage);

	List<UserBehInfo> findUserDataInfo(@Param("userName") String userName, @Param("type") String type,
			@Param("behaviorid") String behaviorid);

	AccPageInfo findAccDataInfo(String behaviorid);

	int updateUserData(UserBehInfo userBehInfo);

	int updateAccData(AccPageInfo accPageInfo);

	int updateOtherUserData(UserBehInfo online);

	int updateAccDataById(AccPageInfo accPageInfo);

	int updateOtherCurrUserData(UserBehInfo online);

}
