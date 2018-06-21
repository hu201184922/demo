package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TarGroDim {
	private Integer tgdId;

	private Integer gddId;

	private String targetCode;

	private String targetName;

	private String groDimName;

	private String opType;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;

	private String creatorId;

	private String modifierId;

	private String creatorName;

	private String modifierName;

	private GroDimDetail groDimDetail;

	public Integer getTgdId() {
		return tgdId;
	}

	public void setTgdId(Integer tgdId) {
		this.tgdId = tgdId;
	}

	public Integer getGddId() {
		return gddId;
	}

	public void setGddId(Integer gddId) {
		this.gddId = gddId;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode == null ? null : targetCode.trim();
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
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

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getGroDimName() {
		return groDimName;
	}

	public void setGroDimName(String groDimName) {
		this.groDimName = groDimName;
	}

	public GroDimDetail getGroDimDetail() {
		return groDimDetail;
	}

	public void setGroDimDetail(GroDimDetail groDimDetail) {
		this.groDimDetail = groDimDetail;
	}

}