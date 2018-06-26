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
	<nav class="navbar navbar-default" role="navigation">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="${ctx}/admin/cachemgmt/delete">清空所有缓存</a></li>
				<li><a id="remove" class="btn btn-default" href="#">删除选定缓存</a></li>
			</ul>
		</div>
	</nav>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-10">
					<form class="form-inline" action="#" role="form">
						<div class="form-group">
							<label for="search_cacheName">缓存名称：</label> <input type="text" id="search_cacheName" name="search_cacheName"
								class="form-control input-sm" value="${param.search_cacheName}">
						</div>
						<div class="form-group">
							<label for="search_cacheKey">缓存Key：</label> <input type="text" id="search_cacheKey" name="search_cacheKey"
								class="form-control input-sm" value="${param.search_cacheKey}">
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
		<div class="panel-heading">缓存信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>用户名称</th>
					<th>缓存Key</th>
					<th>缓存对象</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cacheModels}" var="cache">
					<tr>
						 <td><input type="checkbox"
							value="${cache.cacheName}_${cache.key}" name="box" /></td> 
							
						<td>${cache.cacheName}</td>
						<td>${cache.key}</td>
						<td>${cache.value}</td>
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

		$("#remove").click(function() {
			var arr = "";
			$("td :input[type='checkbox']").each(function() {
				if (this.checked == true) {
					var rs = this.value;
					arr = arr + rs + ",";
				}
			});
			if (arr.length == 0) {
				alert("您还没有选择要删除的对象");
			} else {
				arr = arr.substring(0, arr.length - 1);
				location.href = "${ctx}/admin/cachemgmt/delete/" + arr;
				//location.reload(true);
			}
		});
	</script>
</body>
</html>
