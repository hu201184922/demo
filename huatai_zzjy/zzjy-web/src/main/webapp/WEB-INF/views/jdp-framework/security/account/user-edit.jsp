<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="admin-popup">
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
<div class="col-md-12">
	<form id="inputForm" action="${ctx}/admin/sec/user/${action}"
		method="post" class="form-horizontal">
		<div class="panel panel panel-info">
			<!-- head -->
			<div class="panel-heading">用户管理</div>
			<div class="panel-body">
				<input type="hidden" name="id" value="${user.id}" /> <input
					type="hidden" name="action" id="action" value="${action}" />
					<div class="form-group">
						<label for="loginName" class="col-md-3 control-label">登录名:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm required" id="account"
								name="account" value="${user.account}" readonly/>
						</div>
						<span id="loginNameTip"></span>
					</div>
					
					<div class="form-group">
						<label for="name" class="col-md-3 control-label">用户名:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm required" id="userName"
								name="userName" value="${user.userName}"/>
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="registTime" class="col-md-3 control-label">注册日期:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm" id="registTime"
								name="registTime" value="${user.registerDate}" disabled/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="firstLoginedTime" class="col-md-3 control-label">首次登录时间:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm" id="firstLoginedTime"
								name="firstLoginedTime" value="${user.firstLoginedTime}" disabled/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="lastLoginedTime" class="col-md-3 control-label">最后一次登录时间:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm" id="lastLoginedTime"
								name="lastLoginedTime" value="${user.lastLoginedTime}" disabled/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="expiredTime" class="col-md-3 control-label">过期时间:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm" id="expiredTime"
								name="expiredTime" value="${user.expiredTime}" disabled/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="lockedTime" class="col-md-3 control-label">锁定时间:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm" id="lockedTime"
								name=" lockedTime" value="${user.lockedTime}" disabled/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="pswChangeDate" class="col-md-3 control-label">密码修改时间:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm" id="pswChangeDate"
								name="pswChangeDate" value="${user.pswChangeDate}" disabled/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="credentialsExpiredTime" class="col-md-3 control-label">凭据过期时间:</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-sm" id="credentialsExpiredTime"
								name="credentialsExpiredTime" value="${user.credentialsExpiredTime}" disabled/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">是否超级管理员:</label>
						<div class="col-md-4">
						<c:choose>
							<c:when test="${user.isAdmin }">
							<input type="radio" id="isYes" name="isAdmin" value="1" checked/><label for="isYes">是</label>
							<input type="radio" id="isNo" name="isAdmin" value="0" /><label for="isNo">否</label>
							</c:when>
							<c:otherwise>
							<input type="radio" id="Yes" name="isAdmin" value="1"/><label for="Yes">是</label>
							<input type="radio" id="No" name="isAdmin" value="0"  checked/><label for="No">否</label>
							</c:otherwise>
						</c:choose>
							 
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">账号是否可用:</label>
						<div class="col-md-4">
						<c:choose>
							 <c:when test="${user.enabled}">
							 <input type="radio" id="isY" name="enabled" value="1" checked/><label for="isY">是</label>
							 <input type="radio" id="isN" name="enabled" value="0" /><label for="isN">否</label>
							 </c:when>
							 <c:otherwise>
							 <input type="radio" id="isUseable" name="enabled" value="1"/><label for="isUseable">是</label>
							 <input type="radio" id="noUseable" name="enabled" value="0"  checked/><label for="noUseable">是</label>
							 </c:otherwise>
							 </c:choose>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">帐号是否锁定:</label>
						<div class="col-md-4">
						<c:choose>
							 <c:when test="${user.locked }">
							 <input type="radio" id="isLocked" name="locked" value="1" checked/><label for="isLocked">是</label>
							 <input type="radio" id="noLocked" name="locked" value="0"/><label for="noLocked">否</label>
							 </c:when>
							 <c:otherwise>
							 <input type="radio" id="isLo" name="locked" value="1"/><label for="isLo">是</label>
							 <input type="radio" id="nolo" name="locked" value="0"  checked/><label for="noLo">否</label>
							 </c:otherwise>
							 </c:choose>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">帐号是否过期:</label>
						<div class="col-md-4">
						<c:choose>
							 <c:when test="${user.expired }">
							 <input type="radio" id="isexpired" name="expired" value="1" checked/><label for="isexpired">是</label>
							 <input type="radio" id="noexpired" name="expired" value="0" /><label for="noexpired">否</label>
							 </c:when>
							 <c:otherwise>
							 <input type="radio" id="Isexpired" name="expired" value="1"/><label for="Isexpired">是</label>
							 <input type="radio" id="Noexpired" name="expired" value="0"  checked/><label for="Noexpired">否</label>
							 </c:otherwise>
							 </c:choose>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 是否登录过系统:</label>
						<div class="col-md-4">
							 <c:choose>
							 <c:when test="${user.firstLogined}">
							 <input type="radio" id="isLogined" name="firstLogined" value="1" disabled checked/><label for="isLogined">是</label>
							 <input type="radio" id="noLogined" name="firstLogined" value="0"  disabled/><label for="noLogined">否</label>
							 </c:when>
							 <c:otherwise>
							 <input type="radio" id="IsLogin" name="firstLogined" value="1" disabled/><label for="IsLogin">是</label>
							 <input type="radio" id="NoLogin" name="firstLogined" value="0"  disabled checked/><label for="NoLogin">否</label>
							 </c:otherwise>
							 </c:choose>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">凭据过期:</label>
						<div class="col-md-4">
						<c:choose>
							 <c:when test="${user.credentialsExpired}">
							 <input type="radio" id="isExpired" name="credentialsExpired" value="1" checked/><label for="isExpired">是</label>
							 <input type="radio" id="noExpired" name="credentialsExpired" value="0" /><label for="noExpired">否</label>
							 </c:when>
							 <c:otherwise>
							 <input type="radio" id="isExpire" name="credentialsExpired" value="1"/><label for="isExpire">是</label>
							 <input type="radio" id="noExpire" name="credentialsExpired" value="0" checked/><label for=noExpire"">否</label>
							 </c:otherwise>
							 </c:choose>
						</div>
					</div>
				</div>
		
			<div class="panel-footer">
				<div class=" col-md-offset-2">
				<input id="modify_btn" class="btn btn-default" type="button"
					value="提交" onclick="updateUser()"/>&nbsp; <input id="cancel_btn" class="btn btn-default"
					type="button" value="关闭" onclick="goBack()" />
				</div>
			</div>
	</form>
