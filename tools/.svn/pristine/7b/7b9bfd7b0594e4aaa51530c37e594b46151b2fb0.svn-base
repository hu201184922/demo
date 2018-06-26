<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>模板管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/templet/list',15,$('#templetView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/templet/delete',{tempId:data.tempId},function(bit){
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
						title : "修改模板",
						content : $('#updateTempletForm')[0]
					}));
				});
				
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/templet/update',zhis.getData(),function(data){
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
			$("#saveTempletForm")[0].reset();
			$('#saveTempletForm').data("dialog", $.dialog({
				title : "添加模板",
				content : $('#saveTempletForm')[0]
			}));
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
				"tempCode":$("#tempCodeQuery").val(),
				"tempName":$("#tempNameQuery").val()
		}
		$('#pager').pager().setPostData(postData);
		$('#pager').pager().reload();
	}
	
	function sub(){
		var tempCode = $('input[name="tempCode"]').val()
		var tempName = $('input[name="tempName"]').val();
		if ($.trim(tempCode) == '') {
			$.dialog({time: 2000,content:  "请输入模板编码！"});
			return;
		}
		if ($.trim(tempName) == '') {
			$.dialog({time: 2000,content:  "请输入模板名称！"});
			return;
		}
		$('#saveTempletForm').submit();
	}
</script>
</head>
<body>
	<div class="business_title">模板关联管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createTemplet" value="新建模板" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;" >
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="templetAjaxForm"
						action="${pageContext.request.contextPath}/admin/templet/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: left;">模板编码：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text" name="tempCodeQuery" id="tempCodeQuery"  class="text">&nbsp;&nbsp;&nbsp;</td>
									<td style="text-align: left;">模板名称：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text" name="tempNameQuery" id="tempNameQuery" class="text">&nbsp;&nbsp;&nbsp;</td>
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
						<th>模板编码</th>
					    <th>模板名称</th>
						<th>创建时间</th>
						<th>修改时间</th>
						<th style="border-right: 1px solid #ccc; width: 100px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
					    <td>{tempCode}</td>
					    <td>{tempName}</td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
						<td style="border-right: 1px solid #ccc"><span class="span_edit">
							<a href="${pageContext.request.contextPath}/admin/temReg/index?tempId={tempId}"> <img
									style="float: left;width:17px;"
									src="${pageContext.request.contextPath}/static/images/huatai_region.png"
									title="关联区域">
							</a>
							<a href="javascript:void(0)" class="switch" template="update"> 
							<img style="float: left;width:17px;margin-left: 5px;"
								src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="编辑" /></a>&nbsp; 
							<a href="javascript:void(0)" class="removed"> <img
									style="float: left;width:17px;margin-left: 5px;;"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除">
							</a>
						</span></td>
					 </tr>	
					 
					 <tr class="tb_tr_content template" name="update">
					 	<td><input style="width: 100px;"
							class="text_wd180 view-field" name="tempCode" type="text"
							 /></td>
						<td><input style="width: 100px;"
							class="text_wd180 view-field" name="tempName" type="text"
							 /></td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
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
		  action="${pageContext.request.contextPath}/admin/templet/create"
		  method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">模板编码：</td>
						<td class="text_left"><input type="text" name="tempCode" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"/></td>
					</tr>
					<tr><td height="30px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">模板名称：</td>
						<td class="text_left"><input type="text" name="tempName" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"/></td>
					</tr>
					<tr><td height="30px" colspan="2"></td></tr>
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