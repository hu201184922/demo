package com.fairyland.jdp.framework.util;

import org.apache.log4j.Logger;

import com.fairyland.jdp.framework.util.DictionaryUtils;

public class WechatUtil {

	private static WechatUtil util = null;
	
	private static Logger log = Logger.getLogger(WechatUtil.class);
	
	public static String WECHAT = "wechat";
	
	public static String CORPID = "CorpID";
	
	public static String SECRET = "secret";
	
	/**
	 * 获取微信用户的session的KEY
	 */
	public static String WECHAT_SESSION = "wechat_user";
	
	public static String AGENTID = "agentid";
	
	public static String ACCESS_TOKEN = "access_token";
	
	public static String ACCESS_TOKEN_EXPIRES_IN = "access_token_expire_in";
	
	public static String ACCESS_TOKEN_START_TIME = "access_token_start_time";
	
	public static String JSAPI_TICKET = "jsapi_ticket";
	public static String JSAPI_TICKET_EXPIRE_IN = "jsapi_ticket_expire_in";
	public static String JSAPI_TICKET_START_TIME = "jsapi_ticket_start_time";
	
	
	private WechatUtil(){}
	
	public static synchronized WechatUtil getInstance(){
		if(util == null) util = new WechatUtil();
		return util;
	}

	public String getCorpID(String domain){
		String value = DictionaryUtils.codeToName(domain, CORPID);
		if(value== null) log.error("企业号CorpID不存在，请添加");
		return value;
	}
	
	public String getState(){
		return "1234567890";
	}
	
	public String getAgentid(String domain){
		String value = DictionaryUtils.codeToName(domain, AGENTID);
		if(value== null) log.error("企业号agentid不存在，请添加");
		return value;
	}
	
	public String getCorpsecret(String domain){
		String value = DictionaryUtils.codeToName(domain, SECRET);
		if(value== null) log.error("企业号secret不存在，请添加");
		return value;
	}
	

}
