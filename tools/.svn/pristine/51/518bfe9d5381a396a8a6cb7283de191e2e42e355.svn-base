<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>资源管理</title>
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
</head>
<body>
	<div class="row-fluid">
		<c:if test="${not empty message}">
			<div id="message" class="alert alert-success">
				<button data-dismiss="alert" class="close">×</button>${message}</div>
		</c:if>
	</div>
	<div class="row">
		<div class="col-md-2">
			<ul id="resourceTree" class="ztree"></ul>
		</div>
		<div class="col-md-10">
			<form id="inputForm" action="${ctx}/admin/sec/res/update" method="post" class="form-horizontal">
			<div class="panel panel panel-info">
				<input type="hidden" id="res_id" name="id" value="" /> <input
					type="hidden" id="res_parentId" name="parent.id" value="" /> <input
					type="hidden" name="initalname" id="initalname" />
					<!-- head -->
				<div class="panel-heading">资源管理</div>
				<div class="panel-body">
					<div class="form-group">
						<label for="res_name" class="col-md-2 control-label">资源名称</label>
						<div class="col-md-4">
							<input type="text" id="res_name" name="name" class="form-control input-sm">
						</div>
					</div>
					
					<div class="form-group">
						<label for="res_resType" class="col-md-2 control-label">资源类型</label>
						<div class="col-md-4">
							<input type="text" id="res_resType" name="resType" class="form-control input-sm">
						</div>
					</div>
					
					<div class="form-group">
						<label for="res_resString" class="col-md-2 control-label">资源字符串</label>
						<div class="col-md-4">
							<input type="text" id="res_resString" name="resString" class="form-control input-sm">
						</div>
					</div>
					
					<div class="form-group">
						<label for="res_perString" class="col-md-2 control-label">授权字符串</label>
						<div class="col-md-4">
							<input type="text" id="res_perString" name="perString" class="form-control input-sm">
						</div>
					</div>
					
					<div class="form-group">
						<label for="res_sortIndex" class="col-md-2 control-label">排序号</label>
						<div class="col-md-4">
							<input type="text" id="res_sortIndex" name="sortIndex" class="form-control input-sm">
						</div>
					</div>
					
					<div class="form-group">
						<label for="res_descript" class="col-md-2 control-label">描述</label>
						<div class="col-md-4">
							<textarea id="res_descript" name="descript" class="form-control input-sm"></textarea>
						</div>
					</div>
					</div>
						<!-- footer -->
						<div class="panel-footer">
							<div class=" col-md-offset-2">
								<input id="submit_btn" class="btn btn-default" type="submit" value="保存" /> 
							</div>
						</div>
					</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
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
					enable : true,
					idKey : "id",
					pIdKey : "pId"
					
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
			if (treeNode.editNameFlag
					|| $("#addBtn_" + treeNode.tId).length > 0)
				return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
					+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_" + treeNode.tId);
			if (btn)
				btn.bind("click", function() {
					$.ajax({
						type : "POST",
						url : "${ctx}/admin/sec/res/create",
						data : "parent.id=" + treeNode.id,
						success : function(msg) {
							var zTree = $.fn.zTree.getZTreeObj("resourceTree");
							zTree.addNodes(treeNode, {
								id : msg.id,
								pId : msg.parent.id,
								name : msg.name
							});
						}
					});

					return false;
				});
		};

		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_" + treeNode.tId).unbind().remove();
		};

		function dropPrev(treeId, nodes, targetNode) {
			var pNode = targetNode.getParentNode();
			if (pNode && pNode.dropInner === false) {
				return false;
			} else {
				for (var i = 0, l = curDragNodes.length; i < l; i++) {
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
				for (var i = 0, l = curDragNodes.length; i < l; i++) {
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
				for (var i = 0, l = curDragNodes.length; i < l; i++) {
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
			for (var i = 0, l = treeNodes.length; i < l; i++) {
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
			if(moveType=='inner'){
				parentId = targetNode.id;
			}else{
				//与目标节点成为同级节点
				parentId = targetNode.pid;
			}
			if(treeNodes[0].pid != parentId){
				$.ajax({
					type : "POST",
					url : "${ctx}/admin/sec/res/move/" + treeNodes[0].id,
					data:{"parentId": parentId},
					success : function(msg) {
						var zTree = $.fn.zTree.getZTreeObj(treeId);
						treeNodes[0].pid = parentId;
						zTree.moveNode(targetNode,treeNodes[0],moveType,false);
					}
				});
			}
			
			className = (className === "dark" ? "" : "dark");
			showLog("[ " + getTime()
					+ " beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:"
					+ moveType);
			showLog("target: " + (targetNode ? targetNode.name : "root")
					+ "  -- is "
					+ (isCopy == null ? "cancel" : isCopy ? "copy" : "move"));
			return false;
		}
		function onDrag(event, treeId, treeNodes) {
			className = (className === "dark" ? "" : "dark");
			showLog("[ " + getTime()
					+ " onDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: "
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
				showLog("[ " + getTime()
						+ " onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
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
			var zTree = $.fn.zTree.getZTreeObj("resourceTree");
			zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger")
					.attr("checked");
		}

		function beforeClick(treeId, treeNode, clickFlag) {
			copyNodePropertyToForm(treeNode);
			var zTree = $.fn.zTree.getZTreeObj("resourceTree");
			zTree.selectNode(treeNode, treeId);
			return false;
		}
		function copyNodePropertyToForm(treeNode) {
			$("#res_id").val(treeNode.id);
			$("#res_parentId").val(treeNode.pId);
			$("#res_name").val(treeNode.name);
			$("#res_resType").val(treeNode.resType);
			$("#res_resString").val(treeNode.resString);
			$("#res_perString").val(treeNode.perString);
			$("#initalname").val(treeNode.name);
			if (treeNode.descript) {
				$("#res_descript").text(treeNode.descript);
			}
			$("#res_sortIndex").val(treeNode.sortIndex);
		}

		function beforeRemove(treeId, treeNode) {
			if(confirm("确定要删除选中节点吗") == false){
				return false;
			}
			$.ajax({
				type : "POST",
				url : "${ctx}/admin/sec/res/delete/" + treeNode.id,
				success : function(msg) {
					var zTree = $.fn.zTree.getZTreeObj("resourceTree");
					zTree.removeNode(treeNode);
				}
			});
			return false;
		}
		$(document).ready(
				function() {
					$.getJSON("${ctx}/admin/sec/res/get-tree?" + new Date(),
							function(data) {
								var zNodes = data;
								$.fn.zTree.init($("#resourceTree"), setting,
										zNodes);
								$("#callbackTrigger").bind("change", {},
										setTrigger);
							});
					$("#inputForm").validate({
						rules : {
							name : {
								remote : {
									url : "${ctx}/admin/sec/resource/check",
									type : "post",
									dataType : "json",
									data : {
										name : function() {
											return $("#res_name").val();
										},
										initalname : function() {
											return $("#initalname").val();
										}
									}
								}
							},
							sortIndex : {
								digits : true
							}
						}
					});
				});
	</script>
</body>
</html>