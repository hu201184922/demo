package com.huatai.web.service;

import java.util.List;

import com.huatai.web.model.NoticeFile;

public interface NoticeFileService {

	int insertSelective(NoticeFile record);

	NoticeFile findNoticeFile(NoticeFile record);

	int updateByPrimaryKeySelective(NoticeFile record);

	int deleteByPrimaryKey(NoticeFile record);

	int deleteByNoticeId(Integer noticeId);

	List<NoticeFile> findNoticeId(Integer integer);

	int update(NoticeFile noticeFile);

}
