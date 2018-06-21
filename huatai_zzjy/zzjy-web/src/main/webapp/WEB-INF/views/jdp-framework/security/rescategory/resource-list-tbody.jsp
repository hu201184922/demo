<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<meta name="decorator" content="nodecorate" />

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body>
<div class="panel-heading">资源列表</div>
<table id="contentTable"
	class="table table-condensed table-hover table-striped table-responsive">
	<thead>
		<tr>
			<th><input type="checkbox" id="selectAll" /></th>
			<th>名称</th>
			<th>类型</th>
			<th>唯一字符串</th>
			<th>permission字符串</th>
			<th>是否可用</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody class="td">
		<c:forEach items="${resources }" var="resource">
			<tr>
				<td class="checkboxTd"><input type="checkbox"
					value="${resource.id}" name="box" /></td>
				<td>${resource.name}</td>
				<td><tags:dict dictCode="resourceType"
						itemCode="${resource.resType}" type="label" /></td>
				<td>${resource.resString}</td>
				<td>${resource.perString}</td>
				<td><c:if test="${resource.enabled  eq true}">可用</c:if> <c:if
						test="${resource.enabled  eq false}">不可用</c:if></td>
				<td>${resource.descript}</td>
				<td><a class="editResource" data-fancybox-type="iframe"
					href="${ctx}/admin/sec/rescategory/updateResource/${resCateGoryId}/${resource.id}">编辑</a>
					<a class=""
					href="javascript:if(confirm('确定要删除吗')){location.href='${ctx}/admin/sec/rescategory/deleteResource/${resource.id}?categoryId=${resCateGoryId}'}">删除</a>
					<a class="resource2Role" data-fancybox-type="iframe"
					href="${ctx}/admin/sec/rescategory/resource2RoleView/${resource.id}">分配给角色</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>