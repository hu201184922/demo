<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>区域任务管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/task/regList',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
			})(this);
		}));
		//$('#pager').pager().reload();
		$("#createTemplet").click(function() {
			$("#saveTempletForm")[0].reset();
			$('#saveTempletForm').data("dialog", $.dialog({
				title : "是否启动",
				content : $('#saveTempletForm')[0]
			}));
		});
		
		$('#saveTempletForm').ajaxForm(function(data) {
			$('#saveTempletForm').data("dialog").close();
			$.msgbox({time: 2000,msg: "已启动!",icon:"success"});
			$('#pager').pager().reload();
		});
		
		$("#startTemplet").click(function() {
			$("#saveTempletForm")[0].reset();
			$('#saveTempletForm').data("dialog", $.dialog({
				title : "是否启动",
				content : $('#saveTempletForm')[0]
			}));
		});
		
		$('#saveTempletForm').ajaxForm(function(data) {
			$('#saveTempletForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		
		$("#clearTemplet").click(function() {
			$('#clearTempletForm').data("dialog", $.dialog({
				title : "是否清除",
				content : $('#clearTempletForm')[0]
			}));
		});
		
		$('#clearTempletForm').ajaxForm(function(data) {
			$('#clearTempletForm').data("dialog").close();
			$.msgbox({time: 2000,msg: "已删除!",icon:"success"});
			$('#pager').pager().reload();
		});
		
		query();
		$('#pager').pager().reload();
});	
	function query(){
		var postData ={
				"tempId":"${templet.tempId}",
				"regCode":$("#regCodeQuery").val(),
				"regName":$("#regNameQuery").val()
		}
		$('#pager').pager().setPostData(postData);
	}
	
	function start(index,tempLink){
		var httpStr="";
		httpStr="${pageContext.request.contextPath}/admin/cache/start"+tempLink;
		if(tempLink=="Zdsb"){
			$.ajax({
				  type: 'POST',
				  url: httpStr,
				  data: {reg:index},
				  success: function(data){
					  $.msgbox({time: 2000,msg: "已启动!",icon:"success"});
				  }
			});
		}else{
			$.ajax({
				  type: 'POST',
				  url: httpStr,
				  data: {reg:index.substr(index.length-1,1)},
				  success: function(data){
					  $.msgbox({time: 2000,msg: "已启动!",icon:"success"});
				  }
			});
		}
	}
	function clear1(index,tempLink){
		var httpStr="";
		httpStr="${pageContext.request.contextPath}/admin/cache/clear"+tempLink;
		if(tempLink=="Zdsb"){
			$.ajax({
				  type: 'POST',
				  url: httpStr,
				  data: {reg:index},
				  success: function(data){
					  $.msgbox({time: 2000,msg: "已启动!",icon:"success"});
				  }
			});
		}else{
			$.ajax({
				  type: 'POST',
				  url: httpStr,
				  data: {reg:index.substr(index.length-1,1)},
				  success: function(){
					  $.msgbox({time: 2000,msg: "已删除!",icon:"success"});
				  }
			});
		}
	}
	
	function startSub(obj){
		var httpStr="";
			httpStr="${pageContext.request.contextPath}/admin/cache/start"+obj;
		$('#saveTempletForm').attr("action",httpStr);
		$('#saveTempletForm').submit();
	}
	function clearSub(obj){
		var httpStr="";
		httpStr="${pageContext.request.contextPath}/admin/cache/clear"+obj;
		$('#clearTempletForm').attr("action",httpStr);
		$('#clearTempletForm').submit();
	}
	
</script>
</head>
<body>
	<div class="business_title">区域任务管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<table style="width:25%">
				<tbody>
					<tr>
						<td>模板编码：${templet.tempCode }</td>
						<td style="text-align: left;">模板名称：${templet.tempName }</td>
						<td style="text-align: left;"><a style="position: absolute; margin-left: 800px; line-height: 25px;padding: 0 11px;border: 1px #26bbdb solid;
            				border-radius: 3px; display: inline-block;" href="${pageContext.request.contextPath}/admin/task/index">返回</a>
            			</td>
            		</tr>
				</tbody>
			</table>
		</div>
		<div class="business_search_list_warp" style="width: 95%">
		<input type="button" id="startTemplet" value="全部启动" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;" >
		<input type="button" id="clearTemplet" value="全部清除" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;" >
				<table cellspacing="0" cellpadding="0" class="t-list table"id="parameterView">
				<tbody>
					<tr>
						<th>区域编码</th>
						<th>区域名称</th>
						<th style="border-right: 1px solid #ccc; width: 80px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{regCode}</td>
						<td>{regName}</td>
						<td style="border-right: 1px solid #ccc">
							<a href="javascript:void(0)"
								onclick="start('{regCode}','${templet.tempLink}')">开启
							</a>
							<a href="javascript:void(0)"
								onclick="clear1('{regCode}','${templet.tempLink}')">删除
							</a>
						</td>
					</tr>
				</tbody>
				<input type="hidden" id="tempId" value="${templet.tempId }" />
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form id="saveTempletForm" style="display: none;"
		  <%-- action="${pageContext.request.contextPath}/admin/cache/startAll" --%>
		  method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr><input id="startType" type="hidden" name="reg" value="0"/>
						<td class="text_left"><input type="button" value="启动" onclick="startSub('${templet.tempLink}')"
							class="search_btn"><input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	
	<form id="clearTempletForm" style="display: none;" method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr><input id="clearType" type="hidden" name="reg" value="0"/>
						<td class="text_left"><input type="button" value="删除" onclick="clearSub('${templet.tempLink}')"
							class="search_btn"><input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	
</body>
</html>