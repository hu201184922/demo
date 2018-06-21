package com.ehuatai.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuatai.biz.domain.AccPageInfo;
import com.ehuatai.biz.domain.UserBehInfo;
import com.ehuatai.biz.mapper.InterceptorMapper;

@Service
public class InterceptorServiceImpl implements InterceptorService {

	@Autowired
	private InterceptorMapper interceptorMapper;

	@Override
	public int insertAccData(AccPageInfo accPage) {
		return this.interceptorMapper.insertAccData(accPage);
	}

	@Override
	public UserBehInfo findUserDataInfo(String username, String type, String id) {
		List<UserBehInfo> findUserDataInfo = this.interceptorMapper.findUserDataInfo(username, type, id);
		if (findUserDataInfo.size() != 0) {
			return findUserDataInfo.get(0);
		}
		return null;
	}

	@Override
	public int updateUserData(UserBehInfo userBehInfo) {
		return this.interceptorMapper.updateUserData(userBehInfo);
	}

	@Override
	public int updateAccDataById(AccPageInfo accPageInfo) {
		return this.interceptorMapper.updateAccDataById(accPageInfo);
	}

	@Override
	public int insertUserData(UserBehInfo userBehInfo) {
		return this.interceptorMapper.insertUserData(userBehInfo);
	}

	@Override
	public AccPageInfo findAccDataInfo(String username) {
		return this.interceptorMapper.findAccDataInfo(username);
	}

	@Override
	public int updateAccData(AccPageInfo accPageInfo) {
		return this.interceptorMapper.updateAccData(accPageInfo);
	}

	@Override
	public int updateOtherUserData(UserBehInfo online,int i) {
		switch (i) {
		case 1:
			return this.interceptorMapper.updateOtherUserData(online);
		default:
			return this.interceptorMapper.updateOtherCurrUserData(online);
		}
	}

}
