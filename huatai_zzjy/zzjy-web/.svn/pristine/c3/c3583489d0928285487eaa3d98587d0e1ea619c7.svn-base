<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<html>
<head>
<title>调度定义管理</title>

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

// 操作qrtzDefiniton为1， CronTrigger为2, SimpleTrigger为3
var tabNum = 2;

$(document).ready(function(){
	$.getJSON("${ctx}/admin/qrtz/def/get-tree?" + new Date(), function(data) {
		var zNodes = data;
		$.fn.zTree.init($("#defgrouptreemaintain"), setting, zNodes);
		$("#callbackTrigger").bind("change", {}, setTrigger);
		var zTree = $.fn.zTree.getZTreeObj("defgrouptreemaintain");
		var node = zTree.getNodeByParam("id", "${groupId}", null);
		if(node){
			zTree.expandNode(node, true, true, true);
			zTree.selectNode(node);
			copyNodePropertyToForm(node);
			if(tabNum == 1){
				loadQrtzDefinition("${groupId}");
			}else if(tabNum == 2){
				loadCronTrigger("${groupId}");
			}else if(tabNum == 3){
				loadSimpleTrigger("${groupId}");
			}
// 			loadQrtzDefinition("${groupId}");
		}
	});
	
});

	var setting = {
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			selectedMulti : false
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
			beforeClick : beforeClick,
			beforeDrag : beforeDrag,
			beforeDrop : beforeDrop,
			beforeDragOpen : beforeDragOpen,
			onDrag : onDrag,
			onDrop : onDrop,
			onExpand : onExpand,
			beforeRemove : beforeRemove
		}
	};

	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
			return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_" + treeNode.tId);
		if (btn)
			btn.bind("click", function() {
				$.post("${ctx}/admin/qrtz/def/getGroup/" + treeNode.id + "",
						function(data) {
							if (data == "") {
								create(treeNode);
								return true;
							} else {
// 								alert("该组中有调度定义，无法添加子组");
								$.dialog({content: '该组中有调度定义，无法添加子组', time: 2000});
								return false;
							}
						});
				return false;
			});
	};
	function create(treeNode) {
		$.ajax({
			type : "POST",
			url : "${ctx}/admin/qrtz/def/create-Group",
			data : "parent.id=" + treeNode.id,
			success : function(msg) {
				var zTree = $.fn.zTree.getZTreeObj("defgrouptreemaintain");
				zTree.addNodes(treeNode, {
					id : msg.id,
					pId : msg.pid,
					name : msg.name
				});
			}
		});
	}

	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.tId).unbind().remove();
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
		//保存数据
		var parentId;
		if (moveType == 'inner') {
			parentId = targetNode.id;
		} else {
			//与目标节点成为同级节点
			parentId = targetNode.pid;
		}
		if (treeNodes[0].pid != parentId) {
			$.ajax({
				type : "POST",
				url : "${ctx}/admin/qrtz/def/move-Group/" + treeNodes[0].id,
				data : {
					"parentId" : parentId
				},
				success : function(msg) {
					var zTree = $.fn.zTree.getZTreeObj(treeId);
					treeNodes[0].pid = parentId;
					zTree.moveNode(targetNode, treeNodes[0], moveType, false);
				}
			});
		}
		className = (className === "dark" ? "" : "dark");
		showLog("[ " + getTime()
				+ " beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
		showLog("target: " + (targetNode ? targetNode.name : "root")
				+ "  -- is "
				+ (isCopy == null ? "cancel" : isCopy ? "copy" : "move"));
		return false;
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
				+ (isCopy == null ? "cancel" : isCopy ? "copy" : "move"));
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
		var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
		zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr(
				"checked");
	}

	function beforeClick(treeId, treeNode, clickFlag) {
		copyNodePropertyToForm(treeNode);
		if(tabNum == 1){
			loadQrtzDefinition(treeNode.id);
		}else if(tabNum == 2){
			loadCronTrigger(treeNode.id);
		}else if(tabNum == 3){
			loadSimpleTrigger(treeNode.id);
		}
		var treeObj = $.fn.zTree.getZTreeObj("defgrouptreemaintain");
		treeObj.selectNode(treeNode, false);
		return false;
	}

	function copyNodePropertyToForm(treeNode) {
		$("#qrtzGroup_id").val(treeNode.id);
		$("#qrtzGroup_name").val(treeNode.name);
		$("#qrtzGroup_code").val(treeNode.code);
		$("#qrtzGroup_sortIndex").val(treeNode.sortIndex);
		$("#qrtzGroup_descript").val(treeNode.descript);
		
	}

	
	function beforeRemove(treeId, treeNode, data) {
		$.post("${ctx}/admin/qrtz/def/getChildGroup/"+treeNode.id+"",
			function(data) {
				if(data == ""){
// 					var r = window.confirm("确定要删除吗？");
// 					if (r) {
					$.confirm("是否确定删除？", 
						function() {
							$.post("${ctx}/admin/qrtz/def/getGroup/" + treeNode.id + "",
									function(data) {
										if (data == "") {
											$.ajax({
												type : "POST",
												url : "${ctx}/admin/qrtz/def/delete-Group/"+ treeNode.id,
												success : function(msg) {
															var zTree = $.fn.zTree.getZTreeObj("defgrouptreemaintain");
															zTree.removeNode(treeNode);
	// 														alert("删除成功!");
															$.dialog({content: '删除成功!', time: 2000});
															window.location.reload(true);
														}
												});
											return true;
										} else {
	// 											alert("该组中有调度定义，无法删除该调度组");
												$.dialog({content: '该组中有调度定义，无法删除该调度组!', time: 2000});
												return false;
										}
								});
			 		})
					return false;
				}else {
// 					alert("该组中有子节点，不能删除");
					$.dialog({content: '该组中有子节点，不能删除!', time: 2000});
					return false;
				}
			}
		);
		return false;
	}
	
	function loadQrtzDefinition(id) {
		$("#qrtzGroup_id").val(id);
		$("#definitionAll").load(
					"${ctx}/admin/qrtz/def/getDef/" + id);
	}
	
	function loadCronTrigger(id){
		//console.log("loadCronTrigger");
		jQuery.get("${ctx}/admin/qrtz/cron/getCronTrigger/" + id,function(data,status){
			//console.log(data);
			//console.log(status);
			$("#definitionAll").html(data);
			//console.log("loadCronTrigger end");
		});
		/* console.log($("#definitionAll").html()); */
		/* $.ajax({
			async: true,
			url : "${ctx}/admin/qrtz/cron/getCronTrigger/" + id,
			type:"get",
			dataType : "html",
			success : function(data) {
				console.log(data);
				$("#definitionAll").html(data);
			}
		}); */
		//alert(JSON.stringify(result));
		
		/* $("#definitionAll").load(
				"${ctx}/admin/qrtz/cron/getCronTrigger/" + id); */
		
		
	}
	
	function loadSimpleTrigger(id){
		$("#definitionAll").load(
				"${ctx}/admin/qrtz/simple/getSimpleTrigger/" + id);
	}
	
	$(function(){
		$("#tab1").click(
			function(){
				$(this).tab("show");
				$(this).addClass("active");
				tabNum = 1;
				var id = $("#qrtzGroup_id").val();
				if(id){
					loadQrtzDefinition(id);
				}else{
					loadQrtzDefinition(-1);
				}
			}
		);
		$("#tab2").click(
			function(){
				$(this).tab("show");
				$(this).addClass("active");
				tabNum = 2;
				var id = $("#qrtzGroup_id").val();
				if(id){
					loadCronTrigger(id);
				}else{
					loadCronTrigger(-1);
				}
			}
		);
		$("#tab3").click(
				function(){
					$(this).tab("show");
					$(this).addClass("active");
					tabNum = 3;
					var id = $("#qrtzGroup_id").val();
					if(id){
						loadSimpleTrigger(id);
					}else{
						loadSimpleTrigger(-1);
					}
				}
			);
	});
