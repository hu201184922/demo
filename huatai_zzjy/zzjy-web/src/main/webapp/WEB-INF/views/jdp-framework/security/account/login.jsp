<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登入</title>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.7.2.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/login.css">
<script type="text/javascript">
	$(function(){
		$.formValidator.initConfig({
			formID : "login",
			onError : function(msg) {
				$.alert(msg);
			}
		});
		$("#username").formValidator().inputValidator().functionValidator({
			fun:function(){
				if($("#username").val() == ""||$("#username").val()==$("#username").attr("defaultTxt")){
					return "请输入用户名";
				}
				if($("#password").val() == ""){
					return "请输入密码";
				}
				$("#username").val(stringUtils.trim($("#username").val()));
			}			
		});
		$("[ref=submit]").click(function(){
			$("#login").submit();
		});
		if("${error}"!=""){
			$.alert("${error}");
		}
	});
	$(document).ready(function(){
		$("#username").each(function(){
			this.onkeyup = function(){
				if(this.val != ""){
					$("#usernameAlert").hide();
					$("#loginAlert").hide();
				}
			};
		});
		$("#password").blur(function(){
			if($(this).val()==""){
			$("#passwordtext").show();
			$(this).hide();
			}else{
				$("#passwordtext").hide();
				$(this).show();
			}
		});
		$("#passwordtext").focus(function(){
			$("#password").show();
			$(this).hide();
			$("#password").focus();
		});
		$("#password").blur();
		$("#password").each(function(){
			this.onkeyup = function(){
				if(this.val != ""){
					$("#passwordAlert").hide();
					$("#loginAlert").hide();
				}
			};
		});
	});
</script>
</head>

<body>

<div class="login_top">
    <p><img src="${ctx}/static/images/logo.png" height="104" /></p>
</div>
<div class="login_neirong">
<form id="login" action="${ctx}/admin/login" method="post">
  <ul>
  <li>
    <input name="username" type="text"  class="login_neirong_name"  id="username" value="${username }" defaultTxt="请输入用户名" />
  </li>
  <li>
     	<input name="password" type="password"  class="login_neirong_password"  id="password" style="display:none">
    	<input name="passwordtext" type="text"  class="login_neirong_password" value="请输入密码"  id="passwordtext" style="display:none">
  </li>
  <li>
  <div class="clear"></div>
  </li>
  <input type="submit" style="display: none"></input>
  <p><a ref="submit" href="javascript:void(0);">用户登录</a></p>
  </ul>
 </form>
</div>
</body>
</html>













<%-- <%@ page contentType="text/html;charset=UTF-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%> --%>
<%-- <%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%> --%>
<%-- <c:set var="ctx" value="${pageContext.request.contextPath}" /> --%>
<!-- <meta name="decorator" content="login"> -->

<!-- <div class="row"> -->
<!-- 	<div class="col-md-6 col-md-offset-3"> -->
<%-- 		<tags:message /> --%>
<!-- 		<div class="panel panel-primary"> -->
<!-- 			<div class="panel-heading"> -->
<%-- 				<sp:message code="admin.login.title" /> --%>
<!-- 			</div> -->
<!-- 			<div class="panel-body"> -->
<!-- 				<form class="form-horizontal" role="form" id="loginForm" -->
<%-- 					action="${ctx}/admin/login" method="post"> --%>
<!-- 					<div class="form-group"> -->
<%-- 						<label for="username" class="col-md-2 control-label"><sp:message --%>
<%-- 								code="login.username" />:</label> --%>
<!-- 						<div class="col-md-4"> -->
<!-- 							<input type="text" class="form-control input-sm required" id="username" -->
<!-- 								name="username" -->
<%-- 								placeholder="<sp:message code="login.username"/>"> --%>
<!-- 						</div> -->
<!-- 					</div> -->

<!-- 					<div class="form-group"> -->
<%-- 						<label for="password" class="col-md-2 control-label"><sp:message --%>
<%-- 								code="login.password" />:</label> --%>
<!-- 						<div class="col-md-4"> -->
<!-- 							<input type="password" class="form-control input-sm required" -->
<!-- 								id="password" name="password" -->
<%-- 								placeholder="<sp:message code="login.password"/>"> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<%-- 					<c:if test="${showCaptcha=='true'}"> --%>
<!-- 						<div class="form-group"> -->
<%-- 							<label for="captcha" class="col-md-2 control-label"><sp:message --%>
<%-- 									code="login.captcha" />:</label> --%>
<!-- 							<div class="col-md-4"> -->
<!-- 								<input type="text" id="captcha" name="captcha" -->
<%-- 									placeholder="<sp:message code="login.captcha"/>" --%>
<!-- 									class="form-control input-sm required" /> -->

<!-- 							</div> -->
<!-- 							<div class="col-md-4"> -->
<%-- 								<img id="validateCodeImg" src="${ctx}/admin/sec/captcha" />&nbsp;&nbsp; --%>
<%-- 								<a href="javascript:;" onclick="reloadValidateCode()"><sp:message --%>
<%-- 										code="login.notsee" />？</a> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<%-- 					</c:if> --%>
<!-- 					<div class="form-group"> -->
<!-- 						<div class="col-md-offset-2 col-md-10"> -->
<!-- 							<div class="checkbox"> -->
<!-- 								<label for="rememberMe"> <input type="checkbox" id="rememberMe" -->
<%-- 									name="rememberMe"> <sp:message code="login.rememberme" /> --%>
<!-- 								</label> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<div class="col-md-offset-2 col-md-10"> -->
<!-- 							<button type="submit" class="btn btn-default "> -->
<!-- 								&nbsp; -->
<%-- 								<sp:message code="login.login" /> --%>
<!-- 								&nbsp; -->
<!-- 							</button> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<!-- <script> -->
<!-- // 	$(document).ready(function() { -->
<!-- // 		$("#loginForm").validate(); -->
<!-- // 	}); -->
<!-- // 	function reloadValidateCode() { -->
<!-- // 		$("#validateCodeImg").attr( -->
<!-- // 				"src", -->
<%-- // 				"${ctx}/admin/sec/captcha?data=" + new Date() --%>
<!-- // 						+ Math.floor(Math.random() * 24)); -->
<!-- // 	} -->
<!-- </script> -->

