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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/task.css"/>
<script type="text/javascript">

var defaultView = "month";
$(document).ready(function(){
	
	if('${type}' == "month" || '${type}' == "agendaWeek" || '${type}' == "agendaDay"){
		defaultView = '${type}';
	}
	
// 	 var eventJson = ${eventJson};
	 
	 showCalenderTask();
	
	
});

</script>
</head>
<body>
	<div id="calenderTaskView123">
	<div class="business_title">
		<a href="#" class="home">${userName }</a>
		<img src="${pageContext.request.contextPath}/static/images/jiantou.png" />
		<a href="${ctx }/sharedCalenderList" style="color:#ff9c00; ">【共享我的日历】</a>
	</div>

	<form id="calenderTaskQueryForm" action="${ctx }/calenderTask" method="post">
		<select id="sharedAccount" name="sharedAccount" onchange="changeCalenderTask()" class="selectCon">
			<option value="${sharedAccount }" >本人</option>
			<c:forEach items="${calenderShareds }" var="calenderShared">
				<option value="${calenderShared.account }" <c:if test="${clender == calenderShared.account }">selected="selected"</c:if> >${calenderShared.accountName }</option>
			</c:forEach>
		</select>
		<a href="${ctx }/task/taskModify?jumpPage=calenderTask" class="addRenwu2">新建任务</a>
		<a href="${ctx }/index" class="calenderBack">返回</a>
	</form>
	<div class="clear"></div>
	<div id="calendar"></div>
	
	
<script type="text/javascript">
function changeCalenderTask(){
	$("#calenderTaskQueryForm").submit();
}
function showCalenderTask(){
	$("#calendar").fullCalendar({
		theme: true,
		titleFormat: {
			month: "YYYY年MM月",
			week: "YYYY年MM月DD日",
			day: "YYYY年MM月DD日"
		},
		header: {
			left: 'month,agendaWeek,agendaDay',
			center: 'title',
			right: 'prev,next today'
		},
		monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
		defaultView: defaultView,
		buttonText: {
			prevYear: "<span class='fc-text-arrow'>&laquo;</span>",
			nextYear: "<span class='fc-text-arrow'>&raquo;</span>",
			today: '返回今天',
			month: '月',
			week: '周',
			day: '日'
	    },
	    defaultDate: '${defaultDate}',
		editable: false,
		eventLimit: false,
		events:function(start, end){
			var sharedAccount = $("#sharedAccount").val();
			$.ajax({
				type : "POST",
				url : "${ctx}/calenderTaskQuery",
				async : false,
				data : {sharedAccount : sharedAccount, start : start, end : end},
				dataType : "json",
				success : function(data) {
					$("#calendar").fullCalendar('removeEvents');
					$.each(data, function (index, term) {
						$("#calendar").fullCalendar('renderEvent', term, true);
					});
				}
			});
		}
	});
}

</script>
</div>
</body>
</html>