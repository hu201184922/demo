<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<head>
<title>用户行为分析</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/hchart/highstock.src.js"></script>

<style>
.hq_hy:hover, .hq_zsh:hover, .hq_hb:hover, .hq_jyyc:hover, .hq_byb:hover, .hq_lrcl:hover
　　/*鼠标移上去变色（不点击）*/
        {
            color: #fff;
            border-color: #b1b0b0;
            background: #b1b0b0;
            border: none;
        }
        .start
        {
            cursor: pointer;
        }
        .end
        {
            cursor: pointer;
            color: #fff;
            background: #b1b0b0;
            border: none;
        }
        .but
        {
        	padding: 5px 10px;
        	border-radius: 5px;
        	border:1px solid #90C1E6;
        }
        dl{
		    float: left;
        	margin-right: 50px;
    		width: 21%;
        	background-color:#ccc;
        }
        .dl{
        	margin-top: -60px;
    		margin-left: 100px;
        	width:150px;
        	background-color:initial;
        }
        dt{
        	text-align: center;
        	padding: 10px 20px;
        	background-color:#ccc;
        }
        dl dt:first-child{
        	background-color:#efefef;
        }
        dl dt:last-child{
        	background-color:#a0a0a0;
        }
        .User_left_div div{
        	/* width: 300px;
            margin: 20px 0px;
        	font-size:40px;
        	border:1px solid #ccc;
        	background-color:#e4e1e1;
        	height: 69px; */
        	text-align: -webkit-center;
			font-size:35px;
        	border:1px solid #ccc;
        	background-color:#e4e1e1;
        	height: 90px;
        	width: 95%;
        	margin: 20px 0px;
		 }
        .User_left_div{
        	width: 280px;
       	    height: 350px;
        }
