package com.huatai.web.utils;
import java.util.ArrayList;
import java.util.List;

import com.huatai.web.model.Templet;

public class Constants {
	public static final String GraphType1 = "column";
	public static final String GraphType2 = "stacking";
	public static final String GraphType3 = "no";
	public static final String GraphType4 = "line";
	public static final String GraphType5 = "scatter";
	public static final String GraphType6 = "pie";
	public static final String GraphType7 = "table";
	
	// 上传提示信息
	public static final String upMsgSUCCESS = "上传成功";
	public static final String exMsgSUCCESS = "导入成功";
	public static final String pUpExFile = "请上传EXCEL格式文件";
	public static final String noUpCode = "导入编号不存在";
	public static final String noDeptCodeMsg = "机构编号列不能为空，请重新完善EXCEL后重新上传";
	public static final String noDeptNameMsg = "机构名称列不能为空，请重新完善EXCEL后重新上传";
	public static final String noDateCodeMsg = "日期列不能为空，请重新完善EXCEL后重新上传";
	public static final String noPremFeeMsg = "标准保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noPremValueMsg = "价值保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noAgentPremFeeMsg = "代理人承保标准保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noAgentSLFeeMsg = "代理人受理标准保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noAgentPremValueMsg = "代理人价值保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noCBPremBeeFeeMsg = "承保准金蜂人力列不能为空，请重新完善EXCEL后重新上传";
	public static final String noPremBeeFeeMsg = "受理准金蜂人力列不能为空，请重新完善EXCEL后重新上传";
	public static final String noCBPremYXMsg = "承保有效人力列不能为空，请重新完善EXCEL后重新上传";
	public static final String noSLPremYXMsg = "受理有效人力列不能为空，请重新完善EXCEL后重新上传";
	public static final String noJZNumMsg = "净增人力列不能为空，请重新完善EXCEL后重新上传";
	public static final String noFZLXMsg = "分组类型列不能为空，请重新完善EXCEL后重新上传";
	public static final String noCJNumMsg = "参加早会的人力列不能为空，请重新完善EXCEL后重新上传";
	public static final String noXZNumMsg = "新增人力列不能为空，请重新完善EXCEL后重新上传";
	public static final String noXQCBMsg = "续期新单承保标准保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noXQSLMsg = "续期新单受理标准保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noXQValueMsg = "续期新单价值保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noSGPYMsg = "上岗新人月平移率列不能为空，请重新完善EXCEL后重新上传";
	public static final String noSGPCYMsg = "上岗新人次月平移率列不能为空，请重新完善EXCEL后重新上传";
	public static final String noDYSGXRSYRHRSMsg = "当月上岗新人首月入会人数列不能为空，请重新完善EXCEL后重新上传";
	public static final String noDYSGXRDYCBBBMsg = "当月上岗新人当月承保标保列不能为空，请重新完善EXCEL后重新上传";
	public static final String noCYXZRSMsg = "成员新增人数列不能为空，请重新完善EXCEL后重新上传";
	public static final String noYPYXRSMsg = "应平移新人数列不能为空，请重新完善EXCEL后重新上传";
	public static final String noSJPYXRSMsg = "实际平移新人数列不能为空，请重新完善EXCEL后重新上传";
	public static final String noSGXRSYRHLMsg = "上岗新人首月入会率列不能为空，请重新完善EXCEL后重新上传";
	public static final String noXQSSBFMsg = "续期实收保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noCBBZBFMsg = "承保标准保费列不能为空，请重新完善EXCEL后重新上传";
	public static final String noQNGXGradeMsg = "去年个险评级列不能为空，请重新完善EXCEL后重新上传";
	public static final String noJNGXGradeMsg = "今年个险评级列不能为空，请重新完善EXCEL后重新上传";
	public static final String noYJBBZZLMsg = "月均标保增长率列不能为空，请重新完善EXCEL后重新上传";
	public static final String noJGDBLMsg = "机构达标率列不能为空，请重新完善EXCEL后重新上传";
	public static final String noK2Msg = "K2列不能为空，请重新完善EXCEL后重新上传";
	public static final String noYJZSYXRLMsg = "月均折算有效人力列不能为空，请重新完善EXCEL后重新上传";
	public static final String noYJBBMsg = "月均标保列不能为空，请重新完善EXCEL后重新上传";
	
	public static List<Templet> getTemplate(){
		List<Templet> list=new ArrayList<>();
		/*Templet t0=new Templet();
		t0.setTempCode("TEMP12");
		t0.setTempName("APP分析管理模板");
		t0.setTempId(20);
		t0.setTempLink("");
		list.add(t0);*/
		Templet t1=new Templet();
		t1.setTempCode("TEMP06");
		t1.setTempName("机构对比模板");
		t1.setTempId(19);
		t1.setTempLink("Jgdb");
		list.add(t1);
		Templet t2=new Templet();
		t2.setTempCode("TEMP07");
		t2.setTempName("导出清单模板");
		t2.setTempId(18);
		t2.setTempLink("Dcqd");
		list.add(t2);
		Templet t3=new Templet();
		t3.setTempCode("TEMP11");
		t3.setTempName("经营信息模板");
		t3.setTempId(17);
		t3.setTempLink("Jyfx");
		list.add(t3);
		Templet t4=new Templet();
		t4.setTempCode("DAS01");
		t4.setTempName("营销分析达时报模板");
		t4.setTempId(15);
		t4.setTempLink("Zdsb");
		list.add(t4);
		Templet t5=new Templet();
		t5.setTempCode("TEMP04");
		t5.setTempName("板块模板");
		t5.setTempId(14);
		t5.setTempLink("BkfwByReg");
		list.add(t5);
		Templet t6=new Templet();
		t6.setTempCode("TEMP03");
		t6.setTempName("分析管理模板");
		t6.setTempId(13);
		t6.setTempLink("Fxgl");
		list.add(t6);
		Templet t7=new Templet();
		t7.setTempCode("TEMP02");
		t7.setTempName("固定指标模板");
		t7.setTempId(12);
		t7.setTempLink("Gdzb");
		list.add(t7);
		
		//独立区域
		/*Templet t8=new Templet();
		t8.setTempCode("TEMP12");
		t8.setTempName("虚拟区域");
		t8.setTempId(20);
		t8.setTempLink("Virt");
		list.add(t8);
		Templet t9=new Templet();
		t9.setTempCode("TEMP13");
		t9.setTempName("预警监控");
		t9.setTempId(21);
		t9.setTempLink("Yjjk");
		list.add(t9);*/
		return list;
	}
}
