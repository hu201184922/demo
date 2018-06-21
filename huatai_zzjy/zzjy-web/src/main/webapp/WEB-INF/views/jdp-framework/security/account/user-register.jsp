<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="admin-popup">
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
</head>
<body>
	<div class="col-md-12">
		<form id="inputForm" action="${ctx}/admin/sec/user/${action}"
			method="post" class="form-horizontal">
			<div class="panel panel panel-info">
				<!-- head -->
				<div class="panel-heading">用户管理</div>
				<div class="panel-body">
					<input type="hidden" name="id" value="${user.id}" /> <input
						type="hidden" name="action" id="action" value="${action}" />
					<div class="form-group">
						<label for="loginName" class="col-md-3 control-label">登录名:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm required" id="account"
								name="account" value="${user.account}" />
						</div>
						<span id="loginNameTip"></span>
					</div>

					<div class="form-group">
						<label for="name" class="col-md-3 control-label">用户名:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm required" id="userName"
								name="userName" value="${user.userName}" />
						</div>
					</div>
				</div>
				<div class="panel-footer">
						<div class=" col-md-offset-2">
							<input id="submit_btn" class="btn btn-default submit_btn" type="submit"
								value="保存并添加"/>&nbsp;<input id="add_btn"
								class="btn btn-default submit_btn" type="button" value="保存并关闭"
								 onclick="save()"/>&nbsp;<input id="cancel_btn" class="btn btn-default"
								type="button" value="关闭" onclick="goBack()" />
						</div>
				</div>
		</form>
		</div>



	<script>

		function goBack() {
			 			parent.$.fancybox.close();
			//parent.location.reload();
		}
		
		function save(){
			var validator = $("#inputForm").validate();
			/* 判断加入所有校验都通过后再做ajax提交； */
		    if (validator.form()) {
		    	$.ajax({
					type : "post",
					data : $("#inputForm").serialize(),
					url : "${ctx}/admin/sec/user/${action}" ,
					success : function(msg) {
						//parent.location.reload();
						parent.$.fancybox.close();
					}
				});
		    }else
		    	$("#inputForm").submit();
		}
		
// 		$("#submit_btn").click(function() {
// // 			var roles = $("#roles_id").val();
// // 			$("#roleIds").val(roles);
// 			$("#inputForm").submit();
// 		});
		$(document).ready(
				function() {
					//聚焦第一个输入框
					$("#account").focus();
					//为inputForm注册validate函数
					$("#inputForm").validate({
						rules : {
							account : {
								required : true,
								remote : {
									url : "${ctx}/admin/sec/user/check",
									type : "post",
									dataType : "json",
									data : {
										account : function() {
											return $("#account").val();
										},
										initalname : "${user.account}"
									}
								}

							}
						}
					});

				});
	</script>
</body>
</html>
