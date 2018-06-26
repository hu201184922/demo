<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
<script type="text/javascript">
 
</script>
<div class="row" style="margin-bottom:10px;">
	<div class="col-md-2"></div>
	<div class="btn-group col-md-10">
		<button class="btn btn-default" onclick="buildCategories()">从菜单导入</button>
	</div>
</div>
<div class="row">
	<div class="col-md-2" style="position:opposite;z-index:999;height:410px;background-color:white;border:0px solid;overflow-y:auto;overflow-x:auto;">
		<ul id="resCatetreemaintain" class="ztree"></ul>
	</div>

	<div class="col-md-10">
		<form id="inputForm" action="${ctx }/admin/sec/rescategory/update" method="post" class="form-horizontal">
			<div>
				 <input id="resouceCategoryId" type="hidden" value="" name="id"/>
			</div>
			<div class="panel panel-info">
				<div class="panel-heading">资源分类信息</div>
				<div class="panel-body">
						<div class="form-group">
							<label for="name" class="col-md-2 control-label">名称:</label>
							<div  class="col-md-4">
								<input type="text" id="name" name="name" class="form-control input-sm required"/>
							</div>
							<label for="code" class="col-md-2 control-label">编码:</label>
							<div class="col-md-4">
								<input type="text" id="code" name="code" class="form-control input-sm required"/>
							</div>
							<input type="hidden" id="hidden_code" name="hidden_code" value="">
						</div>
						<div class="form-group">
							<label for="sortIndex" class="col-md-2 control-label">排序号:</label>
							<div class="col-md-4">
								<input type="text" id="sortIndex" name="sortIndex" class="form-control input-sm"/>
							</div>
							<label for="descript" class="col-md-2 control-label">描述:</label>
							<div class="col-md-4">
								<textarea id="descript" name="descript" class="form-control input-sm"></textarea>
							</div>
						</div>
				</div>
				<div class="panel-footer">
					<div class="col-md-offset-2">
						<input type="submit" class="btn btn-default " value="保存"/>
					</div>
				</div>
			</div>
		</form>
	    <div class="btn-group">
			<button onclick="addResource()" class=" btn btn-default" >新增资源</button>
			<button id="remove" class="btn btn-default">删除资源</button>
    	</div>
    	<div  id="resourceListBody" class="panel panel-info">
    		<div class="panel-heading">资源列表</div>
    		<table id="contentTable" class="table table-bordered table-condensed">
				<thead>
					<tr>
						<th><input type="checkbox" id="selectAll" /></th>
						<th>名称</th>
						<th>类型</th>
						<th>唯一字符串</th>
						<th>permission字符串</th>
						<th>是否可用</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody >
					
				</tbody>
			</table>
    	</div>
	</div>

	


