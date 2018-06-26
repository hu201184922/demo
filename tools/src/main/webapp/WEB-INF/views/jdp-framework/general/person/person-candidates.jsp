<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<meta name="decorator" content="nodecorate" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="panel panel-info">
		<div class="panel-heading">岗位信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th>名称</th>
					<th>性别</th>
					<th>所属部门</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${persons}" var="person">
					<tr>
						<td>${person.name}</td>
						<td><tags:dict itemCode="${person.sex}" dictCode="gender" type="label"/></td>
						<td>${person.org.name}</td>
						<td><a class="candidate" pid="${person.id }" pname="${person.name }">选中</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>