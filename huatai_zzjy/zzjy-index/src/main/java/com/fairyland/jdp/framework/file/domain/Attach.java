package com.fairyland.jdp.framework.file.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Attach {
	private Long attachId;
	private String functionId;
	private String functionType;
	private String fileName;
	private String path;
	private String oldFileName;
	private String fileType;
	private String fileSize;
	private String createdBy;
	private String ownerName;
	private String modifiedBy;
	private Date createTime;
	private String createTimeStr;
	private Date modifyTime;
	private String modifyTimeStr;
	private String memoText;
	private String remarkAndAttachType;//附件或备注类型 1是备注 2是附件
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Long getAttachId() {
		return attachId;
	}
	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getFunctionType() {
		return functionType;
	}
	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
	public String getCreateTimeStr() {
		if (createTime != null) {
			createTimeStr = sdf.format(createTime);
		}
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getModifyTimeStr() {
		if (modifyTime != null) {
			modifyTimeStr = sdf.format(modifyTime);
		}
		return modifyTimeStr;
	}
	public void setModifyTimeStr(String modifyTimeStr) {
		this.modifyTimeStr = modifyTimeStr;
	}
	public String getMemoText() {
		return memoText;
	}
	public void setMemoText(String memoText) {
		this.memoText = memoText;
	}
	public String getRemarkAndAttachType() {
		return remarkAndAttachType;
	}
	public void setRemarkAndAttachType(String remarkAndAttachType) {
		this.remarkAndAttachType = remarkAndAttachType;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
}
