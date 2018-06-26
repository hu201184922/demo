<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>指标管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		if('${msg}'!=null){
			if('${msg}'=='1'){
				$.msgbox({time: 2000,msg: "添加成功!",icon:"success"});
			}
			if('${msg}'=='2'){
				$.msgbox({time: 2000,msg: "更新成功!",icon:"success"});
			}
			if('${msg}'=='-1'){
				$.msgbox({time: 2000,msg: "服务器忙!。。。",icon:"success"});
			}
		}
		var page = Ace.decode("${u:serialize(pager)}");
		$('#pager').pager('${pageContext.request.contextPath}/admin/target/list',15,$('#targetView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; display:none; text-align:center;  line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/target/delete',{targetCode:data.targetCode},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/target/update',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setData(data);
						zhis.setTemplate('default',true);
					});
				});
			})(this);
		})).setJSON(page);
		$('#pager').pager().reload();
		$('#targetAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#targetAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$('#saveTargetForm').ajaxForm(function(data) {
			$('#saveTargetForm').data("dialog").close();
			$('#pager').pager().reload();
		});
});	
</script>
</head>
<body>
	<div class="tagContentList">
	<div class="business_title">指标管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<a ajax="{target:'.tagContentList'}" style="line-height: 25px;padding: 0 11px;border: 1px #26bbdb solid;
            border-radius: 3px; display: inline-block;" id="createTarget" href="${pageContext.request.contextPath}/admin/target/updatePage">
				新建指标
			</a>
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="targetAjaxForm"
						action="${pageContext.request.contextPath}/admin/target/list"
						method="post" style="width:900px">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">指标代码：</td>
									<td><input style="border-radius:5px; width:300px;height: 30px;border:1px solid #90C1E6;" class="text_wd180 view-field" name="targetCode" type="text"
							 			/>&nbsp;&nbsp;</td>
							 		<td style="text-align: right;border-left: 100px">指标名称：</td>
									<td><input style="border-radius:5px; width:300px;height: 30px;border:1px solid #90C1E6;" class="text_wd180 view-field" name="targetName" type="text"
							 			/>&nbsp;&nbsp;</td>
								</tr>
								<tr>
							 		<td style="text-align: right">指标属性：</td>
									<td><select style="width: 301px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" name="targetType"  class="view-field">
										<option value="">请选择</option>
										<option value="0">主题或板块</option>
										<option value="1">一级</option>
										<option value="2">二级指标组</option>
										<option value="3">二级</option>									
									</select>&nbsp;&nbsp;</td>
									<td style="text-align: right">主题：</td>
									<td><select style="width: 301px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" name="parentCode"  class="view-field">
										<option value="">请选择</option>
										<c:forEach items="${subject}" var="list">
											<option value="${list.targetCode}">${list.targetName}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;</td>
								</tr>
							</tbody>
							<input type="submit" value="查&#12288;询"
								style="float: right;margin-top: 60px;margin-right: 50px;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;">
						</table>
					</form>
				</div>
			</div>			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="targetView">
				<tbody>
					<tr>
					      <th width="">指标代码</th>
					      <th width="80px">部门</th>	
					      <th width="">指标名称</th>			      				      
						  <th width="">指标属性</th>		 								 
						<th style="border-right: 1px solid #ccc; width: 110px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">	 
						<td>{targetCode}</td>
						<td>{deptCode:dict({0:'',101402:'教育培训部',130101:'机构发展部',130107:'个人业务部',130105:'收展部'})}</td>	
						<td>{targetName}</td>
						<td>{targetType:dict({0:'主题或模板',1:'一级',2:'二级分组',3:'二级'})}</td>
						<td style="border-right: 1px solid #ccc">
							<span class="span_edit">
								<a ajax="{target:'.tagContentList:last'}" class="new_a edit" href="${pageContext.request.contextPath}/admin/target/updatePage?targetCode={targetCode}">
									<img style="float: left;width:17px;margin-left: 15px;" src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
										title="编辑" />
								</a> <a href="javascript:void(0)" class="removed"> <img
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
	</div>
</body>
</html>