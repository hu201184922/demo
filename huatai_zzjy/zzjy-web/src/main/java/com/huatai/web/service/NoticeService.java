package com.huatai.web.service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Notice;

public interface NoticeService {

	Pager<Notice> findNoticePager(Pager<Notice> pager, Notice record);

	int insertSelective(Notice record);

	int updateByPrimaryKeySelective(Notice record);

	int deleteByPrimaryKey(Notice record);

}