function generateAll(){
	$.confirm("是否确定生成全部任务？", function() {
		jQuery.get("${ctx}/admin/qrtz/def/generateAll",function(data,status){
			alert(data);
			window.location.reload();
		});
	});
}
function stopAll(){
	$.confirm("是否确定停止全部任务？", function() {
		jQuery.get("${ctx}/admin/qrtz/def/stopAll",function(data,status){
			alert(data);
			window.location.reload();
		});
	});
}
function generateSelected(){
	$.confirm("是否确定生成所选任务？", function() {
		var ids = "";
		$("input:[name='box']:checked").each(function() {
			ids+=$(this).val()+",";
        });
		ids = ids.substring(0,ids.length-1);
		jQuery.get("${ctx}/admin/qrtz/def/generateSelected?cronTriggerIds="+ids,function(data,status){
			alert(data);
			window.location.reload();
		});
	});
}
function stopSelected(){
	$.confirm("是否确定生成所选任务？", function() {
		var ids = "";
		$("input:[name='box']:checked").each(function() {
			ids+=$(this).val()+",";
        });
		ids = ids.substring(0,ids.length-1);
		jQuery.get("${ctx}/admin/qrtz/def/stopSelected?cronTriggerIds="+ids,function(data,status){
			alert(data);
			window.location.reload();
		});
	});
}
function showRunning(){
	window.location.href="${ctx}/admin/qrtz/def/getRunningJobs";
}
</script>

