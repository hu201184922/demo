<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="decorator" content="admin-popup">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<title>账号设置</title>

<script type="text/javascript">
function closeChange(){
	parent.$.fancybox.close();
};
</script>
</head>

	
	<body>
	<form id="definitionForm" action="${ctx}/admin/profile/settings/save" method="post" 
	 class="form-horizontal">

		<div class="panel panel panel-info">
			<div class="panel-heading">账号设置</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="name" class="col-md-2 control-label">账号:</label>
					<div class="col-md-4">
						<input id="loginName" type="text" name="loginName" class="form-control input-sm required" value="${user.loginName}" readonly/>
					</div>
				</div>
				<div class="form-group">
					<label for="code" class="col-md-2 control-label">用户名:</label>
					<div class="col-md-4">
						<input id="name" type="text"name="name" class="form-control input-sm" value="${user.name}">
					</div>
				</div>
				
				</div>
				
				<!-- footer -->
				<div class="panel-footer">
					<div class=" col-md-offset-2">
						<input class="btn btn-default" type="submit"
							value="保存"/> 
						<input type="button" class="btn btn-default"
							value="关闭" onclick="closeChange()" />
					</div>
				</div>
				
			
			</div>
		</div>
	</form>
</body>
</html>
