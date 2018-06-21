package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BillTarget {
	private Integer btId;

	private Integer billId;

	private String targetCode;

	private Integer sort;

	private String formulaType;

	private Float minVal;

	private Float maxVal;

	private String opType;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;

	private String creatorId;

	private String modifierId;

	private String sqlCode;

	private String unit;

	private String targetName;

	private String deptCode;

	public Integer getBtId() {
		return btId;
	}

	public void setBtId(Integer btId) {
		this.btId = btId;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode == null ? null : targetCode.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getFormulaType() {
		return formulaType;
	}

	public void setFormulaType(String formulaType) {
		this.formulaType = formulaType == null ? null : formulaType.trim();
	}

	public Float getMinVal() {
		return minVal;
	}

	public void setMinVal(Float minVal) {
		this.minVal = minVal;
	}

	public Float getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Float maxVal) {
		this.maxVal = maxVal;
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

	public String getSqlCode() {
		return sqlCode;
	}

	public void setSqlCode(String sqlCode) {
		this.sqlCode = sqlCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}