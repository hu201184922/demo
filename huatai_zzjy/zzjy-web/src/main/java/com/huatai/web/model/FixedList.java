package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class FixedList extends TreeResult{ 
    private String flCode;

    private String flName;

    private String flDeptCode;

    private String opType;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private String creatorId;

    private String modifierId;
    
    private boolean Checked;
 

    public boolean getChecked() {
		return Checked;
	}

	public void setChecked(boolean b) {
		Checked = b;
	}

	public String getFlCode() {
        return flCode;
    }

    public void setFlCode(String flCode) {
        this.flCode = flCode == null ? null : flCode.trim();
    }

    public String getFlName() {
        return flName;
    }

    public void setFlName(String flName) {
        this.flName = flName == null ? null : flName.trim();
    }

    public String getFlDeptCode() {
        return flDeptCode;
    }

    public void setFlDeptCode(String flDeptCode) {
        this.flDeptCode = flDeptCode == null ? null : flDeptCode.trim();
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