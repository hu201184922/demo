<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>岗位管理</title>
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
	<div class="col-md-2">
		<ul id="posttreemaintain" class="ztree"></ul>
	</div>
	<div class="col-md-10">	
	<div id="right">
	 <form id="inputForm" action="${ctx}/admin/party/post/update" method="post" class="form-horizontal">			
<div class="panel panel-info">
	<div class="panel-heading">岗位信息</div>
  <div class="panel-body">
	<input type="hidden" id="post_id" name="id" value=""/> 
	<input type="hidden" id="org_id" name="initalname"/>
	<input type="hidden" id="post_parentId" name="parent.id" value=""/>
	<input type="hidden" id="initalname" name="initalname"/>
	<div class="form-group">
			<label for="post_name" class="col-md-2 control-label">岗位名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control input-sm required"
						id="post_name" name="name">
					</div>
			<label for="orgName" class="col-md-2 control-label">部门：</label>
					<div class="col-sm-3">		
						<input type="hidden" name="organization"  id="orgId" class="required">
						<input type="text" class="form-control input-sm required org"  id="orgName" >			
					</div>		
					<input type="button" class="org btn btn-default"
							value="选择">
	</div>
	
			
	
	<div class="form-group">
			<label for="post_sortIndex" class="col-md-2 control-label">排序号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control input-sm required"
						id="post_sortIndex" name="sortIndex">
					</div>
	</div>
	<div class="form-group">
						<label for="post_descript" class="col-md-2 control-label">描述:</label>
						<div class="col-sm-9">
							<textarea id="post_descript" name="descript" class="form-control input-sm"></textarea>
						</div>
	</div>	
	 </div>
  <div class="panel-footer"><div class=" col-md-offset-5">
   <input id="submit_btn" class="btn btn-default" type="submit" value="保存" />
</div>
</div>	
</div>
</form>	
<div class="btn-group">
		<button type="button" class=" btn btn-default" data-fancybox-type="iframe" href="" onclick="selects()">选择</button>
		<button type="button" id="remove" class=" btn btn-default" >移除</button>
</div>
<div class="panel panel-info">
  <div class="panel-heading">岗位员工信息</div>
	<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>工号</th>
					<th>姓名</th>
					<th>出生日期</th>
					<th>性别</th>
					<th>学历</th>
					<th>邮箱</th>
					<th>电话</th>
					<th>手机号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody" class="td">
		   </tbody>
	</table>
