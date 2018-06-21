<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户[${user.account}]授权</title>
</head>
<script type="text/javascript">
	$(function() {
		$('#userSearchForm').ajaxForm(function() {
			$.msgbox({
				time : 2000,
				msg : "用户授权成功!",
				icon : "success"
			});
			$('#diaClose').click();
		});

	});
</script>
<body>
	<a id="diaClose" class="close" style="display: none;"></a>
	<form id="userSearchForm" action="${pageContext.request.contextPath}/admin/user/updateauth" method="post">
		<input type="hidden" name="id" value="${user.id}" />
		<ul style="width: 100%;" id="tags2" tabs="{selectedClass:'selectTag'}">
			<li class="selectTag" tab="#tagContent0"><a href="javascript:void(0)">用户组</a></li>
		</ul>
		<div id="tagContent0" class="cell_list  tagContent tagContent_div selectTag" style="width: 650px; padding: 0px 0px 10px; border: 1px solid #ccc;float:left;">
			<div class="cell_list_rightdiv" style="width: 100%;float:left;">
				<ul style="margin: 10px; border: 0px;">
					<c:forEach var='role' items='${roles}' varStatus="item">
						<li class="break_words_li" style="width: 50%;float:left;line-24px;height:24px;">
						<span  style="float:left;">
						<input type="checkbox" class="checkbox" name="roleIds" 
						value="${role.id}" <c:if test="${u:exists(matchRoles,role)}">checked="checked"</c:if> />&nbsp;</span> 
						<span style="float:left;line-height:21px;">${role.name}</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<Div class="clear"></Div>
	<div class="cell_list_div"><input type="submit" class="search_btn" value="保 存" style="*margin-left:300px;margin-top:10px;"></div>
	</form>
</body>
</html>