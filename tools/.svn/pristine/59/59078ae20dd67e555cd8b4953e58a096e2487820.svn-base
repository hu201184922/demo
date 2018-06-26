<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta name="decorator" content="admin-popup">
<html>
<head>
<script> 
	var setting = {
			view : {
				selectedMulti : false
			},
			edit : {
				enable : false,
				showAddBtn : false,
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
				onExpand : onExpand
			}
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
					url : "${ctx}/admin/dict/move/" + treeNodes[0].id,
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
		var data;
		function beforeClick(treeId, treeNode, clickFlag) {
			//copyNodePropertyToForm(treeNode);
			//loadDictItem(treeNode.id);
			//var treeObj = $.fn.zTree.getZTreeObj("menutreemaintain");
			//treeObj.selectNode(treeNode, false);
			data = treeNode;
			parent.$.fancybox.close();
			return false;
		}
			function getValue(){
				return data;
			}
		function onExpand(event, treeId, treeNode) {
			if (treeNode === autoExpandNode) {
				className = (className === "dark" ? "" : "dark");
				showLog("[ " + getTime() + " onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;"
						+ treeNode.name);
			}
		}


		<%--function copyNodePropertyToForm(treeNode) {
			$("#dict_id").val(treeNode.id);
			$("#dict_pid").val(treeNode.pId);
			$("#dict_name").val(treeNode.name);
			$("#dict_code").val(treeNode.code);
			$("#hidden_code").val(treeNode.code);
			if (treeNode.system == "true") {
				document.getElementById("isYes").checked=true;
			} else {
				document.getElementById("isNo").checked=true;
			}
			;
			$("#dict_sortIndex").val(treeNode.sortIndex);
			$("#dict_descript").val(treeNode.descript);
			if (treeNode.id == 1) {
				$("#submit_btn").attr("disabled", "disabled");
			} else {
				$("#submit_btn").removeAttr("disabled");
			}

		}
--%>
		$(document).ready(function() {

			$.getJSON("${ctx}/admin/dict/sys/get-tree?" + new Date(), function(data) {
				var zNodes = data;
				$.fn.zTree.init($("#menutreemaintain"), setting, zNodes);
				$("#callbackTrigger").bind("change", {}, setTrigger);
				var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
				var node = zTree.getNodeByParam("pid", null, null);
				zTree.expandNode(node, true, true, true);
				zTree.selectNode(node);
				//copyNodePropertyToForm(node);

			});
		});
</script>
<title>首选项字典选择</title>
</head>
<body>
<div class="panel panel panel-info">
			<div class="panel-heading">首选项字典code选择</div>
			<div class="panel-body">
<div class="col-md-4">
		<div id="menutreemaintain" class="ztree"></div>
</div>
</div>
</div>
</body>
<%--<script type="text/javascript">
$(document).ready(function() {
		$(".b").fancybox({
			fitToView : false,
			width : '80%',
			height : '80%',
			autoSize : false,
			closeClick : false,
			openEffect : 'none',
			closeEffect : 'none',
			beforeLoad : beforeLoad,
			afterClose : function() {
				var id = $("#dict_id").val();
				//loadDictItem(id);
				parent.location.reload();
			}
		});
	});
</script> --%>
</html>
