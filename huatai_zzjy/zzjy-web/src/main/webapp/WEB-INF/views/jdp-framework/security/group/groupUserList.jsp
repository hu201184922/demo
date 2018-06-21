<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="decorator" content="nodecorate" />
<c:set var="ctx" value="${ pageContext.request.contextPath }" />

<div class="">
	<input type="hidden" id="groupId" value="${group.id }" />
	
	<div class="panel panel-info">
		<div class="panel-heading">组内用户信息&nbsp;——&nbsp;&nbsp;组长：<span id="leaderName">${group.leader.name }</span></div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>用户名</th>
					<th>隶属部门</th>
					<th>岗位</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td class="userTd"><input type="checkbox" value="${user.id}" name="box" /></td>
						<td>${user.name}</td>
						<td>${user.person.org.name }</td>
						<td>${user.person.post.name }</td>
						<td>
							<a href="javascript:void()" onclick="setLeader(${user.id},'${user.name}');">选为组长</a>
							<a href="javascript:void()" onclick="deleteSingleUser(${user.id})">移除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script>

$("#selectAll").click(function () { 
	var value = this.checked;
	if(value==true){
		$(".userTd :input").each(function(i){
			 this.checked=true;
		});
		
	}else{
		$(".userTd :input").each(function(i){
			 this.checked=false;
		});
	}	
});

</script>