</div>									
</div>	
</div> 	
<script type="text/javascript">
function selects(){
	  var postId=$("#post_id").val();
	   if(postId==""){
		   alert("请先选择相应的岗位");
		   return  false;
	   }
	   parent.$.fancybox.open({
			type		: "iframe",
			content 	: "<iframe scrolling='auto' class='fancybox-iframe' frameborder='0'" +
							"name ='selectMultiUsers-fancybox-iframe' id='selectMultiUsers-fancybox-iframe' height='100%' width='100%' " +
							"src='${ctx}/admin/party/person/person/multi' />",
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			beforeClose  : function(){
				
				if($.isFunction(parent.window.frames["selectMultiUsers-fancybox-iframe"].getValue)){
					var datas = 
						parent.window.frames["selectMultiUsers-fancybox-iframe"].getValue();
					
					if(datas){
						var ids=[];
						for(var i in datas){
							ids.push(datas[i].id);
						}
						location.href="${ctx}/admin/party/post/addPersons/"+postId+"?personIds="+ids.join(",");						
					}
				}
			}
		});
};
var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
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
			beforeClick  : beforeClick ,
			beforeDrag : beforeDrag,
			beforeDrop : beforeDrop,
			beforeDragOpen : beforeDragOpen,
			onDrag : onDrag,
			onDrop : onDrop,
			onExpand : onExpand,
			beforeRemove: beforeRemove
		}
	};
	
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
				   url: "${ctx}/admin/party/post/create",
				   data: "parent.id="+treeNode.id,
				   success: function(msg){
					   var zTree = $.fn.zTree.getZTreeObj("posttreemaintain");
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
		var zTree = $.fn.zTree.getZTreeObj("posttreemaintain");
		zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr(
				"checked");
	}
	$("#selectAll").click( function () { 
		var obj = document.getElementById("selectAll");  
		var value = obj.checked;
		if(value==true){
			$(".td :input").each(function(i){
				 this.checked=true;
				 
			});
			
		}else{
			$(".td :input").each(function(i){
				 this.checked=false;
			});
		}	
	});
	$("#remove").click(function(){
		var arr="";
		$(".td :input").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
			
		});
		if(arr.length==0){
			alert("您还没有选择要删除的对象");
		}else{
			var rs=window.confirm("确定要删除吗?");
			if(rs){
			arr=arr.substring(0,arr.length-1);
			location.href="${ctx}/admin/party/post/deletePersons/"+arr;
			}
		}
	});
	function beforeClick(treeId, treeNode, clickFlag) {
		copyNodePropertyToForm(treeNode);
		loadPostPerson(treeNode.id);
//  		findPostPerson(treeNode.id);
		var zTree=$.fn.zTree.getZTreeObj("posttreemaintain");
		zTree.selectNode(treeNode,treeId);
		return false;
	}
	function loadPostPerson(id){
		$("#tbody").load("${ctx}/admin/party/post/loadPostPerson/"+id,function(){
			$(".editResource").fancybox({
				fitToView	: false,
				width		: '80%',
				height		: '80%',
				autoSize	: false,
				closeClick	: false,
				openEffect	: 'none',
				beforeClose : function(){
					loadPostPerson(id);
				}
			
			});
			
			$(".resource2Role").fancybox({
				fitToView	: false,
				width		: '50%',
				height		: '65%',
				autoSize	: false,
				closeClick	: false,
				openEffect	: 'none'
			});
		});
	}
	function copyNodePropertyToForm(treeNode){
		$.ajax({
				   type: "POST",
				   url: "${ctx}/admin/party/post/findorg/"+treeNode.id,
 				   success: function(data){
 					   $("#orgId").val(data.id);//传回的id
 					   $("#orgName").val(data.name);//传回的名称name
 					   
 					   $("#post_id").val(treeNode.id);
 						$("#post_name").val(treeNode.name);
 						$("#post_parentId").val(treeNode.pId);
 						$("#initalname").val(treeNode.name);
 						$("#post_descript").val(treeNode.descript);
 						$("#post_sortIndex").val(treeNode.sortIndex);
 				   }
		});
		
	}
	function findPostPerson(id){
		if(id){
// 			$("#tbody").load("${ctx}/admin/party/post/find/"+id);
	 $("#tbody").empty();
		$.ajax({
			   type: "POST",
			   url: "${ctx}/admin/party/post/find/"+id,
			   success: function(data){
				 for(var i=0;i<data.length;i++){
				$("#tbody").append("<tr><td><input type='checkbox' id='"+data[i].id+"' value='"+data[i].id+"'/></td><td>"+data[i].code+"</td><td>"+data[i].name+"</td><td>"+data[i].birthday+
						"</td><td>"+data[i].sex+"</td><td>"+data[i].degree+"</td><td>"+data[i].email+
						"</td><td>"+data[i].phone+"</td><td>"+data[i].mobile+"</td>"
						+" <td><a type='button' onclick='deletePerson("+data[i].id+");'>移除</a></td></tr>"); 
				 }
					 
					
			   }
			});
		} 
	}
	function deletePerson(id){
		var r = window.confirm("确定要删除吗？");
		if(r){
			window.location.href="${ctx}/admin/party/post/deletePerson/"+id;
		};
	}
	function beforeRemove(treeId, treeNode){
		var r=window.confirm("确定要删除吗？");
		if(r){$.ajax({
			   type: "POST",
			   url: "${ctx}/admin/party/post/delete/"+treeNode.id,
			   success: function(msg){
				   var zTree = $.fn.zTree.getZTreeObj("posttreemaintain");
				   zTree.removeNode(treeNode);
				   alert("删除成功");
				   location.reload(false);
			   }
			});
		}
		return false;	   
	}
   $(document).ready(function() {
		jdp.selectSingleOrg("org", function(data){
			$("#orgId").val(data.id);
			$("#orgName").val(data.name);
		});
	   $(".a").fancybox({
			fitToView : false,
			width : '80%',
			height : '80%',
			autoSize : false,
			closeClick : false,
			openEffect : 'none',
			closeEffect : 'none',
		});
	$.getJSON("${ctx}/admin/party/post/get-tree?"+new Date(), function(data) {
		var zNodes = data;
		var zTree=$.fn.zTree.init($("#posttreemaintain"), setting, zNodes);
		$("#callbackTrigger").bind("change", {}, setTrigger);
		var postids="${postid2s}"; 
		if(postids!="")
		{loadPostPerson(postids);
		var tnode=zTree.getNodeByParam("id",postids,null);
		copyNodePropertyToForm(tnode);}
	});
	$("#inputForm").validate({
		rules:{
			sortIndex:{
				digits:true
			},
			name:{
				remote:{
					url:"${ctx}/admin/party/post/check",
					type:"post",
					dataType: "json", 
					data:{
						name:function(){
							return $("#post_name").val();
						},
						initalname:function(){
							return $("#initalname").val();
						}
					}
				}
			}
		}
	});
 });		
</script>
</body>
</html>