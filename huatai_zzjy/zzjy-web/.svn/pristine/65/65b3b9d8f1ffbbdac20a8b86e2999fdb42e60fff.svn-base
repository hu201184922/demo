
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<meta name="decorator" content="nodecorate" />
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>首选项定义</title>

<script type="text/javascript">
function deleteDefinition(id) {
		if (confirm("确定要删除选项定义吗？")) {
			$.ajax({
				type : "post",
				url : "${ctx}/admin/preference/def/definition/delete/" + id,
				success : function(msg) {
					var categoryId = $("#category_id").val();
					loadDefinitions(categoryId);
				}
			});
		}
	}
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
</script>

</head>
<body>
	<div class="panel panel-info">
	<div class="panel-heading">首选项管理</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>名称</th>
					<th>代码</th>
					<th>数据类型</th>
					<th>控件类型</th>
					<th>排序号</th>
					<th>个性化</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="td">
				<c:forEach items="${preferenceDefinitions}" var="definition">
					<tr>
						<td><input type="checkbox" value="${definition.id}"
							name="box" /></td>
						<td>${definition.name}</td>
						<td>${definition.code}</td>
						<td><tags:dict itemCode="${definition.dataType}"
								dictCode="dataType" type="label" /></td>
						<td><tags:dict itemCode="${definition.editorType}"
								dictCode="editorType" type="label" /></td>
						<td>${definition.sortIndex}</td>
						<!--  <td>${definition.overwritable ==true?"是":"否"}</td>-->
					    <td>
					    <c:choose>
					   
					    <c:when test="${isOverwritable==true}">
					    	${definition.overwritable ==true?"是":"否"}
					    </c:when>
					    <c:otherwise>
					        	否
					    </c:otherwise>
					    
					    </c:choose>
					    
					    </td>
						<td><a data-fancybox-type="iframe" class="d"
							href="${ctx}/admin/preference/def/definition/edit/${definition.id}">编辑</a>
							<a onclick="deleteDefinition(${definition.id})">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>