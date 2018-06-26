<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>首选项定义</title>

<script type="text/javascript">

$(document).ready(function(){
	
	//聚焦第一个输入框
	$("#name").focus();
	//为inputForm注册validate函数
	$("#inputform").validate({
		rules :{
			 code:{
				required : true,
				remote : {
					url : "${ctx}/admin/preference/def/checkCode",
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
				$.post("${ctx}/admin/preference/def/definition/getPreference/"
						+ treeNode.id + "", function(data) {
					create(treeNode);
					return true;
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
		loadDefinitions(treeNode.id);
		var treeObj = $.fn.zTree.getZTreeObj("menutreemaintain");
		treeObj.selectNode(treeNode, false);
		return false;
	}

	function copyNodePropertyToForm(treeNode) {
		$("#category_id").val(treeNode.id);
		$("#category_pid").val(treeNode.pId);
		$("#name").val(treeNode.name);
		$("#code").val(treeNode.code);
		$("#hidden_code").val(treeNode.code);
		$("#overwritable").val(treeNode.overwritable);
		if(treeNode.overwritable=='true'){
			document.getElementById("isYes").checked=true;
		}else{
			document.getElementById("isNo").checked=true;
		}
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
		var a ="确定要删除吗？";
		$.post("${ctx}/admin/preference/def/definition/getPreference/"+ treeNode.id + "",function(data) {
			if (data != "") {
				a="该参数模板包含分类模板，确定要删除吗？";
			}
			$.post("${ctx}/admin/preference/def/checkPreferenceCategory/" + treeNode.id + "",function(data) {
				if (data != "") {
					a="该参数模板包含子模板，确定要删除吗？";
				}
				var r = window.confirm(a);
				if (r) {
					location.href="${ctx}/admin/preference/def/category/delete/"+ treeNode.id;
				}
			});
		});
		return false;
	}

	$(document).ready(
			function() {
				$.getJSON("${ctx}/admin/preference/def/get-tree?" + new Date(),
						function(data) {
							var zNodes = data;
							$.fn.zTree.init($("#menutreemaintain"), setting,
									zNodes);
							$("#callbackTrigger")
									.bind("change", {}, setTrigger);
							var zTree = $.fn.zTree
									.getZTreeObj("menutreemaintain");
							var node = zTree.getNodeByParam("pid", null, null);
							zTree.expandNode(node, true, true, true);
							zTree.selectNode(node);
							copyNodePropertyToForm(node);
							loadDefinitions(node.id);
						});
			});

	function loadDefinitions(categoryId) {
		$("#category_id").val(categoryId);
		/* $("#definitionsDiv").load(
				"${ctx}/admin/preference/def/definition/fetch/" + categoryId); */
		$.ajax({
			cache:false,
			type : "get",
			url : '${ctx}/admin/preference/def/definition/fetch/'+ categoryId,
			success : function(html) {
				$("#definitionsDiv").html(html);
			}
		});
	}
	$(document).ready(function() {
		$(".c").fancybox({
			fitToView : false,
			width : '80%',
			height : '80%',
			autoSize : false,
			closeClick : false,
			openEffect : 'none',
			closeEffect : 'none',
			beforeLoad : before,
			afterClose : function() {
				var categoryId = $("#category_id").val();
				loadDefinitions(categoryId);
			}
		});
	});
	function before() {
		var categoryId = $("#category_id").val();
		if (categoryId == "") {
			alert("请选择分类模板");
			return false;
		}
		this.href = "${ctx}/admin/preference/def/definition/create/"
				+ categoryId + "";
		return true;
	}
	$(document).ready(function() {
		$(".d").fancybox({
			fitToView : false,
			width : '80%',
			height : '80%',
			autoSize : false,
			closeClick : false,
			openEffect : 'none',
			closeEffect : 'none',
			afterClose : function() {
				var categoryId = $("#category_id").val();
				loadDefinitions(categoryId);
			}
		});
	});

	function deleteDefinition(id) {
		if (confirm("确定要删除选项定义吗？")) {
			$.ajax({
				type : "post",
				data : $("#inputForm").serialize(),
				url : "${ ctx }/admin/preference/def/definition/delete/" + id,
				success : function(msg) {
					var categoryId = $("#category_id").val();
					loadDefinitions(categoryId);
				}
			});
		}
	}
</script>
<script type="text/javascript">
function isTrue(){
	var a = document.getElementById("overwritables").value;
	if(a=="true"){
		return true;
	}
	return false;
}

</script>
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
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		${message}
	</div>
</c:if>
<body>
	<div id="main-content">
		<div class="container-fluid">
			<div class="row">
				<!-- 岗位列表/左边部分-->
				<div class="col-md-2">
					<ul id="menutreemaintain" class="ztree"></ul>
				</div>

				<!-- 输入框/右边部分 -->
				<div class="col-md-10">
					<form id="inputform" class="form-horizontal"
						action="${ctx}/admin/preference/def/category/save" method="post">
						<input id="category_id" type="hidden" name="id"
							value="${preferenceCategory.id}" /> <input type="hidden"
							id="initalname" name="initalname" />
						<div class="panel panel panel-info">
							<!-- head -->
							<div class="panel-heading">参数模板</div>
							<div class="panel-body">
								<div class="form-group">
									<label for="name" class="col-md-2 control-label">名称</label>
									<div class="col-md-4">
										<input id="name" type="text" name="name"
											class="form-control input-sm required"
											value="${preferenceCategory.name}">
									</div>
									
									<label for="code" class="col-md-2 control-label">code</label>
									<div class="col-md-4">
										<input id="code" type="text" name="code"
											class="form-control input-sm required"
											value="${preferenceCategory.code}">
									</div>
									<input type="hidden" id="hidden_code" name="hidden_code" value="">
									
								</div>

								<div class="form-group">
									<label for="overwritable" class="col-md-2 control-label">个性化</label>
									<div class="col-md-4">
									 
									<%-- 	<c:choose>
											<c:when test="isTrue()"> --%>
											<input type="radio" id="isYes" name="overwritable" value="true" /><label for="isYes">是</label>
											<input type="radio" id="isNo" name="overwritable" value="false" /><label for="isNo">否</label>
											<%-- </c:when>
											<c:otherwise>
											
											<input type="radio" id="Yes" name="overwritable" value="true"/><label for="Yes">是</label>
											<input type="radio" id="No" name="overwritable" value="false"  checked/><label for="No">否</label>
											</c:otherwise>
										</c:choose> --%>
										
									</div>
									
									<label for="sortIndex" class="col-md-2 control-label">排序号</label>
									<div class="col-md-4">
										<input type="text" id="sortIndex" name="sortIndex"
											class="form-control input-sm">
									</div>
								</div>

								<div class="form-group">
									<label for="dictionaryItem_remark" class="col-md-2 control-label">备注</label>
									<div class="col-md-10">
										<textarea name="descript" id="dictionaryItem_remark" class="form-control input-sm">${preferenceCategory.descript }</textarea>
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
						<button class="c btn btn-default" data-fancybox-type="iframe">新增选项</button>
						<button class="btn btn-default" id="remove">删除选项</button>
					</div>
					<div id="definitionsDiv" >
						<div class="panel panel-info">
						<div class="panel-heading">分类信息</div>
						<table id="contentTable"
							class="table table-condensed table-hover table-striped table-responsive">
							<thead>
								<tr>
									<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
									<th>名称</th>
									<th>代码</th>
									<th>数据类型</th>
									<th>控件类型</th>
									<th>排序号</th>
									<th>个性化</th>
									<th>操作</th>
								</tr>
							</thead>
							<div id="definitionsDiv"></div>
						</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$("#remove").click(function() {
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
			var r = window.confirm("确定要删除吗？");
			if(r){
				arr = arr.substring(0, arr.length - 1);
				$.ajax({
					type : "post",
					url : "${ ctx }/admin/preference/def/definition/deleteAll/" + arr,
					success : function(msg) {
						var categoryId = $("#category_id").val();
						loadDefinitions(categoryId);
					}
				});
			};
		};
	});
	</script>
</body>
</html>