package com.huatai.web.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.huatai.web.utils.DateUtil;

/**
 * @功能 {响应实体}
 * @作者 MaxBill
 * @时间 2017年7月17日 下午5:20:44
 */
@Component
public class ResponseBean {
	private String errCode;// 错误码
	private String errMsgs;// 错误信息
	private String success;// 成功信息
	private Object data;// 响应数据
	private String timeStamp;// 时间戳

	public ResponseBean() {
	}

	public ResponseBean(String errCode, String errMsgs, String success, Object data) {
		this.errCode = errCode;
		this.errMsgs = errMsgs;
		this.success = success;
		this.data = data;
		this.timeStamp = DateUtil.formatDate(new Date(), DateUtil.FULL_DATE_TIME_FORMAT);
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsgs() {
		return errMsgs;
	}

	public void setErrMsgs(String errMsgs) {
		this.errMsgs = errMsgs;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
