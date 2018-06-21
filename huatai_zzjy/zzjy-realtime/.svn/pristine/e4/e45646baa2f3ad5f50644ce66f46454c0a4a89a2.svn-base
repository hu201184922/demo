package com.ehuatai.ret;

public enum RetCode {
	SUCCESS("S0000","[S0000] 请求成功"),
	ERROR("E0001","[E0001] 未知错误,请详查"),
	EMPTY("E0002","[E0002] 目标数据为空,请检查参数或结果集"),
	AUTHEN_NOT("A0001","[A0001] 用户为登录或者检查登录状态失败,请重试或重新登录"),
	AUTHEN_USER_ERROR("A0002","[A0002] 用户信息获取失败,请重新登录");
	
	
	private String errCode;
	private String errMsg;
	
	RetCode(String errCode,String errMsg){
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public String getErrCode() {
		return errCode;
	}


	public String getErrMsg() {
		return errMsg;
	}


	@Override
	public String toString() {
		String desc = this.errCode +":"+ this.errMsg;
		System.out.println(desc);
		return desc;
	}
	
	
}
