package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class CustPlate {
	private Integer plateId;

	private String plateName;

	private String plateCode;

	private String roleCode;

	private String username;

	private String opType;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;

	private String creatorId;

	private String modifierId;

	public Integer getPlateId() {
		return plateId;
	}

	public void setPlateId(Integer plateId) {
		this.plateId = plateId;
	}

	public String getPlateName() {
		return plateName;
	}

	public void setPlateName(String plateName) {
		this.plateName = plateName == null ? null : plateName.trim();
	}

	public String getPlateCode() {
		return plateCode;
	}

	public void setPlateCode(String plateCode) {
		this.plateCode = plateCode == null ? null : plateCode.trim();
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode == null ? null : roleCode.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType == null ? null : opType.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId == null ? null : creatorId.trim();
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId == null ? null : modifierId.trim();
	}
}