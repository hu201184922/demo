<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>固定清单管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript" src="/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {	
		$('#pager').pager('${pageContext.request.contextPath}/admin/fixedList/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				
				deleteConfirm.on('confirm',function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/fixedList/delete',{flCode:data.flCode},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/fixedList/create',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setData(data);
						zhis.setTemplate('default',true);
						$('#pager').pager().reload();
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
			$("#saveFixedListForm")[0].reset();
			$('#saveFixedListForm').data("dialog", $.dialog({
				title : "添加",
				content : $('#saveFixedListForm')[0]
			}));
		});
		$('#saveFixedListForm').ajaxForm(function(data) {
			$('#saveFixedListForm').data("dialog").close();
			$('#pager').pager().reload();
		});	
});	
	
function validate(){
	var id = $('#flCode').val();
	var name = $("#flName").val();
	var dept = $("#flDeptCode").val();
	if($.trim(id) == 0 || $.trim(id) == ''){
		$.dialog({content: '请输入固定清单编号', time: 2000});return;
	}else if($.trim(id)==null || $.trim(id)=='NULL'){
		$.dialog({content: '固定清单编号不能为空', time: 2000});return;
	}
	if($.trim(name)=='' || $.trim(name)==null){
		$.dialog({content: '请输入固定清单名称', time: 2000});return;
	}
	if($.trim(dept)=='' || $.trim(dept)==null){
		$.dialog({content: '请输入所属部门名称', time: 2000});return;
	}
	$('#saveFixedListForm').submit();
}


</script>
</head>
<body>
	<div class="business_title">固定清单管理</div>
	<div class="col_lg_04" style="width: 1203px">

		<div class="business_search_list_warp" style="width: 95%">
			<input type="button"
				style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;"
				id="createParameter" value="新建">
			<table cellspacing="0" cellpadding="0" class="t-list table"
				id="parameterView">
				<tbody>
					<tr>
						<th>序号</th>
						<th>固定清单编码</th>
						<th>固定清单名称</th>
						<th>所属部门</th>
						<th style="border-right: 1px solid #ccc; width: 80px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{index:seq()}</td>
						<td>{flCode}</td>
						<td>{flName}</td>
						<td>{flDeptCode:dict({0:'',101402:'教育培训部',130101:'机构发展部',130107:'个人业务部',130105:'收展部'})}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit">
							<a class="switch" href="javascript:void(0)" template="update">
									<img style="float: left;width:17px"src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
										title="修改" />
							</a><a href="javascript:void(0)" class="removed"> <img
										style="float: left;margin-left: 5px;width:17px"
										src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
										title="删除">
								</a>
						</span></td>
					</tr>
					<tr class="tb_tr_content template" name="update">
						<td>{index:seq()}</td>
						<td>{flCode}</td>
						<td><input style="width: 95%; height: 30px;  border-radius: 5px; border: 1px solid #90C1E6;" class="view-field" name="flName" type="text" /></td>
						<td><select style="width: 95%; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;" name="flDeptCode" class="view-field">
									<option value="">请选择</option>
									<c:forEach items="${dictItem}" var="list">
										<option value="${list.code}">${list.name}</option>
									</c:forEach>
							</select></td>
						<td style="border-right: 1px solid #ccc">
							<span class="span_edit">
							<a href="javascript:void(0)"
								class="save"><img style="float: left;width:17px;" alt="" 
								src="${pageContext.request.contextPath}/static/images/huatai_save.png" title="保存"> 
								</a>
							<a href="javascript:void(0)" class="switch back"  template="default">
								<img style="float: left;width:17px;margin-left: 5px;"
								src="${pageContext.request.contextPath}/static/images/huatai_back.png" title="返回"</img></a> </span></td>
					</tr>

				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form id="saveFixedListForm" style="display: none;"
		action="${pageContext.request.contextPath}/admin/fixedList/create"
		method="post">
		<div id="tagContent3" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">固定清单编码：</td>
						<td class="text_left"><input id="flCode" name="flCode"
							style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
							type="text" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					<tr>
						<td class="text_right">固定清单名称：</td>
						<td class="text_left"><input id="flName" name="flName"
							style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
							type="text" /></td>
					</tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					<tr>
						<td class="text_right">所属部门：</td>
						<td class="text_left"> 
							<select style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;" id="flDeptCode" name="flDeptCode" class="view-field">
									<option value="">请选择</option>
									<c:forEach items="${dictItem}" var="list">
										<option value="${list.code}">${list.name}</option>
									</c:forEach>
							</select>
							
						</td>
					</tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button"
							style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;"
							onclick="validate()" value="提 交" class="search_btn">&nbsp;&nbsp;
							<input type="button" style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>