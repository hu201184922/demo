package com.fairyland.jdp.springboot.cas.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;

//@Component
public class FairylandCasFilter extends CasFilter{
	
	private String wafLoginUrl;
	
	public void setWafLoginUrl(String wafLoginUrl) {
		this.wafLoginUrl = wafLoginUrl;
	}
	@Value("${casfilter.failure.url}")
	public void setFailureUrl(String failureUrl){
		super.setFailureUrl(failureUrl);
	}
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		if (wafLoginUrl.equals(savedRequest.getRequestURI())) {
			WebUtils.getAndClearSavedRequest(request);
		}
		issueSuccessRedirect(request, response);
		return false;
	}
}
