package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.bean.FunAnListTotalBean;
import com.huatai.web.bean.ModuleBean;
import com.huatai.web.bean.UserBehBean;
import com.huatai.web.model.AccPageInfo;
import com.huatai.web.model.AccPageInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccPageInfoMapper {
    int countByExample(AccPageInfoExample example);

    int deleteByExample(AccPageInfoExample example);

    int deleteByPrimaryKey(String accessid);

    int insert(AccPageInfo record);

    int insertSelective(AccPageInfo record);

    List<AccPageInfo> selectByExample(AccPageInfoExample example);

    AccPageInfo selectByPrimaryKey(String accessid);

    int updateByExampleSelective(@Param("record") AccPageInfo record, @Param("example") AccPageInfoExample example);

    int updateByExample(@Param("record") AccPageInfo record, @Param("example") AccPageInfoExample example);

    int updateByPrimaryKeySelective(AccPageInfo record);

    int updateByPrimaryKey(AccPageInfo record);

	List<AccPageInfo> findAll(Pager<UserBehBean> pager, UserBehBean record, @Param("orgs")String orgs, @Param("userId")String userId,@Param("module") String module, @Param("isNode")String isNode,
			@Param("datetime1")String datetime1, @Param("datetime2")String datetime2);

	List<String> findfmNameAll();

	List<String> findNodeByFmName(@Param("data")String data);

	Pager<ModuleBean> findFunanList(Pager<ModuleBean> pager, ModuleBean record, @Param("dateDim")String funDate);

	FunAnListTotalBean findTotals(@Param("dateDim")String funDate);

	int updateAccPageInfoByBehaviorid(AccPageInfo acc);

	Pager<UserBehBean> findAccPager(Pager<UserBehBean> pager, @Param("record")UserBehBean record, @Param("orgs")String orgs, @Param("userId")String userId,
			@Param("module")String module, @Param("isNode")String isNode, @Param("datetime1")String datetime1, @Param("datetime2")String datetime2);

	List<ModuleBean> findFunanAll(Object object, ModuleBean record, @Param("dateDim")String dateDim);
}