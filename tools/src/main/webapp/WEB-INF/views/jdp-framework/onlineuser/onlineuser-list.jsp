<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>缓存管理</title>
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
		<div class="panel-heading">会话信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>会话ID</th>
					<th>登录帐户</th>
					<th>用户名称</th>
					<th>组织机构</th>
					<th>主机</th>
					<th>开始时间</th>
					<th>最后访问时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${onlineUsers}" var="onlineUser">
					<tr>
						<td><input type="checkbox" value="${onlineUser.sessionId}"
							name="box" /></td>
						<td>${onlineUser.sessionId}</td>
						<td>${onlineUser.loginName}</td>
						<td>${onlineUser.name}</td>
						<td>${onlineUser.org}</td>
						<td>${onlineUser.host}</td>
						<td>${onlineUser.startTimestamp}</td>
						<td>${onlineUser.lastAccessTime}</td>
						<td><a
							href="${ctx}/admin/onlineuser/forcelogout/${onlineUser.sessionId}">强制注销</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<script>
		$("#selectAll").click(function() {
			var obj = document.getElementById("selectAll");
			var value = obj.checked;
			if (value == true) {
				$("td :input").each(function() {
					this.checked = true;
				});
			} else {
				$("td :input").each(function() {
					this.checked = false;
				});
			}
		});
	</script>
</body>
</html>
