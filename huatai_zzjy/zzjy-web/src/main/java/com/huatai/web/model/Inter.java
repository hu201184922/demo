package com.huatai.web.model;

import java.util.Date;

import org.springframework.data.annotation.Transient;

import com.alibaba.fastjson.annotation.JSONField;

public class Inter {
    private Integer interId;

    private String interName;

    private String remark;

    private String opType;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private String creatorId;

    private String modifierId;
    
    private String creatorName;

    private String modifierName;
    
    @Transient
    private Boolean checked;
    
    public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Integer getInterId() {
        return interId;
    }

    public void setInterId(Integer interId) {
        this.interId = interId;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName == null ? null : interName.trim();
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
    
    
}