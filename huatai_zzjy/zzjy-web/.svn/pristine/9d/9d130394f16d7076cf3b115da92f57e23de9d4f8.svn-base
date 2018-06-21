package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.mapper.NoticeFileMapper;
import com.huatai.web.model.NoticeFile;
import com.huatai.web.service.NoticeFileService;


/**
 * @功能 
 * @作者    胡智辉
 * @时间 2017年8月9日
 * @版本 v1.0.0
 */
@Service
public class NoticeFileServiceImpl implements NoticeFileService {

	@Autowired
	private NoticeFileMapper noticeFileMapper;

	@Override
	public int insertSelective(NoticeFile record) {
		return this.noticeFileMapper.insertSelective(record);
	}

	@Override
	public NoticeFile findNoticeFile(NoticeFile record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(NoticeFile record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(NoticeFile record) {
		return noticeFileMapper.deleteByPrimaryKey(record.getNfId());
	}

	@Override
	public int deleteByNoticeId(Integer noticeId) {
		return this.noticeFileMapper.deleteByNoticeId(noticeId);
	}

	@Override
	public List<NoticeFile> findNoticeId(Integer noticeId) {
		return this.noticeFileMapper.findNoticeId(noticeId);
	}

	@Override
	public int update(NoticeFile noticeFile) {
		return this.noticeFileMapper.updateByPrimaryKeySelective(noticeFile);
	}

	
}
