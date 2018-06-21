package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.Notice;
import com.huatai.web.model.NoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface NoticeMapper {

	int countByExample(NoticeExample example);

	int deleteByExample(NoticeExample example);

	int deleteByPrimaryKey(Integer noticeId);

	int insert(Notice record);

	int insertSelective(Notice record);

	List<Notice> selectByExample(NoticeExample example);

	Notice selectByPrimaryKey(Integer noticeId);

	int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

	int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

	int updateByPrimaryKeySelective(Notice record);

	int updateByPrimaryKey(Notice record);

	Pager<Notice> findNoticePager(Pager<Notice> pager, Notice record);
}