<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache">
<title>登入</title>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.7.2.min.js" charset="utf-8"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/common.js" charset="utf-8"></script> --%>
<script type="text/javascript" src="${ctx}/static/js/common/formvalidator/formValidator-4.1.1.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/common/formvalidator/formValidatorRegex.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/common/formvalidator/themes/Default/js/theme.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/validateJs.js" charset="utf-8"></script>

<link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/login.css">
<script type="text/javascript">
var request = (function() {


	window.contextPath = null;

	return {
		getContextPath : function() {
			if(!contextPath) {
//				var pathName = document.location.pathname;
//				var index = pathName.substr(1).indexOf("/");
//				window.contextPath = pathName.substr(0, index + 1);
				window.contextPath='';
//				window.contextPath = '/';
			}
			return window.contextPath;
		},
	
		getQueryString : function(){
			var url = document.location.href;
			return url.indexOf("#")==-1?url.substr(url.indexOf("?")+1):url.substr(url.indexOf("?")+1,url.indexOf("#"));
		}
	};
})();
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
		if('${findPswMessage}' ==  'success'){
			$.msgbox({time: 1000,msg: "密码修改成功,请重新登录！" ,icon:"success"});
		}
		if(getCookie('username')!=""){
			$("[name=remember]").attr('checked','checked');
			$("#username").removeClass('empty');
			$("#username").val(getCookie('username'));
		}
	});
	function getCookie(name){ 
		var strCookie=document.cookie; 
		var arrCookie=strCookie.split("; "); 
		for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]==name)return arr[1]; 
		} 
		return ""; 
	} 
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

<body  class="foot-img" style=" min-height:560px;">

<div class="login_top">
  <p><img src="${ctx}/static/ezt/web/images/dgh_crm_login_05.jpg" height="104" style="margin:0 auto;"/></p>
</div>
<div class="login_neirong" >
<form id="login" action="${ctx}/login" method="post">
  <ul>
  <li>
    <input name="username" type="text"  class="login_neirong_name"  id="username" value="${username }" defaultTxt="请输入用户名" />
  </li>
  <li>
    <input name="password" type="password"  class="login_neirong_password"  id="password" style="display:none">
    <input name="passwordtext" type="text"  class="login_neirong_password" value="请输入密码"  id="passwordtext" style="display:none">
  </li>
  <li><input type="checkbox" name="remember"/><span style="font-size:14px;color:#fff;">记住用户名</span>
  	<span><a href="${ctx}/changepsw/findPsw" style="float:right;font-size:14px;color:#fff;">找回密码</a></span>
  <div class="clear"></div>
  </li>
  <li><input type="submit" style="display: none"></input>
  <p><a ref="submit" href="javascript:void(0);">用户登录</a></p></li>
  </ul>
  
 </form>
</div>

<a style="font-size: medium;color: white;position: absolute;left: 950px;">B1.0.0000</a>

</body>
</html>
