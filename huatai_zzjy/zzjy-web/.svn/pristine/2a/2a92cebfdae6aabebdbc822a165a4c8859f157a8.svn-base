<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<c:set var="ctx" value="${ pageContext.request.contextPath }" />
<meta name="decorator" content="admin-popup" />
<html>
<head>
<title>执行结果</title>

</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>
			<font color="red">${message}</font>
		</div>
	</c:if>
<%-- 	<input type="hidden" id="columnNameList" value="${columnNames }"/> --%>
<%-- 	<input type="hidden" id="columnValueList" value="${columnValues }"/> --%>
	<div class="panel panel-info" >
		<div class="panel-heading">运行结果</div>
		<div style="overflow: auto; height: 500px;">
		<table id="contentTable" 
			class="table table-condensed table-hover table-striped table-responsive" >
			<thead>
				<tr>
					<c:forEach var="columnName" items="${columnNames }">
						<th>${columnName }</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody >
				<c:choose>
					<c:when test="${fn:length(columnNames) gt 1}">
						<c:forEach var="columnValue" items="${columnValues }">
						<tr>
							<c:forEach var="number" items="${numbers }">
								<td>${columnValue[number] }</td>
							</c:forEach>
						</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="columnValue" items="${columnValues }">
							<tr>
								<td>${columnValue }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>