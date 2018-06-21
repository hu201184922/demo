<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta name="decorator" content="admin-popup" />

<html>
<head>
	<title>密码修改</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/admin/profile/modifyPassword" method="post" class="form-horizontal">
		<input type="hidden"id="user_id" name="id" value="${user.id}"/>
		<div class="panel panel panel-info">
			<div class="panel-heading">修改密码</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="oldPassword" class="col-md-2 control-label">老密码:</label>
					<div class="col-md-4">
						<input type="password" id="oldPassword" name="oldPassword" class="form-control input-sm required" />
					</div>
				</div>
				<div class="form-group">
					<label for="newPassword" class="col-md-2 control-label">新密码:</label>
					<div class="col-md-4">
						<input type="password" id="newPassword" name="newPassword" class="form-control input-sm required" />
					</div>
						
				</div>
	
				<div class="form-group">
					<label for="confirmPassword" class="col-md-2 control-label">确认密码:</label>
					<div class="col-md-4">
						<input type="password" id="confirmPassword" name="confirmPassword" class="form-control input-sm required" equalTo="#newPassword" />		
					</div>
				</div>
			</div>
			<div class="panel-footer">
			     <div class="form-actions col-md-offset-2">
                   <input id="submit_btn" class="btn btn-default" type="button" value="提交" onclick="save()"/>	
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
		

	function save(){

		var za=$("#inputForm").validate();
    	if(za.form()){	
		    $.ajax(
		    		{
			     type : "post",
			     data : {
			    	 userId:$("#user_id").val(),
			    	 newPassword:$("#newPassword").val(),
			    	 oldPassword:$("#oldPassword").val()
			    	 },
			     url : "${ctx}/admin/profile/modifyPassword" ,
			   success:function(data){
				   if(data){
					   alert("修改成功");
				   parent.$.fancybox.close();
				   }else{
					   alert("修改失败");
				   }
				   parent.$.fancybox.close();
			   }
			     });
          
    	}else
    		$("#inputForm").submit();
	}
	</script>
</body>
</html>
