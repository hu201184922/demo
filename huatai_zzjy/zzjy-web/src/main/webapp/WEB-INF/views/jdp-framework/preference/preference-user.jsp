<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>首选项定义</title>

<script type="text/javascript">
	function loadDefinitionForm(categoryId) {
		$("#definitionFormDiv")
				.load(
						"${ctx}/admin/preference/user/loadDefinitionForm/"
								+ categoryId);
	}
	var setting = {
			view : {
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
					$.post("${ctx}/admin/preference/def/definition/getPreference/"
							+ treeNode.id + "", function(data) {
						if (data == "") {
							create(treeNode);
							return true;
						} else {
							alert("请删除所有字典项");
							return false;
						}
					});
					return false;
				});
		};
		function create(treeNode) {
			$.ajax({
				type : "POST",
				url : "${ctx}/admin/preference/def/category/create",
				data : "parent.id=" + treeNode.id,
				success : function(msg) {
					var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
					zTree.addNodes(treeNode, {
						id : msg.id,
						pId : msg.parent.id,
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
					url : "${ctx}/admin/preference/def/category/move/"
							+ treeNodes[0].id,
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
			loadDefinitionForm(treeNode.id);
			var treeObj = $.fn.zTree.getZTreeObj("menutreemaintain");
			treeObj.selectNode(treeNode, false);
			return false;
		}

		function copyNodePropertyToForm(treeNode) {
			$("#category_id").val(treeNode.id);
			$("#category_pid").val(treeNode.pId);
			$("#name").val(treeNode.name);
			$("#code").val(treeNode.code);
			$("#sortIndex").val(treeNode.sortIndex);
			$("#initalname").val(treeNode.name);
			$("#descript").val(treeNode.descript);
			if (treeNode.id == 1) {
				$("#submit_btn").attr("disabled", "disabled");
			} else {
				$("#submit_btn").removeAttr("disabled");
			}

		}

		function beforeRemove(treeId, treeNode, data) {
			var r = window.confirm("确定要删除吗？");
			if (r) {
				$.post("${ctx}/admin/preference/def/definition/getPreference/"
						+ treeNode.id + "", function(data) {
					if (data == "") {
						$.ajax({
							type : "POST",
							url : "${ctx}/admin/preference/def/category/delete/"
									+ treeNode.id,
							success : function(msg) {
								var zTree = $.fn.zTree
										.getZTreeObj("menutreemaintain");
								zTree.removeNode(treeNode);
								alert("删除成功!");
								location.reload();
							}
						});
						return true;
					} else {
						alert("请删除所有字典项");
						return false;
					}
				});
			}
			;
			return false;
		}

		$(document).ready(
				function() {

					$.getJSON("${ctx}/admin/preference/user/get-tree/overwritable?" + new Date(),
							function(data) {
						/* var len=data.length;
								for(var i=0; i < len; i++) {
									alert(data[i].overwritable);
									if(data[i].overwritable=="false") {
										//i为删除元素的索引,1为删除的元素的个数
										//alert(data.length);
										data.splice(i,1);
										//data.remove(data[i]);
										//data.length--;
										//len++;
										
									}
								}  */
							
								//alert(data.length);
								if(data.length != 0){
									var zNodes = data;
									$.fn.zTree.init($("#menutreemaintain"), setting,
											zNodes);
									$("#callbackTrigger")
											.bind("change", {}, setTrigger);
									var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
									var node = zTree.getNodeByParam("pid", null, null);
									zTree.expandNode(node, true, true, true);
									zTree.selectNode(node);
									copyNodePropertyToForm(node);
									loadDefinitionForm(node.id);
								}
							});
				});

</script>

</head>
<body>

	<div id="main-content">
		<div class="container-fluid">
			<div class="row">
				<!-- 岗位列表/左边部分-->
				<div class="col-md-2">
					<ul id="menutreemaintain" class="ztree"></ul>
				</div>

				<!-- 输入框/右边部分 -->
				<div class="col-md-9">
					<div class="span9">
						<div id="definitionFormDiv"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>