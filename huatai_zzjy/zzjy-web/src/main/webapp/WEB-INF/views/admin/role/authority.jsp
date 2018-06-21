<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u" %>
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<form id="saveForm" action="${pageContext.request.contextPath}/admin/sec/role/saveauthority" method="post">
<input type="hidden" name="roleId" value="${role.id}" />
<div class="tagContent tagContent_div" style="display: block;">
<!-- 	<table style="line-height:30px"> -->
<!-- 	    <tbody> -->
<!-- 		<tr> -->
<!-- 		<td colspan="2"> -->
			<div style="width:100%;" id="con2">
				<ul style="width:100%;" id="tags2" tabs="{selectedClass:'selectTag'}">
						<li class="selectTag" tab="#tagContent0"><a href="javascript:void(0)">资源授权</a> </li>
						<table>
							<tbody>
								<tr>
									<td style="float: left;margin-left: 450px;">[${role.name}]权限编辑</td>
									<a style="position: absolute;margin-left: 87%;background-color: #ccc; line-height: 25px;padding: 0 11px;border: 1px #ccc solid;
						            				border-radius: 3px; display: inline-block;"class="backPage" href="javascript:void(-1);">返回</a>
						         </tr>
							</table>
				</ul>
				<div style="height:auto;width:100%; padding-bottom:30px;" id="tagContent">
					<div id="tagContent0" class="tagContent tagContent_div selectTag" >
						<h1>资源授权</h1>
						<jsp:include page="/WEB-INF/views/admin/role/authorityByResource.jsp"/>
				    </div>
				</div>
			</div>
				<input type="submit" value="提 交" class="search_btn">
</div>
</form>
<script type="text/javascript">
	$(function(){
		var options = {
				 beforeSubmit:function(a,f,o){
					 var resourceIds = [];
					 if(true){
						nodes = $('#resourceBoxTree').zTree().getCheckedNodes(true);
						nodes.each(function(){
							resourceIds.push({name:'resourceId',value:this.id,type:'hidden'});

						});
					 }else{
						nodes = $('#resourceBoxTree_internet').zTree().getCheckedNodes(true);
						nodes.each(function(){
							resourceIds.push({name:'resourceId',value:this.resourceid,type:'hidden'});

						});

					 }
					a.pushAll(resourceIds);
				 } ,
				 success:function(){
						$.msgbox({time: 2000,msg: "用户组权限编辑成功!",icon:"success"});
						$('#diaClose').click();
						$('#pager').pager().reload();
						$('.backPage').click();
					}
		};
		$('#saveForm').ajaxForm(options);
		
	});
</script>
</body>
</html>