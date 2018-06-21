<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>数据字典</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		$(function(){
			var dictId = "";
		$('#dictView').view().on('add',function(){
				(function(zhis){
					var deleteConfirm = new Ace.awt.ConfirmBox({
						widget : $('<div style="background-color:#FFFFFF;display:none;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
						trigger : zhis.target.find('.removed'),
						positionAdjust : [ 6, -45 ]
					});	
					deleteConfirm.on('confirm',function(w, t) {
						$.getJSON('${pageContext.request.contextPath}/admin/dict/deleteItem',{id:zhis.getData().id},function(bit){
							if(bit==true){
								$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
							}
							zhis.target.remove();
						});
					});
					zhis.target.find('.save').click(function(){
						$("#dictId").val(dictId);
						$.getJSON('${pageContext.request.contextPath}/admin/dict/updateItem',zhis.getData(),function(data){
							$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
							zhis.setData(data);
							zhis.setTemplate('default',true);
						});
					});
				})(this);
			});
			$('#dictTree').zTree({url:'${pageContext.request.contextPath}/admin/dict/tree',dataFilter:function(treeId, parentNode, childNodes){
				childNodes?childNodes.each(function(){

				}):null;
				return childNodes;
			},
			edit:true,
			callback:{
				onAsyncSuccess:function(event, treeId, treeNode, msg){
					var newNodes = $('#saveDictForm').data('newNodes');
					if(!!newNodes){
						newNodes.each(function(){
							var nodes = $('#dictTree').zTree().getNodesByParam("id", this.id, treeNode);
							nodes.each(function(){
								$('#dictTree').zTree().removeNode(this, false);
							});
						});
					}
					$('#saveDictForm').data('newNodes',null);
				},
				add:function(treeId, treeNode){
					$('#saveDictForm').data('node',treeNode);
					$.dialog({title:"数据字典添加",content:"<h3>选择数据字典的添加方式!</h3>",
						button : [{
							value : '添加数据字典',
							callback : function() {
								dictDialog(treeNode.id,'add');
							}
						},{
							value: '添加字典项',
							callback: function(){
								dictItemDialog(treeNode.id);
							}
							
						}]
					});
				},
				beforeEditName : function(treeId, treeNode) {
					$.confirm('是否确定编辑数据字典:' + treeNode.name + '?', function() {
						$('#saveDictForm').data('type', 'update');
						$('#saveDictForm').data('node', treeNode);
						$('#saveDictForm').template(treeNode);
						$('#saveDictForm').find('[name=enabled]').val(treeNode.enabled);
						$('#saveDictForm').find('[name=domainchk]').val(treeNode.domainchk);
						$('#saveDictForm').data('dialog', $.dialog({
							title : '数据字典编辑',
							content : $('#saveDictForm')[0]
						}));
					});
					return false;
				},
				beforeRemove : function(treeId, treeNode) {
					var title = '是否确定删除数据字典'+':' + treeNode.name + '?';
					$.dialog({title : title,content:"<h3>选择删除数据字典的方式!</h3>",
						button:[{
							value : "删除所有子节点",
							callback : function(){
								$.getJSON('${pageContext.request.contextPath}/admin/dict/del', {
									id : treeNode.id
								}, function(data) {
									$('#dictTree').zTree().removeNode(treeNode, false);
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
				},
				onClick : function(e, treeId, treeNode) {
					dictId = treeNode.id;
					$.getJSON(request.getContextPath()+'/admin/dict/getDictItem', {
						id : treeNode.id
					}, function(data) {
						data.each(function(){
							this.dictId=treeNode.id;
						});
						$("#dtitle").template(treeNode);
						$("#dtitle").show();
						$('#dictView').view().setJSON(data);
					});
				}

			}
		});
		$("#createDict").click(function(){
			dictDialog(null,"add");
		});
		});
		var actions = {'add':'数据字典添加'};
		var dictDialog = function(pid,type){
			$('#saveDictForm').template({
				pid : pid
			});
			$('#saveDictForm').data('type',type);
			$('#saveDictForm').data('dialog', $.dialog( {
				title : actions[type],
				content : $('#saveDictForm')[0]
			}));
		};
		$('#saveDictForm').ajaxForm(function(data) {
			var treeNode = $('#saveDictForm').data('node'); //menuTree.getSelectedNodes();
			$('#saveDictForm').data('dialog').close();
			switch($('#saveDictForm').data('type')){
				case "add":
					$.msgbox({time: 2000,msg: "数据字典添加成功!",icon:"success"});
					data.isParent = false;
					data.id = data.id;
					if(!!treeNode)
						data.pid = treeNode.id;
					$('#saveDictForm').data('newNodes',[data]);
					$('#dictTree').zTree().addNodes(treeNode, data);
					break;
				case "update":
					$.msgbox({time: 2000,msg: "数据字典编辑成功!",icon:"success"});
					var nodes = $('#dictTree').zTree().getNodesByParam('id',data.id, null);
					nodes.each(function(){
						$('#dictTree').zTree().updateNode(Ace.copy(this,data),false);
					});
					break;
			}
		});
		var dictItemDialog = function(dictId){
			$('#saveDictItemForm').template({
				dictId : dictId
			});
			$('#saveDictItemForm').data('dialog', $.dialog( {
				title : "数据字典项添加",
				content : $('#saveDictItemForm')[0]
			}));
		};
		$("#saveDictItemForm").ajaxForm(function(data){
					$('#saveDictItemForm').data('dialog').close();
					$.msgbox({time: 2000,msg: "数据字典添加成功!",icon:"success"});
					$('#dictView').view().add(data);

		});
		
		function sub(){
			var name = $('#dictName').val();
			var code = $('#dictCode').val();
			if ($.trim(name) == '') {
				$.dialog({time: 2000,content:  "请输入字典名称！"});
				return;
			}
			if ($.trim(code) == '') {
				$.dialog({time: 2000,content:  "请输入编码！"});
				return;
			}
			$('#saveDictForm').submit();
		}
		
		function sub1(){
			var name = $('#dictItemName').val();
			var code = $('#dictItemCode').val();
			var sortIndex = $('#sortIndex').val();
			var pid = $('#pid').val();
			if ($.trim(name) == '') {
				$.dialog({time: 2000,content:  "请输入字典名称！"});
				return;
			}
			if ($.trim(code) == '') {
				$.dialog({time: 2000,content:  "请输入编码！"});
				return;
			}
			if ($.trim(sortIndex) == '') {
				$.dialog({time: 2000,content:  "请输入排序！"});
				return;
			}
			if ($.trim(pid) == '') {
				$.dialog({time: 2000,content:  "请输入父级编号！"});
				return;
			}
			$('#saveDictItemForm').submit();
		}
	</script>
</head>
<body>
<div class="business_title">数据字典</div>
<div  style="float:right;width:928px">
<div class="col_lg_04" style="width:923px">
<div class="business_search_list_warp" style="width:95%">
	   <h1 id="dtitle" style="font-size: 150%;display:none">{name}/{code}</h1>
       <table cellspacing="0" cellpadding="0" class="t-list table" id="dictView">
	   <tbody>
	   <tr>
		  <th width="40px">序号</th>
		  <th>编号</th>
		  <th>名称</th>
		  <th>编码</th>
		  <th>备注</th>
		  <th>父级编号</th>
		  <th>排序</th>
		  <th style="border-right:1px solid #ccc; width:100px">操作</th>
	   </tr>
		<tr class="tb_tr_content template" name="default">
			<td>{index:seq()}</td>
			<td>{id}</td>
			<td>{name}</td>
			<td>{code}</td>
			<td>{descript}</td>
			<td>{pid}</td>
			<td>{sortIndex}</td>
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
			<td style="width: 30px;">{id}</td>
			<td>
			<input type="hidden" class="view-field" name="dictId" id="dictId" mapping="dictId"/>
			<input style="width: 120px;" class="view-field" name="name" type="text" mapping="name"/></td>
			<td><input  class="view-field" name="code" type="text" mapping="code"/></td>
			<td><input class="view-field" name="descript" type="text" mapping="descript"/></td>
			<td><input  class="view-field" name="pid" type="text" mapping="pid"/></td>
			<td><input  class="view-field" name="sortIndex" type="text" mapping="sortIndex"/></td>
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
   <div class="clear"></div>
   </div>
</div>
<div style="float:left;width:257px;overflow: auto;height: 300px">
<input type="button" id="createDict" value="新建字典">
	<ul id="dictTree" class="ztree"></ul>
</div>  
<form id="saveDictForm" style="display:none;" action="${pageContext.request.contextPath}/admin/dict/create" method="post" >
	<input type="hidden" name="id" value="{id}" />
	<input type="hidden" name="pid" value="{pid}"/>
	<div id="tagContent2" class="tagContent tagContent_div" style="display:block;">
            <table style="line-height:30px">
			    <tbody><tr>
				    <td class="text_right">字典名称：</td>
					<td class="text_left"><input type="text" name="name" id="dictName" value="{name}" class="text_wd200"/></td>
				</tr>
				<tr>
				    <td class="text_right">编码：</td>
					<td class="text_left">
					<input type="text" name="code" value="{code}" id="dictCode" class="text_wd200"/>
					</td>
				</tr>
				<tr>
				    <td class="text_right">描述：</td>
					<td class="text_left">
						<textarea name="descript" style="width:200px;height:50px;">{descript}</textarea>
					</td>
				</tr>

				<tr>
				    <td height="10px" colspan="2"></td>
				</tr>
				<tr>
				    <td></td>
					<td class="text_left">
					<input type="button" value="提 交" class="search_btn" onclick="sub()">&nbsp;&nbsp;<input type="button" value="取 消" class="search_btn close">
					</td>
				</tr>
			</tbody>
		</table>
    </div>
</form>
<form id="saveDictItemForm" style="display:none;" action="${pageContext.request.contextPath}/admin/dict/createItem" method="post" >
	<input type="hidden" name="dictId" value="{dictId}"/>
	<div id="tagContent2" class="tagContent tagContent_div" style="display:block;">
            <table style="line-height:30px">
			    <tbody><tr>
				    <td class="text_right">字典名称：</td>
					<td class="text_left"><input type="text" name="name" id="dictItemName" value="{name}" class="text_wd200"/></td>
				</tr>
				<tr>
				    <td class="text_right">编码：</td>
					<td class="text_left">
					<input type="text" name="code" value="{code}" id="dictItemCode" class="text_wd200"/>
					</td>
				</tr>
				<tr>
				    <td class="text_right">排序：</td>
					<td class="text_left">
					<input type="text" name="sortIndex" id="sortIndex" value="{sortIndex}" class="text_wd200"/>
					</td>
				</tr>
				<tr>
					<td class="text_right">父级编号：</td>
					<td class="text_left">
					<input type="text" name="pid" value="{pid}" id="pid" class="text_wd200"/>
					</td>
				</tr>
				<tr>
				    <td class="text_right">描述：</td>
					<td class="text_left">
						<textarea name="descript" style="width:200px;height:50px;">{descript}</textarea>
					</td>
				</tr>
				<tr>
				    <td height="10px" colspan="2"></td>
				</tr>
				<tr>
				    <td></td>
					<td class="text_left">
					<input type="button" value="提 交" class="search_btn" onclick="sub1()">&nbsp;&nbsp;<input type="button" value="取 消" class="search_btn close">
					</td>
				</tr>
			</tbody>
		</table>
    </div>
</form>
</body>
</html>