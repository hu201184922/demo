<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>指标接口维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/targetInter/list',15,$('#targetInterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF;display:none;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/targetInter/delete',{id:data.id, dtid:data.dtid},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/targetInter/update',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setData(data);
						zhis.setTemplate('default',true);
					});
				});
			})(this);
		}));
		$('#pager').pager().reload();
		$('#deptAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#deptAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$("#createdept").click(function() {
			$('#savedeptForm').data("dialog", $.dialog({
				title : "添加部门指标",
				content : $('#savedeptForm')[0]
			}));
		});
		$('#savedeptForm').ajaxForm(function(data) {
			$('#savedeptForm').data("dialog").close();
			$('#pager').pager().reload();
		});
});	
	
	function validate(){
		var name = $('#tagContent2 input[name="name"]').val();
		var tid = $("#tagContent2 select[name='tid']").find('option:selected').val();
		var code = $("#tagContent2 input[name='code']").val();
		var sort = $("#tagContent2 input[name='sort']").val();
		if($.trim(name)==''){
			$.dialog({content: '请输入部门名称', time: 2000});return;
		}else if($.trim(name)== null|| $.trim(name)=='NULL'){
			$.dialog({content: '部门名称不能为NULL', time: 2000});return;
		}
		if(tid == 0){
			$.dialog({content: '请选择对应指标', time: 2000});return;
		}
		if($.trim(sort)==''){
			$.dialog({content: '请输入排序', time: 2000});return;
		}else if($.trim(sort)==null || $.trim(sort)=='NULL'){
			$.dialog({content: '排序不能为NULL', time: 2000});return;
		}
		$('#savedeptForm').submit();
	}
</script>
</head>
<body>
	<div class="business_title">接口字段维护</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createdept" value="新建">
		 	<div class="business_search">
				<div class="business_search_left">
					<form id="deptAjaxForm"
						action="${pageContext.request.contextPath}/admin/targetInter/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">字段编号：</td>
									<td colspan="2"><input type="text" style="width: 200px;"
										name="name" class="text"></td>
									<td><input type="submit" value="查&#12288;询"
										style="font-size: 14px;"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="targetInterView">
				<tbody>
					<tr>
						<th width="">序号</th>
						<th width="">指标编号</th>
						<th width="">表名编号</th>
						<th style="border-right: 1px solid #ccc; width: 8%">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td style="display:none">{tiId}</td>
						<td>{index:seq()}</td>
						<td>{targetCode}</td>
						<td>{interId}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> <a href="javascript:void(0)"
								class="switch" template="update"> <img style="float: left;"
								src="${pageContext.request.contextPath}/static/images/xiugai.png"
									title="修改" />
							</a> <a href="javascript:void(0)" class="removed"> <img
									style="float: left;"
									src="${pageContext.request.contextPath}/static/images/del_icon.png"
									title="删除">
							</a>
						</span></td>
					</tr>
					 <tr class="tb_tr_content template" name="update">
					 	<td>{index:seq()}</td>
						<td><input style="width: 120px;"
							class="text_wd180 view-field" name="targetCode" type="text"
							 /></td>					 
						<td><input style="width: 120px;" class="text_wd180 view-field" name="interId" type="text"/>
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
	<form id="savedeptForm" style="display: none;"
		action="${pageContext.request.contextPath}/admin/interField/create"
		method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
				
					<tr>
						<td class="text_right">指标接口编号：</td>
						<td class="text_left"><input name="tiId" type="text" />
						
						</td>
					</tr>
						<tr>
						<td class="text_right">指标编号：</td>
						<td class="text_left"><select name="targetCode" mapping="targetCode" class="view-field">
							<option value="0">--请选择--</option>
							<c:forEach items="${targets}" var="target">
								<option value="${target.id}">${target.id}</option>
							</c:forEach>
						</select>
						</td>
					</tr>
					</tr>
						<tr>
						<td class="text_right">表名编号：</td>
						<td class="text_left"><select name="interId" mapping="tid" class="view-field">
							<option value="0">--请选择--</option>
							<c:forEach items="${targets}" var="target">
								<option value="${target.id}">${target.id}</option>
							</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button" onclick="validate()" value="提 交"
							class="search_btn">&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>