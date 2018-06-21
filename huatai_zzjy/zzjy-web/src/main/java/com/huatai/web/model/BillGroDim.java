package com.huatai.web.model;

import java.util.Date;

public class BillGroDim {
    private Integer bgdId;

    private Integer billId;

    private String groupType;

    private String groupDetailCode;

    private String opType;

    private Date createTime;

    private Date modifyTime;

    private String creatorId;

    private String modifierId;

    public Integer getBgdId() {
        return bgdId;
    }

    public void setBgdId(Integer bgdId) {
        this.bgdId = bgdId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }

    public String getGroupDetailCode() {
        return groupDetailCode;
    }

    public void setGroupDetailCode(String groupDetailCode) {
        this.groupDetailCode = groupDetailCode == null ? null : groupDetailCode.trim();
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