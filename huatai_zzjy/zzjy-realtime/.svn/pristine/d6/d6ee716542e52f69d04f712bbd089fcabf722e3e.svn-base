package com.ehuatai.ret;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * 返回结果集统一处理
 * @author ctl
 *
 */
public class RetResult {

	public String errCode="";
	
	public String errMsg="";
	
	public boolean success = true;
	
	public Object data= new Object();
	
	public RetResult() {}
	
	public RetResult(String errCode,String errMsg,boolean success,Object data) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.success = success;
		this.data = data;
	}
	/**
	 * 结果是失败的
	 * @param retCode
	 */
	public RetResult(RetCode retCode) {
		this(retCode,false,null);
	}
	/**
	 * 结果是成功的
	 * @param data
	 */
	public RetResult(Object data) {
		this(RetCode.SUCCESS,true,data);		
	}
	public RetResult(RetCode retCode,boolean success,Object data) {
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
	public static Object handleJSONString(String json) {
		if(StringUtils.isEmpty(json)) return null;
		return JSON.parse(json);
	}
	/**
	 * 成功结果集
	 * @param data
	 * @return
	 */
	public static RetResult getSuccess(Object data) {
		return new RetResult(RetCode.SUCCESS, true, data);
	}
	/**
	 * 失败结果集
	 * @param retCode
	 * @return
	 */
	public static RetResult getError(RetCode retCode) {
		return new RetResult(retCode, false,null);
	}
	/**
	 * [A0001]用户为登录或者检查登录状态失败,请重试或重新登录
	 * @return
	 */
	public static RetResult getAuthenNot() {
		return new RetResult(RetCode.AUTHEN_NOT);
	}
	/**
	 * [A0002]用户信息获取失败,请重新登录
	 * @return
	 */
	public static RetResult getAuthenUserError() {
		return new RetResult(RetCode.AUTHEN_USER_ERROR);
	}

}
