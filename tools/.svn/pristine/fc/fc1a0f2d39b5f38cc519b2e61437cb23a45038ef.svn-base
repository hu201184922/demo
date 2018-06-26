<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑链接</title>
</head>
<script type="text/javascript">
	$(function() {
		$("[ref=submit]").click(function(){
			var href = $("#href").val().trim();
			if(stringUtils.isEmpty(href)){
				$("#hrefStr").css("display","");
				return;
			}else if(stringUtils.getStrLength(href) > 4000){
				$("#hrefStr").css("display","");
				$("#hrefStr").html("链接长度过长!");
				return;
			}
			$("#roleForm").submit();
		});
		$('#roleForm').ajaxForm(function() {
			$.msgbox({
				time : 2000,
				msg : "链接保存成功!",
				icon : "success"
			});
			$('#diaClose').click();
		});
	});
</script>
<body>
	<a id="diaClose" class="close" style="display: none;"></a>
	<form id="roleForm" action="${pageContext.request.contextPath}/admin/sec/role/update" method="post">
	<input type="hidden" name="id" value="${role.id }"/>
	<textarea id="href" name="href" rows="20" cols="100">${role.href }</textarea>
	<span id="hrefStr" style="display:none;color:red">链接不能为空！</span>
	<div class="cell_list_div"><input ref="submit" class="search_btn" value="保 存" style="*margin-left:300px;"></div>
	</form>
</body>
</html>