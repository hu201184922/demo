package com.huatai.web.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryDimDetail {
	
    private Integer qddId;

    private Integer qdId;
    
    private String queryDimName;

    private String keyCode;

    private String val;

    private String isDefault;

    private String opType;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private String creatorId;

    private String modifierId;
    
    private String creatorName;

    private String modifierName;

    public Integer getQddId() {
        return qddId;
    }

    public void setQddId(Integer qddId) {
        this.qddId = qddId;
    }

    public Integer getQdId() {
        return qdId;
    }

    public void setQdId(Integer qdId) {
        this.qdId = qdId;
    }

    public String getQueryDimName() {
		return queryDimName;
	}

	public void setQueryDimName(String queryDimName) {
		this.queryDimName = queryDimName;
	}

    public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
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