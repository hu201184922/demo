<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="decorator" content="admin-popup"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<SCRIPT type="text/javascript">
<!--
	var data;
	function getValue(){
		return data;
	}

	function chooseOne(id,name){
		data = {};
		data.id = id;
		data.name = name;
		parent.$.fancybox.close();
	}
	$(document).ready(function() {
		
	});
//-->
</SCRIPT>

<div>
	<div class="panel panel-info">
		<div class="panel-heading">用户信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th>用户名</th>
					<th>隶属部门</th>
					<th>岗位</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.person.org.name }</td>
						<td>${user.person.post.name }</td>
						<td><a class="candidate" href="javascript:void(0)" onclick="chooseOne('${user.id}','${user.name}')">选中</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
