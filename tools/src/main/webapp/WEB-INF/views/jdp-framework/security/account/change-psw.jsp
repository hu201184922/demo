<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>修改密码</title>
</head>
<body>
	
	<div class="new_jihui_menu new_jihui_menu_a" style="float: inherit;">
	
		<div class="new_common_bg">
			<span style="float: left;">首次登录，请修改默认密码</span>
		</div>
		
		<div class="new_jihui">
			<form class="form-horizontal" role="form" id="changePswForm" action="${ctx}/admin/sec/user/changepsw" method="post">
				<input type="hidden" name="currentPath" value="${rdictCurrentPath}">
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

<!-- <div class="row"> -->
<!-- 	<div class="col-md-6 col-md-offset-3"> -->
<%-- 		<tags:message /> --%>
<!-- 		<div class="panel panel-primary"> -->
<!-- 			<div class="panel-heading"> -->
<!-- 				首次登录，请修改默认密码 -->
<!-- 			</div> -->
<!-- 			<div class="panel-body"> -->
<!-- 				<form class="form-horizontal" role="form" id="loginForm" -->
<%-- 					action="${ctx}/admin/sec/user/changepsw" method="post"> --%>
<%-- 					<input type="hidden" name="currentPath" value="${rdictCurrentPath}"> --%>
<%-- 					<input type="hidden" name="id" value="${user.id}" /> --%>
<%-- 					<input type="hidden" name="account" value="${user.account}" /> --%>
<!-- 					<div class="form-group"> -->
<!-- 						<label for="userName" class="col-md-2 control-label">账号:</label> -->
<!-- 						<div class="col-md-4"> -->
<%-- 							<input type="hidden" id="userName" name="userName" value="${user.userName}" --%>
<!-- 								class="form-control input-sm required" -->
<%-- 								placeholder="<sp:message code="login.username"/>" /> --%>
<%-- 								${user.account} --%>
<!-- 						</div> -->
<!-- 					</div> -->

<!-- 					<div class="form-group"> -->
<!-- 						<label for="plainPassword" class="col-md-2 control-label">新密码:</label> -->
<!-- 						<div class="col-md-4"> -->
<!-- 							<input type="password" id="plainPassword" name="plainPassword" -->
<!-- 								class="form-control input-sm required" /> -->
<!-- 						</div> -->
<!-- 					</div> -->

<!-- 					<div class="form-group"> -->
<!-- 						<label for="confirmPassword" class="col-md-2 control-label">确认密码:</label> -->
<!-- 						<div class="col-md-4"> -->
<!-- 							<input type="password" id="confirmPassword" -->
<!-- 								name="confirmPassword" class="form-control input-sm required" -->
<!-- 								equalTo="#plainPassword" /> -->
<!-- 						</div> -->
<!-- 					</div> -->

<!-- 					<div class="form-group"> -->
<!-- 						<div class="col-md-offset-2 col-md-10"> -->
<!-- 							<button type="submit" class="btn btn-primary"> -->
<!-- 								&nbsp; -->
<!-- 								提交 -->
<!-- 								&nbsp; -->
<!-- 							</button> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->


<script>
	$(document).ready(function() {
		var _setCookie=function(c_name, value, expireTimes){
			var exdate=new Date();
			exdate.setTime(exdate.getTime() + expireTimes);
			document.cookie=c_name+ "=" + escape(value) + ((expireTimes==null) ? "" : ";expires="+exdate.toGMTString());
		};
		var _getCookie=function(c_name){
			if (document.cookie.length>0){
				c_start=document.cookie.indexOf(c_name + "=");
				if (c_start!=-1){ 
					c_start=c_start + c_name.length+1;
					c_end=document.cookie.indexOf(";",c_start);
					if (c_end==-1){ c_end=document.cookie.length; }
					return unescape(document.cookie.substring(c_start,c_end));
				} 
			}
			return "";
		};
		//首次登陆且无权限
		var hisurl=_getCookie('hisurl');
		if (hisurl!="" && ( hisurl.indexOf("/unauthorized")>0 ) ) {
			location.href="${pageContext.request.contextPath}/logout";
		}
		//聚焦第一个输入框
		if("${msg!=null}"==="true"){
			$.alert("${msg}");
		}
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
		
		
	});
</script>

</body>
</html>