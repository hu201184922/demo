package com.huatai.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.TriggleExecuteMapper;
import com.huatai.web.model.TriggleExecute;
import com.huatai.web.model.TriggleExecuteKey;
import com.huatai.web.service.TriggleExecuteService;

@Service
public class TriggleExecuteServiceImpl implements TriggleExecuteService {
	@Autowired
	private TriggleExecuteMapper triggleExecuteMapper;

	@Override
	public int insert(TriggleExecute record) {
		return triggleExecuteMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKey(TriggleExecute record) {
		return triggleExecuteMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TriggleExecute> findAll(Long qrtzGroupId, String qrtzCode, Date startTime, Date endTime) {
		TriggleExecute triggleExecute = new TriggleExecute();
		triggleExecute.setQrtzGroupId(qrtzGroupId);
		triggleExecute.setQrtzCode(qrtzCode);
		triggleExecute.setExecBeginTime(startTime);
		triggleExecute.setExecEndTime(endTime);
		return triggleExecuteMapper.findAll(triggleExecute);
	}

	@Override
	public int deleteByPrimaryKey(Long qrtzGroupId, String qrtzCode) {
		TriggleExecuteKey key = new TriggleExecuteKey();
		key.setQrtzCode(qrtzCode);
		key.setQrtzGroupId(qrtzGroupId);
		return triggleExecuteMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int deleteByQrtzCode(String qrtzCode) {
		return triggleExecuteMapper.deleteByQrtzCode(qrtzCode);
	}

	@Override
	public Pager<TriggleExecute> findByPager(Pager<TriggleExecute> pager, TriggleExecute record) {
		Pager<TriggleExecute> findByPager = triggleExecuteMapper.findByPager(pager, record);
		for (TriggleExecute triggleExecute : findByPager.getPageItems()) {
			try {
				if (triggleExecute.getExecStatus() == null || triggleExecute.getExecStatus() == "") {
					triggleExecute.setExecStatus("任务异常");
				}
				if (triggleExecute.getExecBeginTime() != null && triggleExecute.getExecEndTime() != null) {
					triggleExecute.setResTime(triggleExecute.getResTime() + "s");
				}
				/*
				 * if(triggleExecute.getExecBeginTime()!=null&&triggleExecute.getExecEndTime()!=
				 * null){ triggleExecute.setResTime((triggleExecute.getExecEndTime().getTime()-
				 * triggleExecute.getExecBeginTime().getTime())/1000+"s"); }else{
				 * triggleExecute.setResTime(""); }
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return findByPager;
	}

}
