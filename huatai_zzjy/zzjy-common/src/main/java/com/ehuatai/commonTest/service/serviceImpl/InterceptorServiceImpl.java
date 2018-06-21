package com.ehuatai.commonTest.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuatai.commonTest.entity.AccPageInfo;
import com.ehuatai.commonTest.entity.UserBehInfo;
import com.ehuatai.commonTest.entity.Version;
import com.ehuatai.commonTest.entity.VersionExample;
import com.ehuatai.commonTest.mapper.InterceptorMapper;
import com.ehuatai.commonTest.mapper.VersionMapper;
import com.ehuatai.commonTest.service.InterceptorService;

@Service
public class InterceptorServiceImpl implements InterceptorService {

	@Autowired
	private InterceptorMapper interceptorMapper;

	@Autowired
	private VersionMapper versionMapper;

	@Override
	public int insertUserData(UserBehInfo userBehInfo) {
		return this.interceptorMapper.insertUserData(userBehInfo);
	}

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
	public AccPageInfo findAccDataInfo(String username) {
		return this.interceptorMapper.findAccDataInfo(username);
	}

	@Override
	public int updateUserData(UserBehInfo userBehInfo) {
		return this.interceptorMapper.updateUserData(userBehInfo);
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

	public Version existVersion(String userId, String type) {
		VersionExample versionExample = new VersionExample();
		versionExample.createCriteria().andUserIdEqualTo(userId).andVersionTypeEqualTo(type);
		List<Version> versionList = this.versionMapper.selectByExample(versionExample);
		if (null != versionList && versionList.size() == 1) {
			return versionList.get(0);
		} else {
			return null;
		}
	}

	public int saveVersion(Version version) {
		return this.versionMapper.insert(version);
	}

	public int updateVersion(Version version) {
		return this.versionMapper.updateByPrimaryKeySelective(version);
	}

	@Override
	public int updateAccDataById(AccPageInfo accPageInfo) {
		return this.interceptorMapper.updateAccDataById(accPageInfo);
	}

}
