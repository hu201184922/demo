<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>筛选查询维度管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
			$('#pager').pager('${pageContext.request.contextPath}/admin/queryDim/list',15,$('#templetView').view().on('add',function(data){
				(function(zhis){
					var deleteConfirm = new Ace.awt.ConfirmBox({
						widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
						trigger : zhis.target.find('.removed'),
						positionAdjust : [ 6, -45 ]
					});	
					deleteConfirm.on('confirm',function(w, t) {
						$.getJSON('${pageContext.request.contextPath}/admin/queryDim/delete',{id:data.qdId},function(bit){
							if(bit==true){
								$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
							}
							$('#pager').pager().reload(function(){
								zhis.target.remove();
							});
						});
					});

					zhis.target.find('.span_edit .edit').on('click',function(){
						$("#tempId").val(data.id);
						$('#updateTempletForm').data("dialog", $.dialog({
							title : "修改",
							content : $('#updateTempletForm')[0]
						}));
					});
					zhis.target.find('.save').click(function(){
						$.getJSON('${pageContext.request.contextPath}/admin/queryDim/update',zhis.getData(),function(data){
							$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
							zhis.setData(data);
							zhis.setTemplate('default',true);
							$('#pager').pager().reload();
						});
					});
				})(this);
			}));
			
		$('#templetAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#templetAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$("#createTemplet").click(function() {
			$('#saveTempletForm').data("dialog", $.dialog({
				title : "添加",
				content : $('#saveTempletForm')[0]
			}));
			$('input[name="queryDimCode"]').val("");
			$('input[name="queryDimName"]').val("");
			$('select[name="queryDimShowType"]').find('option[value="-1"]').attr('selected', true);
		});
		
		$('#saveTempletForm').ajaxForm(function(data) {
			$('#saveTempletForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		
		$('#updateTempletForm').ajaxForm(function(data) {
			$('#updateTempletForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		$("#btnQuery").click(function(){
			query();
		});
		query();

    });	
	function query(){
		var postData ={
				"queryDimCode":$("#queryDimCode").val(),
				"queryDimName":$("#queryDimName").val()
		}
		$('#pager').pager().setPostData(postData);
		$('#pager').pager().reload();
	}
	
	function sub(){
		var qdc = $('input[name="queryDimCode"]').val()
		var qdn = $('input[name="queryDimName"]').val();
		var qdt = $('select[name="queryDimShowType"] option:selected').val();
		if ($.trim(qdc) == ',') {
			$.dialog({content: '请输入查询维度编码！', time: 2000});
			return;
		}
		if ($.trim(qdn) == ',') {
			$.dialog({content: '请输入查询维度名称！', time: 2000});
			return;
		}
		if ($.trim(qdt) == '-1') {
			$.dialog({content: '请选择维度展现形式！', time: 2000});
			return;
		}
		$('#saveTempletForm').submit();
	}
</script>
</head>
<body>
	<div class="business_title">查询维度管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createTemplet" value="新建" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;" >
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="templetAjaxForm" action="${pageContext.request.contextPath}/admin/queryDim/list" method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									
									<td style="text-align: left;">查询维度编码：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text" name="queryDimCode" id="queryDimCode"  class="text">&nbsp;&nbsp;&nbsp;</td>
									<td style="text-align: left;">查询维度名称：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text" name="queryDimName" id="queryDimName" class="text">&nbsp;&nbsp;&nbsp;</td>
									<td><input style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" type="button" value="查&#12288;询"style="font-size: 14px;" id="btnQuery"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<table cellspacing="0" cellpadding="0" class="t-list table"id="templetView">
				<tbody>
					<tr>
						<th>序号</th>
						<th>查询维度编码</th>
					    <th>查询维度名称</th>
					    <th>查询维度展现形式</th>
						<th style="border-right: 1px solid #ccc; width: 100px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
					    <td>{index:seq()}</td>
					    <td>{queryDimCode}</td>
					    <td>{queryDimName}</td>
					    <td>{queryDimShowType:dict({input:'输入框', radio:'单选框', checkbox:'复选框', select:'下拉框',select2:'特殊下拉框'})}</td>
                        <td style="border-right: 1px solid #ccc;width:40px"><span class="span_edit">
							<a href="${pageContext.request.contextPath}/admin/queryDimDetail/index?qdId={qdId}" style="float: left;margin-right:5px"><img style="float: left;width:17px"
								src="${pageContext.request.contextPath}/static/images/huatai_content.png"
									title="编辑详细" /></a>
							<a href="javascript:void(0)" class="switch" template="update"> 
							<img style="float: left;width:17px"
								src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="修改" /></a> 
							<a href="javascript:void(0)" class="removed"> <img
									style="float: left;width:17px"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除">
							</a>
						</span>
						</td>
					 </tr>	
					 <tr class="tb_tr_content template" name="update">
					    <td>{index:seq()}</td>
					 	<td><input style="width: 100px;"
							class="text_wd180 view-field" name="queryDimCode" type="text"
							 /></td>
						<td><input style="width: 100px;"
							class="text_wd180 view-field" name="queryDimName" type="text"
							 /></td>
						<td class="text_left">
							<select name="queryDimShowType" class="view-field">
								<option value="-1">--请选择--</option>
								<c:forEach items="${boxs}" var="box">
									<option value="${box.code}">${box.name}</option>
								</c:forEach>
							</select>
						</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> <a href="javascript:void(0)"
								class="save"><img style="float: left;width:17px;margin-left: 10px;" title="保存"
									src="${pageContext.request.contextPath}/static/images/huatai_save.png"></a>&nbsp;
								<a href="javascript:void(0)" class="switch back"
								template="default"><img style="float: left;width:17px;margin-left: 10px;" title="返回"
									src="${pageContext.request.contextPath}/static/images/huatai_back.png"></a>
						</span></td>
					</tr>			
				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form id="saveTempletForm" style="display: none;"
		  action="${pageContext.request.contextPath}/admin/queryDim/create"
		  method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">查询维度编码：</td>
						<td class="text_left"><input type="text" name="queryDimCode" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"/></td>
					</tr>
					<tr><td height="30px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">查询维度名称：</td>
						<td class="text_left"><input type="text" name="queryDimName" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"/></td>
					</tr>
					<tr><td height="30px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">查询维度展现形式：</td>
						<td class="text_left">
							<select name="queryDimShowType" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;">
								<option value="-1">--请选择--</option>
								<c:forEach items="${boxs}" var="box">
									<option value="${box.code}">${box.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr><td height="30px" colspan="2"></td></tr>
					
					<tr>
						<td></td>
						<td class="text_left"><input type="button" value="提 交" onclick="sub()"
							class="search_btn">&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>