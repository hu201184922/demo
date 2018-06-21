<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta name="decorator" content="admin-popup">
	<title>日志配置管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/admin/logcfg/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${hyLogConfig.id}"/>
		<div class="panel panel-info">
		
			<div class="panel-heading">日志配置管理</div>
			<div class="panel-body">
			<div class="form-group">
				<label for="name" class="control-label col-md-2">名称:</label>
				<div class="col-md-4">
					<input type="text" id="name" name="name" value="${hyLogConfig.name}" class="form-control input-sm required"/>	
				</div>
			</div>	
			<div class="form-group">
				<label for="resString" class="control-label col-md-2">资源字符串:</label>
				<div class="col-md-4">
					<input type="text" id="resString" name="resString"  
						value="${hyLogConfig.resString}" class="form-control input-sm  required"/>
				</div>
			</div>	
			<div class="form-group">
				<label for="method" class="control-label col-md-2">HTTP Method:</label>
				<div class="col-md-4">
					 <input type="radio" id="isGet" name="method" value="GET" <c:if test="${hyLogConfig.method=='GET'}">checked</c:if>/><label for="isGet">Get</label>
					 <input type="radio" id="isPost" name="method" value="POST" <c:if test="${hyLogConfig.method=='POST'}">checked</c:if> /><label for="isPost">Post</label>
					 <input type="radio" id="isPut" name="method" value="PUT" <c:if test="${hyLogConfig.method=='PUT'}">checked</c:if>/><label for="isPut">Put</label>
					 <input type="radio" id="isDelete" name="method" value="DELETE" <c:if test="${hyLogConfig.method=='DELETE'}">checked</c:if>/><label for="isDelete">Delete</label>
				</div>
			</div>	
			
			<div class="form-group">
				<label for="enable" class="control-label col-md-2">启用日志:</label>
				<div class="col-md-4">
					<c:choose>
						 <c:when test="${hyLogConfig.enable }">
						 <input type="radio" id="isenable" name="enable" value="1" checked/><label for="isenable">是</label>
						 <input type="radio" id="noenable" name="enable" value="0"/><label for="noenable">否</label>
						 </c:when>
						 <c:otherwise>
						 <input type="radio" id="Isenable" name="enable" value="1"/><label for="Isenable">是</label>
						 <input type="radio" id="Noenable" name="enable" value="0"  checked/><label for="Noenable">否</label>
						 </c:otherwise>
					 </c:choose>
				</div>
			</div>	
			<div class="form-group">
				<label for="descript" class="control-label col-md-2">备注:</label>
				<div class="col-md-4">
					<input type="textarea" id="descript" name="descript"  
						value="${hyLogConfig.descript}" class="form-control input-sm  required"/>
				</div>
			</div>	
			</div>
			<div class="panel-footer">
				<div class="col-md-offset-2">
					<input id="submit_btn" class="btn btn-default" type="button" onclick="sub()" value="提交"/>&nbsp;	
					<input id="cancel_btn" class="btn btn-default" type="button" value="关闭" onclick="closeChange()"/>
				</div>
			</div>
		</div>
	</form>
	<script>
	function sub(){
		var validator = $("#inputForm").validate();
		if(validator.form()){
			var values = $("#inputForm").serialize();
			$.ajax({
				type : "post",
				data : values,
				url : "${ctx}/admin/logcfg/${action}" ,
				success : function(msg) {
					parent.location.reload();
				}
			});
		}
	}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#resString").focus(); 
			
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules:{
					resString:{
						
						
						
						
						
					},
					name:{
						
						
						
						
						
					},
					enable:{
						
						
						
						
						
					},
					descript:{
						
						
						
						
						
					},
				}
			});
		});
		function closeChange(){
			parent.$.fancybox.close();
		};
	</script>
</body>
</html>
