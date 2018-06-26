<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<SCRIPT type="text/javascript">
<!--
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
			showRemoveBtn : false,
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
			//beforeRemove : beforeRemove
		}
	};

	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
			return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
		if(treeNode.id == -1){
			sObj.after(addStr);
		}else{
			var removeStr = "<span class='button remove' id='remBtn_" + treeNode.tId
				+ "' title='remove node' onfocus='this.blur();'></span>";
			sObj.after(addStr+removeStr);
		}
		
		var btn = $("#addBtn_" + treeNode.tId);
		if (btn)
			btn.bind("click", function() {
				$.ajax({
					type : "POST",
					url : "${ctx}/admin/menu/create",
					data : "parent.id=" + treeNode.id,
					success : function(msg) {
						/* var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
						zTree.addNodes(treeNode, {
							id : msg.id,
							pId : msg.parent?msg.parent.id:-1,
							name : msg.name
						}); */
						location.reload();
					}
				});
				return false;
			});

		var remBtn = $("#remBtn_" + treeNode.tId);
		if (remBtn)
			remBtn.bind("click", function() {
				return beforeRemove(treeId, treeNode);
			});
	};

	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.tId).unbind().remove();
		$("#remBtn_" + treeNode.tId).unbind().remove();
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
			if(targetNode.id == -1){
				return false;
			}
			parentId = targetNode.pid;
		}
		if (treeNodes[0].pid != parentId) {
			$.ajax({
				type : "POST",
				url : "${ctx}/admin/menu/move/" + treeNodes[0].id,
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
		var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
		zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr(
				"checked");
	}

	function beforeClick(treeId, treeNode, clickFlag) {
		copyNodePropertyToForm(treeNode);
		var treeObj = $.fn.zTree.getZTreeObj("menutreemaintain");
		treeObj.selectNode(treeNode, false);
		return false;
	}

	function copyNodePropertyToForm(treeNode) {
		$("#menu_id").val(treeNode.id);
		$("#menu_name").val(treeNode.name);
		$("#menu_url").val(treeNode.url);
		$("#menu_permString").val(treeNode.permString);
		$("#menu_menuIcon").val(treeNode.menuIcon);
		$("#menu_sortIndex").val(treeNode.sortIndex);
		if (treeNode.visible == "true") {
			document.getElementById("isYes").checked=true;
		} else {
			document.getElementById("isNo").checked=true;
		}
		;
		if (treeNode.enabled == "true") {
			document.getElementById("yes").checked=true;
		} else {
			document.getElementById("no").checked=true;
		}
		;
		$("#menu_parentId").val(treeNode.pId);
		$("#menu_description").val(treeNode.description);
		//虚拟菜单不可编辑
		if(treeNode.id == -1){
			$("#inputForm input,textarea").attr("disabled",true);
			$("#buildResource_btn").attr("disabled",false);
		}else{
			$("#inputForm input,textarea").attr("disabled",false);
		}
		
	}

	function beforeRemove(treeId, treeNode) {
		var r = window.confirm("确定要删除吗？");
		if (r) {
			$.ajax({
				type : "POST",
				url : "${ctx}/admin/menu/delete/" + treeNode.id,
				success : function(msg) {
					var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
					zTree.removeNode(treeNode);
					alert("删除成功!");
					location.reload();
				}
			});
		}
		return false;
	}

	$(document).ready(function() {

		$.getJSON("${ctx}/admin/menu/get-tree?" + new Date(), function(data) {
			var zNodes = {id:-1,name:"根菜单",description:"这是一个虚拟菜单",open:true,children:data};
			$.fn.zTree.init($("#menutreemaintain"), setting, zNodes);
			$("#callbackTrigger").bind("change", {}, setTrigger);

			var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
			var node = zTree.getNodeByParam("id", -1, null);
			zTree.expandNode(node, true, true, true);
			zTree.selectNode(node);
			copyNodePropertyToForm(node);
		});
	});
//-->
</SCRIPT>
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>

<div class="row">
	<div class="col-md-2" style="overflow-x:auto;">
		<ul id="menutreemaintain" class="ztree"></ul>
	</div>
	<div class="col-md-10">
		<form class="form-horizontal" role="form" id="inputForm"
			action="${ctx}/admin/menu/update" method="post">
			<div class="panel panel panel-info">
				<!-- head -->
				<div class="panel-heading">菜单信息维护</div>
				<div class="panel-body">

					<input type="hidden" id="menu_id" name="id" value="${menu.id}" />
					<input type="hidden" id="menu_parentId" name="parent.id"
						value="${menu.parent.id}" />
					<div class="form-group">
						<label for="menu_name" class="col-md-2 control-label">菜单名称:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm required"
								id="menu_name" name="name" value="${menu.name}"
								placeholder="菜单名称">
						</div>
						<label for="menu_url" class="col-md-2 control-label">菜单路径:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm required"
								id="menu_url" name="url" value="${menu.url}" placeholder="菜单路径">
						</div>
					</div>

					<div class="form-group">
						<label for="menu_permString" class="col-md-2 control-label">授权字符串:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm required"
								id="menu_permString" name="permString"
								value="${menu.permString}" placeholder="授权字符串">
						</div>
						<label for="menu_sortIndex" class="col-md-2 control-label">排序号:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm required"
								id="menu_sortIndex" name="sortIndex" value="${menu.sortIndex}"
								placeholder="排序号">
						</div>
					</div>

					<div class="form-group">
						<label for="menu_visible" class="col-md-2 control-label">可见:</label>
						<div class="col-md-4">
							<input type="radio" id="isYes" name="visible" value="true" /><label for="isYes">是</label> 
							<input type="radio" id="isNo" name="visible" value="false" /><label for="isNo">否</label>
						</div>
						<label for="menu_enabled" class="col-md-2 control-label">可用:</label>
						<div class="col-md-4">
							<input type="radio" id="yes" name="enabled" value="true" /><label for="isYes">是</label> 
							<input type="radio" id="no" name="enabled" value="false" /><label for="isNo">否</label>
						</div>
					</div>

					<div class="form-group">
						<label for="menu_icon" class="col-md-2 control-label">图标:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" id="menu_menuIcon"
								name="menuIcon" value="${menu.menuIcon}">
						</div>
						<label for="description" class="col-md-2 control-label">备注:</label>
						<div class="col-sm-4">
							<textarea id="menu_description" name="description"
								class="form-control input-sm">${menu.description}</textarea>
						</div>
					</div>
				</div>
				<!-- footer -->
				<div class="panel-footer">
					<div class=" col-md-offset-2">
						<input id="submit_btn" class="btn btn-default" type="submit"
							value="保存" /> <input id="buildResource_btn" class="btn btn-default"
							type="button" onclick="buildResource();" value="生成资源" />
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#menu_name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});

	function buildResource() {
		location = "${ctx}/admin/sec/res/create/" + $("#menu_id").val();
	}
</script>
</div>
</div>
