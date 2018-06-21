<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>字段管理</title>
<meta name="decorator" content="admin-popup">
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>
			${message}
		</div>
	</c:if>
	<div class="btn-group">
		<button class="btn btn-default"
			onclick="location='${ctx}/admin/codegen/fieldset/${fieldSetId}/field/create'">新增</button>
		<button class="btn btn-default" type="button" id="remove">删除</button>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-10">
					<form class="form-inline" action="#">
						<div class="form-group">
							<label for="name">名称：</label> <input type="text"
								name="search_LIKE_name" id="name" class="input-medium"
								value="${param.search_LIKE_name}">
						</div>
						<div class="form-group">
							<label for="label">标签：</label> <input type="text"
								name="search_LIKE_label" id="label" class="input-medium"
								value="${param.search_LIKE_label}">
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
	<div class="panel panel-info">
		<div class="panel-heading">代码明细</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>字段名</th>
					<th>字段标签</th>
					<th>数据类型</th>
					<th>控件类型</th>
					<th>是否可见</th>
					<th>只读</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="td">
				<c:forEach items="${fields.content}" var="field">
					<tr>
						<td><input type="checkbox" value="${field.id}" name="box" /></td>
						<td>${field.name}</td>
						<td>${field.label}</td>
						<td><tags:dict itemCode="${field.dataType}"
								dictCode="dataType" type="label" /></td>
						<td><tags:dict itemCode="${field.control}" dictCode="control"
								type="label" /></td>
						<td><tags:dict itemCode="${field.visible}" dictCode="bool"
								type="label" /></td>
						<td><tags:dict itemCode="${field.readOnly}" dictCode="bool"
								type="label" /></td>
						<td><a
							href="${ctx}/admin/codegen/fieldset/${fieldSetId}/field/update/${field.id}">编辑</a>
							<a onclick='deleteFieldSet(${fieldSetId},${field.id});'>删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="collapse navbar-collapse">
		<div class="nav navbar-nav navbar-right">
			<tags:pagination page="${fields}" paginationSize="5" />
		</div>
	</div>
	<script>
	function deleteFieldSet(fieldSetId,id) {
		var r = window.confirm("确定要删除吗？");
		if(r){
			window.location.href="${ctx}/admin/codegen/fieldset/" + fieldSetId+"/field/delete/"+id;
		}
	}
	</script>

	<script>
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
				var r = window.confirm("确定要删除吗？");
				if(r){
					arr = arr.substring(0, arr.length - 1);
					location.href = "${ctx}/admin/codegen/fieldset/${fieldSetId}/field/deleteAll/" + arr;
				};
			};
		});
	</script>

</body>
</html>
