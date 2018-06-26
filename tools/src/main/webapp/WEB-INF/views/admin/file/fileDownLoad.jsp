<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>

<html decorator="null">
<head>
<meta charset="utf-8" >
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">  
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="email=no">

<title>大都会BMP－下载页面</title>

<style type="text/css">
body{ background:#017dc3}
.loadBtn{width:60%;margin:10% auto 0;}
.loadBtn button{width:100%;height:3.5rem;font-family:Tahoma,Arial,Roboto,"Droid Sans","Helvetica Neue","Droid Sans Fallback","Heiti SC","Hiragino Sans GB",Simsun,sans-self;}
.loadBtn button{width:100%;height:3.5rem;line-height:3.5rem;font-size:1.2rem;text-align:center;color:#fff; border:none;  cursor:pointer;background:none;}
.loadBtn button a{-moz-border-radius:6px;  
    -webkit-border-radius:6px;  
    border-radius:6px; text-decoration:none;color:#fff; background:#00476f; display:block; cursor:pointer}
.loadBtn button a:hover{text-align:center;color:#fff;display:block;background:#ff0;text-decoration:none;}

</style>

<script type="text/javascript">
	$(function (){
		var qrcode_c = document.getElementById("qrcode_c");
		var qrcode_b = document.getElementById("qrcode_b");
		if(qrcode_c){  
		    var iosURL = "${iosNewPath }";
		    var qrcode = new QRCode(qrcode_c, {  
		        width : 130,  
		        height : 130  
		    });  
		    qrcode.makeCode(iosURL); 
		}
		if(qrcode_b){  
		    var androidURL = "${serverIp }fileLoad?attachId=${androidAttachId}";
		    var qrcode = new QRCode(qrcode_b, {  
		        width : 130,  
		        height : 130  
		    });  
		    qrcode.makeCode(androidURL);
		} 
	});
	function down(attachId) {
		if (attachId == undefined) {
			alert("亲，版本更新中。敬请关注!");
			return;
		}
		window.location.href = "${ctx }/fileLoad?attachId="+attachId;
	}
</script>
</head>
<body>
	<div style="width:70%;margin:20% auto 15%;">
		<img width="100%" src="${ctx}/static/images/loadPic.png">
	</div>
	
	<div class="loadBtn">
		<button type="button"><a href="${iosNewPath }">IOS 下载APP</a></button>
	</div>
	<div class="loadBtn">
		<button type="button"><a href="javascript:down(${androidAttachId})">安卓 下载APP</a></button>
	</div>

<!-- 	<div align="center" style="margin-top: 70px;margin-bottom:200px;"> -->
<!-- 		<div class="tags" style="position: absolute;margin-left: 35%">- -->
<%-- 			<a class="big" style="font-size: 20px;font-weight:bold;" href="${iosNewPath }">IOS APP</br><span style="font-size:14px;color:white">Download</span></a> --%>
<!-- 			<div id='qrcode_c' style='backwidth:200px;height:150px;'></div> -->
<!-- 		</div>  -->
<!-- 		<div class="tags2" style="margin-left: 200px;"> -->
<%-- 			<a style="font-size: 20px;font-weight:bold;" href="javascript:down(${androidAttachId})">Android APP</br><span style="font-size:14px;color:white">Download</span></a>   --%>
<!-- 			<div id='qrcode_b' style='width:200px;height:200px;'></div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<%-- 		<button class="button" title="IOS下载" onclick="down('${iosAttachId}');">IOS下载</button> --%>
<%-- 		<button class="button"  title="Android下载" onclick="down('${androidAttachId}');">Android下载</button> --%>
</body>
</html>