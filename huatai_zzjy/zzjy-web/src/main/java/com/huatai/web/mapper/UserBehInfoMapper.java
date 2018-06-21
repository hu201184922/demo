package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.bean.ChartBean;
import com.huatai.web.bean.ChartData;
import com.huatai.web.bean.UserBehBean;
import com.huatai.web.bean.UserUseAnBean;
import com.huatai.web.model.AccPageInfo;
import com.huatai.web.model.UserBehInfo;
import com.huatai.web.model.UserBehInfoExample;

@MyBatisRepository
public interface UserBehInfoMapper {
    int countByExample(UserBehInfoExample example);

    int deleteByExample(UserBehInfoExample example);

    int deleteByPrimaryKey(String behaviorid);

    int insert(UserBehInfo record);

    int insertSelective(UserBehInfo record);

    List<UserBehInfo> selectByExample(UserBehInfoExample example);

    UserBehInfo selectByPrimaryKey(String behaviorid);

    int updateByExampleSelective(@Param("record") UserBehInfo record, @Param("example") UserBehInfoExample example);

    int updateByExample(@Param("record") UserBehInfo record, @Param("example") UserBehInfoExample example);

    int updateByPrimaryKeySelective(UserBehInfo record);

    int updateByPrimaryKey(UserBehInfo record);

	Pager<UserBehBean> findPager(Pager<UserBehBean> pager, UserBehBean record);

	List<UserBehInfo> findAll();

	UserBehInfo findAccUser(String dateDim);

	UserBehInfo findErrRate(String dateDim);

	Pager<UserBehInfo> findByPager(Pager<UserUseAnBean> pager, UserUseAnBean record);

	List<ChartBean> findMinalterType(String dateDim);

	UserBehInfo findByBehaviorId(@Param("id")String id);

	List<AccPageInfo> findModuRat(String dateDim);

	int findCountPage(@Param("behaviorid")String behaviorid);

	Integer findActUser(String dateDim);
	
	Integer findLogUser(String dateDim);
	
	Integer findRegUser(String dateDim);

	UserBehInfo findLifeUser(String dateDim);

	ChartBean findCOnceUser(String dateDim);

	ChartBean findCAddUser(String dateDim);

	ChartBean findCRetUser(String dateDim);

	ChartBean findCSileUser(String dateDim);
	
	ChartBean findCRefUser(String dateDim);
	
	ChartBean findCAwayUser(String dateDim);
	
	List<ChartData> findHieUser(String dateDim);

	Pager<UserBehInfo> findErrList(Pager<UserBehInfo> pager,UserBehBean record, @Param("date")String date);

	String findErrNum(String dateDim);

	String findErrNumDay();

	String findErrNumWeek();

	List<Integer> getDatePerInfo();

	ChartBean findCOtherUser(String dateDim);

	List<ChartData> getHieErrInfo(String dateDim);

	Integer findCallPage(String behaviorid);

	int findOnlinePeople();
	
	int findLAccUser(String dateDim);

	int findLErrRate(String dateDim);

	List<UserBehInfo> findDateRegionByYear();

	List<UserBehInfo> findDateRegionByMonth();

	List<UserBehInfo> findDateRegionByWeek();

	ChartBean findLMinalterType(@Param("dateDim")String dateDim,@Param("type")String type);

	ChartBean findCRetUserByYear(@Param("dateDim")String dateDim);

	ChartBean findCRefUserYear(@Param("dateDim")String dateDim);

	ChartBean findCRefUserMonth(@Param("dateDim")String dateDim);

	ChartBean findCAwayUserMonth(@Param("dateDim")String dateDim);

	ChartBean findCAwayUserYear(@Param("dateDim")String dateDim);

	int findLAccUserWeek(@Param("dateDim")String dateDim);

	int findLAccUserMonth(@Param("dateDim")String dateDim);

	ChartBean findLMinalterTypeMonth(@Param("dateDim")String dateDim,@Param("type")String type);

}