<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>模板区域管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/temReg/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					
					$.post('${pageContext.request.contextPath}/admin/temReg/delete',{id:data.regId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/temReg/update',zhis.getData(),function(data){
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
			$("#saveParameterForm")[0].reset();
			$('#saveParameterForm').data("dialog", $.dialog({
				title : "添加",
				content : $('#saveParameterForm')[0]
			}));
		});
		$('#saveParameterForm').ajaxForm(function(data) {
			$('#saveParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		$("#btnQuery").click(function(){
			query();
		});
		query();
});	
	function query(){
		var postData ={
				"tempId":"${templet.tempId}",
				"regCode":$("#regCodeQuery").val(),
				"regName":$("#regNameQuery").val()
		}
		$('#pager').pager().setPostData(postData);
		$('#pager').pager().reload();
	}
	
	function sub(){
		var regCode = $('input[name="regCode"]').val()
		var regName = $('input[name="regName"]').val();
		if ($.trim(regCode) == '') {
			$.dialog({time: 2000,content:  "请输入区域编码！"});
			return;
		}
		if ($.trim(regName) == '') {
			$.dialog({time: 2000,content:  "请输入区域名称！"});
			return;
		}
		$('#saveParameterForm').submit();
	}
	
</script>
</head>
<body>
	<div class="business_title">模板区域管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<table style="width:25%">
				<tbody>
					<tr>
						<td>模板编码：${templet.tempCode }</td>
						<td style="text-align: left;">模板名称：${templet.tempName }</td>
						<td style="text-align: left;"><a style="position: absolute; margin-left: 800px; line-height: 25px;padding: 0 11px;border: 1px #26bbdb solid;
            				border-radius: 3px; display: inline-block;" href="${pageContext.request.contextPath}/admin/templet/index">返回</a>
            			</td>
            		</tr>
				</tbody>
			</table>
		</div>
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createParameter" value="新建" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;">
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="parameterAjaxForm"
						action="${pageContext.request.contextPath}/admin/temReg/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">区域编码：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="regCodeQuery" id="regCodeQuery" class="text">&nbsp;&nbsp;</td>
									<td style="text-align: right">区域名称：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="regNameQuery" id="regNameQuery"  class="text">&nbsp;&nbsp;</td>
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
						<th>区域编码</th>
						<th>区域名称</th>
						<th>创建时间</th>
						<th>修改时间</th>
						<th style="border-right: 1px solid #ccc; width: 100px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{regCode}</td>
						<td>{regName}</td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> 
							<a href="${pageContext.request.contextPath}/admin/tarReg/index?tempId=${templet.tempId }&regId={regId}"
								> <img style="float: left;width:17px"
								src="${pageContext.request.contextPath}/static/images/huatai_target.png"
									title="关联指标" />
							</a>
							<a href="javascript:void(0)"
								class="switch" template="update"> <img style="float: left;width:17px;margin-left: 5px;"
								src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="修改" />
							</a> <a href="javascript:void(0)" class="removed"> <img
									style="float: left;width:17px;margin-left: 5px;"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除">
							</a>
						</span></td>
					</tr>
					 <tr class="tb_tr_content template" name="update">
					 	<td> <input
							style="width: 120px;" class="view-field" name="regCode"
							type="text" /></td>
						<td >
						<input class="view-field" name="regName" type="text"/>
						</td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
						<!-- <td>{creatorName}</td>
						<td>{modifierName}</td> -->
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> <a href="javascript:void(0)"
								class="save"><img style="float: left;width:17px;margin-left: 5px;" title="保存"
									src="${pageContext.request.contextPath}/static/images/huatai_save.png"></a>&nbsp;
								<a href="javascript:void(0)" class="switch back"
								template="default"><img style="float: left;width:17px;margin-left: 10px;" title="返回"
									src="${pageContext.request.contextPath}/static/images/huatai_back.png"></a>
						</span></td>
					</tr>

				</tbody>
				<input type="hidden" name="tempId" value="${templet.tempId }" />
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form id="saveParameterForm" style="display: none;"
		action="${pageContext.request.contextPath}/admin/temReg/create"
		method="post">
		<input type="hidden" name="tempId" value="${templet.tempId }" />
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">区域编码：</td>
						<td class="text_left"><input name="regCode" type="text"
							style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"></input></td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">区域名称：</td>
						<td class="text_left"><input name="regName" type="text"
							style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"></input></td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr><td height="20px" colspan="2"></td></tr>
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