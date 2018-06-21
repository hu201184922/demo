<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>首页</title>
<link href='${ctx }/static/css/fullcalendar.css' rel='stylesheet' />
<link rel='stylesheet' href='${ctx }/static/css/jquery-ui.min.css' />
<script src='${ctx }/static/js/moment.min.js'></script>
<script src='${ctx }/static/js/fullcalendar.min.js'></script>
<%-- <script type="text/javascript" src="${ctx }/static/ezt/web/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${ctx }/static/ezt/web/js/highcharts/data.js"></script>
<script src="${ctx }/static/js/charts/FusionCharts.js"></script> --%>
 <script>
	Array.prototype.map=ArrayTmp.map;
	Array.prototype.filter=ArrayTmp.filter;
</script>
<script src="${ctx }/static/js/charts/echarts/echarts.min.js"></script>
<script src="${ctx }/static/js/charts/echarts/vintage.js"></script>
<%--  <script src="${ctx }/static/ezt/wechat/js/bigdata/echarts-all.js"></script> --%>
<style >

#ui-tooltip-0{word-break:keep-all;white-space:nowrap;padding:0;}
.ui-widget-content{ background:#fff;cursor:pointer;}
.fc th{height:30px; vertical-align:middle;color:#229fed;font-size:14px;font-weight:normal;font-family:"微软雅黑"; background:#ecf8ff;}
.fc-ltr .fc-basic-view .fc-day-number{font-size:14px;font-family:"微软雅黑";height:30px; line-height:30px; text-align:center;margin:0;padding:0;cursor:pointer;}
.fc-day-grid-container.fc-scroller{height: 340px !important;overflow:hidden !important;}

.fc-event{border:none; background:none;color:#666;}
.ui-state-highlight, .ui-widget-content .ui-state-highlight{border:1px solid #fedd97;color:#ff8a00; background:#fffbe3;}

.fc-event.planpoint{ background:url(${ctx }/static/images/renwu.png) no-repeat center center;}
.fc-event.specaildaypoint{background:url(${ctx }/static/images/shengri.png) no-repeat center center;}
.fc-event.renwu{background:url(${ctx }/static/images/renwu.png) no-repeat center center;}
.fc-day-grid-event{margin:0px 25px 0;}
</style>
<script type="text/javascript">
Ace.apply(Array,{
	  indexOf: function(obj){
	        if (Object.prototype.toString.call(obj) === '[object String]' || /^[]{0,1}[0-9]{0,}[.]{0,1}[0-9]{0,}$/.test(obj)) {
	            var re = new RegExp("," + obj + ",", [""]);
	            return ','.concat(this.toString()).concat(',').replace(re, "┢").replace(/[^,┢]/g, "").indexOf("┢");
	        }
	        else {
	            var flag = -1;
	           
	            jQuery.each(this, function(index){
	            	 
	            	if(jQuery.isPlainObject(this)){
		                if (flag < 0 && this == obj) {
		                    flag = index;
		                }
	            	}else{
	            		
		                if (flag < 0 && this == obj) {
		                    flag = index;
		                }
	            	}
	            });
	            return flag;
	        }
	    }
});
</script>
<script>
$(function(){
	/*  // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('newCustomerNum1'));

    // 指定图表的配置项和数据
    var option = {
   
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    }; */

    // 使用刚指定的配置项和数据显示图表。
   // myChart.setOption(option);
	 $('.tooltip-fixed a').click(function(){
			$(this).parent().hide();
			return false;
	 });
	 $("tr:odd").css('background','#f9f9f9');
	 
	 //待办事项
	 $('#pagerFromAjax').ajaxForm(function(data){
			$('#pager').pager().setPostData(Ace.parseQuery($('#pagerFromAjax').serialize()));
			$('#pager').pager().setJSON(data);
	 });
	 $('#pager').pager('${pageContext.request.contextPath}/getCurrDayPlan',5,$('#currDayPlanPagerView').view().on("add",function(temp){
			(function(zhis){
				
				if(temp.planType == '1'){
					zhis.target.find("#planType").html("客户");
					zhis.target.find("#toPersonPlanDetail").hide();
				}else{
					zhis.target.find("#planType").html("个人");
					zhis.target.find("#editCusplan").hide();
					zhis.target.find("#toCusPlanDetail").hide();
				}
				if(temp.status == 'N'){
					zhis.target.find("#status").html("未完成");
				}else if(temp.status == 'C'){
					zhis.target.find("#status").html("完成");
				}
				if(temp.remark !='' && temp.remark !=null){
					  var remarkval = temp.remark;
					  if(remarkval.length > 5){
						  zhis.target.find("#remark").html(remarkval.substr(0,4)+"..");
					  }else{
						  zhis.target.find("#remark").html(remarkval);
					  }
			    }
				if(temp.planType == '1'){
					zhis.target.find("#delplan").hide();
					zhis.target.find("#editPersonalPlan").hide();
					if(temp.status == 'C'){
						zhis.target.find("#editCusplan").hide();
					}
				}
			})(this);
	 }));
	 $('#pagerFromAjax').submit();
	 //currDayPlanPagerFromAjax();
	 var params = function(){
		var date = new Date();
		var year = date.getFullYear();
		var no = date.getMonth() + 1;
		var timeType = 1;
		return {year:year,no:no,timeType:timeType};
	 }
	 
	 $.ajax({
			url : "/monthData",
			type:"post",
			dataType : "json",
			data:params(),
			success : function(msg) {
				//console.log(msg);
				var targets = msg.targets;
				var anpSetup,anpComp,
				fycSetup,fycComp,
				polSetup,polComp,
				cusSetup,cusComp,
				opSetup,opComp,
				pcSetup,pcComp,
				pdSetup,pdComp,
				refSetup,refComp;
				
				$(targets).each(function(i,o){
					if(o.targetName=='ANP'){
						anpSetup=o.setUp;
						anpComp=o.comp;
					}
					if(o.targetName=='FYC'){
						fycSetup=o.setUp;
						fycComp=o.comp;
					}
					if(o.targetName=='POLNUM'){
						polSetup=o.setUp;
						polComp=o.comp;
					}
					if(o.targetName=='CUSNUM'){
						cusSetup=o.setUp;
						cusComp=o.comp;
					}
					if(o.targetName=='OP'){
						opSetup=o.setUp;
						opComp=o.comp;
					}
					if(o.targetName=='PC'){
						pcSetup=o.setUp;
						pcComp=o.comp;
					}
					if(o.targetName=='PD'){
						pdSetup=o.setUp;
						pdComp=o.comp;
					}
					if(o.targetName=='REF'){
						refSetup=o.setUp;
						refComp=o.comp;
					}
				});
				//echarts
			 	 var type1 = ['新增保费(元)','佣金(元)','件数(件)'];
				var chartContent1 = new Array();
				chartContent1.push([anpSetup.replace(/,/g,''),fycSetup.replace(/,/g,''),polSetup.replace(/,/g,'')]);
				chartContent1.push([anpComp.replace(/,/g,''),fycComp.replace(/,/g,''),polComp.replace(/,/g,'')]);
				createChart("newCustomerNum1","当月业绩目标",type1,chartContent1);
				
				 var type2 = ['新增客户(个) '];
				 var chartContent2 = new Array();
				 chartContent2.push([cusSetup.replace(/,/g,'')]);
				 chartContent2.push([cusComp.replace(/,/g,'')]);
				createChart("accomplishment1","当月客户目标",type2,chartContent2);
				
				var type3 = ['OP(件)','PC(件)','PD(件)','REF(件)'];
				var chartContent3 = new Array();
				chartContent3.push([opSetup.replace(/,/g,''),pcSetup.replace(/,/g,''),pdSetup.replace(/,/g,''),refSetup.replace(/,/g,'')]);
				chartContent3.push([opComp.replace(/,/g,''),pcComp.replace(/,/g,''),pdComp.replace(/,/g,''),refComp.replace(/,/g,'')]);
		
				createChart("planTask","当月核心工作量",type3,chartContent3);
				
				/* function createChart(tableId,tableName,type,chartContent){
				    var myChart = echarts.init(document.getElementById(tableId));
		              option = {
					    title : {
					        text: tableName,
					        x:'center'
					    },
					    tooltip : {
					    },
					    legend: {
					        data:[
					                    {
					                       name:'目标',
					                       textStyle:{fontWeight:'bold', color:'rgba(181,195,52,0.5)'},
					                   }, 
					                   {
					                       name:'达成',
					                       textStyle:{fontWeight:'bold', color:'rgba(252,206,16,0.5)'}
					                   }, 
					                   
					             
					           
					        ],
					    y: 'bottom',
					    borderColor: 'rgba(178,34,34,0.8)',
					    },
					    calculable : true,
					    grid: {y: 70, y2:50,x2:1},
					    xAxis : [
					        {
					            type : 'category',
					            data : ['新增保费(元)','佣金(元)','件数(件)']
					        },
					        {
					            type : 'category',
					            axisLine: {show:false},
					            axisTick: {show:false},
					            axisLabel: {show:false},
					            splitArea: {show:false},
					            splitLine: {show:false},
					            data : type
					           
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value',
					        }
					    ],
					    series : [
					        {
					            
					            type:'bar',
					            xAxisIndex:1,
					            itemStyle: {normal: {color:'#337AB7', label:{show:true}}},
					            data:chartContent[0]
					        },
					        {
					            
					            type:'bar',
					            xAxisIndex:1,
					            itemStyle: {normal: {color:'#F0AD4E', label:{show:true}}},
					            data:chartContent[1]
					        }
					    ]
					    
					    
					};
					         myChart.setOption(option); 
			
				}  */
				 function createChart(tableId,tableName,type,chartContent){
					var myChart = echarts.init(document.getElementById(tableId));
					option = {
							title : {
						        text: tableName,
						        x:'center'
						    },
							tooltip : {
						        trigger: 'axis',
						        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						        }
						    },
						    legend: {
						        data:['目标','达成'],
						        y: 'bottom',
						    },
						    grid: {
						        left: '3%',
						        right: '4%',
						        bottom: '10%',
						        containLabel: true
						    },
						    xAxis : [
						        {
						            type : 'category',
						            data : type
						        }
						    ],
						    yAxis : [
						        {
						            type : 'value'
						        }
						    ],
						    series : [
								{
									 name:'目标',
							            type:'bar',
							            itemStyle: {normal: {color:'#337AB7'}},
							            data:chartContent[0]
								},
								{
									name:'达成',
								    type:'bar',
								    itemStyle: {normal: {color:'#F0AD4E'}},
								    data:chartContent[1]
								}
						    ]
						};

					
					 myChart.setOption(option);
				}
				 
				/* var yAxisMaxValue3 = "${yAxisMaxValue2}";
				 var fourWeekTaskStr = '<chart caption="当月业绩目标" subcaption="" bgColor="FFFFFE,FFFFFF" showBorder="0" showValues="0" palette="2" shownames="1" useRoundEdges="1" legendBorderAlpha="0" ';
				
				 fourWeekTaskStr = fourWeekTaskStr+'baseFontSize="12" >'
				  + '<categories>'
				  + '<category label="新增保费(元)" />'
				  + '<category label="佣金(元)" />'
				  + '<category label="件数(件)" />'
				  + '</categories>'
				  + '<dataset seriesName="目标" color="8BBA00" >'
				  + '<set value="'+anpSetup.replace(/,/g,'')+'" />'
				  + '<set value="'+fycSetup.replace(/,/g,'')+'" />'
				  + '<set value="'+polSetup.replace(/,/g,'')+'" />'
				  + '</dataset>'
				  + '<dataset seriesName="达成" color="1E90FF" >'
				  + '<set value="'+anpComp.replace(/,/g,'')+'" />'
				  + '<set value="'+fycComp.replace(/,/g,'')+'" />'
				  + '<set value="'+polComp.replace(/,/g,'')+'" />'
				  + '</dataset>'
				  +'</chart>';
				 var achieveChart = new FusionCharts("${ctx}/static/js/charts/MSColumn2D.swf", "myChartId1", "285", "285");
				 achieveChart.setXMLData(fourWeekTaskStr);
				 achieveChart.render("newCustomerNum1");
				
				 var achieveStr = '<chart caption="当月客户目标" subcaption="" bgColor="FFFFFE,FFFFFF" showBorder="0" showValues="0" palette="2" shownames="1" useRoundEdges="1" legendBorderAlpha="0" ';
				
				 achieveStr = achieveStr+'baseFontSize="12">'
				  + '<categories>'
				  + '<category label="新增客户(个)" />'
				  + '</categories>'
				  + '<dataset seriesName="目标" color="8BBA00" >'
				  + '<set value="'+cusSetup.replace(/,/g,'')+'" />'
				  + '</dataset>'
				  + '<dataset seriesName="达成" color="1E90FF" >'
				  + '<set value="'+cusComp.replace(/,/g,'')+'" />'
				  + '</dataset>'
				  +'</chart>';
				 var achieveChart = new FusionCharts("${ctx}/static/js/charts/MSColumn2D.swf", "myChartId2", "285", "285");
				 achieveChart.setXMLData(achieveStr);
				 achieveChart.render("accomplishment1"); 
				 
				 
				 var chanceStr = '<chart caption="当月核心工作量" subcaption="" bgColor="FFFFFE,FFFFFF" showBorder="0" showValues="0" palette="2" shownames="1" useRoundEdges="1" legendBorderAlpha="0" ';
					 chanceStr = chanceStr+'baseFontSize="12" >'
					  + '<categories>'
					  + '<category label="OP(件)" />'
					  + '<category label="PC(件)" />'
					  + '<category label="PD(件)" />'
					  + '<category label="REF(件)" />'
					  + '</categories>'
					  + '<dataset seriesName="目标" color="8BBA00" >'
					  + '<set value="'+opSetup.replace(/,/g,'')+'" />'
					  + '<set value="'+pcSetup.replace(/,/g,'')+'" />'
					  + '<set value="'+pdSetup.replace(/,/g,'')+'" />'
					  + '<set value="'+refSetup.replace(/,/g,'')+'" />'
					  + '</dataset>'
					  + '<dataset seriesName="达成" color="1E90FF" >'
					  + '<set value="'+opComp.replace(/,/g,'')+'" />'
					  + '<set value="'+pcComp.replace(/,/g,'')+'" />'
					  + '<set value="'+pdComp.replace(/,/g,'')+'" />'
					  + '<set value="'+refComp.replace(/,/g,'')+'" />'
					  + '</dataset>'
					  +'</chart>';
					 var achieveChart = new FusionCharts("${ctx}/static/js/charts/MSColumn2D.swf", "myChartId3", "285", "285");
					 achieveChart.setXMLData(chanceStr);
					 achieveChart.render("planTask"); */
					 
					
				
				/* $("#datatable1 tbody").html("<tr>"
						+"<th>新增保费("+msg.userType+")</th>"
						+"<td>"+anpSetup.replace(/,/g,'')+"</td>"
						+"<td>"+anpComp.replace(/,/g,'')+"</td>"
						+" </tr>"
						+" <tr>"
						+"<th>佣金("+msg.userType+")</th>"
						+"<td>"+fycSetup.replace(/,/g,'')+"</td>"
						+" <td>"+fycComp.replace(/,/g,'')+"</td>"
						+"</tr>"
						+" <tr>"
						+" <th>件数(件)</th>"
						+" <td>"+polSetup.replace(/,/g,'')+"</td>"
						+" <td>"+polComp.replace(/,/g,'')+"</td>"
						+" </tr>");
				highCharts("#container1","datatable1","当月业绩目标");
				$("#datatable2 tbody").html("<tr>"
						+"<th>新增客户(个)</th>"
						+"<td>"+cusSetup.replace(/,/g,'')+"</td>"
						+"<td>"+cusComp.replace(/,/g,'')+"</td>"
						+" </tr>"
						);
				highCharts("#container2","datatable2","当月客户目标");
				$("#datatable3 tbody").html("<tr>"
						+"<th>OP(件)</th>"
						+"<td>"+opSetup.replace(/,/g,'')+"</td>"
						+"<td>"+opComp.replace(/,/g,'')+"</td>"
						+" </tr>"
						+" <tr>"
						+"<th>PC(件)</th>"
						+"<td>"+pcSetup.replace(/,/g,'')+"</td>"
						+" <td>"+pcComp.replace(/,/g,'')+"</td>"
						+"</tr>"
						+" <tr>"
						+" <th>PD(件)</th>"
						+" <td>"+pdSetup.replace(/,/g,'')+"</td>"
						+" <td>"+pdComp.replace(/,/g,'')+"</td>"
						+" </tr>"
						+"<th>REF(件)</th>"
						+"<td>"+refSetup.replace(/,/g,'')+"</td>"
						+" <td>"+refComp.replace(/,/g,'')+"</td>"
						+"</tr>");
				highCharts("#container3","datatable3","当月核心工作量"); */
			}
		}); 
	/*  var highCharts=function(spanId,tableId,title){
			
			Highcharts.setOptions({
			        colors: ['#058DC7','#FF9655']
			});
			$(spanId).highcharts({
				data: {
			        table: document.getElementById(tableId)
			    },
			    credits:{
			    	enabled:false
			    },
			    chart: {
			        type: 'column',
			        height: 400,
			        width:320
			    },
			    title: {
			        text: title
			    },
			    yAxis: {
			        title: {
			            text: ''
			        },
			        //min: 0.005
			    },
			    plotOptions : { 
		            series : {
		                minPointLength : 3
		            } 
		        },  
		        xAxis: {  
		        	//categories: []
		        }, 
			    tooltip: {
			        formatter: function() {
			            return this.y;
			        }
			    },
			   
			}); 
			} */
	
     //新增客户
    /* 
     var yAxisMaxValue3 = 0;
     var fourWeekTaskStr = '<chart caption="当月新增客户数(单位:个)" subcaption="" bgColor="FFFFFE,FFFFFF" showBorder="0" showValues="0" palette="2" shownames="1" useRoundEdges="1" legendBorderAlpha="0" ';
     if(yAxisMaxValue3 != 0){
     	fourWeekTaskStr = fourWeekTaskStr + ' yAxisMaxValue="' + yAxisMaxValue3 + '" ';
     }
     fourWeekTaskStr = fourWeekTaskStr+'animation="1" decimalPrecision="0" formatNumberScale="0" baseFont="Arial" baseFontSize="12" rotateYAxisName="0" showFCMenuItem="0" palettecolors="#1E90FF">'
			+'<set label="目标" color="8BBA00" value="'+${targetCusnumInfo.setUp}+'"/>'
			+'<set label="达成" color="1E90FF" value="'+${targetCusnumInfo.comp}+'"/>'
			+'</chart>';
     var chart3 = new FusionCharts("${ctx}/static/js/charts/Column2D.swf", "myChartId", "285", "285");
     chart3.setXMLData(fourWeekTaskStr);
     chart3.render("newCustomerNum"); */
        
     //业绩达成-ANP、FYC
	 /* var yAxisMaxValue2 = "${yAxisMaxValue2}";
	 var achieveStr = '<chart caption="当月业绩达成数据(单位:万元)" subcaption="" bgColor="FFFFFE,FFFFFF" showBorder="0" showValues="0" palette="2" shownames="1" useRoundEdges="1" legendBorderAlpha="0" ';
	 /*if(yAxisMaxValue1 == 5){
		 achieveStr = achieveStr + ' yAxisMaxValue="' + yAxisMaxValue2 + '" ';
	 } 
	 achieveStr = achieveStr+'animation="1" decimalPrecision="0" formatNumberScale="0" baseFont="Arial" baseFontSize="12" rotateYAxisName="0" showFCMenuItem="0" palettecolors="1E90FF" showLegend="0">'
	  + '<categories>'
	  + '<category label="月度新增保费" />'
	  + '<category label="月度新增佣金" />'
	  + '</categories>'
	  + '<dataset seriesName="目标" color="8BBA00" showValues="0">'
	  + '<set value="'+${targetAnpInfo.setUp}+'" />'
	  + '<set value="'+${targetFycInfo.setUp}+'" />'
	  + '</dataset>'
	  + '<dataset seriesName="达成" color="1E90FF" showValues="0">'
	  + '<set value="'+${targetAnpInfo.comp}+'" />'
	  + '<set value="'+${targetFycInfo.comp}+'" />'
	  + '</dataset>'
	  +'</chart>';
	 var achieveChart = new FusionCharts("${ctx}/static/js/charts/MSColumn2D.swf", "myChartId2", "285", "285");
	 achieveChart.setXMLData(achieveStr);
	 achieveChart.render("accomplishment1"); */
	 
     //业绩达成-Num
	/*  var achieveStr = '<chart caption="当月业绩达成数据(单位:件)" subcaption="" bgColor="FFFFFE,FFFFFF" showBorder="0" showValues="0" palette="2" shownames="1" useRoundEdges="1" legendBorderAlpha="0" ';
	 /*if(yAxisMaxValue1 == 5){
		 achieveStr = achieveStr + ' yAxisMaxValue="' + yAxisMaxValue2 + '" ';
	 } 
	 achieveStr = achieveStr+'animation="1" decimalPrecision="0" formatNumberScale="0" baseFont="Arial" baseFontSize="12" rotateYAxisName="0" showFCMenuItem="0" palettecolors="1E90FF" showLegend="0">'
	  + '<set label="目标件数" color="8BBA00" value="'+${targetPolnumInfo.setUp}+'" />'
	  + '<set label="达成件数" color="1E90FF" value="'+${targetPolnumInfo.comp}+'" />'
	  +'</chart>';
	 var achieveChart = new FusionCharts("${ctx}/static/js/charts/Column2D.swf", "myChartId3", "285", "285");
	 achieveChart.setXMLData(achieveStr);
	 achieveChart.render("accomplishment2"); */
     
     //活动量
	/*  var yAxisMaxValue1 = "${yAxisMaxValue1}";
	 var chanceStr = '<chart caption="当月任务完成数据(单位:件)" subcaption="" bgColor="FFFFFE,FFFFFF" showBorder="0" showValues="0" palette="2" shownames="1" useRoundEdges="1" legendBorderAlpha="0" ';
	 if(yAxisMaxValue1 == 5){
			chanceStr = chanceStr + ' yAxisMaxValue="' + yAxisMaxValue1 + '" ';
	 }
	 chanceStr = chanceStr+'animation="1" decimalPrecision="0" formatNumberScale="0" baseFont="Arial" baseFontSize="12" rotateYAxisName="0" showFCMenuItem="0" palettecolors="1E90FF">'
			+'<set label="OP" value="'+${targetOPInfo.comp}+'"/>'
			+'<set label="PC" value="'+${targetPCInfo.comp}+'"/>'
			+'<set label="PD" value="'+${targetPDInfo.comp}+'"/>'
			+'</chart>';
	 var chart = new FusionCharts("${ctx}/static/js/charts/Column2D.swf", "myChartId4", "285", "285");
     chart.setXMLData(chanceStr);
     chart.render("planTask"); */
     
     //业绩目标达成 AnpFyc/Num切换
    /*  $("#AnpFyc").click(function(){
    	 $("#accomplishment1").css("display","block");
    	 $("#accomplishment2").css("display","none");
    	 $("#AnpFyc").removeClass("tabstyle").addClass("choosetab");
    	 $("#Num").removeClass("choosetab").addClass("tabstyle");
     });
     $("#Num").click(function(){
    	 $("#accomplishment1").css("display","none");
    	 $("#accomplishment2").css("display","block");
    	 $("#Num").removeClass("tabstyle").addClass("choosetab");
    	 $("#AnpFyc").removeClass("choosetab").addClass("tabstyle");
     }); */
     
});
Date.prototype.format = function(format)
{
	var o = {
	"M+" : this.getMonth()+1, //month
	"d+" : this.getDate(),    //day
	"h+" : this.getHours(),   //hour
	"m+" : this.getMinutes(), //minute
	"s+" : this.getSeconds(), //second
	"q+" : Math.floor((this.getMonth()+3)/3),  //quarter
	"S" : this.getMilliseconds() //millisecond
	}
	if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
	(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)if(new RegExp("("+ k +")").test(format))
	format = format.replace(RegExp.$1,
	RegExp.$1.length==1 ? o[k] :
	("00"+ o[k]).substr((""+ o[k]).length));
	return format;
} 
$(document).ready(function(){
	var eventJson = ${eventJson};
	$("#calendar").fullCalendar({
     	theme: true,
     	header: false,
			defaultDate: "${dataStr}",
			monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
			dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
			editable: false,
			eventLimit: false,
			dayClick: function(date){
				
				var  currentDate = date._d.format('yyyy-MM-dd');
				/* console.log(date._i);
				console.log(currentDate); */
				
				$('.fc-day').each(function(i,o){
					
					if(currentDate!=date._i){
						$(this).removeClass('ui-state-highlight');
					}else{
						$(this).removeClass('ui-state-highlight');
					}
				});
				$(this).addClass('ui-state-highlight');
				
				/* $('.fc-day').each(function(){	
					//debugger;
			   	var data = $(this).attr("data-date"),
			   		_date = new Date(data.replace(/-/g,'/'));
				var currentClass = $(this).attr("class"); 
				//if(_date.getYear() === date.getYear()  )
				//回显今天日期
				if(data == date){
					$(this).addClass(currentClass+"ui-state-highlight");
				}
				}); */
				$("#indexTask").empty();
				$("#indexTask").load("${ctx}/indexTask?date="+date);
			},
			events:eventJson
     });
	
});

function delPersonPlan(o){
	$.confirm("是否确定删除？", function() {
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/plan/personplan/deletePersonPlanById",
			data : {personPlanId:o},
			success : function(msg) {
				$("#pager").pager().reload();
				$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
		    },
		    error:function(){
		    	$.msgbox({time: 2000,msg: "删除失败!",icon:"error"});
		    }
		});
	});

}	
</script>
</head>
<body>
<div>
  <ol class="breadcrumb">
  	  <li>
  	      <a href="${pageContext.request.contextPath}/index"><span class="glyphicon glyphicon-map-marker"></span>首页</a>
  	  </li>
  </ol>
  
  <h4 <c:if test="${currentUser.isType=='01' }">style="display:none"</c:if>>待处理事务</h4>
  <div class="panel" <c:if test="${currentUser.isType=='01' }">style="display:none"</c:if>>
 	  <form id="pagerFromAjax" action="${pageContext.request.contextPath}/getCurrDayPlan" method="post" >
	      <div class="table-responsive">
	         <table class="table table-hover table-striped" id="currDayPlanPagerView">
	             <thead>
	                <tr>
	                    <th>计划类型</th>
	                    <th>计划名</th>
	                    <th>任务类型</th>
	                    <th>客户</th>
	                    <th>开始时间</th>
	                    <th>结束时间</th>
	                    <th>状态</th>
	                    <th>备注</th>
	                    <th>操作</th>
	                </tr>
	             </thead>
	             <tbody>
	               <tr class="template" name="default">
	                    <td><span id="planType"></span></td>
	                    <td>
		                    <a id="toPersonPlanDetail" href="${pageContext.request.contextPath}/plan/personplan/updatePersonPlanPager?personPlanId={planId}">{planName}</a>
		                    <a id="toCusPlanDetail" href="${pageContext.request.contextPath}/customer/plan/edit?cusId={cusId}&cusPlanId={planId}&taskId={taskId}&from=3&back=1">{planName}</a>
	                    </td>
	                    <td>{taskStage}</td>
	                    <td>{cusName}</td>
	                    <td>{beginTime}</td>
	                    <td>{endTime}</td>
	                    <td><span id="status"></span></td>
	                    <td><span id="remark" title="{remark}"></span></td>
	                    <td>
		                    <button style="margin-top:3px;" type="button" id="editPersonalPlan" class="btn btn-xs btn-info"
		                    onclick="javascript:window.location.href='${pageContext.request.contextPath}/plan/personplan/updatePersonPlanPager?personPlanId={planId}';">编辑</button>
		                    <button style="margin-top:3px;" type="button" id="editCusplan" class="btn btn-xs btn-info"
		                    onclick="javascript:window.location.href='${pageContext.request.contextPath}/customer/plan/edit?cusId={cusId}&cusPlanId={planId}&taskId={taskId}&from=3';">编辑</button>
		                    <button style="margin-top:3px;" type="button" id="delplan" class="btn btn-xs btn-danger" 
		                    onclick="javascript:delPersonPlan('{planId}');">删除</button>
		                </td>
	                </tr>
	                
	             </tbody>
	         </table>
	         <div id="pager"></div>
	      </div>
      </form>
  </div>
  
  <h4>行事历</h4><a style="background:url(${pageContext.request.contextPath}/static/images/renwu.png) no-repeat center center;"></a>
  <div class="panel" style="height:470px;">
     <div class="panel-body" style="height:460px;">
            <div class="row" style="height:458px;">
                <div class="col-xs-6" style="height:455px;">
                    <div class="calendar-title" style="height:35px;line-height:35px;width:455px;">
                        <span>
                            <a href="${pageContext.request.contextPath}/calendar/calendarList">月视图</a>
                        </span>
                        <span><a href="${pageContext.request.contextPath}/calendar/calendarList?type=agendaWeek">周视图</a></span>
                    </div>
                    <div id="calendar" ></div>
                </div>
                <div id="indexTask"></div>
            </div>
        </div>
  </div>

  <style>
     .choosetab{border-bottom: 2px solid #5bc0de;color:#5bc0de;}
     .tabstyle{border-bottom: 2px solid #ddd;}
  </style>
  <h4>月度数据</h4>
	<!-- <table id="datatable1" style="margin-left:20px;display:none">
    <thead>
      <tr>
        <th></th>
        <th>目标</th>
        <th>达成</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
	</table>
	<table id="datatable2" style="margin-left:20px;display:none">
    <thead>
      <tr>
        <th></th>
        <th>目标</th>
        <th>达成</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
	</table>
	<table id="datatable3" style="margin-left:20px;display:none">
    <thead>
      <tr>
        <th></th>
        <th>目标</th>
        <th>达成</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
	</table>

    <div id="containers">
	    <div style="width:33%;float:left;height:500px;overflow:hidden;" id="container1" ></div>
	    <div style="width:33%;float:left;height:500px;overflow:hidden;" id="container2" ></div>
	    <div style="width:33%;float:left;height:500px;overflow:hidden;" id="container3" ></div>
	    
    </div> -->

     <div class="panel">
         <div class="panel-body">
             <div class="row">
                 <div class="col-xs-4">
                     <!-- <h4>新增客户</h4>
                     <div style="float:left;line-height:40px;">
                         <span id="AnpFyc" style="cursor:pointer;margin-left:18px;" class="choosetab">新增保费(元)</span>&nbsp;
                         <span id="Num" style="margin-left:5px;cursor:pointer;" class="tabstyle">佣金</span>
                     </div> -->
                     <div >
			             <div class="data" id="newCustomerNum1"></div>
                     </div>
                 </div>
                 <div class="col-xs-4">
                     <!-- <h4 style="float:left">业绩达成</h4>
                     <div style="float:left;line-height:40px;">
                         <span id="AnpFyc" style="cursor:pointer;margin-left:18px;" class="choosetab">新增保费和佣金</span>&nbsp;
                         <span id="Num" style="margin-left:5px;cursor:pointer;" class="tabstyle">件数</span>
                     </div> -->
                     <div >
			             <div class="data" id="accomplishment1"></div>
                     </div>
                 </div>
                 <div class="col-xs-4">
                     <!-- <h4>活动量</h4> -->
                     <div class="data" id="planTask" ></div>
                 </div>
             </div>
         </div>
     </div> 
</div>


<div class="tooltip-fixed" id="remindDiv" style="display:none;">
   <c:if test="${specailDayInfoNum eq 1 }">
    <p style="padding:0px;!important;width:170px;text-align: left;font-size:12px;">纪念日提醒:</p>
       <c:forEach var="specailDayInfo" items="${specailDayInfoList}">
	       <p style="padding:0px;!important;width:190px;font-size:12px;">${specailDayInfo.name}的
	       <c:if test="${specailDayInfo.type eq 'B' }">生日</c:if>
	       <c:if test="${specailDayInfo.type eq 'M' }">${specailDayInfo.specialDesc}</c:if>(${specailDayInfo.specialDay})</p>
       </c:forEach>
   </c:if>
    <button type="button" id="searchMoreRemind" class="btn btn-sm btn-info">点击查看更多</button>
    <a class="glyphicon glyphicon-remove-circle"> </a>
</div> 
<c:if test="${isShow }"><input type="hidden" value="1" id="isShow"/></c:if>
<c:if test="${!isShow }"><input type="hidden" value="2" id="isShow"/></c:if>

<script>
$(function(){
	$("#indexTask").empty();
	$("#indexTask").load("${ctx}/indexTask");
	//console.log($("#isShow").val());
	if($("#isShow").val()==1){
		$("#remindDiv").show();
	}else{
		$("#remindDiv").hide();
	}
	$("#searchMoreRemind").click(function(){
		window.location.href="${ctx}/remind/index?type=4";
	});
});
</script>
</body>
</html>