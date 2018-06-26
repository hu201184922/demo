<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>角色管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript" src="/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {	
			
		$('#pager').pager('${pageContext.request.contextPath}/admin/roles/list',15,$('#parameterView').view().on('add',function(data){
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
		
		$("#createParameter").click(function() {
			$("#saveParameterForm")[0].reset();
			$('#saveParameterForm').data("dialog", $.dialog({
				title : "添加角色",
				content : $('#saveParameterForm')[0]
			}));
		});
		$('#saveParameterForm').ajaxForm(function(data) {
			$('#saveParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});	
		
});	
var selectedBill=[];	
function openBill(roleCode,roleNode){
	var targetTree = $('#targetTree').data('zTree');
	if(targetTree) $('#targetTree').data('zTree','');
	var size=0;
	var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey: 'id',
					pIdKey: 'pId'
				}
			}
			,view:{
				showIcon:false,
				showLine: true
			},check:{enable:true},
			callback:{
				onCheck:function(event,treeId,treeNode){
					selectedBill=[];
					var nodes =  targetTree.getCheckedNodes(true);		
					var nodes1 =  targetTree.getCheckedNodes(false);		
					$.each(nodes, function (n, value) {
						selectedBill.push(this.flCode);
					})
					$.each(nodes1, function (n, value) {
						size-=1;
					})
				}
			}
	};
	var selected;
	//关联固定清单

		$.ajax({
			url:'${pageContext.request.contextPath}/admin/roleFixedList/tree',
			data:{roleCode:roleCode},
			success:function(data){
				selected=data;
				$('#targetTree').html('');
				for(var i=0;i<data.length;i++){
					selected[i].id = data[i].id;//添加节点id属性
					selected[i].pId = data[i].pId;//添加节点pid属性
					selected[i].name = data[i].flName;//添加name属性
					selected[i].checked = data[i].checked;//把所有节点默认为父节点
					size=data.length;
				}		
				targetTree=$('#targetTree').zTree(setting,selected);
				$('#saveTargetFrom').data("dialog", $.dialog({
					title : "关联固定清单",
					content : $('#saveTargetFrom')[0]
				}));
				
			}
		})
		$("#addTarget").off('click')
	 	$("#addTarget").click(function(){
	 		$.getJSON('${pageContext.request.contextPath}/admin/roleFixedList/create',{targetCodes:selectedBill,roleCode:roleCode,size:size},function(result){
				if(result==true){
					$('#saveTargetFrom').data("dialog").close();
					$.msgbox({time: 2000,msg: "关联成功!",icon:"success"});
				}else{
					$.msgbox({time: 2000,msg: "服务器忙!",icon:"success"});
					$('#saveTargetFrom').data("dialog").close();
				}
		 	})
	 	});
}

var selectedList=[];
function openTarget(roleCode,roleNode){
	var targetTree = $('#targetTree').data('zTree');
	if(targetTree) $('#targetTree').data('zTree','')
	var setting = {
			treeId: "targetTree",
			data : {
				simpleData : {
					enable : true,
					idKey: 'id',
					pIdKey: 'pId'
				}
			}
			,view:{
				showIcon:false,
				showLine: true
			},check:{enable:true},
			callback:{
				onCheck:function(event,treeId,treeNode){
					selectedList=[];
					var nodes = targetTree.getCheckedNodes(true);
					var nodes1 = targetTree.getCheckedNodes(false);						
					$.each(nodes, function (n, value) { 
						selectedList.push(this.id);
					})
					$.each(nodes1, function (n, value) {
						size-=1;
					})
				}
			}
	};
	
	//关联指标	
	var selected,size=0;
 	$.ajax({
		url:'${pageContext.request.contextPath}/admin/roleTarget/tree',
		data:{roleCode:roleCode},
		success:function(data){		
			selected=data
			$('#targetTree').html('');
			for(var i=0;i<data.length;i++){
				selected[i].id = data[i].targetCode;//添加节点id属性
				selected[i].pId = data[i].parentCode;//添加节点pid属性
				selected[i].name = data[i].targetName;//添加name属性
				selected[i].checked = data[i].checked;//默认选中节点
				size=data.length;
			}		
			targetTree=$('#targetTree').zTree(setting,selected);
			
			$('#saveTargetFrom').data("dialog", $.dialog({
				title : "关联指标",
				content : $('#saveTargetFrom')[0]
			}));
		}
		
	})
	$("#addTarget").off('click')
 	$("#addTarget").click(function(){
 		$.getJSON('${pageContext.request.contextPath}/admin/roleTarget/create',{targetCodes:selectedList,roleCode:roleCode,size:size},function(result){
			if(result==true){
				$.msgbox({time: 2000,msg: "关联成功!",icon:"success"});
				$('#saveTargetFrom').data("dialog").close();
			}
	 	})
 	});
 	
} 
 

function validate(){
	var id = $('#tagContent2 input[name="roleCode"]').val();
	var name = $("#tagContent2 input[name='roleName']").val();
	if($.trim(id) == 0 || $.trim(id) == ''){
		$.dialog({content: '请输入角色编号', time: 2000});return;
	}else if($.trim(id)=='null' || $.trim(id)=='NULL'){
		$.dialog({content: '角色编号不能为空', time: 2000});return;
	}
	if($.trim(name)=='' || $.trim(name)==null){
		$.dialog({content: '请输入角色名称', time: 2000});return;
	}
	$('#saveParameterForm').submit();
}


</script>
</head>
<body>
	<div class="business_title">角色管理</div>
	<div class="col_lg_04" style="width: 1203px">
		
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button"
				style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;"
				id="createParameter" value="新建角色">
			<table cellspacing="0" cellpadding="0" class="t-list table"
				id="parameterView">
				<tbody>
					<tr>
						<th>序号</th>
						<th>角色名称</th>
						<th>角色编号</th>
						<th>创建时间</th>
						<th style="border-right: 1px solid #ccc; width:120px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{index:seq()}</td>
						<td>{roleName}</td>
						<td>{roleCode}</td>
						<td>{createTime}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> <a href="javascript:void(0)"
								class="openBill" class="switch" name="{roleCode},{roleName}"><img style="float: left;width:17px;"
								src="${pageContext.request.contextPath}/static/images/huatai_bill.png"
									title="关联固定清单" /></a>
							<a href="javascript:void(0)"
								class="openTarget" class="switch" name="{roleCode},{roleName}"><img style="float: left;margin-left: 5px;width:17px;"
								src="${pageContext.request.contextPath}/static/images/huatai_target.png"
									title="关联指标" /></a>
							<a href="javascript:void(0)" class="switch" template="update"> 
								<img style="float: left;margin-left: 5px;width:17px;"
								src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="修改" /></a> 
							<a href="javascript:void(0)" class="removed"> <img
									style="float: left;margin-left: 5px;width:17px;"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除">
							</a>
						</span></td>
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
	<form id="saveParameterForm" style="display: none;"
		action="${pageContext.request.contextPath}/admin/roles/create"
		method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">角色编号：</td>
						<td class="text_left"><input name="roleCode"
							style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
							type="text" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					<tr>
						<td class="text_right">角色名称：</td>
						<td class="text_left"><input name="roleName"
							style="width: 200px; height: 30px; border-radius: 5px; border: 1px solid #90C1E6;"
							type="text" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td height="10px" colspan="2"></td>
					</tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="button"
							style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;"
							onclick="validate()" value="提 交" class="search_btn">&nbsp;&nbsp;<input
							type="button"
							style="width: 60px; height: 25px; border-radius: 5px; border: 1px solid #90C1E6;"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>

	<div id="saveTargetFrom"
		style="display: none; width: 500px; background: #FFFFFF">
		<div height="300px" colspan="2"></div>
		<div style="height: 50px; line-height: 50px;padding-left: 10px;border: 1px solid #ccc;">
			角色编码: <span id="roleCode">{roleCode}</span>&nbsp;&nbsp;角色名称: <span
				id="roleName">{roleName}</span>
		</div>

		<form style="width: 500px; background: #FFFFFF"
			action="${pageContext.request.contextPath}/admin/roleFixedList/create"
			method="post">
			
			<div id="tagContent2" class="tagContent tagContent_div"
				style="display: block; background: #F2F2F2 ;width: 96.5%;border: 1px solid #dee1e2;">
				<div>
					<ul id="targetTree" class="ztree"></ul>
				</div>

			</div>
			<div style="height: 50px; colspan: 2"></div>
			<div class="text_left" style="height: 50px; colspan: 2">
				&nbsp;&nbsp;
				<input type="button"
					style="margin-right: 150px;float: right; width: 80px; height: 35px; border: 1px solid #90C1E6;"
					onclick="" value="取 消" class="search_btn close">
					<input type="button" id="addTarget"
					style="width: 80px; float: right; height: 35px; border: 1px solid #90C1E6;"
					value="提 交" class="search_btn" />
			</div>
			</form>
	</div>

</body>
</html>