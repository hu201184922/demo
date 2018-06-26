<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta name="decorator" content="admin-popup">
<html>
<head>
<title>角色新增</title>
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
	<form id="inputForm" action="${ctx}/admin/sec/role/update"
		method="post" class="form-horizontal">
		<div class="panel panel panel-info">
		<input type="hidden" name="category.id" id="catagory_id" value="${catagoryid}"> 
		<input type="hidden" name="resources_id" id="resources_id" />
		<input type="hidden" name="id" value="${role.id}">
		<input type="hidden" name="initialcode" id="initialcode"   value="${role.code}"/>
		<!-- head -->
			<div class="panel-heading">角色修改</div>
			<div class="panel-body">
			<div class="form-group">
			<label for="name" class="col-md-2 control-label">名称</label>
				<div class="col-md-4">
					<input type="text" name="name" id="name"  class="form-control input-sm required" value="${role.name}"/>
				</div>
				
			</div>
			<div class="form-group">
				<label for="code" class="col-md-2 control-label">角色标识</label>
				<div class="col-md-4">
					<input type="text" name="code" id="code"  class="form-control input-sm required" value="${role.code}"/>
				</div>
			</div>
			<div class="form-group">
				<label for="descript" class="col-md-2 control-label">备注</label>
				<div class="col-md-4">
					<textarea name="descript" id="descript" class="form-control input-sm required" value="${role.descript}"></textarea>
				</div>
			</div>
<!-- 		<fieldset> -->
<!-- 			<legend> -->
<!-- 				<small>资源分配</small> -->
<!-- 			</legend> -->
<!-- 			<div class="row offset1"> -->
<!-- 				<ul id="resourceTree" class="ztree"></ul> -->
<!-- 			</div> -->
<!-- 		</fieldset> -->
		</div>
		<!-- footer -->
		<div class="panel-footer">
			<div class=" col-md-offset-2">
			<input id="submit_btn" class="btn btn-default" type="submit" value="保存并新增"  />
			<input id="submit_btn" class="btn btn-default" type="button" onclick="saveChange(${role.id})"
							value="保存并关闭" />		
			<input id="cancel_btn" type="button" class="btn btn-default" value="关闭" onclick="closeChange()" /> 
			</div>
		</div>
		</div>
	</form>
	<script>
	var setting = {
			view: {
// 				addHoverDom: addHoverDom,
// 				removeHoverDom: removeHoverDom,
				selectedMulti: true
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				radioType: "level",
				chkboxType: { "Y": "s", "N": "s" }

			},
			edit : {
				drag : {
					autoExpandTrigger : true,
					prev : dropPrev,
					inner : dropInner,
					next : dropNext
				},
				enable : false,
				showAddBtn :false,
				showRemoveBtn : false,
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
				beforeRemove: beforeRemove,
				onCheck:onCheck
			}
		};
		function onCheck(){
			 var zTree = $.fn.zTree.getZTreeObj("resourceTree");
			 var nodes=zTree.getCheckedNodes(true);
			 var o=[];
			 for(var i=0;i<nodes.length;i++){
				 o.push(nodes[i].id);
			 }
			 $("#resources_id").val(o.join(","));
		}
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
					   url: "${ctx}/admin/sec/res/create",
					   data: "parent.id="+treeNode.id,
					   success: function(msg){
						   var zTree = $.fn.zTree.getZTreeObj("resourceTree");
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
			var zTree = $.fn.zTree.getZTreeObj("resourceTree");
			zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr(
					"checked");
		}

		function beforeClick(treeId, treeNode, clickFlag) {
			copyNodePropertyToForm(treeNode);
			var zTree=$.fn.zTree.getZTreeObj("resourceTree");
			zTree.selectNode(treeNode,treeId);
			return false;
		}
		function copyNodePropertyToForm(treeNode){
		}
		
		function beforeRemove(treeId, treeNode){
			$.ajax({
				   type: "POST",
				   url: "${ctx}/admin/sec/res/delete/"+treeNode.id,
				   success: function(msg){
					   var zTree = $.fn.zTree.getZTreeObj("resourceTree");
					   zTree.removeNode(treeNode);
				   }
				});
			return false;
		}
		   $(document).ready(function() {
				$.getJSON("${ctx}/admin/sec/res/get-tree?"+new Date(), function(data) {
					var zNodes = data;
					$.fn.zTree.init($("#resourceTree"), setting, zNodes);
					$("#callbackTrigger").bind("change", {}, setTrigger);
				    var resids="${resIds}";
		            var ids=resids.split(",");
		            var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
		            for(var i=0;i<ids.length;i++){
		               var node = treeObj.getNodeByParam("id", ids[i], null);
		               treeObj.checkNode(node,true,false,false);
		            }
				});
	 			$("#task_title").focus();
	 			//为inputForm注册validate函数
				$("#inputForm").validate({
					rules : {
						descript:{required:false,},
					code:{
						required : true,
 						remote:{
 						url:"${ctx}/admin/sec/role/checkCode",
 						type:"post",
 						dataType: "json", 
 						data:{
 							code:function(){
 								return $("#code").val();
 							},
 							initialcode:function(){
 								return $("#initialcode").val();
 							}			
 						}
 						}

						},
					name : {
							required : true,
// 							 						remote:{
// 							 						url:"${ctx}/admin/sec/role/check",
// 							 						type:"post",
// 							 						dataType: "json", 
// 							 						data:{
// 							 							name:function(){
// 							 								return $("#name").val();
// 							 							},
// 							 							initalname:""				
// 							 						}
// 							 						}

						}
					}
				});
		});
		    function saveChange(id){
		    	var za=$("#inputForm").validate();
			   if(za.form()){var values=$("#inputForm").serialize();
			   $.ajax({
				   type:"POST",
				   data:values,
				   url :"${ctx}/admin/sec/role/update",
				   success : function(msg) {
					   parent.$.fancybox.close();
					   
					
					}
				   
			   });}
			   else
				   $("#inputForm").submit();
		   }; 
		   function closeChange(){
			  
			   parent.$.fancybox.close();
		   };
	</script>
</body>
</html>