</head>
<body>

<!-- 信息 -->
<div class="row">
	 <c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
</div>

<!-- 树结构 -->
<div class="col-md-2" style="width:190px;overflow:auto;">
    <div id="left">
		<ul id="defgrouptreemaintain" class="ztree" style="width:300px;"></ul>
	</div>
	
</div>

<!-- 主题内容 -->
<div class="col-md-10">
	<div id="right">
		<div class="btn-group">
			<button class="btn nframe btn-default" onclick="generateAll()">生成所有任务</button>
			<button class="btn nframe btn-default" onclick="stopAll()">停止所有任务</button>
			<button class="btn nframe btn-default" onclick="showRunning()">查看正在运行的任务</button>
		</div>
		<form id="inputform" class="form-horizontal"
						action="${ctx}/admin/qrtz/def/save-Group" method="post">
			<div class="panel panel-info">
				<div class="panel-heading" style="background-color: #EBEFF2;">调度组信息</div>
				<input id="qrtzGroup_id" type="hidden" name="groupId"/>
				<div class="panel-body">
					<div class="form-group">
						<label for="qrtzGroup_name" class="col-md-2 control-label">调度组名称</label>
						<div class="col-md-4">
							<input id="qrtzGroup_name" type="text" name="name" class="form-control input-sm required"
									>
						</div>
									
						<label for="qrtzGroup_sortIndex" class="col-md-2 control-label">排序号：</label>
						<div class="col-md-4">
							<input id="qrtzGroup_sortIndex" type="text" name="sortIndex" class="form-control input-sm required"
									>
						</div>
					</div>
					
					<div class="form-group">
				    	<label for="qrtzGroup_code" class="col-md-2 control-label">Code：</label>
						<div class="col-md-4">
							<input id="qrtzGroup_code" type="text" name="code" class="form-control input-sm required"
									>
						</div>
					</div>

				    <div class="form-group">
						<label for="qrtzGroup_descript" class="col-md-2 control-label">描述</label>
						<div class="col-md-8">
							<textarea id="qrtzGroup_descript" name="descript" class="form-control input-sm"></textarea>
						</div>
					</div>
				</div>
				
				<!-- footer -->
				<div class="panel-footer">
					<div class=" col-md-offset-2">
						<input class="btn btn-default " type="submit" value="保存" />
					</div>
				</div>
			</div>
		</form>
		
		<div class="btn-group">
			<button class="btn nframe btn-default b" data-fancybox-type="iframe">新增</button>
			<button class="btn nframe btn-default" id="remove">删除</button>
		</div>
		<div class="btn-group" style="float:right">
			<button class="btn nframe btn-default" onclick="generateSelected()">生成所选任务</button>
			<button class="btn nframe btn-default" onclick="stopSelected()">停止所选任务</button>
		</div>
		<div  class="panel panel-info">
		<div id="tabs" class="panel-heading" style="background-color: #EBEFF2;">
		    <ul class="nav nav-tabs" id="myTab">
		          <li id="tab1" style="display:none"><a href="javascript:void(0)">调度定义信息</a></li>
		          <li id="tab2" class="active"><a href="javascript:void(0)">CronTrigger定义信息</a></li>
		          <li id="tab3" style="display:none"><a href="javascript:void(0)">SimpleTrigger定义信息</a></li>
		    </ul>
		</div>
		<table id="definitionAll" class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>名称</th>
					<th>code</th>
					<th>权限定类名</th>
					<th>开始日期</th>
					<th>结束日期</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>重复次数</th>
					<th>结束类型</th>
					<th>定期模式类型</th>
					<th>元数据配置</th>
					<th>描述</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

