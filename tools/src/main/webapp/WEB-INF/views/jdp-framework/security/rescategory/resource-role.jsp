<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="decorator" content="admin-popup" />

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript" src="${ctx }/static/lib/jdp/jquery/jquery.multi-select.js"></script>
<link href="${ctx }/static/lib/jdp/jquery/css/multi-select.css" rel="stylesheet"></link>
<div>
	<c:if test="${not empty error}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>
			${error}
		</div>
	</c:if>
	<c:if test="${empty error}">
	<div class="panel panel-info">
			<div class="panel-heading">资源分配给角色</div>
			<div class="panel-body">
				<div>
					<select id="roleIds" multiple="multiple" name="roles_id" size="6" >
						<c:forEach var="role" items="${roles}">
							
							<option value="${role.id}" <c:if test='${not empty matchRoleIds[role.id] }'>selected</c:if> >${role.name}(${role.category.name})</option>
						</c:forEach> 
					</select>
				</div>
			</div>
			<div class="panel-footer">
				<button class="btn btn-default" onclick="save()">保存</button>
			</div>
		</div>
	<script type="text/javascript">
		function save(){
			var roleIds = $("#roleIds").val();
			if(roleIds == null){
				roleIds="";
			}
			$.post("${ctx}/admin/sec/rescategory/resource2Role/${resource.id }?roleIds="+roleIds,function(msg){
				if(msg == 'success'){
					parent.$.fancybox.close();
				}else{
					alert("保存失败");
				}
			});
		}
		$(document).ready(function() {
			$("#roleIds").multiSelect({
	   		 	selectableHeader: "<div class='custom-header'>可选角色</div>",
			 	selectionHeader: "<div class='custom-header'>已选角色</div>"
			});
		});
	</script>
	</c:if>
</div>

