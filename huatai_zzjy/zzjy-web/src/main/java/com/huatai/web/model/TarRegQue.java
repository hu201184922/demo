package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TarRegQue {
    private Long trqId;

    private String subCode;

    private String targetCode;

    private Long tempId;

    private Long regId;

    private Long qdId;

    private String opType;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private String createId;

    private String modifierId;

    public Long getTrqId() {
        return trqId;
    }

    public void setTrqId(Long trqId) {
        this.trqId = trqId;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode == null ? null : subCode.trim();
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode == null ? null : targetCode.trim();
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public Long getQdId() {
        return qdId;
    }

    public void setQdId(Long qdId) {
        this.qdId = qdId;
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

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId == null ? null : modifierId.trim();
    }
}