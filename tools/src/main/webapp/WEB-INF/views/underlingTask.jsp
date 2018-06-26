<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/task.css"/>
<title>下属任务查看</title>
<script type="text/javascript">
$(function(){
	
	$('#underlingTaskFrom').ajaxForm(function(data){
		$('#underlingTaskPager').pager().options.postData={};
		$('#underlingTaskPager').pager().setPostData(Ace.parseQuery($('#underlingTaskFrom').serialize()));
		$('#underlingTaskPager').pager().setJSON(data);
	});
	
	$('#underlingTaskPager').pager('${pageContext.request.contextPath}/underlingTaskQuery',10,$('#underlingTaskView').view().on("add",function(data){
		if((data.taskStatus == '1' || data.taskStatus == '2') && data.finishFlag == '1'){
			this.target.find('#underlingTaskFin').show();
		}else if(data.taskStatus == '3' || data.taskStatus == '4'){
			this.target.find('#finishStatus').show();
		}else{
		}
		if(data.priority == '1'){
			this.target.find('#priority1').show();
		}else{
			this.target.find('#priority2').show();
		}
	}));
	
	$("#searchBtn").click(function(){
		$('#underlingTaskFrom').submit();
	});
	
	$('#underlingTaskFrom').submit();
	
});

function finishTask(taskId,taskStatus,tempEndTime){
	var s;
	var i=0;
	if($("input[name=finishDate]").length>1){
		if(taskStatus==1){
			$("#endTime").val(tempEndTime);
			s= $("input[name=finishDate]:first")[0];
			$("input[name=finishDate]:first").remove();
			i=1;
		}else{
			s=$("input[name=finishDate]:last")[0];
			$("input[name=finishDate]:last").remove();
			i=2;
		}
	}
	$.dialog({
		title : '请录入任务完成时间',
		content: $("#TaskFinishDialog")[0],
		width:'450px',
		height:'100px',
		button : [{
			value : '确定',
			callback : function() {
				$.ajax({
					url:"${pageContext.request.contextPath}/task/finishTask",
					data:{
						"taskId":taskId,
						"taskSta":taskStatus,
						"finishDate":$("#finishDate").val(),
						"proFlag":false
					},
					success:function(data){
						$.msgbox({time: 1000,msg: "操作成功！" ,icon:"success"});
						$("#searchBtn").click();
					},
					error:function(data){
						alert("数据加载失败");
					}
				});
			}
		},{
			value : '返回',
			callback : function() {
				if(i==1){
					$("input[name=finishDate]").before(s);
				}
				if(i==2){
					$("input[name=finishDate]").after(s);
				} 
			}
		}]
	});
	$(".d-close").remove(); 
}

function changeDate(num){
	if($("input[id=date_"+num+"]").attr("checked") == "checked"){
		$("#beginDate").attr("disabled","disabled");
		$("#endDate").attr("disabled","disabled");
	}else{
		$("#beginDate").removeAttr("disabled");
		$("#endDate").removeAttr("disabled");
	}
	$("input[id^=date_]").each(function(index,e){
		if(index!=num){
			e.checked = false;
		}
	});
}

//获取当前时间字符串
function CurentTime() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分

    var clock = year + "-";

    if (month < 10)
        clock += "0";

    clock += month + "-";

    if (day < 10)
        clock += "0";

    clock += day + " ";

    if (hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += "0";
    clock += mm;
    return clock;
}

</script>
</head>
<body>
<div id="underlingTaskList">

	<div class="business_title">
		<a href="#" class="home">${userName }</a>
		<img src="${pageContext.request.contextPath}/static/images/jiantou.png" />
		<a href="${ctx }/index">【返回】</a>
	</div>
	
	<div class="fenpeiCon">
         <form id="underlingTaskFrom" action="${ctx }/underlingTaskQuery " method="post">       	
       	<div class="fenpeiConLine">
           	<p>任务被分配人：</p>
            <select name="findType">
            	<option value="0">全部</option>
            	<option value="1">仅本人</option>
            	<option value="2" selected="selected">仅下属</option>
            </select>
        </div>
           
        <div class="fenpeiConLine">
        	<p>到期日期：</p>
        		<input type="text" value="${beginDate }" class="fenpeiInput fenpeiInputWidth2" style="margin-left:0;" name="beginDate" id="beginDate" date="{dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endDate\')}'}"/><span>~</span><input type="text" value="${endDate }" class="fenpeiInput fenpeiInputWidth2" style="margin-left:18px;" name="endDate" id="endDate" date="{dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'beginDate\')}'}"/>
            <input type="checkbox" id="date_0" onclick="changeDate(0);" value="1" name="expireCheck"/><label>本月</label> 
			<input type="checkbox" id="date_1" onclick="changeDate(1);" value="2" name="expireCheck"/><label>下一月</label> 
			<input type="checkbox" id="date_2" onclick="changeDate(2);" value="3" name="expireCheck"/><label>未来三个月</label> 
            <input type="button" id="searchBtn" class="fenpeiIcon" value="查 询">
        </div>
        </form>
   </div>
       
       
	<div class="library_right_warp">
		<div class="library_right_warp_table">
			<div class="business_search_list_table">
				<table class="t-list table" id="underlingTaskView">
					<tr>
						<th width="10%">类型</th>
						<th width="20%">任务主题</th>
						<th width="20%">到期日期</th>
						<th width="15%">优先级</th>
						<th width="13%">分配人</th>
						<th width="13%">被分配人</th>
						<th>完成</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{taskType:dict({0:'电话',1:'会议',2:'访约',3:'培训',4:'其他'})}</td>
						<td><a ajax="{target:'#underlingTaskList'}" href="${ctx}/task/taskDetail?taskId={taskId}">{taskTitle}</a></td>
						<td>{endTime}</td>
						<td><a id="priority1" style="display: none;">高</a><a id="priority2" style="display: none;">一般</a></td>
						<td>{createName}</td>
						<td>{executorName}</td>
						<td><a id="underlingTaskFin" href="javascript:finishTask({taskId},{taskStatus},'{endTime}')" style="display: none;"><img style="float: none;display: inline;" src="${ctx}/static/images/del_icon.png" title="任务完成"></a><span id="finishStatus" style="display: none;">已完成</span></td>
					</tr>
				</table>
			</div>
			<div id="underlingTaskPager"></div>
			<div class="clear"></div>
		</div>
	</div>
	
</div>

<div id="TaskFinishDialog" style="display: none; width: 400px;">
	<!-- 完成任务 -->
		<div class="new_jihui_warp">
			<div class="business_search">
				<div class="business_search_left">
					<label>完成时间：</label>
					<input type="text" name="finishDate" id="finishDate" class="business_search_input w250 Wdate"
											date="{dateFmt:'yyyy-MM-dd HH:mm',maxDate:'%y-%M-%d %H:%m'}"/>
					<input type="hidden" id="endTime" name="endTime" value=""/>
					<input type="text" name="finishDate" id="finishDate" class="business_search_input w250 Wdate"
								date="{dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'endTime\')}',maxDate:'%y-%M-%d %H:%m'}"/>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	
</body>
</html>