</style>
<script type="text/javascript">
var Highcharts = Highstock;
function userModu(){
    $(function () {
    	var categories = [];
       	var data = [];
       	$.getJSON('${pageContext.request.contextPath}/admin/userBehInfo/getAccUserInfo',{dateDim:$("#dateDim").val()},function(data){
       		data.accUserInfo[0]["innerSize"]="80%";
          $('#pie').highcharts({
               chart: {
                   plotBackgroundColor: null,
                   plotBorderWidth: null,
                   plotShadow: false,
                   spacing : [100, 0 , 40, 0]
               },
               credits: {
               	enabled:false
               },
               title: {
                   floating:true,
                   text: '圆心显示的标题'
               },
               legend:{
               	enabled:true
               },
               tooltip: {
                   pointFormat: '<b>{point.name}: {point.y}({point.percentage:.1f}%)</b>'
               },
               plotOptions: {
                   pie: {
                       allowPointSelect: true,
                       cursor: 'pointer',
                       dataLabels: {
                           enabled: true,
                           format: '<b>{point.name}: {point.percentage:.1f} %</b>',
                           style: {
                               color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                           }
                       },
                       point: {
                           events: {
                               /* mouseOver: function(e) {  // 鼠标滑过时动态更新标题
                                   // 标题更新函数，API 地址：https://api.hcharts.cn/highcharts#Chart.setTitle
                               	chart.setTitle({
                                       text: e.point.name+ '\t'+ e.point.y + ' %'
                                   });
                               } */
                               click: function(e) { // 同样的可以在点击事件里处理
                                    chart.setTitle({
                                        text: e.point.name+ '\t'+ e.point.y + ' 人'
                                    });
                               }
                           }
                       },
                   }
               },
               series: data.accUserInfo,
	           }, function(c) {
	               // 环形图圆心
	               var centerY = c.series[0].center[1],
                   titleHeight = parseInt(c.title.styles.fontSize);
	               c.setTitle({
	                   y:centerY + titleHeight/2
	               }); 
	               chart = c;
	           });
	       	});	
	      
	    });
	    $(function () {
	        $.getJSON('${pageContext.request.contextPath}/admin/userBehInfo/getDateRegionInfo',{dateDim:$("#dateDim").val()},function(data){
	        	var categoriesData=[];
	        	var countData=[];
	        	if(data.length!=0){
		        	$.each(data,function(i,v){
		        		categoriesData.push(v.userName);
		        		countData.push(parseInt(v.behaviorid));
		        		
		        	})
	        		/* if(key!="success"){
		        		categoriesData.push(key);
		        		countData.push(parseInt(data[key]));
	        		} */
	        	}
	        	var dataList=data;
			    var chart = new Highcharts.Chart('dateLine', {
			    	chart:{
			    		hieght:440	
			    	},
			    	credits: {
			        	enabled:false
			        },
			        title: {
			            text: '使用时段',
			            x: -20
			        },
			        xAxis: {
			            categories: categoriesData
			        },
			        yAxis: {
			            title: {
			                text: '使用时段'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: ' 位'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: '用户数',
			            data: countData
			        }]
			    });
	        })
	    })
	    
	    $(function () {
	        $.getJSON('${pageContext.request.contextPath}/admin/userBehInfo/getHieUserInfo',{dateDim:$("#dateDim").val()},function(data){
		         Highcharts.StockChart('pie2',{
		       	 chart: {
		               width:420
		            },
		            credits: {
		            	enabled:false
		            },
		            title: {
		                text: ''
		            },
		            tooltip: {
		                headerFormat: '{series.name}<br>',
		                pointFormat: '<b>{point.name}: {point.y} People({point.percentage:.1f}%)</b>'
		            },
		            navigator:{
		            	enabled:false
		            },
		            xAxis:{
		           	 categories:null,
		           	 type:'category'
		            },
		            series: data.hieUserInfo
		       });
	        });
	    });
	    /* 版本统计 */
	    $(function () {
	    	$("#versionName").children().remove();
	    	$("#versionDate").children().remove();
	        $.getJSON('${pageContext.request.contextPath}/admin/userBehInfo/getVersionList',{type:'0'},function(data){
	        	var strVersionCode="",strVersionId="";
	        	$.each(data,function(i,v){
	        		if(data.length>5){
	        			if(i>=data.length-5){
	        				strVersionCode+="<span style='width: 60px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>"+v.versionCode+"</span>";
	        				strVersionId+="<span style='width: 60px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>"+v.versionId+"</span>";
	        			}
	        		}else{
	        			strVersionCode+="<span style='width: 60px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>"+v.versionCode+"</span>";
		        		strVersionId+="<span style='width: 60px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>"+v.versionId+"</span>";
	        			}
	        		})
	        	$("#versionName").append(strVersionCode);
	        	$("#versionDate").append(strVersionId); 
	        })
	    })
	    $(function () {
	    	$("#versionAppName").children().remove();
	    	$("#versionAppDate").children().remove();
	        $.getJSON('${pageContext.request.contextPath}/admin/userBehInfo/getVersionList',{type:'1'},function(data){
	        	var strVersionCode="",strVersionId="";
	        	if(data.length>0){
		        	$.each(data,function(i,v){
		        		if(data.length>5){
			        		if(i>=data.length-5){
			        			strVersionCode+="<span style='width: 60px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>"+v.versionCode+"</span>";
			        			strVersionId+="<span style='width: 60px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>"+v.versionId+"</span>";
			        		}
			        	}else{
			        			strVersionCode+="<span style='width: 60px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>"+v.versionCode+"</span>";
				        		strVersionId+="<span style='width: 60px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>"+v.versionId+"</span>";
			        		}
		        	})
		        	$("#versionAppName").append(strVersionCode);
		        	$("#versionAppDate").append(strVersionId);
	        	}else{
	        		strVersionCode+="<span style='width: 70px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>0.0.0</span>";
	        		strVersionId+="<span style='width: 70px;text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;'>"+0+"</span>";
	        		$("#versionAppName").append(strVersionCode);
		        	$("#versionAppDate").append(strVersionId);
	        	}
	        })
	    })
	    
	}
	$(function(){
		$("#year").removeClass("start");
		$("#year").addClass("end");
		$("#all").removeClass("start");
		$("#all").addClass("end");
	})
	

	function dj(dom) {
	    var collection = $(".flag");
	    $.each(collection, function () {
	        $(this).removeClass("end");
	        $(this).addClass("start");
	    });
	    $(dom).removeClass("start");
	    $(dom).addClass("end");
	    $("#dateDim").val($(dom).attr("data"));
	    funModuList();
	    errList();
	    errColumn();
	    userModu();
	    $("#targetAjaxForm").submit();
	}
	
	function zb(dom) {
	    var collection = $(".flag1");
	    $.each(collection, function () {
	        $(this).removeClass("end");
	        $(this).addClass("start");
	    });
	    $(dom).removeClass("start");
	    $(dom).addClass("end");
	    $("#targetDim").val($(dom).attr("data"));
	    if($(dom).attr("data")=="all"){
	    	$(".userModu").hide();
	    	$("#gnmk").hide();
	    	$("#cwfx").hide();
	    	$(".all").show();
	    }
	    if($(dom).attr("data")=="userAn"){
	    	$(".all").hide();
	    	$("#gnmk").hide();
	    	$("#cwfx").hide();
	    	$(".userModu").show();
	    	userModu();
	    }
	    if($(dom).attr("data")=="funAn"){
	    	funModuList("year");
	    	$(".all").hide();
	    	$(".userModu").hide();
	    	$("#cwfx").hide();
	    	$("#gnmk").show();
	    }
	    if($(dom).attr("data")=="errAn"){
	    	errList();
	    	errColumn();
	    	$(".all").hide();
	    	$(".userModu").hide();
			$("#cwfx").show();
	    	$("#gnmk").hide();
	    }
	    $("#targetAjaxForm").submit();
	}
	
	$('#targetAjaxForm').ajaxForm(function(data) {
		$("#aUser").text(data.aUser)
		if(data.aUser>1000){
			$("#aUser").text(data.aUser*0.001+"千")
		}
		$("#minalterType").html(data.minalterType+"<font>"+data.maxName+"</font>")
		$("#eRate").text(data.eRate+"%");
		$("#moduRat").text(data.moduRat+"%");
		$("#moduRatName").text(data.moduRatName);
		$("#lifeUser").text(data.lifeUser);
		$("#actUser").text(data.actUser);
		$("#logUser").text(data.logUser);
		
		$("#aUserRatio").text(data.aUserRatio+"%");
		$("#minalterRatio").text(data.minalterRatio);
		$("#eRateRatio").text(data.eRateRatio);
	});
	var chart = null;
	var people=0;
	$(function() {
		$("#dateDim").change(function(){
			if($("#dateDim").val()=="year"){
				$("#year").css("background","#b1b0b0")
			}
		})
		
		var collection = $(".flag");
        $.each(collection, function () {
            $(this).addClass("start");
        });
		
        Highcharts.setOptions({
            global : {
                useUTC : false
            }
        });
        // Create the chart
        $.getJSON('${pageContext.request.contextPath}/admin/userBehInfo/onlinePeople',{dateDim:$("#dateDim").val()},function(data){
        		people=data;
        });
        setInterval(function () {
        	$.getJSON('${pageContext.request.contextPath}/admin/userBehInfo/onlinePeople',{dateDim:$("#dateDim").val()},function(data){
        		people=data;
        	});
        }, 1000*60);
        
        $('#container').highcharts('StockChart', {
            chart : {
                events : {
                    load : function () {
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function () {
                            var x = (new Date()).getTime(), // current time
                                y = people;//Math.round(Math.random() * 100);
                            series.addPoint([x, y], true, true);
                        }, 1000*1);
                    }
                }
            },
            credits: {
            	enabled:false
            },
            rangeSelector: {
                buttons: [{
                    count: 1,
                    type: 'minute',
                    text: '1M'
                }, {
                    count: 5,
                    type: 'minute',
                    text: '5M'
                },{
                    type: 'all',
                    text: 'All'
                }],
                inputEnabled: false,
                selected: 0
            },credits : {
            	enabled : false
            },
            navigator : {
            	enabled:false
            },
            title : {
                text : '统计在线人数'
            },
            exporting: {
                enabled: false
            },
            series : [{
                name : '在线人数',
                data : (function () {
                    // generate an array of random data
                    var data = [], time = (new Date()).getTime(), i;
                    for (i = -999; i <= 0; i += 1) {
                        data.push([
                            time + i * 1000,
                            people//Math.round(Math.random() * 100)
                        ]);
                    }
                    return data;
                }())
            }]
        });
		
        $('#containers').highcharts('StockChart', {
            chart : {
                events : {
                    load : function () {
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function () {
                            var x = (new Date()).getTime(), // current time
                                y = people;//Math.round(Math.random() * 100);
                            series.addPoint([x, y], true, true);
                        }, 1000*1);
                    }
                }
            },
            rangeSelector: {
                buttons: [{
                    count: 1,
                    type: 'minute',
                    text: '1M'
                }, {
                    count: 5,
                    type: 'minute',
                    text: '5M'
                },{
                    type: 'all',
                    text: 'All'
                }],
                inputEnabled: false,
                selected: 0
            },credits : {
            	enabled : false
            },
            navigator : {
            	enabled:false
            },
            title : {
                text : '统计在线人数'
            },
            exporting: {
                enabled: false
            },
            series : [{
            	name : '在线人数',
                data : (function () {
                    // generate an array of random data
                    var data = [], time = (new Date()).getTime(), i;
                    for (i = -999; i <= 0; i += 1) {
	                    data.push([
	                        time + i * 1000,
	                        people//Math.round(Math.random() * 100)
	                    ]);
                    }
                    return data;
                }())
            }]
        });
        
		var page = Ace.decode("${u:serialize(pager)}");
		$('#pager').pager('${pageContext.request.contextPath}/admin/userBehInfo/list',15,$('#targetView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; display:none; text-align:center;  line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
			})(this);
		})).setJSON(page);
		$('#pager').pager().reload();
		
		var pager2 = Ace.decode("${u:serialize(pager2)}");
		$('#pager2').pager('${pageContext.request.contextPath}/admin/userBehInfo/list',15,$('#targetView1').view().on('add',function(data){})).setJSON(pager2);
		$('#pager2').pager().reload();
});	
	
	function errColumn() {
    	$.getJSON('${pageContext.request.contextPath}/admin/userBehInfo/getHieErrInfo',{dateDim:$("#dateDim").val()},function(data){
            $('#err').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                	enabled:false
                },
                legend: {
                    enabled: false
                },
                title: {
                    text: '错误分析'
                },
                xAxis: {
                    categories: [
                        '一月',
                        '二月',
                        '三月',
                        '四月',
                        '五月',
                        '六月',
                        '七月',
                        '八月',
                        '九月',
                        '十月',
                        '十一月',
                        '十二月'
                    ],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '时段内错误次数'
                    }
                },
                tooltip:
                {
                    headerFormat: '{series.name}<br>',
                    pointFormat: '{point.name}: <b>{point.y}</b>'
                },
                
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
//	                series: [{
//	                    name: '错误',
//	                    data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
//	                }]
                series:data.dateErrInfo
            });
    	})
    };
    
	function funModuList(){
		var funPage = Ace.decode("${u:serialize(funPage)}");
		$('#funPage').pager("${pageContext.request.contextPath}/admin/userBehInfo/funanlist",15,$('#funModu').view().on('add',function(data){})).setJSON(funPage);
		$('#funPage').pager().setPostData({funDate:$('#dateDim').val()});
		$('#funPage').pager().reload();
	}
	
	function errList(){
		var errPage = Ace.decode("${u:serialize(errPage)}");
		$('#errPage').pager('${pageContext.request.contextPath}/admin/userBehInfo/errlist',10,$('#errAn').view().on('add',function(data){})).setJSON(errPage);
		$('#errPage').pager().setPostData({errDate:$('#dateDim').val()});
		$('#errPage').pager().reload();
		
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/admin/userBehInfo/errinfo",
			data : {date:$('#dateDim').val()},
			success : function(msg) {
				$("#errRate").html(msg.errRate +"%");
				$("#errNum").html(msg.num);
				$("#errNumDay").html(msg.numDay);
				$("#errNumWeek").html(msg.numWeek);
				
			}
		});
	}
		
	//下载
	function Download(){
		var targetDim = $("#targetDim").val();
		var date = $('#dateDim').val();
		window.location.href = "${pageContext.request.contextPath}/admin/userBehInfo/download?modelName="+targetDim+"&date="+date;
	}
	//刷新页面
	/* function reload(){
		location.reload();
	} */
