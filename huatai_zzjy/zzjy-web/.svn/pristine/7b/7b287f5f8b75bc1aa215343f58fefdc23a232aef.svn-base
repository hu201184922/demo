package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.Notice;
import com.huatai.web.model.NoticeFile;
import com.huatai.web.model.NoticeFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface NoticeFileMapper {

	int countByExample(NoticeFileExample example);

	int deleteByExample(NoticeFileExample example);

	int deleteByPrimaryKey(Integer nfId);

	int insert(NoticeFile record);

	int insertSelective(NoticeFile record);

	List<NoticeFile> selectByExample(NoticeFileExample example);

	NoticeFile selectByPrimaryKey(Integer nfId);

	int updateByExampleSelective(@Param("record") NoticeFile record, @Param("example") NoticeFileExample example);

	int updateByExample(@Param("record") NoticeFile record, @Param("example") NoticeFileExample example);

	int updateByPrimaryKeySelective(NoticeFile record);

	int updateByPrimaryKey(NoticeFile record);

	int deleteByNoticeId(Integer noticeId);

	List<NoticeFile> findNoticeId(Integer noticeId);

	int updateByNoticeIdSelective(Notice record);
}