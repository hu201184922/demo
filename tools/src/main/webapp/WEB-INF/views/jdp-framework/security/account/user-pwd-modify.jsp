<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta name="decorator" content="admin-popup" />
<script type="text/javascript" src="${ctx }/static/lib/jdp/jquery/jquery.multi-select.js"></script>
<link href="${ctx }/static/lib/jdp/jquery/css/multi-select.css" rel="stylesheet">
<div class="col-md-12">
<form action="${ctx}/admin/sec/user/${action}" id="inputForm" method="post" class="form-horizontal">
<div class="panel panel-info">
	<div class="panel-heading">修改密码</div>
	<div class="panel-body">
		<input type="hidden" name="id" value="${user.id}" /> <input
				type="hidden" name="action" id="action" value="${action}" /> 
		<div class="form-group">
			<label for="password" class="col-md-3 control-label">密码:</label>
			<div class="col-md-4">
				<input type="password" class="form-control input-sm required" id="password"
					name="password">
			</div>
		</div>
		<div class="form-group">
			<label for="confirmPassword" class="col-md-3 control-label">确认密码:</label>
			<div class="col-md-4">
				<input type="password" class="form-control input-sm required"
					id="confirmPassword" name="confirmPassword">
			</div>
		</div>
		<div class="panel-info">
			<div class=" col-md-offset-2">
				<span id="passwordTip"></span>
			</div>
		</div>
		</div>
		<div class="panel-footer">
			<div class=" col-md-offset-5">
				<input id="submit_btn" class="btn btn-default" type="button"
					value="提交" onclick="checkpwd()" />
			</div>
		</div>
</div>
</form>
</div>
<script type="text/javascript">
function checkpwd(){
	if($("#password").val()!=$("#confirmPassword").val()){
		$("#passwordTip").html("<font color=\"red\">您两次输入的密码不同，请重输</font>");
		return false;
	}else{
		var values = $("#inputForm").serialize();
		$.ajax({
			type : "post",
			url : "${ctx}/admin/sec/user/${action}",
			data : values,
			success : function(msg) {
				parent.location.reload();
// 				parent.$.fancybox.close();
			}
		});
	}
}
</script>