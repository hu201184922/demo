<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="ctx" value="${ pageContext.request.contextPath }" />
<html>
<head>
<title>组管理</title>

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
	function roleSet() {
		var groupId = $("#groupId").val();
		if (groupId == undefined || groupId == '') {
			alert("请先选择组");
			return;
		}
		$.fancybox.open({
			type : "iframe",
			href : "${ctx}/admin/sec/group/setter/roles/" + groupId,
			fitToView : false,
			width : '80%',
			height : '80%',
			autoSize : false,
			closeClick : false,
			openEffect : 'none',
			closeEffect : 'none'
		});
	}
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
				$.ajax({
					type : "POST",
					url : "${ctx}/admin/sec/group/create",
					data : "parent.id=" + treeNode.id,
					success : function(msg) {
						var zTree = $.fn.zTree.getZTreeObj(treeId);
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
				url : "${ctx}/admin/sec/group/move/" + treeNodes[0].id,
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
		var zTree = $.fn.zTree.getZTreeObj("groupCategoryTree");
		zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr(
				"checked");
	}

	function beforeClick(treeId, treeNode, clickFlag) {
		copyNodePropertyToForm(treeNode);
		var zTree = $.fn.zTree.getZTreeObj("groupCategoryTree");
		zTree.selectNode(treeNode, treeId);
		return false;
	}
	function copyNodePropertyToForm(treeNode) {
		$("#groupId").val(treeNode.id);
		$("#group_code").val(treeNode.code);
		$("#group_name").val(treeNode.name);
		$("#group_sortIndex").val(treeNode.sortIndex);
		$("#group_descript").val(treeNode.descript);
		loadRole(treeNode.id);
		loadUsers(treeNode.id);
	}

	function loadRole(groupId) {
		$("#selectedValue").html("");
		$.ajax({
			type:"post",
			url:"${ctx}/admin/sec/group/get/roles/" + groupId, 
			success:function(msg) {
				
			for ( var i = 0; i < msg.length; i++) {
				$("#selectedValue").append(
						"<tr><td class='roleId'><input type='checkbox' id='"+msg[i].id+"' value='"+msg[i].id+"'/></td><td  selected>" + msg[i].name+ "</td><td>" + msg[i].code + 
						"</td><td>" + msg[i].category_name + "</td><td><a href='javascript:void()' onclick='deleteSingleRole("+msg[i].id+")'>撤除</a></td></tr>");
			}
			}
		});
	}

	function loadUsers(groupId) {
		//$("#groupUserList").html('<iframe id="groupUserFrame" src="${ctx}/admin/sec/group/users/'+groupId+'" width="100%" height="100%" scrolling="no" frameborder="no" border="0"></iframe>');
		//$("#groupUserList").load('${ctx}/admin/sec/group/users/'+groupId);
		$.ajax({
			cache:false,
			type : "get",
			url : '${ctx}/admin/sec/group/users/'+groupId,
			success : function(html) {
				$("#groupUserList").html(html);
			}
		});
	}

	function beforeRemove(treeId, treeNode) {
		if (confirm("确定要删除这条数据吗")) {
			$.ajax({
				type : "POST",
				url : "${ctx}/admin/sec/group/delete/" + treeNode.id,
				success : function(msg) {
					var zTree = $.fn.zTree.getZTreeObj(treeId);
					zTree.removeNode(treeNode);
				}
			});
		}

		return false;
	}

	function editGroup() {
		var groupId = $("#groupId").val();
		if (groupId == undefined || groupId == '') {
			alert("请先选择组");
			return;
		}

	}

	$(document).ready(
			function() {
				$.getJSON("${ctx}/admin/sec/group/get-tree?" + new Date(),
						function(data) {
							var zNodes = data;
							var zTree = $.fn.zTree.init($("#groupCategoryTree"), setting,
									zNodes);
							$("#callbackTrigger")
									.bind("change", {}, setTrigger);
							var groupId='${groupId}';
							if(groupId != ""){
								var node=zTree.getNodeByParam("id",groupId,null);
								beforeClick("groupCategoryTree",node,1);
								zTree.expandNode(node, true, true, true);
							}else{
								loadUsers(-1);
							}
						});
				//聚焦第一个输入框
				$("#group_name").focus();
				
				jdp.selectMultiUsers("addUsers",function(datas){
					var ids=[];
					for(var i in datas){
						ids.push(datas[i].id);
					}
					var groupId = $("#groupId").val();
					//location.href="${ctx}/admin/sec/group/addUsers/"+groupId+"?userIds="+ids.join(",");
					$.ajax({
						url		: "${ctx}/admin/sec/group/addUsers/"+groupId+"?userIds="+ids.join(","),
						type	: "get",
						success	: function(msg){
							loadUsers(groupId);
						},
						error	: function(XMLHttpRequest, textStatus, errorThrown){
							document.body.innerHTML = XMLHttpRequest.responseText;
						}
					});
				},function(){
					var groupId = $("#groupId").val();
					if(groupId == undefined || groupId == ''){
						alert("请先选择组");
						return false;
					}
					return true;
				});
			});
	$(document).ready(function(){
		$("#inputForm").validate({
			rules:{
				code:{required:true},
				descript:{required:false},
				sortIndex:{required:true},
				name:{required:true,
// 					remote:{
// 					url:"${ctx}/admin/sec/group/check",
// 					type:"post",
// 					dataType:"json",
// 					data:{
// 						name:function(){
// 							return $("#group_name").val();
// 						},
// 						initialname:function(){
// 							return $("#initialname").val();
// 						}
// 					}
// 				}	
			}
			}
		});
		$("#selectAllRole").click(function() { 
			var value = this.checked;
			if(value==true){
				$(".roleId :input").each(function(i){
					 this.checked=true;
				});
			}else{
				$(".roleId :input").each(function(i){
					 this.checked=false;
				});
			}
		});
		$.ajaxSetup ({
			cache: false 
			});
	});

function deleteBatch(){
	var arr=[];
	$(".userTd input:checked").each(function(i){
		if(this.checked==true){
			arr.push(this.value);
		}
		
	});
	if(arr.length==0){
		alert("您还没有选择要移除的对象");
	}else{
		if(confirm("您确定要移除选择的对象吗")){
			//location.href="${ctx}/admin/sec/group/deleteUsers/${group.id}?userIds="+arr.join(",");
			var groupId = $("#groupId").val();
			$.get("${ctx }/admin/sec/group/deleteUsers/"+groupId,{userIds:arr.join(",")},function(msg){
				loadUsers(groupId);
			});
		}
		
	}
}

function deleteSingleUser(userId){
	if(confirm('确定要移除吗？')){
		//location.href='${ctx }/admin/sec/group/deleteUser/${group.id}/${user.id}';
		var groupId = $("#groupId").val();
		$.get("${ctx }/admin/sec/group/deleteUser/"+groupId+"/"+userId,function(msg){
			loadUsers(groupId);
		});
	}
}
function deleteSingleRole(roleId){
	if(confirm('确定要撤除吗？')){
		//location.href='${ctx }/admin/sec/group/deleteUser/${group.id}/${user.id}';
		var groupId = $("#groupId").val();
		$.get("${ctx }/admin/sec/group/deleteRole/"+groupId+"/"+roleId,function(msg){
			loadRole(groupId);
		});
	}
}
function setLeader(userId,name){
	if(confirm('确定要选为组长吗？')){
		var groupId = $("#groupId").val();
		$.get("${ctx }/admin/sec/group/leader/"+groupId+"/"+userId,function(msg){
			$("#leaderName").html(name);
		});
	}
	
}
function deleteBatch2(){
	var arr=[];
	$(".roleId input:checked").each(function(i){
		if(this.checked==true){
			arr.push(this.value);
		}	
	});
	if(arr.length==0){
		alert("您还没有选择要撤除的对象");
	}else{
		if(confirm("您确定要撤除选择的对象吗")){
			var groupId = $("#groupId").val();
			$.get("${ctx }/admin/sec/group/deleteRoles/"+groupId,{roleIds:arr.join(",")},function(msg){
				loadRole(groupId);
			});
		}
		
	}
}

</script>
</head>
<body>
	<div class="row">
		<div class="col-md-2" style="position:opposite;z-index:999;height:auto;background-color:white;border:0px solid;overflow-y:auto;overflow-x:auto;">
			<ul id="groupCategoryTree" class="ztree"></ul>
		</div>
		<div class="col-md-10">
			<form id="inputForm" action="${ctx}/admin/sec/group/update"
				method="post" class="form-horizontal">
				<div id="hideFields">
					<input type="hidden" id="groupId" name="id" />
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">组信息</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="group_name" class="col-md-2 control-label">名称:</label>
							<div class="col-sm-3">
								<input type="text" class=" required form-control input-sm"
									id="group_name" name="name">
							</div>
							<label for="group_code" class="col-md-2 control-label">编号:</label>
							<div class="col-sm-3">
								<input type="text" class=" required form-control input-sm"
									id="group_code" name="code">
							</div>
						</div>
						<div class="form-group">
							<label for="group_sortIndex" class="col-md-2 control-label">排序号:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm required" id="group_sortIndex"
									name="sortIndex">
							</div>
							<label for="group_descript" class="col-md-2 control-label">备注:</label>
							<div class="col-sm-3">
								<textarea id="group_descript" name="descript"
									class="form-control input-sm required"></textarea>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class=" col-md-offset-2">
							<input id="submit_btn" class="btn btn-default" type="submit"
								value="保存" />
						</div>
					</div>
				</div>
			</form>
			<div>
				<div class="btn-group">
					<button class="btn btn-default roleSet" onclick="roleSet()">分配角色</button>
					<button class="btn btn-default roleSet" onclick="deleteBatch2()">撤除角色</button>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">组内角色</div>
						<table id="contentTable" class="table table-condensed table-hover table-striped table-responsive">
						<thead>
							<tr>
								<th><input type="checkbox" name="selectAllRole" id="selectAllRole"/></th>
								<th>角色名称</th>
								<th>标识</th>
								<th>角色类别</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="selectedValue">
						</tbody>
						</table>
				</div>
			</div>
			<div>
				<div class="btn-group">
							<button class="btn btn-default addUsers" >添加用户</button>
							<button class="btn btn-default" onclick="deleteBatch()">移除用户</button>
						</div>
				<div id="groupUserList">
				</div>
			</div>
		</div>
	</div>

</body>
</html>