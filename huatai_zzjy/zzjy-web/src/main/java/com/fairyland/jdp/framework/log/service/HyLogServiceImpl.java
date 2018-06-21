package com.fairyland.jdp.framework.log.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.core.mapper.JsonMapper;
import com.fairyland.jdp.core.persistence.DynamicSort;
import com.fairyland.jdp.core.persistence.DynamicSpecifications;
import com.fairyland.jdp.core.persistence.SearchFilter;
import com.fairyland.jdp.core.persistence.SortFilter;
import com.fairyland.jdp.framework.log.dao.HyLogDao;
import com.fairyland.jdp.framework.log.domain.HyLog;
import com.fairyland.jdp.framework.log.domain.LogBean;
import com.fairyland.jdp.framework.log.domain.RunLogBean;
import com.fairyland.jdp.framework.log.mapper.LogMapper;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.fairyland.jdp.useragent.Browser;
import com.fairyland.jdp.useragent.OperatingSystem;
import com.fairyland.jdp.useragent.UserAgent;
import com.fairyland.jdp.useragent.Version;

@Service
@Transactional
public class HyLogServiceImpl implements HyLogService {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private HyLogDao hyLogDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LogMapper logMapper;
	
	public Pager<LogBean> getLogQuery(Pager<LogBean> pager,Map<String,Object> map){
		return logMapper.getLog(pager,map);
	}
	

	@Override
	public Page<HyLog> getAllLogs(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortType) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<HyLog> spec = DynamicSpecifications
				.bySearchFilter(filters.values());
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,
				sortType);
		return hyLogDao.findAll(spec, pageRequest);
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Map<String, SortFilter> filters = SortFilter.parse(sortType);
		// 此处添加更多排序逻辑
		Sort sort = DynamicSort.bySortFilter(filters.values());
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	@Override
	public void createLog(HyLog hyLog) {
		// TODO异步写
		hyLogDao.save(hyLog);
	}

	@Override
	public void createLog(HttpServletRequest request, String handler,
			Exception ex, String descript) {
		String errorMsg = null;
		if (ex != null) {
			errorMsg = ex.getMessage();
		}
		createLog(request, handler, errorMsg, descript);
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private void setBrowserAndOS(HttpServletRequest request, HyLog hyLog) {
		String uastr = request.getHeader("User-Agent");
		UserAgent userAgent = new UserAgent(uastr);

		Browser browser = userAgent.getBrowser();
		Version version = userAgent.getBrowserVersion();
		OperatingSystem os = userAgent.getOperatingSystem();

		if (browser != null) {
			String browerVersion = browser.getName();
			if (version != null) {
				browerVersion += " version:" + version.getVersion();
			}
			hyLog.setBrowserVersion(browerVersion);
		}

		if (os != null) {
			String osVersion = os.getName();
			hyLog.setOsVersion(osVersion);
		}
	}

	@Override
	public void createLog(HttpServletRequest request, String handler,
			String errorMessage, String descript) {
		// TODO 比较前一次访问的URL和时间，反复不停的刷同一URL要做特殊处理，否则将对数据库造成较大的压力
		String url = WebUtils
				.getPathWithinApplication(WebUtils.toHttp(request));
		
		String method=request.getMethod();
		//检查当前访问的URL是否需要记录日志
//		if (!hyLogConfigService.checkEnable(url,method)) {
//			return;
//		}
		
		HyLog hyLog = new HyLog();
		
//		String action=hyLogConfigService.getAction(url, method);
//		hyLog.setAction(action);

		String loginName = SessionContextUtils.getLoginName();
		String sessionId = SessionContextUtils.getSession().getId().toString();
		String ip = getIpAddr(request);
		hyLog.setLoginName(loginName);
		hyLog.setSessionId(sessionId);
		hyLog.setIp(ip);
		hyLog.setTime(new Date());
		hyLog.setMethodHandler(handler);
		hyLog.setDescript(descript);
		hyLog.setMethod(method);

		String parameters = JsonMapper.nonDefaultMapper().toJson(request.getParameterMap());
		hyLog.setParameters(parameters!=null&&parameters.length()>=1800
					?parameters.substring(0, 1800)
					:parameters);
		hyLog.setUrl(url);
		
		hyLog.setRefererURL(request.getHeader("REFERER"));

		try {
			// 设置服务器IP信息
			InetAddress inetAddress = InetAddress.getLocalHost();
			String serverIp = inetAddress.getHostAddress();
			hyLog.setServerIp(serverIp);
		} catch (UnknownHostException e) {
			log.error(e.getMessage());
		}

		// 设置客户端浏览器和操作系统信息
		setBrowserAndOS(request, hyLog);

		// 设置异常信息
		hyLog.setErrorMsg(errorMessage);
		createLog(hyLog);
	}

	@Override
	public void createLogForApp(HttpServletRequest request,Map<String,Object> map) {
		
		String method=request.getMethod();
		String url = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
		
		HyLog hyLog = new HyLog();
		
		if(StringUtil.isNotNull(map.get("account"))){
			User user = userService.findUserByLoginName(map.get("account").toString());
			if(user != null){
				hyLog.setLoginName(user.getAccount());
			}
		}
		String tokenId = request.getHeader("tokenId");
		if(StringUtil.isNotNull(tokenId)){
			hyLog.setSessionId(tokenId);
		}
		String ip = getIpAddr(request);
		hyLog.setIp(ip);
		hyLog.setTime(new Date());
		hyLog.setDescript("APP访问");
		hyLog.setMethod(method);

		String parameters = JsonMapper.nonDefaultMapper().toJson(request.getParameterMap());
		hyLog.setParameters(parameters);
		hyLog.setUrl(url);
		
		hyLog.setRefererURL(request.getHeader("REFERER"));

		try {
			// 设置服务器IP信息
			InetAddress inetAddress = InetAddress.getLocalHost();
			String serverIp = inetAddress.getHostAddress();
			hyLog.setServerIp(serverIp);
		} catch (UnknownHostException e) {
			log.error(e.getMessage());
		}
		
		hyLog.setBrowserVersion(request.getHeader("osType"));
		hyLog.setOsVersion(request.getHeader("osVersion"));
		
		createLog(hyLog);
	}


	@Override
	public Pager<RunLogBean> getRunLogQuery(Pager<RunLogBean> pager,Map<String,Object> map) {
		return logMapper.getRunLogQuery(pager,map);
	}


	@Override
	public List<String> getRunLogDealTab() {
		return logMapper.getRunLogDealTab();
	}


	

}
