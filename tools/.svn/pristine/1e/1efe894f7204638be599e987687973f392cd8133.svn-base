<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>查询维度内容</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function back(){
		window.history.back(-1);
	 };
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/queryDimDetail/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					
					$.post('${pageContext.request.contextPath}/admin/queryDimDetail/delete',{id:data.qddId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/queryDimDetail/update',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setData(data);
						zhis.setTemplate('default',true);
						$('#pager').pager().reload();
					});				
				});			
			})(this);
		}));
		//$('#pager').pager().reload();
		
		$('#parameterAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#parameterAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$("#createParameter").click(function() {
			$('#saveParameterForm').data("dialog", $.dialog({
				title : "添加",
				content : $('#saveParameterForm')[0]
			}));
			$('input[name="keyCode"]').val("");
			$('input[name="val"]').val("");
			$('select[name="isDefault"]').find('option[value="-1"]').attr('selected', true);
		});
		$('#saveParameterForm').ajaxForm(function(data) {
			$('#saveParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		$("#btnQuery").click(function(){
			query();
		});
		function query(){
			var postData = {
					'qdId' : '${queryDim.qdId}',
					'keyCode' : $(".keyCode").val(),
					'val' : $(".val").val()
			}
			$('#pager').pager().setPostData(postData);
			$('#pager').pager().reload();
		}
		query();
});	
	
	function sub(){
		var id = $('#keyCode').val();
		var name = $("#val").val();
		if($.trim(id) == 0 || $.trim(id) == ''){
			$.dialog({content: "请输入KEY值", time: 2000});return;
		}else if($.trim(id)==null || $.trim(id)=='NULL'){
			$.dialog({content: "请输入KEY值", time: 2000});return;
		}
		if($.trim(name)=='' || $.trim(name)==null){
			$.dialog({content: "请输入VALUE值", time: 2000});return;
		}
		if ($('select[name="isDefault"] option:selected').val() == "-1") {
			$.msgbox({time: 2000,msg: "请选择是否设为默认",icon:"error"});
			return;
		}
		$("#saveParameterForm").submit();
	}
</script>
</head>
<body>
	<div class="business_title">查询维度内容</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%;padding-bottom: 0px;">
			<table>
				<tbody>
					<tr>
			<td style="float: left;">查询维度编码： ${queryDim.queryDimCode }</td>
			<td style="float: left;margin-left: 45px;">查询维度名称：  ${queryDim.queryDimName }</td>
			</table>
		</div>
		<a style="float: right;position: relative; margin-top: -10px;margin-right: 30px; line-height: 25px;padding: 0 11px;border: 1px #26bbdb solid;
            				border-radius: 3px; display: inline-block;" onclick="back()">返回</a>
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createParameter" value="新建" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;">
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="parameterAjaxForm"
						action="${pageContext.request.contextPath}/admin/queryDimDetail/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
								
									<td style="text-align: right">KEY值：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="keyCode" class="keyCode">&nbsp;&nbsp;</td>

									<td style="text-align: right">VALUE值：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="val" class="val" >&nbsp;&nbsp;</td>
									<td><input type="button" value="查&#12288;询"
										style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" id="btnQuery"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="parameterView">
				<tbody>
					<tr>
						<th>KEY值</th>
						<th>VALUE值</th>
						<th>是否默认</th>
						<!-- <th width="80px">创建人</th>
						<th width="80px">修改人</th> -->
						<th style="border-right: 1px solid #ccc; width: 80px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
                       <td>{keyCode}</td>
						<td>{val}</td>
						<td>{isDefault:dict({1:'是',0:'否'})}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> 
							<a href="javascript:void(0)"
								class="switch" template="update"> <img style="float: left;width:17px"
								src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="修改" />
							</a> <a href="javascript:void(0)" class="removed"> <img
									style="float: left;width:17px"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除">
							</a>
							
						</span></td>
					</tr>
					 <tr class="tb_tr_content template" name="update">
					 	<td> <input
							style="width: 120px;" class="view-field" name="keyCode"
							type="text" /></td>
						<td >
						<input class="view-field" name="val" type="text"/>
						</td>
						<td>
							<select name="isDefault" class="view-field">
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> <a href="javascript:void(0)"
								class="save"><img style="float: left;" title="保存"
									src="${pageContext.request.contextPath}/static/images/save.png"></a>&nbsp;
								<a href="javascript:void(0)" class="switch back"
								template="default"><img style="float: left;" title="返回"
									src="${pageContext.request.contextPath}/static/images/fanhui.png"></a>
						</span></td>
					</tr>

				</tbody>
				
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form id="saveParameterForm" style="display: none;"
		action="${pageContext.request.contextPath}/admin/queryDimDetail/create"
		method="post">
		<input type="hidden" name="qdId" value="${queryDim.qdId}" />
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">KEY值：</td>
						<td class="text_left"><input name="keyCode" type="text" id="keyCode"
							style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"></input></td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">VALUE值：</td>
						<td class="text_left"><input name="val" type="text" id="val"
							style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"></input></td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">是否设为默认：</td>
						<td class="text_left">
							<select name="isDefault" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;">
								<option value="-1">--请选择--</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button" value="提 交" onclick = "sub()"
							class="search_btn">&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>