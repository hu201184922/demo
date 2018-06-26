<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<SCRIPT type="text/javascript">
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules : {
				code : {
					required : true,
					remote : {
						url : "${ctx}/admin/log/check",
						type : "post",
						dataType : "json",
						data : {
							code : function() {
								return $("#hyLogConfigCategory_code").val();
							},
							initalname : function() {
								return $("#hidden_code").val();
							},
						}
					}
				},
				sortIndex : {
					digits : true
				}
			},

		});
	})

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
				$.post("${ctx}/admin/log/getHyLogConfigCategory/" + treeNode.id
						+ "",//??
				function(data) {
					if (data == "") {
						create(treeNode);
						return true;
					} else {
						alert("该日志配置中有分类，无法添加日志配置");
						return false;
					}
				});
				return false;
			});
	};

	function create(treeNode) {
		$.ajax({
			type : "POST",
			url : "${ctx}/admin/log/create",
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
				url : "${ctx}/admin/log/move/" + treeNodes[0].id,
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
		loadDictItem(treeNode.id);
		var treeObj = $.fn.zTree.getZTreeObj("menutreemaintain");
		treeObj.selectNode(treeNode, false);
		return false;
	}

	function copyNodePropertyToForm(treeNode) {
		$("#hyLogConfigCategory_id").val(treeNode.id);
		$("#hyLogConfigCategory_pid").val(treeNode.pId);
		$("#hyLogConfigCategory_name").val(treeNode.name);
		$("#hyLogConfigCategory_code").val(treeNode.code);
		$("#hidden_code").val(treeNode.code);
		$("#hyLogConfigCategory_sortIndex").val(treeNode.sortIndex);
		$("#hyLogConfigCategory_descript").val(treeNode.descript);
		if (treeNode.id == 1) {
			$("#submit_btn").attr("disabled", "disabled");
		} else {
			$("#submit_btn").removeAttr("disabled");
		}

	}

	function beforeRemove(treeId, treeNode, data) {
		var r = window.confirm("确定要删除吗？");
		if (r) {
			$.post("${ctx}/admin/log/getHyLogConfigCategory/" + treeNode.id
					+ "", function(data) {
				if (data == "") {
					$.ajax({
						type : "POST",
						url : "${ctx}/admin/log/delete/" + treeNode.id,
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
					alert("该日志配置已包含分类，无法删除分类");
					return false;
				}
			});
		}
		;
		return false;
	}

	$(document).ready(function() {

		$.getJSON("${ctx}/admin/log?" + new Date(), function(data) {
			var zNodes = data;
			$.fn.zTree.init($("#menutreemaintain"), setting, zNodes);
			$("#callbackTrigger").bind("change", {}, setTrigger);
			var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
			var node = zTree.getNodeByParam("pid", null, null);
			zTree.expandNode(node, true, true, true);
			zTree.selectNode(node);
			copyNodePropertyToForm(node);

		});
	});

	function loadHyLogConfigCategory(id) {
		$("#hyLogConfigCategory_id").val(id);
		$("#hyLogConfigCategoryAll").load(
				"${ctx}/log/getHyLogConfigCategory/" + id);
	}

	function createHyLogConfigCategoryPage() {
		var id = $("#hyLogConfigCategory_id").val();
		if (id == "1") {
			alert("根节点不能添加日志配置分类");
			return false;
		}
		if (id == "") {
			alert("请选择一个日志配置");
			return false;
		}
		var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
		var child = zTree.getNodeByParam("pId", id, null);
		if (child != null) {
			alert("父节点不能添加日志配置分类");
			return false;
		}
		location.href = "${ctx}/admin/log/createHyLogConfigCategorypage/" + id
				+ "";
	};

	function beforeLoad() {
		var id = $("#dict_id").val();
		if (id == "1") {
			alert("根节点不能添加日志配置分类");
			return false;
		}
		if (id == "") {
			alert("请选择一个日志配置");
			return false;
		}
		var zTree = $.fn.zTree.getZTreeObj("menutreemaintain");
		var child = zTree.getNodeByParam("pId", id, null);
		if (child != null) {
			alert("父节点不能添加日志配置分类");
			return false;
		}
		this.href = "${ctx}/admin/log/createHyLogConfigCategorypage/" + id + "";
		return true;
	};
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
				var id = $("#hyLogConfigCategory_id").val();
				loadHyLogConfigCategory(id);
			}
		});
	});
	$(document).ready(function() {
		$(".a").fancybox({
			fitToView : false,
			width : '80%',
			height : '80%',
			autoSize : false,
			closeClick : false,
			openEffect : 'none',
			closeEffect : 'none',
			afterClose : function() {
				var id = $("#hyLogConfigCategory_id").val();
				loadHyLogConfigCategory(id);
			}
		});
	});
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
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		${message}
	</div>
