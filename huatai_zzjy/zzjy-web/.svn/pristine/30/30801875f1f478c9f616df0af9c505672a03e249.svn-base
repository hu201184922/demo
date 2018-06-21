<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@page import="java.util.Date"%>
<!-- <meta name="decorator" content="admin-popup"> -->

<html>
<head>
<title>Trigger信息</title>
<script type="text/javascript">

function deleteTrigger(name,group){
	var r = window.confirm("确认要删除吗？");
	if(r){
		var idString = name + "," + group;
		$.ajax({
			type : "POST",
			url : "${ctx}/admin/qrtz/trigger/delete/" + idString,
			success : function(msg) {
				window.location.reload(true);
			}
		});
	}
}

function edit(name,group){
	var idString = name + "," + group;
	location.href =  "${ctx}/admin/qrtz/trigger/edit/"+idString;
}

</script>

</head>

<body>

<!-- 信息提示 -->
<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>

<!-- Trigger列表 -->
<div class="panel panel panel-info">
	<div class="panel-heading">Trigger信息列表</div>
	<div class="panel-body">
	<div class="col-md-12">
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th>名称</th>
					<th>所属组</th>
					<th>上一次触发时间</th>
					<th>下一次触发时间</th>
					<th>优先级</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${triggers}" var="trigger">
					<tr>
						<td>${trigger.key.name}</td>
						<td>${trigger.key.group}</td>
						<td>${trigger.previousFireTime }</td>
						<td>${trigger.nextFireTime }</td>
						<td>${trigger.priority }</td>
						<td>${trigger.startTime }</td>
						<td>${trigger.endTime }</td>
						<td>
							<a href="${ctx}/admin/qrtz/trigger/edit/${trigger.key.name},${trigger.key.group}" >编辑</a>
							<a onclick="deleteTrigger('${trigger.key.name}','${trigger.key.group}')" href="javascript:void(0)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
	
</div>

</body>

</html>