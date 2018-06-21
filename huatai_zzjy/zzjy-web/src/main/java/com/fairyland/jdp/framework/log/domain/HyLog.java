package com.fairyland.jdp.framework.log.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fairyland.jdp.framework.domain.IdEntity;

@Entity
@Table(name = "JDP_LOG")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_LOG_ID", table = "JDP_KEYS", pkColumnName = "KEYID", valueColumnName = "KEYVALUE")
@SequenceGenerator(name="HYLOG_SEQ",sequenceName="SEQ_JDP_LOG",allocationSize = 1)
public class HyLog  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2099842250562013344L;

	private Long id;
	
	private String loginName;// 访问者帐号

	private String sessionId;// 会话ID

	private String url;// 访问的URL

	private String ip;// 客户端IP

	private String browserVersion;//客户端浏览器信息

	private String osVersion;//客户端操作系统信息

	private String serverIp;//服务端IP

	private Date time;// 访问的时间


	private String action;//访问操作，需要结合配置表
	
	private String method;//post,get,update,delete
	
	private String methodHandler;//处理的java方法

	private String parameters;//请求参数

	private String errorMsg;// 异常信息

	private String descript;// 描述，其它扩展信息
	
	private String refererURL;//链接页面源

	@Column(length=1000)
	public String getMethodHandler() {
		return methodHandler;
	}

	public void setMethodHandler(String methodHandler) {
		this.methodHandler = methodHandler;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name="PARAMETERS_",length=2000)
	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	@Column(length=4000)
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	@Column(length=2000)
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRefererURL() {
		return refererURL;
	}

	public void setRefererURL(String refererURL) {
		this.refererURL = refererURL;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HYLOG_SEQ")
	@Column(name = "CID")
	public Long getId() {
		return id;
	}
}