</div>
<SCRIPT type="text/javascript">
	var setting = {
		/* check: {
				enable: true,
				chkStyle: "radio",
				radioType: "level"
			}, */
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit : {
			drag : {
				autoExpandTrigger : true,
				prev : dropPrev,
				inner : dropInner,
				next : dropNext
			},
			enable : true,
			showAddBtn : true,
			showRemoveBtn : true,
			showRenameBtn : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeClick  : beforeClick ,
			beforeDrag : beforeDrag,
			beforeDrop : beforeDrop,
			beforeDragOpen : beforeDragOpen,
			onDrag : onDrag,
			onDrop : onDrop,
			onExpand : onExpand,
			beforeRemove: beforeRemove
		}
	};
	
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			$.ajax({
				   type: "POST",
				   url: "${ctx}/admin/sec/rescategory/create",
				   data: "parent.id="+treeNode.id,
				   success: function(msg){
					   var zTree = $.fn.zTree.getZTreeObj("resCatetreemaintain");
						zTree.addNodes(treeNode, {id:msg.id, pId:msg.parent.id, name:msg.name});
				   }
				});
			
			return false;
		});
	};
	
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};

	function dropPrev(treeId, nodes, targetNode) {
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for ( var i = 0, l = curDragNodes.length; i < l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode()
						&& curPNode.childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	function dropInner(treeId, nodes, targetNode) {
		if (targetNode && targetNode.dropInner === false) {
			return false;
		} else {
			for ( var i = 0, l = curDragNodes.length; i < l; i++) {
				if (!targetNode && curDragNodes[i].dropRoot === false) {
					return false;
				} else if (curDragNodes[i].parentTId
						&& curDragNodes[i].getParentNode() !== targetNode
						&& curDragNodes[i].getParentNode().childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	function dropNext(treeId, nodes, targetNode) {
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for ( var i = 0, l = curDragNodes.length; i < l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode()
						&& curPNode.childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}

	var log, className = "dark", curDragNodes, autoExpandNode;
	function beforeDrag(treeId, treeNodes) {
		className = (className === "dark" ? "" : "dark");
		showLog("[ " + getTime()
				+ " beforeDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: "
				+ treeNodes.length + " nodes.");
		for ( var i = 0, l = treeNodes.length; i < l; i++) {
			if (treeNodes[i].drag === false) {
				curDragNodes = null;
				return false;
			} else if (treeNodes[i].parentTId
					&& treeNodes[i].getParentNode().childDrag === false) {
				curDragNodes = null;
				return false;
			}
		}
		curDragNodes = treeNodes;
		return true;
	}
	function beforeDragOpen(treeId, treeNode) {
		autoExpandNode = treeNode;
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
		className = (className === "dark" ? "" : "dark");
		showLog("[ " + getTime()
				+ " beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
		showLog("target: " + (targetNode ? targetNode.name : "root")
				+ "  -- is "
				+ (isCopy == null ? "cancel" : isCopy ? "copy" : "move"));
		return true;
	}
	function onDrag(event, treeId, treeNodes) {
		className = (className === "dark" ? "" : "dark");
		showLog("[ " + getTime() + " onDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: "
				+ treeNodes.length + " nodes.");
	}
	function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
		className = (className === "dark" ? "" : "dark");
		showLog("[ " + getTime()
				+ " onDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
		showLog("target: " + (targetNode ? targetNode.name : "root")
				+ "  -- is "
				+ (isCopy == null ? "cancel" : isCopy ? "copy" : "move"))
	}
	function onExpand(event, treeId, treeNode) {
		if (treeNode === autoExpandNode) {
			className = (className === "dark" ? "" : "dark");
			showLog("[ " + getTime() + " onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;"
					+ treeNode.name);
		}
	}

	function showLog(str) {
		if (!log)
			log = $("#log");
		log.append("<li class='"+className+"'>" + str + "</li>");
		if (log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now = new Date(), h = now.getHours(), m = now.getMinutes(), s = now
				.getSeconds(), ms = now.getMilliseconds();
		return (h + ":" + m + ":" + s + " " + ms);
	}

	function setTrigger() {
		var zTree = $.fn.zTree.getZTreeObj("resCatetreemaintain");
		zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr(
				"checked");
	}

	function beforeClick(treeId, treeNode, clickFlag) {
		copyResourceCategory(treeNode);
		loadResourcesByCategoryId(treeNode.id);
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		treeObj.selectNode(treeNode, false);
		return false;
	}
	
	function copyResourceCategory(treeNode){
		$("#resouceCategoryId").val(treeNode.id);
		$("#code").val(treeNode.code);
		$("#hidden_code").val(treeNode.code);
		$("#name").val(treeNode.name);
		$("#sortIndex").val(treeNode.sortIndex);
		$("#res_enabled").val(treeNode.res_enabled);
		$("#descript").val(treeNode.descript);
	}
	
	function loadResourcesByCategoryId(categoryId){
		$("#resourceListBody").load("${ctx}/admin/sec/rescategory/loadResources/"+categoryId,function(){
			$(".editResource").fancybox({
				fitToView	: false,
				width		: '80%',
				height		: '80%',
				autoSize	: false,
				closeClick	: false,
				openEffect	: 'none',
				beforeClose : function(){
					loadResourcesByCategoryId(categoryId);
				}
			
			});
			
			$(".resource2Role").fancybox({
				fitToView	: false,
				width		: '50%',
				height		: '65%',
				autoSize	: false,
				closeClick	: false,
				openEffect	: 'none'
			});
		});
	}
	
	function beforeRemove(treeId, treeNode){
		
		if(confirm("确定要删除该节点吗")){
			$.ajax({
				   type: "POST",
				   url: "${ctx}/admin/sec/rescategory/delete/"+treeNode.id,
				   success: function(msg){
					   alert("删除成功!");
					   window.location.href="${ctx}/admin/sec/rescategory";
				   }
			});
		}
		
		return false;
	}
	
	$(document).ready(function() {
		
		$.getJSON("${ctx}/admin/sec/rescategory/get-tree?"+new Date(), function(data) {
			var zNodes = data;
			$.fn.zTree.init($("#resCatetreemaintain"), setting, zNodes);
			$("#callbackTrigger").bind("change", {}, setTrigger);
			var zTree = $.fn.zTree.getZTreeObj("resCatetreemaintain");
			var resCateGoryId='${categoryId}';
			if(resCateGoryId != ""){
				var node=zTree.getNodeByParam("id",resCateGoryId,null);
				beforeClick("resCatetreemaintain",node,1);
				zTree.expandNode(node, true, true, true);
			}else{
				var node = zTree.getNodeByParam("pid", null, null);
				if(node){
					beforeClick("resCatetreemaintain",node,1);
					zTree.expandNode(node, true, true, true);
				}
			}
			
		});
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules : {
				 code:{
						required : true,
						remote : {
							url : "${ctx}/admin/sec/rescategory/checkCode",
							type : "post",
							dataType : "json",
							data : {
									code : function() {
										return $("#code").val();
									},
									initalname :  function() {
										return $("#hidden_code").val();
									},
										
								}
							}
						},
				sortIndex : {
					digits : true
				}
			}
		});
		
	});
	
	//新增资源点击方法
	function addResource(){
		var resouceCategoryId = $("#resouceCategoryId").val();
		if(resouceCategoryId.length == 0){
			alert("请选择资源分类");
			return ;
		}
		$.fancybox.open({
			type		: "iframe",
			href		: "${ctx}/admin/sec/rescategory/createResource/"+resouceCategoryId,
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			beforeClose : function(){
				loadResourcesByCategoryId(resouceCategoryId);
			}
		});
	};
	function buildCategories() {
		if(confirm("您确定要根据菜单生成资源分类及资源吗")){
			location = "${ctx}/admin/sec/rescategory/buildCategories";
		}
	}
	function addResource(){
		var resouceCategoryId = $("#resouceCategoryId").val();
		if(resouceCategoryId.length == 0){
			alert("请选择资源分类");
			return ;
		}
		$.fancybox.open({
			type		: "iframe",
			href		: "${ctx}/admin/sec/rescategory/createResource/"+resouceCategoryId,
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			beforeClose : function(){
				loadResourcesByCategoryId(resouceCategoryId);
			}
		});
	};
	
	$("#selectAll").click( function () { 
		var value = this.checked;
		if(value==true){
			$(".checkboxTd :input").each(function(i){
				 this.checked=true;
				 
			});
			
		}else{
			$(".checkboxTd :input").each(function(i){
				 this.checked=false;
			});
		}	
	});
	
	$("#remove").click(function(){
		var arr="";
		$(".checkboxTd :input[type=checkbox]").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}	
		});
		if(arr.length==0){
			alert("您还没有选择要删除的对象");
		}else{
			var r=window.confirm("确认删除？");
			if(r==true){
				arr = arr.substring(0, arr.length - 1);
				$.get("${ctx}/admin/sec/rescategory/deleteResources",{ids:arr},function(data,status){
					var resouceCategoryId = $("#resouceCategoryId").val();
					loadResourcesByCategoryId(resouceCategoryId);
			});
			}	
		}
	});
	
</SCRIPT>