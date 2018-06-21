package com.huatai.web.model;

public class TableTriggle {
    private Long qrtzGroupId;

    private String qrtzCode;

    private String tableCode;

    public Long getQrtzGroupId() {
        return qrtzGroupId;
    }

    public void setQrtzGroupId(Long qrtzGroupId) {
        this.qrtzGroupId = qrtzGroupId;
    }

    public String getQrtzCode() {
        return qrtzCode;
    }

    public void setQrtzCode(String qrtzCode) {
        this.qrtzCode = qrtzCode == null ? null : qrtzCode.trim();
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode == null ? null : tableCode.trim();
    }
}