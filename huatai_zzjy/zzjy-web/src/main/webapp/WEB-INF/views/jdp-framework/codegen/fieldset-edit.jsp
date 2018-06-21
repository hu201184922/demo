<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>字段集管理</title>
<meta name="decorator" content="admin-popup">
</head>

<body>
	<form id="inputForm" action="${ctx}/admin/codegen/fieldset/${action}"
		method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${fieldSet.id}" />
		<div class="panel panel panel-info">
			<!-- head -->
			<div class="panel-heading">字段集管理</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="name" class="col-md-2 control-label">名称</label>
					<div class="col-md-4">
						<input type="text" id="name" name="name"
							class="form-control input-sm required" value="${fieldSet.name}"> <span
							id="nameTip"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="className" class="col-md-2 control-label">domain类名:</label>
					<div class="col-md-4">
						<input type="text" id="className" name="className"
							class="form-control input-sm required" value="${fieldSet.className}">
					</div>
				</div>
				<div class="form-group">
					<label for="tableName" class="col-md-2 control-label">数据库表名:</label>
					<div class="col-md-4">
						<input type="text" id="tableName" name="tableName"
							class="form-control input-sm required" value="${fieldSet.tableName}">
					</div>
				</div>
				<div class="form-group">
					<label for="entityName" class="col-md-2 control-label">Entity名称:</label>
					<div class="col-md-4">
						<input type="text" id="entityName" name="entityName"
							class="form-control input-sm" value="${fieldSet.entityName}">
					</div>
				</div>
				<div class="form-group">
					<label for="description" class="col-md-2 control-label">描述信息:</label>
					<div class="col-md-4">
						<textarea id="description" name="descript" class="form-control input-sm">${fieldSet.descript}</textarea>
					</div>
				</div>
			</div>

			<!-- footer -->
			<div class="panel-footer">
				<div class=" col-md-offset-2">
					<input class="btn btn-default" type="submit" value="保存并新增" /> <input
						class="btn btn-default" type="button" value="保存并关闭"
						onclick="saveFieldSet()" /> <input type="button" class="btn btn-default"
						value="关闭" onclick="closeChange()" />
				</div>
			</div>
		</div>
	</form>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
		function saveFieldSet() {
			var validator = $("#inputForm").validate();
		    if (validator.form()) {//判断加入所有校验都通过后再做ajax提交；
		    	$.ajax({
					type : "post",
					data : $("#inputForm").serialize(),
					url : "${ctx}/admin/codegen/fieldset/${action}" ,
					success : function(msg) {
						parent.$.fancybox.close();
					}
				});
		    }else
		    	$("#inputForm").submit();
		};
		function closeChange() {
			parent.$.fancybox.close();
		};
	</script>
</body>
</html>
