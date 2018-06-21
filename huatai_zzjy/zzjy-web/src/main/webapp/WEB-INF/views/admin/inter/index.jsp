<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>接口表名管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/inter/list',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					
					$.post('${pageContext.request.contextPath}/admin/inter/delete',{id:data.interId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.save').click(function(){
					$.getJSON('${pageContext.request.contextPath}/admin/inter/update',zhis.getData(),function(data){
						$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
						zhis.setData(data);
						zhis.setTemplate('default',true);
						$('#pager').pager().reload();
					});				
				});	
				
				zhis.target.find('.openTarget').click(function(){
					var str=$(this).attr('name').split(',');
					$('#interId').text(str[0]);
					$('#interName').text(str[1]);
					openTarget(str[0],str[1]);
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
			$('#tagContent2 input[name="interName"]').val('');
			$("#tagContent2 textarea[name='remark']").val('');
		});
		$('#saveParameterForm').ajaxForm(function(data) {
			$('#saveParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		$('#pager').pager().reload();		
});	
	
	var selectedList=[];
	function openTarget(interId,roleNode){
		if($('#targetTree').data('zTree')){
					$('#targetTree').data('zTree','');
		}
		var targetTree;
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
					showIcon:true,
					showLine: true
				},check:{enable:true},
				callback:{
					onCheck:function(event,treeId,treeNode){
						var nodes = targetTree.getCheckedNodes(true);					
						var nodes1 =  targetTree.getCheckedNodes(false);		
						selectedList=[];
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
		var selected;
	 	$.ajax({
			url:'${pageContext.request.contextPath}/admin/targetInter/tree',
			data:{interId:interId},
			success:function(data){		
				selected=data
				for(var i=0;i<data.length;i++){
					selected[i].id = data[i].targetCode;//添加节点id属性
					selected[i].pId = data[i].parentCode;//添加节点pid属性
					selected[i].name = data[i].targetName;//添加name属性
					selected[i].isParent = false;//把所有节点默认为父节点	
					selected[i].checked = data[i].checked;//把所有节点默认为父节点					
					size=data.length;
				}
				$('#targetTree').html('');
				targetTree=$('#targetTree').zTree(setting,selected);
				$('#saveInterFrom').data("dialog", $.dialog({
					title : "关联指标",
					content : $('#saveInterFrom')[0]
				}));
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
				$.msgbox({time: 2000,msg: "已存在!",icon:"success"});
				 setTimeout(function(){location.reload()},1000);
			}
			
		})
		$("#addTarget").off('click')
	 	$("#addTarget").click(function(){
	 		$.getJSON('${pageContext.request.contextPath}/admin/targetInter/create',{targetCodes:selectedList,interId:interId,size:size},function(result){
				if(result==true){
					$.msgbox({time: 2000,msg: "关联成功!",icon:"success"});
					$('#saveInterFrom').data("dialog").close();
				}
		 	})
	 	});
	}
	
	function validate(){
		var name = $('#tagContent2 input[name="interName"]').val();
		var remark = $("#tagContent2 textarea[name='remark']").val();
		if($.trim(name)==''||$.trim(name)==null ){
			$.dialog({content: '请输入表名', time: 2000});return;
		}
		$('#saveParameterForm').submit();
	}
</script>
</head>
<body>
	<div class="business_title">接口表管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="createParameter" value="新建" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;">
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="parameterAjaxForm"
						action="${pageContext.request.contextPath}/admin/inter/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">表名：</td>
									<td><input style="width: 300px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="interName"  class="text">&nbsp;&nbsp;</td>
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
						
						<th>表名</th>
						<th>表描述</th>
						<th>操作状态</th>
						<th>创建时间</th>
						<th>修改时间</th>
						<th>创建人</th>
						<th>修改人</th>
						<th style="border-right: 1px solid #ccc; width: 120px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{interName}</td>
						<td>{remark}</td>
						<td>{opType:dict({A:'新增',D:'删除',U:'更新',R:'查看'})}</td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
						<td>{creatorName}</td>
						<td>{modifierName}</td>					
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit">
							<a class="openTarget"
								 name="{interId},{interName}"> <img
									style="float: left;width:17px;margin-left: 5px;"
									src="${pageContext.request.contextPath}/static/images/huatai_target.png"
									title="关联指标">
							</a>
							<a href="${pageContext.request.contextPath}/admin/interField/index?interId={interId}"><img
									style="float: left;width:17px;margin-left: 5px;"
									src="${pageContext.request.contextPath}/static/images/huatai_field.png"
									title="维护字段"></a>
							<a href="javascript:void(0)" class="switch" template="update"> 
								<img style="float: left;width:17px;margin-left: 5px;" src="${pageContext.request.contextPath}/static/images/huatai_edit.png"
									title="修改" /></a> 
							<a href="javascript:void(0)" class="removed"> <img
									style="float: left;margin-left: 5px;width:17px;"
									src="${pageContext.request.contextPath}/static/images/huatai_delete.png"
									title="删除"></a>
						</span></td>
					</tr>
					 <tr class="tb_tr_content template" name="update">
						<td >
						<input class="view-field" name="interName" type="text"/>
						</td>
						<td>
							<input class="view-field" name="remark" type="text" />
						</td>
						<td>{opType:dict({A:'新增',D:'删除',U:'更新',R:'查看'})}</td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
						<td>{creatorName}</td>
						<td>{modifierName}</td>
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit">
								 <a href="javascript:void(0)"
								class="save"><img style="float: left;width:17px;margin-left: 20px;" title="保存"
									src="${pageContext.request.contextPath}/static/images/huatai_save.png"></a>&nbsp;
								<a href="javascript:void(0)" class="switch back"
								template="default"><img style="float: left;width:17px;margin-left:10px;" title="返回"
									src="${pageContext.request.contextPath}/static/images/huatai_back.png"></a>
						</span></td>
					</tr>

				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	<form id="saveParameterForm" style="display: none;"
		action="${pageContext.request.contextPath}/admin/inter/create"
		method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					
					<tr>
						<td class="text_right">表名：</td>
						<td class="text_left"><input name="interName" type="text"
							style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"></input></td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">表描述：</td>
						<td class="text_left"><textarea name="remark" cols="30" rows="5"
							type="text"  style="border-radius:5px;border:1px solid #90C1E6;"></textarea></td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
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
	
	<div id="saveInterFrom" style="display: none;width:500px;background:#FFFFFF">
		<div height="300px" colspan="2"></div>
		<div style="height: 50px; line-height: 50px;padding-left: 10px;border: 1px solid #ccc;">
			表名: <span id="interName">{interName}</span>
		</div>
	
		<form  style="width:500px;background:#FFFFFF"
		action="${pageContext.request.contextPath}/admin/targetInter/create"
		method="post">
		<div id="tagContent3" class="tagContent tagContent_div" style="display: block; background: #F2F2F2;width: 97%;border: 1px solid #dee1e2;">
			<div><ul id="targetTree" class="ztree"></ul></div>
			
			</div>				
			<div style="height:50px;colspan:2"></div>
			<div class="text_left" style="height:50px;colspan:2">
			<input type="button" style="margin-right: 150px;width: 80px;float: right;height: 35px;border:1px solid #90C1E6;"  value="取 消" class="search_btn close">&nbsp;&nbsp;
				<input type="button" style="float: right;width: 80px;height: 35px;border:1px solid #90C1E6;" id="addTarget"  value="提 交" class="search_btn"/>
				
			</div>
		</div>
	</form>
	</div>
</body>
</html>