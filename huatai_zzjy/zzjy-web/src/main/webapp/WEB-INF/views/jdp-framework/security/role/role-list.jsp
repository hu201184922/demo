<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>任务管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row">
		<div class="span6 offset5">
			<form class="form-search" action="#">
				<label for="title">名称：</label> <input type="text" id="title" name="search_LIKE_title" class="input-medium" value="${param.search_LIKE_title}"> 
				<label for="description">描述：</label> <input type="text" id="description" name="search_LIKE_description" class="input-medium" value="${param.search_LIKE_description}"> 
				<button type="submit" class="btn" id="search_btn">查询</button>
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>任务</th><th>描述</th><th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${tasks.content}" var="task">
			<tr>
				<td><a href="${ctx}/task/update/${task.id}">${task.title}</a></td>
				<td>${task.description}</td>
				<td><a href="${ctx}/task/delete/${task.id}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<tags:pagination page="${tasks}" paginationSize="5"/>

	<div><a class="btn" href="${ctx}/task/create">创建任务</a></div>
</body>
</html>
