<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>字段集管理</title>
</head>

<body>
    <div class="btn-group">
		<button class="a btn btn-default" href="${ctx}/admin/codegen/fieldset/create" data-fancybox-type="iframe">新增</button>
		<button class="btn btn-default" type="button" id="remove">删除</button>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-10">
					<form class="form-inline" action="#" role="form">
						<div class="form-group">
							<label for="name">名称：</label> <input type="text" id="name" name="search_LIKE_name" class="form-control input-sm" value="${param.search_LIKE_name}">
						</div>
						<div class="form-group">
							<label for="className">domain类名：</label> <input type="text" id="className" name="search_LIKE_className" class="form-control input-sm" value="${param.search_LIKE_className}">
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
	<div class="panel-heading">代码生成</div>
	<table id="contentTable" class="table table-condensed table-hover table-striped table-responsive">
		<thead>
			<tr>
				<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
				<th>名称</th>
				<th>domain类名</th>
				<th>数据库表名</th>
				<th>Entity名称</th>
				<th>描述信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody  class="td">
		<c:forEach items="${fieldSets.content}" var="fieldSet">
			<tr>
				<td><input type="checkbox" value="${fieldSet.id}" name="box" /></td>
				<td>${fieldSet.name}</td>
				<td>${fieldSet.className}</td>
				<td>${fieldSet.tableName}</td>
				<td>${fieldSet.entityName}</td>
				<td>${fieldSet.descript}</td>
				<td>
					<a class="a btn" data-fancybox-type="iframe" href="${ctx}/admin/codegen/fieldset/update/${fieldSet.id}">编辑</a>
					<a class="a" data-fancybox-type="iframe" href="${ctx}/admin/codegen/fieldset/${fieldSet.id}/field">字段明细</a>
					<a type="button"  onclick='deleteFieldSet(${fieldSet.id});'>删除</a>
					<a class="codegen btn" data-fancybox-type="iframe" href="${ctx}/admin/codegen/create/${fieldSet.id}">生成代码</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="collapse navbar-collapse">
		<div class="nav navbar-nav navbar-right">
			<tags:pagination page="${fieldSets}" paginationSize="5" />
		</div>
	</div>
	
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
					location.href = "${ctx}/admin/codegen/fieldset/deleteAll/" + arr;
				};
			};
		});
	</script>
	
	<script>
	$(document).ready(function() {
		$(".a").fancybox({
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			afterClose : function() {
				window.location.reload();
			}
		});
		$(".codegen").fancybox({
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none'
		});
		jdp.selectMultiPersonsOfOrg("test",function(datas){
			
			if(datas){
				alert(datas[2].name);
			}
		});
	});
	function deleteFieldSet(id) {
		var r = window.confirm("确定要删除吗？");
		if(r){
			window.location.href="${ctx}/admin/codegen/fieldset/delete/" + id;
		}
	}
	</script>
</body>
</html>
