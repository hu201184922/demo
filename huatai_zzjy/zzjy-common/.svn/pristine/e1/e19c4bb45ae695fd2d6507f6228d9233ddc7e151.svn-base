package com.ehuatai.commonTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.ehuatai.biz.domain.Role;
import com.ehuatai.biz.domain.User;
import com.ehuatai.commonTest.entity.Version;
import com.ehuatai.commonTest.service.CommonService;
import com.ehuatai.commonTest.service.InterceptorService;
import com.ehuatai.ret.RestResult;
import com.ehuatai.util.RequestHeaderUtil;
import com.fairyland.jdp.framework.util.Base64Util;

@RestController
@RequestMapping("/api/")
public class CommonController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private InterceptorService interceptorService;

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	/**
	 * 
	 * 用户登录
	 */
	@RequestMapping("/login")
	public Object getLoad(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult result = commonService.getRoleInfo(reqParams, request);
		try {
			User user = JSON.parseObject(Base64Util.decode(result.getData().toString()), User.class);
			List<String> roleList = new ArrayList<String>();
			for (Role role : user.getRoles()) {
				roleList.add(role.getRole());
			}
			request.setAttribute("login", result);
			request.setAttribute("roles", org.apache.commons.lang3.StringUtils.join(roleList,","));
		} catch (Exception e) {
			return result;
		}
		// 登陆成功处理版本
		if (result.success) {
			request.setAttribute("version", reqParams.get("version").toString());
			this.dealWebVersionData(request, reqParams.get("userCode").toString(), reqParams.get("version").toString());
		}
		return result;
	}

	/**
	 * 
	 * 找回密码(用户信息找回)暂不支持
	 */

	@RequestMapping("/findPwdByInfo")
	public Object findPassWordByUserInfo(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RestResult result = commonService.FindPassWordByUserInfor(reqParams, request);
		return result;
	}

	/**
	 * 
	 * 找回密码(手机验证码找回)
	 */
	@RequestMapping("/forgot")
	public Object findPassWordByPhoneCode(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RestResult result = commonService.findPassWordByPhoneCode(reqParams, request);
		return result;
	}

	/**
	 * 
	 * 获取手机验证码
	 */
	@RequestMapping("/getphonecode")
	public Object getPhoneCode(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RestResult result = commonService.getPhoneVerification(reqParams, request);
		return result;
	}

	/**
	 * 
	 * 用户信息
	 */
	@RequestMapping("/roles")
	public Object findUserInfo(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		// String result = commonService.getUserMessage(reqParams);
		return RestResult.getSuccess("角色切换成功");
	}

	/**
	 * 
	 * 销毁TOKEN(用户退出)
	 */
	@RequestMapping("/logout")
	public String destoryToken(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {

		String result = commonService.destoryToken(reqParams);
		request.setAttribute("logout", result);
		return result;
	}

	/**
	 * 
	 * 用户角色和权限
	 */
	@RequestMapping("/userregister")
	public String UserRegister(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		String result = commonService.getRoleAuthority(reqParams, request);
		return result;
	}

	/**
	 * 
	 * 刷新TOKEN
	 */
	@RequestMapping("/refreshtoken")
	public String refreshToken(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		String result = commonService.refreshToken(reqParams);
		return result;
	}

	/**
	 * 
	 * 验证TOKEN
	 */
	@RequestMapping("/validatetoken")
	public String validateToken(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		String result = commonService.validateToken(reqParams);
		return result;
	}

	/**
	 * 
	 * 用户系统权限
	 */
	@RequestMapping("/usersystem")
	public String findUserSystem(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		String result = commonService.getUserSystemAuthority(reqParams, request);
		return result;
	}

	/**
	 * 
	 * 移动端封装用户信息
	 */
	@RequestMapping("/getAppRoleInfo")
	public Object getAppRoleInfo(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RestResult result = commonService.getAppRoleInfo(reqParams, request);
		if (result.success) {
			dealAppVersionData(request, reqParams.get("userCode").toString());
		}
		return result;
	}

	/**
	 * 下载Excel
	 */
	@RequestMapping(value = "/exl/download/{filename}")
	public void getFileUrl(@PathVariable("filename") String filename, HttpServletRequest request,
			HttpServletResponse response) {
		commonService.downloadFile(filename, request, response);
	}

	/**
	 * 下载图片
	 */
	@RequestMapping(value = "/pic/download/{filename}")
	public void getPicUrl(@PathVariable("filename") String filename, HttpServletRequest request,
			HttpServletResponse response) {
		commonService.downloadPicF(filename, request, response);
	}

	/**
	 * 下载当前页图片
	 */
	@PostMapping(value = "/report/page")
	public Object getFileUrl(@RequestBody Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		return commonService.downloadPic(reqParams, request, response);
	}

	/**
	 * @功能 {pc或者app版本统计处理}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 下午4:58:25
	 * @备注 0：pc端，1：安卓端，2：IOS端
	 */
	public void dealWebVersionData(HttpServletRequest request, String userId, String versionCode) {
		if (!StringUtils.isEmpty(userId)) {
			Version version = new Version();
			// web端登陆
			Version versionBean = interceptorService.existVersion(userId, "0");
			if (null != versionBean) {
				if (!versionBean.getVersionCode().equals(versionCode)) {
					versionBean.setVersionCode(versionCode);
					this.interceptorService.updateVersion(versionBean);
				}
			} else {
				version.setUserId(userId);
				version.setVersionCode(versionCode);
				version.setVersionType("0");
				this.interceptorService.saveVersion(version);
			}
		} else {
			logger.debug("===============未获取到用户ID=============");
		}
	}

	public void dealAppVersionData(HttpServletRequest request, String userId) {
		if (!StringUtils.isEmpty(userId)) {
			Version version = new Version();
			// app端登陆
			String ua = request.getHeader("User-Agent");
			Pattern pattern = Pattern.compile("SINO_([\\w]+)_APP\\/([\\d.]+)");
			Matcher matcher = pattern.matcher(ua);
			if (ua.contains("SINO_ANDROID_APP")) {
				if (matcher.find()) {
					String versionCode = matcher.group(2);
					Version versionBean = interceptorService.existVersion(userId, "1");
					if (null != versionBean) {
						if (!versionBean.getVersionCode().equals(versionCode)) {
							versionBean.setVersionCode(versionCode);
							this.interceptorService.updateVersion(versionBean);
						}
					} else {
						version.setUserId(userId);
						version.setVersionCode(versionCode);
						version.setVersionType("1");
						this.interceptorService.saveVersion(version);
					}
				}
			} else if (ua.contains("SINO_IOS_APP")) {
				if (matcher.find()) {
					String versionCode = matcher.group(2);
					Version versionBean = interceptorService.existVersion(userId, "2");
					if (null != versionBean) {
						if (!versionBean.getVersionCode().equals(versionCode)) {
							versionBean.setVersionCode(versionCode);
							this.interceptorService.updateVersion(versionBean);
						}
					} else {
						version.setUserId(userId);
						version.setVersionCode(versionCode);
						version.setVersionType("2");
						this.interceptorService.saveVersion(version);
					}
				}
			}
		} else {
			logger.debug("===============未获取到用户ID=============");
		}
	}

}
