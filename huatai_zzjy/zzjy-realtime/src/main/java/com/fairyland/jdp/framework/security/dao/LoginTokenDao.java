package com.fairyland.jdp.framework.security.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.security.domain.LoginToken;

public interface LoginTokenDao extends PagingAndSortingRepository<LoginToken, Long>,JpaSpecificationExecutor<LoginToken> {
	
	public LoginToken findByAccessToken(String accessToken);
}
