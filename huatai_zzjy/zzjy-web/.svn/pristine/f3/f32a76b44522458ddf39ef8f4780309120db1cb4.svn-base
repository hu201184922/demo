package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.WarnResult;
import com.huatai.web.model.WarnResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface WarnResultMapper {
    int countByExample(WarnResultExample example);

    int deleteByExample(WarnResultExample example);

    int deleteByPrimaryKey(Integer wrId);

    int insert(WarnResult record);

    int insertSelective(WarnResult record);

    List<WarnResult> selectByExample(WarnResultExample example);

    WarnResult selectByPrimaryKey(Integer wrId);

    int updateByExampleSelective(@Param("record") WarnResult record, @Param("example") WarnResultExample example);

    int updateByExample(@Param("record") WarnResult record, @Param("example") WarnResultExample example);

    int updateByPrimaryKeySelective(WarnResult record);

    int updateByPrimaryKey(WarnResult record);
	
	Pager<WarnResult> findWarnMsg(Pager<WarnResult> pager, @Param("username") String username, @Param("role") String role);

	Pager<WarnResult> findWarnResult(Pager<WarnResult> pager, WarnResult warnResult);

	Pager<WarnResult> findWarnResultByName(Pager<WarnResult> pager, WarnResult warnResult);
}