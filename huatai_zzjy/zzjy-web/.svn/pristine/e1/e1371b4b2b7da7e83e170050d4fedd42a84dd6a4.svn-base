<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>新增指标SQL维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.top{float: left;width: calc(100% - 40px); padding: 20px;}
	.top span input{width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;}
	#btnSave{margin: 10px 20px 30px 200px;}
	.formCheck{vertical-align:middle;margin:-4px 2px 0px 2px;}
	.sql{float: left;width:80px;text-overflow: ellipsis;-moz-text-overflow: ellipsis; overflow: hidden;white-space: nowrap;}
</style>
<script type="text/javascript">
	$(function(){
		$("input[name='record']").val(null);
	});
	
	 
	function listGroDim(groupCode,groupDetailCode){
		var targetCode = $("#target").val();
		var gdId = null;
		$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listGroDim',{targetCode:$("#target").val()},function(data){
			var div = $("#groDimDiv");
			//div.append('<input type="radio" name="groupCode" value=""/>无&nbsp;');
			div.append('<option value="">--请选择--</option>');
			$.each( data, function(index, content)
			  {
				var flag = false;
				if(groupCode!=null && groupCode==content.groDimTypeCode){
					flag = true;
					gdId = content.gdId;
				}
					
				//div.append('<input type="radio" name="groupCode" onclick="javascript:selectGroDim('+content.gdId+')" value="'+content.groDimTypeCode+'" class="formCheck" '+(flag?"checked":"")+'/>'+content.groDimTypeName+"&nbsp;")
				div.append('<option gdId='+content.gdId+' value="'+content.groDimTypeCode+'" class="formCheck" '+(flag?"checked":"")+'>'+content.groDimTypeName+"</option>")
			  });
			if(groupDetailCode!=null){
				selectGroDim(gdId,groupDetailCode);
			}
		});	
	}
	
	function selectGroDim(gdId,groupDetailCode){
		var datas=$("#groDimDiv").val();
		$("#groDimDetailDiv").empty();
		var targetCode = $("#target").val();
		$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listGroDimDetail',{targetCode:$("#target").val(),gdId:datas},function(data){
			var div = $("#groDimDetailDiv");
			//div.append('<input type="radio" name="groupDetailCode" value="" class="formCheck"/>无&nbsp;')
			div.append('<option value="">--请选择--</option>')
			$.each( data, function(index, content)
			  {
				var flag = false;
				if(groupDetailCode!=null && groupDetailCode==content.groDimCode){
					flag = true;
				}
				//div.append('<input type="radio" name="groupDetailCode" value="'+content.groDimCode+'" class="formCheck" '+(flag?"checked":"")+'/>'+content.groDimName+"&nbsp;")
				div.append('<option  value="'+content.groDimCode+'" class="formCheck" '+(flag?"selected":"")+'>'+content.groDimName+"</option>")
			  });
		});	
		
	}
	
	function clearSqlForm(){
		$("#groDimDiv").empty();
		$("#groDimDetailDiv").empty();
		$("input[name='dateType']").each(function(){
			$(this).attr("selected",false);
		});
		$("#sqlCode").val(null);
	}
	
	 function goback(){
		 location.replace("${pageContext.request.contextPath}/admin/tarInitSql/index");
	 }
	 
	 function tempSave(){
		 var groupCode = $("#groDimDiv").val();
		 var groupDetailCode = $('#groDimDetailDiv').val();
		 var dateType = $('#dateType').val();
		 var sqlCode = $('#sqlCode').val();
		/* 	if ($.trim(groupCode) == '') {
				$.dialog({time: 2000,content:  "请输入分组类型！"});
				return;
			}
			if ($.trim(groupDetailCode) == '') {
				$.dialog({time: 2000,content:  "请输入分组详细维度！"});
				return;
			}
			if ($.trim(dateType) == '') {
				$.dialog({time: 2000,content:  "请输入日期类型！"});
				return;
			}
			if ($.trim(sqlCode) == '') {
				$.dialog({time: 2000,content:  "请输入SQL！"});
				return;
			} */
		 if (typeof(groupCode) == "undefined") {
			 groupCode="";
		 }
		 if (typeof(groupDetailCode) == "undefined" ||groupDetailCode==null) {
			 groupDetailCode=""
		 }
		 if (typeof(dateType) == "undefined") {
			 dateType=""
		 }
		 $("#noData").hide();
		 if(!(groupCode==""&& groupDetailCode=="" && dateType==""&& sqlCode=="")){
			 $("#tmpRow").append("<tr id='relist'><td>"+groupCode+"</td><td>"+groupDetailCode+
					 "</td><td>"+dateType+"</td><td><div class='sql'>"+sqlCode+
					 "</div></td><td style='cursor:pointer;color:#3399FF;' onclick='removeThis(this)'>删除</td></tr>");
		 }
		 $('#saveParameterForm').data("dialog").close();
		 var oldRecord = $("input[name='record']").val();
		 var str = groupCode+"#"+groupDetailCode+"#"+dateType+"#"+sqlCode;
		 var newRecord;
		 if (oldRecord == "") {
			 newRecord = str;
		 } else {
			 newRecord = oldRecord + "&" + str;
		 }
		 $("input[name='record']").val(newRecord);
	 }
	 
	 function removeThis(obj){
		 var $obj = $(obj).parent();
		 var str = $obj.find("td:eq(0)").html()+"#"+$obj.find("td:eq(1)").html()+"#"+$obj.find("td:eq(2)").html()+"#"+$obj.find("td div").html();
		 $(obj).parent().remove();
		 var oldRecord = $("input[name='record']").val();
		 var newRecord;
		 if (oldRecord.indexOf("&") != -1) {
			 newRecord = oldRecord.replace("&"+str, "");
		 } else {
			 newRecord = oldRecord.replace(str, "");
		 }
		 $("input[name='record']").val(newRecord);
		 if (newRecord=="") {
			 $("#noData").show();
		 }
	 }
	 
	// 加载主题板块对应指标
		function subTarget(sed){
			var seo =$("#subpla").val()
			$('#target').html('');
			$('#target').append('<option value="-1" selected="selected">--请选择--</option>');
			if (seo != "-1") {
				$.post('${pageContext.request.contextPath}/admin/tarInitSql/getTarBySub', {'subPlaId': seo}, function(data){
					$.each(data, function(k, v){
						$('#target').append('<option value="' + v.targetCode + '">' + v.targetName + '</option>');					
					});
				});
			}
		}
		function selectGroDim(gdId,groupDetailCode,targetCode){
			if (typeof(targetCode) == "undefined") {
				targetCode = $("#targetCode").val();
			}
			$("#groDimDetailDiv").empty();
			var div = $("#groDimDetailDiv");
			$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listGroDimDetail',{targetCode:$("#target").val(),gdId:$("#groDimDiv").find("option:selected").attr ("gdId")},function(data){
				
				//div.html('');
				div.append('<option value="" selected="selected">--请选择--</option>');
				$.each( data, function(index, content)
				  {
					var flag = false;
					if(groupDetailCode!=null && groupDetailCode==content.groDimCode){
						flag = true;
					}
					div.append('<option value="'+content.groDimCode+'" class="formCheck" '+(flag?"selected":"")+'>'+content.groDimName+"</option>")
				  });
			});	
		}
		
		function showAddSql(){		
			var subpla = $('#subpla').find('option:selected').val();
			var target = $('#target').find('option:selected').val();
			var clist = $('input[type="checkbox"]:checked').val();
			 if (subpla == -1) {
				$.dialog({time: 2000,content:  "请选择主题/板块！"});
				return;
			}
			if (target == -1) {
				$.dialog({time: 2000,content:  "请选择指标！"});
				return;
			}
			if (typeof(clist) == "undefined") {
				$.dialog({time: 2000,content:  "请选择对应功能！"});
				return;
			}
			clearSqlForm();
			listGroDim();
			$('#saveParameterForm').data("dialog", $.dialog({
				title : "新增SQL",
				content : $('#saveParameterForm')[0]
			}));
			var div = $("#groDimDetailDiv");
			//div.append('<input type="radio" name="groupDetailCode" value="" class="formCheck"/>无&nbsp;')
		}
		
		function updateRecord(tisId, targetCode, groupCode, groupDetailCode,dateType, funIds){
			$("#targetCode").val(targetCode);
			$("#tisId").val(tisId);
			$("#funIds").val(funIds);
			$("#dateType").val(dateType);
			$("input[id='"+dateType.toLowerCase()+"']").attr("checked", true);
			var sqlCode = $('#'+tisId+'_sql').html();
			$('#sqlCode').val(sqlCode);
			var gdId = null;
			$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listGroDim',{targetCode:$("#target").val()},function(data){
				$("#groDimDiv").empty();
				var div = $("#groDimDiv");
				$.each( data, function(index, content)
				  {
					var flag = false;
					if(groupCode!=null && groupCode==content.groDimTypeCode){
						flag = true;
						gdId = content.gdId;
					}
					div.append('<option name="groupCode" onclick="javascript:selectGroDim('+content.gdId+')" value="'+content.groDimTypeCode+'" class="formCheck" '+(flag?"checked":"")+'/>'+content.groDimTypeName+"</option>&nbsp;")
				  });
				//div.append(temp);
				if(groupDetailCode!=null){
					selectGroDim(gdId,groupDetailCode,targetCode);
				}
			});	
			$('#saveParameterForm').data("dialog", $.dialog({
				title : "修改SQL",
				content : $('#saveParameterForm')[0]
			}));
		}
	 
	 function save(){
		var records = $("input[name='record']").val();
		var subpla = $('#subpla').find('option:selected').val();
		var target = $('#target').find('option:selected').val();
		var clist = $('input[type="checkbox"]:checked').val();
		if (subpla == -1) {
			$.dialog({time: 2000,content:  "请选择主题/板块！"});
			return;
		}
		if (target == -1) {
			$.dialog({time: 2000,content:  "请选择指标！"});
			return;
		}
		if (typeof(clist) == "undefined") {
			$.dialog({time: 2000,content:  "请选择对应功能！"});
			return;
		}
		 $.post('${pageContext.request.contextPath}/admin/tarInitSql/saveRecords', 
				 {'records': records,'subpla': subpla,'target':target ,'clist': clist}, function(data){
		 	$.msgbox({time: 2000,msg: "添加成功", icon: "success"});
		 	goback();
		 });
	 }
	 
