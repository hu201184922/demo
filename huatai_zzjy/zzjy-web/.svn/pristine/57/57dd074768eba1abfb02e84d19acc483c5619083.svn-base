<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>

<html>
<head>
<script src='${ctx }/static/js/moment.min.js'></script>
<script>
	$(function(){
		$("#updateFileList").view().on('add',function(data){
			this.target.find('.remove').click(function(){
				$.post(window.contextPath+"/fileManager/delAttachFile",{attachId:data.attachId},function(data){
				});
			});
		});
	});
</script>
</head>
<body>
	<a ajax="{type:'dialog'}" href="${pageContext.request.contextPath}/fileManager/index?functionType=0&functionId=00&flag=admin">上传文件</a>
    <div id="updateFileList">
    	<div class="template" name="default">
			<input type="hidden" class="view-field" name="updateFileList[{index}].attachId" mapping="attachId" />
			<div style="float:left;" >
				<a href="javascript:void(0);" style="float:left;height:20px;padding-top:16px;">{oldFileName}{fileType}</a>
				<img style="margin:10px 5px; *margin:5px; cursor:pointer;" src="${pageContext.request.contextPath}/static/images/dell.png" class="remove"/>
			</div>
		</div>
    </div>
</body>
</html>