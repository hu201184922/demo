<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<hmtl>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	$(function(){
		 $("#pager").pager("${pageContext.request.contextPath}/test/getPager",{name:'ff'},15,$("#pagerView").view());
		 $("#pager").pager().setPostData({"aaa":'ssss'})
		 $("#pager").pager().reload({"xxx":"zzz"});
	});

	</script>
	</head>
	<body>
		<div class="business_title">菜单管理</div>
		<div class="col_lg_04">
		<div class="business_search_list_warp" style="width:95%">
		<form id="pagerFrom" method="post" action="${pageContext.request.contextPath}/test/index">
	          <table cellspacing="0" cellpadding="0" class="t-list table">
			   <tbody>
			   <tr>
				  <th class="sort <c:if test="${pager.orderBy=='name'}">${pager.order}</c:if>" orderBy="name">名称</th>
				  <th class="sort <c:if test="${pager.orderBy=='resString'}">${pager.order}</c:if>" orderBy="resString">resString</th>
				  <th class="sort <c:if test="${pager.orderBy=='perString'}">${pager.order}</c:if>" orderBy="perString">perString</th>
			   </tr>
			   <c:forEach items="${pager.pageItems}" var="item">
				<tr class="tb_tr_content">
					<td>${item.name}</td>
					<td>${item.resString}</td>
					<td>${item.perString}</td>
				</tr>
				</c:forEach>
			</tbody>
			</table>
				<input name="currentPage" type="hidden" value="${pager.currentPage}"/>
				<input name="orderBy" type="hidden" value="${pager.orderBy}"/>
				<input name="order" type="hidden" value="${pager.order}"/>
		</form>
		<ace:pager page="${pager}" formId="pagerFrom"/>
	    </div>
	    <div class="clear"></div>
	    </div>
	     <table cellspacing="0" cellpadding="0" class="t-list table" id="pagerView">
			   <tbody>
			   <tr>
				  <th class="sort" orderBy="name">名称</th>
				  <th>resString</th>
				  <th>perString</th>
			   </tr>
				<tr class="tb_tr_content template" name="default">
					<td>{name}</td>
					<td>{resString}</td>
					<td>{perString}</td>
				</tr>
			</tbody>
			</table>
			<div id="pager"></div>
	</body>
</hmtl>