<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>异常日志管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-10">
					<form class="form-inline" action="#" role="form">
						<div class="form-group">
							<label for="loginName">登录用户：</label> <input type="text"
								id="loginName" name="search_LIKE_loginName" class="form-control input-sm"
								value="${param.search_LIKE_title}">
						</div>
						<div class="form-group">
							<label for="org">组织部门：</label> <input type="text" id="org" name="search_LIKE_org"
								class="form-control input-sm" value="${param.search_LIKE_org}">
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
		<div class="panel-heading">异常日志信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive" style="table-layout:fixed; word-break:break-all">
			<thead>
				<tr>
					<th>登录用户</th>
					<th>组织部门</th>
					<th>操作系统</th>
					<th width="12%">浏览器</th>
					<th>会话ID</th>
					<th>异常时间</th>
					<th>访问服务器</th>
					<th>访问URL</th>
					<th width="30%">异常信息</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${logs.content}" var="log">
					<tr>
						<td>${log.loginName}</td>
						<td>${log.org}</td>
						<td>${log.osVersion}</td>
						<td>${log.browserVersion}</td>
						<td>${log.sessionId}</td>
						<td>${log.time}</td>
						<td>${log.serverIp}</td>
						<td>${log.url}</td>
						<td>${log.errorMsg}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="collapse navbar-collapse">
		<div class="nav navbar-nav navbar-right">
			<tags:pagination page="${logs}" paginationSize="5" />
		</div>
	</div>
</body>
</html>
