package com.ehuatai.biz.header.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ehuatai.biz.domain.User;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.ZtfxService;
import com.ehuatai.thrift.ZtfxService.Client;
import com.ehuatai.thrift.util.ThriftUtil;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value = "/api/header", name = "头部导航（菜单）栏")
public class HeaderController {

	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 头部导航栏Header接口
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/navs")
	public Object findNavs(HttpServletRequest request) throws Exception {
		User user = RequestHeaderUtil.getUser(request);

		log.warn("{}", user == null ? "用户为空" : user.getUsername());

		// 请求参数
		Map<String, Object> reqParams = new HashMap<String, Object>();
		reqParams.put("role", null);

		// 转换为JSON字符串给thrift调用
		String reqParamsJSON = JSON.toJSONString(reqParams);

		Client client = ThriftUtil.getService(ZtfxService.Client.class);

		// ResponseBeanDto responseBeanDto = client.getHeader(reqParamsJSON);

		// String resultJSONString = responseBeanDto.getJson();

		Object result = RestResult.parseJSONString(null, null);
		if (result == null) {
			return RestResult.getError(RestCode.EMPTY);
		}

		return RestResult.getSuccess(result);
	}
}