</div>


	<script>
		var setting = {
			view : {
				selectedMulti : false
			},
			edit : {
				drag : {
					autoExpandTrigger : true,
					prev : dropPrev,
					inner : dropInner,
					next : dropNext
				},
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
				onExpand : onExpand,
				beforeRemove : beforeRemove
			}
		};
		
		function updateUser(){
			var values = $("#inputForm").serialize();
			$.ajax({
				type : "post",
				data : values,
				url : "${ctx}/admin/sec/user/${action}" ,
				success : function(msg) {
					parent.location.reload();
					//parent.$.fancybox.close();
				}
			});
		}
		
		function goBack(){
			parent.location.reload();
		}
		
		function callback(data){
			$("#loginNameTip").html(data);
		}

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
						url : "${ctx}/admin/sec/roleCategory/create",
						data : "parent.id=" + treeNode.id,
						success : function(msg) {
							var zTree = $.fn.zTree
									.getZTreeObj("roleCategoryTree");
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
			className = (className === "dark" ? "" : "dark");
			showLog("[ " + getTime()
					+ " beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:"
					+ moveType);
			showLog("target: " + (targetNode ? targetNode.name : "root")
					+ "  -- is "
					+ (isCopy == null ? "cancel" : isCopy ? "copy" : "move"));
			return true;
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
			var zTree = $.fn.zTree.getZTreeObj("roleCategoryTree");
			zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger")
					.attr("checked");
		}

		function beforeClick(treeId, treeNode, clickFlag) {
			copyNodePropertyToForm(treeNode);
			var zTree = $.fn.zTree.getZTreeObj("roleCategoryTree");
			zTree.selectNode(treeNode, treeId);
			return false;
		}
		function copyNodePropertyToForm(treeNode) {

		}

		function beforeRemove(treeId, treeNode) {
			$.ajax({
				type : "POST",
				url : "${ctx}/admin/sec/roleCategory/delete/" + treeNode.id,
				success : function(msg) {
					var zTree = $.fn.zTree.getZTreeObj("roleCategoryTree");
					zTree.removeNode(treeNode);
				}
			});
			return false;
		}
		$("#submit_btn").click(function(){
			var roles = $("#roles_id").val();
			$("#roleIds").val(roles);
			$("#inputForm").submit();
		});
		

		
		
		$(document).ready(
				function() {
					$.getJSON("${ctx}/admin/sec/roleCategory/get-tree?"
							+ new Date(), function(data) {
						var zNodes = data;
						$.fn.zTree
								.init($("#roleCategoryTree"), setting, zNodes);
						$("#callbackTrigger").bind("change", {}, setTrigger);
					});
					

					$("#roles_id").multiSelect();
					var matchRoles = "${matchRoles}";
					var roles = matchRoles.split(",");
					for (var i = 0; i < roles.length; i++) {
						$("#roles_id").multiSelect('select', [ roles[i] ]);
					}
					var obj = $("#action").val();
					if (obj === 'update') {
						$("#loginName").attr("disabled", "false");
					}
					//聚焦第一个输入框
					$("#name").focus();
					//为inputForm注册validate函数
					$("#inputForm").validate({
						rules : {
							loginName : {
								required : true,
								remote : {
									url : "${ctx}/admin/sec/user/check",
									type : "post",
									dataType : "json",
									data : {
										loginName : function() {
											return $("#loginName").val();
										},
										initalname : "${user.account}"
									}
								}

							}
						}
					});

				});
	</script>
</body>
</html>