</script>
</head>
<body>
	<div class="business_title">添加指标SQL维护</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 97%">
		 	<div class="business_search">
		 		 <span>
					 	<input style="width: 60px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;float: right;" type="button" value="返回" onclick="goback()">
				 </span>
					<span style="float: left;">主题/板块：
						<select id="subpla" class="selectTar" onchange="subTarget(this)" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;">
							<option value="-1" selected="selected">--请选择--</option>
							<c:forEach items="${subPlas}" var="subPla">
								<option value="${subPla.targetCode}">${subPla.targetName}</option>
							</c:forEach>
						</select>
					 </span>
					 <span style="margin-left: 50px;">指标：
						<select id="target" class="selectTar" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;">
							<option value="-1" selected="selected">--请选择--</option>
						</select>
					 </span>
					
				 <div style="width: 100%;float: left;">
					 <span>功&nbsp;&nbsp;能 ：
						<c:forEach items="${funlist}" var="fun">
							<input type="checkbox" value="${fun.code}">${fun.name}&nbsp;
						</c:forEach>
					</span>
				 </div>
			</div>
		</div>		
	</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<div>SQL：<a href="javascript:showAddSql()" style="color:#3399FF">新增</a></div>
			<input type="hidden" name="record" value="">
			<table cellspacing="0" cellpadding="0" class="t-list table"id="templetView">
				<tbody id="tmpRow">
					<tr>
					    <th width="80px">分组类型</th>
					    <th width="80px">分组维度详细编码</th>
					    <th width="80px">日期查询类型</th>
					    <th width="80px">SQL</th>
						<th style="border-right: 1px solid #ccc; width: 40px">操作</th>
					</tr>
					<tr id="noData"><td colspan="8">暂无数据</td></tr>
				</tbody>
			</table>
			<div style="text-align: center;margin-top: 10px;" id="seve">
				<input type="button" onclick="save()" value="保&#12288;存" style="margin-right: 10px;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;">
			</div>
		</div>
	</div>
	<form id="saveParameterForm" style="display: none;">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">分组类型：</td>
						<td class="text_left" >
							<select name="groupCode" id="groDimDiv" onchange="selectGroDim(this)" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
								<option value=''>--请选择--</option>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">分组详细维度：</td>
						<td class="text_left" >
						<select id="groDimDetailDiv" name="groupDetailCode" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
								<option value=''>--请选择--</option>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">日期查询类型：</td>
						<td class="text_left">
							<select name="dateType" id="dateType" class="formCheck" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
									<option value="">--请选择--</option>
									<option value="DAY">日</option>
									<option value="MONTH">月</option>
									<option value="YEAR">年</option>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">SQL：</td>
						<td class="text_left">
							<textarea rows="6" cols="100" class="formtextarea" id="sqlCode" name="sqlCode"></textarea>
						</td>
					</tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button" id="btnSave" value="确定" onclick="tempSave()"
							class="search_btn"/><input type="button" style="margin: 10px 0px" value="取消" class="search_btn close"
							class="search_btn"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>