<%@page import="com.fairyland.jdp.framework.quartz.domain.Triggers"%>
<%@page import="com.fairyland.jdp.framework.quartz.domain.JobDetail"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>

<head>
<title>任务管理</title>

<script type="text/javascript">
	function schedJob(idString){
		var r = window.confirm("确定要执行任务吗？");
		if (r) {
			location.href="${ctx}/admin/qrtz/job/sched/" + idString;
		}
	}
	
	function pauseJob(idString){
		var r = window.confirm("确定要挂起任务吗？");
		if (r) {
			location.href="${ctx}/admin/qrtz/job/pause/" + idString;
		}
	}
	
	function stopJob(idString){
		var r = window.confirm("确定要终止任务吗？");
		if (r) {
			location.href="${ctx}/admin/qrtz/job/stop/" + idString;
		}
	}
	
	function resumeJob(idString){
		var r = window.confirm("确定要激活任务吗？");
		if (r) {
			location.href="${ctx}/admin/qrtz/job/resume/" + idString;
		}
	}
	
	function interruptJob(idString){
		var r = window.confirm("确定要中断吗？");
		if (r) {
			location.href="${ctx}/admin/qrtz/job/interrupt/" + idString;
		}
	}
	
	function deleteJob(idString){
		var r = window.confirm("确定要删除任务吗？");
		if (r) {
			location.href="${ctx}/admin/qrtz/job/delete/" + idString;
		}
	}
	
	$(document).ready(function(){
		
		$(".seeTrigger").fancybox({
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			afterClose:function(){
				location.href="${ctx}/admin/qrtz/job";
			}
		});
		
	});
	
	
</script>

</head>

<body>

<!-- 信息 -->
	<div class="row">
		 <c:if test="${not empty message}">
			<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
		</c:if>
	</div>

<!-- 主要内容 -->
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-10">
					<form class="form-inline" action="#" role="form">
						<div class="form-group">
							<label for="pk.name">任务名称：</label> <input type="text"
								id="pk.name" name="search_LIKE_pk.name" class="form-control input-sm"
								value="${param.search_LIKE_title}">
						</div>
						<button type="submit" class="btn btn-default" id="search_btn">查询</button>
					</form>
				</div>
				<div class="col-md-2">
					<tags:sort />
				</div>
			</div>
		</div>
	</div>
	
	<div class="panel panel-info">
		<div class="panel-heading">任务信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th>名称</th>
					<th>所属组</th>
					<th>下一次触发时间</th>
					<th>Durable</th>
<!-- 					<th>Triggers</th> -->
					<th>状态</th>
					<th>操作</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${jobDetails.content}" var="jobDetail">
					<tr>
						<c:choose>
							<c:when test="${cronTriggerData[jobDetail.idString].name==null || fn:length(cronTriggerData[jobDetail.idString].name)==0}">
								<td>${jobDetail.pk.name}</td>
							</c:when>
							<c:otherwise>
								<td>${cronTriggerData[jobDetail.idString].name}</td>
							</c:otherwise>
						</c:choose>
						<%-- <td>${jobDetail.pk.name}</td> --%>
						
						<td>${jobDetail.pk.group}</td>
						<c:set var="jspData" value="${triggerData[jobDetail.idString] }"></c:set>
						<td>
							<c:if test="${jspData != null}">
								<c:if test="${jspData.nextFireTime != null}">
									<fmt:formatDate value='<%=new Date(((Triggers)pageContext.getAttribute("jspData")).getNextFireTime().longValue()) %>' pattern="yyyy-MM-dd HH:mm:ss"/>
								</c:if>
							</c:if>
						</td>
						<td>
							<c:choose>
								<c:when test="${jobDetail.durability== 1} ">是</c:when>
								<c:otherwise>否</c:otherwise>
							</c:choose>
						</td>
<%-- 						<td>${triggerData2[jobDetail.idString] }</td> --%>
<%-- 						<td>${triggerData[jobDetail.idString].triggerState}</td>	 --%>
						<c:choose>
							<c:when test="${triggerData[jobDetail.idString].triggerState =='WAITING' }">
								<td>等待</td>
								<td>
									<a onclick="schedJob('${jobDetail.idString}')" href="javascript:void(0)">执行</a>
									<a onclick="pauseJob('${jobDetail.idString}')" href="javascript:void(0)">挂起</a>
									<a onclick="stopJob('${jobDetail.idString}')" href="javascript:void(0)">终止</a>
									<a class="seeTrigger" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/trigger/job/trigger/${jobDetail.idString}">查看触发器</a>
								</td>
							</c:when>
							<c:when test="${triggerData[jobDetail.idString].triggerState =='PAUSED' }">
								<td>暂停</td>
								<td>
									<a onclick="resumeJob('${jobDetail.idString}')" href="javascript:void(0)">激活</a>
									<a onclick="stopJob('${jobDetail.idString}')" href="javascript:void(0)">终止</a>
									<a class="seeTrigger" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/trigger/job/trigger/${jobDetail.idString}">查看触发器</a>
								</td>
							</c:when>
							<c:when test="${triggerData[jobDetail.idString].triggerState =='ACQUIRED' }">
								<td>正在执行</td>
								<td>
<%-- 									<a onclick="interruptJob('${jobDetail.idString}')" href="javascript:void(0)">中断</a> --%>
									<a class="seeTrigger" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/trigger/job/trigger/${jobDetail.idString}">查看触发器</a>
								</td>
							</c:when>
							<c:when test="${triggerData[jobDetail.idString].triggerState =='BLOCKED' }">
								<td>阻塞</td>
								<td>
									<a onclick="stopJob('${jobDetail.idString}')" href="javascript:void(0)">终止</a>
									<a class="seeTrigger" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/trigger/job/trigger/${jobDetail.idString}">查看触发器</a>
								</td>
							</c:when>
							<c:when test="${triggerData[jobDetail.idString].triggerState =='COMPLETE' }">
								<td>完成</td>
								<td>
									<a onclick="schedJob('${jobDetail.idString}')" href="javascript:void(0)">执行</a>
									<a class="seeTrigger" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/trigger/job/trigger/${jobDetail.idString}">查看触发器</a>
								</td>
							</c:when>
							<c:otherwise>
								<td>错误</td>
								<td>
									<a onclick="deleteJob('${jobDetail.idString}')" href="javascript:void(0)">删除</a>
									<a class="seeTrigger" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/trigger/job/trigger/${jobDetail.idString}">查看触发器</a>
								</td>
							</c:otherwise>
						</c:choose>
						<td>${jobDetail.description }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="collapse navbar-collapse">
		<div class="nav navbar-nav navbar-right">
			<tags:pagination page="${jobDetails}" paginationSize="5" />
		</div>
	</div>

</body>

</html>