<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>执行结果</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript" src="/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {	
			
		$('#pager').pager('${pageContext.request.contextPath}/admin/triggleExecute/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				
				deleteConfirm.on('confirm',function(w, t) {
					$.getJSON('${pageContext.request.contextPath}/admin/roles/delete',{roleCode:data.roleCode},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/roles/update',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setData(data);
						zhis.setTemplate('default',true);
						$('#pager').pager().reload();
					});
				});
			
				
				zhis.target.find('.openTarget').click(function(){
					
					var str=$(this).attr('name').split(',');
					$('#roleCode').text(str[0]);
					$('#roleName').text(str[1]);
					openTarget(str[0],str[1]);
				});
				zhis.target.find('.openBill').click(function(){
					
					var str=$(this).attr('name').split(',');
					$('#roleCode').text(str[0]);
					$('#roleName').text(str[1]);
					openBill(str[0],str[1]);
				});
			})(this);
		}));
		$('#pager').pager().reload();
		
		$('#parameterAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#parameterAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
});	
 
</script>
</head>
<body>
	<div class="business_title">执行结果</div>
	<div class="col_lg_04" style="width: 1203px">
		
		<div class="business_search_list_warp" style="width: 95%">
			<!-- <input type="button"
				style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;"
				id="createParameter" value="新建"> -->
			<table cellspacing="0" cellpadding="0" class="t-list table"
				id="parameterView">
				<tbody>
					<tr>
						<th>序号</th>
						<th>任务编码</th>
						<th>任务名称</th>
						<th>状态</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>运行时间</th>
						<!-- <th style="border-right: 1px solid #ccc; width:120px">操作</th> -->
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{index:seq()}</td>
						<!-- <td>{qrtzGroupId}</td> -->
						<td>{code}</td>
						<td>{name}</td>
						<td>{execStatus:dict({0:'正在执行',1:'已执行'})}</td>
						<td>{execBeginTime}</td>
						<td>{execEndTime}</td>
						<td>{resTime}</td>
						<%-- <td style="border-right: 1px solid #ccc"><span
							class="span_edit">
							<a href="javascript:void(0)" class="switch" template="update"> 
								<img style="float: left;margin-left: 5px;width:17px;"
								src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="修改" /></a> 
							<a href="javascript:void(0)" class="removed"> <img
									style="float: left;margin-left: 5px;width:17px;"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除">
							</a>
						</span></td> --%>
					</tr>
					<tr class="tb_tr_content template" name="update">
						<td>{index:seq()}</td>
						<td><input class="view-field" name="roleName" type="text" /></td>
						<td>{roleCode}</td>
						<td>{createTime}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit">
							<a href="javascript:void(0)" class="save"> 
								<img style="float: left;margin-left: 20px;width:17px;"
								src="${pageContext.request.contextPath}/static/images/huatai_save.png"
									title="保存" /></a> 
							<a href="javascript:void(0)" class="switch back" template="default"> <img
									style="float: left;margin-left: 10px;width:17px;"
									src="${pageContext.request.contextPath}/static/images/huatai_back.png"
									title="返回">
							</a>
							</span></td>
					</tr>

				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>

</body>
</html>