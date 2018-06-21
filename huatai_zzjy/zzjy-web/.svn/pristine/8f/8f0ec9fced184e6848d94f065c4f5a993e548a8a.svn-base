package com.huatai.web.service;

import java.util.Date;
import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.TriggleExecute;

public interface TriggleExecuteService {

	int deleteByPrimaryKey(Long qrtzGroupId, String qrtzCode);

	int deleteByQrtzCode(String qrtzCode);

	int insert(TriggleExecute record);

	int updateByPrimaryKey(TriggleExecute record);

	List<TriggleExecute> findAll(Long qrtzGroupId, String qrtzCode, Date startTime, Date endTime);

	Pager<TriggleExecute> findByPager(Pager<TriggleExecute> pager, TriggleExecute record);
}
