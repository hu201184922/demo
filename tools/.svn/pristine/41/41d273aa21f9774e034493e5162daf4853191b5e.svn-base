<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!-- <meta name="decorator" content="nodecorate" /> -->
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调度定义</title>
<script type="text/javascript">

$("#selectAll").click(function() {
	var obj = document.getElementById("selectAll");
	var value = obj.checked;
	if (value == true) {
		$(".td :input").each(function(i) {
			this.checked = true;

		});

	} else {
		$(".td :input").each(function(i) {
			this.checked = false;
		});
	}
});

function deleteSimple(id){
	var r = window.confirm("确定要删除吗？");
	if (r) {
		$.ajax({
			type : "post",
			url : "${ctx}/admin/qrtz/simple/delete/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadSimpleTrigger(id);
			}
		});
	}
}

function release(id){
	var r = window.confirm("确定要发布吗？");
	if (r) {
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/simple/release/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadSimpleTrigger(id);
			}
		});
// 		location.href="${ctx}/admin/qrtz/simple/release/" + id;
	}
}

function generate(id){
	var r = window.confirm("确定要立即生成吗？");
	if (r) {
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/simple/generate/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadSimpleTrigger(id);
			}
		});
// 		location.href="${ctx}/admin/qrtz/simple/generate/" + id;
	}
}

function stop(id){
	var r = window.confirm("确定要终止吗？");
	if (r) {
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/simple/stop/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadSimpleTrigger(id);
			}
		});
// 		location.href="${ctx}/admin/qrtz/simple/stop/" + id;
	}
}

</script>
</head>
<body>

		<table id="contentTable" class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>名称</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>重复间隔</th>
					<th>重复次数</th>
					<th>任务参数</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="td" id="tbody">
				<c:if test="${simpleTriggers != null }">
				<c:forEach items="${simpleTriggers}" var="simpleTrigger">
					<tr>
						<td><input type="checkbox" value="${simpleTrigger.id}" name="box" /></td>
						<td>${simpleTrigger.name }</td>
						<td>${simpleTrigger.startTime }</td>
						<td>${simpleTrigger.endTime }</td>
						<td>${simpleTrigger.repeatInterval }</td>
						<td>${simpleTrigger.repeatCount }</td>
						<td>${simpleTrigger.jobDataMap }</td>
						<td>${simpleTrigger.state }</td>
						<td>
							<c:choose>
									<c:when test="${simpleTrigger.state == '草稿' }" >
										<div><a class="btn" onclick="release(${simpleTrigger.id})"  href="javascript:void(0)">发布</a></div>
										<div><a class="btn cron" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/cron/edit/${simpleTrigger.id}">编辑</a></div>
										<div><a class="btn" onclick="deleteSimple(${simpleTrigger.id})" href="javascript:void(0)">删除</a></div>
									</c:when>
									<c:when test="${simpleTrigger.state == '发布' }" >
										<div><a class="btn" onclick="generate(${simpleTrigger.id})" href="javascript:void(0)">立即生成</a></div>
										<div><a class="btn" onclick="deleteSimple(${simpleTrigger.id})" href="javascript:void(0)">删除</a></div>
									</c:when>
									<c:when test="${simpleTrigger.state == '已生成' }" >
										<div><a class="btn" onclick="stop(${simpleTrigger.id})" href="javascript:void(0)">终止</a></div>
										<div><a class="btn" onclick="deleteSimple(${simpleTrigger.id})" href="javascript:void(0)">删除</a></div>
									</c:when>
									<c:when test="${simpleTrigger.state == '终止' }" >
										<div><a class="btn" onclick="generate(${simpleTrigger.id})" href="javascript:void(0)">立即生成</a></div>
										<div><a class="btn" onclick="deleteSimple(${simpleTrigger.id})" href="javascript:void(0)">删除</a></div>
									</c:when>
									<c:otherwise>  
										<div><a class="btn" onclick="deleteSimple(${simpleTrigger.id})" href="javascript:void(0)">删除</a></div>
									</c:otherwise> 
							</c:choose>
<%-- 							<a class="btn simple" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/simple/edit/${simpleTrigger.id}">编辑</a></div> --%>
<%-- 							<a class="btn" onclick="deleteSimple(${simpleTrigger.id})" href="javascript:void(0)">删除</a></div>  --%>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
	
<script type="text/javascript">
$(document).ready(function(){
	
	$(".simple").fancybox({
		fitToView	: false,
		width		: '80%',
		height		: '80%',
		autoSize	: false,
		closeClick	: false,
		openEffect	: 'none',
		closeEffect	: 'none',
		afterClose:function(){
			var id = $("#qrtzGroup_id").val();
			loadSimpleTrigger(id);
		}
	});
	
});
</script>
	
</body>
</html>