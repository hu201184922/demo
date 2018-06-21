package com.ehuatai.commonTest.service;

import com.ehuatai.commonTest.entity.AccPageInfo;
import com.ehuatai.commonTest.entity.UserBehInfo;
import com.ehuatai.commonTest.entity.Version;

public interface InterceptorService {

	public int insertUserData(UserBehInfo userBehInfo);

	public int insertAccData(AccPageInfo accPage);

	public UserBehInfo findUserDataInfo(String username, String type, String id);

	public AccPageInfo findAccDataInfo(String username);

	public int updateUserData(UserBehInfo userBehInfo);

	public int updateAccData(AccPageInfo accPageInfo);

	public int updateOtherUserData(UserBehInfo online, int i);

	Version existVersion(String userId, String type);

	int saveVersion(Version version);

	int updateVersion(Version version);

	public int updateAccDataById(AccPageInfo accPageInfo);

}
