package com.huatai.web.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.UserSetWarnMapper;
import com.huatai.web.model.UserSetWarn;
import com.huatai.web.service.UserSetWarnService;

@Service
public class UserSetWarnServiceImpl implements UserSetWarnService {

	@Autowired
	private UserSetWarnMapper userSetWarnMapper;

	@Override
	public void updateStatus(String status, String bsid) {
		userSetWarnMapper.updateStatus(status, bsid);

	}

	@Override
	public Pager<UserSetWarn> findByPager(Pager<UserSetWarn> pager, UserSetWarn record,String orgName, String warnTarget, String warnStatus,String userName) {
		return userSetWarnMapper.findByPager(pager, record,orgName,warnTarget,warnStatus,userName);
	}

	@Override
	public void addWarn(UserSetWarn userWarn) {
		userSetWarnMapper.insert(userWarn);
	}

	@Override
	public UserSetWarn findWarnById(Integer bsId) {
		return userSetWarnMapper.findWarnById(bsId);
	}

	@Override
	public void deleteWarnById(Integer bsId) throws ParseException {
		this.userSetWarnMapper.deleteByPrimaryKey(bsId);
	}

	@Override
	public Pager<UserSetWarn> findResultByPager(Pager<UserSetWarn> pager, UserSetWarn record,String RorgName,String RwarnTarget) {
		return userSetWarnMapper.findResultByPager(pager, record,RorgName,RwarnTarget);
	}

	@Override
	public void updateWarn(UserSetWarn userWarn) {
		userSetWarnMapper.updateWarn(userWarn);
	}

	@Override
	public List<String> findAlertType() {
		return userSetWarnMapper.findAlertType();
	}
}
