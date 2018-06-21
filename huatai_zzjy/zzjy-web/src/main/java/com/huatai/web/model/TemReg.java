package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 区域和模板关联表，同时也是区域表本身
 *
 */
public class TemReg {
    private Integer regId;

    private Integer tempId;

    private String regCode;

    private String regName;
    
    private String creatorName;
    
    private String modifierName;

    private String opType;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private String creatorId;

    private String modifierId;

    private String remark;

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public Integer getTempId() {
        return tempId;
    }

    public void setTempId(Integer tempId) {
        this.tempId = tempId;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName == null ? null : regName.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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