</div>


<script type="text/javascript">
	$(document).ready(function(){
		$(".b").fancybox({
			fitToView : false,
			width : '80%',
			height : '80%',
			autoSize : false,
			closeClick : false,
			openEffect : 'none',
			closeEffect : 'none',
			beforeLoad : createDef,
			afterClose : function() {
				console.log("afterClose");
				var id = $("#qrtzGroup_id").val();
				console.log(id);
				console.log(tabNum);
				if(tabNum == 1){
					loadQrtzDefinition(id);
				}else if(tabNum == 2){
					loadCronTrigger(id);
				}else if(tabNum == 3){
					loadSimpleTrigger(id);
				}
				console.log("afterClose end");
			}
		});
		
		$(".ase").fancybox({
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			afterClose:function(){
				var id = $("#qrtzGroup_id").val();
				loadQrtzDefinition(id);
			}
		});
	});
	
	function createDef() {
		var id = $("#qrtzGroup_id").val();
		if (id == "1") {
// 			alert("根节点不能添加调度定义");
			$.dialog({content: '根节点不能添加调度定义!', time: 2000});
			return false;
		}
		if (id == "") {
// 			alert("请选择一个调度组");
			$.dialog({content: '请选择一个调度组!', time: 2000});
			return false;
		}
		var zTree = $.fn.zTree.getZTreeObj("defgrouptreemaintain");
		var child = zTree.getNodeByParam("pId", id, null);
		if (child != null) {
// 			alert("父节点不能添加调度定义");
			$.dialog({content: '父节点不能添加调度定义!', time: 2000});
			return false;
		}
		if(tabNum == 1){
			this.href = "${ctx}/admin/qrtz/def/create/" + id + "";
		}else if(tabNum == 2){
			this.href = "${ctx}/admin/qrtz/cron/create/" + id + "";
		}else if(tabNum == 3){
			this.href = "${ctx}/admin/qrtz/simple/create/" + id + "";
		}
		return true;
	};
	
	$("#remove").click(function() {
		var arr = "";
		$(".td :input[type=checkbox]").each(function(i) {
			if (this.checked == true) {
				var rs = this.value;
				arr = arr + rs + ",";
			}

		});
		if (arr.length == 0) {
// 			alert("您还没有选择要删除的对象");
			$.dialog({content: '您还没有选择要删除的对象!', time: 2000});
			return;
		} else {
			arr = arr.substring(0, arr.length - 1);
// 			var r = window.confirm("确定要删除吗？");
// 			if (r) {
			$.confirm("是否确定删除？", 
				function() {
				if(tabNum == 1){
					$.ajax({
						type : "post",
						data : $("#inputForm").serialize(),
						url : "${ctx}/admin/qrtz/def/delete2/" + arr,
						success : function(msg) {
							var id = $("#qrtzGroup_id").val();
							loadQrtzDefinition(id);
						}
					});
				}else if(tabNum == 2){
					$.ajax({
						type : "post",
						data : $("#inputForm").serialize(),
						url : "${ctx}/admin/qrtz/cron/delete/" + arr,
						success : function(msg) {
							//alert(msg);
							var id = $("#qrtzGroup_id").val();
							//alert(id);
							loadCronTrigger(id);
						}
					});
				}else if(tabNum == 3){
					$.ajax({
						type : "post",
						data : $("#inputForm").serialize(),
						url : "${ctx}/admin/qrtz/simple/delete/" + arr,
						success : function(msg) {
							var id = $("#qrtzGroup_id").val();
							loadSimpleTrigger(id);
						}
					});
				}
			})
		}
	});
</script>
</body>
</html>