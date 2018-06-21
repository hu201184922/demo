package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ImportTemp {
    private Long itId;

    private String impTempCode;

    private String impTempName;

    private String impTempPath;

    private String deptCode;

    private String opType;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private String creatorId;

    private String modifierId;

    public Long getItId() {
        return itId;
    }

    public void setItId(Long itId) {
        this.itId = itId;
    }

    public String getImpTempCode() {
        return impTempCode;
    }

    public void setImpTempCode(String impTempCode) {
        this.impTempCode = impTempCode == null ? null : impTempCode.trim();
    }

    public String getImpTempName() {
        return impTempName;
    }

    public void setImpTempName(String impTempName) {
        this.impTempName = impTempName == null ? null : impTempName.trim();
    }

    public String getImpTempPath() {
        return impTempPath;
    }

    public void setImpTempPath(String impTempPath) {
        this.impTempPath = impTempPath == null ? null : impTempPath.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
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