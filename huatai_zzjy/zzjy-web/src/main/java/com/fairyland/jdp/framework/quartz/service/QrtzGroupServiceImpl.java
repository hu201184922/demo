package com.fairyland.jdp.framework.quartz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.framework.quartz.dao.QrtzGroupDao;
import com.fairyland.jdp.framework.quartz.domain.QrtzGroup;


@Transactional
@Service(value="qrtzGroupService")
public class QrtzGroupServiceImpl implements QrtzGroupService {
	
	@Autowired
	private QrtzGroupDao qrtzGroupDao;

	@Override
	public QrtzGroup getQrtzGroupById(Long qrtzGroupId) {
		return qrtzGroupDao.findOne(qrtzGroupId);
	}

	@Override
	public void createQrtzGroup(QrtzGroup qrtzGroup) {
		if(qrtzGroup.getParent() != null && qrtzGroup.getParent().getId() == null){
			qrtzGroup.setParent(null);
		}
		qrtzGroupDao.save(qrtzGroup);
	}

	@Override
	public void updateQrtzGroup(QrtzGroup qrtzGroup) {
		if(qrtzGroup.getParent() != null && qrtzGroup.getParent().getId() == null){
			qrtzGroup.setParent(null);
		}
		qrtzGroupDao.save(qrtzGroup);
	}

	@Override
	public void deleteQrtzGroup(Long id) {
		qrtzGroupDao.delete(id);
		
	}

	@Override
	public List<QrtzGroup> getChildGroup(Long id) {
		return qrtzGroupDao.getChild(id);
	}

	@Override
	public QrtzGroup findByCode(String code) {
		return qrtzGroupDao.findByCode(code);
	}

}
