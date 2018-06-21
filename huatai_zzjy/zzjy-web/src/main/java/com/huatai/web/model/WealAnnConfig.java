package com.huatai.web.model;

import java.util.Date;

public class WealAnnConfig {
    private String groupOrderNumber;

    private Date startDate;

    private Date endDate;

    private String flag;

    public String getGroupOrderNumber() {
        return groupOrderNumber;
    }

    public void setGroupOrderNumber(String groupOrderNumber) {
        this.groupOrderNumber = groupOrderNumber == null ? null : groupOrderNumber.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}