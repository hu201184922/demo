package com.ehuatai.commonTest.service.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ehuatai.biz.domain.Nodes;
import com.ehuatai.biz.domain.Role;
import com.ehuatai.biz.domain.User;
import com.ehuatai.biz.mapper.BaseMapper;
import com.ehuatai.commonTest.entity.RoleData;
import com.ehuatai.commonTest.entity.SubmitData;
import com.ehuatai.commonTest.entity.UrlConnection;
import com.ehuatai.commonTest.service.CommonService;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.util.CephUtil;
import com.ehuatai.util.CommonUtil;
import com.ehuatai.util.ExcelUtils;
import com.fairyland.jdp.framework.util.Base64Util;
import com.fairyland.jdp.framework.util.PropsUtil;

import sun.misc.BASE64Decoder;


@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private BaseMapper baseMapper;
	/**
	 * 用户登录
	 * 
	 */
	@Override
	public String getUserLoad(Map<String, Object> map, HttpServletRequest request) {
		String userCode = (String) map.get("userCode");// 用户账号
		String passWord = (String) map.get("passWord");// 用户密码
		SubmitData smd = new SubmitData();// 提交的数据
		RoleData data = new RoleData();
		smd.setSystem("jyfx");
		smd.setEqp((String) map.get("eqp"));
		smd.setEquipmentType((String) map.get("eqp"));
		smd.setBatchNo(CommonUtil.getBatchNo());
		smd.setTranTime(CommonUtil.getTranTime());
		smd.setTranDate(CommonUtil.getTranDate());
		smd.setIp(CommonUtil.getIp(request));// ip地址
		data.setUserCode(userCode);
		data.setPassWord(CommonUtil.encrypy(passWord));// 密码加密
		data.setTransNo(CommonUtil.getTransNo());
		smd.setData(data);
		String result = JSON.toJSONString(smd);
		String resultJson = UrlConnection.getData(CommonUtil.USERLOGIN, result);
		return resultJson;
	}

	/**
	 * 根据用户信息找回密码（暂且不用）
	 * 
	 */
	@Override
	public RestResult FindPassWordByUserInfor(Map<String, Object> map, HttpServletRequest request) {
		String phone = (String) map.get("phone"); // 用户手机号
		String email = (String) map.get("email");// 用户邮箱
		String newPassWord = (String) map.get("newPassWord");// 用户新密码
		SubmitData smd = new SubmitData();// 提交的数据
		RoleData data = new RoleData();
		smd.setSystem("jyfx");
		smd.setIp(CommonUtil.getIp(request));// ip地址
		smd.setBatchNo(CommonUtil.getBatchNo());
		smd.setTranTime(CommonUtil.getTranTime());
		smd.setTranDate(CommonUtil.getTranDate());
		data.setPhone(CommonUtil.encrypy(phone));
		data.setEmail(CommonUtil.encrypy(email));
		data.setNewPassWord(CommonUtil.encrypy(newPassWord));
		data.setTransNo(CommonUtil.getTransNo());
		smd.setData(data);
		String result = JSON.toJSONString(smd);
		String resultJson = UrlConnection.getData(CommonUtil.FINDPWDBYINFO, result);
		JSONObject jsonResult = JSON.parseObject(resultJson);
		if (jsonResult.getString("code").equals("0")) {
			return RestResult.getSuccess(JSON.parseObject(""));
		} else {
			return new RestResult(RestCode.AUTHEN_PASSWORD.getErrCode(), jsonResult.getString("msg"), false, "");
		}
	}

	/**
	 * 根据手机验证码找回密码
	 * 
	 */
	@Override
	public RestResult findPassWordByPhoneCode(Map<String, Object> map, HttpServletRequest request) {
		String phone = (String) map.get("phone"); // 用户手机号
		String phoneCode = (String) map.get("phoneCode");// 用户验证码
		String newPassWord = (String) map.get("newPassWord");// 用户新密码
		SubmitData smd = new SubmitData();// 提交的数据
		RoleData data = new RoleData();
		smd.setSystem("jyfx");
		smd.setEqp((String) map.get("eqp"));
		smd.setEquipmentType((String) map.get("eqp"));
		smd.setIp(CommonUtil.getIp(request));// ip地址
		smd.setBatchNo(CommonUtil.getBatchNo());
		smd.setTranTime(CommonUtil.getTranTime());
		smd.setTranDate(CommonUtil.getTranDate());
		data.setPhone(CommonUtil.encrypy(phone));
		data.setPhoneCode(CommonUtil.encrypy(phoneCode));
		data.setNewPassWord(CommonUtil.encrypy(newPassWord));
		data.setTransNo(CommonUtil.getTransNo());
		smd.setData(data);
		String result = JSON.toJSONString(smd);
		String resultJson = UrlConnection.getData(CommonUtil.FINDPWDBYCODE, result);
		JSONObject jsonResult = JSON.parseObject(resultJson);
		if (jsonResult.getString("code").equals("0")) {
			return RestResult.getSuccess(JSON.parseObject(""));
		} else {
			return new RestResult(RestCode.AUTHEN_PASSWORD.getErrCode(), jsonResult.getString("msg"), false, "");
		}

	}

	/**
	 * 获取手机验证码
	 * 
	 */
	@Override
	public RestResult getPhoneVerification(Map<String, Object> map, HttpServletRequest request) {
		String phone = (String) map.get("phone"); // 用户手机号
		SubmitData smd = new SubmitData();// 提交的数据
		RoleData data = new RoleData();
		smd.setSystem("jyfx");
		smd.setEqp((String) map.get("eqp"));
		smd.setEquipmentType((String) map.get("eqp"));
		smd.setBatchNo(CommonUtil.getBatchNo());
		smd.setTranTime(CommonUtil.getTranTime());
		smd.setTranDate(CommonUtil.getTranDate());
		smd.setIp(CommonUtil.getIp(request));// ip地址
		data.setPhone(CommonUtil.encrypy(phone));
		data.setTransNo(CommonUtil.getTransNo());
		smd.setData(data);
		String result = JSON.toJSONString(smd);
		String resultJson = UrlConnection.getData(CommonUtil.GETPHONECODE, result);
		JSONObject resultjson = JSON.parseObject(resultJson);
		if (resultjson.getString("code").equals("0")) {
			return RestResult.getSuccess(JSON.parseObject(""));
		} else {
			return new RestResult(RestCode.AUTHEN_PHONE.getErrCode(), resultjson.getString("msg"), false, "");
		}
	}

	/**
	 * 刷新TOKEN
	 * 
	 */
	@Override
	public String refreshToken(Map<String, Object> map) {
		String ldToken = (String) map.get("ldToken");
		SubmitData smd = new SubmitData();
		smd.setEqp((String) map.get("eqp"));
		smd.setEquipmentType((String) map.get("eqp"));
		smd.setLdToken(ldToken);
		String resultJson = UrlConnection.getData(CommonUtil.REFRESHTOKEN, JSON.toJSONString(smd));
		return resultJson;
	}

	/**
	 * 验证TOKEN
	 * 
	 */
	@Override
	public String validateToken(Map<String, Object> map) {
		String ldToken = (String) map.get("ldToken");
		SubmitData smd = new SubmitData();
		smd.setEqp((String) map.get("eqp"));
		smd.setEquipmentType((String) map.get("eqp"));
		smd.setLdToken(ldToken);
		String resultJson = UrlConnection.getData(CommonUtil.VALIDATETOKEN, JSON.toJSONString(smd));
		return resultJson;
	}

	/**
	 * 销毁TOKEN
	 * 
	 */
	@Override
	public String destoryToken(Map<String, Object> map) {
		String ldToken = (String) map.get("ldToken");
		SubmitData smd = new SubmitData();
		smd.setEqp((String) map.get("eqp"));
		smd.setEquipmentType((String) map.get("eqp"));
		smd.setLdToken(ldToken);
		String resultJson = UrlConnection.getData(CommonUtil.DESTORYTOKEN, JSON.toJSONString(smd));
		return resultJson;
	}

	/**
	 * 用户系统权限
	 * 
	 */
	@Override
	public String getUserSystemAuthority(Map<String, Object> map, HttpServletRequest request) {
		String userCode = (String) map.get("userCode");// 用户账号
		String ldToken = (String) map.get("ldToken");
		SubmitData smd = new SubmitData();// 提交的数据
		RoleData data = new RoleData();
		smd.setSystem("jyfx");
		smd.setEqp((String) map.get("eqp"));
		smd.setEquipmentType((String) map.get("eqp"));
		smd.setBatchNo(CommonUtil.getBatchNo());
		smd.setTranTime(CommonUtil.getTranTime());
		smd.setTranDate(CommonUtil.getTranDate());
		smd.setLdToken(ldToken);
		smd.setIp(CommonUtil.getIp(request));// ip地址
		data.setUserCode(userCode);
		data.setTransNo(CommonUtil.getTransNo());
		smd.setData(data);
		String result = JSON.toJSONString(smd);
		String resultJson = UrlConnection.getData(CommonUtil.USERSYSTEM, result);
		return resultJson;
	}

	/**
	 * 获取用户信息
	 * 
	 */
	@Override
	public String getUserMessage(Map<String, Object> map, HttpServletRequest request) {
		String userCode = (String) map.get("userCode");// 用户账号
		String ldToken = (String) map.get("ldToken");
		SubmitData smd = new SubmitData();// 提交的数据
		RoleData data = new RoleData();
		smd.setSystem("jyfx");
		smd.setEqp((String) map.get("eqp"));
		smd.setEquipmentType((String) map.get("eqp"));
		smd.setBatchNo(CommonUtil.getBatchNo());
		smd.setTranTime(CommonUtil.getTranTime());
		smd.setTranDate(CommonUtil.getTranDate());
		smd.setIp(CommonUtil.getIp(request));// ip地址
		smd.setLdToken(ldToken);
		data.setUserCode(userCode);
		data.setTransNo(CommonUtil.getTransNo());
		smd.setData(data);
		String result = JSON.toJSONString(smd);
		String resultJson = UrlConnection.getData(CommonUtil.USERINFO, result);
		return resultJson;
	}

	/**
	 * 用户角色和权限获取
	 * 
	 */
	@Override
	public String getRoleAuthority(Map<String, Object> map, HttpServletRequest request) {
		String userCode = (String) map.get("userCode");// 用户账号
		String ldToken = (String) map.get("ldToken");
		SubmitData smd = new SubmitData();// 提交的数据
		RoleData data = new RoleData();
		smd.setSystem("jyfx");
		smd.setEqp((String) map.get("eqp"));
		smd.setEquipmentType((String) map.get("eqp"));
		smd.setBatchNo(CommonUtil.getBatchNo());
		smd.setTranTime(CommonUtil.getTranTime());
		smd.setTranDate(CommonUtil.getTranDate());
		smd.setIp(CommonUtil.getIp(request));// ip地址
		smd.setLdToken(ldToken);
		data.setUserCode(userCode);
		data.setTransNo(CommonUtil.getTransNo());
		smd.setData(data);
		String result = JSON.toJSONString(smd);
		String resultJson = UrlConnection.getData(CommonUtil.ROLEAUTHORITY, result);
		return resultJson;
	}

	/**
	 * 
	 * 封装用户信息
	 */
	@Override
	public RestResult getRoleInfo(Map<String, Object> map, HttpServletRequest request) {
		String restResult = this.getUserLoad(map, request).replace("\\", "");
		JSONObject jsonOResult;
		try {
			jsonOResult = JSON.parseObject(restResult);
		} catch (Exception e) {
			jsonOResult = JSON.parseObject(restResult.substring(1, restResult.length() - 1));
		}

		if (jsonOResult.getString("code").equals("0")) {// 登陆成功
			JSONObject jsonToken = jsonOResult.getJSONObject("data");
			map.put("ldToken", jsonToken.getString("ldToken"));
			map.put("userCode", jsonToken.getString("userCode"));
			String roleOrg = this.getRoleAuthority(map, request).replace("\\", "");// 获取角色权限
			String userInfo = this.getUserMessage(map, request).replace("\\", "");// 获取用户信息
			JSONObject jsonRole;
			JSONObject jsonUser;
			try {
				jsonRole = JSON.parseObject(roleOrg);
				jsonUser = JSON.parseObject(userInfo);
			} catch (Exception e) {
				jsonRole = JSON.parseObject(roleOrg.substring(1, roleOrg.length() - 1));
				jsonUser = JSON.parseObject(userInfo.substring(1, userInfo.length() - 1));
			}
			JSONObject jsonDataRole = jsonRole.getJSONObject("data");
			JSONObject jsonDataUser = jsonUser.getJSONObject("data");
			User user = new User();
			user.setUsername((String) map.get("userCode"));
			user.setToken(jsonToken.getString("ldToken"));
			user.setNick(jsonDataUser.getString("userName"));
			JSONArray roles = jsonDataRole.getJSONArray("roleIDResult");
			List<Role> roleLists = new ArrayList<Role>();
			if (null != roles) {
				for (int i = 0, len = roles.size(); i < len; i++) {
					Role role = new Role();
					JSONObject jsonRoles = roles.getJSONObject(i);
					role.setRole(jsonRoles.getString("roleID"));
					System.out.println(jsonRoles.getString("roleName"));
					role.setRoleName(jsonRoles.getString("roleName"));
					role.setRoleDept(jsonRoles.getString("department"));
					if (jsonRoles.getString("department").length() == 10) {
						role.setRoleOrg(jsonRoles.getString("department"));
						HashMap<String, Object> map2 = baseMapper.getServiceByCenterCode(role.getRoleOrg());
						role.setRoleOrgName((String) map2.get("NAME"));
					} else {
						role.setRoleOrg(jsonRoles.getString("manageCom"));
						role.setRoleOrgName(jsonRoles.getString("manageComName"));
					}
					role.setRoleDeptName(jsonRoles.getString("departmentName"));

					// role.setRoleOrgName(jsonRoles.getString("manageComName"));
					List<Nodes> nodeLists = new ArrayList<Nodes>();
					JSONArray nodeList = jsonRoles.getJSONArray("nodeResult");
					if (null != nodeList) {
						for (int j = 0, lan = nodeList.size(); j < lan; j++) {
							JSONObject jsonNode = nodeList.getJSONObject(j);
							Nodes node = new Nodes();
							node.setNodeCode(jsonNode.getString("nodeCode"));
							node.setParentNodeCode(jsonNode.getString("parentNodeCode"));
							node.setNodeName(jsonNode.getString("nodeName"));
							node.setNodeOrd(jsonNode.getString("nodeOrder"));
							node.setRunscript(jsonNode.getString("runscript"));
							nodeLists.add(node);
						}
					}
					role.setNodes(nodeLists);
					roleLists.add(role);
				}
			}
			user.setRoles(roleLists);

			// String users = JSON.toJSONString(user);
			// RestResult restResults = RestResult.getSuccess(JSON.parseObject(users));

			String userBase = Base64Util.encode(JSON.toJSONString(user, SerializerFeature.DisableCircularReferenceDetect));
			RestResult restResults = RestResult.getSuccess(userBase);
			return restResults;
		} else {
			return new RestResult(RestCode.AUTHEN_FAILD.getErrCode(), jsonOResult.getString("msg"), false, "");
		}
	}

	/**
	 * 
	 * 移动端封装用户信息
	 */
	@Override
	public RestResult getAppRoleInfo(Map<String, Object> map, HttpServletRequest request) {
		String restResult = this.validateToken(map);
		JSONObject jsonOResult = JSON.parseObject(restResult);
		if (jsonOResult.getString("code").equals("0")) {
			String roleOrg = this.getRoleAuthority(map, request);// 获取角色权限
			String userInfo = this.getUserMessage(map, request);// 获取用户信息
			JSONObject jsonRole = JSON.parseObject(roleOrg);
			JSONObject jsonUser = JSON.parseObject(userInfo);
			JSONObject jsonDataUser = jsonUser.getJSONObject("data");
			JSONObject jsonDataRole = jsonRole.getJSONObject("data");
			User user = new User();
			user.setUsername((String) map.get("userCode"));
			user.setToken((String) map.get("ldToken"));
			user.setNick(jsonDataUser.getString("userName"));
			List<Role> roleLists = new ArrayList<Role>();
			JSONArray roles = jsonDataRole.getJSONArray("roleIDResult");
			if (null != roles) {
				for (int i = 0, len = roles.size(); i < len; i++) {
					Role role = new Role();
					JSONObject jsonRoles = roles.getJSONObject(i);
					role.setRole(jsonRoles.getString("roleID"));
					role.setRoleName(jsonRoles.getString("roleName"));
					role.setRoleDept(jsonRoles.getString("department"));
					if (jsonRoles.getString("department").length() == 10) {
						role.setRoleOrg(jsonRoles.getString("department"));
					} else {
						role.setRoleOrg(jsonRoles.getString("manageCom"));
					}
					role.setRoleDeptName(jsonRoles.getString("departmentName"));
					role.setRoleOrgName(jsonRoles.getString("manageComName"));
					List<Nodes> nodeLists = new ArrayList<Nodes>();
					JSONArray nodeList = jsonRoles.getJSONArray("nodeResult");
					if (null != nodeList) {
						for (int j = 0, lan = nodeList.size(); j < lan; j++) {
							JSONObject jsonNode = nodeList.getJSONObject(j);
							Nodes node = new Nodes();
							node.setNodeCode(jsonNode.getString("nodeCode"));
							node.setParentNodeCode(jsonNode.getString("parentNodeCode"));
							node.setNodeName(jsonNode.getString("nodeName"));
							node.setNodeOrd(jsonNode.getString("nodeOrder"));
							node.setRunscript(jsonNode.getString("runscript"));
							nodeLists.add(node);
						}
					}
					role.setNodes(nodeLists);
					roleLists.add(role);
				}
			}
			user.setRoles(roleLists);
						
			//String userBase = Base64Util.encode(JSON.toJSONString(user,
			//SerializerFeature.DisableCircularReferenceDetect));
			RestResult restResults = RestResult.getSuccess(user);
			return restResults;
		} else {
			return new RestResult(RestCode.AUTHEN_LDTOKEN_EXPIRE.getErrCode(), jsonOResult.getString("msg"), false, "");
		}
	}

	@Override
	public void downloadFile(String filename, HttpServletRequest request, HttpServletResponse response) {
//		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//		String[] str = path.split("target");
		String servicefileUrl = PropsUtil.get("tmppath") + filename + ".xlsx";
		File file = new File(servicefileUrl);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition",
				"attachment; filename=\"" + ExcelUtils.toUtf8String(filename) + ".xlsx");
		InputStream inStream;
		try {
			inStream = new FileInputStream(file);
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void downloadPicF(String filename, HttpServletRequest request, HttpServletResponse response) {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String[] str = path.split("target");
		String servicefileUrl = str[0].substring(1, str[0].length()) + "excel/" + filename + ".png";
		File file = new File(servicefileUrl);
		response.reset();
		response.setContentType("image/png");
		response.addHeader("Content-Disposition",
				"attachment; filename=\"" + ExcelUtils.toUtf8String(filename)+ ".png");
		InputStream inStream;
		try {
			inStream = new FileInputStream(file);
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public RestResult downloadPic(Map<String, Object> reqParams, HttpServletRequest request, HttpServletResponse response) {
		String imgData = reqParams.get("imgData").toString();
		String filename = reqParams.get("filename").toString();
		String imgFilePath = GenerateImage(imgData, filename);
		Map<String, Object> resMap = new HashMap<>();
		if (!"".equals(imgFilePath)) {
			resMap.put("fileurl", PropsUtil.get("base.url") + "/ceph/comdownloadfile/" + imgFilePath);
			resMap.put("filename", imgFilePath);
		}
		return RestResult.getSuccess(resMap);
	}
	
	// base64字符串转化成图片
	public static String GenerateImage(String imgStr, String picname) {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String imgFilePath = "";
		String picN = "";
		String imgBase64 = imgStr.split(",")[1];
		if (imgBase64 != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				// Base64解码
				byte[] b = decoder.decodeBuffer(imgBase64);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
//				String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//				String[] str = path.split("target");
				// 处理图片名
				picN = picname.substring(0, picname.indexOf(".png")) + sdf.format(now.getTime()) + ".png";
				imgFilePath = PropsUtil.get("tmppath") + picN;// 新生成的图片
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
				CephUtil.uploadFile(imgFilePath, "comdownloadfile", picN);
			} catch (Exception e) {
				return "";
			}
		}
		return picN;
	}
}
