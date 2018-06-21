<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>    
<!DOCTYPE html >
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta name="description" content="" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	
	<title>微信测试页面</title>
	<script type="text/javascript" src="${ctx }/static/js/jquery.min.js"></script>
</head>
<body>
微信测试页面<br/>
接收者：<input type="text" id="touser"><br/>
部门：<input type="text" id="toparty"><br/>
标签：<input type="text" id="totag"><br/>
消息类型：<input type="text" id="msgtype" value="text" disabled="disabled"><br/>
内容：<input type="text" id="contents"><br/>
<input type="button" value="发送" id="submit">

<script type="text/javascript">
$(function(){
	$('#submit').on('click',function(){
		
		$.ajax({
			url:'${ctx}/message/textMsg',
			type:'post',
			data:{touser:$('#touser').val(),
				toparty:$('#toparty').val(),
				totag:$('#totag').val(),
				msgtype:$('#msgtype').val(),
				content:$('#contents').val()
				},
			dataType:'json',
			success:function(data){
				alert(data.message);
			}
				
		})
	});
});
</script>
</body>
</html>