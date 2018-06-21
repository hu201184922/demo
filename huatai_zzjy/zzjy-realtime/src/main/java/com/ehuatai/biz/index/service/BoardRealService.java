package com.ehuatai.biz.index.service;

import java.util.Map;

import com.ehuatai.ret.RestResult;

public interface BoardRealService {

	/**
	 * 首页- 实时指标
	 */
	public RestResult getDSBrealTime(Map<String, Object>reqParams);
}
