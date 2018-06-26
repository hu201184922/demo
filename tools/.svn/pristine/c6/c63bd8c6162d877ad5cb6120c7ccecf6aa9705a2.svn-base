<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>修改密码</title>
</head>
<body>
	<div class="new_jihui_menu new_jihui_menu_a" style="float: inherit;">
	
		<div class="new_common_bg">
			<span style="float: left;">修改密码</span>
		</div>
		
		<div class="new_jihui">
			<form class="form-horizontal" role="form" id="changePswForm" action="${ctx}/changepsw/save" method="post">
				<input type="hidden" name="id" value="${user.id}" />
				<input type="hidden" name="account" value="${user.account}" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="16%" align="right" valign="middle"><b>账号：</b></td>
						<td width="26%" align="left" valign="middle">
							<input type="hidden" id="userName" name="userName" value="${user.userName}"
									class="business_search_input  w250"/>
									${user.account}
					</tr>
					<tr>
						<td style="float: right;"><b>旧密码:</b></td>
						<td><input type="password" id="oldPassword" name="oldPassword"
								class="business_search_input  w250" /></td>
							<td style="float: left;"></td>
					</tr>
					<tr>
						<td style="float: right;"><b>新密码:</b></td>
						<td><input type="password" id="plainPassword" name="plainPassword"
								class="business_search_input  w250" /></td>
							<td style="float: left;"></td>
					</tr>
					<tr>
						<td style="float: right;"><b>确认密码:</b></td>
						<td><input type="password" id="confirmPassword" name="confirmPassword"
								class="business_search_input  w250" /></td>
							<td style="float: left;"></td>
					</tr>
				</table>
				
			</form>
		</div>

			
	</div>
	
	<div class="new_jihui_menu new_jihui_menu_a" style="float: inherit;">
		<a ref="submit" id="saveCampaign" href="javascript:void(0);"
			class="new_jihui_menu1">提交</a>
	</div>
<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#oldPassword").focus();
		$("[ref=submit]").click(function(){
			$("#changePswForm").submit();
		});
		$.formValidator.initConfig({
			formID : "changePswForm",
			onError : function(msg) {
				$.alert(msg);
			}
		});
		var passRegex = "^(?!^[0-9]+$)(?!^[A-Za-z]+$)(?!^[!@#$%^&*?<_>:;{}.,”]+$)[a-zA-Z0-9!@#$%^&*?<_>:;{}.,”]*$";
		$("#plainPassword").formValidator().inputValidator({
			min : 1,
			onError : "用户密码不能为空,请确认!"
		}).functionValidator({
			fun : function() {
				if ($("#plainPassword").val().length < 8) {
					return "密码长度不能小于8位!";
				}
			}
		});;
		$("#confirmPassword").formValidator().functionValidator({
			fun : function() {
				if($("#confirmPassword").val()!=$("#plainPassword").val()){
					return "密码与确认密码必须一致！";
				}
				if(!RegExp(passRegex).test($("#plainPassword").val())){
					return "密码格式不正确，必须包含字母,数字,特殊字符的其中两种";
				}
				return true;
			}
		});
		$("#changePswForm").ajaxForm(function(data){
			$.alert(data);
			if(data == "修改成功"){
				window.location.href = "${ctx }/index";
			}
		});
	});
</script>
</body>
</html>