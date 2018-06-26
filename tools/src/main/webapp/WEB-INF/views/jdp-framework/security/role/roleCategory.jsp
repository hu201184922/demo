<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>角色分类管理</title>
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
				<button data-dismiss="alert" class="close">×</button>
				${message}
			</div>
		</c:if>
	</div>
	<div class="row"">
		<div class="col-md-2">
			<ul id="roleCategoryTree" class="ztree"></ul>
		</div>
		<div class="col-md-10">
			<form id="inputForm" action="${ctx}/admin/sec/roleCategory/update"
				method="post" class="form-horizontal">
				<div class="panel panel panel-info">
					<input type="hidden" id="roleCate_id" name="id" value="" /> 
					<input type="hidden" id="treeNode_id" name="treeNode_id"/>
					<input
						type="hidden" id="roleCate_parentId" name="parent.id" value="" />
					<!-- head -->
					<div class="panel-heading">角色类别信息</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="roleCate_name" class="col-md-2 control-label">角色类别名称</label>
							<div class="col-md-4">
								<input type="text" id="roleCate_name" name="name"
									class="form-control input-sm required">
							</div>
						</div>
						<div class="form-group">
							<label for="roleCate_sortIndex" class="col-md-2 control-label">排序号</label>
							<div class="col-md-4">
								<input type="text" id="roleCate_sortIndex" name="sortIndex"
									class="form-control input-sm required ">
							</div>
						</div>
						<div class="form-group">
							<label for="roleCate_descript" class="col-md-2 control-label">描述</label>
							<div class="col-md-4">
								<textarea id="roleCate_descript" name="descript"
									class="form-control input-sm required"></textarea>
							</div>
						</div>
					</div>
					<!-- footer -->
					<div class="panel-footer">
						<div class=" col-md-offset-2">
						<input id="add_btn" class="btn btn-default" type="submit"
								value="保存" />
						

						<input id="reset_btn" class="btn btn-default" type="reset" 
						value="重置"/>
						
						</div>
					</div>
				</div>
			</form>
			<div class="span12">
		<div class="nonboxy-widget">
			<div class="widget-head">
				<div class="btn-group ">
					<a type="button" class="a btn btn-default" data-fancybox-type="iframe"><span
								class="black-icons"></span>添加角色</a>
				    <button class="btn btn-default" type="button" id="remove">删除角色</button>
				</div>
			</div>
		</div>
	</div>
			<div class="panel panel-info">
			<div class="panel-heading">角色管理</div>
				<table id="contentTable"
					class="table table-condensed table-hover table-striped table-responsive">
					<thead>
						<tr>
						    <th><input type="checkbox" name="selectAll" id="selectAll" /></th>
							<th>角色类别</th>
							<th>名称</th>
							<th>角色标识</th>
							<th>备注</th> 
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody" class="td">
					
<%-- 					  <c:forEach items="${roles.content}" var="role"> --%>
<%-- 							<tr id="${role.id}">    --%>
<%-- 							    <td><input type=checkbox name="mm" value="${role.id}"/></td> --%>
<%-- 								<td>${role.category.name}</td> --%>
<%-- 								<td>${role.name}</td> --%>
<%-- 								<td>${role.code}</td> --%>
<%-- 								<td>${role.descript}</td> --%>
<%-- 							    <td> <a data-fancybox-type="iframe" class="b" href="${ctx}/admin/sec/role/update/${role.id}"> --%>
<%-- 							   编辑</a><a type="button" onclick="deleteRole(${role.id})"> 删除</a> --%>
										
										
<!-- 									</td>  -->
								
<!-- 							</tr> -->
<%-- 						</c:forEach>   --%>
					</tbody>
				</table>
			</div>
			</div>
			</div>
