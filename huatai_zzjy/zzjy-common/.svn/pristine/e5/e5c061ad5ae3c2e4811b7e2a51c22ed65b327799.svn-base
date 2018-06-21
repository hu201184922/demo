package com.ehuatai.commonTest.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ehuatai.ret.RestResult;

/**
 * 
 * 
 * 权限登录
 *
 */
public interface CommonService {

	// 用户登录
	public String getUserLoad(Map<String, Object> map, HttpServletRequest request);

	// 封装用户信息
	public RestResult getRoleInfo(Map<String, Object> map,HttpServletRequest request);

	// 根据手机验证码找回密码
	public RestResult findPassWordByPhoneCode(Map<String, Object> map,HttpServletRequest request);

	// 根据用户信息找回密码（暂且不用）
	public RestResult FindPassWordByUserInfor(Map<String, Object> map,HttpServletRequest request);

	// 获取手机验证码
	public RestResult getPhoneVerification(Map<String, Object> map,HttpServletRequest request);

	// 刷新TOKEN
	String refreshToken(Map<String, Object> map);

	// 验证TOKEN
	String validateToken(Map<String, Object> map);

	// 销毁TOKEN
	String destoryToken(Map<String, Object> map);

	// 用户系统权限
	String getUserSystemAuthority(Map<String, Object> map,HttpServletRequest request);

	// 获取用户信息
	String getUserMessage(Map<String, Object> map,HttpServletRequest request);

	// 用户角色和权限获取
	String getRoleAuthority(Map<String, Object> map,HttpServletRequest request);

	// 移动端封装用户信息
	RestResult getAppRoleInfo(Map<String, Object> map, HttpServletRequest request);

	// 下载服务器端Excel文件
	public void downloadFile(String filename, HttpServletRequest request, HttpServletResponse response);

	// 下载当前页图片
	RestResult downloadPic(Map<String, Object> reqParams, HttpServletRequest request, HttpServletResponse response);

	// 下载服务器端图片文件
	public void downloadPicF(String filename, HttpServletRequest request, HttpServletResponse response);

}
