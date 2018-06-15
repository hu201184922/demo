package com.xinwei.model;

public class UserInfo implements java.io.Serializable{

    /**
	 */
	private static final long serialVersionUID = 1L;

    private String batchNo;

    public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEqp() {
		return eqp;
	}

	public void setEqp(String eqp) {
		this.eqp = eqp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	private String tranTime;
     
    private String system;

    private String data;

    private String eqp;
    
    private String ip;
    
    private String tranDate;
 
    private String equipmentType;

}