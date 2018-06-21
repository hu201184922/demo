package com.ehuatai.ret;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * 返回结果集统一处理
 * @author ctl
 *
 */
public class RestResult {

	public String errCode="";
	
	public String errMsg="";
	
	public boolean success = true;
	
	public Object data= new Object();
	
	public RestResult() {}
	
	public RestResult(String errCode,String errMsg,boolean success,Object data) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.success = success;
		this.data = data;
	}
	/**
	 * 结果是失败的
	 * @param retCode
	 */
	public RestResult(RestCode retCode) {
		this(retCode,false,null);
	}
	/**
	 * 结果是成功的
	 * @param data
	 */
	public RestResult(Object data) {
		this(RestCode.SUCCESS,true,data);		
	}
	public RestResult(RestCode retCode,boolean success,Object data) {
		this(retCode.getErrCode(),retCode.getErrMsg(),success,data);
	}
	
	
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * 将字符串转换为对象
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T  parseJSONString(String json,Class<T> clazz) {
		if(StringUtils.isEmpty(json)) return null;
		if(clazz == null) {
			return (T) JSON.parse(json);
		}else {
			Pattern pattern = Pattern.compile("^\\[[\\s\\S]*\\]$");
			if(pattern.matcher(json).matches()) {
				return (T) JSON.parseArray(json, clazz);
			}
			return (T) JSON.parseObject(json, clazz);
		}
		
	}

	/**
	 * 成功结果集
	 * @param data
	 * @return
	 */
	public static RestResult getSuccess(Object data) {
		return new RestResult(RestCode.SUCCESS, true, data);
	}
	/**
	 * 失败结果集
	 * @param retCode
	 * @return
	 */
	public static RestResult getError(RestCode retCode) {
		return new RestResult(retCode, false,null);
	}
	/**
	 * [A0001]用户为登录或者检查登录状态失败,请重试或重新登录
	 * @return
	 */
	public static RestResult getAuthenNot() {
		return new RestResult(RestCode.AUTHEN_NOT);
	}
	/**
	 * [A0002]用户信息获取失败,请重新登录
	 * @return
	 */
	public static RestResult getAuthenUserError() {
		return new RestResult(RestCode.AUTHEN_USER_ERROR);
	}

}
