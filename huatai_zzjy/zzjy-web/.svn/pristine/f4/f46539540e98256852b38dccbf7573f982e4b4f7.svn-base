<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>指标分组维度管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/tarGroDim/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					
					$.post('${pageContext.request.contextPath}/admin/tarGroDim/delete',{id:data.tgdId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/tarGroDim/update',zhis.getData(),function(data){
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
		});
		$('#saveParameterForm').ajaxForm(function(data) {
			$('#saveParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
});	
</script>
</head>
<body>
	<div class="business_title">指标分组维度管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createParameter" value="新建" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;">
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="parameterAjaxForm"
						action="${pageContext.request.contextPath}/admin/tarGroDim/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">分组维度名称：</td>
									<td><input style="width: 300px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="groDimName"  class="text">&nbsp;&nbsp;</td>
									<td><input type="submit" value="查&#12288;询"
										style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="parameterView">
				<tbody>
					<tr>
						<th width="100px">分组维度名称</th>
						<th width="100px">指标名称</th>
						<th width="60px">操作状态</th>
						<th width="150px">创建时间</th>
						<th width="150px">修改时间</th>
						<th width="80px">创建人</th>
						<th width="80px">修改人</th>
						<th style="border-right: 1px solid #ccc; width: 80px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{groDimName}</td>
						<td>{targetName}</td>
						<td>{opType:dict({A:'新增',D:'删除',U:'更新',R:'查看'})}</td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
						<td>{creatorName}</td>
						<td>{modifierName}</td>
						
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
						<td >
							<select name="gddId"  class="view-field">
							  <option value="{gddId}"  selected="true">--请选择--</option>
								<c:forEach items="${groDimDetails}" var="groDimDetail">
								   <option value="${groDimDetail.gddId}">${groDimDetail.groDimName}</option>
								</c:forEach>
						</select>
						</td>
						<td >
							<select name="targetCode"  class="view-field">
							  <option value="{targetCode}"  selected="true">--请选择--</option>
								<c:forEach items="${targets}" var="target">
								   <option value="${target.targetCode}">${target.targetName}</option>
								</c:forEach>
						</select>
						</td>
						<td>{opType:dict({A:'新增',D:'删除',U:'更新',R:'查看'})}</td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
						<td>{creatorName}</td>
						<td>{modifierName}</td>
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
		action="${pageContext.request.contextPath}/admin/tarGroDim/create"
		method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					
					<tr>
						<td class="text_right">分组维度名称：</td>
						<td class="text_left">
							<select name="gddId" style="border-radius:5px; width:202px;height: 32px;border:1px solid #90C1E6;">
								<option value="0">--请选择--</option>
								<c:forEach items="${groDimDetails}" var="groDimDetail">
								   <option value="${groDimDetail.gddId}">${groDimDetail.groDimName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">指标名称：</td>
						<td class="text_left">
							<select name="targetCode" style="border-radius:5px; width:202px;height: 32px;border:1px solid #90C1E6;">
								<option value="0">--请选择--</option>
								<c:forEach items="${targets}" var="target">
								   <option value="${target.targetCode}">${target.targetName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="submit" value="提 交"
							class="search_btn">&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>