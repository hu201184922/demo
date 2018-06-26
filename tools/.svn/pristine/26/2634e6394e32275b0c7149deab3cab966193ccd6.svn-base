<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/static/css/test.css" type="text/css" rel="stylesheet" />
<title>资源管理</title>
<script type="text/javascript">
$(function(){
	$('#autoscan').click(function(){
		$.getJSON("${pageContext.request.contextPath}/admin/sec/resource/scanner",function(data){
			$.alert('操作完成');
		});
		return false;
	});
	
});
$(function(){
	var page = {};
	$('#pager').pager('${pageContext.request.contextPath}/admin/sec/resource/list',15,$('#resourceView').view().on('add',function(){
		(function(zhis){
			var deleteConfirm = new Ace.awt.ConfirmBox({
				widget : $('<div style="background-color:#FFFFFF;display:none;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
				trigger : zhis.target.find('.removed'),
				positionAdjust : [ 6, -45 ]
			});	
			deleteConfirm.on('confirm',function(w, t) {
				$.getJSON('${pageContext.request.contextPath}/admin/sec/resource/delete',{id:zhis.getData().id},function(bit){
					if(bit==true){
						$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
					}
					$('#pager').pager().reload(function(){
						zhis.target.remove();
					});
				});
			});
			zhis.target.find('.save').click(function(){
				$.getJSON('${pageContext.request.contextPath}/admin/sec/resource/update',zhis.getData(),function(data){
					$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
					var nodes = $('#resourceTree').zTree().getNodesByParam('id',data.id, null);
					nodes.each(function(){
						$('#resourceTree').zTree().updateNode(Ace.copy(this,data),false);
					});
					zhis.setData(data);
					zhis.setTemplate('default',true);
				});
			});
		})(this);
	})).setJSON(page);
	$('#resourceAjaxForm').ajaxForm(function(data){
		$('#pager').pager().setPostData(Ace.parseQuery($('#resourceAjaxForm').serialize()));
		$('#pager').pager().setJSON(data);
	});
	
	$('#resourceTree').zTree({url:'${pageContext.request.contextPath}/admin/sec/resource/tree',dataFilter:function(treeId, parentNode, childNodes){
			function filter(cn){
				cn?cn.each(function(){
					this.isParent = this.resType == 'G';
					if(!this.isParent){
						this.edit = false;
						this.add = false;
					}
					if(this.children){
						filter(this.children);
					}
				}):null;
			}
			filter(childNodes);
// 			childNodes?childNodes.each(function(){
// 				this.isParent = this.resType == 'G';
// 				if(!this.isParent){
// 					this.edit = false;
// 					this.add = false;
// 				}
// 			}):null;
			return childNodes;
		},
		edit:true,
		callback:{
			onAsyncSuccess:function(event, treeId, treeNode, msg){
				var newNodes = $('#saveResourceForm').data('newNodes');
				if(!!newNodes){
					newNodes.each(function(){
						var nodes = $('#resourceTree').zTree().getNodesByParam("id", this.id, treeNode);
						nodes.each(function(){
							$('#resourceTree').zTree().removeNode(this, false);
						});
					});
				}
				$('#saveResourceForm').data('newNodes',null);
			},
			add:function(treeId, treeNode){
				$('#saveResourceForm').data('node',treeNode);
				$.dialog({title:"资源组添加",content:"<h3>选择资源的添加方式!</h3>",
					button : [{
						value : '添加新的资源组',
						callback : function() {
							resourceGroupDialog(treeNode.id,'add');
						}
					},{
						value : '添加URL资源',
						callback : function() {
							$('#saveResourceForm').data('dialog',$.dialog({url:'${pageContext.request.contextPath}/admin/sec/resource/urlbox?id='+treeNode.id}));
						}
					}]
				});
			},
			beforeEditName : function(treeId, treeNode) {
				$.confirm('是否确定编辑资源组:' + treeNode.name + '?<br/>如果该资源组被修改,其引用位置都将被修改。', function() {
					$('#saveResourceForm').data('type', 'update');
					$('#saveResourceForm').data('node', treeNode);
					$('#saveResourceForm').template(treeNode);
					$('#saveResourceForm').find('[name=enabled]').val(treeNode.enabled);
					$('#saveResourceForm').find('[name=domainchk]').val(treeNode.domainchk);
					$('#saveResourceForm').data('dialog', $.dialog({
						title : '资源组编辑',
						content : $('#saveResourceForm')[0]
					}));
				});
				return false;
			},
			beforeRemove : function(treeId, treeNode) {
				var title = '是否确定删除资源'+(treeNode.type == 'url'?'':'组')+':' + treeNode.name + '?';
				$.dialog({title : title,content:"<h3>选择删除资源的方式!</h3>",
					button:[{
						value : "删除所有子节点",
						callback : function(){
							$.getJSON('${pageContext.request.contextPath}/admin/sec/resource/delete', {
								id : treeNode.id
							}, function(data) {
								$('#resourceTree').zTree().removeNode(treeNode, false);
								$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
							});
						}
					},
					{
						value : "取消",
						callback : function(){
							
						}
					}]
				
				});
				return false;
			}
		}
	});

	$('#addRootResource').click(function() {
		$('#saveResourceForm').data('node',null);
		resourceGroupDialog(null,'add');
	});

	var actions = {'add':'资源组添加'};
	var resourceGroupDialog = function(pid,type){
		$('#saveResourceForm').template({
			pid : pid
		});
		$('#saveResourceForm').data('type',type);
		$('#saveResourceForm').data('dialog', $.dialog( {
			title : actions[type],
			content : $('#saveResourceForm')[0]
		}));
	};
		
	$('#saveResourceForm').ajaxForm(function(data) {
		var treeNode = $('#saveResourceForm').data('node'); //menuTree.getSelectedNodes();
		$('#saveResourceForm').data('dialog').close();
		switch($('#saveResourceForm').data('type')){
			case "add":
				$.msgbox({time: 2000,msg: "资源组添加成功!",icon:"success"});
				data.isParent = true;
				data.id = data.id;
				if(!!treeNode)
					data.pid = treeNode.id;
				$('#saveResourceForm').data('newNodes',[data]);
				$('#resourceTree').zTree().addNodes(treeNode, data);
				break;
			case "update":
				$.msgbox({time: 2000,msg: "资源组编辑成功!",icon:"success"});
				var nodes = $('#resourceTree').zTree().getNodesByParam('id',data.id, null);
				nodes.each(function(){
					$('#resourceTree').zTree().updateNode(Ace.copy(this,data),false);
				});
				break;
		}
	});

});

	function sub(){
		var name = $('input[name="name"]').val()
		if ($.trim(name) == '') {
			$.dialog({time: 2000,content:  "请输入资源组名称！"});
			return;
		}
		$('#saveResourceForm').submit();
	}

</script>
</head>
<body>

	 <div class="business_title">资源管理</div>
<div style="width:100%;" id="con2">
	<ul style="width:100%;" id="tags2" tabs="{selectedClass:'selectTag'}">
	  <li class="selectTag" tab="#tagContent0"><a href="javascript:void(0)">资源组</a> </li>
	  <li tab="#tagContent1"><a href="javascript:void(0)">URL资源</a> </li>
	</ul>
	<div style="height:auto;width:100%; padding-bottom:30px;" id="tagContent">
		<div id="tagContent0" class="tagContent tagContent_div selectTag" style="display: block;">
			<input id="addRootResource" type="button" value="添加资源组"/>
			<ul id="resourceTree" class="ztree"></ul>
	    </div>
	    
		<div id="tagContent1" class="tagContent tagContent_div" style="display: none;">
		<input type="button" id="autoscan" value="扫描所有URL">
		<div class="business_search">
		<div class="business_search_left">
		<form id="resourceAjaxForm" action="${pageContext.request.contextPath}/admin/sec/resource/list" method="post">
		<input type="hidden" name="pageSize" value="15"/>
		<table>
			<tbody>
			  <tr>
			      <td style="text-align:right">资源名称：</td>
			      <td><input type="text" name="search_LIKE_name" class="text"></td>
				  <td style="text-align:left;" colspan="2">是否启用：
				  	<select name="enabled" style="width:90px">
				  		<option value="true">启用</option>
						<option value="false">禁用</option>
				  	</select>
				  </td>
				  <td colspan="2"><input type="checkbox" name="unreferenced" value="true"/>只显示未分配的资源</td>
			  </tr>
			  <tr>
			      <td style="text-align:right">链接地址：</td>
			      <td colspan="3"><input style="width:300px;" type="text" name="search_LIKE_resString" class="text"></td>
				  <td><input type="submit" value="查&#12288;询" style="font-size: 14px;"></td>
			  </tr>
			</tbody>
		  </table>
		  </form>
		  </div>
		  </div>
          <table cellspacing="0" cellpadding="0" class="t-list table" id="resourceView">
		   <tbody>
		   <tr>
			  <th width="40px">序号</th>
			  <th>资源名称</th>
			  <th>类型</th>
			  <th>链接地址</th>
			  <th>授权字符串</th>
			  <th width="80px">是否启用</th>
			  <th>资源描述</th>
			  <th style="border-right:1px solid #ccc; width:80px">操作</th>
		   </tr>
			<tr class="tb_tr_content template" name="default">
				<td>{index:seq()}</td>
				<td>{name}</td>
				<td>{resType:dict({'U':'URL'})}</td>
				<td>{resString}</td>
				<td>{perString}</td>
				<td>{enabled:dict({'true':'启用','false':'禁用'})}</td>
				<td>{descript}</td>
				<td style="border-right:1px solid #ccc">
					<span class="span_edit">
						<a href="javascript:void(0)" class="switch" template="update">
							<img style="float:left;" src="${pageContext.request.contextPath}/static/images/xiugai.png" title="修改"/>
						</a>
						<a href="javascript:void(0)" class="removed">
							<img style="float:left;" src="${pageContext.request.contextPath}/static/images/del_icon.png" title="删除">
						</a>
					</span> 
				</td>
			</tr>
			<tr class="tb_tr_content template" name="update">
				<td>{index:seq()}</td>
				<td>
				<input type="hidden" name="type" value="url"/>
				<input style="width: 120px;" class="text_wd180 view-field" name="name" type="text" mapping="name"/></td>
				<td>{resType:dict({'U':'URL'})}</td>
				<td>
					<input style="width: 120px;" class="text_wd180 view-field" name="resString" type="text" mapping="resString"/>
				</td>
				<td style="width: 120px;">{perString}</td>
				<td>
					<input class="view-field" name="enabled" type="radio" value="true" style="border:0px"/>启用/<input style="border:0px" name="enabled" type="radio" value="false"/>禁用
				</td>
				<td><input class="text_wd180 view-field" name="descript" type="text" mapping="descript"/></td>
				<td style="border-right:1px solid #ccc">
					<span class="span_edit">
				      <a href="javascript:void(0)" class="save"><img style="float:left;" title="保存" src="${pageContext.request.contextPath}/static/images/save.png"></a>&nbsp;
				      <a href="javascript:void(0)" class="switch back" template="default"><img style="float:left;" title="返回" src="${pageContext.request.contextPath}/static/images/fanhui.png"></a>
				   </span>
				</td>
			</tr>
		</tbody>
		</table>
		<div id="pager"></div>
		
	    </div>
	</div>
</div>
<form id="saveResourceForm" style="display:none;" action="${pageContext.request.contextPath}/admin/sec/resource/create" method="post" >
	<input type="hidden" name="id" value="{id}" />
	<input type="hidden" name="resType" value="G"/>
	<input type="hidden" name="pid" value="{pid}"/>
	<div id="tagContent2" class="tagContent tagContent_div" style="display:block;">
            <table style="line-height:30px">
			    <tbody><tr>
				    <td class="text_right">资源组名称：</td>
					<td class="text_left"><input type="text" name="name" value="{name}" class="text_wd200"/></td>
				</tr>
				<tr>
				    <td class="text_right">资源组状态：</td>
					<td class="text_left">
					<input name="enabled" checked="checked" type="radio" value="true"/>启用/<input name="enabled" type="radio" value="false"/>禁用
					</td>
				</tr>
				<tr>
				    <td class="text_right">资源组描述：</td>
					<td class="text_left">
						<textarea name="description" style="width:200px;height:50px;">{description}</textarea>
					</td>
				</tr>
				<tr>
				    <td height="10px" colspan="2"></td>
				</tr>
				<tr>
				    <td></td>
					<td class="text_left">
					<input type="button" value="提 交" onclick="sub()">&nbsp;&nbsp;<input type="button" class="close" value="取 消" >
					</td>
				</tr>
			</tbody>
		</table>
    </div>
</form>
</body>
</html>