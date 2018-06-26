<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<meta name="decorator" content="nodecorate" />

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<c:forEach items="${persons }" var="person">
	<tr>
			<td class="checkboxTd"><input type="checkbox" value="${person.id}" name="box" /></td>
			
			<td>${person.code}</td>
			<td>${person.name}</td>
			<td><fmt:formatDate value="${person.birthday}" pattern="yyyy-MM-dd"/></td>
			<td><tags:dict dictCode="gender" itemCode="${person.sex}" type="label"/></td>
			<td><tags:dict dictCode="xl" itemCode="${person.degree}" type="label"/></td>
			<td>${person.email}</td>
			<td>${person.phone}</td>
			<td>${person.mobile}</td>
<%-- 			<td><tags:dict dictCode="resourceType" itemCode="${resource.resType}" type="label"/></td> --%>
			<td><a class="" href="javascript:if(confirm('确定要移除吗')){location.href='${ctx}/admin/party/post/deletePerson/${person.id}?id=${id}'}" >移除</a></td>
	 </tr>
</c:forEach>

