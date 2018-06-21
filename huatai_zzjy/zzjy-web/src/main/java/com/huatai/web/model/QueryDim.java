package com.huatai.web.model;

import java.util.Date;

import org.springframework.data.annotation.Transient;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryDim {
	private Integer qdId;

	private String queryDimCode;

	private String queryDimName;

	private String isAuth;

	private String queryDimType;

	private String queryDimShowType;

	private String opType;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;

	private String creatorId;

	private String modifierId;

	private String creatorName;

	private String modifierName;

	@Transient
	private Boolean checked;

	@Transient
	private Object queryDimDetail;

	public Object getQueryDimDetail() {
		return queryDimDetail;
	}

	public void setQueryDimDetail(Object queryDimDetail) {
		this.queryDimDetail = queryDimDetail;
	}

	public Integer getQdId() {
		return qdId;
	}

	public void setQdId(Integer qdId) {
		this.qdId = qdId;
	}

	public String getQueryDimCode() {
		return queryDimCode;
	}

	public void setQueryDimCode(String queryDimCode) {
		this.queryDimCode = queryDimCode == null ? null : queryDimCode.trim();
	}

	public String getQueryDimName() {
		return queryDimName;
	}

	public void setQueryDimName(String queryDimName) {
		this.queryDimName = queryDimName == null ? null : queryDimName.trim();
	}

	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth == null ? null : isAuth.trim();
	}

	public String getQueryDimType() {
		return queryDimType;
	}

	public void setQueryDimType(String queryDimType) {
		this.queryDimType = queryDimType == null ? null : queryDimType.trim();
	}

	public String getQueryDimShowType() {
		return queryDimShowType;
	}

	public void setQueryDimShowType(String queryDimShowType) {
		this.queryDimShowType = queryDimShowType;
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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}