package com.huatai.web.model;

public class TriggleExecuteKey {
    private Long qrtzGroupId;

    private String qrtzCode;

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
}