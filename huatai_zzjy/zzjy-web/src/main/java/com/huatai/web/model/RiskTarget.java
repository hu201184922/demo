package com.huatai.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RiskTarget implements Serializable{

	private static final long serialVersionUID = 1L;

	private String orgCode;

    private String orgName;

    private String dateCode;

    private BigDecimal stadPrem;

    private BigDecimal receStadPrem;

    private BigDecimal valuePrem;

    private BigDecimal recePreGoldBeeNum;

    private BigDecimal preGoldBeeNum;

    private BigDecimal effNum;

    private BigDecimal receEffNum;

    private BigDecimal netGrowthNum;

    private String gradetypecode;

    private BigDecimal morningAttendance;

    private BigDecimal addManpow;

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

    public BigDecimal getStadPrem() {
        return stadPrem;
    }

    public void setStadPrem(BigDecimal stadPrem) {
        this.stadPrem = stadPrem;
    }

    public BigDecimal getReceStadPrem() {
        return receStadPrem;
    }

    public void setReceStadPrem(BigDecimal receStadPrem) {
        this.receStadPrem = receStadPrem;
    }

    public BigDecimal getValuePrem() {
        return valuePrem;
    }

    public void setValuePrem(BigDecimal valuePrem) {
        this.valuePrem = valuePrem;
    }

    public BigDecimal getRecePreGoldBeeNum() {
        return recePreGoldBeeNum;
    }

    public void setRecePreGoldBeeNum(BigDecimal recePreGoldBeeNum) {
        this.recePreGoldBeeNum = recePreGoldBeeNum;
    }

    public BigDecimal getPreGoldBeeNum() {
        return preGoldBeeNum;
    }

    public void setPreGoldBeeNum(BigDecimal preGoldBeeNum) {
        this.preGoldBeeNum = preGoldBeeNum;
    }

    public BigDecimal getEffNum() {
        return effNum;
    }

    public void setEffNum(BigDecimal effNum) {
        this.effNum = effNum;
    }

    public BigDecimal getReceEffNum() {
        return receEffNum;
    }

    public void setReceEffNum(BigDecimal receEffNum) {
        this.receEffNum = receEffNum;
    }

    public BigDecimal getNetGrowthNum() {
        return netGrowthNum;
    }

    public void setNetGrowthNum(BigDecimal netGrowthNum) {
        this.netGrowthNum = netGrowthNum;
    }

    public String getGradetypecode() {
        return gradetypecode;
    }

    public void setGradetypecode(String gradetypecode) {
        this.gradetypecode = gradetypecode == null ? null : gradetypecode.trim();
    }

    public BigDecimal getMorningAttendance() {
        return morningAttendance;
    }

    public void setMorningAttendance(BigDecimal morningAttendance) {
        this.morningAttendance = morningAttendance;
    }

    public BigDecimal getAddManpow() {
        return addManpow;
    }

    public void setAddManpow(BigDecimal addManpow) {
        this.addManpow = addManpow;
    }
}