</c:if>
<div class="row">
	<div class="col-md-2" style="overflow-x: scroll">
		<ul id="menutreemaintain" class="ztree"></ul>
	</div>
	<div class="col-md-10">
		<form id="inputForm" action="${ctx}/admin/log/update" method="post"
			class="form-horizontal">
			<div class="panel panel panel-info">
				<input type="hidden" value="${hyLogConfigCategory.id}"
					id="hyLogConfigCategory_id" name="id" /> <input type="hidden"
					value="${hyLogConfigCategory.parent.id}"
					id="hyLogConfigCategory_pid" name="parent.id" /> <input
					type="hidden" id="initalname" name="initalname" />
				<!-- head -->
				<div class="panel-heading">日志配置分类</div>
				<div class="panel-body">
					<div class="form-group">
						<label for="hyLogConfigCategory_code"
							class="col-md-2 control-label">代码</label>
						<div class="col-md-4">
							<input type="text" id="hyLogConfigCategory_code" name="code"
								class="form-control input-sm required">
						</div>
					</div>

					<div class="form-group">
						<label for="hyLogConfigCategory_descript"
							class="col-md-2 control-label">描述</label>
						<div class="col-md-4">
							<textarea id="hyLogConfigCategory_descript" name="descript"
								class="form-control input-sm"></textarea>
						</div>
					</div>
				</div>


				<!-- footer -->
				<div class="panel-footer">
					<div class=" col-md-offset-2">
						<input id="submit_btn" class="btn btn-default" type="submit"
							value="保存" />
					</div>
				</div>
			</div>
		</form>
		<div class="btn-group">
			<a class="b btn btn-default" type="button" id="create"
				data-fancybox-type="iframe">新增</a>
			<button class="btn btn-default" type="button" id="remove">删除</button>
			<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
				操作 <span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<li><a>刷新缓存</a></li>
			</ul>
		</div>

		<div id="hyLogConfigCategoryAll" class="panel panel-info">
			<div class="panel-heading">字典项</div>
			<table id="contentTable"
				class="table table-condensed table-hover table-striped table-responsive">
				<thead>
					<tr>
						<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
						<th>名称</th>
						<th>编码</th>
						<th>备注</th>
						<th>排序号</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script>
		$("#remove")
				.click(
						function() {
							var arr = "";
							$(".td :input[type=checkbox]").each(function(i) {
								if (this.checked == true) {
									var rs = this.value;
									arr = arr + rs + ",";
								}

							});
							if (arr.length == 0) {
								alert("您还没有选择要删除的对象");
							} else {
								arr = arr.substring(0, arr.length - 1);
								var r = window.confirm("确定要删除吗？");
								if (r) {
									$
											.ajax({
												type : "post",
												data : $("#inputForm")
														.serialize(),
												url : "${ctx}/admin/log/deleteHyLogConfigCategory/"
														+ arr,
												success : function(msg) {
													var id = $(
															"#hyLogConfigCategory_id")
															.val();
													loadHyLogConfigCategory(id);
												}
											});
								}
							}
						});
	</script>

	<script>
		function hyLogConfigCategoryIds(id) {
			var r = window.confirm("确定要删除吗？");
			if (r) {
				$
						.ajax({
							type : "post",
							data : $("#inputForm").serialize(),
							url : "${ctx}/admin/log/deleteHyLogConfigCategoryOne/"
									+ id,
							success : function(msg) {
								var id = $("#hyLogConfigCategory_id").val();
								loadHyLogConfigCategory(id);
							}
						});
			}
			;
		};
	</script>
</div>