package com.ehuatai.biz.tool.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehuatai.biz.tool.service.ToolSQLCacheService;
import com.ehuatai.biz.tool.service.ToolService;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.GjfwService;
import com.ehuatai.thrift.GjfwService.Client;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.util.ThriftUtil;
@Service
public class ToolServiceImpl implements ToolService{

	@Autowired
	private ToolSQLCacheService sqlCacheService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private Client getClient() {
		Client client = null;
		try {
			client = ThriftUtil.getService(GjfwService.Client.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public RestResult toolWarnMy(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.myListWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolWarnToggle(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.switchWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolWarnEditGet(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.selectWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolWarnEditPost(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.updateWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolWarnDelete(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.deleteWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolWarnAddGet(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.selectWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolWarnAddPost(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.saveMyWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolWarnResult(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.resultWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolNotice(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.noticeWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolNoticeRead(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.readMyWarning(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if(StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult toolUpdateInfo(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if(client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dtoSQL = null;
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dtoSQL = client.updateWarnInfo(reqParamsJSON);
			String sqlJSONString = dtoSQL.getJson();
			if (null != sqlJSONString) {
				JSONArray sqlTars = JSON.parseArray(sqlJSONString);
				if (sqlTars != null && !sqlTars.isEmpty()) {
					for (int i=0; i < sqlTars.size(); i++) {
						JSONObject dataJSON =  sqlTars.getJSONObject(i);
						String target = dataJSON.getString("target");
						String sql = dataJSON.getString("sqlCode");
						//得到查询的结果
						reqParams.put("orgCode", dataJSON.getString("roleOrg"));
						reqParams.put("targetCode", target);
						List<Map<String, Object>> lists =  sqlCacheService.getActualVal(reqParams,sql);
						//处理值
						Object actVal = (lists==null || lists.isEmpty())?"":lists.get(0).get("VAL1");
						Object showVal = (lists==null || lists.isEmpty())?"":lists.get(0).get("VAL2");
						Map<String, Object> map = new HashMap<>();
						map.put("target", target);
						map.put("actVal", actVal);
						map.put("showVal", showVal);
						map.put("username", dataJSON.getString("username"));
						map.put("role", dataJSON.getString("role"));
						map.put("bsId", dataJSON.getString("bsId"));
						map.put("warnCode", dataJSON.getString("warnCode"));
						map.put("min", dataJSON.getString("min"));
						map.put("max", dataJSON.getString("max"));
						dto = client.saveWarnInfo(JSON.toJSONString(map));
					}
				} else {
					log.warn("{} 数据为空");
					return RestResult.getSuccess("当前无预警更新");
				}
			} else {
				log.warn("当前无数据更新");
				return RestResult.getSuccess("当前无预警更新");
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		}finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dtoSQL.getJson();
		if (null != dto) {
			resultJSONString = dto.getJson();
			if(StringUtils.isEmpty(resultJSONString)) {
				log.warn("结果为空");
				return RestResult.getError(RestCode.EMPTY);
			}
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}
}
