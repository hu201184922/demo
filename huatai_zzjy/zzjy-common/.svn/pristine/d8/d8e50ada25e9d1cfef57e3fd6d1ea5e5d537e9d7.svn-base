package com.fairyland.jdp.framework.security.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.framework.security.dao.LoginTokenDao;
import com.fairyland.jdp.framework.security.domain.AuthInfo;
import com.fairyland.jdp.framework.security.domain.LoginToken;

@Service
public class JdbcTokenServiceImpl implements TokenService{
	@Autowired
	private LoginTokenDao loginTokenDao;

	@Override
	public boolean setAuthInfo(String key, AuthInfo value) {
		LoginToken loginToken = new LoginToken();
		loginToken.setAccessToken(key);
		loginToken.setAuthInfo(JSON.toJSONString(value));
		loginToken.setExpireTime(value.getExpireTime());
		loginTokenDao.save(loginToken);
		return true;
	}

	@Override
	public AuthInfo getAuthInfo(String key) {
		if(key==null)
			return null;
		LoginToken loginToken = loginTokenDao.findByAccessToken(key);
		if(loginToken==null)
			return null;
		String authInfoJson = loginToken.getAuthInfo();
		AuthInfo authInfo = JSON.parseObject(authInfoJson,AuthInfo.class);
		Date now = new Date();
		if(authInfo.getExpireTime()!=null && authInfo.getExpireTime().getTime()<now.getTime()){
			loginTokenDao.delete(loginToken);
			return null;
		}else{
			return authInfo;
		}
	}

	@Override
	public boolean deleteAuthInfo(String key) {
		LoginToken loginToken = loginTokenDao.findByAccessToken(key);
		loginTokenDao.delete(loginToken);
		return true;
	}

	@Override
	public boolean containKey(String key) {
		LoginToken loginToken = loginTokenDao.findByAccessToken(key);
		return !(loginToken==null);
	}

}
