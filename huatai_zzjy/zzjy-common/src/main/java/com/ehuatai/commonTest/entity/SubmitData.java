package com.ehuatai.commonTest.entity;

/**
 * 
 * 封装请求的参数
 */
public class SubmitData {
	private String tranDate;// 交易日期
	private String tranTime;// 交易时间
	private String batchNo;// 批次号
	private String ip;// IP地址
	private String system;// 请求系统名称
	private String eqp;// 当前请求设备
	private String location;// 经纬度
	private String equipmentType;// 设备类型
	private String equipment;// 移动终端型号
	private String equipmentID;// 设备终端ID
	private String ldToken;// 角色唯一标识
	private RoleData data; // 封装的角色信息

	public String getEqp() {
		return eqp;
	}

	public void setEqp(String eqp) {
		this.eqp = eqp;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getEquipmentID() {
		return equipmentID;
	}

	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}

	public RoleData getData() {
		return data;
	}

	public void setData(RoleData data) {
		this.data = data;
	}

	public String getLdToken() {
		return ldToken;
	}

	public void setLdToken(String ldToken) {
		this.ldToken = ldToken;
	}

}
