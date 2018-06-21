package com.huatai.web.model;

import java.util.Date;

import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;

public class Target extends TreeResult {
	private String targetCode;

	private String targetName;

	private String targetType;

	private String parentCode;

	private Integer sort;

	private String isPlate;

	private String isHomeCoreTarget;

	private String isManageTarget;

	private String isMath;

	private String tarDefSpec;

	private String deptCode;

	private String channel;

	private String calFormula;

	private String calRate;

	private String fieldCode;

	private String isRealtime;

	private String isStatis;

	private String isWarnTarget;

	private String isStatisTarget;

	private String remark;

	private String opType;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;

	private String creatorId;

	private String modifierId;

	@Transient
	private Boolean checked;

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
		this.targetName = targetName == null ? null : targetName.trim();
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType == null ? null : targetType.trim();
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode == null ? null : parentCode.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIsPlate() {
		return isPlate;
	}

	public void setIsPlate(String isPlate) {
		this.isPlate = isPlate == null ? null : isPlate.trim();
	}

	public String getIsHomeCoreTarget() {
		return isHomeCoreTarget;
	}

	public void setIsHomeCoreTarget(String isHomeCoreTarget) {
		this.isHomeCoreTarget = isHomeCoreTarget == null ? null : isHomeCoreTarget.trim();
	}

	public String getIsManageTarget() {
		return isManageTarget;
	}

	public void setIsManageTarget(String isManageTarget) {
		this.isManageTarget = isManageTarget == null ? null : isManageTarget.trim();
	}

	public String getIsMath() {
		return isMath;
	}

	public void setIsMath(String isMath) {
		this.isMath = isMath == null ? null : isMath.trim();
	}

	public String getTarDefSpec() {
		return tarDefSpec;
	}

	public void setTarDefSpec(String tarDefSpec) {
		this.tarDefSpec = tarDefSpec == null ? null : tarDefSpec.trim();
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode == null ? null : deptCode.trim();
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel == null ? null : channel.trim();
	}

	public String getCalFormula() {
		return calFormula;
	}

	public void setCalFormula(String calFormula) {
		this.calFormula = calFormula == null ? null : calFormula.trim();
	}

	public String getCalRate() {
		return calRate;
	}

	public void setCalRate(String calRate) {
		this.calRate = calRate == null ? null : calRate.trim();
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode == null ? null : fieldCode.trim();
	}

	public String getIsRealtime() {
		return isRealtime;
	}

	public void setIsRealtime(String isRealtime) {
		this.isRealtime = isRealtime == null ? null : isRealtime.trim();
	}

	public String getIsStatis() {
		return isStatis;
	}

	public void setIsStatis(String isStatis) {
		this.isStatis = isStatis == null ? null : isStatis.trim();
	}

	public String getIsWarnTarget() {
		return isWarnTarget;
	}

	public void setIsWarnTarget(String isWarnTarget) {
		this.isWarnTarget = isWarnTarget == null ? null : isWarnTarget.trim();
	}

	public String getIsStatisTarget() {
		return isStatisTarget;
	}

	public void setIsStatisTarget(String isStatisTarget) {
		this.isStatisTarget = isStatisTarget == null ? null : isStatisTarget.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}