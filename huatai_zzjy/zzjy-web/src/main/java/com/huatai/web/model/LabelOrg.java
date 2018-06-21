package com.huatai.web.model;

import java.math.BigDecimal;

public class LabelOrg {
    private String orgCode;

    private String orgName;

    private String dateCode;

    private String lastGrade;

    private String grade;

    private BigDecimal monAveGrowthRate;

    private BigDecimal orgReachRate;

    private BigDecimal k2;

    private BigDecimal monAveConversionManpower;

    private BigDecimal monAve;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode == null ? null : dateCode.trim();
    }

    public String getLastGrade() {
        return lastGrade;
    }

    public void setLastGrade(String lastGrade) {
        this.lastGrade = lastGrade == null ? null : lastGrade.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public BigDecimal getMonAveGrowthRate() {
        return monAveGrowthRate;
    }

    public void setMonAveGrowthRate(BigDecimal monAveGrowthRate) {
        this.monAveGrowthRate = monAveGrowthRate;
    }

    public BigDecimal getOrgReachRate() {
        return orgReachRate;
    }

    public void setOrgReachRate(BigDecimal orgReachRate) {
        this.orgReachRate = orgReachRate;
    }

    public BigDecimal getK2() {
        return k2;
    }

    public void setK2(BigDecimal k2) {
        this.k2 = k2;
    }

    public BigDecimal getMonAveConversionManpower() {
        return monAveConversionManpower;
    }

    public void setMonAveConversionManpower(BigDecimal monAveConversionManpower) {
        this.monAveConversionManpower = monAveConversionManpower;
    }

    public BigDecimal getMonAve() {
        return monAve;
    }

    public void setMonAve(BigDecimal monAve) {
        this.monAve = monAve;
    }
}