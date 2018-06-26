<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta name="decorator" content="admin-popup">

<html>
<head>
	<title>字段集管理</title>
</head>

<body>
<div class="col-md-12">
	<form id="inputForm" action="${ctx}/admin/codegen/${action}/${fieldSetId}" method="post" class="form-horizontal">
		<div class="panel panel-info">
			<div class="panel-heading">代码生成信息设置</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="templateDir" class="control-label col-md-3">模板套件（文件夹名）:</label>
					<div class="col-md-6">
						<input type="text" id="templateDir" name="templateDir" class="form-control input-sm" value="general"/>
					</div>
				</div>	
				
				<div class="form-group">
					<label for="templates" class="control-label col-md-3">构建文件的模板集:</label>
					<div class="col-md-6">
						<input type="text" id="templates" name="templates" class="form-control input-sm"/>
					</div>
				</div>	
			</div>
			<div class="panel-footer">
				<div class="col-md-offset-3">
					<input id="submit_btn" class="btn btn-default" type="button" value="提交" onclick="save()"/>
					<input id="cancel_btn" class="btn btn-default" type="button" value="返回" onclick="javascript:parent.$.fancybox.close();"/>
				</div>
			</div>
		</div>
	</form>
</div>
	<script>
		function save(){
			var validator = $("#inputForm").validate();
			if(validator.form()){
				$.ajax({
					url		: "${ctx}/admin/codegen/${action}/${fieldSetId}",
					type	: "post",
					data	: $("#inputForm").serialize(),
					success	: function(msg){
						parent.$.fancybox.close();
					},
					error	: function(XMLHttpRequest, textStatus, errorThrown){
						document.body.innerHTML = XMLHttpRequest.responseText;
					}
				});
			}
		}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
