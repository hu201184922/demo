<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title></title>
<meta name="decorator" content="admin-popup" />
<SCRIPT type="text/javascript">
var pageDatas = "";
	function dataSave(obj) {
		
		var values = document.getElementById("selectedValue").options;
		for (var i = 0; i < values.length; i++) {
			pageDatas = pageDatas + "<option value='"+values[i].value+"' > "+values[i].text+"</option>";
		}
		obj.href = obj.href + "pageDatas=" + pageDatas;
	}
	function chooseOne(id, name) {
		var values = document.getElementById("selectedValue").options;
		for (var i = 0; i < values.length; i++) {
			if (values[i].value == id) {
				return false;
			}
		}
		$("#selectedValue").append(
				'<option value="'+id+'">' + name + '</option>');
		pageDatas += "<option value='" +id+ "' > " +name+ "</option>";
	};

	var data;
	function setUser() {
		var options = document.getElementById("selectedValue").options;
		data = [];

		for (var i = 0; i < options.length; i++) {
			var item = {
				id : options[i].value,
				name : options[i].text
			};
			data.push({
				id : options[i].value,
				name : options[i].text
			});
		}
		parent.$.fancybox.close();
		return false;
	}

	function getValue() {
		return data;
	}

	$(document).ready(function() {
	});
//-->
</SCRIPT>
</head>

<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-10">
					<form class="form-inline" action="#" role="form">
						<div class="form-group">
							<label for="name">用户名：</label> <input type="text" id="name"
								name="search_LIKE_name" class="form-control input-sm"
								value="${param.search_LIKE_name}">
						</div>
						<div class="form-group">
							<label for="person.org.name">隶属部门：</label> <input type="text"
								id="person.org.name" name="search_LIKE_person.org.name"
								class="form-control input-sm"
								value="${param.search_LIKE_person.org.name}">
						</div>
						<button type="submit" class="btn btn-default" id="search_btn">查询</button>
					</form>
				</div>
				<div class="col-md-2">
					<tags:sort />
				</div>
			</div>
		</div>
	</div>
	<div>
		<div class="panel panel-info">
			<div class="panel-heading">用户信息</div>

			<div class="panel-body">
				<div class="col-md-8">
					<div class="panel panel-info">
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
								<!--  
							<c:forEach items="${userList}" var="user">
								<tr>
									<td>${user.name}</td>
									<td>${user.person.org.name }</td>
									<td>${user.person.post.name }</td>
									<td><a class="candidate" href="javascript:void(0)" onclick="chooseOne('${user.id}','${user.name}')">选中</a></td>
								</tr>
							</c:forEach>
						-->
								<c:forEach items="${users.content}" var="user">
									<tr>
										<td>${user.name}</td>
										<td>${user.person.org.name }</td>
										<td>${user.person.post.name }</td>
										<td><a class="candidate" href="javascript:void(0)"
											onclick="chooseOne('${user.id}','${user.name}')">选中</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="collapse navbar-collapse">
						<div class="nav navbar-nav navbar-right">
							<tags:pagination page="${users}" paginationSize="5" />
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="row">
						<select class="col-md-6" multiple="multiple" size="8"
							id="selectedValue"
							ondblclick="this.removeChild(this.options[this.selectedIndex])">
							${pageDatas}
						</select>
					</div>
				</div>
			</div>
			<!-- footer -->
			<div class="panel-footer">
				<div class=" col-md-offset-8">
					<input class="btn btn-primary" type="button" value="确定"
						onclick="setUser()" />
				</div>
			</div>
		</div>
	</div>

</body>

</html>
