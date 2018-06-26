<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>指标SQL维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.selectTar {width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;}
	.sql{float: left;width:80px;text-overflow: ellipsis;-moz-text-overflow: ellipsis; overflow: hidden;white-space: nowrap;}
</style>
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/tarInitSql/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					$.post('${pageContext.request.contextPath}/admin/tarInitSql/delete',{id:data.tisId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
			})(this);
		}));
		$('#pager').pager().reload();
		$('#parameterAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#parameterAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		$("#createParameter").click(function() {
			$('#saveParameterForm').data("dialog", $.dialog({
				title : "添加",
				content : $('#saveParameterForm')[0]
			}));
		});
    });	
	
	function goAdd(){
		location.href = "${pageContext.request.contextPath}/admin/tarInitSql/goAddTarInit";
	}
	
	function selectGroDim(gdId,groupDetailCode,targetCode){
		if (typeof(targetCode) == "undefined") {
			targetCode = $("#targetCode").val();
		}
		$("#groDimDetailDiv").empty();
		$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listGroDimDetail',{targetCode:targetCode,gdId:$("#groDimDiv").find("option:selected").attr ("gdId")},function(data){
			var div = $("#groDimDetailDiv");
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
	
	function updateRecord(tisId, targetCode, groupCode, groupDetailCode,dateType, funIds){
		$("#targetCode").val(targetCode);
		$("#tisId").val(tisId);
		$("#funIds").val(funIds);
		$("#dateType").val(dateType);
		$("input[id='"+dateType.toLowerCase()+"']").attr("checked", true);
		var sqlCode = $('#'+tisId+'_sql').text();
		$('#sqlCode').val(sqlCode);
		var gdId = null;
		$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listGroDim',{targetCode:targetCode},function(data){
			$("#groDimDiv").empty();
			var div = $("#groDimDiv");
			div.append('<option value="">--请选择--</option>');
			$.each( data, function(index, content)
			  {
				var flag = false;
				if(groupCode!=null && groupCode==content.groDimTypeCode){
					flag = true;
					gdId = content.gdId;
				}
				div.append('<option name="groupCode" gdId="'+content.gdId+'" value="'+content.groDimTypeCode+'" class="formCheck" '+(flag?"selected":"")+'>'+content.groDimTypeName+"</option>")
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
	
	function update(){
		var groupCode = $('#groDimDiv').val();
		 var groupDetailCode = $('input[name="groupDetailCode"]:checked').val();
		 var dateType = $('input[name="dateType"]:selected').val();
		 var sqlCode = $('#sqlCode').val();
		 
		 $("#sqlCode").val(encodeURIComponent($("#sqlCode").val()));
		 $("#groupType").val(groupCode);
		 if (typeof(groupCode) == "undefined") {
			 groupCode="";
		 }
		 if (typeof(groupDetailCode) == "undefined" ||groupDetailCode==null) {
			 groupDetailCode="";
		 }
		 if (typeof(dateType) == "undefined") {
			 dateType="";
		 }
		 if (sqlCode == "") {
			 sqlCode =="";
		 }
		 $("#saveParameterForm").submit();
	}
	
	$('#saveParameterForm').ajaxForm(function(data) {
		$('#saveParameterForm').data("dialog").close();
		$('#pager').pager().reload();
	});
</script>
</head>
<body>
	<div class="business_title">指标SQL维护</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
		<input type="button" onclick="goAdd()" value="新建" 
				style="line-height: 25px;padding: 0 11px;border: 1px #26bbdb solid;
            border-radius: 3px; display: inline-block;" >
			<div class="business_search">
				
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="parameterAjaxForm"
						action="${pageContext.request.contextPath}/admin/tarInitSql/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">指标编号：</td>
									<td><input style="width: 250px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="targetCode"  class="text">&nbsp;&nbsp;</td>
									<td style="text-align: right">使用功能：</td>
									<td><select name="funIds" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
											<option value=''>--请选择--</option>
											<c:forEach items="${funlist}" var="fun">
												<option value='${fun.code}'>${fun.name}</option>
											</c:forEach>
										</select>&nbsp;&nbsp;</td>
									<td><input type="submit" value="查&#12288;询"
										style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<table cellspacing="0" cellpadding="0" class="t-list table" id="parameterView">
				<tbody>
					<tr>
						<th width="60px">序号</th>
						<th width="80px">指标编码</th>
						<th width="80px">指标名称</th>
					    <th width="80px">分组类型</th>
					    <th width="80px">分组维度详细名称</th>
					    <th width="80px">日期查询类型</th>
					    <th width="80px">使用功能</th>
					    <th width="80px">SQL</th>
						<th style="border-right: 1px solid #ccc; width: 40px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
					    <td>{index:seq()}</td>
					    <td>{targetCode}</td>
					    <td>{targetName}</td>
					    <td>{groupType}</td>
					    <td>{groupDetailName}</td>
					    <td>{dateType}</td>
					    <td>{funIds:dict({01:'板块',02:'清单',03:'预警',04:'单位'})}</td>
					    <td><div class="sql" id="{tisId}_sql">{sqlCode}</div></td>
                        <td style="border-right: 1px solid #ccc;width:40px"><span class="span_edit">
							<a href="javascript:void(0)" class="switch" onclick="updateRecord('{tisId}','{targetCode}', '{groupType}', '{groupDetailCode}', '{dateType}', '{funIds}')"> 
							<img style="float: left;width:17px;"
								src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="修改" /></a> 
							<a href="javascript:void(0)" class="removed"> <img
									style="float: left;width:17px;margin-left: 5px;"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除">
							</a>
						</span>
						</td>
					 </tr>
				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form id="saveParameterForm" action="${pageContext.request.contextPath}/admin/tarInitSql/update" 
		method="post" style="display: none;">
		<input type="hidden" id="targetCode" name="targetCode">
		<input type="hidden" id="tisId" name="tisId">
		<input type="hidden" id="funIds" name="funIds">
		<input type="hidden" id="groupType" name="groupType">
		<input type="hidden" id="groupDetailCode" name="groupDetailCode">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">分组类型：</td>
						<td class="text_left">
							<select name="groupCode" id="groDimDiv" onchange="selectGroDim(this)" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
								<option value=''>--请选择--</option>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">分组详细维度：</td>
						<td class="text_left">
							<select id="groDimDetailDiv" name="groupDetailCode" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
								<option value=''>--请选择--</option>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">日期查询类型：</td>
						<td class="text_left">
							<select id="dateType" name="dateType" class="formCheck" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
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
						<td class="text_left"><input type="button" onclick="update()" style="margin-left: 280px;
    margin-top: 20px;" id="btnSave" value="确认" class="search_btn"/>&nbsp;&nbsp;<input type="button" style="margin-top: 20px;"
								value="取 消" class="search_btn close"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>