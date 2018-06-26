<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%response.setStatus(403);%>
<!DOCTYPE html>
<html lang="en" decorator="null">
<head>
<meta charset="UTF-8">
<script type="text/javascript">
window.onload=function(){
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
	}
	var _setCookie=function(c_name, value, expireTimes){
		var exdate=new Date();
		exdate.setTime(exdate.getTime() + expireTimes);
		document.cookie=c_name+ "=" + escape(value) + ((expireTimes==null) ? "" : ";expires="+exdate.toGMTString());
	}
	
	var isOut=false;
	var mm=document.getElementById("mmSpan");
	var ti=window.setInterval(function(){
		mm.innerHTML=parseInt(mm.innerHTML)-1;
		if(mm.innerHTML=='0'){ 
			_setCookie('hisurl',location.href,1000*10); //存10秒
			if( /*isOut||*/ ( hisurl!="" && hisurl.indexOf("/unauthorized")>0 )){
				mm.parentNode.style.display='none';
				//location.href="${pageContext.request.contextPath}/logout";
			}else{
				history.back();
			}
			window.clearInterval(ti);
		}
	},1000);
	
	var hisurl=_getCookie('hisurl');
	console.log(hisurl);
	/*
	if (hisurl!="" && ( hisurl.indexOf("/unauthorized")>0 || hisurl.indexOf("admin/sec/user/changepsw")>0) ) {
		isOut=true;
	}
	*/
};
</script>
<style type="text/css">
.main { text-align: left;font-size: 14px;width:100%;height: 400px;padding-left: 30%;padding-top: 10%; }
.main p{ font-size: 18px;font-family: "微软雅黑",arial,sans-serif;margin: 10px 0; }
.link { font-size: 14px;text-decoration: none;background-color: #eee;padding: 8px 20px; }
.link:hover{ background-color: #00aff3;color: #fff; }
#mmSpan{ padding-right: 7px;color: #F00;}
</style>
</head>
<body>
<div class="main" >
<p><span id="mmSpan">5</span>秒后自动返回上一个页面</p>
<p>未经授权，无法访问，请联系管理员!</p>
<p>
<a class="link" href="${pageContext.request.contextPath}/logout">登出</a>
<a class="link" href="javascript:history.back();">后退</a>
</p>
</div>
</body>
</html>