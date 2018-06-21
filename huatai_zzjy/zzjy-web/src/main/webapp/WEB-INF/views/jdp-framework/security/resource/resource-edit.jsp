<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta name="decorator" content="admin-popup">
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
<script type="text/javascript">
	function closeFrame(){
		parent.$.fancybox.close();
	}
	function saveAndClose(){
		var validator = $("#inputForm").validate();
		if(validator.form()){
			$.post("${ctx}/admin/sec/resource/save-close",$("#inputForm").serialize(),function(msg){
				if(msg=='success'){
					parent.$.fancybox.close();
				}else{
					alert("保存失败");
				}
			});
		}
	}
</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/admin/sec/resource/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${resource.id}"/>
		<input type="hidden" id="categoryIds" name="categoryIds"/>
		<input type="hidden" name="action" id="action" value="${action}"/>
		<input type="hidden" name="initalname" id="initalname" value="${resource.name}"/>
		<input type="hidden" name="initalresString" id="initalresString" value="${resource.resString}"/>
		<input type="hidden" name="initalperString" id="initalperString" value="${resource.perString}"/>
		<div class="panel panel-info">
			<div class="panel-heading">填写资源信息</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="name" class="col-md-2 control-label">名称:</label>
					<div class="col-md-4">
						<input type="text" value="${resource.name}" class="form-control input-sm required" name="name" id="name"/>
					</div>
				</div>
				<div class="form-group">
					<label for="resType" class="col-md-2 control-label">类型:</label>
					<div class="col-md-4">
						<select name="resType" id="resType" class="form-control input-sm required">
						  <option value=""></option>
	                      <option value="U">URL</option>
	                      <option value="E">ELEMENT</option>
	                      <option value="M">METHOD</option>
	                    </select>
					</div>
				</div>
				<div class="form-group">
					<label for="resString" class="col-md-2 control-label">唯一字符串:</label>
					<div class="col-md-4">
						<input type="text" id="resString" name="resString" class="form-control input-sm required" value="${resource.resString}" />
					</div>
				</div>
				<div class="form-group">
					<label for="perString" class="col-md-2 control-label">授权字符串:</label>
					<div class="col-md-4">
						<input type="text" id="perString" name="perString" class="form-control input-sm required" value="${resource.perString}"/>
					</div>
				</div>
				<div class="form-group">
					<label for="res_enabled" class="col-md-2 control-label">可用:</label>
					<div class="col-md-4">
						<c:choose>
							<c:when test="${resource.enabled  eq true}">
								<input type="radio" id="res_enabledYes" name="enabled" value="true" checked/><label for="res_enabledYes">是</label>
								<input type="radio" id="res_enabledNo" name="enabled" value="false" /><label for="res_enabledNo">否</label>
							</c:when>
							<c:otherwise>
								<input type="radio" id="res_enabledYes" name="enabled" value="true" /><label for="res_enabledYes">是</label>
								<input type="radio" id="res_enabledNo" name="enabled" value="false" checked/><label for="res_enabledNo">否</label>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group">
					<label for="descript" class="col-md-2 control-label">描述:</label>
					<div class="col-md-4">
						<textarea id="descript" class="form-control input-sm" rows="3" name="descript">${resource.descript}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="resourceCategoryTree" class="col-md-2 control-label">资源分类:</label>
					<div class="col-md-4">
						<div id="resourceCategoryTree" class="ztree"></div>
					</div>
				</div>
			</div>
			
			<div class="panel-footer">
				<div class="col-md-offset-2">
					<input id="submit_btn" class="btn btn-default" type="submit" value="保存并新增"/>
					<input id="submit_btn" class="btn btn-default" type="button" value="保存并关闭" onclick="saveAndClose()"/>
					<input id="cancel_btn" class="btn btn-default" type="button" value="关闭" onclick="closeFrame();"/>
				</div>
			</div>
		 </div>
	</form>
   <script>

   var setting = {
			view: {
				selectedMulti: false
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				radioType: "level",
				chkboxType: { "Y": "s", "N": "s" }

			},
			edit : {
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
				onCheck:onCheck
			}
		};
		function onCheck(){
			 var zTree = $.fn.zTree.getZTreeObj("resourceCategoryTree");
			 var nodes=zTree.getCheckedNodes(true);
			 var o=[];
			 for(var i=0;i<nodes.length;i++){
				 o.push(nodes[i].id);
			 }
			 $("#categoryIds").val(o.join(","));
		}
		
		function setTrigger() {
			var zTree = $.fn.zTree.getZTreeObj("resourceCategoryTree");
			zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr(
					"checked");
		}

		function beforeClick(treeId, treeNode, clickFlag) {
			var zTree=$.fn.zTree.getZTreeObj("resourceCategoryTree");
			zTree.selectNode(treeNode,treeId);
			return false;
		}
		
	$(document).ready(function() {
		    $("select").val("${resource.resType}");
			$("#name").focus();
			$("#inputForm").validate({
				rules:{
					name:{
						remote:{
							url:"${ctx}/admin/sec/resource/check",
							type:"post",
							dataType: "json", 
							data:{
								name:function(){
									return $("#name").val();
								},
								initalname:function(){
									return $("#initalname").val();
								}
							}
						}
					},
					resString:{
						remote:{
							url:"${ctx}/admin/sec/resource/checkRes",
							type:"post",
							dataType: "json", 
							data:{
								resString:function(){
									return $("#resString").val();
								},
								initalRestring:function(){
									return $("#initalresString").val();
								}
							}
						}
					},
					perString:{
						remote:{
							url:"${ctx}/admin/sec/resource/checkPer",
							type:"post",
							dataType: "json", 
							data:{
								perString:function(){
									return $("#perString").val();
								},
								initalPerstring:function(){
									return $("#initalperString").val();
								}
							}
						}
					}
					
					
				}
			});
			
			//加载资源分类树
			$.getJSON("${ctx}/admin/sec/rescategory/get-tree?"+new Date(), function(data) {
				var zNodes = data;
				$.fn.zTree.init($("#resourceCategoryTree"), setting, zNodes);
				$("#callbackTrigger").bind("change", {}, setTrigger);
			    var resids="${resourceCategoryIds}";
	            var ids=resids.split(",");
	            var treeObj = $.fn.zTree.getZTreeObj("resourceCategoryTree");
	            for(var i=0;i<ids.length;i++){
	               var node = treeObj.getNodeByParam("id", ids[i], null);
	               treeObj.checkNode(node,true,false,false);
	            }
			});
		});
	
	</script>

</body>
</html>