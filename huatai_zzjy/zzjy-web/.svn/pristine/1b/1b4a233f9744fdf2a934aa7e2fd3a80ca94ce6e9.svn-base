<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>用户组管理</title>
</head>
<body>
<form id="inputForm" action="${ctx}/admin/sec/group/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${group.id}"/>
		<input type="hidden" name="action" id="action" value="${action}"/>
		<input type="hidden" name="initalname" id="initalname" value="${group.name}"/>
		<fieldset>
			<legend><small>用户组信息</small></legend>
			<div class="control-group">
				<label for="code" class="control-label">用户组标识:</label>
				<div class="controls">
					<input type="text" value="${group.code}" class="input-large required" name="code" id="code"/>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label">用户组名称:</label>
				<div class="controls">
					<input type="text" id="name" name="name" value="${group.name}" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="roleId" class="control-label">角色:</label>
				<div class="controls">
					<select name="roleId" id="roleId" multiple="multiple">
					<c:forEach var="role" items="${roles}">
					<option value="${role.id}">${role.name}</option>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#inputForm").validate();
	});
	$("#inputForm").validate({
		rules:{
			name:{
	         remote:{
	        	    url:"${ctx}/admin/sec/group/check",
					type:"post",
					dataType: "json", 
					data:{
						name:function(){
							return $("#name").val();
						},
						initalname:function(){
							return $("#initalname").val();
						}
					}
	         }
			}
		}
	});
	</script>
</body>
</html>