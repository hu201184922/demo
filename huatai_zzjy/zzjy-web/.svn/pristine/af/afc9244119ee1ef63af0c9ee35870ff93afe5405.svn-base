<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="decorator" content="admin-popup">
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>首选项定义</title>

<script type="text/javascript">
	$(document).ready(function(){
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputform").validate();
	});
	function saveCategory() {
		var validator = $("#inputform").validate();
	    if (validator.form()) {//判断加入所有校验都通过后再做ajax提交；
	    	$.ajax({
				type : "post",
				data : $("#inputform").serialize(),
				url : "${ctx}/admin/preference/def/category/save" ,
				success : function(msg) {
					parent.$.fancybox.close();
				}
			});
	    }
	}
</script>

</head>
<body>
<div>
	<form id="inputform" class="form-horizontal">
		<input type="hidden" name="id" value="${preferenceCategory.id}"/>
		<div class="panel panel panel-info">
			<!-- head -->
			<div class="panel-heading">分类信息</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="name" class="col-md-2 control-label">名称*</label>
					<div class="col-md-4">
						<input id="name" type="text" name="name" class="form-control input-sm required" value="${preferenceCategory.name}">
					</div>
				</div>
				
				<div class="form-group">
					<label for="code" class="col-md-2 control-label">code*</label>
					
					<div class="col-md-4">
						<input id="code" type="text"name="code" class="form-control input-sm required" value="${preferenceCategory.code} ">
					</div>
				</div>
				
				<div class="form-group">
					<label for="dictionaryItem_remark" class="col-md-2 control-label">备注</label>
					<div class="col-md-4">
						<textarea name="descript" id="dictionaryItem_remark" class="form-control input-sm" >${preferenceCategory.descript}</textarea>
					</div>
				</div>
			</div>
			<!-- footer -->
			<div class="panel-footer">
				<div class=" col-md-offset-2">
					<input class="btn btn-default" type="button" value="保存" onclick="saveCategory()"/>
				</div>
			</div>
		</div>
	</form>
</div>
	<script type="text/javascript">
		
	</script>

</body>
</html>