<!-- 			<div class="collapse navbar-collapse"> -->
<!-- 		<div class="nav navbar-nav navbar-right">		 -->
<!-- 	<ul class="pagination">		  -->
<%--                <tags:pagination page="${roles}" paginationSize="1" /> --%>
<!-- 	</ul></div></div>			 -->
<%-- 			<%-- <div class="row"> --%>
<%-- 				<tags:pagination page="${roles}" paginationSize="5" /> --%>
<%-- 			</div> --%>
<!-- 		</div> -->
<!-- 	</div> -->

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
			$("#treeNode_id").val(treeNode.id);
			copyNodePropertyToForm(treeNode);
			loadRoleItem(treeNode.id);
		    var zTree = $.fn.zTree.getZTreeObj("roleCategoryTree");
			zTree.selectNode(treeNode, treeId);
			return false;
		}
		function copyNodePropertyToForm(treeNode) {
			$("#roleCate_id").val(treeNode.id);
			$("#roleCate_parentId").val(treeNode.pId);
			$("#roleCate_name").val(treeNode.name);
			$("#initalname").val(treeNode.name);
			$("#roleCate_descript").val(treeNode.descript);
			$("#roleCate_sortIndex").val(treeNode.sortIndex);
		}

		function beforeRemove(treeId, treeNode) {
			//var roleCateid = document.getElementById("roleCate_sortIndex").value;
			if(treeNode.Id==1){
				alert("根节点不能删除");
				return false;
			}
			var r=window.confirm("确定要删除吗?");
			if(r){$.ajax({
				type : "POST",
				url : "${ctx}/admin/sec/roleCategory/delete/" + treeNode.id,
				success : function(msg) {
					var zTree = $.fn.zTree.getZTreeObj("roleCategoryTree");
					zTree.removeNode(treeNode);
					window.location.reload();
					
				}
			});}
			return false;
		}
		$(document).ready(
				function() {
					$.getJSON("${ctx}/admin/sec/roleCategory/get-tree?"
							+ new Date(), function(data) {
						var zNodes = data;
						$.fn.zTree
								.init($("#roleCategoryTree"), setting, zNodes);
						$("#callbackTrigger").bind("change", {}, setTrigger);
					});
					$("#inputForm").validate({
						
						rules : {
							descript:{required:false},
							sortIndex : {
								digits : true
							}
						}
					});
				});
		function addrole() {
			this.href = "${ctx}/admin/sec/role/createPage/";
			return true;
		};
		$(document).ready(function() {
			$(".a").fancybox({
				fitToView	: false,
				width		: '80%',
				height		: '80%',
				autoSize	: false,
				closeClick	: false,
				openEffect	: 'none',
				closeEffect	: 'none',
				beforeLoad : addrole,
 				afterClose:function(){
 					
					loadRoleItem($("#treeNode_id").val());
 				}
			});
		});
		$(document).ready(function() {
			$(".b").fancybox({
				fitToView	: false,
				width		: '80%',
				height		: '80%',
				autoSize	: false,
				closeClick	: false,
				openEffect	: 'none',
				closeEffect	: 'none',
				afterClose:function(){
					loadRoleItem($("#treeNode_id").val());
				}
			});
			loadRoleItem();
		});
		
		function deleteRole(id){
			var r=window.confirm("确认删除？");
			if(r){
				$.ajax({
					type:"post",
					url:"${ctx}/admin/sec/role/deleteRole/" + id,
					success:function(){
						loadRoleItem($("#treeNode_id").val());
					}
					
				});

			}
		}
		$("#selectAll").click(function() {
			var obj = document.getElementById("selectAll");
			var value = obj.checked;
			if (value == true) {
				$(".td :input").each(function(i) {
					this.checked = true;

				});

			} else {
				$(".td :input").each(function(i) {
					this.checked = false;
				});
			}
		});
		
// 		function saveRoleCategory(){
// 			var za=$("#inputForm").validate();
// 			if(za.form()){
// 				$.ajax({
// 					type:"post",
// 					url:"${ctx}/admin/sec/roleCategory/update",
// 					success : function(){
// 						loadRoleItem($("#treeNode_id").val());
// 					}
// 				});
				
// 			}
// 		}

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
				var r=window.confirm("确认删除？");
				if(r){
				arr = arr.substring(0, arr.length - 1);
				$.ajax({
					type:"get",
					url:"${ctx}/admin/sec/role/delete/" + arr,
					success:function(){
						loadRoleItem($("#treeNode_id").val());
					}
					});
				
				}
			}
		});
// 		function saveRolecategory(){

// 	    	var za=$("#inputForm").validate();
	    
	    	
// 	    	if(za.form()){
	    		
// 	    		var values = $("#inputForm").serialize();
// 			    $.ajax(
// 			    		{
// 				     type : "post",
// 				     data : values,
// 				     url : "${ctx}/admin/sec/roleCategory/update" 
//  				     success : function() 
//  				     {
//  				    	loadRoleItem($("#treeNode_id").val());
//  				      }
// 				     });
              
// 	    	}else
// 	    		$("#inputForm").submit();
// 		}
		
		 
	</script>
	<script type="text/javascript">
	 function loadRoleItem()
	{
		 $("#tbody").empty();
		$.ajax({ 
			type:"post",
			url:"${ctx}/admin/sec/role/getRoleArray/",
			success:function(data)
			{
				
				
				for ( var i = 0; i < data.length; i++)
				{
					

// 					$(".td").append("<tr><td><input type='checkbox' id='"+data[i].id+"' value='"+data[i].id+
// 							"' /></td><td>"+data[i].category_name+"</td><td>"+data[i].name+
// 							"</td><td>"+data[i].code+"</td><td>"+data[i].descript+
// 							"</td>"+"<td><a class='b'data-fancybox-type='iframe' href='${ctx}/admin/sec/role/update/"+data[i].id+"'>编辑</a>"+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a type='button' onclick='deleteRole("+data[i].id+");'>删除</a></td></tr>"); 

					$(".td").append("<tr><td><input type='checkbox' id='"+data[i].id+"' value='"+data[i].id+
							"' /></td><td>"+data[i].category_name+"</td><td>"+data[i].name+
							"</td><td>"+data[i].code+"</td><td>"+data[i].descript+
							"</td>"+"<td><a class='b'data-fancybox-type='iframe' href='${ctx}/admin/sec/role/update/"+data[i].id+"'>编辑</a>"+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a type='button' onclick='deleteRole("+data[i].id+");'>删除</a></td></tr>"); 
			
					
					
					



				} 
			}
		});
	}  
	
	</script>


</body>
</html>