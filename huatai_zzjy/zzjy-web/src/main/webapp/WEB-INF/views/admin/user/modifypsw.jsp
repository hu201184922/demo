<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码修改</title>
</head>
<script type="text/javascript">
function submitForm(){
	$.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/admin/user/updateauth",
		data : $("#modifypswForm").serialize(),
		success : function(msg) {
			if(msg){
				$.msgbox({
					time : 2000,
					msg : "密码修改成功!",
					icon : "success"
				});
				$('#diaClose').click();
			}else{
				$.msgbox({
					time : 3000,
					msg : "密码修改失败!",
					icon : "fail"
				});
			}
		}
	});
}
</script>
<body>
	<a id="diaClose" class="close" style="display: none;"></a>
	<form id="modifypswForm" action="#" method="post">
		<input type="hidden" name="id" value="${user.id}" />
		<div id="tagContent2" class="tagContent tagContent_div" style="display:block;">
            <table style="line-height:30px">
			    <tbody><tr>
				    <td class="text_right">老密码：</td>
					<td class="text_left"><input type="text" id="oldPassword" name="oldPassword"  class="text_wd200"/></td>
				</tr>
				<tr>
				    <td class="text_right">新密码：</td>
					<td class="text_left">
					<input type="text" id="newPassword" name="newPassword" class="text_wd200"/>
					</td>
				</tr>
				<tr>
				    <td class="text_right">确认密码：</td>
					<td class="text_left">
					<input type="text" id="confirmPassword" name="confirmPassword" class="text_wd200"/>
					</td>
				</tr>
				<tr>
				    <td height="10px" colspan="2"></td>
				</tr>
				<tr>
				    <td></td>
					<td class="text_left">
					<input type="button" onclick="submitForm()" value="提 交" class="search_btn">&nbsp;&nbsp;<input type="button" value="取 消" class="search_btn close">
					</td>
				</tr>
				</tbody>
			</table>
    	</div>
	</form>
</body>
</html>