</script>
</head>
<body>
	<div class="tagContentList">
		<div class="business_title">用户行为分析</div>
		<div class="col_lg_04" style="width: 1203px;">
			<div class="business_search_list_warp" style="width: 95%">
				<div class="business_search" align="center">
					<div class="business_search_left" style="margin-left: -20px;"
						align="center">
						<form id="targetAjaxForm"
							action="${pageContext.request.contextPath}/admin/userBehInfo/dateUserAn"
							method="post" style="width: 900px">
							<input type="hidden" name="pageSize" value="15" />
							<table style="float: left;"
								style="border-collapse:separate; border-spacing:0px 10px;">
								<tbody>
									<tr>
										<input type="hidden" id="dateDim" name="dateDim" value="year">
										<input type="hidden" id="targetDim" name="targetDim"
											value="all">
										<td style="text-align: right">时间维度：</td>
										<td><input class="flag hq_hy but"
											style="margin-left: 30px;" type="button" id="year" data="year"
											onclick="dj(this);" value="本年"> <input
											class="flag hq_hy but" style="margin-left: 30px;"
											type="button" data="month" id="month" onclick="dj(this);" value="本月">
											<input class="flag hq_hy but" style="margin-left: 30px;"
											type="button" data="week" id="week" onclick="dj(this);" value="本周"></td>
									</tr>
									<tr>
										<td style="text-align: right">指标维度：</td>
										<td><input class="flag1 hq_hy but"
											style="margin-left: 30px;" type="button" data="all"
											onclick="zb(this);" id="all" value="全部"> <input
											class="flag1 hq_hy but" style="margin-left: 30px;"
											type="button" data="userAn" onclick="zb(this);"
											value="用户使用分析"> <input class="flag1 hq_hy but"
											style="margin-left: 30px;" type="button" data="funAn"
											onclick="zb(this);" value="功能模块使用分析"> <input
											class="flag1 hq_hy but" style="margin-left: 30px;"
											type="button" data="errAn" onclick="zb(this);" value="错误分析"></td>
									</tr>
								</tbody>
							</table>
						</form>
						<div style="float: right; height: 30px;">
							<input type="button" value="下&#12288;载" onclick="Download()"
								style="margin-right: -580px; width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;">
						</div>
					</div>
				</div>
				<div class="all">
					<div style="float: left; width: 100%;">
						<dl>
							<dt>累计用户</dt>
							<dt style="font-size: 50px;height:51px" id="aUser"><c:if test="${aUser<1000}">${aUser}</c:if><c:if test="${aUser>1000}"><${aUser/1000}千</c:if>
							</dt><div style="text-align: -webkit-center;
    padding-bottom: 10px;"><span id="aUserRatio">${aUserRatio}%</span>  同比去年</div>
							<dt style="text-align: -webkit-left;">
								<a href="javascript:void(0)" data="userAn" onclick="zb(this);">更多...</a>
							</dt>
						</dl>
						<dl>
							<dt width="1">最多使用终端</dt>
							<dt style="font-size: 50px;height:51px" id="minalterType">${minalterType}<font>PC</font>
							</dt><div style="text-align: -webkit-center;
    padding-bottom: 10px;"><span id="minalterRatio">${minalterRatio}</span>%  同比去年</div>
							<dt style="text-align: -webkit-left;">
								<a href="javascript:void(0)" data="userAn" onclick="zb(this);">更多...</a>
							</dt>
						</dl>
						<dl width="1">
							<dt>错误率</dt>
							<dt style="font-size: 50px;height:51px" id="eRate">${eRate}%</dt><div style="text-align: -webkit-center;
    padding-bottom: 10px;"><span id="eRateRatio">${eRateRatio}</span>%  同比去年</div>
							<dt style="text-align: -webkit-left;">
								<a href="javascript:void(0)" data="errAn" onclick="zb(this);">更多...</a>
							</dt>
						</dl>
						<dl style="margin-right: 0px;">
							<dt width="1">访问最多模块占比</dt>
							<dt style="font-size: 50px;height:51px;" id="moduRat">${moduRat}%</dt><div style="text-align: -webkit-center;
    padding-bottom: 10px;"><span id="moduRatName">${moduRatName}</span></div>
							<dt style="text-align: -webkit-left;">
								<a href="javascript:void(0)" data="funAn" onclick="zb(this);">更多...</a>
							</dt>
						</dl>
					</div>
					<div class="t-list table" style="height: 200px"></div>
					<div id="container" class="t-list table" style="height: 200px"></div>
					<table cellspacing="0" cellpadding="0" class="t-list table" style="margin-top: 35px;"
						id="targetView">
						<tbody>
							<tr>
								<th width="">序号</th>
								<th width="">用户</th>
								<th width="">终端</th>
								<th width="">使用时长</th>
								<th width="">使用间隔</th>
								<th width="">访问页数</th>
								<th width="">使用版本</th>
							</tr>
							<tr class="tb_tr_content template" name="default">
								<td>{index:seq()}</td>
								<td>{userName}</td>
								<td>{terminalType}</td>
								<td>{useTime}</td>
								<td>{useGapTime} Days</td>
								<td>{callPage}</td>
								<td>{version}</td>
							</tr>
						</tbody>
					</table>
					<div id="pager"></div>
				</div>
				<div class="clear"></div>
				
				<!-- 用户分析 -->
				<div class="business_search userModu" style="display:none;">
						<%-- <form id="targetAjaxForm"
							action="${pageContext.request.contextPath}/admin/userBehInfo/dateUserAn"
							method="post" style="width: 900px">
						</form> --%>
						<div style="border: 1px #ccc solid;">
							<div style="background-color:#eae8e8;border: 1px #ccc solid;display: inline-block;">
								<h1>累计用户分析</h1>
								<div id="pie" class="t-list table" style="width:800px"></div>
							</div>
							<div class="User_left_div" style="float:right;">
								<div><img height="90px" src="${pageContext.request.contextPath}/static/images/actImg.png">
									<span style="font-size: 35px;" id="actUser">${actUser}</span><font>People</font>
									<dl class="dl"><font>活跃用户</font></dl>
								</div>
								<%-- <div><img height="90px" src="${pageContext.request.contextPath}/static/images/regImg.png">
									${regUser}<font>People</font>
									<dl class="dl"><font>近七日均注册数</font></dl>
								</div> --%>
								<div><img height="90px" src="${pageContext.request.contextPath}/static/images/logImg.png">
									<span style="font-size: 35px;" id="logUser">${logUser}</span><font>People</font>
									<dl class="dl"><font>近七日均登录数</font></dl>
								</div>
								<div><img height="90px" src="${pageContext.request.contextPath}/static/images/lifeImg.png">
								<span style="font-size: 35px;" id="lifeUser">${lifeUser}</span><font>Days</font>
									<dl class="dl"><font>用户生命周期</font></dl>
								</div>
							</div>
						</div>
						<div id="containers" class="t-list table" style="height: 200px"></div>
						<div style="background-color:#eae8e8;border: 1px #eae8e8 solid;">
							<div id="dateLine" class="t-list table" style="height: 440px;width:60%;    display: inline-block;
    vertical-align: top;"></div>
							<div style="    display: inline-block;
    vertical-align: top;">
								<div style="line-height: 25px;margin-top: 10px;">各层级用户</div>
								<div id="pie2" class="t-list table" ></div>
							</div>
							
						</div>
						<div border: 1px #eae8e8 solid;">
							<table cellspacing="0" cellpadding="0" class="t-list table"
								id="targetView1">
								<tbody>
									<tr>
										<th width="">序号</th>
										<th width="">用户</th>
										<th width="">终端</th>
										<th width="">使用时长</th>
										<th width="">使用间隔</th>
										<th width="">访问页数</th>
										<th width="">使用版本</th>
									</tr>
									<tr class="tb_tr_content template" name="default">
										<td>{index:seq()}</td>
										<td>{userName}</td>
										<td>{terminalType}</td>
										<td>{useTime}</td>
										<td>{useGapTime} Days</td>
										<td>{callPage}</td>
										<td>{version}</td>
									</tr>
									
								</tbody>
							</table>
							<div width="100%" style="margin-top:20px ">
								<div style="float:left">
								<span style="line-height: 100px;border: 2px #ccc solid;border-right: 1px;display: inline-block;float: left;height:98px;width: 70px;
    text-align: center;">终端使用率</span>
								<span style="display: inline-block;border: 1px #ccc solid;float: left;margin-right: 20px;">
									<div style="display: inline-block;">
										<c:forEach items="${maxName},${minName}" var="list">
											<span style="width: 100px;
    text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;">${list}</span>
										</c:forEach>
									</div>
									<div >
										<c:forEach items="${maxCount},${minCount}" var="list">
											<span style="width: 100px;
    text-align: center;display: inline-block;border: 0.1px #ccc solid;float: left;">${list}</span>
										</c:forEach>
									</div>
								</span>
								</div>
									<div style="float:left">
								<span style="line-height: 100px;border: 2px #ccc solid;border-right: 1px;
    display: inline-block;float: left;height:98px;width:80px;text-align: center;">PC统计</span>
								<span style="display: inline-block;border: 1px #ccc solid;">
									<div style="display: inline-block;" id="versionName">
									</div>
									<div id="versionDate">
									</div>
								</span>
								</div>
									<div style="float:left">
								<span style="line-height: 100px;border-top-color: rgb(204, 204, 204);
								    border-top-style: solid;
								    border-top-width: 2px;
								    border-bottom-color: rgb(204, 204, 204);
								    border-bottom-style: solid;
								    border-bottom-width: 2px;border-left: 1px;display: inline-block;float: left;height:98px;width: 70px;
    								text-align: center;"">APP统计</span>
							    <span style="display: inline-block;border: 1px #ccc solid;float: left;margin-right: 20px;">
									<div style="display: inline-block;" id="versionAppName"></div>
									<div id="versionAppDate"></div>
								</span>
								</div>
							</div>
							<div id="pager2"></div>
						</div>
				</div>

				<!-- 功能模块分析 -->
				<div id="gnmk" style="display:none">	
					<table  id="funModu"  cellspacing="0" cellpadding="0"
						class="t-list table">
						<tbody>
							<tr>
								<th width="">功能模块</th>
								<th width="">所属菜单</th>
								<th width="">访问次数</th>
								<th width="">占比</th>
								<th width="">访问人数</th>
								<th width="">占比</th>
								<th width="">停留时间</th>
								<th width="">占比</th>
								<th width="">跳出率</th>
								<th width="">转化率</th>
							</tr>
							<tr class="tb_tr_content template" name="default">
								<td>{name}</td>
								<td>{pName}</td>
								<td>{visitNum}</td>
								<td>{vnRatio} %</td>
								<td>{visitPeo}</td>
								<td>{vpRatio} %</td>
								<td>{stayTime} Min</td>
								<td>{stRatio} %</td>
								<td>{outRatio} %</td>
								<td>{convertRatio} %</td>
							</tr>
						</tbody>
					</table>
					<div class="funPage"  id="funPage"></div>
				</div>
			<!-- 错误分析 -->	
				<div id="cwfx" style="display:none">	
					<div id="err" style="min-width:400px;height:400px"></div>
					<table  id="errAn"  cellspacing="0" cellpadding="0"
						class="t-list table">
						<tbody>
							<tr>
								<th width="10%">序号</th>
								<th width="30%">错误类型</th>
								<th width="60%">备注</th>
							</tr>
							<tr class="tb_tr_content template" name="default">
								<td>{index:seq()}</td>
								<td>{terminalType}</td>
								<td>{errorInfo}</td>
							</tr>
						</tbody>
					</table>
					<div align="center" style="float:left; margin-left:120px; margin-top:20px; padding:10px; border: 1px solid #ccc;width: 100px;">错误率</div>
					<div align="center" style="float:left; margin-top:20px; padding:10px; border: 1px solid #ccc;width: 50px;" id="errRate"></div>
					<div align="center" style="float:left; margin-left:50px; margin-top:20px; padding:10px; border: 1px solid #ccc;width: 100px;">错误次数</div>
					<div align="center" style="float:left; margin-top:20px; padding:10px; border: 1px solid #ccc;width: 50px;" id="errNum"></div>
					<div align="center" style="float:left; margin-left:50px; margin-top:20px; padding:10px; border: 1px solid #ccc;width: 100px;">今日错误次数</div>
					<div align="center" style="float:left; margin-top:20px; padding:10px; border: 1px solid #ccc;width: 50px;" id="errNumDay">0</div>
					<div align="center" style="float:left; margin-left:50px; margin-top:20px; padding:10px; border: 1px solid #ccc;width: 100px;">近七日错误次数</div>
					<div align="center" style="float:left; margin-top:20px; padding:10px; border: 1px solid #ccc;width: 50px;" id="errNumWeek">0</div>
					<div class="errPage"  id="errPage"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>