package com.huatai.web.model;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

public class WarnResult {
    private Integer wrId;

    private Integer bsId;

    private String username;
    
    private String targetName;

    private String roleCode;

    private Float warnVal;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date firstWarnTime;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date warnTime;

    private String isRead;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date readTime;

	private String targetCode;

	private String orgCode;

	private String warnStatus;

	private UserSetWarn userSetWarn;
	
    public Integer getWrId() {
        return wrId;
    }

    public void setWrId(Integer wrId) {
        this.wrId = wrId;
    }

    public Integer getBsId() {
        return bsId;
    }

    public void setBsId(Integer bsId) {
        this.bsId = bsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    
    public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public Float getWarnVal() {
        return warnVal;
    }

    public void setWarnVal(Float warnVal) {
        this.warnVal = warnVal;
    }

    public Date getFirstWarnTime() {
        return firstWarnTime;
    }

    public void setFirstWarnTime(Date firstWarnTime) {
        this.firstWarnTime = firstWarnTime;
    }

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead == null ? null : isRead.trim();
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getWarnStatus() {
		return warnStatus;
	}

	public void setWarnStatus(String warnStatus) {
		this.warnStatus = warnStatus;
	}

	public UserSetWarn getUserSetWarn() {
		return userSetWarn;
	}

	public void setUserSetWarn(UserSetWarn userSetWarn) {
		this.userSetWarn = userSetWarn;
	}
}