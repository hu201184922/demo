<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta name="decorator" content="admin-popup" />
<script type="text/javascript"
	src="${ctx }/static/lib/jdp/jquery/jquery.multi-select.js"></script>
<link href="${ctx }/static/lib/jdp/jquery/css/multi-select.css"
	rel="stylesheet">
	

<div class="panel panel-info">
	<div class="panel-heading">分配角色</div>
	<div class="panel-body">
		<div class="form-group">
					<div class="col-md-6">
						<label class="col-md-5 contorl-label">待选框：</label> <label
							class="col-md-7 contorl-label">已选框：</label>
					</div>
				</div>
				<div class="form-group">
					<div class="">
						<select id="roles_id" multiple="multiple" name="roles_id" size="6"
							style="width: 200px" onclick="roleselect()">
							<c:choose>
								<c:when test="${empty matchRoles }">
									<c:forEach var="role" items="${roles}">
										<option value="${role.id}">${role.name}(${role.category.name})</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach var="unmatchRole" items="${unmatchRoles }">
										<option value="${unmatchRole.id}">${unmatchRole.name}(${unmatchRole.category.name})</option>
									</c:forEach>
									<c:forEach var="matchRole" items="${matchRoles }">
										<option value="${matchRole.id}" selected>${matchRole.name}(${matchRole.category.name})</option>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
				</div>
			</div>
			<div class="panel-footer">
				<div class="col-md-offset-5">
					<input class="btn btn-default" type="button" value="保存"
						onclick="save()" />
				</div>
			</div>
	</div>

</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#roles_id").multiSelect();
		var matchRoles = $("#roles_id").val();
		var roles = matchRoles.split(",");
		for ( var i = 0; i < roles.length; i++) {
			$("#roles_id").multiSelect('select', [ roles[i] ]);
		}
	});

	function save() {
 		
		var roleIds = $("#roles_id").val();
		if(roleIds == null){
			roleIds="";
		}
		$.ajax({
			type : "post",
			data : "roles_id="+roleIds+"&groupId=${group.id}",
			url : "${ctx}/admin/sec/group/selectRole",
			success : function(msg) {
				parent.location.href="${ctx}/admin/sec/group?groupId=${group.id}";
			}
		});
	}
</script>