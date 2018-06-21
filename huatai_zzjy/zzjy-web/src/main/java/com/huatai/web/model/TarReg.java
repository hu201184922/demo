package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 指标与区域关联表，指标表与该表以targetCode关联
 *
 */
public class TarReg {
    private Integer trId;

    private Integer regId;

    private String targetCode;

    private String subCode;

    private String dimType;

    private String graphType;

    private String graphTitle;

    private String xName;

    private String yName;

    private String unit;
    
    private String color;

    private String roleOrgType;

    private String isRank;

    private String rankOrgType;

    private String isDisplay;

    private Integer sort;

    private String pwd;

    private String opType;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private String creatorId;

    private String modifierId;

    public Integer getTrId() {
        return trId;
    }

    public void setTrId(Integer trId) {
        this.trId = trId;
    }

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode == null ? null : targetCode.trim();
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode == null ? null : subCode.trim();
    }

    public String getDimType() {
        return dimType;
    }

    public void setDimType(String dimType) {
        this.dimType = dimType == null ? null : dimType.trim();
    }

    public String getGraphType() {
        return graphType;
    }

    public void setGraphType(String graphType) {
        this.graphType = graphType == null ? null : graphType.trim();
    }

    public String getGraphTitle() {
        return graphTitle;
    }

    public void setGraphTitle(String graphTitle) {
        this.graphTitle = graphTitle == null ? null : graphTitle.trim();
    }

    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName == null ? null : xName.trim();
    }

    public String getyName() {
        return yName;
    }

    public void setyName(String yName) {
        this.yName = yName == null ? null : yName.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getRoleOrgType() {
        return roleOrgType;
    }

    public void setRoleOrgType(String roleOrgType) {
        this.roleOrgType = roleOrgType == null ? null : roleOrgType.trim();
    }

    public String getIsRank() {
        return isRank;
    }

    public void setIsRank(String isRank) {
        this.isRank = isRank == null ? null : isRank.trim();
    }

    public String getRankOrgType() {
        return rankOrgType;
    }

    public void setRankOrgType(String rankOrgType) {
        this.rankOrgType = rankOrgType == null ? null : rankOrgType.trim();
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay == null ? null : isDisplay.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}