package com.ehuatai.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import com.ehuatai.commonTest.sm2.SM2Utils;
import com.ehuatai.commonTest.sm2.Util;

@Component
public class CommonUtil {

	// public static final String PREFIX =//
	// "http://10.100.252.190:8080/wechat_shiro";
	// "http://123.127.120.126:8080/wechat_shiro";

	public static String PREFIX = new PropsUtils().getValue("authBaseUrl");

	// 用户注册
	public static final String REGISTER = PREFIX + "/register";

	// 用户登录
	public static final String USERLOGIN = PREFIX + "/doLogin";

	// 通过用户信息找回密码
	public static final String FINDPWDBYINFO = PREFIX + "/retrievePassWordByInfo";

	// 通过手机验证码找回密码
	public static final String FINDPWDBYCODE = PREFIX + "/retrievePassWordByCode";

	// 获取手机验证码
	public static final String GETPHONECODE = PREFIX + "/getPhoneCode";

	// 刷新TOKEN
	public static final String REFRESHTOKEN = PREFIX + "/refreshToken";

	// 验证TOKEN
	public static final String VALIDATETOKEN = PREFIX + "/verificationToken";

	// 销毁TOKEN
	public static final String DESTORYTOKEN = PREFIX + "/destroyToken";

	// 用户系统权限
	public static final String USERSYSTEM = PREFIX + "/getSystems";

	// 用户信息
	public static final String USERINFO = PREFIX + "/getUserInfo";

	// 用户角色和权限
	public static final String ROLEAUTHORITY = PREFIX + "/getRolesAndMenus";

	/**
	 * 信息流水号(TransNo)
	 */
	public static String getTransNo() {
		String str = "000000";
		SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		str = str + sim.format(new Date());
		String time1 = Integer.toString((int) (Math.random() * 10));
		String time2 = Integer.toString((int) (Math.random() * 10));
		str = str + time1 + time2;
		return str;
	}

	/**
	 * 
	 * 交易时间(TranDate)
	 */
	public static String getTranDate() {
		SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
		return sim.format(new Date());
	}

	/**
	 * 
	 * 交易日期(TranTime)
	 */
	public static String getTranTime() {
		SimpleDateFormat sim = new SimpleDateFormat("HH:mm:ss");
		return sim.format(new Date());
	}

	/**
	 * 
	 * 批次号(BatchNo)
	 */
	public static String getBatchNo() {
		String str = "jyfx";
		SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		str = str + sim.format(new Date());
		String time1 = Integer.toString((int) (Math.random() * 10));
		String time2 = Integer.toString((int) (Math.random() * 10));
		str = str + time1 + time2;
		return str;
	}

	/**
	 * 
	 * 加密
	 */
	public static String encrypy(String pwd) {
		// 公钥
		String pubk = "0408C64722FFE356E0C88FB72A7C6D81BA85A7FEACE8E3823E1CFE54B2D1486E0D24E3E1E3DF70208D9C1613465D21E10A59B1A42D042EDB338FDFB275B3137570";
		byte[] pwds = pwd.getBytes();
		String str = null;
		try {
			str = SM2Utils.encrypt(Util.hexToByte(pubk), pwds);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 
	 * 获取用户id
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
