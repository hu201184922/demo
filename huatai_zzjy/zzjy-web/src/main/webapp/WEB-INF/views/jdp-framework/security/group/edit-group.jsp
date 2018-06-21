<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${ pageContext.request.contextPath }" />
<meta name="decorator" content="admin-popup">
<html> 
	<head>
		<title>系统管理 &raquo; 区域配置 &raquo; 添加新区域</title>
	</head>
	<body>
		<div>
			<div class="container-fluid">
		  	<div class="row-fluid">
			<div class="span12">
				<h5 class="heading">团队信息</h5>
				<form id="inputForm" action="<c:url value="/admin/sec/group/update" />" method="post">
					<div id="hideFields">
						<input type="hidden" name="id" value="${group.id}"/>
						<input type="hidden" name="roleIds" id="roleIds"/>
						<input type="hidden" name="userIds" id="userIds"/>
					</div>

					<div class="formSep adapt-formSep">
					   <div class="row-fluid">
							<div class="span3">
								<label for="name">名称 <span class="f_req">*</span></label>
								<input type="text" id="name" name="name" class="span12 required" value="${group.name}"/>
							</div>
							<div class="span3">
								<label for="code">code <span class="f_req">*</span></label>
								<input type="text" id="code" name="code" class="span12 required" value="${group.code}"/>
							</div>
							<div class="span3">
								<label for="parentId">上级</label>
								<input type="hidden" id="parentId_hidden" value="${parent.id}" name="parent.id" />
								<input type="hidden" id="pid_hidden" value="${parent.id}" name="pid" />
								<c:if test='${ empty parent.name}'>
								<input type="text" id="parentId" readonly="readonly" data-toggle="tooltip" class="span12 showTeam" value="根节点" />
								</c:if>
								<c:if test='${not empty parent.name}'>
								<input type="text" id="parentId" readonly="readonly" data-toggle="tooltip" class="span12 showTeam" value="${parent.name}" />
								</c:if>
							</div>
							<div class="span3">
								<label for="sortIndex">显示顺序</label>
								<input type="text" id="sortIndex" name="sortIndex" class="span12 number" 
									data-toggle="tooltip" title="如：1,2,3..."  value="${group.sortIndex}"/>
							</div>
					   </div>
					   <div class="row-fluid">
							<div class="span6">
								<label for="descript">描述 </label>
								<textarea name="descript" id="descript" rows="7" class="span12"> ${group.descript}</textarea>
							</div>
					   </div>
					</div>
					<h5 class="heading">组内成员</h5>
					<div class="formSep adapt-formSep">
						<div class="row-fluid">
							<div id="userList" class="span9">
							</div>
							
							<div class="span3">
								<table class="data-tbl-striped table table-striped table-bordered dataTable">
								<thead><tr><th>姓名</th><th style="width:55px">组长</th></tr></thead>
								<tbody id="msTable" >
									<tr id="norecord"><td colspan="2">请选择团队成员</td></tr>
									<c:forEach items="${group.userGroups}" var="userGroup">
										<tr class="selection" id="selection${userGroup.user.id}" style="cursor:pointer"><td>${userGroup.user.name}</td>
											<td class="center selectedUserCol"><input class="selectedUserId" type="radio" id="check"
										 		name="leader.Id" value="${userGroup.user.id}" 
										 		<c:if test="${userGroup.user.id == group.leader.id}">checked</c:if>
										 	/></td>
										</tr>
									</c:forEach>
									<script>
										$(document).ready(function(){
											if($("#msTable .selection").get(0) == undefined) {
                       							$("#norecord").show();
                    						}else{
                    							$("#norecord").hide();
                    						}
										});
									</script>
								</tbody>
								</table>
							</div>
						</div>
					</div>
					<h5 class="heading">组内角色</h5>
					<div class="formSep adapt-formSep">
						<div class="row-fluid">
                			<select id="roles_id" multiple="multiple" onclick="roleselect()" >
                			<c:forEach var="role" items="${roles}">
                			<option value="${role.id}">${role.name}(${role.category.name})</option>
                			</c:forEach>
                			</select>
						</div>
					</div>
                    <div class="form-actions">
						<input type="button" id="submit_btn" class="btn btn-info submit" value="提交" />
						<input type="button" class="btn" onclick="window.location='<c:url value="/admin/sec/group" />'" value="取消" />
					</div>
					</form>
				</div>
			</div>
		    </div>
		</div>
		<script type="text/javascript">
			function bindRemoveClick(){
				$(".selection").unbind("click");
				$(".selection").click(function() {
                    $(this).remove();
                    if($("#msTable .selection").get(0) == undefined) {
                       	$("#norecord").show();
                    }
                });
                
                $(".selectedUserCol").click(function(e) {
                    e.stopPropagation();
                });
			}
			function initUsers(){
				$.get("${ctx}/admin/sec/group/users",function(result) {
		        	var item={};
		        	$("#userList").html(result);
                	$(".candidateUser").unbind("click");
                	$(".candidateUser").click(function() {
                   		var userId = $(this).attr("userId");
                    	var userName = $(this).attr("userName");
                    	var selectedUser = $("#selection"+userId);
                    	var leaderId = $("#msTable").attr("leaderId");
                    	if(selectedUser.get(0) == undefined){
                    		var o = ['<tr class="selection" id="selection',userId,'" style="cursor:pointer"><td>', userName, '</td>'];
                			o.push('<td class="center selectedUserCol"><input class="selectedUserId" type="radio" id="checkCell', userId,
                       			'" name="leader.Id" value="',userId,'" /></td></tr>');
                      		$("#msTable").append(o.join(""));
                       		$("#norecord").hide();
                       		bindRemoveClick();
                       		
               	 		}
           	    	});
                });
               
			}
			
			$(document).ready(function(){
				
				$("#roles_id").multiSelect();
				
				<c:forEach items="${matchRoles}" var="role">
					$("#roles_id").multiSelect('select', [ '${role.id}' ]);
				</c:forEach>
				
		        initUsers();
		        
		        bindRemoveClick();
		        
				$("#submit_btn").click(function(){
					var roles = $("#roles_id").val();
					var userIds=[];
					$(".selectedUserId").each(function(){
						userIds.push(this.value);
					});
					$("#userIds").val(userIds.join(","));
					$("#roleIds").val(roles);
					$("#inputForm").submit();
				});
			});
		</script>
	</body>
</html>