package com.huatai.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.NoticeFileMapper;
import com.huatai.web.mapper.NoticeMapper;
import com.huatai.web.model.Notice;
import com.huatai.web.service.NoticeService;


/**
 * @功能 
 * @作者    胡智辉
 * @时间 2017年8月9日
 * @版本 v1.0.0
 */
@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private NoticeFileMapper noticeFileMapper;

	@Override
	public Pager<Notice> findNoticePager(Pager<Notice> pager, Notice record) {
		Pager<Notice> result=this.noticeMapper.findNoticePager(pager,record);
		return result;
	}

	@Override
	public int insertSelective(Notice record) {
		return this.noticeMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Notice record) {
		return this.noticeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional
	public int deleteByPrimaryKey(Notice notice) {
		int result=this.noticeMapper.updateByPrimaryKeySelective(notice);
		if(result==1){
			Notice record=new Notice();
			record.setOpType("D");
			record.setNoticeId(notice.getNoticeId());
			this.noticeFileMapper.updateByNoticeIdSelective(record);
		}
		return result;
	}

}
