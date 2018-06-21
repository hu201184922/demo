package com.huatai.web.bean;

public class ModuleBean {
	private String name; //模块名称
	
	private String pName;//所属菜单
	
	private Integer visitNum; //访问次数
	
	private Double vnRatio; //访问次数占比
	
	private Integer visitPeo;//访问人数
	
	private Double vpRatio;//访问人数占比
	
	private Double stayTime;//停留时间
	
	private Double stRatio;//停留时间占比
	
	private Double outRatio;//跳出率占比
	
	private Double convertRatio;//转化率

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Integer getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public Double getVnRatio() {
		return vnRatio;
	}

	public void setVnRatio(Double vnRatio) {
		this.vnRatio = vnRatio;
	}

	public Integer getVisitPeo() {
		return visitPeo;
	}

	public void setVisitPeo(Integer visitPeo) {
		this.visitPeo = visitPeo;
	}

	public Double getVpRatio() {
		return vpRatio;
	}

	public void setVpRatio(Double vpRatio) {
		this.vpRatio = vpRatio;
	}

	public Double getStayTime() {
		return stayTime;
	}

	public void setStayTime(Double stayTime) {
		this.stayTime = stayTime;
	}

	public Double getStRatio() {
		return stRatio;
	}

	public void setStRatio(Double stRatio) {
		this.stRatio = stRatio;
	}

	public Double getOutRatio() {
		return outRatio;
	}

	public void setOutRatio(Double outRatio) {
		this.outRatio = outRatio;
	}

	public Double getConvertRatio() {
		return convertRatio;
	}

	public void setConvertRatio(Double convertRatio) {
		this.convertRatio = convertRatio;
	}

	
}
	
	
	
