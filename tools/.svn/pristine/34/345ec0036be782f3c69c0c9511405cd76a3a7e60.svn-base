<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="ctx" value="${ pageContext.request.contextPath }" />
<meta name="decorator" content="admin-popup" />
<html>
<head>
<title>日志</title>
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
</head>
<body>
	<div class="panel panel-info" style="width:100%; height:500px; overflow:auto">
		<div class="panel-heading">日志信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive" >
			<thead>
				<tr>
					<th>操作人</th>
					<th>操作时间</th>
					<th>操作SQL语句</th>
					<th>操作类型</th>
					<th>操作影响</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="execSqlLog" items="${execSqlLogs }">
					<tr>
						<td>${execSqlLog.executeUser.name }</td>
						<td>${execSqlLog.executeDate }</td>
						<td>${execSqlLog.sql }</td>
						<td>${execSqlLog.type }</td>
						<td style="word-wrap:break-word;word-break:break-all;overflow:hidden;">${execSqlLog.influence }</td>
					</tr>
				</c:forEach>
			</tbody>
      	</table>
	</div>
      	
</body>
</html>